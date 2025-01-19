class GetProductsUseCaseTest {
    @Mock
    private lateinit var productRepository: ProductRepository
    
    private lateinit var getProductsUseCase: GetProductsUseCase
    
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getProductsUseCase = GetProductsUseCase(productRepository)
    }
    
    @Test
    fun `when repository returns success, usecase should emit success state`() = runTest {
        // Given
        val products = listOf(
            Product("1", "Test Product", "Description", 99.99, null, false)
        )
        whenever(productRepository.getProducts())
            .thenReturn(flowOf(products))
            
        // When
        val result = getProductsUseCase().first()
        
        // Then
        assertTrue(result is Resource.Success)
        assertEquals(products, (result as Resource.Success).data)
    }
}