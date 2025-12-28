package com.example.pantry.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
class Product (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val barcode: String?,
    val expirationDate: Long
)