package com.huawei.ui.main.stories.nps.https;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.haf.threadpool.ThreadPoolManager;
import health.compact.a.util.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class Http {
    private static final String DEFAULT_ENCODE = "UTF-8";
    private static final String TAG = "PLGOPER_Http";

    private Http() {
    }

    public static int postReq(Context context, String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, HttpResCallback httpResCallback) {
        HttpURLConnection initConnection = initConnection(str);
        if (initConnection == null) {
            if (httpResCallback != null) {
                httpResCallback.onFinished(-1, null);
            }
            return -1;
        }
        initConnection.setDoOutput(true);
        initConnection.setDoInput(true);
        initConnection.setUseCaches(false);
        try {
            initConnection.setRequestMethod("POST");
            HttpUtils.setHeader(context, initConnection, hashMap2);
            ThreadPoolManager.d().execute(getRunnable(initConnection, HttpUtils.getBody(hashMap), httpResCallback));
            return 0;
        } catch (ProtocolException unused) {
            LogUtil.e(TAG, "postReq ProtocolException!");
            return -1;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r8v11, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    public static String postReq(Context context, String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        ?? r8;
        InputStream inputStream;
        String str2;
        HttpURLConnection initConnection = initConnection(str);
        InputStream inputStream2 = null;
        InputStream inputStream3 = null;
        inputStream2 = null;
        String str3 = null;
        if (initConnection == null) {
            return null;
        }
        initConnection.setDoOutput(true);
        initConnection.setDoInput(true);
        initConnection.setUseCaches(false);
        try {
            initConnection.setRequestMethod("POST");
            HttpUtils.setHeader(context, initConnection, hashMap2);
            ?? body = HttpUtils.getBody(hashMap);
            try {
                try {
                    if (TextUtils.isEmpty(body)) {
                        r8 = 0;
                    } else {
                        r8 = initConnection.getOutputStream();
                        try {
                            r8.write(body.getBytes("UTF-8"));
                            r8.flush();
                            r8 = r8;
                        } catch (IOException unused) {
                            inputStream = null;
                            LogUtil.e(TAG, "postReq IOException");
                            HttpUtils.closeStream(inputStream, r8);
                            LogUtil.d(TAG, "exit AchieveHttps.postReq");
                            return str3;
                        } catch (Throwable th) {
                            th = th;
                            HttpUtils.closeStream(inputStream2, r8);
                            throw th;
                        }
                    }
                    initConnection.connect();
                    int responseCode = initConnection.getResponseCode();
                    LogUtil.d(TAG, "postReq-->responseCode:", Integer.valueOf(responseCode));
                    if (responseCode == 200) {
                        inputStream = initConnection.getInputStream();
                        try {
                            str2 = new String(HttpUtils.readInputStream(inputStream), "UTF-8");
                            inputStream3 = inputStream;
                        } catch (IOException unused2) {
                            LogUtil.e(TAG, "postReq IOException");
                            HttpUtils.closeStream(inputStream, r8);
                            LogUtil.d(TAG, "exit AchieveHttps.postReq");
                            return str3;
                        }
                    } else {
                        str2 = null;
                    }
                    HttpUtils.closeStream(inputStream3, r8);
                    str3 = str2;
                } catch (Throwable th2) {
                    r8 = hashMap;
                    inputStream2 = body;
                    th = th2;
                }
            } catch (IOException unused3) {
                inputStream = null;
                r8 = 0;
            } catch (Throwable th3) {
                th = th3;
                r8 = 0;
            }
            LogUtil.d(TAG, "exit AchieveHttps.postReq");
            return str3;
        } catch (ProtocolException unused4) {
            LogUtil.e(TAG, "postReq ProtocolException!");
            return null;
        }
    }

    public static int download(Context context, String str, final String str2, final HttpResCallback httpResCallback) {
        final HttpURLConnection initConnection = initConnection(str);
        if (initConnection == null && httpResCallback != null) {
            httpResCallback.onFinished(-1, null);
            return -1;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.nps.https.Http.1
            /* JADX WARN: Removed duplicated region for block: B:14:0x0055  */
            /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    r10 = this;
                    java.lang.String r0 = "PLGOPER_Http"
                    r1 = 1
                    r2 = 0
                    r3 = 0
                    r4 = -1
                    java.net.HttpURLConnection r5 = r1     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L43
                    int r5 = r5.getResponseCode()     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L43
                    r6 = 2
                    java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L43
                    java.lang.String r7 = "download-->responseCode:"
                    r6[r2] = r7     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L43
                    java.lang.Integer r7 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L43
                    r6[r1] = r7     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L43
                    health.compact.a.util.LogUtil.d(r0, r6)     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L43
                    r6 = 200(0xc8, float:2.8E-43)
                    if (r5 != r6) goto L3d
                    java.net.HttpURLConnection r5 = r1     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L43
                    java.io.InputStream r5 = r5.getInputStream()     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L43
                    byte[] r7 = com.huawei.ui.main.stories.nps.https.HttpUtils.readInputStream(r5)     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L59
                    java.lang.String r8 = new java.lang.String     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L59
                    java.lang.String r9 = "UTF-8"
                    r8.<init>(r7, r9)     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L59
                    java.lang.String r7 = r2     // Catch: java.io.IOException -> L45 java.lang.Throwable -> L59
                    boolean r0 = com.huawei.ui.main.stories.nps.https.HttpUtils.saveFile(r5, r7)     // Catch: java.io.IOException -> L45 java.lang.Throwable -> L59
                    if (r0 == 0) goto L4e
                    r4 = r6
                    goto L4e
                L3b:
                    r8 = r3
                    goto L45
                L3d:
                    r5 = r3
                    r8 = r5
                    goto L4e
                L40:
                    r0 = move-exception
                    r5 = r3
                    goto L5a
                L43:
                    r5 = r3
                    r8 = r5
                L45:
                    java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L59
                    java.lang.String r6 = "download IOException"
                    r1[r2] = r6     // Catch: java.lang.Throwable -> L59
                    health.compact.a.util.LogUtil.e(r0, r1)     // Catch: java.lang.Throwable -> L59
                L4e:
                    com.huawei.ui.main.stories.nps.https.HttpUtils.closeStream(r5, r3)
                    com.huawei.ui.main.stories.nps.https.HttpResCallback r0 = r3
                    if (r0 == 0) goto L58
                    r0.onFinished(r4, r8)
                L58:
                    return
                L59:
                    r0 = move-exception
                L5a:
                    com.huawei.ui.main.stories.nps.https.HttpUtils.closeStream(r5, r3)
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.nps.https.Http.AnonymousClass1.run():void");
            }
        });
        return 0;
    }

    public static int download(Context context, String str, String str2) {
        InputStream download = download(context, initConnection(str));
        if (download == null) {
            return -1;
        }
        int i = HttpUtils.saveFile(download, str2) ? 0 : -1;
        try {
            download.close();
        } catch (IOException unused) {
            LogUtil.e(TAG, "download IOException!");
        }
        return i;
    }

    public static Bitmap download(Context context, String str) {
        InputStream download = download(context, initConnection(str));
        Bitmap readInputStream = download != null ? HttpUtils.readInputStream(context, download) : null;
        if (readInputStream == null) {
            LogUtil.c(TAG, "bitmap is null");
        }
        return readInputStream;
    }

    public static InputStream download(Context context, HttpURLConnection httpURLConnection) {
        InputStream inputStream = null;
        if (httpURLConnection == null) {
            return null;
        }
        try {
            try {
                int responseCode = httpURLConnection.getResponseCode();
                LogUtil.d(TAG, "download-->responseCode:", Integer.valueOf(responseCode));
                InputStream inputStream2 = responseCode == 200 ? httpURLConnection.getInputStream() : null;
                HttpUtils.closeStream(inputStream2, null);
                inputStream = inputStream2;
            } catch (IOException unused) {
                LogUtil.e(TAG, "download IOException");
                HttpUtils.closeStream(null, null);
            }
            LogUtil.d(TAG, "exit download");
            return inputStream;
        } catch (Throwable th) {
            HttpUtils.closeStream(null, null);
            throw th;
        }
    }

    public static HttpURLConnection initConnection(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            try {
                URLConnection openConnection = URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
                if (!(openConnection instanceof HttpURLConnection)) {
                    return null;
                }
                HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(5000);
                try {
                    httpURLConnection.setRequestMethod("GET");
                    return httpURLConnection;
                } catch (ProtocolException unused) {
                    LogUtil.e(TAG, "initConnection ProtocolException!");
                    return httpURLConnection;
                }
            } catch (IOException unused2) {
                LogUtil.e(TAG, "initConnection IOException!");
                return null;
            }
        } catch (MalformedURLException unused3) {
            LogUtil.e(TAG, "initConnection MalformedURLException!");
            return null;
        }
    }

    private static Runnable getRunnable(final HttpURLConnection httpURLConnection, final String str, final HttpResCallback httpResCallback) {
        return new Runnable() { // from class: com.huawei.ui.main.stories.nps.https.Http.2
            /* JADX WARN: Removed duplicated region for block: B:19:0x0074  */
            /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    r11 = this;
                    java.lang.String r0 = "PLGOPER_Http"
                    r1 = 0
                    r2 = 1
                    r3 = -1
                    r4 = 0
                    java.lang.String r5 = r1     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L62
                    boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L62
                    java.lang.String r6 = "UTF-8"
                    if (r5 != 0) goto L23
                    java.net.HttpURLConnection r5 = r2     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L62
                    java.io.OutputStream r5 = r5.getOutputStream()     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L62
                    java.lang.String r7 = r1     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    byte[] r7 = r7.getBytes(r6)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    r5.write(r7)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    r5.flush()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    goto L24
                L23:
                    r5 = r4
                L24:
                    java.net.HttpURLConnection r7 = r2     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    r7.connect()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    java.net.HttpURLConnection r7 = r2     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    int r7 = r7.getResponseCode()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    r8 = 2
                    java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    java.lang.String r9 = "postReq-->responseCode:"
                    r8[r1] = r9     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    java.lang.Integer r9 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    r8[r2] = r9     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    health.compact.a.util.LogUtil.d(r0, r8)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    r8 = 200(0xc8, float:2.8E-43)
                    if (r7 != r8) goto L55
                    java.net.HttpURLConnection r8 = r2     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    java.io.InputStream r8 = r8.getInputStream()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    byte[] r9 = com.huawei.ui.main.stories.nps.https.HttpUtils.readInputStream(r8)     // Catch: java.io.IOException -> L64 java.lang.Throwable -> L78
                    java.lang.String r10 = new java.lang.String     // Catch: java.io.IOException -> L64 java.lang.Throwable -> L78
                    r10.<init>(r9, r6)     // Catch: java.io.IOException -> L64 java.lang.Throwable -> L78
                    r3 = r7
                    r4 = r8
                    goto L56
                L55:
                    r10 = r4
                L56:
                    com.huawei.ui.main.stories.nps.https.HttpUtils.closeStream(r4, r5)
                    r4 = r10
                    goto L70
                L5b:
                    r0 = move-exception
                    goto L7c
                L5d:
                    r8 = r4
                    goto L64
                L5f:
                    r0 = move-exception
                    r5 = r4
                    goto L7c
                L62:
                    r5 = r4
                    r8 = r5
                L64:
                    java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L78
                    java.lang.String r6 = "http IOException"
                    r2[r1] = r6     // Catch: java.lang.Throwable -> L78
                    health.compact.a.util.LogUtil.e(r0, r2)     // Catch: java.lang.Throwable -> L78
                    com.huawei.ui.main.stories.nps.https.HttpUtils.closeStream(r8, r5)
                L70:
                    com.huawei.ui.main.stories.nps.https.HttpResCallback r0 = r3
                    if (r0 == 0) goto L77
                    r0.onFinished(r3, r4)
                L77:
                    return
                L78:
                    r0 = move-exception
                    r4 = r5
                    r5 = r4
                    r4 = r8
                L7c:
                    com.huawei.ui.main.stories.nps.https.HttpUtils.closeStream(r4, r5)
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.nps.https.Http.AnonymousClass2.run():void");
            }
        };
    }
}
