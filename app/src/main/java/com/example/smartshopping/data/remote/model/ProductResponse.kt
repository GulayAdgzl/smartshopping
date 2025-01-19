data class ProductResponse(
    val id: String,
    val name: String,
    val description: String?,
    val price: Double,
    val imageUrl: String?,
    val category: String,
    val lastUpdated: Long
)