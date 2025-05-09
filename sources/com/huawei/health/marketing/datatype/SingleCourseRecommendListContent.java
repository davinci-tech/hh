package com.huawei.health.marketing.datatype;

import com.huawei.health.marketing.datatype.ColumRecommendInfo;
import java.util.Objects;

/* loaded from: classes3.dex */
public class SingleCourseRecommendListContent extends ColumRecommendInfo {
    private boolean auditionFlag;
    private String auditionLink;
    private String extend;
    private long itemEffectiveTime;
    private long itemExpirationTime;
    private String label;
    private String labelGroupId;
    private String linkValue;
    private String picture;
    private int priority;
    private String subTheme;
    private int tabIdx;
    private String tabName;
    private String theme;

    public SingleCourseRecommendListContent(ColumRecommendInfo.Builder builder) {
        super(builder);
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public String getTabName() {
        return this.tabName;
    }

    public void setTabName(String str) {
        this.tabName = str;
    }

    public int getTabIdx() {
        return this.tabIdx;
    }

    public void setTabIdx(int i) {
        this.tabIdx = i;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String str) {
        this.picture = str;
    }

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String str) {
        this.theme = str;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public String getSubTheme() {
        return this.subTheme;
    }

    public void setSubTheme(String str) {
        this.subTheme = str;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public String getAuditionLink() {
        return this.auditionLink;
    }

    public void setAuditionLink(String str) {
        this.auditionLink = str;
    }

    public boolean isAuditionFlag() {
        return this.auditionFlag;
    }

    public void setAuditionFlag(boolean z) {
        this.auditionFlag = z;
    }

    public String getLabelGroupId() {
        return this.labelGroupId;
    }

    public void setLabelGroupId(String str) {
        this.labelGroupId = str;
    }

    public long getItemEffectiveTime() {
        return this.itemEffectiveTime;
    }

    public void setItemEffectiveTime(long j) {
        this.itemEffectiveTime = j;
    }

    public long getItemExpirationTime() {
        return this.itemExpirationTime;
    }

    public void setItemExpirationTime(long j) {
        this.itemExpirationTime = j;
    }

    public String getExtend() {
        return this.extend;
    }

    public void setExtend(String str) {
        this.extend = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SingleCourseRecommendListContent singleCourseRecommendListContent = (SingleCourseRecommendListContent) obj;
        return this.priority == singleCourseRecommendListContent.priority && this.tabIdx == singleCourseRecommendListContent.tabIdx && this.auditionFlag == singleCourseRecommendListContent.auditionFlag && this.itemEffectiveTime == singleCourseRecommendListContent.itemEffectiveTime && this.itemExpirationTime == singleCourseRecommendListContent.itemExpirationTime && Objects.equals(getItemId(), singleCourseRecommendListContent.getItemId()) && Objects.equals(this.tabName, singleCourseRecommendListContent.tabName) && Objects.equals(this.picture, singleCourseRecommendListContent.picture) && Objects.equals(this.theme, singleCourseRecommendListContent.theme) && Objects.equals(this.label, singleCourseRecommendListContent.label) && Objects.equals(this.subTheme, singleCourseRecommendListContent.subTheme) && Objects.equals(this.linkValue, singleCourseRecommendListContent.linkValue) && Objects.equals(this.auditionLink, singleCourseRecommendListContent.auditionLink) && Objects.equals(this.labelGroupId, singleCourseRecommendListContent.labelGroupId) && Objects.equals(this.extend, singleCourseRecommendListContent.extend);
    }

    public int hashCode() {
        return Objects.hash(getItemId(), Integer.valueOf(this.priority), this.tabName, Integer.valueOf(this.tabIdx), this.picture, this.theme, this.label, this.subTheme, this.linkValue, this.auditionLink, Boolean.valueOf(this.auditionFlag), this.labelGroupId, Long.valueOf(this.itemEffectiveTime), Long.valueOf(this.itemExpirationTime), this.extend);
    }

    @Override // com.huawei.health.marketing.datatype.ColumRecommendInfo
    public String toString() {
        return "SingleCourseRecommendListContent{priority=" + this.priority + ", tabName='" + this.tabName + "', tabIdx=" + this.tabIdx + ", picture='" + this.picture + "', theme='" + this.theme + "', label='" + this.label + "', subTheme='" + this.subTheme + "', linkValue='" + this.linkValue + "', auditionLink='" + this.auditionLink + "', auditionFlag=" + this.auditionFlag + ", labelGroupId='" + this.labelGroupId + "', itemEffectiveTime=" + this.itemEffectiveTime + ", itemExpirationTime=" + this.itemExpirationTime + ", extend='" + this.extend + '\'' + super.toString() + '}';
    }
}
