package codeForSlides.ex10_overloading

class Nguoi (ten: String, tuoi: Int) {
    fun hienThiThongTin (ten: String) {
        println("Tên: $ten")
    }

    fun hienThiThongTin (tuoi: Int) {
        println("Tuổi: $tuoi")
    }
}

fun main() {
    val doituong3 = Nguoi("Nam", 25)
    doituong3.hienThiThongTin("Nam")
    doituong3.hienThiThongTin(25)
}
