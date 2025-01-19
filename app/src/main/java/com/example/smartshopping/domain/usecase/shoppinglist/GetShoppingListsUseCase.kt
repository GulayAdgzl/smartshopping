class GetShoppingListsUseCase @Inject constructor(
    private val repository: ShoppingListRepository
) {
    operator fun invoke(): Flow<List<ShoppingList>> {
        return repository.getShoppingLists()
    }
}