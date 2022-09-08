package com.example.company.domain.company;

import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CompanyRepository extends ReactiveCrudRepository<CompanyEntity, UUID> {
  Mono<CompanyEntity> findByIdentificationNumber(final String identificationNumber);
}
