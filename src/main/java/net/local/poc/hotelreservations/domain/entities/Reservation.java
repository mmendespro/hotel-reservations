package net.local.poc.hotelreservations.domain.entities;

import java.time.Instant;

import net.local.poc.hotelreservations.domain.vo.Email;
import net.local.poc.hotelreservations.domain.vo.Period;

// Entity Root formando um Aggregate, Facade
public abstract class Reservation {
    
    private final String reservationId;
    private final String roomId;
    private final Email email;
    private final Period period;
    private String status;
    protected Float price;
    protected Long duration;
    
    public Reservation(String reservationId, String roomId, String email, Instant checkinDate, Instant checkoutDate, String status, Float price, Long duration) {
        this.reservationId = reservationId;
        this.roomId = roomId;
        this.email = new Email(email);
		this.period = new Period(checkinDate, checkoutDate);
        this.status = status;
        this.price = price;
        this.duration = duration;
    }

    public abstract void calculate (Room room);

    public void cancel() {
        if(status.equals("cancelled")) {
            throw new RuntimeException("Reservation already cancelled");
        }
        this.status = "cancelled";
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomId() {
        return roomId;
    }

    public Email getEmail() {
        return email;
    }

    public Period getPeriod() {
        return period;
    }

    public String getStatus() {
        return status;
    }

    public Long getDuration() {
        return duration;
    }

    public Float getPrice() {
        return price;
    }    
}
