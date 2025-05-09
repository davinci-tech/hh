package com.huawei.health.marketing.datatype;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;

/* loaded from: classes3.dex */
public class BaseColumnContent {

    @SerializedName("description")
    protected String mDescription;

    @SerializedName("descriptionVisibility")
    protected boolean mDescriptionVisibility;

    @SerializedName("extend")
    protected String mExtend;

    @SerializedName("itemEffectiveTime")
    protected long mItemEffectiveTime;

    @SerializedName("itemExpirationTime")
    protected long mItemExpirationTime;

    @SerializedName("labelGroupId")
    protected String mLabelGroupId;

    @SerializedName("priority")
    protected int mPriority;

    @SerializedName("theme")
    protected String mTheme;

    @SerializedName("themeVisibility")
    protected boolean mThemeVisibility;

    public int getPriority() {
        return this.mPriority;
    }

    public void setPriority(int i) {
        this.mPriority = i;
    }

    public String getTheme() {
        return this.mTheme;
    }

    public void setTheme(String str) {
        this.mTheme = str;
    }

    public boolean isThemeVisibility() {
        return this.mThemeVisibility;
    }

    public void setThemeVisibility(boolean z) {
        this.mThemeVisibility = z;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public boolean isDescriptionVisibility() {
        return this.mDescriptionVisibility;
    }

    public void setDescriptionVisibility(boolean z) {
        this.mDescriptionVisibility = z;
    }

    public String getLabelGroupId() {
        return this.mLabelGroupId;
    }

    public void setLabelGroupId(String str) {
        this.mLabelGroupId = str;
    }

    public long getItemEffectiveTime() {
        return this.mItemEffectiveTime;
    }

    public void setItemEffectiveTime(long j) {
        this.mItemEffectiveTime = j;
    }

    public long getItemExpirationTime() {
        return this.mItemExpirationTime;
    }

    public void setItemExpirationTime(long j) {
        this.mItemExpirationTime = j;
    }

    public String getExtend() {
        return this.mExtend;
    }

    public void setExtend(String str) {
        this.mExtend = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BaseColumnContent)) {
            return false;
        }
        BaseColumnContent baseColumnContent = (BaseColumnContent) obj;
        return getPriority() == baseColumnContent.getPriority() && isThemeVisibility() == baseColumnContent.isThemeVisibility() && isDescriptionVisibility() == baseColumnContent.isDescriptionVisibility() && getItemEffectiveTime() == baseColumnContent.getItemEffectiveTime() && getItemExpirationTime() == baseColumnContent.getItemExpirationTime() && Objects.equals(getTheme(), baseColumnContent.getTheme()) && Objects.equals(getDescription(), baseColumnContent.getDescription()) && Objects.equals(getLabelGroupId(), baseColumnContent.getLabelGroupId()) && Objects.equals(getExtend(), baseColumnContent.getExtend());
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(getPriority()), getTheme(), Boolean.valueOf(isThemeVisibility()), getDescription(), Boolean.valueOf(isDescriptionVisibility()), getLabelGroupId(), Long.valueOf(getItemEffectiveTime()), Long.valueOf(getItemExpirationTime()), getExtend());
    }

    public String toString() {
        return "BaseColumnContent{priority=" + this.mPriority + ", theme='" + this.mTheme + "', themeVisibility=" + this.mThemeVisibility + ", description='" + this.mDescription + "', descriptionVisibility=" + this.mDescriptionVisibility + ", labelGroupId='" + this.mLabelGroupId + "', itemEffectiveTime=" + this.mItemEffectiveTime + ", itemExpirationTime=" + this.mItemExpirationTime + ", extend='" + this.mExtend + "'}";
    }
}
