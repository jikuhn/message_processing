package com.example.processor;

import com.example.message.Message;

/**
 * Process a message. Processors are chained.
 * Implementations of the {@link MessageProcessor} should do only one thing
 * to follow chain of responsibility principle.
 */
public abstract class MessageProcessor {
    private MessageProcessor next;

    /**
     * Operate on a message
     *
     * @param message message which the processor has to process
     */
    protected abstract void processInternal(Message message);

    /**
     * Process the message and call the next processor in the chain.
     *
     * @param message message to be processed
     */
    public void process(Message message) {
        processInternal(message);
        if (next != null)
            next.process(message);
    }

    /**
     * This chaining call is used during construction of processor chain.
     *
     * @param nextProcessor processor which should be called when this processor has done its work
     * @return the same instance as parameter to ease declaration of processor chain
     */
    public MessageProcessor chain(MessageProcessor nextProcessor) {
        return next = nextProcessor;
    }
}
