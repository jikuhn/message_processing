package com.example.processor;

import com.example.StopException;
import com.example.message.Message;

/**
 * Stops message reading and processing.
 */
public class StopMessageProcessor extends MessageProcessor {
    private int counter;
    private int stopNumber;

    public StopMessageProcessor(int stopNumber) {
        counter = 0;
        this.stopNumber = stopNumber;
    }

    @Override
    protected void processInternal(Message message) {
        if (++counter >= stopNumber)
            throw new StopException();
    }
}
