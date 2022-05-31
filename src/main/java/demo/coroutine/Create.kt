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
    suspend {
        "Coroutineff"
    }.startCoroutine(object :Continuation<String>{
        override val context: CoroutineContext = EmptyCoroutineContext

        override fun resumeWith(result: Result<String>) {
           println("创建及执行:${result}")//会立即输出
        }

    })
}