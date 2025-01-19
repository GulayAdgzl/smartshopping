interface ShoppingListRepository {
    fun getShoppingLists(): Flow<List<ShoppingList>>
    suspend fun createShoppingList(shoppingList: ShoppingList): ShoppingList
    suspend fun deleteShoppingList(listId: String)
    suspend fun getShoppingList(listId: String): ShoppingList?
    suspend fun updateShoppingList(shoppingList: ShoppingList)
}