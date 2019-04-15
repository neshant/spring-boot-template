package com.example.sample.service;


import com.example.sample.domain.Client;
import com.example.sample.domain.Investor;
import com.example.sample.exception.BodyNotFoundException;
import com.example.sample.repository.ClientRepository;
import com.example.sample.repository.InvestorRepository;
import com.example.sample.request.ClientRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientInvestmentService {

    private static final Logger logger = LoggerFactory.getLogger(ClientInvestmentService.class);

    private ClientRepository clientRepository;
    private InvestorRepository investorRepository;

    public ClientInvestmentService(ClientRepository clientRepository,
                                   InvestorRepository investorRepository) {
        this.clientRepository = clientRepository;
        this.investorRepository = investorRepository;
    }

    public Iterable<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Client findClientById(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(BodyNotFoundException::new);
    }

    public Client createClient(ClientRequest clientRequest) {
        Client client =
                Client.builder()
                        .investors(clientRequest.getInvestors())
                        .description(clientRequest.getDescription())
                        .name(clientRequest.getName())
                        .build();

        return clientRepository.save(client);
    }

    public Investor findInvestorById(UUID id) {
        return investorRepository.findById(id).orElseThrow(BodyNotFoundException::new);
    }

    public Client updateClient(UUID clientId, ClientRequest clientRequest) {
        Client client = findClientById(clientId);

        Client updatedClient =
                Client.builder()
                        .id(client.getId())
                        .investors(clientRequest.getInvestors())
                        .description(clientRequest.getDescription())
                        .name(clientRequest.getName())
                        .build();

        return clientRepository.save(updatedClient);
    }
}
