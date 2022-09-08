package com.example.company.domain.company;

import com.example.company.domain.Utils;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CompanyMapper {
  public CompanyEntity toEntity(final CompanyDto dto) {
    return CompanyEntity.builder()
        .name(dto.getName())
        .identificationNumber(Utils.removeNonNumbers(dto.getIdentificationNumber()))
        .type(dto.getType())
        .address(dto.getAddress())
        .addressNumber(dto.getAddressNumber())
        .additional(dto.getAdditional())
        .zipCode(dto.getZipCode())
        .neighborhood(dto.getNeighborhood())
        .city(dto.getCity())
        .uf(dto.getUf())
        .email(dto.getEmail())
        .phoneNumber(dto.getPhoneNumber())
        .build();
  }
}
