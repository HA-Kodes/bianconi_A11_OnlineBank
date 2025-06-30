package com.codercampus.Assignment11.web;

import com.codercampus.Assignment11.domain.Transaction;
import com.codercampus.Assignment11.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public String getAllTransactions(Model model) {
        model.addAttribute("transactions", transactionService.findAll());
        return "transactions"; // maps to transactions.html
    }

    @GetMapping("/{id}")
    public String getTransactionById(@PathVariable Long id, Model model) {
        Transaction transaction = transactionService.findById(id)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction ID not found"));

        model.addAttribute("transaction", transaction);
        return "transaction"; // maps to transaction.html
    }
}