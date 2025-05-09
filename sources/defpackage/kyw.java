package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.apacheclient.ApacheClientInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.KeyValDbManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/* loaded from: classes5.dex */
public class kyw implements Runnable {
    private static boolean b = false;
    private static String e;

    /* renamed from: a, reason: collision with root package name */
    private Context f14710a;
    private String c;
    private long d;
    private long f;
    private boolean g;
    private boolean h;
    private Handler i;
    private boolean j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;

    public kyw(Context context, Handler handler, boolean z, boolean z2, boolean z3) {
        this.f14710a = context;
        this.i = handler;
        this.g = z;
        this.h = z2;
        this.j = z3;
    }

    public static void e(boolean z) {
        b = z;
    }

    private void b(Context context, boolean z) {
        kxi kxiVar = new kxi();
        kxiVar.d(2);
        if (CommonUtil.br()) {
            kxiVar.b(CommonUtil.h());
        } else {
            kxiVar.b(CommonUtil.a(context, false));
        }
        kxiVar.c(e);
        if (this.h) {
            kxiVar.e(kxu.a(kxu.a(), context));
        } else if (this.j) {
            kxiVar.e(kxu.a(kxu.n(), context));
        } else {
            kxiVar.e(kxu.a(kxu.j(), context));
        }
        kxiVar.d("");
        new kyk(kxiVar, z, this.j).e();
    }

    private long b() {
        if (this.o == null) {
            return 0L;
        }
        File file = new File(this.o);
        if (file.exists()) {
            return file.length();
        }
        return 0L;
    }

    private boolean c() {
        this.l = d().r();
        this.o = this.f14710a.getFilesDir() + File.separator + this.l;
        this.d = d().c();
        this.m = d().x();
        this.n = d().h();
        this.k = d().s();
        e = d().w();
        this.f = b();
        KeyValDbManager b2 = KeyValDbManager.b(this.f14710a);
        String e2 = b2.e("health_ota_file_md5");
        String e3 = b2.e("health_ota_file_sha256");
        String e4 = b2.e("health_ota_file_path");
        if (!TextUtils.isEmpty(this.k) && !TextUtils.equals(e3, this.k)) {
            LogUtil.a("DownloadThread", "initDownload check sha256.");
            b(b2, "health_ota_file_md5", "health_ota_file_sha256", "health_ota_file_path");
        } else if (!TextUtils.equals(e2, this.n) || !TextUtils.equals(e4, this.o) || this.f > this.d) {
            LogUtil.a("DownloadThread", "initDownload check md5.");
            b(b2, "health_ota_file_md5", "health_ota_file_sha256", "health_ota_file_path");
        } else {
            LogUtil.h("DownloadThread", "initDownload do nothing.");
        }
        this.c = d().i();
        LogUtil.a("DownloadThread", "initDownload mDownloadedSize=", Long.valueOf(this.f), ",mDownloadUrl=", this.c, ",mIsApp:", Boolean.valueOf(this.g), ",mIsAw70:", Boolean.valueOf(this.h));
        if (this.c != null) {
            return true;
        }
        LogUtil.h("R_OTA_DownloadThread", "initDownload mDownloadUrl is null");
        a(2);
        LogUtil.c("DownloadThread", "initDownload the download uri is null, set the state to DOWNLOADING_STATE_END");
        return false;
    }

    private void b(KeyValDbManager keyValDbManager, String str, String str2, String str3) {
        kxu.b(this.o);
        this.f = 0L;
        keyValDbManager.e(str, this.n);
        keyValDbManager.e(str2, this.k);
        keyValDbManager.e(str3, this.o);
    }

    private kxl d() {
        if (this.g) {
            return kxu.e();
        }
        if (this.h) {
            return kxu.d();
        }
        if (this.j) {
            return kxu.l();
        }
        return kxu.b();
    }

    @Override // java.lang.Runnable
    public void run() {
        OutputStream outputStream;
        DefaultHttpClient defaultHttpClient;
        kxf kxfVar;
        HttpGet d;
        LogUtil.a("DownloadThread", "DownloadThread run begin");
        InputStream inputStream = null;
        try {
        } catch (Exception e2) {
            e = e2;
            outputStream = null;
            defaultHttpClient = null;
        } catch (Throwable th) {
            th = th;
            outputStream = null;
            defaultHttpClient = null;
        }
        if (!c()) {
            c(null, null, null);
            return;
        }
        DefaultHttpClient defaultHttpClient2 = new DefaultHttpClient();
        try {
            kxfVar = new kxf();
            kxfVar.e(this.d);
            d = d(defaultHttpClient2);
        } catch (Exception e3) {
            e = e3;
            defaultHttpClient = defaultHttpClient2;
            outputStream = null;
        } catch (Throwable th2) {
            th = th2;
            defaultHttpClient = defaultHttpClient2;
            outputStream = null;
        }
        if (d == null) {
            c(null, null, defaultHttpClient2);
            return;
        }
        d.addHeader("Range", "bytes=" + this.f + Constants.LINK);
        int o = kxu.o();
        LogUtil.a("DownloadThread", "run Range size=", Long.valueOf(this.f), ",DownloadThread retryNum is ", Integer.valueOf(o));
        try {
            InputStream content = (!(defaultHttpClient2 instanceof HttpClient) ? defaultHttpClient2.execute(d) : ApacheClientInstrumentation.execute(defaultHttpClient2, d)).getEntity().getContent();
            try {
                try {
                    FileOutputStream openFileOutput = this.f14710a.openFileOutput(this.l, 32768);
                    b(content, d, openFileOutput, kxfVar);
                    c(content, openFileOutput, defaultHttpClient2);
                } catch (FileNotFoundException unused) {
                    LogUtil.b("R_OTA_DownloadThread", "run download failed:FileNotFoundException");
                    d.abort();
                    defaultHttpClient2.getConnectionManager().shutdown();
                    a(4);
                    c(content, null, defaultHttpClient2);
                    return;
                }
            } catch (Exception e4) {
                e = e4;
                defaultHttpClient = defaultHttpClient2;
                outputStream = null;
                inputStream = content;
                try {
                    e(e);
                    c(inputStream, outputStream, defaultHttpClient);
                    LogUtil.a("DownloadThread", "run download end");
                } catch (Throwable th3) {
                    th = th3;
                    c(inputStream, outputStream, defaultHttpClient);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                defaultHttpClient = defaultHttpClient2;
                outputStream = null;
                inputStream = content;
                c(inputStream, outputStream, defaultHttpClient);
                throw th;
            }
            LogUtil.a("DownloadThread", "run download end");
        } catch (IOException unused2) {
            LogUtil.b("R_OTA_DownloadThread", "run connect failed, IOException");
            d(d, defaultHttpClient2, o);
            c(null, null, defaultHttpClient2);
        }
    }

    private void e(Exception exc) {
        if (exc instanceof RuntimeException) {
            LogUtil.b("R_OTA_DownloadThread", "run(), RuntimeException");
            LogUtil.b("DownloadThread", "RuntimeException Exception, set the state to DOWNLOADING_STATE_END");
        } else {
            LogUtil.b("R_OTA_DownloadThread", "run(), Exception");
            LogUtil.b("DownloadThread", "Unknown Exception, set the state to DOWNLOADING_STATE_END");
        }
        a(6);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0069, code lost:
    
        r15 = r0;
        r1 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(java.io.InputStream r18, org.apache.http.client.methods.HttpGet r19, java.io.OutputStream r20, defpackage.kxf r21) {
        /*
            r17 = this;
            r7 = r17
            r2 = r21
            long r3 = r7.d
            long r0 = r7.f
            java.lang.Long r5 = java.lang.Long.valueOf(r3)
            java.lang.String r6 = ", currentDownloadSize = "
            java.lang.Long r8 = java.lang.Long.valueOf(r0)
            java.lang.String r9 = "fileTotalSize = "
            java.lang.Object[] r5 = new java.lang.Object[]{r9, r5, r6, r8}
            java.lang.String r6 = "DownloadThread"
            com.huawei.hwlogsmodel.LogUtil.a(r6, r5)
            r5 = 8192(0x2000, float:1.148E-41)
            byte[] r5 = new byte[r5]
            r6 = 0
            r8 = r6
        L23:
            int r9 = defpackage.kxu.o()
            r10 = r18
            int r11 = r7.b(r10, r5)
            java.lang.String r12 = "R_OTA_DownloadThread"
            java.lang.String r13 = "download cancel"
            r14 = -1
            if (r11 != r14) goto L48
            java.lang.Object[] r5 = new java.lang.Object[]{r13}
            com.huawei.hwlogsmodel.LogUtil.a(r12, r5)
            android.content.Context r5 = r7.f14710a
            boolean r5 = defpackage.kxu.b(r5)
            r7.b(r9, r5)
            r19.abort()
            goto L69
        L48:
            boolean r9 = defpackage.kyw.b
            if (r9 == 0) goto L57
            java.lang.Object[] r5 = new java.lang.Object[]{r13}
            com.huawei.hwlogsmodel.LogUtil.a(r12, r5)
            r19.abort()
            goto L8e
        L57:
            defpackage.kxu.a(r6)
            r9 = r20
            int r11 = r7.a(r9, r11, r5)
            if (r11 != r14) goto L6e
            r19.abort()
            r5 = 5
            r7.a(r5)
        L69:
            r5 = 1
            r15 = r0
            r1 = r5
        L6c:
            r5 = r15
            goto L91
        L6e:
            long r11 = (long) r11
            long r0 = r0 + r11
            int r11 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            r12 = 7
            if (r11 >= 0) goto L82
            int r11 = r8 % 20
            if (r11 != 0) goto L7f
            r2.d(r0)
            r7.b(r2, r12)
        L7f:
            int r8 = r8 + 1
            goto L23
        L82:
            r17.h()
            r19.abort()
            r2.d(r0)
            r7.b(r2, r12)
        L8e:
            r15 = r0
            r1 = r6
            goto L6c
        L91:
            r0 = r17
            r2 = r21
            r0.a(r1, r2, r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kyw.b(java.io.InputStream, org.apache.http.client.methods.HttpGet, java.io.OutputStream, kxf):void");
    }

    private void h() {
        LogUtil.a("R_OTA_DownloadThread", "download complete");
        kxu.e(1);
        kxu.a(false);
        LogUtil.a("DownloadThread", "download complete, set the state to DOWNLOADING_STATE_END");
    }

    private int b(InputStream inputStream, byte[] bArr) {
        try {
            return inputStream.read(bArr);
        } catch (IOException unused) {
            LogUtil.b("R_OTA_DownloadThread", "download failed InputStream error:IOException");
            return -1;
        }
    }

    private int a(OutputStream outputStream, int i, byte[] bArr) {
        if (bArr == null || i < 0 || i > bArr.length) {
            LogUtil.b("R_OTA_DownloadThread", "download failed OutputStream error: IndexOutOfBounds");
            return -1;
        }
        if (outputStream != null) {
            try {
                outputStream.write(bArr, 0, i);
                outputStream.flush();
                return i;
            } catch (IOException unused) {
                LogUtil.b("R_OTA_DownloadThread", "download failed OutputStream error: IOException");
            }
        }
        return -1;
    }

    private void a(boolean z, kxf kxfVar, long j, long j2) {
        if (b) {
            a(9);
            LogUtil.a("DownloadThread", "cancel download, set the state to DOWNLOADING_STATE_END");
        } else if (!z) {
            e(kxfVar, j, j2);
        }
        LogUtil.a("DownloadThread", "download end");
    }

    private void e(kxf kxfVar, long j, long j2) {
        if (j2 >= j) {
            e(kxfVar);
        } else {
            LogUtil.a("DownloadThread", "currentDownloadSize < fileTotalSize");
            kxu.b(this.o);
            kxu.a(6, this.g, this.h, this.j);
            b((Object) null, 6);
        }
        kxu.a(false);
        kxu.e(1);
    }

    private void e(kxf kxfVar) {
        String str;
        if (this.g) {
            kxfVar.a(this.m);
            kxfVar.b(this.o);
            b(kxfVar, 8);
            b(this.f14710a, this.g);
            return;
        }
        if (this.h) {
            str = this.f14710a.getFilesDir() + File.separator + kxu.a() + "(new).apk";
        } else if (this.j) {
            str = this.f14710a.getFilesDir() + File.separator + kxu.n() + "(new).zip";
        } else {
            str = this.f14710a.getFilesDir() + File.separator + kxu.j() + "(new).apk";
        }
        LogUtil.a("DownloadThread", "newAppDir : ", str, ";HwSelfUpdateUtility.applicationInfo.STORAGEPATH :", this.o);
        if (g()) {
            String str2 = this.o;
            if (str2 != null) {
                kxfVar.b(str2);
                kxfVar.c(this.n);
                kxfVar.e(this.k);
                kxfVar.a(this.m);
                LogUtil.a("R_OTA_DownloadThread", "download complete and verify success");
                b(kxfVar, 8);
                b(this.f14710a, this.g);
                return;
            }
            c(kxfVar);
            return;
        }
        c(kxfVar);
    }

    private void c(InputStream inputStream, OutputStream outputStream, HttpClient httpClient) {
        LogUtil.c("DownloadThread", "enter closeHttp");
        if (httpClient != null) {
            LogUtil.c("DownloadThread", "httpClient close");
            httpClient.getConnectionManager().shutdown();
        }
        IoUtils.e(outputStream);
        IoUtils.e(inputStream);
    }

    private void c(kxf kxfVar) {
        LogUtil.a("R_OTA_DownloadThread", "verify sha256 or md5 failed");
        kxu.b(this.o);
        kxu.a(1, this.g, this.h, this.j);
        b(kxfVar, 1);
    }

    private void a(int i) {
        kxu.a(i, this.g, this.h, this.j);
        LogUtil.a("DownloadThread", "downloadEnd what = ", Integer.valueOf(i));
        if (i != 3 && i != 9) {
            kxu.b(this.o);
        }
        kxu.e(1);
        kxu.a(false);
        b((Object) null, i);
    }

    private void b(int i, boolean z) {
        Object[] objArr = new Object[1];
        objArr[0] = z ? "download cancel, network is connect but not avaiable," : "download cancel, network is not available,";
        LogUtil.a("R_OTA_DownloadThread", objArr);
        if (i < 3) {
            Object[] objArr2 = new Object[2];
            objArr2[0] = z ? "download cancel, network is connect but not avaiable," : "download cancel, network is not available,";
            objArr2[1] = " set the state to DOWNLOADING_STATE_RETRY";
            LogUtil.a("R_OTA_DownloadThread", objArr2);
            kxu.e(3);
            kxu.a(false);
            return;
        }
        Object[] objArr3 = new Object[2];
        objArr3[0] = z ? "download cancel, network is connect but not avaiable," : "download cancel, network is not available,";
        objArr3[1] = " set the state to DOWNLOADING_STATE_END";
        LogUtil.a("R_OTA_DownloadThread", objArr3);
        kxu.e(1);
        kxu.a(false);
        kxu.a(3, this.g, this.h, this.j);
        b((Object) null, 3);
    }

    private HttpGet d(HttpClient httpClient) {
        try {
            HttpGet httpGet = new HttpGet(this.c);
            kxu.a(httpGet, httpClient, this.f14710a);
            httpGet.getParams().setIntParameter("http.socket.timeout", 10000);
            httpGet.getParams().setIntParameter("http.connection.timeout", 20000);
            httpGet.getParams().setIntParameter("http.socket.buffer-size", 8192);
            return httpGet;
        } catch (IllegalArgumentException unused) {
            LogUtil.b("R_OTA_DownloadThread", "download failed, IllegalArgumentException");
            kxu.b(this.o);
            kxu.e(1);
            kxu.a(false);
            kxu.a(2, this.g, this.h, this.j);
            b((Object) null, 2);
            return null;
        }
    }

    private void d(HttpGet httpGet, HttpClient httpClient, int i) {
        if (i < 3) {
            LogUtil.a("R_OTA_DownloadThread", "connect failed, set the state to DOWNLOADING_STATE_RETRY");
            kxu.e(3);
            kxu.a(false);
        } else {
            LogUtil.a("R_OTA_DownloadThread", "connect failed, set the state to DOWNLOADING_STATE_END and sendMessage DOWNLOAD_FAILED_CONNECT_ERROR");
            a(3);
        }
        httpGet.abort();
        httpClient.getConnectionManager().shutdown();
    }

    private void b(Object obj, int i) {
        if (this.i != null) {
            Message obtain = Message.obtain();
            obtain.obj = obj;
            obtain.what = i;
            this.i.sendMessage(obtain);
        }
    }

    private boolean g() {
        if (!TextUtils.isEmpty(this.k)) {
            return i();
        }
        return a();
    }

    private boolean i() {
        String k = CommonUtil.k(this.o);
        if (!TextUtils.isEmpty(this.k) && this.k.equalsIgnoreCase(k)) {
            LogUtil.a("R_OTA_DownloadThread", "isSha256Valid verify sha256 success.");
            return true;
        }
        LogUtil.h("R_OTA_DownloadThread", "isSha256Valid verify sha256 failed.");
        return false;
    }

    private boolean a() {
        String e2 = kxv.e(this.o);
        if (!TextUtils.isEmpty(this.n) && this.n.equalsIgnoreCase(e2)) {
            LogUtil.a("R_OTA_DownloadThread", "isMd5Valid verify md5 success.");
            return true;
        }
        LogUtil.h("R_OTA_DownloadThread", "isMd5Valid verify md5 failed.");
        return false;
    }

    public void e() {
        Thread thread = new Thread(this);
        thread.setName("Ver-Download");
        thread.start();
    }
}
