package com.huawei.watchface.mvp.model.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.watchface.ae;

/* loaded from: classes7.dex */
public class WatchFaceOrderBean extends ae {
    private String hispaceName;
    public String mCouponId;
    public String mReservedInfor;
    private String paySign;
    private String publicKey;

    @SerializedName("requstParams")
    private RequestParamsBean requestParams;

    @SerializedName("resultcode")
    private String resultCode;

    @SerializedName("resultinfo")
    private String resultInfo;
    private String tradeId;

    public String getPaySign() {
        return this.paySign;
    }

    public void setPaySign(String str) {
        this.paySign = str;
    }

    public String getResultInfo() {
        return this.resultInfo;
    }

    public void setResultInfo(String str) {
        this.resultInfo = str;
    }

    public String getHispaceName() {
        return this.hispaceName;
    }

    public void setHispaceName(String str) {
        this.hispaceName = str;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String str) {
        this.publicKey = str;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String str) {
        this.resultCode = str;
    }

    public String getTradeId() {
        return this.tradeId;
    }

    public void setTradeId(String str) {
        this.tradeId = str;
    }

    public RequestParamsBean getRequestParams() {
        return this.requestParams;
    }

    public void setRequestParams(RequestParamsBean requestParamsBean) {
        this.requestParams = requestParamsBean;
    }

    public static class RequestParamsBean {
        private String amount;

        @SerializedName(HwPayConstant.KEY_APPLICATIONID)
        private String applicationId;
        private String country;
        private String currency;
        private String environment;
        private String merchantId;
        private String merchantName;

        @SerializedName(HwPayConstant.KEY_PRODUCTDESC)
        private String productDescription;
        private String productName;
        private String requestId;
        private String sdkChannel;
        private String serviceCatalog;
        private String url;

        @SerializedName(HwPayConstant.KEY_URLVER)
        private String urlVersion;

        public String getCountry() {
            return this.country;
        }

        public void setCountry(String str) {
            this.country = str;
        }

        public String getAmount() {
            return this.amount;
        }

        public void setAmount(String str) {
            this.amount = str;
        }

        public String getProductName() {
            return this.productName;
        }

        public void setProductName(String str) {
            this.productName = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public String getMerchantName() {
            return this.merchantName;
        }

        public void setMerchantName(String str) {
            this.merchantName = str;
        }

        public String getProductDescription() {
            return this.productDescription;
        }

        public void setProductDescription(String str) {
            this.productDescription = str;
        }

        public String getSdkChannel() {
            return this.sdkChannel;
        }

        public void setSdkChannel(String str) {
            this.sdkChannel = str;
        }

        public String getEnvironment() {
            return this.environment;
        }

        public void setEnvironment(String str) {
            this.environment = str;
        }

        public String getUrlVersion() {
            return this.urlVersion;
        }

        public void setUrlVersion(String str) {
            this.urlVersion = str;
        }

        public String getMerchantId() {
            return this.merchantId;
        }

        public void setMerchantId(String str) {
            this.merchantId = str;
        }

        public String getRequestId() {
            return this.requestId;
        }

        public void setRequestId(String str) {
            this.requestId = str;
        }

        public String getServiceCatalog() {
            return this.serviceCatalog;
        }

        public void setServiceCatalog(String str) {
            this.serviceCatalog = str;
        }

        public String getApplicationId() {
            return this.applicationId;
        }

        public void setApplicationId(String str) {
            this.applicationId = str;
        }

        public String getCurrency() {
            return this.currency;
        }

        public void setCurrency(String str) {
            this.currency = str;
        }
    }
}
