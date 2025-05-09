package com.huawei.health.marketing.datatype.templates;

/* loaded from: classes3.dex */
public class TextGeneralTemplate extends BaseTemplate {
    private String linkValue;
    private String picture;
    private String theme;
    private Boolean themeVisibility;

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String str) {
        this.theme = str;
    }

    public Boolean isThemeVisibility() {
        return this.themeVisibility;
    }

    public void setThemeVisibility(Boolean bool) {
        this.themeVisibility = bool;
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

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("TextGeneralTemplate{theme='");
        stringBuffer.append(this.theme).append("', themeVisibility=");
        stringBuffer.append(this.themeVisibility);
        stringBuffer.append(", picture='").append(this.picture).append("', linkValue='");
        stringBuffer.append(this.linkValue).append("', extend='");
        stringBuffer.append(this.extend).append("'}");
        return stringBuffer.toString();
    }
}
