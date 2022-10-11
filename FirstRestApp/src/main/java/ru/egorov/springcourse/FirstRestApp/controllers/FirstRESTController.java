package ru.egorov.springcourse.FirstRestApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // то же самое, что и @Controller + @ResponseBody над каждым методом
@RequestMapping("/api")
public class FirstRESTController {

    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello world!";
    }
}
