package defpackage;

import android.text.SpannableString;
import android.view.View;

/* loaded from: classes3.dex */
public class eea {

    /* renamed from: a, reason: collision with root package name */
    private d f11974a;
    private View.OnClickListener b;
    private float c;
    private float d;
    private d e;
    private boolean g;
    private final b[] h = new b[3];
    private a i;

    /* loaded from: classes8.dex */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public String f11975a;
        public String c;
        public boolean d;
        public String e;
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public SpannableString f11976a;
        public String b;
        public int[] c;
        public int[] d;
        public View.OnClickListener e;
        public int f;
        public int g;
        public int h;
        public int i;
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        public String f11977a;
        public View.OnClickListener b;
        public int c;
        public String d;
        public String e;
    }

    public float b() {
        return this.c;
    }

    public void c(float f) {
        this.c = f;
    }

    public float e() {
        return this.d;
    }

    public d c() {
        return this.e;
    }

    public void b(d dVar) {
        this.e = dVar;
    }

    public d d() {
        return this.f11974a;
    }

    public void d(d dVar) {
        this.f11974a = dVar;
    }

    public b a(int i) {
        if (i > 2) {
            return new b();
        }
        return this.h[i];
    }

    public void e(int i, b bVar) {
        this.h[i] = bVar;
    }

    public void e(float f) {
        this.d = f;
    }

    public void b(boolean z) {
        this.g = z;
    }

    public boolean i() {
        return this.g;
    }

    public View.OnClickListener agJ_() {
        return this.b;
    }

    public void agK_(View.OnClickListener onClickListener) {
        this.b = onClickListener;
    }

    public a j() {
        return this.i;
    }

    public void a(a aVar) {
        this.i = aVar;
    }

    public String toString() {
        return "SectionActiveThreeCircleBean{, mDistance=" + this.c + ", mClimbs=" + this.d + ", mIsSupportFloor=" + this.g + '}';
    }
}
