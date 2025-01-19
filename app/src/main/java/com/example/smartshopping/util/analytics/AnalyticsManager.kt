@Singleton
class AnalyticsManager @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics
) {
    fun logEvent(eventName: String, params: Map<String, Any> = emptyMap()) {
        val bundle = Bundle().apply {
            params.forEach { (key, value) ->
                when (value) {
                    is String -> putString(key, value)
                    is Int -> putInt(key, value)
                    is Long -> putLong(key, value)
                    is Double -> putDouble(key, value)
                    is Boolean -> putBoolean(key, value)
                }
            }
        }
        firebaseAnalytics.logEvent(eventName, bundle)
    }

    fun logProductView(productId: String) {
        logEvent(
            "product_view",
            mapOf(
                "product_id" to productId,
                "timestamp" to System.currentTimeMillis()
            )
        )
    }

    fun logPriceAlert(productId: String, oldPrice: Double, newPrice: Double) {
        logEvent(
            "price_alert",
            mapOf(
                "product_id" to productId,
                "old_price" to oldPrice,
                "new_price" to newPrice,
                "price_change" to (newPrice - oldPrice)
            )
        )
    }
}