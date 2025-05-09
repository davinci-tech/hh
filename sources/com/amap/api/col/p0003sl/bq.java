package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.col.p0003sl.bw;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.col.p0003sl.jv;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.MapsInitializer;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes2.dex */
public final class bq implements jv.a {

    /* renamed from: a, reason: collision with root package name */
    br f925a;
    long d;
    bl f;
    a h;
    private Context i;
    private bw j;
    private String k;
    private kc l;
    private bm m;
    long b = 0;
    long c = 0;
    boolean e = true;
    long g = 0;
    private boolean n = false;

    public interface a {
        void c();
    }

    public bq(br brVar, String str, Context context, bw bwVar) throws IOException {
        this.f925a = null;
        this.f = bl.a(context.getApplicationContext());
        this.f925a = brVar;
        this.i = context;
        this.k = str;
        this.j = bwVar;
        d();
    }

    private void c() throws IOException {
        bx bxVar = new bx(this.k);
        bxVar.setConnectionTimeout(30000);
        bxVar.setSoTimeout(30000);
        this.l = new kc(bxVar, this.b, this.c, MapsInitializer.getProtocol() == 2);
        this.m = new bm(this.f925a.b() + File.separator + this.f925a.c(), this.b);
    }

    private void d() {
        File file = new File(this.f925a.b() + this.f925a.c());
        if (file.exists()) {
            this.e = false;
            this.b = file.length();
            try {
                long g = g();
                this.d = g;
                this.c = g;
                return;
            } catch (IOException unused) {
                bw bwVar = this.j;
                if (bwVar != null) {
                    bwVar.a(bw.a.file_io_exception);
                    return;
                }
                return;
            }
        }
        this.b = 0L;
        this.c = 0L;
    }

    public final void a() {
        try {
            if (dv.d(this.i)) {
                f();
                if (ho.f1133a != 1) {
                    bw bwVar = this.j;
                    if (bwVar != null) {
                        bwVar.a(bw.a.amap_exception);
                        return;
                    }
                    return;
                }
                if (!e()) {
                    this.e = true;
                }
                if (this.e) {
                    long g = g();
                    this.d = g;
                    if (g != -1 && g != -2) {
                        this.c = g;
                    }
                    this.b = 0L;
                }
                bw bwVar2 = this.j;
                if (bwVar2 != null) {
                    bwVar2.m();
                }
                if (this.b >= this.c) {
                    onFinish();
                    return;
                } else {
                    c();
                    this.l.a(this);
                    return;
                }
            }
            bw bwVar3 = this.j;
            if (bwVar3 != null) {
                bwVar3.a(bw.a.network_exception);
            }
        } catch (AMapException e) {
            iv.c(e, "SiteFileFetch", "download");
            bw bwVar4 = this.j;
            if (bwVar4 != null) {
                bwVar4.a(bw.a.amap_exception);
            }
        } catch (IOException unused) {
            bw bwVar5 = this.j;
            if (bwVar5 != null) {
                bwVar5.a(bw.a.file_io_exception);
            }
        }
    }

    private boolean e() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f925a.b());
        sb.append(File.separator);
        sb.append(this.f925a.c());
        return new File(sb.toString()).length() >= 10;
    }

    private void f() throws AMapException {
        if (ho.f1133a != 1) {
            for (int i = 0; i < 3; i++) {
                try {
                    ho.a(this.i, dv.a(), "", (Map<String, String>) null);
                } catch (Throwable th) {
                    iv.c(th, "SiteFileFetch", "authOffLineDownLoad");
                    th.printStackTrace();
                }
                if (ho.f1133a == 1) {
                    return;
                }
            }
        }
    }

    static final class b extends cz {

        /* renamed from: a, reason: collision with root package name */
        private final String f926a;

        @Override // com.amap.api.col.p0003sl.ka
        public final Map<String, String> getRequestHead() {
            return null;
        }

        @Override // com.amap.api.col.p0003sl.ka
        public final boolean isSupportIPV6() {
            return false;
        }

        public b(String str) {
            this.f926a = str;
        }

        @Override // com.amap.api.col.p0003sl.ka
        public final String getURL() {
            return this.f926a;
        }

        @Override // com.amap.api.col.p0003sl.ka
        public final String getIPV6URL() {
            return getURL();
        }
    }

    private long g() throws IOException {
        Map<String, String> map;
        if (hw.a(this.i, dv.a()).f1161a != hw.c.SuccessCode) {
            return -1L;
        }
        String a2 = this.f925a.a();
        try {
            jz.b();
            map = jz.d((ka) new b(a2), MapsInitializer.getProtocol() == 2);
        } catch (hm e) {
            e.printStackTrace();
            map = null;
        }
        int i = -1;
        if (map != null) {
            for (String str : map.keySet()) {
                if ("Content-Length".equalsIgnoreCase(str)) {
                    i = Integer.parseInt(map.get(str));
                }
            }
        }
        return i;
    }

    private void h() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f925a == null || currentTimeMillis - this.g <= 500) {
            return;
        }
        i();
        this.g = currentTimeMillis;
        a(this.b);
    }

    private void i() {
        this.f.a(this.f925a.e(), this.f925a.d(), this.d, this.b, this.c);
    }

    private void a(long j) {
        bw bwVar;
        long j2 = this.d;
        if (j2 <= 0 || (bwVar = this.j) == null) {
            return;
        }
        bwVar.a(j2, j);
        this.g = System.currentTimeMillis();
    }

    public final void b() {
        kc kcVar = this.l;
        if (kcVar != null) {
            kcVar.a();
        }
    }

    @Override // com.amap.api.col.3sl.jv.a
    public final void onStop() {
        if (this.n) {
            return;
        }
        bw bwVar = this.j;
        if (bwVar != null) {
            bwVar.o();
        }
        i();
    }

    @Override // com.amap.api.col.3sl.jv.a
    public final void onFinish() {
        h();
        bw bwVar = this.j;
        if (bwVar != null) {
            bwVar.n();
        }
        bm bmVar = this.m;
        if (bmVar != null) {
            bmVar.a();
        }
        a aVar = this.h;
        if (aVar != null) {
            aVar.c();
        }
    }

    @Override // com.amap.api.col.3sl.jv.a
    public final void onException(Throwable th) {
        bm bmVar;
        this.n = true;
        b();
        bw bwVar = this.j;
        if (bwVar != null) {
            bwVar.a(bw.a.network_exception);
        }
        if ((th instanceof IOException) || (bmVar = this.m) == null) {
            return;
        }
        bmVar.a();
    }

    @Override // com.amap.api.col.3sl.jv.a
    public final void onDownload(byte[] bArr, long j) {
        try {
            this.m.a(bArr);
            this.b = j;
            h();
        } catch (IOException e) {
            e.printStackTrace();
            iv.c(e, "fileAccessI", "fileAccessI.write(byte[] data)");
            bw bwVar = this.j;
            if (bwVar != null) {
                bwVar.a(bw.a.file_io_exception);
            }
            kc kcVar = this.l;
            if (kcVar != null) {
                kcVar.a();
            }
        }
    }

    public final void a(a aVar) {
        this.h = aVar;
    }
}
