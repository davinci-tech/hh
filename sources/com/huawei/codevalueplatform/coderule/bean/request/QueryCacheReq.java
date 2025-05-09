package com.huawei.codevalueplatform.coderule.bean.request;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;

/* loaded from: classes3.dex */
public class QueryCacheReq extends Request {

    @SerializedName("startIdx")
    private int startIdx = 0;

    @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
    private int pageSize = 250;

    public int getStartIdx() {
        return this.startIdx;
    }

    public void setStartIdx(int i) {
        this.startIdx = i;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int i) {
        this.pageSize = i;
    }
}
