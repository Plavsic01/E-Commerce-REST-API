package com.plavsic.ecommerce.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csfr -> csfr.disable())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/api/auth/**").permitAll();
                    authorize.requestMatchers(HttpMethod.GET,"/api/users/{id}").hasAnyRole("USER","ADMIN");
                    authorize.requestMatchers(HttpMethod.GET,"/api/users/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.POST,"/api/users/**").permitAll(); // Register user
                    authorize.requestMatchers(HttpMethod.PUT,"/api/users/**").hasAnyRole("USER","ADMIN");
                    authorize.requestMatchers(HttpMethod.DELETE,"/api/users/**").hasAnyRole("USER","ADMIN");
                    authorize.requestMatchers(HttpMethod.GET,"/api/books/**").permitAll();
                    authorize.requestMatchers(HttpMethod.POST,"/api/books/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT,"/api/books/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.DELETE,"/api/books/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.GET,"/api/orders/**").hasAnyRole("USER","ADMIN");
                    authorize.requestMatchers(HttpMethod.GET,"/api/orders").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.POST,"/api/orders/**").hasAnyRole("USER","ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT,"/api/orders/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.DELETE,"/api/orders/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.GET,"/api/cart/**").hasAnyRole("USER","ADMIN");
                    authorize.requestMatchers(HttpMethod.POST,"/api/cart/**").hasAnyRole("USER","ADMIN");
                    authorize.requestMatchers(HttpMethod.DELETE,"/api/cart/**").hasAnyRole("USER","ADMIN");
                    authorize.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll();
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());

        http.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
