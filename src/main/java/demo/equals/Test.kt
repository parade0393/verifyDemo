package demo.equals

fun main() {
    val a = 10.222222225
    val b = 10.222222229
    println(a<b)//true
    println(a==b)//false

    val e = arrayListOf<String>("a")
    val f = arrayListOf<String>("a")
    println(e!=f)//false
    println(e!==f)//true
}