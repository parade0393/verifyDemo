package demo.delegate

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

fun main() {
   /* println(lazyProp)
    println(lazyProp)
    println(lazyProp)
    println(lazyProp)*/
    /*observableProp = "第一次修改值"
    observableProp = "第二次修改值"*/
  /*  println("vetoDemo=${vetoDemo}")
    vetoDemo = 10
    println("vetoDemo=${vetoDemo}")
    vetoDemo = 5//这里赋值并不会生效
    println("vetoDemo=${vetoDemo}")
    vetoDemo = 100
    println("vetoDemo=${vetoDemo}")*/
    val user = User(mapOf("name" to "parade","age" to 30))
    println("name=${user.name} age=${user.age}")
}

/**
 * lazy默认就是LazyThreadSafetyMode.SYNCHRONIZED模式，线程安全的
 */
val lazyProp by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
    print("第一次调用${"".padEnd(4)}")
    "parade"
}

/**
 *可观察属性，想要观察一个属性的变化过程，可以使用这个可观察属性
 */
var observableProp:String by Delegates.observable("初始"){ kProperty: KProperty<*>, oldValue: String, newValue: String ->
    println("property:${kProperty}:$oldValue -> $newValue")
}

/**
 * 同样可以观察属性值的变化，同时它可以通过处理器函数来决定属性值是否生效
 */
var vetoDemo :Int by Delegates.vetoable(0){
    _,oldValue,_newValue ->
    //新值大于旧值才生效
    _newValue > oldValue
}

/**
 * 属性存储在映射中，在一个map里存储属性的值，使用映射实例自身作为委托来实现委托属性
 */
class User(val map:Map<String,Any?>){
    val name:String by map
    val age :Int by map
}

