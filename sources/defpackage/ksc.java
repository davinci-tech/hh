package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.util.GmsVersion;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.api.entity.common.CommonPickerConstant;
import com.huawei.hwidauth.api.Result;
import com.huawei.hwidauth.api.ResultCallBack;
import com.huawei.hwidauth.datatype.DeviceInfo;
import com.huawei.hwidauth.h.f;
import com.huawei.hwidauth.i.a;
import com.huawei.hwidauth.ui.WebViewActivity;
import com.huawei.hwidauth.ui.f;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import com.huawei.secure.android.common.encrypt.hash.SHA;
import com.huawei.secure.android.common.encrypt.rsa.RSAEncrypt;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.intent.SafeBundle;
import com.huawei.secure.android.common.intent.SafeIntent;
import com.huawei.secure.android.common.util.EncodeUtil;
import com.huawei.secure.android.common.util.SafeBase64;
import com.huawei.secure.android.common.webview.SafeWebView;
import com.tencent.connect.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ksc extends f.a {
    private String aa;
    private String ab;
    private boolean ac;
    private String ad;
    private String b;
    private f.b c;
    private String d;
    private String e;
    private String j;
    private String k;
    private String l;
    private DeviceInfo m;
    private String n;
    private String o;
    private int p;
    private String q;
    private String s;
    private Context t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;
    private String g = "";
    private String f = "";
    private String h = "";
    private String i = "";
    private String r = "0";

    /* renamed from: a, reason: collision with root package name */
    private boolean f14565a = false;

    public ksc(f.b bVar, Context context) {
        this.c = bVar;
        this.t = context;
    }

    public void a(String str) {
        this.aa = str;
    }

    @Override // com.huawei.hwidauth.ui.b
    public void init(SafeIntent safeIntent) {
        ksy.b("WebViewPresenter", "init", true);
        Bundle extras = safeIntent.getExtras();
        if (extras == null) {
            ksy.c("WebViewPresenter", "Excepton：bundle null", true);
            e(404, "parse intent exception", "");
        } else {
            a(new SafeBundle(extras), safeIntent);
            ksy.b("WebViewPresenter", "init end", true);
        }
    }

    private void a(SafeBundle safeBundle, SafeIntent safeIntent) {
        try {
            this.g = safeBundle.getString("key_app_id");
            this.f = safeBundle.getString("key_scopes");
            this.h = safeBundle.getString("key_access_token");
            this.j = safeBundle.getString("key_redirecturi", "");
            this.i = safeBundle.getString("key_oper", "");
            this.z = safeIntent.getStringExtra("key_pickerSignIn");
            this.m = (DeviceInfo) safeBundle.getParcelable("key_device_info", DeviceInfo.class);
            this.n = safeBundle.getString("key_qr_code");
            this.p = safeBundle.getInt("key_qr_siteid");
            this.s = safeBundle.getString("key_qr_code_source", "");
            this.r = safeBundle.getString("key_check_password_type");
            this.v = safeBundle.getString("key_extends_param");
            this.o = safeBundle.getString("key_cp_login_url");
            this.k = safeBundle.getString("key_login_mode");
            this.x = safeBundle.getString("reqClientType");
            this.ab = safeBundle.getString("key_uid");
            this.l = safeBundle.getString("key_transId");
            this.ad = safeBundle.getString("key_code_verifier");
            this.f14565a = safeBundle.getBoolean("key_mcp_signIn");
            this.ac = safeBundle.getBoolean("independentAuthorization", false);
            this.d = safeBundle.getString("user_code");
            this.e = safeBundle.getString("verification_url");
            this.b = safeBundle.getString("grs_app_name");
            o();
            if (TextUtils.isEmpty(this.i) || !"from_qr_authorize".equals(this.i)) {
                r();
            }
        } catch (RuntimeException unused) {
            ksy.c("WebViewPresenter", "RuntimeException：parse intent", true);
            e(404, "parse intent exception", "");
        } catch (Exception unused2) {
            ksy.c("WebViewPresenter", "Exception：parse intent", true);
            e(404, "parse intent exception", "");
        }
    }

    private void o() {
        ksy.b("WebViewPresenter", "parsingExtendsParam mExtendsParam =" + this.v, false);
        if (TextUtils.isEmpty(this.v)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.v);
            if (jSONObject.has("countryCode")) {
                this.w = jSONObject.getString("countryCode");
            }
            if (jSONObject.has("phoneNumber")) {
                this.u = jSONObject.getString("phoneNumber");
            }
            if (jSONObject.has("reqClientType")) {
                this.x = jSONObject.getString("reqClientType");
            }
            if (jSONObject.has("loginChannel")) {
                this.y = jSONObject.getString("loginChannel");
            }
        } catch (JSONException unused) {
            ksy.c("WebViewPresenter", "parsingExtendsParam Exception", true);
            e(404, "parse intent exception", "");
        }
    }

    private String k() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ED", this.q);
        } catch (JSONException unused) {
            ksy.c("WebViewPresenter", "JSONException_ed", true);
        }
        return jSONObject.toString();
    }

    private String n() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("D", p());
        } catch (JSONException unused) {
            ksy.c("WebViewPresenter", "JSONException_d", true);
        }
        return jSONObject.toString();
    }

    private String c(String str, String str2) throws UnsupportedEncodingException {
        return "LOGIN_MODE_HUAWEI_UNITE_ID".equals(this.k) ? a.a().a() : a.a().b() + "?reqClientType=" + this.x + kte.g(this.t) + "&loginChannel=7000000&displayType=2&clientID=" + this.g + "&lang=" + str.toLowerCase(Locale.getDefault()) + "&service=" + URLEncoder.encode(str2 + "?reqClientType=" + this.x + kte.g(this.t), "UTF-8");
    }

    public String e(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(this.x)) {
            this.x = "7";
        }
        try {
            sb.append("ext_clientInfo=");
            sb.append(v());
            sb.append("&loginUrl=");
            sb.append(URLEncoder.encode(c(str, str2), "UTF-8"));
            sb.append("&service=");
            sb.append(URLEncoder.encode(str2 + "?reqClientType=" + this.x + kte.g(this.t) + "&loginChannel=7000000&service=" + URLEncoder.encode(krj.c().d(this.t), "UTF-8") + "&displayType=2&lang=" + str.toLowerCase(Locale.getDefault()), "UTF-8"));
            sb.append("&loginChannel=");
            sb.append(GmsVersion.VERSION_ORLA);
            sb.append("&reqClientType=");
            sb.append(Integer.parseInt(this.x));
            sb.append("&lang=");
            sb.append(URLEncoder.encode(str.toLowerCase(Locale.getDefault()), "UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            sb = new StringBuilder();
        } catch (RuntimeException unused2) {
            sb = new StringBuilder();
        } catch (Exception unused3) {
            sb = new StringBuilder();
        }
        ksy.b("WebViewPresenter", "centerUrlData：" + ((Object) sb), false);
        return sb.toString();
    }

    public String d(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("loginUrl=");
            sb.append(URLEncoder.encode(a.a().b() + "?reqClientType=7&loginChannel=7000000&displayType=2&clientID=" + this.g + "&lang=" + str.toLowerCase(Locale.getDefault()) + kte.g(this.t), "UTF-8"));
            sb.append("&service=");
            sb.append(URLEncoder.encode(a.a().d() + "?reqClientType=7&loginChannel=7000000&service=" + URLEncoder.encode(krj.c().d(this.t), "UTF-8") + "&displayType=2&lang=" + str.toLowerCase(Locale.getDefault()) + kte.g(this.t), "UTF-8"));
            sb.append("&loginChannel=");
            sb.append(GmsVersion.VERSION_ORLA);
            sb.append("&reqClientType=");
            sb.append(7);
            sb.append("&accessToken=");
            sb.append(URLEncoder.encode(this.h, "UTF-8"));
            sb.append("&lang=");
            sb.append(URLEncoder.encode(str.toLowerCase(Locale.getDefault()), "UTF-8"));
            sb.append("&appID=");
            sb.append(this.g);
            sb.append(kte.g(this.t));
        } catch (UnsupportedEncodingException unused) {
            sb = new StringBuilder();
        } catch (Exception unused2) {
            sb = new StringBuilder();
        }
        ksy.b("WebViewPresenter", "authAppListUrlData：" + ((Object) sb), false);
        return sb.toString();
    }

    public String c(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("ext_clientInfo=");
            sb.append(v());
            sb.append("&chkType=");
            sb.append(this.r);
            sb.append("&reqClientType=7");
            sb.append("&lang=");
            sb.append(URLEncoder.encode(str.toLowerCase(Locale.getDefault()), "UTF-8"));
            sb.append(kte.g(this.t));
        } catch (UnsupportedEncodingException unused) {
            sb = new StringBuilder();
        }
        ksy.b("WebViewPresenter", "verifyPasswordData：" + ((Object) sb), false);
        return sb.toString();
    }

    public String e(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("loginUrl=");
            sb.append(URLEncoder.encode(a.a().b() + "?reqClientType=7&loginChannel=7000000&displayType=2&clientID=" + this.g + "&lang=" + str.toLowerCase(Locale.getDefault()) + kte.g(this.t), "UTF-8"));
            sb.append("&service=");
            sb.append(krj.c().i(this.t));
            sb.append("&loginChannel=");
            sb.append(GmsVersion.VERSION_ORLA);
            sb.append("&reqClientType=");
            sb.append(7);
            sb.append("&lang=");
            sb.append(URLEncoder.encode(str.toLowerCase(Locale.getDefault()), "UTF-8"));
            sb.append("&appID=");
            sb.append(this.g);
            sb.append("&qrSiteID=");
            sb.append(this.p);
            sb.append("&qrCode=");
            sb.append(this.n);
            sb.append(kte.g(this.t));
            if (!TextUtils.isEmpty(this.s)) {
                sb.append("&qrCodeSource=");
                sb.append(this.s);
            }
        } catch (UnsupportedEncodingException unused) {
            sb = new StringBuilder();
        } catch (Exception unused2) {
            sb = new StringBuilder();
        }
        ksy.b("WebViewPresenter", "qrLoginUrlData：" + ((Object) sb), false);
        return sb.toString();
    }

    public String a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("code=");
            sb.append(URLEncoder.encode(str, "UTF-8"));
            sb.append("&state=");
            sb.append(URLEncoder.encode(str2, "UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            sb = new StringBuilder();
        } catch (Exception unused2) {
            sb = new StringBuilder();
        }
        ksy.b("WebViewPresenter", "centerUrlData：" + ((Object) sb), false);
        return sb.toString();
    }

    public String c() {
        return this.i;
    }

    public void d() {
        try {
            ksy.b("WebViewPresenter", "get urlMap from grs Cluster", true);
            Map<String, String> d = kqe.e().d(this.t, this.b, "com.huawei.cloud.hwid");
            if (d != null) {
                b(d);
            } else {
                ksy.c("WebViewPresenter", "urlMap null", true);
                e(6, "User cancel", "");
            }
        } catch (NoClassDefFoundError unused) {
            ksy.c("WebViewPresenter", "NoClassDefFoundError", true);
            e(6, "User cancel", "");
        }
    }

    private void b(Map<String, String> map) {
        String str;
        ksy.b("WebViewPresenter", "handleGrsUrlMap start.", true);
        ksy.b("WebViewPresenter", "get hw domain.", true);
        if ("scan_code_login".equalsIgnoreCase(this.i)) {
            str = c(this.p, map);
        } else {
            str = map.get("CASDomainUrl");
        }
        String str2 = map.get("CASDomainUrl");
        String str3 = map.get("CASGetResourceUrl");
        String str4 = map.get("CASAuthorizeUrl");
        String str5 = map.get("GwSilentCodeUrl");
        String str6 = map.get("Root");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            ksy.c("WebViewPresenter", "url is empty exit", true);
            e(6, "User cancel", "");
            return;
        }
        c(str, str2, str3, str4, str5, str6);
        a.a().a(str, str3, str4);
        ksy.b("WebViewPresenter", "initDomainUrlAndLoadUrl", true);
        if (TextUtils.isEmpty(ksi.p()) || ksk.b(ksi.r()).booleanValue()) {
            c(true);
            l();
        } else {
            c(false);
        }
    }

    private void l() {
        ksy.b("WebViewPresenter", "executeGetResourceRequest==", true);
        String r = krv.o().r();
        if (TextUtils.isEmpty(r)) {
            ksy.c("WebViewPresenter", "getResourceUrl is null.", true);
            e(6, "User cancel", "");
            return;
        }
        kqw kqwVar = new kqw(this.t, "casLogin");
        String str = r + kqwVar.c();
        ksy.b("WebViewPresenter", "executeGetResourceRequest:" + str, false);
        new kry(kqwVar, this.t, str, new f.a() { // from class: ksc.4
            @Override // com.huawei.hwidauth.h.f.a
            public void a(SafeBundle safeBundle) {
                ksc.this.b(safeBundle);
            }

            @Override // com.huawei.hwidauth.h.f.a
            public void a(int i, String str2) {
                ksc.this.c.a(i, str2);
            }
        }).a();
    }

    public void b(String str) {
        kqy kqyVar = new kqy(this.t, str, "0", "");
        String r = krv.o().r();
        if (TextUtils.isEmpty(r)) {
            ksy.c("WebViewPresenter", "getResourceUrl is null.", true);
            this.c.a(d("1", "9999"));
            return;
        }
        String str2 = r + kqyVar.c();
        d(kqyVar);
        ksy.b("WebViewPresenter", "getDeviceAuthCode:" + str2, false);
        new krx(kqyVar, this.t, str2, new f.a() { // from class: ksc.3
            @Override // com.huawei.hwidauth.h.f.a
            public void a(SafeBundle safeBundle) {
                ksc.this.e(safeBundle);
            }

            @Override // com.huawei.hwidauth.h.f.a
            public void a(int i, String str3) {
                ksc.this.c.a(ksc.this.d("1", "9999"));
            }
        }).a();
    }

    private String c(int i, Map<String, String> map) {
        ksy.b("WebViewPresenter", "dealScanRequestUrl start.", true);
        String c = i > 0 ? krj.c().c(this.t, i) : "";
        return (TextUtils.isEmpty(c) || "https://".equals(c)) ? map.get("CASDomainUrl") : c;
    }

    private void c(String str, String str2, String str3, String str4, String str5, String str6) {
        StringBuilder sb = new StringBuilder("get domain url from grs mainDomainUrl size:");
        sb.append(str == null ? 0 : str.length());
        ksy.b("WebViewPresenter", sb.toString(), true);
        StringBuilder sb2 = new StringBuilder("get domain url from grs cASDomainUrl size:");
        sb2.append(str2 == null ? 0 : str2.length());
        ksy.b("WebViewPresenter", sb2.toString(), true);
        StringBuilder sb3 = new StringBuilder("get domain url from grs authorizeUrl size:");
        sb3.append(str4 == null ? 0 : str4.length());
        ksy.b("WebViewPresenter", sb3.toString(), true);
        StringBuilder sb4 = new StringBuilder("get domain url from grs getResourceUrl size:");
        sb4.append(str3 == null ? 0 : str3.length());
        ksy.b("WebViewPresenter", sb4.toString(), true);
        StringBuilder sb5 = new StringBuilder("get domain url from grs gwSilentCodeUrl size:");
        sb5.append(str5 == null ? 0 : str5.length());
        ksy.b("WebViewPresenter", sb5.toString(), true);
        StringBuilder sb6 = new StringBuilder("get domain url from grs root ");
        sb6.append(str6 != null ? str6.length() : 0);
        ksy.b("WebViewPresenter", sb6.toString(), true);
    }

    public HashMap<String, String> j(String str) {
        ksy.b("WebViewPresenter", "getHeaders start.", true);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("X-Huawei-Client-Info", k(str));
        return hashMap;
    }

    public HashMap<String, String> h(String str) {
        ksy.b("WebViewPresenter", "getQrCodeHeaders start.", true);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("X-Huawei-Client-Info", k(str));
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + this.h);
        return hashMap;
    }

    public HashMap<String, String> b() {
        ksy.b("WebViewPresenter", "getVerifyPasswordHeaders start.", true);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("srcAppVersion", "");
        hashMap.put("srcAppName", this.t.getPackageName());
        return hashMap;
    }

    public HashMap<String, String> g(String str) {
        ksy.b("WebViewPresenter", "getSignInHeaders start.", true);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("X-Huawei-Client-Info", k(str));
        if (TextUtils.isEmpty(this.h)) {
            return hashMap;
        }
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + this.h);
        return hashMap;
    }

    public String f(String str) {
        StringBuilder sb;
        String t = t();
        String b = b(str, t);
        if (this.ac || !TextUtils.isEmpty(this.h)) {
            return b;
        }
        boolean c = krn.c(this.t).c("getSignInCode", false);
        ksy.b("WebViewPresenter", "getSignInUrl  hasGetCode= " + c, true);
        if (c) {
            long currentTimeMillis = System.currentTimeMillis() - krn.c(this.t).c("getSignInCodeTime", 0L);
            ksy.b("WebViewPresenter", "getSignInUrl  midTime= " + currentTimeMillis, true);
            if (currentTimeMillis > 0 && currentTimeMillis < 216000) {
                return b;
            }
        }
        try {
            sb = new StringBuilder(a.a().b());
            sb.append("?service");
            sb.append('=');
            sb.append(URLEncoder.encode(b, "UTF-8"));
            sb.append("&lang=");
            sb.append(str);
            sb.append("&loginChannel=");
            sb.append(TextUtils.isEmpty(this.y) ? 70000 : this.y);
            sb.append("&reqClientType=");
            sb.append(TextUtils.isEmpty(this.x) ? 7 : this.x);
            sb.append("&clientID=");
            sb.append(URLEncoder.encode(this.g, "UTF-8"));
            sb.append(kte.g(this.t));
            if (!TextUtils.isEmpty(t)) {
                sb.append("&countryCode=");
                sb.append(t);
            }
        } catch (Exception e) {
            ksy.c("WebViewPresenter", "Exception:" + e.getClass().getSimpleName(), true);
            sb = new StringBuilder();
        }
        return sb.toString();
    }

    private String b(String str, String str2) {
        return e(new StringBuilder(a.a().f()), str2, str).toString();
    }

    private StringBuilder e(StringBuilder sb, String str, String str2) {
        try {
            sb.append("access_type=offline");
            sb.append('&');
            sb.append(CommonConstant.ReqAccessTokenParam.RESPONSE_TYPE);
            sb.append('=');
            sb.append("code");
            sb.append('&');
            sb.append("client_id");
            sb.append('=');
            sb.append(URLEncoder.encode(this.g, "UTF-8"));
            sb.append('&');
            sb.append("ui_locales");
            sb.append('=');
            sb.append(URLEncoder.encode(str2.toLowerCase(Locale.getDefault()), "UTF-8"));
            sb.append('&');
            sb.append(CommonConstant.ReqAccessTokenParam.REDIRECT_URI);
            sb.append('=');
            sb.append(URLEncoder.encode(this.j, "UTF-8"));
            sb.append('&');
            sb.append("state");
            sb.append('=');
            sb.append(URLEncoder.encode(kti.a(), "UTF-8"));
            String str3 = this.f;
            if (this.ac) {
                sb.append("&independent_authorization=");
                sb.append("true");
            } else if (!str3.contains("openid")) {
                str3 = "openid " + str3;
            }
            if (!TextUtils.isEmpty(str3)) {
                sb.append('&');
                sb.append("scope");
                sb.append('=');
                sb.append(URLEncoder.encode(str3, "UTF-8"));
            }
            sb.append('&');
            sb.append("display");
            sb.append('=');
            sb.append("touch");
            sb.append('&');
            sb.append(Constants.NONCE);
            sb.append('=');
            sb.append(URLEncoder.encode(ktc.c(), "UTF-8"));
            sb.append('&');
            sb.append("include_granted_scopes");
            sb.append('=');
            sb.append("true");
            sb.append('&');
            sb.append("uuid");
            sb.append('=');
            sb.append(this.m.e());
            sb.append(kte.g(this.t));
            if (!TextUtils.isEmpty(this.y)) {
                sb.append("&loginChannel=");
                sb.append(this.y);
            }
            if (!TextUtils.isEmpty(this.x)) {
                sb.append("&reqClientType=");
                sb.append(this.x);
            }
            if (!TextUtils.isEmpty(str)) {
                sb.append("&countryCode=");
                sb.append(str);
            }
            sb.append("&cVersion=");
            sb.append("HwID_6.12.0.302");
            if (!this.f14565a) {
                return sb;
            }
            String encodeToString = SafeBase64.encodeToString(MessageDigest.getInstance("SHA-256").digest(this.ad.getBytes("ISO_8859_1")), 11);
            sb.append("&code_challenge=");
            sb.append(encodeToString);
            sb.append("&code_challenge_method=");
            sb.append("S256");
            if (!TextUtils.isEmpty(this.h)) {
                sb.append("&auth_type=");
                sb.append("1");
            }
            String b = kti.b();
            if (TextUtils.isEmpty(b)) {
                b = "unkown";
            }
            sb.append("&terminal-type=");
            sb.append(b);
            return sb;
        } catch (UnsupportedEncodingException e) {
            ksy.c("WebViewPresenter", "UnsupportedEncodingException:" + e.getClass().getSimpleName(), true);
            return new StringBuilder();
        } catch (RuntimeException e2) {
            ksy.c("WebViewPresenter", "RuntimeException:" + e2.getClass().getSimpleName(), true);
            return new StringBuilder();
        } catch (NoSuchAlgorithmException e3) {
            ksy.c("WebViewPresenter", "NoSuchAlgorithmException:" + e3.getClass().getSimpleName(), true);
            return new StringBuilder();
        } catch (Exception e4) {
            ksy.c("WebViewPresenter", "Exception:" + e4.getClass().getSimpleName(), true);
            return new StringBuilder();
        }
    }

    private String t() {
        if (!TextUtils.isEmpty(this.w)) {
            return this.w;
        }
        String h = krj.c().h(this.t.getApplicationContext());
        ksy.b("WebViewPresenter", "countryIsoCoce:" + h, false);
        return !TextUtils.isEmpty(h) ? h : "";
    }

    public String a() {
        return this.o;
    }

    public void e(SafeBundle safeBundle) {
        String str;
        ksy.b("WebViewPresenter", "handleRequestGetDevAuthCode ==", true);
        try {
            String string = safeBundle.getString("devAuthCode");
            String string2 = safeBundle.getString("devSecretKey");
            String string3 = safeBundle.getString("randomID");
            Context context = this.t;
            String a2 = context instanceof Activity ? ksw.a((Activity) context) : "NONE";
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject.put("uuid", this.m.e());
            jSONObject.put("terminalType", this.m.b());
            jSONObject.put("deviceName", this.m.d());
            jSONObject.put("deviceType", this.m.c());
            jSONObject.put(DeviceInfo.TAG_DEVICE_ID, this.m.a());
            jSONObject.put("deviceAuthCode", string);
            jSONObject.put("loginStatus", "0");
            jSONObject.put("netType", a2);
            ksy.b("WebViewPresenter", "GetDevAuthCode ==" + jSONObject.toString(), false);
            if (TextUtils.isEmpty(string2)) {
                str = "";
            } else {
                str = j(string2, jSONObject.toString());
            }
            jSONObject2.put("factors", str);
            jSONObject2.put("randomID", string3);
            this.c.a(jSONObject2.toString());
        } catch (Exception unused) {
            ksy.b("WebViewPresenter", "JSONException", true);
            this.c.a(d("1", "9999"));
        }
    }

    public String d(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("factors", "");
            jSONObject.put("randomID", "");
            jSONObject.put("phonestateperm", str);
            jSONObject.put(VastAttribute.ERROR, str2);
        } catch (JSONException unused) {
            ksy.b("WebViewPresenter", "buildDefaultJson JSONException", true);
        }
        return jSONObject.toString();
    }

    private String j(String str, String str2) {
        try {
            return kqt.b(ksl.b(str2), ksl.b(str));
        } catch (InvalidAlgorithmParameterException unused) {
            ksy.c("WebViewPresenter", "NoSuchPaddingException ", true);
            return "";
        } catch (InvalidKeyException unused2) {
            ksy.c("WebViewPresenter", "InvalidKeyException ", true);
            return "";
        } catch (NoSuchAlgorithmException unused3) {
            ksy.c("WebViewPresenter", "NoSuchAlgorithmException ", true);
            return "";
        } catch (BadPaddingException unused4) {
            ksy.c("WebViewPresenter", "BadPaddingException ", true);
            return "";
        } catch (IllegalBlockSizeException unused5) {
            ksy.c("WebViewPresenter", "IllegalBlockSizeException ", true);
            return "";
        } catch (NoSuchPaddingException unused6) {
            ksy.c("WebViewPresenter", "NoSuchPaddingException ", true);
            return "";
        }
    }

    private void r() {
        s(s());
    }

    private String s() {
        if (this.m == null) {
            e(404, "parameter error DeviceInfo is null", "");
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        String i = i(this.m.a(), this.m.c());
        try {
            jSONObject.put("terminalType", this.m.b());
            jSONObject.put("deviceAliasName", this.m.d());
            jSONObject.put("deviceType", this.m.c());
            jSONObject.put(DeviceInfo.TAG_DEVICE_ID, this.m.a());
            if (!TextUtils.isEmpty(i)) {
                jSONObject.put("deviceID2", i);
            }
            jSONObject.put("uuid", this.m.e());
            Context context = this.t;
            jSONObject.put("netType", context instanceof Activity ? ksw.a((Activity) context) : "NONE");
        } catch (JSONException unused) {
            ksy.b("WebViewPresenter", "JSONException", true);
            e(404, "parameter error DeviceInfo is error", "");
        }
        ksy.b("WebViewPresenter", "getEDjson" + jSONObject.toString(), false);
        return jSONObject.toString();
    }

    private String i(String str, String str2) {
        return str2.equals("8") ? SHA.sha256Encrypt(str) : "";
    }

    private String p() {
        if ("from_qr_authorize".equals(this.i)) {
            ksy.b("WebViewPresenter", "mFrom is from_qr_authorize and deviceinfo is null", true);
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("terminalType", this.m.b());
            jSONObject.put("deviceAliasName", this.m.d());
        } catch (JSONException unused) {
            ksy.b("WebViewPresenter", "JSONException", true);
        }
        ksy.b("WebViewPresenter", "getDjson:" + jSONObject.toString(), false);
        return jSONObject.toString();
    }

    private String s(String str) {
        String p = ksi.p();
        if (!TextUtils.isEmpty(p)) {
            try {
                byte[] encrypt = RSAEncrypt.encrypt(t(str), EncryptUtil.getPublicKey(p));
                if (encrypt == null) {
                    ksy.c("WebViewPresenter", "rsaJsonBytes null", false);
                    return "";
                }
                this.q = SafeBase64.encodeToString(encrypt, 2);
                ksy.b("WebViewPresenter", "getJsonHeader  mRSAJsonStr " + this.q, false);
                return this.q;
            } catch (Exception e) {
                ksy.c("WebViewPresenter", "Exception:" + e.getClass().getSimpleName(), true);
            }
        }
        return "";
    }

    private byte[] t(String str) {
        byte[] bArr = new byte[0];
        if (TextUtils.isEmpty(str)) {
            ksy.c("WebViewPresenter", "data is empty", true);
            return bArr;
        }
        try {
            if (kst.i()) {
                return d(str.getBytes("UTF-8"));
            }
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            ksy.c("WebViewPresenter", "getBytes error" + e.getClass().getSimpleName(), true);
            return bArr;
        }
    }

    private byte[] d(byte[] bArr) {
        if (bArr != null && bArr.length >= 447) {
            ksy.b("WebViewPresenter", "the message is too long, need to compress data", true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(bArr);
                    gZIPOutputStream.finish();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    gZIPOutputStream.close();
                    return byteArray;
                } finally {
                }
            } catch (IOException unused) {
                ksy.c("WebViewPresenter", "failed to compress data", true);
            }
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(SafeBundle safeBundle) {
        ksy.b("WebViewPresenter", "handleRequestNet ==", true);
        ArrayList<String> stringArrayList = safeBundle.getStringArrayList("domainAllowList");
        ksi.g(safeBundle.getString("publicKey"));
        ksi.e(stringArrayList);
        if (TextUtils.isEmpty(this.i) || !"from_qr_authorize".equals(this.i)) {
            r();
        }
        c(false);
    }

    private void c(final boolean z) {
        ksy.b("WebViewPresenter", "setLoading", true);
        Context context = this.t;
        if (context instanceof WebViewActivity) {
            ((WebViewActivity) context).runOnUiThread(new Runnable() { // from class: ksc.2
                @Override // java.lang.Runnable
                public void run() {
                    ksc.this.c.a(z);
                }
            });
        }
    }

    private String q() {
        if (!this.ac) {
            return "accountPickerH5.signIn_v2";
        }
        ksy.b("WebViewPresenter", "IndependentAuthorization", true);
        return "independent_authorization";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00ff, code lost:
    
        if (r13.equals("scan_code_login") == false) goto L60;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(int r13, java.lang.String r14, java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 532
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ksc.e(int, java.lang.String, java.lang.String):void");
    }

    private void c(final int i, final String str) {
        ksy.b("WebViewPresenter", "handleFromPickerResult", true);
        ((WebViewActivity) this.t).runOnUiThread(new Runnable() { // from class: ksc.1
            @Override // java.lang.Runnable
            public void run() {
                if (200 == i) {
                    ksc.this.c.b(-1, ksc.this.h(str, "0"));
                    return;
                }
                ksc.this.c.b(0, ksc.this.h(str, i + ""));
            }
        });
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void c(kqm kqmVar, int i, String str) {
        char c;
        String str2 = this.i;
        str2.hashCode();
        switch (str2.hashCode()) {
            case -1899443177:
                if (str2.equals("from_open_realNameInfo")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -982451862:
                if (str2.equals("from_open_childInfo")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -313839168:
                if (str2.equals("from_qr_authorize")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 341052952:
                if (str2.equals("open_personal_info")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            c(ksi.f(), new kqs(kqmVar), 907115010, i, str, "accountPickerH5.openRealNameInfo");
            ksy.b("WebViewPresenter", "CONST_OPEN_REAL_NAME", true);
            return;
        }
        if (c == 1) {
            ResultCallBack l = ksi.l();
            if (kqmVar.e() == 10001401) {
                kqmVar = new kqm(2008, "AccessToken is invalid.");
            }
            c(l, new kqs(kqmVar), 907115012, i, str, "accountPickerH5.openChildAccountDetailInfo");
            ksy.b("WebViewPresenter", "CallBack CONST_OPEN_CHILD_INFO", true);
            return;
        }
        if (c == 2) {
            c(ksi.m(), new kqs(kqmVar), 907115011, i, str, "accountPickerH5.qrCodeAuthorize");
            ksy.b("WebViewPresenter", "CONST_QR_AUTHORIZE", true);
        } else if (c == 3) {
            c(ksi.i(), new kqs(kqmVar), 907115006, i, str, "accountPickerH5.openPersonalInfo");
            ksy.b("WebViewPresenter", "CONST_OPEN_PERSONAL_INFO", true);
        } else {
            ksy.b("WebViewPresenter", "dealOtherOper not match", true);
            this.c.b();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0039 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean i(java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 301
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ksc.i(java.lang.String):boolean");
    }

    private boolean p(String str) {
        if ("from_open_center_mng_new".equalsIgnoreCase(c()) && str.startsWith(a.a().a())) {
            ResultCallBack n = ksi.n();
            kqm kqmVar = new kqm(2008, "invalid at");
            kqmVar.b(false);
            kqq kqqVar = new kqq(kqmVar);
            if (n != null) {
                ksg.c(this.t, 907115004, 404, "access token is invalid", this.l, "accountPickerH5.openAccountManager_v3", "api_ret");
                n.onResult(kqqVar);
                this.c.b();
                return true;
            }
        }
        return false;
    }

    public boolean m(String str) {
        ksy.b("WebViewPresenter", "checkOverLoadRedirectUrlStart: url = " + str, false);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(this.j)) {
            return false;
        }
        if (!str.startsWith(this.j + "?")) {
            if (!str.startsWith(this.j + "&")) {
                return false;
            }
        }
        return true;
    }

    public boolean n(String str) {
        Bundle d;
        ksy.b("WebViewPresenter", "redirectUrl url:" + this.j + "?", false);
        if (m(str) && (d = ksi.d(str)) != null) {
            SafeBundle safeBundle = new SafeBundle(d);
            if (ksi.a(d)) {
                ksy.b("WebViewPresenter", "get code success", true);
                d(safeBundle);
                return true;
            }
            if (!TextUtils.isEmpty(d.getString(VastAttribute.ERROR, ""))) {
                ksy.b("WebViewPresenter", "get code error", true);
                d(safeBundle);
                return true;
            }
        }
        return false;
    }

    public void d(kqy kqyVar) {
        kqyVar.c(this.m);
    }

    public void e() {
        if (TextUtils.isEmpty(this.u)) {
            return;
        }
        String q = q(this.u);
        ksy.b("WebViewPresenter", "fillAccountJs = " + q, false);
        this.c.b(q);
    }

    private String q(String str) {
        return "javascript:fillAccount('1','" + EncodeUtil.encodeForJavaScript(str) + com.huawei.operation.utils.Constants.RIGHT_BRACKET;
    }

    private void d(SafeBundle safeBundle) {
        int i;
        ksy.b("WebViewPresenter", "parseCode", true);
        String string = safeBundle.getString("code");
        String string2 = safeBundle.getString("state");
        if (!TextUtils.isEmpty(string)) {
            ksy.b("WebViewPresenter", "get authorization_code success", true);
            e(200, string2, string);
            return;
        }
        ksy.b("WebViewPresenter", "get authorization_code error", true);
        String string3 = safeBundle.getString(VastAttribute.ERROR);
        String string4 = safeBundle.getString("sub_error");
        try {
            i = Integer.parseInt(string3);
        } catch (NumberFormatException unused) {
            ksy.c("WebViewPresenter", "NumberFormatException", true);
            i = 404;
        }
        String string5 = safeBundle.getString("error_description");
        ksy.b("WebViewPresenter", "get authorization_code errorCode " + i, true);
        ksy.b("WebViewPresenter", "get authorization_code subError " + string4, false);
        ksy.b("WebViewPresenter", "get authorization_code errorMessage " + string5, false);
        e(i, "sub_error " + string4 + " " + string5, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Intent h(String str, String str2) {
        ksy.b("WebViewPresenter", "getResultIntent", true);
        ksy.b("WebViewPresenter", "pickerSdk signIn return code", true);
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("statusCode", str2);
            jSONObject.put("status", jSONObject2);
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("serverAuthCode", str);
                jSONObject.put(CommonPickerConstant.RequestParams.KEY_SIGN_IN_HUAWEI_ID, jSONObject3);
            }
        } catch (JSONException unused) {
            ksy.c("WebViewPresenter", TrackConstants$Events.EXCEPTION, true);
        }
        Intent intent = new Intent();
        intent.putExtra("HUAWEIID_SIGNIN_RESULT", jSONObject.toString());
        return intent;
    }

    private String r(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("CUID", str);
        } catch (JSONException unused) {
            ksy.b("WebViewPresenter", "childUserIdToJson JSONException", true);
        }
        ksy.b("WebViewPresenter", "childUserIdToJson = " + jSONObject.toString(), false);
        return s(jSONObject.toString());
    }

    private String v() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("AT", this.h);
            jSONObject.put("AD", this.g);
            JSONObject jSONObject2 = new JSONObject();
            String i = i(this.m.a(), this.m.c());
            jSONObject2.put("terminalType", this.m.b());
            jSONObject2.put("deviceAliasName", this.m.d());
            jSONObject2.put("deviceType", this.m.c());
            jSONObject2.put(DeviceInfo.TAG_DEVICE_ID, this.m.a());
            if (!TextUtils.isEmpty(i)) {
                jSONObject2.put("deviceID2", this.m.a());
            }
            jSONObject2.put("uuid", this.m.e());
            jSONObject.put("ED", jSONObject2.toString());
            jSONObject.put("TY", 1);
            jSONObject.put("TS", System.currentTimeMillis());
        } catch (JSONException unused) {
            ksy.b("WebViewPresenter", "JSONException", true);
        }
        ksy.b("WebViewPresenter", "getExtClientInfo:" + jSONObject.toString(), false);
        return s(jSONObject.toString());
    }

    public String d(Context context) {
        String e = ksi.e(context);
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("ext_clientInfo=");
            sb.append(v());
            sb.append("&reqClientType=");
            sb.append(7);
            sb.append("&lang=");
            sb.append(URLEncoder.encode(e.toLowerCase(Locale.getDefault()), "UTF-8"));
            sb.append(kte.g(this.t));
            sb.append("&service=");
            sb.append(URLEncoder.encode(a.a().m() + "?" + kte.g(this.t), "UTF-8"));
            sb.append("&loginUrl=");
            sb.append(URLEncoder.encode(a.a().b() + "?reqClientType=7&loginChannel=7000000&displayType=2&clientID=" + this.g + "&lang=" + e.toLowerCase(Locale.getDefault()) + kte.g(this.t), "UTF-8"));
            sb.append("&loginChannel=");
            sb.append(GmsVersion.VERSION_ORLA);
            sb.append("&clientNonce=");
            sb.append(ksi.s());
        } catch (UnsupportedEncodingException unused) {
            sb = new StringBuilder();
        } catch (Exception unused2) {
            sb = new StringBuilder();
        }
        ksy.b("WebViewPresenter", "personalInfo：" + ((Object) sb), false);
        return sb.toString();
    }

    public String b(Context context) {
        String e = ksi.e(context);
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("ext_clientInfo=");
            sb.append(v());
            sb.append("&reqClientType=");
            sb.append(TextUtils.isEmpty(this.x) ? 7 : this.x);
            sb.append("&lang=");
            sb.append(URLEncoder.encode(e.toLowerCase(Locale.getDefault()), "UTF-8"));
            sb.append(kte.g(this.t));
            sb.append("&serviceType=");
            sb.append(2);
            sb.append("&service=");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(a.a().k());
            sb2.append("?lang=");
            sb2.append(e.toLowerCase(Locale.getDefault()));
            sb2.append("&loginChannel=7000000&reqClientType=");
            sb2.append(TextUtils.isEmpty(this.x) ? 7 : this.x);
            sb2.append("&isFromLiteSDK=true");
            sb2.append(kte.g(this.t));
            sb.append(URLEncoder.encode(sb2.toString(), "UTF-8"));
            sb.append("&loginChannel=");
            sb.append(GmsVersion.VERSION_ORLA);
            sb.append("&clientNonce=");
            sb.append(ksi.s());
        } catch (UnsupportedEncodingException unused) {
            sb = new StringBuilder();
        } catch (Exception unused2) {
            sb = new StringBuilder();
        }
        ksy.b("WebViewPresenter", "realNameInfo：" + ((Object) sb), false);
        return sb.toString();
    }

    public String a(Context context) {
        String e = ksi.e(context);
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("ext_clientInfo=");
            sb.append(v());
            sb.append("&reqClientType=");
            sb.append(TextUtils.isEmpty(this.x) ? 7 : this.x);
            sb.append("&lang=");
            sb.append(URLEncoder.encode(e.toLowerCase(Locale.getDefault()), "UTF-8"));
            sb.append(kte.g(this.t));
            sb.append("&serviceType=");
            sb.append(6);
            sb.append("&service=");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(a.a().l());
            sb2.append("?childInfo=");
            sb2.append(r(this.ab));
            sb2.append("&lang=");
            sb2.append(e.toLowerCase(Locale.getDefault()));
            sb2.append("&loginChannel=7000000&reqClientType=");
            sb2.append(TextUtils.isEmpty(this.x) ? 7 : this.x);
            sb2.append("&isFromLiteSDK=true");
            sb2.append(kte.g(this.t));
            sb.append(URLEncoder.encode(sb2.toString(), "UTF-8"));
            sb.append("&loginChannel=");
            sb.append(GmsVersion.VERSION_ORLA);
            sb.append("&clientNonce=");
            sb.append(ksi.s());
        } catch (UnsupportedEncodingException unused) {
            sb = new StringBuilder();
        } catch (Exception unused2) {
            sb = new StringBuilder();
        }
        ksy.b("WebViewPresenter", "getCloudChildInfo：" + sb.toString(), false);
        return sb.toString();
    }

    public String e(Context context) {
        return this.e + "?user_code=" + this.d + "&cVersion=HwID_6.12.0.302" + kte.g(context);
    }

    public boolean l(String str) {
        ksy.b("WebViewPresenter", "enter checkSignInService", true);
        Bundle d = ksi.d(str);
        if (d == null) {
            return false;
        }
        SafeBundle safeBundle = new SafeBundle(d);
        if ("pickerSignIn".equals(this.z)) {
            e(200, safeBundle.getString("state"), safeBundle.getString("code"));
            return true;
        }
        if (safeBundle.containsKey("isNotHuaweiId") && "1".equals(safeBundle.getString("isNotHuaweiId", "0"))) {
            String string = safeBundle.getString("ticket", "");
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            Intent intent = new Intent();
            intent.putExtra("HUAWEIID_SIGNIN_RESULT", a(e(null, null, null, string), true));
            this.c.a(-1, intent);
            ksy.b("WebViewPresenter", "checkSignInService get ticket exit", true);
            return true;
        }
        String string2 = safeBundle.getString("code", "");
        String string3 = safeBundle.getString("state", "");
        String string4 = safeBundle.getString("risks", "");
        if (TextUtils.isEmpty(string2)) {
            return false;
        }
        Intent intent2 = new Intent();
        intent2.putExtra("HUAWEIID_SIGNIN_RESULT", a(e(string2, string3, string4, null), true));
        this.c.a(-1, intent2);
        ksy.b("WebViewPresenter", "checkSignInService get code exit", true);
        return true;
    }

    private void w(String str) {
        ksy.b("WebViewPresenter", "saveGetAuthorizationCodeFlag mIsIndependentAuthorization = " + this.ac, true);
        if (TextUtils.isEmpty(str) || this.ac) {
            return;
        }
        krn.c(this.t).d("getSignInCode", true);
        krn.c(this.t).b("getSignInCodeTime", System.currentTimeMillis());
    }

    private void c(int i, String str, String str2, String str3) {
        ksy.b("WebViewPresenter", "packingResult stateCode = " + i + "--statusMessage = " + str + "--authorizationCode =" + str2, false);
        Intent intent = new Intent();
        if (i == 200) {
            if (f()) {
                w(str2);
            }
            intent.putExtra("HUAWEIID_SIGNIN_RESULT", a(e(str2, null, null, null), true));
            this.c.a(-1, intent);
            ksg.c(this.t, 907115001, 200, str, this.l, str3, "api_ret");
            return;
        }
        intent.putExtra("HUAWEIID_SIGNIN_RESULT", a(d(i, str), false));
        this.c.a(0, intent);
        ksg.c(this.t, 907115001, 404, str, this.l, str3, "api_ret");
    }

    private JSONObject d(int i, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("stateCode", i);
            jSONObject.put("stateMessage", str);
        } catch (JSONException unused) {
            ksy.c("WebViewPresenter", "getFailJsonObject JSONException", true);
        } catch (Exception unused2) {
            ksy.c("WebViewPresenter", "getFailJsonObject Exception", true);
        }
        return jSONObject;
    }

    private String a(JSONObject jSONObject, boolean z) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(CommonPickerConstant.RequestParams.KEY_SIGN_IN_HUAWEI_ID, jSONObject);
            jSONObject2.put("result", z);
        } catch (JSONException unused) {
            ksy.c("WebViewPresenter", "getSignInResult JSONException", true);
        } catch (Exception unused2) {
            ksy.c("WebViewPresenter", "getSignInResult Exception", true);
        }
        return jSONObject2.toString();
    }

    private JSONObject e(String str, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("code", str);
                jSONObject.put("state", str2);
                jSONObject.put("risks", str3);
            } else if (!TextUtils.isEmpty(str4)) {
                jSONObject.put("ticket", str4);
            }
        } catch (JSONException unused) {
            ksy.c("WebViewPresenter", "getSucJsonObject JSONException", true);
        } catch (Exception unused2) {
            ksy.c("WebViewPresenter", "getSucJsonObject Exception", true);
        }
        return jSONObject;
    }

    private void c(ResultCallBack resultCallBack, Result result, int i, int i2, String str, String str2) {
        if (resultCallBack != null) {
            resultCallBack.onResult(result);
            ksg.c(this.t, i, i2, str, this.l, str2, "api_ret");
        } else {
            ksg.c(this.t, i, 404, "callBack is null", this.l, str2, "api_ret");
        }
        this.c.b();
    }

    public String j() {
        return this.l;
    }

    public String k(String str) {
        ksy.b("WebViewPresenter", "getDeviceInfo", true);
        if (ksl.c(str)) {
            ksy.b("WebViewPresenter", "getEdJsonStr", true);
            return k();
        }
        ksy.b("WebViewPresenter", "getDJsonStr", true);
        return n();
    }

    public boolean g() {
        return "from_other_app_signin".equalsIgnoreCase(this.i);
    }

    public boolean f() {
        return "from_v3_signin".equalsIgnoreCase(this.i);
    }

    public String i() {
        return this.j;
    }

    public String h() {
        return this.z;
    }

    public boolean m() {
        try {
            return ((Boolean) Class.forName("android.app.Activity").getMethod("isInMultiWindowMode", new Class[0]).invoke(this, new Object[0])).booleanValue();
        } catch (ClassNotFoundException unused) {
            ksy.b("WebViewPresenter", "ClassNotFoundException", true);
            return false;
        } catch (IllegalAccessException unused2) {
            ksy.b("WebViewPresenter", "IllegalAccessException", true);
            return false;
        } catch (IllegalArgumentException unused3) {
            ksy.b("WebViewPresenter", "IllegalArgumentException", true);
            return false;
        } catch (NoSuchMethodException unused4) {
            ksy.b("WebViewPresenter", "NoSuchMethodException", true);
            return false;
        } catch (RuntimeException unused5) {
            ksy.b("WebViewPresenter", "RuntimeException", true);
            return false;
        } catch (InvocationTargetException unused6) {
            ksy.b("WebViewPresenter", "InvocationTargetException", true);
            return false;
        } catch (Exception unused7) {
            ksy.b("WebViewPresenter", "Exception", true);
            return false;
        }
    }

    public boolean a(int[] iArr) {
        if (iArr == null || iArr.length <= 0) {
            return false;
        }
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean a(int i) {
        if (bQD_((Activity) this.t, new String[]{"android.permission.CAMERA"}, i)) {
            ksy.b("WebViewPresenter", "checkCameraPermission return true", true);
            return true;
        }
        ksy.b("WebViewPresenter", "checkCameraPermission return false", true);
        return false;
    }

    public boolean d(int i) {
        if (bQD_((Activity) this.t, new String[]{ksi.d()}, i)) {
            ksy.b("WebViewPresenter", "checkSDPermission return true", true);
            return true;
        }
        ksy.b("WebViewPresenter", "checkSDPermission return false", true);
        return false;
    }

    private boolean bQD_(Activity activity, String[] strArr, int i) {
        if (activity == null || strArr == null || strArr.length == 0) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (activity.checkSelfPermission(str) != 0) {
                arrayList.add(str);
            }
        }
        if (arrayList.size() <= 0) {
            return true;
        }
        activity.requestPermissions((String[]) arrayList.toArray(new String[arrayList.size()]), i);
        return false;
    }

    public String d(SafeWebView safeWebView, String str) {
        ksy.b("WebViewPresenter", "buildAuthInfo", true);
        if (!safeWebView.isWhiteListUrl(a.a().e())) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            DeviceInfo deviceInfo = this.m;
            if (deviceInfo != null) {
                jSONObject.put("deviceType", deviceInfo.c());
                jSONObject.put("deviceId", this.m.a());
                jSONObject.put("uuid", this.m.e());
            }
            jSONObject.put("packageName", str);
            jSONObject.put("reqClientType", "");
            jSONObject.put("loginChannel", "90000100");
            if (!TextUtils.isEmpty(this.h)) {
                jSONObject.put("accessToken", this.h);
            }
            jSONObject.put("tokenType", 1);
            ksy.b("WebViewPresenter", "mAuthInfoString", true);
            String jSONObject2 = jSONObject.toString();
            ksy.b("WebViewPresenter", "buildAuthInfo: json string = " + jSONObject2, false);
            return jSONObject2;
        } catch (JSONException unused) {
            ksy.c("WebViewPresenter", "JSONException", true);
            return null;
        }
    }

    public String o(String str) {
        ksy.b("WebViewPresenter", "the loginUrl is:" + str, false);
        if (TextUtils.isEmpty(str) || !str.contains(AuthInternalPickerConstant.EXT_CLIENTINFO_STUB)) {
            ksy.b("WebViewPresenter", "the loginUrl is empty or loginUrl not contains ext_clientInfo", true);
            return str;
        }
        String u = u(str);
        ksy.b("WebViewPresenter", "the deviceInfo is:" + u, false);
        return str.replace(AuthInternalPickerConstant.EXT_CLIENTINFO_STUB, "ext_clientInfo=" + u);
    }

    private String u(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (ksl.c(str)) {
                String i = i(this.m.a(), this.m.c());
                jSONObject.put("AD", this.g);
                jSONObject2.put("deviceType", this.m.c());
                jSONObject2.put(DeviceInfo.TAG_DEVICE_ID, this.m.a());
                jSONObject2.put("uuid", this.m.e());
                if (!TextUtils.isEmpty(i)) {
                    jSONObject2.put("deviceID2", i);
                }
            }
            jSONObject2.put("terminalType", this.m.b());
            jSONObject2.put("deviceAliasName", this.m.d());
            jSONObject.put("ED", jSONObject2.toString());
            jSONObject.put("TY", 5);
            jSONObject.put("TS", System.currentTimeMillis());
        } catch (JSONException unused) {
            ksy.b("WebViewPresenter", "JSONException", true);
        }
        ksy.b("WebViewPresenter", "getSignInExtClientInfo:" + jSONObject.toString(), false);
        return s(jSONObject.toString());
    }
}
