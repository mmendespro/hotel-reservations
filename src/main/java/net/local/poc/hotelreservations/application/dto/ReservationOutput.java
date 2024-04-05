package net.local.poc.hotelreservations.application.dto;

public record ReservationOutput(String reservationId, String status, String roomId, Long totalPeriod, Float total) {
}
