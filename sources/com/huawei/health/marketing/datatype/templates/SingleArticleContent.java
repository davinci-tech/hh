package com.huawei.health.marketing.datatype.templates;

/* loaded from: classes3.dex */
public class SingleArticleContent {
    private String description;
    private long itemEffectiveTime;
    private long itemExpirationTime;
    private String labelGroupId;
    private String linkValue;
    private boolean linkVisibility;
    private String picture;
    private int priority;
    private String theme;

    public int getPriority() {
        return this.priority;
    }

    public String getTheme() {
        return this.theme;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPicture() {
        return this.picture;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public boolean isLinkVisibility() {
        return this.linkVisibility;
    }

    public String getLabelGroupId() {
        return this.labelGroupId;
    }

    public long getItemEffectiveTime() {
        return this.itemEffectiveTime;
    }

    public long getItemExpirationTime() {
        return this.itemExpirationTime;
    }
}
