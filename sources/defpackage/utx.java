package defpackage;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.tencent.connect.common.Constants;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class utx {
    private static final Set<String> l = Collections.unmodifiableSet(new HashSet(Arrays.asList("client_id", "code", "code_verifier", "grant_type", CommonConstant.ReqAccessTokenParam.REDIRECT_URI, "refresh_token", "scope")));

    /* renamed from: a, reason: collision with root package name */
    public final String f17554a;
    public final Map<String, String> b;
    public final String c;
    public final String d;
    public final AuthorizationServiceConfiguration e;
    public final String f;
    public final String g;
    public final String h;
    public final Uri i;
    public final String j;

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        private String f17555a;
        private String b;
        private AuthorizationServiceConfiguration c;
        private Map<String, String> d;
        private String e;
        private String f;
        private String g;
        private String h;
        private String i;
        private Uri j;

        public c(AuthorizationServiceConfiguration authorizationServiceConfiguration, String str) {
            a(authorizationServiceConfiguration);
            d(str);
            this.d = new LinkedHashMap();
        }

        public c a(AuthorizationServiceConfiguration authorizationServiceConfiguration) {
            this.c = (AuthorizationServiceConfiguration) utq.a(authorizationServiceConfiguration);
            return this;
        }

        public c d(String str) {
            this.b = utq.d(str, (Object) "clientId cannot be null or empty");
            return this;
        }

        public c c(String str) {
            if (TextUtils.isEmpty(str)) {
                this.g = null;
            } else {
                this.g = str;
            }
            return this;
        }

        public c e(String str) {
            this.f = utq.d(str, (Object) "grantType cannot be null or empty");
            return this;
        }

        public c fgr_(Uri uri) {
            if (uri != null) {
                utq.d(uri.getScheme(), (Object) "redirectUri must have a scheme");
            }
            this.j = uri;
            return this;
        }

        public c i(String str) {
            if (TextUtils.isEmpty(str)) {
                this.h = null;
            } else {
                b(str.split(" +"));
            }
            return this;
        }

        public c b(String... strArr) {
            if (strArr == null) {
                strArr = new String[0];
            }
            e(Arrays.asList(strArr));
            return this;
        }

        public c e(Iterable<String> iterable) {
            this.h = utj.a(iterable);
            return this;
        }

        public c b(String str) {
            utq.e(str, "authorization code must not be empty");
            this.f17555a = str;
            return this;
        }

        public c g(String str) {
            if (str != null) {
                utq.d(str, (Object) "refresh token cannot be empty if defined");
            }
            this.i = str;
            return this;
        }

        public c a(String str) {
            if (str != null) {
                utm.a(str);
            }
            this.e = str;
            return this;
        }

        public c a(Map<String, String> map) {
            this.d = utc.e(map, utx.l);
            return this;
        }

        public utx b() {
            String c = c();
            if ("authorization_code".equals(c)) {
                utq.d(this.f17555a, (Object) "authorization code must be specified for grant_type = authorization_code");
            }
            if ("refresh_token".equals(c)) {
                utq.d(this.i, (Object) "refresh token must be specified for grant_type = refresh_token");
            }
            if (c.equals("authorization_code") && this.j == null) {
                throw new IllegalStateException("no redirect URI specified on token request for code exchange");
            }
            return new utx(this.c, this.b, this.g, c, this.j, this.h, this.f17555a, this.i, this.e, Collections.unmodifiableMap(this.d));
        }

        private String c() {
            String str = this.f;
            if (str != null) {
                return str;
            }
            if (this.f17555a != null) {
                return "authorization_code";
            }
            if (this.i != null) {
                return "refresh_token";
            }
            throw new IllegalStateException("grant type not specified and cannot be inferred");
        }
    }

    private utx(AuthorizationServiceConfiguration authorizationServiceConfiguration, String str, String str2, String str3, Uri uri, String str4, String str5, String str6, String str7, Map<String, String> map) {
        this.e = authorizationServiceConfiguration;
        this.c = str;
        this.j = str2;
        this.h = str3;
        this.i = uri;
        this.g = str4;
        this.d = str5;
        this.f = str6;
        this.f17554a = str7;
        this.b = map;
    }

    public Map<String, String> a() {
        HashMap hashMap = new HashMap();
        hashMap.put("grant_type", this.h);
        d(hashMap, CommonConstant.ReqAccessTokenParam.REDIRECT_URI, this.i);
        d(hashMap, "code", this.d);
        d(hashMap, "refresh_token", this.f);
        d(hashMap, "code_verifier", this.f17554a);
        d(hashMap, "scope", this.g);
        for (Map.Entry<String, String> entry : this.b.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue());
        }
        return hashMap;
    }

    private void d(Map<String, String> map, String str, Object obj) {
        if (obj != null) {
            map.put(str, obj.toString());
        }
    }

    public JSONObject e() {
        JSONObject jSONObject = new JSONObject();
        JsonUtil.c(jSONObject, "configuration", this.e.e());
        JsonUtil.e(jSONObject, "clientId", this.c);
        JsonUtil.b(jSONObject, Constants.NONCE, this.j);
        JsonUtil.e(jSONObject, "grantType", this.h);
        JsonUtil.fgp_(jSONObject, "redirectUri", this.i);
        JsonUtil.b(jSONObject, "scope", this.g);
        JsonUtil.b(jSONObject, "authorizationCode", this.d);
        JsonUtil.b(jSONObject, FaqConstants.FAQ_REFRESH, this.f);
        JsonUtil.b(jSONObject, "codeVerifier", this.f17554a);
        JsonUtil.c(jSONObject, "additionalParameters", JsonUtil.c(this.b));
        return jSONObject;
    }

    public String b() {
        return e().toString();
    }

    public static utx c(JSONObject jSONObject) throws JSONException {
        utq.d(jSONObject, "json object cannot be null");
        return new utx(AuthorizationServiceConfiguration.d(jSONObject.getJSONObject("configuration")), JsonUtil.a(jSONObject, "clientId"), JsonUtil.d(jSONObject, Constants.NONCE), JsonUtil.a(jSONObject, "grantType"), JsonUtil.fgo_(jSONObject, "redirectUri"), JsonUtil.d(jSONObject, "scope"), JsonUtil.d(jSONObject, "authorizationCode"), JsonUtil.d(jSONObject, FaqConstants.FAQ_REFRESH), JsonUtil.d(jSONObject, "codeVerifier"), JsonUtil.g(jSONObject, "additionalParameters"));
    }
}
