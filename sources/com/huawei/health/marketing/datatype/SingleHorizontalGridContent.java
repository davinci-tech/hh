package com.huawei.health.marketing.datatype;

/* loaded from: classes3.dex */
public class SingleHorizontalGridContent {
    private boolean auditionFlag;
    private String dynamicDataId;
    private int index;
    private String link;
    private String picture;

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public String getDynamicDataId() {
        return this.dynamicDataId;
    }

    public void setDynamicDataId(String str) {
        this.dynamicDataId = str;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String str) {
        this.picture = str;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }

    public boolean getAuditionFlag() {
        return this.auditionFlag;
    }

    public void setAuditionFlag(boolean z) {
        this.auditionFlag = z;
    }

    public String toString() {
        return "SingleHorizontalGridContent{index=" + this.index + ", dynamicDataId='" + this.dynamicDataId + "', picture='" + this.picture + "', link='" + this.link + "', auditionFlag=" + this.auditionFlag + '}';
    }
}
