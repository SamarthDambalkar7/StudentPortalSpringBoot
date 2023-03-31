package com.learning.mongodbatlas.mongodbatlas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.mongodbatlas.mongodbatlas.service.AdministrationServiceImpl;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdministrationController {

    @Autowired
    AdministrationServiceImpl administrationServiceImpl;

    @PostMapping("/populatestudentsfees")
    public HttpStatus addNewStudentFees() {
        return administrationServiceImpl.addNewStudentFees();
    }
}
