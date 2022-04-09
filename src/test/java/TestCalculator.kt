import demo.calculator.CalculatorV2
import org.junit.Assert
import org.junit.Test

class TestCalculator {

    @Test
    fun test(){
        val calculatorV2 = CalculatorV2()
        val calculate = calculatorV2.calculate("1+2")
        Assert.assertEquals("3",calculate)
    }
}