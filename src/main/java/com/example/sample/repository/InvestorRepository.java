package com.example.sample.repository;

import com.example.sample.domain.Investor;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface InvestorRepository extends CrudRepository<Investor, UUID> {
}
