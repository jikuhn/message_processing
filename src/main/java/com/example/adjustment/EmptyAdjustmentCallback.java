package com.example.adjustment;

import com.example.message.Sale;

import java.math.BigDecimal;

/**
 * No action is taken in this callback
 */
public class EmptyAdjustmentCallback implements AdjustmentCallback {
    @Override
    public void afterAdjustment(Sale sale, BigDecimal originalValue, int occurrence) {
        // nothing
    }
}
