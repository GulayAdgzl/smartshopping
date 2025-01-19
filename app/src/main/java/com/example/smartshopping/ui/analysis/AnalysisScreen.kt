@Composable
fun AnalysisScreen(
    viewModel: AnalysisViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Alışveriş Analizi",
            style = MaterialTheme.typography.headlineMedium
        )

        when (val state = uiState) {
            is AnalysisUiState.Success -> {
                SpendingChart(data = state.spendingData)
                CategoryBreakdown(categories = state.categoryData)
                SavingsSuggestions(suggestions = state.suggestions)
            }
            is AnalysisUiState.Loading -> CircularProgressIndicator()
            is AnalysisUiState.Error -> ErrorMessage(state.message)
        }
    }
}