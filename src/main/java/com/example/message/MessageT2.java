package com.example.message;

/**
 * Message of type 2.
 */
public class MessageT2 extends Message {
    private int occurrence;

    public MessageT2(Sale sale, int occurrence) {
        super(MessageType.TYPE_2, sale);
        this.occurrence = occurrence;
    }

    public int getOccurrence() {
        return occurrence;
    }

    @Override
    protected void describeSelf(StringBuilder sb) {
        sb.append(", occurrence: ").append(occurrence);
    }
}
