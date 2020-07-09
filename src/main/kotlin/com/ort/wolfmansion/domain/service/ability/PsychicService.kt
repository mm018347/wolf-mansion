package com.ort.wolfmansion.domain.service.ability

import com.ort.wolfmansion.domain.model.charachip.Chara
import com.ort.wolfmansion.domain.model.daychange.DayChange
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import org.springframework.stereotype.Service

@Service
class PsychicService {

    fun process(dayChange: DayChange): DayChange {
        // 霊能がいない、または処刑・突然死がいない場合は何もしない
        val existsAlivePsychic = dayChange.village.participant.filterAlive().list.any { it.skill!!.toCdef().isHasPsychicAbility }
        if (!existsAlivePsychic) return dayChange
        val todayDeadParticipants = dayChange.village.todayDeadParticipants().list.filter { it.dead!!.toCdef().isPsychicableDeath }
        if (todayDeadParticipants.isEmpty()) return dayChange

        var messages = dayChange.messages.copy()
        todayDeadParticipants.forEach { deadParticipant ->
            messages = messages.add(createPsychicPrivateMessage(dayChange.village, deadParticipant))
        }
        return dayChange.copy(messages = messages).setIsChange(dayChange)
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun createPsychicPrivateMessage(
        village: Village,
        deadParticipant: VillageParticipant
    ): Message {
        val isWolf = village.participant.member(deadParticipant.id).skill!!.toCdef().isPsychicResultWolf
        val text = createPsychicPrivateMessageString(deadParticipant.chara, isWolf)
        return Message.createPsychicPrivateMessage(text, village.days.latestDay().day)
    }

    private fun createPsychicPrivateMessageString(chara: Chara, isWolf: Boolean): String =
        "${chara.name.fullName()}は人狼${if (isWolf) "の" else "ではない"}ようだ。"
}