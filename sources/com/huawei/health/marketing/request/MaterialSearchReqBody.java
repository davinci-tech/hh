package com.huawei.health.marketing.request;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes8.dex */
public class MaterialSearchReqBody {

    @SerializedName("deviceName")
    private String deviceName;

    @SerializedName("sceneType")
    private String sceneType;

    @SerializedName("searchWord")
    private String searchWord;

    @SerializedName("site")
    private String site;

    @SerializedName("size")
    private int size;

    @SerializedName("sort")
    private int sort;

    @SerializedName("start")
    private int start;

    public String getSearchWord() {
        return this.searchWord;
    }

    public void setSearchWord(String str) {
        this.searchWord = str;
    }

    public int getStart() {
        return this.start;
    }

    public void setStart(int i) {
        this.start = i;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int i) {
        this.size = i;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public MaterialSearchReqBody setDeviceName(String str) {
        this.deviceName = str;
        return this;
    }

    public String getSceneType() {
        return this.sceneType;
    }

    public MaterialSearchReqBody setSceneType(String str) {
        this.sceneType = str;
        return this;
    }

    public String getSite() {
        return this.site;
    }

    public MaterialSearchReqBody setSite(String str) {
        this.site = str;
        return this;
    }

    public int getSort() {
        return this.sort;
    }

    public MaterialSearchReqBody setSort(int i) {
        this.sort = i;
        return this;
    }
}
