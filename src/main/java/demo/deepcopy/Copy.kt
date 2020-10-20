package demo.deepcopy

import java.util.*
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField

//深拷贝
fun <T: Any> T.deepCopy(): T {

    val copiedObjects = mutableMapOf<Any,Any>()
    var initialObject = getValueFromCopiedCollection(this,copiedObjects)
    initialObject = deepCopy(this, copiedObjects)
    return initialObject
}

private fun<T: Any> deepCopy(obj: T,copiedObjects: MutableMap<Any,Any>): T{

    val objClassJava = obj::class.java
    val objClass = obj::class

    // 基本数据类型直接返回
    if (objClassJava.isPrimitive) {
        return obj
    } else {
        if (objClass.javaPrimitiveType != null) {
            return obj
        }
    }

    when(obj){
        is String -> {
            val newString = String(obj.toCharArray())
            copiedObjects[obj] = newString
            return newString as T
        }
        is Array<*> -> {
            val arrList = ArrayList<Any?>()
            val newArray: Any

            for (elem in obj){
                if (elem == null)
                    arrList.add(elem)
                else
                    arrList.add(getValueFromCopiedCollection(elem, copiedObjects))
            }
            newArray = obj.clone()
            arrList.toArray(newArray)
            copiedObjects[obj] = newArray
            return newArray as T
        }
        is List<*> -> {
            val arrList = ArrayList<Any?>()
            for (elem in obj){
                if (elem == null)
                    arrList.add(elem)
                else
                    arrList.add(getValueFromCopiedCollection(elem, copiedObjects))
                copiedObjects[obj] = arrList
            }

        }
        is Map<*,*> -> {
            val newMap = mutableMapOf<Any?,Any?>()
            for ((key,value) in obj){
                if (key == null){
                    if (value == null)
                        newMap[key] = value
                    else
                        newMap[key] = getValueFromCopiedCollection(value,copiedObjects)
                } else {
                    if (value === null)
                        newMap[getValueFromCopiedCollection(key,copiedObjects)] = value
                    else
                        newMap[getValueFromCopiedCollection(key,copiedObjects)] = getValueFromCopiedCollection(value,copiedObjects)
                }
            }
            copiedObjects[obj] = newMap
            return newMap as T
        }
        is Set<*> -> {
            val newSet = mutableSetOf<Any?>()
            for (elem in obj){
                if (elem == null)
                    newSet.add(elem)
                else
                    newSet.add(getValueFromCopiedCollection(elem, copiedObjects))
            }
            copiedObjects[obj] = newSet
            return newSet as T
        }
        is Date -> {
            return Date(obj.time) as T
        }
        else -> {
            println("deepCopy, class name: ${objClass.simpleName}")
            val properties = objClass.memberProperties
            val newCopy = objClassJava.newInstance()

            properties.forEach {prop ->
                val field = prop.javaField
                if (field != null){
                    field.isAccessible = true
                    val value = field.get(obj)
                    val type = field.type

                    if (value == null)
                        field.set(newCopy, value)
                    else
                        field.set(newCopy, getValueFromCopiedCollection(value,copiedObjects))
                }
            }
            copiedObjects[obj] = newCopy
            return newCopy
        }
    }
    //傻逼kotlin,前面都return完了，还要强行你这样写，虽然可以return when,但是代码不直观
    return obj
}

private fun<T: Any> getValueFromCopiedCollection(value: T,
                                                 copiedObjects: MutableMap<Any,Any>): T{

    if (copiedObjects.containsKey(value)){
        return copiedObjects[value] as T
    }

    var tempValue: Any? = null


    if (!value::class.java.isPrimitive//不是基本类型
            && value::class.javaPrimitiveType == null//不是基本类型
            && !value::class.java.isArray//不是数组
            && (value !is Collection<*>)){//是集合

        tempValue = value::class.createInstance()
    }

    if (copiedObjects.isNotEmpty()){
        tempValue = deepCopy(value,copiedObjects)
    }

    if (tempValue == null)//基本类型
        tempValue = value
    else
        copiedObjects[value] = tempValue

    return tempValue as T
}