package com.learning.mongodbatlas.mongodbatlas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learning.mongodbatlas.mongodbatlas.model.Student;

public interface StudentRepository extends MongoRepository<Student, Integer> {

}
