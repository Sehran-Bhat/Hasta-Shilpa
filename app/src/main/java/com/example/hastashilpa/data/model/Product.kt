package com.example.hastashilpa.data.model

enum class Category {
    FURNITURE, HOME_DECOR, STORAGE, LIGHTING
}

data class Product(
    val id: String,
    val title: String,
    val description: String,
    val category: Category,
    val imageUrl: Int,
    val blueprintUrl: Int,
    val materialCost: Double,
    val laborCost: Double,
    val profitMargin: Double,
    val artisanName: String,
    val materialsUsed: List<String>,
    val estimatedDimensions: String,
    val estimatedHours: Int,
    val price: Double = materialCost + laborCost + (materialCost + laborCost) * (profitMargin / 100)
)
