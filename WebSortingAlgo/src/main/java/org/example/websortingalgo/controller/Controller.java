package org.example.websortingalgo.controller;

import org.example.websortingalgo.dto.InputRequest;
import org.example.websortingalgo.dto.SortingRequest;
import org.example.websortingalgo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class Controller {
    private final Greeting greeting;
    private final SortingService sortingService;


    @Autowired
    public Controller(Greeting greeting, SortingService sortingService) {
        this.greeting = greeting;
        this.sortingService = sortingService;


    }



    @GetMapping("/welcome")
    public String sort() {
        return "Hello World";
    }

    @PostMapping("/sort")
    public ResponseEntity<Map<String, Object>>  sortData(@RequestBody SortingRequest sortingRequest) {
        // retrieving values from the DTO
        Map<String, Object> response = sortingService.handleSorting(sortingRequest);
        return ResponseEntity.ok(response);
  }


  //for testing
    @PostMapping("/greeting")
    public ResponseEntity<String> algorithm(@RequestBody InputRequest inputRequest) {
        String result = greeting.greet(inputRequest.getData());
        return ResponseEntity.ok(result);
    }
}
