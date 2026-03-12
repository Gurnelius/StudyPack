
fun main() {
    task()
}



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