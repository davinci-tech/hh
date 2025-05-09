package com.huawei.health.plan.model.model;

import com.google.gson.Gson;

/* loaded from: classes3.dex */
public class DataSync {
    private long recordId;
    private int status;
    private int type;
    private String userId;
    private String value;

    public long getRecordId() {
        return this.recordId;
    }

    public void setRecordId(long j) {
        this.recordId = j;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
