package com.example.message;

/**
 * Enumeration of possible message types.
 */
public enum MessageType {
    TYPE_1(1),
    TYPE_2(2),
    TYPE_3(3);

    private int value;

    MessageType(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
