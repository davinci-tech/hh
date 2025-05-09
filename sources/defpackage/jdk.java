package defpackage;

import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.utils.handler.AppDownloadHandler;
import com.huawei.networkclient.CommonApi;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.IllegalBlockingModeException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jdk implements Runnable {
    private static Map<String, Boolean> e = new HashMap(16);

    /* renamed from: a, reason: collision with root package name */
    private String f13753a;
    private int b;
    private AppDownloadHandler c;
    private long d;
    private final String f;
    private int g;
    private String h;
    private String i;
    private boolean j;
    private String l;
    private String m;
    private int o;

    public jdk(d dVar) {
        this.f = dVar.g;
        this.i = dVar.c;
        this.f13753a = dVar.d;
        this.h = dVar.e;
        this.c = dVar.b;
        this.m = dVar.i;
        this.l = dVar.f;
        this.j = dVar.f13754a;
        this.o = dVar.h;
    }

    public static void e(String str, boolean z) {
        e.put(str, Boolean.valueOf(z));
        LogUtil.a("HwOtaDownloadRunnable", "setCancel value:", Integer.valueOf(e.size()), " mac ", CommonUtil.l(str), " isCancel ", Boolean.valueOf(z));
    }

    @Override // java.lang.Runnable
    public void run() {
        LogUtil.a("HwOtaDownloadRunnable", "run enter");
        if (a()) {
            return;
        }
        c();
    }

    private boolean a() {
        if (this.f13753a == null || this.h == null || this.c == null) {
            LogUtil.h("HwOtaDownloadRunnable", "mDownLoadUrl or mFilePath or mDownloadListener is null");
            return true;
        }
        if (CommonUtil.as() && TextUtils.equals(kxz.m(BaseApplication.getContext()), "true")) {
            try {
                String canonicalPath = new File(this.h).getCanonicalPath();
                List<String> l = kxz.l(BaseApplication.getContext());
                if (l.size() >= 2 && !l.contains(canonicalPath)) {
                    String str = l.get(0);
                    File file = new File(str);
                    if (file.exists()) {
                        file.delete();
                    }
                    l.remove(0);
                    kxz.e(l, BaseApplication.getContext());
                    LogUtil.a("HwOtaDownloadRunnable", "initDownload delete LoopUpgradePackagePaths path = ", str, "startToDownload, path = ", canonicalPath);
                }
            } catch (IOException unused) {
                LogUtil.h("HwOtaDownloadRunnable", "initDownload IOException");
            }
        }
        File file2 = new File(this.h);
        if (file2.exists()) {
            if (this.j) {
                long length = file2.length();
                this.d = length;
                LogUtil.a("HwOtaDownloadRunnable", "resume mDownloadedSize :", Long.valueOf(length));
            } else {
                LogUtil.a("HwOtaDownloadRunnable", "not resume isDelete :", Boolean.valueOf(file2.delete()));
                this.d = 0L;
            }
        }
        return false;
    }

    private void b(Object obj, int i) {
        if (this.c != null) {
            Message obtain = Message.obtain();
            obtain.obj = obj;
            obtain.what = i;
            this.c.sendMessage(obtain);
        }
    }

    private void c() {
        kxf kxfVar;
        Response<ResponseBody> execute;
        int code;
        InputStream inputStream = null;
        try {
            try {
                try {
                    kxfVar = new kxf();
                    kxl c = kxu.c(this.i);
                    if (c != null) {
                        kxfVar.e(c.c());
                    }
                    HashMap hashMap = new HashMap(16);
                    if (this.j && this.d > 0) {
                        hashMap.put("Range", "bytes=" + this.d + Constants.LINK);
                    }
                    execute = ((CommonApi) lqi.a(e()).b(CommonApi.class)).commonDownload(this.f13753a, hashMap).execute();
                    code = execute.getCode();
                    LogUtil.a("HwOtaDownloadRunnable", "download getResponseCode:", Integer.valueOf(code));
                } catch (IOException e2) {
                    LogUtil.b("HwOtaDownloadRunnable", "downloadFile IOException ", ExceptionUtils.d(e2));
                    a(1003);
                }
            } catch (IllegalStateException e3) {
                LogUtil.b("HwOtaDownloadRunnable", "downloadFile IllegalStateException", ExceptionUtils.d(e3));
                a(1003);
            }
            if (a(code, kxfVar)) {
                return;
            }
            ResponseBody body = execute.getBody();
            if (body != null && body.getInputStream() != null) {
                long contentLength = body.getContentLength();
                inputStream = body.getInputStream();
                if (b(code, contentLength)) {
                    return;
                }
                d(contentLength, inputStream, kxfVar);
                return;
            }
            LogUtil.c("HwOtaDownloadRunnable", "downloadFile responseBody or responseBody.getInputStream() is null");
        } finally {
            d(null);
        }
    }

    private static HttpClient e() {
        return new HttpClient.Builder().connectTimeout(5000).readTimeout(5000).build();
    }

    private boolean b(int i, long j) {
        LogUtil.a("HwOtaDownloadRunnable", "checkResult responseCode", Integer.valueOf(i));
        if (i != 200 && i != 206) {
            LogUtil.h("HwOtaDownloadRunnable", "responseCode is :", Integer.valueOf(i));
            b((Object) null, 2);
            return true;
        }
        if (j != 0) {
            return false;
        }
        LogUtil.h("HwOtaDownloadRunnable", "run file length is 0");
        b((Object) null, 2);
        return true;
    }

    private boolean a(int i, kxf kxfVar) {
        if (i != 416) {
            return false;
        }
        if (f()) {
            e(kxfVar);
            LogUtil.a("HwOtaDownloadRunnable", "checkResponseCode onComplete");
            return true;
        }
        d();
        b((Object) null, 2);
        LogUtil.h("HwOtaDownloadRunnable", "checkResponseCode onFailure");
        return true;
    }

    private boolean f() {
        if (!TextUtils.isEmpty(this.m)) {
            return j();
        }
        return i();
    }

    private boolean j() {
        String k = CommonUtil.k(this.h);
        if (!TextUtils.isEmpty(this.m) && this.m.equalsIgnoreCase(k)) {
            LogUtil.a("HwOtaDownloadRunnable", "isSha256Valid verify sha256 success.");
            return true;
        }
        LogUtil.h("HwOtaDownloadRunnable", "isSha256Valid verify sha256 failed.");
        return false;
    }

    private boolean i() {
        String e2 = kxv.e(this.h);
        if (!TextUtils.isEmpty(this.l) && this.l.equalsIgnoreCase(e2)) {
            LogUtil.a("HwOtaDownloadRunnable", "isMd5Valid verify md5 success.");
            return true;
        }
        LogUtil.h("HwOtaDownloadRunnable", "isMd5Valid verify md5 failed.");
        return false;
    }

    private boolean g() {
        if (e.containsKey(this.f)) {
            return e.get(this.f).booleanValue();
        }
        return false;
    }

    private void e(kxf kxfVar) {
        kxfVar.b(this.h);
        kxfVar.c(this.l);
        kxfVar.e(this.m);
        kxl c = kxu.c(this.i);
        if (c != null) {
            kxfVar.a(c.x());
        }
        b(kxfVar, 8);
    }

    private void d(long j, InputStream inputStream, kxf kxfVar) {
        RandomAccessFile randomAccessFile;
        int i;
        int i2 = 2;
        Object obj = null;
        try {
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(new File(this.h), "rw");
            try {
                long length = randomAccessFile2.length();
                LogUtil.a("HwOtaDownloadRunnable", "initial size ", Long.valueOf(length), " length ", Long.valueOf(j), "sManageMac ", CommonUtil.l(this.f), " sCancelList.size() ", Integer.valueOf(e.size()));
                byte[] bArr = new byte[8192];
                while (true) {
                    if (g()) {
                        Object[] objArr = new Object[i2];
                        objArr[0] = "need cancel sManageMac ";
                        objArr[1] = CommonUtil.l(this.f);
                        LogUtil.a("HwOtaDownloadRunnable", objArr);
                        b(obj, 9);
                        break;
                    }
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        a(kxfVar);
                        break;
                    }
                    length += read;
                    int i3 = (int) ((length / (j + this.d)) * 100.0f);
                    if (i3 - this.b >= 1 || i3 > 99) {
                        if (i3 > 99) {
                            i3 = 100;
                        }
                        this.b = i3;
                        kxfVar.d(i3);
                        b(kxfVar, 7);
                    }
                    randomAccessFile2.seek(randomAccessFile2.length());
                    if (!d(randomAccessFile2, bArr, read)) {
                        break;
                    }
                    if (this.g != 0) {
                        this.g = 0;
                    }
                    i2 = 2;
                    obj = null;
                }
                c(randomAccessFile2);
            } catch (IOException e2) {
                e = e2;
                randomAccessFile = randomAccessFile2;
                i = 2;
                try {
                    Object[] objArr2 = new Object[i];
                    objArr2[0] = "determineTheDownloadUrlType IOException";
                    objArr2[1] = ExceptionUtils.d(e);
                    LogUtil.b("HwOtaDownloadRunnable", objArr2);
                    a(1003);
                    c(randomAccessFile);
                } catch (Throwable th) {
                    th = th;
                    c(randomAccessFile);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                randomAccessFile = randomAccessFile2;
                c(randomAccessFile);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            i = 2;
            randomAccessFile = null;
        } catch (Throwable th3) {
            th = th3;
            randomAccessFile = null;
        }
    }

    private boolean d(RandomAccessFile randomAccessFile, byte[] bArr, int i) {
        try {
            randomAccessFile.write(bArr, 0, i);
            return true;
        } catch (IOException e2) {
            LogUtil.b("HwOtaDownloadRunnable", "writeDownloadFile IOException", ExceptionUtils.d(e2));
            a(1004);
            return false;
        }
    }

    private void c(RandomAccessFile randomAccessFile) {
        if (randomAccessFile != null) {
            try {
                randomAccessFile.close();
            } catch (IOException e2) {
                LogUtil.b("HwOtaDownloadRunnable", "closeFile IOException", ExceptionUtils.d(e2));
            }
        }
    }

    private void a(kxf kxfVar) {
        if (f()) {
            e(kxfVar);
            LogUtil.a("HwOtaDownloadRunnable", "checkResponseCode onComplete");
        } else {
            a(1006);
            LogUtil.h("HwOtaDownloadRunnable", "checkResponseCode onFailure");
        }
    }

    private void d(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
                LogUtil.b("HwOtaDownloadRunnable", "closeStream: IOException");
                return;
            } catch (IllegalBlockingModeException unused2) {
                LogUtil.b("HwOtaDownloadRunnable", "closeStream IllegalBlockingModeException");
                return;
            }
        }
        LogUtil.a("HwOtaDownloadRunnable", "closeStream");
    }

    private void a(int i) {
        LogUtil.a("HwOtaDownloadRunnable", "handleFailed :", Integer.valueOf(i));
        if (i == 1006) {
            d();
            b((Object) null, 1);
            return;
        }
        if (i == 1004) {
            b((Object) null, 5);
            return;
        }
        int i2 = this.g;
        if (i2 < this.o) {
            LogUtil.a("HwOtaDownloadRunnable", "handleFailed,retryNum:", Integer.valueOf(i2));
            b();
            return;
        }
        if (this.c != null) {
            if (i == 1003) {
                b((Object) null, 3);
            } else {
                b((Object) null, 6);
            }
        }
        this.g = 0;
    }

    private void b() {
        this.g++;
        this.b = 0;
        File file = new File(this.h);
        if (file.exists()) {
            if (this.j) {
                long length = file.length();
                this.d = length;
                LogUtil.a("HwOtaDownloadRunnable", "downloadRetry, resume mDownloadedSize :", Long.valueOf(length));
            } else {
                LogUtil.a("HwOtaDownloadRunnable", "downloadRetry, not resume isDelete :", Boolean.valueOf(file.delete()));
                this.d = 0L;
            }
        }
        h();
        c();
    }

    private void d() {
        File file = new File(this.h);
        if (file.exists()) {
            LogUtil.a("HwOtaDownloadRunnable", "handleVerifyFailed file.delete() ", Boolean.valueOf(file.delete()));
        }
    }

    private void h() {
        try {
            LogUtil.a("HwOtaDownloadRunnable", "startWait");
            Thread.sleep(500L);
        } catch (InterruptedException unused) {
            LogUtil.b("HwOtaDownloadRunnable", "startWait InterruptedException");
        }
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private boolean f13754a;
        private AppDownloadHandler b;
        private String c;
        private String d;
        private String e;
        private String f;
        private String g;
        private int h;
        private String i;

        public d(String str, String str2, String str3, String str4, AppDownloadHandler appDownloadHandler) {
            this.g = str;
            this.c = str2;
            this.d = str3;
            this.e = str4;
            this.b = appDownloadHandler;
        }

        public d a(String str) {
            this.i = str;
            return this;
        }

        public d c(String str) {
            this.f = str;
            return this;
        }

        public d d(boolean z) {
            this.f13754a = z;
            return this;
        }

        public d d(int i) {
            this.h = i;
            return this;
        }
    }
}
