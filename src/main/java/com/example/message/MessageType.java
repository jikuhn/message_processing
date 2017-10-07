package com.example.message;

/**
 * Enumeration of possible message types.
 */
public enum MessageType {
    TYPE_1(1, true),
    TYPE_2(2, true),
    TYPE_3(3, false);

    private int value;
    private boolean adjustable;

    MessageType(int value, boolean adjustable) {
        this.value = value;
        this.adjustable = adjustable;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public boolean isAdjustable() {
        return adjustable;
    }
}
