package com.huawei.health.tradeservice.cloud;

import com.google.gson.annotations.SerializedName;
import com.huawei.trade.datatype.CouponResult;
import java.util.List;

/* loaded from: classes4.dex */
public class ReceiveCouponRsp extends BaseRsp {

    @SerializedName("couponResultList")
    private List<CouponResult> couponResultList;

    public List<CouponResult> getCouponResultList() {
        return this.couponResultList;
    }

    public void setCouponResultList(List<CouponResult> list) {
        this.couponResultList = list;
    }
}
