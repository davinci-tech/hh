package defpackage;

import android.content.Intent;
import android.net.Uri;
import androidx.collection.ArrayMap;
import com.huawei.openalliance.ad.constant.VastAttribute;
import java.util.Collections;
import java.util.Map;
import net.openid.appauth.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public final class utg extends Exception {

    /* renamed from: a, reason: collision with root package name */
    public final String f17539a;
    public final int b;
    public final String c;
    public final int d;
    public final Uri e;

    public static final class c {
        public static final utg b = utg.e(0, "Invalid discovery document");
        public static final utg j = utg.e(1, "User cancelled flow");
        public static final utg i = utg.e(2, "Flow cancelled programmatically");
        public static final utg h = utg.e(3, "Network error");
        public static final utg g = utg.e(4, "Server error");
        public static final utg e = utg.e(5, "JSON deserialization error");
        public static final utg f = utg.e(6, "Token response construction error");

        /* renamed from: a, reason: collision with root package name */
        public static final utg f17540a = utg.e(7, "Invalid registration response");
        public static final utg d = utg.e(8, "Unable to parse ID Token");
        public static final utg c = utg.e(9, "Invalid ID Token");
    }

    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        public static final utg f17541a;
        public static final utg b;
        public static final utg c;
        public static final utg d;
        public static final utg e;
        public static final utg f;
        public static final utg g;
        public static final utg h;
        public static final utg i;
        public static final utg j;
        private static final Map<String, utg> m;

        static {
            utg d2 = utg.d(1000, "invalid_request");
            f17541a = d2;
            utg d3 = utg.d(1001, "unauthorized_client");
            i = d3;
            utg d4 = utg.d(1002, "access_denied");
            e = d4;
            utg d5 = utg.d(1003, "unsupported_response_type");
            f = d5;
            utg d6 = utg.d(1004, "invalid_scope");
            b = d6;
            utg d7 = utg.d(1005, "server_error");
            j = d7;
            utg d8 = utg.d(1006, "temporarily_unavailable");
            h = d8;
            utg d9 = utg.d(1007, null);
            d = d9;
            utg d10 = utg.d(1008, null);
            c = d10;
            g = utg.e(9, "Response state param did not match request state");
            m = utg.d(d2, d3, d4, d5, d6, d7, d8, d9, d10);
        }

        public static utg d(String str) {
            utg utgVar = m.get(str);
            return utgVar != null ? utgVar : c;
        }
    }

    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        public static final utg f17542a;
        public static final utg b;
        public static final utg c;
        public static final utg d;
        public static final utg e;
        public static final utg f;
        public static final utg g;
        private static final Map<String, utg> i;
        public static final utg j;

        static {
            utg g2 = utg.g(2000, "invalid_request");
            e = g2;
            utg g3 = utg.g(2001, "invalid_client");
            f17542a = g3;
            utg g4 = utg.g(2002, "invalid_grant");
            c = g4;
            utg g5 = utg.g(2003, "unauthorized_client");
            j = g5;
            utg g6 = utg.g(2004, "unsupported_grant_type");
            f = g6;
            utg g7 = utg.g(2005, "invalid_scope");
            d = g7;
            utg g8 = utg.g(2006, null);
            b = g8;
            utg g9 = utg.g(2007, null);
            g = g9;
            i = utg.d(g2, g3, g4, g5, g6, g7, g8, g9);
        }

        public static utg d(String str) {
            utg utgVar = i.get(str);
            return utgVar != null ? utgVar : g;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static utg e(int i, String str) {
        return new utg(0, i, null, str, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static utg d(int i, String str) {
        return new utg(1, i, str, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static utg g(int i, String str) {
        return new utg(2, i, str, null, null, null);
    }

    public static utg c(utg utgVar, Throwable th) {
        return new utg(utgVar.b, utgVar.d, utgVar.c, utgVar.f17539a, utgVar.e, th);
    }

    public static utg ffV_(utg utgVar, String str, String str2, Uri uri) {
        int i = utgVar.b;
        int i2 = utgVar.d;
        if (str == null) {
            str = utgVar.c;
        }
        String str3 = str;
        if (str2 == null) {
            str2 = utgVar.f17539a;
        }
        String str4 = str2;
        if (uri == null) {
            uri = utgVar.e;
        }
        return new utg(i, i2, str3, str4, uri, null);
    }

    public static utg ffU_(Uri uri) {
        String queryParameter = uri.getQueryParameter(VastAttribute.ERROR);
        String queryParameter2 = uri.getQueryParameter("error_description");
        String queryParameter3 = uri.getQueryParameter("error_uri");
        utg d2 = d.d(queryParameter);
        int i = d2.b;
        int i2 = d2.d;
        if (queryParameter2 == null) {
            queryParameter2 = d2.f17539a;
        }
        return new utg(i, i2, queryParameter, queryParameter2, queryParameter3 != null ? Uri.parse(queryParameter3) : d2.e, null);
    }

    public static utg a(String str) throws JSONException {
        utq.d(str, (Object) "jsonStr cannot be null or empty");
        return c(new JSONObject(str));
    }

    public static utg c(JSONObject jSONObject) throws JSONException {
        utq.d(jSONObject, "json cannot be null");
        return new utg(jSONObject.getInt("type"), jSONObject.getInt("code"), JsonUtil.d(jSONObject, VastAttribute.ERROR), JsonUtil.d(jSONObject, "errorDescription"), JsonUtil.fgo_(jSONObject, "errorUri"), null);
    }

    public static utg ffT_(Intent intent) {
        utq.a(intent);
        if (!intent.hasExtra("net.openid.appauth.AuthorizationException")) {
            return null;
        }
        try {
            return a(intent.getStringExtra("net.openid.appauth.AuthorizationException"));
        } catch (JSONException e2) {
            throw new IllegalArgumentException("Intent contains malformed exception data", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, utg> d(utg... utgVarArr) {
        ArrayMap arrayMap = new ArrayMap(utgVarArr != null ? utgVarArr.length : 0);
        if (utgVarArr != null) {
            for (utg utgVar : utgVarArr) {
                String str = utgVar.c;
                if (str != null) {
                    arrayMap.put(str, utgVar);
                }
            }
        }
        return Collections.unmodifiableMap(arrayMap);
    }

    public utg(int i, int i2, String str, String str2, Uri uri, Throwable th) {
        super(str2, th);
        this.b = i;
        this.d = i2;
        this.c = str;
        this.f17539a = str2;
        this.e = uri;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        JsonUtil.a(jSONObject, "type", this.b);
        JsonUtil.a(jSONObject, "code", this.d);
        JsonUtil.b(jSONObject, VastAttribute.ERROR, this.c);
        JsonUtil.b(jSONObject, "errorDescription", this.f17539a);
        JsonUtil.fgp_(jSONObject, "errorUri", this.e);
        return jSONObject;
    }

    public String d() {
        return a().toString();
    }

    public Intent ffW_() {
        Intent intent = new Intent();
        intent.putExtra("net.openid.appauth.AuthorizationException", d());
        return intent;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof utg)) {
            return false;
        }
        utg utgVar = (utg) obj;
        return this.b == utgVar.b && this.d == utgVar.d;
    }

    public int hashCode() {
        return ((this.b + 31) * 31) + this.d;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "AuthorizationException: " + d();
    }
}
