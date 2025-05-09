package com.huawei.maps.offlinedata.handler;

import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.maps.offlinedata.service.cloud.a;
import com.huawei.maps.offlinedata.utils.g;

/* loaded from: classes5.dex */
public class AuthClient4H5 extends JsBaseModule {
    @JavascriptInterface
    public void authenticate(final long j) {
        g.a("AuthClient4H5", "H5 call authenticate start");
        new Thread(new Runnable() { // from class: com.huawei.maps.offlinedata.handler.AuthClient4H5$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AuthClient4H5.a(j);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(long j) {
        try {
            if (a.a().h()) {
                com.huawei.maps.offlinedata.jsbridge.a.a().a(a.a().c(), j);
                return;
            }
        } catch (Exception e) {
            g.d("AuthClient4H5", "AuthClient4H5 exception:" + e.getMessage());
        }
        com.huawei.maps.offlinedata.jsbridge.a.a().a(-1, "auth failed", j);
    }
}
