package com.huawei.health.marketing.datatype.templates;

import com.huawei.health.marketing.datatype.ColumRecommendInfo;

/* loaded from: classes3.dex */
public class DailyRecommendTemplate extends CornerTemplate {
    private String description;
    private boolean descriptionVisibility;
    private String dynamicDataId;
    private String linkValue;
    private String picture;
    private String theme;
    private boolean themeVisibility;

    public DailyRecommendTemplate(ColumRecommendInfo.Builder builder) {
        super(builder);
    }

    public String getDescription() {
        return this.description;
    }

    public String getDynamicDataId() {
        return this.dynamicDataId;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public String getPicture() {
        return this.picture;
    }

    public String getTheme() {
        return this.theme;
    }

    public boolean getThemeVisibility() {
        return this.themeVisibility;
    }

    public boolean getDescriptionVisibility() {
        return this.descriptionVisibility;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDescriptionVisibility(boolean z) {
        this.descriptionVisibility = z;
    }

    public void setDynamicDataId(String str) {
        this.dynamicDataId = str;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public void setPicture(String str) {
        this.picture = str;
    }

    public void setTheme(String str) {
        this.theme = str;
    }

    public void setThemeVisibility(boolean z) {
        this.themeVisibility = z;
    }
}
