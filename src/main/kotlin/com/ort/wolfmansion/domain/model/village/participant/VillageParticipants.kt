package com.ort.wolfmansion.domain.model.village.participant

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.charachip.Chara
import com.ort.wolfmansion.domain.model.player.Player
import com.ort.wolfmansion.domain.model.skill.Skill
import com.ort.wolfmansion.domain.model.skill.SkillRequest
import com.ort.wolfmansion.domain.model.village.VillageDay
import org.springframework.data.jpa.domain.AbstractPersistable_.id

data class VillageParticipants(
    val count: Int,
    val list: List<VillageParticipant> = listOf()
) {

    constructor(
        list: List<VillageParticipant>
    ) : this(
        count = list.size,
        list = list
    )

    // ===================================================================================
    //                                                                       filter / find
    //                                                                        ============
    fun filterAlive(): VillageParticipants {
        val aliveMemberList = list.filter { it.isAlive() }
        return VillageParticipants(
            count = aliveMemberList.size,
            list = aliveMemberList
        )
    }

    fun filterNotGone(): VillageParticipants {
        val notGoneMemberList = list.filter { !it.isGone }
        return VillageParticipants(
            count = notGoneMemberList.size,
            list = notGoneMemberList
        )
    }

    fun findMember(participantId: Int): VillageParticipant? {
        return list.firstOrNull { it.id == participantId }
    }

    fun member(participantId: Int): VillageParticipant {
        return list.firstOrNull { it.id == participantId } ?: throw IllegalStateException("not exist participant. id: $id")
    }

    fun memberByCharaId(charaId: Int): VillageParticipant {
        return list.firstOrNull { it.chara.id == charaId } ?: throw IllegalStateException("not exist participant. charaId: $id")
    }

    fun findRandom(predicate: (VillageParticipant) -> Boolean): VillageParticipant? {
        return list.filter { predicate(it) }.shuffled().firstOrNull()
    }

    // ===================================================================================
    //                                                                              update
    //                                                                              ======
    // 役職割り当て
    fun assignSkill(villageParticipantId: Int, skill: Skill): VillageParticipants {
        return this.copy(
            list = this.list.map {
                if (it.id == villageParticipantId) it.assignSkill(skill)
                else it.copy()
            }
        )
    }

    fun addParticipant(
        chara: Chara,
        player: Player,
        skillRequest: SkillRequest,
        isSpectator: Boolean
    ): VillageParticipants {
        return this.copy(
            count = count + 1,
            list = list + VillageParticipant(
                id = -1, // dummy
                chara = chara,
                player = player,
                isSpectator = isSpectator,
                skillRequest = skillRequest
            )
        )
    }

    // 役職希望変更
    fun changeSkillRequest(participantId: Int, first: CDef.Skill, second: CDef.Skill): VillageParticipants {
        return this.copy(
            list = this.list.map {
                if (it.id == participantId) it.changeSkillRequest(first, second)
                else it.copy()
            }
        )
    }

    // 退村
    fun leave(participantId: Int): VillageParticipants {
        return this.copy(
            count = this.count - 1,
            list = this.list.map {
                if (it.id == participantId) it.gone() else it.copy()
            }
        )
    }

    // 突然死
    fun suddenlyDeath(participantId: Int, villageDay: VillageDay): VillageParticipants {
        return this.copy(
            list = this.list.map {
                if (it.id == participantId) it.suddenlyDeath(villageDay) else it.copy()
            }
        )
    }

    // 処刑
    fun execute(participantId: Int, villageDay: VillageDay): VillageParticipants {
        return this.copy(
            list = this.list.map {
                if (it.id == participantId) it.execute(villageDay) else it.copy()
            }
        )
    }

    // 襲撃
    fun attack(participantId: Int, villageDay: VillageDay): VillageParticipants {
        return this.copy(
            list = this.list.map {
                if (it.id == participantId) it.attack(villageDay) else it.copy()
            }
        )
    }

    // 呪殺
    fun divineKill(participantId: Int, villageDay: VillageDay): VillageParticipants {
        return this.copy(
            list = this.list.map {
                if (it.id == participantId) it.divineKill(villageDay) else it.copy()
            }
        )
    }

    // 勝敗
    fun winLose(cdefWinCamp: CDef.Camp): VillageParticipants = this.copy(list = this.list.map { it.winLose(cdefWinCamp) })


    fun existsDifference(participant: VillageParticipants): Boolean {
        if (count != participant.count) return true
        if (list.size != participant.list.size) return true
        return list.any { member1 ->
            participant.list.none { member2 -> !member1.existsDifference(member2) }
        }
    }
}
