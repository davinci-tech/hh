package com.huawei.hms.ads.jsb.inner.data;

import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.utils.cz;

/* loaded from: classes4.dex */
public class App {
    private String afDlBtnText;
    private String appDesc;
    private String appName;
    private String dlBtnText;
    private String iconUrl;
    private String packageName;
    private String reservedPkgName;

    public App(AppInfo appInfo) {
        if (appInfo != null) {
            this.appName = cz.d(appInfo.getAppName());
            this.iconUrl = appInfo.getIconUrl();
            this.appDesc = cz.d(appInfo.getAppDesc());
            this.packageName = appInfo.getPackageName();
            this.dlBtnText = cz.d(appInfo.i());
            this.afDlBtnText = cz.d(appInfo.j());
            this.reservedPkgName = appInfo.H();
        }
    }
}
