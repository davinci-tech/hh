package com.huawei.health.h5pro.jsbridge.system.logger;

import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.LogPrintOperate;
import com.huawei.health.h5pro.utils.LogUtil;
import java.util.Locale;

/* loaded from: classes3.dex */
public class LoggerEntry extends JsBaseModule {
    public String c;
    public boolean e;

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        this.c = LogUtil.getTag(this.mH5ProInstance, "AppLog");
        this.e = LogPrintOperate.isDecodePrint(this.mH5ProInstance);
    }

    @JavascriptInterface
    public void info(String str, String str2) {
        LogUtil.i(d(str), this.e, str2);
    }

    @JavascriptInterface
    public void error(String str, String str2) {
        LogUtil.e(d(str), this.e, null, str2);
    }

    @JavascriptInterface
    public void debug(String str, String str2) {
        LogUtil.d(d(str), this.e, str2);
    }

    private String d(String str) {
        return String.format(Locale.ROOT, "%1$s[%2$s]", this.c, str);
    }
}
