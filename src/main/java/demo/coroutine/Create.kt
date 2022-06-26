package demo.coroutine

import kotlin.coroutines.*

fun main() {
    //创建协程
    val createCoroutine = suspend {
        println("In Coroutine")
        5
    }.createCoroutine(object : Continuation<Int> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        override fun resumeWith(result: Result<Int>) {
            println("Coroutine End:${result}")
        }

    })
    //启动协程
   createCoroutine.resume(Unit)
//    createCoroutine.resume(Unit)//resume只能被调用一次，否则会跑异常
    suspend {
        "Coroutineff"
    }.startCoroutine(object :Continuation<String>{
        override val context: CoroutineContext = EmptyCoroutineContext

        override fun resumeWith(result: Result<String>) {
           println("创建及执行:${result}")//会立即输出
        }

    })

    //以上是创建协程的两个基础api，还有两个重载的api(带有receiver的创建)
}