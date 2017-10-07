package com.example.message;

/**
 * Message of type 1.
 */
public class MessageT1 extends Message {
    public MessageT1(Sale sale) {
        super(MessageType.TYPE_1, sale);
    }

    @Override
    protected void describeSelf(StringBuilder sb) {
        // nothing
    }
}
