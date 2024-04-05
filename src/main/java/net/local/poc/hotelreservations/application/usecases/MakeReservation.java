package net.local.poc.hotelreservations.application.usecases;

import java.time.ZoneOffset;

import net.local.poc.hotelreservations.application.dto.ReservationInput;
import net.local.poc.hotelreservations.application.ports.MakeReservationPort;
import net.local.poc.hotelreservations.domain.factory.ReservationFactory;
import net.local.poc.hotelreservations.domain.repository.ReservationRepository;
import net.local.poc.hotelreservations.domain.repository.RoomRepository;

public class MakeReservation implements MakeReservationPort {
    
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    
    public MakeReservation(RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public String execute(ReservationInput input) {
        var room = roomRepository.load(input.roomId()).orElseThrow(() -> new RuntimeException("Room not found"));

        var hasReservations = reservationRepository.hasActiveReservations(room.getRoomId(), input.checkinDate().toInstant(ZoneOffset.UTC), input.checkoutDate().toInstant(ZoneOffset.UTC));

        if (hasReservations) {
            throw new RuntimeException("Room is not available");
        }
        
        var reservation = ReservationFactory.create(room.getRoomType().name(), room.getRoomId(), input.email(), input.checkinDate().toInstant(ZoneOffset.UTC), input.checkoutDate().toInstant(ZoneOffset.UTC));
            reservation.calculate(room);
        
        reservationRepository.save(reservation);

        return reservation.getReservationId();
    }
}
