package com.example.adjustment

import com.example.message.Sale
import spock.lang.Specification
import spock.lang.Unroll

class AdjustmentOperationTest extends Specification {
    Sale sale

    BigDecimal value(long v) {
        BigDecimal.valueOf(v)
    }

    def setup() {
        sale = new Sale('', value(10))
    }

    @Unroll
    def 'adjustment operation: #name'(String name, AdjustmentOperation op, long result) {
        when:
        op.adjust(sale, value(3))

        then:
        sale.value == value(result)

        where:
        name  | op                                | result
        'add' | new AddAdjustmentOperation()      | 13
        'sub' | new SubtractAdjustmentOperation() | 7
        'mul' | new MultiplyAdjustmentOperation() | 30
    }

    def 'result of multiplication has still scale of two'() {
        given:
        def op = new MultiplyAdjustmentOperation()

        when:
        def sale = new Sale('x', new BigDecimal('1.10'))
        op.adjust(sale, new BigDecimal('0.20'))
        println sale.value

        then:
        sale.value == new BigDecimal('0.22')
        sale.value.scale() == 2
    }
}
