package defpackage;

import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes5.dex */
public class lol {
    private static lol c;

    public static lol c() {
        if (c == null) {
            c = new lol();
        }
        return c;
    }

    public String b(String str, String str2, String str3, String str4, String str5) throws KeyManagementException, NoSuchAlgorithmException {
        try {
            try {
                URL url = new URL(str);
                if (loq.b.booleanValue()) {
                    loq.e("HttpConnectionUtils", "doPostRequest->url=%s, urlParameters=%s", url, str2);
                }
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection());
                httpsURLConnection.setConnectTimeout(30000);
                httpsURLConnection.setReadTimeout(30000);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpsURLConnection.setRequestProperty("Content-Length", String.valueOf(str2.getBytes("utf-8").length));
                httpsURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                httpsURLConnection.setRequestProperty("ES-Version", "1.0.7");
                httpsURLConnection.setRequestProperty("User-Agent", "3gpp-gba");
                httpsURLConnection.setRequestProperty("X-3GPP-Intended-IDENTITY", str5);
                if (!TextUtils.isEmpty(str4)) {
                    httpsURLConnection.setRequestProperty("Authorization", str4);
                }
                if (str3 != null) {
                    loq.c("HttpConnectionUtils", "cookie is not null");
                    httpsURLConnection.setRequestProperty("Cookie", str3);
                }
                if (loe.e() != null) {
                    String str6 = loe.e().get("appid");
                    String str7 = loe.e().get("appkey");
                    httpsURLConnection.setRequestProperty("ES-APP-ID", str6);
                    httpsURLConnection.setRequestProperty("ES-APP-Key", str7);
                }
                httpsURLConnection.setRequestProperty("ES-APP-ID", "00104605");
                httpsURLConnection.setRequestProperty("ES-APP-Key", "0C22D5E56878F9D3");
                httpsURLConnection.connect();
                OutputStream outputStream = httpsURLConnection.getOutputStream();
                outputStream.write(str2.getBytes("utf-8"));
                outputStream.flush();
                int responseCode = httpsURLConnection.getResponseCode();
                if (200 != responseCode) {
                    loq.c("HttpConnectionUtils", "doPostRequest->error responseCode= " + responseCode);
                    e(outputStream);
                    return String.valueOf(responseCode);
                }
                String a2 = a(httpsURLConnection);
                if (loq.b.booleanValue()) {
                    loq.c("HttpConnectionUtils", "response: " + a2);
                }
                lop.b(httpsURLConnection, str2);
                httpsURLConnection.disconnect();
                loq.c("HttpConnectionUtils", "doPostRequest->success.");
                e(outputStream);
                return a2;
            } catch (IOException unused) {
                loq.b("HttpConnectionUtils", "doPostRequest->IOException");
                e(null);
                return String.valueOf(98);
            } catch (RuntimeException unused2) {
                loq.b("HttpConnectionUtils", "doPostRequest->RuntimeException");
                e(null);
                return String.valueOf(99);
            }
        } catch (Throwable th) {
            e(null);
            throw th;
        }
    }

    private static String a(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            inputStream = httpURLConnection.getInputStream();
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        String byteArrayOutputStream2 = byteArrayOutputStream.toString("utf-8");
                        e(inputStream);
                        e(byteArrayOutputStream);
                        return byteArrayOutputStream2;
                    }
                }
            } catch (Throwable th) {
                th = th;
                e(inputStream);
                e(byteArrayOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
    }

    private static void e(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                loq.g("HttpConnectionUtils", "closeStream->close error");
            }
        }
    }
}
