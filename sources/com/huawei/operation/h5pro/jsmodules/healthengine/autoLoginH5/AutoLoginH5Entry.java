package com.huawei.operation.h5pro.jsmodules.healthengine.autoLoginH5;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import defpackage.jbc;
import defpackage.nsn;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AutoLoginH5Entry extends JsBaseModule {
    private static final String RELEASE_TAG = "R_H5PRO_AutoLoginH5Entry";
    private static final String TAG = "H5PRO_AutoLoginH5Entry";

    @JavascriptInterface
    public void returnMessageToThirdApp(long j, String str) {
        LogUtil.i(TAG, "returnMessageToThirdApp");
        if (TextUtils.isEmpty(str)) {
            LogUtil.w(TAG, " params is null");
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.healthengine.autoLoginH5.AutoLoginH5Entry$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AutoLoginH5Entry.lambda$returnMessageToThirdApp$0();
            }
        });
        try {
            String e = SharedPreferenceManager.e("HealthKit", "thirdUrlScheme", "");
            if (TextUtils.isEmpty(e)) {
                ReleaseLogUtil.c(RELEASE_TAG, "returnMessageToThirdApp, thirdScheme is empty");
                onFailureCallback(j, "returnMessageToThirdApp, thirdScheme is empty");
            }
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("code");
            Uri.Builder buildUpon = Uri.parse(e).buildUpon();
            if (!TextUtils.isEmpty(optString)) {
                ReleaseLogUtil.e(RELEASE_TAG, "returnMessageToThirdApp, auth success");
                buildUpon.appendQueryParameter("code", optString);
            } else {
                ReleaseLogUtil.e(RELEASE_TAG, "returnMessageToThirdApp, auth fail");
                buildUpon.appendQueryParameter("errorCode", String.valueOf(219));
            }
            String optString2 = jSONObject.optString("state");
            if (!TextUtils.isEmpty(optString2)) {
                buildUpon.appendQueryParameter("state", optString2);
            }
            String uri = buildUpon.build().toString();
            LogUtil.i(TAG, "returnMessageToThirdApp, response thirdUrl: ", uri);
            final Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(uri));
            intent.setFlags(268435456);
            if (this.mContext == null) {
                LogUtil.w(TAG, "context is null");
                return;
            }
            ResolveInfo resolveActivity = this.mContext.getPackageManager().resolveActivity(intent, 65536);
            if (resolveActivity != null) {
                nsn.cLO_(resolveActivity.activityInfo.packageName, this.mContext, resolveActivity.loadLabel(this.mContext.getPackageManager()).toString(), new View.OnClickListener() { // from class: com.huawei.operation.h5pro.jsmodules.healthengine.autoLoginH5.AutoLoginH5Entry$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AutoLoginH5Entry.this.m748x1bb4112c(intent, view);
                    }
                }, new View.OnClickListener() { // from class: com.huawei.operation.h5pro.jsmodules.healthengine.autoLoginH5.AutoLoginH5Entry$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AutoLoginH5Entry.this.m749xf9a7770b(view);
                    }
                });
                onSuccessCallback(j);
                return;
            }
            LogUtil.e(TAG, "Activity not found");
            onFailureCallback(j, "Activity not found");
            if (this.mContext instanceof Activity) {
                ((Activity) this.mContext).finish();
            }
        } catch (JSONException e2) {
            LogUtil.e(TAG, "JSONException -> " + e2.getMessage());
            onFailureCallback(j, "Param parse fails");
            if (this.mContext instanceof Activity) {
                ((Activity) this.mContext).finish();
            }
        }
    }

    static /* synthetic */ void lambda$returnMessageToThirdApp$0() {
        String e = SharedPreferenceManager.e("HealthKit", "clientId", "");
        if (TextUtils.isEmpty(e)) {
            ReleaseLogUtil.d(RELEASE_TAG, "returnMessageToThirdApp, clientId is empty");
        } else {
            jbc.d(e);
        }
    }

    /* renamed from: lambda$returnMessageToThirdApp$1$com-huawei-operation-h5pro-jsmodules-healthengine-autoLoginH5-AutoLoginH5Entry, reason: not valid java name */
    /* synthetic */ void m748x1bb4112c(Intent intent, View view) {
        nsn.cLR_(this.mContext, intent);
        if (this.mContext instanceof Activity) {
            ((Activity) this.mContext).finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$returnMessageToThirdApp$2$com-huawei-operation-h5pro-jsmodules-healthengine-autoLoginH5-AutoLoginH5Entry, reason: not valid java name */
    /* synthetic */ void m749xf9a7770b(View view) {
        if (this.mContext instanceof Activity) {
            ((Activity) this.mContext).finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }
}
