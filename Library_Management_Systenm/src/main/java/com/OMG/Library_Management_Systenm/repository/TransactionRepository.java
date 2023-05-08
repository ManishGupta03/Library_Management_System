package com.OMG.Library_Management_Systenm.repository;

import com.OMG.Library_Management_Systenm.entity.Transaction;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {


}
