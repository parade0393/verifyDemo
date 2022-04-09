package demo.kotlinlambda

fun main() {
    val fileTypeList by lazy {
        arrayListOf("txt", "doc", "docx", "xls", "xlsx", "pdf", "ppt", "apk", "zip", "rar")
    }
    println("start")

    fileTypeList.forEach {
        if (it.endsWith("xls")) {
            return@forEach//局部返回到lambda的调用者(本次lambda之后的代码不再执行，还是会执行forEach)，即forEach循环(相当于cotinue)
        }
        println(it)
    }

   /* run {
        fileTypeList.forEach {
            if (it.endsWith("xls")) {
                return@run//从传入run的lambda返回，相当于break
            }
            println(it)

        }
    }*/


    println("end")
}