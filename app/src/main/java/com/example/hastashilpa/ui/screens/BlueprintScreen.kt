package com.example.hastashilpa.ui.screens

import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.compose.ui.res.stringResource
import com.example.hastashilpa.viewmodel.HastaShilpaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlueprintScreen(navController: NavController, viewModel: HastaShilpaViewModel, productId: String) {
    val product = viewModel.getProductById(productId)
    
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    if (product == null) {
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(com.example.hastashilpa.R.string.blueprint) + ": ${product.title}") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            scale = (scale * zoom).coerceIn(1f, 3f)
                            offsetX += pan.x * scale
                            offsetY += pan.y * scale
                        }
                    }
            ) {
                AsyncImage(
                    model = product.blueprintUrl,
                    contentDescription = "Blueprint",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            translationX = offsetX,
                            translationY = offsetY
                        )
                )
            }
            
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(stringResource(com.example.hastashilpa.R.string.measurements), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(stringResource(com.example.hastashilpa.R.string.dimensions) + " ${product.estimatedDimensions}")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(stringResource(com.example.hastashilpa.R.string.required_materials))
                    product.materialsUsed.forEach {
                        Text("- $it", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
