package net.local.poc.hotelreservations.domain.vo;

public class Email {
    
    private final String value;
    private final String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

    public Email(String value) {
        if(!value.matches(regex)) {
            throw new RuntimeException("Mail address not valid");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
