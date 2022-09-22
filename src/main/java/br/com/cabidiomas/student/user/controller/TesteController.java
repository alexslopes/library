package br.com.cabidiomas.library.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teste")
@RequiredArgsConstructor
public class TesteController {

    @GetMapping
    public String teste(){
        return "teste ok";
    }

}
