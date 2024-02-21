package com.example.myapplication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.SpecificationItems

class SpecificationViewModel : ViewModel() {
    private val _selectedItems = mutableStateMapOf<String, Boolean>()
    private val _selectedPrices = mutableStateMapOf<String, Int>()
    private val _itemQuantities = mutableStateMapOf<String, Int>()
    private val _totalPrice = mutableIntStateOf(0)
    var selectedApartmentOption: SpecificationItems? by mutableStateOf(null)

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

    fun updateApartmentPrice(price: Int) {
        _selectedPrices["apartment"] = price
        updateTotalPrice()
    }

    private fun updateTotalPrice() {
        val apartmentPrice = _selectedPrices["apartment"] ?: 0
        val selectedItemsPrice = _selectedItems
            .filterValues { it }
            .mapNotNull { (itemId, _) ->
                _selectedPrices[itemId]?.times(_itemQuantities[itemId] ?: 1)
            }.sum()

        _totalPrice.intValue = apartmentPrice + selectedItemsPrice
    }


    fun updateItemQuantity(itemId: String, quantity: Int) {
        _itemQuantities[itemId] = quantity
        updateTotalPrice()
    }
}



