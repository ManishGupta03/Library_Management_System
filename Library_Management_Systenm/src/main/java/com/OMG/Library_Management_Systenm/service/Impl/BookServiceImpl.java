package com.OMG.Library_Management_Systenm.service.Impl;

import com.OMG.Library_Management_Systenm.entity.Author;
import com.OMG.Library_Management_Systenm.entity.Book;
import com.OMG.Library_Management_Systenm.repository.AuthorRepository;
import com.OMG.Library_Management_Systenm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public String addBook(Book book) throws Exception {

        Author author;
        try{
            author = authorRepository.findById(book.getAuthor().getId()).get();
        }
        catch (Exception e){
            throw new Exception("Author not present");
        }

        author.getBooks().add(book);
        book.setAuthor(author);

        authorRepository.save(author);
        return "Book added";

    }
}