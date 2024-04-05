package net.local.poc.hotelreservations.domain.factory;

import java.time.Instant;

import net.local.poc.hotelreservations.domain.entities.DayReservation;
import net.local.poc.hotelreservations.domain.entities.HourReservation;
import net.local.poc.hotelreservations.domain.entities.Reservation;

public class ReservationFactory {
    
    public static Reservation create (String type, String roomId, String email, Instant checkinDate, Instant checkoutDate) {
		if (type.equals("DAY")) return DayReservation.create(roomId, email, checkinDate, checkoutDate);
		if (type.equals("HOUR")) return HourReservation.create(roomId, email, checkinDate, checkoutDate);
		throw new RuntimeException("Invalid reservation type");
	}

    public static Reservation restore (String type, String reservationId, String roomId, String email, Instant checkinDate, Instant checkoutDate, String status, Float price, Long duration) {
		if (type.equals("DAY")) return new DayReservation(reservationId, roomId, email, checkinDate, checkoutDate, status, price, duration);
		if (type.equals("HOUR")) return new HourReservation(reservationId, roomId, email, checkinDate, checkoutDate, status, price, duration);
		throw new RuntimeException("Invalid reservation type");
	}
}
