package exercises_week_1_2.ex6

class KySu (
    viTri:String,
    hoTen:String,
    tuoi:Int,
    gioiTinh:String,
    diaChi:String,
    private var nganhDT:String
): CanBo (viTri, hoTen, tuoi, gioiTinh, diaChi) {
    override fun printOutput() :String {
        return super.printOutput() + "$nganhDT"
    }

    override fun toString(): String {
        return "  Vị trí: $viTri. Họ và Tên: $hoTen. Tuổi: $tuoi. Giới tính: $gioiTinh. Địa chỉ: $diaChi. Ngành đào tạo: $nganhDT"
    }
}