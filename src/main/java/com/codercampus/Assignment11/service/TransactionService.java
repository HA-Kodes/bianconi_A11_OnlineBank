package com.codercampus.Assignment11.service;

import com.codercampus.Assignment11.domain.Transaction;
import com.codercampus.Assignment11.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service

public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> findAll () {
        List<Transaction> transactions = transactionRepository.findAll();
        transactions.sort(Comparator.comparing(Transaction::getDate));
        return transactions;
    }
    public Transaction findById(Long transactionId) {
        return transactionRepository.findById(transactionId);
    }

}

