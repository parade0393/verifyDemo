package demo

import java.util.regex.Pattern

fun main() {
    val str =
        """     #1 js,90 #
            #2 java,86 #
            #3 djfi,93 # dsf 
            #4 Kotlin,87 #
    """
    val regStr = "^\\s*#\\d+.+\\s+#$";
    val pattern = Pattern.compile(regStr, Pattern.MULTILINE)
    val matcher = pattern.matcher(str)
    val list = mutableListOf<String>()
    while (matcher.find()) {
        list.add(matcher.group())
    }
    val result = list.map { v ->
       val (name,price) =  v.replace("\\s*#\\d+\\s*".toRegex(), "").replace("#".toRegex(),"").replace("\\s+".toRegex(),"").split(",")
       mapOf("name" to name,"price" to price)
    }
    println(result)//[{name=js, price=90}, {name=java, price=86}, {name=Kotlin, price=87}]
}



