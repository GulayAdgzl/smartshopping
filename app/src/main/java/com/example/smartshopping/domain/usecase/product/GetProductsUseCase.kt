class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())
        try {
            productRepository.getProducts()
                .collect { products ->
                    emit(Resource.Success(products))
                }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Bir hata oluştu"))
        }
    }
}