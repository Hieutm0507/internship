package exercises_week_1_2

import kotlin.math.sqrt

// EXERCISE 5: Viết chương trình giải phương trình bậc 2: ax2 + bx + c = 0.

/* HƯỚNG GIẢI QUYẾT: Chia 2 trường hợp
*           TH1: a = 0 => Phương trình bậc nhất
*           TH2: a ≠ 0 => Dùng delta giải phuơng trình bậc 2
*/

fun main() {
    // PHẦN 1: Lấy các giá trị của các hệ số
    println("Hãy nhâp hệ số a:")
    val a:Double = readln().toDouble()
    println("Hãy nhâp hệ số b:")
    val b:Double = readln().toDouble()
    println("Hãy nhâp hệ số c:")
    val c:Double = readln().toDouble()
    println("Phương trình của bạn là $a.(x^2) + $b.x + $c = 0")

    //PHẦN 2: Tìm nghiệm

    // TH1: a = 0
    if (a == 0.0) {
        if (b == 0.0) {
            if (c == 0.0)
                println("=> Phương trình có vô số nghiệm")
            else
                println("=> Phương trình vô nghiệm")
        }

        else
            if (c == 0.0)
                println("=> Phương trình có nghiệm duy nhất: x = 0")
            else
                println("=> Phương trình có nghiệm duy nhất: " + (-c/b))
    }

    // TH2: a ≠ 0
    else {
        val delta = (b * b) - 4 * a * c

        if (delta < 0)
            println("=> Phương trình này vô nghiệm")
        else if (delta == 0.0)  //vì input là Double nên để 0 sẽ báo lỗi
            println("=> Phương trình có nghiệm kép: x = " + (-b / (2 * a)))
        else {
            println("=> Phương trình có hai nghiệm phân biệt")
            println("\t x1 = " + ((-b + sqrt(delta)) / (2 * a)))
            println("\t x2 = " + ((-b - sqrt(delta)) / (2 * a)))
        }
    }
}