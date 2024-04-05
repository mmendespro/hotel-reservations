package net.local.poc.hotelreservations.domain.entities;

import net.local.poc.hotelreservations.domain.vo.Price;
import net.local.poc.hotelreservations.domain.vo.RoomType;

public class Room {
    
    private final String roomId;
    private final RoomType roomType;
    private final Price price;
    
    public Room(String roomId, RoomType roomType, Price price) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.price = price;
    }

    public String getRoomId() {
        return roomId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public Price getPrice() {
        return price;
    }
}
