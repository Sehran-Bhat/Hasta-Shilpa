package com.example.hastashilpa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hastashilpa.viewmodel.HastaShilpaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceSuggestionScreen(navController: NavController, viewModel: HastaShilpaViewModel) {
    val materialCost by viewModel.calcMaterialCost.collectAsState()
    val laborCost by viewModel.calcLaborCost.collectAsState()
    val profitMargin by viewModel.calcProfitMargin.collectAsState()
    val suggestedPrice by viewModel.suggestedPrice.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Price Calculator") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Calculate Selling Price",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = materialCost,
                onValueChange = { viewModel.updatePriceCalculator(it, laborCost, profitMargin) },
                label = { Text("Total Material Cost (₹)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = laborCost,
                onValueChange = { viewModel.updatePriceCalculator(materialCost, it, profitMargin) },
                label = { Text("Total Labor Cost (₹)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = profitMargin,
                onValueChange = { viewModel.updatePriceCalculator(materialCost, laborCost, it) },
                label = { Text("Profit Margin (%)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Suggested Selling Price",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "₹${String.format("%.2f", suggestedPrice)}",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
