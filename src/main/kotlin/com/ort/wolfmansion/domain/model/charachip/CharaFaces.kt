package com.ort.wolfmansion.domain.model.charachip

data class CharaFaces(
    val list: List<CharaFace>
) {

    fun face(faceCode: String): CharaFace =
        list.firstOrNull {
            it.type == faceCode
        } ?: throw IllegalStateException("have no face. code: $faceCode")
}
