package com.example.processor;

import com.example.adjustment.AdjustmentOperator;
import com.example.message.Message;
import com.example.message.MessageT3;
import com.example.message.MessageType;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Aggregate information about adjustments applied during application run.
 */
public class FinalLogProcessing extends MessageProcessor {
    /**
     * First key is a product.
     */
    private Map<String, Map<AdjustmentOperator, AtomicInteger>> log;

    public FinalLogProcessing() {
        this.log = new TreeMap<>();
    }

    @Override
    protected void processInternal(Message message) {
        if (message.getType() == MessageType.TYPE_3)
            addToLog((MessageT3) message);
    }

    private void addToLog(MessageT3 message) {
        String product = message.getSale().getProduct();

        Map<AdjustmentOperator, AtomicInteger> adjustments = log.computeIfAbsent(product, k -> new TreeMap<>());
        AtomicInteger counter = adjustments.computeIfAbsent(message.getAdjustmentOperator(), c -> new AtomicInteger());

        counter.incrementAndGet();
    }

    public void printLog() {
        System.out.println("\n=== final log");

        for (Map.Entry<String, Map<AdjustmentOperator, AtomicInteger>> l1 : log.entrySet()) {
            System.out.println("+ product: " + l1.getKey());
            for (Map.Entry<AdjustmentOperator, AtomicInteger> l2 : l1.getValue().entrySet()) {
                System.out.println("      " + l2.getKey() + " " + l2.getValue() + "x");
            }
        }
    }
}