package com.huawei.health.plan.model.intplan;

import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.intplan.StatInfo;

/* loaded from: classes3.dex */
public class StatInfoBean<T> {

    @SerializedName("type")
    private String mType;

    @SerializedName("value")
    private T mValue;

    public StatInfoBean(String str, T t) {
        this.mType = str;
        this.mValue = t;
    }

    public StatInfoBean(StatInfo statInfo) {
        this.mType = statInfo.getStatType();
        this.mValue = (T) statInfo.getValue();
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public T getValue() {
        return this.mValue;
    }

    public void setValue(T t) {
        this.mValue = t;
    }
}
