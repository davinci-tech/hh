package com.huawei.health.marketing.datatype;

/* loaded from: classes3.dex */
public class CrossedCardOneCardContent {
    private String backgroundColor;
    private String backgroundPicture;
    private String darkBackgroundColor;
    private String darkBackgroundPicture;
    private long itemEffectiveTime;
    private long itemExpirationTime;
    private String linkValue;
    private String picture;
    private int priority;
    private String subTheme;
    private boolean subThemeVisibility;
    private String theme;
    private boolean themeVisibility;

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
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

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String str) {
        this.picture = str;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
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

    public String toString() {
        return "CrossedCardOneCardContent{priority=" + this.priority + ", theme='" + this.theme + "', themeVisibility=" + this.themeVisibility + ", subTheme='" + this.subTheme + "', subThemeVisibility=" + this.subThemeVisibility + ", backgroundPicture='" + this.backgroundPicture + "', backgroundColor='" + this.backgroundColor + "', darkBackgroundPicture='" + this.darkBackgroundPicture + "', darkBackgroundColor='" + this.darkBackgroundColor + "', picture='" + this.picture + "', linkValue='" + this.linkValue + "', itemEffectiveTime=" + this.itemEffectiveTime + ", itemExpirationTime=" + this.itemExpirationTime + '}';
    }
}
