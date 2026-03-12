class Rectangle(var length: Int, var width: Int) {

    fun draw() {
        drawLength()
        drawWidth()
        drawLength()
    }

    fun drawLength() {
        for (i in 0 until length) {
            print("* ")
        }
        println()
    }

    fun drawWidth() {

        val adjustedHeight = (width - 2) / 2

        for (i in 0 until adjustedHeight) {

            print("* ")

            for (j in 0 until length - 2) {
                print("  ")
            }

            println("*")
        }
    }
}

fun main() {
    val rect = Rectangle(20, 10)
    rect.draw()
}