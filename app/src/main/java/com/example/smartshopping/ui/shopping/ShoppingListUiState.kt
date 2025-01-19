sealed class ShoppingListUiState {
    object Loading : ShoppingListUiState()
    data class Success(val shoppingLists: List<ShoppingList>) : ShoppingListUiState()
    data class Error(val message: String) : ShoppingListUiState()
}

sealed class ShoppingListEvent {
    object ListCreated : ShoppingListEvent()
    object ListDeleted : ShoppingListEvent()
    data class NavigateToList(val listId: String) : ShoppingListEvent()
    data class Error(val message: String) : ShoppingListEvent()
}

