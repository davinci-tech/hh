package defpackage;

import android.net.Uri;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.openid.appauth.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class utn {

    /* renamed from: a, reason: collision with root package name */
    static final JsonUtil.d f17548a;
    static final JsonUtil.d aa;
    static final JsonUtil.c ab;
    static final JsonUtil.d ac;
    static final JsonUtil.c ad;
    static final JsonUtil.c ae;
    static final JsonUtil.d af;
    static final JsonUtil.d ag;
    static final JsonUtil.d ah;
    static final JsonUtil.d ai;
    static final JsonUtil.d aj;
    private static final List<String> ak;
    static final JsonUtil.d b;
    static final JsonUtil.c c;
    static final JsonUtil.d d;
    static final JsonUtil.e e;
    static final JsonUtil.d f;
    static final JsonUtil.c g;
    static final JsonUtil.d h;
    static final JsonUtil.d i;
    static final JsonUtil.d j;
    static final JsonUtil.d k;
    static final JsonUtil.d l;
    static final JsonUtil.c m;
    static final JsonUtil.c n;
    static final JsonUtil.a o;
    static final JsonUtil.c p;
    static final JsonUtil.d q;
    static final JsonUtil.d r;
    static final JsonUtil.c s;
    static final JsonUtil.d t;
    static final JsonUtil.e u;
    static final JsonUtil.e v;
    static final JsonUtil.d w;
    static final JsonUtil.d x;
    static final JsonUtil.e y;
    static final JsonUtil.d z;
    public final JSONObject an;

    static {
        JsonUtil.a e2 = e("issuer");
        o = e2;
        JsonUtil.c b2 = b("authorization_endpoint");
        c = b2;
        ad = b("token_endpoint");
        g = b("end_session_endpoint");
        ae = b("userinfo_endpoint");
        JsonUtil.c b3 = b("jwks_uri");
        n = b3;
        p = b("registration_endpoint");
        z = d("scopes_supported");
        JsonUtil.d d2 = d("response_types_supported");
        x = d2;
        w = d("response_modes_supported");
        f = d("grant_types_supported", Arrays.asList("authorization_code", "implicit"));
        f17548a = d("acr_values_supported");
        JsonUtil.d d3 = d("subject_types_supported");
        ac = d3;
        JsonUtil.d d4 = d("id_token_signing_alg_values_supported");
        k = d4;
        i = d("id_token_encryption_enc_values_supported");
        l = d("id_token_encryption_enc_values_supported");
        aj = d("userinfo_signing_alg_values_supported");
        ai = d("userinfo_encryption_alg_values_supported");
        af = d("userinfo_encryption_enc_values_supported");
        t = d("request_object_signing_alg_values_supported");
        q = d("request_object_encryption_alg_values_supported");
        r = d("request_object_encryption_enc_values_supported");
        aa = d("token_endpoint_auth_methods_supported", Collections.singletonList("client_secret_basic"));
        ah = d("token_endpoint_auth_signing_alg_values_supported");
        j = d("display_values_supported");
        h = d("claim_types_supported", Collections.singletonList("normal"));
        d = d("claims_supported");
        ab = b("service_documentation");
        b = d("claims_locales_supported");
        ag = d("ui_locales_supported");
        e = a("claims_parameter_supported", false);
        y = a("request_parameter_supported", false);
        v = a("request_uri_parameter_supported", true);
        u = a("require_request_uri_registration", false);
        m = b("op_policy_uri");
        s = b("op_tos_uri");
        ak = Arrays.asList(e2.key, b2.key, b3.key, d2.key, d3.key, d4.key);
    }

    public utn(JSONObject jSONObject) throws JSONException, a {
        this.an = (JSONObject) utq.a(jSONObject);
        for (String str : ak) {
            if (!this.an.has(str) || this.an.get(str) == null) {
                throw new a(str);
            }
        }
    }

    public static class a extends Exception {
        private String c;

        public a(String str) {
            super("Missing mandatory configuration field: " + str);
            this.c = str;
        }

        public String d() {
            return this.c;
        }
    }

    private <T> T c(JsonUtil.Field<T> field) {
        return (T) JsonUtil.d(this.an, field);
    }

    public String c() {
        return (String) c(o);
    }

    public Uri fgi_() {
        return (Uri) c(c);
    }

    public Uri fgl_() {
        return (Uri) c(ad);
    }

    public Uri fgj_() {
        return (Uri) c(g);
    }

    public Uri fgk_() {
        return (Uri) c(p);
    }

    private static JsonUtil.a e(String str) {
        return new JsonUtil.a(str);
    }

    private static JsonUtil.c b(String str) {
        return new JsonUtil.c(str);
    }

    private static JsonUtil.d d(String str) {
        return new JsonUtil.d(str);
    }

    private static JsonUtil.d d(String str, List<String> list) {
        return new JsonUtil.d(str, list);
    }

    private static JsonUtil.e a(String str, boolean z2) {
        return new JsonUtil.e(str, z2);
    }
}
