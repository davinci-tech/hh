package com.huawei.health.recognizekit.h5;

import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import defpackage.fau;

/* loaded from: classes.dex */
public class RecognizeH5Helper extends JsModuleBase {
    private static IH5Wrapper e;

    /* loaded from: classes7.dex */
    public interface IH5Wrapper {
        void finish();

        void inputManual();

        void reShooting();
    }

    public static void a(IH5Wrapper iH5Wrapper) {
        e = iH5Wrapper;
    }

    @JavascriptInterface
    public void inputManual() {
        IH5Wrapper iH5Wrapper = e;
        if (iH5Wrapper != null) {
            iH5Wrapper.inputManual();
        }
    }

    @JavascriptInterface
    public void reShooting() {
        IH5Wrapper iH5Wrapper = e;
        if (iH5Wrapper != null) {
            iH5Wrapper.reShooting();
        }
    }

    @JavascriptInterface
    public void finishNative() {
        IH5Wrapper iH5Wrapper = e;
        if (iH5Wrapper != null) {
            iH5Wrapper.finish();
        }
    }

    @JavascriptInterface
    public String getBackground() {
        return fau.b();
    }

    public static void c() {
        e = null;
    }
}
