package com.practical.miniproject.entity;

import com.practical.miniproject.enums.Grade;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "SUBJECT_TABLE")
public class Subject {
    @Id
    @GeneratedValue
    private int id;
    private int computer_Networking;
    private int dsa;
    private int advance_Java;
    private int information_Security;
    private int mobile_Computing;
}
