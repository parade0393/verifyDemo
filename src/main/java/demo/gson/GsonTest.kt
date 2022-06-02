package demo.gson

import com.google.gson.GsonBuilder

fun main() {
    val bean = GsonBean()
    bean.name = null
    bean.score = "12"
    val gson = GsonBuilder().registerTypeAdapterFactory(NullStringToEmptyAdapterFactory).serializeNulls().create()
    val toJson = gson.toJson(bean)//{"name":null,"score":"12"}  如果没有serializeNulls，则为{"score":"12"}
    println(toJson)
    val gsonBean = gson.fromJson<GsonBean>(toJson, GsonBean::class.java)
    println(gsonBean)//GsonBean(name=, score=12),如果没有registerTypeAdapterFactory，则为GsonBean(name=null, score=12)

}
