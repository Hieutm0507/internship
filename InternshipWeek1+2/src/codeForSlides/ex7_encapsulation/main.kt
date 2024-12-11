package codeForSlides.ex7_encapsulation

fun main() {
    val p = Person()
    p.layTen("Hiếu")
    p.layTuoi(20)
    println("Tên: ${p.traTen()}")
    println("Tuổi: ${p.traTuoi()}")
}
