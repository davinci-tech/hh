package com.huawei.health.marketing.datatype.templates;

/* loaded from: classes3.dex */
public class TopBannerTemplate extends BaseTemplate {
    protected String corner;
    protected String cornerColor;
    protected boolean cornerVisibility;
    private String linkValue;
    private String picture;

    public String getCorner() {
        return this.corner;
    }

    public void setCorner(String str) {
        this.corner = str;
    }

    public String getCornerColor() {
        return this.cornerColor;
    }

    public void setCornerColor(String str) {
        this.cornerColor = str;
    }

    public boolean isCornerVisibility() {
        return this.cornerVisibility;
    }

    public void setCornerVisibility(boolean z) {
        this.cornerVisibility = z;
    }

    public void setPicture(String str) {
        this.picture = str;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public String getPicture() {
        return this.picture;
    }

    public String getLinkValue() {
        return this.linkValue;
    }
}
