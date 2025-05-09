package com.huawei.watchface.mvp.model.latona;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes9.dex */
public class LatonaElement {

    @SerializedName("@is_support_option")
    private String mIsSupportOption;

    @SerializedName("@label")
    private String mLabel;

    @SerializedName("layer")
    private List<LatonaLayer> mLayers;

    @SerializedName("@res_preview")
    private String mResPreview;

    @SerializedName("@selected_option")
    private String mSelectedOption;

    public List<LatonaLayer> getLayers() {
        return this.mLayers;
    }

    public void setLayers(List<LatonaLayer> list) {
        this.mLayers = list;
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

    public LatonaLayer getLayer(String str) {
        List<LatonaLayer> list = this.mLayers;
        if (list == null || list.size() <= 0) {
            return null;
        }
        for (LatonaLayer latonaLayer : this.mLayers) {
            if (latonaLayer != null && TextUtils.equals(latonaLayer.getIndex().trim(), str.trim())) {
                return latonaLayer;
            }
        }
        return null;
    }

    public String toString() {
        return "LatonaElement{layers=" + this.mLayers + ", mLabel='" + this.mLabel + "', mIsSupportOption='" + this.mIsSupportOption + "', mSelectedOption='" + this.mSelectedOption + "', mResPreview='" + this.mResPreview + "'}";
    }
}
