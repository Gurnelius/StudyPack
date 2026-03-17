


sealed class NetworkResult {
    object  Loading: NetworkResult()
    object Empty: NetworkResult()

    data class Success (
        val username: String,
        val email: String,
    ) : NetworkResult()

    data class Error(
        val message: String,
        val errorCode: Int
    ): NetworkResult()
}

fun handleResult(result: NetworkResult): String{
    return when (result) {
        is NetworkResult.Loading -> {
            "Fetching profile, please wait..."
        }
        is NetworkResult.Success -> {
            "Welcome ${result.username}! Your email is ${result.email}"
        }
        is NetworkResult.Error -> {
            "Result failed (${result.errorCode}): ${result.message}"
        }
        is NetworkResult.Empty -> {
            "Profile exists but no data was returned."
        }
    }
}

fun simulateRequest(code: Int): NetworkResult {

    return when(code) {
        1 -> NetworkResult.Loading
        2 -> NetworkResult.Success(username = "joyce", email = "joyce@gmail.com")
        3 -> NetworkResult.Error(message = "No internet connection.", errorCode = 3)
        4 -> NetworkResult.Empty
        else -> NetworkResult.Error(message = "Unknown state.", errorCode = 0)
    }
}

fun isTerminal(result: NetworkResult): Boolean {
    return result !is NetworkResult.Loading
}

fun main() {
    val codes = listOf(
        1, 2, 3, 4, 99
    )

    codes.forEach { code ->
        val result = simulateRequest(code)
        println(handleResult(result))
        println("Is terminal state: ${isTerminal(result)}")
    }
}