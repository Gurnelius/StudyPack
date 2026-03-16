


sealed class NetworkResult {
    object  Loading: NetworkResult()

    data class Success (
        val username: String,
        val email: String,
    ) : NetworkResult()

    data class Error(
        val message: String,
        val errorCode: Int
    ): NetworkResult()
}

//3. Write a function simulateRequest() that takes an Int and returns a NetworkResult:
//
//1 → return Loading
//2 → return a Success with any data you choose
//3 → return an Error with any message and code you choose
//anything else → an error with message "Unknown state" and code 000
//
//4. In main(), call simulateRequest() for inputs 1, 2, 3, 99 and pass each result to handleResult().

fun handleResult(result: NetworkResult): Unit {
    when (result) {
        is NetworkResult.Loading -> {
            println("Fetching profile, please wait...")
        }
        is NetworkResult.Success -> {
            println("Welcome ${result.username}! Your email is ${result.email}")
        }
        is NetworkResult.Error -> {
            println("Result failed (${result.errorCode}): ${result.message}")
        }
    }
}

fun simulateRequest(code: Int): NetworkResult {

    return when(code) {
        1 -> NetworkResult.Loading
        2 -> NetworkResult.Success(username = "joyce", email = "joyce@gmail.com")
        3 -> NetworkResult.Error(message = "No internet connection.", errorCode = 3)
        else -> NetworkResult.Error(message = "Unknown state.", errorCode = 0)
    }
}

fun main() {
    val codes = listOf(
        1, 2, 3, 99
    )

    codes.forEach { code ->
        handleResult(simulateRequest(code))
    }
}