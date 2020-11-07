package demo.byte

fun ByteArray.toHexString():String = joinToString(separator = "") { "%02x".format(it) }