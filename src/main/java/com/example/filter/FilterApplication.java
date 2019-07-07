package com.example.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FilterApplication {

    @GetMapping("/public")
    public ResponseEntity publicApi(){
        return ResponseEntity.status(HttpStatus.OK).body("This is public data");
    }
    @GetMapping("/private")
    public ResponseEntity privateApi(){
        return ResponseEntity.status(HttpStatus.OK).body("This is private data");
    }

    public static void main(String[] args) {
        SpringApplication.run(FilterApplication.class, args);
    }

}
