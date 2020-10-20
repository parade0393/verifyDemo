package demo.pattern

import java.lang.Exception
import java.lang.IllegalStateException
import java.util.regex.Pattern

fun main() {
   val string = "abcabcabcabdcabc"
    val pattern = Pattern.compile("a")
    val matcher = pattern.matcher(string)
    var num = 0
    while (matcher.find()){
        num++
        if (num == 20){
            break
        }
    }
    var start: Int
    try {
         start= matcher.start()
    }catch (e:IllegalStateException){
        start = -1
    }

    println("d第二次出现的位置:$start")
}