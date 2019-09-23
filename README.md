# Spring Cloud Gateway with OAuth2

- Token Relay pattern supported by OAuth 2.0
  - [Token Relay](https://cloud.spring.io/spring-cloud-static/spring-cloud-security/2.1.3.RELEASE/single/spring-cloud-security.html#_token_relay)

## Description

## Demo

## Features

- feature:1
- feature:2

## Requirement

## Usage
### Application Configuration
- `spring.security.oauth2.client.registration`

|Definition|Description|
|----------|-----------|
|registrationId|The ID that uniquely identifies the ClientRegistration<br>ex: gateway|
|client-id|The client identifier<br>UAAに登録したClient ID|
|client-secret|The client secret<br>UAAから取得したSecret|
|client-authentication-method|The method used to authenticate the Client with the Provider<br>`basic` / `post`|
|authorization-grant-type|The OAuth 2.0 Authorization Framework defines four Authorization Grant types<br>**[UAA Authorization Grant Types](https://docs.cloudfoundry.org/uaa/uaa-concepts.html##auth-grant-types)**<br>`authorization_code`<br>`password`<br>`implicit`<br>`client_credentials`|
|redirect-uri-template|The client’s registered redirect URI that the Authorization Server redirects the end-user’s user-agent to after the end-user has authenticated and authorized access to the client<br>ログイン後に認証サーバー(UAA)からリダイレクトするURL<br>DEFAULT: `{baseUrl}/login/oauth2/code/{registrationId}`|
|scope|The scope requested by the client during the Authorization Request flow, such as openid, email, or profile|

- `spring.security.oauth2.client.provider`

|Definition|Description|
|----------|-----------|
|authorization-uri|The Authorization Endpoint URI for the Authorization Server|
|token-uri|The Token Endpoint URI for the Authorization Server|
|jwkSetUri|The URI used to retrieve the JSON Web Key (JWK) Set from the Authorization Server<br>JWK contains the cryptographic key(s) used to verify the JSON Web Signature (JWS) of the ID Token and optionally the UserInfo Response|
|authenticationMethod|The authentication method used when sending the access token to the UserInfo Endpoint|
|user-name-attribute|The name of the attribute returned in the UserInfo Response that references the Name or Identifier of the end-user<br>ex: sub|


#### Cloud Foundry UAA
- [Client Grant Type for UAA](https://docs.cloudfoundry.org/uaa/uaa-concepts.html##select-type)

#### UAA Token

**User.id** is expressed as a “sub” claim in the tokens generated by UAA.

- [Standard Claims](https://openid.net/specs/openid-connect-core-1_0.html#StandardClaims)

```
$ uaac token decode eyJhbGciOiJIUzI1NiIsImtpZCI6ImxlZ2FjeS10b2tlbi1rZXkiLCJ0eXAiOiJKV1QifQ.eyJqdGkiOiIxOWYyNWU2Y2E5Y2M0ZWIyYTdmNTAxNmU0NDFjZThkNCIsInN1YiI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiY2xpZW50cy5yZWFkIiwiY2xpZW50cy5zZWNyZXQiLCJjbGllbnRzLndyaXRlIiwidWFhLmFkbWluIiwiY2xpZW50cy5hZG1pbiIsInNjaW0ud3JpdGUiLCJzY2ltLnJlYWQiXSwic2NvcGUiOlsiY2xpZW50cy5yZWFkIiwiY2xpZW50cy5zZWNyZXQiLCJjbGllbnRzLndyaXRlIiwidWFhLmFkbWluIiwiY2xpZW50cy5hZG1pbiIsInNjaW0ud3JpdGUiLCJzY2ltLnJlYWQiXSwiY2xpZW50X2lkIjoiYWRtaW4iLCJjaWQiOiJhZG1pbiIsImF6cCI6ImFkbWluIiwiZ3JhbnRfdHlwZSI6ImNsaWVudF9jcmVkZW50aWFscyIsInJldl9zaWciOiIyNjcxOTlkMSIsImlhdCI6MTUyODIzMjAxNywiZXhwIjoxNTI4Mjc1MjE3LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvdWFhL29hdXRoL3Rva2VuIiwiemlkIjoidWFhIiwiYXVkIjpbInNjaW0iLCJjbGllbnRzIiwidWFhIiwiYWRtaW4iXX0.L2cn6HqLQAEyqTrYYkL9Al_8JyfwB330er7DshUb9wg

Note: no key given to validate token signature

  jti: 19f25e6ca9cc4eb2a7f5016e441ce8d4
  sub: admin
  authorities: clients.read clients.secret clients.write uaa.admin clients.admin scim.write scim.read
  scope: clients.read clients.secret clients.write uaa.admin clients.admin scim.write scim.read
  client_id: admin
  cid: admin
  azp: admin
  grant_type: client_credentials
  rev_sig: 267199d1
  iat: 1528232017
  exp: 1528275217
  iss: http://localhost:8080/uaa/oauth/token
  zid: uaa
  aud: scim clients uaa admin
```

### Gateway Routing
#### Client Token Relay in Spring Cloud Gateway
- [Token Relay](https://cloud.spring.io/spring-cloud-static/Greenwich.RC2/single/spring-cloud.html#_client_token_relay_in_spring_cloud_gateway)
  - [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway) embedded reverse proxy then you can ask it to forward OAuth2 access tokens downstream to the services it is proxying

#### Route Configuration - Java
```
@Bean
fun routeLocator(builder: RouteLocatorBuilder): RouteLocator {
  return builder.routes()
      .route("resource") { r ->
        r.path("/resource")
            .filters { f -> f.filters(filterFactory.apply()) }
            .uri("http://resource:9000")}
      .build()
}
```

#### Route Configuration - YAML
```
spring:
  cloud:
    gateway:
      routes:
        - id: resource
          uri: http://resource:9000
          predicates:
            - Path=/resource
          filters:
            - TokenRelay=
```

## Installation

## Licence

Released under the [MIT license](https://gist.githubusercontent.com/shinyay/56e54ee4c0e22db8211e05e70a63247e/raw/34c6fdd50d54aa8e23560c296424aeb61599aa71/LICENSE)

## Author

[shinyay](https://github.com/shinyay)
