@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String?,
    val currentPrice: Double,
    val lastUpdated: Long,
    val imageUrl: String?,
    @ColumnInfo(name = "is_tracked")
    val isTracked: Boolean = false
)