package defpackage;

import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.connectivity.https.HttpResCallBack;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mdh {

    /* renamed from: a, reason: collision with root package name */
    private static ExecutorService f14894a = Executors.newCachedThreadPool();

    public static int e(String str, JSONObject jSONObject, HashMap<String, String> hashMap, final HttpResCallBack httpResCallBack) {
        LogUtil.a("PLGACHIEVE_Http", "postReq enter");
        if (hashMap != null && jSONObject != null) {
            try {
                try {
                    URLConnection openConnection = URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
                    if (openConnection instanceof HttpURLConnection) {
                        final HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setUseCaches(false);
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.setReadTimeout(10000);
                        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        try {
                            httpURLConnection.setRequestMethod("POST");
                            mdk.b(httpURLConnection, hashMap);
                            final String jSONObject2 = jSONObject.toString();
                            LogUtil.c("PLGACHIEVE_Http", "postReq-->strBody:", jSONObject2);
                            new Thread(new Runnable() { // from class: mdh.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    mdh.a(jSONObject2, httpURLConnection, httpResCallBack);
                                }
                            }).start();
                            LogUtil.a("PLGACHIEVE_Http", "postReq exit!!");
                            return 0;
                        } catch (ProtocolException e) {
                            LogUtil.b("PLGACHIEVE_Http", "postReq e=", e.getMessage());
                        }
                    }
                    return -1;
                } catch (IOException unused) {
                    LogUtil.b("PLGACHIEVE_Http", "postReq e2=IOException");
                    return -1;
                }
            } catch (MalformedURLException e2) {
                LogUtil.b("PLGACHIEVE_Http", "postReq e=", e2.getMessage());
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.lang.String r9, java.net.HttpURLConnection r10, com.huawei.pluginachievement.connectivity.https.HttpResCallBack r11) {
        /*
            java.lang.String r0 = "PLGACHIEVE_Http"
            r1 = 0
            r2 = 1
            r3 = -1
            r4 = 0
            boolean r5 = android.text.TextUtils.isEmpty(r9)     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L57
            java.lang.String r6 = "UTF-8"
            if (r5 != 0) goto L1d
            java.io.OutputStream r5 = r10.getOutputStream()     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L57
            byte[] r9 = r9.getBytes(r6)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L52
            r5.write(r9)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L52
            r5.flush()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L52
            goto L1e
        L1d:
            r5 = r4
        L1e:
            r10.connect()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L52
            int r9 = r10.getResponseCode()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L52
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L52
            java.lang.String r8 = "postReq-->responseCode:"
            r7[r1] = r8     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L52
            java.lang.Integer r8 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L52
            r7[r2] = r8     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L52
            com.huawei.hwlogsmodel.LogUtil.a(r0, r7)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L52
            r7 = 200(0xc8, float:2.8E-43)
            if (r9 != r7) goto L49
            java.io.InputStream r10 = r10.getInputStream()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L52
            byte[] r7 = defpackage.mdk.b(r10)     // Catch: java.io.IOException -> L59 java.lang.Throwable -> L6b
            java.lang.String r8 = new java.lang.String     // Catch: java.io.IOException -> L59 java.lang.Throwable -> L6b
            r8.<init>(r7, r6)     // Catch: java.io.IOException -> L59 java.lang.Throwable -> L6b
            r3 = r9
            r4 = r10
            goto L4a
        L49:
            r8 = r4
        L4a:
            a(r5, r4)
            r4 = r8
            goto L65
        L4f:
            r9 = move-exception
            r10 = r4
            goto L6c
        L52:
            r10 = r4
            goto L59
        L54:
            r9 = move-exception
            r10 = r4
            goto L6d
        L57:
            r10 = r4
            r5 = r10
        L59:
            java.lang.Object[] r9 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L6b
            java.lang.String r2 = "postReq IOException"
            r9[r1] = r2     // Catch: java.lang.Throwable -> L6b
            com.huawei.hwlogsmodel.LogUtil.b(r0, r9)     // Catch: java.lang.Throwable -> L6b
            a(r5, r10)
        L65:
            if (r11 == 0) goto L6a
            r11.onFinished(r3, r4)
        L6a:
            return
        L6b:
            r9 = move-exception
        L6c:
            r4 = r5
        L6d:
            a(r4, r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mdh.a(java.lang.String, java.net.HttpURLConnection, com.huawei.pluginachievement.connectivity.https.HttpResCallBack):void");
    }

    private static void a(OutputStream outputStream, InputStream inputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
                LogUtil.b("PLGACHIEVE_Http", "postReq e6=IOException");
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused2) {
                LogUtil.b("PLGACHIEVE_Http", "postReq e7=IOException");
            }
        }
    }

    public static int e(String str, final String str2, final HttpResCallBack httpResCallBack) {
        LogUtil.a("PLGACHIEVE_Http", "download enter");
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                try {
                    URLConnection openConnection = URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
                    if (openConnection instanceof HttpURLConnection) {
                        final HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.setReadTimeout(10000);
                        try {
                            httpURLConnection.setRequestMethod("GET");
                            f14894a.execute(new Runnable() { // from class: mdh.5
                                @Override // java.lang.Runnable
                                public void run() {
                                    mdh.b(httpURLConnection, str2, httpResCallBack);
                                }
                            });
                            LogUtil.a("PLGACHIEVE_Http", "download exit");
                            return 0;
                        } catch (ProtocolException e) {
                            LogUtil.b("PLGACHIEVE_Http", "download e=", e.getMessage());
                        }
                    }
                    return -1;
                } catch (IOException unused) {
                    LogUtil.b("PLGACHIEVE_Http", "download e2=IOException");
                    return -1;
                }
            } catch (MalformedURLException e2) {
                LogUtil.b("PLGACHIEVE_Http", "download e=", e2.getMessage());
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b(java.net.HttpURLConnection r9, java.lang.String r10, com.huawei.pluginachievement.connectivity.https.HttpResCallBack r11) {
        /*
            java.lang.String r0 = "download e6=IOException"
            java.lang.String r1 = "PLGACHIEVE_Http"
            r2 = 1
            r3 = 0
            r4 = -1
            r5 = 0
            int r6 = r9.getResponseCode()     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3d
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3d
            java.lang.String r8 = "download-->responseCode:"
            r7[r3] = r8     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3d
            java.lang.Integer r8 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3d
            r7[r2] = r8     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3d
            com.huawei.hwlogsmodel.LogUtil.a(r1, r7)     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3d
            r7 = 200(0xc8, float:2.8E-43)
            if (r6 != r7) goto L2c
            java.io.InputStream r9 = r9.getInputStream()     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3d
            boolean r10 = defpackage.mdk.e(r9, r10)     // Catch: java.io.IOException -> L3e java.lang.Throwable -> L5a
            if (r10 == 0) goto L2d
            r4 = r7
            goto L2d
        L2c:
            r9 = r5
        L2d:
            if (r9 == 0) goto L54
            r9.close()     // Catch: java.io.IOException -> L33
            goto L54
        L33:
            java.lang.Object[] r9 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r9)
            goto L54
        L3b:
            r9 = move-exception
            goto L5d
        L3d:
            r9 = r5
        L3e:
            java.lang.Object[] r10 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L5a
            java.lang.String r2 = "download e4=IOException"
            r10[r3] = r2     // Catch: java.lang.Throwable -> L5a
            com.huawei.hwlogsmodel.LogUtil.b(r1, r10)     // Catch: java.lang.Throwable -> L5a
            if (r9 == 0) goto L54
            r9.close()     // Catch: java.io.IOException -> L4d
            goto L54
        L4d:
            java.lang.Object[] r9 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r9)
        L54:
            if (r11 == 0) goto L59
            r11.onFinished(r4, r5)
        L59:
            return
        L5a:
            r10 = move-exception
            r5 = r9
            r9 = r10
        L5d:
            if (r5 == 0) goto L6a
            r5.close()     // Catch: java.io.IOException -> L63
            goto L6a
        L63:
            java.lang.Object[] r10 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r10)
        L6a:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mdh.b(java.net.HttpURLConnection, java.lang.String, com.huawei.pluginachievement.connectivity.https.HttpResCallBack):void");
    }
}
