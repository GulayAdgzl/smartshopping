@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val getShoppingListsUseCase: GetShoppingListsUseCase,
    private val createShoppingListUseCase: CreateShoppingListUseCase,
    private val deleteShoppingListUseCase: DeleteShoppingListUseCase,
    private val analyticsManager: AnalyticsManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<ShoppingListUiState>(ShoppingListUiState.Loading)
    val uiState: StateFlow<ShoppingListUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<ShoppingListEvent>()
    val events = _events.asSharedFlow()

    init {
        loadShoppingLists()
    }

    fun loadShoppingLists() {
        viewModelScope.launch {
            _uiState.value = ShoppingListUiState.Loading
            try {
                getShoppingListsUseCase().collect { lists ->
                    _uiState.value = ShoppingListUiState.Success(lists)
                }
            } catch (e: Exception) {
                _uiState.value = ShoppingListUiState.Error(e.message ?: "Bir hata oluştu")
            }
        }
    }

    fun createList(name: String, budget: Double) {
        viewModelScope.launch {
            try {
                val newList = createShoppingListUseCase(
                    name = name,
                    budget = budget
                )
                analyticsManager.logEvent("shopping_list_created")
                _events.emit(ShoppingListEvent.ListCreated)
                loadShoppingLists()
            } catch (e: Exception) {
                _events.emit(ShoppingListEvent.Error("Liste oluşturulamadı"))
            }
        }
    }

    fun deleteList(listId: String) {
        viewModelScope.launch {
            try {
                deleteShoppingListUseCase(listId)
                analyticsManager.logEvent("shopping_list_deleted")
                _events.emit(ShoppingListEvent.ListDeleted)
                loadShoppingLists()
            } catch (e: Exception) {
                _events.emit(ShoppingListEvent.Error("Liste silinemedi"))
            }
        }
    }

    fun onListClick(listId: String) {
        viewModelScope.launch {
            _events.emit(ShoppingListEvent.NavigateToList(listId))
        }
    }
}