package defpackage;

import health.compact.a.LogUtil;
import java.io.File;

/* loaded from: classes7.dex */
public class spn {

    /* renamed from: a, reason: collision with root package name */
    private boolean f17204a;
    private File b;
    private String c;
    private int d;
    private byte[] e;

    protected spn(b bVar) {
        this.c = bVar.c;
        this.e = bVar.f17205a;
        this.b = bVar.e;
        this.f17204a = bVar.d;
        this.d = bVar.b;
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private byte[] f17205a;
        String c;
        private File e;
        private int j = 0;
        private boolean d = true;
        private int b = 1;

        public b c(byte[] bArr) {
            int i = this.j;
            if (i == 0 || i == 1) {
                this.f17205a = bArr;
                this.j = 1;
            } else {
                LogUtil.a("P2pMessage", "Data setPayload type: ", Integer.valueOf(i));
            }
            return this;
        }

        public b a(File file) {
            int i = this.j;
            if (i == 0 || i == 2) {
                this.e = file;
                this.j = 2;
            } else {
                LogUtil.a("P2pMessage", "File setPayload type: ", Integer.valueOf(i));
            }
            return this;
        }

        public b e(String str) {
            this.c = str;
            return this;
        }

        public b d(boolean z) {
            this.d = z;
            return this;
        }

        public b c(int i) {
            this.b = i;
            return this;
        }

        public spn e() {
            return new spn(this);
        }
    }

    public byte[] b() {
        return this.e;
    }

    public File a() {
        return this.b;
    }

    public String e() {
        return this.c;
    }

    public int d() {
        if (this.e != null) {
            return 1;
        }
        return this.b != null ? 2 : 0;
    }

    public boolean g() {
        return this.f17204a;
    }

    public int c() {
        return this.d;
    }
}
