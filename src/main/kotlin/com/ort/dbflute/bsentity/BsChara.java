package com.ort.dbflute.bsentity;

import java.util.List;
import java.util.ArrayList;

import org.dbflute.Entity;
import org.dbflute.dbmeta.DBMeta;
import org.dbflute.dbmeta.AbstractEntity;
import org.dbflute.dbmeta.accessory.DomainEntity;
import org.dbflute.optional.OptionalEntity;
import com.ort.dbflute.allcommon.EntityDefinedCommonColumn;
import com.ort.dbflute.allcommon.DBMetaInstanceHandler;
import com.ort.dbflute.exentity.*;

/**
 * The entity of CHARA as TABLE. <br>
 * キャラクター
 * <pre>
 * [primary-key]
 *     CHARA_ID
 *
 * [column]
 *     CHARA_ID, CHARA_NAME, CHARA_SHORT_NAME, CHARA_GROUP_ID, DEFAULT_JOIN_MESSAGE, DEFAULT_FIRSTDAY_MESSAGE, DISPLAY_WIDTH, DISPLAY_HEIGHT, REGISTER_DATETIME, REGISTER_TRACE, UPDATE_DATETIME, UPDATE_TRACE
 *
 * [sequence]
 *     
 *
 * [identity]
 *     CHARA_ID
 *
 * [version-no]
 *     
 *
 * [foreign table]
 *     CHARA_GROUP
 *
 * [referrer table]
 *     ABILITY, CHARA_IMAGE, FOOTSTEP, VILLAGE_PLAYER, VOTE
 *
 * [foreign property]
 *     charaGroup
 *
 * [referrer property]
 *     abilityByCharaIdList, abilityByTargetCharaIdList, charaImageList, footstepList, villagePlayerList, voteByCharaIdList, voteByVoteCharaIdList
 *
 * [get/set template]
 * /= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
 * Integer charaId = entity.getCharaId();
 * String charaName = entity.getCharaName();
 * String charaShortName = entity.getCharaShortName();
 * Integer charaGroupId = entity.getCharaGroupId();
 * String defaultJoinMessage = entity.getDefaultJoinMessage();
 * String defaultFirstdayMessage = entity.getDefaultFirstdayMessage();
 * Integer displayWidth = entity.getDisplayWidth();
 * Integer displayHeight = entity.getDisplayHeight();
 * java.time.LocalDateTime registerDatetime = entity.getRegisterDatetime();
 * String registerTrace = entity.getRegisterTrace();
 * java.time.LocalDateTime updateDatetime = entity.getUpdateDatetime();
 * String updateTrace = entity.getUpdateTrace();
 * entity.setCharaId(charaId);
 * entity.setCharaName(charaName);
 * entity.setCharaShortName(charaShortName);
 * entity.setCharaGroupId(charaGroupId);
 * entity.setDefaultJoinMessage(defaultJoinMessage);
 * entity.setDefaultFirstdayMessage(defaultFirstdayMessage);
 * entity.setDisplayWidth(displayWidth);
 * entity.setDisplayHeight(displayHeight);
 * entity.setRegisterDatetime(registerDatetime);
 * entity.setRegisterTrace(registerTrace);
 * entity.setUpdateDatetime(updateDatetime);
 * entity.setUpdateTrace(updateTrace);
 * = = = = = = = = = =/
 * </pre>
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsChara extends AbstractEntity implements DomainEntity, EntityDefinedCommonColumn {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** The serial version UID for object serialization. (Default) */
    private static final long serialVersionUID = 1L;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** CHARA_ID: {PK, ID, NotNull, INT UNSIGNED(10)} */
    protected Integer _charaId;

    /** CHARA_NAME: {NotNull, VARCHAR(40)} */
    protected String _charaName;

    /** CHARA_SHORT_NAME: {NotNull, CHAR(1)} */
    protected String _charaShortName;

    /** CHARA_GROUP_ID: {IX, NotNull, INT UNSIGNED(10), FK to chara_group} */
    protected Integer _charaGroupId;

    /** DEFAULT_JOIN_MESSAGE: {VARCHAR(200)} */
    protected String _defaultJoinMessage;

    /** DEFAULT_FIRSTDAY_MESSAGE: {VARCHAR(200)} */
    protected String _defaultFirstdayMessage;

    /** DISPLAY_WIDTH: {NotNull, INT UNSIGNED(10)} */
    protected Integer _displayWidth;

    /** DISPLAY_HEIGHT: {NotNull, INT UNSIGNED(10)} */
    protected Integer _displayHeight;

    /** REGISTER_DATETIME: {NotNull, DATETIME(19)} */
    protected java.time.LocalDateTime _registerDatetime;

    /** REGISTER_TRACE: {NotNull, VARCHAR(64)} */
    protected String _registerTrace;

    /** UPDATE_DATETIME: {NotNull, DATETIME(19)} */
    protected java.time.LocalDateTime _updateDatetime;

    /** UPDATE_TRACE: {NotNull, VARCHAR(64)} */
    protected String _updateTrace;

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    /** {@inheritDoc} */
    public DBMeta asDBMeta() {
        return DBMetaInstanceHandler.findDBMeta(asTableDbName());
    }

    /** {@inheritDoc} */
    public String asTableDbName() {
        return "chara";
    }

    // ===================================================================================
    //                                                                        Key Handling
    //                                                                        ============
    /** {@inheritDoc} */
    public boolean hasPrimaryKeyValue() {
        if (_charaId == null) { return false; }
        return true;
    }

    // ===================================================================================
    //                                                                    Foreign Property
    //                                                                    ================
    /** CHARA_GROUP by my CHARA_GROUP_ID, named 'charaGroup'. */
    protected OptionalEntity<CharaGroup> _charaGroup;

    /**
     * [get] CHARA_GROUP by my CHARA_GROUP_ID, named 'charaGroup'. <br>
     * Optional: alwaysPresent(), ifPresent().orElse(), get(), ...
     * @return The entity of foreign property 'charaGroup'. (NotNull, EmptyAllowed: when e.g. null FK column, no setupSelect)
     */
    public OptionalEntity<CharaGroup> getCharaGroup() {
        if (_charaGroup == null) { _charaGroup = OptionalEntity.relationEmpty(this, "charaGroup"); }
        return _charaGroup;
    }

    /**
     * [set] CHARA_GROUP by my CHARA_GROUP_ID, named 'charaGroup'.
     * @param charaGroup The entity of foreign property 'charaGroup'. (NullAllowed)
     */
    public void setCharaGroup(OptionalEntity<CharaGroup> charaGroup) {
        _charaGroup = charaGroup;
    }

    // ===================================================================================
    //                                                                   Referrer Property
    //                                                                   =================
    /** ABILITY by CHARA_ID, named 'abilityByCharaIdList'. */
    protected List<Ability> _abilityByCharaIdList;

    /**
     * [get] ABILITY by CHARA_ID, named 'abilityByCharaIdList'.
     * @return The entity list of referrer property 'abilityByCharaIdList'. (NotNull: even if no loading, returns empty list)
     */
    public List<Ability> getAbilityByCharaIdList() {
        if (_abilityByCharaIdList == null) { _abilityByCharaIdList = newReferrerList(); }
        return _abilityByCharaIdList;
    }

    /**
     * [set] ABILITY by CHARA_ID, named 'abilityByCharaIdList'.
     * @param abilityByCharaIdList The entity list of referrer property 'abilityByCharaIdList'. (NullAllowed)
     */
    public void setAbilityByCharaIdList(List<Ability> abilityByCharaIdList) {
        _abilityByCharaIdList = abilityByCharaIdList;
    }

    /** ABILITY by TARGET_CHARA_ID, named 'abilityByTargetCharaIdList'. */
    protected List<Ability> _abilityByTargetCharaIdList;

    /**
     * [get] ABILITY by TARGET_CHARA_ID, named 'abilityByTargetCharaIdList'.
     * @return The entity list of referrer property 'abilityByTargetCharaIdList'. (NotNull: even if no loading, returns empty list)
     */
    public List<Ability> getAbilityByTargetCharaIdList() {
        if (_abilityByTargetCharaIdList == null) { _abilityByTargetCharaIdList = newReferrerList(); }
        return _abilityByTargetCharaIdList;
    }

    /**
     * [set] ABILITY by TARGET_CHARA_ID, named 'abilityByTargetCharaIdList'.
     * @param abilityByTargetCharaIdList The entity list of referrer property 'abilityByTargetCharaIdList'. (NullAllowed)
     */
    public void setAbilityByTargetCharaIdList(List<Ability> abilityByTargetCharaIdList) {
        _abilityByTargetCharaIdList = abilityByTargetCharaIdList;
    }

    /** CHARA_IMAGE by CHARA_ID, named 'charaImageList'. */
    protected List<CharaImage> _charaImageList;

    /**
     * [get] CHARA_IMAGE by CHARA_ID, named 'charaImageList'.
     * @return The entity list of referrer property 'charaImageList'. (NotNull: even if no loading, returns empty list)
     */
    public List<CharaImage> getCharaImageList() {
        if (_charaImageList == null) { _charaImageList = newReferrerList(); }
        return _charaImageList;
    }

    /**
     * [set] CHARA_IMAGE by CHARA_ID, named 'charaImageList'.
     * @param charaImageList The entity list of referrer property 'charaImageList'. (NullAllowed)
     */
    public void setCharaImageList(List<CharaImage> charaImageList) {
        _charaImageList = charaImageList;
    }

    /** FOOTSTEP by CHARA_ID, named 'footstepList'. */
    protected List<Footstep> _footstepList;

    /**
     * [get] FOOTSTEP by CHARA_ID, named 'footstepList'.
     * @return The entity list of referrer property 'footstepList'. (NotNull: even if no loading, returns empty list)
     */
    public List<Footstep> getFootstepList() {
        if (_footstepList == null) { _footstepList = newReferrerList(); }
        return _footstepList;
    }

    /**
     * [set] FOOTSTEP by CHARA_ID, named 'footstepList'.
     * @param footstepList The entity list of referrer property 'footstepList'. (NullAllowed)
     */
    public void setFootstepList(List<Footstep> footstepList) {
        _footstepList = footstepList;
    }

    /** VILLAGE_PLAYER by CHARA_ID, named 'villagePlayerList'. */
    protected List<VillagePlayer> _villagePlayerList;

    /**
     * [get] VILLAGE_PLAYER by CHARA_ID, named 'villagePlayerList'.
     * @return The entity list of referrer property 'villagePlayerList'. (NotNull: even if no loading, returns empty list)
     */
    public List<VillagePlayer> getVillagePlayerList() {
        if (_villagePlayerList == null) { _villagePlayerList = newReferrerList(); }
        return _villagePlayerList;
    }

    /**
     * [set] VILLAGE_PLAYER by CHARA_ID, named 'villagePlayerList'.
     * @param villagePlayerList The entity list of referrer property 'villagePlayerList'. (NullAllowed)
     */
    public void setVillagePlayerList(List<VillagePlayer> villagePlayerList) {
        _villagePlayerList = villagePlayerList;
    }

    /** VOTE by CHARA_ID, named 'voteByCharaIdList'. */
    protected List<Vote> _voteByCharaIdList;

    /**
     * [get] VOTE by CHARA_ID, named 'voteByCharaIdList'.
     * @return The entity list of referrer property 'voteByCharaIdList'. (NotNull: even if no loading, returns empty list)
     */
    public List<Vote> getVoteByCharaIdList() {
        if (_voteByCharaIdList == null) { _voteByCharaIdList = newReferrerList(); }
        return _voteByCharaIdList;
    }

    /**
     * [set] VOTE by CHARA_ID, named 'voteByCharaIdList'.
     * @param voteByCharaIdList The entity list of referrer property 'voteByCharaIdList'. (NullAllowed)
     */
    public void setVoteByCharaIdList(List<Vote> voteByCharaIdList) {
        _voteByCharaIdList = voteByCharaIdList;
    }

    /** VOTE by VOTE_CHARA_ID, named 'voteByVoteCharaIdList'. */
    protected List<Vote> _voteByVoteCharaIdList;

    /**
     * [get] VOTE by VOTE_CHARA_ID, named 'voteByVoteCharaIdList'.
     * @return The entity list of referrer property 'voteByVoteCharaIdList'. (NotNull: even if no loading, returns empty list)
     */
    public List<Vote> getVoteByVoteCharaIdList() {
        if (_voteByVoteCharaIdList == null) { _voteByVoteCharaIdList = newReferrerList(); }
        return _voteByVoteCharaIdList;
    }

    /**
     * [set] VOTE by VOTE_CHARA_ID, named 'voteByVoteCharaIdList'.
     * @param voteByVoteCharaIdList The entity list of referrer property 'voteByVoteCharaIdList'. (NullAllowed)
     */
    public void setVoteByVoteCharaIdList(List<Vote> voteByVoteCharaIdList) {
        _voteByVoteCharaIdList = voteByVoteCharaIdList;
    }

    protected <ELEMENT> List<ELEMENT> newReferrerList() { // overriding to import
        return new ArrayList<ELEMENT>();
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    protected boolean doEquals(Object obj) {
        if (obj instanceof BsChara) {
            BsChara other = (BsChara)obj;
            if (!xSV(_charaId, other._charaId)) { return false; }
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected int doHashCode(int initial) {
        int hs = initial;
        hs = xCH(hs, asTableDbName());
        hs = xCH(hs, _charaId);
        return hs;
    }

    @Override
    protected String doBuildStringWithRelation(String li) {
        StringBuilder sb = new StringBuilder();
        if (_charaGroup != null && _charaGroup.isPresent())
        { sb.append(li).append(xbRDS(_charaGroup, "charaGroup")); }
        if (_abilityByCharaIdList != null) { for (Ability et : _abilityByCharaIdList)
        { if (et != null) { sb.append(li).append(xbRDS(et, "abilityByCharaIdList")); } } }
        if (_abilityByTargetCharaIdList != null) { for (Ability et : _abilityByTargetCharaIdList)
        { if (et != null) { sb.append(li).append(xbRDS(et, "abilityByTargetCharaIdList")); } } }
        if (_charaImageList != null) { for (CharaImage et : _charaImageList)
        { if (et != null) { sb.append(li).append(xbRDS(et, "charaImageList")); } } }
        if (_footstepList != null) { for (Footstep et : _footstepList)
        { if (et != null) { sb.append(li).append(xbRDS(et, "footstepList")); } } }
        if (_villagePlayerList != null) { for (VillagePlayer et : _villagePlayerList)
        { if (et != null) { sb.append(li).append(xbRDS(et, "villagePlayerList")); } } }
        if (_voteByCharaIdList != null) { for (Vote et : _voteByCharaIdList)
        { if (et != null) { sb.append(li).append(xbRDS(et, "voteByCharaIdList")); } } }
        if (_voteByVoteCharaIdList != null) { for (Vote et : _voteByVoteCharaIdList)
        { if (et != null) { sb.append(li).append(xbRDS(et, "voteByVoteCharaIdList")); } } }
        return sb.toString();
    }
    protected <ET extends Entity> String xbRDS(org.dbflute.optional.OptionalEntity<ET> et, String name) { // buildRelationDisplayString()
        return et.get().buildDisplayString(name, true, true);
    }

    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(xfND(_charaId));
        sb.append(dm).append(xfND(_charaName));
        sb.append(dm).append(xfND(_charaShortName));
        sb.append(dm).append(xfND(_charaGroupId));
        sb.append(dm).append(xfND(_defaultJoinMessage));
        sb.append(dm).append(xfND(_defaultFirstdayMessage));
        sb.append(dm).append(xfND(_displayWidth));
        sb.append(dm).append(xfND(_displayHeight));
        sb.append(dm).append(xfND(_registerDatetime));
        sb.append(dm).append(xfND(_registerTrace));
        sb.append(dm).append(xfND(_updateDatetime));
        sb.append(dm).append(xfND(_updateTrace));
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    @Override
    protected String doBuildRelationString(String dm) {
        StringBuilder sb = new StringBuilder();
        if (_charaGroup != null && _charaGroup.isPresent())
        { sb.append(dm).append("charaGroup"); }
        if (_abilityByCharaIdList != null && !_abilityByCharaIdList.isEmpty())
        { sb.append(dm).append("abilityByCharaIdList"); }
        if (_abilityByTargetCharaIdList != null && !_abilityByTargetCharaIdList.isEmpty())
        { sb.append(dm).append("abilityByTargetCharaIdList"); }
        if (_charaImageList != null && !_charaImageList.isEmpty())
        { sb.append(dm).append("charaImageList"); }
        if (_footstepList != null && !_footstepList.isEmpty())
        { sb.append(dm).append("footstepList"); }
        if (_villagePlayerList != null && !_villagePlayerList.isEmpty())
        { sb.append(dm).append("villagePlayerList"); }
        if (_voteByCharaIdList != null && !_voteByCharaIdList.isEmpty())
        { sb.append(dm).append("voteByCharaIdList"); }
        if (_voteByVoteCharaIdList != null && !_voteByVoteCharaIdList.isEmpty())
        { sb.append(dm).append("voteByVoteCharaIdList"); }
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length()).insert(0, "(").append(")");
        }
        return sb.toString();
    }

    @Override
    public Chara clone() {
        return (Chara)super.clone();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * [get] CHARA_ID: {PK, ID, NotNull, INT UNSIGNED(10)} <br>
     * キャラクターID
     * @return The value of the column 'CHARA_ID'. (basically NotNull if selected: for the constraint)
     */
    public Integer getCharaId() {
        checkSpecifiedProperty("charaId");
        return _charaId;
    }

    /**
     * [set] CHARA_ID: {PK, ID, NotNull, INT UNSIGNED(10)} <br>
     * キャラクターID
     * @param charaId The value of the column 'CHARA_ID'. (basically NotNull if update: for the constraint)
     */
    public void setCharaId(Integer charaId) {
        registerModifiedProperty("charaId");
        _charaId = charaId;
    }

    /**
     * [get] CHARA_NAME: {NotNull, VARCHAR(40)} <br>
     * キャラクター名
     * @return The value of the column 'CHARA_NAME'. (basically NotNull if selected: for the constraint)
     */
    public String getCharaName() {
        checkSpecifiedProperty("charaName");
        return convertEmptyToNull(_charaName);
    }

    /**
     * [set] CHARA_NAME: {NotNull, VARCHAR(40)} <br>
     * キャラクター名
     * @param charaName The value of the column 'CHARA_NAME'. (basically NotNull if update: for the constraint)
     */
    public void setCharaName(String charaName) {
        registerModifiedProperty("charaName");
        _charaName = charaName;
    }

    /**
     * [get] CHARA_SHORT_NAME: {NotNull, CHAR(1)} <br>
     * キャラクター略称
     * @return The value of the column 'CHARA_SHORT_NAME'. (basically NotNull if selected: for the constraint)
     */
    public String getCharaShortName() {
        checkSpecifiedProperty("charaShortName");
        return convertEmptyToNull(_charaShortName);
    }

    /**
     * [set] CHARA_SHORT_NAME: {NotNull, CHAR(1)} <br>
     * キャラクター略称
     * @param charaShortName The value of the column 'CHARA_SHORT_NAME'. (basically NotNull if update: for the constraint)
     */
    public void setCharaShortName(String charaShortName) {
        registerModifiedProperty("charaShortName");
        _charaShortName = charaShortName;
    }

    /**
     * [get] CHARA_GROUP_ID: {IX, NotNull, INT UNSIGNED(10), FK to chara_group} <br>
     * キャラクターグループID
     * @return The value of the column 'CHARA_GROUP_ID'. (basically NotNull if selected: for the constraint)
     */
    public Integer getCharaGroupId() {
        checkSpecifiedProperty("charaGroupId");
        return _charaGroupId;
    }

    /**
     * [set] CHARA_GROUP_ID: {IX, NotNull, INT UNSIGNED(10), FK to chara_group} <br>
     * キャラクターグループID
     * @param charaGroupId The value of the column 'CHARA_GROUP_ID'. (basically NotNull if update: for the constraint)
     */
    public void setCharaGroupId(Integer charaGroupId) {
        registerModifiedProperty("charaGroupId");
        _charaGroupId = charaGroupId;
    }

    /**
     * [get] DEFAULT_JOIN_MESSAGE: {VARCHAR(200)} <br>
     * 入村時デフォルト発言
     * @return The value of the column 'DEFAULT_JOIN_MESSAGE'. (NullAllowed even if selected: for no constraint)
     */
    public String getDefaultJoinMessage() {
        checkSpecifiedProperty("defaultJoinMessage");
        return convertEmptyToNull(_defaultJoinMessage);
    }

    /**
     * [set] DEFAULT_JOIN_MESSAGE: {VARCHAR(200)} <br>
     * 入村時デフォルト発言
     * @param defaultJoinMessage The value of the column 'DEFAULT_JOIN_MESSAGE'. (NullAllowed: null update allowed for no constraint)
     */
    public void setDefaultJoinMessage(String defaultJoinMessage) {
        registerModifiedProperty("defaultJoinMessage");
        _defaultJoinMessage = defaultJoinMessage;
    }

    /**
     * [get] DEFAULT_FIRSTDAY_MESSAGE: {VARCHAR(200)} <br>
     * デフォルト1日目発言
     * @return The value of the column 'DEFAULT_FIRSTDAY_MESSAGE'. (NullAllowed even if selected: for no constraint)
     */
    public String getDefaultFirstdayMessage() {
        checkSpecifiedProperty("defaultFirstdayMessage");
        return convertEmptyToNull(_defaultFirstdayMessage);
    }

    /**
     * [set] DEFAULT_FIRSTDAY_MESSAGE: {VARCHAR(200)} <br>
     * デフォルト1日目発言
     * @param defaultFirstdayMessage The value of the column 'DEFAULT_FIRSTDAY_MESSAGE'. (NullAllowed: null update allowed for no constraint)
     */
    public void setDefaultFirstdayMessage(String defaultFirstdayMessage) {
        registerModifiedProperty("defaultFirstdayMessage");
        _defaultFirstdayMessage = defaultFirstdayMessage;
    }

    /**
     * [get] DISPLAY_WIDTH: {NotNull, INT UNSIGNED(10)} <br>
     * 表示時横幅
     * @return The value of the column 'DISPLAY_WIDTH'. (basically NotNull if selected: for the constraint)
     */
    public Integer getDisplayWidth() {
        checkSpecifiedProperty("displayWidth");
        return _displayWidth;
    }

    /**
     * [set] DISPLAY_WIDTH: {NotNull, INT UNSIGNED(10)} <br>
     * 表示時横幅
     * @param displayWidth The value of the column 'DISPLAY_WIDTH'. (basically NotNull if update: for the constraint)
     */
    public void setDisplayWidth(Integer displayWidth) {
        registerModifiedProperty("displayWidth");
        _displayWidth = displayWidth;
    }

    /**
     * [get] DISPLAY_HEIGHT: {NotNull, INT UNSIGNED(10)} <br>
     * 表示時縦幅
     * @return The value of the column 'DISPLAY_HEIGHT'. (basically NotNull if selected: for the constraint)
     */
    public Integer getDisplayHeight() {
        checkSpecifiedProperty("displayHeight");
        return _displayHeight;
    }

    /**
     * [set] DISPLAY_HEIGHT: {NotNull, INT UNSIGNED(10)} <br>
     * 表示時縦幅
     * @param displayHeight The value of the column 'DISPLAY_HEIGHT'. (basically NotNull if update: for the constraint)
     */
    public void setDisplayHeight(Integer displayHeight) {
        registerModifiedProperty("displayHeight");
        _displayHeight = displayHeight;
    }

    /**
     * [get] REGISTER_DATETIME: {NotNull, DATETIME(19)} <br>
     * 登録日時
     * @return The value of the column 'REGISTER_DATETIME'. (basically NotNull if selected: for the constraint)
     */
    public java.time.LocalDateTime getRegisterDatetime() {
        checkSpecifiedProperty("registerDatetime");
        return _registerDatetime;
    }

    /**
     * [set] REGISTER_DATETIME: {NotNull, DATETIME(19)} <br>
     * 登録日時
     * @param registerDatetime The value of the column 'REGISTER_DATETIME'. (basically NotNull if update: for the constraint)
     */
    public void setRegisterDatetime(java.time.LocalDateTime registerDatetime) {
        registerModifiedProperty("registerDatetime");
        _registerDatetime = registerDatetime;
    }

    /**
     * [get] REGISTER_TRACE: {NotNull, VARCHAR(64)} <br>
     * 登録トレース
     * @return The value of the column 'REGISTER_TRACE'. (basically NotNull if selected: for the constraint)
     */
    public String getRegisterTrace() {
        checkSpecifiedProperty("registerTrace");
        return convertEmptyToNull(_registerTrace);
    }

    /**
     * [set] REGISTER_TRACE: {NotNull, VARCHAR(64)} <br>
     * 登録トレース
     * @param registerTrace The value of the column 'REGISTER_TRACE'. (basically NotNull if update: for the constraint)
     */
    public void setRegisterTrace(String registerTrace) {
        registerModifiedProperty("registerTrace");
        _registerTrace = registerTrace;
    }

    /**
     * [get] UPDATE_DATETIME: {NotNull, DATETIME(19)} <br>
     * 更新日時
     * @return The value of the column 'UPDATE_DATETIME'. (basically NotNull if selected: for the constraint)
     */
    public java.time.LocalDateTime getUpdateDatetime() {
        checkSpecifiedProperty("updateDatetime");
        return _updateDatetime;
    }

    /**
     * [set] UPDATE_DATETIME: {NotNull, DATETIME(19)} <br>
     * 更新日時
     * @param updateDatetime The value of the column 'UPDATE_DATETIME'. (basically NotNull if update: for the constraint)
     */
    public void setUpdateDatetime(java.time.LocalDateTime updateDatetime) {
        registerModifiedProperty("updateDatetime");
        _updateDatetime = updateDatetime;
    }

    /**
     * [get] UPDATE_TRACE: {NotNull, VARCHAR(64)} <br>
     * 更新トレース
     * @return The value of the column 'UPDATE_TRACE'. (basically NotNull if selected: for the constraint)
     */
    public String getUpdateTrace() {
        checkSpecifiedProperty("updateTrace");
        return convertEmptyToNull(_updateTrace);
    }

    /**
     * [set] UPDATE_TRACE: {NotNull, VARCHAR(64)} <br>
     * 更新トレース
     * @param updateTrace The value of the column 'UPDATE_TRACE'. (basically NotNull if update: for the constraint)
     */
    public void setUpdateTrace(String updateTrace) {
        registerModifiedProperty("updateTrace");
        _updateTrace = updateTrace;
    }
}
