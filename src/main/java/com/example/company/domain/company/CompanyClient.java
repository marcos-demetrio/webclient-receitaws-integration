package com.example.company.domain.company;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.example.company.domain.ReactorRetry;
import com.example.company.domain.exception.DomainException;
import com.example.company.domain.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CompanyClient {
  private static final String API_ENDPOINT = "https://receitaws.com.br/v1";

  private final WebClient webClient;

  public CompanyClient(WebClient.Builder builder) {
    this.webClient = builder.baseUrl(API_ENDPOINT).build();
  }

  public Mono<CompanyDto> findCompanyByIdentificationNumberV1(final String identificationNumber) {
    return this.webClient
        .get()
        .uri(builder -> builder.path("/cnpj/{identificationNumber}").build(identificationNumber))
        .accept(APPLICATION_JSON)
        .retrieve()
        .onStatus(
            HttpStatus::is4xxClientError,
            companyResponse -> {
              if (TOO_MANY_REQUESTS.equals(companyResponse.statusCode())) {
                return Mono.error(new DomainException(TOO_MANY_REQUESTS.getReasonPhrase()));
              }

              if (NOT_FOUND.equals(companyResponse.statusCode())) {
                return Mono.error(new NotFoundException(NOT_FOUND.getReasonPhrase()));
              }

              return Mono.empty();
            })
        .bodyToMono(CompanyDto.class)
        .retryWhen(ReactorRetry.retryWebClient());
  }
}
