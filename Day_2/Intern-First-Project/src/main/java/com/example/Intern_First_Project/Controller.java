package com.example.Intern_First_Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/create")
public class Controller {
    @Autowired
    ServiceUser serviceUser;

    private ResponseEntity<Dto> create_user(@RequestBody Dto dto)
    {
        return new ResponseEntity<>(serviceUser.create_user(dto), HttpStatus.CREATED);
    }
}
