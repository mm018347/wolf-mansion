package com.ort.dbflute.cbean.cq.bs;

import java.util.*;

import org.dbflute.cbean.*;
import org.dbflute.cbean.chelper.*;
import org.dbflute.cbean.ckey.*;
import org.dbflute.cbean.coption.*;
import org.dbflute.cbean.cvalue.ConditionValue;
import org.dbflute.cbean.ordering.*;
import org.dbflute.cbean.scoping.*;
import org.dbflute.cbean.sqlclause.SqlClause;
import org.dbflute.dbmeta.DBMetaProvider;
import com.ort.dbflute.allcommon.*;
import com.ort.dbflute.cbean.*;
import com.ort.dbflute.cbean.cq.*;

/**
 * The abstract condition-query of village_player.
 * @author DBFlute(AutoGenerator)
 */
public abstract class AbstractBsVillagePlayerCQ extends AbstractConditionQuery {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public AbstractBsVillagePlayerCQ(ConditionQuery referrerQuery, SqlClause sqlClause, String aliasName, int nestLevel) {
        super(referrerQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    @Override
    protected DBMetaProvider xgetDBMetaProvider() {
        return DBMetaInstanceHandler.getProvider();
    }

    public String asTableDbName() {
        return "village_player";
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====
    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * VILLAGE_PLAYER_ID: {PK, ID, NotNull, INT UNSIGNED(10)}
     * @param villagePlayerId The value of villagePlayerId as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setVillagePlayerId_Equal(Integer villagePlayerId) {
        doSetVillagePlayerId_Equal(villagePlayerId);
    }

    protected void doSetVillagePlayerId_Equal(Integer villagePlayerId) {
        regVillagePlayerId(CK_EQ, villagePlayerId);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * VILLAGE_PLAYER_ID: {PK, ID, NotNull, INT UNSIGNED(10)}
     * @param villagePlayerId The value of villagePlayerId as notEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setVillagePlayerId_NotEqual(Integer villagePlayerId) {
        doSetVillagePlayerId_NotEqual(villagePlayerId);
    }

    protected void doSetVillagePlayerId_NotEqual(Integer villagePlayerId) {
        regVillagePlayerId(CK_NES, villagePlayerId);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * VILLAGE_PLAYER_ID: {PK, ID, NotNull, INT UNSIGNED(10)}
     * @param villagePlayerId The value of villagePlayerId as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setVillagePlayerId_GreaterThan(Integer villagePlayerId) {
        regVillagePlayerId(CK_GT, villagePlayerId);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * VILLAGE_PLAYER_ID: {PK, ID, NotNull, INT UNSIGNED(10)}
     * @param villagePlayerId The value of villagePlayerId as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setVillagePlayerId_LessThan(Integer villagePlayerId) {
        regVillagePlayerId(CK_LT, villagePlayerId);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * VILLAGE_PLAYER_ID: {PK, ID, NotNull, INT UNSIGNED(10)}
     * @param villagePlayerId The value of villagePlayerId as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setVillagePlayerId_GreaterEqual(Integer villagePlayerId) {
        regVillagePlayerId(CK_GE, villagePlayerId);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * VILLAGE_PLAYER_ID: {PK, ID, NotNull, INT UNSIGNED(10)}
     * @param villagePlayerId The value of villagePlayerId as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setVillagePlayerId_LessEqual(Integer villagePlayerId) {
        regVillagePlayerId(CK_LE, villagePlayerId);
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * VILLAGE_PLAYER_ID: {PK, ID, NotNull, INT UNSIGNED(10)}
     * @param minNumber The min number of villagePlayerId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of villagePlayerId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of range-of. (NotNull)
     */
    public void setVillagePlayerId_RangeOf(Integer minNumber, Integer maxNumber, ConditionOptionCall<RangeOfOption> opLambda) {
        setVillagePlayerId_RangeOf(minNumber, maxNumber, xcROOP(opLambda));
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * VILLAGE_PLAYER_ID: {PK, ID, NotNull, INT UNSIGNED(10)}
     * @param minNumber The min number of villagePlayerId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of villagePlayerId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    protected void setVillagePlayerId_RangeOf(Integer minNumber, Integer maxNumber, RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, xgetCValueVillagePlayerId(), "VILLAGE_PLAYER_ID", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * VILLAGE_PLAYER_ID: {PK, ID, NotNull, INT UNSIGNED(10)}
     * @param villagePlayerIdList The collection of villagePlayerId as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setVillagePlayerId_InScope(Collection<Integer> villagePlayerIdList) {
        doSetVillagePlayerId_InScope(villagePlayerIdList);
    }

    protected void doSetVillagePlayerId_InScope(Collection<Integer> villagePlayerIdList) {
        regINS(CK_INS, cTL(villagePlayerIdList), xgetCValueVillagePlayerId(), "VILLAGE_PLAYER_ID");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * VILLAGE_PLAYER_ID: {PK, ID, NotNull, INT UNSIGNED(10)}
     * @param villagePlayerIdList The collection of villagePlayerId as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setVillagePlayerId_NotInScope(Collection<Integer> villagePlayerIdList) {
        doSetVillagePlayerId_NotInScope(villagePlayerIdList);
    }

    protected void doSetVillagePlayerId_NotInScope(Collection<Integer> villagePlayerIdList) {
        regINS(CK_NINS, cTL(villagePlayerIdList), xgetCValueVillagePlayerId(), "VILLAGE_PLAYER_ID");
    }

    /**
     * Set up ExistsReferrer (correlated sub-query). <br>
     * {exists (select VILLAGE_PLAYER_ID from commit where ...)} <br>
     * commit by VILLAGE_PLAYER_ID, named 'commitAsOne'.
     * <pre>
     * cb.query().<span style="color: #CC4747">existsCommit</span>(commitCB <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     commitCB.query().set...
     * });
     * </pre>
     * @param subCBLambda The callback for sub-query of CommitList for 'exists'. (NotNull)
     */
    public void existsCommit(SubQuery<CommitCB> subCBLambda) {
        assertObjectNotNull("subCBLambda", subCBLambda);
        CommitCB cb = new CommitCB(); cb.xsetupForExistsReferrer(this);
        lockCall(() -> subCBLambda.query(cb)); String pp = keepVillagePlayerId_ExistsReferrer_CommitList(cb.query());
        registerExistsReferrer(cb.query(), "VILLAGE_PLAYER_ID", "VILLAGE_PLAYER_ID", pp, "commitList");
    }
    public abstract String keepVillagePlayerId_ExistsReferrer_CommitList(CommitCQ sq);

    /**
     * Set up ExistsReferrer (correlated sub-query). <br>
     * {exists (select TO_VILLAGE_PLAYER_ID from message where ...)} <br>
     * message by TO_VILLAGE_PLAYER_ID, named 'messageByToVillagePlayerIdAsOne'.
     * <pre>
     * cb.query().<span style="color: #CC4747">existsMessageByToVillagePlayerId</span>(messageCB <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     messageCB.query().set...
     * });
     * </pre>
     * @param subCBLambda The callback for sub-query of MessageByToVillagePlayerIdList for 'exists'. (NotNull)
     */
    public void existsMessageByToVillagePlayerId(SubQuery<MessageCB> subCBLambda) {
        assertObjectNotNull("subCBLambda", subCBLambda);
        MessageCB cb = new MessageCB(); cb.xsetupForExistsReferrer(this);
        lockCall(() -> subCBLambda.query(cb)); String pp = keepVillagePlayerId_ExistsReferrer_MessageByToVillagePlayerIdList(cb.query());
        registerExistsReferrer(cb.query(), "VILLAGE_PLAYER_ID", "TO_VILLAGE_PLAYER_ID", pp, "messageByToVillagePlayerIdList");
    }
    public abstract String keepVillagePlayerId_ExistsReferrer_MessageByToVillagePlayerIdList(MessageCQ sq);

    /**
     * Set up ExistsReferrer (correlated sub-query). <br>
     * {exists (select VILLAGE_PLAYER_ID from message where ...)} <br>
     * message by VILLAGE_PLAYER_ID, named 'messageByVillagePlayerIdAsOne'.
     * <pre>
     * cb.query().<span style="color: #CC4747">existsMessageByVillagePlayerId</span>(messageCB <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     messageCB.query().set...
     * });
     * </pre>
     * @param subCBLambda The callback for sub-query of MessageByVillagePlayerIdList for 'exists'. (NotNull)
     */
    public void existsMessageByVillagePlayerId(SubQuery<MessageCB> subCBLambda) {
        assertObjectNotNull("subCBLambda", subCBLambda);
        MessageCB cb = new MessageCB(); cb.xsetupForExistsReferrer(this);
        lockCall(() -> subCBLambda.query(cb)); String pp = keepVillagePlayerId_ExistsReferrer_MessageByVillagePlayerIdList(cb.query());
        registerExistsReferrer(cb.query(), "VILLAGE_PLAYER_ID", "VILLAGE_PLAYER_ID", pp, "messageByVillagePlayerIdList");
    }
    public abstract String keepVillagePlayerId_ExistsReferrer_MessageByVillagePlayerIdList(MessageCQ sq);

    /**
     * Set up NotExistsReferrer (correlated sub-query). <br>
     * {not exists (select VILLAGE_PLAYER_ID from commit where ...)} <br>
     * commit by VILLAGE_PLAYER_ID, named 'commitAsOne'.
     * <pre>
     * cb.query().<span style="color: #CC4747">notExistsCommit</span>(commitCB <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     commitCB.query().set...
     * });
     * </pre>
     * @param subCBLambda The callback for sub-query of VillagePlayerId_NotExistsReferrer_CommitList for 'not exists'. (NotNull)
     */
    public void notExistsCommit(SubQuery<CommitCB> subCBLambda) {
        assertObjectNotNull("subCBLambda", subCBLambda);
        CommitCB cb = new CommitCB(); cb.xsetupForExistsReferrer(this);
        lockCall(() -> subCBLambda.query(cb)); String pp = keepVillagePlayerId_NotExistsReferrer_CommitList(cb.query());
        registerNotExistsReferrer(cb.query(), "VILLAGE_PLAYER_ID", "VILLAGE_PLAYER_ID", pp, "commitList");
    }
    public abstract String keepVillagePlayerId_NotExistsReferrer_CommitList(CommitCQ sq);

    /**
     * Set up NotExistsReferrer (correlated sub-query). <br>
     * {not exists (select TO_VILLAGE_PLAYER_ID from message where ...)} <br>
     * message by TO_VILLAGE_PLAYER_ID, named 'messageByToVillagePlayerIdAsOne'.
     * <pre>
     * cb.query().<span style="color: #CC4747">notExistsMessageByToVillagePlayerId</span>(messageCB <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     messageCB.query().set...
     * });
     * </pre>
     * @param subCBLambda The callback for sub-query of VillagePlayerId_NotExistsReferrer_MessageByToVillagePlayerIdList for 'not exists'. (NotNull)
     */
    public void notExistsMessageByToVillagePlayerId(SubQuery<MessageCB> subCBLambda) {
        assertObjectNotNull("subCBLambda", subCBLambda);
        MessageCB cb = new MessageCB(); cb.xsetupForExistsReferrer(this);
        lockCall(() -> subCBLambda.query(cb)); String pp = keepVillagePlayerId_NotExistsReferrer_MessageByToVillagePlayerIdList(cb.query());
        registerNotExistsReferrer(cb.query(), "VILLAGE_PLAYER_ID", "TO_VILLAGE_PLAYER_ID", pp, "messageByToVillagePlayerIdList");
    }
    public abstract String keepVillagePlayerId_NotExistsReferrer_MessageByToVillagePlayerIdList(MessageCQ sq);

    /**
     * Set up NotExistsReferrer (correlated sub-query). <br>
     * {not exists (select VILLAGE_PLAYER_ID from message where ...)} <br>
     * message by VILLAGE_PLAYER_ID, named 'messageByVillagePlayerIdAsOne'.
     * <pre>
     * cb.query().<span style="color: #CC4747">notExistsMessageByVillagePlayerId</span>(messageCB <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     messageCB.query().set...
     * });
     * </pre>
     * @param subCBLambda The callback for sub-query of VillagePlayerId_NotExistsReferrer_MessageByVillagePlayerIdList for 'not exists'. (NotNull)
     */
    public void notExistsMessageByVillagePlayerId(SubQuery<MessageCB> subCBLambda) {
        assertObjectNotNull("subCBLambda", subCBLambda);
        MessageCB cb = new MessageCB(); cb.xsetupForExistsReferrer(this);
        lockCall(() -> subCBLambda.query(cb)); String pp = keepVillagePlayerId_NotExistsReferrer_MessageByVillagePlayerIdList(cb.query());
        registerNotExistsReferrer(cb.query(), "VILLAGE_PLAYER_ID", "VILLAGE_PLAYER_ID", pp, "messageByVillagePlayerIdList");
    }
    public abstract String keepVillagePlayerId_NotExistsReferrer_MessageByVillagePlayerIdList(MessageCQ sq);

    public void xsderiveCommitList(String fn, SubQuery<CommitCB> sq, String al, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        CommitCB cb = new CommitCB(); cb.xsetupForDerivedReferrer(this);
        lockCall(() -> sq.query(cb)); String pp = keepVillagePlayerId_SpecifyDerivedReferrer_CommitList(cb.query());
        registerSpecifyDerivedReferrer(fn, cb.query(), "VILLAGE_PLAYER_ID", "VILLAGE_PLAYER_ID", pp, "commitList", al, op);
    }
    public abstract String keepVillagePlayerId_SpecifyDerivedReferrer_CommitList(CommitCQ sq);

    public void xsderiveMessageByToVillagePlayerIdList(String fn, SubQuery<MessageCB> sq, String al, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        MessageCB cb = new MessageCB(); cb.xsetupForDerivedReferrer(this);
        lockCall(() -> sq.query(cb)); String pp = keepVillagePlayerId_SpecifyDerivedReferrer_MessageByToVillagePlayerIdList(cb.query());
        registerSpecifyDerivedReferrer(fn, cb.query(), "VILLAGE_PLAYER_ID", "TO_VILLAGE_PLAYER_ID", pp, "messageByToVillagePlayerIdList", al, op);
    }
    public abstract String keepVillagePlayerId_SpecifyDerivedReferrer_MessageByToVillagePlayerIdList(MessageCQ sq);

    public void xsderiveMessageByVillagePlayerIdList(String fn, SubQuery<MessageCB> sq, String al, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        MessageCB cb = new MessageCB(); cb.xsetupForDerivedReferrer(this);
        lockCall(() -> sq.query(cb)); String pp = keepVillagePlayerId_SpecifyDerivedReferrer_MessageByVillagePlayerIdList(cb.query());
        registerSpecifyDerivedReferrer(fn, cb.query(), "VILLAGE_PLAYER_ID", "VILLAGE_PLAYER_ID", pp, "messageByVillagePlayerIdList", al, op);
    }
    public abstract String keepVillagePlayerId_SpecifyDerivedReferrer_MessageByVillagePlayerIdList(MessageCQ sq);

    /**
     * Prepare for (Query)DerivedReferrer (correlated sub-query). <br>
     * {FOO &lt;= (select max(BAR) from commit where ...)} <br>
     * commit by VILLAGE_PLAYER_ID, named 'commitAsOne'.
     * <pre>
     * cb.query().<span style="color: #CC4747">derivedCommit()</span>.<span style="color: #CC4747">max</span>(commitCB <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     commitCB.specify().<span style="color: #CC4747">columnFoo...</span> <span style="color: #3F7E5E">// derived column by function</span>
     *     commitCB.query().setBar... <span style="color: #3F7E5E">// referrer condition</span>
     * }).<span style="color: #CC4747">greaterEqual</span>(123); <span style="color: #3F7E5E">// condition to derived column</span>
     * </pre>
     * @return The object to set up a function for referrer table. (NotNull)
     */
    public HpQDRFunction<CommitCB> derivedCommit() {
        return xcreateQDRFunctionCommitList();
    }
    protected HpQDRFunction<CommitCB> xcreateQDRFunctionCommitList() {
        return xcQDRFunc((fn, sq, rd, vl, op) -> xqderiveCommitList(fn, sq, rd, vl, op));
    }
    public void xqderiveCommitList(String fn, SubQuery<CommitCB> sq, String rd, Object vl, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        CommitCB cb = new CommitCB(); cb.xsetupForDerivedReferrer(this);
        lockCall(() -> sq.query(cb)); String sqpp = keepVillagePlayerId_QueryDerivedReferrer_CommitList(cb.query()); String prpp = keepVillagePlayerId_QueryDerivedReferrer_CommitListParameter(vl);
        registerQueryDerivedReferrer(fn, cb.query(), "VILLAGE_PLAYER_ID", "VILLAGE_PLAYER_ID", sqpp, "commitList", rd, vl, prpp, op);
    }
    public abstract String keepVillagePlayerId_QueryDerivedReferrer_CommitList(CommitCQ sq);
    public abstract String keepVillagePlayerId_QueryDerivedReferrer_CommitListParameter(Object vl);

    /**
     * Prepare for (Query)DerivedReferrer (correlated sub-query). <br>
     * {FOO &lt;= (select max(BAR) from message where ...)} <br>
     * message by TO_VILLAGE_PLAYER_ID, named 'messageByToVillagePlayerIdAsOne'.
     * <pre>
     * cb.query().<span style="color: #CC4747">derivedMessageByToVillagePlayerId()</span>.<span style="color: #CC4747">max</span>(messageCB <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     messageCB.specify().<span style="color: #CC4747">columnFoo...</span> <span style="color: #3F7E5E">// derived column by function</span>
     *     messageCB.query().setBar... <span style="color: #3F7E5E">// referrer condition</span>
     * }).<span style="color: #CC4747">greaterEqual</span>(123); <span style="color: #3F7E5E">// condition to derived column</span>
     * </pre>
     * @return The object to set up a function for referrer table. (NotNull)
     */
    public HpQDRFunction<MessageCB> derivedMessageByToVillagePlayerId() {
        return xcreateQDRFunctionMessageByToVillagePlayerIdList();
    }
    protected HpQDRFunction<MessageCB> xcreateQDRFunctionMessageByToVillagePlayerIdList() {
        return xcQDRFunc((fn, sq, rd, vl, op) -> xqderiveMessageByToVillagePlayerIdList(fn, sq, rd, vl, op));
    }
    public void xqderiveMessageByToVillagePlayerIdList(String fn, SubQuery<MessageCB> sq, String rd, Object vl, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        MessageCB cb = new MessageCB(); cb.xsetupForDerivedReferrer(this);
        lockCall(() -> sq.query(cb)); String sqpp = keepVillagePlayerId_QueryDerivedReferrer_MessageByToVillagePlayerIdList(cb.query()); String prpp = keepVillagePlayerId_QueryDerivedReferrer_MessageByToVillagePlayerIdListParameter(vl);
        registerQueryDerivedReferrer(fn, cb.query(), "VILLAGE_PLAYER_ID", "TO_VILLAGE_PLAYER_ID", sqpp, "messageByToVillagePlayerIdList", rd, vl, prpp, op);
    }
    public abstract String keepVillagePlayerId_QueryDerivedReferrer_MessageByToVillagePlayerIdList(MessageCQ sq);
    public abstract String keepVillagePlayerId_QueryDerivedReferrer_MessageByToVillagePlayerIdListParameter(Object vl);

    /**
     * Prepare for (Query)DerivedReferrer (correlated sub-query). <br>
     * {FOO &lt;= (select max(BAR) from message where ...)} <br>
     * message by VILLAGE_PLAYER_ID, named 'messageByVillagePlayerIdAsOne'.
     * <pre>
     * cb.query().<span style="color: #CC4747">derivedMessageByVillagePlayerId()</span>.<span style="color: #CC4747">max</span>(messageCB <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     messageCB.specify().<span style="color: #CC4747">columnFoo...</span> <span style="color: #3F7E5E">// derived column by function</span>
     *     messageCB.query().setBar... <span style="color: #3F7E5E">// referrer condition</span>
     * }).<span style="color: #CC4747">greaterEqual</span>(123); <span style="color: #3F7E5E">// condition to derived column</span>
     * </pre>
     * @return The object to set up a function for referrer table. (NotNull)
     */
    public HpQDRFunction<MessageCB> derivedMessageByVillagePlayerId() {
        return xcreateQDRFunctionMessageByVillagePlayerIdList();
    }
    protected HpQDRFunction<MessageCB> xcreateQDRFunctionMessageByVillagePlayerIdList() {
        return xcQDRFunc((fn, sq, rd, vl, op) -> xqderiveMessageByVillagePlayerIdList(fn, sq, rd, vl, op));
    }
    public void xqderiveMessageByVillagePlayerIdList(String fn, SubQuery<MessageCB> sq, String rd, Object vl, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        MessageCB cb = new MessageCB(); cb.xsetupForDerivedReferrer(this);
        lockCall(() -> sq.query(cb)); String sqpp = keepVillagePlayerId_QueryDerivedReferrer_MessageByVillagePlayerIdList(cb.query()); String prpp = keepVillagePlayerId_QueryDerivedReferrer_MessageByVillagePlayerIdListParameter(vl);
        registerQueryDerivedReferrer(fn, cb.query(), "VILLAGE_PLAYER_ID", "VILLAGE_PLAYER_ID", sqpp, "messageByVillagePlayerIdList", rd, vl, prpp, op);
    }
    public abstract String keepVillagePlayerId_QueryDerivedReferrer_MessageByVillagePlayerIdList(MessageCQ sq);
    public abstract String keepVillagePlayerId_QueryDerivedReferrer_MessageByVillagePlayerIdListParameter(Object vl);

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * VILLAGE_PLAYER_ID: {PK, ID, NotNull, INT UNSIGNED(10)}
     */
    public void setVillagePlayerId_IsNull() { regVillagePlayerId(CK_ISN, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * VILLAGE_PLAYER_ID: {PK, ID, NotNull, INT UNSIGNED(10)}
     */
    public void setVillagePlayerId_IsNotNull() { regVillagePlayerId(CK_ISNN, DOBJ); }

    protected void regVillagePlayerId(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueVillagePlayerId(), "VILLAGE_PLAYER_ID"); }
    protected abstract ConditionValue xgetCValueVillagePlayerId();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * VILLAGE_ID: {IX, NotNull, INT UNSIGNED(10), FK to village}
     * @param villageId The value of villageId as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setVillageId_Equal(Integer villageId) {
        doSetVillageId_Equal(villageId);
    }

    protected void doSetVillageId_Equal(Integer villageId) {
        regVillageId(CK_EQ, villageId);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * VILLAGE_ID: {IX, NotNull, INT UNSIGNED(10), FK to village}
     * @param villageId The value of villageId as notEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setVillageId_NotEqual(Integer villageId) {
        doSetVillageId_NotEqual(villageId);
    }

    protected void doSetVillageId_NotEqual(Integer villageId) {
        regVillageId(CK_NES, villageId);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * VILLAGE_ID: {IX, NotNull, INT UNSIGNED(10), FK to village}
     * @param villageId The value of villageId as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setVillageId_GreaterThan(Integer villageId) {
        regVillageId(CK_GT, villageId);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * VILLAGE_ID: {IX, NotNull, INT UNSIGNED(10), FK to village}
     * @param villageId The value of villageId as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setVillageId_LessThan(Integer villageId) {
        regVillageId(CK_LT, villageId);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * VILLAGE_ID: {IX, NotNull, INT UNSIGNED(10), FK to village}
     * @param villageId The value of villageId as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setVillageId_GreaterEqual(Integer villageId) {
        regVillageId(CK_GE, villageId);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * VILLAGE_ID: {IX, NotNull, INT UNSIGNED(10), FK to village}
     * @param villageId The value of villageId as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setVillageId_LessEqual(Integer villageId) {
        regVillageId(CK_LE, villageId);
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * VILLAGE_ID: {IX, NotNull, INT UNSIGNED(10), FK to village}
     * @param minNumber The min number of villageId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of villageId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of range-of. (NotNull)
     */
    public void setVillageId_RangeOf(Integer minNumber, Integer maxNumber, ConditionOptionCall<RangeOfOption> opLambda) {
        setVillageId_RangeOf(minNumber, maxNumber, xcROOP(opLambda));
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * VILLAGE_ID: {IX, NotNull, INT UNSIGNED(10), FK to village}
     * @param minNumber The min number of villageId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of villageId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    protected void setVillageId_RangeOf(Integer minNumber, Integer maxNumber, RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, xgetCValueVillageId(), "VILLAGE_ID", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * VILLAGE_ID: {IX, NotNull, INT UNSIGNED(10), FK to village}
     * @param villageIdList The collection of villageId as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setVillageId_InScope(Collection<Integer> villageIdList) {
        doSetVillageId_InScope(villageIdList);
    }

    protected void doSetVillageId_InScope(Collection<Integer> villageIdList) {
        regINS(CK_INS, cTL(villageIdList), xgetCValueVillageId(), "VILLAGE_ID");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * VILLAGE_ID: {IX, NotNull, INT UNSIGNED(10), FK to village}
     * @param villageIdList The collection of villageId as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setVillageId_NotInScope(Collection<Integer> villageIdList) {
        doSetVillageId_NotInScope(villageIdList);
    }

    protected void doSetVillageId_NotInScope(Collection<Integer> villageIdList) {
        regINS(CK_NINS, cTL(villageIdList), xgetCValueVillageId(), "VILLAGE_ID");
    }

    protected void regVillageId(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueVillageId(), "VILLAGE_ID"); }
    protected abstract ConditionValue xgetCValueVillageId();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * PLAYER_ID: {IX, NotNull, INT UNSIGNED(10), FK to player}
     * @param playerId The value of playerId as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setPlayerId_Equal(Integer playerId) {
        doSetPlayerId_Equal(playerId);
    }

    protected void doSetPlayerId_Equal(Integer playerId) {
        regPlayerId(CK_EQ, playerId);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * PLAYER_ID: {IX, NotNull, INT UNSIGNED(10), FK to player}
     * @param playerId The value of playerId as notEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setPlayerId_NotEqual(Integer playerId) {
        doSetPlayerId_NotEqual(playerId);
    }

    protected void doSetPlayerId_NotEqual(Integer playerId) {
        regPlayerId(CK_NES, playerId);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * PLAYER_ID: {IX, NotNull, INT UNSIGNED(10), FK to player}
     * @param playerId The value of playerId as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setPlayerId_GreaterThan(Integer playerId) {
        regPlayerId(CK_GT, playerId);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * PLAYER_ID: {IX, NotNull, INT UNSIGNED(10), FK to player}
     * @param playerId The value of playerId as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setPlayerId_LessThan(Integer playerId) {
        regPlayerId(CK_LT, playerId);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * PLAYER_ID: {IX, NotNull, INT UNSIGNED(10), FK to player}
     * @param playerId The value of playerId as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setPlayerId_GreaterEqual(Integer playerId) {
        regPlayerId(CK_GE, playerId);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * PLAYER_ID: {IX, NotNull, INT UNSIGNED(10), FK to player}
     * @param playerId The value of playerId as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setPlayerId_LessEqual(Integer playerId) {
        regPlayerId(CK_LE, playerId);
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * PLAYER_ID: {IX, NotNull, INT UNSIGNED(10), FK to player}
     * @param minNumber The min number of playerId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of playerId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of range-of. (NotNull)
     */
    public void setPlayerId_RangeOf(Integer minNumber, Integer maxNumber, ConditionOptionCall<RangeOfOption> opLambda) {
        setPlayerId_RangeOf(minNumber, maxNumber, xcROOP(opLambda));
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * PLAYER_ID: {IX, NotNull, INT UNSIGNED(10), FK to player}
     * @param minNumber The min number of playerId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of playerId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    protected void setPlayerId_RangeOf(Integer minNumber, Integer maxNumber, RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, xgetCValuePlayerId(), "PLAYER_ID", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * PLAYER_ID: {IX, NotNull, INT UNSIGNED(10), FK to player}
     * @param playerIdList The collection of playerId as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setPlayerId_InScope(Collection<Integer> playerIdList) {
        doSetPlayerId_InScope(playerIdList);
    }

    protected void doSetPlayerId_InScope(Collection<Integer> playerIdList) {
        regINS(CK_INS, cTL(playerIdList), xgetCValuePlayerId(), "PLAYER_ID");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * PLAYER_ID: {IX, NotNull, INT UNSIGNED(10), FK to player}
     * @param playerIdList The collection of playerId as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setPlayerId_NotInScope(Collection<Integer> playerIdList) {
        doSetPlayerId_NotInScope(playerIdList);
    }

    protected void doSetPlayerId_NotInScope(Collection<Integer> playerIdList) {
        regINS(CK_NINS, cTL(playerIdList), xgetCValuePlayerId(), "PLAYER_ID");
    }

    protected void regPlayerId(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValuePlayerId(), "PLAYER_ID"); }
    protected abstract ConditionValue xgetCValuePlayerId();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * CHARA_ID: {IX, NotNull, INT UNSIGNED(10), FK to chara}
     * @param charaId The value of charaId as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setCharaId_Equal(Integer charaId) {
        doSetCharaId_Equal(charaId);
    }

    protected void doSetCharaId_Equal(Integer charaId) {
        regCharaId(CK_EQ, charaId);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * CHARA_ID: {IX, NotNull, INT UNSIGNED(10), FK to chara}
     * @param charaId The value of charaId as notEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setCharaId_NotEqual(Integer charaId) {
        doSetCharaId_NotEqual(charaId);
    }

    protected void doSetCharaId_NotEqual(Integer charaId) {
        regCharaId(CK_NES, charaId);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * CHARA_ID: {IX, NotNull, INT UNSIGNED(10), FK to chara}
     * @param charaId The value of charaId as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setCharaId_GreaterThan(Integer charaId) {
        regCharaId(CK_GT, charaId);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * CHARA_ID: {IX, NotNull, INT UNSIGNED(10), FK to chara}
     * @param charaId The value of charaId as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setCharaId_LessThan(Integer charaId) {
        regCharaId(CK_LT, charaId);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * CHARA_ID: {IX, NotNull, INT UNSIGNED(10), FK to chara}
     * @param charaId The value of charaId as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setCharaId_GreaterEqual(Integer charaId) {
        regCharaId(CK_GE, charaId);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * CHARA_ID: {IX, NotNull, INT UNSIGNED(10), FK to chara}
     * @param charaId The value of charaId as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setCharaId_LessEqual(Integer charaId) {
        regCharaId(CK_LE, charaId);
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * CHARA_ID: {IX, NotNull, INT UNSIGNED(10), FK to chara}
     * @param minNumber The min number of charaId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of charaId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of range-of. (NotNull)
     */
    public void setCharaId_RangeOf(Integer minNumber, Integer maxNumber, ConditionOptionCall<RangeOfOption> opLambda) {
        setCharaId_RangeOf(minNumber, maxNumber, xcROOP(opLambda));
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * CHARA_ID: {IX, NotNull, INT UNSIGNED(10), FK to chara}
     * @param minNumber The min number of charaId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of charaId. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    protected void setCharaId_RangeOf(Integer minNumber, Integer maxNumber, RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, xgetCValueCharaId(), "CHARA_ID", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * CHARA_ID: {IX, NotNull, INT UNSIGNED(10), FK to chara}
     * @param charaIdList The collection of charaId as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setCharaId_InScope(Collection<Integer> charaIdList) {
        doSetCharaId_InScope(charaIdList);
    }

    protected void doSetCharaId_InScope(Collection<Integer> charaIdList) {
        regINS(CK_INS, cTL(charaIdList), xgetCValueCharaId(), "CHARA_ID");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * CHARA_ID: {IX, NotNull, INT UNSIGNED(10), FK to chara}
     * @param charaIdList The collection of charaId as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setCharaId_NotInScope(Collection<Integer> charaIdList) {
        doSetCharaId_NotInScope(charaIdList);
    }

    protected void doSetCharaId_NotInScope(Collection<Integer> charaIdList) {
        regINS(CK_NINS, cTL(charaIdList), xgetCValueCharaId(), "CHARA_ID");
    }

    protected void regCharaId(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueCharaId(), "CHARA_ID"); }
    protected abstract ConditionValue xgetCValueCharaId();

    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     * @param skillCode The value of skillCode as equal. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setSkillCode_Equal(String skillCode) {
        doSetSkillCode_Equal(fRES(skillCode));
    }

    /**
     * Equal(=). As Skill. And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill} <br>
     * 役職
     * @param cdef The instance of classification definition (as ENUM type). (basically NotNull: error as default, or no condition as option)
     */
    public void setSkillCode_Equal_AsSkill(CDef.Skill cdef) {
        doSetSkillCode_Equal(cdef != null ? cdef.code() : null);
    }

    /**
     * Equal(=). As 占星術師 (ASTROLOGER). And OnlyOnceRegistered. <br>
     * 占星術師
     */
    public void setSkillCode_Equal_占星術師() {
        setSkillCode_Equal_AsSkill(CDef.Skill.占星術師);
    }

    /**
     * Equal(=). As パン屋 (BAKERY). And OnlyOnceRegistered. <br>
     * パン屋
     */
    public void setSkillCode_Equal_パン屋() {
        setSkillCode_Equal_AsSkill(CDef.Skill.パン屋);
    }

    /**
     * Equal(=). As 爆弾魔 (BOMBER). And OnlyOnceRegistered. <br>
     * 爆弾魔
     */
    public void setSkillCode_Equal_爆弾魔() {
        setSkillCode_Equal_AsSkill(CDef.Skill.爆弾魔);
    }

    /**
     * Equal(=). As C国狂人 (CMADMAN). And OnlyOnceRegistered. <br>
     * C国狂人
     */
    public void setSkillCode_Equal_C国狂人() {
        setSkillCode_Equal_AsSkill(CDef.Skill.C国狂人);
    }

    /**
     * Equal(=). As 検死官 (CORONER). And OnlyOnceRegistered. <br>
     * 検死官
     */
    public void setSkillCode_Equal_検死官() {
        setSkillCode_Equal_AsSkill(CDef.Skill.検死官);
    }

    /**
     * Equal(=). As 呪狼 (CURSEWOLF). And OnlyOnceRegistered. <br>
     * 呪狼
     */
    public void setSkillCode_Equal_呪狼() {
        setSkillCode_Equal_AsSkill(CDef.Skill.呪狼);
    }

    /**
     * Equal(=). As 探偵 (DETECTIVE). And OnlyOnceRegistered. <br>
     * 探偵
     */
    public void setSkillCode_Equal_探偵() {
        setSkillCode_Equal_AsSkill(CDef.Skill.探偵);
    }

    /**
     * Equal(=). As 魔神官 (EVILMEDIUM). And OnlyOnceRegistered. <br>
     * 魔神官
     */
    public void setSkillCode_Equal_魔神官() {
        setSkillCode_Equal_AsSkill(CDef.Skill.魔神官);
    }

    /**
     * Equal(=). As 狂信者 (FANATIC). And OnlyOnceRegistered. <br>
     * 狂信者
     */
    public void setSkillCode_Equal_狂信者() {
        setSkillCode_Equal_AsSkill(CDef.Skill.狂信者);
    }

    /**
     * Equal(=). As おまかせ足音職 (FOOTSTEPS). And OnlyOnceRegistered. <br>
     * おまかせ（足音職）
     */
    public void setSkillCode_Equal_おまかせ足音職() {
        setSkillCode_Equal_AsSkill(CDef.Skill.おまかせ足音職);
    }

    /**
     * Equal(=). As 妖狐 (FOX). And OnlyOnceRegistered. <br>
     * 妖狐
     */
    public void setSkillCode_Equal_妖狐() {
        setSkillCode_Equal_AsSkill(CDef.Skill.妖狐);
    }

    /**
     * Equal(=). As おまかせ役職窓あり (FRIENDS). And OnlyOnceRegistered. <br>
     * おまかせ（役職窓あり）
     */
    public void setSkillCode_Equal_おまかせ役職窓あり() {
        setSkillCode_Equal_AsSkill(CDef.Skill.おまかせ役職窓あり);
    }

    /**
     * Equal(=). As 導師 (GURU). And OnlyOnceRegistered. <br>
     * 導師
     */
    public void setSkillCode_Equal_導師() {
        setSkillCode_Equal_AsSkill(CDef.Skill.導師);
    }

    /**
     * Equal(=). As 狩人 (HUNTER). And OnlyOnceRegistered. <br>
     * 狩人
     */
    public void setSkillCode_Equal_狩人() {
        setSkillCode_Equal_AsSkill(CDef.Skill.狩人);
    }

    /**
     * Equal(=). As おまかせ (LEFTOVER). And OnlyOnceRegistered. <br>
     * おまかせ
     */
    public void setSkillCode_Equal_おまかせ() {
        setSkillCode_Equal_AsSkill(CDef.Skill.おまかせ);
    }

    /**
     * Equal(=). As 狂人 (MADMAN). And OnlyOnceRegistered. <br>
     * 狂人
     */
    public void setSkillCode_Equal_狂人() {
        setSkillCode_Equal_AsSkill(CDef.Skill.狂人);
    }

    /**
     * Equal(=). As 共鳴者 (MASON). And OnlyOnceRegistered. <br>
     * 共鳴者
     */
    public void setSkillCode_Equal_共鳴者() {
        setSkillCode_Equal_AsSkill(CDef.Skill.共鳴者);
    }

    /**
     * Equal(=). As 霊能者 (MEDIUM). And OnlyOnceRegistered. <br>
     * 霊能者
     */
    public void setSkillCode_Equal_霊能者() {
        setSkillCode_Equal_AsSkill(CDef.Skill.霊能者);
    }

    /**
     * Equal(=). As おまかせ人外 (NOVILLAGERS). And OnlyOnceRegistered. <br>
     * おまかせ（人外）
     */
    public void setSkillCode_Equal_おまかせ人外() {
        setSkillCode_Equal_AsSkill(CDef.Skill.おまかせ人外);
    }

    /**
     * Equal(=). As 占い師 (SEER). And OnlyOnceRegistered. <br>
     * 占い師
     */
    public void setSkillCode_Equal_占い師() {
        setSkillCode_Equal_AsSkill(CDef.Skill.占い師);
    }

    /**
     * Equal(=). As 罠師 (TRAPPER). And OnlyOnceRegistered. <br>
     * 罠師
     */
    public void setSkillCode_Equal_罠師() {
        setSkillCode_Equal_AsSkill(CDef.Skill.罠師);
    }

    /**
     * Equal(=). As 村人 (VILLAGER). And OnlyOnceRegistered. <br>
     * 村人
     */
    public void setSkillCode_Equal_村人() {
        setSkillCode_Equal_AsSkill(CDef.Skill.村人);
    }

    /**
     * Equal(=). As おまかせ村人陣営 (VILLAGERS). And OnlyOnceRegistered. <br>
     * おまかせ（村人陣営）
     */
    public void setSkillCode_Equal_おまかせ村人陣営() {
        setSkillCode_Equal_AsSkill(CDef.Skill.おまかせ村人陣営);
    }

    /**
     * Equal(=). As 人狼 (WEREWOLF). And OnlyOnceRegistered. <br>
     * 人狼
     */
    public void setSkillCode_Equal_人狼() {
        setSkillCode_Equal_AsSkill(CDef.Skill.人狼);
    }

    /**
     * Equal(=). As おまかせ人狼陣営 (WEREWOLFS). And OnlyOnceRegistered. <br>
     * おまかせ（人狼陣営）
     */
    public void setSkillCode_Equal_おまかせ人狼陣営() {
        setSkillCode_Equal_AsSkill(CDef.Skill.おまかせ人狼陣営);
    }

    /**
     * Equal(=). As 賢者 (WISE). And OnlyOnceRegistered. <br>
     * 賢者
     */
    public void setSkillCode_Equal_賢者() {
        setSkillCode_Equal_AsSkill(CDef.Skill.賢者);
    }

    /**
     * Equal(=). As 智狼 (WISEWOLF). And OnlyOnceRegistered. <br>
     * 智狼
     */
    public void setSkillCode_Equal_智狼() {
        setSkillCode_Equal_AsSkill(CDef.Skill.智狼);
    }

    protected void doSetSkillCode_Equal(String skillCode) {
        regSkillCode(CK_EQ, skillCode);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     * @param skillCode The value of skillCode as notEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setSkillCode_NotEqual(String skillCode) {
        doSetSkillCode_NotEqual(fRES(skillCode));
    }

    /**
     * NotEqual(&lt;&gt;). As Skill. And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill} <br>
     * 役職
     * @param cdef The instance of classification definition (as ENUM type). (basically NotNull: error as default, or no condition as option)
     */
    public void setSkillCode_NotEqual_AsSkill(CDef.Skill cdef) {
        doSetSkillCode_NotEqual(cdef != null ? cdef.code() : null);
    }

    /**
     * NotEqual(&lt;&gt;). As 占星術師 (ASTROLOGER). And OnlyOnceRegistered. <br>
     * 占星術師
     */
    public void setSkillCode_NotEqual_占星術師() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.占星術師);
    }

    /**
     * NotEqual(&lt;&gt;). As パン屋 (BAKERY). And OnlyOnceRegistered. <br>
     * パン屋
     */
    public void setSkillCode_NotEqual_パン屋() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.パン屋);
    }

    /**
     * NotEqual(&lt;&gt;). As 爆弾魔 (BOMBER). And OnlyOnceRegistered. <br>
     * 爆弾魔
     */
    public void setSkillCode_NotEqual_爆弾魔() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.爆弾魔);
    }

    /**
     * NotEqual(&lt;&gt;). As C国狂人 (CMADMAN). And OnlyOnceRegistered. <br>
     * C国狂人
     */
    public void setSkillCode_NotEqual_C国狂人() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.C国狂人);
    }

    /**
     * NotEqual(&lt;&gt;). As 検死官 (CORONER). And OnlyOnceRegistered. <br>
     * 検死官
     */
    public void setSkillCode_NotEqual_検死官() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.検死官);
    }

    /**
     * NotEqual(&lt;&gt;). As 呪狼 (CURSEWOLF). And OnlyOnceRegistered. <br>
     * 呪狼
     */
    public void setSkillCode_NotEqual_呪狼() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.呪狼);
    }

    /**
     * NotEqual(&lt;&gt;). As 探偵 (DETECTIVE). And OnlyOnceRegistered. <br>
     * 探偵
     */
    public void setSkillCode_NotEqual_探偵() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.探偵);
    }

    /**
     * NotEqual(&lt;&gt;). As 魔神官 (EVILMEDIUM). And OnlyOnceRegistered. <br>
     * 魔神官
     */
    public void setSkillCode_NotEqual_魔神官() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.魔神官);
    }

    /**
     * NotEqual(&lt;&gt;). As 狂信者 (FANATIC). And OnlyOnceRegistered. <br>
     * 狂信者
     */
    public void setSkillCode_NotEqual_狂信者() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.狂信者);
    }

    /**
     * NotEqual(&lt;&gt;). As おまかせ足音職 (FOOTSTEPS). And OnlyOnceRegistered. <br>
     * おまかせ（足音職）
     */
    public void setSkillCode_NotEqual_おまかせ足音職() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.おまかせ足音職);
    }

    /**
     * NotEqual(&lt;&gt;). As 妖狐 (FOX). And OnlyOnceRegistered. <br>
     * 妖狐
     */
    public void setSkillCode_NotEqual_妖狐() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.妖狐);
    }

    /**
     * NotEqual(&lt;&gt;). As おまかせ役職窓あり (FRIENDS). And OnlyOnceRegistered. <br>
     * おまかせ（役職窓あり）
     */
    public void setSkillCode_NotEqual_おまかせ役職窓あり() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.おまかせ役職窓あり);
    }

    /**
     * NotEqual(&lt;&gt;). As 導師 (GURU). And OnlyOnceRegistered. <br>
     * 導師
     */
    public void setSkillCode_NotEqual_導師() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.導師);
    }

    /**
     * NotEqual(&lt;&gt;). As 狩人 (HUNTER). And OnlyOnceRegistered. <br>
     * 狩人
     */
    public void setSkillCode_NotEqual_狩人() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.狩人);
    }

    /**
     * NotEqual(&lt;&gt;). As おまかせ (LEFTOVER). And OnlyOnceRegistered. <br>
     * おまかせ
     */
    public void setSkillCode_NotEqual_おまかせ() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.おまかせ);
    }

    /**
     * NotEqual(&lt;&gt;). As 狂人 (MADMAN). And OnlyOnceRegistered. <br>
     * 狂人
     */
    public void setSkillCode_NotEqual_狂人() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.狂人);
    }

    /**
     * NotEqual(&lt;&gt;). As 共鳴者 (MASON). And OnlyOnceRegistered. <br>
     * 共鳴者
     */
    public void setSkillCode_NotEqual_共鳴者() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.共鳴者);
    }

    /**
     * NotEqual(&lt;&gt;). As 霊能者 (MEDIUM). And OnlyOnceRegistered. <br>
     * 霊能者
     */
    public void setSkillCode_NotEqual_霊能者() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.霊能者);
    }

    /**
     * NotEqual(&lt;&gt;). As おまかせ人外 (NOVILLAGERS). And OnlyOnceRegistered. <br>
     * おまかせ（人外）
     */
    public void setSkillCode_NotEqual_おまかせ人外() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.おまかせ人外);
    }

    /**
     * NotEqual(&lt;&gt;). As 占い師 (SEER). And OnlyOnceRegistered. <br>
     * 占い師
     */
    public void setSkillCode_NotEqual_占い師() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.占い師);
    }

    /**
     * NotEqual(&lt;&gt;). As 罠師 (TRAPPER). And OnlyOnceRegistered. <br>
     * 罠師
     */
    public void setSkillCode_NotEqual_罠師() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.罠師);
    }

    /**
     * NotEqual(&lt;&gt;). As 村人 (VILLAGER). And OnlyOnceRegistered. <br>
     * 村人
     */
    public void setSkillCode_NotEqual_村人() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.村人);
    }

    /**
     * NotEqual(&lt;&gt;). As おまかせ村人陣営 (VILLAGERS). And OnlyOnceRegistered. <br>
     * おまかせ（村人陣営）
     */
    public void setSkillCode_NotEqual_おまかせ村人陣営() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.おまかせ村人陣営);
    }

    /**
     * NotEqual(&lt;&gt;). As 人狼 (WEREWOLF). And OnlyOnceRegistered. <br>
     * 人狼
     */
    public void setSkillCode_NotEqual_人狼() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.人狼);
    }

    /**
     * NotEqual(&lt;&gt;). As おまかせ人狼陣営 (WEREWOLFS). And OnlyOnceRegistered. <br>
     * おまかせ（人狼陣営）
     */
    public void setSkillCode_NotEqual_おまかせ人狼陣営() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.おまかせ人狼陣営);
    }

    /**
     * NotEqual(&lt;&gt;). As 賢者 (WISE). And OnlyOnceRegistered. <br>
     * 賢者
     */
    public void setSkillCode_NotEqual_賢者() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.賢者);
    }

    /**
     * NotEqual(&lt;&gt;). As 智狼 (WISEWOLF). And OnlyOnceRegistered. <br>
     * 智狼
     */
    public void setSkillCode_NotEqual_智狼() {
        setSkillCode_NotEqual_AsSkill(CDef.Skill.智狼);
    }

    protected void doSetSkillCode_NotEqual(String skillCode) {
        regSkillCode(CK_NES, skillCode);
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     * @param skillCodeList The collection of skillCode as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setSkillCode_InScope(Collection<String> skillCodeList) {
        doSetSkillCode_InScope(skillCodeList);
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill} <br>
     * 役職
     * @param cdefList The list of classification definition (as ENUM type). (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setSkillCode_InScope_AsSkill(Collection<CDef.Skill> cdefList) {
        doSetSkillCode_InScope(cTStrL(cdefList));
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 囁きを見られる <br>
     * The group elements:[人狼, 呪狼, 智狼, C国狂人]
     */
    public void setSkillCode_InScope_ViewableWerewolfSay() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfViewableWerewolfSay());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 囁き可能 <br>
     * The group elements:[人狼, 呪狼, 智狼, C国狂人]
     */
    public void setSkillCode_InScope_AvailableWerewolfSay() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfAvailableWerewolfSay());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 共鳴発言を見られる <br>
     * The group elements:[共鳴者]
     */
    public void setSkillCode_InScope_ViewableMasonSay() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfViewableMasonSay());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 共鳴発言可能 <br>
     * The group elements:[共鳴者]
     */
    public void setSkillCode_InScope_AvailableMasonSay() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfAvailableMasonSay());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 白黒占い能力を持つ <br>
     * The group elements:[占い師]
     */
    public void setSkillCode_InScope_HasDivineAbility() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfHasDivineAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 白黒占い結果が人狼になる <br>
     * The group elements:[人狼, 呪狼, 智狼]
     */
    public void setSkillCode_InScope_DivineResultWolf() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfDivineResultWolf());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 役職占い能力を持つ <br>
     * The group elements:[賢者]
     */
    public void setSkillCode_InScope_HasWiseAbility() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfHasWiseAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 占星術能力を持つ <br>
     * The group elements:[占星術師]
     */
    public void setSkillCode_InScope_HasAstroAbility() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfHasAstroAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 占いにより死亡する <br>
     * The group elements:[妖狐]
     */
    public void setSkillCode_InScope_DeadByDivine() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfDeadByDivine());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 占いにより占った側が死亡する <br>
     * The group elements:[呪狼]
     */
    public void setSkillCode_InScope_CounterDeadByDivine() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfCounterDeadByDivine());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 白黒霊能能力を持つ <br>
     * The group elements:[霊能者]
     */
    public void setSkillCode_InScope_HasPsychicAbility() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfHasPsychicAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 白黒霊能結果が人狼になる <br>
     * The group elements:[人狼, 呪狼, 智狼]
     */
    public void setSkillCode_InScope_PsychicResultWolf() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfPsychicResultWolf());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 役職霊能能力を持つ <br>
     * The group elements:[導師, 魔神官]
     */
    public void setSkillCode_InScope_HasSkillPsychicAbility() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfHasSkillPsychicAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 護衛能力を持つ <br>
     * The group elements:[狩人]
     */
    public void setSkillCode_InScope_HasGuardAbility() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfHasGuardAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 検死能力を持つ <br>
     * The group elements:[検死官]
     */
    public void setSkillCode_InScope_HasAutopsyAbility() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfHasAutopsyAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 探偵能力を持つ <br>
     * The group elements:[探偵]
     */
    public void setSkillCode_InScope_HasInvestigateAbility() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfHasInvestigateAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 罠能力を持つ <br>
     * The group elements:[罠師]
     */
    public void setSkillCode_InScope_HasTrapAbility() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfHasTrapAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 爆弾能力を持つ <br>
     * The group elements:[爆弾魔]
     */
    public void setSkillCode_InScope_HasBombAbility() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfHasBombAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * パン屋能力を持つ <br>
     * The group elements:[パン屋]
     */
    public void setSkillCode_InScope_HasBakeryAbility() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfHasBakeryAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 襲撃能力を持つ <br>
     * The group elements:[人狼, 呪狼, 智狼]
     */
    public void setSkillCode_InScope_HasAttackAbility() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfHasAttackAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 襲撃に成功すると対象の役職を知ることができる <br>
     * The group elements:[智狼]
     */
    public void setSkillCode_InScope_HasAttackWiseAbility() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfHasAttackWiseAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 襲撃されても死なない <br>
     * The group elements:[妖狐, 爆弾魔]
     */
    public void setSkillCode_InScope_NoDeadByAttack() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfNoDeadByAttack());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 襲撃対象に選べない <br>
     * The group elements:[人狼, 呪狼, 智狼]
     */
    public void setSkillCode_InScope_NotSelectableAttack() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfNotSelectableAttack());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 狂人能力を持つ <br>
     * The group elements:[C国狂人, 狂人, 狂信者, 魔神官]
     */
    public void setSkillCode_InScope_HasMadmanAbility() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfHasMadmanAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 徘徊能力を持つ <br>
     * The group elements:[C国狂人, 狂人, 狂信者, 魔神官, 妖狐]
     */
    public void setSkillCode_InScope_HasDisturbAbility() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfHasDisturbAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 勝敗判定時狼にカウントする <br>
     * The group elements:[人狼, 呪狼, 智狼]
     */
    public void setSkillCode_InScope_CountWolf() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfCountWolf());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 勝敗判定時に人間としてカウントしない <br>
     * The group elements:[妖狐]
     */
    public void setSkillCode_InScope_NoCount() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfNoCount());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * おまかせ系 <br>
     * The group elements:[おまかせ, おまかせ村人陣営, おまかせ人狼陣営, おまかせ人外, おまかせ足音職, おまかせ役職窓あり]
     */
    public void setSkillCode_InScope_SomeoneSkill() {
        setSkillCode_InScope_AsSkill(CDef.Skill.listOfSomeoneSkill());
    }

    protected void doSetSkillCode_InScope(Collection<String> skillCodeList) {
        regINS(CK_INS, cTL(skillCodeList), xgetCValueSkillCode(), "SKILL_CODE");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     * @param skillCodeList The collection of skillCode as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setSkillCode_NotInScope(Collection<String> skillCodeList) {
        doSetSkillCode_NotInScope(skillCodeList);
    }

    /**
     * NotInScope {not in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill} <br>
     * 役職
     * @param cdefList The list of classification definition (as ENUM type). (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setSkillCode_NotInScope_AsSkill(Collection<CDef.Skill> cdefList) {
        doSetSkillCode_NotInScope(cTStrL(cdefList));
    }

    protected void doSetSkillCode_NotInScope(Collection<String> skillCodeList) {
        regINS(CK_NINS, cTL(skillCodeList), xgetCValueSkillCode(), "SKILL_CODE");
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     */
    public void setSkillCode_IsNull() { regSkillCode(CK_ISN, DOBJ); }

    /**
     * IsNullOrEmpty {is null or empty}. And OnlyOnceRegistered. <br>
     * SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     */
    public void setSkillCode_IsNullOrEmpty() { regSkillCode(CK_ISNOE, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     */
    public void setSkillCode_IsNotNull() { regSkillCode(CK_ISNN, DOBJ); }

    protected void regSkillCode(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueSkillCode(), "SKILL_CODE"); }
    protected abstract ConditionValue xgetCValueSkillCode();

    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     * @param requestSkillCode The value of requestSkillCode as equal. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setRequestSkillCode_Equal(String requestSkillCode) {
        doSetRequestSkillCode_Equal(fRES(requestSkillCode));
    }

    /**
     * Equal(=). As Skill. And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill} <br>
     * 役職
     * @param cdef The instance of classification definition (as ENUM type). (basically NotNull: error as default, or no condition as option)
     */
    public void setRequestSkillCode_Equal_AsSkill(CDef.Skill cdef) {
        doSetRequestSkillCode_Equal(cdef != null ? cdef.code() : null);
    }

    /**
     * Equal(=). As 占星術師 (ASTROLOGER). And OnlyOnceRegistered. <br>
     * 占星術師
     */
    public void setRequestSkillCode_Equal_占星術師() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.占星術師);
    }

    /**
     * Equal(=). As パン屋 (BAKERY). And OnlyOnceRegistered. <br>
     * パン屋
     */
    public void setRequestSkillCode_Equal_パン屋() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.パン屋);
    }

    /**
     * Equal(=). As 爆弾魔 (BOMBER). And OnlyOnceRegistered. <br>
     * 爆弾魔
     */
    public void setRequestSkillCode_Equal_爆弾魔() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.爆弾魔);
    }

    /**
     * Equal(=). As C国狂人 (CMADMAN). And OnlyOnceRegistered. <br>
     * C国狂人
     */
    public void setRequestSkillCode_Equal_C国狂人() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.C国狂人);
    }

    /**
     * Equal(=). As 検死官 (CORONER). And OnlyOnceRegistered. <br>
     * 検死官
     */
    public void setRequestSkillCode_Equal_検死官() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.検死官);
    }

    /**
     * Equal(=). As 呪狼 (CURSEWOLF). And OnlyOnceRegistered. <br>
     * 呪狼
     */
    public void setRequestSkillCode_Equal_呪狼() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.呪狼);
    }

    /**
     * Equal(=). As 探偵 (DETECTIVE). And OnlyOnceRegistered. <br>
     * 探偵
     */
    public void setRequestSkillCode_Equal_探偵() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.探偵);
    }

    /**
     * Equal(=). As 魔神官 (EVILMEDIUM). And OnlyOnceRegistered. <br>
     * 魔神官
     */
    public void setRequestSkillCode_Equal_魔神官() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.魔神官);
    }

    /**
     * Equal(=). As 狂信者 (FANATIC). And OnlyOnceRegistered. <br>
     * 狂信者
     */
    public void setRequestSkillCode_Equal_狂信者() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.狂信者);
    }

    /**
     * Equal(=). As おまかせ足音職 (FOOTSTEPS). And OnlyOnceRegistered. <br>
     * おまかせ（足音職）
     */
    public void setRequestSkillCode_Equal_おまかせ足音職() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.おまかせ足音職);
    }

    /**
     * Equal(=). As 妖狐 (FOX). And OnlyOnceRegistered. <br>
     * 妖狐
     */
    public void setRequestSkillCode_Equal_妖狐() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.妖狐);
    }

    /**
     * Equal(=). As おまかせ役職窓あり (FRIENDS). And OnlyOnceRegistered. <br>
     * おまかせ（役職窓あり）
     */
    public void setRequestSkillCode_Equal_おまかせ役職窓あり() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.おまかせ役職窓あり);
    }

    /**
     * Equal(=). As 導師 (GURU). And OnlyOnceRegistered. <br>
     * 導師
     */
    public void setRequestSkillCode_Equal_導師() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.導師);
    }

    /**
     * Equal(=). As 狩人 (HUNTER). And OnlyOnceRegistered. <br>
     * 狩人
     */
    public void setRequestSkillCode_Equal_狩人() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.狩人);
    }

    /**
     * Equal(=). As おまかせ (LEFTOVER). And OnlyOnceRegistered. <br>
     * おまかせ
     */
    public void setRequestSkillCode_Equal_おまかせ() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.おまかせ);
    }

    /**
     * Equal(=). As 狂人 (MADMAN). And OnlyOnceRegistered. <br>
     * 狂人
     */
    public void setRequestSkillCode_Equal_狂人() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.狂人);
    }

    /**
     * Equal(=). As 共鳴者 (MASON). And OnlyOnceRegistered. <br>
     * 共鳴者
     */
    public void setRequestSkillCode_Equal_共鳴者() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.共鳴者);
    }

    /**
     * Equal(=). As 霊能者 (MEDIUM). And OnlyOnceRegistered. <br>
     * 霊能者
     */
    public void setRequestSkillCode_Equal_霊能者() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.霊能者);
    }

    /**
     * Equal(=). As おまかせ人外 (NOVILLAGERS). And OnlyOnceRegistered. <br>
     * おまかせ（人外）
     */
    public void setRequestSkillCode_Equal_おまかせ人外() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.おまかせ人外);
    }

    /**
     * Equal(=). As 占い師 (SEER). And OnlyOnceRegistered. <br>
     * 占い師
     */
    public void setRequestSkillCode_Equal_占い師() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.占い師);
    }

    /**
     * Equal(=). As 罠師 (TRAPPER). And OnlyOnceRegistered. <br>
     * 罠師
     */
    public void setRequestSkillCode_Equal_罠師() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.罠師);
    }

    /**
     * Equal(=). As 村人 (VILLAGER). And OnlyOnceRegistered. <br>
     * 村人
     */
    public void setRequestSkillCode_Equal_村人() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.村人);
    }

    /**
     * Equal(=). As おまかせ村人陣営 (VILLAGERS). And OnlyOnceRegistered. <br>
     * おまかせ（村人陣営）
     */
    public void setRequestSkillCode_Equal_おまかせ村人陣営() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.おまかせ村人陣営);
    }

    /**
     * Equal(=). As 人狼 (WEREWOLF). And OnlyOnceRegistered. <br>
     * 人狼
     */
    public void setRequestSkillCode_Equal_人狼() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.人狼);
    }

    /**
     * Equal(=). As おまかせ人狼陣営 (WEREWOLFS). And OnlyOnceRegistered. <br>
     * おまかせ（人狼陣営）
     */
    public void setRequestSkillCode_Equal_おまかせ人狼陣営() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.おまかせ人狼陣営);
    }

    /**
     * Equal(=). As 賢者 (WISE). And OnlyOnceRegistered. <br>
     * 賢者
     */
    public void setRequestSkillCode_Equal_賢者() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.賢者);
    }

    /**
     * Equal(=). As 智狼 (WISEWOLF). And OnlyOnceRegistered. <br>
     * 智狼
     */
    public void setRequestSkillCode_Equal_智狼() {
        setRequestSkillCode_Equal_AsSkill(CDef.Skill.智狼);
    }

    protected void doSetRequestSkillCode_Equal(String requestSkillCode) {
        regRequestSkillCode(CK_EQ, requestSkillCode);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     * @param requestSkillCode The value of requestSkillCode as notEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setRequestSkillCode_NotEqual(String requestSkillCode) {
        doSetRequestSkillCode_NotEqual(fRES(requestSkillCode));
    }

    /**
     * NotEqual(&lt;&gt;). As Skill. And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill} <br>
     * 役職
     * @param cdef The instance of classification definition (as ENUM type). (basically NotNull: error as default, or no condition as option)
     */
    public void setRequestSkillCode_NotEqual_AsSkill(CDef.Skill cdef) {
        doSetRequestSkillCode_NotEqual(cdef != null ? cdef.code() : null);
    }

    /**
     * NotEqual(&lt;&gt;). As 占星術師 (ASTROLOGER). And OnlyOnceRegistered. <br>
     * 占星術師
     */
    public void setRequestSkillCode_NotEqual_占星術師() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.占星術師);
    }

    /**
     * NotEqual(&lt;&gt;). As パン屋 (BAKERY). And OnlyOnceRegistered. <br>
     * パン屋
     */
    public void setRequestSkillCode_NotEqual_パン屋() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.パン屋);
    }

    /**
     * NotEqual(&lt;&gt;). As 爆弾魔 (BOMBER). And OnlyOnceRegistered. <br>
     * 爆弾魔
     */
    public void setRequestSkillCode_NotEqual_爆弾魔() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.爆弾魔);
    }

    /**
     * NotEqual(&lt;&gt;). As C国狂人 (CMADMAN). And OnlyOnceRegistered. <br>
     * C国狂人
     */
    public void setRequestSkillCode_NotEqual_C国狂人() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.C国狂人);
    }

    /**
     * NotEqual(&lt;&gt;). As 検死官 (CORONER). And OnlyOnceRegistered. <br>
     * 検死官
     */
    public void setRequestSkillCode_NotEqual_検死官() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.検死官);
    }

    /**
     * NotEqual(&lt;&gt;). As 呪狼 (CURSEWOLF). And OnlyOnceRegistered. <br>
     * 呪狼
     */
    public void setRequestSkillCode_NotEqual_呪狼() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.呪狼);
    }

    /**
     * NotEqual(&lt;&gt;). As 探偵 (DETECTIVE). And OnlyOnceRegistered. <br>
     * 探偵
     */
    public void setRequestSkillCode_NotEqual_探偵() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.探偵);
    }

    /**
     * NotEqual(&lt;&gt;). As 魔神官 (EVILMEDIUM). And OnlyOnceRegistered. <br>
     * 魔神官
     */
    public void setRequestSkillCode_NotEqual_魔神官() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.魔神官);
    }

    /**
     * NotEqual(&lt;&gt;). As 狂信者 (FANATIC). And OnlyOnceRegistered. <br>
     * 狂信者
     */
    public void setRequestSkillCode_NotEqual_狂信者() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.狂信者);
    }

    /**
     * NotEqual(&lt;&gt;). As おまかせ足音職 (FOOTSTEPS). And OnlyOnceRegistered. <br>
     * おまかせ（足音職）
     */
    public void setRequestSkillCode_NotEqual_おまかせ足音職() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.おまかせ足音職);
    }

    /**
     * NotEqual(&lt;&gt;). As 妖狐 (FOX). And OnlyOnceRegistered. <br>
     * 妖狐
     */
    public void setRequestSkillCode_NotEqual_妖狐() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.妖狐);
    }

    /**
     * NotEqual(&lt;&gt;). As おまかせ役職窓あり (FRIENDS). And OnlyOnceRegistered. <br>
     * おまかせ（役職窓あり）
     */
    public void setRequestSkillCode_NotEqual_おまかせ役職窓あり() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.おまかせ役職窓あり);
    }

    /**
     * NotEqual(&lt;&gt;). As 導師 (GURU). And OnlyOnceRegistered. <br>
     * 導師
     */
    public void setRequestSkillCode_NotEqual_導師() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.導師);
    }

    /**
     * NotEqual(&lt;&gt;). As 狩人 (HUNTER). And OnlyOnceRegistered. <br>
     * 狩人
     */
    public void setRequestSkillCode_NotEqual_狩人() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.狩人);
    }

    /**
     * NotEqual(&lt;&gt;). As おまかせ (LEFTOVER). And OnlyOnceRegistered. <br>
     * おまかせ
     */
    public void setRequestSkillCode_NotEqual_おまかせ() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.おまかせ);
    }

    /**
     * NotEqual(&lt;&gt;). As 狂人 (MADMAN). And OnlyOnceRegistered. <br>
     * 狂人
     */
    public void setRequestSkillCode_NotEqual_狂人() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.狂人);
    }

    /**
     * NotEqual(&lt;&gt;). As 共鳴者 (MASON). And OnlyOnceRegistered. <br>
     * 共鳴者
     */
    public void setRequestSkillCode_NotEqual_共鳴者() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.共鳴者);
    }

    /**
     * NotEqual(&lt;&gt;). As 霊能者 (MEDIUM). And OnlyOnceRegistered. <br>
     * 霊能者
     */
    public void setRequestSkillCode_NotEqual_霊能者() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.霊能者);
    }

    /**
     * NotEqual(&lt;&gt;). As おまかせ人外 (NOVILLAGERS). And OnlyOnceRegistered. <br>
     * おまかせ（人外）
     */
    public void setRequestSkillCode_NotEqual_おまかせ人外() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.おまかせ人外);
    }

    /**
     * NotEqual(&lt;&gt;). As 占い師 (SEER). And OnlyOnceRegistered. <br>
     * 占い師
     */
    public void setRequestSkillCode_NotEqual_占い師() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.占い師);
    }

    /**
     * NotEqual(&lt;&gt;). As 罠師 (TRAPPER). And OnlyOnceRegistered. <br>
     * 罠師
     */
    public void setRequestSkillCode_NotEqual_罠師() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.罠師);
    }

    /**
     * NotEqual(&lt;&gt;). As 村人 (VILLAGER). And OnlyOnceRegistered. <br>
     * 村人
     */
    public void setRequestSkillCode_NotEqual_村人() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.村人);
    }

    /**
     * NotEqual(&lt;&gt;). As おまかせ村人陣営 (VILLAGERS). And OnlyOnceRegistered. <br>
     * おまかせ（村人陣営）
     */
    public void setRequestSkillCode_NotEqual_おまかせ村人陣営() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.おまかせ村人陣営);
    }

    /**
     * NotEqual(&lt;&gt;). As 人狼 (WEREWOLF). And OnlyOnceRegistered. <br>
     * 人狼
     */
    public void setRequestSkillCode_NotEqual_人狼() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.人狼);
    }

    /**
     * NotEqual(&lt;&gt;). As おまかせ人狼陣営 (WEREWOLFS). And OnlyOnceRegistered. <br>
     * おまかせ（人狼陣営）
     */
    public void setRequestSkillCode_NotEqual_おまかせ人狼陣営() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.おまかせ人狼陣営);
    }

    /**
     * NotEqual(&lt;&gt;). As 賢者 (WISE). And OnlyOnceRegistered. <br>
     * 賢者
     */
    public void setRequestSkillCode_NotEqual_賢者() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.賢者);
    }

    /**
     * NotEqual(&lt;&gt;). As 智狼 (WISEWOLF). And OnlyOnceRegistered. <br>
     * 智狼
     */
    public void setRequestSkillCode_NotEqual_智狼() {
        setRequestSkillCode_NotEqual_AsSkill(CDef.Skill.智狼);
    }

    protected void doSetRequestSkillCode_NotEqual(String requestSkillCode) {
        regRequestSkillCode(CK_NES, requestSkillCode);
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     * @param requestSkillCodeList The collection of requestSkillCode as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setRequestSkillCode_InScope(Collection<String> requestSkillCodeList) {
        doSetRequestSkillCode_InScope(requestSkillCodeList);
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill} <br>
     * 役職
     * @param cdefList The list of classification definition (as ENUM type). (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setRequestSkillCode_InScope_AsSkill(Collection<CDef.Skill> cdefList) {
        doSetRequestSkillCode_InScope(cTStrL(cdefList));
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 囁きを見られる <br>
     * The group elements:[人狼, 呪狼, 智狼, C国狂人]
     */
    public void setRequestSkillCode_InScope_ViewableWerewolfSay() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfViewableWerewolfSay());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 囁き可能 <br>
     * The group elements:[人狼, 呪狼, 智狼, C国狂人]
     */
    public void setRequestSkillCode_InScope_AvailableWerewolfSay() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfAvailableWerewolfSay());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 共鳴発言を見られる <br>
     * The group elements:[共鳴者]
     */
    public void setRequestSkillCode_InScope_ViewableMasonSay() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfViewableMasonSay());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 共鳴発言可能 <br>
     * The group elements:[共鳴者]
     */
    public void setRequestSkillCode_InScope_AvailableMasonSay() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfAvailableMasonSay());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 白黒占い能力を持つ <br>
     * The group elements:[占い師]
     */
    public void setRequestSkillCode_InScope_HasDivineAbility() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasDivineAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 白黒占い結果が人狼になる <br>
     * The group elements:[人狼, 呪狼, 智狼]
     */
    public void setRequestSkillCode_InScope_DivineResultWolf() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfDivineResultWolf());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 役職占い能力を持つ <br>
     * The group elements:[賢者]
     */
    public void setRequestSkillCode_InScope_HasWiseAbility() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasWiseAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 占星術能力を持つ <br>
     * The group elements:[占星術師]
     */
    public void setRequestSkillCode_InScope_HasAstroAbility() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasAstroAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 占いにより死亡する <br>
     * The group elements:[妖狐]
     */
    public void setRequestSkillCode_InScope_DeadByDivine() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfDeadByDivine());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 占いにより占った側が死亡する <br>
     * The group elements:[呪狼]
     */
    public void setRequestSkillCode_InScope_CounterDeadByDivine() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfCounterDeadByDivine());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 白黒霊能能力を持つ <br>
     * The group elements:[霊能者]
     */
    public void setRequestSkillCode_InScope_HasPsychicAbility() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasPsychicAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 白黒霊能結果が人狼になる <br>
     * The group elements:[人狼, 呪狼, 智狼]
     */
    public void setRequestSkillCode_InScope_PsychicResultWolf() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfPsychicResultWolf());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 役職霊能能力を持つ <br>
     * The group elements:[導師, 魔神官]
     */
    public void setRequestSkillCode_InScope_HasSkillPsychicAbility() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasSkillPsychicAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 護衛能力を持つ <br>
     * The group elements:[狩人]
     */
    public void setRequestSkillCode_InScope_HasGuardAbility() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasGuardAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 検死能力を持つ <br>
     * The group elements:[検死官]
     */
    public void setRequestSkillCode_InScope_HasAutopsyAbility() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasAutopsyAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 探偵能力を持つ <br>
     * The group elements:[探偵]
     */
    public void setRequestSkillCode_InScope_HasInvestigateAbility() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasInvestigateAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 罠能力を持つ <br>
     * The group elements:[罠師]
     */
    public void setRequestSkillCode_InScope_HasTrapAbility() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasTrapAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 爆弾能力を持つ <br>
     * The group elements:[爆弾魔]
     */
    public void setRequestSkillCode_InScope_HasBombAbility() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasBombAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * パン屋能力を持つ <br>
     * The group elements:[パン屋]
     */
    public void setRequestSkillCode_InScope_HasBakeryAbility() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasBakeryAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 襲撃能力を持つ <br>
     * The group elements:[人狼, 呪狼, 智狼]
     */
    public void setRequestSkillCode_InScope_HasAttackAbility() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasAttackAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 襲撃に成功すると対象の役職を知ることができる <br>
     * The group elements:[智狼]
     */
    public void setRequestSkillCode_InScope_HasAttackWiseAbility() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasAttackWiseAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 襲撃されても死なない <br>
     * The group elements:[妖狐, 爆弾魔]
     */
    public void setRequestSkillCode_InScope_NoDeadByAttack() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfNoDeadByAttack());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 襲撃対象に選べない <br>
     * The group elements:[人狼, 呪狼, 智狼]
     */
    public void setRequestSkillCode_InScope_NotSelectableAttack() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfNotSelectableAttack());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 狂人能力を持つ <br>
     * The group elements:[C国狂人, 狂人, 狂信者, 魔神官]
     */
    public void setRequestSkillCode_InScope_HasMadmanAbility() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasMadmanAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 徘徊能力を持つ <br>
     * The group elements:[C国狂人, 狂人, 狂信者, 魔神官, 妖狐]
     */
    public void setRequestSkillCode_InScope_HasDisturbAbility() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasDisturbAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 勝敗判定時狼にカウントする <br>
     * The group elements:[人狼, 呪狼, 智狼]
     */
    public void setRequestSkillCode_InScope_CountWolf() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfCountWolf());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 勝敗判定時に人間としてカウントしない <br>
     * The group elements:[妖狐]
     */
    public void setRequestSkillCode_InScope_NoCount() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfNoCount());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * おまかせ系 <br>
     * The group elements:[おまかせ, おまかせ村人陣営, おまかせ人狼陣営, おまかせ人外, おまかせ足音職, おまかせ役職窓あり]
     */
    public void setRequestSkillCode_InScope_SomeoneSkill() {
        setRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfSomeoneSkill());
    }

    protected void doSetRequestSkillCode_InScope(Collection<String> requestSkillCodeList) {
        regINS(CK_INS, cTL(requestSkillCodeList), xgetCValueRequestSkillCode(), "REQUEST_SKILL_CODE");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     * @param requestSkillCodeList The collection of requestSkillCode as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setRequestSkillCode_NotInScope(Collection<String> requestSkillCodeList) {
        doSetRequestSkillCode_NotInScope(requestSkillCodeList);
    }

    /**
     * NotInScope {not in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill} <br>
     * 役職
     * @param cdefList The list of classification definition (as ENUM type). (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setRequestSkillCode_NotInScope_AsSkill(Collection<CDef.Skill> cdefList) {
        doSetRequestSkillCode_NotInScope(cTStrL(cdefList));
    }

    protected void doSetRequestSkillCode_NotInScope(Collection<String> requestSkillCodeList) {
        regINS(CK_NINS, cTL(requestSkillCodeList), xgetCValueRequestSkillCode(), "REQUEST_SKILL_CODE");
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     */
    public void setRequestSkillCode_IsNull() { regRequestSkillCode(CK_ISN, DOBJ); }

    /**
     * IsNullOrEmpty {is null or empty}. And OnlyOnceRegistered. <br>
     * REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     */
    public void setRequestSkillCode_IsNullOrEmpty() { regRequestSkillCode(CK_ISNOE, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     */
    public void setRequestSkillCode_IsNotNull() { regRequestSkillCode(CK_ISNN, DOBJ); }

    protected void regRequestSkillCode(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueRequestSkillCode(), "REQUEST_SKILL_CODE"); }
    protected abstract ConditionValue xgetCValueRequestSkillCode();

    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * SECOND_REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     * @param secondRequestSkillCode The value of secondRequestSkillCode as equal. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setSecondRequestSkillCode_Equal(String secondRequestSkillCode) {
        doSetSecondRequestSkillCode_Equal(fRES(secondRequestSkillCode));
    }

    /**
     * Equal(=). As Skill. And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * SECOND_REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill} <br>
     * 役職
     * @param cdef The instance of classification definition (as ENUM type). (basically NotNull: error as default, or no condition as option)
     */
    public void setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill cdef) {
        doSetSecondRequestSkillCode_Equal(cdef != null ? cdef.code() : null);
    }

    /**
     * Equal(=). As 占星術師 (ASTROLOGER). And OnlyOnceRegistered. <br>
     * 占星術師
     */
    public void setSecondRequestSkillCode_Equal_占星術師() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.占星術師);
    }

    /**
     * Equal(=). As パン屋 (BAKERY). And OnlyOnceRegistered. <br>
     * パン屋
     */
    public void setSecondRequestSkillCode_Equal_パン屋() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.パン屋);
    }

    /**
     * Equal(=). As 爆弾魔 (BOMBER). And OnlyOnceRegistered. <br>
     * 爆弾魔
     */
    public void setSecondRequestSkillCode_Equal_爆弾魔() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.爆弾魔);
    }

    /**
     * Equal(=). As C国狂人 (CMADMAN). And OnlyOnceRegistered. <br>
     * C国狂人
     */
    public void setSecondRequestSkillCode_Equal_C国狂人() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.C国狂人);
    }

    /**
     * Equal(=). As 検死官 (CORONER). And OnlyOnceRegistered. <br>
     * 検死官
     */
    public void setSecondRequestSkillCode_Equal_検死官() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.検死官);
    }

    /**
     * Equal(=). As 呪狼 (CURSEWOLF). And OnlyOnceRegistered. <br>
     * 呪狼
     */
    public void setSecondRequestSkillCode_Equal_呪狼() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.呪狼);
    }

    /**
     * Equal(=). As 探偵 (DETECTIVE). And OnlyOnceRegistered. <br>
     * 探偵
     */
    public void setSecondRequestSkillCode_Equal_探偵() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.探偵);
    }

    /**
     * Equal(=). As 魔神官 (EVILMEDIUM). And OnlyOnceRegistered. <br>
     * 魔神官
     */
    public void setSecondRequestSkillCode_Equal_魔神官() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.魔神官);
    }

    /**
     * Equal(=). As 狂信者 (FANATIC). And OnlyOnceRegistered. <br>
     * 狂信者
     */
    public void setSecondRequestSkillCode_Equal_狂信者() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.狂信者);
    }

    /**
     * Equal(=). As おまかせ足音職 (FOOTSTEPS). And OnlyOnceRegistered. <br>
     * おまかせ（足音職）
     */
    public void setSecondRequestSkillCode_Equal_おまかせ足音職() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.おまかせ足音職);
    }

    /**
     * Equal(=). As 妖狐 (FOX). And OnlyOnceRegistered. <br>
     * 妖狐
     */
    public void setSecondRequestSkillCode_Equal_妖狐() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.妖狐);
    }

    /**
     * Equal(=). As おまかせ役職窓あり (FRIENDS). And OnlyOnceRegistered. <br>
     * おまかせ（役職窓あり）
     */
    public void setSecondRequestSkillCode_Equal_おまかせ役職窓あり() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.おまかせ役職窓あり);
    }

    /**
     * Equal(=). As 導師 (GURU). And OnlyOnceRegistered. <br>
     * 導師
     */
    public void setSecondRequestSkillCode_Equal_導師() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.導師);
    }

    /**
     * Equal(=). As 狩人 (HUNTER). And OnlyOnceRegistered. <br>
     * 狩人
     */
    public void setSecondRequestSkillCode_Equal_狩人() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.狩人);
    }

    /**
     * Equal(=). As おまかせ (LEFTOVER). And OnlyOnceRegistered. <br>
     * おまかせ
     */
    public void setSecondRequestSkillCode_Equal_おまかせ() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.おまかせ);
    }

    /**
     * Equal(=). As 狂人 (MADMAN). And OnlyOnceRegistered. <br>
     * 狂人
     */
    public void setSecondRequestSkillCode_Equal_狂人() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.狂人);
    }

    /**
     * Equal(=). As 共鳴者 (MASON). And OnlyOnceRegistered. <br>
     * 共鳴者
     */
    public void setSecondRequestSkillCode_Equal_共鳴者() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.共鳴者);
    }

    /**
     * Equal(=). As 霊能者 (MEDIUM). And OnlyOnceRegistered. <br>
     * 霊能者
     */
    public void setSecondRequestSkillCode_Equal_霊能者() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.霊能者);
    }

    /**
     * Equal(=). As おまかせ人外 (NOVILLAGERS). And OnlyOnceRegistered. <br>
     * おまかせ（人外）
     */
    public void setSecondRequestSkillCode_Equal_おまかせ人外() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.おまかせ人外);
    }

    /**
     * Equal(=). As 占い師 (SEER). And OnlyOnceRegistered. <br>
     * 占い師
     */
    public void setSecondRequestSkillCode_Equal_占い師() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.占い師);
    }

    /**
     * Equal(=). As 罠師 (TRAPPER). And OnlyOnceRegistered. <br>
     * 罠師
     */
    public void setSecondRequestSkillCode_Equal_罠師() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.罠師);
    }

    /**
     * Equal(=). As 村人 (VILLAGER). And OnlyOnceRegistered. <br>
     * 村人
     */
    public void setSecondRequestSkillCode_Equal_村人() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.村人);
    }

    /**
     * Equal(=). As おまかせ村人陣営 (VILLAGERS). And OnlyOnceRegistered. <br>
     * おまかせ（村人陣営）
     */
    public void setSecondRequestSkillCode_Equal_おまかせ村人陣営() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.おまかせ村人陣営);
    }

    /**
     * Equal(=). As 人狼 (WEREWOLF). And OnlyOnceRegistered. <br>
     * 人狼
     */
    public void setSecondRequestSkillCode_Equal_人狼() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.人狼);
    }

    /**
     * Equal(=). As おまかせ人狼陣営 (WEREWOLFS). And OnlyOnceRegistered. <br>
     * おまかせ（人狼陣営）
     */
    public void setSecondRequestSkillCode_Equal_おまかせ人狼陣営() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.おまかせ人狼陣営);
    }

    /**
     * Equal(=). As 賢者 (WISE). And OnlyOnceRegistered. <br>
     * 賢者
     */
    public void setSecondRequestSkillCode_Equal_賢者() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.賢者);
    }

    /**
     * Equal(=). As 智狼 (WISEWOLF). And OnlyOnceRegistered. <br>
     * 智狼
     */
    public void setSecondRequestSkillCode_Equal_智狼() {
        setSecondRequestSkillCode_Equal_AsSkill(CDef.Skill.智狼);
    }

    protected void doSetSecondRequestSkillCode_Equal(String secondRequestSkillCode) {
        regSecondRequestSkillCode(CK_EQ, secondRequestSkillCode);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * SECOND_REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     * @param secondRequestSkillCode The value of secondRequestSkillCode as notEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setSecondRequestSkillCode_NotEqual(String secondRequestSkillCode) {
        doSetSecondRequestSkillCode_NotEqual(fRES(secondRequestSkillCode));
    }

    /**
     * NotEqual(&lt;&gt;). As Skill. And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * SECOND_REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill} <br>
     * 役職
     * @param cdef The instance of classification definition (as ENUM type). (basically NotNull: error as default, or no condition as option)
     */
    public void setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill cdef) {
        doSetSecondRequestSkillCode_NotEqual(cdef != null ? cdef.code() : null);
    }

    /**
     * NotEqual(&lt;&gt;). As 占星術師 (ASTROLOGER). And OnlyOnceRegistered. <br>
     * 占星術師
     */
    public void setSecondRequestSkillCode_NotEqual_占星術師() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.占星術師);
    }

    /**
     * NotEqual(&lt;&gt;). As パン屋 (BAKERY). And OnlyOnceRegistered. <br>
     * パン屋
     */
    public void setSecondRequestSkillCode_NotEqual_パン屋() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.パン屋);
    }

    /**
     * NotEqual(&lt;&gt;). As 爆弾魔 (BOMBER). And OnlyOnceRegistered. <br>
     * 爆弾魔
     */
    public void setSecondRequestSkillCode_NotEqual_爆弾魔() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.爆弾魔);
    }

    /**
     * NotEqual(&lt;&gt;). As C国狂人 (CMADMAN). And OnlyOnceRegistered. <br>
     * C国狂人
     */
    public void setSecondRequestSkillCode_NotEqual_C国狂人() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.C国狂人);
    }

    /**
     * NotEqual(&lt;&gt;). As 検死官 (CORONER). And OnlyOnceRegistered. <br>
     * 検死官
     */
    public void setSecondRequestSkillCode_NotEqual_検死官() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.検死官);
    }

    /**
     * NotEqual(&lt;&gt;). As 呪狼 (CURSEWOLF). And OnlyOnceRegistered. <br>
     * 呪狼
     */
    public void setSecondRequestSkillCode_NotEqual_呪狼() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.呪狼);
    }

    /**
     * NotEqual(&lt;&gt;). As 探偵 (DETECTIVE). And OnlyOnceRegistered. <br>
     * 探偵
     */
    public void setSecondRequestSkillCode_NotEqual_探偵() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.探偵);
    }

    /**
     * NotEqual(&lt;&gt;). As 魔神官 (EVILMEDIUM). And OnlyOnceRegistered. <br>
     * 魔神官
     */
    public void setSecondRequestSkillCode_NotEqual_魔神官() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.魔神官);
    }

    /**
     * NotEqual(&lt;&gt;). As 狂信者 (FANATIC). And OnlyOnceRegistered. <br>
     * 狂信者
     */
    public void setSecondRequestSkillCode_NotEqual_狂信者() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.狂信者);
    }

    /**
     * NotEqual(&lt;&gt;). As おまかせ足音職 (FOOTSTEPS). And OnlyOnceRegistered. <br>
     * おまかせ（足音職）
     */
    public void setSecondRequestSkillCode_NotEqual_おまかせ足音職() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.おまかせ足音職);
    }

    /**
     * NotEqual(&lt;&gt;). As 妖狐 (FOX). And OnlyOnceRegistered. <br>
     * 妖狐
     */
    public void setSecondRequestSkillCode_NotEqual_妖狐() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.妖狐);
    }

    /**
     * NotEqual(&lt;&gt;). As おまかせ役職窓あり (FRIENDS). And OnlyOnceRegistered. <br>
     * おまかせ（役職窓あり）
     */
    public void setSecondRequestSkillCode_NotEqual_おまかせ役職窓あり() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.おまかせ役職窓あり);
    }

    /**
     * NotEqual(&lt;&gt;). As 導師 (GURU). And OnlyOnceRegistered. <br>
     * 導師
     */
    public void setSecondRequestSkillCode_NotEqual_導師() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.導師);
    }

    /**
     * NotEqual(&lt;&gt;). As 狩人 (HUNTER). And OnlyOnceRegistered. <br>
     * 狩人
     */
    public void setSecondRequestSkillCode_NotEqual_狩人() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.狩人);
    }

    /**
     * NotEqual(&lt;&gt;). As おまかせ (LEFTOVER). And OnlyOnceRegistered. <br>
     * おまかせ
     */
    public void setSecondRequestSkillCode_NotEqual_おまかせ() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.おまかせ);
    }

    /**
     * NotEqual(&lt;&gt;). As 狂人 (MADMAN). And OnlyOnceRegistered. <br>
     * 狂人
     */
    public void setSecondRequestSkillCode_NotEqual_狂人() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.狂人);
    }

    /**
     * NotEqual(&lt;&gt;). As 共鳴者 (MASON). And OnlyOnceRegistered. <br>
     * 共鳴者
     */
    public void setSecondRequestSkillCode_NotEqual_共鳴者() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.共鳴者);
    }

    /**
     * NotEqual(&lt;&gt;). As 霊能者 (MEDIUM). And OnlyOnceRegistered. <br>
     * 霊能者
     */
    public void setSecondRequestSkillCode_NotEqual_霊能者() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.霊能者);
    }

    /**
     * NotEqual(&lt;&gt;). As おまかせ人外 (NOVILLAGERS). And OnlyOnceRegistered. <br>
     * おまかせ（人外）
     */
    public void setSecondRequestSkillCode_NotEqual_おまかせ人外() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.おまかせ人外);
    }

    /**
     * NotEqual(&lt;&gt;). As 占い師 (SEER). And OnlyOnceRegistered. <br>
     * 占い師
     */
    public void setSecondRequestSkillCode_NotEqual_占い師() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.占い師);
    }

    /**
     * NotEqual(&lt;&gt;). As 罠師 (TRAPPER). And OnlyOnceRegistered. <br>
     * 罠師
     */
    public void setSecondRequestSkillCode_NotEqual_罠師() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.罠師);
    }

    /**
     * NotEqual(&lt;&gt;). As 村人 (VILLAGER). And OnlyOnceRegistered. <br>
     * 村人
     */
    public void setSecondRequestSkillCode_NotEqual_村人() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.村人);
    }

    /**
     * NotEqual(&lt;&gt;). As おまかせ村人陣営 (VILLAGERS). And OnlyOnceRegistered. <br>
     * おまかせ（村人陣営）
     */
    public void setSecondRequestSkillCode_NotEqual_おまかせ村人陣営() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.おまかせ村人陣営);
    }

    /**
     * NotEqual(&lt;&gt;). As 人狼 (WEREWOLF). And OnlyOnceRegistered. <br>
     * 人狼
     */
    public void setSecondRequestSkillCode_NotEqual_人狼() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.人狼);
    }

    /**
     * NotEqual(&lt;&gt;). As おまかせ人狼陣営 (WEREWOLFS). And OnlyOnceRegistered. <br>
     * おまかせ（人狼陣営）
     */
    public void setSecondRequestSkillCode_NotEqual_おまかせ人狼陣営() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.おまかせ人狼陣営);
    }

    /**
     * NotEqual(&lt;&gt;). As 賢者 (WISE). And OnlyOnceRegistered. <br>
     * 賢者
     */
    public void setSecondRequestSkillCode_NotEqual_賢者() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.賢者);
    }

    /**
     * NotEqual(&lt;&gt;). As 智狼 (WISEWOLF). And OnlyOnceRegistered. <br>
     * 智狼
     */
    public void setSecondRequestSkillCode_NotEqual_智狼() {
        setSecondRequestSkillCode_NotEqual_AsSkill(CDef.Skill.智狼);
    }

    protected void doSetSecondRequestSkillCode_NotEqual(String secondRequestSkillCode) {
        regSecondRequestSkillCode(CK_NES, secondRequestSkillCode);
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * SECOND_REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     * @param secondRequestSkillCodeList The collection of secondRequestSkillCode as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setSecondRequestSkillCode_InScope(Collection<String> secondRequestSkillCodeList) {
        doSetSecondRequestSkillCode_InScope(secondRequestSkillCodeList);
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * SECOND_REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill} <br>
     * 役職
     * @param cdefList The list of classification definition (as ENUM type). (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setSecondRequestSkillCode_InScope_AsSkill(Collection<CDef.Skill> cdefList) {
        doSetSecondRequestSkillCode_InScope(cTStrL(cdefList));
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 囁きを見られる <br>
     * The group elements:[人狼, 呪狼, 智狼, C国狂人]
     */
    public void setSecondRequestSkillCode_InScope_ViewableWerewolfSay() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfViewableWerewolfSay());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 囁き可能 <br>
     * The group elements:[人狼, 呪狼, 智狼, C国狂人]
     */
    public void setSecondRequestSkillCode_InScope_AvailableWerewolfSay() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfAvailableWerewolfSay());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 共鳴発言を見られる <br>
     * The group elements:[共鳴者]
     */
    public void setSecondRequestSkillCode_InScope_ViewableMasonSay() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfViewableMasonSay());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 共鳴発言可能 <br>
     * The group elements:[共鳴者]
     */
    public void setSecondRequestSkillCode_InScope_AvailableMasonSay() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfAvailableMasonSay());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 白黒占い能力を持つ <br>
     * The group elements:[占い師]
     */
    public void setSecondRequestSkillCode_InScope_HasDivineAbility() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasDivineAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 白黒占い結果が人狼になる <br>
     * The group elements:[人狼, 呪狼, 智狼]
     */
    public void setSecondRequestSkillCode_InScope_DivineResultWolf() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfDivineResultWolf());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 役職占い能力を持つ <br>
     * The group elements:[賢者]
     */
    public void setSecondRequestSkillCode_InScope_HasWiseAbility() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasWiseAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 占星術能力を持つ <br>
     * The group elements:[占星術師]
     */
    public void setSecondRequestSkillCode_InScope_HasAstroAbility() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasAstroAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 占いにより死亡する <br>
     * The group elements:[妖狐]
     */
    public void setSecondRequestSkillCode_InScope_DeadByDivine() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfDeadByDivine());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 占いにより占った側が死亡する <br>
     * The group elements:[呪狼]
     */
    public void setSecondRequestSkillCode_InScope_CounterDeadByDivine() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfCounterDeadByDivine());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 白黒霊能能力を持つ <br>
     * The group elements:[霊能者]
     */
    public void setSecondRequestSkillCode_InScope_HasPsychicAbility() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasPsychicAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 白黒霊能結果が人狼になる <br>
     * The group elements:[人狼, 呪狼, 智狼]
     */
    public void setSecondRequestSkillCode_InScope_PsychicResultWolf() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfPsychicResultWolf());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 役職霊能能力を持つ <br>
     * The group elements:[導師, 魔神官]
     */
    public void setSecondRequestSkillCode_InScope_HasSkillPsychicAbility() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasSkillPsychicAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 護衛能力を持つ <br>
     * The group elements:[狩人]
     */
    public void setSecondRequestSkillCode_InScope_HasGuardAbility() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasGuardAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 検死能力を持つ <br>
     * The group elements:[検死官]
     */
    public void setSecondRequestSkillCode_InScope_HasAutopsyAbility() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasAutopsyAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 探偵能力を持つ <br>
     * The group elements:[探偵]
     */
    public void setSecondRequestSkillCode_InScope_HasInvestigateAbility() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasInvestigateAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 罠能力を持つ <br>
     * The group elements:[罠師]
     */
    public void setSecondRequestSkillCode_InScope_HasTrapAbility() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasTrapAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 爆弾能力を持つ <br>
     * The group elements:[爆弾魔]
     */
    public void setSecondRequestSkillCode_InScope_HasBombAbility() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasBombAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * パン屋能力を持つ <br>
     * The group elements:[パン屋]
     */
    public void setSecondRequestSkillCode_InScope_HasBakeryAbility() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasBakeryAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 襲撃能力を持つ <br>
     * The group elements:[人狼, 呪狼, 智狼]
     */
    public void setSecondRequestSkillCode_InScope_HasAttackAbility() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasAttackAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 襲撃に成功すると対象の役職を知ることができる <br>
     * The group elements:[智狼]
     */
    public void setSecondRequestSkillCode_InScope_HasAttackWiseAbility() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasAttackWiseAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 襲撃されても死なない <br>
     * The group elements:[妖狐, 爆弾魔]
     */
    public void setSecondRequestSkillCode_InScope_NoDeadByAttack() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfNoDeadByAttack());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 襲撃対象に選べない <br>
     * The group elements:[人狼, 呪狼, 智狼]
     */
    public void setSecondRequestSkillCode_InScope_NotSelectableAttack() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfNotSelectableAttack());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 狂人能力を持つ <br>
     * The group elements:[C国狂人, 狂人, 狂信者, 魔神官]
     */
    public void setSecondRequestSkillCode_InScope_HasMadmanAbility() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasMadmanAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 徘徊能力を持つ <br>
     * The group elements:[C国狂人, 狂人, 狂信者, 魔神官, 妖狐]
     */
    public void setSecondRequestSkillCode_InScope_HasDisturbAbility() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfHasDisturbAbility());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 勝敗判定時狼にカウントする <br>
     * The group elements:[人狼, 呪狼, 智狼]
     */
    public void setSecondRequestSkillCode_InScope_CountWolf() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfCountWolf());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * 勝敗判定時に人間としてカウントしない <br>
     * The group elements:[妖狐]
     */
    public void setSecondRequestSkillCode_InScope_NoCount() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfNoCount());
    }

    /**
     * InScope {in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 役職 <br>
     * おまかせ系 <br>
     * The group elements:[おまかせ, おまかせ村人陣営, おまかせ人狼陣営, おまかせ人外, おまかせ足音職, おまかせ役職窓あり]
     */
    public void setSecondRequestSkillCode_InScope_SomeoneSkill() {
        setSecondRequestSkillCode_InScope_AsSkill(CDef.Skill.listOfSomeoneSkill());
    }

    protected void doSetSecondRequestSkillCode_InScope(Collection<String> secondRequestSkillCodeList) {
        regINS(CK_INS, cTL(secondRequestSkillCodeList), xgetCValueSecondRequestSkillCode(), "SECOND_REQUEST_SKILL_CODE");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * SECOND_REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     * @param secondRequestSkillCodeList The collection of secondRequestSkillCode as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setSecondRequestSkillCode_NotInScope(Collection<String> secondRequestSkillCodeList) {
        doSetSecondRequestSkillCode_NotInScope(secondRequestSkillCodeList);
    }

    /**
     * NotInScope {not in ('a', 'b')}. As Skill. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * SECOND_REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill} <br>
     * 役職
     * @param cdefList The list of classification definition (as ENUM type). (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setSecondRequestSkillCode_NotInScope_AsSkill(Collection<CDef.Skill> cdefList) {
        doSetSecondRequestSkillCode_NotInScope(cTStrL(cdefList));
    }

    protected void doSetSecondRequestSkillCode_NotInScope(Collection<String> secondRequestSkillCodeList) {
        regINS(CK_NINS, cTL(secondRequestSkillCodeList), xgetCValueSecondRequestSkillCode(), "SECOND_REQUEST_SKILL_CODE");
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * SECOND_REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     */
    public void setSecondRequestSkillCode_IsNull() { regSecondRequestSkillCode(CK_ISN, DOBJ); }

    /**
     * IsNullOrEmpty {is null or empty}. And OnlyOnceRegistered. <br>
     * SECOND_REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     */
    public void setSecondRequestSkillCode_IsNullOrEmpty() { regSecondRequestSkillCode(CK_ISNOE, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * SECOND_REQUEST_SKILL_CODE: {IX, VARCHAR(20), FK to skill, classification=Skill}
     */
    public void setSecondRequestSkillCode_IsNotNull() { regSecondRequestSkillCode(CK_ISNN, DOBJ); }

    protected void regSecondRequestSkillCode(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueSecondRequestSkillCode(), "SECOND_REQUEST_SKILL_CODE"); }
    protected abstract ConditionValue xgetCValueSecondRequestSkillCode();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * ROOM_NUMBER: {INT UNSIGNED(10)}
     * @param roomNumber The value of roomNumber as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setRoomNumber_Equal(Integer roomNumber) {
        doSetRoomNumber_Equal(roomNumber);
    }

    protected void doSetRoomNumber_Equal(Integer roomNumber) {
        regRoomNumber(CK_EQ, roomNumber);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * ROOM_NUMBER: {INT UNSIGNED(10)}
     * @param roomNumber The value of roomNumber as notEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setRoomNumber_NotEqual(Integer roomNumber) {
        doSetRoomNumber_NotEqual(roomNumber);
    }

    protected void doSetRoomNumber_NotEqual(Integer roomNumber) {
        regRoomNumber(CK_NES, roomNumber);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * ROOM_NUMBER: {INT UNSIGNED(10)}
     * @param roomNumber The value of roomNumber as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setRoomNumber_GreaterThan(Integer roomNumber) {
        regRoomNumber(CK_GT, roomNumber);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * ROOM_NUMBER: {INT UNSIGNED(10)}
     * @param roomNumber The value of roomNumber as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setRoomNumber_LessThan(Integer roomNumber) {
        regRoomNumber(CK_LT, roomNumber);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * ROOM_NUMBER: {INT UNSIGNED(10)}
     * @param roomNumber The value of roomNumber as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setRoomNumber_GreaterEqual(Integer roomNumber) {
        regRoomNumber(CK_GE, roomNumber);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * ROOM_NUMBER: {INT UNSIGNED(10)}
     * @param roomNumber The value of roomNumber as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setRoomNumber_LessEqual(Integer roomNumber) {
        regRoomNumber(CK_LE, roomNumber);
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * ROOM_NUMBER: {INT UNSIGNED(10)}
     * @param minNumber The min number of roomNumber. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of roomNumber. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of range-of. (NotNull)
     */
    public void setRoomNumber_RangeOf(Integer minNumber, Integer maxNumber, ConditionOptionCall<RangeOfOption> opLambda) {
        setRoomNumber_RangeOf(minNumber, maxNumber, xcROOP(opLambda));
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * ROOM_NUMBER: {INT UNSIGNED(10)}
     * @param minNumber The min number of roomNumber. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of roomNumber. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    protected void setRoomNumber_RangeOf(Integer minNumber, Integer maxNumber, RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, xgetCValueRoomNumber(), "ROOM_NUMBER", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * ROOM_NUMBER: {INT UNSIGNED(10)}
     * @param roomNumberList The collection of roomNumber as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setRoomNumber_InScope(Collection<Integer> roomNumberList) {
        doSetRoomNumber_InScope(roomNumberList);
    }

    protected void doSetRoomNumber_InScope(Collection<Integer> roomNumberList) {
        regINS(CK_INS, cTL(roomNumberList), xgetCValueRoomNumber(), "ROOM_NUMBER");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * ROOM_NUMBER: {INT UNSIGNED(10)}
     * @param roomNumberList The collection of roomNumber as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setRoomNumber_NotInScope(Collection<Integer> roomNumberList) {
        doSetRoomNumber_NotInScope(roomNumberList);
    }

    protected void doSetRoomNumber_NotInScope(Collection<Integer> roomNumberList) {
        regINS(CK_NINS, cTL(roomNumberList), xgetCValueRoomNumber(), "ROOM_NUMBER");
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * ROOM_NUMBER: {INT UNSIGNED(10)}
     */
    public void setRoomNumber_IsNull() { regRoomNumber(CK_ISN, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * ROOM_NUMBER: {INT UNSIGNED(10)}
     */
    public void setRoomNumber_IsNotNull() { regRoomNumber(CK_ISNN, DOBJ); }

    protected void regRoomNumber(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueRoomNumber(), "ROOM_NUMBER"); }
    protected abstract ConditionValue xgetCValueRoomNumber();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * IS_DEAD: {NotNull, BIT, classification=Flg}
     * @param isDead The value of isDead as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setIsDead_Equal(Boolean isDead) {
        regIsDead(CK_EQ, isDead);
    }

    /**
     * Equal(=). As Flg. And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * IS_DEAD: {NotNull, BIT, classification=Flg} <br>
     * フラグを示す
     * @param cdef The instance of classification definition (as ENUM type). (basically NotNull: error as default, or no condition as option)
     */
    public void setIsDead_Equal_AsFlg(CDef.Flg cdef) {
        doSetIsDead_Equal(cdef != null ? Boolean.valueOf(cdef.code()) : null);
    }

    /**
     * Equal(=). As True. And OnlyOnceRegistered. <br>
     * はい: 有効を示す
     */
    public void setIsDead_Equal_True() {
        doSetIsDead_Equal(Boolean.valueOf(CDef.Flg.True.code()));
    }

    /**
     * Equal(=). As False. And OnlyOnceRegistered. <br>
     * いいえ: 無効を示す
     */
    public void setIsDead_Equal_False() {
        doSetIsDead_Equal(Boolean.valueOf(CDef.Flg.False.code()));
    }

    protected void doSetIsDead_Equal(Boolean isDead) {
        regIsDead(CK_EQ, isDead);
    }

    protected void regIsDead(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueIsDead(), "IS_DEAD"); }
    protected abstract ConditionValue xgetCValueIsDead();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * IS_SPECTATOR: {NotNull, BIT, classification=Flg}
     * @param isSpectator The value of isSpectator as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setIsSpectator_Equal(Boolean isSpectator) {
        regIsSpectator(CK_EQ, isSpectator);
    }

    /**
     * Equal(=). As Flg. And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * IS_SPECTATOR: {NotNull, BIT, classification=Flg} <br>
     * フラグを示す
     * @param cdef The instance of classification definition (as ENUM type). (basically NotNull: error as default, or no condition as option)
     */
    public void setIsSpectator_Equal_AsFlg(CDef.Flg cdef) {
        doSetIsSpectator_Equal(cdef != null ? Boolean.valueOf(cdef.code()) : null);
    }

    /**
     * Equal(=). As True. And OnlyOnceRegistered. <br>
     * はい: 有効を示す
     */
    public void setIsSpectator_Equal_True() {
        doSetIsSpectator_Equal(Boolean.valueOf(CDef.Flg.True.code()));
    }

    /**
     * Equal(=). As False. And OnlyOnceRegistered. <br>
     * いいえ: 無効を示す
     */
    public void setIsSpectator_Equal_False() {
        doSetIsSpectator_Equal(Boolean.valueOf(CDef.Flg.False.code()));
    }

    protected void doSetIsSpectator_Equal(Boolean isSpectator) {
        regIsSpectator(CK_EQ, isSpectator);
    }

    protected void regIsSpectator(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueIsSpectator(), "IS_SPECTATOR"); }
    protected abstract ConditionValue xgetCValueIsSpectator();

    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * DEAD_REASON_CODE: {IX, VARCHAR(20), FK to dead_reason, classification=DeadReason}
     * @param deadReasonCode The value of deadReasonCode as equal. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setDeadReasonCode_Equal(String deadReasonCode) {
        doSetDeadReasonCode_Equal(fRES(deadReasonCode));
    }

    /**
     * Equal(=). As DeadReason. And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * DEAD_REASON_CODE: {IX, VARCHAR(20), FK to dead_reason, classification=DeadReason} <br>
     * 死亡理由
     * @param cdef The instance of classification definition (as ENUM type). (basically NotNull: error as default, or no condition as option)
     */
    public void setDeadReasonCode_Equal_AsDeadReason(CDef.DeadReason cdef) {
        doSetDeadReasonCode_Equal(cdef != null ? cdef.code() : null);
    }

    /**
     * Equal(=). As 襲撃 (ATTACK). And OnlyOnceRegistered. <br>
     * 襲撃
     */
    public void setDeadReasonCode_Equal_襲撃() {
        setDeadReasonCode_Equal_AsDeadReason(CDef.DeadReason.襲撃);
    }

    /**
     * Equal(=). As 爆死 (BOMBED). And OnlyOnceRegistered. <br>
     * 爆死
     */
    public void setDeadReasonCode_Equal_爆死() {
        setDeadReasonCode_Equal_AsDeadReason(CDef.DeadReason.爆死);
    }

    /**
     * Equal(=). As 呪殺 (DIVINED). And OnlyOnceRegistered. <br>
     * 呪殺
     */
    public void setDeadReasonCode_Equal_呪殺() {
        setDeadReasonCode_Equal_AsDeadReason(CDef.DeadReason.呪殺);
    }

    /**
     * Equal(=). As 処刑 (EXECUTE). And OnlyOnceRegistered. <br>
     * 処刑
     */
    public void setDeadReasonCode_Equal_処刑() {
        setDeadReasonCode_Equal_AsDeadReason(CDef.DeadReason.処刑);
    }

    /**
     * Equal(=). As 突然 (SUDDON). And OnlyOnceRegistered. <br>
     * 突然
     */
    public void setDeadReasonCode_Equal_突然() {
        setDeadReasonCode_Equal_AsDeadReason(CDef.DeadReason.突然);
    }

    /**
     * Equal(=). As 罠死 (TRAPPED). And OnlyOnceRegistered. <br>
     * 罠死
     */
    public void setDeadReasonCode_Equal_罠死() {
        setDeadReasonCode_Equal_AsDeadReason(CDef.DeadReason.罠死);
    }

    protected void doSetDeadReasonCode_Equal(String deadReasonCode) {
        regDeadReasonCode(CK_EQ, deadReasonCode);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * DEAD_REASON_CODE: {IX, VARCHAR(20), FK to dead_reason, classification=DeadReason}
     * @param deadReasonCode The value of deadReasonCode as notEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setDeadReasonCode_NotEqual(String deadReasonCode) {
        doSetDeadReasonCode_NotEqual(fRES(deadReasonCode));
    }

    /**
     * NotEqual(&lt;&gt;). As DeadReason. And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * DEAD_REASON_CODE: {IX, VARCHAR(20), FK to dead_reason, classification=DeadReason} <br>
     * 死亡理由
     * @param cdef The instance of classification definition (as ENUM type). (basically NotNull: error as default, or no condition as option)
     */
    public void setDeadReasonCode_NotEqual_AsDeadReason(CDef.DeadReason cdef) {
        doSetDeadReasonCode_NotEqual(cdef != null ? cdef.code() : null);
    }

    /**
     * NotEqual(&lt;&gt;). As 襲撃 (ATTACK). And OnlyOnceRegistered. <br>
     * 襲撃
     */
    public void setDeadReasonCode_NotEqual_襲撃() {
        setDeadReasonCode_NotEqual_AsDeadReason(CDef.DeadReason.襲撃);
    }

    /**
     * NotEqual(&lt;&gt;). As 爆死 (BOMBED). And OnlyOnceRegistered. <br>
     * 爆死
     */
    public void setDeadReasonCode_NotEqual_爆死() {
        setDeadReasonCode_NotEqual_AsDeadReason(CDef.DeadReason.爆死);
    }

    /**
     * NotEqual(&lt;&gt;). As 呪殺 (DIVINED). And OnlyOnceRegistered. <br>
     * 呪殺
     */
    public void setDeadReasonCode_NotEqual_呪殺() {
        setDeadReasonCode_NotEqual_AsDeadReason(CDef.DeadReason.呪殺);
    }

    /**
     * NotEqual(&lt;&gt;). As 処刑 (EXECUTE). And OnlyOnceRegistered. <br>
     * 処刑
     */
    public void setDeadReasonCode_NotEqual_処刑() {
        setDeadReasonCode_NotEqual_AsDeadReason(CDef.DeadReason.処刑);
    }

    /**
     * NotEqual(&lt;&gt;). As 突然 (SUDDON). And OnlyOnceRegistered. <br>
     * 突然
     */
    public void setDeadReasonCode_NotEqual_突然() {
        setDeadReasonCode_NotEqual_AsDeadReason(CDef.DeadReason.突然);
    }

    /**
     * NotEqual(&lt;&gt;). As 罠死 (TRAPPED). And OnlyOnceRegistered. <br>
     * 罠死
     */
    public void setDeadReasonCode_NotEqual_罠死() {
        setDeadReasonCode_NotEqual_AsDeadReason(CDef.DeadReason.罠死);
    }

    protected void doSetDeadReasonCode_NotEqual(String deadReasonCode) {
        regDeadReasonCode(CK_NES, deadReasonCode);
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * DEAD_REASON_CODE: {IX, VARCHAR(20), FK to dead_reason, classification=DeadReason}
     * @param deadReasonCodeList The collection of deadReasonCode as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setDeadReasonCode_InScope(Collection<String> deadReasonCodeList) {
        doSetDeadReasonCode_InScope(deadReasonCodeList);
    }

    /**
     * InScope {in ('a', 'b')}. As DeadReason. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * DEAD_REASON_CODE: {IX, VARCHAR(20), FK to dead_reason, classification=DeadReason} <br>
     * 死亡理由
     * @param cdefList The list of classification definition (as ENUM type). (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setDeadReasonCode_InScope_AsDeadReason(Collection<CDef.DeadReason> cdefList) {
        doSetDeadReasonCode_InScope(cTStrL(cdefList));
    }

    /**
     * InScope {in ('a', 'b')}. As DeadReason. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 死亡理由 <br>
     * 無惨な死 <br>
     * The group elements:[襲撃, 呪殺]
     */
    public void setDeadReasonCode_InScope_MiserableDeath() {
        setDeadReasonCode_InScope_AsDeadReason(CDef.DeadReason.listOfMiserableDeath());
    }

    /**
     * InScope {in ('a', 'b')}. As DeadReason. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * 死亡理由 <br>
     * 霊能判定可能な死因 <br>
     * The group elements:[処刑, 突然]
     */
    public void setDeadReasonCode_InScope_PsychicableDeath() {
        setDeadReasonCode_InScope_AsDeadReason(CDef.DeadReason.listOfPsychicableDeath());
    }

    protected void doSetDeadReasonCode_InScope(Collection<String> deadReasonCodeList) {
        regINS(CK_INS, cTL(deadReasonCodeList), xgetCValueDeadReasonCode(), "DEAD_REASON_CODE");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * DEAD_REASON_CODE: {IX, VARCHAR(20), FK to dead_reason, classification=DeadReason}
     * @param deadReasonCodeList The collection of deadReasonCode as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setDeadReasonCode_NotInScope(Collection<String> deadReasonCodeList) {
        doSetDeadReasonCode_NotInScope(deadReasonCodeList);
    }

    /**
     * NotInScope {not in ('a', 'b')}. As DeadReason. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * DEAD_REASON_CODE: {IX, VARCHAR(20), FK to dead_reason, classification=DeadReason} <br>
     * 死亡理由
     * @param cdefList The list of classification definition (as ENUM type). (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setDeadReasonCode_NotInScope_AsDeadReason(Collection<CDef.DeadReason> cdefList) {
        doSetDeadReasonCode_NotInScope(cTStrL(cdefList));
    }

    protected void doSetDeadReasonCode_NotInScope(Collection<String> deadReasonCodeList) {
        regINS(CK_NINS, cTL(deadReasonCodeList), xgetCValueDeadReasonCode(), "DEAD_REASON_CODE");
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * DEAD_REASON_CODE: {IX, VARCHAR(20), FK to dead_reason, classification=DeadReason}
     */
    public void setDeadReasonCode_IsNull() { regDeadReasonCode(CK_ISN, DOBJ); }

    /**
     * IsNullOrEmpty {is null or empty}. And OnlyOnceRegistered. <br>
     * DEAD_REASON_CODE: {IX, VARCHAR(20), FK to dead_reason, classification=DeadReason}
     */
    public void setDeadReasonCode_IsNullOrEmpty() { regDeadReasonCode(CK_ISNOE, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * DEAD_REASON_CODE: {IX, VARCHAR(20), FK to dead_reason, classification=DeadReason}
     */
    public void setDeadReasonCode_IsNotNull() { regDeadReasonCode(CK_ISNN, DOBJ); }

    protected void regDeadReasonCode(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueDeadReasonCode(), "DEAD_REASON_CODE"); }
    protected abstract ConditionValue xgetCValueDeadReasonCode();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * DEAD_DAY: {INT UNSIGNED(10)}
     * @param deadDay The value of deadDay as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setDeadDay_Equal(Integer deadDay) {
        doSetDeadDay_Equal(deadDay);
    }

    protected void doSetDeadDay_Equal(Integer deadDay) {
        regDeadDay(CK_EQ, deadDay);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * DEAD_DAY: {INT UNSIGNED(10)}
     * @param deadDay The value of deadDay as notEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setDeadDay_NotEqual(Integer deadDay) {
        doSetDeadDay_NotEqual(deadDay);
    }

    protected void doSetDeadDay_NotEqual(Integer deadDay) {
        regDeadDay(CK_NES, deadDay);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * DEAD_DAY: {INT UNSIGNED(10)}
     * @param deadDay The value of deadDay as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setDeadDay_GreaterThan(Integer deadDay) {
        regDeadDay(CK_GT, deadDay);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * DEAD_DAY: {INT UNSIGNED(10)}
     * @param deadDay The value of deadDay as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setDeadDay_LessThan(Integer deadDay) {
        regDeadDay(CK_LT, deadDay);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * DEAD_DAY: {INT UNSIGNED(10)}
     * @param deadDay The value of deadDay as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setDeadDay_GreaterEqual(Integer deadDay) {
        regDeadDay(CK_GE, deadDay);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * DEAD_DAY: {INT UNSIGNED(10)}
     * @param deadDay The value of deadDay as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setDeadDay_LessEqual(Integer deadDay) {
        regDeadDay(CK_LE, deadDay);
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * DEAD_DAY: {INT UNSIGNED(10)}
     * @param minNumber The min number of deadDay. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of deadDay. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of range-of. (NotNull)
     */
    public void setDeadDay_RangeOf(Integer minNumber, Integer maxNumber, ConditionOptionCall<RangeOfOption> opLambda) {
        setDeadDay_RangeOf(minNumber, maxNumber, xcROOP(opLambda));
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * DEAD_DAY: {INT UNSIGNED(10)}
     * @param minNumber The min number of deadDay. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of deadDay. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    protected void setDeadDay_RangeOf(Integer minNumber, Integer maxNumber, RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, xgetCValueDeadDay(), "DEAD_DAY", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * DEAD_DAY: {INT UNSIGNED(10)}
     * @param deadDayList The collection of deadDay as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setDeadDay_InScope(Collection<Integer> deadDayList) {
        doSetDeadDay_InScope(deadDayList);
    }

    protected void doSetDeadDay_InScope(Collection<Integer> deadDayList) {
        regINS(CK_INS, cTL(deadDayList), xgetCValueDeadDay(), "DEAD_DAY");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * DEAD_DAY: {INT UNSIGNED(10)}
     * @param deadDayList The collection of deadDay as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setDeadDay_NotInScope(Collection<Integer> deadDayList) {
        doSetDeadDay_NotInScope(deadDayList);
    }

    protected void doSetDeadDay_NotInScope(Collection<Integer> deadDayList) {
        regINS(CK_NINS, cTL(deadDayList), xgetCValueDeadDay(), "DEAD_DAY");
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * DEAD_DAY: {INT UNSIGNED(10)}
     */
    public void setDeadDay_IsNull() { regDeadDay(CK_ISN, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * DEAD_DAY: {INT UNSIGNED(10)}
     */
    public void setDeadDay_IsNotNull() { regDeadDay(CK_ISNN, DOBJ); }

    protected void regDeadDay(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueDeadDay(), "DEAD_DAY"); }
    protected abstract ConditionValue xgetCValueDeadDay();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * IS_GONE: {NotNull, BIT, classification=Flg}
     * @param isGone The value of isGone as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setIsGone_Equal(Boolean isGone) {
        regIsGone(CK_EQ, isGone);
    }

    /**
     * Equal(=). As Flg. And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * IS_GONE: {NotNull, BIT, classification=Flg} <br>
     * フラグを示す
     * @param cdef The instance of classification definition (as ENUM type). (basically NotNull: error as default, or no condition as option)
     */
    public void setIsGone_Equal_AsFlg(CDef.Flg cdef) {
        doSetIsGone_Equal(cdef != null ? Boolean.valueOf(cdef.code()) : null);
    }

    /**
     * Equal(=). As True. And OnlyOnceRegistered. <br>
     * はい: 有効を示す
     */
    public void setIsGone_Equal_True() {
        doSetIsGone_Equal(Boolean.valueOf(CDef.Flg.True.code()));
    }

    /**
     * Equal(=). As False. And OnlyOnceRegistered. <br>
     * いいえ: 無効を示す
     */
    public void setIsGone_Equal_False() {
        doSetIsGone_Equal(Boolean.valueOf(CDef.Flg.False.code()));
    }

    protected void doSetIsGone_Equal(Boolean isGone) {
        regIsGone(CK_EQ, isGone);
    }

    protected void regIsGone(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueIsGone(), "IS_GONE"); }
    protected abstract ConditionValue xgetCValueIsGone();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * LAST_ACCESS_DATETIME: {DATETIME(19)}
     * @param lastAccessDatetime The value of lastAccessDatetime as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setLastAccessDatetime_Equal(java.time.LocalDateTime lastAccessDatetime) {
        regLastAccessDatetime(CK_EQ,  lastAccessDatetime);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * LAST_ACCESS_DATETIME: {DATETIME(19)}
     * @param lastAccessDatetime The value of lastAccessDatetime as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setLastAccessDatetime_GreaterThan(java.time.LocalDateTime lastAccessDatetime) {
        regLastAccessDatetime(CK_GT,  lastAccessDatetime);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * LAST_ACCESS_DATETIME: {DATETIME(19)}
     * @param lastAccessDatetime The value of lastAccessDatetime as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setLastAccessDatetime_LessThan(java.time.LocalDateTime lastAccessDatetime) {
        regLastAccessDatetime(CK_LT,  lastAccessDatetime);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * LAST_ACCESS_DATETIME: {DATETIME(19)}
     * @param lastAccessDatetime The value of lastAccessDatetime as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setLastAccessDatetime_GreaterEqual(java.time.LocalDateTime lastAccessDatetime) {
        regLastAccessDatetime(CK_GE,  lastAccessDatetime);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * LAST_ACCESS_DATETIME: {DATETIME(19)}
     * @param lastAccessDatetime The value of lastAccessDatetime as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setLastAccessDatetime_LessEqual(java.time.LocalDateTime lastAccessDatetime) {
        regLastAccessDatetime(CK_LE, lastAccessDatetime);
    }

    /**
     * FromTo with various options. (versatile) {(default) fromDatetime &lt;= column &lt;= toDatetime} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * LAST_ACCESS_DATETIME: {DATETIME(19)}
     * <pre>e.g. setLastAccessDatetime_FromTo(fromDate, toDate, op <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> op.<span style="color: #CC4747">compareAsDate()</span>);</pre>
     * @param fromDatetime The from-datetime(yyyy/MM/dd HH:mm:ss.SSS) of lastAccessDatetime. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param toDatetime The to-datetime(yyyy/MM/dd HH:mm:ss.SSS) of lastAccessDatetime. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of from-to. (NotNull)
     */
    public void setLastAccessDatetime_FromTo(java.time.LocalDateTime fromDatetime, java.time.LocalDateTime toDatetime, ConditionOptionCall<FromToOption> opLambda) {
        setLastAccessDatetime_FromTo(fromDatetime, toDatetime, xcFTOP(opLambda));
    }

    /**
     * FromTo with various options. (versatile) {(default) fromDatetime &lt;= column &lt;= toDatetime} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * LAST_ACCESS_DATETIME: {DATETIME(19)}
     * <pre>e.g. setLastAccessDatetime_FromTo(fromDate, toDate, new <span style="color: #CC4747">FromToOption</span>().compareAsDate());</pre>
     * @param fromDatetime The from-datetime(yyyy/MM/dd HH:mm:ss.SSS) of lastAccessDatetime. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param toDatetime The to-datetime(yyyy/MM/dd HH:mm:ss.SSS) of lastAccessDatetime. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param fromToOption The option of from-to. (NotNull)
     */
    protected void setLastAccessDatetime_FromTo(java.time.LocalDateTime fromDatetime, java.time.LocalDateTime toDatetime, FromToOption fromToOption) {
        String nm = "LAST_ACCESS_DATETIME"; FromToOption op = fromToOption;
        regFTQ(xfFTHD(fromDatetime, nm, op), xfFTHD(toDatetime, nm, op), xgetCValueLastAccessDatetime(), nm, op);
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * LAST_ACCESS_DATETIME: {DATETIME(19)}
     */
    public void setLastAccessDatetime_IsNull() { regLastAccessDatetime(CK_ISN, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * LAST_ACCESS_DATETIME: {DATETIME(19)}
     */
    public void setLastAccessDatetime_IsNotNull() { regLastAccessDatetime(CK_ISNN, DOBJ); }

    protected void regLastAccessDatetime(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueLastAccessDatetime(), "LAST_ACCESS_DATETIME"); }
    protected abstract ConditionValue xgetCValueLastAccessDatetime();

    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * CAMP_CODE: {VARCHAR(20)}
     * @param campCode The value of campCode as equal. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setCampCode_Equal(String campCode) {
        doSetCampCode_Equal(fRES(campCode));
    }

    protected void doSetCampCode_Equal(String campCode) {
        regCampCode(CK_EQ, campCode);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * CAMP_CODE: {VARCHAR(20)}
     * @param campCode The value of campCode as notEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setCampCode_NotEqual(String campCode) {
        doSetCampCode_NotEqual(fRES(campCode));
    }

    protected void doSetCampCode_NotEqual(String campCode) {
        regCampCode(CK_NES, campCode);
    }

    /**
     * GreaterThan(&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * CAMP_CODE: {VARCHAR(20)}
     * @param campCode The value of campCode as greaterThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setCampCode_GreaterThan(String campCode) {
        regCampCode(CK_GT, fRES(campCode));
    }

    /**
     * LessThan(&lt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * CAMP_CODE: {VARCHAR(20)}
     * @param campCode The value of campCode as lessThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setCampCode_LessThan(String campCode) {
        regCampCode(CK_LT, fRES(campCode));
    }

    /**
     * GreaterEqual(&gt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * CAMP_CODE: {VARCHAR(20)}
     * @param campCode The value of campCode as greaterEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setCampCode_GreaterEqual(String campCode) {
        regCampCode(CK_GE, fRES(campCode));
    }

    /**
     * LessEqual(&lt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * CAMP_CODE: {VARCHAR(20)}
     * @param campCode The value of campCode as lessEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setCampCode_LessEqual(String campCode) {
        regCampCode(CK_LE, fRES(campCode));
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * CAMP_CODE: {VARCHAR(20)}
     * @param campCodeList The collection of campCode as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setCampCode_InScope(Collection<String> campCodeList) {
        doSetCampCode_InScope(campCodeList);
    }

    protected void doSetCampCode_InScope(Collection<String> campCodeList) {
        regINS(CK_INS, cTL(campCodeList), xgetCValueCampCode(), "CAMP_CODE");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * CAMP_CODE: {VARCHAR(20)}
     * @param campCodeList The collection of campCode as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setCampCode_NotInScope(Collection<String> campCodeList) {
        doSetCampCode_NotInScope(campCodeList);
    }

    protected void doSetCampCode_NotInScope(Collection<String> campCodeList) {
        regINS(CK_NINS, cTL(campCodeList), xgetCValueCampCode(), "CAMP_CODE");
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * CAMP_CODE: {VARCHAR(20)} <br>
     * <pre>e.g. setCampCode_LikeSearch("xxx", op <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> op.<span style="color: #CC4747">likeContain()</span>);</pre>
     * @param campCode The value of campCode as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setCampCode_LikeSearch(String campCode, ConditionOptionCall<LikeSearchOption> opLambda) {
        setCampCode_LikeSearch(campCode, xcLSOP(opLambda));
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * CAMP_CODE: {VARCHAR(20)} <br>
     * <pre>e.g. setCampCode_LikeSearch("xxx", new <span style="color: #CC4747">LikeSearchOption</span>().likeContain());</pre>
     * @param campCode The value of campCode as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of like-search. (NotNull)
     */
    protected void setCampCode_LikeSearch(String campCode, LikeSearchOption likeSearchOption) {
        regLSQ(CK_LS, fRES(campCode), xgetCValueCampCode(), "CAMP_CODE", likeSearchOption);
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * CAMP_CODE: {VARCHAR(20)}
     * @param campCode The value of campCode as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setCampCode_NotLikeSearch(String campCode, ConditionOptionCall<LikeSearchOption> opLambda) {
        setCampCode_NotLikeSearch(campCode, xcLSOP(opLambda));
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * CAMP_CODE: {VARCHAR(20)}
     * @param campCode The value of campCode as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of not-like-search. (NotNull)
     */
    protected void setCampCode_NotLikeSearch(String campCode, LikeSearchOption likeSearchOption) {
        regLSQ(CK_NLS, fRES(campCode), xgetCValueCampCode(), "CAMP_CODE", likeSearchOption);
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * CAMP_CODE: {VARCHAR(20)}
     */
    public void setCampCode_IsNull() { regCampCode(CK_ISN, DOBJ); }

    /**
     * IsNullOrEmpty {is null or empty}. And OnlyOnceRegistered. <br>
     * CAMP_CODE: {VARCHAR(20)}
     */
    public void setCampCode_IsNullOrEmpty() { regCampCode(CK_ISNOE, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * CAMP_CODE: {VARCHAR(20)}
     */
    public void setCampCode_IsNotNull() { regCampCode(CK_ISNN, DOBJ); }

    protected void regCampCode(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueCampCode(), "CAMP_CODE"); }
    protected abstract ConditionValue xgetCValueCampCode();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * IS_WIN: {BIT, classification=Flg}
     * @param isWin The value of isWin as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setIsWin_Equal(Boolean isWin) {
        regIsWin(CK_EQ, isWin);
    }

    /**
     * Equal(=). As Flg. And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * IS_WIN: {BIT, classification=Flg} <br>
     * フラグを示す
     * @param cdef The instance of classification definition (as ENUM type). (basically NotNull: error as default, or no condition as option)
     */
    public void setIsWin_Equal_AsFlg(CDef.Flg cdef) {
        doSetIsWin_Equal(cdef != null ? Boolean.valueOf(cdef.code()) : null);
    }

    /**
     * Equal(=). As True. And OnlyOnceRegistered. <br>
     * はい: 有効を示す
     */
    public void setIsWin_Equal_True() {
        doSetIsWin_Equal(Boolean.valueOf(CDef.Flg.True.code()));
    }

    /**
     * Equal(=). As False. And OnlyOnceRegistered. <br>
     * いいえ: 無効を示す
     */
    public void setIsWin_Equal_False() {
        doSetIsWin_Equal(Boolean.valueOf(CDef.Flg.False.code()));
    }

    protected void doSetIsWin_Equal(Boolean isWin) {
        regIsWin(CK_EQ, isWin);
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * IS_WIN: {BIT, classification=Flg}
     */
    public void setIsWin_IsNull() { regIsWin(CK_ISN, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * IS_WIN: {BIT, classification=Flg}
     */
    public void setIsWin_IsNotNull() { regIsWin(CK_ISNN, DOBJ); }

    protected void regIsWin(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueIsWin(), "IS_WIN"); }
    protected abstract ConditionValue xgetCValueIsWin();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * REGISTER_DATETIME: {NotNull, DATETIME(19)}
     * @param registerDatetime The value of registerDatetime as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setRegisterDatetime_Equal(java.time.LocalDateTime registerDatetime) {
        regRegisterDatetime(CK_EQ,  registerDatetime);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * REGISTER_DATETIME: {NotNull, DATETIME(19)}
     * @param registerDatetime The value of registerDatetime as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setRegisterDatetime_GreaterThan(java.time.LocalDateTime registerDatetime) {
        regRegisterDatetime(CK_GT,  registerDatetime);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * REGISTER_DATETIME: {NotNull, DATETIME(19)}
     * @param registerDatetime The value of registerDatetime as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setRegisterDatetime_LessThan(java.time.LocalDateTime registerDatetime) {
        regRegisterDatetime(CK_LT,  registerDatetime);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * REGISTER_DATETIME: {NotNull, DATETIME(19)}
     * @param registerDatetime The value of registerDatetime as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setRegisterDatetime_GreaterEqual(java.time.LocalDateTime registerDatetime) {
        regRegisterDatetime(CK_GE,  registerDatetime);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * REGISTER_DATETIME: {NotNull, DATETIME(19)}
     * @param registerDatetime The value of registerDatetime as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setRegisterDatetime_LessEqual(java.time.LocalDateTime registerDatetime) {
        regRegisterDatetime(CK_LE, registerDatetime);
    }

    /**
     * FromTo with various options. (versatile) {(default) fromDatetime &lt;= column &lt;= toDatetime} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * REGISTER_DATETIME: {NotNull, DATETIME(19)}
     * <pre>e.g. setRegisterDatetime_FromTo(fromDate, toDate, op <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> op.<span style="color: #CC4747">compareAsDate()</span>);</pre>
     * @param fromDatetime The from-datetime(yyyy/MM/dd HH:mm:ss.SSS) of registerDatetime. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param toDatetime The to-datetime(yyyy/MM/dd HH:mm:ss.SSS) of registerDatetime. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of from-to. (NotNull)
     */
    public void setRegisterDatetime_FromTo(java.time.LocalDateTime fromDatetime, java.time.LocalDateTime toDatetime, ConditionOptionCall<FromToOption> opLambda) {
        setRegisterDatetime_FromTo(fromDatetime, toDatetime, xcFTOP(opLambda));
    }

    /**
     * FromTo with various options. (versatile) {(default) fromDatetime &lt;= column &lt;= toDatetime} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * REGISTER_DATETIME: {NotNull, DATETIME(19)}
     * <pre>e.g. setRegisterDatetime_FromTo(fromDate, toDate, new <span style="color: #CC4747">FromToOption</span>().compareAsDate());</pre>
     * @param fromDatetime The from-datetime(yyyy/MM/dd HH:mm:ss.SSS) of registerDatetime. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param toDatetime The to-datetime(yyyy/MM/dd HH:mm:ss.SSS) of registerDatetime. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param fromToOption The option of from-to. (NotNull)
     */
    protected void setRegisterDatetime_FromTo(java.time.LocalDateTime fromDatetime, java.time.LocalDateTime toDatetime, FromToOption fromToOption) {
        String nm = "REGISTER_DATETIME"; FromToOption op = fromToOption;
        regFTQ(xfFTHD(fromDatetime, nm, op), xfFTHD(toDatetime, nm, op), xgetCValueRegisterDatetime(), nm, op);
    }

    protected void regRegisterDatetime(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueRegisterDatetime(), "REGISTER_DATETIME"); }
    protected abstract ConditionValue xgetCValueRegisterDatetime();

    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * REGISTER_TRACE: {NotNull, VARCHAR(64)}
     * @param registerTrace The value of registerTrace as equal. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setRegisterTrace_Equal(String registerTrace) {
        doSetRegisterTrace_Equal(fRES(registerTrace));
    }

    protected void doSetRegisterTrace_Equal(String registerTrace) {
        regRegisterTrace(CK_EQ, registerTrace);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * REGISTER_TRACE: {NotNull, VARCHAR(64)}
     * @param registerTrace The value of registerTrace as notEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setRegisterTrace_NotEqual(String registerTrace) {
        doSetRegisterTrace_NotEqual(fRES(registerTrace));
    }

    protected void doSetRegisterTrace_NotEqual(String registerTrace) {
        regRegisterTrace(CK_NES, registerTrace);
    }

    /**
     * GreaterThan(&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * REGISTER_TRACE: {NotNull, VARCHAR(64)}
     * @param registerTrace The value of registerTrace as greaterThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setRegisterTrace_GreaterThan(String registerTrace) {
        regRegisterTrace(CK_GT, fRES(registerTrace));
    }

    /**
     * LessThan(&lt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * REGISTER_TRACE: {NotNull, VARCHAR(64)}
     * @param registerTrace The value of registerTrace as lessThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setRegisterTrace_LessThan(String registerTrace) {
        regRegisterTrace(CK_LT, fRES(registerTrace));
    }

    /**
     * GreaterEqual(&gt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * REGISTER_TRACE: {NotNull, VARCHAR(64)}
     * @param registerTrace The value of registerTrace as greaterEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setRegisterTrace_GreaterEqual(String registerTrace) {
        regRegisterTrace(CK_GE, fRES(registerTrace));
    }

    /**
     * LessEqual(&lt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * REGISTER_TRACE: {NotNull, VARCHAR(64)}
     * @param registerTrace The value of registerTrace as lessEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setRegisterTrace_LessEqual(String registerTrace) {
        regRegisterTrace(CK_LE, fRES(registerTrace));
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * REGISTER_TRACE: {NotNull, VARCHAR(64)}
     * @param registerTraceList The collection of registerTrace as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setRegisterTrace_InScope(Collection<String> registerTraceList) {
        doSetRegisterTrace_InScope(registerTraceList);
    }

    protected void doSetRegisterTrace_InScope(Collection<String> registerTraceList) {
        regINS(CK_INS, cTL(registerTraceList), xgetCValueRegisterTrace(), "REGISTER_TRACE");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * REGISTER_TRACE: {NotNull, VARCHAR(64)}
     * @param registerTraceList The collection of registerTrace as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setRegisterTrace_NotInScope(Collection<String> registerTraceList) {
        doSetRegisterTrace_NotInScope(registerTraceList);
    }

    protected void doSetRegisterTrace_NotInScope(Collection<String> registerTraceList) {
        regINS(CK_NINS, cTL(registerTraceList), xgetCValueRegisterTrace(), "REGISTER_TRACE");
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * REGISTER_TRACE: {NotNull, VARCHAR(64)} <br>
     * <pre>e.g. setRegisterTrace_LikeSearch("xxx", op <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> op.<span style="color: #CC4747">likeContain()</span>);</pre>
     * @param registerTrace The value of registerTrace as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setRegisterTrace_LikeSearch(String registerTrace, ConditionOptionCall<LikeSearchOption> opLambda) {
        setRegisterTrace_LikeSearch(registerTrace, xcLSOP(opLambda));
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * REGISTER_TRACE: {NotNull, VARCHAR(64)} <br>
     * <pre>e.g. setRegisterTrace_LikeSearch("xxx", new <span style="color: #CC4747">LikeSearchOption</span>().likeContain());</pre>
     * @param registerTrace The value of registerTrace as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of like-search. (NotNull)
     */
    protected void setRegisterTrace_LikeSearch(String registerTrace, LikeSearchOption likeSearchOption) {
        regLSQ(CK_LS, fRES(registerTrace), xgetCValueRegisterTrace(), "REGISTER_TRACE", likeSearchOption);
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * REGISTER_TRACE: {NotNull, VARCHAR(64)}
     * @param registerTrace The value of registerTrace as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setRegisterTrace_NotLikeSearch(String registerTrace, ConditionOptionCall<LikeSearchOption> opLambda) {
        setRegisterTrace_NotLikeSearch(registerTrace, xcLSOP(opLambda));
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * REGISTER_TRACE: {NotNull, VARCHAR(64)}
     * @param registerTrace The value of registerTrace as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of not-like-search. (NotNull)
     */
    protected void setRegisterTrace_NotLikeSearch(String registerTrace, LikeSearchOption likeSearchOption) {
        regLSQ(CK_NLS, fRES(registerTrace), xgetCValueRegisterTrace(), "REGISTER_TRACE", likeSearchOption);
    }

    protected void regRegisterTrace(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueRegisterTrace(), "REGISTER_TRACE"); }
    protected abstract ConditionValue xgetCValueRegisterTrace();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * UPDATE_DATETIME: {NotNull, DATETIME(19)}
     * @param updateDatetime The value of updateDatetime as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setUpdateDatetime_Equal(java.time.LocalDateTime updateDatetime) {
        regUpdateDatetime(CK_EQ,  updateDatetime);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * UPDATE_DATETIME: {NotNull, DATETIME(19)}
     * @param updateDatetime The value of updateDatetime as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setUpdateDatetime_GreaterThan(java.time.LocalDateTime updateDatetime) {
        regUpdateDatetime(CK_GT,  updateDatetime);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * UPDATE_DATETIME: {NotNull, DATETIME(19)}
     * @param updateDatetime The value of updateDatetime as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setUpdateDatetime_LessThan(java.time.LocalDateTime updateDatetime) {
        regUpdateDatetime(CK_LT,  updateDatetime);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * UPDATE_DATETIME: {NotNull, DATETIME(19)}
     * @param updateDatetime The value of updateDatetime as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setUpdateDatetime_GreaterEqual(java.time.LocalDateTime updateDatetime) {
        regUpdateDatetime(CK_GE,  updateDatetime);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * UPDATE_DATETIME: {NotNull, DATETIME(19)}
     * @param updateDatetime The value of updateDatetime as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setUpdateDatetime_LessEqual(java.time.LocalDateTime updateDatetime) {
        regUpdateDatetime(CK_LE, updateDatetime);
    }

    /**
     * FromTo with various options. (versatile) {(default) fromDatetime &lt;= column &lt;= toDatetime} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * UPDATE_DATETIME: {NotNull, DATETIME(19)}
     * <pre>e.g. setUpdateDatetime_FromTo(fromDate, toDate, op <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> op.<span style="color: #CC4747">compareAsDate()</span>);</pre>
     * @param fromDatetime The from-datetime(yyyy/MM/dd HH:mm:ss.SSS) of updateDatetime. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param toDatetime The to-datetime(yyyy/MM/dd HH:mm:ss.SSS) of updateDatetime. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of from-to. (NotNull)
     */
    public void setUpdateDatetime_FromTo(java.time.LocalDateTime fromDatetime, java.time.LocalDateTime toDatetime, ConditionOptionCall<FromToOption> opLambda) {
        setUpdateDatetime_FromTo(fromDatetime, toDatetime, xcFTOP(opLambda));
    }

    /**
     * FromTo with various options. (versatile) {(default) fromDatetime &lt;= column &lt;= toDatetime} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * UPDATE_DATETIME: {NotNull, DATETIME(19)}
     * <pre>e.g. setUpdateDatetime_FromTo(fromDate, toDate, new <span style="color: #CC4747">FromToOption</span>().compareAsDate());</pre>
     * @param fromDatetime The from-datetime(yyyy/MM/dd HH:mm:ss.SSS) of updateDatetime. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param toDatetime The to-datetime(yyyy/MM/dd HH:mm:ss.SSS) of updateDatetime. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param fromToOption The option of from-to. (NotNull)
     */
    protected void setUpdateDatetime_FromTo(java.time.LocalDateTime fromDatetime, java.time.LocalDateTime toDatetime, FromToOption fromToOption) {
        String nm = "UPDATE_DATETIME"; FromToOption op = fromToOption;
        regFTQ(xfFTHD(fromDatetime, nm, op), xfFTHD(toDatetime, nm, op), xgetCValueUpdateDatetime(), nm, op);
    }

    protected void regUpdateDatetime(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueUpdateDatetime(), "UPDATE_DATETIME"); }
    protected abstract ConditionValue xgetCValueUpdateDatetime();

    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * UPDATE_TRACE: {NotNull, VARCHAR(64)}
     * @param updateTrace The value of updateTrace as equal. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setUpdateTrace_Equal(String updateTrace) {
        doSetUpdateTrace_Equal(fRES(updateTrace));
    }

    protected void doSetUpdateTrace_Equal(String updateTrace) {
        regUpdateTrace(CK_EQ, updateTrace);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * UPDATE_TRACE: {NotNull, VARCHAR(64)}
     * @param updateTrace The value of updateTrace as notEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setUpdateTrace_NotEqual(String updateTrace) {
        doSetUpdateTrace_NotEqual(fRES(updateTrace));
    }

    protected void doSetUpdateTrace_NotEqual(String updateTrace) {
        regUpdateTrace(CK_NES, updateTrace);
    }

    /**
     * GreaterThan(&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * UPDATE_TRACE: {NotNull, VARCHAR(64)}
     * @param updateTrace The value of updateTrace as greaterThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setUpdateTrace_GreaterThan(String updateTrace) {
        regUpdateTrace(CK_GT, fRES(updateTrace));
    }

    /**
     * LessThan(&lt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * UPDATE_TRACE: {NotNull, VARCHAR(64)}
     * @param updateTrace The value of updateTrace as lessThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setUpdateTrace_LessThan(String updateTrace) {
        regUpdateTrace(CK_LT, fRES(updateTrace));
    }

    /**
     * GreaterEqual(&gt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * UPDATE_TRACE: {NotNull, VARCHAR(64)}
     * @param updateTrace The value of updateTrace as greaterEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setUpdateTrace_GreaterEqual(String updateTrace) {
        regUpdateTrace(CK_GE, fRES(updateTrace));
    }

    /**
     * LessEqual(&lt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * UPDATE_TRACE: {NotNull, VARCHAR(64)}
     * @param updateTrace The value of updateTrace as lessEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setUpdateTrace_LessEqual(String updateTrace) {
        regUpdateTrace(CK_LE, fRES(updateTrace));
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * UPDATE_TRACE: {NotNull, VARCHAR(64)}
     * @param updateTraceList The collection of updateTrace as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setUpdateTrace_InScope(Collection<String> updateTraceList) {
        doSetUpdateTrace_InScope(updateTraceList);
    }

    protected void doSetUpdateTrace_InScope(Collection<String> updateTraceList) {
        regINS(CK_INS, cTL(updateTraceList), xgetCValueUpdateTrace(), "UPDATE_TRACE");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * UPDATE_TRACE: {NotNull, VARCHAR(64)}
     * @param updateTraceList The collection of updateTrace as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setUpdateTrace_NotInScope(Collection<String> updateTraceList) {
        doSetUpdateTrace_NotInScope(updateTraceList);
    }

    protected void doSetUpdateTrace_NotInScope(Collection<String> updateTraceList) {
        regINS(CK_NINS, cTL(updateTraceList), xgetCValueUpdateTrace(), "UPDATE_TRACE");
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * UPDATE_TRACE: {NotNull, VARCHAR(64)} <br>
     * <pre>e.g. setUpdateTrace_LikeSearch("xxx", op <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> op.<span style="color: #CC4747">likeContain()</span>);</pre>
     * @param updateTrace The value of updateTrace as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setUpdateTrace_LikeSearch(String updateTrace, ConditionOptionCall<LikeSearchOption> opLambda) {
        setUpdateTrace_LikeSearch(updateTrace, xcLSOP(opLambda));
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * UPDATE_TRACE: {NotNull, VARCHAR(64)} <br>
     * <pre>e.g. setUpdateTrace_LikeSearch("xxx", new <span style="color: #CC4747">LikeSearchOption</span>().likeContain());</pre>
     * @param updateTrace The value of updateTrace as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of like-search. (NotNull)
     */
    protected void setUpdateTrace_LikeSearch(String updateTrace, LikeSearchOption likeSearchOption) {
        regLSQ(CK_LS, fRES(updateTrace), xgetCValueUpdateTrace(), "UPDATE_TRACE", likeSearchOption);
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * UPDATE_TRACE: {NotNull, VARCHAR(64)}
     * @param updateTrace The value of updateTrace as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setUpdateTrace_NotLikeSearch(String updateTrace, ConditionOptionCall<LikeSearchOption> opLambda) {
        setUpdateTrace_NotLikeSearch(updateTrace, xcLSOP(opLambda));
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * UPDATE_TRACE: {NotNull, VARCHAR(64)}
     * @param updateTrace The value of updateTrace as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of not-like-search. (NotNull)
     */
    protected void setUpdateTrace_NotLikeSearch(String updateTrace, LikeSearchOption likeSearchOption) {
        regLSQ(CK_NLS, fRES(updateTrace), xgetCValueUpdateTrace(), "UPDATE_TRACE", likeSearchOption);
    }

    protected void regUpdateTrace(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueUpdateTrace(), "UPDATE_TRACE"); }
    protected abstract ConditionValue xgetCValueUpdateTrace();

    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    /**
     * Prepare ScalarCondition as equal. <br>
     * {where FOO = (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<VillagePlayerCB> scalar_Equal() {
        return xcreateSLCFunction(CK_EQ, VillagePlayerCB.class);
    }

    /**
     * Prepare ScalarCondition as equal. <br>
     * {where FOO &lt;&gt; (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<VillagePlayerCB> scalar_NotEqual() {
        return xcreateSLCFunction(CK_NES, VillagePlayerCB.class);
    }

    /**
     * Prepare ScalarCondition as greaterThan. <br>
     * {where FOO &gt; (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<VillagePlayerCB> scalar_GreaterThan() {
        return xcreateSLCFunction(CK_GT, VillagePlayerCB.class);
    }

    /**
     * Prepare ScalarCondition as lessThan. <br>
     * {where FOO &lt; (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<VillagePlayerCB> scalar_LessThan() {
        return xcreateSLCFunction(CK_LT, VillagePlayerCB.class);
    }

    /**
     * Prepare ScalarCondition as greaterEqual. <br>
     * {where FOO &gt;= (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<VillagePlayerCB> scalar_GreaterEqual() {
        return xcreateSLCFunction(CK_GE, VillagePlayerCB.class);
    }

    /**
     * Prepare ScalarCondition as lessEqual. <br>
     * {where FOO &lt;= (select max(BAR) from ...)}
     * <pre>
     * cb.query().<span style="color: #CC4747">scalar_LessEqual()</span>.max(new SubQuery&lt;VillagePlayerCB&gt;() {
     *     public void query(VillagePlayerCB subCB) {
     *         subCB.specify().setFoo... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setBar...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<VillagePlayerCB> scalar_LessEqual() {
        return xcreateSLCFunction(CK_LE, VillagePlayerCB.class);
    }

    @SuppressWarnings("unchecked")
    protected <CB extends ConditionBean> void xscalarCondition(String fn, SubQuery<CB> sq, String rd, HpSLCCustomized<CB> cs, ScalarConditionOption op) {
        assertObjectNotNull("subQuery", sq);
        VillagePlayerCB cb = xcreateScalarConditionCB(); sq.query((CB)cb);
        String pp = keepScalarCondition(cb.query()); // for saving query-value
        cs.setPartitionByCBean((CB)xcreateScalarConditionPartitionByCB()); // for using partition-by
        registerScalarCondition(fn, cb.query(), pp, rd, cs, op);
    }
    public abstract String keepScalarCondition(VillagePlayerCQ sq);

    protected VillagePlayerCB xcreateScalarConditionCB() {
        VillagePlayerCB cb = newMyCB(); cb.xsetupForScalarCondition(this); return cb;
    }

    protected VillagePlayerCB xcreateScalarConditionPartitionByCB() {
        VillagePlayerCB cb = newMyCB(); cb.xsetupForScalarConditionPartitionBy(this); return cb;
    }

    // ===================================================================================
    //                                                                       MyselfDerived
    //                                                                       =============
    public void xsmyselfDerive(String fn, SubQuery<VillagePlayerCB> sq, String al, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        VillagePlayerCB cb = new VillagePlayerCB(); cb.xsetupForDerivedReferrer(this);
        lockCall(() -> sq.query(cb)); String pp = keepSpecifyMyselfDerived(cb.query()); String pk = "VILLAGE_PLAYER_ID";
        registerSpecifyMyselfDerived(fn, cb.query(), pk, pk, pp, "myselfDerived", al, op);
    }
    public abstract String keepSpecifyMyselfDerived(VillagePlayerCQ sq);

    /**
     * Prepare for (Query)MyselfDerived (correlated sub-query).
     * @return The object to set up a function for myself table. (NotNull)
     */
    public HpQDRFunction<VillagePlayerCB> myselfDerived() {
        return xcreateQDRFunctionMyselfDerived(VillagePlayerCB.class);
    }
    @SuppressWarnings("unchecked")
    protected <CB extends ConditionBean> void xqderiveMyselfDerived(String fn, SubQuery<CB> sq, String rd, Object vl, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        VillagePlayerCB cb = new VillagePlayerCB(); cb.xsetupForDerivedReferrer(this); sq.query((CB)cb);
        String pk = "VILLAGE_PLAYER_ID";
        String sqpp = keepQueryMyselfDerived(cb.query()); // for saving query-value.
        String prpp = keepQueryMyselfDerivedParameter(vl);
        registerQueryMyselfDerived(fn, cb.query(), pk, pk, sqpp, "myselfDerived", rd, vl, prpp, op);
    }
    public abstract String keepQueryMyselfDerived(VillagePlayerCQ sq);
    public abstract String keepQueryMyselfDerivedParameter(Object vl);

    // ===================================================================================
    //                                                                        MyselfExists
    //                                                                        ============
    /**
     * Prepare for MyselfExists (correlated sub-query).
     * @param subCBLambda The implementation of sub-query. (NotNull)
     */
    public void myselfExists(SubQuery<VillagePlayerCB> subCBLambda) {
        assertObjectNotNull("subCBLambda", subCBLambda);
        VillagePlayerCB cb = new VillagePlayerCB(); cb.xsetupForMyselfExists(this);
        lockCall(() -> subCBLambda.query(cb)); String pp = keepMyselfExists(cb.query());
        registerMyselfExists(cb.query(), pp);
    }
    public abstract String keepMyselfExists(VillagePlayerCQ sq);

    // ===================================================================================
    //                                                                        Manual Order
    //                                                                        ============
    /**
     * Order along manual ordering information.
     * <pre>
     * cb.query().addOrderBy_Birthdate_Asc().<span style="color: #CC4747">withManualOrder</span>(<span style="color: #553000">op</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_GreaterEqual</span>(priorityDate); <span style="color: #3F7E5E">// e.g. 2000/01/01</span>
     * });
     * <span style="color: #3F7E5E">// order by </span>
     * <span style="color: #3F7E5E">//   case</span>
     * <span style="color: #3F7E5E">//     when BIRTHDATE &gt;= '2000/01/01' then 0</span>
     * <span style="color: #3F7E5E">//     else 1</span>
     * <span style="color: #3F7E5E">//   end asc, ...</span>
     *
     * cb.query().addOrderBy_MemberStatusCode_Asc().<span style="color: #CC4747">withManualOrder</span>(<span style="color: #553000">op</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_Equal</span>(CDef.MemberStatus.Withdrawal);
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_Equal</span>(CDef.MemberStatus.Formalized);
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_Equal</span>(CDef.MemberStatus.Provisional);
     * });
     * <span style="color: #3F7E5E">// order by </span>
     * <span style="color: #3F7E5E">//   case</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'WDL' then 0</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'FML' then 1</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'PRV' then 2</span>
     * <span style="color: #3F7E5E">//     else 3</span>
     * <span style="color: #3F7E5E">//   end asc, ...</span>
     * </pre>
     * <p>This function with Union is unsupported!</p>
     * <p>The order values are bound (treated as bind parameter).</p>
     * @param opLambda The callback for option of manual-order containing order values. (NotNull)
     */
    public void withManualOrder(ManualOrderOptionCall opLambda) { // is user public!
        xdoWithManualOrder(cMOO(opLambda));
    }

    // ===================================================================================
    //                                                                    Small Adjustment
    //                                                                    ================
    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    protected VillagePlayerCB newMyCB() {
        return new VillagePlayerCB();
    }
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xabUDT() { return Date.class.getName(); }
    protected String xabCQ() { return VillagePlayerCQ.class.getName(); }
    protected String xabLSO() { return LikeSearchOption.class.getName(); }
    protected String xabSLCS() { return HpSLCSetupper.class.getName(); }
    protected String xabSCP() { return SubQuery.class.getName(); }
}
