package com.OMG.Library_Management_Systenm.service.Impl;

import com.OMG.Library_Management_Systenm.DTO.ResponseDto.AuthorResponseDto;
import com.OMG.Library_Management_Systenm.entity.Author;
import com.OMG.Library_Management_Systenm.repository.AuthorRepository;
import com.OMG.Library_Management_Systenm.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public String addAuthor(Author author) {

        authorRepository.save(author);
        return "Author added";
    }

    @Override
    public AuthorResponseDto getByEmail(String email) {

        Author author = authorRepository.findByEmail(email);

        // prepare response Dto
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setName(author.getName());
        authorResponseDto.setAge(author.getAge());

        return authorResponseDto;
    }
}
