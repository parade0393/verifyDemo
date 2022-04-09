package demo

fun main() {
   val list = listOf(1, 2, 3, 4, 5, 6);
   list.forEach {
      println(it)
      if (it == 3){
         return@forEach
      }
   }
   for (i in list) {
      println("----------")
      if(i == 3){
         return
      }
   }
}



