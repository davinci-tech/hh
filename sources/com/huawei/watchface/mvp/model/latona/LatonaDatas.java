package com.huawei.watchface.mvp.model.latona;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes9.dex */
public class LatonaDatas {

    @SerializedName("data")
    private List<LatonaData> mLatonaDataList;

    @SerializedName("@selected_index")
    private String mSelectedIndex;

    public List<LatonaData> getLatonaDataList() {
        return this.mLatonaDataList;
    }

    public void setLatonaDataList(List<LatonaData> list) {
        this.mLatonaDataList = list;
    }

    public String getSelectedIndex() {
        return this.mSelectedIndex;
    }

    public void setSelectedIndex(String str) {
        this.mSelectedIndex = str;
    }

    public String toString() {
        return "LatonaDatas{mLatonaDataList=" + this.mLatonaDataList + ", mSelectedIndex='" + this.mSelectedIndex + "'}";
    }
}
