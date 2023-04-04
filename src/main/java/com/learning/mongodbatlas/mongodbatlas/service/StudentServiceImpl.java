package com.learning.mongodbatlas.mongodbatlas.service;

import java.util.Iterator;
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

        // Map to be set in student
        LinkedHashMap<Subjects, LinkedList<Notes>> notesMap = new LinkedHashMap<>();

        for (Subjects subject : student.getSubjects()) {
            // default new note object
            Notes defaultnote = new Notes();

            // Set Notes according to subjects / std
            LinkedList<Notes> defaultNotes = new LinkedList<>();
            defaultnote.setDate("");
            defaultnote.setTitle("These are your Pinned notes for :" + subject.name());
            defaultnote.setContent("");
            defaultNotes.add(defaultnote);
            notesMap.put(subject, defaultNotes);
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
        return String.valueOf(student.getStudentId());

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

        boolean isPresent = false;
        Student student = studentRepository.findById(Id).get();
        LinkedHashMap<Subjects, LinkedList<Notes>> notesMap = student.getNotes();

        Iterator<Notes> iterator = notesMap.get(subjects).iterator();
        LinkedList<Notes> notesToAdd = new LinkedList<>();

        // add new notes to existing notes map

        // check if notes for a particular subject exist for that day

        if (iterator.hasNext() && !notes.getDate().equals("")) {
            while (iterator.hasNext()) {
                Notes note = iterator.next();

                if (note.getDate().equals(notes.getDate())) {
                    note.setContent(notes.getContent());
                    note.setTitle(notes.getTitle());
                    isPresent = true;
                }
            }
            if (!isPresent) {
                notesToAdd.add(notes);
            }
        } else {
            notesToAdd.add(notes);
        }

        notesMap.get(subjects).addAll(notesToAdd);

        // replace old notes with new in db
        student.setNotes(notesMap);
        studentRepository.save(student);

        return HttpStatus.OK;

    }

    @Override
    public HttpStatus deleteNotes(int Id, Subjects subjects, String date) {

        Student student = studentRepository.findById(Id).get();
        LinkedList<Notes> notesList = student.getNotes().get(subjects);
        LinkedHashMap<Subjects, LinkedList<Notes>> notesMap = student.getNotes();

        Iterator<Notes> iterator = notesList.iterator();

        while (iterator.hasNext()) {
            Notes note = iterator.next();
            if (date.equals(note.getDate())) {
                iterator.remove();
            }
        }

        notesMap.put(subjects, notesList);
        student.setNotes(notesMap);
        studentRepository.save(student);

        return HttpStatus.OK;
    }

}
