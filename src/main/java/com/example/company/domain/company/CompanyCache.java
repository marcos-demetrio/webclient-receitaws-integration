package com.example.company.domain.company;

import static io.lettuce.core.SetArgs.Builder.ex;

import com.example.company.domain.exception.CacheProcessorException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CompanyCache {
  private static final String KEY_PREFIX = "CompanyId:%s";
  private static final long TTL = 15L;

  private final RedisReactiveCommands<String, String> reactiveCommands;
  private final ObjectMapper mapper;

  public Mono<CompanyEntity> insert(final CompanyEntity entity) {
    var key = String.format(KEY_PREFIX, entity.getIdentificationNumber());

    return Mono.fromCallable(() -> mapper.writeValueAsString(entity))
        .onErrorResume(
            t -> Mono.error(new IllegalArgumentException("Unable to convert using ObjectMapper")))
        .flatMap(value -> reactiveCommands.set(key, value, ex(TTL)).thenReturn(entity))
        .flatMap(Mono::just);
  }

  public Mono<CompanyEntity> get(final String identificationNumber) {
    var key = String.format(KEY_PREFIX, identificationNumber);

    return reactiveCommands
        .get(key)
        .map(
            value -> {
              try {
                return mapper.readValue(value, CompanyEntity.class);
              } catch (JsonProcessingException e) {
                throw new CacheProcessorException(e.getMessage());
              }
            })
        .flatMap(Mono::just);
  }
}
