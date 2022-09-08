package com.example.company.api.company;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyResponse {
  private String name;
  private String identificationNumber;
  private String type;
  private String address;
  private String addressNumber;
  private String additional;
  private String zipCode;
  private String neighborhood;
  private String city;
  private String uf;
  private String email;
  private String phoneNumber;
}
