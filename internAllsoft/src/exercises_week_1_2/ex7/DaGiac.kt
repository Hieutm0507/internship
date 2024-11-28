package exercises_week_1_2.ex7

open class DaGiac (
    protected var soCanh: Int,
    protected val canh:IntArray
) {

    open fun chuVi(): Int {
        var sum = 0
        for (int in canh) {
            sum += int
        }
        return sum
    }

    open fun inGiaTri() {
        for (int in canh) {
            print("$int")
        }
    }
}