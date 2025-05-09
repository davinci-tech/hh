package defpackage;

import java.util.HashMap;
import java.util.Map;
import net.openid.appauth.ClientAuthentication;

/* loaded from: classes7.dex */
public class utp implements ClientAuthentication {

    /* renamed from: a, reason: collision with root package name */
    private String f17549a;

    @Override // net.openid.appauth.ClientAuthentication
    public final Map<String, String> getRequestHeaders(String str) {
        return null;
    }

    public utp(String str) {
        this.f17549a = (String) utq.d(str, (Object) "clientSecret cannot be null");
    }

    @Override // net.openid.appauth.ClientAuthentication
    public final Map<String, String> getRequestParameters(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("client_id", str);
        hashMap.put("client_secret", this.f17549a);
        return hashMap;
    }
}
