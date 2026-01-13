package com.example.Fintech.Fraud.Detection.controller; // <--- FIXED

import com.example.Fintech.Fraud.Detection.model.Transaction;      // <--- FIXED IMPORT
import com.example.Fintech.Fraud.Detection.repository.TransactionRepository; // <--- FIXED IMPORT
import com.example.Fintech.Fraud.Detection.service.FraudDetectionService;    // <--- FIXED IMPORT
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*") 
public class TransactionController {

    @Autowired
    private TransactionRepository repository;
    
    @Autowired
    private FraudDetectionService fraudService;

    @PostMapping("/upload")
    public ResponseEntity<List<Transaction>> uploadFile(@RequestParam("file") MultipartFile file) {
        List<Transaction> savedTransactions = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean firstLine = true; 
            
            while ((line = reader.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; }

                // 1. Basic Validation: Skip empty lines
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");

                // 2. Safety Check: Do we have enough columns?
                if (data.length < 4) {
                    System.err.println("SKIPPING INVALID ROW (Not enough columns): " + line);
                    continue;
                }

                try {
                    Transaction t = new Transaction();
                    
                    // DATE
                    t.setTimestamp(LocalDateTime.parse(data[0], formatter));
                    
                    // MERCHANT (Clean quotes if present)
                    t.setMerchant(data[1].replace("\"", "")); 
                    
                    // AMOUNT (The Crash Zone)
                    // Fix: Trim whitespace and remove quotes before parsing
                    String cleanAmount = data[2].replace("\"", "").trim();
                    t.setAmount(new BigDecimal(cleanAmount));
                    
                    // LOCATION
                    t.setLocation(data[3]);
                    
                    // FRAUD CHECK
                    t.setStatus(fraudService.evaluate(t));
                    
                    savedTransactions.add(repository.save(t));
                    
                } catch (Exception e) {
                    // 3. Error Handling: Log the bad row but DON'T crash the server
                    System.err.println("FAILED TO PARSE ROW: " + line);
                    System.err.println("Reason: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(savedTransactions);
    }
}