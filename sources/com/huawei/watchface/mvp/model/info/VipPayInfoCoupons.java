package com.huawei.watchface.mvp.model.info;

import com.huawei.watchface.mvp.model.datatype.VipPackageBean;

/* loaded from: classes9.dex */
public class VipPayInfoCoupons extends VipPackageBean {
    private String couponId;
    private String deviceModel;
    private int orderChannel;
    private String orderType;
    private String promotionId;
    private String promotionName;
    private String reportedPrice;
    private String sign;

    public String getCouponId() {
        return this.couponId;
    }

    public void setCouponId(String str) {
        this.couponId = str;
    }

    public int getOrderChannel() {
        return this.orderChannel;
    }

    public void setOrderChannel(int i) {
        this.orderChannel = i;
    }

    public String getPromotionId() {
        return this.promotionId;
    }

    public void setPromotionId(String str) {
        this.promotionId = str;
    }

    public String getReportedPrice() {
        return this.reportedPrice;
    }

    public void setReportedPrice(String str) {
        this.reportedPrice = str;
    }

    public String getOrderType() {
        return this.orderType;
    }

    public void setOrderType(String str) {
        this.orderType = str;
    }

    public String getPromotionName() {
        return this.promotionName;
    }

    public void setPromotionName(String str) {
        this.promotionName = str;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public void copyInfo(VipPackageBean vipPackageBean) {
        if (vipPackageBean != null) {
            setProductCode(vipPackageBean.getProductCode());
            setProductType(vipPackageBean.getProductType());
            setUserType(vipPackageBean.getUserType());
            setProductName(vipPackageBean.getProductName());
            setPrice(vipPackageBean.getPrice());
            setProductDesc(vipPackageBean.getProductDesc());
            setSymbol(vipPackageBean.getSymbol());
            setDiscountPrice(vipPackageBean.getDiscountPrice());
            setCurrency(vipPackageBean.getCurrency());
            setValidDay(vipPackageBean.getValidDay());
            setCanRenewFlag(vipPackageBean.getCanRenewFlag());
            setRenewCode(vipPackageBean.getRenewCode());
            setExtendInfo(vipPackageBean.getExtendInfo());
            setSuspension(vipPackageBean.getSuspension());
            setPromotiondesc(vipPackageBean.getPromotiondesc());
            setPromotionurl(vipPackageBean.getPromotionurl());
            setMarkUrl(vipPackageBean.getMarkUrl());
            setSendCount(vipPackageBean.getSendCount());
            setRelProductCode(vipPackageBean.getRelProductCode());
            setRelProductDes(vipPackageBean.getRelProductDes());
            setRedirectUrl(vipPackageBean.getRedirectUrl());
        }
    }
}
