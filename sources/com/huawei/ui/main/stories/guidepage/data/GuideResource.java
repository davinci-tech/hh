package com.huawei.ui.main.stories.guidepage.data;

/* loaded from: classes6.dex */
public class GuideResource {
    private String buttonName;
    private int display;
    private int resourceType;
    private String resourceUrl;
    private int skip;
    private String targetUrl;
    private int weight;

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int i) {
        this.weight = i;
    }

    public void setResourceUrl(String str) {
        this.resourceUrl = str;
    }

    public String getResourceUrl() {
        return this.resourceUrl;
    }

    public String getTargetUrl() {
        return this.targetUrl;
    }

    public void setTargetUrl(String str) {
        this.targetUrl = str;
    }

    public void setResourceType(int i) {
        this.resourceType = i;
    }

    public int getResourceType() {
        return this.resourceType;
    }

    public String getButtonName() {
        return this.buttonName;
    }

    public void setButtonName(String str) {
        this.buttonName = str;
    }

    public int getDisplay() {
        return this.display;
    }

    public void setDisplay(int i) {
        this.display = i;
    }

    public int getSkip() {
        return this.skip;
    }

    public void setSkip(int i) {
        this.skip = i;
    }

    public String toString() {
        return " resourceType:" + this.resourceType + " weight:" + this.weight + " skip:" + this.skip + " display:" + this.display + " buttonName:" + this.buttonName;
    }
}
