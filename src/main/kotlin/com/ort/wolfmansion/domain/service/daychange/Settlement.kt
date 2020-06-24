package com.ort.wolfmansion.domain.service.daychange

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.camp.Camp
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.village.Village

/**
 * 決着
 */
data class Settlement(
    val village: Village
) {

    fun isSettled(): Boolean {
        val wolfCount = wolfCount()
        return wolfCount <= 0 || villagerCount() <= wolfCount
    }

    fun winCamp(): Camp? {
        if (!this.isSettled()) return null
        if (foxCount() > 0) return Camp(CDef.Camp.狐陣営)
        if (wolfCount() > 0) return Camp(CDef.Camp.人狼陣営)
        return Camp(CDef.Camp.村人陣営)
    }

    /**
     * 勝利陣営メッセージ
     */
    fun createWinCampMessage(day: Int): Message =
        Message.createPublicSystemMessage(getWinCampMessage(winCamp()!!), day)

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun getWinCampMessage(camp: Camp): String {
        return when (camp.toCdef()) {
            CDef.Camp.村人陣営 -> "全ての人狼を退治した。人狼に怯える日々は去ったのだ！"
            CDef.Camp.人狼陣営 -> "もう人狼に抵抗できるほど村人は残っていない。\n人狼は残った村人を全て食らい、別の獲物を求めて村を去っていった。"
            CDef.Camp.狐陣営 -> "全ては終わったかのように見えた。\nだが、奴が生き残っていた。"
            else -> ""
        }
    }


    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun villagerCount(): Int =
        this.village.participant.filterAlive().list.count { !it.skill!!.toCdef().isCountWolf && !it.skill.toCdef().isNoCount }

    private fun wolfCount(): Int = this.village.participant.filterAlive().list.count { it.skill!!.toCdef().isCountWolf }

    private fun foxCount(): Int = this.village.participant.filterAlive().list.count { it.skill!!.toCdef().isNoCount }
}