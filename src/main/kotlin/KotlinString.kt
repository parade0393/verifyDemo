package demo.string

fun main() {
    val s:String = "demo"
    val padEnd = s.padEnd(s.length+2, 'f')//demoff
    println(padEnd)

    val a:String = "demo"
    val commonSuffixWith = a.commonSuffixWith("hermo")
    println(commonSuffixWith)//mo

    val b:String? = "Hello"
    val plus = b.plus("World")?:"dddd"
    println(plus)//nullWorld

    val c = "demo"
    val dropWhile = c.take(2)
    println(dropWhile)//de

    val d = "Hello"
    val dropLast = d.dropLast(2)
    println(dropLast)//hel

    val e = "Hello"
    val drop = e.drop(2)
    println(drop)//llo

    val y:Int? = null
    val x:String? = y as String?
    println(x)

    val handlePhoneMiddleText = "1370393783".handlePhoneMiddleText("13703937803")
    println(handlePhoneMiddleText)

}


fun String.handlePhoneMiddleText(originalPhone:String):String{
//    return if (contains("*")) originalPhone else this.replace(Regex("(\\d{3})\\d{4}(\\d{4})"),"$1****$2")
    return if (contains("*")) originalPhone else this.replace(Regex("(\\d{3})\\d{4}(\\d{${this.length-7}})"),"$1****$2")
}