package exercises_week_1_2

import java.util.Scanner
fun main() {
    val scanner = Scanner(System.`in`)
    print("Nhập số nguyên dương n: ")
    val n = scanner.nextInt()
    val primeFactors = primeFactorization(n)
    println("Các thừa số nguyên tố của $n là: ${primeFactors.joinToString(" x ")}")
}

fun primeFactorization(number: Int): List<Int> {
    var n = number
    val factors = mutableListOf<Int>()

    // Lấy các thừa số nguyên tố nhỏ nhất (2)
    while (n % 2 == 0) {
        factors.add(2)
        n /= 2 }

    // Lấy các thừa số nguyên tố lẻ từ 3 trở đi
    var i = 3
    while (i <= Math.sqrt(n.toDouble()).toInt()) {
        while (n % i == 0) {
            factors.add(i)
            n /= i
        }

        i += 2 }

    // Nếu n là số nguyên tố lớn hơn 2
    if (n > 2) {
        factors.add(n)
    }
    return factors
}