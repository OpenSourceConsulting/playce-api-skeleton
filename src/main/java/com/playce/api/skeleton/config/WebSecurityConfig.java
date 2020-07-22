/*
 * Copyright 2020 The Playce Project.
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Revision History
 * Author			Date				Description
 * ---------------	----------------	------------
 * SangCheon Park	Jul 22, 2020	    First Draft.
 */
package com.playce.api.skeleton.config;

import com.playce.api.skeleton.security.JwtAuthenticationEntryPoint;
import com.playce.api.skeleton.security.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <pre>
 * 보안 관련 Spring 설정 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    @Qualifier("jwtUserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());

        /*
        authenticationManagerBuilder.inMemoryAuthentication()
            .withUser("admin")
            .password("admin")
            .roles("ADMIN", "USER");

        authenticationManagerBuilder.inMemoryAuthentication()
            .withUser("user")
            .password("user")
            .roles("USER");
        //*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                // allow anonymous resource requests
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/favicon.ico",
                        "/assets/**/*",
                        "/static/**/*",
                        "/**/*.html", 
                        "/**/*.scss",
                        "/**/*.css", 
                        "/**/*.js", 
                        "/**/*.map", 
                        "/**/*.ttf", 
                        "/**/*.woff", 
                        "/**/*.woff2", 
                        "/**/*.png",
                        "/**/*.jpg", 
                        "/**/*.gif")
                // Swagger 관련
                .antMatchers("/webjars/**", "/swagger-resources/**", "/swagger-ui.html**", "/v2/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                .antMatchers("/dashboard/**").permitAll()
                .antMatchers("/wizard/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/domain/**").permitAll()
                .antMatchers("/server/**").permitAll()
                .antMatchers("/resource/**").permitAll()
                .antMatchers("/monitoring/**").permitAll()
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/comparefile/**").permitAll()

                //.antMatchers("/api/**").permitAll()
                .antMatchers("/websocket/**").permitAll()
                .antMatchers("/auth/**").permitAll() // Do not move to above configure(WebSecurity web) - 500 Internal
                                                                 // error return for /auth/login
                .anyRequest().authenticated();

        // Custom JWT based security filter
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity.headers().cacheControl();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
// end of WebSecurityConfig.java