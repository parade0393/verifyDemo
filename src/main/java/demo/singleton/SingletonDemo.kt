package demo.singleton

class SingletonDemo private constructor(){
    val insntance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { SingletonDemo() }
}