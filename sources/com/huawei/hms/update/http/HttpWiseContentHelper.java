package com.huawei.hms.update.http;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew;
import com.huawei.secure.android.common.ssl.hostname.StrictHostnameVerifier;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes9.dex */
public class HttpWiseContentHelper {
    private static String a(Context context, String str) throws IOException {
        HttpsURLConnection b = b(context, str);
        if (b == null) {
            HMSLog.e("HttpWiseContentHelper", "<internalGetContent> connection is null");
            return "";
        }
        b.setRequestMethod("GET");
        int responseCode = b.getResponseCode();
        if (responseCode == 206 || responseCode == 200) {
            InputStream inputStream = b.getInputStream();
            byte[] a2 = a(inputStream);
            inputStream.close();
            return new String(a2, "UTF-8");
        }
        HMSLog.e("HttpWiseContentHelper", "<internalGetContent> error statusCode: " + responseCode);
        return "";
    }

    private static HttpsURLConnection b(Context context, String str) throws IOException {
        URLConnection openConnection = new URL(str).openConnection();
        if (openConnection == null) {
            HMSLog.i("HttpWiseContentHelper", "urlConnection is null");
            return null;
        }
        if (!(openConnection instanceof HttpsURLConnection)) {
            HMSLog.i("HttpWiseContentHelper", "current request is http not allow connection");
            return null;
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection;
        try {
            SecureSSLSocketFactoryNew secureSSLSocketFactoryNew = SecureSSLSocketFactoryNew.getInstance(context, EncryptUtil.genSecureRandom());
            if (secureSSLSocketFactoryNew != null) {
                httpsURLConnection.setSSLSocketFactory(secureSSLSocketFactoryNew);
            }
            httpsURLConnection.setSSLSocketFactory(secureSSLSocketFactoryNew);
            httpsURLConnection.setHostnameVerifier(new StrictHostnameVerifier());
            httpsURLConnection.setConnectTimeout(30000);
            httpsURLConnection.setReadTimeout(30000);
            return httpsURLConnection;
        } catch (IOException | IllegalAccessException | IllegalArgumentException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            HMSLog.e("HttpWiseContentHelper", "Failed to new TLSSocketFactory instance." + e.getMessage());
            throw new IOException("Failed to create SSLSocketFactory.");
        }
    }

    public static String syncGetContent(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            HMSLog.e("HttpWiseContentHelper", "<syncGetContent> context or url is null.");
            return "";
        }
        try {
            return a(context, str);
        } catch (IOException unused) {
            HMSLog.e("HttpWiseContentHelper", "<syncGetContent> IOException failed ");
            return "";
        } catch (RuntimeException unused2) {
            HMSLog.e("HttpWiseContentHelper", "<syncGetContent> RuntimeException failed");
            return "";
        }
    }

    private static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
