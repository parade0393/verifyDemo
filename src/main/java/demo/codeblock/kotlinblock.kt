package demo.codeblock

/**
 *
 */
fun main() {
    val funEntity = FunEntity("ming", 1,1)
    /**
     * init block
     * annother
     */
}

class FunEntity(val name: String, val age: Int) {

    constructor( name: String, age: Int, ban:Int):this(name, age){
        println("annother")
    }
    init {
        println("init block")
    }
}
