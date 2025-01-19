@RunWith(AndroidJUnit4::class)
class ProductRepositoryImplTest {
    private lateinit var database: AppDatabase
    private lateinit var productDao: ProductDao
    private lateinit var repository: ProductRepositoryImpl
    
    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        productDao = database.productDao()
        repository = ProductRepositoryImpl(productDao, mockApiService)
    }
    
    @Test
    fun `insert product and verify data is saved`() = runBlocking {
        // Given
        val product = ProductEntity("1", "Test", null, 99.99, 0, null)
        
        // When
        productDao.insertProduct(product)
        
        // Then
        val savedProduct = productDao.getProductById("1")
        assertNotNull(savedProduct)
        assertEquals(product.name, savedProduct?.name)
    }
}