package com.huawei.health.marketing.datatype.templates;

import java.util.List;

/* loaded from: classes3.dex */
public abstract class BaseSidingTemplate<T> extends BaseTemplate {
    private String linkValue;
    private String name;
    private boolean nameVisibility;
    private String subTitle;
    private String theme;
    private String topRightCornerText;

    public abstract List<T> getGridContents();

    public abstract void setGridContents(List<T> list);

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean isNameVisibility() {
        return this.nameVisibility;
    }

    public void setNameVisibility(boolean z) {
        this.nameVisibility = z;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String str) {
        this.theme = str;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public String getTopRightCornerText() {
        return this.topRightCornerText;
    }

    public void setTopRightCornerText(String str) {
        this.topRightCornerText = str;
    }
}
