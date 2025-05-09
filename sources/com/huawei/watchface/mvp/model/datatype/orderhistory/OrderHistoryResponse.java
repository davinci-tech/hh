package com.huawei.watchface.mvp.model.datatype.orderhistory;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes7.dex */
public class OrderHistoryResponse {

    @SerializedName("body")
    private List<OrderHistoryBean> orderHistoryBeanList;
    private String recordCount;

    @SerializedName("resultcode")
    private String resultCode;

    @SerializedName("resultinfo")
    private String resultInfo;

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String str) {
        this.resultCode = str;
    }

    public List<OrderHistoryBean> getOrderHistoryBeanList() {
        return this.orderHistoryBeanList;
    }

    public void setOrderHistoryBeanList(List<OrderHistoryBean> list) {
        this.orderHistoryBeanList = list;
    }

    public String getResultInfo() {
        return this.resultInfo;
    }

    public void setResultInfo(String str) {
        this.resultInfo = str;
    }

    public String getRecordCount() {
        return this.recordCount;
    }

    public void setRecordCount(String str) {
        this.recordCount = str;
    }
}
