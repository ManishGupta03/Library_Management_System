package com.OMG.Library_Management_Systenm.repository;

import com.OMG.Library_Management_Systenm.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
