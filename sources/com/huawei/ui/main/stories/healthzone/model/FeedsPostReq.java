package com.huawei.ui.main.stories.healthzone.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwcloudmodel.model.CloudCommonRequest;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes7.dex */
public class FeedsPostReq extends CloudCommonRequest {

    @SerializedName("cursor")
    private String cursor;

    @SerializedName(HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION)
    private int direction;

    @SerializedName(BleConstants.LIMIT)
    private int limit;

    @SerializedName("tmpLang")
    private int tmpLang;

    public int setPostLimit(int i) {
        this.limit = i;
        return i;
    }

    public String setDataCursor(String str) {
        this.cursor = str;
        return str;
    }

    public int setPageDirection(int i) {
        this.direction = i;
        return i;
    }

    public int setTmpLang(int i) {
        this.tmpLang = i;
        return i;
    }
}
