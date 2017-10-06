package com.example;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MessageProcessingAppTest {
    @Test
    public void testAppHasAGreeting() {
        MessageProcessingApp app = new MessageProcessingApp();
        assertNotNull("app should have a greeting", app.getGreeting());
    }
}