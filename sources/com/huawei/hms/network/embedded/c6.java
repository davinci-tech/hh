package com.huawei.hms.network.embedded;

import android.content.Context;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import com.huawei.hms.network.embedded.b6;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes9.dex */
public class c6 {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5201a = "c6";
    public static SSLSocketFactory b;

    public static String a(Context context) {
        String str = f5201a;
        Logger.v(str, "entry to getDomainName function");
        Map<String, String> synGetGrsUrls = new GrsClient(context, new GrsBaseInfo()).synGetGrsUrls(b6.n.j);
        if (synGetGrsUrls == null || synGetGrsUrls.isEmpty()) {
            return "";
        }
        String str2 = synGetGrsUrls.get(new ArrayList(synGetGrsUrls.keySet()).get(new SecureRandom().nextInt(synGetGrsUrls.size())));
        Logger.v(str, "domain in random: " + str2);
        return str2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0053, code lost:
    
        if (r4 != null) goto L29;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.hms.network.embedded.d5 a(android.content.Context r3, java.lang.String r4, java.lang.String r5) {
        /*
            java.security.SecureRandom r0 = com.huawei.secure.android.common.encrypt.utils.EncryptUtil.genSecureRandom()     // Catch: java.security.KeyManagementException -> Lb java.security.KeyStoreException -> Ld java.security.cert.CertificateException -> Lf java.security.NoSuchAlgorithmException -> L11 java.io.IOException -> L13
            com.huawei.secure.android.common.ssl.SSFCompatiableSystemCA r0 = com.huawei.secure.android.common.ssl.SSFCompatiableSystemCA.getInstance(r3, r0)     // Catch: java.security.KeyManagementException -> Lb java.security.KeyStoreException -> Ld java.security.cert.CertificateException -> Lf java.security.NoSuchAlgorithmException -> L11 java.io.IOException -> L13
            com.huawei.hms.network.embedded.c6.b = r0     // Catch: java.security.KeyManagementException -> Lb java.security.KeyStoreException -> Ld java.security.cert.CertificateException -> Lf java.security.NoSuchAlgorithmException -> L11 java.io.IOException -> L13
            goto L1b
        Lb:
            r0 = move-exception
            goto L14
        Ld:
            r0 = move-exception
            goto L14
        Lf:
            r0 = move-exception
            goto L14
        L11:
            r0 = move-exception
            goto L14
        L13:
            r0 = move-exception
        L14:
            java.lang.String r1 = com.huawei.hms.network.embedded.c6.f5201a
            java.lang.String r2 = "catch exception when create sslSocketFactory"
            com.huawei.hms.framework.common.Logger.w(r1, r2, r0)
        L1b:
            com.huawei.hms.network.embedded.d5 r0 = new com.huawei.hms.network.embedded.d5
            r0.<init>()
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            java.net.URLConnection r4 = r2.openConnection()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            javax.net.ssl.HttpsURLConnection r4 = (javax.net.ssl.HttpsURLConnection) r4     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            java.lang.String r1 = "App-Name"
            java.lang.String r3 = r3.getPackageName()     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L58
            r4.addRequestProperty(r1, r3)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L58
            java.lang.String r3 = "App-ID"
            r4.addRequestProperty(r3, r5)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L58
            java.lang.String r3 = "POST"
            r4.setRequestMethod(r3)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L58
            javax.net.ssl.SSLSocketFactory r3 = com.huawei.hms.network.embedded.c6.b     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L58
            r4.setSSLSocketFactory(r3)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L58
            com.huawei.secure.android.common.ssl.hostname.StrictHostnameVerifier r3 = new com.huawei.secure.android.common.ssl.hostname.StrictHostnameVerifier     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L58
            r3.<init>()     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L58
            r4.setHostnameVerifier(r3)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L58
            int r3 = r4.getResponseCode()     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L58
            r0.a(r3)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L58
            if (r4 == 0) goto L72
            goto L6f
        L56:
            r3 = move-exception
            goto L74
        L58:
            r3 = move-exception
            r1 = r4
            goto L5e
        L5b:
            r3 = move-exception
            goto L73
        L5d:
            r3 = move-exception
        L5e:
            java.lang.String r4 = com.huawei.hms.network.embedded.c6.f5201a     // Catch: java.lang.Throwable -> L5b
            java.lang.String r5 = "connection query fail"
            com.huawei.hms.framework.common.Logger.v(r4, r5)     // Catch: java.lang.Throwable -> L5b
            int r3 = com.huawei.hms.framework.common.ExceptionCode.getErrorCodeFromException(r3)     // Catch: java.lang.Throwable -> L5b
            r0.a(r3)     // Catch: java.lang.Throwable -> L5b
            if (r1 == 0) goto L72
            r4 = r1
        L6f:
            r4.disconnect()
        L72:
            return r0
        L73:
            r4 = r1
        L74:
            if (r4 == 0) goto L79
            r4.disconnect()
        L79:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.c6.a(android.content.Context, java.lang.String, java.lang.String):com.huawei.hms.network.embedded.d5");
    }
}
