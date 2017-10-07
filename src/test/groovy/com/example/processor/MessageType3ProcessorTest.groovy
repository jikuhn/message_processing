package com.example.processor

import com.example.adjustment.AdjustmentOperator
import com.example.message.MessageT1
import com.example.message.MessageT3
import com.example.message.Sale
import com.example.recorder.MemoryMessageRecorder
import com.example.recorder.MessageRecorder
import spock.lang.Specification

class MessageType3ProcessorTest extends Specification {
    MessageRecorder recorder
    MessageType3Processor processor

    def setup() {
        recorder = new MemoryMessageRecorder()
        processor = new MessageType3Processor(recorder)
    }

    BigDecimal value(String value) {
        new BigDecimal(value)
    }

    Sale sale(String v) {
        sale('product', v)
    }

    Sale sale(String p, String v) {
        new Sale(p, value(v))
    }

    def 'message type 3 is not adjusted itself'() {
        given:
        def m1 = new MessageT3(sale('1.5'), AdjustmentOperator.ADD)
        def m2 = new MessageT3(sale('1'), AdjustmentOperator.ADD)

        recorder.record(m1)

        when:
        processor.process(m2)

        then:
        m1.sale.value == value('1.5')
    }

    def 'message type 1 of different type is not adjusted'() {
        given:
        def m1 = new MessageT1(sale('x', '1.5'))
        def m2 = new MessageT1(sale('y', '1.5'))

        recorder.record(m1)
        recorder.record(m2)

        when:
        processor.process(new MessageT3(sale('x', '1'), AdjustmentOperator.ADD))

        then:
        m1.sale.value == value('2.5')
        m2.sale.value == value('1.5')
    }
}
