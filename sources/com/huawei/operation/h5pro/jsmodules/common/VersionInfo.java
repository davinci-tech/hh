package com.huawei.operation.h5pro.jsmodules.common;

import android.os.Build;
import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.constant.OsType;
import health.compact.a.BuildConfigProperties;
import health.compact.a.EnvironmentUtils;

/* loaded from: classes.dex */
class VersionInfo {

    @SerializedName("apiLevel")
    final int apiLevel = 9;

    @SerializedName("platform")
    final String platform = OsType.ANDROID;

    @SerializedName("appVersion")
    final String appVersion = EnvironmentUtils.a();

    @SerializedName("sysVersion")
    final String sysVersion = Build.VERSION.RELEASE;

    @SerializedName("buildType")
    final String buildType = BuildConfigProperties.b();

    VersionInfo() {
    }
}
