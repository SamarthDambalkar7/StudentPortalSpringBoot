package com.learning.mongodbatlas.mongodbatlas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learning.mongodbatlas.mongodbatlas.model.Administration;

public interface AdministrationRepository extends MongoRepository<Administration, Integer> {

}
