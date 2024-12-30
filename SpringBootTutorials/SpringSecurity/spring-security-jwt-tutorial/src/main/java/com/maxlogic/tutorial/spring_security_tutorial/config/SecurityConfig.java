package com.maxlogic.tutorial.spring_security_tutorial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.maxlogic.tutorial.spring_security_tutorial.auth.AuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthFilter authFilter) throws Exception {
        http.formLogin(form -> form.successForwardUrl("/generate-token").failureForwardUrl("/login-failure"))
                .authorizeHttpRequests(
                        authorize -> authorize
                                // Not allowing access to login end point for unauthenticated users, and added
                                // formLogin (UsernamePasswordAuthenticationFilter) instead, so that user trying
                                // to access login end point will be authentication using
                                // UsernamePasswordAuthenticationFilter, and we dont need to write logic in
                                // controller to authenticate user, instead we can just create JWT token.
                                // .requestMatchers(HttpMethod.POST, "/login").permitAll()
                                .requestMatchers("/actuator/**", "/login-failure").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails userDetails = User.withUsername("sourabh")
                .password(passwordEncoder.encode("sourabh"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Uses BCrypt algorithm to hash passwords
    }

}
