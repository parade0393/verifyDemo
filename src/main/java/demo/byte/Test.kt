package demo.byte

import java.security.MessageDigest

fun main() {
    val byteArrayOf = byteArrayOf(10, 2, 15, 11)
    val toHexString = byteArrayOf.toHexString()
    println(toHexString)

    val instance = MessageDigest.getInstance("md5")
    instance.update("123456".toByteArray())
    val digest = instance.digest()
    val toHexString1 = digest.toHexString()
    println(toHexString1)


}