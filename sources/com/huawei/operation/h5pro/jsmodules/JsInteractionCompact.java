package com.huawei.operation.h5pro.jsmodules;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.adapter.SendCurrentUrlCallback;
import com.huawei.operation.h5pro.TrustListCheckerImpl;
import com.huawei.operation.h5pro.jsmodules.compact.CaptureShareCompact;
import com.huawei.operation.h5pro.jsmodules.compact.CloseWebCallbackCompact;
import com.huawei.operation.h5pro.jsmodules.compact.JsDataCompact;
import com.huawei.operation.h5pro.jsmodules.compact.SendServerErrorMsgCallbackCompact;
import com.huawei.operation.h5pro.jsmodules.compact.StartAppSettingPageCompact;
import com.huawei.operation.h5pro.jsmodules.compact.ToastCompact;
import com.huawei.operation.js.JsInteraction;
import defpackage.gmz;
import defpackage.jeg;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JsInteractionCompact extends JsInteraction {
    private static final String TAG = "PluginOperation_JsInteractionCompact";
    private Context mContext;
    private H5ProInstance mInstance;

    @Override // com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onMount(Context context, H5ProInstance h5ProInstance) {
        this.mInstance = h5ProInstance;
        this.mContext = context;
        init(context);
        compactInit();
    }

    @JavascriptInterface
    public void getUserPrivacy(long j, int i) {
        LogUtil.a(TAG, "getUserPrivacy");
        onSuccessCallback(j, Boolean.valueOf("true".equalsIgnoreCase(gmz.d().c(i))));
    }

    @JavascriptInterface
    public void setUserPrivacy(final long j, String str) {
        LogUtil.a(TAG, "setUserPrivacy");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "setUserPrivacy: param is null");
            onFailureCallback(j, "param is null", -1);
            return;
        }
        if (!TrustListCheckerImpl.isUseSpecialJsApi(this.mInstance)) {
            LogUtil.h(TAG, "setUserPrivacy: unTrust");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            gmz.d().c(jSONObject.optInt("id"), jSONObject.optBoolean("value"), TAG, new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.JsInteractionCompact$$ExternalSyntheticLambda0
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    JsInteractionCompact.this.m724xdbcbbcd6(j, i, obj);
                }
            });
        } catch (JSONException e) {
            LogUtil.b(TAG, "setUserPrivacy: exception -> " + e.getMessage());
            onFailureCallback(j, e.getMessage(), -1);
        }
    }

    /* renamed from: lambda$setUserPrivacy$0$com-huawei-operation-h5pro-jsmodules-JsInteractionCompact, reason: not valid java name */
    /* synthetic */ void m724xdbcbbcd6(long j, int i, Object obj) {
        if (i == 0) {
            onSuccessCallback(j, Integer.valueOf(i));
        } else {
            onFailureCallback(j, "setting failed", i);
        }
    }

    private <T> void onSuccessCallback(long j, T t) {
        H5ProInstance h5ProInstance = this.mInstance;
        if (h5ProInstance == null) {
            LogUtil.h(TAG, "onSuccessCallback: mInstance is null");
        } else {
            h5ProInstance.getJsCbkInvoker().onSuccess(t, j);
        }
    }

    private void onFailureCallback(long j, String str, int i) {
        H5ProInstance h5ProInstance = this.mInstance;
        if (h5ProInstance == null) {
            LogUtil.h(TAG, "onFailureCallback: mInstance is null");
        } else {
            h5ProInstance.getJsCbkInvoker().onFailure(i, str, j);
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        jeg.d().d(strArr, iArr);
    }

    private void compactInit() {
        this.mSendCurrentUrlCallback = new SendCurrentUrlCallback() { // from class: com.huawei.operation.h5pro.jsmodules.JsInteractionCompact.1
            @Override // com.huawei.operation.adapter.SendCurrentUrlCallback
            public String getCurrentUrl() {
                return JsInteractionCompact.this.mInstance.getUrl();
            }

            @Override // com.huawei.operation.adapter.SendCurrentUrlCallback
            public String getWebViewUrl() {
                return JsInteractionCompact.this.mInstance.getUrl();
            }
        };
        this.mOnCaptureCallback = new CaptureShareCompact(this.mContext, this.mInstance);
        this.mJsDataCallback = new JsDataCompact(this.mContext, this.mInstance);
        this.mStartAppSettingPage = new StartAppSettingPageCompact();
        this.mSendServerErrorMsgCallback = new SendServerErrorMsgCallbackCompact(this.mInstance);
        this.mCloseWebCallback = new CloseWebCallbackCompact();
        this.mToastCallback = new ToastCompact(this.mContext);
    }

    @Override // com.huawei.operation.js.JsInteraction, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        this.mContext = null;
        this.mInstance = null;
    }
}
