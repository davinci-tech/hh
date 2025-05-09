package com.huawei.maps.offlinedata.handler;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.ui.SafeIntent;
import com.huawei.maps.offlinedata.utils.a;
import com.huawei.maps.offlinedata.utils.g;

/* loaded from: classes5.dex */
public class PagesDirection extends JsBaseModule {
    @JavascriptInterface
    public void toReconnectPage() {
        g.a("PagesDirection", "call toReconnectPage start");
        try {
            SafeIntent safeIntent = new SafeIntent(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweischeme://healthapp/devicemanagement")));
            safeIntent.setFlags(268435456);
            a.a().startActivity(safeIntent);
        } catch (Exception e) {
            g.d("PagesDirection", "call toReconnectPage Exception:" + e.getMessage());
        }
    }

    @JavascriptInterface
    public void toWlanAccessPage() {
        g.a("PagesDirection", "call toWlanAccessPage start");
        try {
            SafeIntent safeIntent = new SafeIntent(new Intent("android.settings.WIFI_SETTINGS"));
            safeIntent.setFlags(268435456);
            a.a().startActivity(safeIntent);
        } catch (ActivityNotFoundException e) {
            g.d("PagesDirection", "call toWlanAccessPage Exception:" + e.getMessage());
        }
    }
}
