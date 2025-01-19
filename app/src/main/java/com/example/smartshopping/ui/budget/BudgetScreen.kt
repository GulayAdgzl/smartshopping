@Composable
fun BudgetScreen(
    viewModel: BudgetViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Üst Başlık ve Toplam Bütçe
        BudgetHeader()
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Ana içerik
        when (val state = uiState) {
            is BudgetUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            
            is BudgetUiState.Success -> {
                BudgetContent(
                    budgetData = state.budgetData,
                    onCategoryClick = viewModel::onCategoryClick
                )
            }
            
            is BudgetUiState.Error -> {
                ErrorMessage(
                    message = state.message,
                    onRetry = viewModel::loadBudgetAnalysis
                )
            }
        }
    }
}

@Composable
private fun BudgetHeader(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Bütçe Yönetimi",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            TotalBudgetSummary()
        }
    }
}

@Composable
private fun TotalBudgetSummary() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Toplam Bütçe",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "₺5,000",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        LinearProgressIndicator(
            progress = 0.7f,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Harcanan: ₺3,500")
            Text("Kalan: ₺1,500")
        }
    }
}

@Composable
private fun BudgetContent(
    budgetData: BudgetData,
    onCategoryClick: (String) -> Unit
) {
    LazyColumn {
        item {
            Text(
                text = "Kategori Bazlı Bütçeler",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
        
        items(budgetData.categories) { category ->
            CategoryBudgetCard(
                category = category,
                onClick = { onCategoryClick(category.id) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        
        item {
            Spacer(modifier = Modifier.height(16.dp))
            BudgetSummaryChart(budgetData = budgetData)
        }
    }
}

@Composable
private fun CategoryBudgetCard(
    category: CategoryBudget,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${category.spent}₺ / ${category.limit}₺",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            LinearProgressIndicator(
                progress = category.spent / category.limit,
                modifier = Modifier.fillMaxWidth()
            )
            
            if (category.spent > category.limit) {
                Text(
                    text = "Bütçe aşımı: ${category.spent - category.limit}₺",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun BudgetSummaryChart(
    budgetData: BudgetData
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Aylık Bütçe Özeti",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Pie chart veya bar chart eklenebilir
        }
    }
}

@Composable
private fun ErrorMessage(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )
        Button(
            onClick = onRetry,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Tekrar Dene")
        }
    }
}