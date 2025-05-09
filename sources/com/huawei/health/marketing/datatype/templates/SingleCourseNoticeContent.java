package com.huawei.health.marketing.datatype.templates;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.marketing.datatype.ColumRecommendInfo;

/* loaded from: classes3.dex */
public class SingleCourseNoticeContent extends CornerTemplate {

    @SerializedName("itemCategory")
    private String itemCategory;

    @SerializedName("itemEffectiveTime")
    private long itemEffectiveTime;

    @SerializedName("itemExpirationTime")
    private long itemExpirationTime;

    @SerializedName("linkValue")
    private String linkValue;

    @SerializedName("picture")
    private String picture;

    @SerializedName("priority")
    private int priority;

    @SerializedName("subTheme")
    private String subTheme;

    @SerializedName("tabIdx")
    private int tabIndex;

    @SerializedName("tabName")
    private String tabName;

    @SerializedName("theme")
    private String theme;

    @SerializedName("themeVisibility")
    private boolean themeVisibility;

    public SingleCourseNoticeContent(ColumRecommendInfo.Builder builder) {
        super(builder);
    }

    public int getPriority() {
        return this.priority;
    }

    public String getTabName() {
        return this.tabName;
    }

    public int getTabIndex() {
        return this.tabIndex;
    }

    public String getTheme() {
        return this.theme;
    }

    public boolean isThemeVisibility() {
        return this.themeVisibility;
    }

    public String getSubTheme() {
        return this.subTheme;
    }

    public String getPicture() {
        return this.picture;
    }

    public String getItemCategory() {
        return this.itemCategory;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public long getItemEffectiveTime() {
        return this.itemEffectiveTime;
    }

    public long getItemExpirationTime() {
        return this.itemExpirationTime;
    }
}
