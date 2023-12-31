package com.datn.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.datn.api.exceptions.EntryPointExceptionHandler;
import com.datn.api.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;

	private final EntryPointExceptionHandler entryPointExceptionHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.exceptionHandling(exception -> exception.authenticationEntryPoint(entryPointExceptionHandler))
				.authorizeHttpRequests((request) -> request
						.requestMatchers(HttpMethod.GET, "/api/v1/auth/**", "/api/v1/users/**", "/api/v1/services/**",
								"/api/v1/hotels/**", "/api/v1/hotel/**", "/api/v1/user/**",
								"/oauth2/authorization/google")
						.permitAll().requestMatchers("/api/v1/post/**", "/api/v1/user/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll().anyRequest().authenticated())
				.oauth2Login(configurer -> configurer.defaultSuccessUrl("/api/v1/auth/login-google"))
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
