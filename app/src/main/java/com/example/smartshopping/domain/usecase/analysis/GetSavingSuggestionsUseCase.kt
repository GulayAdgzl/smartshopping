class GetSavingSuggestionsUseCase @Inject constructor(
    private val repository: ShoppingRepository
) {
    suspend operator fun invoke(): List<SavingSuggestion> {
        val spending = repository.getSpendingHistory()
        val categories = repository.getCategorySpending()
        
        return analyzeSavingOpportunities(spending, categories)
    }

    private fun analyzeSavingOpportunities(
        spending: List<SpendingHistory>,
        categories: List<CategorySpending>
    ): List<SavingSuggestion> {
        // Tasarruf önerileri oluşturma logic'i
        return emptyList()
    }
}