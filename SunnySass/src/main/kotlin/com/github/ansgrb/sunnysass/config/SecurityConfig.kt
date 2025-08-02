package com.github.ansgrb.sunnysass.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {

	@Bean
	fun filterChain(http: HttpSecurity): SecurityFilterChain {
		http
			.csrf { it.disable() }
			.authorizeHttpRequests { authorize ->
				authorize
					.requestMatchers(
						"/swagger-ui.html",
						"/swagger-ui/**",
						"/api-docs",
						"/api-docs/**",
						"/api/weather/forecast"
					).permitAll()
					.requestMatchers(HttpMethod.POST, "/api/weather/forecast").permitAll()
					.anyRequest().authenticated()
			}
		return http.build()
	}
}