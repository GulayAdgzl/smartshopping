sealed class AnalysisUiState {
    object Loading : AnalysisUiState()
    data class Success(
        val spendingData: List<SpendingData>,
        val categoryData: List<CategoryData>,
        val suggestions: List<SavingSuggestion>
    ) : AnalysisUiState()
    data class Error(val message: String) : AnalysisUiState()
}
