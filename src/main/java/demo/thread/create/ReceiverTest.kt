package demo.thread.create

import java.util.concurrent.TimeUnit
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine

/**
 * 带有receiver的协程创建 createCoroutine也可以待receiver
 */
fun main() {

    launchCoroutine(ProducerScope<Int>()) {
        produce(1024)
        produce(2048)
        10
    }

    TimeUnit.MINUTES.sleep(1)
}


class ProducerScope<T> {
    suspend fun produce(value: T) {
        println("产生了树:${value}")
    }
}

fun <R, T> launchCoroutine(receiver: R, block: suspend R.() -> T) {
    block.startCoroutine(receiver, object : Continuation<T> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        override fun resumeWith(result: Result<T>) {
            println("Coroutine End:$result")
        }

    })
}