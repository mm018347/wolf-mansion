package com.ort.wolfmansion.api.view.response

import com.ort.wolfmansion.api.view.domain.model.message.MessageView
import com.ort.wolfmansion.domain.model.commit.Commits
import com.ort.wolfmansion.domain.model.message.Messages
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.vote.VillageVotes
import java.time.format.DateTimeFormatter

data class VillageMessageListResponse(
    val messageList: List<MessageView>,
    val villageStatusMessage: String?,
    val commitStatusMessage: String?,
    val suddenlyDeathMessage: String?,
    val latestDay: Int,
    val allPageCount: Int?,
    val existPrePage: Boolean?,
    val existNextPage: Boolean?,
    val currentPageNum: Int?,
    val pageNumList: List<Int>?,
    val latestMessageDatetime: String
) {
    constructor(
        village: Village,
        messages: Messages,
        commits: Commits,
        votes: VillageVotes,
        isLatestDay: Boolean,
        isParticipating: Boolean
    ) : this(
        messageList = messages.list.map {
            MessageView(
                village = village,
                message = it
            )
        },
        villageStatusMessage = if (isLatestDay) village.createStatusMessage(isParticipating) else null,
        commitStatusMessage = if (isLatestDay) village.createCommitMessage(commits) else null,
        suddenlyDeathMessage = if (isLatestDay) village.createSuddenlyDeathMessage(votes) else null,
        latestDay = village.days.latestDay().day,
        allPageCount = messages.allPageCount,
        existPrePage = messages.isExistPrePage,
        existNextPage = messages.isExistNextPage,
        currentPageNum = messages.currentPageNum,
        pageNumList = messages.pageNumList(),
        latestMessageDatetime = messages.list.lastOrNull()?.time?.datetime?.format(
            DateTimeFormatter.ofPattern("uuuuMMddHHmmss")
        ) ?: "0"
    )
}
