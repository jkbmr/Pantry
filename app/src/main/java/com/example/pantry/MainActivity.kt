package com.example.pantry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pantry.data.ProductRepository
import com.example.pantry.data.local.AppDatabase
import com.example.pantry.ui.screens.ProductAddScreen
import com.example.pantry.ui.screens.ProductListScreen
import com.example.pantry.ui.viewmodel.ProductViewModel
import com.example.pantry.ui.viewmodel.ProductViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)
        val repository = ProductRepository(database.productDao())

        val viewModelFactory = ProductViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[ProductViewModel::class.java]

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "product_list") {
                composable("product_list") {
                    ProductListScreen(
                        viewModel = viewModel,
                        onNavigateToAdd = { navController.navigate("add_product" )}
                    )
                }
                composable("add_product") {
                    ProductAddScreen(
                        viewModel = viewModel,
                        onNavigateBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}