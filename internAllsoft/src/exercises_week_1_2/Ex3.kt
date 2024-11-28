package exercises_week_1_2

import kotlin.math.sqrt

/* EXERCISE 3: Viết chương trình nhập số nguyên dương n và thực hiện các chức năng sau:
                    a) Tính tổng các chữ số của n.
                    b) Phân tích n thành tích các thừa số nguyên tố.
                    c) Liệt kê các ước số của n.
                    d) Liệt kê các ước số là nguyên tố của n.
*/

/* HƯỚNG GIẢI QUYẾT:
 *      a) Chia cho 10 lấy phần dư => cộng lại
 *      b) chạy giá trị từ 2 đến căn => while loop + soNguyenTo()
 *      c) for loop, chia hết => lấy
 *      d) for loop, chia hết + số nguyên tố => lấy
 */

fun main() {
    var num = 0
    while (num <= 0) {
        print("Hãy nhập số nguyên dương nè: ")
        val n:String = readln()
        num = n.toInt()
    }

    // a) Tính tổng các phần tử
    val sumOfNum = sumNumber(num)
    println("Tổng các chữ số = $sumOfNum")

    // -----------------------------------------------------------
    // b) Phân tích thành thừa số nguyên tố
    val thuaSoNguyenTo = phanTichNguyenTo(num)
    println("Thừa số nguyên tố của $num là $thuaSoNguyenTo")


    // -----------------------------------------------------------
    // c) Liệt kê các ước số của n.
    val uoc = timUoc(num)
    println("Các ước của $num là: $uoc")


    // -----------------------------------------------------------
    // d) Liệt kê các ước số là nguyên tố của n.
    val uocNguyenTo = timUocNguyenTo(num)
    println("Các ước là số nguyên tố của $num là: $uocNguyenTo")
}

fun sumNumber(number: Int): Int {
    var sum = 0
    var num = number

    while (num != 0) {
        sum += num % 10
        num /= 10
    }
    return sum
}

fun phanTichNguyenTo(num: Int): String {
    val thuaSoNguyenTo = mutableListOf<Int>()
    var n = num
    for (i in 2..sqrt(num.toDouble()).toInt()) {
        while (n % i == 0 && soNguyenTo(i)) {
            thuaSoNguyenTo.add(i)
            n /= i
        }
    }
    return thuaSoNguyenTo.joinToString(" x ")
}

fun timUoc (num: Int): String {
    val uoc = mutableListOf<Int>()

    for (i in 1..num) {
        if (num % i == 0) {
            uoc.add(i)
        }
    }
    return uoc.joinToString(", ")
}

fun soNguyenTo(num: Int): Boolean {
    if (num < 2)
        return false
    else {
        for (i in 2..sqrt(num.toDouble()).toInt()) {
            if (num % i == 0) {
                return false
            }
        }
        return true
    }
}

fun timUocNguyenTo(num: Int): String {
    val uocNguyenTo = mutableListOf<Int>()
    for (i in 1..num) {
        if (num % i == 0 && soNguyenTo(i)) {
            uocNguyenTo.add(i)
        }
    }
    return uocNguyenTo.joinToString(", ")
}