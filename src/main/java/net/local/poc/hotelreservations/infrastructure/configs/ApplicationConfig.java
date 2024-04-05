package net.local.poc.hotelreservations.infrastructure.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import net.local.poc.hotelreservations.application.ports.CancelReservationPort;
import net.local.poc.hotelreservations.application.ports.GetReservationPort;
import net.local.poc.hotelreservations.application.ports.ListRoomPort;
import net.local.poc.hotelreservations.application.ports.MakeReservationPort;
import net.local.poc.hotelreservations.application.usecases.CancelReservation;
import net.local.poc.hotelreservations.application.usecases.GetReservation;
import net.local.poc.hotelreservations.application.usecases.ListRoom;
import net.local.poc.hotelreservations.application.usecases.MakeReservation;
import net.local.poc.hotelreservations.domain.repository.ReservationRepository;
import net.local.poc.hotelreservations.domain.repository.RoomRepository;

@Configuration
public class ApplicationConfig {

    @Bean
    public ObjectMapper configureObjectMapper() {
        var mapper = new ObjectMapper();
        mapper.registerModule(new ParameterNamesModule())
              .registerModule(new Jdk8Module())
              .registerModule(new JavaTimeModule());
        mapper.findAndRegisterModules();
        return mapper;
    }

    @Bean
    public ListRoomPort listRoomPort(RoomRepository repository) {
        return new ListRoom(repository);
    }
    
    @Bean
    public GetReservationPort reservationPort(ReservationRepository repository) {
        return new GetReservation(repository);
    }

    @Bean
    public CancelReservationPort cancelReservationPort(ReservationRepository repository) {
        return new CancelReservation(repository);
    }

    @Bean
    public MakeReservationPort makeReservationPort(RoomRepository roomRepository, ReservationRepository reservationRepository) {
        return new MakeReservation(roomRepository, reservationRepository);
    }
}
