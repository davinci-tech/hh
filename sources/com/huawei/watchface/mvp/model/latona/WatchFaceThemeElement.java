package com.huawei.watchface.mvp.model.latona;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes9.dex */
public class WatchFaceThemeElement {

    @SerializedName("@is_support_option")
    private String mIsSupportOption;

    @SerializedName("@label")
    private String mLabel;

    @SerializedName("@res_preview")
    private String mResPreview;

    @SerializedName("@selected_option")
    private String mSelectedOption;

    @SerializedName("layer")
    private List<WatchFaceThemeLayer> mWatchFaceThemeLayerList;

    public List<WatchFaceThemeLayer> getWatchFaceThemeLayerList() {
        return this.mWatchFaceThemeLayerList;
    }

    public void setWatchFaceThemeLayerList(List<WatchFaceThemeLayer> list) {
        this.mWatchFaceThemeLayerList = list;
    }

    public void setLabel(String str) {
        this.mLabel = str;
    }

    public void setIsSupportOption(String str) {
        this.mIsSupportOption = str;
    }

    public void setResPreview(String str) {
        this.mResPreview = str;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public String getIsSupportOption() {
        return this.mIsSupportOption;
    }

    public String getSelectedOption() {
        return this.mSelectedOption;
    }

    public void setSelectedOption(String str) {
        this.mSelectedOption = str;
    }

    public String getResPreview() {
        return this.mResPreview;
    }

    public String toString() {
        return " WatchFaceThemeElement{mWatchFaceThemeLayerList=" + this.mWatchFaceThemeLayerList + " , mLabel=" + this.mLabel + " , mIsSupportOption=" + this.mIsSupportOption + " , mSelectedOption=" + this.mSelectedOption + " } ";
    }
}
