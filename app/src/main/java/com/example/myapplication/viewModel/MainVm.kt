package com.example.myapplication.viewModel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel

class SpecificationViewModel : ViewModel() {
    private val _selectedItems = mutableStateMapOf<String, Boolean>()
    private val _selectedPrices = mutableStateMapOf<String, Int>()
    private val _itemQuantities = mutableStateMapOf<String, Int>()
    private val _totalPrice = mutableIntStateOf(0)

    val selectedItems: Map<String, Boolean> get() = _selectedItems
    val selectedPrices: Map<String, Int> get() = _selectedPrices
    val itemQuantities: Map<String, Int> get() = _itemQuantities
    val totalPrice: Int get() = _totalPrice.intValue

    fun toggleItemSelected(itemId: String, price: Int) {
        val isSelected = _selectedItems[itemId] ?: false
        _selectedItems[itemId] = !isSelected
        _selectedPrices[itemId] = if (!isSelected) price else 0
        updateTotalPrice()
    }

    private fun updateTotalPrice() {
        _totalPrice.intValue = _selectedPrices.filter { entry ->
            _selectedItems[entry.key] == true
        }.map { entry ->
            entry.value * (_itemQuantities[entry.key] ?: 1)
        }.sum()
    }


    fun updateItemQuantity(itemId: String, quantity: Int) {
        _itemQuantities[itemId] = quantity
        updateTotalPrice()
    }
}



