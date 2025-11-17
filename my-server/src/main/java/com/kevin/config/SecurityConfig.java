package com.kevin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()        // 关闭 CSRF
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")                  // 明确告诉 Spring 这是 logout
                .logoutSuccessUrl("http://localhost:3000/logout-success")
                .deleteCookies("JSESSIONID")           // 删除 cookie
                .invalidateHttpSession(true)           // 清 session
                .clearAuthentication(true)
                .permitAll()
                .and()
                .oauth2Login()
                .loginPage("/oauth2/authorization/google") // 登录入口
                .authorizationEndpoint().baseUri("/oauth2/authorization")
                .and()
                .redirectionEndpoint().baseUri("/login/oauth2/code/*")
                .and()
                .defaultSuccessUrl("http://localhost:3000", true);


        return http.build();
    }

}
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowedOrigins(List.of("http://localhost:3000")); // 允许前端来源
//        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // 允许的方法
//        config.setAllowedHeaders(List.of("Authorization", "Content-Type")); // 允许的请求头
//        config.setAllowCredentials(true); // 允许携带cookie/session
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }

