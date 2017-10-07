package com.example.reader;

import com.example.message.Message;

/**
 * An interface which defines reading the messages.
 */
public interface MessageReader {
    /**
     * Read one message from input.
     *
     * @return message instance, return <tt>null</tt> if there is no message left.
     */
    Message readMessage();
}
