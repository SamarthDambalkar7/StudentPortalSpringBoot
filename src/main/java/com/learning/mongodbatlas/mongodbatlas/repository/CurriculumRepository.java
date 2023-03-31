package com.learning.mongodbatlas.mongodbatlas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learning.mongodbatlas.mongodbatlas.model.Curriculum;

public interface CurriculumRepository extends MongoRepository<Curriculum, Integer> {

}
