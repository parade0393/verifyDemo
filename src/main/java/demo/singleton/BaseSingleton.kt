package demo.singleton

/**
 * 抽象模板单例
 */
abstract class BaseSingleton<in p, out T> {
    @Volatile
    private var instance: T? = null

    protected abstract val creator: (p) -> T

    fun getInstance(params: p): T =
        instance ?: synchronized(this) {
            instance ?: creator(params).also { instance = it }
        }
}

class PersonManager private constructor(name: String) {
    companion object : BaseSingleton<String, PersonManager>() {
        override val creator = ::PersonManager
    }
}

fun main() {

}