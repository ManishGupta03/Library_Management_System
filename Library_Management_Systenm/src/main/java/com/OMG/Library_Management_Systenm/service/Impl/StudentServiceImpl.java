package com.OMG.Library_Management_Systenm.service.Impl;

import com.OMG.Library_Management_Systenm.DTO.RequestDto.StudentRequestDto;
import com.OMG.Library_Management_Systenm.DTO.RequestDto.UpdateStudentMobRequestDto;
import com.OMG.Library_Management_Systenm.DTO.ResponseDto.CardResponseDto;
import com.OMG.Library_Management_Systenm.DTO.ResponseDto.StudentResponseDto;
import com.OMG.Library_Management_Systenm.DTO.ResponseDto.UpdateStudentMobResponseDto;
import com.OMG.Library_Management_Systenm.entity.Card;
import com.OMG.Library_Management_Systenm.entity.Student;
import com.OMG.Library_Management_Systenm.enums.CardStatus;
import com.OMG.Library_Management_Systenm.exception.StudentNotFoundException;
import com.OMG.Library_Management_Systenm.repository.StudentRepository;
import com.OMG.Library_Management_Systenm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public String addStudent(StudentRequestDto studentRequestDto) {

        Student student = new Student();
        student.setAge(studentRequestDto.getAge());
        student.setName(studentRequestDto.getName());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setMobNo(studentRequestDto.getMobNo());

        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setValidTill("2024-01-01");
        card.setStudent(student);

        student.setCard(card);
        studentRepository.save(student);

        return "Student added";
    }

    @Override
    public StudentResponseDto getStudentById(int id) {

        Student student = studentRepository.findById(id).get();

        // prepare responsedto
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setDepartment(student.getDepartment());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setMobNo(student.getMobNo());

        CardResponseDto cardResponseDto = new CardResponseDto();
        cardResponseDto.setIssueDate(student.getCard().getIssueDate());
        cardResponseDto.setCardStatus(student.getCard().getCardStatus());
        cardResponseDto.setUpdatedOn(student.getCard().getUpdatedOn());
        cardResponseDto.setValidTill(student.getCard().getValidTill());
        cardResponseDto.setId(student.getCard().getId());

        studentResponseDto.setCardResponseDto(cardResponseDto);
        return studentResponseDto;
    }

    @Override
    public UpdateStudentMobResponseDto updateMobNo(UpdateStudentMobRequestDto updateStudentMobRequestDto) throws StudentNotFoundException {

        try{
            Student student = studentRepository.findById(updateStudentMobRequestDto.getId()).get();
            student.setMobNo(updateStudentMobRequestDto.getMobNo());
            Student updatedStudent = studentRepository.save(student);

            // prepare response dto
            UpdateStudentMobResponseDto updateStudentMobNoResponseDto = new UpdateStudentMobResponseDto();
            updateStudentMobNoResponseDto.setName(updatedStudent.getName());
            updateStudentMobNoResponseDto.setMobNo(updateStudentMobRequestDto.getMobNo());
            return updateStudentMobNoResponseDto;
        }
        catch (Exception e){
            throw new StudentNotFoundException("Invalid student id");
        }
    }
}
