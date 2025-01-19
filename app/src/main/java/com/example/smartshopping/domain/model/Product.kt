data class Product(
    val id: String,
    val name: String,
    val description: String?,
    val price: Double,
    val imageUrl: String?,
    val isTracked: Boolean,
    val priceHistory: List<PriceHistory> = emptyList()
)