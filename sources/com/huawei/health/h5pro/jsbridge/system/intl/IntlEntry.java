package com.huawei.health.h5pro.jsbridge.system.intl;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.LogUtil;

/* loaded from: classes3.dex */
public class IntlEntry extends JsBaseModule {

    /* renamed from: a, reason: collision with root package name */
    public IntlDateFormatOperate f2388a;

    @JavascriptInterface
    public String formatDateTime(String str) {
        LogUtil.i(this.TAG, "formatDateTime");
        if (TextUtils.isEmpty(str)) {
            LogUtil.w(this.TAG, "formatDateTime: param is empty");
            return "";
        }
        if (this.f2388a == null) {
            this.f2388a = new IntlDateFormatOperate();
        }
        return this.f2388a.formatDateTime(this.mContext, str).toString();
    }

    @JavascriptInterface
    public String formatDateRange(String str) {
        LogUtil.i(this.TAG, "formatDateRange");
        if (TextUtils.isEmpty(str)) {
            LogUtil.w(this.TAG, "formatDateRange: param is empty");
            return "";
        }
        if (this.f2388a == null) {
            this.f2388a = new IntlDateFormatOperate();
        }
        return this.f2388a.formatDateRange(this.mContext, str).toString();
    }
}
