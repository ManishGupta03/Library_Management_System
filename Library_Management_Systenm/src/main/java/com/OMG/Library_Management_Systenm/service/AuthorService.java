package com.OMG.Library_Management_Systenm.service;

import com.OMG.Library_Management_Systenm.DTO.ResponseDto.AuthorResponseDto;
import com.OMG.Library_Management_Systenm.entity.Author;

public interface AuthorService {

    public String addAuthor(Author author);
    public AuthorResponseDto getByEmail(String email);
}
