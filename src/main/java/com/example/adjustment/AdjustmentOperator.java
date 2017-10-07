package com.example.adjustment;

import com.example.message.Sale;

import java.math.BigDecimal;

/**
 * Enumeration of adjustment types.
 */
public enum AdjustmentOperator {
    ADD(new AddAdjustmentOperation()),
    SUBTRACT(new SubtractAdjustmentOperation()),
    MULTIPLY(new MultiplyAdjustmentOperation());

    private AdjustmentOperation operation;

    AdjustmentOperator(AdjustmentOperation operation) {
        this.operation = operation;
    }

    public void adjust(Sale sale, BigDecimal value) {
        operation.adjust(sale, value);
    }
}
