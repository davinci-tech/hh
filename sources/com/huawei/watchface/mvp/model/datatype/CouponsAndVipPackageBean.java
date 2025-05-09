package com.huawei.watchface.mvp.model.datatype;

/* loaded from: classes9.dex */
public class CouponsAndVipPackageBean {
    private CouponsByproductBean couponsByproductBean;
    private VipPackageBean vipPackageBean;

    public CouponsAndVipPackageBean(VipPackageBean vipPackageBean, CouponsByproductBean couponsByproductBean) {
        this.vipPackageBean = vipPackageBean;
        this.couponsByproductBean = couponsByproductBean;
    }

    public VipPackageBean getVipPackageBean() {
        return this.vipPackageBean;
    }

    public void setVipPackageBean(VipPackageBean vipPackageBean) {
        this.vipPackageBean = vipPackageBean;
    }

    public CouponsByproductBean getCouponsByproductBean() {
        return this.couponsByproductBean;
    }

    public void setCouponsByproductBean(CouponsByproductBean couponsByproductBean) {
        this.couponsByproductBean = couponsByproductBean;
    }
}
