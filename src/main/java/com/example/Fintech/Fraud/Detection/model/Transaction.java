package com.example.Fintech.Fraud.Detection.model; // <--- FIXED

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;
    private BigDecimal amount;
    private String merchant;
    private String location;
    
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    public enum TransactionStatus {
        PENDING, APPROVED, SUSPICIOUS
    }
}