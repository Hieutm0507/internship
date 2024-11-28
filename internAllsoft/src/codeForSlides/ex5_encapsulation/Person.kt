package codeForSlides.ex5_encapsulation

open class Person (ten:String, tuoi:Int) {
    private var ten:String = ""
    private var tuoi:Int = 0

    public fun layTen():String {
        return ten
    }

    public fun layTuoi(): Int {
        return tuoi
    }
}