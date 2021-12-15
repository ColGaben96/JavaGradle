package com.example.javagradle.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionControlller implements ErrorController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    @RequestMapping("/not-found")
    public String notFound(Model model) {
        var errCode = 404;
        model.addAttribute("errCode", errCode);
        return "error/handler";
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(Exception.class)
    @RequestMapping("/unauthorized")
    public String unauthorized(Model model) {
        var errCode = 401;
        model.addAttribute("errCode", errCode);
        return "error/handler";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @RequestMapping("/server-error")
    public String handleError(Model model) {
        var errCode = 500;
        model.addAttribute("errCode", errCode);
        return "error/handler";
    }
}
