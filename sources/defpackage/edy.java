package defpackage;

import android.view.View;

/* loaded from: classes3.dex */
public class edy {

    /* renamed from: a, reason: collision with root package name */
    private int f11972a;
    private int c;
    private View.OnClickListener d;
    private int e;

    public int e() {
        return this.f11972a;
    }

    public int a() {
        return this.c;
    }

    public int c() {
        return this.e;
    }

    public View.OnClickListener agO_() {
        return this.d;
    }

    private edy(d dVar) {
        this.f11972a = dVar.e;
        this.c = dVar.c;
        this.e = dVar.b;
        this.d = dVar.d;
    }

    /* loaded from: classes8.dex */
    public static class d {
        private int b;
        private int c;
        private View.OnClickListener d;
        private int e;

        public d e(int i) {
            this.e = i;
            return this;
        }

        public d d(int i) {
            this.c = i;
            return this;
        }

        public d a(int i) {
            this.b = i;
            return this;
        }

        public d agQ_(View.OnClickListener onClickListener) {
            this.d = onClickListener;
            return this;
        }

        public edy b() {
            return new edy(this);
        }
    }
}
