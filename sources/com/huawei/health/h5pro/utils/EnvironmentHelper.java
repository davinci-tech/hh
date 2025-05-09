package com.huawei.health.h5pro.utils;

import android.text.TextUtils;

/* loaded from: classes.dex */
public class EnvironmentHelper {
    public static volatile EnvironmentHelper c;
    public String d = null;

    /* renamed from: a, reason: collision with root package name */
    public BuildType f2455a = BuildType.RELEASE;
    public boolean h = false;
    public boolean j = false;
    public String b = "";
    public String i = "";
    public String e = "";

    public enum BuildType {
        RELEASE,
        BETA,
        TEST,
        DEBUG,
        GREEN
    }

    public void useHmsLite(boolean z, String str) {
        this.h = z;
        this.b = str;
    }

    public void setUserFlag(String str) {
        this.i = str;
    }

    public void setMobileAppEngine(boolean z) {
        this.j = z;
    }

    public void setBuildType(BuildType buildType) {
        if (buildType == null) {
            buildType = BuildType.RELEASE;
        }
        this.f2455a = buildType;
    }

    public void setBaseUrl(String str) {
        this.d = str;
    }

    public void setAccountGrsAppName(String str) {
        if (TextUtils.equals(this.e, str)) {
            return;
        }
        this.e = str;
    }

    public boolean isUseHmsLite() {
        return this.h;
    }

    public boolean isMobileAppEngine() {
        return this.j;
    }

    public String getUserFlag() {
        return this.i;
    }

    public String getUrl() {
        String str = this.d;
        return str != null ? str : "";
    }

    public String getHostAt() {
        return this.b;
    }

    public BuildType getBuildType() {
        BuildType buildType = this.f2455a;
        return buildType == null ? BuildType.RELEASE : buildType;
    }

    public String getAccountGrsAppName() {
        return this.e;
    }

    public static EnvironmentHelper getInstance() {
        if (c == null) {
            synchronized (EnvironmentHelper.class) {
                if (c == null) {
                    c = new EnvironmentHelper();
                }
            }
        }
        return c;
    }
}
