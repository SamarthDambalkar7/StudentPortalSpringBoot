package com.learning.mongodbatlas.mongodbatlas.model;

import java.util.LinkedHashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.learning.mongodbatlas.mongodbatlas.enums.StudentType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document("Administration")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Administration {

    @Id
    private int std;

    private LinkedHashMap<StudentType, Double> fees;

}
