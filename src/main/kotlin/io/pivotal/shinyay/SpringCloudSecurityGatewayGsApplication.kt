package io.pivotal.shinyay

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.security.oauth2.gateway.TokenRelayGatewayFilterFactory
import org.springframework.context.annotation.Bean
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
@SpringBootApplication
class SpringCloudSecurityGatewayGsApplication(val filterFactory: TokenRelayGatewayFilterFactory) {

	val logger: Logger = LoggerFactory.getLogger(this.javaClass.name)

	@Bean
	fun routeLocator(builder: RouteLocatorBuilder): RouteLocator {
		return builder.routes()
				.route("resource") { r ->
					r.path("/resource")
							.filters { f -> f.filters(filterFactory.apply())
									.removeRequestHeader("Cookie")}
							.uri("http://resource:9000")}
				.build()
	}

	@GetMapping("/")
	fun index(model: Model,
			  @RegisteredOAuth2AuthorizedClient authorizedClient: OAuth2AuthorizedClient,
			  @AuthenticationPrincipal oAuth2User: OAuth2User): String {
		logger.trace("***** USERNAME: ${oAuth2User.name}")
		logger.trace("***** CLIENT_NAME: ${authorizedClient.clientRegistration.clientName}")
		logger.trace("***** USER_ATTRIBUTES: ${oAuth2User.attributes}")

		model.addAttribute("userName", oAuth2User.name)
		model.addAttribute("clientName", authorizedClient.clientRegistration.clientName)
		model.addAttribute("userAttributes", oAuth2User.attributes)
		return "index"
	}
}

fun main(args: Array<String>) {
	runApplication<SpringCloudSecurityGatewayGsApplication>(*args)
}
