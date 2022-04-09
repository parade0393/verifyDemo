package demo.delegate

import kotlin.properties.Delegates

class MyClass {
    var p by Delegate()
    val w by lazy {  }
    val m by Delegates.notNull<Int>()
}