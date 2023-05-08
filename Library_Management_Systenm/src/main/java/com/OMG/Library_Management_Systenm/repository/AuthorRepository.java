package com.OMG.Library_Management_Systenm.repository;

import com.OMG.Library_Management_Systenm.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

    Author findByEmail(String email);
}
