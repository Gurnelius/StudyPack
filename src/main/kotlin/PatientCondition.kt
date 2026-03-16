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
        val name: String,
        val symptoms: String,
        val wardNumber: Int
    ): PatientCondition()

}

fun triagePatient(condition: PatientCondition): String {
    return when (condition) {
        is PatientCondition.Critical ->
            "EMERGENCY: ${condition.name} needs immediate attention. Surgery required: ${condition.requiresSurgery}"
        is PatientCondition.Moderate ->
            "${condition.name} will be attended to in ${condition.waitingTime} minutes. Reported issue: ${condition.symptoms}"
        is PatientCondition.Minor ->
            "${condition.name} has been registered. Please wait in the waiting bay."
        is PatientCondition.UnderObservation ->
            "${condition.name} is under observation in ward ${condition.wardNumber}. Reason: ${condition.symptoms}"
    }
}

fun getQueuePriority(condition: PatientCondition): Int {
    return when (condition) {
        is PatientCondition.Critical -> 1
        is PatientCondition.Moderate -> 2
        is PatientCondition.UnderObservation -> 3
        is PatientCondition.Minor -> 4
    }
}


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
            name="James Buzzelndine",
            "Coughing, high fever, weight loss, jaundice",
            wardNumber = 4
        )
    )

    val sorted = patients.sortedBy { getQueuePriority(it) }

    sorted.forEach { patient ->
        println(triagePatient(patient))
    }
}