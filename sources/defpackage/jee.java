package defpackage;

import android.content.Context;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.secure.android.common.SecureSSLSocketFactory;
import health.compact.a.IoUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* loaded from: classes5.dex */
public class jee {
    public static String d(String str, String str2, boolean z) {
        if (str2 == null) {
            LogUtil.h("HwCommonHttpsUtil", "doHttpsPost param is null");
            return null;
        }
        return a(str, str2, false, z);
    }

    private static String a(String str, String str2, boolean z, boolean z2) {
        try {
            try {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
                LogUtil.a("HwCommonHttpsUtil", "postRequest url.openConnection!");
                LogUtil.a("HwCommonHttpsUtil", "postRequest openConnection already!");
                if (z2 && c(httpsURLConnection)) {
                    LogUtil.h("HwCommonHttpsUtil", "postRequest setSslSocketFactory fails");
                    return null;
                }
                try {
                    httpsURLConnection.setRequestMethod("POST");
                    LogUtil.a("HwCommonHttpsUtil", "postRequest setRequestMethod(HTTP_POST)!");
                    byte[] a2 = a(str2, z, httpsURLConnection, SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER, z2);
                    if (a2 == null) {
                        LogUtil.h("HwCommonHttpsUtil", "postRequest null data");
                        return null;
                    }
                    return a(a2, httpsURLConnection);
                } catch (ProtocolException e) {
                    LogUtil.b("HwCommonHttpsUtil", "postRequest setRequestMethod failed ", e.getMessage());
                    return null;
                }
            } catch (IOException e2) {
                LogUtil.b("HwCommonHttpsUtil", "postRequest openConnection failed ", e2.getMessage());
                return null;
            }
        } catch (MalformedURLException e3) {
            LogUtil.b("HwCommonHttpsUtil", "postRequest MalformedURLException ", e3.getMessage());
            return null;
        }
    }

    private static String a(byte[] bArr, HttpsURLConnection httpsURLConnection) {
        String str;
        InputStream inputStream = null;
        try {
            try {
                inputStream = d(bArr, httpsURLConnection);
                LogUtil.a("HwCommonHttpsUtil", "getResponseLine getHttpsResponsesStream!");
                str = jeb.b(inputStream);
            } catch (Exception unused) {
                LogUtil.b("HwCommonHttpsUtil", "getResponseLine getHttpsResponsesStream Exception");
                LogUtil.a("HwCommonHttpsUtil", "getResponseLine close response");
                IoUtils.e(inputStream);
                str = "";
            }
            return str;
        } finally {
            LogUtil.a("HwCommonHttpsUtil", "getResponseLine close response");
            IoUtils.e(inputStream);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static byte[] a(java.lang.String r2, boolean r3, javax.net.ssl.HttpsURLConnection r4, javax.net.ssl.HostnameVerifier r5, boolean r6) {
        /*
            java.lang.String r0 = "HwCommonHttpsUtil"
            if (r2 == 0) goto L19
            java.lang.String r1 = "UTF-8"
            byte[] r2 = r2.getBytes(r1)     // Catch: java.io.UnsupportedEncodingException -> Lb
            goto L1a
        Lb:
            r2 = move-exception
            java.lang.String r1 = "getData UnsupportedEncodingException "
            java.lang.String r2 = r2.getMessage()
            java.lang.Object[] r2 = new java.lang.Object[]{r1, r2}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)
        L19:
            r2 = 0
        L1a:
            if (r6 == 0) goto L1f
            r4.setHostnameVerifier(r5)
        L1f:
            defpackage.jeb.e(r4, r2)
            java.lang.String r5 = "getData set end"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r5)
            if (r3 != 0) goto L34
            java.lang.String r3 = "Accept-Encoding"
            java.lang.String r5 = "identity"
            r4.setRequestProperty(r3, r5)
        L34:
            java.lang.String r3 = "getData setRequestProperty jsonObject already!"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jee.a(java.lang.String, boolean, javax.net.ssl.HttpsURLConnection, javax.net.ssl.HostnameVerifier, boolean):byte[]");
    }

    private static boolean c(HttpsURLConnection httpsURLConnection) {
        try {
            httpsURLConnection.setSSLSocketFactory(SecureSSLSocketFactory.getInstance(BaseApplication.getContext()));
            LogUtil.a("HwCommonHttpsUtil", "setSslSocketFactory end!");
            return false;
        } catch (IOException | IllegalAccessException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            LogUtil.b("HwCommonHttpsUtil", "setSslSocketFactory ", e.getClass().getName(), e.getMessage());
            return true;
        }
    }

    /* JADX WARN: Not initialized variable reg: 5, insn: 0x005d: MOVE (r4 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:17:0x005d */
    private static InputStream d(byte[] bArr, HttpsURLConnection httpsURLConnection) {
        OutputStream outputStream;
        OutputStream outputStream2;
        OutputStream outputStream3 = null;
        try {
            try {
                httpsURLConnection.connect();
                LogUtil.a("HwCommonHttpsUtil", "getHttpsResponsesStream connect");
                outputStream = httpsURLConnection.getOutputStream();
                try {
                    LogUtil.a("HwCommonHttpsUtil", "getHttpsResponsesStream getOutput");
                    outputStream.write(bArr);
                    LogUtil.a("HwCommonHttpsUtil", "getHttpsResponsesStream write");
                    LogUtil.a("HwCommonHttpsUtil", "getHttpsResponsesStream finally closeOutputStream!");
                    jeb.a(outputStream);
                    return jeb.e(httpsURLConnection);
                } catch (IOException e) {
                    e = e;
                    LogUtil.b("HwCommonHttpsUtil", "getHttpsResponsesStream IOException ", e.getMessage());
                    LogUtil.a("HwCommonHttpsUtil", "getHttpsResponsesStream finally closeOutputStream!");
                    jeb.a(outputStream);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                outputStream3 = outputStream2;
                LogUtil.a("HwCommonHttpsUtil", "getHttpsResponsesStream finally closeOutputStream!");
                jeb.a(outputStream3);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            outputStream = null;
        } catch (Throwable th2) {
            th = th2;
            LogUtil.a("HwCommonHttpsUtil", "getHttpsResponsesStream finally closeOutputStream!");
            jeb.a(outputStream3);
            throw th;
        }
    }

    public static int a(String str, OutputStream outputStream) {
        Context context = BaseApplication.getContext();
        URL e = jeb.e(str);
        if (e == null) {
            LogUtil.h("HwCommonHttpsUtil", "doReportStatus null url");
            return -1;
        }
        try {
            URLConnection openConnection = URLConnectionInstrumentation.openConnection(e.openConnection());
            if (!(openConnection instanceof HttpsURLConnection)) {
                return -1;
            }
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection;
            LogUtil.a("HwCommonHttpsUtil", "doReportStatus url.openConnection!");
            LogUtil.a("HwCommonHttpsUtil", "doReportStatus openConnection already!");
            try {
                httpsURLConnection.setSSLSocketFactory(SecureSSLSocketFactory.getInstance(context));
                try {
                    httpsURLConnection.setRequestMethod("POST");
                    LogUtil.a("HwCommonHttpsUtil", "doReportStatus setRequestMethod(HTTP_POST)!");
                    return a(outputStream, httpsURLConnection);
                } catch (ProtocolException e2) {
                    LogUtil.b("HwCommonHttpsUtil", "doReportStatus setRequestMethod failed ", e2.getMessage());
                    return -1;
                }
            } catch (IOException | IllegalAccessException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException e3) {
                LogUtil.b("HwCommonHttpsUtil", "doReportStatus ", e3.getClass().getName(), e3.getMessage());
                return -1;
            }
        } catch (IOException e4) {
            LogUtil.b("HwCommonHttpsUtil", "doReportStatus openConnection failed ", e4.getMessage());
            return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int a(java.io.OutputStream r3, javax.net.ssl.HttpsURLConnection r4) {
        /*
            java.lang.String r0 = "HwCommonHttpsUtil"
            if (r3 == 0) goto L1e
            java.lang.String r3 = r3.toString()     // Catch: java.io.UnsupportedEncodingException -> Lf
            java.lang.String r1 = "UTF-8"
            byte[] r3 = r3.getBytes(r1)     // Catch: java.io.UnsupportedEncodingException -> Lf
            goto L1f
        Lf:
            r3 = move-exception
            java.lang.String r1 = "sendData UnsupportedEncodingException "
            java.lang.String r3 = r3.getMessage()
            java.lang.Object[] r3 = new java.lang.Object[]{r1, r3}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r3)
        L1e:
            r3 = 0
        L1f:
            r1 = -1
            if (r3 != 0) goto L2d
            java.lang.String r3 = "sendData Get null post data!"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r3)
            return r1
        L2d:
            org.apache.http.conn.ssl.X509HostnameVerifier r2 = org.apache.http.conn.ssl.SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER
            r4.setHostnameVerifier(r2)
            defpackage.jeb.e(r4, r3)
            java.lang.String r2 = "sendData set end"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r2)
            int r1 = e(r3, r4)     // Catch: java.lang.Exception -> L50
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L50
            java.lang.String r4 = "sendData getHttpsReportCode"
            r2 = 0
            r3[r2] = r4     // Catch: java.lang.Exception -> L50
            com.huawei.hwlogsmodel.LogUtil.a(r0, r3)     // Catch: java.lang.Exception -> L50
            goto L5a
        L50:
            java.lang.String r3 = "sendData getHttpsReportCode Exception"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r3)
        L5a:
            java.lang.String r3 = "sendData StatusCode is "
            java.lang.Integer r4 = java.lang.Integer.valueOf(r1)
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r4}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jee.a(java.io.OutputStream, javax.net.ssl.HttpsURLConnection):int");
    }

    private static int e(byte[] bArr, HttpsURLConnection httpsURLConnection) {
        OutputStream outputStream = null;
        int i = -1;
        try {
            try {
                httpsURLConnection.connect();
                LogUtil.a("HwCommonHttpsUtil", "getHttpsReportCode connect");
                outputStream = httpsURLConnection.getOutputStream();
                LogUtil.a("HwCommonHttpsUtil", "getHttpsReportCode getOutput");
                outputStream.write(bArr);
                LogUtil.a("HwCommonHttpsUtil", "getHttpsReportCode write");
                httpsURLConnection.getInputStream();
                i = httpsURLConnection.getResponseCode();
                LogUtil.a("HwCommonHttpsUtil", "getHttpsReportCode StatusCode: ", Integer.valueOf(i));
                LogUtil.a("HwCommonHttpsUtil", "getHttpsReportCode finally closeOutputStream!");
            } catch (IOException e) {
                LogUtil.b("HwCommonHttpsUtil", e.getMessage(), e.getMessage());
                LogUtil.a("HwCommonHttpsUtil", "getHttpsReportCode finally closeOutputStream!");
            }
            jeb.a(outputStream);
            return i;
        } catch (Throwable th) {
            LogUtil.a("HwCommonHttpsUtil", "getHttpsReportCode finally closeOutputStream!");
            jeb.a(outputStream);
            throw th;
        }
    }
}
