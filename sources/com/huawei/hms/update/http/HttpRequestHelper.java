package com.huawei.hms.update.http;

import android.content.Context;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.IOUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew;
import com.huawei.secure.android.common.ssl.hostname.StrictHostnameVerifier;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes9.dex */
public class HttpRequestHelper implements IHttpRequestHelper {

    /* renamed from: a, reason: collision with root package name */
    private HttpsURLConnection f6049a;
    private volatile int b = -1;

    private void a(String str, Context context) throws IOException {
        if (this.b == 0) {
            HMSLog.e("HttpRequestHelper", "Not allowed to repeat open http(s) connection.");
        }
        URLConnection openConnection = new URL(str).openConnection();
        if (openConnection == null) {
            HMSLog.i("HttpRequestHelper", "urlConnection is null");
            return;
        }
        if (!(openConnection instanceof HttpsURLConnection)) {
            HMSLog.i("HttpRequestHelper", "current request is http not allow connection");
            this.f6049a = null;
            return;
        }
        this.f6049a = (HttpsURLConnection) openConnection;
        try {
            SecureSSLSocketFactoryNew secureSSLSocketFactoryNew = SecureSSLSocketFactoryNew.getInstance(context, EncryptUtil.genSecureRandom());
            if (secureSSLSocketFactoryNew != null) {
                this.f6049a.setSSLSocketFactory(secureSSLSocketFactoryNew);
            }
            this.f6049a.setSSLSocketFactory(secureSSLSocketFactoryNew);
            this.f6049a.setHostnameVerifier(new StrictHostnameVerifier());
            this.f6049a.setConnectTimeout(30000);
            this.f6049a.setReadTimeout(30000);
            this.f6049a.setDoInput(true);
            this.f6049a.setDoOutput(true);
            this.f6049a.setUseCaches(false);
            this.f6049a.setInstanceFollowRedirects(true);
            this.b = 0;
        } catch (IOException | IllegalAccessException | IllegalArgumentException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            HMSLog.e("HttpRequestHelper", "Failed to new TLSSocketFactory instance." + e.getMessage());
            throw new IOException("Failed to create SSLSocketFactory.");
        }
    }

    @Override // com.huawei.hms.update.http.IHttpRequestHelper
    public void cancel() {
        this.b = 1;
    }

    @Override // com.huawei.hms.update.http.IHttpRequestHelper
    public void close() {
        this.b = -1;
        HttpsURLConnection httpsURLConnection = this.f6049a;
        if (httpsURLConnection != null) {
            httpsURLConnection.disconnect();
        }
    }

    @Override // com.huawei.hms.update.http.IHttpRequestHelper
    public int get(String str, OutputStream outputStream, Context context) throws IOException, CanceledException {
        return get(str, outputStream, 0, 0, context);
    }

    @Override // com.huawei.hms.update.http.IHttpRequestHelper
    public int post(String str, InputStream inputStream, OutputStream outputStream, Context context) throws IOException, CanceledException {
        Throwable th;
        OutputStream outputStream2;
        InputStream inputStream2 = null;
        try {
            a(str, context);
            HttpsURLConnection httpsURLConnection = this.f6049a;
            if (httpsURLConnection == null) {
                HMSLog.i("HttpRequestHelper", "mConnection is null");
                IOUtils.closeQuietly((InputStream) null);
                IOUtils.closeQuietly((OutputStream) null);
                return -1;
            }
            httpsURLConnection.setRequestMethod("POST");
            outputStream2 = this.f6049a.getOutputStream();
            try {
                a(inputStream, outputStream2);
                outputStream2.flush();
                int responseCode = this.f6049a.getResponseCode();
                if (responseCode == 200 && outputStream != null) {
                    inputStream2 = this.f6049a.getInputStream();
                    a(new BufferedInputStream(inputStream2, 4096), outputStream);
                    outputStream.flush();
                }
                IOUtils.closeQuietly(inputStream2);
                IOUtils.closeQuietly(outputStream2);
                return responseCode;
            } catch (Throwable th2) {
                th = th2;
                IOUtils.closeQuietly(inputStream2);
                IOUtils.closeQuietly(outputStream2);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            outputStream2 = null;
        }
    }

    @Override // com.huawei.hms.update.http.IHttpRequestHelper
    public int get(String str, OutputStream outputStream, int i, int i2, Context context) throws IOException, CanceledException {
        InputStream inputStream = null;
        try {
            a(str, context);
            HttpsURLConnection httpsURLConnection = this.f6049a;
            if (httpsURLConnection == null) {
                HMSLog.i("HttpRequestHelper", "mConnection is null");
                IOUtils.closeQuietly((InputStream) null);
                return -1;
            }
            httpsURLConnection.setRequestMethod("GET");
            if (i > 0) {
                this.f6049a.addRequestProperty("Range", "bytes=" + i + Constants.LINK + i2);
            }
            int responseCode = this.f6049a.getResponseCode();
            if ((i > 0 && responseCode == 206) || (i <= 0 && responseCode == 200)) {
                inputStream = this.f6049a.getInputStream();
                a(new BufferedInputStream(inputStream, 4096), outputStream);
                if (outputStream != null) {
                    outputStream.flush();
                }
            }
            return responseCode;
        } finally {
            IOUtils.closeQuietly((InputStream) null);
        }
    }

    private void a(InputStream inputStream, OutputStream outputStream) throws IOException, CanceledException {
        if (inputStream != null && outputStream != null) {
            byte[] bArr = new byte[4096];
            do {
                int read = inputStream.read(bArr);
                if (-1 == read) {
                    return;
                } else {
                    outputStream.write(bArr, 0, read);
                }
            } while (this.b != 1);
            throw new CanceledException("HTTP(s) request was canceled.");
        }
        HMSLog.w("HttpRequestHelper", "input or output is null");
    }
}
