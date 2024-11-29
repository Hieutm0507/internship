package codeForSlides.ex6_interface

open class Animal {
    open fun taoTiengOn() {
        println("Ghumm")
    }
}

interface BietBay {
    fun bay()
}

interface BietNhay {
    fun nhay()
}

class BoSieuNhan : Animal(), BietBay, BietNhay {
    override fun taoTiengOn() {
        println("Moo...Moo...Moo")
    }

    override fun bay() {
        println("Bay đỉnh nóc kịch trần")
    }

    override fun nhay() {
        println("Nhảy mượt như Sunsilk")
    }
}

fun main() {
    val boSieuNhan = BoSieuNhan()
    boSieuNhan.taoTiengOn()
    boSieuNhan.bay()
    boSieuNhan.nhay()
}