package com.in2minutes.rest.webservices.restfulwebservices.controllers;

import com.in2minutes.rest.webservices.restfulwebservices.domain.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/hello-world")
    public String getHelloworld() {
        return "Hello world";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean getHelloWorld() {
        return new HelloWorldBean("Hello world");
    }

    @GetMapping("/hello-world-bean/{userName}")
    public HelloWorldBean getHelloWorldPath(@PathVariable String userName) {
        return new HelloWorldBean(String.format("Hello %s", userName));
    }

    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false)Locale locale) {
        return messageSource.getMessage("good.morning", null, locale);
    }

    @GetMapping("/hello-world-internationalized-general")
    public String helloWorldInternationalizedGeneral() {
        return messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale());
    }
}
