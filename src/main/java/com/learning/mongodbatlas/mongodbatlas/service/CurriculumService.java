package com.learning.mongodbatlas.mongodbatlas.service;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.springframework.http.HttpStatus;

import com.learning.mongodbatlas.mongodbatlas.enums.Days;
import com.learning.mongodbatlas.mongodbatlas.enums.Subjects;
import com.learning.mongodbatlas.mongodbatlas.model.lectureTime;

public interface CurriculumService {

    HttpStatus addSubjectsByStd(int std, LinkedList<Subjects> subjects);

    HttpStatus setTimeTable(int std, LinkedHashMap<Days, LinkedHashMap<lectureTime, Subjects>> info);

}
