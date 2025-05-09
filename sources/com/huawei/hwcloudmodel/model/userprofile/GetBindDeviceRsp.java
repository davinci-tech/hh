package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes5.dex */
public class GetBindDeviceRsp extends CloudCommonReponse {

    @SerializedName("deviceInfos")
    private List<DeviceInfo> deviceInfos;

    public List<DeviceInfo> getDeviceInfos() {
        return this.deviceInfos;
    }

    public void setDeviceInfos(List<DeviceInfo> list) {
        this.deviceInfos = list;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "GetBindDeviceRsp{deviceInfos=" + this.deviceInfos + '}';
    }
}
