package com.huawei.health.marketing.datatype.templates;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class SingleFamousTeacherContent {

    @SerializedName("description")
    private String mDescription;

    @SerializedName("descriptionVisibility")
    private boolean mDescriptionVisibility;

    @SerializedName("dynamicDataId")
    private String mDynamicDataId;

    @SerializedName("linkValue")
    private String mLinkValue;

    @SerializedName("picture")
    private String mPicture;

    @SerializedName("priority")
    private int mPriority;

    @SerializedName("theme")
    private String mTheme;

    @SerializedName("themeVisibility")
    private boolean mThemeVisibility;

    public int getPriority() {
        return this.mPriority;
    }

    public String getTheme() {
        return this.mTheme;
    }

    public boolean isThemeVisibility() {
        return this.mThemeVisibility;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public boolean isDescriptionVisibility() {
        return this.mDescriptionVisibility;
    }

    public String getDynamicDataId() {
        return this.mDynamicDataId;
    }

    public String getPicture() {
        return this.mPicture;
    }

    public String getLinkValue() {
        return this.mLinkValue;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public void setDescriptionVisibility(boolean z) {
        this.mDescriptionVisibility = z;
    }

    public void setTheme(String str) {
        this.mTheme = str;
    }

    public void setThemeVisibility(boolean z) {
        this.mThemeVisibility = z;
    }

    public String toString() {
        return "SingleFamousTeacherContent{mPriority=" + this.mPriority + ", mTheme='" + this.mTheme + "', mThemeVisibility=" + this.mThemeVisibility + ", mDescription='" + this.mDescription + "', mDescriptionVisibility=" + this.mDescriptionVisibility + ", mDynamicDataId='" + this.mDynamicDataId + "', mPicture='" + this.mPicture + "', mLinkValue='" + this.mLinkValue + "'}";
    }
}
