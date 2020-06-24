package com.ort.dbflute.cbean.cq;

import com.ort.dbflute.allcommon.CDef;
import com.ort.dbflute.cbean.cq.bs.BsVillageCQ;
import org.dbflute.cbean.ConditionQuery;
import org.dbflute.cbean.sqlclause.SqlClause;

import java.util.Arrays;

/**
 * The condition-query of VILLAGE.
 * <p>
 * You can implement your original methods here.
 * This class remains when re-generating.
 * </p>
 * @author DBFlute(AutoGenerator)
 */
public class VillageCQ extends BsVillageCQ {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    // You should NOT touch with this constructor.

    /**
     * Auto-generated constructor to create query instance, basically called in DBFlute.
     * @param referrerQuery The instance of referrer query. (NullAllowed: if null, this is base query)
     * @param sqlClause The instance of SQL clause. (NotNull)
     * @param aliasName The alias name for this query. (NotNull)
     * @param nestLevel The nest level of this query. (if zero, this is base query)
     */
    public VillageCQ(ConditionQuery referrerQuery, SqlClause sqlClause, String aliasName, int nestLevel) {
        super(referrerQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                       Arrange Query
    //                                                                       =============
    public void arrangeNotSolvedStatus() {
        setVillageStatusCode_InScope_AsVillageStatus(
                Arrays.asList(CDef.VillageStatus.募集中, CDef.VillageStatus.開始待ち, CDef.VillageStatus.進行中));
    }
}
