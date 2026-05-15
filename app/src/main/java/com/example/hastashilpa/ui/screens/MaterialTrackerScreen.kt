package com.example.hastashilpa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hastashilpa.viewmodel.HastaShilpaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialTrackerScreen(navController: NavController, viewModel: HastaShilpaViewModel) {
    val bamboo by viewModel.bambooCount.collectAsState()
    val rope by viewModel.ropeCount.collectAsState()
    val wood by viewModel.woodCount.collectAsState()
    val hours by viewModel.laborHours.collectAsState()
    val materialCost by viewModel.estimatedMaterialCost.collectAsState()
    val totalCost by viewModel.totalProductionCost.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Material Tracker") })
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
                "Input Materials & Labor",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = bamboo,
                onValueChange = { viewModel.updateMaterialTracker(it, rope, wood, hours) },
                label = { Text("Bamboo Poles") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = rope,
                onValueChange = { viewModel.updateMaterialTracker(bamboo, it, wood, hours) },
                label = { Text("Rope Quantity (bundles)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = wood,
                onValueChange = { viewModel.updateMaterialTracker(bamboo, rope, it, hours) },
                label = { Text("Wood Pieces") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = hours,
                onValueChange = { viewModel.updateMaterialTracker(bamboo, rope, wood, it) },
                label = { Text("Labor Hours") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Cost Estimation", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Material Cost:", color = MaterialTheme.colorScheme.onPrimaryContainer)
                        Text("₹${materialCost}", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Total Estimated Cost:", color = MaterialTheme.colorScheme.onPrimaryContainer)
                        Text("₹${totalCost}", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                    }
                }
            }
        }
    }
}
