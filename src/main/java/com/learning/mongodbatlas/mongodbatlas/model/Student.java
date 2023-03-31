package com.learning.mongodbatlas.mongodbatlas.model;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.learning.mongodbatlas.mongodbatlas.enums.StudentType;
import com.learning.mongodbatlas.mongodbatlas.enums.Subjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document("Student")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Student {

    // AutoAssigned

    @Id
    private int studentId = (int) Math.random();

    private double fees;

    private List<Subjects> subjects;

    private LinkedHashMap<Subjects, LinkedList<Notes>> notes;

    // Getting from User during Registraion
    private String studentName;

    private StudentType studentType;

    private String password;

    private int std;

    private String emailId;

    private long phoneNo;
}
