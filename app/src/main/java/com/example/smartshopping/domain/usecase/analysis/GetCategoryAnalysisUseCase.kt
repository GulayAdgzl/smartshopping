
class GetCategoryAnalysisUseCase @Inject constructor(
    private val repository: ShoppingRepository
) {
    suspend operator fun invoke(): List<CategoryData> {
        return repository.getCategorySpending()
            .map { category ->
                CategoryData(
                    category = category.name,
                    totalSpent = category.total,
                    percentage = calculatePercentage(category),
                    trend = analyzeTrend(category)
                )
            }
    }

    private fun calculatePercentage(category: CategorySpending): Double {
        // Kategori yüzdesi hesaplama logic'i
        return 0.0
    }

    private fun analyzeTrend(category: CategorySpending): Trend {
        // Trend analizi logic'i
        return Trend.STABLE
    }
}
