package demo.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun now():String{
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
       return simpleDateFormat.format(Date())
    }
}