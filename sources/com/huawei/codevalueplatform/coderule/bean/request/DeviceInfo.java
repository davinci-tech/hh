package com.huawei.codevalueplatform.coderule.bean.request;

import com.google.gson.annotations.SerializedName;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;

/* loaded from: classes3.dex */
public class DeviceInfo {

    @SerializedName("androidVersion")
    private String androidVersion;

    @SerializedName(FaqConstants.FAQ_EMUIVERSION)
    private String emuiVersion;

    @SerializedName("terminalType")
    private String terminalType;

    public String getTerminalType() {
        return this.terminalType;
    }

    public void setTerminalType(String str) {
        this.terminalType = str;
    }

    public String getAndroidVersion() {
        return this.androidVersion;
    }

    public void setAndroidVersion(String str) {
        this.androidVersion = str;
    }

    public String getEmuiVersion() {
        return this.emuiVersion;
    }

    public void setEmuiVersion(String str) {
        this.emuiVersion = str;
    }
}
