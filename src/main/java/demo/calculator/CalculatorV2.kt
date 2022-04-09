package demo.calculator

import kotlin.system.exitProcess


class CalculatorV2 {
    private val help = """
        
    """.trimIndent()
    private val exit = "exit"
    fun start() {
        while (true) {
            println(help)
            val input = readLine() ?: continue
            val result = calculate(input)
            if (result == null) {
                println("输入格式不对")
                continue
            } else {
                println("$input = $result")
            }
        }
    }

    fun calculate(input: String): String? {
        if (shouldExit(input)) exitProcess(0)
        val exp = parseExpression(input) ?: return null
        val (left, operator, right) = exp
        return when (operator) {
            Operation.ADD -> addString(left, right)
            Operation.MINUS -> minusString(left, right)
            Operation.MULTI -> multiString(left, right)
            Operation.DIVI -> diviString(left, right)
        }
    }

    private fun shouldExit(input: String) = input == exit
    private fun parseExpression(input: String): Expression? {
        val operation = parseOperator(input)?:return null
        val list = input.split(operation.value)
        if (list.size != 2) return null
        return Expression(
                left = list[0].trim(),
                operator = operation,
                right = list[1].trim()
        )
    }

    private fun parseOperator(input: String):Operation?{
        Operation.values().forEach {
            if (input.contains(it.value)){
                return it
            }
        }
        return null
    }

    private fun addString(left: String, right: String):String{
        return (left.toInt()+right.toInt()).toString()
    }
    private fun minusString(left: String, right: String):String = (left.toInt()-right.toInt()).toString()
    private fun multiString(left: String, right: String):String = (left.toInt() * right.toInt()).toString()
    private fun diviString(left: String, right: String):String = (left.toInt()/right.toInt()).toString()
}

data class Expression(
        val left: String,
        val operator: Operation,
        val right: String
)

enum class Operation(val value: String) {
    ADD("+"),
    MINUS("-"),
    MULTI("*"),
    DIVI("/")
}

fun main() {
    val calculatorV2 = CalculatorV2()
    calculatorV2.start()

}