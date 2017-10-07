package com.example.processor;

import com.example.message.Message;
import com.example.message.MessageT3;
import com.example.message.MessageType;
import com.example.recorder.MessageHolder;
import com.example.recorder.MessageRecorder;

import java.math.BigDecimal;

/**
 * Perform action specific for type 3 message.
 */
public class MessageType3Processor extends MessageProcessor {
    private MessageRecorder recorder;

    public MessageType3Processor(MessageRecorder recorder) {
        this.recorder = recorder;
    }

    @Override
    protected void processInternal(Message message) {
        if (message.getType() == MessageType.TYPE_3)
            processMessage((MessageT3) message);
    }

    private void processMessage(MessageT3 message) {
        BigDecimal value = message.getSale().getValue();

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
        m3.getAdjustmentOperator().adjust(holder.getMessage().getSale(), m3.getSale().getValue());
        holder.update();
    }
}
