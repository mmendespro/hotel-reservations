package net.local.poc.hotelreservations.application.ports;

import net.local.poc.hotelreservations.application.dto.ReservationOutput;

public interface GetReservationPort {
    public ReservationOutput execute(String reservationId);
}
