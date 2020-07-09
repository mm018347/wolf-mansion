package com.ort.wolfmansion.api.form

import com.ort.wolfmansion.domain.model.village.settings.PersonCapacity
import com.ort.wolfmansion.domain.model.village.settings.VillageTime
import org.jetbrains.annotations.NotNull
import java.time.DateTimeException
import java.time.LocalDateTime
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class VillageSettingsForm(
    /** 最低開始人数 */
    @field:NotNull
    @Min(PersonCapacity.min_min.toLong())
    val startPersonMinNum: Int? = null,

    /** 定員 */
    @field:NotNull
    @Max(PersonCapacity.max_max.toLong())
    val personNumMax: Int? = null,

    /** 更新間隔h */
    @Min(VillageTime.dayChangeIntervalHours_min.toLong())
    @Max(VillageTime.dayChangeIntervalHours_max.toLong())
    val dayChangeIntervalHours: Int? = null,

    /** 更新間隔m */
    @Min(0)
    @Max(59)
    val dayChangeIntervalMinutes: Int? = null,

    /** 更新間隔秒 */
    @Min(0)
    @Max(59)
    val dayChangeIntervalSeconds: Int? = null,

    /** 開始年 */
    @Min(0)
    val startYear: Int? = null,

    /** 開始月 */
    @Min(1)
    @Max(12)
    val startMonth: Int? = null,

    /** 開始日 */
    @Min(1)
    @Max(31)
    val startDay: Int? = null,

    /** 開始時間 */
    @Min(0)
    @Max(23)
    val startHour: Int? = null,

    /** 開始分 */
    @Min(0)
    @Max(59)
    val startMinute: Int? = null,

    /** 記名投票か */
    @field:NotNull
    val openVote: Boolean? = null,

    /** 連続襲撃ありか */
    @field:NotNull
    val availableSameWolfAttack: Boolean? = null,

    /** 墓下役職公開ありか */
    @field:NotNull
    val openSkillInGrave: Boolean? = null,

    /** 墓下見学発言を地上から見られるか */
    @field:NotNull
    val visibleGraveSpectateMessage: Boolean? = null,

    /** 秘話可能範囲 */
    @field:NotNull
    val allowedSecretSayCode: String,

    /** 見学可能か */
    @field:NotNull
    val availableSpectate: Boolean? = null,

    /** 突然死ありか */
    @field:NotNull
    val availableSuddenlyDeath: Boolean? = null,

    /** コミットありか */
    @field:NotNull
    val availableCommit: Boolean? = null,

    /** 連続護衛ありか */
    @field:NotNull
    val availableGuardSameTraget: Boolean? = null,

    /** 編成 */
    @field:NotNull
    val organization: String? = null,

    /** 入村パスワード */
    val joinPassword: String? = null,

    /** 発言制限 */
    @field:NotNull
    @Valid
    val sayRestrictList: List<VillageSayRestrictForm>? = null
) {

    fun toIntervalSeconds(): Int {
        return dayChangeIntervalHours!! * 3600 + dayChangeIntervalMinutes!! * 60 + dayChangeIntervalSeconds!!
    }

    @Throws(DateTimeException::class)
    fun toStratDatetime(): LocalDateTime {
        return LocalDateTime.of(startYear!!, startMonth!!, startDay!!, startHour!!, startMinute!!)
    }
}
