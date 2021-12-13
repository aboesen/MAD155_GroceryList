package com.example.grocerylist

import java.io.Serializable

data class Model(
    val store_name: String,
    val item_full_name: String,
    val quantity: String,
    val qDescription: String
) : Serializable


