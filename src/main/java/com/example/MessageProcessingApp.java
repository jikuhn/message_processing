package com.example;

import com.example.message.Message;
import com.example.processor.*;
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
        // define message recorder
        MessageRecorder recorder = new MemoryMessageRecorder();

        // define message reader
        MessageGenerator reader = new MessageGenerator(
                Arrays.asList("apple", "pear", "orange", "pineapple", "lemon", "plum", "cherry"),
                50,
                5
        );

        // define processors
        ContinuousReportProcessor continuousReportProcessor = new ContinuousReportProcessor(10);

        MessageProcessor processor = new ConsoleProcessor();
        processor
                .chain(new RecorderProcessor(recorder))
                .chain(new MessageType3Processor(recorder, continuousReportProcessor))
                .chain(continuousReportProcessor)
                .chain(new StopMessageProcessor(50));

        // run the processing
        new MessageProcessingApp(reader, processor).process();
    }
}
