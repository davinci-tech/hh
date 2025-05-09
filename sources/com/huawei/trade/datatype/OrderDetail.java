package com.huawei.trade.datatype;

import java.util.List;

/* loaded from: classes6.dex */
public class OrderDetail {
    private int clientType;
    private String currency;
    private long genTime;
    private List<ProductResourceInfo> giveawaysInfo;
    private int invoiceStatus;
    private String orderId;
    private long originPrice;
    private long payPrice;
    private long payTime;
    private String payType;
    private int prefMode;
    private long preference;
    private ProductResourceInfo productInfo;
    private long reclaimTime;
    private long refundTime;
    private long releaseTime;
    private int state;

    public long getPreference() {
        return this.preference;
    }

    public void setPreference(long j) {
        this.preference = j;
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

    public List<ProductResourceInfo> getGiveawaysInfo() {
        return this.giveawaysInfo;
    }

    public void setGiveawaysInfo(List<ProductResourceInfo> list) {
        this.giveawaysInfo = list;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public long getOriginPrice() {
        return this.originPrice;
    }

    public void setOriginPrice(long j) {
        this.originPrice = j;
    }

    public long getPayPrice() {
        return this.payPrice;
    }

    public void setPayPrice(long j) {
        this.payPrice = j;
    }

    public int getPrefMode() {
        return this.prefMode;
    }

    public void setPrefMode(int i) {
        this.prefMode = i;
    }

    public String getPayType() {
        return this.payType;
    }

    public void setPayType(String str) {
        this.payType = str;
    }

    public long getPayTime() {
        return this.payTime;
    }

    public void setPayTime(long j) {
        this.payTime = j;
    }

    public long getRefundTime() {
        return this.refundTime;
    }

    public void setRefundTime(long j) {
        this.refundTime = j;
    }

    public long getReleaseTime() {
        return this.releaseTime;
    }

    public void setReleaseTime(long j) {
        this.releaseTime = j;
    }

    public long getReclaimTime() {
        return this.reclaimTime;
    }

    public void setReclaimTime(long j) {
        this.reclaimTime = j;
    }

    public int getInvoiceStatus() {
        return this.invoiceStatus;
    }

    public void setInvoiceStatus(int i) {
        this.invoiceStatus = i;
    }
}
