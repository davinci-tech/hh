package com.huawei.watchface.mvp.model.datatype;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes9.dex */
public class VipPackageResp {
    private List<VipPackageBean> productInfoList;

    @SerializedName("resultcode")
    private String resultVipPackageCode;

    @SerializedName("resultinfo")
    private String resultVipPackageInfo;

    public List<VipPackageBean> getProductInfoList() {
        return this.productInfoList;
    }
}
