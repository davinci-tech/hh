package com.huawei.health.marketing.datatype.templates;

/* loaded from: classes7.dex */
public class InternalLetterTemplate {
    private String extend;
    private String linkValue;
    private String picture;
    private String senderName;
    private String summary;
    private String theme;

    public String getSenderName() {
        return this.senderName;
    }

    public void setSenderName(String str) {
        this.senderName = str;
    }

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String str) {
        this.theme = str;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String str) {
        this.summary = str;
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

    public String getExtend() {
        return this.extend;
    }

    public void setExtend(String str) {
        this.extend = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("InternalLetterTemplate{senderName='");
        stringBuffer.append(this.senderName).append("', theme='");
        stringBuffer.append(this.theme).append("', summary='");
        stringBuffer.append(this.summary).append("', picture='");
        stringBuffer.append(this.picture).append("', linkValue='");
        stringBuffer.append(this.linkValue).append("', extend='");
        stringBuffer.append(this.extend).append("'}");
        return stringBuffer.toString();
    }
}
