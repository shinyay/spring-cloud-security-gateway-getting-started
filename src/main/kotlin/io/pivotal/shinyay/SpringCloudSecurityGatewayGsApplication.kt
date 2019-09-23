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
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@SpringBootApplication
class SpringCloudSecurityGatewayGsApplication(val filterFactory: TokenRelayGatewayFilterFactory) {

	val logger: Logger = LoggerFactory.getLogger(this.javaClass.name)

	@Bean
	fun routeLocator(builder: RouteLocatorBuilder): RouteLocator {
		return builder.routes()
				.route("resource") { r ->
					r.path("/resource")
							.filters { f -> f.filters(filterFactory.apply()) }
							.uri("http://resource:9000")}
				.build()
	}

	@GetMapping("/resource")
	fun resource(@AuthenticationPrincipal jwt: Jwt) {
	}
}

fun main(args: Array<String>) {
	runApplication<SpringCloudSecurityGatewayGsApplication>(*args)
}
