package com.huawei.ui.thirdpartservice.syncdata.oauth;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.ThirdOauthTokenI;
import com.huawei.hwcloudmodel.model.ThirdOauthTokenO;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.webview.WebViewActivity;
import com.huawei.ui.thirdpartservice.syncdata.callback.PerformOauthCallback;
import com.huawei.ui.thirdpartservice.syncdata.callback.RefreshTokenCallback;
import com.huawei.ui.thirdpartservice.syncdata.callback.RevokeOauthCallback;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager;
import defpackage.ixx;
import defpackage.jbs;
import defpackage.nsn;
import health.compact.a.KeyValDbManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.HashMap;

/* loaded from: classes7.dex */
public abstract class OauthManager {
    private static final String DEFAULT_OAUTH_TIME = "-1";
    private static final String KEY_LAST_HUID = "key_last_huid";
    private String mAccessToken;
    private String mOpenId;

    protected abstract String getConnectTimeKey();

    protected abstract String getModuleId();

    protected abstract int getThirdAccountType();

    public OauthManager() {
        String e = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), getModuleId(), KEY_LAST_HUID);
        if (TextUtils.isEmpty(b) || !b.equals(e)) {
            saveOauthTime(Long.parseLong("-1"));
            SharedPreferenceManager.e(BaseApplication.getContext(), getModuleId(), KEY_LAST_HUID, e, (StorageParams) null);
        }
    }

    public static void openOauthPage(final Context context, String str) {
        if (!isChromeInstall()) {
            jumpToWebview(context, str);
            return;
        }
        try {
            final Intent intent = new Intent();
            intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
            intent.setData(Uri.parse(str));
            intent.setClassName("com.android.chrome", "com.google.android.apps.chrome.Main");
            nsn.cLO_("com.android.chrome", context, context.getString(R.string._2130847432_res_0x7f0226c8), new View.OnClickListener() { // from class: sjb
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OauthManager.lambda$openOauthPage$0(context, intent, view);
                }
            }, null);
        } catch (ActivityNotFoundException unused) {
            Intent intent2 = new Intent();
            intent2.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
            intent2.setData(Uri.parse(str));
            jumpToBrowser(context, intent2);
        }
    }

    public static /* synthetic */ void lambda$openOauthPage$0(Context context, Intent intent, View view) {
        context.startActivity(intent);
        ViewClickInstrumentation.clickOnView(view);
    }

    private static void jumpToBrowser(final Context context, final Intent intent) {
        ResolveInfo resolveActivity;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null || (resolveActivity = packageManager.resolveActivity(intent, 65536)) == null) {
            return;
        }
        intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
        nsn.cLO_(resolveActivity.activityInfo.packageName, context, context.getString(R.string._2130847432_res_0x7f0226c8), new View.OnClickListener() { // from class: sja
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OauthManager.lambda$jumpToBrowser$1(context, intent, view);
            }
        }, null);
    }

    public static /* synthetic */ void lambda$jumpToBrowser$1(Context context, Intent intent, View view) {
        context.startActivity(intent);
        ViewClickInstrumentation.clickOnView(view);
    }

    private static void jumpToWebview(Context context, String str) {
        Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
        intent.putExtra("WebViewActivity.REQUEST_URL_KEY", str);
        intent.putExtra("WebViewActivity.HANDLE_REDIRECT", true);
        intent.putExtra("WebViewActivity.TITLE", context.getString(R.string._2130844406_res_0x7f021af6));
        context.startActivity(intent);
    }

    private static boolean isChromeInstall() {
        return Utils.c(com.huawei.haf.application.BaseApplication.e(), "com.android.chrome");
    }

    public void checkConnectStatus(RefreshTokenCallback refreshTokenCallback) {
        checkConnectStatus(false, refreshTokenCallback);
    }

    public void refreshConnectStatus() {
        checkConnectStatus(true, null);
    }

    public void checkConnectStatus(boolean z, RefreshTokenCallback refreshTokenCallback) {
        LogUtil.a("OauthManager", "  checkConnectStatus   called");
        if (z || refreshTokenCallback == null) {
            refreshAccessToken(refreshTokenCallback);
            return;
        }
        long oauthTime = getOauthTime();
        if (oauthTime == 0) {
            refreshTokenCallback.refreshResult(true, false);
        } else if (oauthTime > 0) {
            refreshTokenCallback.refreshResult(true, true);
        } else {
            refreshAccessToken(refreshTokenCallback);
        }
    }

    public String getOpenId() {
        return this.mOpenId;
    }

    private void recordOauthBiEvent(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("status", str);
        hashMap.put(SyncDataConstant.BI_KEY_ACCOUNT_TYPE, getAccountTypeName());
        LogUtil.a(getClass().getSimpleName(), AnalyticsValue.THIRD_OAUTH_STATUS_2041095.value(), str);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.THIRD_OAUTH_STATUS_2041095.value(), hashMap, 0);
    }

    private String getAccountTypeName() {
        switch (getThirdAccountType()) {
            case 25:
                return SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_RUNTASTIC;
            case 26:
                return SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_KOMMOT;
            case 27:
                return SyncDataConstant.BI_VALUE_ACCOUNT_TYPE_STRAVA;
            default:
                return "";
        }
    }

    public void refreshAccessToken(final RefreshTokenCallback refreshTokenCallback) {
        ThirdOauthTokenO thirdOauthTokenO = new ThirdOauthTokenO();
        thirdOauthTokenO.setThirdAccountType(getThirdAccountType());
        thirdOauthTokenO.setThirdTokenType(2);
        jbs.a(BaseApplication.getContext()).a(thirdOauthTokenO, new ICloudOperationResult() { // from class: sjc
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                OauthManager.this.m870x32b8c820(refreshTokenCallback, (ThirdOauthTokenI) obj, str, z);
            }
        });
    }

    /* renamed from: lambda$refreshAccessToken$2$com-huawei-ui-thirdpartservice-syncdata-oauth-OauthManager, reason: not valid java name */
    public /* synthetic */ void m870x32b8c820(RefreshTokenCallback refreshTokenCallback, ThirdOauthTokenI thirdOauthTokenI, String str, boolean z) {
        if (refreshTokenCallback == null) {
            return;
        }
        if (thirdOauthTokenI == null) {
            refreshTokenCallback.refreshResult(false, false);
            return;
        }
        if (!z || thirdOauthTokenI.getThirdAuthToken() == null || TextUtils.isEmpty(thirdOauthTokenI.getThirdAuthToken().getThirdToken())) {
            revokeOauthTime();
            refreshTokenCallback.refreshResult(true, false);
        } else {
            setAccessToken(thirdOauthTokenI.getThirdAuthToken().getThirdToken());
            setOpenId(thirdOauthTokenI.getThirdAuthToken().getOpenId());
            saveOauthTime(thirdOauthTokenI.getThirdAuthToken().getCreateTime());
            refreshTokenCallback.refreshResult(true, true);
        }
    }

    public void revokeOauth(final RevokeOauthCallback revokeOauthCallback) {
        jbs.a(BaseApplication.getContext()).d(getThirdAccountType(), new ICloudOperationResult() { // from class: siy
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                OauthManager.this.m871x24b44762(revokeOauthCallback, (CloudCommonReponse) obj, str, z);
            }
        });
    }

    /* renamed from: lambda$revokeOauth$3$com-huawei-ui-thirdpartservice-syncdata-oauth-OauthManager, reason: not valid java name */
    public /* synthetic */ void m871x24b44762(RevokeOauthCallback revokeOauthCallback, CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        if (cloudCommonReponse == null) {
            revokeOauthCallback.revokeResult(false, false, str);
        } else {
            if (z) {
                revokeOauthTime();
                recordOauthBiEvent("cancel");
                revokeOauthCallback.revokeResult(true, true, "");
                return;
            }
            revokeOauthCallback.revokeResult(true, false, str);
        }
    }

    public void performOauth(String str, final PerformOauthCallback performOauthCallback) {
        ThirdOauthTokenO thirdOauthTokenO = new ThirdOauthTokenO();
        thirdOauthTokenO.setThirdAccountType(getThirdAccountType());
        thirdOauthTokenO.setThirdTokenType(3);
        thirdOauthTokenO.setThirdToken(str);
        jbs.a(BaseApplication.getContext()).e(thirdOauthTokenO, new ICloudOperationResult() { // from class: siz
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str2, boolean z) {
                OauthManager.this.m869xd80b7002(performOauthCallback, (ThirdOauthTokenI) obj, str2, z);
            }
        });
    }

    /* renamed from: lambda$performOauth$4$com-huawei-ui-thirdpartservice-syncdata-oauth-OauthManager, reason: not valid java name */
    public /* synthetic */ void m869xd80b7002(PerformOauthCallback performOauthCallback, ThirdOauthTokenI thirdOauthTokenI, String str, boolean z) {
        if (thirdOauthTokenI == null) {
            performOauthCallback.oauthResult(false, false, 0L, str);
            return;
        }
        if (!z) {
            performOauthCallback.oauthResult(true, false, 0L, str);
            return;
        }
        if (thirdOauthTokenI.getThirdAuthToken() == null || TextUtils.isEmpty(thirdOauthTokenI.getThirdAuthToken().getThirdToken())) {
            performOauthCallback.oauthResult(true, false, 0L, "");
            return;
        }
        setAccessToken(thirdOauthTokenI.getThirdAuthToken().getThirdToken());
        setOpenId(thirdOauthTokenI.getThirdAuthToken().getOpenId());
        saveOauthTime(thirdOauthTokenI.getThirdAuthToken().getCreateTime());
        recordOauthBiEvent(SyncDataConstant.BI_VALUE_OAUTH_STATUS_OAUTH);
        performOauthCallback.oauthResult(true, true, thirdOauthTokenI.getThirdAuthToken().getCreateTime(), "");
    }

    private void setAccessToken(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.mAccessToken = str;
    }

    private void setOpenId(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.mOpenId = str;
    }

    public long getOauthTime() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), getModuleId(), getConnectTimeKey());
        LogUtil.a("OauthManager", b);
        try {
            if (TextUtils.isEmpty(b)) {
                b = "-1";
            }
            return Long.parseLong(b);
        } catch (NumberFormatException e) {
            LogUtil.b("OauthManager", e.getMessage());
            return -1L;
        }
    }

    public String getAccessToken() {
        return TextUtils.isEmpty(this.mAccessToken) ? "" : this.mAccessToken;
    }

    private void saveOauthTime(long j) {
        SharedPreferenceManager.e(BaseApplication.getContext(), getModuleId(), getConnectTimeKey(), Long.toString(j), (StorageParams) null);
    }

    private void revokeOauthTime() {
        SharedPreferenceManager.e(BaseApplication.getContext(), getModuleId(), getConnectTimeKey(), "0", (StorageParams) null);
    }
}
