class Box<T>(val value: T){
    init{
        println("This is the value $value")
    }
}


fun main() {
    val box = Box("Rectangle")
    val box2 = Box(2)

    val box3 = Box<Double>(3.3)
}