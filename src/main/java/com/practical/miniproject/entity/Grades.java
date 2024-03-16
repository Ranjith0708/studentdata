package com.practical.miniproject.entity;

import com.practical.miniproject.enums.Grade;
import lombok.Data;

@Data
public class Grades {
    private Subject subject;
    private Grade grade;
}
