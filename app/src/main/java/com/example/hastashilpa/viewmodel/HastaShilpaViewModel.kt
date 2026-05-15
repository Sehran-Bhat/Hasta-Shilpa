package com.example.hastashilpa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hastashilpa.data.model.DummyData
import com.example.hastashilpa.data.model.Product
import kotlinx.coroutines.flow.*

class HastaShilpaViewModel : ViewModel() {

    private val _products = MutableStateFlow(DummyData.products)
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val products: StateFlow<List<Product>> = combine(
        _products,
        _searchQuery
    ) { productsList, query ->
        if (query.isBlank()) {
            productsList
        } else {
            productsList.filter { it.title.contains(query, ignoreCase = true) || it.category.name.contains(query, ignoreCase = true) }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _products.value
    )

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun getProductById(id: String): Product? {
        return _products.value.find { it.id == id }
    }

    // Material Tracker State
    private val _bambooCount = MutableStateFlow("")
    val bambooCount: StateFlow<String> = _bambooCount.asStateFlow()

    private val _ropeCount = MutableStateFlow("")
    val ropeCount: StateFlow<String> = _ropeCount.asStateFlow()

    private val _woodCount = MutableStateFlow("")
    val woodCount: StateFlow<String> = _woodCount.asStateFlow()

    private val _laborHours = MutableStateFlow("")
    val laborHours: StateFlow<String> = _laborHours.asStateFlow()

    // Calculated fields
    private val _estimatedMaterialCost = MutableStateFlow(0.0)
    val estimatedMaterialCost: StateFlow<Double> = _estimatedMaterialCost.asStateFlow()

    private val _totalProductionCost = MutableStateFlow(0.0)
    val totalProductionCost: StateFlow<Double> = _totalProductionCost.asStateFlow()

    fun updateMaterialTracker(bamboo: String, rope: String, wood: String, hours: String) {
        _bambooCount.value = bamboo
        _ropeCount.value = rope
        _woodCount.value = wood
        _laborHours.value = hours

        calculateCosts()
    }

    private fun calculateCosts() {
        val bambooCost = (_bambooCount.value.toDoubleOrNull() ?: 0.0) * 150.0 // 150 per pole
        val ropeCost = (_ropeCount.value.toDoubleOrNull() ?: 0.0) * 50.0 // 50 per bundle
        val woodCost = (_woodCount.value.toDoubleOrNull() ?: 0.0) * 200.0 // 200 per piece
        val laborCost = (_laborHours.value.toDoubleOrNull() ?: 0.0) * 100.0 // 100 per hour

        val materialTotal = bambooCost + ropeCost + woodCost
        _estimatedMaterialCost.value = materialTotal
        _totalProductionCost.value = materialTotal + laborCost
    }

    // Price Calculator State
    private val _calcMaterialCost = MutableStateFlow("")
    val calcMaterialCost: StateFlow<String> = _calcMaterialCost.asStateFlow()

    private val _calcLaborCost = MutableStateFlow("")
    val calcLaborCost: StateFlow<String> = _calcLaborCost.asStateFlow()

    private val _calcProfitMargin = MutableStateFlow("20")
    val calcProfitMargin: StateFlow<String> = _calcProfitMargin.asStateFlow()
    
    private val _suggestedPrice = MutableStateFlow(0.0)
    val suggestedPrice: StateFlow<Double> = _suggestedPrice.asStateFlow()

    fun updatePriceCalculator(materialCost: String, laborCost: String, profitMargin: String) {
        _calcMaterialCost.value = materialCost
        _calcLaborCost.value = laborCost
        _calcProfitMargin.value = profitMargin
        
        val matCost = materialCost.toDoubleOrNull() ?: 0.0
        val labCost = laborCost.toDoubleOrNull() ?: 0.0
        val profit = profitMargin.toDoubleOrNull() ?: 0.0
        
        val basePrice = matCost + labCost
        _suggestedPrice.value = basePrice + (basePrice * (profit / 100))
    }
}
