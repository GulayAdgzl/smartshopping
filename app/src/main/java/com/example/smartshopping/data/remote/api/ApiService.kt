interface ApiService {
    @GET("products")
    suspend fun getProducts(): Response<List<ProductResponse>>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") productId: String): Response<ProductResponse>

    @GET("products/{id}/price-history")
    suspend fun getPriceHistory(@Path("id") productId: String): Response<List<PriceHistoryResponse>>
}