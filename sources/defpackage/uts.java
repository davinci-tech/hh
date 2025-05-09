package defpackage;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import androidx.webkit.ProxyConfig;
import com.tencent.connect.common.Constants;
import defpackage.utg;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.openid.appauth.Clock;
import net.openid.appauth.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class uts {

    /* renamed from: a, reason: collision with root package name */
    public final Long f17551a;
    public final String b;
    public final List<String> c;
    public final Long d;
    public final Map<String, Object> e;
    public final String f;
    public final String g;
    public final String i;
    private static final Long h = 1000L;
    private static final Long o = 600L;
    private static final Set<String> j = utc.b("iss", "sub", "aud", "exp", "iat", Constants.NONCE, "azp");

    uts(String str, String str2, List<String> list, Long l, Long l2, String str3, String str4, Map<String, Object> map) {
        this.f = str;
        this.g = str2;
        this.c = list;
        this.f17551a = l;
        this.d = l2;
        this.i = str3;
        this.b = str4;
        this.e = map;
    }

    private static JSONObject c(String str) throws JSONException {
        return new JSONObject(new String(Base64.decode(str, 8)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static uts d(String str) throws JSONException, b {
        List list;
        String[] split = str.split("\\.");
        if (split.length <= 1) {
            throw new b("ID token must have both header and claims section");
        }
        c(split[0]);
        JSONObject c = c(split[1]);
        String a2 = JsonUtil.a(c, "iss");
        String a3 = JsonUtil.a(c, "sub");
        try {
            list = JsonUtil.c(c, "aud");
        } catch (JSONException unused) {
            List arrayList = new ArrayList();
            arrayList.add(JsonUtil.a(c, "aud"));
            list = arrayList;
        }
        long j2 = c.getLong("exp");
        long j3 = c.getLong("iat");
        String d = JsonUtil.d(c, Constants.NONCE);
        String d2 = JsonUtil.d(c, "azp");
        Iterator<String> it = j.iterator();
        while (it.hasNext()) {
            c.remove(it.next());
        }
        return new uts(a2, a3, list, Long.valueOf(j2), Long.valueOf(j3), d, d2, JsonUtil.e(c));
    }

    public void e(utx utxVar, Clock clock, boolean z) throws utg {
        utn utnVar = utxVar.e.c;
        if (utnVar != null) {
            if (!this.f.equals(utnVar.c())) {
                throw utg.c(utg.c.c, new b("Issuer mismatch"));
            }
            Uri parse = Uri.parse(this.f);
            if (!z && !parse.getScheme().equals(ProxyConfig.MATCH_HTTPS)) {
                throw utg.c(utg.c.c, new b("Issuer must be an https URL"));
            }
            if (TextUtils.isEmpty(parse.getHost())) {
                throw utg.c(utg.c.c, new b("Issuer host can not be empty"));
            }
            if (parse.getFragment() != null || parse.getQueryParameterNames().size() > 0) {
                throw utg.c(utg.c.c, new b("Issuer URL should not containt query parameters or fragment components"));
            }
        }
        String str = utxVar.c;
        if (!this.c.contains(str) && !str.equals(this.b)) {
            throw utg.c(utg.c.c, new b("Audience mismatch"));
        }
        Long valueOf = Long.valueOf(clock.getCurrentTimeMillis() / h.longValue());
        if (valueOf.longValue() > this.f17551a.longValue()) {
            throw utg.c(utg.c.c, new b("ID Token expired"));
        }
        if (Math.abs(valueOf.longValue() - this.d.longValue()) > o.longValue()) {
            throw utg.c(utg.c.c, new b("Issued at time is more than 10 minutes before or after the current time"));
        }
        if ("authorization_code".equals(utxVar.h)) {
            if (!TextUtils.equals(this.i, utxVar.j)) {
                throw utg.c(utg.c.c, new b("Nonce mismatch"));
            }
        }
    }

    public static class b extends Exception {
        b(String str) {
            super(str);
        }
    }
}
