package defpackage;

import android.app.Application;
import android.content.Context;

/* loaded from: classes6.dex */
public class mca {

    /* renamed from: a, reason: collision with root package name */
    private final Context f14876a;
    private final String b;
    private final String c;
    private final Application d;
    private final String e;

    private mca(e eVar) {
        this.d = eVar.f14877a;
        this.f14876a = eVar.e;
        this.c = eVar.b;
        this.e = eVar.c;
        this.b = eVar.d;
    }

    public Context e() {
        Application application = this.d;
        return application != null ? application : this.f14876a;
    }

    public String b() {
        return this.c;
    }

    public String a() {
        return this.e;
    }

    public String c() {
        return this.b;
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private Application f14877a;
        private String b;
        private String c;
        private String d;
        private Context e;

        public e cfI_(Application application) {
            this.f14877a = application;
            return this;
        }

        public e e(String str) {
            this.b = str;
            return this;
        }

        public e d(String str) {
            this.c = str;
            return this;
        }

        public e c(String str) {
            this.d = str;
            return this;
        }

        public mca d() {
            return new mca(this);
        }
    }
}
