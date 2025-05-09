package defpackage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler;
import com.huawei.multisimsdk.odsa.presenter.ILoginPresenter;
import com.huawei.multisimsdk.odsa.response.OdsaResponseParam;
import com.huawei.multisimsdk.odsa.utils.OdsaHttpConnectionUtils;
import com.huawei.multisimsdk.odsa.view.ILoginView;
import com.tencent.connect.common.Constants;
import defpackage.uth;
import defpackage.uti;
import defpackage.utl;
import defpackage.utv;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.ClientAuthentication;
import net.openid.appauth.browser.BrowserMatcher;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes5.dex */
public class lpm implements ILoginPresenter {
    private final loi c;
    private String d;
    private AuthorizationService e;
    private utl g;
    private final lox i;
    private final loz j;
    private String k;
    private final Context m;
    private String n;
    private ExecutorService o;
    private String p;
    private int q;
    private String s;
    private String u;
    private utx v;
    private String x;
    private final WeakReference<ILoginView> y;
    private final AtomicReference<String> f = new AtomicReference<>();
    private final AtomicReference<uth> b = new AtomicReference<>();
    private final AtomicReference<JSONObject> w = new AtomicReference<>();
    private final BrowserMatcher h = uud.d;

    /* renamed from: a, reason: collision with root package name */
    private CountDownLatch f14828a = new CountDownLatch(1);
    private final Handler l = new Handler(Looper.getMainLooper()) { // from class: lpm.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                loq.c("LoginPresenter", "EVENT_INIT_AUTH_REQUEST");
                lpm.this.g();
                lpm.this.n();
            } else {
                if (i != 2) {
                    return;
                }
                loq.c("LoginPresenter", "EVENT_FETCH_USER_INFO");
                lpm.this.b();
            }
        }
    };
    private final ResponseHandler r = new ResponseHandler() { // from class: lpm.2
        @Override // com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler
        public void onCallback(String str) {
            loq.e("LoginPresenter", "mResponseHandler onCallback result=" + str);
        }

        @Override // com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler
        public void onCallback(int i, String str) {
            int parseInt;
            loq.e("LoginPresenter", "mResponseHandler onCallback type=" + i + ", result=" + str);
            if (lop.c(str) && (parseInt = Integer.parseInt(str)) != 302) {
                lpm.this.a(parseInt);
                return;
            }
            if (i != 4) {
                return;
            }
            Map<String, String> d = OdsaHttpConnectionUtils.d(str);
            for (Map.Entry<String, String> entry : d.entrySet()) {
                loq.e("LoginPresenter", "mResponseHandler, map[" + entry.getKey() + "]=" + entry.getValue());
            }
            lpm.this.j.a(d);
            lpm.this.s = d.get("service_id");
            lpm.this.u = d.get("state");
            lpm.this.n = d.get(Constants.NONCE);
            lpm.this.d = d.get("acr_values");
            lpm.this.p = d.get("prompt");
            if (lpm.this.j.g()) {
                lpm.this.l();
            } else {
                lpm.this.a(1054);
            }
        }
    };
    private ResponseHandler t = new ResponseHandler() { // from class: lpm.3
        @Override // com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler
        public void onCallback(String str) {
            try {
                loq.e("LoginPresenter", "mResponseHandlerWithAuthCode onCallback result=" + str);
                XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                if (newPullParser != null) {
                    newPullParser.setInput(new ByteArrayInputStream(str.getBytes("UTF-8")), "UTF-8");
                }
                OdsaResponseParam c = lpi.c(newPullParser);
                if (c == null) {
                    lpm.this.a(1061);
                    return;
                }
                String token = c.getToken();
                loq.e("LoginPresenter", "mResponseHandlerWithAuthCode onCallback odsaResponseParam=" + c.toString());
                loq.e("LoginPresenter", "mResponseHandlerWithAuthCode onCallback token=" + token);
                if (TextUtils.isEmpty(token)) {
                    lpm.this.a(1061);
                } else {
                    lpm.this.b(new utv.e(lpm.this.v).a(token).b(), (utg) null);
                }
            } catch (UnsupportedEncodingException e) {
                loq.e("LoginPresenter", "mResponseHandlerWithAuthCode occur an UnsupportedEncodingException ", e);
            } catch (IOException e2) {
                e = e2;
                loq.e("LoginPresenter", "mResponseHandlerWithAuthCode occur an IOException error", e);
                lpm.this.a(1061);
            } catch (XmlPullParserException e3) {
                e = e3;
                loq.e("LoginPresenter", "mResponseHandlerWithAuthCode occur an IOException error", e);
                lpm.this.a(1061);
            } catch (Exception e4) {
                loq.e("LoginPresenter", "mResponseHandlerWithAuthCode occur an Exception ", e4);
            }
        }
    };

    public lpm(ILoginView iLoginView, int i) {
        loq.c("LoginPresenter", "constructor slotId= " + i);
        WeakReference<ILoginView> weakReference = new WeakReference<>(iLoginView);
        this.y = weakReference;
        Context appContext = weakReference.get().getAppContext();
        this.m = appContext;
        this.i = lox.d(appContext);
        this.j = loz.d(appContext, i);
        this.o = Executors.newSingleThreadExecutor();
        this.c = loi.d();
        this.q = i;
    }

    @Override // com.huawei.multisimsdk.odsa.presenter.ILoginPresenter
    public void startLogin() {
        loq.e("LoginPresenter", "startLogin 1: " + this.i.d().f());
        loq.e("LoginPresenter", "startLogin 2: " + this.j.f());
        String a2 = lop.a("ODSA_ESURL", "");
        this.k = a2;
        if (TextUtils.isEmpty(a2)) {
            a(1053);
        } else if (lpd.a() == null) {
            a(1052);
        } else {
            loq.e("LoginPresenter", "Step1. startLogin, try to get params from es server.");
            this.c.b(lpd.a().d(), lpd.a().b(), null, this.r, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (!this.j.g()) {
            loq.c("LoginPresenter", "configuration is invalid, response fail");
            a(1052);
            return;
        }
        h();
        if (!this.o.isShutdown()) {
            this.o.submit(new Runnable() { // from class: lpv
                @Override // java.lang.Runnable
                public final void run() {
                    lpm.this.f();
                }
            });
        } else {
            a(1056);
        }
    }

    @Override // com.huawei.multisimsdk.odsa.presenter.ILoginPresenter
    public void onStart() {
        if (this.o.isShutdown()) {
            this.o = Executors.newSingleThreadExecutor();
        }
    }

    @Override // com.huawei.multisimsdk.odsa.presenter.ILoginPresenter
    public void onStop() {
        this.o.shutdownNow();
    }

    @Override // com.huawei.multisimsdk.odsa.presenter.ILoginPresenter
    public void onDestroy() {
        AuthorizationService authorizationService = this.e;
        if (authorizationService != null) {
            authorizationService.c();
        }
        ExecutorService executorService = this.o;
        if (executorService != null) {
            executorService.shutdownNow();
        }
        WeakReference<ILoginView> weakReference = this.y;
        if (weakReference != null) {
            weakReference.clear();
        }
    }

    private void m() {
        loq.c("LoginPresenter", "responseSuccess");
        if (lpd.a() != null) {
            lpd.a().d(1000);
        }
        if (i()) {
            this.y.get().finishView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        loq.c("LoginPresenter", "responseFailWithReason: reason = " + i);
        if (lpd.a() != null) {
            lpd.a().d(i);
        }
        if (i()) {
            this.y.get().finishView();
        }
    }

    private void h() {
        if (this.j.f()) {
            loq.c("LoginPresenter", "Configuration change detected, discarding old state");
            this.i.d(new AuthState());
            this.j.c();
        }
    }

    private boolean i() {
        WeakReference<ILoginView> weakReference = this.y;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        loq.c("LoginPresenter", "Step2. initAppAuthService");
        o();
        if (this.i.d().b() != null) {
            loq.c("LoginPresenter", "auth config already got");
            j();
        } else {
            if (this.j.bYJ_() == null) {
                loq.c("LoginPresenter", "Creating auth config");
                this.i.d(new AuthState(new AuthorizationServiceConfiguration(this.j.bYK_(), this.j.bYK_())));
                j();
                return;
            }
            loq.c("LoginPresenter", "Retrieving OpenID discovery doc");
            AuthorizationServiceConfiguration.fgh_(this.j.bYJ_(), new AuthorizationServiceConfiguration.RetrieveConfigurationCallback() { // from class: lpp
                @Override // net.openid.appauth.AuthorizationServiceConfiguration.RetrieveConfigurationCallback
                public final void onFetchConfigurationCompleted(AuthorizationServiceConfiguration authorizationServiceConfiguration, utg utgVar) {
                    lpm.this.e(authorizationServiceConfiguration, utgVar);
                }
            }, this.j.a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(AuthorizationServiceConfiguration authorizationServiceConfiguration, utg utgVar) {
        if (authorizationServiceConfiguration == null) {
            loq.d("LoginPresenter", "handleRetrieveConfigurationCallback: Failed to retrieve discovery document", utgVar);
            a(1055);
            return;
        }
        loq.c("LoginPresenter", "handleRetrieveConfigurationCallback: Discovery document retrieved");
        this.i.d(new AuthState(authorizationServiceConfiguration));
        if (!this.o.isShutdown()) {
            this.o.submit(new Runnable() { // from class: lpo
                @Override // java.lang.Runnable
                public final void run() {
                    lpm.this.j();
                }
            });
        } else {
            loq.g("LoginPresenter", "handleRetrieveConfigurationCallback: excutor service shut down!");
        }
    }

    private void o() {
        loq.c("LoginPresenter", "refreshAppAuthService start.");
        if (this.e != null) {
            loq.c("LoginPresenter", "refreshAppAuthService mAuthService dispose");
            this.e.c();
        }
        this.e = a();
        this.b.set(null);
    }

    private AuthorizationService a() {
        loq.c("LoginPresenter", "Creating authorization service");
        uti.c cVar = new uti.c();
        cVar.b(this.h);
        cVar.c(uua.e);
        return new AuthorizationService(this.m, cVar.e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        loq.c("LoginPresenter", "initClient");
        if (this.j.b() != null) {
            loq.e("LoginPresenter", "initClient, Using static client ID: " + this.j.b());
            this.f.set(this.j.b());
            Handler handler = this.l;
            handler.sendMessage(handler.obtainMessage(1));
            return;
        }
        loq.g("LoginPresenter", "initClient, It will not happen!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        loq.c("LoginPresenter", "initAuthRequest");
        e();
    }

    private void e() {
        uth.d d = new uth.d(this.i.d().b(), this.f.get(), "code", this.j.bYL_()).h(this.j.i()).c(null).d(c());
        if (!TextUtils.isEmpty(this.u)) {
            d = d.j(this.u);
        }
        if (!TextUtils.isEmpty(this.p)) {
            d = d.e(this.p);
        }
        String b = lop.b();
        if (lop.e("OIDC_SUPPORT_SPECIFY_LANGUAGE", (Boolean) false).booleanValue() && !TextUtils.isEmpty(b)) {
            d = d.i(b);
        }
        this.b.set(d.e());
    }

    private Map<String, String> c() {
        HashMap hashMap = new HashMap(2);
        if (!TextUtils.isEmpty(this.s)) {
            hashMap.put("service_id", this.s);
        }
        if (!TextUtils.isEmpty(this.n)) {
            hashMap.put(Constants.NONCE, this.n);
        }
        if (!TextUtils.isEmpty(this.d)) {
            hashMap.put("acr_values", this.d);
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        loq.c("LoginPresenter", "Step3. startAuth.");
        if (!this.o.isShutdown()) {
            this.o.submit(new Runnable() { // from class: lps
                @Override // java.lang.Runnable
                public final void run() {
                    lpm.this.d();
                }
            });
        } else {
            a(1056);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        loq.c("LoginPresenter", "doAuth");
        if (i()) {
            this.y.get().loadUrl(this.b.get().toUri().toString());
        }
    }

    @Override // com.huawei.multisimsdk.odsa.presenter.ILoginPresenter
    public void setUrlWithAuthorizationCodeFromWebView(String str) {
        this.x = str;
    }

    @Override // com.huawei.multisimsdk.odsa.presenter.ILoginPresenter
    public String getTokenValue(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.indexOf("&lt;") >= 0) {
            str = Html.fromHtml(str).toString();
        }
        int indexOf = str.indexOf("<wap-provisioningdoc");
        int indexOf2 = str.indexOf("</wap-provisioningdoc>");
        loq.e("LoginPresenter", "getTokenValue firstIndex = " + indexOf);
        loq.e("LoginPresenter", "getTokenValue secondIndex = " + indexOf2);
        if (indexOf >= 0 && indexOf2 >= 0 && (i = indexOf2 + 22) <= str.length() && i >= indexOf) {
            String substring = str.substring(indexOf, i);
            loq.e("LoginPresenter", "getTokenValue content = " + substring);
            try {
                loq.e("LoginPresenter", "getTokenValue onCallback result=" + substring);
                XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                if (newPullParser != null) {
                    newPullParser.setInput(new ByteArrayInputStream(substring.getBytes("UTF-8")), "UTF-8");
                }
                OdsaResponseParam c = lpi.c(newPullParser);
                String token = c.getToken();
                loq.e("LoginPresenter", "getTokenValue onCallback odsaResponseParam=" + c.toString());
                loq.e("LoginPresenter", "getTokenValue onCallback token=" + token);
                if (TextUtils.isEmpty(token)) {
                    return null;
                }
                return token;
            } catch (UnsupportedEncodingException e) {
                loq.e("LoginPresenter", "getTokenValue occur an UnsupportedEncodingException ", e);
            } catch (IOException e2) {
                e = e2;
                loq.e("LoginPresenter", "getTokenValue occur an IOException ", e);
                return null;
            } catch (XmlPullParserException e3) {
                e = e3;
                loq.e("LoginPresenter", "getTokenValue occur an IOException ", e);
                return null;
            } catch (Exception e4) {
                loq.e("LoginPresenter", "getTokenValue occur an Exception ", e4);
            }
        }
        return null;
    }

    @Override // com.huawei.multisimsdk.odsa.presenter.ILoginPresenter
    public void processAuthResultFromWebView(String str) {
        utl.e fgf_;
        loq.e("LoginPresenter", "processAuthResultFromWebView, token = " + str);
        if (str == null) {
            a(1056);
            return;
        }
        loq.e("LoginPresenter", "processAuthResultFromWebView, 1 = " + str);
        utl.e eVar = new utl.e(this.b.get());
        String str2 = this.x;
        if (str2 != null) {
            fgf_ = eVar.fgf_(Uri.parse(str2));
        } else {
            fgf_ = eVar.fgf_(this.b.get().toUri());
        }
        loq.e("LoginPresenter", "processAuthResultFromWebView, 2 = " + str);
        fgf_.b(str);
        this.g = fgf_.b();
        loq.e("LoginPresenter", "mAuthorizationResponse: " + this.g.jsonSerializeString());
        loq.e("LoginPresenter", "processAuthResultFromWebView, 3, mAuthorizationResponse = " + this.g);
        this.v = this.g.d();
        loq.e("LoginPresenter", "processAuthResultFromWebView, 4, mTokenRequest = " + this.v);
        if (!TextUtils.isEmpty(str)) {
            utv b = new utv.e(this.v).a(str).b();
            loq.e("LoginPresenter", "processAuthResultFromWebView, 5, response = " + b);
            b(b, (utg) null);
            return;
        }
        loq.e("LoginPresenter", "processAuthResultFromWebView, 6, fail");
        a(1061);
    }

    @Override // com.huawei.multisimsdk.odsa.presenter.ILoginPresenter
    public void processAuthResult(int i, int i2, Intent intent) {
        if (i == 0) {
            a(1056);
            return;
        }
        if (intent == null) {
            a(1057);
            return;
        }
        utl fge_ = utl.fge_(intent);
        this.g = fge_;
        utg ffT_ = utg.ffT_(intent);
        if (fge_ != null || ffT_ != null) {
            this.i.e(fge_, ffT_);
        }
        if (fge_ != null && fge_.f17546a != null) {
            loq.e("LoginPresenter", "debug response.authorizationCode is " + fge_.f17546a);
            loq.e("LoginPresenter", "debug response.authorizationCode is " + fge_.jsonSerializeString());
            c(fge_);
            return;
        }
        if (ffT_ != null) {
            loq.g("LoginPresenter", "Authorization flow failed: " + ffT_.getMessage());
        }
        a(1058);
    }

    private void c(utl utlVar) {
        loq.c("LoginPresenter", "Exchanging authorization code.");
        this.v = utlVar.d();
        loq.e("LoginPresenter", "Exchanging tokenRequest 1 " + this.v.e.e);
        loq.e("LoginPresenter", "Exchanging tokenRequest 2 " + this.v.e.b());
        loq.e("LoginPresenter", "Exchanging tokenRequest 3 " + this.v.b());
        c(this.v, new AuthorizationService.TokenResponseCallback() { // from class: lpq
            @Override // net.openid.appauth.AuthorizationService.TokenResponseCallback
            public final void onTokenRequestCompleted(utv utvVar, utg utgVar) {
                lpm.this.b(utvVar, utgVar);
            }
        });
    }

    private void c(utx utxVar, AuthorizationService.TokenResponseCallback tokenResponseCallback) {
        try {
            ClientAuthentication a2 = this.i.d().a();
            StringBuilder sb = new StringBuilder("mAuthService ");
            sb.append(this.e == null);
            sb.append(" callback ");
            sb.append(tokenResponseCallback == null);
            sb.append(" client ");
            sb.append(a2 == null);
            loq.c("LoginPresenter", sb.toString());
            o();
            this.e.a(utxVar, a2, tokenResponseCallback);
        } catch (ClientAuthentication.e e) {
            loq.c("LoginPresenter", "Token request cannot be made, client authentication for the token endpoint could not be constructed (%s)", e.toString());
            a(1059);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(utv utvVar, utg utgVar) {
        loq.e("LoginPresenter", "before token is " + this.i.d().d());
        this.i.e(utvVar, utgVar);
        loq.e("LoginPresenter", "after token is " + this.i.d().d());
        if (!this.i.d().f()) {
            StringBuilder sb = new StringBuilder("Authorization Code exchange failed");
            sb.append(utgVar != null ? utgVar.c : "");
            loq.g("LoginPresenter", "handleCodeExchangeResponse displayNotAuthorized: " + sb.toString() + " authException: " + utgVar);
            k();
            return;
        }
        loq.c("LoginPresenter", "handleCodeExchangeResponse displayAuthorized.");
        Handler handler = this.l;
        handler.sendMessage(handler.obtainMessage(2));
    }

    private void k() {
        if (TextUtils.isEmpty(this.k) || this.g == null) {
            a(1060);
            return;
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("state", this.g.h);
        hashMap.put("code", this.g.f17546a);
        this.c.b(lpd.a().d(), lpd.a().b(), hashMap, this.t, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        loq.c("LoginPresenter", "fetchUserInfo start.");
        this.i.d().c(this.e, new AuthState.AuthStateAction() { // from class: lpn
            @Override // net.openid.appauth.AuthState.AuthStateAction
            public final void execute(String str, String str2, utg utgVar) {
                lpm.this.c(str, str2, utgVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2, utg utgVar) {
        loq.e("LoginPresenter", "fetchUserInfo accessToken:" + str + ", idToken:" + str2 + ", ex:" + utgVar);
        if (utgVar != null) {
            loq.g("LoginPresenter", "Token fresh failed");
            this.w.set(null);
            a(1061);
            return;
        }
        m();
    }
}
