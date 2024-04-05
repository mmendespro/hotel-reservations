package net.local.poc.hotelreservations.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import net.local.poc.hotelreservations.domain.entities.Room;
import net.local.poc.hotelreservations.domain.repository.RoomRepository;
import net.local.poc.hotelreservations.domain.vo.Price;
import net.local.poc.hotelreservations.domain.vo.RoomType;

@Repository
public class RoomJdbcRepository implements RoomRepository {

    private final JdbcClient jdbcClient;

    public RoomJdbcRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Room> load(String roomId) {
        var roomData = jdbcClient.sql("SELECT ROOM_ID, ROOM_TYPE, PRICE FROM ROOM WHERE ROOM_ID = :roomId")
                             .param("roomId", roomId)
                             .query().singleRow();
        
        if (roomData.size() > 0) {
            Room room = new Room(roomId, RoomType.valueOf(roomData.get("ROOM_TYPE").toString()), new Price("BRL", Float.valueOf(roomData.get("PRICE").toString())));
            return Optional.of(room);
        }

        return Optional.empty();
    }

    @Override
    public List<Room> load() {
        var roomData = jdbcClient.sql("SELECT ROOM_ID, ROOM_TYPE, PRICE FROM ROOM")
                             .query().listOfRows();
        if(roomData.size() > 0) {
            return roomData.stream()
                           .map(obj -> new Room(obj.get("ROOM_ID").toString(), RoomType.valueOf(obj.get("ROOM_TYPE").toString()), new Price("BRL", Float.valueOf(obj.get("PRICE").toString()))))
                           .toList();
        }
        return List.of();
    }
    
}
