package com.example.company.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "create")
public class ErrorResponse {
  private int errorCode;
  private String message;
}
