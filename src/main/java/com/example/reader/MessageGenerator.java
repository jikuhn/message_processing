package com.example.reader;

import com.example.adjustment.AdjustmentOperator;
import com.example.message.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

/**
 * Generate messages.
 */
public class MessageGenerator implements MessageReader {
    private List<String> products;
    private int maxOccurrence;
    private Random random;

    /**
     * Construct the generator with the given values.
     *
     * @param products      list of available products
     * @param maxOccurrence maximum number of occurrence for message of type 2
     */
    public MessageGenerator(List<String> products, int maxOccurrence, int maxAdjustment) {
        this.products = products;
        this.maxOccurrence = maxOccurrence;

        random = new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes());
    }

    @Override
    public Message readMessage() {
        Sale sale = generateSale();
        MessageType type = randomValueFromArray(MessageType.values());

        switch (type) {
            case TYPE_1:
                return new MessageT1(sale);

            case TYPE_2:
                return new MessageT2(sale, random.nextInt(maxOccurrence) + 1);

            default:
                // note: when generating subtract adjustment the result of the adjustment operation
                // may be negative, but to keep it simple, I don't care about it
                AdjustmentOperator operator = randomValueFromArray(AdjustmentOperator.values());
                return new MessageT3(sale, operator);
        }
    }

    private <T> T randomValueFromArray(T[] values) {
        return values[random.nextInt(values.length)];
    }

    private Sale generateSale() {
        String product = products.get(random.nextInt(products.size()));
        // if the random number is 55, the generated value is 55 / 10 = 5.50
        BigDecimal value = new BigDecimal(random.nextInt(100)).divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP);

        return new Sale(product, value);
    }
}
