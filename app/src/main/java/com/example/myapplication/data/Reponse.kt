package com.example.myapplication.data

data class Reponse(
    val _id: String,
    val item_taxes: List<Int>,
    val name: List<String>,
    val price: Int,
    val specifications: List<Specification>
)