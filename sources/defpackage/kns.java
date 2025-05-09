package defpackage;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.huawei.hwfabengine.FloatingActionButtonAnimationListener;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class kns extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    private Drawable f14439a;
    private int b;
    private int c;
    private boolean d;
    private boolean e;
    private boolean f;
    private FloatingActionButtonAnimationListener g;
    private Drawable.Callback h;
    private HashMap<String, Drawable> i;
    private knr j;
    private ValueAnimator.AnimatorUpdateListener k;
    private final FloatingActionButtonAnimationListener l;
    private final FloatingActionButtonAnimationListener m;

    private boolean h() {
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    private void bPu_(Drawable drawable) {
        this.f14439a = drawable;
        if (drawable == null) {
            Log.i("FloatingActionButtonDrawable", "The drawable is null!");
            return;
        }
        if (this.b == 0 || this.c == 0) {
            this.b = drawable.getIntrinsicHeight();
            this.c = this.f14439a.getIntrinsicWidth();
        }
        this.f14439a.setBounds(0, 0, this.c, this.b);
        this.f14439a.setCallback(this.h);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f14439a;
        if (drawable != null) {
            drawable.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f14439a.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.c;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        super.getOutline(outline);
    }

    public void e() {
        if (!h() && this.d) {
            if (this.j == null) {
                Log.e("FloatingActionButtonDrawable", "The paramaters are null, start down animation failed!");
                return;
            }
            this.e = false;
            this.d = false;
            this.f = false;
            Log.i("FloatingActionButtonDrawable", "It starts down animation!");
            bPu_(this.i.get(this.j.c()));
            d(this.l, null);
            a();
        }
    }

    public void b() {
        if (h()) {
            return;
        }
        this.f = true;
        if (this.e) {
            c();
        }
    }

    private void c() {
        if (this.j == null || this.g == null) {
            Log.e("FloatingActionButtonDrawable", "The paramaters are null, start up animation failed!");
            return;
        }
        Log.i("FloatingActionButtonDrawable", "It starts up animation!");
        this.d = false;
        this.e = false;
        bPu_(this.i.get(this.j.f()));
        d(this.g, this.m);
        g();
    }

    private void d(FloatingActionButtonAnimationListener floatingActionButtonAnimationListener, FloatingActionButtonAnimationListener floatingActionButtonAnimationListener2) {
        knt.bPw_(this.f14439a, floatingActionButtonAnimationListener, floatingActionButtonAnimationListener2);
    }

    public void b(FloatingActionButtonAnimationListener floatingActionButtonAnimationListener) {
        this.g = floatingActionButtonAnimationListener;
    }

    public FloatingActionButtonAnimationListener d() {
        return this.g;
    }

    private void a() {
        knr knrVar = this.j;
        if (knrVar != null) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(knrVar.d(), this.j.b());
            ofFloat.setDuration(this.j.e());
            ofFloat.setInterpolator(this.j.bPt_());
            ofFloat.addUpdateListener(this.k);
            ofFloat.start();
        }
    }

    private void g() {
        knr knrVar = this.j;
        if (knrVar != null) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(knrVar.b(), this.j.d());
            ofFloat.setDuration(this.j.e());
            ofFloat.setInterpolator(this.j.bPt_());
            ofFloat.addUpdateListener(this.k);
            ofFloat.start();
        }
    }
}
