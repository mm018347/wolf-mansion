package com.ort.wolfmansion.infrastructure.datasource.village

import com.ort.dbflute.allcommon.CDef
import com.ort.dbflute.exbhv.MessageRestrictionBhv
import com.ort.dbflute.exbhv.VillageBhv
import com.ort.dbflute.exbhv.VillageDayBhv
import com.ort.dbflute.exbhv.VillagePlayerBhv
import com.ort.dbflute.exbhv.VillageSettingsBhv
import com.ort.dbflute.exentity.MessageRestriction
import com.ort.dbflute.exentity.Village
import com.ort.dbflute.exentity.VillageDay
import com.ort.dbflute.exentity.VillagePlayer
import com.ort.dbflute.exentity.VillageSettings
import com.ort.wolfmansion.domain.model.village.Villages
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.domain.model.village.settings.VillageMessageRestrict
import com.ort.wolfmansion.fw.security.WolfMansionUser
import com.ort.wolfmansion.util.WolfMansionDateUtil
import org.springframework.stereotype.Repository

@Repository
class VillageDataSource(
    val villageBhv: VillageBhv,
    val villageSettingsBhv: VillageSettingsBhv,
    val villageDayBhv: VillageDayBhv,
    val villagePlayerBhv: VillagePlayerBhv,
    val messageRestrictionBhv: MessageRestrictionBhv
) {

    // ===================================================================================
    //                                                                             village
    //                                                                           =========
    fun findVillages(
        user: WolfMansionUser? = null,
        villageStatusList: List<com.ort.wolfmansion.domain.model.village.VillageStatus>?,
        villageIdList: List<Int>?
    ): Villages {
        val villageList = villageBhv.selectList {
            it.setupSelect_VillageSettingsAsOne()

            it.specify().derivedVillagePlayer().count({ vpCB ->
                vpCB.specify().columnVillagePlayerId()
                vpCB.query().setIsGone_Equal(false)
                vpCB.query().setIsSpectator_Equal(false)
            }, Village.ALIAS_participantCount)
            it.specify().derivedVillagePlayer().count({ vpCB ->
                vpCB.specify().columnVillagePlayerId()
                vpCB.query().setIsGone_Equal(false)
                vpCB.query().setIsSpectator_Equal(true)
            }, Village.ALIAS_visitorCount)

            if (user != null) {
                it.query().existsVillagePlayer { vpCB ->
                    vpCB.query().setIsGone_Equal(false)
                    vpCB.query().queryPlayer().setPlayerName_Equal(user.name)
                }
            }
            if (!villageStatusList.isNullOrEmpty()) {
                it.query().setVillageStatusCode_InScope_AsVillageStatus(
                    villageStatusList.map { status -> status.toCdef() }
                )
            }
            if (!villageIdList.isNullOrEmpty()) {
                it.query().setVillageId_InScope(villageIdList)
            }
            it.query().addOrderBy_VillageId_Desc()
        }
        villageBhv.load(villageList) { loader ->
            loader.loadVillageDay {
                it.query().addOrderBy_Day_Desc()
            }
            loader.loadMessageRestriction { }
        }
        return VillageDataConverter.convertVillageListToVillages(villageList)
    }

    fun findVillagesAsDetail(
        villageIdList: List<Int>
    ): Villages {
        if (villageIdList.isEmpty()) return Villages(listOf())
        val villageList = villageBhv.selectList {
            it.setupSelect_VillageSettingsAsOne()
            it.query().setVillageId_InScope(villageIdList)
            it.query().addOrderBy_VillageId_Desc()
        }
        villageBhv.load(villageList) { loader ->
            loader.loadVillagePlayer { vpcb ->
                vpcb.setupSelect_Chara()
                vpcb.setupSelect_Player()
                vpcb.query().setIsGone_Equal_False()
            }.withNestedReferrer {
                it.pulloutChara().loadCharaImage { }
            }
            loader.loadVillageDay {
                it.query().addOrderBy_Day_Asc()
            }
            loader.loadMessageRestriction { }
        }

        return Villages(villageList.map { VillageDataConverter.convertVillage(it) })
    }

    fun findSolvedVillagesAsDetail(
        user: WolfMansionUser
    ): Villages {
        val villageList = villageBhv.selectList {
            it.setupSelect_VillageSettingsAsOne()
            it.query().existsVillagePlayer { vpCB ->
                vpCB.query().setIsGone_Equal_False()
                vpCB.query().queryPlayer().setPlayerName_Equal(user.name)
            }
            it.query().setVillageStatusCode_InScope_AsVillageStatus(
                listOf(
                    CDef.VillageStatus.エピローグ,
                    CDef.VillageStatus.終了
                )
            )
            it.query().addOrderBy_VillageId_Desc()
        }
        villageBhv.load(villageList) { loader ->
            loader.loadVillagePlayer { vpcb ->
                vpcb.setupSelect_Chara()
                vpcb.setupSelect_Player()
                vpcb.query().setIsGone_Equal_False()
            }.withNestedReferrer {
                it.pulloutChara().loadCharaImage { }
            }
            loader.loadVillageDay {
                it.query().addOrderBy_Day_Asc()
            }
            loader.loadMessageRestriction { }
        }

        return Villages(villageList.map { VillageDataConverter.convertVillage(it) })
    }

    fun findVillage(
        id: Int,
        excludeGonePlayer: Boolean = true
    ): com.ort.wolfmansion.domain.model.village.Village {
        val village = villageBhv.selectEntityWithDeletedCheck {
            it.setupSelect_VillageSettingsAsOne()
            it.query().setVillageId_Equal(id)
        }
        villageBhv.load(village) { loader ->
            loader.loadVillagePlayer { vpcb ->
                vpcb.setupSelect_Chara()
                vpcb.setupSelect_Player()
                if (excludeGonePlayer) vpcb.query().setIsGone_Equal_False()
            }.withNestedReferrer {
                it.pulloutChara().loadCharaImage { }
            }
            loader.loadVillageDay {
                it.query().addOrderBy_Day_Asc()
            }
            loader.loadMessageRestriction { }
        }

        return VillageDataConverter.convertVillage(village)
    }

    fun registerVillage(
        village: com.ort.wolfmansion.domain.model.village.Village
    ): com.ort.wolfmansion.domain.model.village.Village {
        // 村
        val villageId = insertVillage(village)
        // 村設定
        insertVillageSettings(villageId, village)
        // 発言制限
        insertMessageRestrictions(villageId, village)
        // 村日付
        insertVillageDay(
            villageId,
            com.ort.wolfmansion.domain.model.village.VillageDay(
                day = 0,
                dayChangeDatetime = village.setting.time.startDatetime
            )
        )
        return findVillage(villageId)
    }

    fun updateDifference(
        before: com.ort.wolfmansion.domain.model.village.Village,
        after: com.ort.wolfmansion.domain.model.village.Village
    ): com.ort.wolfmansion.domain.model.village.Village {
        // village
        updateVillageDifference(before, after)
        // village_day
        updateVillageDayDifference(before, after)
        // village_player
        updateVillagePlayerDifference(before, after)
        // village_setting
        updateVillageSettingDifference(before, after)
        // message_restriction
        updateMessageRestrictionDifference(before, after)

        return findVillage(before.id)
    }

    fun updateLastAccessDatetime(
        villageId: Int,
        participantId: Int
    ) {
        val participant = VillagePlayer()
        participant.villageId = villageId
        participant.villagePlayerId = participantId
        participant.lastAccessDatetime = WolfMansionDateUtil.currentLocalDateTime()
        villagePlayerBhv.update(participant)
    }

    // ===================================================================================
    //                                                                             village
    //                                                                        ============
    private fun insertVillage(
        village: com.ort.wolfmansion.domain.model.village.Village
    ): Int {
        val entity = Village()
        entity.villageDisplayName = village.name
        entity.villageStatusCodeAsVillageStatus = village.status.toCdef()
        entity.createPlayerName = village.creatorPlayerName
        villageBhv.insert(entity)
        return entity.villageId
    }

    private fun updateVillageDifference(
        before: com.ort.wolfmansion.domain.model.village.Village,
        after: com.ort.wolfmansion.domain.model.village.Village
    ) {
        if (before.status.code != after.status.code
            || before.winCamp?.code != after.winCamp?.code
        ) {
            updateVillage(after)
        }
    }

    private fun updateVillage(
        village: com.ort.wolfmansion.domain.model.village.Village
    ) {
        val entity = Village()
        entity.villageId = village.id
        entity.villageDisplayName = village.name
        entity.villageStatusCodeAsVillageStatus = village.status.toCdef()
        entity.createPlayerName = village.creatorPlayerName
        villageBhv.update(entity)
    }

    // ===================================================================================
    //                                                                    village_settings
    //                                                                        ============
    private fun insertVillageSettings(
        villageId: Int,
        village: com.ort.wolfmansion.domain.model.village.Village
    ) {
        val entity = VillageSettings()
        entity.villageId = villageId
        village.setting.let { setting ->
            setting.capacity.let {
                entity.personMaxNum = it.max
                entity.startPersonMinNum = it.min
            }
            setting.charachip.let {
                entity.dummyCharaId = it.dummyCharaId
                entity.characterGroupId = it.charachipId
            }
            setting.organizations.let {
                entity.organize = it.toString()
            }
            setting.time.let {
                entity.startDatetime = it.startDatetime
                entity.dayChangeIntervalSeconds = it.dayChangeIntervalSeconds
            }
            setting.rules.let {
                entity.isOpenVote = it.openVote
                entity.isPossibleSkillRequest = it.availableSkillRequest
                entity.isAvailableSpectate = it.availableSpectate
                entity.isAvailableSameWolfAttack = it.availableSameWolfAttack
                entity.isOpenSkillInGrave = it.openSkillInGrave
                entity.isVisibleGraveSpectateMessage = it.visibleGraveMessage
                entity.isAvailableSuddonlyDeath = it.availableSuddenlyDeath
                entity.isAvailableCommit = it.availableCommit
                entity.isAvailableGuardSameTarget = it.availableGuardSameTarget
            }
        }
        villageSettingsBhv.insert(entity)
    }

    private fun updateVillageSettingDifference(
        before: com.ort.wolfmansion.domain.model.village.Village,
        after: com.ort.wolfmansion.domain.model.village.Village
    ) {
        if (before.setting.existsDifference(after.setting)) updateVillageSettings(after)
    }

    private fun updateVillageSettings(
        village: com.ort.wolfmansion.domain.model.village.Village
    ) {
        val entity = VillageSettings()
        entity.villageId = village.id
        village.setting.let { setting ->
            setting.capacity.let {
                entity.personMaxNum = it.max
                entity.startPersonMinNum = it.min
            }
            setting.charachip.let {
                entity.dummyCharaId = it.dummyCharaId
                entity.characterGroupId = it.charachipId
            }
            setting.organizations.let {
                entity.organize = it.toString()
            }
            setting.time.let {
                entity.startDatetime = it.startDatetime
                entity.dayChangeIntervalSeconds = it.dayChangeIntervalSeconds
            }
            setting.rules.let {
                entity.isOpenVote = it.openVote
                entity.isPossibleSkillRequest = it.availableSkillRequest
                entity.isAvailableSpectate = it.availableSpectate
                entity.isAvailableSameWolfAttack = it.availableSameWolfAttack
                entity.isOpenSkillInGrave = it.openSkillInGrave
                entity.isVisibleGraveSpectateMessage = it.visibleGraveMessage
                entity.isAvailableSuddonlyDeath = it.availableSuddenlyDeath
                entity.isAvailableCommit = it.availableCommit
                entity.isAvailableGuardSameTarget = it.availableGuardSameTarget
            }
        }
        villageSettingsBhv.update(entity)
    }

    // ===================================================================================
    //                                                                 message_restriction
    //                                                                        ============
    private fun insertMessageRestrictions(
        villageId: Int,
        village: com.ort.wolfmansion.domain.model.village.Village
    ) {
        village.setting.rules.messageRestrict.list.forEach {
            val entity = MessageRestriction()
            entity.villageId = villageId
            entity.skillCodeAsSkill = it.skill.toCdef()
            entity.messageTypeCodeAsMessageType = it.messageType.toCdef()
            entity.messageMaxLength = it.length
            entity.messageMaxNum = it.count
            messageRestrictionBhv.insert(entity)
        }
    }

    private fun insertMessageRestriction(
        villageId: Int,
        restrict: VillageMessageRestrict
    ) {
        val entity = MessageRestriction()
        entity.villageId = villageId
        entity.skillCodeAsSkill = restrict.skill.toCdef()
        entity.messageTypeCodeAsMessageType = restrict.messageType.toCdef()
        entity.messageMaxLength = restrict.length
        entity.messageMaxNum = restrict.count
        messageRestrictionBhv.insert(entity)
    }

    private fun updateMessageRestrictionDifference(
        before: com.ort.wolfmansion.domain.model.village.Village,
        after: com.ort.wolfmansion.domain.model.village.Village
    ) {
        val villageId = after.id
        val beforeRestricts = before.setting.rules.messageRestrict
        val afterRestricts = after.setting.rules.messageRestrict
        if (!beforeRestricts.existsDifference(afterRestricts)) return
        // 変更前にしかないものは削除
        beforeRestricts.list.filterNot { beforeRestrict ->
            afterRestricts.list.any { afterRestrict ->
                beforeRestrict.skill.code == afterRestrict.skill.code && beforeRestrict.messageType.code == afterRestrict.messageType.code
            }
        }.forEach { deleteMessageRestriction(villageId, it) }
        // 両方にあるものは更新
        beforeRestricts.list.filter { beforeRestrict ->
            afterRestricts.list.any { afterRestrict ->
                beforeRestrict.skill.code == afterRestrict.skill.code && beforeRestrict.messageType.code == afterRestrict.messageType.code
            }
        }.forEach { updateMessageRestriction(villageId, it) }
        // 変更後にしかないものは登録
        afterRestricts.list.filterNot { afterRestrict ->
            beforeRestricts.list.any { beforeRestrict ->
                beforeRestrict.skill.code == afterRestrict.skill.code && beforeRestrict.messageType.code == afterRestrict.messageType.code
            }
        }.forEach { insertMessageRestriction(villageId, it) }
    }

    private fun updateMessageRestriction(
        villageId: Int,
        restrict: VillageMessageRestrict
    ) {
        val entity = MessageRestriction()
        entity.villageId = villageId
        entity.skillCodeAsSkill = restrict.skill.toCdef()
        entity.messageTypeCodeAsMessageType = restrict.messageType.toCdef()
        entity.messageMaxLength = restrict.length
        entity.messageMaxNum = restrict.count
        messageRestrictionBhv.update(entity)
    }

    private fun deleteMessageRestriction(
        villageId: Int,
        restrict: VillageMessageRestrict
    ) {
        messageRestrictionBhv.queryDelete {
            it.query().setVillageId_Equal(villageId)
            it.query().setSkillCode_Equal_AsSkill(restrict.skill.toCdef())
            it.query().setMessageTypeCode_Equal_AsMessageType(restrict.messageType.toCdef())
        }
    }

    // ===================================================================================
    //                                                                         village_day
    //                                                                        ============
    private fun insertVillageDay(
        villageId: Int,
        villageDay: com.ort.wolfmansion.domain.model.village.VillageDay
    ) {
        val entity = VillageDay()
        entity.villageId = villageId
        entity.day = villageDay.day
        entity.daychangeDatetime = villageDay.dayChangeDatetime
        villageDayBhv.insert(entity)
    }

    private fun updateVillageDayDifference(
        before: com.ort.wolfmansion.domain.model.village.Village,
        after: com.ort.wolfmansion.domain.model.village.Village
    ) {
        if (!before.days.existsDifference(after.days)) return
        // 新規を追加
        after.days.list
            .filterNot { afterDay -> before.days.list.any { it.day == afterDay.day } }
            .forEach { insertVillageDay(after.id, it) }
        // すでにある分を更新
        after.days.list
            .filter { afterDay -> before.days.list.any { it.day == afterDay.day } }
            .forEach { afterDay ->
                val beforeDay = before.days.list.first { it.day == afterDay.day }
                if (afterDay.existsDifference(beforeDay)) updateVillageDay(after.id, afterDay)
            }

    }

    private fun updateVillageDay(
        villageId: Int,
        villageDay: com.ort.wolfmansion.domain.model.village.VillageDay
    ) {
        val entity = VillageDay()
        entity.villageId = villageId
        entity.day = villageDay.day
        entity.daychangeDatetime = villageDay.dayChangeDatetime
        villageDayBhv.update(entity)
    }


    // ===================================================================================
    //                                                                      village_player
    //                                                                        ============
    private fun insertVillagePlayer(
        villageId: Int,
        participant: VillageParticipant
    ): Int {
        val vp = VillagePlayer()
        vp.villageId = villageId
        vp.playerId = participant.player!!.id
        vp.charaId = participant.chara!!.id
        vp.isDead = false
        vp.isSpectator = participant.isSpectator
        vp.isGone = false
        vp.requestSkillCodeAsSkill = participant.skillRequest.first.toCdef()
        vp.secondRequestSkillCodeAsSkill = participant.skillRequest.second.toCdef()
        villagePlayerBhv.insert(vp)
        return vp.villagePlayerId
    }

    private fun updateVillagePlayerDifference(
        before: com.ort.wolfmansion.domain.model.village.Village,
        after: com.ort.wolfmansion.domain.model.village.Village
    ) {
        val villageId = after.id
        if (!before.participant.existsDifference(after.participant)) return
        // 新規
        after.participant.list.filterNot { member ->
            before.participant.list.any { it.id == member.id }
        }.forEach { insertVillagePlayer(villageId, it) }

        after.spectator.list.filterNot { member ->
            before.spectator.list.any { it.id == member.id }
        }.forEach { insertVillagePlayer(villageId, it) }

        // 更新
        after.participant.list.filter { member ->
            before.participant.list.any { it.id == member.id }
        }.filter {
            it.existsDifference(before.participant.member(it.id))
        }.forEach { updateVillagePlayer(villageId, it) }

        after.spectator.list.filter { member ->
            before.spectator.list.any { it.id == member.id }
        }.filter {
            it.existsDifference(before.spectator.member(it.id))
        }.forEach { updateVillagePlayer(villageId, it) }
    }

    private fun updateVillagePlayer(
        villageId: Int,
        villagePlayerModel: VillageParticipant
    ) {
        val villagePlayer = VillagePlayer()
        villagePlayer.villageId = villageId
        villagePlayer.villagePlayerId = villagePlayerModel.id
        villagePlayer.isDead = villagePlayerModel.dead != null
        villagePlayer.deadReasonCodeAsDeadReason = villagePlayerModel.dead?.toCdef()
        villagePlayer.deadDay = villagePlayerModel.dead?.villageDay?.day
        villagePlayer.isGone = villagePlayerModel.isGone
        villagePlayer.skillCodeAsSkill = villagePlayerModel.skill?.toCdef()
        villagePlayer.requestSkillCodeAsSkill = villagePlayerModel.skillRequest.first.toCdef()
        villagePlayer.secondRequestSkillCodeAsSkill = villagePlayerModel.skillRequest.second.toCdef()
        villagePlayerBhv.update(villagePlayer)
    }
}