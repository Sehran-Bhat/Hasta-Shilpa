package com.example.hastashilpa.data.model

object DummyData {
    val products = listOf(
        Product(
            id = "1",
            title = "Bamboo Lounge Chair",
            description = "A comfortable and eco-friendly lounge chair made entirely from treated bamboo.",
            category = Category.FURNITURE,
            imageUrl = com.example.hastashilpa.R.drawable.bamboo_chair,
            blueprintUrl = com.example.hastashilpa.R.drawable.blueprint_sketch,
            materialCost = 1500.0,
            laborCost = 2000.0,
            profitMargin = 30.0,
            artisanName = "Rahul Sharma",
            materialsUsed = listOf("10 Bamboo Poles", "Coir Rope", "Wood Varnish"),
            estimatedDimensions = "80cm x 60cm x 90cm",
            estimatedHours = 12
        ),
        Product(
            id = "2",
            title = "Cane Storage Basket",
            description = "Handwoven cane basket perfect for laundry or storage.",
            category = Category.STORAGE,
            imageUrl = com.example.hastashilpa.R.drawable.cane_basket,
            blueprintUrl = com.example.hastashilpa.R.drawable.cane_basket_blueprint,
            materialCost = 300.0,
            laborCost = 500.0,
            profitMargin = 25.0,
            artisanName = "Anita Devi",
            materialsUsed = listOf("Cane strips", "Binding wire"),
            estimatedDimensions = "40cm x 40cm x 50cm",
            estimatedHours = 5
        ),
        Product(
            id = "3",
            title = "Bamboo Pendant Light",
            description = "Elegant hanging lamp shade crafted from fine bamboo strips.",
            category = Category.LIGHTING,
            imageUrl = com.example.hastashilpa.R.drawable.bamboo_light,
            blueprintUrl = com.example.hastashilpa.R.drawable.bamboo_light_blueprint,
            materialCost = 400.0,
            laborCost = 800.0,
            profitMargin = 40.0,
            artisanName = "Vikram Singh",
            materialsUsed = listOf("Bamboo strips", "Bulb holder", "Wire"),
            estimatedDimensions = "30cm diameter, 40cm height",
            estimatedHours = 6
        ),
        Product(
            id = "4",
            title = "Wall Decor Mirror",
            description = "Mirror framed with intricate bamboo weave.",
            category = Category.HOME_DECOR,
            imageUrl = com.example.hastashilpa.R.drawable.bamboo_mirror,
            blueprintUrl = com.example.hastashilpa.R.drawable.bamboo_mirror_blueprint,
            materialCost = 600.0,
            laborCost = 1000.0,
            profitMargin = 35.0,
            artisanName = "Meena Kumari",
            materialsUsed = listOf("Mirror glass", "Bamboo pieces", "Glue"),
            estimatedDimensions = "60cm diameter",
            estimatedHours = 8
        ),
        Product(
            id = "5",
            title = "Bamboo Dining Table",
            description = "An elegant bamboo dining table with modern earthy tones.",
            category = Category.FURNITURE,
            imageUrl = com.example.hastashilpa.R.drawable.bamboo_table,
            blueprintUrl = com.example.hastashilpa.R.drawable.bamboo_table_blueprint,
            materialCost = 2500.0,
            laborCost = 3000.0,
            profitMargin = 35.0,
            artisanName = "Suresh Kumar",
            materialsUsed = listOf("Large Bamboo Poles", "Wood Varnish", "Glass Top"),
            estimatedDimensions = "150cm x 90cm x 75cm",
            estimatedHours = 18
        ),
        Product(
            id = "6",
            title = "Cane Sofa",
            description = "Comfortable modern cane sofa with earthy cushions.",
            category = Category.FURNITURE,
            imageUrl = com.example.hastashilpa.R.drawable.cane_sofa,
            blueprintUrl = com.example.hastashilpa.R.drawable.cane_sofa_blueprint,
            materialCost = 4000.0,
            laborCost = 5000.0,
            profitMargin = 40.0,
            artisanName = "Anjali Desai",
            materialsUsed = listOf("Thick Cane", "Cushions", "Upholstery Fabric", "Varnish"),
            estimatedDimensions = "180cm x 80cm x 85cm",
            estimatedHours = 24
        )
    )
}
