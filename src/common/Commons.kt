package common

fun convertDecimal2BinaryString(number: Int): String {
    if (number < 0) throw IllegalArgumentException("Number must be non-negative")
    var result = ""
    var n = number
    do {
        result = "${n % 2}$result"
        n /= 2
    } while (n >= 1)
    return result.substringAfter('1').ifEmpty { "0" }
}

fun main() {
    println(convertDecimal2BinaryString(1))
    println(convertDecimal2BinaryString(10))
}
