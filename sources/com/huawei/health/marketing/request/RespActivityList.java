package com.huawei.health.marketing.request;

import java.util.List;

/* loaded from: classes3.dex */
public class RespActivityList {
    private String currentTime;
    private List<ActivityIdInfo> pageActivityList;
    private int resultCode;

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public List<ActivityIdInfo> getPageActivityList() {
        return this.pageActivityList;
    }

    public void setPageActivityList(List<ActivityIdInfo> list) {
        this.pageActivityList = list;
    }

    public String getCurrentTime() {
        return this.currentTime;
    }

    public void setCurrentTime(String str) {
        this.currentTime = str;
    }
}
