# Demonstration


1. Show application without dependency, i.e. everything is accessible
2. Add Spring security dependency, what change? Also use curl
3. Add the web security config to only block the sensitive path
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security
                .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/private/**").authenticated()
                .anyRequest().permitAll()
        ).build();
    }
}
```
4. Add a form login (see `LoginController`).
```java
security.formLogin((form) -> form
        .loginPage("/login").permitAll()
    );
```
5. For acessing the H2 console, a configurer is needed...
```java
@Bean
public WebSecurityCustomizer webSecurityCustomizer() {
    // Do not use in production !!!
    return (web) -> web.ignoring().requestMatchers("/h2-console/**");
}
```
6. Writing a custom `UserDetailsService` bean. User then needs to implement `UserDetails`. Also implement `CredentialsContainer`!
7. Requires to have `PasswordEncoder` bean. `NoOpPasswordEncoder.getInstance()` can be used if you do not want hashing. But better use `Bcrypt`.
8. Enable Basic Auth:
```java
security.httpBasic(Customizer.withDefaults())
```
10. Enable TLS via a reverse proxy:
For RSA key
```shell
sudo openssl req -x509 -nodes -days 365 -newkey rsa:2048 \
  -keyout selfsigned.key \
  -out selfsigned.crt
```
For ECC key
```shell
openssl req -new -newkey ec \
  -pkeyopt ec_paramgen_curve:prime256v1 -x509 \
  -nodes -days 365 -out selfsigned.crt -keyout selfsigned.key
```
To run nginx:
```shell
nginx -c <absolute path to>/nginx.conf
```
9. Run keycloak (either install locally or in a Docker container). Create a new realm with users, roles, and clients.
The client should be registered with a redirect to `{basePath}/login/oauth2/code/{configuration}`.
You may visit <http://localhost:8080/realms/{realm}/.well-known/openid-configuration> to inspect all the OpenId endpoints.
10. Add the oauth client library, by uncommenting the dependency:
```kotlin
implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
``` 
and set the following `application.properties`:
```properties
spring.security.oauth2.client.registration.keycloak.client-id={see keycloak}
spring.security.oauth2.client.registration.keycloak.client-secret={see keycloak}
spring.security.oauth2.client.registration.keycloak.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.keycloak.provider=keycloak
spring.security.oauth2.client.registration.keycloak.scope=openid
spring.security.oauth2.client.provider.keycloak.issuer-uri=http://localhost:8080/realms/dat250
```
Finally, the security filter chain has to be configured:
```java
security.oauth2Login(Customizer.withDefaults());
```
Maybe:
```java
  @Bean
    public CookieSameSiteSupplier applicationCookieSameSiteSupplier() {
        return CookieSameSiteSupplier.ofStrict();
    }

```
