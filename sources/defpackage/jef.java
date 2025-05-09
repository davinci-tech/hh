package defpackage;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class jef {
    public static int c(String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, final HttpResCallback httpResCallback) {
        LogUtil.a("HwCommonModelHttp", "entry Http.postReq");
        if (!TextUtils.isEmpty(str) && hashMap != null && hashMap2 != null) {
            final HttpURLConnection b = b(str);
            if (b == null) {
                if (httpResCallback != null) {
                    httpResCallback.onFinished(-1, null);
                }
                return -1;
            }
            b.setDoOutput(true);
            b.setDoInput(true);
            b.setUseCaches(false);
            try {
                b.setRequestMethod("POST");
                jei.a(b, hashMap2);
                final String c = jei.c(hashMap);
                LogUtil.c("HwCommonModelHttp", "postReq-->strBody:", c);
                ThreadPoolManager.d().execute(new Runnable() { // from class: jef.2
                    @Override // java.lang.Runnable
                    public void run() {
                        jef.a(c, b, httpResCallback);
                    }
                });
                LogUtil.a("HwCommonModelHttp", "exit AchieveHttps.postReq");
                return 0;
            } catch (ProtocolException e) {
                LogUtil.b("HwCommonModelHttp", e.getMessage());
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.lang.String r11, java.net.HttpURLConnection r12, com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback r13) {
        /*
            java.lang.String r0 = "HwCommonModelHttp"
            r1 = 0
            r2 = 1
            r3 = -1
            r4 = 0
            boolean r5 = android.text.TextUtils.isEmpty(r11)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            if (r5 != 0) goto L1d
            java.io.OutputStream r5 = r12.getOutputStream()     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            java.lang.String r6 = "UTF-8"
            byte[] r11 = r11.getBytes(r6)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            r5.write(r11)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            r5.flush()     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            goto L1e
        L1d:
            r5 = r4
        L1e:
            r12.connect()     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            int r11 = r12.getResponseCode()     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            r6 = 2
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            java.lang.String r8 = "postReq-->responseCode:"
            r7[r1] = r8     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            java.lang.Integer r8 = java.lang.Integer.valueOf(r11)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            r7[r2] = r8     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            com.huawei.hwlogsmodel.LogUtil.a(r0, r7)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            r7 = 200(0xc8, float:2.8E-43)
            if (r11 != r7) goto L5b
            java.io.InputStream r12 = r12.getInputStream()     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64
            byte[] r7 = defpackage.jei.e(r12)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L59
            java.lang.String r8 = new java.lang.String     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L59
            java.nio.charset.Charset r9 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L59
            r8.<init>(r7, r9)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L59
            java.lang.Object[] r4 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L66
            java.lang.String r6 = "postReq-->jsonResult:"
            r4[r1] = r6     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L66
            r4[r2] = r8     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L66
            com.huawei.hwlogsmodel.LogUtil.c(r0, r4)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L66
            r3 = r11
            r4 = r12
            goto L5c
        L56:
            r11 = move-exception
            r4 = r12
            goto L61
        L59:
            r8 = r4
            goto L66
        L5b:
            r8 = r4
        L5c:
            defpackage.jei.c(r5)
            goto L7b
        L60:
            r11 = move-exception
        L61:
            r12 = r4
            r4 = r5
            goto L89
        L64:
            r12 = r4
            r8 = r12
        L66:
            r4 = r5
            r11 = r4
            r4 = r12
            goto L6f
        L6a:
            r11 = move-exception
            r12 = r4
            goto L89
        L6d:
            r11 = r4
            r8 = r11
        L6f:
            java.lang.Object[] r12 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L84
            java.lang.String r2 = "postReq postReqRun IOException,request failed"
            r12[r1] = r2     // Catch: java.lang.Throwable -> L84
            com.huawei.hwlogsmodel.LogUtil.b(r0, r12)     // Catch: java.lang.Throwable -> L84
            defpackage.jei.c(r11)
        L7b:
            defpackage.jei.d(r4)
            if (r13 == 0) goto L83
            r13.onFinished(r3, r8)
        L83:
            return
        L84:
            r12 = move-exception
            r10 = r4
            r4 = r11
            r11 = r12
            r12 = r10
        L89:
            defpackage.jei.c(r4)
            defpackage.jei.d(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jef.a(java.lang.String, java.net.HttpURLConnection, com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback):void");
    }

    public static String b(String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        HttpURLConnection b;
        InputStream inputStream;
        InputStream inputStream2;
        String str2;
        Throwable th;
        OutputStream outputStream;
        LogUtil.a("HwCommonModelHttp", "entry Http.postReq");
        if (TextUtils.isEmpty(str) || hashMap == null || hashMap2 == null || (b = b(str)) == null) {
            return "";
        }
        b.setDoOutput(true);
        b.setDoInput(true);
        b.setUseCaches(false);
        try {
            b.setRequestMethod("POST");
            jei.a(b, hashMap2);
            String c = jei.c(hashMap);
            LogUtil.c("HwCommonModelHttp", "postReq-->strBody:", c);
            OutputStream outputStream2 = null;
            InputStream inputStream3 = null;
            outputStream2 = null;
            try {
                if (TextUtils.isEmpty(c)) {
                    outputStream = null;
                } else {
                    outputStream = b.getOutputStream();
                    try {
                        outputStream.write(c.getBytes("UTF-8"));
                        outputStream.flush();
                    } catch (IOException unused) {
                        inputStream2 = null;
                        str2 = null;
                        outputStream2 = outputStream;
                        try {
                            LogUtil.b("HwCommonModelHttp", "postReq IOException,request failed");
                            jei.c(outputStream2);
                            jei.d(inputStream2);
                            LogUtil.a("HwCommonModelHttp", "exit AchieveHttps.postReq");
                            return str2;
                        } catch (Throwable th2) {
                            th = th2;
                            Throwable th3 = th;
                            inputStream = inputStream2;
                            th = th3;
                            jei.c(outputStream2);
                            jei.d(inputStream);
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        inputStream = null;
                        outputStream2 = outputStream;
                        jei.c(outputStream2);
                        jei.d(inputStream);
                        throw th;
                    }
                }
                b.connect();
                int responseCode = b.getResponseCode();
                LogUtil.a("HwCommonModelHttp", "postReq-->responseCode:", Integer.valueOf(responseCode));
                if (responseCode == 200) {
                    inputStream2 = b.getInputStream();
                    try {
                        try {
                            str2 = new String(jei.e(inputStream2), "UTF-8");
                            try {
                                LogUtil.c("HwCommonModelHttp", "postReq-->jsonResult:", str2);
                                inputStream3 = inputStream2;
                            } catch (IOException unused2) {
                                outputStream2 = outputStream;
                                LogUtil.b("HwCommonModelHttp", "postReq IOException,request failed");
                                jei.c(outputStream2);
                                jei.d(inputStream2);
                                LogUtil.a("HwCommonModelHttp", "exit AchieveHttps.postReq");
                                return str2;
                            }
                        } catch (IOException unused3) {
                            str2 = null;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        outputStream2 = outputStream;
                        Throwable th32 = th;
                        inputStream = inputStream2;
                        th = th32;
                        jei.c(outputStream2);
                        jei.d(inputStream);
                        throw th;
                    }
                } else {
                    str2 = null;
                }
                jei.c(outputStream);
                jei.d(inputStream3);
            } catch (IOException unused4) {
                inputStream2 = null;
                str2 = null;
            } catch (Throwable th6) {
                th = th6;
                inputStream = null;
            }
            LogUtil.a("HwCommonModelHttp", "exit AchieveHttps.postReq");
            return str2;
        } catch (ProtocolException e) {
            LogUtil.b("HwCommonModelHttp", e.getMessage());
            return "";
        }
    }

    public static Bitmap bGr_(String str) {
        InputStream e;
        LogUtil.a("HwCommonModelHttp", "entry download");
        HttpURLConnection b = b(str);
        if (b == null || (e = e(b)) == null) {
            return null;
        }
        Bitmap bGu_ = jei.bGu_(e);
        jei.d(e);
        LogUtil.a("HwCommonModelHttp", "exit download");
        if (bGu_ != null) {
            return bGu_;
        }
        return null;
    }

    public static HttpURLConnection b(String str) {
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
                } catch (ProtocolException e) {
                    LogUtil.b("HwCommonModelHttp", e.getMessage());
                    return httpURLConnection;
                }
            } catch (IOException unused) {
                LogUtil.b("HwCommonModelHttp", "initConnection IOException,initConnection failed");
                return null;
            }
        } catch (MalformedURLException e2) {
            LogUtil.b("HwCommonModelHttp", e2.getMessage());
            return null;
        }
    }

    public static InputStream e(HttpURLConnection httpURLConnection) {
        InputStream inputStream = null;
        if (httpURLConnection == null) {
            return null;
        }
        try {
            try {
                int responseCode = httpURLConnection.getResponseCode();
                LogUtil.a("HwCommonModelHttp", "download-->responseCode:", Integer.valueOf(responseCode));
                if (responseCode == 200) {
                    inputStream = httpURLConnection.getInputStream();
                }
            } catch (IOException unused) {
                LogUtil.b("HwCommonModelHttp", "download IOException,download failed");
            }
            jei.d(inputStream);
            LogUtil.a("HwCommonModelHttp", "exit download");
            return inputStream;
        } catch (Throwable th) {
            jei.d(null);
            throw th;
        }
    }
}
