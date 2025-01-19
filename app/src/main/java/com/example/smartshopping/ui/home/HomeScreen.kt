@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is HomeUiState.Loading -> LoadingScreen()
        is HomeUiState.Success -> HomeContent(
            products = state.trackedProducts,
            onProductClick = viewModel::onProductClick
        )
        is HomeUiState.Error -> ErrorScreen(message = state.message)
    }
}

@Composable
private fun HomeContent(
    products: List<Product>,
    onProductClick: (String) -> Unit
) {
    LazyColumn {
        items(products) { product ->
            ProductCard(
                product = product,
                onClick = { onProductClick(product.id) }
            )
        }
    }
}