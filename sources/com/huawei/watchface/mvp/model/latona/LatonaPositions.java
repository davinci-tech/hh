package com.huawei.watchface.mvp.model.latona;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes9.dex */
public class LatonaPositions {

    @SerializedName("position")
    private List<LatonaPosition> mLatonaPositionList;

    @SerializedName("@selected_index")
    private String mSelectedIndex;

    public List<LatonaPosition> getLatonaPositionList() {
        return this.mLatonaPositionList;
    }

    public void setLatonaPositionList(List<LatonaPosition> list) {
        this.mLatonaPositionList = list;
    }

    public String getSelectedIndex() {
        return this.mSelectedIndex;
    }

    public void setSelectedIndex(String str) {
        this.mSelectedIndex = str;
    }

    public String toString() {
        return "LatonaPositions{mLatonaPositionList=" + this.mLatonaPositionList + ", mSelectedIndex='" + this.mSelectedIndex + "'}";
    }
}
