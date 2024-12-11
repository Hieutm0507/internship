package exercises_week_1_2.ex7

fun main() {
    val tamGiacList = mutableListOf<TamGiac>()

    println("Nhập số lượng tam giác:")
    val n = readln().toIntOrNull() ?: return
    for (i in 1..n) {
        println("Nhập 3 cạnh của tam giác thứ $i:")
        val canh = IntArray(3) { readln().toIntOrNull() ?: 0 }
        tamGiacList.add(TamGiac(canh))
    }


    while (true) {
        println("")
        println("0. In bảng danh sách tam giác")
        println("1. In các cạnh của tam giác có diện tích lớn nhất.")
        println("2. Tìm kiếm tam giác theo thứ tự bạn đã nhập.")
        println("3. Xoá một tam giác.")
        println("4. Sắp xếp tam giác theo chiều tăng dần diện tích.")
        println("5. Thoát khỏi chương trình.")
        println("Bạn hãy chọn số ứng với chức năng mong muốn: ")
        val method = readln().toInt()

        when (method) {
            // In bảng danh sách tam giác
            0 -> {
                if (tamGiacList.size == 0) {
                    println("Không có tam giác")
                }
                else {
                    println("Danh sách tam giác")
                    println("|\tTam giác\t|\tCạnh a\t|\tCạnh b\t|\tCạnh c\t|")
                    for (t in tamGiacList) {
                        println("|\t\t${tamGiacList.indexOf(t)}\t\t|" + t.toString())
                    }
                }
            }

            // In các cạnh của tam giác có diện tích lớn nhất
            1 -> {
                val maxDienTich = tamGiacList.maxBy { it.dienTich() }
                println(maxDienTich)
            }


            // Tìm kiếm theo index
            2 -> {
                print("Nhập thứ tự của tam giác bạn muốn tìm kiếm: ")
                val i = readln().toInt()
                if (i in 0..<n) {
                    val tamGiacCanTim = tamGiacList.filter { tamGiacList.indexOf(it) == i-1 }
                    println(tamGiacCanTim.toString())
                }
                else
                    println("Không tìm thấy")
            }


            // Xoá tam giác
            3 -> {
                print("Nhập thứ tự của tam giác bạn muốn xoá: ")
                val i = readln().toInt()
                if (i in tamGiacList.indices) {
                    tamGiacList.removeAt(i - 1)
                    println("Đã xoá tam giác ở vị trí thứ $i")
                }
                else
                    println("Không tìm thấy tam giác hp lệ")

            }


            // Sắp xếp theo chiều tăng diện tích
            4 -> {
                tamGiacList.sortBy { it.dienTich() }
                println("|\tThứ tự\t|\tCạnh a\t|\tCạnh b\t|\tCạnh c\t|\tDiện tích")
                for (i in tamGiacList) {
                    println("|\t  ${tamGiacList.indexOf(i)+1}\t\t|" + i.toString() + "\t${i.dienTich()}" )
                }
            }

            // Thoát
            5 -> {
                println("Hẹn gặp lại!")
                break
            }
        }
    }
}