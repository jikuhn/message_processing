package com.example.recorder;

import com.example.message.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Record a message in memory. Don't use any database.
 */
public class MemoryMessageRecorder implements MessageRecorder {
    private List<Message> messages;

    public MemoryMessageRecorder() {
        messages = new LinkedList<>();
    }

    @Override
    public void record(Message message) {
        messages.add(message);
    }

    @Override
    public Stream<MessageHolder> messages() {
        return messages.stream().map(MemoryMessageRecorder::mapper);
    }

    private static MemoryMessageHolder mapper(Message message) {
        return new MemoryMessageHolder(message);
    }

    private static class MemoryMessageHolder implements MessageHolder {
        private Message message;

        MemoryMessageHolder(Message message) {
            this.message = message;
        }

        @Override
        public Message getMessage() {
            return message;
        }

        @Override
        public void update() {
            // nothing to do for memory storage
        }
    }
}
