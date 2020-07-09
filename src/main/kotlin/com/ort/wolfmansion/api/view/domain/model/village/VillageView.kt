package com.ort.wolfmansion.api.view.domain.model.village

import com.ort.wolfmansion.api.view.domain.model.village.participant.VillageParticipantView
import com.ort.wolfmansion.api.view.domain.model.village.participant.VillageParticipantsView
import com.ort.wolfmansion.api.view.model.option.OptionModel
import com.ort.wolfmansion.domain.model.camp.Camp
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.VillageDays
import com.ort.wolfmansion.domain.model.village.VillageSettings
import com.ort.wolfmansion.domain.model.village.VillageStatus
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipants
import com.ort.wolfmansion.domain.model.village.room.VillageRooms
import java.time.LocalDateTime

data class VillageView(
    val id: Int,
    val name: String,
    val creatorPlayerName: String,
    val status: VillageStatus,
    val rooms: VillageRooms?,
    val winCamp: Camp?,
    val setting: VillageSettings,
    val participant: VillageParticipantsView,
    val spectator: VillageParticipantsView,
    val days: VillageDays,

    /** ステータスごとの参加者一覧 */
    val members: VillageMembersModel,
    /** キャラクター一覧 */
    val characters: CharactersModel,
    /** 部屋割り当て */
    val assignedRoomRows: AssignedRoomRows?
) {
    constructor(
        village: Village
    ) : this(
        id = village.id,
        name = village.name,
        creatorPlayerName = village.creatorPlayerName,
        status = village.status,
        rooms = village.rooms,
        winCamp = village.winCamp,
        setting = village.setting,
        participant = VillageParticipantsView(
            village = village,
            participants = village.participant
        ),
        spectator = VillageParticipantsView(
            village = village,
            participants = village.spectator
        ),
        days = village.days,
        members = VillageMembersModel(
            participants = village.participant,
            spectators = village.spectator
        ),
        characters = CharactersModel(village),
        assignedRoomRows = if (village.rooms == null) null else AssignedRoomRows(village)
    )
}

data class VillageMembersModel(
    var list: List<VillageStatusMember>
) {
    constructor(
        participants: VillageParticipants,
        spectators: VillageParticipants
    ) : this(
        list = listOf()
    ) {
        val memberList = mutableListOf<VillageStatusMember>()
        // 生存
        memberList.add(
            VillageStatusMember(
                status = "生存",
                participants = participants.filterAlive()
            )
        )
        // 処刑
        memberList.add(
            VillageStatusMember(
                status = "処刑死",
                participants = VillageParticipants(
                    list = participants.list.filter {
                        it.dead != null && it.dead.isExecuted()
                    }
                )
            )
        )

        // 無惨
        memberList.add(
            VillageStatusMember(
                status = "無惨死",
                participants = VillageParticipants(
                    list = participants.list.filter {
                        it.dead != null && it.dead.isMiserable()
                    }
                )
            )
        )

        // 突然死
        memberList.add(
            VillageStatusMember(
                status = "突然死",
                participants = VillageParticipants(
                    list = participants.list.filter {
                        it.dead != null && it.dead.isSuddenly()
                    }
                )
            )
        )

        // 見学
        memberList.add(
            VillageStatusMember(
                status = "見学",
                participants = VillageParticipants(
                    list = spectators.list
                )
            )
        )

        this.list = memberList
    }
}

data class VillageStatusMember(
    /** 状態 e.g. 襲撃 */
    val status: String,
    /** キャラリスト */
    val memberList: List<VillageMemberDetail>
) {
    constructor(
        status: String,
        participants: VillageParticipants
    ) : this(
        status = status,
        memberList = participants.list
            .sortedWith(
                compareBy<VillageParticipant> {
                    it.dead?.villageDay?.day ?: 0
                }.thenBy { it.chara.id }
            ).map { VillageMemberDetail(it) }
    )
}

data class VillageMemberDetail(
    val charaName: String,
    val deadDay: String?,
    val lastAccessDatetime: LocalDateTime?
) {
    constructor(
        participant: VillageParticipant
    ) : this(
        charaName = participant.name(),
        deadDay = participant.dead?.villageDay?.let { "${it.day}d" },
        lastAccessDatetime = participant.lastAccessDatetime
    )
}

data class CharactersModel(
    val list: List<OptionModel>
) {
    constructor(
        village: Village
    ) : this(
        list = (village.participant.list + village.spectator.list)
            .sortedWith(
                compareBy<VillageParticipant> {
                    if (it.dead == null) 0 else 1
                }.thenBy {
                    it.room?.displayNo ?: 0
                }.thenBy { it.chara.id }
            ).map {
                OptionModel(
                    label = it.name(),
                    value = it.chara.id.toString()
                )
            }
    )
}

data class AssignedRoomRows(
    var list: List<AssignedRoomRow>
) {
    constructor(
        village: Village
    ) : this(
        list = (0 until village.rooms!!.height).map {
            AssignedRoomRow(it, village)
        }
    )
}

data class AssignedRoomRow(
    val assignedRoomList: List<AssignedRoom>
) {
    constructor(
        heightIndex: Int,
        village: Village
    ) : this(
        assignedRoomList = (0 until village.rooms!!.width).map { widthIndex ->
            val roomNo = village.rooms.width * heightIndex + widthIndex
            AssignedRoom(roomNo, village)
        }
    )
}

data class AssignedRoom(
    /** 参加者 */
    val participant: VillageParticipantView?,
    /** ダミーか */
    val dummy: Boolean
) {
    constructor(
        roomNo: Int,
        village: Village
    ) : this(
        participant = village.participant.list.firstOrNull {
            it.room?.no == roomNo
        }?.let {
            VillageParticipantView(
                participant = it,
                village = village
            )
        },
        dummy = village.participant.list.firstOrNull {
            it.room?.no == roomNo
        }?.id == village.dummyParticipant().id
    )
}