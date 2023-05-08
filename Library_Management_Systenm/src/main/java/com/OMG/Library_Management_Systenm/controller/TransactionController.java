package com.OMG.Library_Management_Systenm.controller;

import com.OMG.Library_Management_Systenm.DTO.RequestDto.IssueBookRequestDto;
import com.OMG.Library_Management_Systenm.DTO.ResponseDto.IssueBookResponseDto;
import com.OMG.Library_Management_Systenm.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/add")
    public IssueBookResponseDto issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto) throws Exception {

        return transactionService.issueBook(issueBookRequestDto);
    }
}