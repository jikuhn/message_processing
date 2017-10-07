package com.example.recorder;

import com.example.message.Message;

import java.util.stream.Stream;

/**
 * Holds the message when streaming messages from the recorder.
 */
public interface MessageHolder {
    /**
     * Get holded message.
     *
     * @return message instance
     */
    Message getMessage();

    /**
     * If the message is updated, this method should be called to persist the changes.
     */
    void update();
}
