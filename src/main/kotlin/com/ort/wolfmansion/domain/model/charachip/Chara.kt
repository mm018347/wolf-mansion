package com.ort.wolfmansion.domain.model.charachip

data class Chara(
    val id: Int,
    val name: CharaName,
    val charachipId: Int,
    val defaultMessage: CharaDefaultMessage,
    val display: CharaSize,
    val faces: CharaFaces
)