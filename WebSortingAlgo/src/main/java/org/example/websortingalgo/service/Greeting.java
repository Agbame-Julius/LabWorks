package org.example.websortingalgo.service;

import org.springframework.stereotype.Service;

@Service
public class Greeting {
    public String greet(String name) {
        return "Hello, " + name + "!";
    }

}
