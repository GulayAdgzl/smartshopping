class TrackProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(productId: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            productRepository.trackProduct(productId)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Ürün takibi başlatılamadı"))
        }
    }
}