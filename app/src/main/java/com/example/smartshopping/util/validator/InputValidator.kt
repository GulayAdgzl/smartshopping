object InputValidator {
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPrice(price: String): Boolean {
        return try {
            val priceValue = price.toDouble()
            priceValue > 0
        } catch (e: NumberFormatException) {
            false
        }
    }

    fun isValidProductName(name: String): Boolean {
        return name.length in 3..50
    }
}