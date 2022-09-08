package com.example.company.api.company;

import static org.springframework.http.HttpStatus.OK;

import com.example.company.domain.company.CompanyService;
import com.example.company.domain.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/company")
public class CompanyController {
  private final CompanyService service;

  @GetMapping("/{identificationNumber}")
  public Mono<CompanyResponse> findById(
      @PathVariable("identificationNumber") final String registrationNumber,
      ServerWebExchange exchange) {
    return service
        .findCompanyByIdentificationNumber(Utils.removeNonNumbers(registrationNumber))
        .map(CompanyMapper::toResponse)
        .doOnSuccess(r -> exchange.getResponse().setStatusCode(OK));
  }
}
