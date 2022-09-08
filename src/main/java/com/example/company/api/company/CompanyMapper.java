package com.example.company.api.company;

import com.example.company.domain.company.CompanyEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CompanyMapper {
  public CompanyResponse toResponse(final CompanyEntity entity) {
    return CompanyResponse.builder()
        .name(entity.getName())
        .identificationNumber(entity.getIdentificationNumber())
        .type(entity.getType())
        .address(entity.getAddress())
        .addressNumber(entity.getAddressNumber())
        .additional(entity.getAdditional())
        .zipCode(entity.getZipCode())
        .neighborhood(entity.getNeighborhood())
        .city(entity.getCity())
        .uf(entity.getUf())
        .email(entity.getEmail())
        .phoneNumber(entity.getPhoneNumber())
        .build();
  }
}
