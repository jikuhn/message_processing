package com.example.processor;

import com.example.message.Message;
import com.example.recorder.MessageRecorder;

/**
 * Record the message.
 */
public class RecorderProcessor extends MessageProcessor {
    private MessageRecorder recorder;

    public RecorderProcessor(MessageRecorder recorder) {
        this.recorder = recorder;
    }

    @Override
    protected void processInternal(Message message) {
        recorder.record(message);
    }
}
