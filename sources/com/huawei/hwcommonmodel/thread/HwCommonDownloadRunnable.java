package com.huawei.hwcommonmodel.thread;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.secure.android.common.SecureSSLSocketFactory;
import health.compact.a.CommonUtil;
import health.compact.a.HEXUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* loaded from: classes9.dex */
public class HwCommonDownloadRunnable implements Runnable {
    private static CopyOnWriteArraySet<Integer> c = new CopyOnWriteArraySet<>();

    /* renamed from: a, reason: collision with root package name */
    private int f6268a;
    private String b;
    private DownloadFileListener d;
    private long e;
    private int f;
    private int g;
    private String h;
    private final int i;
    private boolean j;
    private int l;
    private String m;

    public interface DownloadFileListener {
        void onCancel();

        void onComplete();

        void onFailure(int i);

        void onProgress(int i);
    }

    @Override // java.lang.Runnable
    public void run() {
        LogUtil.a("HwCommonDownloadRunnable", "start run, managerId:", Integer.valueOf(this.i));
        if (b()) {
            return;
        }
        d();
    }

    private boolean b() {
        if (this.b == null || this.h == null || this.d == null) {
            LogUtil.h("HwCommonDownloadRunnable", "mDownLoadUrl or mFilePath or mDownloadListener is null");
            return true;
        }
        c.remove(Integer.valueOf(this.i));
        File file = new File(this.h);
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            try {
                if (!parentFile.exists()) {
                    LogUtil.a("HwCommonDownloadRunnable", "parentFile.mkdirs(): ", Boolean.valueOf(parentFile.mkdirs()));
                }
                if (!file.exists()) {
                    LogUtil.a("HwCommonDownloadRunnable", "file.createNewFile(): ", Boolean.valueOf(file.createNewFile()));
                }
            } catch (IOException unused) {
                this.d.onFailure(1001);
            }
        }
        if (file.exists()) {
            if (this.j) {
                long length = file.length();
                this.e = length;
                LogUtil.a("HwCommonDownloadRunnable", "resume mDownloadedSize :", Long.valueOf(length));
            } else {
                LogUtil.a("HwCommonDownloadRunnable", "not resume isDelete :", Boolean.valueOf(file.delete()));
                this.e = 0L;
            }
            return false;
        }
        LogUtil.h("HwCommonDownloadRunnable", "file path is error");
        this.d.onFailure(1001);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0075 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0079 A[Catch: all -> 0x007d, IOException -> 0x007f, ProtocolException -> 0x008c, MalformedURLException -> 0x0099, TRY_ENTER, TRY_LEAVE, TryCatch #3 {ProtocolException -> 0x008c, blocks: (B:3:0x0005, B:5:0x0025, B:7:0x0029, B:12:0x003d, B:13:0x006f, B:17:0x0079, B:22:0x0046, B:24:0x004a, B:28:0x0056, B:32:0x0064), top: B:2:0x0005, outer: #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d() {
        /*
            r7 = this;
            java.lang.String r0 = "HwCommonDownloadRunnable"
            r1 = 1
            r2 = 0
            r3 = 0
            java.net.URL r4 = new java.net.URL     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            java.lang.String r5 = r7.b     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            java.net.URLConnection r4 = r4.openConnection()     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            java.net.URLConnection r4 = com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation.openConnection(r4)     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            java.lang.String r6 = "downloadFile openConnection success"
            r5[r2] = r6     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            com.huawei.hwlogsmodel.LogUtil.a(r0, r5)     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            java.lang.String r5 = r7.b     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            boolean r5 = r7.b(r5)     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            if (r5 == 0) goto L46
            boolean r5 = r4 instanceof java.net.HttpURLConnection     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            if (r5 == 0) goto L6d
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            java.net.HttpURLConnection r4 = r7.e(r4)     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            int r5 = r4.getResponseCode()     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            boolean r6 = r7.a(r5)     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            if (r6 == 0) goto L3d
            r7.d(r3)
            return
        L3d:
            int r6 = r4.getContentLength()     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            java.io.InputStream r3 = r4.getInputStream()     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            goto L6f
        L46:
            boolean r5 = r4 instanceof javax.net.ssl.HttpsURLConnection     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            if (r5 == 0) goto L6d
            javax.net.ssl.HttpsURLConnection r4 = (javax.net.ssl.HttpsURLConnection) r4     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            javax.net.ssl.HttpsURLConnection r4 = r7.e(r4)     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            if (r4 != 0) goto L56
            r7.d(r3)
            return
        L56:
            int r5 = r4.getResponseCode()     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            boolean r6 = r7.a(r5)     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            if (r6 == 0) goto L64
            r7.d(r3)
            return
        L64:
            int r6 = r4.getContentLength()     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            java.io.InputStream r3 = r4.getInputStream()     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            goto L6f
        L6d:
            r5 = r2
            r6 = r5
        L6f:
            boolean r4 = r7.d(r5, r6)     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            if (r4 == 0) goto L79
            r7.d(r3)
            return
        L79:
            r7.d(r6, r3)     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L7f java.net.ProtocolException -> L8c java.net.MalformedURLException -> L99
            goto La5
        L7d:
            r0 = move-exception
            goto La9
        L7f:
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L7d
            java.lang.String r4 = "downloadFile IOException"
            r1[r2] = r4     // Catch: java.lang.Throwable -> L7d
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)     // Catch: java.lang.Throwable -> L7d
            r7.a()     // Catch: java.lang.Throwable -> L7d
            goto La5
        L8c:
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L7d
            java.lang.String r4 = "downloadFile ProtocolException"
            r1[r2] = r4     // Catch: java.lang.Throwable -> L7d
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)     // Catch: java.lang.Throwable -> L7d
            r7.a()     // Catch: java.lang.Throwable -> L7d
            goto La5
        L99:
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L7d
            java.lang.String r4 = "downloadFile MalformedURLException"
            r1[r2] = r4     // Catch: java.lang.Throwable -> L7d
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)     // Catch: java.lang.Throwable -> L7d
            r7.a()     // Catch: java.lang.Throwable -> L7d
        La5:
            r7.d(r3)
            return
        La9:
            r7.d(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwcommonmodel.thread.HwCommonDownloadRunnable.d():void");
    }

    private void a() {
        if (CommonUtil.aa(BaseApplication.getContext())) {
            d(1003);
            return;
        }
        LogUtil.h("HwCommonDownloadRunnable", "checkExceptionReason network not connect");
        DownloadFileListener downloadFileListener = this.d;
        if (downloadFileListener != null) {
            downloadFileListener.onFailure(1007);
        }
    }

    private HttpsURLConnection e(HttpsURLConnection httpsURLConnection) throws ProtocolException {
        if (b(httpsURLConnection)) {
            this.d.onFailure(1002);
            return null;
        }
        httpsURLConnection.setRequestMethod("GET");
        httpsURLConnection.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        httpsURLConnection.setConnectTimeout(10000);
        if (this.j && this.e > 0) {
            httpsURLConnection.setRequestProperty("Range", "bytes=" + this.e + Constants.LINK);
        }
        return httpsURLConnection;
    }

    private HttpURLConnection e(HttpURLConnection httpURLConnection) throws ProtocolException {
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(10000);
        if (this.j && this.e > 0) {
            httpURLConnection.setRequestProperty("Range", "bytes=" + this.e + Constants.LINK);
        }
        return httpURLConnection;
    }

    private boolean d(int i, int i2) {
        LogUtil.a("HwCommonDownloadRunnable", "checkResult responseCode", Integer.valueOf(i));
        if (i != 200 && i != 206) {
            LogUtil.h("HwCommonDownloadRunnable", "responseCode is :", Integer.valueOf(i));
            this.d.onFailure(1003);
            return true;
        }
        if (i2 != 0) {
            return false;
        }
        LogUtil.h("HwCommonDownloadRunnable", "run file length is 0");
        this.d.onFailure(1005);
        return true;
    }

    private boolean a(int i) {
        LogUtil.a("HwCommonDownloadRunnable", "checkResponseCode responseCode :", Integer.valueOf(i));
        if (i != 416) {
            return false;
        }
        if (!TextUtils.isEmpty(this.m) && this.m.equalsIgnoreCase(c(this.h))) {
            this.d.onComplete();
            LogUtil.a("HwCommonDownloadRunnable", "checkResponseCode onComplete");
            return true;
        }
        d(1004);
        LogUtil.h("HwCommonDownloadRunnable", "checkResponseCode onFailure");
        return true;
    }

    private boolean b(String str) {
        int indexOf = str.indexOf(":");
        if (indexOf != -1) {
            return "http".equalsIgnoreCase(str.substring(0, indexOf));
        }
        return false;
    }

    private void d(int i, InputStream inputStream) throws IOException {
        if (inputStream == null) {
            LogUtil.h("HwCommonDownloadRunnable", "inputStream is null");
            this.d.onFailure(1003);
            return;
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File(this.h), "rw");
        long length = randomAccessFile.length();
        LogUtil.a("HwCommonDownloadRunnable", "initial size:", Long.valueOf(length));
        byte[] bArr = new byte[1024];
        while (true) {
            if (c.contains(Integer.valueOf(this.i))) {
                LogUtil.a("HwCommonDownloadRunnable", "need cancel");
                this.d.onCancel();
                break;
            }
            int read = inputStream.read(bArr);
            if (read <= 0) {
                if (TextUtils.isEmpty(this.m)) {
                    LogUtil.a("HwCommonDownloadRunnable", "hashValue is empty,report complete");
                    this.d.onComplete();
                } else {
                    if (this.m.equalsIgnoreCase(c(this.h))) {
                        LogUtil.a("HwCommonDownloadRunnable", "hashValue equals");
                        this.d.onComplete();
                    } else {
                        d(1006);
                    }
                }
            } else {
                length += read;
                int i2 = (int) ((length / (i + this.e)) * 100.0f);
                if (i2 - this.f6268a >= this.f || i2 > 99) {
                    this.d.onProgress(i2);
                    this.f6268a = i2;
                }
                randomAccessFile.seek(randomAccessFile.length());
                randomAccessFile.write(bArr, 0, read);
            }
        }
        randomAccessFile.close();
    }

    private String c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwCommonDownloadRunnable", "path is null");
            return "";
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(CommonUtil.c(str));
            try {
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                    byte[] bArr = new byte[1024];
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        messageDigest.update(bArr, 0, 0);
                    } else {
                        messageDigest.update(bArr, 0, read);
                        while (true) {
                            int read2 = fileInputStream.read(bArr);
                            if (read2 == -1) {
                                break;
                            }
                            messageDigest.update(bArr, 0, read2);
                        }
                    }
                    return HEXUtils.a(messageDigest.digest());
                } finally {
                    d(fileInputStream);
                }
            } catch (IOException unused) {
                LogUtil.b("HwCommonDownloadRunnable", "getShaValue IOException");
                return "";
            } catch (NoSuchAlgorithmException unused2) {
                LogUtil.b("HwCommonDownloadRunnable", "getShaValue NoSuchAlgorithmException");
                return "";
            }
        } catch (FileNotFoundException unused3) {
            LogUtil.b("HwCommonDownloadRunnable", "getShaValue FileNotFoundException");
            return "";
        }
    }

    private boolean b(HttpsURLConnection httpsURLConnection) {
        try {
            httpsURLConnection.setSSLSocketFactory(SecureSSLSocketFactory.getInstance(BaseApplication.getContext()));
            return false;
        } catch (IOException | IllegalAccessException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException unused) {
            LogUtil.b("HwCommonDownloadRunnable", "setScoketFactoryFunc exception");
            return true;
        }
    }

    private void d(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
                LogUtil.b("HwCommonDownloadRunnable", "closeStream: IOException");
                return;
            }
        }
        LogUtil.a("HwCommonDownloadRunnable", "closeStream");
    }

    private void d(int i) {
        LogUtil.a("HwCommonDownloadRunnable", "handleFailed :", Integer.valueOf(i));
        int i2 = this.g;
        if (i2 < this.l) {
            LogUtil.a("HwCommonDownloadRunnable", "handleFailed,retryNum:", Integer.valueOf(i2));
            if (i == 1006 || i == 1004) {
                b(i);
                return;
            } else {
                e();
                return;
            }
        }
        DownloadFileListener downloadFileListener = this.d;
        if (downloadFileListener != null) {
            downloadFileListener.onFailure(i);
        }
        this.g = 0;
    }

    private void e() {
        this.g++;
        this.f6268a = 0;
        File file = new File(this.h);
        if (file.exists()) {
            if (this.j) {
                long length = file.length();
                this.e = length;
                LogUtil.a("HwCommonDownloadRunnable", "downloadRetry, resume mDownloadedSize :", Long.valueOf(length));
            } else {
                LogUtil.a("HwCommonDownloadRunnable", "downloadRetry, not resume isDelete :", Boolean.valueOf(file.delete()));
                this.e = 0L;
            }
        }
        g();
        d();
    }

    private void b(int i) {
        File file = new File(this.h);
        if (file.exists()) {
            if (file.delete()) {
                if (a(file)) {
                    c();
                    return;
                } else {
                    e();
                    return;
                }
            }
            DownloadFileListener downloadFileListener = this.d;
            if (downloadFileListener != null) {
                downloadFileListener.onFailure(i);
                return;
            }
            return;
        }
        if (a(file)) {
            c();
        } else {
            e();
        }
    }

    private void c() {
        DownloadFileListener downloadFileListener = this.d;
        if (downloadFileListener != null) {
            downloadFileListener.onFailure(1001);
        }
    }

    private boolean a(File file) {
        File parentFile = file.getParentFile();
        try {
            if (parentFile == null) {
                LogUtil.h("HwCommonDownloadRunnable", "createDownloadFileError parentFile is null");
                return true;
            }
            if (!parentFile.exists()) {
                boolean mkdirs = parentFile.mkdirs();
                LogUtil.a("HwCommonDownloadRunnable", "createDownloadFileError parentFile.mkdirs(): ", Boolean.valueOf(mkdirs));
                if (!mkdirs) {
                    return true;
                }
            }
            if (!file.exists()) {
                boolean createNewFile = file.createNewFile();
                LogUtil.a("HwCommonDownloadRunnable", "createDownloadFileError file.createNewFile(): ", Boolean.valueOf(createNewFile));
                if (!createNewFile) {
                    return true;
                }
            }
            return false;
        } catch (IOException unused) {
            LogUtil.b("HwCommonDownloadRunnable", "createDownloadFileError IOException");
            return true;
        }
    }

    private void g() {
        try {
            LogUtil.a("HwCommonDownloadRunnable", "startWait");
            Thread.sleep(500L);
        } catch (InterruptedException unused) {
            LogUtil.b("HwCommonDownloadRunnable", "startWait InterruptedException");
        }
    }
}
