package demo.deepcopy


import org.junit.Assert.*
import org.junit.Test
import java.util.*


class DeepCloneTest {

    @Test
    fun testThisClone() {
        val thisCopy = deepCopy()
        assertFalse(thisCopy === this)
    }

    @Test
    fun testStringClone() {
        val string = "水天滑稽天照八野滑稽石"
        val stringClone = string.deepCopy()

        assertNotNull(stringClone)
        assertEquals(string, stringClone)
        assertFalse(string === stringClone)
    }

    @Test
    fun testIntegerPrimitiveClone() {
        val integer = 100500
        val integerClone = integer.deepCopy()

        assertNotNull(integerClone)
        assertTrue(integer == integerClone)
    }

    @Test
    fun testIntegerClone() {
        val integer = Integer(100500)
        val integerClone =  integer.deepCopy()

        println(integerClone.toString())

        assertNotNull(integerClone)
        assertTrue(integer == integerClone)
    }

    @Test
    fun testListClone() {
        val listOfStrings = listOf("a", "b", "c")
        val listOfStringsClone =  listOfStrings.deepCopy() as List<String>

        assertNotNull(listOfStringsClone)
        assertTrue(listOfStringsClone.isNotEmpty())
        assertTrue(listOfStringsClone.size == 3)
    }

    @Test
    fun testSimpleObjectClone() {
        val simpleObject = SimpleObject()
        val simpleObjectClone =  simpleObject.deepCopy()

        assertNotNull(simpleObjectClone)
        assertTrue(simpleObject == simpleObjectClone)
        assertFalse(simpleObject === simpleObjectClone)
    }

    @Test
    fun testComplexObjectClone() {
        val complexObject = ComplexObject()
        val complexObjectClone =  complexObject.deepCopy()

        val removeAt = complexObjectClone.listExample.removeAt(1)

        println(complexObject.listExample)
        println(complexObjectClone.listExample)

        assertNotNull(complexObjectClone)
        assertFalse(complexObject === complexObjectClone)

    }

    @Test
    fun test(){
        val list = mutableListOf<Parent>()
        for (i in 0..5) {
            val children = Children()
            children.id  = i
            children.name = "name$i"
            for (t in 0..5){
                val parent = Parent()
                parent.departmentId = t
                parent.departmentName="department${t*6}"
                parent.children = children
                list.add(parent)
            }
        }

        val copyList = list.deepCopy()

        copyList[0].children.name = "yu"
        println(list[0].children.name)
        println(copyList[0].children.name)//change
    }

    @Test
    fun test1(){
        val user = User("parade", 18, mutableListOf("1","2"))
        val copy = user.copy()
        copy.name = "change"
//        copy.list[0] = "test"
        println(user)
        println(copy)

    }

}

data class SimpleObject(
        var foo: String = "foo",
        var bar: String = "bar"
)

class ComplexObject {
    val noField: String
        get() {
            return "123"
        }

    var a = 1
        get() = field + 1
        set(value) {
            field = value + 5
        }

    val b = 2

    var listExample = mutableListOf(1, 2, 3)
    var mapExample = mutableMapOf(1 to 1, 2 to 2, 3 to 3)
    var arrExample = arrayOf(arrayOf(1, 2, 3), 2, 3)
    var hashMapExample = hashMapOf(1 to 2, 3 to 2, 2 to 3)
    var setExample = setOf(1, 2, 3)
    var nullableInt: Int? = null
    var date = Date()

    val simpleNestedObject = SimpleObject()

}

