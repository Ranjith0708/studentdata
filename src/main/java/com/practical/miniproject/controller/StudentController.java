package com.practical.miniproject.controller;

import com.practical.miniproject.entity.Student;
import com.practical.miniproject.service.StudentService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@Data
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/addStudentDetails")
    public String saveStudentDetails(@RequestParam String name,@RequestParam String surName,@RequestParam String gender,@RequestParam int computer_Networking,@RequestParam int dsa,@RequestParam int advance_Java,
                                      @RequestParam int information_Security,@RequestParam int mobile_Computing){
        return  studentService.saveStudentDetails(name,surName,gender,computer_Networking,dsa,advance_Java,information_Security,mobile_Computing);
    }

    @GetMapping("/getStudent/{roleNumber}")
    public List<Student> saveStudent(@PathVariable int roleNumber){
        return studentService.getStudent(roleNumber);
    }

    @DeleteMapping("/deleteStudent/{roleNumber}")
    public String deleteStudent(@PathVariable int roleNumber){
        return studentService.deleteStudent(roleNumber);
    }

    @PutMapping("/updateStundent")
    public String updateStudent(@RequestParam int roleNum,@RequestParam String name,@RequestParam String surName,@RequestParam String gender,@RequestParam int computer_Networking,@RequestParam int dsa,@RequestParam int advance_Java,
                                @RequestParam int information_Security,@RequestParam int mobile_Computing){
        return studentService.updateStudent(roleNum,name,surName, gender,computer_Networking,dsa, advance_Java, information_Security,mobile_Computing);
    }
}
