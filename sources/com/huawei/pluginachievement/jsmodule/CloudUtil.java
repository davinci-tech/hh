package com.huawei.pluginachievement.jsmodule;

import android.os.Build;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.HiDateUtil;

/* loaded from: classes8.dex */
public class CloudUtil extends JsBaseModule {
    @JavascriptInterface
    public String getKakaCloudUrl() {
        return GRSManager.a(this.mContext).getUrl("achievementUrl");
    }

    @JavascriptInterface
    public String getHuid() {
        return LoginInit.getInstance(this.mContext).getAccountInfo(1011);
    }

    @JavascriptInterface
    public String getToken() {
        return LoginInit.getInstance(this.mContext).getAccountInfo(1008);
    }

    @JavascriptInterface
    public int getTokenType() {
        return ThirdLoginDataStorageUtil.getTokenTypeValue();
    }

    @JavascriptInterface
    public String getLanguage() {
        return this.mContext.getResources().getConfiguration().locale.getLanguage();
    }

    @JavascriptInterface
    public String getTimeZone() {
        return HiDateUtil.d("");
    }

    @JavascriptInterface
    public String getSysVersion() {
        return Build.VERSION.RELEASE;
    }

    @JavascriptInterface
    public String getDeviceId() {
        return LoginInit.getInstance(this.mContext).getDeviceId();
    }

    @JavascriptInterface
    public String getDeviceType() {
        return PhoneInfoUtils.getDeviceModel();
    }

    @JavascriptInterface
    public String getCountryCode() {
        return LoginInit.getInstance(this.mContext).getAccountInfo(1010);
    }

    @JavascriptInterface
    public int getSiteId() {
        return CommonUtil.m(this.mContext, LoginInit.getInstance(this.mContext).getAccountInfo(1009));
    }

    @JavascriptInterface
    public String getVersion() {
        return CommonUtil.c(BaseApplication.getContext());
    }
}
