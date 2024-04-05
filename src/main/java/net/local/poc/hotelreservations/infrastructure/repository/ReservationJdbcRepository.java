package net.local.poc.hotelreservations.infrastructure.repository;

import java.time.Instant;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import net.local.poc.hotelreservations.domain.entities.Reservation;
import net.local.poc.hotelreservations.domain.repository.ReservationRepository;

@Repository
public class ReservationJdbcRepository implements ReservationRepository {
    
    private final JdbcClient jdbcClient;

    public ReservationJdbcRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public boolean hasActiveReservations(String roomId, Instant checkinDate, Instant checkoutDate) {
        var reservations = jdbcClient.sql("SELECT room_id FROM reservation WHERE room_id = :roomId AND checkin_date <= :checkinDate AND checkout_date >= :checkoutDate AND status = 'active'")
                                     .param("roomId", roomId)
                                     .param("checkinDate", checkinDate)
                                     .param("checkoutDate", checkoutDate)
                                     .query().listOfRows();
        return (reservations.size() > 0);
    }

    @Override
    public Optional<Reservation> load(String reservationId) {
        var reservation = jdbcClient.sql("SELECT * FROM reservation WHERE reservation_id = :reservationId")
                                    .param("reservationId", reservationId)
                                    .query(Reservation.class)
                                    .single();
        return Optional.ofNullable(reservation);
    }

    @Override
    public void save(Reservation reservation) {
        jdbcClient.sql("""
            INSERT INTO reservation (reservation_id, room_id, email, checkin_date, checkout_date, price, status, duration) 
            VALUES (:reservationId, :roomId, :email, :checkinDate, :checkoutDate, :price, :status, :duration)    
        """).param("reservationId", reservation.getReservationId())
            .param("roomId", reservation.getRoomId())
            .param("email", reservation.getEmail().getValue())
            .param("checkinDate", reservation.getPeriod().getStart())
            .param("checkoutDate", reservation.getPeriod().getEnd())
            .param("price", reservation.getPrice())
            .param("status", reservation.getStatus())
            .param("duration", reservation.getDuration())
        .update();
    }
}
