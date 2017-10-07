package com.example.message;

/**
 * Base class of a message.
 */
public abstract class Message {
    private MessageType type;
    protected Sale sale;

    public Message(MessageType type, Sale sale) {
        this.type = type;
        this.sale = sale;
    }

    public MessageType getType() {
        return type;
    }

    public Sale getSale() {
        return sale;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("type: ").append(type);
        sb.append(", sale: ").append(sale);
        describeSelf(sb);
        return sb.toString();
    }

    /**
     * Each implementation of this class can add its own specific description.
     */
    protected abstract void describeSelf(StringBuilder sb);
}
