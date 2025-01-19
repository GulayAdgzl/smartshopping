class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val apiService: ApiService
) : ProductRepository {
    
    override suspend fun getProducts(): Flow<List<Product>> = flow {
        // Emit cached data first
        emit(productDao.getAllProducts().map { it.toDomain() })
        
        try {
            // Fetch from remote
            val response = apiService.getProducts()
            if (response.isSuccessful) {
                response.body()?.let { products ->
                    // Update cache
                    products.forEach { 
                        productDao.insertProduct(it.toEntity()) 
                    }
                    // Emit new data
                    emit(products.map { it.toDomain() })
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun trackProduct(productId: String) {
        productDao.getProductById(productId)?.let { product ->
            productDao.insertProduct(product.copy(isTracked = true))
        }
    }

    override suspend fun getTrackedProducts(): Flow<List<Product>> = flow {
        emit(productDao.getTrackedProducts().map { it.toDomain() })
    }
}