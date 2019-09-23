package io.pivotal.shinyay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.security.oauth2.gateway.TokenRelayGatewayFilterFactory

@SpringBootApplication
class SpringCloudSecurityGatewayGsApplication(val filterFactory: TokenRelayGatewayFilterFactory) {

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
