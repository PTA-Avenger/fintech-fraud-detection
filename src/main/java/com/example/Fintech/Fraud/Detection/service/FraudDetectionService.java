package com.example.Fintech.Fraud.Detection.service; // <--- FIXED

import com.example.Fintech.Fraud.Detection.model.Transaction; // <--- FIXED IMPORT
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
public class FraudDetectionService {

    private static final Set<String> BLACKLISTED_MERCHANTS = new HashSet<>();

    static {
        BLACKLISTED_MERCHANTS.add("Unknown Offshore LLC");
        BLACKLISTED_MERCHANTS.add("Crypto-mixer-X");
        BLACKLISTED_MERCHANTS.add("Gambling-Site-777");
        BLACKLISTED_MERCHANTS.add("DarkWeb Market");
    }

    public Transaction.TransactionStatus evaluate(Transaction t) {
        if (BLACKLISTED_MERCHANTS.contains(t.getMerchant())) {
            return Transaction.TransactionStatus.SUSPICIOUS;
        }

        BigDecimal threshold = new BigDecimal("10000.00");
        int hour = t.getTimestamp().getHour();

        if (t.getAmount().compareTo(threshold) > 0 && (hour >= 23 || hour <= 4)) {
            return Transaction.TransactionStatus.SUSPICIOUS;
        }

        return Transaction.TransactionStatus.APPROVED;
    }
}