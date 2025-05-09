package com.huawei.health.marketing.request;

import java.util.List;

/* loaded from: classes3.dex */
public class RespBreathExerciseInfo {
    private List<BreathExerciseInfo> breathTrainings;
    private String resultCode;
    private String resultDesc;
    private int totalCount;

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String str) {
        this.resultCode = str;
    }

    public String getResultDesc() {
        return this.resultDesc;
    }

    public void setResultDesc(String str) {
        this.resultDesc = str;
    }

    public List<BreathExerciseInfo> getBreathTrainings() {
        return this.breathTrainings;
    }

    public void setBreathTrainings(List<BreathExerciseInfo> list) {
        this.breathTrainings = list;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int i) {
        this.totalCount = i;
    }
}
