package com.huawei.health.marketing.datatype;

import android.view.View;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class SingleTextContent {
    public static final String NONE_TYPE = "none";
    public static final String NORMAL_TYPE = "normal";
    public static final String SEARCH_TYPE = "search";

    @SerializedName("extend")
    private String extend;
    private boolean isSelected;

    @SerializedName("itemEffectiveTime")
    private long itemEffectiveTime;

    @SerializedName("itemExpirationTime")
    private long itemExpirationTime;

    @SerializedName("labelGroupId")
    private String labelGroupId;

    @SerializedName("linkType")
    private String linkType;

    @SerializedName("linkValue")
    private String linkValue;

    @SerializedName("marketingIcon")
    private String marketingIcon;
    private View.OnClickListener onClickListener;

    @SerializedName("positionId")
    private int positionId;

    @SerializedName("priority")
    private int priority;

    @SerializedName("theme")
    private String theme;

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

    public String getLinkType() {
        return this.linkType;
    }

    public void setLinkType(String str) {
        this.linkType = str;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public String getMarketingIcon() {
        return this.marketingIcon;
    }

    public void setMarketingIcon(String str) {
        this.marketingIcon = str;
    }

    public String getLabelGroupId() {
        return this.labelGroupId;
    }

    public void setLabelGroupId(String str) {
        this.labelGroupId = str;
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

    public int getPositionId() {
        return this.positionId;
    }

    public void setPositionId(int i) {
        this.positionId = i;
    }

    public View.OnClickListener getOnClickListener() {
        return this.onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }
}
