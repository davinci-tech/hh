package com.huawei.multisimsdk.odsa.utils;

import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;
import com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice;
import com.huawei.multisimsdk.multidevicemanager.common.AbsPrimaryDevice;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.lop;
import defpackage.loq;
import defpackage.loy;
import defpackage.lpl;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes5.dex */
public class OdsaHttpConnectionUtils {

    /* renamed from: a, reason: collision with root package name */
    private static OdsaHttpConnectionUtils f6536a;
    private static final Object b = new Object();

    enum RequestType {
        AKA_FIRST,
        AKA_SECOND,
        ODSA
    }

    public static OdsaHttpConnectionUtils c() {
        OdsaHttpConnectionUtils odsaHttpConnectionUtils;
        synchronized (b) {
            if (f6536a == null) {
                f6536a = new OdsaHttpConnectionUtils();
            }
            odsaHttpConnectionUtils = f6536a;
        }
        return odsaHttpConnectionUtils;
    }

    public String d(int i, AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Map<String, String> map) throws Exception {
        HttpsURLConnection httpsURLConnection = null;
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        if (!lop.c(loy.e().b())) {
            loq.c("OdsaHttpConnectionUtils", "no Network!");
            return String.valueOf(98);
        }
        try {
            URL d = lpl.d(lop.a("ODSA_ESURL", ""), absPrimaryDevice, absPairedDevice, i, map);
            if (d == null) {
                loq.b("OdsaHttpConnectionUtils", "doGetRequest url is null");
                loq.c("OdsaHttpConnectionUtils", "doGetRequest->close connection safely.");
                return String.valueOf(-1004);
            }
            HttpsURLConnection e = e(d);
            if (c("GET", e, false, "")) {
                String b2 = b(e, RequestType.ODSA, "");
                loq.c("OdsaHttpConnectionUtils", "doGetRequest->close connection safely.");
                if (e != null) {
                    e.disconnect();
                }
                return b2;
            }
            loq.g("OdsaHttpConnectionUtils", "setUrlConnectionParams is false");
            loq.c("OdsaHttpConnectionUtils", "doGetRequest->close connection safely.");
            if (e != null) {
                e.disconnect();
            }
            return String.valueOf(-1008);
        } catch (Throwable th) {
            loq.c("OdsaHttpConnectionUtils", "doGetRequest->close connection safely.");
            if (0 != 0) {
                httpsURLConnection.disconnect();
            }
            throw th;
        }
    }

    public String b(int i, AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Map<String, String> map) throws Exception {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        if (!lop.c(loy.e().b())) {
            loq.c("OdsaHttpConnectionUtils", "no Network!");
            return String.valueOf(98);
        }
        try {
            URL url = new URL(lop.a("ODSA_ESURL", ""));
            String a2 = lpl.a(absPrimaryDevice, absPairedDevice, i, map);
            HttpsURLConnection e = e(url);
            if (!c("POST", e, false, "")) {
                loq.g("OdsaHttpConnectionUtils", "setUrlConnectionParams is false");
                return String.valueOf(-1008);
            }
            if (d(e, a2)) {
                return b(e, RequestType.ODSA, "");
            }
            return String.valueOf(-1009);
        } catch (MalformedURLException unused) {
            loq.b("OdsaHttpConnectionUtils", "doPostRequest error");
            return String.valueOf(99);
        }
    }

    private static String c(HttpsURLConnection httpsURLConnection) {
        try {
            InputStream inputStream = httpsURLConnection.getInputStream();
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    String byteArrayOutputStream2 = byteArrayOutputStream.toString("utf-8");
                    byteArrayOutputStream.close();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return byteArrayOutputStream2;
                } finally {
                }
            } finally {
            }
        } catch (IOException unused) {
            loq.b("OdsaHttpConnectionUtils", "IOException");
            return "";
        }
    }

    private static String b(HttpsURLConnection httpsURLConnection, RequestType requestType, String str) {
        String c;
        if (httpsURLConnection == null) {
            loq.b("OdsaHttpConnectionUtils", "getResponse connection == null");
            return "";
        }
        try {
            int responseCode = httpsURLConnection.getResponseCode();
            int i = AnonymousClass1.c[requestType.ordinal()];
            if (i == 1 || i == 2) {
                if (responseCode != 200) {
                    loq.c("OdsaHttpConnectionUtils", "getResponse->error responseCode= " + responseCode);
                    return String.valueOf(responseCode);
                }
            } else {
                if (i != 3) {
                    return "";
                }
                if (responseCode != 200 && responseCode != 302) {
                    loq.b("OdsaHttpConnectionUtils", "getResponse->error responseCode= " + responseCode);
                    return String.valueOf(responseCode);
                }
                String headerField = httpsURLConnection.getHeaderField("Location");
                loq.d("OdsaHttpConnectionUtils", "getResponse->success.responseCode=" + responseCode);
                if (!TextUtils.isEmpty(headerField)) {
                    return headerField;
                }
            }
            if (TextUtils.equals(httpsURLConnection.getHeaderField("Content-Encoding"), Constants.GZIP)) {
                c = lop.c(httpsURLConnection);
            } else {
                c = c(httpsURLConnection);
            }
            if (requestType.equals(RequestType.AKA_FIRST)) {
                String headerField2 = httpsURLConnection.getHeaderField("set-cookie");
                if (TextUtils.isEmpty(headerField2)) {
                    return c;
                }
                lop.d(loy.e().b(), str, "cookie", headerField2);
                return c;
            }
            return c + "&" + httpsURLConnection.getContentType();
        } catch (IOException unused) {
            loq.b("OdsaHttpConnectionUtils", "getResponse error");
            return "";
        }
    }

    /* renamed from: com.huawei.multisimsdk.odsa.utils.OdsaHttpConnectionUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[RequestType.values().length];
            c = iArr;
            try {
                iArr[RequestType.AKA_FIRST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[RequestType.AKA_SECOND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[RequestType.ODSA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                loq.g("OdsaHttpConnectionUtils", "closeStream->close error");
            }
        }
    }

    public static Map<String, String> d(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return Collections.emptyMap();
        }
        int indexOf = str.indexOf(63);
        loq.c("OdsaHttpConnectionUtils", "resolveFromUrl, index = " + indexOf);
        if (indexOf == -1 || indexOf == str.length()) {
            return Collections.emptyMap();
        }
        String substring = str.substring(0, indexOf);
        String substring2 = str.substring(indexOf + 1);
        loq.b("OdsaHttpConnectionUtils", "resolveFromUrl, remaining = ", substring2);
        String[] split = substring2.split("&");
        HashMap hashMap = new HashMap();
        hashMap.put("oidc_url", substring);
        for (String str3 : split) {
            loq.b("OdsaHttpConnectionUtils", "resolveFromUrl, item = ", str3);
            String[] split2 = str3.split("=", 2);
            if (split2 == null || split2.length < 2) {
                loq.b("OdsaHttpConnectionUtils", "resolveFromUrl, string length error");
            } else {
                String str4 = split2[0];
                try {
                    str2 = URLDecoder.decode(split2[1], "UTF-8");
                } catch (UnsupportedEncodingException unused) {
                    loq.b("OdsaHttpConnectionUtils", "resolveFromUrl, UnsupportedEncodingException");
                    str2 = split2[1];
                }
                if (TextUtils.isEmpty(str4) || TextUtils.isEmpty(str2)) {
                    loq.b("OdsaHttpConnectionUtils", "resolveFromUrl, string content error");
                } else {
                    hashMap.put(str4, str2);
                }
            }
        }
        return hashMap;
    }

    public String a(String str, String str2, String str3, String str4) {
        int i;
        HttpsURLConnection e;
        HttpsURLConnection httpsURLConnection = null;
        try {
            try {
                e = e(new URL(str + str2));
            } catch (MalformedURLException unused) {
                loq.b("OdsaHttpConnectionUtils", "doGetRequestForAkaFirstAuth->IOException");
                if (0 != 0) {
                    httpsURLConnection.disconnect();
                }
                i = ExceptionCode.FILE_NO_EXIST;
            }
            if (!c("GET", e, true, str3)) {
                loq.g("OdsaHttpConnectionUtils", "setUrlConnectionParams is false");
                if (e != null) {
                    e.disconnect();
                }
                i = -1008;
                return String.valueOf(i);
            }
            String b2 = b(e, RequestType.AKA_FIRST, str4);
            if (e != null) {
                e.disconnect();
            }
            return b2;
        } catch (Throwable th) {
            if (0 != 0) {
                httpsURLConnection.disconnect();
            }
            throw th;
        }
    }

    public String c(String str, String str2, String str3, String str4) {
        try {
            HttpsURLConnection e = e(new URL(str));
            if (!c("POST", e, true, str3)) {
                loq.g("OdsaHttpConnectionUtils", "setUrlConnectionParams is false");
                return String.valueOf(-1008);
            }
            if (!d(e, str2)) {
                return String.valueOf(-1009);
            }
            return b(e, RequestType.AKA_FIRST, str4);
        } catch (MalformedURLException unused) {
            loq.b("OdsaHttpConnectionUtils", "doPostRequestForAkaFirstAuth error");
            return String.valueOf(99);
        }
    }

    public String b(String str, String str2, String str3) {
        try {
            HttpsURLConnection e = e(new URL(str));
            if (!c("POST", e, true, str3)) {
                loq.g("OdsaHttpConnectionUtils", "setUrlConnectionParams is false");
                return String.valueOf(-1008);
            }
            if (!d(e, str2)) {
                return String.valueOf(-1009);
            }
            return b(e, RequestType.AKA_SECOND, "");
        } catch (MalformedURLException unused) {
            loq.b("OdsaHttpConnectionUtils", "doPostRequestForAkaAuth MalformedURLException");
            return String.valueOf(99);
        }
    }

    private static HttpsURLConnection e(URL url) {
        try {
            URLConnection openConnection = URLConnectionInstrumentation.openConnection(url.openConnection());
            if (openConnection instanceof HttpsURLConnection) {
                return (HttpsURLConnection) openConnection;
            }
        } catch (IOException unused) {
            loq.b("OdsaHttpConnectionUtils", "getHttpsUrlConnection IOException");
        }
        return null;
    }

    private static boolean c(String str, HttpsURLConnection httpsURLConnection, boolean z, String str2) {
        if (httpsURLConnection == null) {
            loq.b("OdsaHttpConnectionUtils", "setUrlConnectionParams connection == null");
            return false;
        }
        try {
            httpsURLConnection.setRequestMethod(str);
            httpsURLConnection.setConnectTimeout(30000);
            httpsURLConnection.setReadTimeout(30000);
            httpsURLConnection.setRequestProperty("Accept-encoding", Constants.GZIP);
            if (str.equals("POST")) {
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpsURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                if (lop.e("ODSA_SUPPORT_GZIP_PARAMETER", (Boolean) false).booleanValue()) {
                    httpsURLConnection.setRequestProperty("Content-Encoding", Constants.GZIP);
                }
            } else {
                httpsURLConnection.setRequestProperty("Connection", "close");
            }
            if (z) {
                httpsURLConnection.setRequestProperty("Accept", "application/vnd.gsma.eap-relay.v1.0+json, text/vnd.wap.connectivity-xml");
            } else {
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setInstanceFollowRedirects(false);
                httpsURLConnection.setRequestProperty("Accept", "application/json, application/xml");
            }
            if (!TextUtils.isEmpty(str2)) {
                httpsURLConnection.setRequestProperty("Cookie", str2);
            }
            httpsURLConnection.connect();
            return true;
        } catch (IOException unused) {
            loq.b("OdsaHttpConnectionUtils", "setRequestMethod failed protocol exception");
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v6 */
    private static boolean d(HttpsURLConnection httpsURLConnection, String str) {
        ?? r7;
        OutputStream outputStream;
        if (httpsURLConnection == null) {
            loq.b("OdsaHttpConnectionUtils", "writeData connection == null");
            return false;
        }
        OutputStreamWriter outputStreamWriter = null;
        try {
            try {
                if (lop.e("ODSA_SUPPORT_GZIP_PARAMETER", (Boolean) false).booleanValue()) {
                    outputStream = httpsURLConnection.getOutputStream();
                    try {
                        outputStream.write(lop.d(str));
                        outputStream.flush();
                    } catch (IOException unused) {
                        loq.b("OdsaHttpConnectionUtils", "writeData error");
                        a(outputStreamWriter);
                        a(outputStream);
                        return false;
                    }
                } else {
                    OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(httpsURLConnection.getOutputStream(), "utf-8");
                    try {
                        outputStreamWriter2.write(str);
                        outputStreamWriter2.flush();
                        outputStream = null;
                        outputStreamWriter = outputStreamWriter2;
                    } catch (IOException unused2) {
                        outputStream = null;
                        outputStreamWriter = outputStreamWriter2;
                        loq.b("OdsaHttpConnectionUtils", "writeData error");
                        a(outputStreamWriter);
                        a(outputStream);
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        r7 = 0;
                        outputStreamWriter = outputStreamWriter2;
                        a(outputStreamWriter);
                        a(r7);
                        throw th;
                    }
                }
                a(outputStreamWriter);
                a(outputStream);
                return true;
            } catch (Throwable th2) {
                r7 = httpsURLConnection;
                th = th2;
            }
        } catch (IOException unused3) {
            outputStream = null;
        } catch (Throwable th3) {
            th = th3;
            r7 = 0;
        }
    }
}
