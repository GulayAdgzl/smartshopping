class GetSpendingAnalysisUseCase @Inject constructor(
    private val repository: ShoppingRepository
) {
    suspend operator fun invoke(): List<SpendingData> {
        return repository.getSpendingHistory()
            .map { spending ->
                SpendingData(
                    date = spending.date,
                    amount = spending.amount,
                    comparedToAverage = calculateComparisonToAverage(spending)
                )
            }
    }

    private fun calculateComparisonToAverage(spending: SpendingHistory): Double {
        // Ortalama harcama ile karşılaştırma logic'i
        return 0.0
    }
}
