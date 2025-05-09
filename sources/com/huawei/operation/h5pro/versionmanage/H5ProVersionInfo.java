package com.huawei.operation.h5pro.versionmanage;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.db.bean.SdkCfgSha256Record;

/* loaded from: classes5.dex */
public class H5ProVersionInfo {
    public static final String GRAY_SWITCH_OPEN = "1";

    @SerializedName("graySwitch")
    private String graySwitch;

    @SerializedName(SdkCfgSha256Record.PKGNAME)
    private String pkgName;

    @SerializedName("version")
    private String version;

    public String getPkgName() {
        return this.pkgName;
    }

    public void setPkgName(String str) {
        this.pkgName = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getGraySwitch() {
        return this.graySwitch;
    }

    public void setGraySwitch(String str) {
        this.graySwitch = str;
    }

    public boolean isIllegal() {
        return (TextUtils.isEmpty(this.pkgName) || TextUtils.isEmpty(this.version)) ? false : true;
    }
}
