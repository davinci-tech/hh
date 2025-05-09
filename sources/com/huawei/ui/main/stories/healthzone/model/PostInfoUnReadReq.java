package com.huawei.ui.main.stories.healthzone.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonRequest;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes9.dex */
public class PostInfoUnReadReq extends CloudCommonRequest {

    @SerializedName(BleConstants.LIMIT)
    private int limit;

    @SerializedName("tmpLang")
    private int tmpLang;

    public int setTotalUnreadLimit(int i) {
        this.limit = i;
        return i;
    }

    public void setTmpLang(int i) {
        this.tmpLang = i;
    }
}
