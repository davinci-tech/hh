package com.huawei.health.marketing.datatype.templates;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.marketing.datatype.SingleHotSeriesRecommendContent;
import java.util.List;

/* loaded from: classes3.dex */
public class HotSeriesRecommendTemplate extends BaseTemplate {

    @SerializedName("gridContents")
    protected List<SingleHotSeriesRecommendContent> mGridContents;

    @SerializedName("linkValue")
    protected String mLinkValue;

    @SerializedName("moreMenuTitle")
    private String mMoreMenuTitle;

    @SerializedName("name")
    protected String mName;

    @SerializedName("nameVisibility")
    protected boolean mNameVisibility;

    HotSeriesRecommendTemplate() {
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public boolean isNameVisibility() {
        return this.mNameVisibility;
    }

    public void setNameVisibility(boolean z) {
        this.mNameVisibility = z;
    }

    public String getLinkValue() {
        return this.mLinkValue;
    }

    public void setLinkValue(String str) {
        this.mLinkValue = str;
    }

    public List<SingleHotSeriesRecommendContent> getGridContents() {
        return this.mGridContents;
    }

    public void setGridContents(List<SingleHotSeriesRecommendContent> list) {
        this.mGridContents = list;
    }

    public String getMoreMenuTitle() {
        return this.mMoreMenuTitle;
    }

    public void setMoreMenuTitle(String str) {
        this.mMoreMenuTitle = str;
    }
}
