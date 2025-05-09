package com.huawei.hms.iapfull.bean;

import com.huawei.hms.iapfull.b1;
import com.huawei.hms.iapfull.network.model.WebBaseResponse;
import java.util.List;

/* loaded from: classes9.dex */
public class WebPurchaseInfoResp extends WebBaseResponse implements b1 {
    private long pageCount;
    private List<PurchaseInfo> purchaseInfoList;
    private String rtnCode;
    private String sign;

    public void setSign(String str) {
        this.sign = str;
    }

    public void setRtnCode(String str) {
        this.rtnCode = str;
    }

    public void setPurchaseInfoList(List<PurchaseInfo> list) {
        this.purchaseInfoList = list;
    }

    public void setPageCount(long j) {
        this.pageCount = j;
    }

    public String getSign() {
        return this.sign;
    }

    public String getRtnCode() {
        return this.rtnCode;
    }

    public List<PurchaseInfo> getPurchaseInfoList() {
        return this.purchaseInfoList;
    }

    public long getPageCount() {
        return this.pageCount;
    }
}
