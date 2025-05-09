package defpackage;

import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.connectivity.https.HttpResCallBack;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mdl {
    private static ExecutorService c = Executors.newCachedThreadPool();

    public static int e(String str, JSONObject jSONObject, HashMap<String, String> hashMap, HttpResCallBack httpResCallBack) {
        LogUtil.a("PLGACHIEVE_Https", "postReq enter");
        if (hashMap != null && jSONObject != null) {
            try {
                try {
                    URLConnection openConnection = URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
                    if (!(openConnection instanceof HttpsURLConnection)) {
                        return -1;
                    }
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection;
                    d(httpsURLConnection);
                    httpsURLConnection.setDoOutput(true);
                    httpsURLConnection.setDoInput(true);
                    httpsURLConnection.setUseCaches(false);
                    httpsURLConnection.setConnectTimeout(10000);
                    httpsURLConnection.setReadTimeout(10000);
                    httpsURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    try {
                        httpsURLConnection.setRequestMethod("POST");
                        mdk.b(httpsURLConnection, hashMap);
                        c.execute(new a(jSONObject.toString(), httpsURLConnection, httpResCallBack));
                        LogUtil.a("PLGACHIEVE_Https", "postReq exit");
                        return 0;
                    } catch (ProtocolException unused) {
                        LogUtil.b("PLGACHIEVE_Https", "download e3=ProtocolException");
                        return -1;
                    }
                } catch (IOException unused2) {
                    LogUtil.b("PLGACHIEVE_Https", "download IOException");
                    return -1;
                }
            } catch (MalformedURLException unused3) {
                LogUtil.b("PLGACHIEVE_Https", "download MalformedURLException");
            }
        }
        return -1;
    }

    public static int c(String str, String str2, HttpResCallBack httpResCallBack) {
        LogUtil.a("PLGACHIEVE_Https", "download enter");
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                try {
                    URLConnection openConnection = URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
                    if (!(openConnection instanceof HttpsURLConnection)) {
                        return -1;
                    }
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection;
                    d(httpsURLConnection);
                    httpsURLConnection.setConnectTimeout(10000);
                    httpsURLConnection.setReadTimeout(10000);
                    try {
                        httpsURLConnection.setRequestMethod("GET");
                        c.execute(new b(httpsURLConnection, str2, httpResCallBack));
                        LogUtil.a("PLGACHIEVE_Https", "download exit");
                        return 0;
                    } catch (ProtocolException unused) {
                        LogUtil.b("PLGACHIEVE_Https", "download e3=ProtocolException");
                        return -1;
                    }
                } catch (IOException unused2) {
                    LogUtil.b("PLGACHIEVE_Https", "download e2=IOException");
                    return -1;
                }
            } catch (MalformedURLException unused3) {
                LogUtil.b("PLGACHIEVE_Https", "download e1=MalformedURLException");
            }
        }
        return -1;
    }

    public static void d(HttpsURLConnection httpsURLConnection) {
        try {
            SecureSSLSocketFactoryNew secureSSLSocketFactoryNew = SecureSSLSocketFactoryNew.getInstance(BaseApplication.getContext());
            httpsURLConnection.setHostnameVerifier(HttpsURLConnection.getDefaultHostnameVerifier());
            if (secureSSLSocketFactoryNew != null) {
                httpsURLConnection.setSSLSocketFactory(secureSSLSocketFactoryNew);
            }
        } catch (IOException unused) {
            LogUtil.b("PLGACHIEVE_Https", "initHttpsUrlConnection IOException");
        } catch (IllegalAccessException e) {
            LogUtil.b("PLGACHIEVE_Https", " IllegalAccessException = ", e.getMessage());
        } catch (KeyManagementException e2) {
            LogUtil.b("PLGACHIEVE_Https", " KeyManagementException = ", e2.getMessage());
        } catch (KeyStoreException e3) {
            LogUtil.b("PLGACHIEVE_Https", " KeyStoreException = ", e3.getMessage());
        } catch (NoSuchAlgorithmException e4) {
            LogUtil.b("PLGACHIEVE_Https", " NoSuchAlgorithmException = ", e4.getMessage());
        } catch (CertificateException e5) {
            LogUtil.b("PLGACHIEVE_Https", " CertificateException = ", e5.getMessage());
        }
    }

    static class a implements Runnable {
        private String b;
        private HttpResCallBack c;
        private HttpsURLConnection d;

        a(String str, HttpsURLConnection httpsURLConnection, HttpResCallBack httpResCallBack) {
            this.b = str;
            this.d = httpsURLConnection;
            this.c = httpResCallBack;
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x00c7  */
        /* JADX WARN: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00b8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:58:0x00dc A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:64:? A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:65:0x00cf A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 232
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: mdl.a.run():void");
        }
    }

    static class b extends Thread {
        private String b;
        private HttpResCallBack d;
        private HttpsURLConnection e;

        b(HttpsURLConnection httpsURLConnection, String str, HttpResCallBack httpResCallBack) {
            this.e = httpsURLConnection;
            this.b = str;
            this.d = httpResCallBack;
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x004a, code lost:
        
            if (r0 != null) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x006b, code lost:
        
            r0 = r9.d;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x006d, code lost:
        
            if (r0 == null) goto L59;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x006f, code lost:
        
            r0.onFinished(r4, null);
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0072, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:?, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0068, code lost:
        
            r0.disconnect();
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0066, code lost:
        
            if (r0 == null) goto L33;
         */
        /* JADX WARN: Not initialized variable reg: 6, insn: 0x0074: MOVE (r5 I:??[OBJECT, ARRAY]) = (r6 I:??[OBJECT, ARRAY]), block:B:43:0x0074 */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0086  */
        /* JADX WARN: Removed duplicated region for block: B:49:? A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:50:0x0077 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r9 = this;
                java.lang.String r0 = "download IOException"
                java.lang.String r1 = "PLGACHIEVE_Https"
                r2 = 0
                r3 = 1
                r4 = -1
                r5 = 0
                javax.net.ssl.HttpsURLConnection r6 = r9.e     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f
                if (r6 != 0) goto L12
                if (r6 == 0) goto L11
                r6.disconnect()
            L11:
                return
            L12:
                int r6 = r6.getResponseCode()     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f
                r7 = 2
                java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f
                java.lang.String r8 = "download-->responseCode:"
                r7[r2] = r8     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f
                java.lang.Integer r8 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f
                r7[r3] = r8     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f
                com.huawei.hwlogsmodel.LogUtil.a(r1, r7)     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f
                r7 = 200(0xc8, float:2.8E-43)
                if (r6 != r7) goto L3a
                javax.net.ssl.HttpsURLConnection r6 = r9.e     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f
                java.io.InputStream r6 = r6.getInputStream()     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f
                java.lang.String r8 = r9.b     // Catch: java.io.IOException -> L50 java.lang.Throwable -> L73
                boolean r2 = defpackage.mdk.e(r6, r8)     // Catch: java.io.IOException -> L50 java.lang.Throwable -> L73
                if (r2 == 0) goto L3b
                r4 = r7
                goto L3b
            L3a:
                r6 = r5
            L3b:
                if (r6 == 0) goto L48
                r6.close()     // Catch: java.io.IOException -> L41
                goto L48
            L41:
                java.lang.Object[] r0 = new java.lang.Object[]{r0}
                com.huawei.hwlogsmodel.LogUtil.b(r1, r0)
            L48:
                javax.net.ssl.HttpsURLConnection r0 = r9.e
                if (r0 == 0) goto L6b
                goto L68
            L4d:
                r2 = move-exception
                goto L75
            L4f:
                r6 = r5
            L50:
                java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L73
                r3[r2] = r0     // Catch: java.lang.Throwable -> L73
                com.huawei.hwlogsmodel.LogUtil.b(r1, r3)     // Catch: java.lang.Throwable -> L73
                if (r6 == 0) goto L64
                r6.close()     // Catch: java.io.IOException -> L5d
                goto L64
            L5d:
                java.lang.Object[] r0 = new java.lang.Object[]{r0}
                com.huawei.hwlogsmodel.LogUtil.b(r1, r0)
            L64:
                javax.net.ssl.HttpsURLConnection r0 = r9.e
                if (r0 == 0) goto L6b
            L68:
                r0.disconnect()
            L6b:
                com.huawei.pluginachievement.connectivity.https.HttpResCallBack r0 = r9.d
                if (r0 == 0) goto L72
                r0.onFinished(r4, r5)
            L72:
                return
            L73:
                r2 = move-exception
                r5 = r6
            L75:
                if (r5 == 0) goto L82
                r5.close()     // Catch: java.io.IOException -> L7b
                goto L82
            L7b:
                java.lang.Object[] r0 = new java.lang.Object[]{r0}
                com.huawei.hwlogsmodel.LogUtil.b(r1, r0)
            L82:
                javax.net.ssl.HttpsURLConnection r0 = r9.e
                if (r0 == 0) goto L89
                r0.disconnect()
            L89:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: mdl.b.run():void");
        }
    }
}
