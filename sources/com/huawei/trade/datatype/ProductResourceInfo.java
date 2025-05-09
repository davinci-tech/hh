package com.huawei.trade.datatype;

/* loaded from: classes6.dex */
public class ProductResourceInfo {
    private long attachProductPrice;
    private String attachType;
    private String buttonDescAsGiveaway;
    private int linkType;
    private String linkValue;
    private String productDesc;
    private String productId;
    private String productName;
    private String productUrl;
    private String resourceId;
    private String resourceName;
    private int resourceType;
    private boolean showGiveawayFlag;

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public String getProductDesc() {
        return this.productDesc;
    }

    public void setProductDesc(String str) {
        this.productDesc = str;
    }

    public String getProductUrl() {
        return this.productUrl;
    }

    public void setProductUrl(String str) {
        this.productUrl = str;
    }

    public int getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(int i) {
        this.resourceType = i;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(String str) {
        this.resourceId = str;
    }

    public String getAttachType() {
        return this.attachType;
    }

    public void setAttachType(String str) {
        this.attachType = str;
    }

    public long getAttachProductPrice() {
        return this.attachProductPrice;
    }

    public void setAttachProductPrice(long j) {
        this.attachProductPrice = j;
    }

    public String getResourceName() {
        String str = this.resourceName;
        return str == null ? "" : str;
    }

    public void setResourceName(String str) {
        this.resourceName = str;
    }

    public boolean getShowGiveawayFlag() {
        return this.showGiveawayFlag;
    }

    public void setShowGiveawayFlag(boolean z) {
        this.showGiveawayFlag = z;
    }

    public int getLinkType() {
        return this.linkType;
    }

    public void setLinkType(int i) {
        this.linkType = i;
    }

    public String getLinkValue() {
        String str = this.linkValue;
        return str == null ? "" : str;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public String getButtonDescAsGiveaway() {
        String str = this.buttonDescAsGiveaway;
        return str == null ? "" : str;
    }

    public void setButtonDescAsGiveaway(String str) {
        this.buttonDescAsGiveaway = str;
    }

    public String toString() {
        return "ProductResourceInfo{productId='" + this.productId + "', productName='" + this.productName + "', productDesc='" + this.productDesc + "', productUrl='" + this.productUrl + "', resourceType=" + this.resourceType + ", resourceId='" + this.resourceId + "', attachType='" + this.attachType + "', attachProductPrice=" + this.attachProductPrice + ", resourceName='" + this.resourceName + "', showGiveawayFlag=" + this.showGiveawayFlag + ", linkType=" + this.linkType + ", linkValue='" + this.linkValue + "', buttonDescAsGiveaway='" + this.buttonDescAsGiveaway + "'}";
    }
}
