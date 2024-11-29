package codeForSlides.ex7_encapsulation

open class Person () {
    private var ten:String = ""
    private var tuoi:Int = 0

    fun layTen(pn:String) {
        ten = pn        // pn là person name
    }

    fun layTuoi(pa:Int) {
        tuoi = pa       // pa là person age
    }

    fun traTen(): String {
        return ten
    }

    fun traTuoi(): Int {
        return tuoi
    }
}
