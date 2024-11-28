package exercises_week_1_2

/* EXERCISE 1: Viết chương trình tìm tất cả các số chia hết cho 7 nhưng không phải bội số của 5,
        nằm trong đoạn 10 và 200 (tính cả 10 và 200). Các số thu được sẽ được in thành chuỗi trên
        một dòng, cách nhau bằng dấu phẩy.
*/

    /* Hướng giải quyêt: Dùng for loop để lấy các giá trị từ 10 đến 100
                         Nếu giá trị nào chia 7 dư 0 sẽ được print ra
                         Trong đó các giá trị chia 5 dư 0 sẽ bị pass
    */


fun main() {
    val tapSo = mutableListOf<Int>()

    for (i in 10..200) {
        if (i%7 == 0 && i%5 != 0) {
            tapSo.add(i)
        }
    }
    println(tapSo.joinToString ( ", " ))
}