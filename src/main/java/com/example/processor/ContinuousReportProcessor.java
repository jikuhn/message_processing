package com.example.processor;

import com.example.adjustment.AdjustmentCallback;
import com.example.message.Message;
import com.example.message.MessageT2;
import com.example.message.MessageType;
import com.example.message.Sale;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

/**
 * After every N-th message received we should log a report detailing the number
 * of sales of each product and their total value.
 */
public class ContinuousReportProcessor extends MessageProcessor implements AdjustmentCallback {
    private int howOften;
    private int counter;
    Map<String, OneReport> report;

    public ContinuousReportProcessor(int howOften) {
        this.howOften = howOften;
        counter = 0;
        report = new TreeMap<>();   // key is a product, keep products sorted
    }

    @Override
    protected void processInternal(Message message) {
        counter++;

        addToReport(message);

        if (counter == howOften) {
            printReport();
            counter = 0;
        }
    }

    @Override
    public void afterAdjustment(Sale sale, BigDecimal originalValue, int occurrence) {
        OneReport r = report.get(sale.getProduct());
        if (r == null)
            return;

        BigDecimal o = BigDecimal.valueOf(occurrence);
        r.totalValue = r.totalValue
                .subtract(originalValue.multiply(o))
                .add(sale.getValue().multiply(o));
    }

    private void addToReport(Message message) {
        // type 3 message doesn't contain any real sale
        // the callback mechanism is used to work with type 3 messages
        if (message.getType() == MessageType.TYPE_3)
            return;

        String product = message.getSale().getProduct();
        OneReport r = report.get(product);
        if (r == null)
            report.put(product, r = new OneReport());

        r.add(message);
    }

    private void printReport() {
        System.out.println("--- continuous report:");
        for (Map.Entry<String, OneReport> e : report.entrySet()) {
            System.out.printf("product %s - number of sales %d, total value: %s\n", e.getKey(), e.getValue().numberOfSales, e.getValue().totalValue);
        }
        System.out.println("--- end of report");
    }

    private static class OneReport {
        int numberOfSales;
        BigDecimal totalValue;

        OneReport() {
            numberOfSales = 0;
            totalValue = BigDecimal.ZERO;
        }

        void add(Message message) {
            int occurrence = 1;
            BigDecimal value = message.getSale().getValue();

            if (message.getType() == MessageType.TYPE_2) {
                MessageT2 m2 = (MessageT2) message;
                occurrence = m2.getOccurrence();
                value = value.multiply(BigDecimal.valueOf(occurrence));
            }

            numberOfSales += occurrence;
            totalValue = totalValue.add(value);
        }
    }
}
