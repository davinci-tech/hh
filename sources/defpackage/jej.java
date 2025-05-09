package defpackage;

import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew;
import health.compact.a.CommonUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes5.dex */
public class jej {
    public static int b(String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, final HttpResCallback httpResCallback) {
        LogUtil.a("HwCommonModelHttps", "entry Https.postReq");
        if (!TextUtils.isEmpty(str) && hashMap != null && hashMap2 != null && httpResCallback != null) {
            HttpURLConnection b = jef.b(str);
            if (!(b instanceof HttpsURLConnection)) {
                return -1;
            }
            final HttpsURLConnection httpsURLConnection = (HttpsURLConnection) b;
            d(httpsURLConnection);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setUseCaches(false);
            try {
                httpsURLConnection.setRequestMethod("POST");
                jei.a(httpsURLConnection, hashMap2);
                final String c = jei.c(hashMap);
                LogUtil.c("HwCommonModelHttps", "postReq-->strBody:", c);
                ThreadPoolManager.d().execute(new Runnable() { // from class: jej.1
                    @Override // java.lang.Runnable
                    public void run() {
                        jej.c(c, httpsURLConnection, httpResCallback);
                    }
                });
                LogUtil.a("HwCommonModelHttps", "exit AchieveHttps.postReq");
                return 0;
            } catch (ProtocolException e) {
                LogUtil.b("HwCommonModelHttps", e.getMessage());
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:21:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void c(java.lang.String r10, javax.net.ssl.HttpsURLConnection r11, com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback r12) {
        /*
            java.lang.String r0 = "HwCommonModelHttps"
            r1 = 2
            r2 = 1
            r3 = 0
            r4 = -1
            r5 = 0
            boolean r6 = android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Throwable -> L73 java.lang.NullPointerException -> L76 java.io.IOException -> L78
            java.lang.String r7 = "UTF-8"
            if (r6 != 0) goto L1e
            java.io.OutputStream r6 = r11.getOutputStream()     // Catch: java.lang.Throwable -> L73 java.lang.NullPointerException -> L76 java.io.IOException -> L78
            byte[] r10 = r10.getBytes(r7)     // Catch: java.lang.Throwable -> L68 java.lang.NullPointerException -> L6c java.io.IOException -> L6e
            r6.write(r10)     // Catch: java.lang.Throwable -> L68 java.lang.NullPointerException -> L6c java.io.IOException -> L6e
            r6.flush()     // Catch: java.lang.Throwable -> L68 java.lang.NullPointerException -> L6c java.io.IOException -> L6e
            goto L1f
        L1e:
            r6 = r5
        L1f:
            r11.connect()     // Catch: java.lang.Throwable -> L68 java.lang.NullPointerException -> L6c java.io.IOException -> L6e
            int r10 = r11.getResponseCode()     // Catch: java.lang.Throwable -> L68 java.lang.NullPointerException -> L6c java.io.IOException -> L6e
            java.lang.Object[] r8 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L68 java.lang.NullPointerException -> L6c java.io.IOException -> L6e
            java.lang.String r9 = "postReq-->responseCode:"
            r8[r3] = r9     // Catch: java.lang.Throwable -> L68 java.lang.NullPointerException -> L6c java.io.IOException -> L6e
            java.lang.Integer r9 = java.lang.Integer.valueOf(r10)     // Catch: java.lang.Throwable -> L68 java.lang.NullPointerException -> L6c java.io.IOException -> L6e
            r8[r2] = r9     // Catch: java.lang.Throwable -> L68 java.lang.NullPointerException -> L6c java.io.IOException -> L6e
            com.huawei.hwlogsmodel.LogUtil.a(r0, r8)     // Catch: java.lang.Throwable -> L68 java.lang.NullPointerException -> L6c java.io.IOException -> L6e
            r8 = 200(0xc8, float:2.8E-43)
            if (r10 != r8) goto L60
            java.io.InputStream r11 = r11.getInputStream()     // Catch: java.lang.Throwable -> L68 java.lang.NullPointerException -> L6c java.io.IOException -> L6e
            byte[] r8 = defpackage.jei.e(r11)     // Catch: java.lang.Throwable -> L58 java.lang.NullPointerException -> L5b java.io.IOException -> L5d
            java.lang.String r9 = new java.lang.String     // Catch: java.lang.Throwable -> L58 java.lang.NullPointerException -> L5b java.io.IOException -> L5d
            r9.<init>(r8, r7)     // Catch: java.lang.Throwable -> L58 java.lang.NullPointerException -> L5b java.io.IOException -> L5d
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch: java.lang.NullPointerException -> L54 java.io.IOException -> L56 java.lang.Throwable -> L58
            java.lang.String r7 = "postReq-->jsonResult :"
            r5[r3] = r7     // Catch: java.lang.NullPointerException -> L54 java.io.IOException -> L56 java.lang.Throwable -> L58
            r5[r2] = r9     // Catch: java.lang.NullPointerException -> L54 java.io.IOException -> L56 java.lang.Throwable -> L58
            com.huawei.hwlogsmodel.LogUtil.c(r0, r5)     // Catch: java.lang.NullPointerException -> L54 java.io.IOException -> L56 java.lang.Throwable -> L58
            r4 = r10
            r5 = r11
            goto L61
        L54:
            r10 = move-exception
            goto L71
        L56:
            r10 = move-exception
            goto L71
        L58:
            r10 = move-exception
            r5 = r11
            goto L69
        L5b:
            r10 = move-exception
            goto L5e
        L5d:
            r10 = move-exception
        L5e:
            r9 = r5
            goto L71
        L60:
            r9 = r5
        L61:
            defpackage.jei.c(r6)
            defpackage.jei.d(r5)
            goto L99
        L68:
            r10 = move-exception
        L69:
            r11 = r5
            r5 = r6
            goto La0
        L6c:
            r10 = move-exception
            goto L6f
        L6e:
            r10 = move-exception
        L6f:
            r11 = r5
            r9 = r11
        L71:
            r5 = r6
            goto L7b
        L73:
            r10 = move-exception
            r11 = r5
            goto La0
        L76:
            r10 = move-exception
            goto L79
        L78:
            r10 = move-exception
        L79:
            r11 = r5
            r9 = r11
        L7b:
            r6 = 3
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L9f
            java.lang.String r7 = "postReq postReqRun "
            r6[r3] = r7     // Catch: java.lang.Throwable -> L9f
            java.lang.Class r10 = r10.getClass()     // Catch: java.lang.Throwable -> L9f
            java.lang.String r10 = r10.getSimpleName()     // Catch: java.lang.Throwable -> L9f
            r6[r2] = r10     // Catch: java.lang.Throwable -> L9f
            java.lang.String r10 = ", request failed"
            r6[r1] = r10     // Catch: java.lang.Throwable -> L9f
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r0, r6)     // Catch: java.lang.Throwable -> L9f
            defpackage.jei.c(r5)
            defpackage.jei.d(r11)
        L99:
            if (r12 == 0) goto L9e
            r12.onFinished(r4, r9)
        L9e:
            return
        L9f:
            r10 = move-exception
        La0:
            defpackage.jei.c(r5)
            defpackage.jei.d(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jej.c(java.lang.String, javax.net.ssl.HttpsURLConnection, com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback):void");
    }

    public static String b(String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        LogUtil.a("HwCommonModelHttps", "entry Https.postReq");
        if (!TextUtils.isEmpty(str) && hashMap != null && hashMap2 != null) {
            HttpURLConnection b = jef.b(str);
            if (!(b instanceof HttpsURLConnection)) {
                return "";
            }
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) b;
            d(httpsURLConnection);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setUseCaches(false);
            try {
                httpsURLConnection.setRequestMethod("POST");
                jei.a(httpsURLConnection, hashMap2);
                String c = jei.c(hashMap);
                LogUtil.c("HwCommonModelHttps", "postReq-->strBody:", c);
                return d(httpsURLConnection, c);
            } catch (ProtocolException e) {
                LogUtil.b("HwCommonModelHttps", e.getMessage());
            }
        }
        return "";
    }

    private static String d(HttpsURLConnection httpsURLConnection, String str) {
        Throwable th;
        InputStream inputStream;
        String str2;
        OutputStream outputStream;
        OutputStream outputStream2 = null;
        InputStream inputStream2 = null;
        InputStream inputStream3 = null;
        outputStream2 = null;
        try {
            if (TextUtils.isEmpty(str)) {
                outputStream = null;
            } else {
                outputStream = httpsURLConnection.getOutputStream();
                try {
                    outputStream.write(str.getBytes("UTF-8"));
                    outputStream.flush();
                } catch (IOException unused) {
                    str2 = null;
                    inputStream = inputStream3;
                    outputStream2 = outputStream;
                    try {
                        LogUtil.b("HwCommonModelHttps", "postReq postReqJsonResult IOException,request failed");
                        jei.c(outputStream2);
                        jei.d(inputStream);
                        LogUtil.a("HwCommonModelHttps", "exit AchieveHttps.postReq");
                        return str2;
                    } catch (Throwable th2) {
                        th = th2;
                        jei.c(outputStream2);
                        jei.d(inputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    inputStream = null;
                    outputStream2 = outputStream;
                    jei.c(outputStream2);
                    jei.d(inputStream);
                    throw th;
                }
            }
            httpsURLConnection.connect();
            int responseCode = httpsURLConnection.getResponseCode();
            LogUtil.a("HwCommonModelHttps", "postReq postReqJsonResult responseCode:", Integer.valueOf(responseCode));
            if (responseCode == 200) {
                inputStream = httpsURLConnection.getInputStream();
                try {
                    try {
                        str2 = new String(jei.e(inputStream), "UTF-8");
                        try {
                            LogUtil.c("HwCommonModelHttps", "postReq postReqJsonResult jsonResult:", str2);
                            inputStream2 = inputStream;
                        } catch (IOException unused2) {
                            inputStream3 = inputStream;
                            inputStream = inputStream3;
                            outputStream2 = outputStream;
                            LogUtil.b("HwCommonModelHttps", "postReq postReqJsonResult IOException,request failed");
                            jei.c(outputStream2);
                            jei.d(inputStream);
                            LogUtil.a("HwCommonModelHttps", "exit AchieveHttps.postReq");
                            return str2;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        outputStream2 = outputStream;
                        jei.c(outputStream2);
                        jei.d(inputStream);
                        throw th;
                    }
                } catch (IOException unused3) {
                    str2 = null;
                }
            } else {
                str2 = null;
            }
            jei.c(outputStream);
            jei.d(inputStream2);
        } catch (IOException unused4) {
            inputStream = null;
            str2 = null;
        } catch (Throwable th5) {
            th = th5;
            inputStream = null;
        }
        LogUtil.a("HwCommonModelHttps", "exit AchieveHttps.postReq");
        return str2;
    }

    public static void d(HttpsURLConnection httpsURLConnection) {
        if (CommonUtil.cc()) {
            LogUtil.a("HwCommonModelHttps", "this is testVersion");
            return;
        }
        try {
            httpsURLConnection.setSSLSocketFactory(SecureSSLSocketFactoryNew.getInstance(BaseApplication.getContext()));
            httpsURLConnection.setHostnameVerifier(HttpsURLConnection.getDefaultHostnameVerifier());
        } catch (IOException unused) {
            LogUtil.b("HwCommonModelHttps", "initHttpsUrlConnection gets an exception");
        } catch (IllegalAccessException e) {
            e = e;
            LogUtil.b("HwCommonModelHttps", "initHttpsURLConnection exception", e.getMessage());
        } catch (GeneralSecurityException e2) {
            e = e2;
            LogUtil.b("HwCommonModelHttps", "initHttpsURLConnection exception", e.getMessage());
        }
    }
}
