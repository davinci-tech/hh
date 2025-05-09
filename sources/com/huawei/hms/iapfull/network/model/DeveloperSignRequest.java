package com.huawei.hms.iapfull.network.model;

import com.huawei.hms.iapfull.bean.PayRequest;
import java.util.List;

/* loaded from: classes4.dex */
public class DeveloperSignRequest extends PayRequest {
    private String developUserSign;
    private List<String> dics;
    private String extUserName;
    private String noiseTamp;
    private String signType;
    private String ver;

    public void setVer(String str) {
        this.ver = str;
    }

    public void setSignType(String str) {
        this.signType = str;
    }

    public void setNoiseTamp(String str) {
        this.noiseTamp = str;
    }

    public void setExtUserName(String str) {
        this.extUserName = str;
    }

    public void setDics(List<String> list) {
        this.dics = list;
    }

    public void setDevelopUserSign(String str) {
        this.developUserSign = str;
    }

    public String getVer() {
        return this.ver;
    }

    public String getSignType() {
        return this.signType;
    }

    public String getNoiseTamp() {
        return this.noiseTamp;
    }

    public String getExtUserName() {
        return this.extUserName;
    }

    public List<String> getDics() {
        return this.dics;
    }

    public String getDevelopUserSign() {
        return this.developUserSign;
    }

    public DeveloperSignRequest(PayRequest payRequest) {
        this.productName = payRequest.getProductName();
        this.productDesc = payRequest.getProductDesc();
        this.applicationID = payRequest.getApplicationID();
        this.requestId = payRequest.getRequestId();
        this.amount = payRequest.getAmount();
        this.merchantId = payRequest.getMerchantId();
        this.sdkChannel = payRequest.getSdkChannel();
        this.url = payRequest.getUrl();
        this.currency = payRequest.getCurrency();
        this.country = payRequest.getCountry();
        this.extReserved = payRequest.getExtReserved();
        this.serviceCatalog = payRequest.getServiceCatalog();
        this.urlver = payRequest.getUrlver();
        if ("toSign".equals(payRequest.getTradeType())) {
            this.tradeType = payRequest.getTradeType();
        }
        this.reservedInfor = payRequest.getReservedInfor();
        this.expireTime = payRequest.getExpireTime();
        this.signType = payRequest.getUsedSignType();
    }

    public DeveloperSignRequest() {
    }
}
