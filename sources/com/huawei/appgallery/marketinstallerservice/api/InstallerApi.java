package com.huawei.appgallery.marketinstallerservice.api;

import android.app.Activity;
import android.content.Context;
import com.huawei.appgallery.marketinstallerservice.a.c;
import defpackage.agl;
import defpackage.agr;

/* loaded from: classes3.dex */
public class InstallerApi {
    public static void installMarket(Activity activity, InstallParamSpec installParamSpec, InstallCallback installCallback) {
        c cVar = (c) agl.a(c.class);
        if (cVar != null) {
            cVar.a(activity, installParamSpec, installCallback);
        } else {
            agr.e("InstallerApi", "installMarket impl error!");
        }
    }

    public static void getMarketInfo(Context context, BaseParamSpec baseParamSpec, InstallCallback installCallback) {
        c cVar = (c) agl.a(c.class);
        if (cVar != null) {
            cVar.a(context, baseParamSpec, installCallback);
        } else {
            agr.e("InstallerApi", "getMarketInfo impl error!");
        }
    }
}
