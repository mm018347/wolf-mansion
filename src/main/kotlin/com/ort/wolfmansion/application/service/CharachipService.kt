package com.ort.wolfmansion.application.service

import com.ort.wolfmansion.domain.model.charachip.Chara
import com.ort.wolfmansion.domain.model.charachip.Charachip
import com.ort.wolfmansion.domain.model.charachip.Charachips
import com.ort.wolfmansion.domain.model.charachip.Charas
import com.ort.wolfmansion.infrastructure.datasource.chara.CharaDataSource
import com.ort.wolfmansion.infrastructure.datasource.charachip.CharachipDataSource
import org.springframework.stereotype.Service

@Service
class CharachipService(
    val charachipDataSource: CharachipDataSource,
    val charaDataSource: CharaDataSource
) {

    fun findCharaChips(): Charachips = charachipDataSource.findCharachips()

    fun findCharaChip(charachipId: Int): Charachip = charachipDataSource.findCharachip(charachipId)

    fun findChara(charaId: Int): Chara = charaDataSource.findChara(charaId)

    fun findCharas(charachips: Charachips): Charas = charaDataSource.findCharas(charachips)

    fun findCharas(charachipId: Int): Charas = charaDataSource.findCharas(charachipId)

    fun findCharas(charachipIdList: List<Int>): Charas = charaDataSource.findCharas(charachipIdList)

    fun findDummyChara(charaChipId: Int): Chara = charaDataSource.findDummyChara(charaChipId)
}