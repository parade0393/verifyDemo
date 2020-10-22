package demo.string


fun main() {
    println("    ".isSpace())

    val name = mutableSetOf<String>()
    name.add("parade")
    name.add("parades")
    val joinToString = ",${name.joinToString(",")},"
}

fun String.isSpace():Boolean{
    this.forEach {
        if (!Character.isWhitespace(it)) return false
    }
    return true
}