package net.local.poc.hotelreservations.application.dto;

import java.time.LocalDateTime;

public record ReservationInput(String roomId, String email, LocalDateTime checkinDate, LocalDateTime checkoutDate) {
}
