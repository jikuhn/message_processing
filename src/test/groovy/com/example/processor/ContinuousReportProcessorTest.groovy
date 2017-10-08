package com.example.processor

import com.example.adjustment.AdjustmentOperator
import com.example.message.MessageT1
import com.example.message.MessageT2
import com.example.message.MessageT3
import com.example.message.Sale
import com.example.recorder.MemoryMessageRecorder
import spock.lang.Specification

class ContinuousReportProcessorTest extends Specification {
    def sale(int value) {
        new Sale('x', BigDecimal.valueOf(value))
    }

    def 'handling of type 3 messages via callback'() {
        given:
        def recorder = new MemoryMessageRecorder()
        def crp = new ContinuousReportProcessor(4)

        def processors = new RecorderProcessor(recorder)
        processors
                .chain(crp)
                .chain(new MessageType3Processor(recorder, crp))

        when: 'warm the report'
        processors.process(new MessageT1(sale(3)))
        processors.process(new MessageT2(sale(2), 3))

        def oneReport = crp.report.get('x')

        then:
        oneReport.numberOfSales == 4
        oneReport.totalValue == 9

        when: 'modify previous sales by message type 3'
        processors.process(new MessageT3(sale(1), AdjustmentOperator.SUBTRACT))

        then: 'reported total value should be subtracted per each occurrence'
        oneReport.numberOfSales == 4
        oneReport.totalValue == 5
    }
}
