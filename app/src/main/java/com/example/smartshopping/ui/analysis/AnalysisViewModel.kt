@HiltViewModel
class AnalysisViewModel @Inject constructor(
    private val getSpendingAnalysisUseCase: GetSpendingAnalysisUseCase,
    private val getCategoryAnalysisUseCase: GetCategoryAnalysisUseCase,
    private val getSavingSuggestionsUseCase: GetSavingSuggestionsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AnalysisUiState>(AnalysisUiState.Loading)
    val uiState: StateFlow<AnalysisUiState> = _uiState.asStateFlow()

    init {
        loadAnalysisData()
    }

    private fun loadAnalysisData() {
        viewModelScope.launch {
            try {
                // Paralel olarak tüm analiz verilerini yükle
                val spendingDeferred = async { getSpendingAnalysisUseCase() }
                val categoriesDeferred = async { getCategoryAnalysisUseCase() }
                val suggestionsDeferred = async { getSavingSuggestionsUseCase() }

                // Tüm sonuçları bekle
                val spending = spendingDeferred.await()
                val categories = categoriesDeferred.await()
                val suggestions = suggestionsDeferred.await()

                _uiState.value = AnalysisUiState.Success(
                    spendingData = spending,
                    categoryData = categories,
                    suggestions = suggestions
                )
            } catch (e: Exception) {
                _uiState.value = AnalysisUiState.Error(
                    message = e.message ?: "Analiz verileri yüklenirken bir hata oluştu"
                )
            }
        }
    }

    fun refreshData() {
        _uiState.value = AnalysisUiState.Loading
        loadAnalysisData()
    }
}