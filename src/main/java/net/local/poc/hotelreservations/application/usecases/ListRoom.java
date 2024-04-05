package net.local.poc.hotelreservations.application.usecases;

import java.util.List;

import net.local.poc.hotelreservations.application.dto.RoomOutput;
import net.local.poc.hotelreservations.application.ports.ListRoomPort;
import net.local.poc.hotelreservations.domain.repository.RoomRepository;

public class ListRoom implements ListRoomPort {

    private final RoomRepository repository;

    public ListRoom(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RoomOutput> execute() {
        return repository.load()
                         .stream()
                         .map(room -> new RoomOutput(room.getRoomId(), room.getRoomType().name(), room.getPrice().getAmount()))
                         .toList();
    }
    
}
