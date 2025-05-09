package com.huawei.watchface.mvp.model.latona;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import java.util.List;

/* loaded from: classes9.dex */
public class LatonaStyles {

    @SerializedName("@selected_index")
    private String mSelectedIndex;

    @SerializedName(TemplateStyleRecord.STYLE)
    private List<LatonaStyle> mStyleList;

    public String getSelectedIndex() {
        return this.mSelectedIndex;
    }

    public void setSelectedIndex(String str) {
        this.mSelectedIndex = str;
    }

    public List<LatonaStyle> getStyles() {
        return this.mStyleList;
    }

    public void setStyles(List<LatonaStyle> list) {
        this.mStyleList = list;
    }

    public String toString() {
        return "LatonaStyles{mSelectedIndex='" + this.mSelectedIndex + "', mStyleList=" + this.mStyleList + '}';
    }
}
