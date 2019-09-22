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
- `application.yml`

|Definition|Description|
|----------|-----------|
|registrationId|The ID that uniquely identifies the ClientRegistration<br>ex: gateway|
|client-id|The client identifier|
|client-secret|The client secret|
|client-authentication-method|The method used to authenticate the Client with the Provider<br>`basic` / `post`|
|authorization-grant-type|The OAuth 2.0 Authorization Framework defines four Authorization Grant types<br>**[UAA Authorization Grant Types](https://docs.cloudfoundry.org/uaa/uaa-concepts.html##auth-grant-types)**<br>`authorization_code`<br>`password`<br>`implicit`<br>`client_credentials`|
|redirect-uri-template||

## Installation

## Licence

Released under the [MIT license](https://gist.githubusercontent.com/shinyay/56e54ee4c0e22db8211e05e70a63247e/raw/34c6fdd50d54aa8e23560c296424aeb61599aa71/LICENSE)

## Author

[shinyay](https://github.com/shinyay)
