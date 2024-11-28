fun main(args: Array<String>) {
    println("Hello")
//
//    //math oper (plus, minus, times, div)
//    var a = 10
//    var b = 6
//    var c = 2
//    println(a)
//
//    //to print the datatype
//    println(a::class.java.typeName)
//
//    //method 1:
//    println(a+b)
//
//    //method 2:
//    println(a.plus(b))
//
//    //division and ép data
//    println(a/b) //the result will take the approximation
//    var kq:Float = a.toFloat()/b
//    println(kq)
//
//    println(a+(b*c))

    // While loop
    var n = 0
    while (n < 1 || n > 99) {
        println("Hãy nhập số nguyên trong khoảng từ 1 đến 99:")
        var s:String? = readLine()
        if (s != null)
            n=s.toInt()
    }
    println("Thực hiện task tiếp theo")

}