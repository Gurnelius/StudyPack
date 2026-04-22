
fun main() {
    val str = "This is not good. However, I care not."

    println(str.countVowels())
    println(str.countCaps())
    println(str.count())


    println("Task:")
    task()

    val names = "Joyce, Killian, Julius, Joel"
    val list = names.split(",")
    val join = list.joinToString(" | ")

    println("the quick brown fox".toTitleCase())
}

fun String.toTitleCase(): String =
    split(" ").joinToString(" ") { it.replaceFirstChar { it.uppercase() } }

fun String.countVowels(): Int {
    var count = 0
    val vowels = listOf('a', 'e', 'i', 'o', 'u')

    for (i in 0.. this.length - 1) {
        if (this[i] in vowels) {
            count++
        }
    }

    return count
}

fun String.countCaps(): Int = count { it.isUpperCase() }

fun task(): Unit{
    val tasks = listOf("Buy Bread", "Write Code", "Sleep")

    for (task in tasks) {
        if ("Code" in task) {
            println(task)
        }
    }

    println("Using list comprehensions")

    val taskCode = tasks.filter{it.contains("Code")}
    val upperCase = tasks.map{it.uppercase()}

    println(taskCode)
    println(upperCase)

    println("Chained functions: \n")

    tasks
        .filter{it.contains("r")}
        .map{it.lowercase()}
        .forEach{println(it) }
}


