package com.example.myapplication.data

data class Specification(
    val _id: String,
    val isAssociated: Boolean,
    val isParentAssociate: Boolean,
    val is_required: Boolean,
    val list: List<SpecificationItems>,
    val max_range: Int,
    val modifierGroupId: String,
    val modifierGroupName: String,
    val modifierId: String,
    val modifierName: String,
    val name: List<String>,
    val range: Int,
    val sequence_number: Int,
    val type: Int,
    val unique_id: Int,
    val user_can_add_specification_quantity: Boolean
)