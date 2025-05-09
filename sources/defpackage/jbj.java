package defpackage;

import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hms.framework.network.restclient.hwhttp.Response;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.SharedPreferenceManager;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jbj {
    public static HttpsURLConnection b(String str) {
        HttpsURLConnection httpsURLConnection = null;
        try {
            try {
                URLConnection openConnection = URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
                if (openConnection instanceof HttpsURLConnection) {
                    HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) openConnection;
                    try {
                        httpsURLConnection2.setConnectTimeout(5000);
                        httpsURLConnection2.setReadTimeout(5000);
                        httpsURLConnection = httpsURLConnection2;
                    } catch (IOException unused) {
                        httpsURLConnection = httpsURLConnection2;
                        LogUtil.b("AGR_HttpUtils", "httpURLConnection open failed");
                        return httpsURLConnection;
                    }
                }
                if (httpsURLConnection != null) {
                    try {
                        httpsURLConnection.setRequestMethod("POST");
                    } catch (ProtocolException unused2) {
                        LogUtil.b("AGR_HttpUtils", "ProtocolException");
                    }
                }
                return httpsURLConnection;
            } catch (IOException unused3) {
            }
        } catch (MalformedURLException unused4) {
            LogUtil.b("AGR_HttpUtils", "MalformedURLException");
            return null;
        }
    }

    public static void c(HttpsURLConnection httpsURLConnection) {
        if (httpsURLConnection == null) {
            LogUtil.a("AGR_HttpUtils", "initHttpsURLConnection httpsURLConnection is null");
            return;
        }
        if (CommonUtil.cc()) {
            LogUtil.a("AGR_HttpUtils", "this is testversion...");
            httpsURLConnection.setHostnameVerifier(d());
            try {
                SSLContext sSLContext = SSLContext.getInstance("TLS");
                sSLContext.init(null, a(), new SecureRandom());
                httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
                return;
            } catch (KeyManagementException unused) {
                LogUtil.b("AGR_HttpUtils", "initHttpsURLConnection KeyManagementException");
                return;
            } catch (NoSuchAlgorithmException unused2) {
                LogUtil.b("AGR_HttpUtils", "initHttpsURLConnection NoSuchAlgorithmException");
                return;
            }
        }
        try {
            SecureSSLSocketFactoryNew secureSSLSocketFactoryNew = SecureSSLSocketFactoryNew.getInstance(BaseApplication.getContext());
            httpsURLConnection.setHostnameVerifier(HttpsURLConnection.getDefaultHostnameVerifier());
            if (secureSSLSocketFactoryNew != null) {
                httpsURLConnection.setSSLSocketFactory(secureSSLSocketFactoryNew);
            }
        } catch (IOException unused3) {
            LogUtil.b("AGR_HttpUtils", " IOException");
        } catch (IllegalAccessException unused4) {
            LogUtil.b("AGR_HttpUtils", " IllegalAccessException");
        } catch (KeyManagementException unused5) {
            LogUtil.b("AGR_HttpUtils", " KeyManagementException");
        } catch (KeyStoreException unused6) {
            LogUtil.b("AGR_HttpUtils", " KeyStoreException");
        } catch (NoSuchAlgorithmException unused7) {
            LogUtil.b("AGR_HttpUtils", " NoSuchAlgorithmException");
        } catch (CertificateException unused8) {
            LogUtil.b("AGR_HttpUtils", " CertificateException");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [javax.net.ssl.HttpsURLConnection] */
    public static String c(String str, String str2) {
        Throwable th;
        HttpsURLConnection httpsURLConnection;
        ?? r2 = 0;
        try {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("AGR_HttpUtils", "postData url is empty");
                return null;
            }
            try {
                httpsURLConnection = (HttpsURLConnection) URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
                try {
                    httpsURLConnection.setSSLSocketFactory(SecureSSLSocketFactoryNew.getInstance(BaseApplication.getContext()));
                    httpsURLConnection.setHostnameVerifier(HttpsURLConnection.getDefaultHostnameVerifier());
                    String b = b(httpsURLConnection, str2);
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    return b;
                } catch (IOException | IllegalAccessException | GeneralSecurityException unused) {
                    LogUtil.b("AGR_HttpUtils", "postData GeneralSecurityException|IOException|IllegalAccessException");
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    return null;
                }
            } catch (IOException | IllegalAccessException | GeneralSecurityException unused2) {
                httpsURLConnection = null;
            } catch (Throwable th2) {
                th = th2;
                if (r2 != 0) {
                    r2.disconnect();
                }
                throw th;
            }
        } catch (Throwable th3) {
            r2 = str;
            th = th3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v8 */
    private static String b(HttpURLConnection httpURLConnection, String str) {
        Throwable th;
        InputStream inputStream;
        Exception e;
        InputStream inputStream2;
        InputStreamReader inputStreamReader;
        OutputStream outputStream;
        InputStream inputStream3;
        String str2;
        if ("1".equals(SharedPreferenceManager.b(BaseApplication.getContext(), "developeroptions", "developerswitch"))) {
            String b = SharedPreferenceManager.b(BaseApplication.getContext(), "app_enter_module", "app_environment_change_url");
            if (!TextUtils.isEmpty(b) && httpURLConnection != null) {
                httpURLConnection.setRequestProperty("Device-Id", b);
            }
        }
        ?? r0 = 5000;
        ?? r3 = 0;
        r3 = 0;
        InputStreamReader inputStreamReader2 = null;
        r3 = 0;
        try {
            try {
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("POST");
                if (str == null || str.trim().isEmpty()) {
                    outputStream = null;
                } else {
                    httpURLConnection.setDoOutput(true);
                    outputStream = httpURLConnection.getOutputStream();
                    try {
                        outputStream.write(str.getBytes(StandardCharsets.UTF_8));
                        outputStream.flush();
                        outputStream = outputStream;
                    } catch (IOException e2) {
                        e = e2;
                        e = e;
                        inputStream2 = null;
                        inputStreamReader = null;
                        LogUtil.b("AGR_HttpUtils", "postData ", e.getClass().getSimpleName(), " ex = ", e.getMessage(), ", cause = ", e.getCause());
                        IoUtils.e(inputStreamReader);
                        IoUtils.e(outputStream);
                        IoUtils.e(inputStream2);
                        return null;
                    } catch (NullPointerException e3) {
                        e = e3;
                        e = e;
                        inputStream2 = null;
                        inputStreamReader = null;
                        LogUtil.b("AGR_HttpUtils", "postData ", e.getClass().getSimpleName(), " ex = ", e.getMessage(), ", cause = ", e.getCause());
                        IoUtils.e(inputStreamReader);
                        IoUtils.e(outputStream);
                        IoUtils.e(inputStream2);
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = null;
                        r0 = outputStream;
                        IoUtils.e((Closeable) r3);
                        IoUtils.e((Closeable) r0);
                        IoUtils.e(inputStream);
                        throw th;
                    }
                }
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == 200) {
                    inputStream2 = httpURLConnection.getInputStream();
                    try {
                        inputStreamReader = new InputStreamReader(inputStream2, StandardCharsets.UTF_8);
                        try {
                            StringBuilder sb = new StringBuilder(1024);
                            char[] cArr = new char[1024];
                            while (true) {
                                int read = inputStreamReader.read(cArr, 0, 1024);
                                if (read == -1) {
                                    break;
                                }
                                sb.append(cArr, 0, read);
                            }
                            str2 = sb.toString();
                            inputStreamReader2 = inputStreamReader;
                            inputStream3 = inputStream2;
                        } catch (IOException e4) {
                            e = e4;
                            LogUtil.b("AGR_HttpUtils", "postData ", e.getClass().getSimpleName(), " ex = ", e.getMessage(), ", cause = ", e.getCause());
                            IoUtils.e(inputStreamReader);
                            IoUtils.e(outputStream);
                            IoUtils.e(inputStream2);
                            return null;
                        } catch (NullPointerException e5) {
                            e = e5;
                            LogUtil.b("AGR_HttpUtils", "postData ", e.getClass().getSimpleName(), " ex = ", e.getMessage(), ", cause = ", e.getCause());
                            IoUtils.e(inputStreamReader);
                            IoUtils.e(outputStream);
                            IoUtils.e(inputStream2);
                            return null;
                        }
                    } catch (IOException e6) {
                        e = e6;
                        e = e;
                        inputStreamReader = null;
                        LogUtil.b("AGR_HttpUtils", "postData ", e.getClass().getSimpleName(), " ex = ", e.getMessage(), ", cause = ", e.getCause());
                        IoUtils.e(inputStreamReader);
                        IoUtils.e(outputStream);
                        IoUtils.e(inputStream2);
                        return null;
                    } catch (NullPointerException e7) {
                        e = e7;
                        e = e;
                        inputStreamReader = null;
                        LogUtil.b("AGR_HttpUtils", "postData ", e.getClass().getSimpleName(), " ex = ", e.getMessage(), ", cause = ", e.getCause());
                        IoUtils.e(inputStreamReader);
                        IoUtils.e(outputStream);
                        IoUtils.e(inputStream2);
                        return null;
                    } catch (Throwable th3) {
                        th = th3;
                        r0 = outputStream;
                        inputStream = inputStream2;
                        IoUtils.e((Closeable) r3);
                        IoUtils.e((Closeable) r0);
                        IoUtils.e(inputStream);
                        throw th;
                    }
                } else {
                    inputStream3 = null;
                    str2 = null;
                }
                IoUtils.e(inputStreamReader2);
                IoUtils.e(outputStream);
                IoUtils.e(inputStream3);
                return str2;
            } catch (Throwable th4) {
                th = th4;
                r3 = str;
                inputStream = httpURLConnection;
            }
        } catch (IOException e8) {
            e = e8;
            e = e;
            inputStream2 = null;
            inputStreamReader = null;
            outputStream = null;
            LogUtil.b("AGR_HttpUtils", "postData ", e.getClass().getSimpleName(), " ex = ", e.getMessage(), ", cause = ", e.getCause());
            IoUtils.e(inputStreamReader);
            IoUtils.e(outputStream);
            IoUtils.e(inputStream2);
            return null;
        } catch (NullPointerException e9) {
            e = e9;
            e = e;
            inputStream2 = null;
            inputStreamReader = null;
            outputStream = null;
            LogUtil.b("AGR_HttpUtils", "postData ", e.getClass().getSimpleName(), " ex = ", e.getMessage(), ", cause = ", e.getCause());
            IoUtils.e(inputStreamReader);
            IoUtils.e(outputStream);
            IoUtils.e(inputStream2);
            return null;
        } catch (Throwable th5) {
            th = th5;
            inputStream = null;
            r0 = 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean b(java.lang.String r5, java.lang.String r6) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            java.lang.String r1 = "AGR_HttpUtils"
            r2 = 0
            if (r0 != 0) goto L92
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 == 0) goto L11
            goto L92
        L11:
            java.lang.String r6 = health.compact.a.CommonUtil.c(r6)
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 == 0) goto L25
            java.lang.String r5 = "downloadFile safeFilePath is empty"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r5)
            return r2
        L25:
            r0 = 0
            java.net.URL r3 = new java.net.URL     // Catch: java.lang.Throwable -> L5c java.lang.ClassCastException -> L5f java.lang.IllegalAccessException -> L62 java.io.IOException -> L64 java.security.GeneralSecurityException -> L66
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L5c java.lang.ClassCastException -> L5f java.lang.IllegalAccessException -> L62 java.io.IOException -> L64 java.security.GeneralSecurityException -> L66
            java.net.URLConnection r5 = r3.openConnection()     // Catch: java.lang.Throwable -> L5c java.lang.ClassCastException -> L5f java.lang.IllegalAccessException -> L62 java.io.IOException -> L64 java.security.GeneralSecurityException -> L66
            java.net.URLConnection r5 = com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation.openConnection(r5)     // Catch: java.lang.Throwable -> L5c java.lang.ClassCastException -> L5f java.lang.IllegalAccessException -> L62 java.io.IOException -> L64 java.security.GeneralSecurityException -> L66
            javax.net.ssl.HttpsURLConnection r5 = (javax.net.ssl.HttpsURLConnection) r5     // Catch: java.lang.Throwable -> L5c java.lang.ClassCastException -> L5f java.lang.IllegalAccessException -> L62 java.io.IOException -> L64 java.security.GeneralSecurityException -> L66
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Throwable -> L51 java.lang.ClassCastException -> L53 java.lang.IllegalAccessException -> L55 java.io.IOException -> L57 java.security.GeneralSecurityException -> L59
            com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew r0 = com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew.getInstance(r0)     // Catch: java.lang.Throwable -> L51 java.lang.ClassCastException -> L53 java.lang.IllegalAccessException -> L55 java.io.IOException -> L57 java.security.GeneralSecurityException -> L59
            r5.setSSLSocketFactory(r0)     // Catch: java.lang.Throwable -> L51 java.lang.ClassCastException -> L53 java.lang.IllegalAccessException -> L55 java.io.IOException -> L57 java.security.GeneralSecurityException -> L59
            javax.net.ssl.HostnameVerifier r0 = javax.net.ssl.HttpsURLConnection.getDefaultHostnameVerifier()     // Catch: java.lang.Throwable -> L51 java.lang.ClassCastException -> L53 java.lang.IllegalAccessException -> L55 java.io.IOException -> L57 java.security.GeneralSecurityException -> L59
            r5.setHostnameVerifier(r0)     // Catch: java.lang.Throwable -> L51 java.lang.ClassCastException -> L53 java.lang.IllegalAccessException -> L55 java.io.IOException -> L57 java.security.GeneralSecurityException -> L59
            boolean r6 = e(r5, r6)     // Catch: java.lang.Throwable -> L51 java.lang.ClassCastException -> L53 java.lang.IllegalAccessException -> L55 java.io.IOException -> L57 java.security.GeneralSecurityException -> L59
            if (r5 == 0) goto L50
            r5.disconnect()
        L50:
            return r6
        L51:
            r6 = move-exception
            goto L8c
        L53:
            r6 = move-exception
            goto L5a
        L55:
            r6 = move-exception
            goto L5a
        L57:
            r6 = move-exception
            goto L5a
        L59:
            r6 = move-exception
        L5a:
            r0 = r5
            goto L68
        L5c:
            r5 = move-exception
            r6 = r5
            goto L8b
        L5f:
            r5 = move-exception
        L60:
            r6 = r5
            goto L68
        L62:
            r5 = move-exception
            goto L60
        L64:
            r5 = move-exception
            goto L60
        L66:
            r5 = move-exception
            goto L60
        L68:
            r5 = 4
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L5c
            java.lang.String r3 = "downloadFile GeneralSecurityException|IOException|IllegalAccessException exception = "
            r5[r2] = r3     // Catch: java.lang.Throwable -> L5c
            java.lang.String r3 = r6.getMessage()     // Catch: java.lang.Throwable -> L5c
            r4 = 1
            r5[r4] = r3     // Catch: java.lang.Throwable -> L5c
            java.lang.String r3 = ", cause = "
            r4 = 2
            r5[r4] = r3     // Catch: java.lang.Throwable -> L5c
            java.lang.Throwable r6 = r6.getCause()     // Catch: java.lang.Throwable -> L5c
            r3 = 3
            r5[r3] = r6     // Catch: java.lang.Throwable -> L5c
            com.huawei.hwlogsmodel.LogUtil.b(r1, r5)     // Catch: java.lang.Throwable -> L5c
            if (r0 == 0) goto L8a
            r0.disconnect()
        L8a:
            return r2
        L8b:
            r5 = r0
        L8c:
            if (r5 == 0) goto L91
            r5.disconnect()
        L91:
            throw r6
        L92:
            java.lang.String r5 = "downloadFile url or filePath is empty"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r5)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jbj.b(java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean e(HttpURLConnection httpURLConnection, String str) {
        Throwable th;
        InputStream inputStream;
        IOException e;
        InputStream inputStream2 = null;
        try {
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
        } catch (IOException e2) {
            e = e2;
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
        if (httpURLConnection.getResponseCode() != 200) {
            IoUtils.e((Closeable) null);
            IoUtils.e((Closeable) null);
            return false;
        }
        File file = new File(str);
        a(file, true);
        InputStream inputStream3 = httpURLConnection.getInputStream();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream3.read(bArr);
                    if (read == -1) {
                        fileOutputStream.flush();
                        IoUtils.e(inputStream3);
                        IoUtils.e(fileOutputStream);
                        return true;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
            } catch (IOException e3) {
                e = e3;
                inputStream2 = fileOutputStream;
                InputStream inputStream4 = inputStream2;
                inputStream2 = inputStream3;
                inputStream = inputStream4;
                try {
                    LogUtil.b("AGR_HttpUtils", "download IOException", e.getMessage());
                    IoUtils.e(inputStream2);
                    IoUtils.e(inputStream);
                    return false;
                } catch (Throwable th3) {
                    th = th3;
                    IoUtils.e(inputStream2);
                    IoUtils.e(inputStream);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                inputStream2 = fileOutputStream;
                InputStream inputStream5 = inputStream2;
                inputStream2 = inputStream3;
                inputStream = inputStream5;
                IoUtils.e(inputStream2);
                IoUtils.e(inputStream);
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
        } catch (Throwable th5) {
            th = th5;
        }
    }

    private static void a(File file, boolean z) throws IOException {
        if (z ? file.exists() : file.exists() & file.isFile()) {
            LogUtil.a("AGR_HttpUtils", "initializeDestFile isDeleteFile = ", Boolean.valueOf(file.delete()));
        }
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            LogUtil.a("AGR_HttpUtils", "initializeDestFile isMkdirFile = ", Boolean.valueOf(file.getParentFile().mkdirs()));
        }
        if (file.isFile()) {
            return;
        }
        LogUtil.a("AGR_HttpUtils", "initializeDestFile isCreateNewFile = ", Boolean.valueOf(file.createNewFile()));
    }

    private static TrustManager[] a() {
        return new TrustManager[]{new X509TrustManager() { // from class: jbj.1
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                LogUtil.a("AGR_HttpUtils", "checkClientTrusted enter ");
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                if (x509CertificateArr == null || x509CertificateArr.length <= 0) {
                    LogUtil.b("AGR_HttpUtils", "check server trusted error");
                } else {
                    try {
                        x509CertificateArr[0].checkValidity();
                    } catch (CertificateException unused) {
                        throw new CertificateException("Certificate not valid or trusted.");
                    }
                }
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};
    }

    public static final HostnameVerifier d() {
        return new HostnameVerifier() { // from class: jbj.5
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str, SSLSession sSLSession) {
                return !TextUtils.isEmpty(str);
            }
        };
    }

    public static void b(HttpURLConnection httpURLConnection, HashMap<String, String> hashMap) {
        if (httpURLConnection == null || hashMap == null || hashMap.size() <= 0 || (r5 = hashMap.entrySet().iterator()) == null) {
            return;
        }
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key != null && value != null) {
                httpURLConnection.setRequestProperty(String.valueOf(key), String.valueOf(value));
                LogUtil.c("AGR_HttpUtils", "setHeader-->key:" + String.valueOf(key) + ",value:" + String.valueOf(value));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.io.ByteArrayOutputStream] */
    public static byte[] d(InputStream inputStream) throws Exception {
        ?? r5;
        byte[] bArr = new byte[0];
        byte[] bArr2 = null;
        ?? r4 = 0;
        try {
            try {
                r5 = new ByteArrayOutputStream();
            } catch (Throwable th) {
                th = th;
                r5 = bArr2;
            }
        } catch (IOException unused) {
        }
        try {
            byte[] bArr3 = new byte[1024];
            if (inputStream != null) {
                int read = inputStream.read(bArr3);
                while (read != -1) {
                    r5.write(bArr3, 0, read);
                    read = inputStream.read(bArr3);
                }
                r5.flush();
            }
            bArr = r5.toByteArray();
            try {
                r5.close();
                bArr2 = bArr3;
            } catch (IOException unused2) {
                LogUtil.b("AGR_HttpUtils", "IOException byteArrayOutputStream close error");
                bArr2 = bArr3;
            }
        } catch (IOException unused3) {
            r4 = r5;
            LogUtil.b("AGR_HttpUtils", "IOException error");
            bArr2 = r4;
            if (r4 != 0) {
                try {
                    r4.close();
                    bArr2 = r4;
                } catch (IOException unused4) {
                    LogUtil.b("AGR_HttpUtils", "IOException byteArrayOutputStream close error");
                    bArr2 = r4;
                }
            }
            return bArr;
        } catch (Throwable th2) {
            th = th2;
            if (r5 != 0) {
                try {
                    r5.close();
                } catch (IOException unused5) {
                    LogUtil.b("AGR_HttpUtils", "IOException byteArrayOutputStream close error");
                }
            }
            throw th;
        }
        return bArr;
    }

    public static void a(String str, JSONObject jSONObject, ResultCallback<String> resultCallback) {
        if (TextUtils.isEmpty(str) || jSONObject == null || resultCallback == null) {
            LogUtil.h("AGR_HttpUtils", "post() url is null or object or callback is null");
        } else {
            lqi.d().b(str, e(), jSONObject.toString(), String.class, resultCallback);
        }
    }

    public static Response d(String str, JSONObject jSONObject) throws IOException {
        if (TextUtils.isEmpty(str) || jSONObject == null) {
            LogUtil.h("AGR_HttpUtils", "post() url or object is null");
            return new Response.Builder().build();
        }
        return (Response) lqi.d().d(str, e(), jSONObject.toString(), Response.class);
    }

    private static Map<String, String> e() {
        HashMap hashMap = new HashMap();
        if (!LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            hashMap.put("x-huid", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        }
        hashMap.put("x-version", CommonUtil.c(BaseApplication.getContext()));
        hashMap.put("Content-Type", "application/json; charset=utf-8");
        return hashMap;
    }
}
