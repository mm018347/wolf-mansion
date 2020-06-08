package com.ort.wolfmansion.domain.model.charachip

data class Charachip(
    val id: Int,
    val name: String,
    val designer: Designer,
    val descriptionUrl: String,
    val charas: Charas
) {
}