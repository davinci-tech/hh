package com.huawei.health.tradeservice.cloud;

import com.huawei.trade.datatype.Coupon;
import java.util.List;

/* loaded from: classes4.dex */
public class CouponListRsp extends BaseRsp {
    private List<Coupon> couponList;

    public List<Coupon> getCouponList() {
        return this.couponList;
    }

    public void setCouponList(List<Coupon> list) {
        this.couponList = list;
    }
}
