package demo

fun main() {

    val name = mutableSetOf<String>()
    name.add("parade")
    name.add("parades")
    val joinToString = ",${name.joinToString(",")},"

    println(joinToString)

}
