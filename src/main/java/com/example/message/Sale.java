package com.example.message;

import java.math.BigDecimal;

/**
 * Representation of a sale which has a product type and a value.
 */
public class Sale {
    /**
     * Product name.
     */
    private String product;

    /**
     * Product value.
     */
    private BigDecimal value;

    public Sale(String product, BigDecimal value) {
        this.product = product;
        this.value = value;
    }

    public String getProduct() {
        return product;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{product: " + product + ", value: " + value + "}";
    }
}
