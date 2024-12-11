package exercises_week_1_2.ex7

import kotlin.math.sqrt

open class TamGiac(
    canh: IntArray
):DaGiac(3, canh) {

    override fun chuVi(): Int {
        return canh.sum()
    }

    private fun nuaChuVi(): Int {
        return chuVi()/2
    }

    fun dienTich(): Double {
        val p = nuaChuVi()
        val s = sqrt((p*(p-canh[0])*(p-canh[1])*(p-canh[2])).toDouble())
        return s
    }

    override fun toString(): String {
        val a = canh[0].toString()
        val b = canh[1].toString()
        val c = canh[2].toString()
        return "\t $a\t\t|\t $b\t\t|\t $c\t\t|"
    }
}