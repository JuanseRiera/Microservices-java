package es.edu.escuela_it.microservices.controller;

import es.edu.escuela_it.microservices.configuration.appConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HolaMundoRest {

    @Autowired
    private appConfiguration appConfig;


    @GetMapping("/holamundo")
    public String saludo(){
        System.out.println(appConfig.toString());
        return "Hola mundo desde java";
    }
}
