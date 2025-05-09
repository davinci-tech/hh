package com.huawei.watchface.mvp.model.datatype.orderhistory;

/* loaded from: classes7.dex */
public class OrderHistoryRequestModel {
    private String id;
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

    public OrderHistoryRequestModel(String str, String str2) {
        this.id = str;
        this.type = str2;
    }
}
