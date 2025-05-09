package com.huawei.operation.jsoperation;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.WebViewUtils;

/* loaded from: classes5.dex */
public class JsRegisterFunctionResUtil {
    private static final String TAG = "PluginOperation_JsRegisterFunctionResUtil";

    private JsRegisterFunctionResUtil() {
    }

    public static void callBackResStatus(final WebViewActivity webViewActivity, String str, String str2) {
        if (webViewActivity == null || TextUtils.isEmpty(str)) {
            LogUtil.a(TAG, "param is error");
        } else {
            final String urlForHtml = WebViewUtils.getUrlForHtml(str, str2);
            webViewActivity.runOnUiThread(new Runnable() { // from class: com.huawei.operation.jsoperation.JsRegisterFunctionResUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    WebViewActivity.this.startLoadJs(urlForHtml);
                }
            });
        }
    }
}
