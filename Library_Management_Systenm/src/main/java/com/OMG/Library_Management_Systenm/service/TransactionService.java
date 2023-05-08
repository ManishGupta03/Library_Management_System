package com.OMG.Library_Management_Systenm.service;

import com.OMG.Library_Management_Systenm.DTO.RequestDto.IssueBookRequestDto;
import com.OMG.Library_Management_Systenm.DTO.ResponseDto.IssueBookResponseDto;

public interface TransactionService {

    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception;
}
