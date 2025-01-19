class CreateShoppingListUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) {
    suspend operator fun invoke(
        name: String,
        budget: Double
    ): Flow<Resource<ShoppingList>> = flow {
        emit(Resource.Loading())
        try {
            val shoppingList = ShoppingList(
                id = UUID.randomUUID().toString(),
                name = name,
                items = emptyList(),
                totalBudget = budget,
                createdAt = System.currentTimeMillis()
            )
            shoppingListRepository.createShoppingList(shoppingList)
            emit(Resource.Success(shoppingList))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Alışveriş listesi oluşturulamadı"))
        }
    }
}