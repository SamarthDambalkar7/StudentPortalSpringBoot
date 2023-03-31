package com.learning.mongodbatlas.mongodbatlas.service;

import java.util.LinkedList;

import org.springframework.http.HttpStatus;

import com.learning.mongodbatlas.mongodbatlas.enums.Subjects;

public interface CurriculumService {

    HttpStatus addSubjectsByStd(int std, LinkedList<Subjects> subjects);

}
