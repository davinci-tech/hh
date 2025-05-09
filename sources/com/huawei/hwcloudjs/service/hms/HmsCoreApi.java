package com.huawei.hwcloudjs.service.hms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.webkit.WebView;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.api.HuaweiApiClientImpl;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.huawei.hms.support.api.entity.auth.PermissionInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.api.hwid.SignInResult;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hms.support.hwid.HuaweiIdAuthAPIManager;
import com.huawei.hms.support.hwid.HuaweiIdAuthManager;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParamsHelper;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import com.huawei.hms.support.hwid.service.HuaweiIdAuthService;
import com.huawei.hwcloudjs.annotation.JSMethod;
import com.huawei.hwcloudjs.api.JsParam;
import com.huawei.hwcloudjs.core.JSRequest;
import com.huawei.hwcloudjs.core.JsCallback;
import com.huawei.hwcloudjs.service.hms.a;
import com.huawei.hwcloudjs.service.hms.k;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HmsCoreApi extends JSRequest implements com.huawei.hwcloudjs.core.c {
    private static final String b = "HmsCoreApi";
    private static final int c = 9001;
    private static final int d = 9002;
    private static final int e = 9005;

    @JSMethod(isOpen = true, name = "silentSignIn")
    public void silentSignIn(LoginReq loginReq, JsCallback jsCallback) {
        com.huawei.hwcloudjs.f.d.c(b, "silentSignIn begin", true);
        WebView webView = jsCallback.getWebView();
        if (!a(loginReq, jsCallback, webView)) {
            com.huawei.hwcloudjs.f.d.b(b, "silentSignIn checkparam false", true);
            return;
        }
        if (!(webView.getContext() instanceof Activity)) {
            com.huawei.hwcloudjs.f.d.b(b, "webView is error", true);
            return;
        }
        try {
            Task<AuthHuaweiId> silentSignIn = a(loginReq, (Activity) webView.getContext()).silentSignIn();
            if (silentSignIn != null) {
                com.huawei.hwcloudjs.f.d.c(b, "silentSignIn task is not null", true);
                if (silentSignIn.isSuccessful()) {
                    a(silentSignIn.getResult(), loginReq.needAuthCode, jsCallback);
                    return;
                } else {
                    silentSignIn.addOnCompleteListener(new c(this, loginReq, jsCallback));
                    return;
                }
            }
            com.huawei.hwcloudjs.f.d.b(b, "silentSignIn failed, task is null", true);
            jsCallback.failure("silentSignIn failed, task is null");
        } catch (ApiException unused) {
            com.huawei.hwcloudjs.f.d.b(b, "silentSignIn appid is not correct", true);
            jsCallback.failure("appid is not correct");
        }
    }

    @JSMethod(isOpen = true, name = "signOut")
    public void signOut(JsParam jsParam, JsCallback jsCallback) {
        com.huawei.hwcloudjs.f.d.c(b, "signOut begin", true);
        String appId = jsParam.getAppId();
        String bridgeId = jsParam.getBridgeId();
        HuaweiIdAuthService a2 = r.a().a(bridgeId, appId);
        HuaweiApiClient b2 = r.a().b(bridgeId, appId);
        if (a2 == null && b2 == null) {
            com.huawei.hwcloudjs.f.d.b(b, "signOut failed, HuaweiIdAuthService is null", true);
            jsCallback.failure("signOut failed, please invoke signIn/getSignInIntent/silentSignIn first");
        } else if (a2 != null) {
            com.huawei.hwcloudjs.f.d.c(b, "signOut service is not null", true);
            a2.signOut().addOnCompleteListener(new f(this, jsCallback));
        } else {
            com.huawei.hwcloudjs.f.d.c(b, "signOut service is null", true);
            HuaweiIdAuthAPIManager.HuaweiIdAuthAPIService.signOut(b2).setResultCallback(new g(this, jsCallback));
        }
    }

    @JSMethod(isOpen = true, name = "signIn")
    public void signIn(LoginReq loginReq, JsCallback jsCallback) {
        WebView webView = jsCallback.getWebView();
        boolean z = true;
        if (!a(loginReq, jsCallback, webView)) {
            com.huawei.hwcloudjs.f.d.b(b, "signIn checkparam false", true);
            return;
        }
        if (!(webView.getContext() instanceof Activity)) {
            com.huawei.hwcloudjs.f.d.b(b, "webView is error", true);
            jsCallback.failure("webView is error");
            return;
        }
        String appId = loginReq.getAppId();
        String bridgeId = loginReq.getBridgeId();
        List<Scope> a2 = a(loginReq.scope);
        boolean isEmpty = a2.isEmpty();
        boolean z2 = loginReq.needAuthCode;
        try {
            a(loginReq, (Activity) webView.getContext());
            HuaweiApiClient b2 = r.a().b(bridgeId, appId);
            boolean z3 = false;
            if (b2 == null || !(b2 instanceof HuaweiApiClientImpl)) {
                z = false;
            } else {
                HuaweiApiClientImpl huaweiApiClientImpl = (HuaweiApiClientImpl) b2;
                boolean a3 = k.a(a2, huaweiApiClientImpl.getScopes());
                List<PermissionInfo> permissionInfos = huaweiApiClientImpl.getPermissionInfos();
                if (permissionInfos != null) {
                    for (PermissionInfo permissionInfo : permissionInfos) {
                        if (permissionInfo != null && "https://www.huawei.com/auth/account/base.profile/serviceauthcode".equals(permissionInfo.getPermission())) {
                            break;
                        }
                    }
                }
                z = false;
                z3 = a3;
            }
            if (b2 == null || !z3 || z != z2) {
                if (b2 != null) {
                    b2.disconnect();
                }
                b2 = a(webView.getContext().getApplicationContext(), z2, a2);
                b2.setSubAppInfo(new SubAppInfo(appId));
                r.a().a(bridgeId, appId, b2);
            }
            Activity activity = (Activity) webView.getContext();
            WeakReference<HuaweiApiClient> weakReference = new WeakReference<>(b2);
            WeakReference<Activity> weakReference2 = new WeakReference<>(activity);
            if (b2.isConnected()) {
                a(weakReference, jsCallback, weakReference2, z2, isEmpty);
                return;
            }
            b2.setConnectionCallbacks(new h(this, weakReference, jsCallback, weakReference2, z2, isEmpty));
            b2.setConnectionFailedListener(new k.b(weakReference, jsCallback, weakReference2));
            b2.connect(activity);
        } catch (ApiException unused) {
            com.huawei.hwcloudjs.f.d.b(b, "appid is not correct", true);
            jsCallback.failure("appid is not correct");
        }
    }

    @Override // com.huawei.hwcloudjs.core.c
    public void onDestroy(String str) {
        r.a().b(str);
        r.a().a(str);
    }

    @Override // com.huawei.hwcloudjs.core.c
    public void onCompletedConfig(Context context, String str, String str2, String str3, JSONObject jSONObject) {
        String str4;
        boolean z = false;
        if (jSONObject != null) {
            str4 = jSONObject.optString("scope", null);
            z = jSONObject.optBoolean("needAuthCode", false);
        } else {
            str4 = null;
        }
        List<Scope> a2 = a(str4);
        if (a2.isEmpty()) {
            return;
        }
        HuaweiApiClient a3 = a(context, z, a2);
        a3.setSubAppInfo(new SubAppInfo(str));
        if (context instanceof Activity) {
            a3.connect((Activity) context);
        } else {
            a3.connect((Activity) null);
        }
        r.a().a(str3, str, a3);
    }

    @JSMethod(isOpen = true, name = "getSignInIntent")
    public void getSignInIntent(LoginReq loginReq, JsCallback jsCallback) {
        com.huawei.hwcloudjs.f.d.c(b, "getSignInIntent begin", true);
        WebView webView = jsCallback.getWebView();
        Activity activity = (Activity) webView.getContext();
        if (!a(loginReq, jsCallback, webView)) {
            com.huawei.hwcloudjs.f.d.b(b, "getSignInIntent checkparam false", true);
            return;
        }
        try {
            com.huawei.hwcloudjs.service.hms.a.a().a(activity, a(loginReq, activity).getSignInIntent(), 9005, new b(this, loginReq, jsCallback));
        } catch (ApiException unused) {
            com.huawei.hwcloudjs.f.d.b(b, "appid is not correct", true);
            jsCallback.failure("appid is not correct");
        }
    }

    @Override // com.huawei.hwcloudjs.core.c
    public String getApi() {
        return com.huawei.hwcloudjs.g.a.c;
    }

    @JSMethod(isOpen = true, name = "cancelAuthorization")
    public void cancelAuthorization(JsParam jsParam, JsCallback jsCallback) {
        com.huawei.hwcloudjs.f.d.c(b, "cancelAuthorization begin", true);
        String appId = jsParam.getAppId();
        String bridgeId = jsParam.getBridgeId();
        HuaweiIdAuthService a2 = r.a().a(bridgeId, appId);
        HuaweiApiClient b2 = r.a().b(bridgeId, appId);
        if (a2 == null && b2 == null) {
            com.huawei.hwcloudjs.f.d.b(b, "cancelAuthorization failed, HuaweiIdAuthService is null", true);
            jsCallback.failure("cancelAuthorization failed, please invoke signIn/getSignInIntent/silentSignIn first");
        } else if (a2 != null) {
            com.huawei.hwcloudjs.f.d.c(b, "cancelAuthorization service is not null", true);
            a2.cancelAuthorization().addOnCompleteListener(new d(this, jsCallback));
        } else {
            com.huawei.hwcloudjs.f.d.c(b, "cancelAuthorization service is null", true);
            HuaweiIdAuthAPIManager.HuaweiIdAuthAPIService.cancelAuthorization(b2).setResultCallback(new e(this, jsCallback));
        }
    }

    private boolean a(LoginReq loginReq, JsCallback jsCallback, WebView webView) {
        if (webView == null) {
            com.huawei.hwcloudjs.f.d.b(b, "signIn webview is null", true);
            jsCallback.failure("webview is null");
            return false;
        }
        if (loginReq == null) {
            com.huawei.hwcloudjs.f.d.b(b, "signIn JS_RET_CODE_PARSE_PARAM_ERROR", true);
            jsCallback.failure(13);
            return false;
        }
        if (loginReq.getAppId() != null && !loginReq.getAppId().isEmpty()) {
            return true;
        }
        com.huawei.hwcloudjs.f.d.b(b, "appid is not config", true);
        jsCallback.failure("appid is not config");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(WeakReference<HuaweiApiClient> weakReference, JsCallback jsCallback, WeakReference<Activity> weakReference2, boolean z, boolean z2) {
        PendingResult<SignInResult> signIn;
        com.huawei.hwcloudjs.f.d.c(b, "signIn begin", true);
        HuaweiApiClient huaweiApiClient = weakReference.get();
        if (huaweiApiClient == null) {
            com.huawei.hwcloudjs.f.d.b(b, "client is null", true);
            jsCallback.failure();
            return;
        }
        if (z2) {
            signIn = HuaweiIdAuthAPIManager.HuaweiIdAuthAPIService.signInBackend(huaweiApiClient);
        } else {
            Activity activity = weakReference2.get();
            if (activity == null) {
                com.huawei.hwcloudjs.f.d.b(b, "signIn with null activity", true);
                jsCallback.failure();
                return;
            }
            signIn = HuaweiIdAuthAPIManager.HuaweiIdAuthAPIService.signIn(activity, huaweiApiClient);
        }
        signIn.setResultCallback(new i(this, z, weakReference2, weakReference, z2, jsCallback));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(a.b bVar, WeakReference<HuaweiApiClient> weakReference, JsCallback jsCallback, WeakReference<Activity> weakReference2, boolean z, boolean z2) {
        if (bVar.c() == -1) {
            com.huawei.hwcloudjs.f.d.c(b, "signIn Activity.RESULT_OK", true);
            a(weakReference, jsCallback, weakReference2, z, z2);
        } else {
            com.huawei.hwcloudjs.f.d.b(b, "signIn JS_RET_CODE_USER_DENAL", true);
            jsCallback.failure(1);
        }
    }

    /* loaded from: classes9.dex */
    public static final class LoginReq extends JsParam {
        private boolean needAuthCode = false;
        private boolean needIdToken = false;
        private String scope;

        public void setneedAuthCode(boolean z) {
            this.needAuthCode = z;
        }

        public void setScope(String str) {
            this.scope = str;
        }

        public boolean getneedAuthCode() {
            return this.needAuthCode;
        }

        public String getScope() {
            return this.scope;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AuthHuaweiId authHuaweiId, boolean z, JsCallback jsCallback) {
        String familyName;
        String str;
        try {
            JSONObject jSONObject = new JSONObject();
            if (z) {
                familyName = authHuaweiId.getAuthorizationCode();
                str = "serverAuthCode";
            } else {
                jSONObject.put("openId", authHuaweiId.getOpenId());
                jSONObject.put("accessToken", authHuaweiId.getAccessToken());
                jSONObject.put(CommonConstant.KEY_DISPLAY_NAME, authHuaweiId.getDisplayName());
                jSONObject.put("photoUrl", authHuaweiId.getAvatarUriString());
                jSONObject.put(CommonConstant.KEY_ID_TOKEN, authHuaweiId.getIdToken());
                jSONObject.put(CommonConstant.KEY_UNION_ID, authHuaweiId.getUnionId());
                jSONObject.put("email", authHuaweiId.getEmail());
                jSONObject.put(CommonConstant.KEY_GIVEN_NAME, authHuaweiId.getGivenName());
                familyName = authHuaweiId.getFamilyName();
                str = CommonConstant.KEY_FAMILY_NAME;
            }
            jSONObject.put(str, familyName);
            jsCallback.success(jSONObject.toString());
        } catch (JSONException unused) {
            com.huawei.hwcloudjs.f.d.b(b, "handleAuthHuaweiId JSONException", true);
            jsCallback.failure();
        }
    }

    private void a(SignInResult signInResult, boolean z, WeakReference<Activity> weakReference, WeakReference<HuaweiApiClient> weakReference2, boolean z2, JsCallback jsCallback, Activity activity) {
        com.huawei.hwcloudjs.service.hms.a.a().a(activity, signInResult.getData(), 9001, new j(this, weakReference2, jsCallback, weakReference, z, z2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(SignInResult signInResult, boolean z, WeakReference<Activity> weakReference, WeakReference<HuaweiApiClient> weakReference2, boolean z2, JsCallback jsCallback) {
        if (signInResult != null) {
            com.huawei.hwcloudjs.f.d.c(b, "signIn result is not null", true);
            if (signInResult.isSuccess()) {
                com.huawei.hwcloudjs.f.d.c(b, "signIn result isSuccess", true);
                jsCallback.success(k.a(signInResult, z));
                return;
            }
            int statusCode = signInResult.getStatus().getStatusCode();
            com.huawei.hwcloudjs.f.d.c(b, "signIn result statusCode:" + statusCode, true);
            if (statusCode == 2001 || statusCode == 2004) {
                Activity activity = weakReference.get();
                if (activity != null) {
                    a(signInResult, z, weakReference, weakReference2, z2, jsCallback, activity);
                    return;
                }
                com.huawei.hwcloudjs.f.d.b(b, "signIn webViewActivity == null", true);
            } else {
                if (statusCode != 2002) {
                    jsCallback.failure(signInResult.getStatus().getStatusCode());
                    return;
                }
                com.huawei.hwcloudjs.f.d.c(b, "signIn SIGN_IN_AUTH", true);
                Activity activity2 = weakReference.get();
                if (activity2 != null) {
                    com.huawei.hwcloudjs.service.hms.a.a().a(activity2, signInResult.getData(), 9002, new a(jsCallback, z));
                    return;
                }
                com.huawei.hwcloudjs.f.d.c(b, "webViewActivity is null", true);
            }
            jsCallback.failure();
            return;
        }
        com.huawei.hwcloudjs.f.d.b(b, "signIn failed, result is null", true);
        jsCallback.failure("signIn failed, result is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ApiException apiException, JsCallback jsCallback) {
        com.huawei.hwcloudjs.f.d.b(b, "silentSignIn failed, statusCode:" + apiException.getStatusCode() + " msg:" + apiException.getStatusMessage(), true);
        String statusMessage = apiException.getStatusMessage();
        if (apiException.getStatusCode() == 2002) {
            statusMessage = "silentSignIn failed, please invoke signIn/getSignInIntent first";
        }
        jsCallback.failure(apiException.getStatusCode(), statusMessage);
    }

    private static List<Scope> a(String str) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            com.huawei.hwcloudjs.f.d.c(b, "parserScope scopeEmpty", true);
        } else {
            for (String str2 : str.split("\\,")) {
                if (!TextUtils.isEmpty(str2)) {
                    arrayList.add(new Scope(str2));
                }
            }
        }
        return arrayList;
    }

    /* loaded from: classes9.dex */
    static final class a implements a.c {

        /* renamed from: a, reason: collision with root package name */
        private JsCallback f6231a;
        private boolean b;

        @Override // com.huawei.hwcloudjs.service.hms.a.c
        public void a(a.b bVar) {
            JsCallback jsCallback;
            String str;
            Intent a2 = bVar.a();
            if (a2 != null) {
                SignInResult hwIdSignInResultFromIntent = HuaweiIdAuthAPIManager.HuaweiIdAuthAPIService.getHwIdSignInResultFromIntent(a2);
                if (hwIdSignInResultFromIntent != null) {
                    if (hwIdSignInResultFromIntent.isSuccess()) {
                        com.huawei.hwcloudjs.f.d.c(HmsCoreApi.b, "signIn onResult isSuccess", true);
                        this.f6231a.success(k.a(hwIdSignInResultFromIntent, this.b));
                        return;
                    } else {
                        com.huawei.hwcloudjs.f.d.b(HmsCoreApi.b, "signIn onResult" + hwIdSignInResultFromIntent.getStatus().getStatusCode(), true);
                        this.f6231a.failure(hwIdSignInResultFromIntent.getStatus().getStatusCode());
                        return;
                    }
                }
                com.huawei.hwcloudjs.f.d.b(HmsCoreApi.b, "signIn onResult signInResult is null", true);
                jsCallback = this.f6231a;
                str = "signInResult is null";
            } else if (bVar.c() != -1) {
                com.huawei.hwcloudjs.f.d.b(HmsCoreApi.b, "signIn onResult JS_RET_CODE_USER_DENAL", true);
                this.f6231a.failure(1);
                return;
            } else {
                com.huawei.hwcloudjs.f.d.b(HmsCoreApi.b, "signIn onResult hms not ret data", true);
                jsCallback = this.f6231a;
                str = "hms not ret data";
            }
            jsCallback.failure(str);
        }

        public a(JsCallback jsCallback, boolean z) {
            this.f6231a = jsCallback;
            this.b = z;
        }
    }

    private HuaweiIdAuthService a(LoginReq loginReq, Activity activity) throws ApiException {
        String appId = loginReq.getAppId();
        String bridgeId = loginReq.getBridgeId();
        List<Scope> a2 = a(loginReq.scope);
        com.huawei.hwcloudjs.f.d.c(b, "getAuthService appId:" + appId, false);
        com.huawei.hwcloudjs.f.d.c(b, "getAuthService bridgeId:" + bridgeId, false);
        com.huawei.hwcloudjs.f.d.c(b, "getAuthService scopeList:" + a2, false);
        HuaweiIdAuthParamsHelper scopeList = new HuaweiIdAuthParamsHelper(HuaweiIdAuthParams.DEFAULT_AUTH_REQUEST_PARAM).setScopeList(a2);
        if (loginReq.needAuthCode) {
            scopeList.setAuthorizationCode();
        } else {
            scopeList.setAccessToken().setId();
        }
        if (loginReq.needIdToken) {
            scopeList.setIdToken();
        }
        HuaweiIdAuthService service = HuaweiIdAuthManager.getService(activity, scopeList.createParams());
        service.setSubAppId(appId);
        r.a().a(bridgeId, appId, service);
        return service;
    }

    private HuaweiApiClient a(Context context, boolean z, List<Scope> list) {
        HuaweiIdAuthParamsHelper huaweiIdAuthParamsHelper = new HuaweiIdAuthParamsHelper(HuaweiIdAuthParams.DEFAULT_AUTH_REQUEST_PARAM);
        if (z) {
            huaweiIdAuthParamsHelper.setAuthorizationCode();
        } else {
            huaweiIdAuthParamsHelper.setAccessToken().setId();
        }
        HuaweiApiClient.Builder builder = new HuaweiApiClient.Builder(context.getApplicationContext());
        builder.addApi(HuaweiIdAuthAPIManager.HUAWEI_OAUTH_API, huaweiIdAuthParamsHelper.createParams());
        if (list != null) {
            Iterator<Scope> it = list.iterator();
            while (it.hasNext()) {
                builder.addScope(it.next());
            }
        }
        return builder.build();
    }
}
