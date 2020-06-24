package com.ort.wolfmansion.domain.model.skill

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.ability.AbilityType
import com.ort.wolfmansion.domain.model.ability.AbilityTypes
import com.ort.wolfmansion.domain.model.camp.Camp
import com.ort.wolfmansion.domain.model.message.MessageType

data class Skill(
    val code: String,
    val name: String
) {
    constructor(cdef: CDef.Skill) : this(
        code = cdef.code(),
        name = cdef.alias()
    )

    companion object {

        // TODO 追加
        private val skillAbilityTypeListMap = mapOf(
            CDef.Skill.人狼 to listOf(CDef.AbilityType.襲撃),
            CDef.Skill.占い師 to listOf(CDef.AbilityType.占い),
            CDef.Skill.狩人 to listOf(CDef.AbilityType.護衛)
        )

        fun skillByShortName(shortName: String): Skill? {
            val cdefSkill: CDef.Skill = CDef.Skill.listAll().firstOrNull {
                it.shortName() == shortName
            } ?: return null
            return Skill(cdefSkill)
        }

        fun getAbilityTypes(cdefSkill: CDef.Skill): AbilityTypes {
            val cdefAbilityList = skillAbilityTypeListMap[cdefSkill] ?: return AbilityTypes(listOf())
            return AbilityTypes(cdefAbilityList.map { AbilityType(it) })
        }

        fun getSayableMessageTypeList(cdefSkill: CDef.Skill): List<MessageType> {
            val list = mutableListOf<MessageType>()
            // 囁き
            if (cdefSkill.isAvailableWerewolfSay) list.add(MessageType(CDef.MessageType.人狼の囁き))

            return list
        }

        fun getViewableMessageTypeList(cdefSkill: CDef.Skill): List<MessageType> {
            val list = mutableListOf<MessageType>()
            // 囁き
            if (cdefSkill.isViewableWerewolfSay) list.add(MessageType(CDef.MessageType.人狼の囁き))

            return list
        }

        private fun getCountCamp(cdefSkill: CDef.Skill): Camp? {
            return when {
                cdefSkill.isCountWolf -> return Camp(CDef.Camp.人狼陣営)
                cdefSkill.isNoCount -> return null
                else -> Camp(CDef.Camp.村人陣営)
            }
        }
    }

    fun getAbilityTypes(): AbilityTypes {
        val cdefSkill = CDef.Skill.codeOf(code) ?: return AbilityTypes(listOf())
        return Companion.getAbilityTypes(cdefSkill)
    }

    fun toCdef(): CDef.Skill = CDef.Skill.codeOf(this.code)

    fun winJudgeCamp(): Camp = Camp(CDef.Camp.codeOf(toCdef().campCode()))
}