package defpackage;

import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes5.dex */
public class kwd {
    private static volatile ExecutorService b;

    public static void d(final String str, final HashMap<String, String> hashMap, final HashMap<String, String> hashMap2, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("SMART_HttpUtil", "pullRefresh");
        if (CommonUtil.bu()) {
            LogUtil.a("SMART_HttpUtil", "storeDemo no smart");
            iBaseResponseCallback.d(-1, "");
        } else if (b(str, hashMap, hashMap2)) {
            if (b == null || b.isShutdown()) {
                b = Executors.newSingleThreadExecutor();
            }
            b.execute(new Runnable() { // from class: kwd.5
                @Override // java.lang.Runnable
                public void run() {
                    kwd.b(str, hashMap, hashMap2, iBaseResponseCallback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("SMART_HttpUtil", "doPostReq");
        e(str, hashMap, hashMap2, iBaseResponseCallback);
    }

    private static int e(String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("SMART_HttpUtil", "entry Https.postReq");
        try {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
                if (!a(str)) {
                    if (httpURLConnection == null) {
                        return -1;
                    }
                    e((HttpsURLConnection) httpURLConnection);
                }
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(5000);
                try {
                    httpURLConnection.setRequestMethod("POST");
                    b(httpURLConnection, hashMap2);
                    b(b(hashMap), httpURLConnection, iBaseResponseCallback);
                    return 200;
                } catch (ProtocolException e) {
                    LogUtil.b("SMART_HttpUtil", e.getMessage());
                    return -1;
                }
            } catch (IOException unused) {
                LogUtil.a("SMART_HttpUtil", "IOException");
                return -1;
            }
        } catch (MalformedURLException unused2) {
            LogUtil.b("SMART_HttpUtil", "postHttpsReq MalformedURLException");
            return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void b(java.lang.String r10, java.net.HttpURLConnection r11, com.huawei.hwbasemgr.IBaseResponseCallback r12) {
        /*
            java.lang.String r0 = "SMART_HttpUtil"
            r1 = 1
            r2 = 2
            r3 = 0
            r4 = -1
            r5 = 0
            boolean r6 = android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Throwable -> L5e java.lang.NullPointerException -> L61 java.io.IOException -> L63
            if (r6 != 0) goto L1e
            java.io.OutputStream r6 = r11.getOutputStream()     // Catch: java.lang.Throwable -> L5e java.lang.NullPointerException -> L61 java.io.IOException -> L63
            java.nio.charset.Charset r7 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.lang.Throwable -> L55 java.lang.NullPointerException -> L59 java.io.IOException -> L5b
            byte[] r10 = r10.getBytes(r7)     // Catch: java.lang.Throwable -> L55 java.lang.NullPointerException -> L59 java.io.IOException -> L5b
            r6.write(r10)     // Catch: java.lang.Throwable -> L55 java.lang.NullPointerException -> L59 java.io.IOException -> L5b
            r6.flush()     // Catch: java.lang.Throwable -> L55 java.lang.NullPointerException -> L59 java.io.IOException -> L5b
            goto L1f
        L1e:
            r6 = r5
        L1f:
            r11.connect()     // Catch: java.lang.Throwable -> L55 java.lang.NullPointerException -> L59 java.io.IOException -> L5b
            int r10 = r11.getResponseCode()     // Catch: java.lang.Throwable -> L55 java.lang.NullPointerException -> L59 java.io.IOException -> L5b
            java.lang.Object[] r7 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L55 java.lang.NullPointerException -> L59 java.io.IOException -> L5b
            java.lang.String r8 = "postReq-->respCode:"
            r7[r3] = r8     // Catch: java.lang.Throwable -> L55 java.lang.NullPointerException -> L59 java.io.IOException -> L5b
            java.lang.Integer r8 = java.lang.Integer.valueOf(r10)     // Catch: java.lang.Throwable -> L55 java.lang.NullPointerException -> L59 java.io.IOException -> L5b
            r7[r1] = r8     // Catch: java.lang.Throwable -> L55 java.lang.NullPointerException -> L59 java.io.IOException -> L5b
            com.huawei.hwlogsmodel.LogUtil.a(r0, r7)     // Catch: java.lang.Throwable -> L55 java.lang.NullPointerException -> L59 java.io.IOException -> L5b
            r7 = 200(0xc8, float:2.8E-43)
            if (r10 != r7) goto L4c
            java.io.InputStream r7 = r11.getInputStream()     // Catch: java.lang.Throwable -> L55 java.lang.NullPointerException -> L59 java.io.IOException -> L5b
            byte[] r8 = a(r7)     // Catch: java.lang.NullPointerException -> L48 java.io.IOException -> L4a java.lang.Throwable -> L9a
            java.lang.String r1 = e(r8)     // Catch: java.lang.NullPointerException -> L48 java.io.IOException -> L4a java.lang.Throwable -> L9a
            r4 = r10
            r5 = r7
            goto L4d
        L48:
            r10 = move-exception
            goto L66
        L4a:
            r10 = move-exception
            goto L66
        L4c:
            r1 = r5
        L4d:
            e(r6, r5)
            r11.disconnect()
            r5 = r1
            goto L8b
        L55:
            r10 = move-exception
            r7 = r5
        L57:
            r5 = r6
            goto L9c
        L59:
            r10 = move-exception
            goto L5c
        L5b:
            r10 = move-exception
        L5c:
            r7 = r5
            goto L66
        L5e:
            r10 = move-exception
            r7 = r5
            goto L9c
        L61:
            r10 = move-exception
            goto L64
        L63:
            r10 = move-exception
        L64:
            r6 = r5
            r7 = r6
        L66:
            r8 = 4
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L9a
            java.lang.String r9 = "postRequest "
            r8[r3] = r9     // Catch: java.lang.Throwable -> L9a
            java.lang.Class r3 = r10.getClass()     // Catch: java.lang.Throwable -> L9a
            java.lang.String r3 = r3.getSimpleName()     // Catch: java.lang.Throwable -> L9a
            r8[r1] = r3     // Catch: java.lang.Throwable -> L9a
            java.lang.String r1 = ": "
            r8[r2] = r1     // Catch: java.lang.Throwable -> L9a
            java.lang.String r10 = health.compact.a.LogAnonymous.b(r10)     // Catch: java.lang.Throwable -> L9a
            r1 = 3
            r8[r1] = r10     // Catch: java.lang.Throwable -> L9a
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r0, r8)     // Catch: java.lang.Throwable -> L9a
            e(r6, r7)
            r11.disconnect()
        L8b:
            if (r12 == 0) goto L90
            r12.d(r4, r5)
        L90:
            java.lang.String r10 = "exit AchieveHttps.postReq"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r10)
            return
        L9a:
            r10 = move-exception
            goto L57
        L9c:
            e(r5, r7)
            r11.disconnect()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kwd.b(java.lang.String, java.net.HttpURLConnection, com.huawei.hwbasemgr.IBaseResponseCallback):void");
    }

    private static String e(byte[] bArr) {
        String str = new String(bArr, StandardCharsets.UTF_8);
        LogUtil.c("SMART_HttpUtil", "postReq, jsonResult:", str);
        return str;
    }

    private static void b(HttpURLConnection httpURLConnection, HashMap<String, String> hashMap) {
        if (httpURLConnection == null) {
            LogUtil.a("SMART_HttpUtil", "connection null, return");
            return;
        }
        if (hashMap == null || hashMap.isEmpty()) {
            LogUtil.a("SMART_HttpUtil", "mapHeader null, return");
            return;
        }
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key != null && value != null) {
                httpURLConnection.setRequestProperty(key, value);
            }
        }
    }

    private static String b(HashMap<String, String> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(1024);
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!TextUtils.isEmpty(key)) {
                stringBuffer.append("&").append(key).append("=").append(value);
            }
        }
        String trim = stringBuffer.toString().trim();
        return (trim == null || trim.length() <= 1) ? trim : trim.substring(1);
    }

    private static boolean a(String str) {
        int indexOf;
        String substring = (TextUtils.isEmpty(str) || !str.contains(":") || (indexOf = str.indexOf(":")) <= 0) ? "" : str.substring(0, indexOf);
        LogUtil.a("SMART_HttpUtil", "protocol:", substring);
        return "http".equalsIgnoreCase(substring);
    }

    private static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        int i = 0;
        do {
            int read = inputStream.read(bArr);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
            i += read;
        } while (i <= 31459280);
        byteArrayOutputStream.flush();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return byteArray;
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void e(javax.net.ssl.HttpsURLConnection r2) {
        /*
            java.lang.String r0 = "SMART_HttpUtil"
            android.content.Context r1 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew r1 = com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew.getInstance(r1)     // Catch: java.io.IOException -> Lb java.security.cert.CertificateException -> L15 java.security.KeyStoreException -> L1f java.security.NoSuchAlgorithmException -> L29 java.lang.IllegalAccessException -> L33 java.lang.Throwable -> L3d
            goto L47
        Lb:
            java.lang.String r1 = "initHttpsUrlConnection IOException"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)
            goto L46
        L15:
            java.lang.String r1 = "initHttpsUrlConnection CertificateException"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)
            goto L46
        L1f:
            java.lang.String r1 = "initHttpsUrlConnection KeyStoreException"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)
            goto L46
        L29:
            java.lang.String r1 = "initHttpsUrlConnection NoSuchAlgorithmException"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)
            goto L46
        L33:
            java.lang.String r1 = "initHttpsUrlConnection IllegalAccessException"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)
            goto L46
        L3d:
            java.lang.String r1 = "initHttpsUrlConnection KeyManagementException or SSLException"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)
        L46:
            r1 = 0
        L47:
            if (r1 == 0) goto L4c
            r2.setSSLSocketFactory(r1)
        L4c:
            org.apache.http.conn.ssl.X509HostnameVerifier r1 = org.apache.http.conn.ssl.SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER     // Catch: java.lang.IllegalArgumentException -> L52
            r2.setHostnameVerifier(r1)     // Catch: java.lang.IllegalArgumentException -> L52
            goto L5b
        L52:
            java.lang.String r2 = "Fail to set DefaultHostnameVerifier!"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)
        L5b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kwd.e(javax.net.ssl.HttpsURLConnection):void");
    }

    private static boolean b(String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        boolean z;
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("SMART_HttpUtil", "url is null");
            z = false;
        } else {
            z = true;
        }
        if (hashMap == null || hashMap.isEmpty()) {
            LogUtil.a("SMART_HttpUtil", "mapParam is null");
            z = false;
        }
        if (hashMap2 != null && !hashMap2.isEmpty()) {
            return z;
        }
        LogUtil.a("SMART_HttpUtil", "mapHeader is null");
        return false;
    }

    private static void e(OutputStream outputStream, InputStream inputStream) {
        FileUtils.d(outputStream);
        FileUtils.d(inputStream);
    }
}
