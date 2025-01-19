@Composable
fun ShoppingListScreen(
    viewModel: ShoppingListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showCreateDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showCreateDialog = true }
            ) {
                Icon(Icons.Default.Add, "Liste Oluştur")
            }
        }
    ) { padding ->
        when (val state = uiState) {
            is ShoppingListUiState.Loading -> LoadingScreen()
            is ShoppingListUiState.Success -> ShoppingListContent(
                lists = state.shoppingLists,
                onListClick = viewModel::onListClick
            )
            is ShoppingListUiState.Error -> ErrorScreen(message = state.message)
        }

        if (showCreateDialog) {
            CreateShoppingListDialog(
                onDismiss = { showCreateDialog = false },
                onCreate = viewModel::createList
            )
        }
    }
}