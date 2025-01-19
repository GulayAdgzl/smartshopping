interface ProductRepository {
    suspend fun getProducts(): Flow<List<Product>>
    suspend fun getProductById(id: String): Product?
    suspend fun trackProduct(productId: String)
    suspend fun getTrackedProducts(): Flow<List<Product>>
    suspend fun getPriceHistory(productId: String): Flow<List<PriceHistory>>
}