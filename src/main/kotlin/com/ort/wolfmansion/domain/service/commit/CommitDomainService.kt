package com.ort.wolfmansion.domain.service.commit

import com.ort.wolfmansion.domain.model.charachip.Chara
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.fw.exception.WolfMansionBusinessException
import org.springframework.stereotype.Service

@Service
class CommitDomainService {

    fun isAvailableCommit(
        village: Village,
        participant: VillageParticipant?
    ): Boolean {
        // 村として可能か
        if (!village.isAvailableCommit()) return false
        // 参加者として可能か
        participant ?: return false
        return participant.isAvailableCommit(village.dummyParticipant().id)
    }

    /**
     * コミットチェック
     * @param village village
     * @param participant 参加者
     */
    fun assertCommit(
        village: Village,
        participant: VillageParticipant?
    ) {
        if (!isAvailableCommit(village, participant)) throw WolfMansionBusinessException("コミットできません")
    }

    fun createCommitMessage(
        chara: Chara,
        doCommit: Boolean,
        day: Int
    ): Message = Message.createPrivateSystemMessage(getCommitSetMessage(doCommit, chara), day)

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun getCommitSetMessage(doCommit: Boolean, chara: Chara): String =
        if (doCommit) "${chara.name.name}がコミットしました。" else "${chara.name.name}がコミットを取り消しました。"
}