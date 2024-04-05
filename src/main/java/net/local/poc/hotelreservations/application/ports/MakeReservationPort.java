package net.local.poc.hotelreservations.application.ports;

import net.local.poc.hotelreservations.application.dto.ReservationInput;

public interface MakeReservationPort {
    public String execute(ReservationInput input);
}
