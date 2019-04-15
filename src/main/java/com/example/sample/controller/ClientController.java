package com.example.sample.controller;


import com.example.sample.domain.Client;
import com.example.sample.request.ClientRequest;
import com.example.sample.service.ClientInvestmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {
    public static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private ClientInvestmentService clientInvestmentService;

    public ClientController(ClientInvestmentService clientInvestmentService) {
        this.clientInvestmentService = clientInvestmentService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Client>> getClients() {
        return ResponseEntity.ok(clientInvestmentService.findAllClients());
    }

    @PostMapping()
    public ResponseEntity<Client> createClient(@Valid @RequestBody ClientRequest clientRequest) {
        return ResponseEntity.ok(clientInvestmentService.createClient(clientRequest));
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> findClientById(@PathVariable("clientId") UUID clientId) {
        Client client = clientInvestmentService.findClientById(clientId);

        return ResponseEntity.ok(client
        );
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable("clientId") UUID clientId,
                                               @Valid @RequestBody ClientRequest clientRequest) {
        return ResponseEntity.ok(clientInvestmentService.updateClient(clientId, clientRequest));
    }
}
