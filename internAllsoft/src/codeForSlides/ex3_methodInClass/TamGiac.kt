package codeForSlides.ex3_methodInClass

import kotlin.math.sqrt

class TamGiac {
    private var a = 3.0
    private var b = 5.0
    private var c = 4.0

    fun chuVi(): Double {
        return a + b + c
    }

    private fun nuaChuVi(): Double {
        return chuVi()/2
    }

    fun dienTich(): Double {
        val p = nuaChuVi()
        val s = sqrt(p * (p-a) * (p-b) * (p-c))
        return s
    }
}

fun main() {
    println("Chu vi tam giác là: ${TamGiac().chuVi()}")
    println("Diện tích tam giác là: ${TamGiac().dienTich()}")
}