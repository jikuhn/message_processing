package com.example.adjustment;

import com.example.message.Sale;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Multiply adjustment operation.
 */
class MultiplyAdjustmentOperation implements AdjustmentOperation {
    private MathContext mc = new MathContext(2);

    @Override
    public void adjust(Sale sale, BigDecimal value) {
        sale.setValue(sale.getValue().multiply(value, mc));
    }
}
