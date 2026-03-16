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
}

fun triagePatient(condition: PatientCondition): String {
    return when (condition) {
        is PatientCondition.Critical -> "EMERGENCY: ${condition.name} needs immediate attention. Surgery required: ${condition.requiresSurgery}"
        is PatientCondition.Moderate -> "${condition.name} will be attended to in ${condition.waitingTime} minutes. Reported issue: ${condition.symptoms}"
        is PatientCondition.Minor -> "${condition.name} has been registered. Please wait in the waiting bay."
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
        )
    )

    patients.forEach { patient ->
        println(triagePatient(patient))
    }
}