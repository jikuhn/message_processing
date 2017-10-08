package com.example.adjustment;

import com.example.message.Sale;

import java.math.BigDecimal;

/**
 * The callback is a way how to react on sale modification.
 */
public interface AdjustmentCallback {
    /**
     * Method called after each adjustment.
     *  @param sale          instance of a sale after adjustment
     * @param originalValue value of the sale before adjustment
     * @param occurrence
     */
    void afterAdjustment(Sale sale, BigDecimal originalValue, int occurrence);
}
