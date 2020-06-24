package com.ort.wolfmansion.domain.service.skill

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.skill.Skill
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipants
import org.springframework.stereotype.Service

@Service
class SkillAssignService {

    fun assign(
        village: Village
    ): VillageParticipants {
        // TODO 存在しない役職を自動変更

        val skillPersonCountMap = village.setting.organizations.mapToSkillCount(village.participant.count)

        // ダミー配役
        var changedParticipants = village.participant.assignSkill(village.dummyParticipant().id, Skill(CDef.Skill.村人))

        // 第1希望で役職希望した人を割り当て
        changedParticipants = assignFirstSpecifyRequest(changedParticipants, skillPersonCountMap)

        // 第1希望で範囲指定希望した人を割り当て
        changedParticipants = assignFirstRangeRequest(changedParticipants, skillPersonCountMap)

        // 第2希望で役職希望した人を割り当て
        changedParticipants = assignSecondSpecifyRequest(changedParticipants, skillPersonCountMap)

        // 第2希望で範囲指定希望した人を割り当て
        changedParticipants = assignSecondRangeRequest(changedParticipants, skillPersonCountMap)

        // ここまでで割当たらなかった人に割り当て
        changedParticipants = assignOther(changedParticipants, skillPersonCountMap)

        return changedParticipants
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun assignFirstSpecifyRequest(
        participants: VillageParticipants,
        skillPersonCountMap: Map<CDef.Skill, Int>
    ): VillageParticipants {
        var changedParticipants = participants.copy()
        for ((cdefSkill, capacity) in skillPersonCountMap.entries) {
            // この役職を希望していてまだ割り当たってない人
            var requestPlayerList = changedParticipants.list.filter {
                it.skillRequest.first.code == cdefSkill.code() && it.skill == null
            }
            // 希望している人がいない
            if (requestPlayerList.isEmpty()) continue

            // 空いている枠数
            val left = capacity - changedParticipants.list.count { it.skill?.code == cdefSkill.code() }
            // もう空きがない
            if (left <= 0) continue

            // 空いている人数まで割り当てる
            var count = 0
            for (requestPlayer in requestPlayerList.shuffled()) {
                if (count >= left) break
                changedParticipants = changedParticipants.assignSkill(requestPlayer.id, Skill(cdefSkill))
                count++
            }
        }
        return changedParticipants
    }

    private fun assignSecondSpecifyRequest(
        participants: VillageParticipants,
        skillPersonCountMap: Map<CDef.Skill, Int>
    ): VillageParticipants {
        var changedParticipants = participants.copy()
        for ((cdefSkill, capacity) in skillPersonCountMap.entries) {
            // この役職を希望していてまだ割り当たってない人
            var requestPlayerList = changedParticipants.list.filter {
                it.skillRequest.second.code == cdefSkill.code() && it.skill == null
            }
            // 希望している人がいない
            if (requestPlayerList.isEmpty()) continue

            // 空いている枠数
            val left = capacity - changedParticipants.list.count { it.skill?.code == cdefSkill.code() }
            // もう空きがない
            if (left <= 0) continue

            // 空いている人数まで割り当てる
            var count = 0
            for (requestPlayer in requestPlayerList.shuffled()) {
                if (count >= left) break
                changedParticipants = changedParticipants.assignSkill(requestPlayer.id, Skill(cdefSkill))
                count++
            }
        }
        return changedParticipants
    }

    private fun assignFirstRangeRequest(participants: VillageParticipants, skillPersonCountMap: Map<CDef.Skill, Int>): VillageParticipants {
        var changedParticipants = participants.copy()
        // 範囲指定している人
        changedParticipants.list
            .filter { it.skill == null && CDef.Skill.listOfSomeoneSkill().contains(it.skillRequest.first.toCdef()) && it.skillRequest.first.toCdef() != CDef.Skill.おまかせ }
            .shuffled()
            .forEach {
                // 役職候補
                val candidateSkillList = getCandidateSkillList(it.skillRequest.first.toCdef()).sorted()
                for (skill in candidateSkillList) {
                    val capacity = skillPersonCountMap[skill] ?: 0
                    val left = capacity - changedParticipants.list.count { member -> member.skill?.toCdef() == skill }
                    if (left <= 0) continue
                    changedParticipants = changedParticipants.assignSkill(it.id, Skill(skill))
                    break
                }
            }
        return changedParticipants
    }

    private fun getCandidateSkillList(cdefSomeoneSkill: CDef.Skill): List<CDef.Skill> {
        return when (cdefSomeoneSkill) {
            CDef.Skill.おまかせ村人陣営 -> CDef.Skill.listAll().filter {
                !CDef.Skill.listOfSomeoneSkill().contains(it) && it.campCode() == CDef.Camp.村人陣営.code()
            }
            CDef.Skill.おまかせ人狼陣営 -> CDef.Skill.listAll().filter {
                !CDef.Skill.listOfSomeoneSkill().contains(it) && it.campCode() == CDef.Camp.人狼陣営.code()
            }
            else -> throw IllegalStateException("謎のおまかせ希望")
        }
    }

    private fun assignSecondRangeRequest(
        participants: VillageParticipants,
        skillPersonCountMap: Map<CDef.Skill, Int>
    ): VillageParticipants {
        var changedParticipants = participants.copy()
        // 範囲指定している人
        changedParticipants.list
            .filter { it.skill == null && CDef.Skill.listOfSomeoneSkill().contains(it.skillRequest.second.toCdef()) && it.skillRequest.second.toCdef() != CDef.Skill.おまかせ }
            .shuffled()
            .forEach {
                // 役職候補
                val candidateSkillList = getCandidateSkillList(it.skillRequest.second.toCdef()).sorted()
                for (skill in candidateSkillList) {
                    val capacity = skillPersonCountMap[skill] ?: 0
                    val left = capacity - changedParticipants.list.count { member -> member.skill?.toCdef() == skill }
                    if (left <= 0) continue
                    changedParticipants = changedParticipants.assignSkill(it.id, Skill(skill))
                    break
                }
            }
        return changedParticipants
    }

    private fun assignOther(participants: VillageParticipants, skillPersonCountMap: Map<CDef.Skill, Int>): VillageParticipants {
        var changedParticipants = participants.copy()

        // 役職が決まっていない参加者に
        participants.list.filter { it.skill == null }.shuffled().forEach { member ->
            // 枠が余っている役職を割り当てる
            for ((cdefSkill, capacity) in skillPersonCountMap.entries) {
                // 空いている枠数
                val left = capacity - changedParticipants.list.count { it.skill?.code == cdefSkill.code() }
                if (left <= 0) continue
                changedParticipants = changedParticipants.assignSkill(member.id, Skill(cdefSkill))
                break
            }
        }
        return changedParticipants
    }
}