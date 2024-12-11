package codeForSlides.ex8_inheritance

open class DongVat {
    val nguonGoc = "Thổ Nhỹ Kỳ"
    open fun taoTiengOn() {
        println("Ghumm")
    }
}

class BoSua : DongVat() {
    val coSua = true
    fun anCo() {
        println("Grazingg")
    }
}

fun main() {
    val boSua = BoSua()
    // Thừa kế
    println("Nguồn gốc từ: ${boSua.nguonGoc}")
    boSua.taoTiengOn()
    // Thuộc tính, phương thức riêng
    println("Có sữa: ${boSua.coSua}")
    boSua.anCo()
}
