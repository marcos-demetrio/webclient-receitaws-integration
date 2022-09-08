package com.example.company.domain;

import static java.util.Objects.nonNull;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {
  public String removeNonNumbers(final String source) {
    return nonNull(source) ? source.replaceAll("\\D", "") : "";
  }
}
