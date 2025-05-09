package com.huawei.pluginachievement.manager.model;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class KakaCheckinRecord {

    @SerializedName(ParsedFieldTag.KAKA_CONSECUTIVE_DAYS)
    private int mConDays;

    @SerializedName("kaka")
    private int mKaka;

    @SerializedName(ParsedFieldTag.RECORD_DAY)
    private String mRecordDay;

    public String getRecordDay() {
        return this.mRecordDay;
    }

    public void setRecordDay(String str) {
        this.mRecordDay = str;
    }

    public int getConDays() {
        return this.mConDays;
    }

    public void setConDays(int i) {
        this.mConDays = i;
    }

    public int getKaka() {
        return this.mKaka;
    }

    public void setKaka(int i) {
        this.mKaka = i;
    }
}
