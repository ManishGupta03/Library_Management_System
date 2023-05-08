package com.OMG.Library_Management_Systenm.controller;

import com.OMG.Library_Management_Systenm.DTO.RequestDto.StudentRequestDto;
import com.OMG.Library_Management_Systenm.DTO.RequestDto.UpdateStudentMobRequestDto;
import com.OMG.Library_Management_Systenm.DTO.ResponseDto.StudentResponseDto;
import com.OMG.Library_Management_Systenm.DTO.ResponseDto.UpdateStudentMobResponseDto;
import com.OMG.Library_Management_Systenm.entity.Student;
import com.OMG.Library_Management_Systenm.exception.StudentNotFoundException;
import com.OMG.Library_Management_Systenm.repository.StudentRepository;
import com.OMG.Library_Management_Systenm.service.StudentService;
import com.OMG.Library_Management_Systenm.service.Impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto){

        return studentService.addStudent(studentRequestDto);
    }

    @PutMapping("/update_mobile")
    public UpdateStudentMobResponseDto updateMobNo(@RequestBody UpdateStudentMobRequestDto updateStudentMobRequestDto) throws StudentNotFoundException {

        return studentService.updateMobNo(updateStudentMobRequestDto);
    }

    // delete a student by id

    // update the student by id

    @GetMapping("/get_student")
    public StudentResponseDto getStudent(@RequestParam("id") int id){
        return studentService.getStudentById(id);
    }

    // find all the students
}