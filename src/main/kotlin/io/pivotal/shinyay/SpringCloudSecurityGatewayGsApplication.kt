package io.pivotal.shinyay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.security.oauth2.gateway.TokenRelayGatewayFilterFactory

@SpringBootApplication
class SpringCloudSecurityGatewayGsApplication(val filterFactory: TokenRelayGatewayFilterFactory)

fun main(args: Array<String>) {
	runApplication<SpringCloudSecurityGatewayGsApplication>(*args)
}
