package net.local.poc.hotelreservations.application.ports;

import java.util.List;

import net.local.poc.hotelreservations.application.dto.RoomOutput;

public interface ListRoomPort {
    public List<RoomOutput> execute();
}
