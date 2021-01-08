package demo.singleton

class SingletonDemo private constructor(){
    companion object{
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { SingletonDemo() }
    }
}