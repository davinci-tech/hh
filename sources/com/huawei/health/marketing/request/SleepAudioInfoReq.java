package com.huawei.health.marketing.request;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class SleepAudioInfoReq implements IRequest {

    @SerializedName("ids")
    private String ids;

    @SerializedName("pageNo")
    private String pageNo;

    @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
    private String pageSize;

    public String getIds() {
        return this.ids;
    }

    public void setIds(String str) {
        this.ids = str;
    }

    public String getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(String str) {
        this.pageNo = str;
    }

    public String getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(String str) {
        this.pageSize = str;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("messageCenterUrl") + "/messageCenter/user/getSleepAudioList";
    }
}
