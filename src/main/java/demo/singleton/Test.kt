package demo.singleton

fun main() {
    VerifyInit.ob()//没有实例化VerifyInit， VerifyInit.Companion.ob();
    ODemo.p()//ODemo.INSTANCE.p();
    SingletonDemo.instance
} 