package defpackage;

/* loaded from: classes8.dex */
public class yd {

    /* renamed from: a, reason: collision with root package name */
    private final xs f17754a;
    private final xx b;

    yd(xs xsVar, xx xxVar) {
        this.f17754a = xsVar;
        this.b = xxVar;
    }

    public void c(int i) {
        xs xsVar = this.f17754a;
        xsVar.d(new a(xsVar, this.b, i));
    }

    public void e(int i, int i2) {
        xs xsVar = this.f17754a;
        xsVar.d(new a(xsVar, this.b, i, i2));
    }

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final int f17755a;
        private final xs b;
        private final xx c;
        private final int e;

        a(xs xsVar, xx xxVar, int i) {
            this(xsVar, xxVar, i, 0);
        }

        a(xs xsVar, xx xxVar, int i, int i2) {
            this.b = xsVar;
            this.c = xxVar;
            this.e = i;
            this.f17755a = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i = this.f17755a;
            if (i != 0) {
                this.b.d(this.c.d(this.e, i));
            } else {
                this.b.d(this.c.c(this.e));
            }
        }
    }
}
