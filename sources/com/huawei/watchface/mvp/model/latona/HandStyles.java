package com.huawei.watchface.mvp.model.latona;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.maps.api.BuildConfig;
import java.util.List;

/* loaded from: classes9.dex */
public class HandStyles {

    @SerializedName(BuildConfig.FLAVOR)
    private List<HandStyle> mBackgroundList;

    @SerializedName("@selected_index")
    private String mSelectedIndex;

    public String getSelectedIndex() {
        return this.mSelectedIndex;
    }

    public void setSelectedIndex(String str) {
        this.mSelectedIndex = str;
    }

    public List<HandStyle> getBackgroundList() {
        return this.mBackgroundList;
    }

    public void setBackgroundList(List<HandStyle> list) {
        this.mBackgroundList = list;
    }

    public String toString() {
        return "LatonaStyles{mSelectedIndex='" + this.mSelectedIndex + "', mBackgroundList=" + this.mBackgroundList + '}';
    }
}
