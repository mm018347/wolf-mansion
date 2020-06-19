package com.ort.wolfmansion.infrastructure.datasource.chara

import com.ort.dbflute.exbhv.CharaBhv
import com.ort.dbflute.exentity.Chara
import com.ort.wolfmansion.domain.model.charachip.CharaDefaultMessage
import com.ort.wolfmansion.domain.model.charachip.CharaFace
import com.ort.wolfmansion.domain.model.charachip.CharaFaces
import com.ort.wolfmansion.domain.model.charachip.CharaName
import com.ort.wolfmansion.domain.model.charachip.CharaSize
import com.ort.wolfmansion.domain.model.charachip.Charachips
import com.ort.wolfmansion.domain.model.charachip.Charas
import org.springframework.stereotype.Repository

@Repository
class CharaDataSource(
    val charaBhv: CharaBhv
) {

    fun findCharas(charachipId: Int): Charas {
        val charaList = charaBhv.selectList {
            it.query().setCharaGroupId_Equal(charachipId)
        }
        charaBhv.loadCharaImage(charaList) {
            it.query().queryFaceType().addOrderBy_DispOrder_Asc()
        }
        return Charas(charaList.map { convertCharaToChara(it) })
    }

    fun findCharas(charachips: Charachips): Charas {
        val charaList = charaBhv.selectList {
            it.query().setCharaGroupId_InScope(charachips.list.map { charachip -> charachip.id })
        }
        charaBhv.loadCharaImage(charaList) {
            it.query().queryFaceType().addOrderBy_DispOrder_Asc()
        }
        return Charas(charaList.map { convertCharaToChara(it) })
    }

    fun findCharas(charachipIdList: List<Int>): Charas {
        if (charachipIdList.isEmpty()) return Charas(listOf())
        val charaList = charaBhv.selectList {
            it.query().setCharaGroupId_InScope(charachipIdList)
        }
        charaBhv.loadCharaImage(charaList) {
            it.query().queryFaceType().addOrderBy_DispOrder_Asc()
        }
        return Charas(charaList.map { convertCharaToChara(it) })
    }

    fun findChara(charaId: Int): com.ort.wolfmansion.domain.model.charachip.Chara {
        val chara = charaBhv.selectEntityWithDeletedCheck {
            it.query().setCharaId_Equal(charaId)
        }
        charaBhv.loadCharaImage(chara) {
            it.query().queryFaceType().addOrderBy_DispOrder_Asc()
        }
        return convertCharaToChara(chara)
    }

    fun findDummyChara(charaChipId: Int): com.ort.wolfmansion.domain.model.charachip.Chara {
        val chara = charaBhv.selectEntityWithDeletedCheck {
            it.query().setCharaGroupId_Equal(charaChipId)
            it.query().addOrderBy_DefaultJoinMessage_Asc().withNullsLast()
            it.query().addOrderBy_CharaId_Asc()
            it.fetchFirst(1)
        }
        charaBhv.loadCharaImage(chara) {
            it.query().queryFaceType().addOrderBy_DispOrder_Asc()
        }
        return convertCharaToChara(chara)
    }

    // ===================================================================================
    //                                                                             Mapping
    //                                                                             =======
    private fun convertCharaToChara(chara: Chara): com.ort.wolfmansion.domain.model.charachip.Chara {
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
}