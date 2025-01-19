class DeepLinkHandler @Inject constructor(
    private val navController: NavHostController
) {
    fun handleDeepLink(deepLink: Uri) {
        when (deepLink.host) {
            "product" -> {
                val productId = deepLink.lastPathSegment
                productId?.let {
                    navController.navigate("product/$it")
                }
            }
            "list" -> {
                val listId = deepLink.lastPathSegment
                listId?.let {
                    navController.navigate("shopping_list/$it")
                }
            }
        }
    }
}