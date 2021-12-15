package com.example.javagradle.config;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class Web implements WebMvcConfigurer{

    @Bean
    public LocaleResolver localeResolver(){
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registro){
        registro.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registro){
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/app/login");
        registro.addViewController("/app/signup");
        registro.addViewController("/app/dashboard");
        registro.addViewController("/app/forgot");
        registro.addViewController("/admin/login");
        registro.addViewController("/admin/dashboard");
        registro.addViewController("/admin/forgot");
        registro.addViewController("/not-found");
        registro.addViewController("/unauthorized");
        registro.addViewController("/server-error");
        registro.addStatusController("/not-found", HttpStatus.NOT_FOUND);
        registro.addStatusController("/unauthorized", HttpStatus.UNAUTHORIZED);
        registro.addStatusController("/server-error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

