package com.example.recorder;

import com.example.message.Message;

import java.util.stream.Stream;

/**
 * Message recording interface.
 */
public interface MessageRecorder {
    /**
     * Record a message.
     *
     * @param message message instance to record
     */
    void record(Message message);

    /**
     * Provide messages already recorded.
     *
     * @return stream of
     */
    Stream<MessageHolder> messages();
}
