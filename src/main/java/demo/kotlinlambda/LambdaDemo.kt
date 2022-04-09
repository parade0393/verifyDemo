package demo.kotlinlambda

fun main() {
    //对于一个声明好的函数，不管你是要把他作为参数传递给函数还是要把它赋值给变量，都要在函数名的左边加上双冒号才行
    a(::b)
    val d=::b
    //加了两个冒号，函数才变成一个对象
    //kotlin里「函数可以作为参数」的本质，是函数在kotlin里可以作为对象存在----因为只有对象才能传递给函数，也只有对象才能赋值给变量
    //但是kotlin函数本身的性质又决定了它没法当作一个对象，于是kotlin的选择是创建一个和函数具有相同功能的对象
    //在kotlin里，一个函数名的左边加上双冒号,它就不表示这个函数本身了，而表示一个对象，或者说一个指向对象的引用，但这个对象不是函数本身，而是一个和函数具有相同功能的对象
    //怎么用函数，就怎么用这个加了双冒号的对象
    b(1)
    d(1)//实际上会调用d.invoke(1)
    (::b)(1)//实际上会调用(::b).invoke(1)
    //可以对一个函数类型的对象调用invoke，但是不能对函数调用invoke

    a(fun (param: Int): String {
        return param.toString()
    })
    val e = fun (params:Int):String{return params.toString()}
    //以上两种使用了匿名函数
    //匿名函数和 双冒号加函数名是一类东西
    //匿名函数 ，lambda，双冒号加函数名都是函数类型的对象
    //在你知道了在 Kotlin 里「函数并不能传递，传递的是对象」和「匿名函数和 Lambda 表达式其实都是对象」这些本质之后，你以后去写 Kotlin 的高阶函数会非常轻松非常舒畅。


    //扩展函数
    (Int::toFloat)(1)
    Int::toFloat.invoke(1)
    //跟普通函数的引用一样，扩展函数的引用也可以被调用，直接调用或者用 invoke() 都可以，不过要记得把 Receiver 也就是接收者或者说调用者填成第一个参数
    (String::demo1)("parade",1)
    String::demo1.invoke("parade",3)

    val a: String.(Int) -> Unit = String::demo1

    

}

fun b(param: Int): String {
    return param.toString()
}
fun String.demo1(i:Int){}


fun a (fund:(Int) -> String):String{
    return  fund(1)
}

