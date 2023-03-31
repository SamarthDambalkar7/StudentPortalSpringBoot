package com.learning.mongodbatlas.mongodbatlas.service;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.learning.mongodbatlas.mongodbatlas.enums.StudentType;
import com.learning.mongodbatlas.mongodbatlas.model.Administration;
import com.learning.mongodbatlas.mongodbatlas.repository.AdministrationRepository;

@Service
public class AdministrationServiceImpl implements AdministrationService {

    @Autowired
    AdministrationRepository administrationRepository;

    @Override
    public HttpStatus addNewStudentFees() {

        for (int i = 1; i <= 12; i++) {
            Administration studentDoc = new Administration();
            LinkedHashMap<StudentType, Double> feesMap = new LinkedHashMap<>();

            studentDoc.setStd(i);
            feesMap.put(StudentType.OPEN, Double.valueOf(100000) + (Double.valueOf(100000) * (i) / 10));
            feesMap.put(StudentType.OBC, Double.valueOf(50000) + (Double.valueOf(50000) * i / 10));
            feesMap.put(StudentType.EBC, Double.valueOf(30000) + (Double.valueOf(30000) * i / 10));
            feesMap.put(StudentType.SC, Double.valueOf(5000) + (Double.valueOf(5000) * i / 10));
            feesMap.put(StudentType.ST, Double.valueOf(4500) + (Double.valueOf(4500) * i / 10));
            studentDoc.setFees(feesMap);
            administrationRepository.save(studentDoc);
        }

        return HttpStatus.OK;
    }

}
