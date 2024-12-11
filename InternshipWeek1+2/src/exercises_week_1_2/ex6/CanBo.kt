package exercises_week_1_2.ex6

open class CanBo (
    var viTri:String,
    var hoTen:String,
    var tuoi:Int,
    var gioiTinh:String,
    var diaChi:String
) {

    init {
        print("Thông tin Cán bộ mới được thêm: ")
        println("$viTri: $hoTen - $tuoi - $gioiTinh - $diaChi")
        println("--------------------------------------")
    }

    open fun printOutput() : String {
        val output = "$viTri - $hoTen - $tuoi - $gioiTinh - $diaChi - "
        return output
    }
}
