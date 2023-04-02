package com.learning.mongodbatlas.mongodbatlas.service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.learning.mongodbatlas.mongodbatlas.enums.Days;
import com.learning.mongodbatlas.mongodbatlas.enums.Subjects;
import com.learning.mongodbatlas.mongodbatlas.model.Curriculum;
import com.learning.mongodbatlas.mongodbatlas.model.lectureTime;
import com.learning.mongodbatlas.mongodbatlas.repository.CurriculumRepository;

@Service
public class CurriculumServiceImpl implements CurriculumService {

    @Autowired
    CurriculumRepository curriculumRepository;

    @Override
    public HttpStatus addSubjectsByStd(int std, LinkedList<Subjects> subjects) throws NoSuchElementException {

        try {
            Curriculum curriculum = curriculumRepository.findById(std).get();
            LinkedList<Subjects> subjectsList = curriculum.getSubjects();
            for (Subjects subject : subjects) {
                subjectsList.add(subject);
            }
            curriculum.setSubjects(subjectsList);
            curriculumRepository.save(curriculum);
        } catch (NoSuchElementException e) {
            Curriculum curriculum = new Curriculum();
            curriculum.setStd(std);
            curriculum.setSubjects(subjects);
            curriculumRepository.save(curriculum);
        }

        return HttpStatus.OK;
    }

    @Override
    public HttpStatus setTimeTable(int std, LinkedHashMap<Days, LinkedHashMap<lectureTime, Subjects>> info)
            throws NoSuchElementException {
        try {
            Curriculum curriculum = curriculumRepository.findById(std).get();
            LinkedHashMap<Days, LinkedHashMap<lectureTime, Subjects>> timeTable = curriculum.getTimeTable();
            for (Entry<Days, LinkedHashMap<lectureTime, Subjects>> entry : info.entrySet()) {

                timeTable.put(entry.getKey(), entry.getValue());
            }
            curriculum.setTimeTable(timeTable);
            curriculumRepository.save(curriculum);

        } catch (NoSuchElementException e) {

        }

        return HttpStatus.OK;
    }

}
