package defpackage;

import android.net.Uri;
import java.util.Map;
import java.util.Set;
import net.openid.appauth.AuthorizationManagementRequest;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes10.dex */
public class utr implements AuthorizationManagementRequest {
    private static final Set<String> j = utc.b("id_token_hint", "post_logout_redirect_uri", "state", "ui_locales");

    /* renamed from: a, reason: collision with root package name */
    public final Uri f17550a;
    public final String b;
    public final AuthorizationServiceConfiguration c;
    public final String d;
    public final Map<String, String> e;
    public final String f;

    private utr(AuthorizationServiceConfiguration authorizationServiceConfiguration, String str, Uri uri, String str2, String str3, Map<String, String> map) {
        this.c = authorizationServiceConfiguration;
        this.d = str;
        this.f17550a = uri;
        this.b = str2;
        this.f = str3;
        this.e = map;
    }

    @Override // net.openid.appauth.AuthorizationManagementRequest
    public String getState() {
        return this.b;
    }

    @Override // net.openid.appauth.AuthorizationManagementRequest
    public Uri toUri() {
        Uri.Builder buildUpon = this.c.b.buildUpon();
        uuh.fgw_(buildUpon, "id_token_hint", this.d);
        uuh.fgw_(buildUpon, "state", this.b);
        uuh.fgw_(buildUpon, "ui_locales", this.f);
        Uri uri = this.f17550a;
        if (uri != null) {
            buildUpon.appendQueryParameter("post_logout_redirect_uri", uri.toString());
        }
        for (Map.Entry<String, String> entry : this.e.entrySet()) {
            buildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        return buildUpon.build();
    }

    @Override // net.openid.appauth.AuthorizationManagementRequest
    public JSONObject jsonSerialize() {
        JSONObject jSONObject = new JSONObject();
        JsonUtil.c(jSONObject, "configuration", this.c.e());
        JsonUtil.b(jSONObject, "id_token_hint", this.d);
        JsonUtil.fgp_(jSONObject, "post_logout_redirect_uri", this.f17550a);
        JsonUtil.b(jSONObject, "state", this.b);
        JsonUtil.b(jSONObject, "ui_locales", this.f);
        JsonUtil.c(jSONObject, "additionalParameters", JsonUtil.c(this.e));
        return jSONObject;
    }

    @Override // net.openid.appauth.AuthorizationManagementRequest
    public String jsonSerializeString() {
        return jsonSerialize().toString();
    }

    public static utr b(JSONObject jSONObject) throws JSONException {
        utq.d(jSONObject, "json cannot be null");
        return new utr(AuthorizationServiceConfiguration.d(jSONObject.getJSONObject("configuration")), JsonUtil.d(jSONObject, "id_token_hint"), JsonUtil.fgo_(jSONObject, "post_logout_redirect_uri"), JsonUtil.d(jSONObject, "state"), JsonUtil.d(jSONObject, "ui_locales"), JsonUtil.g(jSONObject, "additionalParameters"));
    }
}
