package exercises_week_1_2.ex6

fun main() {
    val qlcb = QLCB()
    qlcb.themCanBo()

    while (true) {
        print("\n")
        println("CHƯƠNG TRÌNH QUẢN LÝ CÁN BỘ")
        println("\t1. Thêm mới cán bộ")
        println("\t2. Tìm kiếm theo họ tên")
        println("\t3. Hiển thị thông tin về danh sách cán bộ")
        println("\t4. Thoát khỏi chương trình")
        println("Bạn hãy chọn số ứng với chức năng mong muốn: ")
        val method: Int = readln().toInt()

        // dùng switch case
        when (method) {
            //Thêm Cán Bộ
            1 -> {
                println("Bạn muốn thêm cán bộ ở vị trí nào?")
                println("\t1.Công nhân")
                println("\t2.Kỹ sư")
                println("\t3.Nhân viên")
                println("Lựa chọn của bạn: ")
                val method1: Int = readln().toInt()
                when (method1) {
                    1 -> {
                        print("Họ tên: ")
                        val hoTen = readln()
                        print("Tuổi: ")
                        val tuoi = readln().toInt()
                        print("Giới tính: ")
                        val gioiTinh = readln()
                        print("Địa chỉ: ")
                        val diaChi = readln()
                        print("Bậc (1-10): ")
                        val bac = readln().toInt()

                        val congNhan = CongNhan(viTri = "Công nhân", hoTen, tuoi, gioiTinh, diaChi, bac)
                        qlcb.themCongNhan(congNhan)
                    }

                    2 -> {
                        print("Họ tên: ")
                        val hoTen = readln()
                        print("Tuổi: ")
                        val tuoi = readln().toInt()
                        print("Giới tính: ")
                        val gioiTinh = readln()
                        print("Địa chỉ: ")
                        val diaChi = readln()
                        print("Ngành đào tạo: ")
                        val nganhDT = readln()

                        val kySu = KySu(viTri = "Kỹ Sư", hoTen, tuoi, gioiTinh, diaChi, nganhDT)
                        qlcb.themKySu(kySu)
                    }

                    3 -> {
                        print("Họ tên: ")
                        val hoTen = readln()
                        print("Tuổi: ")
                        val tuoi = readln().toInt()
                        print("Giới tính: ")
                        val gioiTinh = readln()
                        print("Địa chỉ: ")
                        val diaChi = readln()
                        print("Công việc: ")
                        val congViec = readln()

                        val nhanVien = NhanVien(viTri = "Nhân viên", hoTen, tuoi, gioiTinh, diaChi, congViec)
                        qlcb.themNhanVien(nhanVien)
                    }
                    else -> println("Lựa chọn không hợp lệ. Hãy thử lại!")
                }
            }

            2 -> {
                println("Nhập họ tên Cán bộ mà bạn muốn tìm kiếm:")
                val hoTenTimKiem = readln()
                qlcb.timKiem(hoTenTimKiem)
            }

            3 -> {
                qlcb.inDanhSachCanBo()
            }

            4 -> {
                qlcb.thoat()
                break
            }
        }
    }
}


class QLCB {
    private val danhSachCanBo: MutableList<CanBo> = mutableListOf()

    // Thêm cán bộ có sẵn
    fun themCanBo() {
        val nhanVien1 = NhanVien("Nhân viên", "Minh Hiếu", 20, "Nam", "Hà Nội", "Android Dev")
        danhSachCanBo.add(nhanVien1)
        val kySu1 = KySu("Kỹ Sư", "Minh Anh", 30, "Nữ", "Hà Nam", "Công nghệ phần mềm")
        danhSachCanBo.add(kySu1)
    }

    // Task 1: Thêm mới cán bộ
    fun themCongNhan(congNhan: CongNhan) {
        danhSachCanBo.add(congNhan)
    }

    fun themKySu(kySu: KySu) {
        danhSachCanBo.add(kySu)
    }

    fun themNhanVien(nhanVien: NhanVien) {
        danhSachCanBo.add(nhanVien)
    }


    // Task 2: Tìm kiếm theo họ tên
    fun timKiem(hoTenTimKiem: String) {
        val ketQua = danhSachCanBo.filter {
            it.hoTen.lowercase().contains(hoTenTimKiem.lowercase())
        }

        if (ketQua.isNotEmpty()) {
            println("Kết quả tìm kiếm:")
            ketQua.forEachIndexed { index, canBo ->
                println("Cán bộ số ${index+1}:" + canBo.toString())
            }
        } else {
            println("Không tìm thấy")
        }
    }

    // Task 3: In danh sách cán bộ
    fun inDanhSachCanBo() {
        if (danhSachCanBo.isEmpty())
            println("Danh sách cán bộ trống")
        else {
            println("Danh sách Cán Bộ là:")
            println("VỊ TRÍ - HỌ TÊN - TUỔI - GIỚI TNH - ĐỊA CHỈ - GHI CHÚ")
            danhSachCanBo.forEach {
                println(it.printOutput())
            }
        }
    }

    // Task 4: Thoát khỏi chương trình
    fun thoat() {
        println("Đang đăng xuất ra khỏi chương trình quản lý ...")
        println("Hẹn gặp lại!")
    }
}