
data class Task(val title: String, var done: Boolean = false){
    override fun toString() = "${if (done) "✅" else "⬜"} $title"
}

class TaskManager {
    private val tasks = mutableListOf<Task>()

    fun addTask(title: String) {
        tasks.add(Task(title))
        println("Added task: $title")
    }

    fun completeTask(index: Int){
        tasks.getOrNull(index)?.let {
            it.done = true
            println("Completed task: $it")
        }
    }

    fun showTasks() {
        tasks.forEach(::println)
    }

    fun deleteTask(index: Int) {
        tasks.getOrNull(index)?.let{
            tasks.removeAt(index)
            println("Removed task: $it")
        }
    }
}


fun main() {

    val tasks = TaskManager()
    tasks.addTask("Learn Kotlin")
    tasks.addTask("Buy bread")
    tasks.addTask("Cook food")
    tasks.completeTask(0)
    tasks.showTasks()
    tasks.deleteTask(2)
    tasks.showTasks()
}