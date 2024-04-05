package net.local.poc.hotelreservations.domain.repository;

import java.time.Instant;
import java.util.Optional;

import net.local.poc.hotelreservations.domain.entities.Reservation;

public interface ReservationRepository {
    
    public boolean hasActiveReservations (String roomId, Instant checkinDate, Instant checkoutDate);
    public Optional<Reservation> load(String reservationId);
    public void save(Reservation reservation);
}
