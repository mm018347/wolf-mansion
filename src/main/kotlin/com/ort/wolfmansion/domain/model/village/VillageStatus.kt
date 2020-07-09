package com.ort.wolfmansion.domain.model.village

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.camp.Camp
import com.ort.wolfmansion.domain.model.village.settings.PersonCapacity
import java.time.format.DateTimeFormatter

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

    /** ステータスに応じたメッセージ */
    fun createStatusMessage(
        capacity: PersonCapacity,
        latestDay: VillageDay,
        winCamp: Camp?,
        isParticipating: Boolean
    ): String {
        val datetime = latestDay.dayChangeDatetime.format(DateTimeFormatter.ofPattern("MM/dd HH:mm"))
        return when {
            isPrologue() -> createPrologueMessage(isParticipating, datetime, capacity)
            isProgress() -> createProgressMessage(latestDay, datetime)
            isEpilogue() -> createEpilogueMessage(winCamp, datetime)
            this.toCdef() == CDef.VillageStatus.終了 -> "終了しました。"
            this.toCdef() == CDef.VillageStatus.廃村 -> "この村は廃村となりました。"
            else -> throw IllegalStateException("unknown status.")
        }
    }

    companion object {

        fun listOfNotFinished(): List<VillageStatus> {
            return CDef.VillageStatus.listAll().map {
                VillageStatus(it)
            }.filter {
                !it.isFinishedVillage()
            }
        }
    }

    private fun createPrologueMessage(
        isParticipating: Boolean,
        datetime: String?,
        capacity: PersonCapacity
    ): String {
        return if (isParticipating) "$datetime に${capacity.min}名以上がエントリーしていれば進行します。\n" +
            "最大${capacity.max}名まで参加可能です。\n\n" +
            "プロローグ中は24時間アクセスなしで自動退村となるため、定期的にアクセスしてください。"
        else "演じたいキャラクターを選び、発言してください。\n" +
            "$datetime に${capacity.min}名以上がエントリーしていれば進行します。\n" +
            "最大${capacity.max}名まで参加可能です。"
    }

    private fun createProgressMessage(latestDay: VillageDay, datetime: String?): String {
        return if (latestDay.day == 1) "特殊な能力を持つ人は、$datetime までに行動を確定して下さい。"
        else "$datetime までに、誰を処刑するべきかの投票先を決定して下さい。\n" +
            "一番票を集めた人物が処刑されます。同数だった場合はランダムで決定されます。\n\n" +
            "特殊な能力を持つ人は、$datetime までに行動を確定して下さい。"
    }

    private fun createEpilogueMessage(winCamp: Camp?, datetime: String?): String {
        return "${winCamp!!.name}の勝利です！\n" +
            "全てのログとユーザー名を公開します。\n\n" +
            "$datetime まで自由に書き込めますので、今回の感想などをどうぞ。"
    }
}
