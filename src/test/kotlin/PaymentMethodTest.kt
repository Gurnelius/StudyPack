import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class PaymentMethodTest {

    private lateinit var bankTransfer: PaymentMethod.BankTransfer
    private lateinit var creditCard: PaymentMethod.CreditCard
    private lateinit var mobileMoney: PaymentMethod.MobileMoney

    private var phoneNumber = "0728456854"
    private var bankName = "Equity"
    private var accountNumber = "483843943853353"
    private var cardNumber = "43853355383833"
    private var expiryDate = "10/28"
    private var provider = "Safaricom"
    private var amount = 1500.0

    @BeforeEach                          // runs before every test — like setUp() in Python
    fun setUp() {

        bankTransfer = PaymentMethod.BankTransfer(bankName = bankName, accountNumber = accountNumber)
        creditCard = PaymentMethod.CreditCard(cardNumber = cardNumber, expiryDate = expiryDate)
        mobileMoney = PaymentMethod.MobileMoney(phoneNumber = phoneNumber, provider = provider)
    }

    @AfterEach                           // runs after every test — like tearDown() in Python
    fun tearDown() {
        println("Test completed, cleaning up...")
    }

    @Test
    fun test_bank_transfer_returns_the_correct_message() {
        val result = processPayment(amount=amount, method=bankTransfer)
        assertEquals(
            "Transferring KES $amount to $accountNumber at $bankName",
            result
        )
    }

    @Test
    fun test_bank_transfer_can_handle_negative_amount() {
        val negativeAmount = -1500.0
        val result = processPayment(amount=negativeAmount, method=bankTransfer)
        assertEquals("Amount must be greater than zero.",result)
    }

    @Test
    fun test_bank_transfer_can_handle_zero_amount() {
        val zeroAmount = 0.0
        val result = processPayment(amount=zeroAmount, method=bankTransfer)
        assertEquals("Amount must be greater than zero.",result)
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
    fun test_process_payment_can_handle_wrong_credit_numbers() {
        val shortCardNumber = "444"
        val invalidCard = PaymentMethod.CreditCard(cardNumber = shortCardNumber, expiryDate = expiryDate)
        val result = processPayment(amount = amount, method = invalidCard)
        assertEquals("Invalid card number: $shortCardNumber", result)
    }

    @Test
    fun test_mobile_money_returns_the_correct_message() {
        val result = processPayment(amount=amount, method=mobileMoney)
        assertEquals(
            "Sending KES $amount via $provider to $phoneNumber",
            result
        )
    }

    @Test
    fun test_process_payment_can_handle_empty_phone_number() {
        val phoneNumber = ""
        val invalidMobileMoney = PaymentMethod.MobileMoney(phoneNumber = phoneNumber, provider = provider)
        val result = processPayment(amount=amount, method=invalidMobileMoney)

        assertEquals("Sending KES $amount via $provider to $phoneNumber",result)
    }


}