package com.tkdev.salsospontanapp.ui.workshops.filter

data class FilterElement(
    val uid: Long,
    val name: String,
    val isSelected: Boolean = false,
    val filterType: FilterType
)

// filter ideas - COUPLE/SOLO, WORKSHOP TAG??, LIVEMUSIC, LEVEL

sealed class FilterType {
    data object DANCER : FilterType()
}
