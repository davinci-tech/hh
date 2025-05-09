package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WatchFaceUtil;
import com.huawei.networkclient.IRequest;
import com.huawei.operation.utils.CloudParamKeys;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes9.dex */
public class WatchFaceDetailRequest implements IRequest {

    @SerializedName("abilities")
    private List<WatchAbility> abilities;

    @SerializedName(CloudParamKeys.CLIENT_TYPE)
    private int clientType;

    @SerializedName("countryCode")
    private String countryCode;

    @SerializedName("deviceType")
    private String deviceType;

    @SerializedName("lang")
    private String lang;

    @SerializedName("version")
    private String version;

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public String getLang() {
        return this.lang;
    }

    public void setLang(String str) {
        this.lang = str;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public int getClientType() {
        return this.clientType;
    }

    public void setClientType(int i) {
        this.clientType = i;
    }

    public List<WatchAbility> getAbilities() {
        return this.abilities;
    }

    public void setAbilities(List<WatchAbility> list) {
        this.abilities = list;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return WatchFaceUtil.joinUrl(GRSManager.a(BaseApplication.e()).getUrl("healthOperationUrl"), WatchFaceConstant.DETAIL_REQUEST_URL);
    }
}
