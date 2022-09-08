package com.example.company.domain.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompanyDto {

  @JsonProperty("status")
  private Status status;

  @JsonProperty("message")
  private String message;

  @JsonProperty("nome")
  private String name;

  @JsonProperty("cnpj")
  private String identificationNumber;

  @JsonProperty("tipo")
  private String type;

  @JsonProperty("logradouro")
  private String address;

  @JsonProperty("numero")
  private String addressNumber;

  @JsonProperty("complemento")
  private String additional;

  @JsonProperty("cep")
  private String zipCode;

  @JsonProperty("bairro")
  private String neighborhood;

  @JsonProperty("municipio")
  private String city;

  @JsonProperty("uf")
  private String uf;

  @JsonProperty("email")
  private String email;

  @JsonProperty("telefone")
  private String phoneNumber;
}
