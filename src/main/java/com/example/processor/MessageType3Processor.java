package com.example.processor;

import com.example.adjustment.AdjustmentCallback;
import com.example.message.*;
import com.example.recorder.MessageHolder;
import com.example.recorder.MessageRecorder;

import java.math.BigDecimal;

/**
 * Perform action specific for type 3 message.
 */
public class MessageType3Processor extends MessageProcessor {
    private MessageRecorder recorder;
    private AdjustmentCallback callback;

    public MessageType3Processor(MessageRecorder recorder, AdjustmentCallback callback) {
        this.recorder = recorder;
        this.callback = callback;
    }

    @Override
    protected void processInternal(Message message) {
        if (message.getType() == MessageType.TYPE_3)
            processMessage((MessageT3) message);
    }

    private void processMessage(MessageT3 message) {
        recorder
                .messages()
                .filter(holder -> isAdjustable(holder, message))
                .forEach(holder -> adjust(holder, message));
    }

    /**
     * Message which is adjustable must be:
     * - only messages of type 1 and 2
     * - having the same product as currently processed type 3 message
     */
    private boolean isAdjustable(MessageHolder holder, MessageT3 m3) {
        String adjustedProduct = m3.getSale().getProduct();
        Message message = holder.getMessage();

        return adjustedProduct.equals(message.getSale().getProduct())
                && message.getType().isAdjustable();
    }

    /**
     * Adjust the sale and persist the modification.
     */
    private void adjust(MessageHolder holder, MessageT3 m3) {
        Sale sale = holder.getMessage().getSale();
        BigDecimal originalValue = sale.getValue();

        m3.getAdjustmentOperator().adjust(sale, m3.getSale().getValue());

        callback.afterAdjustment(sale, originalValue, occurrence(holder.getMessage()));
        holder.update();
    }

    /**
     * Extract number of sale occurrence from the given message.
     */
    private int occurrence(Message message) {
        int occurrence = 1;

        if (message.getType() == MessageType.TYPE_2)
            occurrence = ((MessageT2)message).getOccurrence();

        return occurrence;
    }
}
