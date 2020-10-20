package demo.string


fun main() {
    println("    ".isSpace())
}

fun String.isSpace():Boolean{
    this.forEach {
        if (!Character.isWhitespace(it)) return false
    }
    return true
}