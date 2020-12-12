package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class MyController {

    @Autowired
    StudentRepo studentRepo;

    @GetMapping("/hello")
    public String myMehotd() {
        return "Hello World";
    }

    @PostMapping(
            value = "/create/student",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Student> postBody(@RequestBody Student student) {
        Student studentPerson = studentRepo.save(student);
        return ResponseEntity
                .created(URI
                        .create(String.format("/student/%s", student.getName())))
                .body(studentPerson);
    }
}
