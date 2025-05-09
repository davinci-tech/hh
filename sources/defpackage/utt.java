package defpackage;

import java.util.Collections;
import java.util.Map;
import net.openid.appauth.ClientAuthentication;

/* loaded from: classes7.dex */
public class utt implements ClientAuthentication {
    public static final utt b = new utt();

    @Override // net.openid.appauth.ClientAuthentication
    public Map<String, String> getRequestHeaders(String str) {
        return null;
    }

    private utt() {
    }

    @Override // net.openid.appauth.ClientAuthentication
    public Map<String, String> getRequestParameters(String str) {
        return Collections.singletonMap("client_id", str);
    }
}
