package com.huawei.hms.framework.network.grs;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.PackageUtils;

@Deprecated
/* loaded from: classes9.dex */
public class GrsConfig {
    private String appName;
    private String countrySource;
    private String issueCountry;
    private String regCountry;
    private String serCountry;
    private String userId;
    private String versionName;

    @Deprecated
    public void setVersionName(String str) {
        this.versionName = str;
    }

    @Deprecated
    public void setUserId(String str) {
        this.userId = str;
    }

    @Deprecated
    public void setSerCountry(String str) {
        this.serCountry = str;
    }

    @Deprecated
    public void setRegCountry(String str) {
        this.regCountry = str;
    }

    @Deprecated
    public void setIssueCountry(String str) {
        this.issueCountry = str;
    }

    @Deprecated
    public void setCountrySource(String str) {
        this.countrySource = str;
    }

    @Deprecated
    public void setAppName(String str) {
        this.appName = str;
    }

    @Deprecated
    public String getuserId() {
        return this.userId;
    }

    @Deprecated
    public String getVersionName() {
        return this.versionName;
    }

    @Deprecated
    public String getSerCountry() {
        return this.serCountry;
    }

    @Deprecated
    public String getRegCountry() {
        return this.regCountry;
    }

    @Deprecated
    public String getIssueCountry() {
        return this.issueCountry;
    }

    @Deprecated
    public GrsBaseInfo getGrsBaseInfo(Context context) {
        GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
        grsBaseInfo.setSerCountry(this.serCountry);
        grsBaseInfo.setVersionName(TextUtils.isEmpty(this.versionName) ? PackageUtils.getVersionName(context) : this.versionName);
        grsBaseInfo.setAppName(this.appName);
        grsBaseInfo.setUid(this.userId);
        grsBaseInfo.setRegCountry(this.regCountry);
        grsBaseInfo.setIssueCountry(this.issueCountry);
        grsBaseInfo.setCountrySource(this.countrySource);
        return grsBaseInfo;
    }

    @Deprecated
    public String getCountrySource() {
        return this.countrySource;
    }

    @Deprecated
    public String getAppName() {
        return this.appName;
    }

    @Deprecated
    public boolean equal(GrsConfig grsConfig) {
        return grsConfig != null && getObjSummary(grsConfig).equals(getObjSummary(this));
    }

    private static String getObjSummary(GrsConfig grsConfig) {
        if (grsConfig == null) {
            return "";
        }
        return "appName:" + grsConfig.appName + "serCountry:" + grsConfig.serCountry + "regCountry:" + grsConfig.regCountry + "issueCountry:" + grsConfig.issueCountry + "userId:" + grsConfig.userId + "versionName:" + grsConfig.versionName + "countrySource:" + grsConfig.countrySource;
    }

    @Deprecated
    public GrsConfig() {
    }
}
