data class ShoppingList(
    val id: String,
    val name: String,
    val items: List<ShoppingItem>,
    val totalBudget: Double,
    val createdAt: Long
)

data class ShoppingItem(
    val productId: String,
    val quantity: Int,
    val isChecked: Boolean = false
)