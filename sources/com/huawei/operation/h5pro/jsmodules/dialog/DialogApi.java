package com.huawei.operation.h5pro.jsmodules.dialog;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import defpackage.nrh;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DialogApi extends JsBaseModule {
    private static final int BUTTON_LIMIT = 2;
    private static final String TAG = "H5PRO_DialogApi";

    @JavascriptInterface
    public void showAlertDialog(final long j, String str) {
        LogUtil.i(TAG, "showAlertDialog: " + str);
        try {
            final JSONObject jSONObject = new JSONObject(str);
            final String optString = jSONObject.optString("message");
            if (TextUtils.isEmpty(optString)) {
                LogUtil.e(TAG, "showAlertDialog params illegal: message is empty");
                onFailureCallback(j, "message is empty");
                return;
            }
            String optString2 = jSONObject.optString("buttons");
            if (TextUtils.isEmpty(optString2)) {
                LogUtil.e(TAG, "showAlertDialog params illegal: no buttons");
                onFailureCallback(j, "no buttons");
                return;
            }
            final String[] split = !TextUtils.isEmpty(optString2) ? optString2.split(",") : new String[0];
            if (split.length > 2) {
                LogUtil.e(TAG, "showAlertDialog params illegal: buttons limit 2");
                onFailureCallback(j, "no buttons");
            } else {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.dialog.DialogApi$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        DialogApi.this.m747x7d9a05db(jSONObject, j, optString, split);
                    }
                });
            }
        } catch (JSONException e) {
            LogUtil.e(TAG, "showAlertDialog JSONException: " + e.getMessage());
            onFailureCallback(j, "parse params exception");
        }
    }

    /* renamed from: lambda$showAlertDialog$0$com-huawei-operation-h5pro-jsmodules-dialog-DialogApi, reason: not valid java name */
    /* synthetic */ void m747x7d9a05db(JSONObject jSONObject, long j, String str, String[] strArr) {
        BaseDialog alertDialog;
        String optString = jSONObject.optString("title");
        if (TextUtils.isEmpty(optString)) {
            alertDialog = getNoTitleAlertDialog(j, str, strArr);
        } else {
            alertDialog = getAlertDialog(j, optString, str, strArr);
        }
        alertDialog.show();
    }

    private BaseDialog getNoTitleAlertDialog(final long j, String str, String[] strArr) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.mContext);
        if (!TextUtils.isEmpty(str)) {
            builder.e(str);
        }
        final int i = 0;
        final int i2 = strArr.length <= 1 ? 0 : 1;
        String str2 = strArr[i2];
        String str3 = strArr.length == 2 ? strArr[0] : "";
        if (!TextUtils.isEmpty(str2)) {
            builder.czE_(str2, new View.OnClickListener() { // from class: com.huawei.operation.h5pro.jsmodules.dialog.DialogApi.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DialogApi.this.onSuccessCallback(j, Integer.valueOf(i2));
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        if (!TextUtils.isEmpty(str3)) {
            builder.czA_(str3, new View.OnClickListener() { // from class: com.huawei.operation.h5pro.jsmodules.dialog.DialogApi.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DialogApi.this.onSuccessCallback(j, Integer.valueOf(i));
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        return builder.e();
    }

    private BaseDialog getAlertDialog(final long j, String str, String str2, String[] strArr) {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.mContext);
        if (!TextUtils.isEmpty(str)) {
            builder.b(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            builder.e(str2);
        }
        final int i = 0;
        final int i2 = strArr.length <= 1 ? 0 : 1;
        String str3 = strArr[i2];
        String str4 = strArr.length == 2 ? strArr[0] : "";
        if (!TextUtils.isEmpty(str3)) {
            builder.cyV_(str3, new View.OnClickListener() { // from class: com.huawei.operation.h5pro.jsmodules.dialog.DialogApi.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DialogApi.this.onSuccessCallback(j, Integer.valueOf(i2));
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        if (!TextUtils.isEmpty(str4)) {
            builder.cyS_(str4, new View.OnClickListener() { // from class: com.huawei.operation.h5pro.jsmodules.dialog.DialogApi.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DialogApi.this.onSuccessCallback(j, Integer.valueOf(i));
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        return builder.a();
    }

    @JavascriptInterface
    public void showToast(long j, String str) {
        LogUtil.i(TAG, "showToast: " + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("message");
            int optInt = jSONObject.optInt("duration", 0);
            if (TextUtils.isEmpty(optString)) {
                onFailureCallback(j, "message cannot be empty");
                return;
            }
            if (optInt == 1) {
                nrh.c(this.mContext, optString);
            } else {
                nrh.d(this.mContext, optString);
            }
            onSuccessCallback(j);
        } catch (JSONException unused) {
            onFailureCallback(j, "parse params exception");
        }
    }
}
