package com.huawei.hms.iapfull.bean;

import com.huawei.hms.iapfull.b1;
import com.huawei.hms.iapfull.network.model.WebBaseResponse;
import java.util.List;

/* loaded from: classes9.dex */
public class WebProductInfoResp extends WebBaseResponse implements b1 {
    private String errMsg;
    private List<ProductInfo> productInfoList;
    private int returnCode;
    private List<ProductInfo> skuList;

    public void setSkuList(List<ProductInfo> list) {
        this.skuList = list;
    }

    public void setReturnCode(int i) {
        this.returnCode = i;
    }

    public void setProductInfoList(List<ProductInfo> list) {
        this.productInfoList = list;
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
    }

    public List<ProductInfo> getSkuList() {
        return this.skuList;
    }

    public int getReturnCode() {
        return this.returnCode;
    }

    public List<ProductInfo> getProductInfoList() {
        return this.productInfoList;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void convert() {
        List<ProductInfo> list = this.productInfoList;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (ProductInfo productInfo : this.productInfoList) {
            productInfo.setSubscriptionPeriod(productInfo.getSubPeriod());
            productInfo.setSubsIntroductoryPrice(productInfo.getSubSpecialPrice());
            productInfo.setSubsIntroductoryPriceMicros(productInfo.getSubSpecialPriceMicros());
            productInfo.setSubsIntroductoryPeriod(productInfo.getSubSpecialPeriod());
            productInfo.setSubsIntroductoryPeriodCycles(productInfo.getSubSpecialPeriodCycles());
        }
        this.skuList = this.productInfoList;
    }
}
