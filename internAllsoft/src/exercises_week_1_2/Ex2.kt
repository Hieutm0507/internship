package exercises_week_1_2

import kotlin.math.sqrt

/* EXERCISE 2: Viết chương trình liệt kê các số Fibonacci nhỏ hơn n và là số nguyên tố trong Java.
 *             Với n là số nguyên dương được nhập từ bàn phím.
 */

fun main() {
    // Lấy giá trị và kiểm tra
    var n = 0
    while (n <= 0) {
        println("Hãy nhập một số nguyên dương bất kỳ:")
        val s:String = readln()
        n = s.toInt()
    }

    val soFibonacci = fibonacci(n)
    val soFibonacciPrime = soFibonacci.filter { checkSoNguyenTo(it) }

    println("Chuỗi số Fibonacci nhỏ hơn $n và là số nguyên tố là: ")
    println(soFibonacciPrime)
}

fun fibonacci(n: Int): MutableList<Int> {
    val dayFibonacci = mutableListOf(0, 1)
    var f0 = 0
    var f1 = 1
    while (true) {
        val soTiepTheo = f0 + f1
        if (soTiepTheo >= n )
            break
        dayFibonacci.add(soTiepTheo)
        f0 = f1
        f1 = soTiepTheo
    }
    return dayFibonacci
}

fun checkSoNguyenTo(num: Int): Boolean {
    if (num < 2) return false
    else {
        for (i in 2..sqrt(num.toDouble()).toInt()) {
            if (num % i == 0)
                return false
        }
        return true
    }
}