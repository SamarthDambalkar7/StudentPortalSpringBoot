package com.learning.mongodbatlas.mongodbatlas.model;

import java.util.LinkedList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.learning.mongodbatlas.mongodbatlas.enums.Subjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document("Curriculum")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Curriculum {

    @Id
    private int std;

    private LinkedList<Subjects> subjects;

}
