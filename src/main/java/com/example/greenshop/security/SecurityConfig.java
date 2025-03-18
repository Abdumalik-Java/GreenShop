package com.example.greenshop.security;

import com.example.greenshop.model.MD5Util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry
                    // User class
                    .requestMatchers(HttpMethod.GET, "/user").permitAll()
                    .requestMatchers(HttpMethod.GET, "/user/*").hasAnyRole("ADMIN", "SUPERADMIN")
                    .requestMatchers(HttpMethod.POST, "/user").hasRole("USER")
                    .requestMatchers(HttpMethod.PUT, "/user/*").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.DELETE, "/user/*").hasAnyRole("ADMIN", "USER")
                    // Address class
                    .requestMatchers(HttpMethod.GET, "/address").permitAll()
                    .requestMatchers(HttpMethod.GET, "/address/*").hasAnyRole("ADMIN", "SUPERADMIN")
                    .requestMatchers(HttpMethod.POST, "/address").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.PUT, "/address/*").hasAnyRole("ADMIN", "SUPERADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/address/*").hasAnyRole("ADMIN", "SUPERADMIN")
                    //Category class
                    .requestMatchers(HttpMethod.GET, "/category").permitAll()
                    .requestMatchers(HttpMethod.GET, "/category/*").hasAnyRole("SUPERADMIN", "ADMIN")
                    .requestMatchers(HttpMethod.POST, "/category").hasAnyRole("SUPERADMIN", "ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/category/*").hasAnyRole("ADMIN", "SUPERADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/category/*").hasAnyRole("ADMIN", "SUPERADMIN")
                    // Like class
                    .requestMatchers(HttpMethod.GET, "/like", "/like/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/like").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/like/*").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/like/*").hasRole("USER")
                    // Login class
                    .requestMatchers(HttpMethod.GET, "/login", "/login/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/login").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/login").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/login").permitAll()
                    // Order class
                    .requestMatchers(HttpMethod.GET, "/order", "/order/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/order").hasAnyRole("SUPERADMIN", "ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/order/*").hasAnyRole("SUPERADMIN", "ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/order/*").hasAnyRole("ADMIN", "USER")
                    // Payment class
                    .requestMatchers(HttpMethod.GET, "/payment", "/payment/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/payment").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/payment/*").hasAnyRole("ADMIN", "SUPERADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/payment/*").hasAnyRole("ADMIN", "USER")
                    // PriceRange class
                    .requestMatchers(HttpMethod.GET, "/priceRange", "/priceRange/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/priceRange").hasAnyRole("ADMIN", "SUPERADMIN")
                    .requestMatchers(HttpMethod.PUT, "/priceRange/*").hasAnyRole("ADMIN", "SUPERADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/priceRange/*").hasAnyRole("ADMIN", "SUPERADMIN")
                    // Product class
                    .requestMatchers(HttpMethod.GET, "/product", "/product/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/product").hasAnyRole("ADMIN", "SUPERADMIN")
                    .requestMatchers(HttpMethod.PUT, "/product/*").hasAnyRole("ADMIN", "SUPERADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/product/*").hasAnyRole("ADMIN", "SUPERADMIN")
                    // Shop class
                    .requestMatchers(HttpMethod.GET, "/shop", "/shop/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/shop").hasAnyRole("ADMIN", "SUPERADMIN")
                    .requestMatchers(HttpMethod.PUT, "/shop/*").hasAnyRole("ADMIN", "SUPERADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/shop/*").hasAnyRole("ADMIN", "SUPERADMIN")
                    // ShoppingCard class
                    .requestMatchers(HttpMethod.GET, "/shoppingCard", "/shoppingCard/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/shoppingCard").hasAnyRole("ADMIN", "SUPERADMIN")
                    .requestMatchers(HttpMethod.PUT, "/shoppingCard/*").hasAnyRole("ADMIN", "SUPERADMIN", "USER")
                    .requestMatchers(HttpMethod.DELETE, "/shoppingCard/*").hasAnyRole("ADMIN", "USER", "SUPERADMIN")
                    // Size class
                    .requestMatchers(HttpMethod.GET, "/size", "/size/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/size").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/size/*").hasAnyRole("ADMIN", "SUPERADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/size/*").hasAnyRole("ADMIN", "SUPERADMIN")
                    // Tag class
                    .requestMatchers(HttpMethod.GET, "/tag", "/tag/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/tag").hasAnyRole("ADMIN", "SUPERADMIN", "USER")
                    .requestMatchers(HttpMethod.PUT, "/tag/*").hasAnyRole("ADMIN", "SUPERADMIN", "USER")
                    .requestMatchers(HttpMethod.DELETE, "/tag/*").hasAnyRole("ADMIN", "USER", "SUPERADMIN")
                    // Total class
                    .requestMatchers(HttpMethod.GET, "/total", "/total/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/total").hasAnyRole("ADMIN", "SUPERADMIN")
                    .requestMatchers(HttpMethod.PUT, "/total/*").hasAnyRole("ADMIN", "SUPERADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/total/*").hasAnyRole("ADMIN", "SUPERADMIN")

                    .anyRequest()
                    .authenticated();
        }).formLogin(Customizer.withDefaults());

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));
        http.cors(cors -> cors.configurationSource(request -> {
            var config = new org.springframework.web.cors.CorsConfiguration();
            config.setAllowedOrigins(List.of("https://your-frontend.com")); // frontend domain
            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
            config.setAllowedHeaders(List.of("*"));
            return config;
        }));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String md5 = MD5Util.getMD5(rawPassword.toString());
                return md5.equals(encodedPassword);
            }
        };

    }

}