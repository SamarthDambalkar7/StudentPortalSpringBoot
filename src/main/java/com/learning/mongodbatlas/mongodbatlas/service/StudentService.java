package com.learning.mongodbatlas.mongodbatlas.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import com.learning.mongodbatlas.mongodbatlas.enums.StudentType;
import com.learning.mongodbatlas.mongodbatlas.enums.Subjects;
import com.learning.mongodbatlas.mongodbatlas.model.Notes;
import com.learning.mongodbatlas.mongodbatlas.model.Student;

public interface StudentService {

    Optional<Student> getStudentByID(int Id, String password);

    List<Student> getAllStudents();

    List<Student> getStudentsByType(StudentType studentType);

    String addNewStudent(Student student);

    String deleteStudentById(int Id);

    List<Subjects> getSubjectsList(int Id);

    LinkedList<Notes> getNotesBySubject(int Id, Subjects subjects);

    HttpStatus addNewNotes(int Id, Subjects subjects, Notes notes);

    HttpStatus deleteNotes(int Id, Subjects subjects, String date);

}