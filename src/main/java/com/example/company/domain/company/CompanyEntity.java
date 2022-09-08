package com.example.company.domain.company;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("company")
public class CompanyEntity {

  @Id
  @Column("company_id")
  private UUID id;

  @Column("name")
  private String name;

  @Column("identification_number")
  private String identificationNumber;

  @Column("type")
  private String type;

  @Column("address")
  private String address;

  @Column("address_number")
  private String addressNumber;

  @Column("additional")
  private String additional;

  @Column("zip_code")
  private String zipCode;

  @Column("neighborhood")
  private String neighborhood;

  @Column("city")
  private String city;

  @Column("uf")
  private String uf;

  @Column("email")
  private String email;

  @Column("phone_number")
  private String phoneNumber;
}
