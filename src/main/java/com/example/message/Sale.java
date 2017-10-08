package com.example.message;

import java.math.BigDecimal;
import java.util.Objects;

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

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{product: " + product + ", value: " + value + "}";
    }

    // equals() method created for spock tests
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(product, sale.product) &&
                Objects.equals(value, sale.value);
    }
}
