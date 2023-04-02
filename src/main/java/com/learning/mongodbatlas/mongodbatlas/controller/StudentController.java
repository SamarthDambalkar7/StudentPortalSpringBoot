package com.learning.mongodbatlas.mongodbatlas.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.mongodbatlas.mongodbatlas.enums.StudentType;
import com.learning.mongodbatlas.mongodbatlas.enums.Subjects;
import com.learning.mongodbatlas.mongodbatlas.model.Notes;
import com.learning.mongodbatlas.mongodbatlas.model.Student;
import com.learning.mongodbatlas.mongodbatlas.service.StudentServiceImpl;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    StudentServiceImpl studentServiceImpl;

    /* Get Mappings */

    @GetMapping("/getstudentbyid")
    public Optional<Student> getStudentById(@RequestParam int ID, @RequestParam String password) {
        return studentServiceImpl.getStudentByID(ID, password);
    }

    @GetMapping("/getallstudents")
    public List<Student> getAllStudents() {
        return studentServiceImpl.getAllStudents();
    }

    @GetMapping("/getstudentbytype")
    public List<Student> getStudentsByType(@RequestParam StudentType studentType) {
        return studentServiceImpl.getStudentsByType(studentType);
    }

    @GetMapping("/getsubjects")
    public List<Subjects> getStudentSubjects(@RequestParam int Id) {
        return studentServiceImpl.getSubjectsList(Id);
    }

    @GetMapping("/getnotesbysubject")
    public LinkedList<Notes> getNotesBySubject(@RequestParam int Id, @RequestParam Subjects subjects) {

        return studentServiceImpl.getNotesBySubject(Id, subjects);
    }

    /* Post Mappings */
    @PostMapping("/addstudent")
    public String addNewStudent(@RequestBody Student student) {

        return studentServiceImpl.addNewStudent(student);
    }

    @PostMapping("/addnewnotes")
    public HttpStatusCode addNewNotes(@RequestParam int Id, @RequestParam Subjects subjects,
            @RequestBody Notes notes) {
        return studentServiceImpl.addNewNotes(Id, subjects, notes);
    }

    /* Delete Mappings */
    @DeleteMapping("/deletestudent")
    public String deleteStudent(@RequestParam int ID) {
        studentServiceImpl.deleteStudentById(ID);

        return "Student has been deleted";
    }

    @DeleteMapping("/deleteNotes")
    public HttpStatusCode deleteNotes(@RequestParam int Id, @RequestParam Subjects subjects,
            @RequestParam String date) {
        return studentServiceImpl.deleteNotes(Id, subjects, date);
    }
}
