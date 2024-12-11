package codeForSlides.ex2_attributeInClass

class PhepTinh {
    val a = 10
    val b = 5

    fun phepCong ():Int {
        return a + b
    }

    fun phepNhan (): Int {
        return a * b
    }
}

fun main() {
    val phepTinh = PhepTinh()
    println("Kết quả phép cộng: ${phepTinh.phepCong()}")
    println("Kết quả phép nhân: ${phepTinh.phepNhan()}")
}
