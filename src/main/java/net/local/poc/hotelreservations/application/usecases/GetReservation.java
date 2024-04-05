package net.local.poc.hotelreservations.application.usecases;

import net.local.poc.hotelreservations.application.dto.ReservationOutput;
import net.local.poc.hotelreservations.application.ports.GetReservationPort;
import net.local.poc.hotelreservations.domain.repository.ReservationRepository;

public class GetReservation implements GetReservationPort {
    
    private final ReservationRepository repository;

    public GetReservation(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public ReservationOutput execute(String reservationId) {
        var reservation = repository.load(reservationId).orElseThrow(() -> new RuntimeException("Reservation not found"));

        return new ReservationOutput(reservation.getReservationId(), reservation.getStatus(), reservation.getRoomId(), reservation.getDuration(), reservation.getPrice());
    }
}
