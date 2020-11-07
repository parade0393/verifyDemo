package demo.singleton

class VerifyInit {
    init {//相当于无参构造的方法体
        println(Te.a)//调用静态内部类的单例的get方法...如果是常量就直接编译成了成员变量   VerifyInit.Te.INSTANCE.v()
        Te.v()//调用静态内部类的单例的方法
        println("init")
        ob()//Companion.ob();编译后会直接帮我们调用伴生类的对应方法，Companion是伴生类的实例
    }

    object Te {
        //其实就是个静态内部类，在静态代码块里创建了单例
        const val a = 3
        fun v() {
            println("companion object")
        }
    }

    companion object {
        //伴生静态内部类，只能有一个
        fun ob() {
            println("default")
        }
    }

    init {
        println("34")//会按照init的顺序在无参构造里码代码
        /**
         * public VerifyInit() {
        byte var1 = 3;
        boolean var2 = false;
        System.out.println(var1);
        VerifyInit.Te.INSTANCE.v();
        String var3 = "init";
        var2 = false;
        System.out.println(var3);
        var3 = "34";
        var2 = false;
        System.out.println(var3);
        }
         */
    }
}