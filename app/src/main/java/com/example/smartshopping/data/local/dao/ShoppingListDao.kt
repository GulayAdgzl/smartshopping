@Dao
interface ShoppingListDao {
    @Query("SELECT * FROM shopping_lists")
    suspend fun getAllShoppingLists(): List<ShoppingListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingList(shoppingList: ShoppingListEntity)

    @Delete
    suspend fun deleteShoppingList(shoppingList: ShoppingListEntity)
}