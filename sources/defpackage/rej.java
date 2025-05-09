package defpackage;

import androidx.webkit.ProxyConfig;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew;
import com.huawei.secure.android.common.ssl.hostname.StrictHostnameVerifier;
import com.huawei.ui.main.stories.lightcloud.service.LightCloudHttpCallBack;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes7.dex */
public class rej {
    private static volatile ExecutorService b;

    public static void d(final String str, final String str2, final LightCloudHttpCallBack lightCloudHttpCallBack) {
        LogUtil.a("UIDV_HttpUtil", "pullRefresh");
        if (str == null || "".equals(str)) {
            LogUtil.h("UIDV_HttpUtil", "url is null");
            return;
        }
        if (b == null || b.isShutdown()) {
            LogUtil.a("UIDV_HttpUtil", "new executorService");
            b = Executors.newSingleThreadExecutor();
        }
        b.execute(new Runnable() { // from class: rej.5
            @Override // java.lang.Runnable
            public void run() {
                rej.e(str, str2, lightCloudHttpCallBack);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String str, String str2, LightCloudHttpCallBack lightCloudHttpCallBack) {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        OutputStream outputStream;
        OutputStreamWriter outputStreamWriter;
        PrintWriter printWriter;
        int i;
        String str3 = "";
        LogUtil.a("UIDV_HttpUtil", "doPostReq");
        InputStreamReader inputStreamReader = null;
        int i2 = -1;
        try {
            try {
                LogUtil.c("UIDV_HttpUtil", "param = ", str2);
                httpURLConnection = e(str);
            } catch (Throwable th) {
                th = th;
                i2 = i;
                inputStream = inputStream;
                inputStreamReader = inputStreamReader;
            }
        } catch (IOException e) {
            e = e;
            httpURLConnection = null;
            i = -1;
            outputStream = null;
            outputStreamWriter = null;
            printWriter = null;
            inputStream = null;
            inputStreamReader = null;
            ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
            rei.d(httpURLConnection);
            rei.c(outputStreamWriter);
            rei.c(printWriter);
            rei.b(inputStreamReader);
            rei.a(inputStream);
            rei.b(outputStream);
            lightCloudHttpCallBack.onResponce(-1, str3);
        } catch (NullPointerException e2) {
            e = e2;
            httpURLConnection = null;
            i = -1;
            outputStream = null;
            outputStreamWriter = null;
            printWriter = null;
            inputStream = null;
            inputStreamReader = null;
            ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
            rei.d(httpURLConnection);
            rei.c(outputStreamWriter);
            rei.c(printWriter);
            rei.b(inputStreamReader);
            rei.a(inputStream);
            rei.b(outputStream);
            lightCloudHttpCallBack.onResponce(-1, str3);
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection = null;
        }
        try {
        } catch (IOException e3) {
            e = e3;
            outputStream = null;
            outputStreamWriter = null;
            printWriter = null;
            inputStream = null;
            i = -1;
            ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
            rei.d(httpURLConnection);
            rei.c(outputStreamWriter);
            rei.c(printWriter);
            rei.b(inputStreamReader);
            rei.a(inputStream);
            rei.b(outputStream);
            lightCloudHttpCallBack.onResponce(-1, str3);
        } catch (NullPointerException e4) {
            e = e4;
            outputStream = null;
            outputStreamWriter = null;
            printWriter = null;
            inputStream = null;
            i = -1;
            ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
            rei.d(httpURLConnection);
            rei.c(outputStreamWriter);
            rei.c(printWriter);
            rei.b(inputStreamReader);
            rei.a(inputStream);
            rei.b(outputStream);
            lightCloudHttpCallBack.onResponce(-1, str3);
        } catch (Throwable th3) {
            th = th3;
            outputStream = null;
        }
        if (httpURLConnection == null) {
            LogUtil.h("UIDV_HttpUtil", "urlConnection is null");
            rei.d(httpURLConnection);
            rei.c((OutputStreamWriter) null);
            rei.c((PrintWriter) null);
            rei.b((InputStreamReader) null);
            rei.a((InputStream) null);
            rei.b((OutputStream) null);
            lightCloudHttpCallBack.onResponce(-1, "");
            return;
        }
        if (str2 == null || "".equals(str2.trim())) {
            try {
                LogUtil.h("UIDV_HttpUtil", "parm is null");
                outputStream = null;
                outputStreamWriter = null;
                printWriter = null;
            } catch (IOException e5) {
                e = e5;
                i = -1;
                outputStream = null;
                outputStreamWriter = null;
                printWriter = null;
                inputStream = null;
                inputStreamReader = null;
                ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
                rei.d(httpURLConnection);
                rei.c(outputStreamWriter);
                rei.c(printWriter);
                rei.b(inputStreamReader);
                rei.a(inputStream);
                rei.b(outputStream);
                lightCloudHttpCallBack.onResponce(-1, str3);
            } catch (NullPointerException e6) {
                e = e6;
                i = -1;
                outputStream = null;
                outputStreamWriter = null;
                printWriter = null;
                inputStream = null;
                inputStreamReader = null;
                ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
                rei.d(httpURLConnection);
                rei.c(outputStreamWriter);
                rei.c(printWriter);
                rei.b(inputStreamReader);
                rei.a(inputStream);
                rei.b(outputStream);
                lightCloudHttpCallBack.onResponce(-1, str3);
            } catch (Throwable th4) {
                th = th4;
                outputStream = null;
                outputStreamWriter = null;
                printWriter = null;
                inputStream = null;
                inputStreamReader = null;
                rei.d(httpURLConnection);
                rei.c(outputStreamWriter);
                rei.c(printWriter);
                rei.b(inputStreamReader);
                rei.a(inputStream);
                rei.b(outputStream);
                lightCloudHttpCallBack.onResponce(i2, str3);
                throw th;
            }
        } else {
            outputStream = httpURLConnection.getOutputStream();
            try {
                outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            } catch (IOException e7) {
                e = e7;
                outputStreamWriter = null;
                printWriter = null;
                inputStream = null;
                i = -1;
                ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
                rei.d(httpURLConnection);
                rei.c(outputStreamWriter);
                rei.c(printWriter);
                rei.b(inputStreamReader);
                rei.a(inputStream);
                rei.b(outputStream);
                lightCloudHttpCallBack.onResponce(-1, str3);
            } catch (NullPointerException e8) {
                e = e8;
                outputStreamWriter = null;
                printWriter = null;
                inputStream = null;
                i = -1;
                ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
                rei.d(httpURLConnection);
                rei.c(outputStreamWriter);
                rei.c(printWriter);
                rei.b(inputStreamReader);
                rei.a(inputStream);
                rei.b(outputStream);
                lightCloudHttpCallBack.onResponce(-1, str3);
            } catch (Throwable th5) {
                th = th5;
                outputStreamWriter = null;
                printWriter = null;
                inputStream = null;
                rei.d(httpURLConnection);
                rei.c(outputStreamWriter);
                rei.c(printWriter);
                rei.b(inputStreamReader);
                rei.a(inputStream);
                rei.b(outputStream);
                lightCloudHttpCallBack.onResponce(i2, str3);
                throw th;
            }
            try {
                printWriter = new PrintWriter(outputStreamWriter);
                try {
                    printWriter.print(str2);
                    printWriter.flush();
                } catch (IOException e9) {
                    e = e9;
                    inputStream = null;
                    i = -1;
                    ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
                    rei.d(httpURLConnection);
                    rei.c(outputStreamWriter);
                    rei.c(printWriter);
                    rei.b(inputStreamReader);
                    rei.a(inputStream);
                    rei.b(outputStream);
                    lightCloudHttpCallBack.onResponce(-1, str3);
                } catch (NullPointerException e10) {
                    e = e10;
                    inputStream = null;
                    i = -1;
                    ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
                    rei.d(httpURLConnection);
                    rei.c(outputStreamWriter);
                    rei.c(printWriter);
                    rei.b(inputStreamReader);
                    rei.a(inputStream);
                    rei.b(outputStream);
                    lightCloudHttpCallBack.onResponce(-1, str3);
                } catch (Throwable th6) {
                    th = th6;
                    inputStream = null;
                    rei.d(httpURLConnection);
                    rei.c(outputStreamWriter);
                    rei.c(printWriter);
                    rei.b(inputStreamReader);
                    rei.a(inputStream);
                    rei.b(outputStream);
                    lightCloudHttpCallBack.onResponce(i2, str3);
                    throw th;
                }
            } catch (IOException e11) {
                e = e11;
                printWriter = null;
                inputStream = null;
                i = -1;
                ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
                rei.d(httpURLConnection);
                rei.c(outputStreamWriter);
                rei.c(printWriter);
                rei.b(inputStreamReader);
                rei.a(inputStream);
                rei.b(outputStream);
                lightCloudHttpCallBack.onResponce(-1, str3);
            } catch (NullPointerException e12) {
                e = e12;
                printWriter = null;
                inputStream = null;
                i = -1;
                ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
                rei.d(httpURLConnection);
                rei.c(outputStreamWriter);
                rei.c(printWriter);
                rei.b(inputStreamReader);
                rei.a(inputStream);
                rei.b(outputStream);
                lightCloudHttpCallBack.onResponce(-1, str3);
            } catch (Throwable th7) {
                th = th7;
                printWriter = null;
                inputStream = null;
                rei.d(httpURLConnection);
                rei.c(outputStreamWriter);
                rei.c(printWriter);
                rei.b(inputStreamReader);
                rei.a(inputStream);
                rei.b(outputStream);
                lightCloudHttpCallBack.onResponce(i2, str3);
                throw th;
            }
        }
        try {
            httpURLConnection.connect();
            i = httpURLConnection.getResponseCode();
            try {
                inputStream = httpURLConnection.getInputStream();
            } catch (IOException e13) {
                e = e13;
                inputStream = null;
                inputStreamReader = null;
                ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
                rei.d(httpURLConnection);
                rei.c(outputStreamWriter);
                rei.c(printWriter);
                rei.b(inputStreamReader);
                rei.a(inputStream);
                rei.b(outputStream);
                lightCloudHttpCallBack.onResponce(-1, str3);
            } catch (NullPointerException e14) {
                e = e14;
                inputStream = null;
                inputStreamReader = null;
                ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
                rei.d(httpURLConnection);
                rei.c(outputStreamWriter);
                rei.c(printWriter);
                rei.b(inputStreamReader);
                rei.a(inputStream);
                rei.b(outputStream);
                lightCloudHttpCallBack.onResponce(-1, str3);
            } catch (Throwable th8) {
                th = th8;
                i2 = i;
                inputStream = null;
                inputStreamReader = null;
                rei.d(httpURLConnection);
                rei.c(outputStreamWriter);
                rei.c(printWriter);
                rei.b(inputStreamReader);
                rei.a(inputStream);
                rei.b(outputStream);
                lightCloudHttpCallBack.onResponce(i2, str3);
                throw th;
            }
            try {
                inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                try {
                    str3 = "" + b(inputStreamReader);
                    LogUtil.c("UIDV_HttpUtil", "result = ", str3);
                    rei.d(httpURLConnection);
                    rei.c(outputStreamWriter);
                    rei.c(printWriter);
                    rei.b(inputStreamReader);
                    rei.a(inputStream);
                    rei.b(outputStream);
                    lightCloudHttpCallBack.onResponce(i, str3);
                } catch (IOException e15) {
                    e = e15;
                    ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
                    rei.d(httpURLConnection);
                    rei.c(outputStreamWriter);
                    rei.c(printWriter);
                    rei.b(inputStreamReader);
                    rei.a(inputStream);
                    rei.b(outputStream);
                    lightCloudHttpCallBack.onResponce(-1, str3);
                } catch (NullPointerException e16) {
                    e = e16;
                    ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
                    rei.d(httpURLConnection);
                    rei.c(outputStreamWriter);
                    rei.c(printWriter);
                    rei.b(inputStreamReader);
                    rei.a(inputStream);
                    rei.b(outputStream);
                    lightCloudHttpCallBack.onResponce(-1, str3);
                }
            } catch (IOException e17) {
                e = e17;
                inputStreamReader = null;
                ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
                rei.d(httpURLConnection);
                rei.c(outputStreamWriter);
                rei.c(printWriter);
                rei.b(inputStreamReader);
                rei.a(inputStream);
                rei.b(outputStream);
                lightCloudHttpCallBack.onResponce(-1, str3);
            } catch (NullPointerException e18) {
                e = e18;
                inputStreamReader = null;
                ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
                rei.d(httpURLConnection);
                rei.c(outputStreamWriter);
                rei.c(printWriter);
                rei.b(inputStreamReader);
                rei.a(inputStream);
                rei.b(outputStream);
                lightCloudHttpCallBack.onResponce(-1, str3);
            } catch (Throwable th9) {
                th = th9;
                i2 = i;
                inputStreamReader = null;
                rei.d(httpURLConnection);
                rei.c(outputStreamWriter);
                rei.c(printWriter);
                rei.b(inputStreamReader);
                rei.a(inputStream);
                rei.b(outputStream);
                lightCloudHttpCallBack.onResponce(i2, str3);
                throw th;
            }
        } catch (IOException e19) {
            e = e19;
            i = -1;
            inputStream = null;
            inputStreamReader = null;
            ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
            rei.d(httpURLConnection);
            rei.c(outputStreamWriter);
            rei.c(printWriter);
            rei.b(inputStreamReader);
            rei.a(inputStream);
            rei.b(outputStream);
            lightCloudHttpCallBack.onResponce(-1, str3);
        } catch (NullPointerException e20) {
            e = e20;
            i = -1;
            inputStream = null;
            inputStreamReader = null;
            ReleaseLogUtil.c("UIDV_HttpUtil", "doPostReq ", e.getClass().getSimpleName(), ": ", LogAnonymous.b(e));
            rei.d(httpURLConnection);
            rei.c(outputStreamWriter);
            rei.c(printWriter);
            rei.b(inputStreamReader);
            rei.a(inputStream);
            rei.b(outputStream);
            lightCloudHttpCallBack.onResponce(-1, str3);
        } catch (Throwable th10) {
            th = th10;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v18 */
    private static HttpURLConnection e(String str) {
        CertificateException e;
        HttpsURLConnection httpsURLConnection;
        NoSuchAlgorithmException e2;
        KeyStoreException e3;
        KeyManagementException e4;
        IllegalAccessException e5;
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2;
        HttpURLConnection httpURLConnection3 = null;
        try {
            URL url = new URL(str);
            if (ProxyConfig.MATCH_HTTPS.equals(url.getProtocol().toLowerCase(Locale.ENGLISH))) {
                LogUtil.a("UIDV_HttpUtil", ProxyConfig.MATCH_HTTPS);
                httpsURLConnection = (HttpsURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection());
                try {
                    HttpsURLConnection httpsURLConnection2 = httpsURLConnection;
                    httpsURLConnection.setSSLSocketFactory(SecureSSLSocketFactoryNew.getInstance(BaseApplication.getContext()));
                    HttpsURLConnection httpsURLConnection3 = httpsURLConnection;
                    httpsURLConnection.setHostnameVerifier(new StrictHostnameVerifier());
                    httpURLConnection2 = httpsURLConnection;
                } catch (IOException unused) {
                    LogUtil.b("UIDV_HttpUtil", "IOException");
                    httpURLConnection = httpsURLConnection;
                    return httpURLConnection;
                } catch (IllegalAccessException e6) {
                    e5 = e6;
                    LogUtil.b("UIDV_HttpUtil", "IllegalAccessException:", e5.getMessage());
                    httpURLConnection = httpsURLConnection;
                    return httpURLConnection;
                } catch (KeyManagementException e7) {
                    e4 = e7;
                    LogUtil.b("UIDV_HttpUtil", "KeyManagementException:", e4.getMessage());
                    httpURLConnection = httpsURLConnection;
                    return httpURLConnection;
                } catch (KeyStoreException e8) {
                    e3 = e8;
                    LogUtil.b("UIDV_HttpUtil", "KeyStoreException:", e3.getMessage());
                    httpURLConnection = httpsURLConnection;
                    return httpURLConnection;
                } catch (NoSuchAlgorithmException e9) {
                    e2 = e9;
                    LogUtil.b("UIDV_HttpUtil", "NoSuchAlgorithmException:", e2.getMessage());
                    httpURLConnection = httpsURLConnection;
                    return httpURLConnection;
                } catch (CertificateException e10) {
                    e = e10;
                    LogUtil.b("UIDV_HttpUtil", "CertificateException:", e.getMessage());
                    httpURLConnection = httpsURLConnection;
                    return httpURLConnection;
                }
            } else {
                httpURLConnection2 = (HttpURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection());
            }
            httpURLConnection3 = httpURLConnection2;
            httpURLConnection3.setRequestMethod("POST");
            httpURLConnection3.setRequestProperty("Content-Type", "application/json");
            httpURLConnection3.setUseCaches(false);
            httpURLConnection3.setDoInput(true);
            httpURLConnection3.setDoOutput(true);
            httpURLConnection3.setReadTimeout(10000);
            httpURLConnection3.setConnectTimeout(10000);
            return httpURLConnection3;
        } catch (IOException unused2) {
            httpsURLConnection = httpURLConnection3;
        } catch (IllegalAccessException e11) {
            e5 = e11;
            httpsURLConnection = httpURLConnection3;
        } catch (KeyManagementException e12) {
            e4 = e12;
            httpsURLConnection = httpURLConnection3;
        } catch (KeyStoreException e13) {
            e3 = e13;
            httpsURLConnection = httpURLConnection3;
        } catch (NoSuchAlgorithmException e14) {
            e2 = e14;
            httpsURLConnection = httpURLConnection3;
        } catch (CertificateException e15) {
            e = e15;
            httpsURLConnection = httpURLConnection3;
        }
    }

    private static String b(InputStreamReader inputStreamReader) {
        char[] cArr = new char[1024];
        StringBuilder sb = new StringBuilder();
        int i = 0;
        do {
            try {
                int read = inputStreamReader.read(cArr, 0, 1024);
                if (read == -1) {
                    break;
                }
                sb.append(cArr, 0, read);
                i += read;
            } catch (IOException e) {
                LogUtil.a("UIDV_HttpUtil", "ResourceManagerHelper postRequest result e:", e.getMessage());
            }
        } while (i <= 31459280);
        return sb.toString();
    }
}
