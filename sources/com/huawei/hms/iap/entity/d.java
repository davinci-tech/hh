package com.huawei.hms.iap.entity;

import com.huawei.hms.core.aidl.AbstractMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;
import java.util.List;

/* loaded from: classes4.dex */
public class d extends AbstractMessageEntity {

    @Packed
    private String continuationToken;

    @Packed
    private String errMsg;

    @Packed
    private List<String> inAppPurchaseDataList;

    @Packed
    private List<String> inAppSignatureList;

    @Packed
    private List<String> itemList;

    @Packed
    private List<String> placedInappPurchaseDataList;

    @Packed
    private List<String> placedInappSignatureList;

    @Packed
    private int returnCode;

    @Packed
    private String signatureAlgorithm;

    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public int getReturnCode() {
        return this.returnCode;
    }

    public List<String> getPlacedInappSignatureList() {
        return this.placedInappSignatureList;
    }

    public List<String> getPlacedInappPurchaseDataList() {
        return this.placedInappPurchaseDataList;
    }

    public List<String> getItemList() {
        return this.itemList;
    }

    public List<String> getInAppSignatureList() {
        return this.inAppSignatureList;
    }

    public List<String> getInAppPurchaseDataList() {
        return this.inAppPurchaseDataList;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public String getContinuationToken() {
        return this.continuationToken;
    }
}
