package com.OMG.Library_Management_Systenm.service.Impl;

import com.OMG.Library_Management_Systenm.DTO.RequestDto.IssueBookRequestDto;
import com.OMG.Library_Management_Systenm.DTO.ResponseDto.IssueBookResponseDto;
import com.OMG.Library_Management_Systenm.entity.Book;
import com.OMG.Library_Management_Systenm.entity.Card;
import com.OMG.Library_Management_Systenm.entity.Transaction;
import com.OMG.Library_Management_Systenm.enums.CardStatus;
import com.OMG.Library_Management_Systenm.enums.TransactionStatus;
import com.OMG.Library_Management_Systenm.repository.BookRepository;
import com.OMG.Library_Management_Systenm.repository.CardRepository;
import com.OMG.Library_Management_Systenm.repository.TransactionRepository;
import com.OMG.Library_Management_Systenm.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception {

        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);

        Card card;
        try{
            card = cardRepository.findById(issueBookRequestDto.getCardId()).get();
        }
        catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Invalid card id!!!");
        }

        transaction.setCard(card);

        Book book;
        try{
            book = bookRepository.findById(issueBookRequestDto.getBookId()).get();
        }
        catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Invalid Book id!!!!");
        }

        transaction.setBook(book);

        if(card.getCardStatus()!= CardStatus.ACTIVATED){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Card is not Active!!!");
        }

        if(book.isIssued()==true){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not available!!!");
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        book.setIssued(true);
        book.setCard(card);
        book.getTransactionList().add(transaction);

        card.getBooksIssued().add(book);
        card.getTransactionList().add(transaction);

        cardRepository.save(card); // save card, book and transaction.

        // prepare response dtp;

        IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();
        issueBookResponseDto.setBookName(book.getTitle());
        issueBookResponseDto.setTransactionNumber(transaction.getTransactionNumber());
        issueBookResponseDto.setTransactionStatus(transaction.getTransactionStatus());

        String text = "Congrats! " + card.getStudent().getName() +  " You have been issued the book " + book.getTitle();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("acciojobbackendapril@gmail.com");
        message.setTo(card.getStudent().getMobNo());
        message.setSubject("Issue book");
        message.setText(text);
        emailSender.send(message);

        return issueBookResponseDto;
    }


    // return book api
}