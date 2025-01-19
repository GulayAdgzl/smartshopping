@Singleton
class CryptoManager @Inject constructor(
    private val context: Context
) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val encryptedSharedPreferences = EncryptedSharedPreferences.create(
        context,
        "secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveSecureData(key: String, value: String) {
        encryptedSharedPreferences.edit().putString(key, value).apply()
    }

    fun getSecureData(key: String): String? {
        return encryptedSharedPreferences.getString(key, null)
    }
}