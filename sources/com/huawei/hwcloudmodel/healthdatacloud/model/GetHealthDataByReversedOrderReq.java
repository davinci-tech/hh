package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.Utils;

/* loaded from: classes.dex */
public class GetHealthDataByReversedOrderReq implements IRequest {

    @SerializedName("dataType")
    private int dataType;

    @SerializedName("deviceCode")
    private long deviceCode;

    @SerializedName(ParsedFieldTag.RECORD_DAY)
    private int recordDay;

    @SerializedName("type")
    private int type;

    public long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(Long l) {
        this.deviceCode = l.longValue();
    }

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(Integer num) {
        this.dataType = num.intValue();
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getRecordDay() {
        return this.recordDay;
    }

    public void setRecordDay(int i) {
        this.recordDay = i;
    }

    public String toString() {
        return "GetHealthDataByReversedOrderReq{recordDay=" + this.recordDay + ", type=" + this.type + ", dataType=" + this.dataType + ", deviceCode=" + this.deviceCode + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataQuery/health/getHealthDataByRecordDay";
    }
}
