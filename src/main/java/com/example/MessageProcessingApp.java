package com.example;

/**
 * Main class which executes message processing.
 */
public class MessageProcessingApp {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        System.out.println(new MessageProcessingApp().getGreeting());
    }
}
