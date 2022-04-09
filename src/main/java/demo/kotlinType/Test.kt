package demo.kotlinType

fun main() {
    val a = Ex("pa",3)
    val b = Ex("ra",4)
}
var  i = 1
fun reportError():Nothing{
    throw RuntimeException()
}
fun example(){
    reportError()//抛出异常
    i = 2//编译器警告，Unreachable code
}

fun example2(map:Map<String,String>){
    val data = map["key"]?: reportError()//编译成功,另外这里可以使用kotlin提供的error函数
}

data class Ex(
    val name:String,
    val age:Int
    )
