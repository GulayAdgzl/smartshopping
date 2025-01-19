@HiltViewModel
class BudgetViewModel @Inject constructor(
    private val getBudgetAnalysisUseCase: GetBudgetAnalysisUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<BudgetUiState>(BudgetUiState.Loading)
    val uiState: StateFlow<BudgetUiState> = _uiState.asStateFlow()

    init {
        loadBudgetAnalysis()
    }

    private fun loadBudgetAnalysis() {
        viewModelScope.launch {
            getBudgetAnalysisUseCase()
                .collect { result ->
                    _uiState.value = when (result) {
                        is Resource.Success -> BudgetUiState.Success(result.data)
                        is Resource.Error -> BudgetUiState.Error(result.message)
                        is Resource.Loading -> BudgetUiState.Loading
                    }
                }
        }
    }
}