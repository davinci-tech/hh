package com.huawei.health.marketing.datatype.templates;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class MarketBaseTemplate extends BaseTemplate {

    @SerializedName("linkValue")
    private String linkValue;

    @SerializedName("moreMenuTitle")
    private String moreMenuTitle;

    @SerializedName("name")
    private String name;

    @SerializedName("nameVisibility")
    private boolean nameVisibility;

    public String getName() {
        return this.name;
    }

    public boolean isNameVisibility() {
        return this.nameVisibility;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public String getMoreMenuTitle() {
        return this.moreMenuTitle;
    }

    public String toString() {
        return " name='" + this.name + "', nameVisibility=" + this.nameVisibility + ", linkValue='" + this.linkValue + "', moreMenuTitle='" + this.moreMenuTitle + "', extend='" + this.extend + '\'';
    }
}
