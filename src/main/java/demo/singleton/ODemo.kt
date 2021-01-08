package demo.singleton

object ODemo {

    init {
        println("init")
    }//编译后放在静态代码块里

    fun p(){
        println("p")
    }
}