package com.ort.wolfmansion.domain.service.daychange

import com.ort.wolfmansion.domain.model.charachip.Charas
import com.ort.wolfmansion.domain.model.daychange.DayChange
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.village.Village
import org.springframework.stereotype.Service

@Service
class MiserableDeathService {

    fun process(dayChange: DayChange): DayChange {
        val latestDay = dayChange.village.days.latestDay()

        val miserableDeathCharaList = dayChange.village.participant.list.filter {
            !it.isAlive() && it.dead?.villageDay?.day == latestDay.day && it.dead.toCdef().isMiserableDeath
        }.map { it.chara }

        return dayChange.copy(
            messages = dayChange.messages.add(createMiserableDeathMessage(dayChange.village, Charas(miserableDeathCharaList)))
        ).setIsChange(dayChange)
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    /**
     * 無惨メッセージ
     * @param village village
     * @param charas 犠牲者キャラ
     * @return 無惨メッセージ
     */
    private fun createMiserableDeathMessage(
        village: Village,
        charas: Charas
    ): Message {
        val text = if (charas.list.isEmpty()) {
            "今日は犠牲者がいないようだ。人狼は襲撃に失敗したのだろうか。"
        } else {
            charas.list.shuffled().joinToString(
                prefix = "次の日の朝、以下の村人が無惨な姿で発見された。\n",
                separator = "、\n"
            ) { it.name.fullName() }
        }
        return Message.createPublicSystemMessage(text, village.days.latestDay().day)
    }
}