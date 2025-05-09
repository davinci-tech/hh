package defpackage;

import android.net.Uri;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class uty {
    private static final Set<String> m = utc.b("redirect_uris", "response_types", "grant_types", "application_type", "subject_type", "jwks_uri", "jwks", "token_endpoint_auth_method");

    /* renamed from: a, reason: collision with root package name */
    public final List<String> f17556a;
    public final Map<String, String> b;
    public final JSONObject c;
    public final String d = Constants.NATIVE_CACHE;
    public final AuthorizationServiceConfiguration e;
    public final String f;
    public final List<Uri> g;
    public final String h;
    public final List<String> i;
    public final Uri j;

    private uty(AuthorizationServiceConfiguration authorizationServiceConfiguration, List<Uri> list, List<String> list2, List<String> list3, String str, Uri uri, JSONObject jSONObject, String str2, Map<String, String> map) {
        this.e = authorizationServiceConfiguration;
        this.g = list;
        this.i = list2;
        this.f17556a = list3;
        this.h = str;
        this.j = uri;
        this.c = jSONObject;
        this.f = str2;
        this.b = map;
    }

    public JSONObject e() {
        JSONObject b = b();
        JsonUtil.c(b, "configuration", this.e.e());
        JsonUtil.c(b, "additionalParameters", JsonUtil.c(this.b));
        return b;
    }

    private JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        JsonUtil.a(jSONObject, "redirect_uris", JsonUtil.d(this.g));
        JsonUtil.e(jSONObject, "application_type", this.d);
        List<String> list = this.i;
        if (list != null) {
            JsonUtil.a(jSONObject, "response_types", JsonUtil.d(list));
        }
        List<String> list2 = this.f17556a;
        if (list2 != null) {
            JsonUtil.a(jSONObject, "grant_types", JsonUtil.d(list2));
        }
        JsonUtil.b(jSONObject, "subject_type", this.h);
        JsonUtil.fgp_(jSONObject, "jwks_uri", this.j);
        JsonUtil.a(jSONObject, "jwks", this.c);
        JsonUtil.b(jSONObject, "token_endpoint_auth_method", this.f);
        return jSONObject;
    }

    public static uty d(JSONObject jSONObject) throws JSONException {
        utq.d(jSONObject, "json must not be null");
        return new uty(AuthorizationServiceConfiguration.d(jSONObject.getJSONObject("configuration")), JsonUtil.j(jSONObject, "redirect_uris"), JsonUtil.h(jSONObject, "response_types"), JsonUtil.h(jSONObject, "grant_types"), JsonUtil.d(jSONObject, "subject_type"), JsonUtil.fgo_(jSONObject, "jwks_uri"), JsonUtil.e(jSONObject, "jwks"), JsonUtil.d(jSONObject, "token_endpoint_auth_method"), JsonUtil.g(jSONObject, "additionalParameters"));
    }
}
