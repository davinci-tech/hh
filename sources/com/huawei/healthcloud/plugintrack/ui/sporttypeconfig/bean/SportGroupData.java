package com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class SportGroupData {

    @SerializedName("sportTypeGroupList")
    private ArrayList<HwSportTypeGroup> mSportTypeGroups;

    @SerializedName("version")
    private String mVersion;

    public String getVersion() {
        return this.mVersion;
    }

    public void setVersion(String str) {
        this.mVersion = str;
    }

    public ArrayList<HwSportTypeGroup> getSportTypeGroups() {
        return (ArrayList) this.mSportTypeGroups.clone();
    }

    public void setSportTypeGroups(ArrayList<HwSportTypeGroup> arrayList) {
        this.mSportTypeGroups = arrayList;
    }
}
