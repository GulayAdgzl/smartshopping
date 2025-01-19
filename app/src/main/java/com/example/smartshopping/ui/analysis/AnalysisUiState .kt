sealed class AnalysisUiState {
    object Loading : AnalysisUiState()
    data class Success(
        val spendingData: List<SpendingData>,
        val categoryData: List<CategoryData>,
        val suggestions: List<SavingSuggestion>
    ) : AnalysisUiState()
    data class Error(val message: String) : AnalysisUiState()
}
data class SpendingData(
    val date: String,
    val amount: Double,
    val comparedToAverage: Double
)

data class CategoryData(
    val category: String,
    val totalSpent: Double,
    val percentage: Double,
    val trend: Trend
)

data class SavingSuggestion(
    val title: String,
    val description: String,
    val potentialSaving: Double
)

enum class Trend {
    INCREASING, DECREASING, STABLE
}