package com.ort.dbflute.bsbhv.loader;

import java.util.List;

import org.dbflute.bhv.*;
import org.dbflute.bhv.referrer.*;
import com.ort.dbflute.exbhv.*;
import com.ort.dbflute.exentity.*;
import com.ort.dbflute.cbean.*;

/**
 * The referrer loader of VILLAGE as TABLE. <br>
 * <pre>
 * [primary key]
 *     VILLAGE_ID
 *
 * [column]
 *     VILLAGE_ID, VILLAGE_DISPLAY_NAME, CREATE_PLAYER_NAME, VILLAGE_STATUS_CODE, ROOM_SIZE_WIDTH, ROOM_SIZE_HEIGHT, EPILOGUE_DAY, WIN_CAMP_CODE, REGISTER_DATETIME, REGISTER_TRACE, UPDATE_DATETIME, UPDATE_TRACE
 *
 * [sequence]
 *     
 *
 * [identity]
 *     VILLAGE_ID
 *
 * [version-no]
 *     
 *
 * [foreign table]
 *     VILLAGE_STATUS, CAMP, VILLAGE_SETTINGS(AsOne)
 *
 * [referrer table]
 *     MESSAGE_RESTRICTION, VILLAGE_DAY, VILLAGE_PLAYER, VILLAGE_SETTINGS
 *
 * [foreign property]
 *     villageStatus, camp, villageSettingsAsOne
 *
 * [referrer property]
 *     messageRestrictionList, villageDayList, villagePlayerList
 * </pre>
 * @author DBFlute(AutoGenerator)
 */
public class LoaderOfVillage {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected List<Village> _selectedList;
    protected BehaviorSelector _selector;
    protected VillageBhv _myBhv; // lazy-loaded

    // ===================================================================================
    //                                                                   Ready for Loading
    //                                                                   =================
    public LoaderOfVillage ready(List<Village> selectedList, BehaviorSelector selector)
    { _selectedList = selectedList; _selector = selector; return this; }

    protected VillageBhv myBhv()
    { if (_myBhv != null) { return _myBhv; } else { _myBhv = _selector.select(VillageBhv.class); return _myBhv; } }

    // ===================================================================================
    //                                                                       Load Referrer
    //                                                                       =============
    protected List<MessageRestriction> _referrerMessageRestriction;

    /**
     * Load referrer of messageRestrictionList by the set-upper of referrer. <br>
     * MESSAGE_RESTRICTION by VILLAGE_ID, named 'messageRestrictionList'.
     * <pre>
     * <span style="color: #0000C0">villageBhv</span>.<span style="color: #994747">load</span>(<span style="color: #553000">villageList</span>, <span style="color: #553000">villageLoader</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">villageLoader</span>.<span style="color: #CC4747">loadMessageRestriction</span>(<span style="color: #553000">restrictionCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *         <span style="color: #553000">restrictionCB</span>.setupSelect...
     *         <span style="color: #553000">restrictionCB</span>.query().set...
     *         <span style="color: #553000">restrictionCB</span>.query().addOrderBy...
     *     }); <span style="color: #3F7E5E">// you can load nested referrer from here</span>
     *     <span style="color: #3F7E5E">//}).withNestedReferrer(<span style="color: #553000">restrictionLoader</span> -&gt; {</span>
     *     <span style="color: #3F7E5E">//    restrictionLoader.load...</span>
     *     <span style="color: #3F7E5E">//});</span>
     * });
     * for (Village village : <span style="color: #553000">villageList</span>) {
     *     ... = village.<span style="color: #CC4747">getMessageRestrictionList()</span>;
     * }
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has settings before callback as follows:
     * <pre>
     * cb.query().setVillageId_InScope(pkList);
     * cb.query().addOrderBy_VillageId_Asc();
     * </pre>
     * @param refCBLambda The callback to set up referrer condition-bean for loading referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerLoaderGateway<LoaderOfMessageRestriction> loadMessageRestriction(ReferrerConditionSetupper<MessageRestrictionCB> refCBLambda) {
        myBhv().loadMessageRestriction(_selectedList, refCBLambda).withNestedReferrer(refLs -> _referrerMessageRestriction = refLs);
        return hd -> hd.handle(new LoaderOfMessageRestriction().ready(_referrerMessageRestriction, _selector));
    }

    protected List<VillageDay> _referrerVillageDay;

    /**
     * Load referrer of villageDayList by the set-upper of referrer. <br>
     * VILLAGE_DAY by VILLAGE_ID, named 'villageDayList'.
     * <pre>
     * <span style="color: #0000C0">villageBhv</span>.<span style="color: #994747">load</span>(<span style="color: #553000">villageList</span>, <span style="color: #553000">villageLoader</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">villageLoader</span>.<span style="color: #CC4747">loadVillageDay</span>(<span style="color: #553000">dayCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *         <span style="color: #553000">dayCB</span>.setupSelect...
     *         <span style="color: #553000">dayCB</span>.query().set...
     *         <span style="color: #553000">dayCB</span>.query().addOrderBy...
     *     }); <span style="color: #3F7E5E">// you can load nested referrer from here</span>
     *     <span style="color: #3F7E5E">//}).withNestedReferrer(<span style="color: #553000">dayLoader</span> -&gt; {</span>
     *     <span style="color: #3F7E5E">//    dayLoader.load...</span>
     *     <span style="color: #3F7E5E">//});</span>
     * });
     * for (Village village : <span style="color: #553000">villageList</span>) {
     *     ... = village.<span style="color: #CC4747">getVillageDayList()</span>;
     * }
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has settings before callback as follows:
     * <pre>
     * cb.query().setVillageId_InScope(pkList);
     * cb.query().addOrderBy_VillageId_Asc();
     * </pre>
     * @param refCBLambda The callback to set up referrer condition-bean for loading referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerLoaderGateway<LoaderOfVillageDay> loadVillageDay(ReferrerConditionSetupper<VillageDayCB> refCBLambda) {
        myBhv().loadVillageDay(_selectedList, refCBLambda).withNestedReferrer(refLs -> _referrerVillageDay = refLs);
        return hd -> hd.handle(new LoaderOfVillageDay().ready(_referrerVillageDay, _selector));
    }

    protected List<VillagePlayer> _referrerVillagePlayer;

    /**
     * Load referrer of villagePlayerList by the set-upper of referrer. <br>
     * VILLAGE_PLAYER by VILLAGE_ID, named 'villagePlayerList'.
     * <pre>
     * <span style="color: #0000C0">villageBhv</span>.<span style="color: #994747">load</span>(<span style="color: #553000">villageList</span>, <span style="color: #553000">villageLoader</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">villageLoader</span>.<span style="color: #CC4747">loadVillagePlayer</span>(<span style="color: #553000">playerCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *         <span style="color: #553000">playerCB</span>.setupSelect...
     *         <span style="color: #553000">playerCB</span>.query().set...
     *         <span style="color: #553000">playerCB</span>.query().addOrderBy...
     *     }); <span style="color: #3F7E5E">// you can load nested referrer from here</span>
     *     <span style="color: #3F7E5E">//}).withNestedReferrer(<span style="color: #553000">playerLoader</span> -&gt; {</span>
     *     <span style="color: #3F7E5E">//    playerLoader.load...</span>
     *     <span style="color: #3F7E5E">//});</span>
     * });
     * for (Village village : <span style="color: #553000">villageList</span>) {
     *     ... = village.<span style="color: #CC4747">getVillagePlayerList()</span>;
     * }
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has settings before callback as follows:
     * <pre>
     * cb.query().setVillageId_InScope(pkList);
     * cb.query().addOrderBy_VillageId_Asc();
     * </pre>
     * @param refCBLambda The callback to set up referrer condition-bean for loading referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerLoaderGateway<LoaderOfVillagePlayer> loadVillagePlayer(ReferrerConditionSetupper<VillagePlayerCB> refCBLambda) {
        myBhv().loadVillagePlayer(_selectedList, refCBLambda).withNestedReferrer(refLs -> _referrerVillagePlayer = refLs);
        return hd -> hd.handle(new LoaderOfVillagePlayer().ready(_referrerVillagePlayer, _selector));
    }

    // ===================================================================================
    //                                                                    Pull out Foreign
    //                                                                    ================
    protected LoaderOfVillageStatus _foreignVillageStatusLoader;
    public LoaderOfVillageStatus pulloutVillageStatus() {
        if (_foreignVillageStatusLoader == null)
        { _foreignVillageStatusLoader = new LoaderOfVillageStatus().ready(myBhv().pulloutVillageStatus(_selectedList), _selector); }
        return _foreignVillageStatusLoader;
    }

    protected LoaderOfCamp _foreignCampLoader;
    public LoaderOfCamp pulloutCamp() {
        if (_foreignCampLoader == null)
        { _foreignCampLoader = new LoaderOfCamp().ready(myBhv().pulloutCamp(_selectedList), _selector); }
        return _foreignCampLoader;
    }

    protected LoaderOfVillageSettings _foreignVillageSettingsAsOneLoader;
    public LoaderOfVillageSettings pulloutVillageSettingsAsOne() {
        if (_foreignVillageSettingsAsOneLoader == null)
        { _foreignVillageSettingsAsOneLoader = new LoaderOfVillageSettings().ready(myBhv().pulloutVillageSettingsAsOne(_selectedList), _selector); }
        return _foreignVillageSettingsAsOneLoader;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public List<Village> getSelectedList() { return _selectedList; }
    public BehaviorSelector getSelector() { return _selector; }
}
