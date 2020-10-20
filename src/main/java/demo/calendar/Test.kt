package demo.calendar

import java.time.Year
import java.util.*
import java.util.Calendar



fun main() {
  /*  //测试日期：2020年9月27周日
    val instance = Calendar.getInstance()
    val year = instance.get(Calendar.YEAR)//2020
    val month = instance.get(Calendar.MONTH)//8
    val dayOfMonth = instance.get(Calendar.DAY_OF_MONTH)//27
    val dayOfWeek = instance.get(Calendar.DAY_OF_WEEK)//1 我们的周日是老外一周第一天

    println("${year},${month},${dayOfMonth},${dayOfWeek}")*/

    /*val instance = Calendar.getInstance()
    val weekRow = getWeekRow(instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DAY_OF_MONTH))

    println("weekRow:${weekRow}")//4*/

    val instance = Calendar.getInstance()
    instance.set(instance.get(Calendar.YEAR),instance.get(Calendar.MONTH),1)
    val get = instance.get(Calendar.DAY_OF_WEEK)
    println(get)


}

fun getWeekRow(year: Int,month: Int,day:Int):Int{
    var dayIn = day
    val week = getFirstDayWeek(year, month)//3
    println("week:${week}")//3
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)
    val lastWeek = calendar.get(Calendar.DAY_OF_WEEK)//当前是周几
    println("lastWeek:${lastWeek}")//1
    if (lastWeek == 7)
        dayIn--
    return (dayIn + week - 1) / 7
}

fun getFirstDayWeek(year: Int,month:Int):Int{
    val instance = Calendar.getInstance()
    instance.set(year,month,1)
    return instance.get(Calendar.DAY_OF_WEEK)
}