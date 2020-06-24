package com.lambdaschool.secretrecipes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * Once the client has gained authorization, users need to gain authentication. This class is response for handling that.
 * It also configures which roles have access to which endpoints. So controls the users' access!
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/",
                        "/h2-console/**",
                        "/swagger-resources/**",
                        "/swagger-resource/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/webjars/**",
                        "/register")
                .permitAll()
                .antMatchers(HttpMethod.POST,
                        "/users/**",
                        "/items/**")
                .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/users/**",
                        "/items/**")
                .hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/users/**",
                        "/items/**")
                .hasAnyRole("USER", "ADMIN")
                .antMatchers("/users/**",
                        "/oauth/revoke-token",
                        "/logout")
                .authenticated()
                .antMatchers("/roles/**")
                .hasAnyRole("USER", "ADMIN")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());

        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.logout().disable();
    }
}
