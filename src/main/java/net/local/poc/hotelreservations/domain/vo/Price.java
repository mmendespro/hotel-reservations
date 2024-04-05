package net.local.poc.hotelreservations.domain.vo;

public class Price {
    
    private final String currency;
    private final Float amount;
    
    public Price(String currency, Float amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public Float getAmount() {
        return amount;
    }
    
    public String getFormattedValue() {
        return currency.concat(String.format(" %.02f", amount));
    }
}
