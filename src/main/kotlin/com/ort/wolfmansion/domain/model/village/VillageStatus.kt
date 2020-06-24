package com.ort.wolfmansion.domain.model.village

import com.ort.dbflute.allcommon.CDef

data class VillageStatus(
    val code: String,
    val name: String
) {
    constructor(cdef: CDef.VillageStatus) : this(
        code = cdef.code(),
        name = cdef.alias()
    )

    fun toCdef(): CDef.VillageStatus = CDef.VillageStatus.codeOf(this.code)

    fun isPrologue(): Boolean = listOf(CDef.VillageStatus.募集中, CDef.VillageStatus.開始待ち).any { it == this.toCdef() }

    fun isProgress(): Boolean = this.toCdef() == CDef.VillageStatus.進行中

    fun isEpilogue(): Boolean = this.toCdef() == CDef.VillageStatus.エピローグ

    fun isSolved(): Boolean = listOf(
        CDef.VillageStatus.終了,
        CDef.VillageStatus.廃村,
        CDef.VillageStatus.エピローグ
    ).any { it == this.toCdef() }

    fun isFinishedVillage(): Boolean = listOf(CDef.VillageStatus.廃村, CDef.VillageStatus.終了).any { it == this.toCdef() }

    companion object {

        fun listOfNotFinished(): List<VillageStatus> {
            return CDef.VillageStatus.listAll().map {
                VillageStatus(it)
            }.filter {
                !it.isFinishedVillage()
            }
        }
    }
}
