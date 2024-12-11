package codeForSlides.ex11_overriding

open class DongVat {
    open fun phatRaTieng() {
        println("Phát ra tiếng ồn")
    }
}

class Meo : DongVat() {
    override fun phatRaTieng () {
        println("Mèo kêu: Meow...Meow...Meow")
    }
}

fun main() {
    val meo = Meo()
    meo.phatRaTieng()
}