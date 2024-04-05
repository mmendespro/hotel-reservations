package net.local.poc.hotelreservations.infrastructure.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.local.poc.hotelreservations.application.dto.RoomOutput;
import net.local.poc.hotelreservations.application.ports.ListRoomPort;

@RestController
@RequestMapping("rooms")
public class RoomController {

    private final ListRoomPort listRoomPort;

    public RoomController(ListRoomPort listRoomPort) {
        this.listRoomPort = listRoomPort;
    }

    @GetMapping
    public ResponseEntity<List<RoomOutput>> load() {
        return ResponseEntity.ok(listRoomPort.execute());
    }
}
