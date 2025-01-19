class CreateShoppingListUseCase @Inject constructor(
    private val repository: ShoppingListRepository
) {
    suspend operator fun invoke(name: String, budget: Double): ShoppingList {
        require(name.isNotBlank()) { "Liste adı boş olamaz" }
        require(budget > 0) { "Bütçe 0'dan büyük olmalıdır" }

        return repository.createShoppingList(
            ShoppingList(
                id = UUID.randomUUID().toString(),
                name = name,
                itemCount = 0,
                budget = budget,
                createdAt = System.currentTimeMillis()
            )
        )
    }
}