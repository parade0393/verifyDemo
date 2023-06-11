package demo.collection

fun main() {
    val strList = listOf("a", "b", "c", "d")
    var intList = listOf(1,2,3,4,5,6)

   outer@ for (i in intList) {
        println(i)
        for (s in strList) {
            if (s == "b"){
                break@outer
            }
            println(s)
        }
    }
}