package io.pivotal.shinyay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringCloudSecurityGatewayGsApplication

fun main(args: Array<String>) {
	runApplication<SpringCloudSecurityGatewayGsApplication>(*args)
}
