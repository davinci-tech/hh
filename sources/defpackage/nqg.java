package defpackage;

import android.graphics.drawable.Drawable;
import com.huawei.ui.commonui.R$color;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nqg {

    /* renamed from: a, reason: collision with root package name */
    private Drawable f15440a;
    private float b;
    private int c;
    private Drawable d;
    private final boolean e;
    private int f;
    private int g;
    private int h;
    private final List<String> i = new ArrayList();
    private final List<Integer> j = new ArrayList();
    private float k;
    private float l;
    private String m;
    private float n;

    public nqg(boolean z) {
        this.e = z;
        if (z) {
            this.c = R$color.circle_scale_mini_cursor;
        }
        this.f = R$color.circle_scale_ring_start;
        this.h = R$color.circle_scale_ring_center;
        this.g = R$color.circle_scale_ring_end;
    }

    public boolean k() {
        return this.e;
    }

    public int c() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public int i() {
        return this.f;
    }

    public int b() {
        return this.h;
    }

    public int g() {
        return this.g;
    }

    public void c(int i) {
        this.f = i;
        this.h = i;
        this.g = i;
    }

    public void e(int i, int i2, int i3) {
        this.f = i;
        this.h = i2;
        this.g = i3;
    }

    public float a() {
        return this.b;
    }

    public void c(float f) {
        this.b = f;
    }

    public float o() {
        return this.n;
    }

    public float f() {
        return this.l;
    }

    public void b(float f, float f2) {
        this.n = f;
        this.l = f2;
    }

    public String n() {
        return this.m;
    }

    public void d(String str) {
        this.m = str;
    }

    public List<String> h() {
        return this.i;
    }

    public void e(List<String> list) {
        this.i.clear();
        this.i.addAll(list);
    }

    public List<Integer> j() {
        return this.j;
    }

    public void a(List<Integer> list) {
        this.j.clear();
        this.j.addAll(list);
    }

    public float m() {
        return this.k;
    }

    public void a(float f) {
        this.k = f;
    }

    public Drawable cFy_() {
        return this.f15440a;
    }

    public void cFA_(Drawable drawable) {
        this.f15440a = drawable;
    }

    public Drawable cFz_() {
        return this.d;
    }

    public void cFB_(Drawable drawable) {
        this.d = drawable;
    }

    public String toString() {
        return "CircleScaleRulerData{mIsMini=" + this.e + ", mMiniCursorColor=" + this.c + ", mRingStartColor=" + this.f + ", mRingCentreColor=" + this.h + ", mRingEndColor=" + this.g + ", mCursorAngle=" + this.b + ", mScopeStartAngle=" + this.n + ", mScopeEndAngle=" + this.l + ", mScopeText=" + this.m + ", mRulerTextList=" + this.i + ", mRulerHighlightIndexList=" + this.j + ", mZoomScale=" + this.k + ", mBackground=" + this.f15440a + ", mCursor=" + this.d + '}';
    }
}
