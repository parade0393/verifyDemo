package demo.pattern

import  java.lang.Exception
import java.lang.IllegalStateException
import java.util.regex.Pattern

fun main() {
   val string = "abcabcabcabdcabc"
    val pattern = Pattern.compile("a")
    val matcher = pattern.matcher(string)
    var num = 0
    while (matcher.find()){
        num++
        if (num == 2){
            break
        }
    }
    val start: Int
    start = try {
        matcher.start()
    }catch (e:IllegalStateException){
        -1
    }

    println("d第二次出现的位置:$start")
}