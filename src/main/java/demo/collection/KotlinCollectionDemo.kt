package demo.collection

import java.util.*

fun main() {
    val list = mutableListOf<Int>()
   for (i in 0..11){
       list.add(i)
   }
    val filter = list.filter {
        it % 2 == 0
    }

    println(filter)

    //Arrays.asList(split) string先转数组，再转list


    //如果集合里元素比较简单，是string，那么用下面的方法更简单
    val stringList: MutableList<String> = ArrayList()

    val joinToString = stringList.joinToString(",")
    println(joinToString)
}