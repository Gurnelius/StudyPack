
enum class Priority { HIGH, MEDIUM, LOW }

// Extend List<Task> directly
fun List<Task>.pending() = filter { !it.done }
fun List<Task>.completed() = filter { it.done }
fun List<Task>.summary() = "${completed().size}/${size} tasks done"

data class Task(val title: String, var done: Boolean = false, val priority: Priority = Priority.MEDIUM){


    private val badge = when(priority) {
        Priority.LOW -> "🟢"
        Priority.MEDIUM -> "🟡"
        Priority.HIGH -> "🔴"
    }

    override fun toString() = "${if (done) "✅" else "⬜"} [$badge] $title"
}

class TaskManager {
    private val tasks = mutableListOf<Task>()

    fun addTask(title: String, priority: Priority = Priority.MEDIUM) {
        tasks.add(Task(title, priority = priority))
        println("Added task: $title")
    }

    fun completeTask(index: Int) {
        tasks.getOrNull(index)?.let {
            it.done = true
            println("Completed task: $it")
        }
    }

    fun showTasks() {
        if (tasks.isEmpty()) return println("No tasks yet.")
        tasks.forEach(::println)
    }

    fun deleteTask(index: Int) {
        tasks.getOrNull(index)?.let {
            tasks.removeAt(index)
            println("Removed task: $it")
        }
    }

    fun showPending() = tasks.pending().forEach { println("Pending $it") }
    fun showSummary() = println(tasks.summary())
    fun showPriority(priority: Priority) =
        tasks.filter { it.priority == priority }.forEach(::println)

}

fun main() {

    val tasks = TaskManager()
    tasks.showTasks()
    tasks.addTask("Learn Kotlin", Priority.HIGH)
    tasks.addTask("Buy bread")
    tasks.addTask("Cook food")
    tasks.showPriority(priority = Priority.HIGH)
    tasks.completeTask(0)
    tasks.showTasks()
    tasks.deleteTask(2)
    tasks.showTasks()
    tasks.showPending()
    tasks.showSummary()
}