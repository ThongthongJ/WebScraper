package com.example.websrcapper.repository;

import com.example.websrcapper.entity.TrTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrTransactionRepository extends JpaRepository<TrTransactionEntity,String> {

}
