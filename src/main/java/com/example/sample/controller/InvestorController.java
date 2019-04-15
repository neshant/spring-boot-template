package com.example.sample.controller;

import com.example.sample.service.ClientInvestmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/investors")
public class InvestorController {

    private ClientInvestmentService clientInvestmentService;

    public InvestorController(ClientInvestmentService clientInvestmentService) {
        this.clientInvestmentService = clientInvestmentService;
    }

    public static final Logger logger = LoggerFactory.getLogger(InvestorController.class);

    @GetMapping
    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> getFundsListForInvestor(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(clientInvestmentService.findInvestorById(id));
    }
}
