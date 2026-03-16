sealed class PatientCondition {
    data class CriticalPatient(
        val name: String,
        val symptoms: String,
        val requireImmediateSurgery: Boolean
    ): PatientCondition()

    data class ModeratePatient(
        val name: String,
        val symptoms: String,
        val waitingTime: Int
    ): PatientCondition()

    data class MinorPatient(
        val name: String,
        val symptoms: String,
    ): PatientCondition()
}

fun triagePatient(condition: PatientCondition): String {
    return when (condition) {
        is PatientCondition.CriticalPatient -> "EMERGENCY: ${condition.name} needs immediate attention. Surgery required: ${condition.requireImmediateSurgery}"
        is PatientCondition.ModeratePatient -> "${condition.name} will be attended to in ${condition.waitingTime} minutes. Reported issue: ${condition.symptoms}"
        is PatientCondition.MinorPatient -> "${condition.name} has been registered. Please wait in the waiting bay."
    }
}

fun main() {
    val patients = listOf(
        PatientCondition.CriticalPatient(
            "Joyce Killian",
            "Internal bleeding, Convulsing",
            true
        ),
        PatientCondition.ModeratePatient(
            "Wesly Homes",
            "Shoulder dislocation",
            10
        ),
        PatientCondition.MinorPatient(
            "Bridget Sunders",
            "Headache, fever"
        )
    )

    patients.forEach { patient ->
        println(triagePatient(patient))
    }
}