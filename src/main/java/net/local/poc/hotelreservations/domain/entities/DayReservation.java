package net.local.poc.hotelreservations.domain.entities;

import java.time.Instant;
import java.util.UUID;

public class DayReservation extends Reservation {

    public DayReservation(String reservationId, String roomId, String email, Instant checkinDate, Instant checkoutDate, String status) {
        super(reservationId, roomId, email, checkinDate, checkoutDate, status, 0F, 0L);
    }

    public DayReservation(String reservationId, String roomId, String email, Instant checkinDate, Instant checkoutDate,
            String status, Float price, Long duration) {
        super(reservationId, roomId, email, checkinDate, checkoutDate, status, price, duration);
    }

    public static DayReservation create(String roomId, String email, Instant checkinDate, Instant checkoutDate) {
        return new DayReservation(UUID.randomUUID().toString(), roomId, email, checkinDate, checkoutDate, "active");
    }

    @Override
    public void calculate(Room room) {
        this.duration = this.getPeriod().getDiffInDays();
		this.price = this.duration * room.getPrice().getAmount();
    }
    
}
