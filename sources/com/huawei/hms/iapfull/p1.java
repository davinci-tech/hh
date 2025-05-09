package com.huawei.hms.iapfull;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import com.huawei.secure.android.common.util.ScreenUtil;

/* loaded from: classes4.dex */
public class p1 {

    /* renamed from: a, reason: collision with root package name */
    private Activity f4740a;

    @JavascriptInterface
    public void enableScreenshots() {
        Activity activity = this.f4740a;
        if (activity == null) {
            y0.a("JsThemeUtil", "enableScreenshots Activity is null");
        } else {
            ScreenUtil.enableScreenshots(activity);
        }
    }

    @JavascriptInterface
    public void disableScreenshots() {
        Activity activity = this.f4740a;
        if (activity == null) {
            y0.a("JsThemeUtil", "disableScreenshots Activity is null");
        } else {
            ScreenUtil.disableScreenshots(activity);
        }
    }

    public p1(Activity activity) {
        this.f4740a = activity;
    }
}
