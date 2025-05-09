package com.huawei.health.marketing.datatype.templates;

import java.util.List;

/* loaded from: classes3.dex */
public class MultiFunctionTemplate extends BaseTemplate {
    private String darkBackgroundPicture;
    private List<SingleFunctionGridContent> gridContents;
    private boolean intelligentFlag;
    private String linkValue;
    private String name;
    private boolean nameVisibility;
    private String picture;
    private int sleepAudioType;
    private String sortingRules;
    private String topRightCornerText;

    public boolean isIntelligentFlag() {
        return this.intelligentFlag;
    }

    public void setIntelligentFlag(boolean z) {
        this.intelligentFlag = z;
    }

    public String getSortingRules() {
        return this.sortingRules;
    }

    public void setSortingRules(String str) {
        this.sortingRules = str;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String str) {
        this.picture = str;
    }

    public String getDarkBackgroundPicture() {
        return this.darkBackgroundPicture;
    }

    public void setDarkBackgroundPicture(String str) {
        this.darkBackgroundPicture = str;
    }

    public String getTopRightCornerText() {
        return this.topRightCornerText;
    }

    public void setTopRightCornerText(String str) {
        this.topRightCornerText = str;
    }

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

    public int getSleepAudioType() {
        return this.sleepAudioType;
    }

    public void setSleepAudioType(int i) {
        this.sleepAudioType = i;
    }

    public List<SingleFunctionGridContent> getGridContents() {
        return this.gridContents;
    }

    public void setGridContents(List<SingleFunctionGridContent> list) {
        this.gridContents = list;
    }
}
