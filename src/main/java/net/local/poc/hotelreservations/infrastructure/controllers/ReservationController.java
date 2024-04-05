package net.local.poc.hotelreservations.infrastructure.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.local.poc.hotelreservations.application.dto.ReservationInput;
import net.local.poc.hotelreservations.application.dto.ReservationOutput;
import net.local.poc.hotelreservations.application.ports.GetReservationPort;
import net.local.poc.hotelreservations.application.ports.MakeReservationPort;

@RestController
@RequestMapping("reservations")
public class ReservationController {

    private final GetReservationPort reservationPort;
    private final MakeReservationPort makeReservationPort;

    public ReservationController(GetReservationPort reservationPort, MakeReservationPort makeReservationPort) {
        this.reservationPort = reservationPort;
        this.makeReservationPort = makeReservationPort;
    }

    @GetMapping("{reservationId}")
    public ResponseEntity<ReservationOutput> loadReservation(@PathVariable String reservationId) {
        return ResponseEntity.ok(reservationPort.execute(reservationId));
    }

    @PostMapping
    public ResponseEntity<String> makeReserve(@RequestBody ReservationInput input) {
        var result = makeReservationPort.execute(input);
        var uri = URI.create(String.format("/reservations/%s", result));
        return ResponseEntity.created(uri).body(result);
    }
}
