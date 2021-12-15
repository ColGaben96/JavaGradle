package com.example.javagradle.config;

import com.example.javagradle.bl.utils.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityClient extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new PasswordEncrypter();
    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/app/login", "/app/signup", "/app/forgot", "/app/newUser").permitAll()
                .antMatchers("/app/**").hasRole("CLIENT")
            .and()
            .formLogin()
                .loginPage("/app/login")
                .loginProcessingUrl("/checkAccess")
                .defaultSuccessUrl("/app/dashboard", true)
            .and()
            .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
            .and()
            .exceptionHandling()
                .accessDeniedPage("/unauthorized");

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("client@example.com").password("BCrypt{123456aB!}").roles("CLIENT");
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("BCrypt{123456aB!}").roles("ADMIN");
    }
}
