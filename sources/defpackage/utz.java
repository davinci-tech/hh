package defpackage;

import android.net.Uri;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.openid.appauth.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class utz {
    private static final Set<String> h = new HashSet(Arrays.asList("client_id", "client_secret", "client_secret_expires_at", "registration_access_token", "registration_client_uri", "client_id_issued_at", "token_endpoint_auth_method"));

    /* renamed from: a, reason: collision with root package name */
    public final Long f17557a;
    public final String b;
    public final Map<String, String> c;
    public final Long d;
    public final String e;
    public final String f;
    public final String g;
    public final Uri i;
    public final uty j;

    private utz(uty utyVar, String str, Long l, String str2, Long l2, String str3, Uri uri, String str4, Map<String, String> map) {
        this.j = utyVar;
        this.b = str;
        this.d = l;
        this.e = str2;
        this.f17557a = l2;
        this.f = str3;
        this.i = uri;
        this.g = str4;
        this.c = map;
    }

    public JSONObject e() {
        JSONObject jSONObject = new JSONObject();
        JsonUtil.c(jSONObject, "request", this.j.e());
        JsonUtil.e(jSONObject, "client_id", this.b);
        JsonUtil.d(jSONObject, "client_id_issued_at", this.d);
        JsonUtil.b(jSONObject, "client_secret", this.e);
        JsonUtil.d(jSONObject, "client_secret_expires_at", this.f17557a);
        JsonUtil.b(jSONObject, "registration_access_token", this.f);
        JsonUtil.fgp_(jSONObject, "registration_client_uri", this.i);
        JsonUtil.b(jSONObject, "token_endpoint_auth_method", this.g);
        JsonUtil.c(jSONObject, "additionalParameters", JsonUtil.c(this.c));
        return jSONObject;
    }

    public static utz e(JSONObject jSONObject) throws JSONException {
        utq.d(jSONObject, "json cannot be null");
        if (!jSONObject.has("request")) {
            throw new IllegalArgumentException("registration request not found in JSON");
        }
        return new utz(uty.d(jSONObject.getJSONObject("request")), JsonUtil.a(jSONObject, "client_id"), JsonUtil.b(jSONObject, "client_id_issued_at"), JsonUtil.d(jSONObject, "client_secret"), JsonUtil.b(jSONObject, "client_secret_expires_at"), JsonUtil.d(jSONObject, "registration_access_token"), JsonUtil.fgo_(jSONObject, "registration_client_uri"), JsonUtil.d(jSONObject, "token_endpoint_auth_method"), JsonUtil.g(jSONObject, "additionalParameters"));
    }
}
