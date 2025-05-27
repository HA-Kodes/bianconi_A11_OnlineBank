package com.codercampus.Assignment11.web;

import com.codercampus.Assignment11.service.TransactionService;
import org.apache.tomcat.util.descriptor.tld.TldRuleSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @GetMapping("/transactions")
    public String getAllTransactions(ModelMap model) {
        model.put("transactions",transactionService.findAll());
        return "transactions";
    }
    @GetMapping("/transactions/{transactionId}")
    public String getTransactions(@PathVariable Long transactionId, ModelMap model) {
        model.put("transaction", transactionService.findById(transactionId));
        return "transaction";
    }
}