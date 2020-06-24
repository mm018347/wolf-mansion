package com.ort.wolfmansion.domain.service.daychange

import com.ort.wolfmansion.domain.model.charachip.Charas
import com.ort.wolfmansion.domain.model.commit.Commits
import com.ort.wolfmansion.domain.model.daychange.DayChange
import com.ort.wolfmansion.domain.model.message.Messages
import org.springframework.stereotype.Service

@Service
class DayChangeService(
    private val prologueService: PrologueService,
    private val progressService: ProgressService,
    private val epilogueService: EpilogueService
) {

    fun leaveParticipantIfNeeded(
        daychange: DayChange,
        todayMessages: Messages,
        charas: Charas
    ): DayChange {
        return if (!daychange.village.status.isPrologue()) daychange
        else prologueService.leaveParticipantIfNeeded(daychange, todayMessages, charas)
    }

    // コミットや時間経過で次の日に遷移させる場合は村日付を追加
    fun addDayIfNeeded(
        daychange: DayChange,
        commits: Commits
    ): DayChange {
        val status = daychange.village.status
        return when {
            // プロローグ
            status.isPrologue() -> prologueService.addDayIfNeeded(daychange)
            // 進行中
            status.isProgress() -> progressService.addDayIfNeeded(daychange, commits)
            // エピローグ
            status.isEpilogue() -> epilogueService.addDayIfNeeded(daychange)
            // 終了後
            else -> daychange
        }
    }

    // 日付変更処理
    fun process(
        daychange: DayChange,
        todayMessages: Messages,
        charas: Charas
    ): DayChange {
        val status = daychange.village.status
        return when {
            // プロローグ
            status.isPrologue() -> prologueService.dayChange(daychange, charas)
            // 進行中
            status.isProgress() -> progressService.dayChange(daychange, todayMessages, charas)
            // エピローグ
            status.isEpilogue() -> epilogueService.dayChange(daychange)
            // 終了後
            else -> daychange
        }
    }
}