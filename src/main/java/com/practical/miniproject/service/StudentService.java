package com.practical.miniproject.service;

import com.practical.miniproject.entity.Student;
import com.practical.miniproject.entity.Subject;
import com.practical.miniproject.enums.Gender;
import com.practical.miniproject.enums.Grade;
import com.practical.miniproject.enums.Result;
import com.practical.miniproject.repository.StudentRepository;
import com.practical.miniproject.repository.SubjectRepository;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    private  Student student;


    private Subject subject;

    public Student saveStudent(Student student){

        return studentRepository.save(student);
    }

//    public Student saveStudentDetails(String name,String surName, String gender,int computer_Networking,int dsa,int advance_Java,int information_Security,int mobile_Computing){
//        subject =new Subject();
//        student = new Student();
//        student.setName(name);
//        student.setSurName(surName);
//        student.setGender(findGender(gender));
//        System.out.println("gender "+student.getGender());
//        subject.setAdvance_Java(findGrade(advance_Java));
//        subject.setDsa(findGrade(dsa));
//        subject.setComputer_Networking(findGrade(computer_Networking));
//        subject.setInformation_Security(findGrade(information_Security));
//        subject.setMobile_Computing(findGrade(mobile_Computing));
//        student.setSubject(subject);
//        student.setResult(findResult(student));
//        return studentRepository.save(student);
//    }
//    private Result findResult(Student student){
//        if(student.getSubject().getAdvance_Java().equals(Grade.F) || student.getSubject().getDsa().equals(Grade.F)
//         || student.getSubject().getComputer_Networking().equals(Grade.F) || student.getSubject().getMobile_Computing().equals(Grade.F)
//        || student.getSubject().getInformation_Security().equals(Grade.F)){
//            return Result.Fail;
//        };
//        return Result.Pass;
//    }
//    private Grade findGrade(int mark){
//        if(mark>=95){
//         return Grade.O;
//        }
//        if(mark>=85 && mark<95){
//            return Grade.A;
//        }
//        if(mark>=75 && mark<85){
//            return Grade.B;
//        }
//        if(mark>=50 && mark<75){
//            return Grade.C;
//        }
//
//        return Grade.F;
//
//    }
    public String saveStudentDetails(String name,String surName,String gender,int computer_Networking,int dsa,int advance_Java,int information_Security,int mobile_Computing){
        Student student = new Student();
        Subject subject = new Subject();
        subject.setAdvance_Java(advance_Java);
        subject.setDsa(dsa);
        subject.setInformation_Security(information_Security);
        subject.setComputer_Networking(computer_Networking);
        subject.setMobile_Computing(mobile_Computing);
        student.setName(name);
        student.setSurName(surName);
        student.setGender(gender);
        student.setResult(findResult(subject));
        student.setSubject(subject);
        Student res =  studentRepository.save(student);
        return "Student added successfully. Roll Number for student "+res.getName()+" "+res.getSurName()+" is "+ res.getRoleNumber();

    }

    private Result findResult(Subject subject){
        int minMark = 45;
        if(subject.getInformation_Security()<minMark || subject.getDsa() < minMark || subject.getMobile_Computing()<minMark
        || subject.getDsa() < minMark || subject.getAdvance_Java() < minMark){
            System.out.println("inside result fail");
            return Result.Fail;
        }
        System.out.println("inside result");
        return Result.Pass;
    }
    private Gender findGender(String gender){
        switch (gender){
            case "Male" :
                return Gender.MALE;
            case "Female" :
                return  Gender.FEMALE;
            case "PreferNotToSay" :
                return Gender.PREFER_NOT_TO_SAY;
        }
        return Gender.INVALID;
    }
    public List<Student> getStudent(int roleNumber){
        List<Student> stdLst = studentRepository.findAll();

        return stdLst.stream().filter(student -> student.getRoleNumber() == roleNumber).collect(Collectors.toList());
    }

    public String deleteStudent(int roleNUmber){
        try{
            List<Student> std = getStudent(roleNUmber);
            if(std.size() == 0){
                return "Student data not found";
            }
            studentRepository.delete(std.get(0));
            std.clear();
            return "Student deleted successfully";
        }catch (Exception e){
            return "Exception acquired while deleting student";
        }
    }

    public String updateStudent( int roleNum,String name,String surName,String gender,int computer_Networking,int dsa,int advance_Java,
                                int information_Security, int mobile_Computing){
        try {
            Student updateStudent = new Student();
            List<Student> std = getStudent(roleNum);
            if(std.size() == 0){
                return "Student data not found";
            }
            updateStudent.setRoleNumber(roleNum);
            updateStudent.setName(name);
            updateStudent.setSurName(surName);
            Subject subject = new Subject();
            subject.setMobile_Computing((mobile_Computing));
            subject.setDsa((dsa));
            subject.setComputer_Networking((computer_Networking));
            subject.setAdvance_Java((advance_Java));
            subject.setInformation_Security((information_Security));
            updateStudent.setSubject(subject);
            updateStudent.setGender((gender));
            updateStudent.setResult(findResult(subject));
            subjectRepository.deleteById(std.get(0).getRoleNumber());
            studentRepository.save(updateStudent);
            std.clear();
            return "Student updated successfully";
        }catch (Exception e){
            return "Error occurs while updating student";
        }
    }

}
