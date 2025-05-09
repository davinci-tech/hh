package com.huawei.health.marketing.datatype.templates;

/* loaded from: classes3.dex */
public class TabGeneralTemplate extends BaseTemplate {
    private String cornerIcon;
    private String linkValue;
    private String name;
    private boolean nameVisibility;
    private String picture;
    private int selectedTab;
    private int tabPosition;

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

    public String getPicture() {
        return this.picture;
    }

    public int getSelectedTab() {
        return this.selectedTab;
    }

    public void setSelectedTab(int i) {
        this.selectedTab = i;
    }

    public void setPicture(String str) {
        this.picture = str;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public int getTabPosition() {
        return this.tabPosition;
    }

    public void setTabPosition(int i) {
        this.tabPosition = i;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public String getCornerIcon() {
        return this.cornerIcon;
    }

    public void setCornerIcon(String str) {
        this.cornerIcon = str;
    }

    public String toString() {
        return "TabGeneralTemplate{name = " + this.name + "'nameVisibility = " + this.nameVisibility + "'picture = " + this.picture + "'linkValue = " + this.linkValue + "'selectedTab ='" + this.selectedTab + "tabPosition ='" + this.tabPosition + "}";
    }
}
