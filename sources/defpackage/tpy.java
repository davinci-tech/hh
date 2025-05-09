package defpackage;

import java.io.File;

/* loaded from: classes9.dex */
public class tpy {

    /* renamed from: a, reason: collision with root package name */
    private File f17338a;
    private boolean b;
    private String d;
    private byte[] e;

    protected tpy(b bVar) {
        this.d = bVar.d;
        this.e = bVar.f17339a;
        this.f17338a = bVar.c;
        this.b = bVar.b;
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private byte[] f17339a;
        private File c;
        String d;
        private int e = 0;
        private boolean b = true;

        public b a(byte[] bArr) {
            int i = this.e;
            if (i == 0 || i == 1) {
                this.f17339a = bArr == null ? null : (byte[]) bArr.clone();
                this.e = 1;
                return this;
            }
            throw new tnx(5);
        }

        public b d(File file) {
            int i = this.e;
            if (i == 0 || i == 2) {
                this.c = file;
                this.e = 2;
                return this;
            }
            throw new tnx(5);
        }

        public tpy a() {
            return new tpy(this);
        }
    }

    public byte[] a() {
        byte[] bArr = this.e;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public File b() {
        return this.f17338a;
    }

    public String c() {
        return this.d;
    }

    public int d() {
        if (this.e != null) {
            return 1;
        }
        return this.f17338a != null ? 2 : 0;
    }

    public boolean e() {
        return this.b;
    }
}
