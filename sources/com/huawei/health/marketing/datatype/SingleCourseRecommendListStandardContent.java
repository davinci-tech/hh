package com.huawei.health.marketing.datatype;

import com.huawei.health.marketing.datatype.ColumRecommendInfo;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public class SingleCourseRecommendListStandardContent extends ColumRecommendInfo {
    private String buttonDescription;
    private String buttonLink;
    private String darkListPicture;
    private String darkListPictureColor;
    private String darkPicture;
    private String darkPictureColor;
    private String listPicture;
    private String listPictureColor;
    private String picture;
    private String pictureColor;
    private List<RcmItem> recommendList;
    private String sortingRules;
    private List<SingleCourseRecommendListContent> subContents;
    private String subTheme;
    private boolean subThemeVisibility;
    private String tabId;
    private String tabLink;
    private String tabName;
    private int tabPriority;
    private String theme;
    private boolean themeVisibility;

    public SingleCourseRecommendListStandardContent(ColumRecommendInfo.Builder builder) {
        super(builder);
    }

    public int getTabPriority() {
        return this.tabPriority;
    }

    public void setTabPriority(int i) {
        this.tabPriority = i;
    }

    public String getLink() {
        return this.tabLink;
    }

    public void setLink(String str) {
        this.tabLink = str;
    }

    public String getTabName() {
        return this.tabName;
    }

    public void setTabName(String str) {
        this.tabName = str;
    }

    public String getTabId() {
        return this.tabId;
    }

    public void setTabId(String str) {
        this.tabId = str;
    }

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String str) {
        this.theme = str;
    }

    public boolean isThemeVisibility() {
        return this.themeVisibility;
    }

    public void setThemeVisibility(boolean z) {
        this.themeVisibility = z;
    }

    public String getSubTheme() {
        return this.subTheme;
    }

    public void setSubTheme(String str) {
        this.subTheme = str;
    }

    public boolean isSubThemeVisibility() {
        return this.subThemeVisibility;
    }

    public void setSubThemeVisibility(boolean z) {
        this.subThemeVisibility = z;
    }

    public String getDarkBackground() {
        return this.darkPicture;
    }

    public void setDarkBackground(String str) {
        this.darkPicture = str;
    }

    public String getDarkBackgroundColor() {
        return this.darkPictureColor;
    }

    public void setDarkBackgroundColor(String str) {
        this.darkPictureColor = str;
    }

    public String getDarkListBackground() {
        return this.darkListPicture;
    }

    public void setDarkListBackground(String str) {
        this.darkListPicture = str;
    }

    public String getDarkListBackgroundColor() {
        return this.darkListPictureColor;
    }

    public void setDarkListBackgroundColor(String str) {
        this.darkListPictureColor = str;
    }

    public String getBackground() {
        return this.picture;
    }

    public void setBackground(String str) {
        this.picture = str;
    }

    public String getBackgroundColor() {
        return this.pictureColor;
    }

    public void setBackgroundColor(String str) {
        this.pictureColor = str;
    }

    public String getListBackground() {
        return this.listPicture;
    }

    public void setListBackground(String str) {
        this.listPicture = str;
    }

    public String getListBackgroundColor() {
        return this.listPictureColor;
    }

    public void setListBackgroundColor(String str) {
        this.listPictureColor = str;
    }

    public String getButtonDescription() {
        return this.buttonDescription;
    }

    public void setButtonDescription(String str) {
        this.buttonDescription = str;
    }

    public String getButtonLink() {
        return this.buttonLink;
    }

    public void setButtonLink(String str) {
        this.buttonLink = str;
    }

    public List<SingleCourseRecommendListContent> getSubContents() {
        return this.subContents;
    }

    public void setSubContents(List<SingleCourseRecommendListContent> list) {
        this.subContents = list;
    }

    public List<RcmItem> getRecommendList() {
        return this.recommendList;
    }

    public String getSortingRules() {
        return this.sortingRules;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SingleCourseRecommendListStandardContent singleCourseRecommendListStandardContent = (SingleCourseRecommendListStandardContent) obj;
        return this.tabPriority == singleCourseRecommendListStandardContent.tabPriority && this.themeVisibility == singleCourseRecommendListStandardContent.themeVisibility && this.subThemeVisibility == singleCourseRecommendListStandardContent.subThemeVisibility && Objects.equals(this.tabName, singleCourseRecommendListStandardContent.tabName) && Objects.equals(this.tabId, singleCourseRecommendListStandardContent.tabId) && Objects.equals(this.theme, singleCourseRecommendListStandardContent.theme) && Objects.equals(this.subTheme, singleCourseRecommendListStandardContent.subTheme) && Objects.equals(this.picture, singleCourseRecommendListStandardContent.picture) && Objects.equals(this.pictureColor, singleCourseRecommendListStandardContent.pictureColor) && Objects.equals(this.listPicture, singleCourseRecommendListStandardContent.listPicture) && Objects.equals(this.listPictureColor, singleCourseRecommendListStandardContent.listPictureColor) && Objects.equals(this.tabLink, singleCourseRecommendListStandardContent.tabLink) && Objects.equals(this.buttonDescription, singleCourseRecommendListStandardContent.buttonDescription) && Objects.equals(this.buttonLink, singleCourseRecommendListStandardContent.buttonLink) && Objects.equals(this.subContents, singleCourseRecommendListStandardContent.subContents) && Objects.equals(this.recommendList, singleCourseRecommendListStandardContent.recommendList) && Objects.equals(this.sortingRules, singleCourseRecommendListStandardContent.sortingRules);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.tabPriority), this.tabName, this.tabId, this.theme, Boolean.valueOf(this.themeVisibility), this.subTheme, Boolean.valueOf(this.subThemeVisibility), this.picture, this.pictureColor, this.listPicture, this.listPictureColor, this.tabLink, this.buttonDescription, this.buttonLink, this.subContents, this.recommendList, this.sortingRules);
    }

    @Override // com.huawei.health.marketing.datatype.ColumRecommendInfo
    public String toString() {
        return "SingleCourseRecommendListStandardContent{tabPriority=" + this.tabPriority + ", tabName='" + this.tabName + "', tabId='" + this.tabId + "', theme='" + this.theme + "', themeVisibility=" + this.themeVisibility + ", subTheme='" + this.subTheme + "', subThemeVisibility=" + this.subThemeVisibility + ", picture='" + this.picture + "', pictureColor='" + this.pictureColor + "', listPicture='" + this.listPicture + "', listPictureColor='" + this.listPictureColor + "', tabLink='" + this.tabLink + "', buttonDescription='" + this.buttonDescription + "', buttonLink='" + this.buttonLink + "', subContents=" + this.subContents + ", recommendList=" + this.recommendList + ", sortingRules=" + this.sortingRules + '}';
    }
}
