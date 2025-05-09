package com.huawei.basefitnessadvice.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import health.compact.a.LogAnonymous;
import health.compact.a.LogUtil;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class WeekInfo implements Serializable, Cloneable {
    private static final long serialVersionUID = 1;

    @SerializedName("duration")
    private int mDuration;

    @SerializedName("order")
    private int mOrder;

    @SerializedName("sentence")
    private String mSentence;

    @SerializedName("stage")
    private int mStage;

    @SerializedName("weekName")
    private String mWeekName;

    public int acquireOrder() {
        return this.mOrder;
    }

    public void saveOrder(int i) {
        this.mOrder = i;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public String getWeekName() {
        return this.mWeekName;
    }

    public void saveWeekName(String str) {
        this.mWeekName = str;
    }

    public String getSentence() {
        return this.mSentence;
    }

    public void saveSentence(String str) {
        this.mSentence = str;
    }

    public int getStage() {
        return this.mStage;
    }

    public void setStage(int i) {
        this.mStage = i;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            LogUtil.e("WeekInfo clone failed", LogAnonymous.b((Throwable) e));
            throw new AssertionError();
        }
    }
}
