package com.practical.miniproject.repository;

import com.practical.miniproject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {

}
