package com.employee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.employee.security.JwtAuthenticationEntryPoint;
import com.employee.security.JwtAuthenticationFilter;
import com.employee.service.UserInfoDetailsService;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint point;
	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public UserInfoDetailsService userDetailService() {
//		UserDetails userDetails = User.builder()
//				.username("Vishal21")
//				.password(passwordEncoder()
//				.encode("Vishal@21"))
//				.build();
//		return new InMemoryUserDetailsManager(userDetails);
		
		return new UserInfoDetailsService();
	}

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                		.requestMatchers("/users/**").permitAll()
        				.requestMatchers("/employees/**").authenticated()
        				.requestMatchers("/v1/config/test").authenticated()
        	            .requestMatchers("/auth/login/**").permitAll().anyRequest().authenticated()
        	            
        	        )
                .exceptionHandling(ex->ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager AuthenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}
}
