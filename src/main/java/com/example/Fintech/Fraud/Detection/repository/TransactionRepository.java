package com.example.Fintech.Fraud.Detection.repository; // <--- FIXED

import com.example.Fintech.Fraud.Detection.model.Transaction; // <--- FIXED IMPORT
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}