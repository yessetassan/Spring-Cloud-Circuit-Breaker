package com.serviceb.serviceb;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class WelcomeController {

    @GetMapping("")
    public Record all() {
        return Record.builder()
                .message("Hello Java !")
                .build();
    }
}
