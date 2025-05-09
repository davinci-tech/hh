package com.huawei.hihealth.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class PPGTypeRstSimpleData {

    @SerializedName("algRstFlag")
    private int algRstFlag;

    @SerializedName("mPPGTypeRstBeanList")
    private ArrayList<PPGTypeRstData> mPPGTypeRstDataList;

    public int getAlgRstFlag() {
        return this.algRstFlag;
    }

    public void setAlgRstFlag(int i) {
        this.algRstFlag = i;
    }

    public ArrayList<PPGTypeRstData> getPPGTypeRstDataList() {
        return this.mPPGTypeRstDataList;
    }

    public void setPPGTypeRstDataList(ArrayList<PPGTypeRstData> arrayList) {
        this.mPPGTypeRstDataList = arrayList;
    }
}
