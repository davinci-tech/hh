package com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class SportTypeData {

    @SerializedName("sportTypeInfoList")
    private ArrayList<HwSportTypeInfo> mSportTypeInfos;

    @SerializedName("version")
    private String mVersion;

    public String getVersion() {
        return this.mVersion;
    }

    public void setVersion(String str) {
        this.mVersion = str;
    }

    public ArrayList<HwSportTypeInfo> getSportTypeInfos() {
        return (ArrayList) this.mSportTypeInfos.clone();
    }

    public void setSportTypeInfos(ArrayList<HwSportTypeInfo> arrayList) {
        this.mSportTypeInfos = arrayList;
    }
}
