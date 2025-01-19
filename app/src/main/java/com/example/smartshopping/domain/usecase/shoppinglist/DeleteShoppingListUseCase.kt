class DeleteShoppingListUseCase @Inject constructor(
    private val repository: ShoppingListRepository
) {
    suspend operator fun invoke(listId: String) {
        repository.deleteShoppingList(listId)
    }
}