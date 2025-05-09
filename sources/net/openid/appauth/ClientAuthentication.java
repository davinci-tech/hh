package net.openid.appauth;

import java.util.Map;

/* loaded from: classes7.dex */
public interface ClientAuthentication {
    Map<String, String> getRequestHeaders(String str);

    Map<String, String> getRequestParameters(String str);

    public static class e extends Exception {
        private String b;

        public e(String str) {
            super("Unsupported client authentication method: " + str);
            this.b = str;
        }
    }
}
