package com.htp.springnewswebapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .anyRequest()
//                .hasAnyRole("ADMIN", "USER")
//                .and()
//                .authorizeRequests()
//                .antMatchers()
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .authorizeRequests()
                .antMatchers("/newslist", "/deletenews", "/add", "/edit","signout").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .successHandler(new AuthSuccessHandler())
                .loginProcessingUrl("/signin")
                .loginPage("/home")
                .defaultSuccessUrl("/newslist")
                .failureUrl("/home")
                .usernameParameter("login")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutSuccessUrl("/home")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")
                .and()
                .csrf()
                .disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

