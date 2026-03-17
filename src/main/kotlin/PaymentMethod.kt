sealed class PaymentMethod {

    data class BankTransfer (
        val bankName: String,
        val accountNumber: String
    ): PaymentMethod()

    data class MobileMoney (
        val phoneNumber: String,
        val provider: String
    ): PaymentMethod()

    data class CreditCard (
        val cardNumber: String,
        val expiryDate: String
    ): PaymentMethod()
}


fun processPayment(amount: Double, method: PaymentMethod): String {

    return when(method) {
        is PaymentMethod.CreditCard -> {
            // We get smart cast here — Kotlin KNOWS it's a CreditCard
            // so we can access .cardNumber directly, no casting needed
            // Handle too short credit card
            if (method.cardNumber.length !in 13..19) {
                "Invalid card number: ${method.cardNumber}"
            }
            else {
                "Charging KES $amount to card ending in ${method.cardNumber.takeLast(4)}"
            }
        }
        is PaymentMethod.MobileMoney -> {
            "Sending KES $amount via ${method.provider} to ${method.phoneNumber}"
        }
        is PaymentMethod.BankTransfer -> {
            if (amount < 1) {
                "Amount must be greater than zero."
            } else {
                "Transferring KES $amount to ${method.accountNumber} at ${method.bankName}"
            }
        }
        // No else needed — the compiler KNOWS these are the only 3 options
        // If you add a 4th subclass later, this will fail to compile until you handle it
    }
}

fun main() {
    val payments = listOf(
        PaymentMethod.BankTransfer("Equity", "483843943853353"),
        PaymentMethod.CreditCard("43853355383833", "10/28"),
        PaymentMethod.MobileMoney("0728456854", "Safaricom")
    )

    payments.forEach { method ->
        println(processPayment(1500.90, method))
    }
}