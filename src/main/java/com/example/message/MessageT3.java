package com.example.message;

/**
 * Message of type 3.
 */
public class MessageT3 extends Message {
    private AdjustmentOperator adjustmentOperator;

    public MessageT3(Sale sale, AdjustmentOperator adjustmentOperator) {
        super(MessageType.TYPE_3, sale);
        this.adjustmentOperator = adjustmentOperator;
    }

    public AdjustmentOperator getAdjustmentOperator() {
        return adjustmentOperator;
    }

    @Override
    protected void describeSelf(StringBuilder sb) {
        sb.append(", adjustment ").append(adjustmentOperator);
    }
}
