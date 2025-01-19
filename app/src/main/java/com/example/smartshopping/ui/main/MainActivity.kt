package com.example.smartshopping

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartShoppingTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Home, "Home") },
                    label = { Text("Ana Sayfa") },
                    selected = true,
                    onClick = { /* Navigation */ }
                )
                // Diğer navigation itemlar
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") { HomeScreen() }
            composable("shopping") { ShoppingListScreen() }
            composable("budget") { BudgetScreen() }
            composable("analysis") { AnalysisScreen() }
        }
    }
}