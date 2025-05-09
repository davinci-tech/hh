package com.huawei.watchface.mvp.model.pay;

import com.huawei.hms.support.api.pay.PayResultInfo;
import com.huawei.openalliance.ad.constant.DetailedCreativeType;
import com.huawei.watchface.ab;
import com.huawei.watchface.mvp.model.datatype.VipPackageBean;

/* loaded from: classes9.dex */
public class MagicJsPayResult {
    private int buttonType;
    private String discountPrice;
    private boolean isVip;
    private String orderId;
    private String packagePrice;
    private String productCode;
    private String productCurrency;
    private String productDesc;
    private String productName;
    private String productPrice;
    private String productType;
    private String renewCode;
    private String requestCode;
    private int resultCode;
    private String resultDesc;
    private String timestamp;

    public int getButtonType() {
        return this.buttonType;
    }

    public void setButtonType(int i) {
        this.buttonType = i;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String str) {
        this.productCode = str;
    }

    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String str) {
        this.productType = str;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public String getProductDesc() {
        return this.productDesc;
    }

    public void setProductDesc(String str) {
        this.productDesc = str;
    }

    public String getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(String str) {
        this.productPrice = str;
    }

    public String getDiscountPrice() {
        return this.discountPrice;
    }

    public void setDiscountPrice(String str) {
        this.discountPrice = str;
    }

    public String getProductCurrency() {
        return this.productCurrency;
    }

    public void setProductCurrency(String str) {
        this.productCurrency = str;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public String getPackagePrice() {
        return this.packagePrice;
    }

    public void setPackagePrice(String str) {
        this.packagePrice = str;
    }

    public String getRenewCode() {
        return this.renewCode;
    }

    public void setRenewCode(String str) {
        this.renewCode = str;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public String getResultDesc() {
        return this.resultDesc;
    }

    public void setResultDesc(String str) {
        this.resultDesc = str;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public String getRequestCode() {
        return this.requestCode;
    }

    public void setRequestCode(String str) {
        this.requestCode = str;
    }

    public boolean isVip() {
        return this.isVip;
    }

    public void setVip(boolean z) {
        this.isVip = z;
    }

    public void copyInfo(VipPackageBean vipPackageBean) {
        if (vipPackageBean != null) {
            setButtonType(DetailedCreativeType.LONG_TEXT);
            setDiscountPrice(vipPackageBean.getDiscountPrice());
            setProductCurrency(vipPackageBean.getCurrency());
            setVip(ab.a().f());
            setRenewCode(vipPackageBean.getRenewCode());
            setProductCode(vipPackageBean.getProductCode());
            setProductName(vipPackageBean.getProductName());
            setProductType(vipPackageBean.getProductType());
            setProductDesc(vipPackageBean.getProductDesc());
            setProductPrice(vipPackageBean.getPrice());
        }
    }

    public void copyInfo(PayResultInfo payResultInfo) {
        if (payResultInfo != null) {
            setResultCode(payResultInfo.getReturnCode());
            setResultDesc(payResultInfo.getErrMsg());
            setPackagePrice(payResultInfo.getAmount());
            setOrderId(payResultInfo.getOrderID());
            setRequestCode(payResultInfo.getRequestId());
            setTimestamp(payResultInfo.getTime());
        }
    }
}
