package com.huawei.health.marketing.datatype;

import java.util.List;

/* loaded from: classes3.dex */
public class SingleHorizontalGridStandardContent {
    private String backgroundColor;
    private String backgroundPicture;
    private String darkBackgroundColor;
    private String darkBackgroundPicture;
    private String extend;
    private long itemEffectiveTime;
    private long itemExpirationTime;
    private String linkValue;
    private int priority;
    private List<SingleHorizontalGridContent> subContents;
    private String subTheme;
    private boolean subThemeVisibility;
    private String theme;
    private boolean themeVisibility;
    private String title;

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String str) {
        this.theme = str;
    }

    public boolean getThemeVisibility() {
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

    public boolean getSubThemeVisibility() {
        return this.subThemeVisibility;
    }

    public void setSubThemeVisibility(boolean z) {
        this.subThemeVisibility = z;
    }

    public String getBackgroundPicture() {
        return this.backgroundPicture;
    }

    public void setBackgroundPicture(String str) {
        this.backgroundPicture = str;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(String str) {
        this.backgroundColor = str;
    }

    public String getDarkBackgroundPicture() {
        return this.darkBackgroundPicture;
    }

    public void setDarkBackgroundPicture(String str) {
        this.darkBackgroundPicture = str;
    }

    public String getDarkBackgroundColor() {
        return this.darkBackgroundColor;
    }

    public void setDarkBackgroundColor(String str) {
        this.darkBackgroundColor = str;
    }

    public List<SingleHorizontalGridContent> getSubContents() {
        return this.subContents;
    }

    public void setSubContents(List<SingleHorizontalGridContent> list) {
        this.subContents = list;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
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

    public String toString() {
        return "SingleHorizontalGridStandardContent{priority=" + this.priority + ", theme='" + this.theme + "', themeVisibility=" + this.themeVisibility + ", subTheme='" + this.subTheme + "', subThemeVisibility=" + this.subThemeVisibility + ", backgroundPicture='" + this.backgroundPicture + "', backgroundColor='" + this.backgroundColor + "', darkBackgroundPicture='" + this.darkBackgroundPicture + "', darkBackgroundColor='" + this.darkBackgroundColor + "', subContents=" + this.subContents + ", title='" + this.title + "', itemEffectiveTime=" + this.itemEffectiveTime + ", itemExpirationTime=" + this.itemExpirationTime + ", extend='" + this.extend + "'}";
    }
}
