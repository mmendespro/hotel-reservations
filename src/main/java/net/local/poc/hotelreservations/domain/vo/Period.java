package net.local.poc.hotelreservations.domain.vo;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Period {
    
    private final Instant start;
    private final Instant end;
    
    public Period(Instant start, Instant end) {
        if(start.isAfter(end)) {
            throw new RuntimeException("Invalid period");
        }
        this.start = start;
        this.end = end;
    }

    public Instant getStart() {
        return start;
    }

    public Instant getEnd() {
        return end;
    }

    public Long getDiffInDays() {
        return ChronoUnit.DAYS.between(start, end);
    }

    public Long getDiffInHours() {
        return ChronoUnit.HOURS.between(start, end);
    }

    public Long getDiffInMinutes() {
        return ChronoUnit.MINUTES.between(start, end);
    }
}
