package com.ort.wolfmansion.application.service

import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.VillageStatus
import com.ort.wolfmansion.domain.model.village.Villages
import com.ort.wolfmansion.fw.security.WolfMansionUser
import com.ort.wolfmansion.infrastructure.datasource.village.VillageDataSource
import org.springframework.stereotype.Service

@Service
class VillageService(
    private val villageDataSource: VillageDataSource
) {

    /**
     * 村一覧取得
     */
    fun findVillages(
        user: WolfMansionUser? = null,
        villageStatusList: List<VillageStatus>? = listOf(),
        villageIdList: List<Int>? = listOf()
    ): Villages = villageDataSource.findVillages(user, villageStatusList, villageIdList)

    /**
     * 村一覧（詳細）取得
     */
    fun findVillagesAsDetail(villageIdList: List<Int>): Villages = villageDataSource.findVillagesAsDetail(villageIdList)

    /**
     * 村一覧（詳細）取得
     */
    fun findSolvedVillagesAsDetail(user: WolfMansionUser): Villages = villageDataSource.findSolvedVillagesAsDetail(user)

    /**
     * 村取得
     */
    fun findVillage(villageId: Int, excludeGonePlayer: Boolean = true): Village =
        villageDataSource.findVillage(villageId, excludeGonePlayer)

    /**
     * 村登録
     */
    fun registerVillage(village: Village): Village = villageDataSource.registerVillage(village)

    /**
     * 差分更新
     */
    fun updateVillageDifference(before: Village, after: Village): Village = villageDataSource.updateDifference(before, after)

    /** 最終アクセス日時更新 */
    fun updateLastAccessDatetime(villageId: Int, participantId: Int) = villageDataSource.updateLastAccessDatetime(villageId, participantId)
}