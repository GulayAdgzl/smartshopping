@Singleton
class ApiKeyStore @Inject constructor(
    private val cryptoManager: CryptoManager
) {
    private val keyAlias = "api_key"
    
    fun storeApiKey(apiKey: String) {
        cryptoManager.saveSecureData(keyAlias, apiKey)
    }
    
    fun getApiKey(): String? {
        return cryptoManager.getSecureData(keyAlias)
    }
}