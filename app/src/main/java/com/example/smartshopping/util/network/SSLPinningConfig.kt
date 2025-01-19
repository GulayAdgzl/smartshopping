object SSLPinningConfig {
    private val certificatePinner = CertificatePinner.Builder()
        .add("api.example.com", "sha256/XXXX=") // Production certificate
        .add("api.example.com", "sha256/YYYY=") // Backup certificate
        .build()

    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .build()
    }
}