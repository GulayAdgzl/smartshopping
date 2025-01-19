@Dao
interface ProductDao {
    @Query("SELECT * FROM products WHERE is_tracked = 1")
    fun getTrackedProductsFlow(): Flow<List<ProductEntity>>

    @Transaction
    @Query("SELECT * FROM products WHERE id IN (:productIds)")
    suspend fun getProductsWithPriceHistory(productIds: List<String>): List<ProductWithPriceHistory>

    @Query("DELETE FROM price_history WHERE timestamp < :timestamp")
    suspend fun deletePriceHistoryOlderThan(timestamp: Long)
}