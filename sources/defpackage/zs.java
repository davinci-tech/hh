package defpackage;

import android.content.Context;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes8.dex */
public class zs {
    public static void b(Context context, HttpsURLConnection httpsURLConnection) {
        try {
            httpsURLConnection.setSSLSocketFactory(b(context));
            httpsURLConnection.setHostnameVerifier(SecureSSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        } catch (IOException | IllegalAccessException | GeneralSecurityException e) {
            abd.b("SecureNetSSLSocketFactory", "SSLSocketFactory error:" + e.getMessage());
        }
    }

    public static SSLSocketFactory b(Context context) throws IOException, GeneralSecurityException, IllegalAccessException {
        return SecureSSLSocketFactory.getInstance(context);
    }
}
