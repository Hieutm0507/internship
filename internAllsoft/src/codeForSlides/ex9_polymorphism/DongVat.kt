package codeForSlides.ex9_polymorphism

open class DongVat {
    open fun phatRaTieng() {
        println("Phát ra tiếng ồn")
    }
}

class Bo : DongVat() {
    override fun phatRaTieng () {
        println("Bò kêu: Moo...Moo...Moo")
    }
}

class Meo : DongVat() {
    override fun phatRaTieng () {
        println("Mèo kêu: Meow...Meow...Meow")
    }
}

class Cho : DongVat() {
    override fun phatRaTieng () {
        println("Chó sủa: Gâu...Gâu..Gâu")
    }
}

fun main() {
    val bo = Bo()
    bo.phatRaTieng()

    val meo = Meo()
    meo.phatRaTieng()

    val cho = Cho()
    cho.phatRaTieng()
}