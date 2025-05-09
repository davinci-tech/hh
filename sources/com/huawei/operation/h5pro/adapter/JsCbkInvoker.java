package com.huawei.operation.h5pro.adapter;

import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker;
import com.huawei.operation.utils.Constants;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JsCbkInvoker<T> implements H5ProJsCbkInvoker<T> {
    private static final String TAG = "H5PRO_JsCbkInvoker";
    private WeakReference<WebView> mWebView;

    static class CbkResult {

        @SerializedName("callback_id")
        public long callbackId;

        @SerializedName("data")
        public Object data;

        @SerializedName("err_code")
        public int errCode;

        @SerializedName("isComplete")
        public boolean isComplete;
    }

    public JsCbkInvoker(WebView webView) {
        this.mWebView = new WeakReference<>(webView);
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker
    public void onSuccess(T t, long j) {
        invokeCbk(j, 0, t, false);
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker
    public void onFailure(int i, String str, long j) {
        invokeCbk(j, i, str, false);
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker
    public void onComplete(int i, String str, long j) {
        invokeCbk(j, i, str, true);
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker
    public void invokeJsFunc(final String str, final Object... objArr) {
        new JsCbkHandler(Looper.getMainLooper()).post(new JsCbkHandlerRunnable() { // from class: com.huawei.operation.h5pro.adapter.JsCbkInvoker.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // java.lang.Runnable
            public void run() {
                WebView webView;
                if (JsCbkInvoker.this.mWebView != null && (webView = (WebView) JsCbkInvoker.this.mWebView.get()) != null) {
                    String str2 = str + Constants.LEFT_BRACKET_ONLY + JsCbkInvoker.this.buildParams(objArr) + Constants.RIGHT_BRACKET_ONLY;
                    LogUtil.i(JsCbkInvoker.TAG, "invokeJsFunc: script : " + str2);
                    webView.evaluateJavascript(str2, null);
                    return;
                }
                LogUtil.w(JsCbkInvoker.TAG, "invokeJsFunc mWebView is null");
            }
        });
    }

    private <A> void invokeCbk(long j, int i, A a2, boolean z) {
        invokeJsFunc("window.h5proRuntime.bridge.consumeCallback", buildCbkParam(j, i, a2, z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String buildParams(Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Gson gson = new Gson();
        for (int i = 0; i < objArr.length; i++) {
            sb.append(gson.toJson(objArr[i]));
            if (i < objArr.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private <A> CbkResult buildCbkParam(long j, int i, A a2, boolean z) {
        LogUtil.d(TAG, "triggerCallback enter, callback id:" + j + " errorCode:" + i + " isComplete" + z);
        CbkResult cbkResult = new CbkResult();
        cbkResult.callbackId = j;
        cbkResult.errCode = i;
        if (a2 instanceof JSONObject) {
            cbkResult.data = new Gson().fromJson(a2.toString(), (Class) JsonObject.class);
        } else {
            cbkResult.data = a2;
        }
        cbkResult.isComplete = z;
        return cbkResult;
    }

    static class JsCbkHandler extends Handler {
        JsCbkHandler(Looper looper) {
            super(looper);
        }
    }

    static abstract class JsCbkHandlerRunnable implements Runnable {
        private JsCbkHandlerRunnable() {
        }
    }
}
