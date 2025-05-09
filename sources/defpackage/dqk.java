package defpackage;

import com.huawei.health.healthcloudconfig.listener.DownloadCallback;

/* loaded from: classes3.dex */
public class dqk {

    /* renamed from: a, reason: collision with root package name */
    private String f11784a;
    private dql b;
    private boolean c;
    private String d;
    private DownloadCallback e;

    private dqk(e eVar) {
        this.c = false;
        this.b = eVar.f11785a;
        this.f11784a = eVar.e;
        this.d = eVar.b;
        this.c = eVar.d;
        this.e = eVar.c;
    }

    public dql c() {
        return this.b;
    }

    public String e() {
        return this.f11784a;
    }

    public String b() {
        return this.d;
    }

    public boolean d() {
        return this.c;
    }

    public DownloadCallback a() {
        return this.e;
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private dql f11785a;
        private String b;
        private DownloadCallback c;
        private boolean d;
        private String e;

        public e e(dql dqlVar) {
            this.f11785a = dqlVar;
            return this;
        }

        public e d(String str) {
            this.e = str;
            return this;
        }

        public e e(String str) {
            this.b = str;
            return this;
        }

        public e d(boolean z) {
            this.d = z;
            return this;
        }

        public e a(DownloadCallback downloadCallback) {
            this.c = downloadCallback;
            return this;
        }

        public dqk a() {
            return new dqk(this);
        }
    }
}
