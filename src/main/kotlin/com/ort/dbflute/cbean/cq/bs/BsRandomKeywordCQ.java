package com.ort.dbflute.cbean.cq.bs;

import java.util.Map;

import org.dbflute.cbean.*;
import org.dbflute.cbean.chelper.*;
import org.dbflute.cbean.coption.*;
import org.dbflute.cbean.cvalue.ConditionValue;
import org.dbflute.cbean.sqlclause.SqlClause;
import org.dbflute.exception.IllegalConditionBeanOperationException;
import com.ort.dbflute.cbean.cq.ciq.*;
import com.ort.dbflute.cbean.*;
import com.ort.dbflute.cbean.cq.*;

/**
 * The base condition-query of random_keyword.
 * @author DBFlute(AutoGenerator)
 */
public class BsRandomKeywordCQ extends AbstractBsRandomKeywordCQ {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected RandomKeywordCIQ _inlineQuery;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BsRandomKeywordCQ(ConditionQuery referrerQuery, SqlClause sqlClause, String aliasName, int nestLevel) {
        super(referrerQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                 InlineView/OrClause
    //                                                                 ===================
    /**
     * Prepare InlineView query. <br>
     * {select ... from ... left outer join (select * from random_keyword) where FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #CC4747">inline()</span>.setFoo...;
     * </pre>
     * @return The condition-query for InlineView query. (NotNull)
     */
    public RandomKeywordCIQ inline() {
        if (_inlineQuery == null) { _inlineQuery = xcreateCIQ(); }
        _inlineQuery.xsetOnClause(false); return _inlineQuery;
    }

    protected RandomKeywordCIQ xcreateCIQ() {
        RandomKeywordCIQ ciq = xnewCIQ();
        ciq.xsetBaseCB(_baseCB);
        return ciq;
    }

    protected RandomKeywordCIQ xnewCIQ() {
        return new RandomKeywordCIQ(xgetReferrerQuery(), xgetSqlClause(), xgetAliasName(), xgetNestLevel(), this);
    }

    /**
     * Prepare OnClause query. <br>
     * {select ... from ... left outer join random_keyword on ... and FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #CC4747">on()</span>.setFoo...;
     * </pre>
     * @return The condition-query for OnClause query. (NotNull)
     * @throws IllegalConditionBeanOperationException When this condition-query is base query.
     */
    public RandomKeywordCIQ on() {
        if (isBaseQuery()) { throw new IllegalConditionBeanOperationException("OnClause for local table is unavailable!"); }
        RandomKeywordCIQ inlineQuery = inline(); inlineQuery.xsetOnClause(true); return inlineQuery;
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====
    protected ConditionValue _randomKeywordId;
    public ConditionValue xdfgetRandomKeywordId()
    { if (_randomKeywordId == null) { _randomKeywordId = nCV(); }
      return _randomKeywordId; }
    protected ConditionValue xgetCValueRandomKeywordId() { return xdfgetRandomKeywordId(); }

    public Map<String, RandomContentCQ> xdfgetRandomKeywordId_ExistsReferrer_RandomContentList() { return xgetSQueMap("randomKeywordId_ExistsReferrer_RandomContentList"); }
    public String keepRandomKeywordId_ExistsReferrer_RandomContentList(RandomContentCQ sq) { return xkeepSQue("randomKeywordId_ExistsReferrer_RandomContentList", sq); }

    public Map<String, RandomContentCQ> xdfgetRandomKeywordId_NotExistsReferrer_RandomContentList() { return xgetSQueMap("randomKeywordId_NotExistsReferrer_RandomContentList"); }
    public String keepRandomKeywordId_NotExistsReferrer_RandomContentList(RandomContentCQ sq) { return xkeepSQue("randomKeywordId_NotExistsReferrer_RandomContentList", sq); }

    public Map<String, RandomContentCQ> xdfgetRandomKeywordId_SpecifyDerivedReferrer_RandomContentList() { return xgetSQueMap("randomKeywordId_SpecifyDerivedReferrer_RandomContentList"); }
    public String keepRandomKeywordId_SpecifyDerivedReferrer_RandomContentList(RandomContentCQ sq) { return xkeepSQue("randomKeywordId_SpecifyDerivedReferrer_RandomContentList", sq); }

    public Map<String, RandomContentCQ> xdfgetRandomKeywordId_QueryDerivedReferrer_RandomContentList() { return xgetSQueMap("randomKeywordId_QueryDerivedReferrer_RandomContentList"); }
    public String keepRandomKeywordId_QueryDerivedReferrer_RandomContentList(RandomContentCQ sq) { return xkeepSQue("randomKeywordId_QueryDerivedReferrer_RandomContentList", sq); }
    public Map<String, Object> xdfgetRandomKeywordId_QueryDerivedReferrer_RandomContentListParameter() { return xgetSQuePmMap("randomKeywordId_QueryDerivedReferrer_RandomContentList"); }
    public String keepRandomKeywordId_QueryDerivedReferrer_RandomContentListParameter(Object pm) { return xkeepSQuePm("randomKeywordId_QueryDerivedReferrer_RandomContentList", pm); }

    /**
     * Add order-by as ascend. <br>
     * RANDOM_KEYWORD_ID: {PK, ID, NotNull, INT UNSIGNED(10)}
     * @return this. (NotNull)
     */
    public BsRandomKeywordCQ addOrderBy_RandomKeywordId_Asc() { regOBA("RANDOM_KEYWORD_ID"); return this; }

    /**
     * Add order-by as descend. <br>
     * RANDOM_KEYWORD_ID: {PK, ID, NotNull, INT UNSIGNED(10)}
     * @return this. (NotNull)
     */
    public BsRandomKeywordCQ addOrderBy_RandomKeywordId_Desc() { regOBD("RANDOM_KEYWORD_ID"); return this; }

    protected ConditionValue _keyword;
    public ConditionValue xdfgetKeyword()
    { if (_keyword == null) { _keyword = nCV(); }
      return _keyword; }
    protected ConditionValue xgetCValueKeyword() { return xdfgetKeyword(); }

    /**
     * Add order-by as ascend. <br>
     * KEYWORD: {UQ, NotNull, VARCHAR(10)}
     * @return this. (NotNull)
     */
    public BsRandomKeywordCQ addOrderBy_Keyword_Asc() { regOBA("KEYWORD"); return this; }

    /**
     * Add order-by as descend. <br>
     * KEYWORD: {UQ, NotNull, VARCHAR(10)}
     * @return this. (NotNull)
     */
    public BsRandomKeywordCQ addOrderBy_Keyword_Desc() { regOBD("KEYWORD"); return this; }

    // ===================================================================================
    //                                                             SpecifiedDerivedOrderBy
    //                                                             =======================
    /**
     * Add order-by for specified derived column as ascend.
     * <pre>
     * cb.specify().derivedPurchaseList().max(new SubQuery&lt;PurchaseCB&gt;() {
     *     public void query(PurchaseCB subCB) {
     *         subCB.specify().columnPurchaseDatetime();
     *     }
     * }, <span style="color: #CC4747">aliasName</span>);
     * <span style="color: #3F7E5E">// order by [alias-name] asc</span>
     * cb.<span style="color: #CC4747">addSpecifiedDerivedOrderBy_Asc</span>(<span style="color: #CC4747">aliasName</span>);
     * </pre>
     * @param aliasName The alias name specified at (Specify)DerivedReferrer. (NotNull)
     * @return this. (NotNull)
     */
    public BsRandomKeywordCQ addSpecifiedDerivedOrderBy_Asc(String aliasName) { registerSpecifiedDerivedOrderBy_Asc(aliasName); return this; }

    /**
     * Add order-by for specified derived column as descend.
     * <pre>
     * cb.specify().derivedPurchaseList().max(new SubQuery&lt;PurchaseCB&gt;() {
     *     public void query(PurchaseCB subCB) {
     *         subCB.specify().columnPurchaseDatetime();
     *     }
     * }, <span style="color: #CC4747">aliasName</span>);
     * <span style="color: #3F7E5E">// order by [alias-name] desc</span>
     * cb.<span style="color: #CC4747">addSpecifiedDerivedOrderBy_Desc</span>(<span style="color: #CC4747">aliasName</span>);
     * </pre>
     * @param aliasName The alias name specified at (Specify)DerivedReferrer. (NotNull)
     * @return this. (NotNull)
     */
    public BsRandomKeywordCQ addSpecifiedDerivedOrderBy_Desc(String aliasName) { registerSpecifiedDerivedOrderBy_Desc(aliasName); return this; }

    // ===================================================================================
    //                                                                         Union Query
    //                                                                         ===========
    public void reflectRelationOnUnionQuery(ConditionQuery bqs, ConditionQuery uqs) {
    }

    // ===================================================================================
    //                                                                       Foreign Query
    //                                                                       =============
    protected Map<String, Object> xfindFixedConditionDynamicParameterMap(String property) {
        return null;
    }

    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    public Map<String, RandomKeywordCQ> xdfgetScalarCondition() { return xgetSQueMap("scalarCondition"); }
    public String keepScalarCondition(RandomKeywordCQ sq) { return xkeepSQue("scalarCondition", sq); }

    // ===================================================================================
    //                                                                       MyselfDerived
    //                                                                       =============
    public Map<String, RandomKeywordCQ> xdfgetSpecifyMyselfDerived() { return xgetSQueMap("specifyMyselfDerived"); }
    public String keepSpecifyMyselfDerived(RandomKeywordCQ sq) { return xkeepSQue("specifyMyselfDerived", sq); }

    public Map<String, RandomKeywordCQ> xdfgetQueryMyselfDerived() { return xgetSQueMap("queryMyselfDerived"); }
    public String keepQueryMyselfDerived(RandomKeywordCQ sq) { return xkeepSQue("queryMyselfDerived", sq); }
    public Map<String, Object> xdfgetQueryMyselfDerivedParameter() { return xgetSQuePmMap("queryMyselfDerived"); }
    public String keepQueryMyselfDerivedParameter(Object pm) { return xkeepSQuePm("queryMyselfDerived", pm); }

    // ===================================================================================
    //                                                                        MyselfExists
    //                                                                        ============
    protected Map<String, RandomKeywordCQ> _myselfExistsMap;
    public Map<String, RandomKeywordCQ> xdfgetMyselfExists() { return xgetSQueMap("myselfExists"); }
    public String keepMyselfExists(RandomKeywordCQ sq) { return xkeepSQue("myselfExists", sq); }

    // ===================================================================================
    //                                                                       MyselfInScope
    //                                                                       =============
    public Map<String, RandomKeywordCQ> xdfgetMyselfInScope() { return xgetSQueMap("myselfInScope"); }
    public String keepMyselfInScope(RandomKeywordCQ sq) { return xkeepSQue("myselfInScope", sq); }

    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xCB() { return RandomKeywordCB.class.getName(); }
    protected String xCQ() { return RandomKeywordCQ.class.getName(); }
    protected String xCHp() { return HpQDRFunction.class.getName(); }
    protected String xCOp() { return ConditionOption.class.getName(); }
    protected String xMap() { return Map.class.getName(); }
}
