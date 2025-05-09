package defpackage;

/* loaded from: classes3.dex */
public class bjf {
    private String b;
    private int d;

    protected bjf(a aVar) {
        this.d = aVar.c;
        this.b = aVar.d;
    }

    public static class a {
        private int c = 0;
        private String d;

        public a e(int i) {
            this.c = i;
            return this;
        }

        public a a(String str) {
            this.d = str;
            return this;
        }

        public bjf a() {
            return new bjf(this);
        }
    }

    public int e() {
        return this.d;
    }

    public String d() {
        return this.b;
    }
}
