package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.col.p0003sl.jv;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;

/* loaded from: classes2.dex */
public final class u implements jv.a {

    /* renamed from: a, reason: collision with root package name */
    a f1362a;
    private final Context b;
    private RandomAccessFile c;
    private kc d;
    private String e;

    @Override // com.amap.api.col.3sl.jv.a
    public final void onStop() {
    }

    public u(Context context, a aVar) {
        this.b = context.getApplicationContext();
        this.f1362a = aVar;
        this.d = new kc(new b(aVar));
        this.e = aVar.c();
    }

    public final void a() {
        kc kcVar;
        if (z.f1381a == null || hw.a(z.f1381a, dv.a()).f1161a == hw.c.SuccessCode) {
            try {
                if (!b() || (kcVar = this.d) == null) {
                    return;
                }
                kcVar.a(this);
            } catch (Throwable th) {
                iv.c(th, "AuthTaskDownload", "startDownload()");
            }
        }
    }

    private boolean b() {
        c e = this.f1362a.e();
        return (e != null && e.c() && dl.a(this.b, e.a(), e.b(), "").equalsIgnoreCase(this.f1362a.b())) ? false : true;
    }

    @Override // com.amap.api.col.3sl.jv.a
    public final void onDownload(byte[] bArr, long j) {
        try {
            if (this.c == null) {
                File file = new File(this.e);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.c = new RandomAccessFile(file, "rw");
            }
            this.c.seek(j);
            this.c.write(bArr);
        } catch (Throwable th) {
            iv.c(th, "AuthTaskDownload", "onDownload()");
        }
    }

    @Override // com.amap.api.col.3sl.jv.a
    public final void onFinish() {
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = this.c;
        } catch (Throwable th) {
            iv.c(th, "AuthTaskDownload", "onFinish()");
        }
        if (randomAccessFile == null) {
            return;
        }
        try {
            randomAccessFile.close();
        } catch (Throwable th2) {
            iv.c(th2, "AuthTaskDownload", "onFinish3");
        }
        String b2 = this.f1362a.b();
        String a2 = hv.a(this.e);
        if (a2 != null && b2.equalsIgnoreCase(a2)) {
            String d2 = this.f1362a.d();
            try {
                bn bnVar = new bn();
                File file = new File(this.e);
                bnVar.a(file, new File(d2), -1L, bt.a(file), null);
                c e = this.f1362a.e();
                if (e != null && e.c()) {
                    dl.a(this.b, e.a(), e.b(), (Object) a2);
                }
                new File(this.e).delete();
                return;
            } catch (Throwable th3) {
                iv.c(th3, "AuthTaskDownload", "onFinish1");
                return;
            }
        }
        try {
            new File(this.e).delete();
            return;
        } catch (Throwable th4) {
            iv.c(th4, "AuthTaskDownload", "onFinish");
            return;
        }
        iv.c(th, "AuthTaskDownload", "onFinish()");
    }

    @Override // com.amap.api.col.3sl.jv.a
    public final void onException(Throwable th) {
        try {
            RandomAccessFile randomAccessFile = this.c;
            if (randomAccessFile == null) {
                return;
            }
            randomAccessFile.close();
        } catch (Throwable th2) {
            iv.c(th2, "AuthTaskDownload", "onException()");
        }
    }

    static final class c {

        /* renamed from: a, reason: collision with root package name */
        protected String f1365a;
        protected String b;

        public c(String str, String str2) {
            this.f1365a = str;
            this.b = str2;
        }

        public final String a() {
            return this.f1365a;
        }

        public final String b() {
            return this.b;
        }

        public final boolean c() {
            return (TextUtils.isEmpty(this.f1365a) || TextUtils.isEmpty(this.b)) ? false : true;
        }
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        protected String f1363a;
        protected String b;
        protected String c;
        protected String d;
        protected String e;
        protected c f;

        public a(String str, String str2, String str3, String str4) {
            this.f1363a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4 + ".tmp";
            this.e = str4;
        }

        public final String a() {
            return this.f1363a;
        }

        public final String b() {
            return this.b;
        }

        public final String c() {
            return this.d;
        }

        public final String d() {
            return this.e;
        }

        public final void a(c cVar) {
            this.f = cVar;
        }

        public final c e() {
            return this.f;
        }
    }

    static final class d extends a {
        public d(String str, String str2, String str3, String str4) {
            super(str, str2, str3, str4);
        }

        public final void a(String str, String str2) {
            a(new c(str, str2));
        }
    }

    static final class b extends cz {

        /* renamed from: a, reason: collision with root package name */
        private final a f1364a;

        @Override // com.amap.api.col.p0003sl.cz, com.amap.api.col.p0003sl.ka
        public final Map<String, String> getParams() {
            return null;
        }

        @Override // com.amap.api.col.p0003sl.ka
        public final Map<String, String> getRequestHead() {
            return null;
        }

        @Override // com.amap.api.col.p0003sl.ka
        public final boolean isSupportIPV6() {
            return false;
        }

        b(a aVar) {
            this.f1364a = aVar;
        }

        @Override // com.amap.api.col.p0003sl.ka
        public final String getURL() {
            a aVar = this.f1364a;
            if (aVar != null) {
                return aVar.a();
            }
            return null;
        }

        @Override // com.amap.api.col.p0003sl.ka
        public final String getIPV6URL() {
            return getURL();
        }
    }
}
