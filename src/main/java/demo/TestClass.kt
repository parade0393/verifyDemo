package demo

class TestClass {
    var a = 0
    private val b = "1"
    var clickable: Clickable? = null
    var isEmpty: Boolean = false
        set(value) {
            field = this.a > 0
        }

}