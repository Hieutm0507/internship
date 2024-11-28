package exercises_week_1_2

/* EXERCISE 4: Viết chương trình nhập vào mảng A có n phần tử, các phần tử là số nguyên lớn hơn 0 và nhỏ hơn 100. Thực hiện các chức năng sau:
        a) Tìm phần tử lớn thứ nhất và lớn thứ 2 trong mảng với các chỉ số của chúng (chỉ số đầu tiên tìm được).
        b) Sắp xếp mảng theo thứ tự tăng dần.
        c) Nhập số nguyên x và chèn x vào mảng A sao cho vẫn đảm bảo tính tăng dần cho mảng A.
 */

fun main() {
    // Hàm để lấy kích thước của array
    var size = 0
    while (size <= 0) {
        println("Kích thước mảng phải là số nguyên dương")
        println("Hãy nhập kích thước của mảng nha:")
        val s:String = readln()
        size = s.toInt()
    }

    // Tạo array
    val array = IntArray(size)

    // Nhập elements cho array
    for (i in 0 .. size-1) {
        println("Hãy nhập giá trị cho index $i")
        array[i] = readln().toInt()
    }

    // Duyệt mảng
    println("\nMảng của bạn là:")
    for (i in array) {
        print("  $i \t")
    }

    // -----------------------------------------------------------

    // (a) Tìm giá trị lớn nhất và giá trị lớn thứ 2
    println("\n\na)")
    val indexMax = array.indexOf(array.max())
    println("Giá trị lớn nhất = ${array.max()} ứng với chỉ số $indexMax")

    var max = Int.MIN_VALUE
    var secondMax = Int.MIN_VALUE
    for(element in array) {
        if (element > max) {
            secondMax = max
            max = element
        }
        else if (element in (secondMax+1) .. max) {
            secondMax = element
        }
    }

    val indexSecondMax = array.indexOf(secondMax)
    println("Giá trị lớn thứ hai = $secondMax ứng với chỉ số $indexSecondMax")

    // -----------------------------------------------------------

    // (b) Sắp xếp tăng dần
    array.sort()
    println("\nb)")
    println("Sắp xếp mảng theo chiều tăng dần các giá trị:")
    for (i in array) {
        print("  $i \t")
    }

    // -----------------------------------------------------------

    // (c) Nhập thêm số nguyên và sắp xếp
    println("\n\nc) Giá trị bạn muốn nhập thêm")
    val x:Int = readln().toInt()
    val array2 = IntArray(size+1)
    for (i in 0 until size) {
        array2[i] = array[i]
        array2[size] = x
    }

    array2.sort()
    println("Sắp xếp lại mảng")
    for (i in array2) {
        print("  $i \t")
    }
}