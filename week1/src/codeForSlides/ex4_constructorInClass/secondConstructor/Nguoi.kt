package codeForSlides.ex4_constructorInClass.secondConstructor

class Nguoi {
    // Second Constructor 1
    constructor(ten:String, tuoi:Int, chieuCao:String) {
        println(ten)
        println("Tuổi: $tuoi")
        println("Chiều cao: $chieuCao")
        println()
    }
    // Second Constructor 2
    constructor(ten:String, tuoi:Int) {
        println(ten)
        println("Tuổi: $tuoi")
        println()
    }
}

fun main() {
    // Sử dụng constructor 1
    val doituong1 = Nguoi("Hiếu", 20, "1m86")
    val doituong2 = Nguoi("An", 21, "1m70")
    // Sử dụng constructor 2
    val doituong3 = Nguoi("Chi", 18)
}
