import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class PatientConditionTest {

    private lateinit var criticalPatient: PatientCondition.Critical
    private lateinit var minorPatient: PatientCondition.Minor

    @BeforeEach                          // runs before every test — like setUp() in Python
    fun setUp() {
        criticalPatient = PatientCondition.Critical(
            name = "Joyce Killian",
            symptoms = "Internal bleeding",
            requiresSurgery = true
        )
        minorPatient = PatientCondition.Minor(
            name = "Bridget Saunders",
            symptoms = "Headache"
        )
    }

    @AfterEach                           // runs after every test — like tearDown() in Python
    fun tearDown() {
        println("Test completed, cleaning up...")
        // close DB connections, clear files, reset state, etc.
    }

    @Test
    fun test_critical_patient_returns_emergency_message() {
        val result = triagePatient(criticalPatient)
        assertEquals(
            "EMERGENCY: Joyce Killian needs immediate attention. Surgery required: true",
            result
        )
    }

    @Test
    fun test_minor_patient_returns_waiting_bay_message() {
        val result = triagePatient(minorPatient)
        assertEquals(
            "Bridget Saunders has been registered. Please wait in the waiting bay.",
            result
        )
    }

    @Test
    fun test_critical_patient_has_highest_priority() {
        assertEquals(1, getQueuePriority(criticalPatient))
    }
}