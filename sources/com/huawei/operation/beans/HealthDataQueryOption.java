package com.huawei.operation.beans;

import java.util.HashMap;

/* loaded from: classes9.dex */
public class HealthDataQueryOption {
    private int mLimit;
    private int mQueryKind;
    private HashMap<Long, Integer> mStartTimes;

    public int getQueryKind() {
        return this.mQueryKind;
    }

    public void setQueryKind(int i) {
        this.mQueryKind = i;
    }

    public int getLimit() {
        return this.mLimit;
    }

    public void setLimit(int i) {
        this.mLimit = i;
    }

    public HashMap<Long, Integer> getStartTimes() {
        return this.mStartTimes;
    }

    public void setStartTimes(HashMap<Long, Integer> hashMap) {
        this.mStartTimes = hashMap;
    }
}
