package com.example.company.domain.company;

import static com.example.company.domain.company.Status.ERROR;

import com.example.company.domain.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CompanyService {
  private final CompanyCache cache;
  private final CompanyClient client;
  private final CompanyRepository repository;

  public Mono<CompanyEntity> findCompanyByIdentificationNumber(final String identificationNumber) {
    var requestAndSaveCompanyMono =
        this.client
            .findCompanyByIdentificationNumberV1(identificationNumber)
            .flatMap(
                companyDto -> {
                  if (ERROR.equals(companyDto.getStatus())) {
                    return Mono.error(new DomainException(companyDto.getMessage()));
                  }

                  return Mono.just(CompanyMapper.toEntity(companyDto));
                })
            .flatMap(repository::save)
            .flatMap(cache::insert);

    var findCompanyMono =
        repository
            .findByIdentificationNumber(identificationNumber)
            .flatMap(cache::insert)
            .switchIfEmpty(requestAndSaveCompanyMono);

    return cache.get(identificationNumber).switchIfEmpty(findCompanyMono);
  }
}
