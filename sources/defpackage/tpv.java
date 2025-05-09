package defpackage;

import java.io.File;

/* loaded from: classes9.dex */
public class tpv {

    /* renamed from: a, reason: collision with root package name */
    private String f17331a;
    private File b;

    protected tpv(a aVar) {
        this.b = aVar.c;
        this.f17331a = aVar.b;
    }

    public static class a {
        private String b;
        private File c;

        public a c(File file) {
            this.c = file;
            return this;
        }

        public tpv d() {
            return new tpv(this);
        }
    }

    public File b() {
        return this.b;
    }

    public String a() {
        return this.f17331a;
    }
}
