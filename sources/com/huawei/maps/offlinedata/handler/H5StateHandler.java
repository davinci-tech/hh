package com.huawei.maps.offlinedata.handler;

import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.maps.offlinedata.service.a;
import com.huawei.maps.offlinedata.utils.g;

/* loaded from: classes5.dex */
public class H5StateHandler extends JsBaseModule {
    @JavascriptInterface
    public void h5InAvailableToStateAvailable() {
        g.a("H5StateHandler", "h5InAvailableToStateAvailable");
        a.a().a(true);
        a.a().g();
    }

    @JavascriptInterface
    public void h5AvailableToStateInAvailable() {
        g.a("H5StateHandler", "h5AvailableToStateInAvailable");
        a.a().a(false);
        a.a().f();
    }
}
