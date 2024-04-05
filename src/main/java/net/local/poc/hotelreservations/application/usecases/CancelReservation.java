package net.local.poc.hotelreservations.application.usecases;

import net.local.poc.hotelreservations.application.ports.CancelReservationPort;
import net.local.poc.hotelreservations.domain.repository.ReservationRepository;

public class CancelReservation implements CancelReservationPort {
    
    private final ReservationRepository repository;

    public CancelReservation(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String reservationId) {
        var reservation = repository.load(reservationId).orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.cancel();
        repository.save(reservation);
    }
}
