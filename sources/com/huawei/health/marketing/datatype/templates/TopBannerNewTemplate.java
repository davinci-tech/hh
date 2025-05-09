package com.huawei.health.marketing.datatype.templates;

/* loaded from: classes3.dex */
public class TopBannerNewTemplate {
    public static final int SOURCE_CUSTOM = 0;
    public static final int SOURCE_DAILY_TASK = 1;
    private int contentSource;
    private String extend;
    private String linkValue;
    private String picture;

    public int getContentSource() {
        return this.contentSource;
    }

    public void setContentSource(int i) {
        this.contentSource = i;
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
}
