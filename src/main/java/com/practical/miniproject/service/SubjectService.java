package com.practical.miniproject.service;

import com.practical.miniproject.entity.Subject;
import com.practical.miniproject.repository.SubjectRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject saveSubject(Subject subject){
        return subjectRepository.save(subject);
    }
}
