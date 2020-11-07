package demo.empty

fun main() {
    val name:String? = null

    name?.let {
        println("not null")
    }?:{
        println("null")
    }
}