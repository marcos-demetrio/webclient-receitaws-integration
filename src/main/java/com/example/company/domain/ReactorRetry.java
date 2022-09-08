package com.example.company.domain;

import com.example.company.domain.exception.DomainException;
import com.example.company.domain.exception.NotFoundException;
import java.time.Duration;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

public class ReactorRetry {
  private static final int MAX_RETRIES = 1;
  private static final Duration INITIAL_INTERVAL = Duration.ofSeconds(2L);

  private ReactorRetry() {
    throw new IllegalStateException("Operation not allowed");
  }

  public static RetryBackoffSpec retryWebClient() {
    return Retry.backoff(MAX_RETRIES, INITIAL_INTERVAL)
        .filter(
            throwable ->
                !((throwable instanceof DomainException)
                        || (throwable instanceof NotFoundException))
                    && (!(throwable instanceof WebClientResponseException)
                        || ((WebClientResponseException) throwable)
                            .getStatusCode()
                            .is5xxServerError()));
  }
}
