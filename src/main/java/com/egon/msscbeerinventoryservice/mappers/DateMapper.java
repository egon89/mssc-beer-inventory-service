package com.egon.msscbeerinventoryservice.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Component
public class DateMapper {
  public OffsetDateTime toOffsetDateTime(Timestamp ts) {
    if (Objects.isNull(ts)) return null;

    return OffsetDateTime.of(ts.toLocalDateTime(), ZoneOffset.UTC);
  }

  public Timestamp toTimestamp(OffsetDateTime offsetDateTime) {
    if (Objects.isNull(offsetDateTime)) return null;

    return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
  }
}
