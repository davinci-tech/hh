package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;

/* loaded from: classes5.dex */
public class FeedBackRequest {

    @SerializedName("accessToken")
    private String accessToken;

    @SerializedName("orderType")
    private int orderType;

    @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
    private int pageSize;

    @SerializedName("problemId")
    private String problemId;

    @SerializedName("problemSourceCode")
    private String problemSourceCode;

    @SerializedName("startWith")
    private String startWith;

    public void setStartWith(String str) {
        this.startWith = str;
    }

    public void setProblemSourceCode(String str) {
        this.problemSourceCode = str;
    }

    public void setProblemId(String str) {
        this.problemId = str;
    }

    public void setPageSize(int i) {
        this.pageSize = i;
    }

    public void setOrderType(int i) {
        this.orderType = i;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public String getStartWith() {
        return this.startWith;
    }

    public String getProblemSourceCode() {
        return this.problemSourceCode;
    }

    public String getProblemId() {
        return this.problemId;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getOrderType() {
        return this.orderType;
    }

    public String getAccessToken() {
        return this.accessToken;
    }
}
