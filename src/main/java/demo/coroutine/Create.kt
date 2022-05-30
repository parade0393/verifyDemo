package demo.coroutine

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.createCoroutine

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
   createCoroutine.resumeWith(Result.success(Unit))
}