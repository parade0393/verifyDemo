package demo.delegate

import kotlin.reflect.KProperty

class Delegate {
    var propValue:Any? = null

    /**
     * 第一个参数用来声明该该委托类可以什么类中使用
     * 第二个参数是kotlin中一个属性操作类，用于获取各种属性相关的值<*>不关心或不知道泛型的具体类型
     */
    operator fun getValue(myClass: Any,prop:KProperty<*>):Any?{
        return propValue
    }

    operator fun setValue(myClass: Any,prop:KProperty<*>,value:Any?){
        propValue = value
    }
}