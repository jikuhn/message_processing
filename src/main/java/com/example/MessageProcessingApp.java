package com.example;

import com.example.message.Message;
import com.example.processor.ConsoleProcessor;
import com.example.processor.MessageProcessor;
import com.example.processor.RecorderProcessor;
import com.example.processor.StopMessageProcessor;
import com.example.reader.MessageGenerator;
import com.example.reader.MessageReader;
import com.example.recorder.MemoryMessageRecorder;
import com.example.recorder.MessageRecorder;

import java.util.Arrays;

/**
 * Main class which executes message processing.
 */
public class MessageProcessingApp {
    private MessageReader reader;
    private MessageProcessor processor;

    private MessageProcessingApp(MessageReader reader, MessageProcessor processor) {
        this.reader = reader;
        this.processor = processor;
    }

    private void process() {
        try {
            Message message;
            while ((message = reader.readMessage()) != null)
                processor.process(message);
        } catch (StopException e) {
            System.out.println("caught stop signal");
        }
    }

    public static void main(String[] args) {
        // configure message processing and execute it

        MessageRecorder recorder = new MemoryMessageRecorder();

        // define processors
        MessageProcessor processor = new ConsoleProcessor();
        processor
                .chain(new RecorderProcessor(recorder))
                .chain(new StopMessageProcessor(50));

        new MessageProcessingApp(
                new MessageGenerator(
                        Arrays.asList("apple", "pear", "orange", "pineapple", "lemon", "plum", "cherry"),
                        50,
                        5
                ),
                processor
        ).process();
    }
}
