package com.learning.mongodbatlas.mongodbatlas.controller;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.mongodbatlas.mongodbatlas.enums.Subjects;
import com.learning.mongodbatlas.mongodbatlas.service.CurriculumServiceImpl;

@RestController
@RequestMapping("/curriculum")
public class CurriculumController {

    @Autowired
    CurriculumServiceImpl curriculumServiceImpl;

    @PostMapping("/addsubjectsbystd")
    public HttpStatus addSubjectsByStd(@RequestParam int Id, @RequestParam LinkedList<Subjects> subjects) {
        return curriculumServiceImpl.addSubjectsByStd(Id, subjects);
    }
}
