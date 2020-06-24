package com.ort.wolfmansion.infrastructure.datasource.village

import com.ort.dbflute.exentity.MessageRestriction
import com.ort.dbflute.exentity.Village
import com.ort.dbflute.exentity.VillageDay
import com.ort.dbflute.exentity.VillagePlayer
import com.ort.dbflute.exentity.VillageSettings
import com.ort.wolfmansion.domain.model.dead.Dead
import com.ort.wolfmansion.domain.model.message.MessageType
import com.ort.wolfmansion.domain.model.skill.Skill
import com.ort.wolfmansion.domain.model.skill.SkillRequest
import com.ort.wolfmansion.domain.model.village.VillageDays
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipants
import com.ort.wolfmansion.domain.model.village.VillageStatus
import com.ort.wolfmansion.domain.model.village.Villages
import com.ort.wolfmansion.domain.model.village.settings.PersonCapacity
import com.ort.wolfmansion.domain.model.village.settings.VillageCharachip
import com.ort.wolfmansion.domain.model.village.settings.VillageMessageRestrict
import com.ort.wolfmansion.domain.model.village.settings.VillageMessageRestricts
import com.ort.wolfmansion.domain.model.village.settings.VillageOrganizations
import com.ort.wolfmansion.domain.model.village.settings.VillagePassword
import com.ort.wolfmansion.domain.model.village.settings.VillageRules
import com.ort.wolfmansion.domain.model.village.settings.VillageTime
import org.dbflute.cbean.result.ListResultBean
import java.util.*

object VillageDataConverter {

    fun convertVillageListToVillages(villageList: ListResultBean<Village>): Villages {
        return Villages(list = villageList.map { convertVillageToSimpleVillage(it) })
    }

    fun convertVillage(village: Village): com.ort.wolfmansion.domain.model.village.Village {
        val participantList = village.villagePlayerList.filter { it.isParticipant }
        val spectatorList = village.villagePlayerList.filter { it.isVisitor }
        val villageDays = VillageDays(village.villageDayList.map { convertToVillageDay(it) })
        return com.ort.wolfmansion.domain.model.village.Village(
            id = village.villageId,
            name = village.villageDisplayName,
            creatorPlayerName = village.createPlayerName,
            status = VillageStatus(village.villageStatusCodeAsVillageStatus),
            setting = convertToVillageSetting(village.villageSettingsAsOne.get(), village.messageRestrictionList),
            participant = VillageParticipants(
                count = participantList.size,
                list = participantList.map { convertToVillageParticipant(it, villageDays) }
            ),
            spectator = VillageParticipants(
                count = spectatorList.size,
                list = spectatorList.map { convertToVillageParticipant(it, villageDays) }
            ),
            days = villageDays,
            winCamp = village.winCampCodeAsCamp?.let { com.ort.wolfmansion.domain.model.camp.Camp(it) }
        )
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun convertVillageToSimpleVillage(
        village: Village
    ): com.ort.wolfmansion.domain.model.village.Village {
        return com.ort.wolfmansion.domain.model.village.Village(
            id = village.villageId,
            name = village.villageDisplayName,
            creatorPlayerName = village.createPlayerName,
            status = VillageStatus(village.villageStatusCodeAsVillageStatus),
            setting = convertToVillageSetting(village.villageSettingsAsOne.get(), village.messageRestrictionList),
            participant = VillageParticipants(count = village.participantCount),
            spectator = VillageParticipants(count = village.visitorCount),
            days = VillageDays( // 最新の1日だけ
                list = village.villageDayList.firstOrNull()?.let {
                    listOf(convertToVillageDay(it))
                }.orEmpty()
            ),
            winCamp = village.winCampCodeAsCamp?.let { com.ort.wolfmansion.domain.model.camp.Camp(it) }
        )
    }

    private fun convertToVillageParticipant(
        vp: VillagePlayer,
        villageDays: VillageDays
    ): VillageParticipant {
        return VillageParticipant(
            id = vp.villagePlayerId,
            charaId = vp.charaId,
            playerId = vp.playerId,
            roomNo = vp.roomNumber,
            dead = if (vp.isDead) convertToDead(vp, villageDays) else null,
            isSpectator = vp.isSpectator,
            isGone = vp.isGone,
            skill = Optional.ofNullable(vp.skillCodeAsSkill).map { Skill(it) }.orElse(null),
            skillRequest = SkillRequest(vp.requestSkillCodeAsSkill, vp.secondRequestSkillCodeAsSkill),
            isWin = vp.isWin
        )
    }

    private fun convertToDead(vp: VillagePlayer, villageDays: VillageDays): Dead {
        return Dead(
            code = vp.deadReasonCode,
            reason = vp.deadReasonCodeAsDeadReason.alias(),
            villageDay = villageDays.day(vp.deadDay)
        )
    }

    private fun convertToVillageDay(villageDay: VillageDay): com.ort.wolfmansion.domain.model.village.VillageDay {
        return com.ort.wolfmansion.domain.model.village.VillageDay(
            day = villageDay.day,
            dayChangeDatetime = villageDay.daychangeDatetime
        )
    }

    private fun convertToVillageSetting(
        settings: VillageSettings,
        messageRestrictionList: List<MessageRestriction>
    ): com.ort.wolfmansion.domain.model.village.VillageSettings {
        return com.ort.wolfmansion.domain.model.village.VillageSettings(
            capacity = PersonCapacity(
                min = settings.startPersonMinNum,
                max = settings.personMaxNum
            ),
            time = VillageTime(
                startDatetime = settings.startDatetime,
                dayChangeIntervalSeconds = settings.dayChangeIntervalSeconds
            ),
            charachip = VillageCharachip(
                dummyCharaId = settings.dummyCharaId,
                charachipId = settings.characterGroupId
            ),
            organizations = VillageOrganizations(
                organization = settings.organize.replace("\r\n", "\n").split("\n").map { it.length to it }.toMap()
            ),
            rules = VillageRules(
                openVote = settings.isOpenVote,
                availableSkillRequest = settings.isPossibleSkillRequest,
                availableSpectate = settings.isAvailableSpectate,
                availableSameWolfAttack = settings.isAvailableSameWolfAttack,
                openSkillInGrave = settings.isOpenSkillInGrave,
                visibleGraveMessage = settings.isVisibleGraveSpectateMessage,
                availableSuddenlyDeath = settings.isAvailableSuddonlyDeath,
                availableCommit = settings.isAvailableCommit,
                availableGuardSameTarget = settings.isAvailableGuardSameTarget,
                messageRestrict = VillageMessageRestricts(
                    list = messageRestrictionList.map {
                        VillageMessageRestrict(
                            skill = Skill(it.skillCodeAsSkill),
                            messageType = MessageType(it.messageTypeCodeAsMessageType),
                            count = it.messageMaxNum,
                            length = it.messageMaxLength
                        )
                    }
                )
            ),
            password = VillagePassword(
                joinPassword = settings.joinPassword
            )
        )
    }
}