package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.net.http.Response;
import com.huawei.openalliance.ad.net.http.e;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

/* loaded from: classes5.dex */
public class rr {
    private static final LinkedHashMap<String, String> c = new LinkedHashMap<>(0, 0.75f, true);

    /* renamed from: a, reason: collision with root package name */
    private rq f7514a;
    private String b;
    private rt d;
    private ct e;
    private Context f;
    private dk g;
    private String h;

    public ru a() {
        if (com.huawei.openalliance.ad.utils.cz.b(this.b)) {
            return null;
        }
        return a(this.d);
    }

    private boolean e(String str) {
        if (this.d == null) {
            ho.c("SourceFetcher", "checkSourceDataAndFileState, source error");
            return false;
        }
        if (b(str) == null) {
            return true;
        }
        ho.b("SourceFetcher", "file is in progress");
        return false;
    }

    private void d(String str) {
        synchronized (this) {
            ho.a("SourceFetcher", "removeLoadingImages, key:%s", com.huawei.openalliance.ad.utils.dl.a(str));
            c.remove(str);
        }
    }

    private void c(String str) {
        synchronized (this) {
            ho.a("SourceFetcher", "addLoadingImages, key:%s", com.huawei.openalliance.ad.utils.dl.a(str));
            c.put(str, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j) {
        File file = new File(this.b);
        if (!file.isDirectory() || file.getFreeSpace() > j) {
            return;
        }
        a("73");
    }

    private String b(String str) {
        String str2;
        synchronized (this) {
            str2 = c.get(str);
        }
        return str2;
    }

    private boolean a(String str, String str2, long j) {
        String str3;
        if (!e(str)) {
            ho.c("SourceFetcher", "downloadUrlToStream, checkSourceDataAndFileState failed");
            return false;
        }
        c(str);
        try {
            try {
                try {
                    Response<Boolean> a2 = this.f7514a.a(str, new a(str2, j));
                    ho.b("SourceFetcher", "httpCode: %s", Integer.valueOf(a2.a()));
                    if (a2.a() != 200) {
                        a("2", a2.a(), a2.d(), j);
                    }
                    this.d.a(a2.m());
                    Boolean b = a2.b();
                    boolean booleanValue = b != null ? b.booleanValue() : false;
                    ho.b("SourceFetcher", "file download result: %s", Boolean.valueOf(booleanValue));
                    d(str);
                    a("72", j);
                    return booleanValue;
                } catch (IllegalArgumentException e) {
                    e = e;
                    str3 = "Error in download file - IllegalArgumentException";
                    ho.c("SourceFetcher", str3);
                    ho.a(5, e);
                    d(str);
                    a("72", j);
                    return false;
                } catch (Exception e2) {
                    e = e2;
                    str3 = "Error in download file";
                    ho.c("SourceFetcher", str3);
                    ho.a(5, e);
                    d(str);
                    a("72", j);
                    return false;
                }
            } catch (Throwable th) {
                th = th;
                d(str);
                a("72", j);
                throw th;
            }
        } catch (IllegalArgumentException e3) {
            e = e3;
        } catch (Exception e4) {
            e = e4;
        } catch (Throwable th2) {
            th = th2;
            d(str);
            a("72", j);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, long j) {
        a(str, 0, "", j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final int i, final String str2, final long j) {
        if (this.e != null) {
            final long currentTimeMillis = System.currentTimeMillis();
            final rt rtVar = this.d;
            com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.rr.1
                @Override // java.lang.Runnable
                public void run() {
                    rr.this.e.a(rtVar, str, j, currentTimeMillis, i, str2);
                }
            });
        }
    }

    private void a(String str) {
        a(str, 0, "", 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BufferedInputStream bufferedInputStream, BufferedOutputStream bufferedOutputStream, File file) {
        com.huawei.openalliance.ad.utils.cy.a(bufferedOutputStream);
        com.huawei.openalliance.ad.utils.cy.a((Closeable) bufferedInputStream);
        com.huawei.openalliance.ad.utils.ae.a(file);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j) {
        File file = new File(this.b);
        if (!file.isDirectory() || file.getFreeSpace() > j) {
            return;
        }
        if (ho.a()) {
            ho.a("SourceFetcher", "free disk space is: %d", Long.valueOf(file.getFreeSpace()));
        }
        com.huawei.openalliance.ad.utils.ae.a(file, j * 3);
    }

    class a implements kx<Boolean> {
        private final String b;
        private final long c;
        private long d;

        @Override // com.huawei.openalliance.ad.lb
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Boolean a(int i, InputStream inputStream, long j, ko koVar) {
            BufferedOutputStream bufferedOutputStream;
            IOException iOException;
            if (200 != i && 206 != i) {
                ho.c("SourceFetcher", "downloadfailed, http.response.code:" + i);
                rr.this.a("2", i, "error_http_code", this.c);
                return false;
            }
            rr.this.b(j);
            if (rr.this.d.a()) {
                rr.this.a(j);
            }
            long f = rr.this.d.f();
            if (j > f) {
                ho.c("SourceFetcher", "fileSize is not under limit %s", String.valueOf(f));
                rr.this.a("2", i, "fileSize_exceed_limit", this.c);
                return false;
            }
            File file = new File(b());
            BufferedInputStream bufferedInputStream = null;
            try {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream, 8192);
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), 8192);
                    try {
                        byte[] bArr = new byte[8192];
                        int i2 = 0;
                        while (true) {
                            int read = bufferedInputStream2.read(bArr);
                            if (read <= 0) {
                                this.d = System.currentTimeMillis();
                                bufferedOutputStream.flush();
                                rr.this.d.b(i2);
                                rr.this.d.b(Long.valueOf(System.currentTimeMillis()));
                                String c = rr.this.d.c();
                                if (!rr.this.d.h() || com.huawei.openalliance.ad.utils.ae.a(c, file)) {
                                    if (!com.huawei.openalliance.ad.utils.ae.a(rr.this.f, file, this.b, rs.a(rr.this.d), rr.this.h)) {
                                        rr.this.a("2", i, "rename_file_fail", this.c);
                                        rr.this.a(bufferedInputStream2, bufferedOutputStream, file);
                                        return false;
                                    }
                                    rr.this.a(bufferedInputStream2, bufferedOutputStream, file);
                                    return true;
                                }
                                rr.this.a("3", this.c);
                                ho.c("SourceFetcher", "downloadUrlToStream error, downloaded file hashcode is not right");
                                com.huawei.openalliance.ad.utils.ae.a(file);
                                rr.this.a(bufferedInputStream2, bufferedOutputStream, file);
                                return false;
                            }
                            i2 += read;
                            if (i2 > f) {
                                rr.this.a("3", this.c);
                                ho.c("SourceFetcher", "downloadUrlToStream error, downloaded size " + i2 + ", over the limit");
                                com.huawei.openalliance.ad.utils.ae.a(file);
                                rr.this.a(bufferedInputStream2, bufferedOutputStream, file);
                                return false;
                            }
                            bufferedOutputStream.write(bArr, 0, read);
                        }
                    } catch (IOException e) {
                        iOException = e;
                        bufferedInputStream = bufferedInputStream2;
                        try {
                            ho.c("SourceFetcher", "download file encounters IOException, ex:" + iOException.getClass().getSimpleName());
                            rr.this.a("2", i, iOException.getClass().getSimpleName(), this.c);
                            rr.this.a(bufferedInputStream, bufferedOutputStream, file);
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            bufferedInputStream2 = bufferedInputStream;
                            bufferedInputStream = bufferedInputStream2;
                            rr.this.a(bufferedInputStream, bufferedOutputStream, file);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedInputStream = bufferedInputStream2;
                        rr.this.a(bufferedInputStream, bufferedOutputStream, file);
                        throw th;
                    }
                } catch (IOException e2) {
                    iOException = e2;
                    bufferedOutputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedOutputStream = null;
                }
            } catch (IOException e3) {
                iOException = e3;
                bufferedOutputStream = null;
            } catch (Throwable th4) {
                th = th4;
                bufferedOutputStream = null;
                rr.this.a(bufferedInputStream, bufferedOutputStream, file);
                throw th;
            }
        }

        @Override // com.huawei.openalliance.ad.lb
        public long a() {
            return this.d;
        }

        private String b() {
            StringBuilder sb;
            String str;
            if (dk.i(this.b)) {
                sb = new StringBuilder();
                sb.append(rr.this.b);
                str = dk.k(this.b);
            } else {
                sb = new StringBuilder();
                str = this.b;
            }
            sb.append(str);
            sb.append(".bak");
            return sb.toString();
        }

        a(String str, long j) {
            this.b = str;
            this.c = j;
        }
    }

    private ru a(String str, boolean z) {
        ru ruVar = new ru();
        String c2 = dk.i(str) ? this.g.c(str) : str;
        if (com.huawei.openalliance.ad.utils.ae.c(this.f, c2, this.h)) {
            ruVar.a(str);
            ruVar.b(c2);
        }
        if (ho.a()) {
            ho.a("SourceFetcher", "filePath: %s, localFilePath: %s", com.huawei.openalliance.ad.utils.dl.a(str), com.huawei.openalliance.ad.utils.dl.a(c2));
        }
        ruVar.a(z);
        return ruVar;
    }

    private ru a(String str, String str2) {
        ho.b("SourceFetcher", "download file from %s local", str2);
        if (dk.i(str)) {
            dk a2 = dh.a(this.f, str2);
            this.g = a2;
            this.h = str2;
            rs.a(this.f, str, a2, this.d, str2);
        } else {
            com.huawei.openalliance.ad.utils.ae.c(new File(str));
        }
        return a(str, false);
    }

    private ru a(rt rtVar) {
        String h;
        StringBuilder sb;
        if (rtVar == null) {
            ho.c("SourceFetcher", "downloadFile - data is null");
            return null;
        }
        String g = rtVar.g();
        if (TextUtils.isEmpty(g)) {
            ho.b("SourceFetcher", "downloadFile - file url is null");
            return null;
        }
        if (ho.a()) {
            ho.a("SourceFetcher", "download file: %s cacheType: %s", com.huawei.openalliance.ad.utils.dl.a(g), this.h);
        }
        if (!rtVar.k()) {
            h = com.huawei.openalliance.ad.utils.ae.h(new File(this.b + rs.a(g)));
        } else if (this.d.q()) {
            h = dk.d(g) + com.huawei.openalliance.ad.utils.ae.e(g);
        } else {
            h = dk.d(g);
        }
        if (!this.d.r() && com.huawei.openalliance.ad.utils.ae.c(this.f, h, "normal")) {
            return a(h, "normal");
        }
        if (!this.d.r() && com.huawei.openalliance.ad.utils.ae.c(this.f, h, Constants.TPLATE_CACHE)) {
            return a(h, Constants.TPLATE_CACHE);
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (a(g, h, currentTimeMillis)) {
                ho.b("SourceFetcher", "download file from network");
                a("5", currentTimeMillis);
                return a(h, true);
            }
        } catch (RuntimeException e) {
            e = e;
            com.huawei.openalliance.ad.utils.ae.a(this.f, h, this.h);
            sb = new StringBuilder("downloadFile RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("SourceFetcher", sb.toString());
            return null;
        } catch (Exception e2) {
            e = e2;
            com.huawei.openalliance.ad.utils.ae.a(this.f, h, this.h);
            sb = new StringBuilder("downloadFile Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("SourceFetcher", sb.toString());
            return null;
        }
        return null;
    }

    public rr(Context context, rt rtVar) {
        this.h = "normal";
        if (!TextUtils.isEmpty(rtVar.g())) {
            rtVar.c(com.huawei.openalliance.ad.utils.az.b(context, rtVar.g()));
        }
        this.b = (rtVar.k() ? com.huawei.openalliance.ad.utils.cw.f(context) : com.huawei.openalliance.ad.utils.cw.c(context)) + File.separator + Constants.PPS_ROOT_PATH + File.separator;
        if (!com.huawei.openalliance.ad.utils.cz.b(rtVar.b())) {
            this.b += rtVar.b() + File.separator;
        }
        File file = new File(this.b);
        if (!file.exists() && !com.huawei.openalliance.ad.utils.ae.g(file)) {
            ho.c("SourceFetcher", "SourceFetcher mkdirs failed");
        }
        this.f7514a = (rq) new e.a(context).a(true).b(false).a(rtVar.d()).b(rtVar.e()).a().a(rq.class);
        this.d = rtVar;
        if (rtVar.p() != null) {
            this.g = dh.a(context, rtVar.p());
            this.h = rtVar.p();
        } else {
            rtVar.d("normal");
            this.g = dh.a(context, "normal");
        }
        this.e = new com.huawei.openalliance.ad.analysis.g(context);
        this.f = context.getApplicationContext();
    }
}
