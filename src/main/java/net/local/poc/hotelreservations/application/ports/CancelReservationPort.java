package net.local.poc.hotelreservations.application.ports;

public interface CancelReservationPort {
    public void execute(String reservationId);
}
