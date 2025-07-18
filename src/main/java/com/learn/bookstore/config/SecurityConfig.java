package com.learn.bookstore.config;

import com.learn.bookstore.constants.BookEndPointsConstants;
import com.learn.bookstore.constants.SwaggerEndPointsConstants;
import com.learn.bookstore.constants.UserEndPointsConstants;
import com.learn.bookstore.constants.WelcomeEndPointsConstants;
import com.learn.bookstore.filters.JWTTokenGeneratorFilter;
import com.learn.bookstore.filters.JWTTokenValidatorFilter;
import com.learn.bookstore.models.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsConfig -> corsConfig.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("*"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(List.of("Authorization"));
                    config.setMaxAge(3600L);
                    return config;
                }))
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                WelcomeEndPointsConstants.WELCOME,
                                UserEndPointsConstants.LOGIN,
                                UserEndPointsConstants.REGISTER_USER,
                                UserEndPointsConstants.REGISTER_ADMIN
                        ).permitAll()
                        .requestMatchers(SwaggerEndPointsConstants.SWAGGER_WHITELIST).permitAll()
                        .requestMatchers(
                                UserEndPointsConstants.GET_USER_BY_ID,
                                UserEndPointsConstants.GET_ALL_USERS,
                                BookEndPointsConstants.CREATE_AUTHOR,
                                BookEndPointsConstants.UPDATE_AUTHOR_BY_ID,
                                BookEndPointsConstants.DELETE_AUTHOR_BY_ID,
                                BookEndPointsConstants.CREATE_CATEGORY,
                                BookEndPointsConstants.UPDATE_CATEGORY_BY_ID,
                                BookEndPointsConstants.DELETE_CATEGORY_BY_ID,
                                BookEndPointsConstants.CREATE_NEW_BOOK,
                                BookEndPointsConstants.UPLOAD_COVER_BOOK_IMAGE,
                                BookEndPointsConstants.UPLOAD_BOOK_FILE,
                                BookEndPointsConstants.UPDATE_BOOK_BY_ID,
                                BookEndPointsConstants.DELETE_BOOK_BY_ID

                        ).hasRole(Role.ADMIN.name())
                        .requestMatchers(
                                UserEndPointsConstants.GET_CURR_USER,
                                UserEndPointsConstants.ADD_PHONE_NUMBER,
                                UserEndPointsConstants.UPDATE_USER_ADMIN,
                                UserEndPointsConstants.DELETE_USER_ADMIN,
                                BookEndPointsConstants.GET_AUTHOR_BY_ID,
                                BookEndPointsConstants.GET_AUTHORS_BY_NAME,
                                BookEndPointsConstants.GET_ALL_AUTHORS,
                                BookEndPointsConstants.GET_CATEGORY_BY_ID,
                                BookEndPointsConstants.GET_ALL_CATEGORIES,
                                BookEndPointsConstants.GET_BOOK_BY_ID,
                                BookEndPointsConstants.GET_ALL_BOOKS,
                                BookEndPointsConstants.GET_BOOK_BY_CATEGORY_ID,
                                BookEndPointsConstants.GET_BOOK_BY_AUTHOR_ID
                        ).authenticated()
                        .anyRequest().denyAll()
                );
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        UsernamePasswordAuthenticationProvider authenticationProvider =
                new UsernamePasswordAuthenticationProvider(userDetailsService, passwordEncoder);
        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }
}
