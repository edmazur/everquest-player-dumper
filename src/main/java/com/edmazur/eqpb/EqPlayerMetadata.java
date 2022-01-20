package com.edmazur.eqpb;

import java.time.LocalDateTime;
import java.util.Optional;

public class EqPlayerMetadata<T> {

  private final Optional<T> maybeData;
  private final LocalDateTime observationTime;

  public EqPlayerMetadata(
      Optional<T> maybeData, LocalDateTime observationTime) {
    this.maybeData = maybeData;
    this.observationTime = observationTime;
  }

  public Optional<T> getData() {
    return maybeData;
  }

  public LocalDateTime getObservationTime() {
    return observationTime;
  }

}
