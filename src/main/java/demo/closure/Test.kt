package demo.closure

fun main() {
    //f的类型是(Int)->Int类型，是函数类型的对象，是lambda的引用,lambda的返回值是局部变量total，但是total的作用域是在高阶函数sum中
    //每次执行完f后，total变量的作用域就不存在了，可是total的变量值都能够被保持，由此说明被捕获的变量都存储在一个特殊的容器
    val f = sum()
    println(f(10))
    println(f(10))
    println(f(10))


}

fun sum():(Int)->Int{//声明高阶函数sum的返回值是(Int)->Int类型
    var total = 0//total是sum高阶函数的局部变量
    return {//使用lambda作为sum()高阶函数的返回值
        total = total+it
        total
    }
}
