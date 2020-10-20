package demo

class TestGetter {
    var ids:String="shi"
    get() {
        if (list.size==0) field="0"
        return field
    }

   private val list = listOf<String>("s")
}