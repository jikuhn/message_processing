package com.example.adjustment;

import com.example.message.Sale;

import java.math.BigDecimal;

/**
 * Definition of adjustment operation.
 */
interface AdjustmentOperation {
    /**
     * Do the adjustment operation.
     *
     * @param sale  sale to adjust
     * @param value adjustment value
     */
    void adjust(Sale sale, BigDecimal value);
}
