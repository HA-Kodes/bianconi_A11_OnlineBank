package com.codercampus.Assignment11.service;

import com.codercampus.Assignment11.domain.Transaction;
import com.codercampus.Assignment11.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> findAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        transactions.sort(Comparator.comparing(Transaction::getDate));
        return transactions;
    }

    public Optional<Transaction> findById(Long id) {
        return Optional.ofNullable(transactionRepository.findById(id));
    }

    public void save(Transaction transaction) {
        List<Transaction> allTransactions = transactionRepository.findAll();

        if (transaction.getId() == null) {
            Long maxId = allTransactions.stream()
                    .map(Transaction::getId)
                    .filter(Objects::nonNull)
                    .max(Long::compareTo)
                    .orElse(0L);
            transaction.setId(maxId + 1);
        }

        allTransactions.add(transaction);
    }
}
