package com.huawei.pluginachievement.manager.db;

import defpackage.mcz;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public interface IAchieveDBMgr {
    public static final String PARAM_HUID = "huid";
    public static final String PARAM_PAGE = "page";
    public static final String PARAM_PAGE_SIZE = "pageSize";
    public static final String PARAM_REPORT_NO = "reportNo";

    int delete(mcz mczVar);

    long insert(mcz mczVar);

    mcz query(Map<String, String> map);

    List<mcz> queryAll(Map<String, String> map);

    int update(mcz mczVar);
}
