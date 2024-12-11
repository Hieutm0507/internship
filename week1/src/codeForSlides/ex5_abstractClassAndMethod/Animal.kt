package codeForSlides.ex5_abstractClassAndMethod

abstract class Animal {
    open fun dangNgu () {
        println("Đi ngủ rồi")
    }

    abstract fun taoTiengOn ()
}

class Bo : Animal() {
    override fun dangNgu() {
        println("Bò ngủ: zzz...zzz...zzz")
    }

    override fun taoTiengOn () {
        println("Bò kêu: Moo...Moo...Moo")
    }
}

fun main() {
    val bo = Bo()
    bo.dangNgu()
    bo.taoTiengOn()
}
