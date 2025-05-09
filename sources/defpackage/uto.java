package defpackage;

import android.util.Base64;
import java.util.Collections;
import java.util.Map;
import net.openid.appauth.ClientAuthentication;

/* loaded from: classes7.dex */
public class uto implements ClientAuthentication {
    private String e;

    @Override // net.openid.appauth.ClientAuthentication
    public final Map<String, String> getRequestParameters(String str) {
        return null;
    }

    public uto(String str) {
        this.e = (String) utq.d(str, (Object) "mClientSecret cannot be null");
    }

    @Override // net.openid.appauth.ClientAuthentication
    public final Map<String, String> getRequestHeaders(String str) {
        return Collections.singletonMap("Authorization", "Basic " + Base64.encodeToString((uuh.e(str) + ":" + uuh.e(this.e)).getBytes(), 2));
    }
}
