package com.huawei.basefitnessadvice.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import health.compact.a.LogAnonymous;
import health.compact.a.LogUtil;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class DayInfo implements Serializable, Cloneable {
    private static final long serialVersionUID = 1;

    @SerializedName("date")
    private String mDate;

    @SerializedName("dayDesc")
    private String mDayDesc;

    @SerializedName("dayTitle")
    private String mDayTitle;

    @SerializedName("order")
    private int mOrder;

    @SerializedName("singlesCount")
    private int mSinglesCount;

    public String acquireDate() {
        return this.mDate;
    }

    public void saveDate(String str) {
        this.mDate = str;
    }

    public int getOrder() {
        return this.mOrder;
    }

    public void saveOrder(int i) {
        this.mOrder = i;
    }

    public int getSinglesCount() {
        return this.mSinglesCount;
    }

    public void saveSinglesCount(int i) {
        this.mSinglesCount = i;
    }

    public DayInfo copy() {
        saveSinglesCount(this.mSinglesCount);
        saveOrder(this.mOrder);
        saveDayTitle(this.mDayTitle);
        saveDayDesc(this.mDayDesc);
        return new DayInfo();
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            LogUtil.e("DayInfo clone failed", LogAnonymous.b((Throwable) e));
            throw new AssertionError();
        }
    }

    public String acquireDayTitle() {
        return this.mDayTitle;
    }

    public void saveDayTitle(String str) {
        this.mDayTitle = str;
    }

    public String acquireDayDesc() {
        return this.mDayDesc;
    }

    public void saveDayDesc(String str) {
        this.mDayDesc = str;
    }
}
