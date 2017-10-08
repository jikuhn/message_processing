package com.example.adjustment;

import com.example.message.Sale;

import java.math.BigDecimal;

/**
 * Subtract adjustment operation.
 */
class SubtractAdjustmentOperation implements AdjustmentOperation {
    @Override
    public void adjust(Sale sale, BigDecimal value) {
        sale.setValue(sale.getValue().subtract(value));
    }
}
