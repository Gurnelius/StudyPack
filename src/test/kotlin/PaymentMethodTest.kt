import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class PaymentMethodTest {

    private lateinit var bankTransfer: PaymentMethod.BankTransfer
    private lateinit var creditCard: PaymentMethod.CreditCard
    private lateinit var mobileMoney: PaymentMethod.MobileMoney

    val phoneNumber = "0728456854"
    val bankName = "Equity"
    val accountNumber = "483843943853353"
    val cardNumber = "43853355383833"
    val expiryDate = "10/28"
    val provider = "Safaricom"
    val amount = 1500.9

    @BeforeEach                          // runs before every test — like setUp() in Python
    fun setUp() {


        bankTransfer = PaymentMethod.BankTransfer(bankName, accountNumber)
        creditCard = PaymentMethod.CreditCard(cardNumber = cardNumber, expiryDate)
        mobileMoney = PaymentMethod.MobileMoney(phoneNumber, provider)
    }

    @AfterEach                           // runs after every test — like tearDown() in Python
    fun tearDown() {
        println("Test completed, cleaning up...")
        // close DB connections, clear files, reset state, etc.
    }

    @Test
    fun test_bank_transfer_returns_emergency_message() {
        val result = processPayment(amount=amount, method=bankTransfer)
        assertEquals(
            "Transferring KES $amount to $accountNumber at $bankName",
            result
        )
    }

    @Test
    fun test_credit_card_returns_the_correct_message() {
        val result = processPayment(amount=amount, method=creditCard)
        assertEquals(
            "Charging KES $amount to card ending in ${cardNumber.takeLast(4)}",
            result
        )
    }

    @Test
    fun test_mobile_money_returns_the_correct_message() {
        val result = processPayment(amount=amount, method=mobileMoney)
        assertEquals(
            "Sending KES $amount via $provider to $phoneNumber",
            result
        )
    }


}