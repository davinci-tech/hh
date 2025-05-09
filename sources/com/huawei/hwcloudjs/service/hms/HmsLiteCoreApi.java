package com.huawei.hwcloudjs.service.hms;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebView;
import com.huawei.hwcloudjs.JsClientApi;
import com.huawei.hwcloudjs.annotation.JSMethod;
import com.huawei.hwcloudjs.api.IAccessToken;
import com.huawei.hwcloudjs.api.JsParam;
import com.huawei.hwcloudjs.core.JSRequest;
import com.huawei.hwcloudjs.core.JsCallback;
import defpackage.kqi;
import defpackage.kqj;
import defpackage.kqk;
import defpackage.kqo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HmsLiteCoreApi extends JSRequest {
    private static final String b = "HmsLiteCoreApi";
    private static final long c = 5000;
    private static final int d = 4;
    public static String mGrsAppName = "";
    private IAccessToken e;
    private volatile boolean f = false;
    private Handler g = new Handler(new n(this));

    public static final class LoginLiteReq extends JsParam {
        private String IMEI;
        private String SN;
        private String UDID;
        private String UUID;
        private String redirectUrl;
        private String scope;
        private boolean needAuthCode = false;
        private boolean guideLogin = true;

        public void setUUID(String str) {
            this.UUID = str;
        }

        public void setUDID(String str) {
            this.UDID = str;
        }

        public void setScope(String str) {
            this.scope = str;
        }

        public void setSN(String str) {
            this.SN = str;
        }

        public void setRedirectUrl(String str) {
            this.redirectUrl = str;
        }

        public void setNeedAuthCode(boolean z) {
            this.needAuthCode = z;
        }

        public void setIMEI(String str) {
            this.IMEI = str;
        }

        public void setGuideLogin(boolean z) {
            this.guideLogin = z;
        }

        public String getUUID() {
            return this.UUID;
        }

        public String getUDID() {
            return this.UDID;
        }

        public String getScope() {
            return this.scope;
        }

        public String getSN() {
            return this.SN;
        }

        public String getRedirectUrl() {
            return this.redirectUrl;
        }

        public boolean getNeedAuthCode() {
            return this.needAuthCode;
        }

        public String getIMEI() {
            return this.IMEI;
        }

        public boolean getGuideLogin() {
            return this.guideLogin;
        }
    }

    @JSMethod(isOpen = true, name = "signOut")
    public void signOut(JsParam jsParam, JsCallback jsCallback) {
        String str;
        com.huawei.hwcloudjs.f.d.c(b, "signOut begin", true);
        if (jsParam.getAppId() == null) {
            com.huawei.hwcloudjs.f.d.b(b, "signOut appid is null", true);
            str = "ParamInvalidException error!";
        } else {
            WebView webView = jsCallback.getWebView();
            if (webView == null) {
                com.huawei.hwcloudjs.f.d.b(b, "signOut webview is null", true);
                str = "webview is null";
            } else {
                try {
                    kqi.b((Activity) webView.getContext(), new p(this, jsCallback));
                    return;
                } catch (kqk unused) {
                    com.huawei.hwcloudjs.f.d.b(b, "signOut failed", true);
                    jsCallback.failure("JS signOut parameter invalid");
                    return;
                }
            }
        }
        jsCallback.failure(str);
    }

    @JSMethod(isOpen = true, name = "signIn")
    public void signIn(LoginLiteReq loginLiteReq, JsCallback jsCallback) {
        com.huawei.hwcloudjs.f.d.c(b, "signIn begin", true);
        if (jsCallback == null) {
            com.huawei.hwcloudjs.f.d.b(b, "signIn JsCallback is null", true);
            return;
        }
        WebView webView = jsCallback.getWebView();
        if (webView == null || !(webView.getContext() instanceof Activity)) {
            com.huawei.hwcloudjs.f.d.b(b, "signIn webview is null", true);
            jsCallback.failure("webview is null");
            return;
        }
        if (loginLiteReq == null) {
            com.huawei.hwcloudjs.f.d.b(b, "signIn JS_RET_CODE_PARSE_PARAM_ERROR", true);
            jsCallback.failure(13);
            return;
        }
        String appId = loginLiteReq.getAppId();
        String scope = loginLiteReq.getScope();
        String a2 = a(loginLiteReq);
        String redirectUrl = loginLiteReq.getRedirectUrl();
        String[] split = scope.split("\\,");
        boolean guideLogin = loginLiteReq.getGuideLogin();
        Activity activity = (Activity) webView.getContext();
        com.huawei.hwcloudjs.f.d.c(b, "req:" + loginLiteReq.toString(), false);
        com.huawei.hwcloudjs.f.d.c(b, "deviceInfo:" + a2, false);
        if (appId == null || redirectUrl == null || activity == null) {
            com.huawei.hwcloudjs.f.d.b(b, "signIn appId or redirectUrl or mActivity is null", true);
            jsCallback.failure("ParamInvalidException error!");
            return;
        }
        IAccessToken iAccessTokenClass = JsClientApi.getIAccessTokenClass();
        this.e = iAccessTokenClass;
        if (iAccessTokenClass == null) {
            com.huawei.hwcloudjs.f.d.c(b, "not initAccessTokenClass!", true);
            a(activity, appId, split, redirectUrl, a2, null, guideLogin, jsCallback);
            return;
        }
        Message message = new Message();
        message.obj = new com.huawei.hwcloudjs.service.hms.bean.a(loginLiteReq, jsCallback);
        message.what = 4;
        this.g.sendMessageDelayed(message, 5000L);
        this.f = false;
        this.e.getAccessToken(new m(this, activity, appId, split, redirectUrl, a2, guideLogin, jsCallback));
    }

    @JSMethod(isOpen = true, name = "cancelAuthorization")
    public void cancelAuthorization(RevokeLiteReq revokeLiteReq, JsCallback jsCallback) {
        com.huawei.hwcloudjs.f.d.c(b, "cancelAuthorization begin", true);
        if (jsCallback == null) {
            com.huawei.hwcloudjs.f.d.b(b, "cancelAuthorization JsCallback is null", true);
            return;
        }
        WebView webView = jsCallback.getWebView();
        if (webView == null) {
            com.huawei.hwcloudjs.f.d.b(b, "cancelAuthorization webview is null", true);
            jsCallback.failure("webview is null");
            return;
        }
        if (revokeLiteReq == null) {
            com.huawei.hwcloudjs.f.d.b(b, "cancelAuthorization JS_RET_CODE_PARSE_PARAM_ERROR", true);
            jsCallback.failure(13);
            return;
        }
        String appId = revokeLiteReq.getAppId();
        String accessToken = revokeLiteReq.getAccessToken();
        if (appId == null || accessToken == null) {
            com.huawei.hwcloudjs.f.d.b(b, "cancelAuthorization appid is null", true);
            jsCallback.failure("ParamInvalidException error!");
        } else {
            if (!(webView.getContext() instanceof Activity)) {
                com.huawei.hwcloudjs.f.d.b(b, "webView is error", true);
                jsCallback.failure("webview is error");
                return;
            }
            try {
                kqi.c((Activity) webView.getContext(), mGrsAppName, accessToken, new q(this, jsCallback));
            } catch (kqk unused) {
                com.huawei.hwcloudjs.f.d.b(b, "cancelAuthorization exception", true);
                jsCallback.failure("cancelAuthorization parameter invalid");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(kqo kqoVar, JsCallback jsCallback) {
        String str;
        com.huawei.hwcloudjs.f.d.c(b, "signInOauth onSignInResult begin", true);
        if (kqoVar == null || kqoVar.getStatus() == null) {
            jsCallback.failure("signIn result is null");
            str = "signInResult is null or signInResult.getStatus() is null";
        } else if (kqoVar.c()) {
            com.huawei.hwcloudjs.f.d.c(b, "onSignOAuthResult Success", true);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("AuthorizationCode", kqoVar.d());
                jSONObject.put("StatusCode", kqoVar.getStatus().e());
                jSONObject.put("StatusMessage", kqoVar.getStatus().a());
                jsCallback.success(jSONObject.toString());
                return;
            } catch (JSONException unused) {
                jsCallback.failure();
                str = "onSignOAuthResult JSONException!";
            }
        } else {
            jsCallback.failure(kqoVar.getStatus().e(), kqoVar.getStatus().a());
            str = "StatusCode:" + kqoVar.getStatus().e() + " StatusMessage:" + kqoVar.getStatus().a();
        }
        com.huawei.hwcloudjs.f.d.b(b, str, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.huawei.hwcloudjs.service.hms.bean.a aVar) {
        com.huawei.hwcloudjs.f.d.c(b, "get Token timeout handlerResult", true);
        JsCallback a2 = aVar.a();
        LoginLiteReq b2 = aVar.b();
        a((Activity) a2.getWebView().getContext(), b2.getAppId(), aVar.b().getScope().split("\\,"), b2.getRedirectUrl(), a(b2), null, b2.getGuideLogin(), a2);
    }

    /* loaded from: classes9.dex */
    public static final class RevokeLiteReq extends JsParam {
        private String accessToken;

        public void setAccessToken(String str) {
            this.accessToken = str;
        }

        public String getAccessToken() {
            return this.accessToken;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity, String str, String[] strArr, String str2, String str3, String str4, boolean z, JsCallback jsCallback) {
        com.huawei.hwcloudjs.f.d.c(b, "signInOauth begin", true);
        this.f = true;
        try {
            kqi.bQm_(activity, mGrsAppName, str, strArr, str2, str3, str4, z, new o(this, jsCallback));
        } catch (kqk unused) {
            com.huawei.hwcloudjs.f.d.b(b, "signOAuth ParamInvalidException!", true);
            jsCallback.failure("ParamInvalidException");
        }
    }

    private String a(LoginLiteReq loginLiteReq) {
        String sn = loginLiteReq.getSN();
        return kqj.d(loginLiteReq.getIMEI(), loginLiteReq.getUUID(), sn, loginLiteReq.getUDID());
    }
}
