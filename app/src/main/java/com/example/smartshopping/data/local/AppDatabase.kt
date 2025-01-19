@Database(
    entities = [
        ProductEntity::class,
        ShoppingListEntity::class,
        PriceAlertEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun priceAlertDao(): PriceAlertDao

    companion object {
        const val DATABASE_NAME = "smart_shopping_db"
    }
}