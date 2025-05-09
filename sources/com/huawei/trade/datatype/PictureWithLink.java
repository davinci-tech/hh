package com.huawei.trade.datatype;

/* loaded from: classes9.dex */
public class PictureWithLink {
    private String largePictureUrl;
    private int linkType;
    private String linkValue;
    private int order;
    private String pictureUrl;

    public String getPictureUrl() {
        return this.pictureUrl;
    }

    public void setPictureUrl(String str) {
        this.pictureUrl = str;
    }

    public String getLargePictureUrl() {
        return this.largePictureUrl;
    }

    public void setLargePictureUrl(String str) {
        this.largePictureUrl = str;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public int getLinkType() {
        return this.linkType;
    }

    public void setLinkType(int i) {
        this.linkType = i;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int i) {
        this.order = i;
    }
}
