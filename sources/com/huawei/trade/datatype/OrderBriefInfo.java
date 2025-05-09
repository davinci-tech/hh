package com.huawei.trade.datatype;

import java.util.List;

/* loaded from: classes6.dex */
public class OrderBriefInfo {
    private int clientType;
    private String currency;
    private long genTime;
    private int giveawaysExist;
    private List<ProductResourceInfo> giveawaysInfo;
    private String orderCode;
    private String orderId;
    private long originPrice;
    private long payPrice;
    private int prefMode;
    private ProductResourceInfo productInfo;
    private int state;

    public String getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(String str) {
        this.orderCode = str;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public long getGenTime() {
        return this.genTime;
    }

    public void setGenTime(long j) {
        this.genTime = j;
    }

    public int getClientType() {
        return this.clientType;
    }

    public void setClientType(int i) {
        this.clientType = i;
    }

    public ProductResourceInfo getProductInfo() {
        return this.productInfo;
    }

    public void setProductInfo(ProductResourceInfo productResourceInfo) {
        this.productInfo = productResourceInfo;
    }

    public int getGiveawaysExist() {
        return this.giveawaysExist;
    }

    public void setGiveawaysExist(int i) {
        this.giveawaysExist = i;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public long getPayPrice() {
        return this.payPrice;
    }

    public void setPayPrice(long j) {
        this.payPrice = j;
    }

    public long getOriginPrice() {
        return this.originPrice;
    }

    public void setOriginPrice(long j) {
        this.originPrice = j;
    }

    public int getPrefMode() {
        return this.prefMode;
    }

    public void setPrefMode(int i) {
        this.prefMode = i;
    }

    public List<ProductResourceInfo> getGiveawaysInfo() {
        return this.giveawaysInfo;
    }

    public void setGiveawaysInfo(List<ProductResourceInfo> list) {
        this.giveawaysInfo = list;
    }

    public String toString() {
        return "OrderBriefInfo{orderCode='" + this.orderCode + "', orderId='" + this.orderId + "', state=" + this.state + ", genTime=" + this.genTime + ", productInfo=" + this.productInfo + ", giveawaysExist=" + this.giveawaysExist + ", currency='" + this.currency + "', payPrice=" + this.payPrice + ", originPrice=" + this.originPrice + ", prefMode=" + this.prefMode + ", giveawaysInfo=" + this.giveawaysInfo + '}';
    }
}
