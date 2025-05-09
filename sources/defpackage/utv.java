package defpackage;

import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import net.openid.appauth.Clock;
import net.openid.appauth.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class utv {
    private static final Set<String> h = new HashSet(Arrays.asList("token_type", Constants.PARAM_ACCESS_TOKEN, Constants.PARAM_EXPIRES_IN, "refresh_token", "id_token", "scope"));

    /* renamed from: a, reason: collision with root package name */
    public final String f17552a;
    public final Map<String, String> b;
    public final Long c;
    public final String d;
    public final String e;
    public final utx f;
    public final String i;
    public final String j;

    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        private String f17553a;
        private String b;
        private Long c;
        private String d;
        private Map<String, String> e;
        private utx g;
        private String i;
        private String j;

        public e(utx utxVar) {
            d(utxVar);
            this.e = Collections.emptyMap();
        }

        public e e(JSONObject jSONObject) throws JSONException {
            b(JsonUtil.a(jSONObject, "token_type"));
            a(JsonUtil.d(jSONObject, Constants.PARAM_ACCESS_TOKEN));
            e(JsonUtil.b(jSONObject, "expires_at"));
            if (jSONObject.has(Constants.PARAM_EXPIRES_IN)) {
                b(Long.valueOf(jSONObject.getLong(Constants.PARAM_EXPIRES_IN)));
            }
            d(JsonUtil.d(jSONObject, "refresh_token"));
            c(JsonUtil.d(jSONObject, "id_token"));
            e(JsonUtil.d(jSONObject, "scope"));
            a(utc.b(jSONObject, utv.h));
            return this;
        }

        public e d(utx utxVar) {
            this.g = (utx) utq.d(utxVar, "request cannot be null");
            return this;
        }

        public e b(String str) {
            this.i = utq.e(str, "token type must not be empty if defined");
            return this;
        }

        public e a(String str) {
            this.f17553a = utq.e(str, "access token cannot be empty if specified");
            return this;
        }

        public e b(Long l) {
            return a(l, utw.d);
        }

        e a(Long l, Clock clock) {
            if (l == null) {
                this.c = null;
            } else {
                this.c = Long.valueOf(clock.getCurrentTimeMillis() + TimeUnit.SECONDS.toMillis(l.longValue()));
            }
            return this;
        }

        public e e(Long l) {
            this.c = l;
            return this;
        }

        public e c(String str) {
            this.b = utq.e(str, "id token must not be empty if defined");
            return this;
        }

        public e d(String str) {
            this.d = utq.e(str, "refresh token must not be empty if defined");
            return this;
        }

        public e e(String str) {
            if (TextUtils.isEmpty(str)) {
                this.j = null;
            } else {
                a(str.split(" +"));
            }
            return this;
        }

        public e a(String... strArr) {
            if (strArr == null) {
                strArr = new String[0];
            }
            a(Arrays.asList(strArr));
            return this;
        }

        public e a(Iterable<String> iterable) {
            this.j = utj.a(iterable);
            return this;
        }

        public e a(Map<String, String> map) {
            this.e = utc.e(map, utv.h);
            return this;
        }

        public utv b() {
            return new utv(this.g, this.i, this.f17553a, this.c, this.b, this.d, this.j, this.e);
        }
    }

    utv(utx utxVar, String str, String str2, Long l, String str3, String str4, String str5, Map<String, String> map) {
        this.f = utxVar;
        this.j = str;
        this.f17552a = str2;
        this.c = l;
        this.d = str3;
        this.e = str4;
        this.i = str5;
        this.b = map;
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        JsonUtil.c(jSONObject, "request", this.f.e());
        JsonUtil.b(jSONObject, "token_type", this.j);
        JsonUtil.b(jSONObject, Constants.PARAM_ACCESS_TOKEN, this.f17552a);
        JsonUtil.d(jSONObject, "expires_at", this.c);
        JsonUtil.b(jSONObject, "id_token", this.d);
        JsonUtil.b(jSONObject, "refresh_token", this.e);
        JsonUtil.b(jSONObject, "scope", this.i);
        JsonUtil.c(jSONObject, "additionalParameters", JsonUtil.c(this.b));
        return jSONObject;
    }

    public static utv d(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("request")) {
            throw new IllegalArgumentException("token request not provided and not found in JSON");
        }
        return new utv(utx.c(jSONObject.getJSONObject("request")), JsonUtil.d(jSONObject, "token_type"), JsonUtil.d(jSONObject, Constants.PARAM_ACCESS_TOKEN), JsonUtil.b(jSONObject, "expires_at"), JsonUtil.d(jSONObject, "id_token"), JsonUtil.d(jSONObject, "refresh_token"), JsonUtil.d(jSONObject, "scope"), JsonUtil.g(jSONObject, "additionalParameters"));
    }
}
