package io.pivotal.shinyay

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.security.oauth2.gateway.TokenRelayGatewayFilterFactory
import org.springframework.context.annotation.Bean

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
}

fun main(args: Array<String>) {
	runApplication<SpringCloudSecurityGatewayGsApplication>(*args)
}
