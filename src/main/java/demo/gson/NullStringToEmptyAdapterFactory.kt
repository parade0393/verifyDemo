package demo.gson

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken

object NullStringToEmptyAdapterFactory :TypeAdapterFactory{
    override fun <T : Any?> create(gson: Gson?, type: TypeToken<T>?): TypeAdapter<T>? {
        val rawType:Class<T> = type?.rawType as Class<T>
        if (rawType!=String::class.java){
            return null
        }
        return StringAdapter() as TypeAdapter<T>
    }

}