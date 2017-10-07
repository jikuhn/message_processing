package com.example.processor;

import com.example.message.Message;

/**
 * Just print the processed message.
 */
public class ConsoleProcessor extends MessageProcessor {
    private int line = 0;
    @Override
    protected void processInternal(Message message) {
        System.out.println((++line) + ": " + message);
    }
}
