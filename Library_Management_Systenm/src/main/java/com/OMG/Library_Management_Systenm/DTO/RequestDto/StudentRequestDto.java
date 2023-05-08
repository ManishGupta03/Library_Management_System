package com.OMG.Library_Management_Systenm.DTO.RequestDto;

import com.OMG.Library_Management_Systenm.enums.Department;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentRequestDto {

    private String name;

    private int age;

    private Department department;

    private String mobNo;
}
