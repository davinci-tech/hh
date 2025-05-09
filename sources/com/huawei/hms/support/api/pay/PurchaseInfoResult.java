package com.huawei.hms.support.api.pay;

import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.pay.PurchaseInfo;
import com.huawei.hms.support.api.entity.pay.PurchaseInfoResp;
import com.huawei.hms.support.log.HMSLog;
import java.util.List;

/* loaded from: classes9.dex */
public class PurchaseInfoResult extends Result {
    private static final String TAG = "PurchaseInfoResult";
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

    public PurchaseInfoResult(PurchaseInfoResp purchaseInfoResp) {
        String str;
        int i = -1;
        if (purchaseInfoResp == null) {
            setStatus(new Status(-1, "purchaseinfo resp is null"));
            return;
        }
        this.rtnCode = purchaseInfoResp.getRtnCode();
        this.pageCount = purchaseInfoResp.getPageCount();
        this.purchaseInfoList = purchaseInfoResp.getPurchaseInfoList();
        this.sign = purchaseInfoResp.getSign();
        Status commonStatus = purchaseInfoResp.getCommonStatus();
        if (commonStatus == null) {
            if (this.rtnCode != null) {
                try {
                    i = Integer.parseInt(purchaseInfoResp.getRtnCode());
                    str = null;
                } catch (NumberFormatException e) {
                    HMSLog.e(TAG, "getPurchaseInfo parseInt exception :" + e.getMessage());
                    str = "parse purchaseinfo code exception, source code:" + this.rtnCode;
                }
            } else {
                str = "purchaseinfo rtnCode is null";
            }
            commonStatus = new Status(i, str);
        }
        setStatus(commonStatus);
    }

    public PurchaseInfoResult() {
    }
}
