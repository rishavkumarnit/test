package com.rishav.parking;

public enum Currency {
    
    USD("USD", 1.00),
    INR("INR", 83.00);

    private final String symbol;
    private final double value;

    Currency(String symbol, double value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public double getValue() {
        return this.value;
    }
}
