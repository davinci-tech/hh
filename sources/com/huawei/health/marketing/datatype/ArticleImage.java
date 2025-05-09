package com.huawei.health.marketing.datatype;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class ArticleImage {

    @SerializedName("combinNums")
    private int combinNums;

    @SerializedName("combinStatus")
    private int combinStatus;

    @SerializedName("combinStyle")
    private int combinStyle;

    @SerializedName("imgName")
    private String imgName;

    @SerializedName("imgUrl")
    private String imgUrl;

    public int getCombinNums() {
        return this.combinNums;
    }

    public void setCombinNums(int i) {
        this.combinNums = i;
    }

    public int getCombinStyle() {
        return this.combinStyle;
    }

    public void setCombinStyle(int i) {
        this.combinStyle = i;
    }

    public int getCombinStatus() {
        return this.combinStatus;
    }

    public void setCombinStatus(int i) {
        this.combinStatus = i;
    }

    public String getImgName() {
        return this.imgName;
    }

    public void setImgName(String str) {
        this.imgName = str;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public String toString() {
        return "ArticleImage{imgName='" + this.imgName + ", imgUrl=" + this.imgUrl + '}';
    }
}
