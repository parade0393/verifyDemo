package demo.collection

fun main() {
   val list = mutableListOf(1,2,3,4,5,4,3,2,1)
    //方法一：迭代器
//    val iterator = list.iterator()
//    while (iterator.hasNext()){
//        if (iterator.next() > 2){
//            iterator.remove()
//        }
//    }
//    for (i in list.size - 1 downTo 0) {
//        if (list[i] > 2){
//            list.removeAt(i)
//        }
//    }
    //还是java的方法，调用了迭代器
    list.removeIf { i -> i>2 }
    println(list)
}