package defpackage;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.tencent.connect.common.Constants;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.openid.appauth.AuthorizationManagementRequest;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class uth implements AuthorizationManagementRequest {
    private static final Set<String> p = utc.b("client_id", "code_challenge", "code_challenge_method", "display", "login_hint", "prompt", "ui_locales", CommonConstant.ReqAccessTokenParam.REDIRECT_URI, "response_mode", CommonConstant.ReqAccessTokenParam.RESPONSE_TYPE, "scope", "state", "claims", "claims_locales");

    /* renamed from: a, reason: collision with root package name */
    public final JSONObject f17543a;
    public final Map<String, String> b;
    public final String c;
    public final String d;
    public final String e;
    public final AuthorizationServiceConfiguration f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final Uri m;
    public final String n;
    public final String o;
    public final String q;
    public final String r;
    public final String t;

    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        private JSONObject f17544a;
        private String b;
        private Map<String, String> c = new HashMap();
        private String d;
        private String e;
        private AuthorizationServiceConfiguration f;
        private String g;
        private String h;
        private String i;
        private String j;
        private String k;
        private Uri l;
        private String m;
        private String n;
        private String o;
        private String p;
        private String s;
        private String t;

        public d(AuthorizationServiceConfiguration authorizationServiceConfiguration, String str, String str2, Uri uri) {
            a(authorizationServiceConfiguration);
            a(str);
            b(str2);
            fgd_(uri);
            j(utk.a());
            d(utk.a());
            c(utm.c());
        }

        public d a(AuthorizationServiceConfiguration authorizationServiceConfiguration) {
            this.f = (AuthorizationServiceConfiguration) utq.d(authorizationServiceConfiguration, "configuration cannot be null");
            return this;
        }

        public d a(String str) {
            this.d = utq.d(str, (Object) "client ID cannot be null or empty");
            return this;
        }

        public d e(String str) {
            this.n = utq.e(str, "prompt must be null or non-empty");
            return this;
        }

        public d i(String str) {
            this.p = utq.e(str, "uiLocales must be null or not empty");
            return this;
        }

        public d b(String str) {
            this.o = utq.d(str, (Object) "expected response type cannot be null or empty");
            return this;
        }

        public d fgd_(Uri uri) {
            this.l = (Uri) utq.d(uri, "redirect URI cannot be null or empty");
            return this;
        }

        public d h(String str) {
            if (TextUtils.isEmpty(str)) {
                this.s = null;
            } else {
                b(str.split(" +"));
            }
            return this;
        }

        public d b(String... strArr) {
            if (strArr == null) {
                strArr = new String[0];
            }
            d(Arrays.asList(strArr));
            return this;
        }

        public d d(Iterable<String> iterable) {
            this.s = utj.a(iterable);
            return this;
        }

        public d j(String str) {
            this.t = utq.e(str, "state cannot be empty if defined");
            return this;
        }

        public d d(String str) {
            this.k = utq.e(str, "nonce cannot be empty if defined");
            return this;
        }

        public d c(String str) {
            if (str != null) {
                utm.a(str);
                this.e = str;
                this.j = utm.e(str);
                this.g = utm.b();
            } else {
                this.e = null;
                this.j = null;
                this.g = null;
            }
            return this;
        }

        public d d(Map<String, String> map) {
            this.c = utc.e(map, uth.p);
            return this;
        }

        public uth e() {
            return new uth(this.f, this.d, this.o, this.l, this.h, this.i, this.n, this.p, this.s, this.t, this.k, this.e, this.j, this.g, this.m, this.f17544a, this.b, Collections.unmodifiableMap(new HashMap(this.c)));
        }
    }

    private uth(AuthorizationServiceConfiguration authorizationServiceConfiguration, String str, String str2, Uri uri, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, JSONObject jSONObject, String str14, Map<String, String> map) {
        this.f = authorizationServiceConfiguration;
        this.d = str;
        this.n = str2;
        this.m = uri;
        this.b = map;
        this.j = str3;
        this.i = str4;
        this.l = str5;
        this.t = str6;
        this.q = str7;
        this.r = str8;
        this.k = str9;
        this.e = str10;
        this.h = str11;
        this.g = str12;
        this.o = str13;
        this.f17543a = jSONObject;
        this.c = str14;
    }

    @Override // net.openid.appauth.AuthorizationManagementRequest
    public String getState() {
        return this.r;
    }

    @Override // net.openid.appauth.AuthorizationManagementRequest
    public Uri toUri() {
        Uri.Builder appendQueryParameter = this.f.f15278a.buildUpon().appendQueryParameter(CommonConstant.ReqAccessTokenParam.REDIRECT_URI, this.m.toString()).appendQueryParameter("client_id", this.d).appendQueryParameter(CommonConstant.ReqAccessTokenParam.RESPONSE_TYPE, this.n);
        uuh.fgw_(appendQueryParameter, "display", this.j);
        uuh.fgw_(appendQueryParameter, "login_hint", this.i);
        uuh.fgw_(appendQueryParameter, "prompt", this.l);
        uuh.fgw_(appendQueryParameter, "ui_locales", this.t);
        uuh.fgw_(appendQueryParameter, "state", this.r);
        uuh.fgw_(appendQueryParameter, Constants.NONCE, this.k);
        uuh.fgw_(appendQueryParameter, "scope", this.q);
        uuh.fgw_(appendQueryParameter, "response_mode", this.o);
        if (this.e != null) {
            appendQueryParameter.appendQueryParameter("code_challenge", this.h).appendQueryParameter("code_challenge_method", this.g);
        }
        uuh.fgw_(appendQueryParameter, "claims", this.f17543a);
        uuh.fgw_(appendQueryParameter, "claims_locales", this.c);
        for (Map.Entry<String, String> entry : this.b.entrySet()) {
            appendQueryParameter.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        return appendQueryParameter.build();
    }

    @Override // net.openid.appauth.AuthorizationManagementRequest
    public JSONObject jsonSerialize() {
        JSONObject jSONObject = new JSONObject();
        JsonUtil.c(jSONObject, "configuration", this.f.e());
        JsonUtil.e(jSONObject, "clientId", this.d);
        JsonUtil.e(jSONObject, "responseType", this.n);
        JsonUtil.e(jSONObject, "redirectUri", this.m.toString());
        JsonUtil.b(jSONObject, "display", this.j);
        JsonUtil.b(jSONObject, "login_hint", this.i);
        JsonUtil.b(jSONObject, "scope", this.q);
        JsonUtil.b(jSONObject, "prompt", this.l);
        JsonUtil.b(jSONObject, "ui_locales", this.t);
        JsonUtil.b(jSONObject, "state", this.r);
        JsonUtil.b(jSONObject, Constants.NONCE, this.k);
        JsonUtil.b(jSONObject, "codeVerifier", this.e);
        JsonUtil.b(jSONObject, "codeVerifierChallenge", this.h);
        JsonUtil.b(jSONObject, "codeVerifierChallengeMethod", this.g);
        JsonUtil.b(jSONObject, "responseMode", this.o);
        JsonUtil.a(jSONObject, "claims", this.f17543a);
        JsonUtil.b(jSONObject, "claimsLocales", this.c);
        JsonUtil.c(jSONObject, "additionalParameters", JsonUtil.c(this.b));
        return jSONObject;
    }

    @Override // net.openid.appauth.AuthorizationManagementRequest
    public String jsonSerializeString() {
        return jsonSerialize().toString();
    }

    public static uth b(JSONObject jSONObject) throws JSONException {
        utq.d(jSONObject, "json cannot be null");
        return new uth(AuthorizationServiceConfiguration.d(jSONObject.getJSONObject("configuration")), JsonUtil.a(jSONObject, "clientId"), JsonUtil.a(jSONObject, "responseType"), JsonUtil.fgn_(jSONObject, "redirectUri"), JsonUtil.d(jSONObject, "display"), JsonUtil.d(jSONObject, "login_hint"), JsonUtil.d(jSONObject, "prompt"), JsonUtil.d(jSONObject, "ui_locales"), JsonUtil.d(jSONObject, "scope"), JsonUtil.d(jSONObject, "state"), JsonUtil.d(jSONObject, Constants.NONCE), JsonUtil.d(jSONObject, "codeVerifier"), JsonUtil.d(jSONObject, "codeVerifierChallenge"), JsonUtil.d(jSONObject, "codeVerifierChallengeMethod"), JsonUtil.d(jSONObject, "responseMode"), JsonUtil.e(jSONObject, "claims"), JsonUtil.d(jSONObject, "claimsLocales"), JsonUtil.g(jSONObject, "additionalParameters"));
    }
}
