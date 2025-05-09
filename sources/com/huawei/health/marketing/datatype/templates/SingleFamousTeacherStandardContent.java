package com.huawei.health.marketing.datatype.templates;

import java.util.List;

/* loaded from: classes3.dex */
public class SingleFamousTeacherStandardContent {
    private String description;
    private boolean descriptionVisibility;
    private String headPicture;
    private String linkValue;
    private String picture;
    private List<SingleFamousTeacherContent> subContents;
    private String tabName;
    private int tabPriority;
    private String tahitiPicture;
    private String theme;
    private boolean themeVisibility;

    public int getTabPriority() {
        return this.tabPriority;
    }

    public String getTabName() {
        return this.tabName;
    }

    public String getHeadPicture() {
        return this.headPicture;
    }

    public String getTheme() {
        return this.theme;
    }

    public boolean isThemeVisibility() {
        return this.themeVisibility;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDescriptionVisibility() {
        return this.descriptionVisibility;
    }

    public String getPicture() {
        return this.picture;
    }

    public List<SingleFamousTeacherContent> getSubContents() {
        return this.subContents;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public String getTahitiPicture() {
        String str = this.tahitiPicture;
        return str == null ? "" : str;
    }

    public String toString() {
        return "SingleFamousTeacherStandardContent{tabPriority=" + this.tabPriority + ", tabName='" + this.tabName + "', headPicture='" + this.headPicture + "', theme='" + this.theme + "', themeVisibility=" + this.themeVisibility + ", description='" + this.description + "', descriptionVisibility=" + this.descriptionVisibility + ", picture='" + this.picture + "', tahitiPicture='" + this.tahitiPicture + "', linkValue='" + this.linkValue + "', subContents=" + this.subContents + '}';
    }
}
