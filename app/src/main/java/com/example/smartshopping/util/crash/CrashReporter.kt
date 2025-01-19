@Singleton
class CrashReporter @Inject constructor(
    private val crashlytics: FirebaseCrashlytics
) {
    fun logException(throwable: Throwable, customKey: String? = null) {
        crashlytics.apply {
            customKey?.let { key ->
                setCustomKey(key, throwable.message ?: "Unknown error")
            }
            recordException(throwable)
        }
    }

    fun setUserIdentifier(userId: String) {
        crashlytics.setUserId(userId)
    }
}