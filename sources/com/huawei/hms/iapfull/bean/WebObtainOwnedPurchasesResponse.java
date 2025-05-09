package com.huawei.hms.iapfull.bean;

import com.huawei.hms.iapfull.b1;
import com.huawei.hms.iapfull.network.model.WebBaseResponse;
import java.util.List;

/* loaded from: classes9.dex */
public class WebObtainOwnedPurchasesResponse extends WebBaseResponse implements b1 {
    private String continuationToken;
    private String errMsg;
    private List<String> inAppPurchaseDataList;
    private List<String> inAppSignature;
    private List<String> itemList;
    private List<String> placedInappPurchaseDataList;
    private List<String> placedInappSignatureList;
    private int returnCode;
    private String signatureAlgorithm;

    public void setSignatureAlgorithm(String str) {
        this.signatureAlgorithm = str;
    }

    public void setReturnCode(int i) {
        this.returnCode = i;
    }

    public void setPlacedInappSignatureList(List<String> list) {
        this.placedInappSignatureList = list;
    }

    public void setPlacedInappPurchaseDataList(List<String> list) {
        this.placedInappPurchaseDataList = list;
    }

    public void setItemList(List<String> list) {
        this.itemList = list;
    }

    public void setInAppSignature(List<String> list) {
        this.inAppSignature = list;
    }

    public void setInAppPurchaseDataList(List<String> list) {
        this.inAppPurchaseDataList = list;
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
    }

    public void setContinuationToken(String str) {
        this.continuationToken = str;
    }

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

    public List<String> getInAppSignature() {
        return this.inAppSignature;
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
