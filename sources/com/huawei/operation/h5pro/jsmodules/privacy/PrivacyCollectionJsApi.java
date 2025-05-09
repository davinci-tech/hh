package com.huawei.operation.h5pro.jsmodules.privacy;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class PrivacyCollectionJsApi extends JsBaseModule {
    private static final String TAG = "PrivacyCollectionJsApi";

    @JavascriptInterface
    public void queryPrivacyCollection(long j, String str) {
        try {
            String e = SharedPreferenceManager.e("privacy_center", new JSONObject(str).getString("itemKey"), "");
            LogUtil.a(TAG, "queryPrivacyCollection ", Long.valueOf(j), " params ", str, " time ", e);
            onSuccessCallback(j, TextUtils.isEmpty(e) ? "0" : e);
        } catch (JSONException unused) {
            onFailureCallback(j, "0");
        }
    }
}
