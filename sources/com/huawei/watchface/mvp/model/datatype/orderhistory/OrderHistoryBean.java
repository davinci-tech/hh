package com.huawei.watchface.mvp.model.datatype.orderhistory;

/* loaded from: classes7.dex */
public class OrderHistoryBean {
    private String amount;
    private int contentprivtype;
    private int currency;
    private String id;
    private int isCharge;
    private String isOrdered;
    private String type;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public int getCurrency() {
        return this.currency;
    }

    public void setCurrency(int i) {
        this.currency = i;
    }

    public int getIsCharge() {
        return this.isCharge;
    }

    public void setIsCharge(int i) {
        this.isCharge = i;
    }

    public int getContentPrivType() {
        return this.contentprivtype;
    }

    public void setContentPrivType(int i) {
        this.contentprivtype = i;
    }

    public String getIsOrdered() {
        return this.isOrdered;
    }

    public void setIsOrdered(String str) {
        this.isOrdered = str;
    }
}
