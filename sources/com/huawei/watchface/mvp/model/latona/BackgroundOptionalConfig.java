package com.huawei.watchface.mvp.model.latona;

/* loaded from: classes7.dex */
public class BackgroundOptionalConfig {
    private String honeycombBgPath;
    private String mIndex;
    private String mResPreview;
    private int mSortTag;
    private String radiationBgPath;

    public String getIndex() {
        return this.mIndex;
    }

    public void setIndex(String str) {
        this.mIndex = str;
    }

    public String getResPreview() {
        return this.mResPreview;
    }

    public void setResPreview(String str) {
        this.mResPreview = str;
    }

    public String getHoneycombBgPath() {
        return this.honeycombBgPath;
    }

    public void setHoneycombBgPath(String str) {
        this.honeycombBgPath = str;
    }

    public String getRadiationBgPath() {
        return this.radiationBgPath;
    }

    public void setRadiationBgPath(String str) {
        this.radiationBgPath = str;
    }

    public int getSortTag() {
        return this.mSortTag;
    }

    public void setSortTag(int i) {
        this.mSortTag = i;
    }

    public String toString() {
        return "BackgroundOptionalConfig{mIndex='" + this.mIndex + "'mSortTag='" + this.mSortTag + "', mResPreview='" + this.mResPreview + "', honeycombBgPath='" + this.honeycombBgPath + "', radiationBgPath='" + this.radiationBgPath + "'}";
    }
}
