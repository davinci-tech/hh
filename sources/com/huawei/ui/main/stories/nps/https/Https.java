package com.huawei.ui.main.stories.nps.https;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import health.compact.a.util.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.HashMap;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes9.dex */
public class Https {
    private static final String DEFAULT_ENCODE = "UTF-8";
    private static final String TAG = "PLGOPER_Https";

    private Https() {
    }

    public static int postReq(Context context, String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, HttpResCallback httpResCallback) {
        HttpURLConnection initConnection = Http.initConnection(str);
        if (!(initConnection instanceof HttpsURLConnection)) {
            if (httpResCallback != null) {
                httpResCallback.onFinished(-1, null);
            }
            return -1;
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) initConnection;
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setUseCaches(false);
        try {
            httpsURLConnection.setRequestMethod("POST");
            HttpUtils.setHeader(context, httpsURLConnection, hashMap2);
            ThreadPoolManager.d().execute(getRunnable(httpsURLConnection, HttpUtils.getBody(hashMap), httpResCallback));
            LogUtil.d(TAG, "exit AchieveHttps.postReq");
            return 0;
        } catch (ProtocolException unused) {
            LogUtil.e(TAG, "exit ProtocolException");
            return -1;
        }
    }

    public static String postReq(Context context, String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        OutputStream outputStream;
        InputStream inputStream;
        String str2;
        LogUtil.d(TAG, "entry Https.postReq");
        HttpURLConnection initConnection = Http.initConnection(str);
        InputStream inputStream2 = null;
        InputStream inputStream3 = null;
        inputStream2 = null;
        String str3 = null;
        if (!(initConnection instanceof HttpsURLConnection)) {
            return null;
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) initConnection;
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setUseCaches(false);
        try {
            httpsURLConnection.setRequestMethod("POST");
            HttpUtils.setHeader(context, httpsURLConnection, hashMap2);
            String body = HttpUtils.getBody(hashMap);
            try {
                if (TextUtils.isEmpty(body)) {
                    outputStream = null;
                } else {
                    outputStream = httpsURLConnection.getOutputStream();
                    if (outputStream != null) {
                        try {
                            outputStream.write(body.getBytes("UTF-8"));
                            outputStream.flush();
                        } catch (IOException unused) {
                            inputStream = null;
                            LogUtil.e(TAG, "postReq IOException");
                            HttpUtils.closeStream(inputStream, outputStream);
                            httpsURLConnection.disconnect();
                            initConnection.disconnect();
                            LogUtil.d(TAG, "exit AchieveHttps.postReq");
                            return str3;
                        } catch (Throwable th) {
                            th = th;
                            HttpUtils.closeStream(inputStream2, outputStream);
                            httpsURLConnection.disconnect();
                            initConnection.disconnect();
                            throw th;
                        }
                    }
                }
                httpsURLConnection.connect();
                int responseCode = httpsURLConnection.getResponseCode();
                LogUtil.d(TAG, "postReq-->responseCode:", Integer.valueOf(responseCode));
                if (responseCode == 200) {
                    inputStream = httpsURLConnection.getInputStream();
                    try {
                        try {
                            str2 = new String(HttpUtils.readInputStream(inputStream), "UTF-8");
                            inputStream3 = inputStream;
                        } catch (IOException unused2) {
                            LogUtil.e(TAG, "postReq IOException");
                            HttpUtils.closeStream(inputStream, outputStream);
                            httpsURLConnection.disconnect();
                            initConnection.disconnect();
                            LogUtil.d(TAG, "exit AchieveHttps.postReq");
                            return str3;
                        }
                    } catch (Throwable th2) {
                        outputStream = outputStream;
                        inputStream2 = inputStream;
                        th = th2;
                        HttpUtils.closeStream(inputStream2, outputStream);
                        httpsURLConnection.disconnect();
                        initConnection.disconnect();
                        throw th;
                    }
                } else {
                    str2 = null;
                }
                HttpUtils.closeStream(inputStream3, outputStream);
                httpsURLConnection.disconnect();
                initConnection.disconnect();
                str3 = str2;
            } catch (IOException unused3) {
                inputStream = null;
                outputStream = null;
            } catch (Throwable th3) {
                th = th3;
                outputStream = null;
            }
            LogUtil.d(TAG, "exit AchieveHttps.postReq");
            return str3;
        } catch (ProtocolException unused4) {
            LogUtil.e(TAG, "postReq ProtocolException!");
            return null;
        }
    }

    public static int download(Context context, String str, final String str2, final HttpResCallback httpResCallback) {
        LogUtil.d(TAG, "entry download");
        final HttpsURLConnection httpsURLConnection = (HttpsURLConnection) Http.initConnection(str);
        if (httpsURLConnection == null) {
            if (httpResCallback != null) {
                httpResCallback.onFinished(-1, null);
            }
            return -1;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.nps.https.Https.1
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
                    java.lang.String r0 = "PLGOPER_Https"
                    r1 = 1
                    r2 = 0
                    r3 = 0
                    r4 = -1
                    javax.net.ssl.HttpsURLConnection r5 = r1     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L43
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
                    javax.net.ssl.HttpsURLConnection r5 = r1     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L43
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
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.nps.https.Https.AnonymousClass1.run():void");
            }
        });
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v9, types: [java.io.InputStream] */
    public static int download(Context context, String str, String str2) {
        Throwable th;
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) Http.initConnection(str);
        int i = -1;
        if (httpsURLConnection == null) {
            return -1;
        }
        try {
            try {
                context = Http.download((Context) context, httpsURLConnection);
            } catch (Throwable th2) {
                th = th2;
                HttpUtils.closeStream(context, null);
                throw th;
            }
        } catch (Exception unused) {
            context = 0;
        } catch (Throwable th3) {
            th = th3;
            context = 0;
            HttpUtils.closeStream(context, null);
            throw th;
        }
        if (context != 0) {
            try {
                boolean saveFile = HttpUtils.saveFile(context, str2);
                context = context;
                if (saveFile) {
                    i = 0;
                    context = context;
                }
            } catch (Exception unused2) {
                LogUtil.e(TAG, "download Exception");
                context = context;
                HttpUtils.closeStream(context, null);
                return i;
            }
        }
        HttpUtils.closeStream(context, null);
        return i;
    }

    public static Bitmap download(Context context, String str) {
        HttpURLConnection initConnection = Http.initConnection(str);
        if (!(initConnection instanceof HttpsURLConnection)) {
            return null;
        }
        InputStream download = Http.download(context, (HttpsURLConnection) initConnection);
        if (download == null) {
            LogUtil.d(TAG, "download inputStream = null");
            return null;
        }
        Bitmap readInputStream = HttpUtils.readInputStream(context, download);
        LogUtil.d(TAG, "exit download");
        return readInputStream;
    }

    private static Runnable getRunnable(final HttpsURLConnection httpsURLConnection, final String str, final HttpResCallback httpResCallback) {
        return new Runnable() { // from class: com.huawei.ui.main.stories.nps.https.Https.2
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
                    java.lang.String r0 = "PLGOPER_Https"
                    r1 = 0
                    r2 = 1
                    r3 = -1
                    r4 = 0
                    java.lang.String r5 = r1     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L62
                    boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L62
                    java.lang.String r6 = "UTF-8"
                    if (r5 != 0) goto L23
                    javax.net.ssl.HttpsURLConnection r5 = r2     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L62
                    java.io.OutputStream r5 = r5.getOutputStream()     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L62
                    java.lang.String r7 = r1     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    byte[] r7 = r7.getBytes(r6)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    r5.write(r7)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    r5.flush()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    goto L24
                L23:
                    r5 = r4
                L24:
                    javax.net.ssl.HttpsURLConnection r7 = r2     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    r7.connect()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
                    javax.net.ssl.HttpsURLConnection r7 = r2     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
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
                    javax.net.ssl.HttpsURLConnection r8 = r2     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
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
                    java.lang.String r6 = "postReq IOException"
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
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.nps.https.Https.AnonymousClass2.run():void");
            }
        };
    }
}
