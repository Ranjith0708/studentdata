package com.practical.miniproject.entity;

import com.practical.miniproject.enums.Gender;
import com.practical.miniproject.enums.Grade;
import com.practical.miniproject.enums.Result;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "STUDENT_TABLE")
public class Student {
    @Id
    @GeneratedValue
    private  int roleNumber;
    private String name;
    private String surName;
    private String gender;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @Enumerated(EnumType.STRING)
    private Result result;
}
