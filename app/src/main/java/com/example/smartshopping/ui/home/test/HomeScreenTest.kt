@HiltAndroidTest
class HomeScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    
    @Test
    fun homeScreen_displaysProducts() {
        // Given
        val products = listOf(
            Product("1", "Test Product", null, 99.99, null, false)
        )
        
        // When
        composeTestRule.setContent { 
            HomeScreen(products = products)
        }
        
        // Then
        composeTestRule
            .onNodeWithText("Test Product")
            .assertIsDisplayed()
    }
}