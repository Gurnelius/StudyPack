import java.io.File

class FileHandler {
    fun write(filename: String) {
        File(filename).writeText("Adam hid himself. ")
        File(filename).appendText("Eve also hid herself.")

    }
}

fun main() {
    val fileHandler = FileHandler()
    fileHandler.write("output.txt")
}