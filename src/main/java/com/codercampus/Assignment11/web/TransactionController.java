package com.codercampus.Assignment11.web;

import com.codercampus.Assignment11.domain.Transaction;
import com.codercampus.Assignment11.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public String getAllTransactions(@RequestParam(required = false) String actionType, Model model) {
        model.addAttribute("transactions", transactionService.findAll());
        model.addAttribute("actionType", actionType);
        return "transactions";
    }

    @PostMapping("/add")
    public String addTransaction(@ModelAttribute Transaction transaction, RedirectAttributes redirectAttributes) {
        transactionService.save(transaction);
        redirectAttributes.addAttribute("actionType", "added"); // appends to query string
        return "redirect:/transactions";
    }

    @GetMapping("/{id:[0-9]+}")
    public String getTransactionById(@PathVariable Long id, Model model) {
        Transaction transaction = transactionService.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction ID not found"));
        model.addAttribute("transaction", transaction);
        return "transaction";
    }
}