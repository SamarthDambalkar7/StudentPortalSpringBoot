package com.learning.mongodbatlas.mongodbatlas.service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.learning.mongodbatlas.mongodbatlas.enums.StudentType;
import com.learning.mongodbatlas.mongodbatlas.enums.Subjects;
import com.learning.mongodbatlas.mongodbatlas.model.Notes;
import com.learning.mongodbatlas.mongodbatlas.model.Student;
import com.learning.mongodbatlas.mongodbatlas.repository.AdministrationRepository;
import com.learning.mongodbatlas.mongodbatlas.repository.CurriculumRepository;
import com.learning.mongodbatlas.mongodbatlas.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Autowired
    private AdministrationRepository administrationRepository;

    @Override
    public Optional<Student> getStudentByID(int Id, String password) {

        if (studentRepository.findById(Id) != null) {

            if (studentRepository.findById(Id).get().getPassword().equals(password)) {
                return studentRepository.findById(Id);
            }
        }

        return null;

    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudentsByType(StudentType studentType) {

        List<Student> studentsList = studentRepository.findAll();

        return studentsList.stream().filter(stud -> stud.getStudentType().equals(studentType)).toList();

    }

    @Override
    public String addNewStudent(Student student) {

        // for id generation
        int minRange = 10000;
        int maxRange = 99999;
        Random random = new Random();

        student.setStudentId(random.nextInt((maxRange - minRange)) + minRange);

        // for assigning default values

        // Set subjects according to std
        student.setSubjects(curriculumRepository.findById(student.getStd()).get().getSubjects());

        // Set Notes according to subjects / std
        LinkedHashMap<Subjects, LinkedList<Notes>> notesMap = new LinkedHashMap<>();

        for (Subjects subject : student.getSubjects()) {
            notesMap.put(subject, new LinkedList<>());
        }
        student.setNotes(notesMap);

        // set student fees according to category and std
        for (Entry<StudentType, Double> entry : administrationRepository.findById(student.getStd()).get().getFees()
                .entrySet()) {
            if (student.getStudentType().equals(entry.getKey())) {
                student.setFees(entry.getValue());
            }
        }

        // storing student with all properties to MongoDB
        studentRepository.save(student);
        return "Student with StudentId:" + student.getStudentId() + " created successfully";

    }

    @Override
    public String deleteStudentById(int Id) throws java.util.NoSuchElementException {

        try {
            studentRepository.findById(Id).get();
            studentRepository.deleteById(Id);
            return "student with StudentId: " + Id + " has been deleted";

        } catch (Exception e) {
            return "Student with StudentId: " + Id + " does not exist";

        }
    }

    @Override
    public List<Subjects> getSubjectsList(int Id) {

        return studentRepository.findById(Id).get().getSubjects();
    }

    @Override
    public LinkedList<Notes> getNotesBySubject(int Id, Subjects subjects) {

        for (Entry<Subjects, LinkedList<Notes>> entrySet : studentRepository.findById(Id).get().getNotes().entrySet()) {
            if (entrySet.getKey().equals(subjects)) {
                return entrySet.getValue();
            }
        }

        return null;
    }

    @Override
    public HttpStatus addNewNotes(int Id, Subjects subjects, Notes notes) throws NullPointerException {

        // fetch old results of notes

        Student student = studentRepository.findById(Id).get();
        LinkedHashMap<Subjects, LinkedList<Notes>> notesMap = student.getNotes();
        System.out.println(notesMap);

        // add new notes to existing notes map

        try {
            notesMap.get(subjects).add(notes);

            // replace old notes with new in db

            student.setNotes(notesMap);
            studentRepository.save(student);

            return HttpStatus.OK;
        } catch (NullPointerException e) {

            LinkedList<Notes> newNotesList = new LinkedList<>();
            newNotesList.add(notes);
            notesMap.put(subjects, newNotesList);
            student.setNotes(notesMap);
            studentRepository.save(student);

        }
        return HttpStatus.NOT_FOUND;

    }

    @Override
    public HttpStatus deleteNotes(int Id, Subjects subjects, String date) {

        Student student = studentRepository.findById(Id).get();
        LinkedHashMap<Subjects, LinkedList<Notes>> notesMap = student.getNotes();

        if (notesMap.get(subjects).element().getDate().equals(date)) {
            System.out.println("_________________________________________________________");
            System.out.println(notesMap.get(subjects).element());
            notesMap.get(subjects).remove(notesMap.get(subjects).element());
            student.setNotes(notesMap);
            studentRepository.save(student);
        }

        return HttpStatus.NOT_FOUND;
    }

}
