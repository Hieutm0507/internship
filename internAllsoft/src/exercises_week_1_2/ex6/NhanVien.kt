package exercises_week_1_2.ex6

class NhanVien (
    viTri:String,
    hoTen:String,
    tuoi:Int,
    gioiTinh:String,
    diaChi:String,
    private var congViec:String
): CanBo (viTri, hoTen, tuoi, gioiTinh, diaChi) {
    override fun printOutput() :String {
        return super.printOutput() + "$congViec."
    }

    override fun toString(): String {
        return "  Vị trí: $viTri. Họ và Tên: $hoTen. Tuổi: $tuoi. Giới tính: $gioiTinh. Địa chỉ: $diaChi. Công việc: $congViec"
    }
}