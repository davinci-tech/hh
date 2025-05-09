package defpackage;

import com.huawei.hms.network.NetworkKit;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.Response;
import java.io.IOException;

/* loaded from: classes8.dex */
public class tro {
    public static void e() {
        try {
            if (tot.a() == null) {
                tos.e("ScopeHttpUtil", "Networkkit init context is null");
            } else {
                NetworkKit.init(tot.a(), new NetworkKit.Callback() { // from class: tro.2
                    @Override // com.huawei.hms.network.NetworkKit.Callback
                    public void onResult(boolean z) {
                        tos.a("ScopeHttpUtil", "Networkkit init result: " + z);
                    }
                });
            }
        } catch (Throwable unused) {
            tos.e("ScopeHttpUtil", "Networkkit init exception");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0025 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.huawei.hms.network.httpclient.HttpClient a() {
        /*
            java.lang.String r0 = "ScopeHttpUtil"
            r1 = 0
            android.content.Context r2 = defpackage.tot.a()     // Catch: java.lang.Throwable -> L15 java.io.FileNotFoundException -> L1c
            ton r2 = defpackage.ton.b(r2)     // Catch: java.lang.Throwable -> L15 java.io.FileNotFoundException -> L1c
            com.huawei.wearengine.common.SecureX509TrustManager r3 = new com.huawei.wearengine.common.SecureX509TrustManager     // Catch: java.lang.Throwable -> L16 java.io.FileNotFoundException -> L1d
            android.content.Context r4 = defpackage.tot.a()     // Catch: java.lang.Throwable -> L16 java.io.FileNotFoundException -> L1d
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L16 java.io.FileNotFoundException -> L1d
            goto L23
        L15:
            r2 = r1
        L16:
            java.lang.String r3 = "HttpRequest new HealthSafeSocketFactory Exception "
            defpackage.tos.e(r0, r3)
            goto L22
        L1c:
            r2 = r1
        L1d:
            java.lang.String r3 = "file not found"
            defpackage.tos.e(r0, r3)
        L22:
            r3 = r1
        L23:
            if (r2 == 0) goto L4a
            if (r3 != 0) goto L28
            goto L4a
        L28:
            com.huawei.hms.network.httpclient.HttpClient$Builder r4 = new com.huawei.hms.network.httpclient.HttpClient$Builder     // Catch: java.lang.Throwable -> L44
            r4.<init>()     // Catch: java.lang.Throwable -> L44
            r5 = 30000(0x7530, float:4.2039E-41)
            com.huawei.hms.network.httpclient.HttpClient$Builder r4 = r4.connectTimeout(r5)     // Catch: java.lang.Throwable -> L44
            com.huawei.hms.network.httpclient.HttpClient$Builder r4 = r4.readTimeout(r5)     // Catch: java.lang.Throwable -> L44
            com.huawei.hms.network.httpclient.HttpClient$Builder r4 = r4.writeTimeout(r5)     // Catch: java.lang.Throwable -> L44
            com.huawei.hms.network.httpclient.HttpClient$Builder r2 = r4.sslSocketFactory(r2, r3)     // Catch: java.lang.Throwable -> L44
            com.huawei.hms.network.httpclient.HttpClient r0 = r2.build()     // Catch: java.lang.Throwable -> L44
            return r0
        L44:
            java.lang.String r2 = "getHttpClient exception"
            defpackage.tos.e(r0, r2)
            return r1
        L4a:
            java.lang.String r2 = "Class HttpRequest safeSocketFactory or healthX509TrustManager is null"
            defpackage.tos.e(r0, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.tro.a():com.huawei.hms.network.httpclient.HttpClient");
    }

    public static Response c(String str, String str2) {
        tos.a("ScopeHttpUtil", "get scope appID " + str2);
        try {
            HttpClient a2 = a();
            Request build = a2.newRequest().url(str).method("GET").build();
            if (a2 == null) {
                tos.e("ScopeHttpUtil", "get okHttpClient is null");
                return null;
            }
            return a2.newSubmit(build).execute();
        } catch (IOException unused) {
            tos.e("ScopeHttpUtil", "get scope IOException");
            return null;
        }
    }
}
