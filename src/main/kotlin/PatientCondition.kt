sealed class PatientCondition {
    data class Critical(
        val name: String,
        val symptoms: String,
        val requiresSurgery: Boolean
    ): PatientCondition()

    data class Moderate(
        val name: String,
        val symptoms: String,
        val waitingTime: Int
    ): PatientCondition()

    data class Minor(
        val name: String,
        val symptoms: String,
    ): PatientCondition()

    data class UnderObservation(
        val wardNumber: Int
    ): PatientCondition()

}

fun triagePatient(condition: PatientCondition): String {
    return when (condition) {
        is PatientCondition.Critical -> "EMERGENCY: ${condition.name} needs immediate attention. Surgery required: ${condition.requiresSurgery}"
        is PatientCondition.Moderate -> "${condition.name} will be attended to in ${condition.waitingTime} minutes. Reported issue: ${condition.symptoms}"
        is PatientCondition.Minor -> "${condition.name} has been registered. Please wait in the waiting bay."
        is PatientCondition.UnderObservation -> "The patient is under observation in ward ${condition.wardNumber}."
    }
}

fun getQueuePriority(condition: PatientCondition): Int {
    // Critical = 1, Moderate = 2, UnderObservation = 3, Minor = 4
    return when (condition) {
        is PatientCondition.Critical -> 1
        is PatientCondition.Moderate -> 2
        is PatientCondition.UnderObservation -> 3
        is PatientCondition.Minor -> 4
    }
}

//Stretch Goals (once the basics work)
//
//Add a 4th condition UnderObservation with a wardNumber and see how the compiler guides you to handle it everywhere
//Write a function getQueuePriority() that returns an Int (1 = most urgent) based on condition type
//Create a list of 5 patients in mixed order and sort them by priority before printing

fun main() {
    val patients = listOf(
        PatientCondition.Critical(
            "Joyce Killian",
            "Internal bleeding, Convulsing",
            true
        ),
        PatientCondition.Moderate(
            "Wesly Homes",
            "Shoulder dislocation",
            10
        ),
        PatientCondition.Minor(
            "Bridget Sunders",
            "Headache, fever"
        ),

        PatientCondition.UnderObservation(
            wardNumber = 4
        )
    )

    val sorted = patients.sortedBy { getQueuePriority(it) }

    sorted.forEach { patient ->
        println(triagePatient(patient))
    }
}