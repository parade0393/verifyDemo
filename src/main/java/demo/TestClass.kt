package demo

fun main() {
    val containers = listOf(
            listOf("one", "two", "three"),
            listOf("four", "five", "six"),
            listOf("seven", "eight")
    )
    val newList = containers.flatten()
//    println(newList)//[one, two, three, four, five, six, seven, eight]
    val list = containers.flatMap { item ->
        item.map { el -> "${el}suffix" }
    }
//    println(list)
    //[onesuffix, twosuffix, threesuffix, foursuffix, fivesuffix, sixsuffix, sevensuffix, eightsuffix]

    val con = listOf(
            listOf("one","two"),
            listOf(listOf("three","four"))
    )

   println( mutableListOf<String>().take(1))
}


