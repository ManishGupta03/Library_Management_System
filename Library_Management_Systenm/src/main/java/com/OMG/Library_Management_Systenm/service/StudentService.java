package com.OMG.Library_Management_Systenm.service;

import com.OMG.Library_Management_Systenm.DTO.RequestDto.StudentRequestDto;
import com.OMG.Library_Management_Systenm.DTO.RequestDto.UpdateStudentMobRequestDto;
import com.OMG.Library_Management_Systenm.DTO.ResponseDto.StudentResponseDto;
import com.OMG.Library_Management_Systenm.DTO.ResponseDto.UpdateStudentMobResponseDto;
import com.OMG.Library_Management_Systenm.entity.Student;
import com.OMG.Library_Management_Systenm.exception.StudentNotFoundException;

public interface StudentService {

    public String addStudent(StudentRequestDto studentRequestDto);

    public StudentResponseDto getStudentById(int id);
    public UpdateStudentMobResponseDto updateMobNo(UpdateStudentMobRequestDto updateStudentMobRequestDto) throws StudentNotFoundException;
}
