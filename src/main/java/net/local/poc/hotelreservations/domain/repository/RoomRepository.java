package net.local.poc.hotelreservations.domain.repository;

import java.util.List;
import java.util.Optional;

import net.local.poc.hotelreservations.domain.entities.Room;

public interface RoomRepository {
    public List<Room> load();
    public Optional<Room> load(String roomId);
}
