package codeForSlides.ex4_constructorInClass.firstConstructor

class Nguoi (ten:String, tuoi:Int, chieuCao:String) {
    init {
        println(ten)
        println("Tuổi: $tuoi")
        println("Chiều cao: $chieuCao")
        println()
    }
}

fun main() {
    val doituong1 = Nguoi("Hiếu", 20, "1m86")
    val doituong2 = Nguoi("An", 21, "1m70")
}
