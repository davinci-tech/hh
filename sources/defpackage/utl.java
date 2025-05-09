package defpackage;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import defpackage.utx;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import net.openid.appauth.AuthorizationManagementResponse;
import net.openid.appauth.Clock;
import net.openid.appauth.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class utl extends AuthorizationManagementResponse {
    private static final Set<String> f = Collections.unmodifiableSet(new HashSet(Arrays.asList("token_type", "state", "code", Constants.PARAM_ACCESS_TOKEN, Constants.PARAM_EXPIRES_IN, "id_token", "scope")));

    /* renamed from: a, reason: collision with root package name */
    public final String f17546a;
    public final String b;
    public final String c;
    public final Long d;
    public final Map<String, String> e;
    public final uth g;
    public final String h;
    public final String i;
    public final String j;

    /* loaded from: classes10.dex */
    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        private String f17547a;
        private String b;
        private String c;
        private Long d;
        private Map<String, String> e = new LinkedHashMap();
        private uth f;
        private String h;
        private String i;
        private String j;

        public e(uth uthVar) {
            this.f = (uth) utq.d(uthVar, "authorization request cannot be null");
        }

        public e fgf_(Uri uri) {
            return fgg_(uri, utw.d);
        }

        e fgg_(Uri uri, Clock clock) {
            c(uri.getQueryParameter("state"));
            i(uri.getQueryParameter("token_type"));
            a(uri.getQueryParameter("code"));
            b(uri.getQueryParameter(Constants.PARAM_ACCESS_TOKEN));
            b(uuh.fgx_(uri, Constants.PARAM_EXPIRES_IN), clock);
            d(uri.getQueryParameter("id_token"));
            e(uri.getQueryParameter("scope"));
            a(utc.ffS_(uri, utl.f));
            return this;
        }

        public e c(String str) {
            utq.e(str, "state must not be empty");
            this.j = str;
            return this;
        }

        public e i(String str) {
            utq.e(str, "tokenType must not be empty");
            this.i = str;
            return this;
        }

        public e a(String str) {
            utq.e(str, "authorizationCode must not be empty");
            this.f17547a = str;
            return this;
        }

        public e b(String str) {
            utq.e(str, "accessToken must not be empty");
            this.c = str;
            return this;
        }

        public e b(Long l, Clock clock) {
            if (l == null) {
                this.d = null;
            } else {
                this.d = Long.valueOf(clock.getCurrentTimeMillis() + TimeUnit.SECONDS.toMillis(l.longValue()));
            }
            return this;
        }

        public e d(String str) {
            utq.e(str, "idToken cannot be empty");
            this.b = str;
            return this;
        }

        public e e(String str) {
            if (TextUtils.isEmpty(str)) {
                this.h = null;
            } else {
                a(str.split(" +"));
            }
            return this;
        }

        public e a(String... strArr) {
            if (strArr == null) {
                this.h = null;
            } else {
                a(Arrays.asList(strArr));
            }
            return this;
        }

        public e a(Iterable<String> iterable) {
            this.h = utj.a(iterable);
            return this;
        }

        public e a(Map<String, String> map) {
            this.e = utc.e(map, utl.f);
            return this;
        }

        public utl b() {
            return new utl(this.f, this.j, this.i, this.f17547a, this.c, this.d, this.b, this.h, Collections.unmodifiableMap(this.e));
        }
    }

    private utl(uth uthVar, String str, String str2, String str3, String str4, Long l, String str5, String str6, Map<String, String> map) {
        this.g = uthVar;
        this.h = str;
        this.j = str2;
        this.f17546a = str3;
        this.c = str4;
        this.d = l;
        this.b = str5;
        this.i = str6;
        this.e = map;
    }

    public utx d() {
        return d(Collections.emptyMap());
    }

    public utx d(Map<String, String> map) {
        utq.d(map, "additionalExchangeParameters cannot be null");
        if (this.f17546a == null) {
            throw new IllegalStateException("authorizationCode not available for exchange request");
        }
        return new utx.c(this.g.f, this.g.d).e("authorization_code").fgr_(this.g.m).a(this.g.e).b(this.f17546a).a(map).c(this.g.k).b();
    }

    @Override // net.openid.appauth.AuthorizationManagementResponse
    public String getState() {
        return this.h;
    }

    @Override // net.openid.appauth.AuthorizationManagementResponse
    public JSONObject jsonSerialize() {
        JSONObject jSONObject = new JSONObject();
        JsonUtil.c(jSONObject, "request", this.g.jsonSerialize());
        JsonUtil.b(jSONObject, "state", this.h);
        JsonUtil.b(jSONObject, "token_type", this.j);
        JsonUtil.b(jSONObject, "code", this.f17546a);
        JsonUtil.b(jSONObject, Constants.PARAM_ACCESS_TOKEN, this.c);
        JsonUtil.d(jSONObject, "expires_at", this.d);
        JsonUtil.b(jSONObject, "id_token", this.b);
        JsonUtil.b(jSONObject, "scope", this.i);
        JsonUtil.c(jSONObject, "additional_parameters", JsonUtil.c(this.e));
        return jSONObject;
    }

    public static utl d(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("request")) {
            throw new IllegalArgumentException("authorization request not provided and not found in JSON");
        }
        return new utl(uth.b(jSONObject.getJSONObject("request")), JsonUtil.d(jSONObject, "state"), JsonUtil.d(jSONObject, "token_type"), JsonUtil.d(jSONObject, "code"), JsonUtil.d(jSONObject, Constants.PARAM_ACCESS_TOKEN), JsonUtil.b(jSONObject, "expires_at"), JsonUtil.d(jSONObject, "id_token"), JsonUtil.d(jSONObject, "scope"), JsonUtil.g(jSONObject, "additional_parameters"));
    }

    public static utl a(String str) throws JSONException {
        return d(new JSONObject(str));
    }

    @Override // net.openid.appauth.AuthorizationManagementResponse
    public Intent toIntent() {
        Intent intent = new Intent();
        intent.putExtra("net.openid.appauth.AuthorizationResponse", jsonSerializeString());
        return intent;
    }

    public static utl fge_(Intent intent) {
        utq.d(intent, "dataIntent must not be null");
        if (!intent.hasExtra("net.openid.appauth.AuthorizationResponse")) {
            return null;
        }
        try {
            return a(intent.getStringExtra("net.openid.appauth.AuthorizationResponse"));
        } catch (JSONException e2) {
            throw new IllegalArgumentException("Intent contains malformed auth response", e2);
        }
    }
}
