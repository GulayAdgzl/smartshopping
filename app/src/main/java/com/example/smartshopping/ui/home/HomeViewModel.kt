@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTrackedProductsUseCase: GetTrackedProductsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            getTrackedProductsUseCase()
                .collect { result ->
                    _uiState.value = when (result) {
                        is Resource.Success -> HomeUiState.Success(result.data)
                        is Resource.Error -> HomeUiState.Error(result.message)
                        is Resource.Loading -> HomeUiState.Loading
                    }
                }
        }
    }
}