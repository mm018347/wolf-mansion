package com.ort.wolfmansion.infrastructure.datasource.village

import com.ort.dbflute.exentity.MessageRestriction
import com.ort.dbflute.exentity.Player
import com.ort.dbflute.exentity.Village
import com.ort.dbflute.exentity.VillageDay
import com.ort.dbflute.exentity.VillagePlayer
import com.ort.dbflute.exentity.VillageSettings
import com.ort.wolfmansion.domain.model.charachip.CharaDefaultMessage
import com.ort.wolfmansion.domain.model.charachip.CharaFace
import com.ort.wolfmansion.domain.model.charachip.CharaFaces
import com.ort.wolfmansion.domain.model.charachip.CharaName
import com.ort.wolfmansion.domain.model.charachip.CharaSize
import com.ort.wolfmansion.domain.model.dead.Dead
import com.ort.wolfmansion.domain.model.message.MessageType
import com.ort.wolfmansion.domain.model.skill.Skill
import com.ort.wolfmansion.domain.model.skill.SkillRequest
import com.ort.wolfmansion.domain.model.village.VillageDays
import com.ort.wolfmansion.domain.model.village.VillageStatus
import com.ort.wolfmansion.domain.model.village.Villages
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipants
import com.ort.wolfmansion.domain.model.village.room.VillageRoom
import com.ort.wolfmansion.domain.model.village.room.VillageRooms
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
        val villageDays = VillageDays(
            list = village.villageDayList.map { convertToVillageDay(it) },
            epilogueDay = village.epilogueDay
        )
        return com.ort.wolfmansion.domain.model.village.Village(
            id = village.villageId,
            name = village.villageDisplayName,
            creatorPlayerName = village.createPlayerName,
            status = VillageStatus(village.villageStatusCodeAsVillageStatus),
            rooms = convertToVillageRooms(village),
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
            rooms = null,
            setting = convertToVillageSetting(village.villageSettingsAsOne.get(), village.messageRestrictionList),
            participant = VillageParticipants(count = village.participantCount),
            spectator = VillageParticipants(count = village.visitorCount),
            days = VillageDays( // 最新の1日だけ
                list = village.villageDayList.firstOrNull()?.let {
                    listOf(convertToVillageDay(it))
                }.orEmpty(),
                epilogueDay = village.epilogueDay
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
            chara = convertCharaToChara(vp.chara.get()),
            player = convertPlayerToSimplePlayer(vp.player.get()),
            room = vp.roomNumber?.let { VillageRoom(it) },
            dead = if (vp.isDead) convertToDead(vp, villageDays) else null,
            isSpectator = vp.isSpectator,
            isGone = vp.isGone,
            skill = Optional.ofNullable(vp.skillCodeAsSkill).map { Skill(it) }.orElse(null),
            skillRequest = SkillRequest(vp.requestSkillCodeAsSkill, vp.secondRequestSkillCodeAsSkill),
            isWin = vp.isWin,
            lastAccessDatetime = vp.lastAccessDatetime
        )
    }

    private fun convertToVillageRooms(
        village: Village
    ): VillageRooms? {
        if (village.villagePlayerList.none { it.roomNumber != null }) return null

        return VillageRooms(
            list = village.villagePlayerList
                .filter { it.roomNumber != null }
                .map { VillageRoom(no = it.roomNumber) },
            width = village.roomSizeWidth,
            height = village.roomSizeHeight
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

    // ===================================================================================
    //                                                                             Mapping
    //                                                                             =======
    private fun convertCharaToChara(chara: com.ort.dbflute.exentity.Chara): com.ort.wolfmansion.domain.model.charachip.Chara {
        return com.ort.wolfmansion.domain.model.charachip.Chara(
            id = chara.charaId,
            name = CharaName(
                name = chara.charaName,
                shortName = chara.charaShortName
            ),
            charachipId = chara.charaGroupId,
            defaultMessage = CharaDefaultMessage(
                joinMessage = chara.defaultJoinMessage,
                firstDayMessage = chara.defaultFirstdayMessage
            ),
            display = CharaSize(
                width = chara.displayWidth,
                height = chara.displayHeight
            ),
            faces = CharaFaces(
                list = chara.charaImageList.map { image ->
                    CharaFace(
                        type = image.faceTypeCode,
                        name = image.faceTypeCodeAsFaceType.alias(),
                        imageUrl = image.charaImgUrl
                    )
                }
            )
        )
    }

    private fun convertPlayerToSimplePlayer(
        player: Player
    ): com.ort.wolfmansion.domain.model.player.Player {
        return com.ort.wolfmansion.domain.model.player.Player(
            id = player.playerId,
            name = player.playerName,
            isRestrictedParticipation = player.isRestrictedParticipation,
            participatingNotSolvedVillageIdList = listOf(),
            createNotSolvedVillageIdList = listOf()
        )
    }
}