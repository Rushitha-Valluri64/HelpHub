package com.helphub.security;

import com.helphub.security.JwtRequestFilter;
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

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers(
            			    "/api/users/register",
            			    "/api/users/login",
            			    "/",
            			    "/home.html",
            			    "/about.html",
            			    "/login.html",
            			    "/signup.html",
            			    "/acceptor_update_delete.html",
            			    "/select-role.html",
            			    "/donor.html",
            			    "/donor_dashboard.html",
            			    "/thankyou.html",
            			    "/acceptor.html",
            			    "/acceptor_request.html",
            			    "/api/acceptor/update/**",
            			    "/api/acceptor/delete/**",
            			    "/api/donation/update/**",
            			    "/api/donation/delete/**",
            			    "/api/donation/by-email",
            			    "/api/acceptor/by-email",
            			    "/update_request.html",
            			    "/delete_request.html",
            			    "/view_requests.html",
            			    "/donor_dashoard",
            			    "/api/donation/all",
            			    "/donor_update_delete.html",
            			    "/donor_thankyou.html",
            			    "/acceptor_thankyou.html",
            			    "/api/acceptor/create",
            			    "/api/donation/donate/**", 
            			    "/api/acceptor/category/**",
            			    "/api/acceptor/all",
            			    "/api/donors/create",              // ✅ Add this line
            			    "/images/**",
            			    "/css/**",
            			    "/js/**"
            			).permitAll()

            	    .anyRequest().authenticated()
            	)

            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // ✅ JWT filter should be added *after* permitting public routes
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
