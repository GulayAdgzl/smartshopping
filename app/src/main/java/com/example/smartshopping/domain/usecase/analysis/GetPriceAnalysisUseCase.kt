class GetPriceAnalysisUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val priceAnalyzer: PriceAnalyzer
) {
    suspend operator fun invoke(productId: String): Flow<Resource<PriceAnalysis>> = flow {
        emit(Resource.Loading())
        try {
            val priceHistory = productRepository.getPriceHistory(productId)
                .first()
            
            val analysis = priceAnalyzer.analyze(priceHistory)
            emit(Resource.Success(analysis))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Fiyat analizi yapılamadı"))
        }
    }
}