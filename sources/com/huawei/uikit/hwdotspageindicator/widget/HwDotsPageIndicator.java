package com.huawei.uikit.hwdotspageindicator.widget;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicatorAnimation;
import com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicatorInteractor;
import com.huawei.uikit.hwdotspageindicator.widget.bzrwd;
import com.huawei.uikit.hwviewpager.widget.HwPagerAdapter;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import defpackage.slb;
import defpackage.slg;
import defpackage.sll;
import defpackage.sms;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class HwDotsPageIndicator extends bzrwd implements HwViewPager.OnPageChangeListener, View.OnClickListener, HwDotsPageIndicatorAnimation.AnimationUpdateListener {
    private int aa;
    private int ab;
    private h ac;
    private awsks ad;
    private int ae;
    private RectF af;
    private RectF ag;
    private int ah;
    private int ai;
    private int aj;
    private int ak;
    private RectF al;
    private RectF am;
    private RectF an;
    private int ao;
    private int ap;
    private int aq;
    private float ar;
    private final Runnable as;
    private float at;
    private int au;
    private int av;
    private int aw;
    private int ax;
    private int ay;
    private int az;
    protected boolean b;
    private int ba;
    private int bb;
    private float bc;
    private int bd;
    private boolean be;
    private float bf;
    private float bg;
    private int bh;
    private boolean bi;
    private boolean bj;
    private long bk;
    private String bl;
    private float bm;
    private boolean bn;
    private boolean bo;
    private boolean bp;
    private int bq;
    private boolean br;
    private boolean bs;
    private boolean bt;
    private boolean bu;
    private HwViewPager.OnPageChangeListener bv;
    private boolean bw;
    private Paint bx;
    private Paint by;
    private boolean bz;
    private boolean ca;
    private Handler cb;
    private Paint cd;
    private boolean cg;
    protected HwViewPager e;
    private Paint k;
    private boolean l;
    private int m;
    private Paint.FontMetrics n;
    private boolean o;
    private HwDotsPageIndicatorInteractor.OnGestureListener p;
    private boolean q;
    private sll.e r;
    private int s;
    private HwDotsPageIndicatorInteractor.OnClickListener t;
    private float u;
    private int v;
    private HwDotsPageIndicatorInteractor.OnMouseOperationListener w;
    private float x;
    private bfscp y;
    private a z;

    class a implements Runnable {
        private a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HwDotsPageIndicator.this.e(false);
        }

        /* synthetic */ a(HwDotsPageIndicator hwDotsPageIndicator, i iVar) {
            this();
        }
    }

    enum awsks {
        DEFAULT,
        TARGET,
        SLIDE
    }

    class b extends HwDotsPageIndicatorAnimation.AnimationStateListener {
        final /* synthetic */ boolean b;

        b(boolean z) {
            this.b = z;
        }

        @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicatorAnimation.AnimationStateListener
        void b() {
            if (this.b) {
                HwDotsPageIndicator.this.r();
            }
        }
    }

    enum bfscp {
        COMMON,
        VISIBLE,
        MOUSE_ON_DOT
    }

    class c extends HwDotsPageIndicatorAnimation.AnimationStateListener {
        c() {
        }

        @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicatorAnimation.AnimationStateListener
        void a(float f) {
            if (HwDotsPageIndicator.this.w != null) {
                HwDotsPageIndicator.this.w.onFocusAnimationProgress(f);
            }
        }
    }

    class d extends HwDotsPageIndicatorAnimation.AnimationStateListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f10640a;
        final /* synthetic */ boolean b;
        final /* synthetic */ int c;
        final /* synthetic */ HwDotsPageIndicatorAnimation.AnimationStateListener d;
        final /* synthetic */ float e;

        d(float f, boolean z, int i, float f2, HwDotsPageIndicatorAnimation.AnimationStateListener animationStateListener) {
            this.e = f;
            this.b = z;
            this.c = i;
            this.f10640a = f2;
            this.d = animationStateListener;
        }

        @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicatorAnimation.AnimationStateListener
        void a(float f) {
            if (f < this.e || HwDotsPageIndicator.this.g()) {
                return;
            }
            HwDotsPageIndicator.this.c.h();
            HwDotsPageIndicatorAnimation.a e = new HwDotsPageIndicatorAnimation.a.e().c(this.b ? HwDotsPageIndicator.this.f10654a.q() : HwDotsPageIndicator.this.f10654a.p()).a(this.f10640a).e(HwDotsPageIndicator.this.f).d(sll.e(HwDotsPageIndicator.this.d, this.c)).d(HwDotsPageIndicator.this).c(this.d).e();
            HwDotsPageIndicator hwDotsPageIndicator = HwDotsPageIndicator.this;
            hwDotsPageIndicator.c.e(hwDotsPageIndicator.bo, e);
        }
    }

    class e extends DataSetObserver {
        e() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            HwDotsPageIndicator hwDotsPageIndicator = HwDotsPageIndicator.this;
            hwDotsPageIndicator.setPageCount(hwDotsPageIndicator.e.getAdapter().getCount());
        }
    }

    class f extends HwDotsPageIndicatorAnimation.AnimationStateListener {
        final /* synthetic */ boolean c;

        f(boolean z) {
            this.c = z;
        }

        @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicatorAnimation.AnimationStateListener
        void b() {
            HwDotsPageIndicator.this.r.a(HwDotsPageIndicator.this.bm);
            if (this.c && HwDotsPageIndicator.this.p != null) {
                HwDotsPageIndicator.this.p.onLongPress(2);
            }
            if (this.c || HwDotsPageIndicator.this.w == null) {
                return;
            }
            HwDotsPageIndicator.this.w.onMoveInHotZone(2);
        }
    }

    class g extends HwDotsPageIndicatorAnimation.AnimationStateListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f10643a;
        final /* synthetic */ float c;
        final /* synthetic */ float d;
        final /* synthetic */ float e;

        g(float f, boolean z, float f2, float f3) {
            this.e = f;
            this.f10643a = z;
            this.c = f2;
            this.d = f3;
        }

        @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicatorAnimation.AnimationStateListener
        void a(float f) {
            if (f <= this.e || HwDotsPageIndicator.this.g()) {
                return;
            }
            HwDotsPageIndicator.this.c.h();
            HwDotsPageIndicator hwDotsPageIndicator = HwDotsPageIndicator.this;
            boolean z = hwDotsPageIndicator.bo;
            float q = this.f10643a ? HwDotsPageIndicator.this.f10654a.q() : HwDotsPageIndicator.this.f10654a.p();
            float f2 = this.f10643a ? this.c : this.d;
            HwDotsPageIndicator hwDotsPageIndicator2 = HwDotsPageIndicator.this;
            hwDotsPageIndicator.a(z, q, f2, hwDotsPageIndicator2.f, hwDotsPageIndicator2.d);
        }
    }

    class h implements Runnable {
        private h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (HwDotsPageIndicator.this.p != null && HwDotsPageIndicator.this.bu) {
                HwDotsPageIndicator.this.p.onLongPress(1);
            }
            HwDotsPageIndicator.this.a(true);
        }

        /* synthetic */ h(HwDotsPageIndicator hwDotsPageIndicator, i iVar) {
            this();
        }
    }

    class i implements Runnable {
        i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HwViewPager hwViewPager = HwDotsPageIndicator.this.e;
            if (hwViewPager == null || hwViewPager.getAdapter() == null) {
                Log.w("HwDotsPageIndicator", "HwViewPager or adapter is illegal.");
                return;
            }
            HwPagerAdapter adapter = HwDotsPageIndicator.this.e.getAdapter();
            if (adapter.getCount() < 2) {
                Log.w("HwDotsPageIndicator", "Auto play but pager count is less than two.");
                return;
            }
            int currentItem = HwDotsPageIndicator.this.e.getCurrentItem();
            HwDotsPageIndicator.this.e.setCurrentItem((HwDotsPageIndicator.this.e.isSupportLoop() || currentItem < adapter.getCount() - 1) ? currentItem + 1 : 0, true);
            if (HwDotsPageIndicator.this.bt) {
                HwDotsPageIndicator.this.cb.postDelayed(HwDotsPageIndicator.this.as, HwDotsPageIndicator.this.bq);
            }
        }
    }

    public HwDotsPageIndicator(Context context) {
        this(context, null);
    }

    private void aa() {
        if (this.an == null) {
            return;
        }
        if (this.ag == null) {
            this.ag = new RectF();
        }
        int i2 = this.bh;
        if (i2 == 0) {
            this.ag = new RectF();
            return;
        }
        this.ag.left = this.q ? this.f10654a.h(i2) + (this.aa / 2.0f) : this.an.left;
        RectF rectF = this.ag;
        RectF rectF2 = this.an;
        rectF.top = rectF2.top;
        rectF.right = this.q ? rectF2.right : this.f10654a.h(this.bh) - (this.aa / 2.0f);
        this.ag.bottom = this.an.bottom;
    }

    private slg ab() {
        slg f2 = this.f10654a.f();
        f2.c(this.v / 2.0f);
        f2.t(this.ap);
        f2.d(this.f10654a.l(this.bh));
        f2.d(this.f10654a.i());
        f2.ecG_(this.al);
        f2.j(this.at - (this.v / 2.0f));
        f2.o(this.at + (this.v / 2.0f));
        f2.f(this.f10654a.f(this.bh));
        f2.h(this.f10654a.g(this.bh));
        return f2;
    }

    private void ac() {
        this.f10654a.d(this.q);
        this.f10654a.j(this.at - this.u);
        this.f10654a.o(this.at + this.u);
        slg slgVar = this.f10654a;
        slgVar.f(slgVar.h(this.bh));
        slg slgVar2 = this.f10654a;
        slgVar2.h(slgVar2.d(this.bh));
        this.f10654a.c(this.u);
        this.f10654a.m(this.u);
        this.f10654a.t(this.ao);
        this.f10654a.d(this.f10654a.i(this.bh));
        this.f10654a.d(this.at);
        this.f10654a.ecG_(this.am);
    }

    private void ad() {
        float paddingLeft = getPaddingLeft() + (((getWidth() - getPaddingRight()) - getScaledWidth()) / 2.0f) + this.ay;
        this.f10654a.b(paddingLeft);
        float f2 = paddingLeft - this.ay;
        this.al = new RectF(f2, this.at - (this.av / 2.0f), getScaledWidth() + f2, this.at + (this.av / 2.0f));
    }

    private boolean ae() {
        return this.y == bfscp.COMMON;
    }

    private boolean af() {
        return (!this.l || this.bu || this.bt) ? false : true;
    }

    private boolean ag() {
        return getLayoutDirection() == 1;
    }

    private void ah() {
        if (this.ac == null) {
            h hVar = new h(this, null);
            this.ac = hVar;
            postDelayed(hVar, 300L);
        }
    }

    private void ai() {
        a aVar = this.z;
        if (aVar != null) {
            removeCallbacks(aVar);
        }
    }

    private void ak() {
        String str;
        HwViewPager hwViewPager = this.e;
        boolean z = false;
        int currentItem = hwViewPager != null ? hwViewPager.getCurrentItem() : 0;
        this.bh = currentItem;
        this.f10654a.p(currentItem);
        if (this.l) {
            if (this.bj && j()) {
                z = true;
            }
            this.q = z;
            this.f10654a.d(z);
            return;
        }
        if (j()) {
            str = this.bd + "/" + (this.bh + 1);
        } else {
            str = (this.bh + 1) + "/" + this.bd;
        }
        this.bl = str;
    }

    private void am() {
        slg slgVar = this.f10654a;
        slgVar.f(slgVar.e(ae(), this.bh));
        slg slgVar2 = this.f10654a;
        slgVar2.h(slgVar2.a(ae(), this.bh));
        this.f10654a.j(this.at - (this.v / 2.0f));
        this.f10654a.o(this.at + (this.v / 2.0f));
        this.f10654a.a(false);
    }

    private int getDesiredWidth() {
        float f2 = this.ay;
        int i2 = this.aa;
        int i3 = this.bd - 1;
        return (int) ((f2 * 2.0f) + (i2 * i3) + (this.s * i3) + this.ak);
    }

    private int getDistanceProper() {
        int i2;
        int i3;
        if (ae()) {
            i2 = this.aa;
            i3 = this.s;
        } else {
            i2 = this.ab;
            i3 = this.v;
        }
        return i2 + i3;
    }

    private int getScaledWidth() {
        float f2 = this.ay;
        int i2 = this.ab;
        int i3 = this.bd - 1;
        return (int) ((f2 * 2.0f) + (i2 * i3) + (this.v * i3) + this.au);
    }

    private void m() {
        Handler handler = this.cb;
        if (handler != null) {
            handler.removeCallbacks(this.as);
        }
        this.cb = null;
    }

    private void setCurrentItemIndirect(int i2) {
        HwViewPager hwViewPager = this.e;
        if (hwViewPager == null || hwViewPager.getAdapter() == null || this.e.getAdapter().getCount() < 2 || i2 < 0 || i2 >= this.bd) {
            return;
        }
        this.e.setCurrentItem(i2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPageCount(int i2) {
        this.bd = i2;
        this.f10654a.r(i2);
        this.h.b(this.bd);
        if (this.b) {
            b();
            return;
        }
        k();
        requestLayout();
        invalidate();
    }

    private void settleToTarget(int i2) {
        this.r.a(this.bm);
        this.r.c(this.bm);
        this.r.d(true);
        setCurrentItemIndirect(i2);
        if (isHapticFeedbackEnabled()) {
            slb.ebC_(this, 7, 0);
        }
        e(1.0f, 3, i2);
    }

    private slg v() {
        slg f2 = this.f10654a.f();
        f2.c(this.u);
        f2.t(this.ao);
        f2.d(this.f10654a.i(this.bh));
        f2.d(this.f10654a.i());
        f2.ecG_(this.am);
        f2.j(this.at - this.u);
        f2.f(this.f10654a.h(this.bh));
        f2.h(this.f10654a.d(this.bh));
        f2.o(this.at + this.u);
        return f2;
    }

    private void x() {
        this.cb = new Handler();
    }

    private void y() {
        if (this.an == null) {
            return;
        }
        if (this.af == null) {
            this.af = new RectF();
        }
        int i2 = this.bh;
        if (i2 == this.bd - 1) {
            this.af = new RectF();
            return;
        }
        this.af.left = this.q ? this.an.left : this.f10654a.d(i2) + (this.aa / 2.0f);
        RectF rectF = this.af;
        RectF rectF2 = this.an;
        rectF.top = rectF2.top;
        rectF.right = this.q ? this.f10654a.d(this.bh) - (this.aa / 2.0f) : rectF2.right;
        this.af.bottom = this.an.bottom;
    }

    private void z() {
        Paint paint = new Paint(1);
        this.bx = paint;
        paint.setColor(this.ai);
        Paint paint2 = new Paint(1);
        this.by = paint2;
        paint2.setColor(this.ah);
        Paint paint3 = new Paint(1);
        this.k = paint3;
        paint3.setColor(this.ap);
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ void a(float f2, float f3, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener, HwDotsPageIndicatorAnimation.AnimationStateListener animationStateListener) {
        super.a(f2, f3, animationUpdateListener, animationStateListener);
    }

    public void a(int i2) {
        this.bt = true;
        this.bs = false;
        this.bq = i2;
        if (this.cb == null) {
            x();
        }
        this.cb.removeCallbacks(this.as);
        this.cb.postDelayed(this.as, i2);
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ void a(bzrwd.e eVar, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener) {
        super.a(eVar, animationUpdateListener);
    }

    protected void a(boolean z) {
        HwDotsPageIndicatorInteractor.OnMouseOperationListener onMouseOperationListener;
        HwDotsPageIndicatorInteractor.OnMouseOperationListener onMouseOperationListener2;
        HwDotsPageIndicatorInteractor.OnGestureListener onGestureListener;
        slg ab = ab();
        if (!this.bu) {
            this.f10654a = ab;
            invalidate();
            this.y = bfscp.VISIBLE;
            this.r.a(this.bm);
            if (z && (onGestureListener = this.p) != null) {
                onGestureListener.onLongPress(2);
            }
            if (z || (onMouseOperationListener2 = this.w) == null) {
                return;
            }
            onMouseOperationListener2.onMoveInHotZone(2);
            return;
        }
        HwDotsPageIndicatorAnimation hwDotsPageIndicatorAnimation = this.c;
        if (hwDotsPageIndicatorAnimation == null || hwDotsPageIndicatorAnimation.j()) {
            return;
        }
        this.c.o();
        i();
        f fVar = new f(z);
        if (!z && (onMouseOperationListener = this.w) != null) {
            onMouseOperationListener.onMoveInHotZone(1);
        }
        e(ab, z, this, fVar);
        this.y = bfscp.VISIBLE;
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ void a(float[] fArr, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener) {
        super.a(fArr, animationUpdateListener);
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ boolean a() {
        return super.a();
    }

    protected void b() {
        Log.i("HwDotsPageIndicator", "initWatchOptions");
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ void b(int i2, float f2, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener) {
        super.b(i2, f2, animationUpdateListener);
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ void b(String str) {
        super.b(str);
    }

    public void c() {
        this.bt = false;
        m();
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ void d(float f2, float f3, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener) {
        super.d(f2, f3, animationUpdateListener);
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ void d(float f2, float f3, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener, HwDotsPageIndicatorAnimation.AnimationStateListener animationStateListener) {
        super.d(f2, f3, animationUpdateListener, animationStateListener);
    }

    public boolean d() {
        return this.bz;
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ void e(slg slgVar, boolean z, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener, HwDotsPageIndicatorAnimation.AnimationStateListener animationStateListener) {
        super.e(slgVar, z, animationUpdateListener, animationStateListener);
    }

    protected void e(boolean z) {
        slg v = v();
        if (!this.bu) {
            this.f10654a = v;
            invalidate();
            this.y = bfscp.COMMON;
            this.m = 0;
            this.f10654a.q(-1);
            this.f10654a.h();
            return;
        }
        HwDotsPageIndicatorAnimation hwDotsPageIndicatorAnimation = this.c;
        if (hwDotsPageIndicatorAnimation == null || hwDotsPageIndicatorAnimation.b()) {
            return;
        }
        this.c.k();
        i();
        ecY_(z, v, this, this);
        this.y = bfscp.COMMON;
        this.m = 0;
    }

    protected boolean e() {
        return this.ca;
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ void ecW_(RectF rectF, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener) {
        super.ecW_(rectF, animationUpdateListener);
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ void ecX_(RectF rectF, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener) {
        super.ecX_(rectF, animationUpdateListener);
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ void ecY_(boolean z, slg slgVar, View view, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener) {
        super.ecY_(z, slgVar, view, animationUpdateListener);
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ void ecZ_(int i2, View view, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener) {
        super.ecZ_(i2, view, animationUpdateListener);
    }

    public void eck_(Canvas canvas, RectF rectF) {
        Paint paint = new Paint(1);
        paint.setStrokeWidth(this.ar);
        paint.setColor(this.aq);
        paint.setStyle(Paint.Style.STROKE);
        RectF rectF2 = new RectF();
        if (!this.l) {
            float measureText = this.cd.measureText(this.bl);
            rectF2.left = ((getWidth() - measureText) / 2.0f) - this.az;
            rectF2.top = this.ar / 2.0f;
            rectF2.right = ((getWidth() + measureText) / 2.0f) + this.az;
            rectF2.bottom = getHeight() - (this.ar / 2.0f);
            float height = (getHeight() - this.ar) / 2.0f;
            canvas.drawRoundRect(rectF2, height, height, paint);
            return;
        }
        RectF rectF3 = this.an;
        if (rectF3 != null) {
            if (rectF == null) {
                rectF = rectF3;
            }
            float f2 = rectF.left;
            float f3 = this.ar;
            float f4 = f3 / 2.0f;
            rectF2.left = f2 + f4;
            rectF2.top = rectF.top + f4;
            rectF2.right = rectF.right - f4;
            rectF2.bottom = rectF.bottom - f4;
            float f5 = (this.av - f3) / 2.0f;
            canvas.drawRoundRect(rectF2, f5, f5, paint);
        }
    }

    public void ecl_(Canvas canvas, int i2) {
        if (this.k == null || this.f10654a.ecI_() == null) {
            return;
        }
        float f2 = (this.f10654a.ecI_().bottom - this.f10654a.ecI_().top) / 2.0f;
        this.k.setColor(i2);
        canvas.drawRoundRect(this.f10654a.ecI_(), f2, f2, this.k);
    }

    protected void f() {
        int i2 = this.bh;
        if (i2 == 0) {
            if (this.e.isSupportLoop()) {
                a(this.bd - 1, false);
                b(this.bh, this.bd - 1);
                return;
            }
            return;
        }
        b(i2, i2 - 1);
        if (this.l && this.bu) {
            b(false);
        } else {
            this.e.prePage();
        }
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ boolean g() {
        return super.g();
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public TimeInterpolator getAccelerateInterpolator() {
        return super.getAccelerateInterpolator();
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public TimeInterpolator getAlphaInterpolator() {
        return super.getAlphaInterpolator();
    }

    protected int getBgFocusSelectedDotColor() {
        return this.aj;
    }

    protected int getBgFocusUnSelectedDotColor() {
        return this.ae;
    }

    public int getCurrentBgColor() {
        return this.f10654a.ad();
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public TimeInterpolator getDecelerateInterpolator() {
        return super.getDecelerateInterpolator();
    }

    protected int getDesiredHeight() {
        return this.av;
    }

    public int getDotColor() {
        return this.ai;
    }

    public int getFocusBoxColor() {
        return this.aq;
    }

    public int getFocusDotColor() {
        return this.ah;
    }

    protected RectF getHotZoneRectF() {
        return this.f10654a.ecI_();
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ float getMaxDiffFraction() {
        return super.getMaxDiffFraction();
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ TimeInterpolator getNavigationPointInterpolator() {
        return super.getNavigationPointInterpolator();
    }

    public int getNumTextColor() {
        return this.ax;
    }

    public int getPressedStateColor() {
        return this.ap;
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public TimeInterpolator getScaleInterpolator() {
        return super.getScaleInterpolator();
    }

    protected int getStartBgColor() {
        return this.ao;
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ TimeInterpolator getWatchAccelerateInterpolator() {
        return super.getWatchAccelerateInterpolator();
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ TimeInterpolator getWatchDecelerateInterpolator() {
        return super.getWatchDecelerateInterpolator();
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ TimeInterpolator getWatchDotTouchAndSlideInterpolator() {
        return super.getWatchDotTouchAndSlideInterpolator();
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ TimeInterpolator getWatchTouchFocusAccelerateInterpolator() {
        return super.getWatchTouchFocusAccelerateInterpolator();
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ TimeInterpolator getWatchTouchFocusDecelerateInterpolator() {
        return super.getWatchTouchFocusDecelerateInterpolator();
    }

    protected void h() {
        int i2 = this.bh;
        if (i2 == this.bd - 1) {
            if (this.e.isSupportLoop()) {
                a(0, false);
                b(this.bh, 0);
                return;
            }
            return;
        }
        b(i2, i2 + 1);
        if (this.l && this.bu) {
            b(true);
        } else {
            this.e.nextPage();
        }
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public /* bridge */ /* synthetic */ void i() {
        super.i();
    }

    protected boolean j() {
        String language = Locale.getDefault().getLanguage();
        return (language.contains("ar") || language.contains("fa") || language.contains("iw")) || (language.contains("ug") || language.contains(Constants.URDU_LANG)) || ag();
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicatorAnimation.AnimationUpdateListener
    public void onAnimationUpdate(slg slgVar) {
        if (slgVar == null) {
            return;
        }
        this.f10654a.ecG_(slgVar.ecI_());
        this.f10654a.t(slgVar.ad());
        this.f10654a.d(slgVar.g());
        this.f10654a.c(slgVar.l());
        this.f10654a.ecF_(slgVar.ecH_());
        invalidate();
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.be = true;
        if (this.bt) {
            a(this.bq);
        }
        if (this.cg) {
            this.bn = hasFocus();
            boolean hasWindowFocus = hasWindowFocus();
            this.bw = hasWindowFocus;
            setIndicatorFocusChanged(this.bn && hasWindowFocus);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (!this.l || this.bt || !this.bs) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (a() && g()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (this.y == bfscp.MOUSE_ON_DOT && this.f10654a.d() != -1) {
            HwDotsPageIndicatorInteractor.OnMouseOperationListener onMouseOperationListener = this.w;
            if (onMouseOperationListener != null) {
                onMouseOperationListener.onDotClick(this.bh, this.f10654a.d());
            }
            a(this.f10654a.d(), true);
            e(this.f10654a.d());
            this.f10654a.q(-1);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.be = false;
        if (this.bt) {
            m();
        }
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicatorAnimation.AnimationUpdateListener
    public void onDotCenterChanged(float[] fArr) {
        this.f10654a.d(fArr);
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (canvas == null || this.bd <= 0) {
            return;
        }
        if (!this.l) {
            eci_(canvas);
        } else {
            ece_(canvas);
            ecg_(canvas);
        }
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i2, Rect rect) {
        super.onFocusChanged(z, i2, rect);
        if (this.cg) {
            if (!z || this.y == bfscp.COMMON) {
                if (b(z, this.bw)) {
                    setIndicatorFocusChanged(z);
                }
                this.bn = z;
                invalidate();
            }
        }
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicatorAnimation.AnimationUpdateListener
    public void onFocusDotChanged(boolean z, float f2) {
        if (z) {
            if (this.bo) {
                this.f10654a.h(f2);
            } else {
                this.f10654a.f(f2);
            }
        } else if (this.bo) {
            if (!g()) {
                this.f10654a.f(f2);
            }
        } else if (!g()) {
            this.f10654a.h(f2);
        }
        invalidate();
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicatorAnimation.AnimationUpdateListener
    public void onFocusSingleScaled(RectF rectF) {
        if (rectF == null) {
            return;
        }
        this.f10654a.f(this.q ? rectF.right : rectF.left);
        this.f10654a.h(this.q ? rectF.left : rectF.right);
        this.f10654a.j(rectF.top);
        this.f10654a.o(rectF.bottom);
        invalidate();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (!e(motionEvent.getAction(), x, y)) {
            return super.onHoverEvent(motionEvent);
        }
        if (this.y != bfscp.COMMON) {
            a(x, y);
            return super.onHoverEvent(motionEvent);
        }
        if (this.bi) {
            ai();
            a(false);
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo != null) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClickable(false);
            accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
            Resources resources = getContext().getResources();
            int i2 = this.bh + 1;
            String quantityString = resources.getQuantityString(R.plurals._2130903536_res_0x7f0301f0, i2, Integer.valueOf(i2));
            Resources resources2 = getContext().getResources();
            int i3 = this.bd;
            String quantityString2 = resources2.getQuantityString(R.plurals._2130903539_res_0x7f0301f3, i3, Integer.valueOf(i3));
            accessibilityNodeInfo.setContentDescription(String.format(Locale.ROOT, getContext().getString(R.string._2130851442_res_0x7f023672), quantityString, quantityString2));
        }
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (keyEvent == null) {
            return false;
        }
        if (!this.bs && this.l) {
            return false;
        }
        if (this.bd <= 1 || !this.bz || (i2 != 21 && i2 != 22)) {
            return super.onKeyDown(i2, keyEvent);
        }
        if (!(i2 == 21 && this.q) && (i2 != 22 || this.q)) {
            f();
        } else {
            h();
        }
        return true;
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (z || this.o) {
            k();
            this.o = false;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        Pair<Integer, Integer> ecK_ = sll.ecK_(i2, i3, this.l ? sll.e(this.bd, getScaledWidth()) : View.MeasureSpec.getSize(i2), getDesiredHeight());
        setMeasuredDimension(((Integer) ecK_.first).intValue(), ((Integer) ecK_.second).intValue());
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
        HwViewPager.OnPageChangeListener onPageChangeListener = this.bv;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i2);
        }
        d(i2);
        this.m = i2;
        if (i2 == 1 && this.y == bfscp.COMMON) {
            i();
            HwDotsPageIndicatorAnimation hwDotsPageIndicatorAnimation = this.c;
            if (hwDotsPageIndicatorAnimation != null && (hwDotsPageIndicatorAnimation.a() || this.c.d())) {
                this.c.i();
                this.c.h();
                this.ad = awsks.DEFAULT;
            }
        }
        if (this.m != 0) {
            o();
        }
        if (this.m == 0) {
            HwDotsPageIndicatorAnimation hwDotsPageIndicatorAnimation2 = this.c;
            boolean z = hwDotsPageIndicatorAnimation2 != null && (hwDotsPageIndicatorAnimation2.j() || this.c.b());
            if (!a() && !z) {
                boolean c2 = this.f10654a.c(ae(), this.bh, this.f10654a.q(), this.f10654a.p());
                if (this.l && (true ^ c2)) {
                    i();
                    slg slgVar = this.f10654a;
                    slgVar.f(slgVar.e(ae(), this.bh));
                    slg slgVar2 = this.f10654a;
                    slgVar2.h(slgVar2.a(ae(), this.bh));
                    invalidate();
                }
            }
            this.ad = awsks.DEFAULT;
            this.f10654a.p(this.bh);
            if (!this.l || z) {
                return;
            }
            d(ae());
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
        HwViewPager.OnPageChangeListener onPageChangeListener = this.bv;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(i2, f2, i3);
        }
        if (this.m == 1) {
            this.br = i2 == this.bh;
        }
        if (a(i2, f2)) {
            b(i2, f2);
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        HwViewPager.OnPageChangeListener onPageChangeListener = this.bv;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(i2);
        }
        if (!this.be) {
            ak();
            return;
        }
        if (!this.l || !this.bu) {
            setSelectedPage(i2);
            return;
        }
        sll.e(this.ad == awsks.DEFAULT);
        if (sll.m()) {
            i();
            setSelectedPage(i2);
            ac();
            invalidate();
            return;
        }
        if ((this.bt ? b(i2) : h(i2)) && !this.bz) {
            HwDotsPageIndicatorAnimation hwDotsPageIndicatorAnimation = this.c;
            if (hwDotsPageIndicatorAnimation != null) {
                hwDotsPageIndicatorAnimation.i();
                this.c.h();
                i();
            }
            a(i2, false);
        }
        setSelectedPage(i2);
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicatorAnimation.AnimationUpdateListener
    public void onSingleScaled(boolean z, int i2, float f2) {
        if (z) {
            this.f10654a.m(f2);
        }
        this.f10654a.b(i2, f2);
        invalidate();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        setMeasuredDimension(i2, i3);
        k();
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicatorAnimation.AnimationUpdateListener
    public void onSpringAnimationUpdate(boolean z, float f2) {
        if (this.m == 1 || sll.m()) {
            return;
        }
        if (z) {
            this.f10654a.f(f2);
        } else {
            this.f10654a.h(f2);
        }
        invalidate();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        if (!this.l || this.bd == 0 || this.bt || !this.bs) {
            return super.onTouchEvent(motionEvent);
        }
        int actionIndex = motionEvent.getActionIndex();
        float x = motionEvent.getX(actionIndex);
        float y = motionEvent.getY(actionIndex);
        int action = motionEvent.getAction() & 255;
        if (this.y == bfscp.MOUSE_ON_DOT) {
            ai();
            ah();
            return super.onTouchEvent(motionEvent);
        }
        if (action == 0 || action == 5) {
            t();
            if (this.bk == 0) {
                this.bk = SystemClock.uptimeMillis();
            }
            this.bm = x;
        } else if (action == 1 || action == 6) {
            if (SystemClock.uptimeMillis() - this.bk < 300) {
                d(x, y);
            }
            u();
        } else if (action == 2) {
            b(x);
            this.bm = x;
        } else {
            if (action != 3) {
                return super.onTouchEvent(motionEvent);
            }
            u();
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.cg) {
            if (b(this.bn, z)) {
                setIndicatorFocusChanged(z);
            }
            this.bw = z;
            invalidate();
        }
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public void setAlphaInterpolator(TimeInterpolator timeInterpolator) {
        super.setAlphaInterpolator(timeInterpolator);
    }

    public void setAnimationEnable(boolean z) {
        this.bu = z;
        if (z && this.c == null) {
            this.c = new HwDotsPageIndicatorAnimation();
        }
    }

    public void setDotColor(int i2) {
        b("setDotColor");
        if (this.ai != i2) {
            this.ai = i2;
            Paint paint = this.bx;
            if (paint == null || !this.l) {
                return;
            }
            paint.setColor(i2);
            invalidate();
        }
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public void setDragAccelerateInterpolator(TimeInterpolator timeInterpolator) {
        super.setDragAccelerateInterpolator(timeInterpolator);
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public void setDragDecelerateInterpolator(TimeInterpolator timeInterpolator) {
        super.setDragDecelerateInterpolator(timeInterpolator);
    }

    public void setFocusBoxColor(int i2) {
        this.aq = i2;
    }

    protected void setFocusConfirm(boolean z) {
        this.ca = z;
    }

    public void setFocusDotColor(int i2) {
        b("setFocusDotColor");
        if (this.ah != i2) {
            this.ah = i2;
            Paint paint = this.by;
            if (paint == null || !this.l) {
                return;
            }
            paint.setColor(i2);
            invalidate();
        }
    }

    public void setGestureEnable(boolean z) {
        if (this.bt) {
            return;
        }
        this.bs = z;
    }

    protected void setIndicatorFocusChanged(boolean z) {
        this.bz = z;
    }

    public void setNumTextColor(int i2) {
        b("setNumTextColor");
        if (this.ax != i2) {
            this.ax = i2;
            Paint paint = this.cd;
            if (paint == null || this.l) {
                return;
            }
            paint.setColor(i2);
            invalidate();
        }
    }

    public void setOnIndicatorClickListener(HwDotsPageIndicatorInteractor.OnClickListener onClickListener) {
        this.t = onClickListener;
    }

    public void setOnIndicatorGestureListener(HwDotsPageIndicatorInteractor.OnGestureListener onGestureListener) {
        this.p = onGestureListener;
    }

    public void setOnIndicatorMouseOperatorListener(HwDotsPageIndicatorInteractor.OnMouseOperationListener onMouseOperationListener) {
        this.w = onMouseOperationListener;
    }

    public void setOnPageChangeListener(HwViewPager.OnPageChangeListener onPageChangeListener) {
        this.bv = onPageChangeListener;
    }

    public void setPressedStateColor(int i2) {
        b("setPressedStateColor");
        this.ap = i2;
        if (ae() || !this.l) {
            return;
        }
        this.f10654a.t(this.ap);
        invalidate();
    }

    public void setRtlEnable(boolean z) {
        this.bj = z;
        invalidate();
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public void setScaleInterpolator(TimeInterpolator timeInterpolator) {
        super.setScaleInterpolator(timeInterpolator);
    }

    public void setSelectedPage(int i2) {
        if (i2 == this.bh || this.bd == 0) {
            return;
        }
        ak();
        if (af()) {
            if (ae()) {
                ac();
            } else {
                this.f10654a.d(this.f10654a.b(false, this.bh));
                slg slgVar = this.f10654a;
                slgVar.f(slgVar.f(this.bh));
                slg slgVar2 = this.f10654a;
                slgVar2.h(slgVar2.g(this.bh));
            }
        }
        invalidate();
    }

    public void setShowAsDot(boolean z) {
        b("setShowAsDot");
        if (this.l == z) {
            return;
        }
        this.l = z;
        this.o = true;
        requestLayout();
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public void setSpringAnimationDamping(float f2) {
        super.setSpringAnimationDamping(f2);
    }

    @Override // com.huawei.uikit.hwdotspageindicator.widget.bzrwd
    public void setSpringAnimationStiffness(float f2) {
        super.setSpringAnimationStiffness(f2);
    }

    public void setViewPager(HwViewPager hwViewPager) {
        if (hwViewPager == null || hwViewPager.getAdapter() == null) {
            return;
        }
        this.e = hwViewPager;
        setPageCount(hwViewPager.getAdapter().getCount());
        hwViewPager.getAdapter().registerDataSetObserver(new e());
        hwViewPager.addOnPageChangeListener(this);
        ak();
    }

    public HwDotsPageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100278_res_0x7f060276);
    }

    private void e(int i2, float f2, int i3) {
        if (this.br) {
            b(i2, f2, i3);
            return;
        }
        float a2 = this.f10654a.a(ae(), this.bh);
        float f3 = 1.0f - f2;
        this.f10654a.h(this.q ? a2 + (c(f3) * i3) : a2 - (c(f3) * i3));
        float q = this.f10654a.q();
        float e2 = this.f10654a.e(ae(), i2 + 1);
        if (g()) {
            return;
        }
        this.bo = true;
        a(true, q, e2, this.f, this.d);
    }

    private void ecf_(Context context, AttributeSet attributeSet, int i2) {
        ecd_(context, attributeSet, i2);
        boolean z = sms.d(context) == 8;
        this.b = z;
        if (this.bt) {
            this.bs = false;
        }
        if (!this.l) {
            this.bs = false;
            this.bu = false;
        }
        if (this.bu) {
            if (z) {
                this.j = new HwWatchDotsPageIndicatorAnimation();
            } else {
                this.c = new HwDotsPageIndicatorAnimation();
            }
        }
        if (isInEditMode()) {
            this.bd = 3;
            this.f10654a.r(3);
            this.h.b(this.bd);
        }
        ech_(context, attributeSet, i2);
        d(context, attributeSet, i2);
        if (this.bt) {
            x();
        }
        setOnClickListener(this);
    }

    private void h(int i2, float f2, int i3) {
        if (!this.br) {
            a(i2, f2, i3);
            return;
        }
        float e2 = this.f10654a.e(ae(), i2);
        this.f10654a.f(this.q ? e2 - (c(f2) * i3) : e2 + (c(f2) * i3));
        float p = this.f10654a.p();
        float a2 = this.f10654a.a(ae(), i2);
        if (g()) {
            return;
        }
        this.bo = false;
        a(false, p, a2, this.f, this.d);
    }

    private void p() {
        if (!this.bs || this.c == null || !this.f10654a.a() || this.c.j() || this.c.e()) {
            return;
        }
        this.c.f();
        float r = this.v - (this.f10654a.r() - this.f10654a.t());
        if (this.bu) {
            float f2 = r / 2.0f;
            ecX_(new RectF(this.f10654a.q() - r, this.f10654a.t() - f2, this.f10654a.p() + r, this.f10654a.r() + f2), this);
            this.y = bfscp.VISIBLE;
            return;
        }
        slg slgVar = this.f10654a;
        float f3 = r / 2.0f;
        slgVar.j(slgVar.t() - f3);
        slg slgVar2 = this.f10654a;
        slgVar2.o(slgVar2.r() + f3);
        float q = this.f10654a.q();
        float p = this.f10654a.p();
        this.f10654a.f(this.q ? q + r : q - r);
        this.f10654a.h(this.q ? p - r : p + r);
        this.y = bfscp.VISIBLE;
        this.f10654a.a(false);
        invalidate();
    }

    private void s() {
        if (!this.bs || this.p == null) {
            return;
        }
        h hVar = this.ac;
        if (hVar != null) {
            removeCallbacks(hVar);
            this.ac = null;
        }
        if (ae()) {
            return;
        }
        e(true);
        if (this.ad == awsks.SLIDE) {
            this.ad = awsks.DEFAULT;
        }
        this.r.a(0.0f);
    }

    private void t() {
        HwDotsPageIndicatorInteractor.OnGestureListener onGestureListener;
        if (!this.bs || (onGestureListener = this.p) == null || this.bz) {
            return;
        }
        onGestureListener.onLongPress(0);
        ah();
    }

    private void u() {
        this.bk = 0L;
        this.r.d(true);
        s();
    }

    private void w() {
        int paddingLeft = getPaddingLeft();
        int width = getWidth();
        int paddingRight = getPaddingRight();
        float desiredWidth = getDesiredWidth();
        float f2 = paddingLeft + (((width - paddingRight) - desiredWidth) / 2.0f) + this.ay;
        float f3 = this.av / 2.0f;
        this.at = f3;
        this.f10654a.d(f3);
        this.f10654a.a(f2);
        float f4 = f2 - this.ay;
        float f5 = this.at;
        float f6 = this.aw / 2.0f;
        float f7 = desiredWidth + f4;
        this.am = new RectF(f4, f5 - f6, f7, f5 + f6);
        float f8 = this.bb - this.ay;
        float f9 = this.at;
        float f10 = this.av / 2.0f;
        this.an = new RectF(f4 - f8, f9 - f10, f7 + f8, f9 + f10);
    }

    public HwDotsPageIndicator(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.bq = 5000;
        this.m = 0;
        this.q = false;
        this.bj = true;
        this.bk = 0L;
        this.bi = false;
        this.br = false;
        this.bo = true;
        this.bp = false;
        this.r = new sll.e();
        this.y = bfscp.COMMON;
        this.ad = awsks.DEFAULT;
        this.as = new i();
        ecf_(super.getContext(), attributeSet, i2);
        setOnClickListener(this);
        setFocusable(true);
        setDefaultFocusHighlightEnabled(false);
    }

    private void d(Context context, AttributeSet attributeSet, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100199_res_0x7f060227, R.attr._2131100200_res_0x7f060228, R.attr._2131100201_res_0x7f060229, R.attr._2131100202_res_0x7f06022a, R.attr._2131100203_res_0x7f06022b, R.attr._2131100276_res_0x7f060274, R.attr._2131100277_res_0x7f060275, R.attr._2131100328_res_0x7f0602a8, R.attr._2131100329_res_0x7f0602a9, R.attr._2131100346_res_0x7f0602ba, R.attr._2131100355_res_0x7f0602c3, R.attr._2131100356_res_0x7f0602c4, R.attr._2131100357_res_0x7f0602c5, R.attr._2131100384_res_0x7f0602e0, R.attr._2131100386_res_0x7f0602e2, R.attr._2131100388_res_0x7f0602e4, R.attr._2131100403_res_0x7f0602f3, R.attr._2131100416_res_0x7f060300, R.attr._2131100417_res_0x7f060301, R.attr._2131100512_res_0x7f060360, R.attr._2131100514_res_0x7f060362, R.attr._2131100515_res_0x7f060363, R.attr._2131100570_res_0x7f06039a, R.attr._2131100571_res_0x7f06039b, R.attr._2131100590_res_0x7f0603ae, R.attr._2131100592_res_0x7f0603b0, R.attr._2131100593_res_0x7f0603b1, R.attr._2131100594_res_0x7f0603b2, R.attr._2131100597_res_0x7f0603b5}, i2, 0);
        this.bg = obtainStyledAttributes.getDimensionPixelSize(22, R.dimen._2131362703_res_0x7f0a038f);
        this.az = obtainStyledAttributes.getDimensionPixelSize(12, R.dimen._2131364218_res_0x7f0a097a);
        this.ar = obtainStyledAttributes.getDimensionPixelSize(8, R.dimen._2131364192_res_0x7f0a0960);
        obtainStyledAttributes.recycle();
        Paint paint = new Paint(1);
        this.cd = paint;
        paint.setTextSize(this.bg);
        this.cd.setColor(this.ax);
        this.cd.setTextAlign(Paint.Align.CENTER);
        this.cd.setTypeface(Typeface.create(getResources().getString(R.string._2130850837_res_0x7f023415), 0));
        this.n = this.cd.getFontMetrics();
    }

    private void ech_(Context context, AttributeSet attributeSet, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100199_res_0x7f060227, R.attr._2131100200_res_0x7f060228, R.attr._2131100201_res_0x7f060229, R.attr._2131100202_res_0x7f06022a, R.attr._2131100203_res_0x7f06022b, R.attr._2131100276_res_0x7f060274, R.attr._2131100277_res_0x7f060275, R.attr._2131100328_res_0x7f0602a8, R.attr._2131100329_res_0x7f0602a9, R.attr._2131100346_res_0x7f0602ba, R.attr._2131100355_res_0x7f0602c3, R.attr._2131100356_res_0x7f0602c4, R.attr._2131100357_res_0x7f0602c5, R.attr._2131100384_res_0x7f0602e0, R.attr._2131100386_res_0x7f0602e2, R.attr._2131100388_res_0x7f0602e4, R.attr._2131100403_res_0x7f0602f3, R.attr._2131100416_res_0x7f060300, R.attr._2131100417_res_0x7f060301, R.attr._2131100512_res_0x7f060360, R.attr._2131100514_res_0x7f060362, R.attr._2131100515_res_0x7f060363, R.attr._2131100570_res_0x7f06039a, R.attr._2131100571_res_0x7f06039b, R.attr._2131100590_res_0x7f0603ae, R.attr._2131100592_res_0x7f0603b0, R.attr._2131100593_res_0x7f0603b1, R.attr._2131100594_res_0x7f0603b2, R.attr._2131100597_res_0x7f0603b5}, i2, 0);
        this.v = obtainStyledAttributes.getDimensionPixelSize(26, R.dimen._2131364212_res_0x7f0a0974);
        this.x = obtainStyledAttributes.getDimensionPixelSize(27, R.dimen._2131364214_res_0x7f0a0976);
        this.s = obtainStyledAttributes.getDimensionPixelSize(25, R.dimen._2131364210_res_0x7f0a0972);
        this.aa = obtainStyledAttributes.getDimensionPixelSize(5, R.dimen._2131364188_res_0x7f0a095c);
        this.ab = obtainStyledAttributes.getDimensionPixelSize(6, R.dimen._2131364215_res_0x7f0a0977);
        this.ak = obtainStyledAttributes.getDimensionPixelSize(20, R.dimen._2131364201_res_0x7f0a0969);
        this.av = obtainStyledAttributes.getDimensionPixelSize(24, R.dimen._2131364205_res_0x7f0a096d);
        this.au = obtainStyledAttributes.getDimensionPixelSize(21, R.dimen._2131364203_res_0x7f0a096b);
        this.ar = obtainStyledAttributes.getDimensionPixelSize(8, R.dimen._2131364192_res_0x7f0a0960);
        this.aw = obtainStyledAttributes.getDimensionPixelSize(10, R.dimen._2131364190_res_0x7f0a095e);
        this.ay = obtainStyledAttributes.getDimensionPixelSize(16, R.dimen._2131364197_res_0x7f0a0965);
        this.bb = obtainStyledAttributes.getDimensionPixelSize(11, R.dimen._2131364194_res_0x7f0a0962);
        obtainStyledAttributes.recycle();
        Resources resources = getResources();
        float dimensionPixelSize = resources.getDimensionPixelSize(R.dimen._2131364208_res_0x7f0a0970);
        float dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen._2131364209_res_0x7f0a0971);
        float dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen._2131364207_res_0x7f0a096f);
        this.r.e(dimensionPixelSize);
        this.r.b(dimensionPixelOffset);
        this.r.d(dimensionPixelOffset2);
        this.f10654a.c(this.s / 2.0f);
        this.f10654a.n(this.aa);
        this.f10654a.s(this.ab);
        this.f10654a.i(this.ak);
        this.f10654a.g(this.au);
        this.f10654a.e(this.v);
        this.u = this.s / 2.0f;
        z();
    }

    private void ecd_(Context context, AttributeSet attributeSet, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100199_res_0x7f060227, R.attr._2131100200_res_0x7f060228, R.attr._2131100201_res_0x7f060229, R.attr._2131100202_res_0x7f06022a, R.attr._2131100203_res_0x7f06022b, R.attr._2131100276_res_0x7f060274, R.attr._2131100277_res_0x7f060275, R.attr._2131100328_res_0x7f0602a8, R.attr._2131100329_res_0x7f0602a9, R.attr._2131100346_res_0x7f0602ba, R.attr._2131100355_res_0x7f0602c3, R.attr._2131100356_res_0x7f0602c4, R.attr._2131100357_res_0x7f0602c5, R.attr._2131100384_res_0x7f0602e0, R.attr._2131100386_res_0x7f0602e2, R.attr._2131100388_res_0x7f0602e4, R.attr._2131100403_res_0x7f0602f3, R.attr._2131100416_res_0x7f060300, R.attr._2131100417_res_0x7f060301, R.attr._2131100512_res_0x7f060360, R.attr._2131100514_res_0x7f060362, R.attr._2131100515_res_0x7f060363, R.attr._2131100570_res_0x7f06039a, R.attr._2131100571_res_0x7f06039b, R.attr._2131100590_res_0x7f0603ae, R.attr._2131100592_res_0x7f0603b0, R.attr._2131100593_res_0x7f0603b1, R.attr._2131100594_res_0x7f0603b2, R.attr._2131100597_res_0x7f0603b5}, i2, 0);
        this.bt = obtainStyledAttributes.getBoolean(13, false);
        this.ai = obtainStyledAttributes.getColor(28, ContextCompat.getColor(getContext(), R.color._2131297400_res_0x7f090478));
        this.ah = obtainStyledAttributes.getColor(19, ContextCompat.getColor(getContext(), R.color._2131297388_res_0x7f09046c));
        this.ap = obtainStyledAttributes.getColor(0, ContextCompat.getColor(getContext(), R.color.emui_clickeffic_default_color));
        int color = obtainStyledAttributes.getColor(4, ContextCompat.getColor(getContext(), R.color.emui_clickeffic_default_color));
        this.ao = color;
        this.f10654a.k(color);
        this.f10654a.o(this.ap);
        this.aq = obtainStyledAttributes.getColor(7, ContextCompat.getColor(getContext(), R.color._2131297390_res_0x7f09046e));
        this.l = obtainStyledAttributes.getBoolean(15, true);
        this.bu = obtainStyledAttributes.getBoolean(9, true);
        this.bs = obtainStyledAttributes.getBoolean(14, true);
        this.ax = obtainStyledAttributes.getColor(18, ContextCompat.getColor(getContext(), R.color._2131297519_res_0x7f0904ef));
        this.ba = obtainStyledAttributes.getColor(17, ContextCompat.getColor(getContext(), R.color._2131297442_res_0x7f0904a2));
        this.ae = obtainStyledAttributes.getColor(3, ContextCompat.getColor(getContext(), R.color._2131298335_res_0x7f09081f));
        this.aj = obtainStyledAttributes.getColor(2, ContextCompat.getColor(getContext(), R.color._2131297388_res_0x7f09046c));
        this.cg = obtainStyledAttributes.getBoolean(1, false);
        obtainStyledAttributes.recycle();
    }

    private float c(float f2) {
        return getDecelerateInterpolator().getInterpolation(f2);
    }

    private void q() {
        if (!this.bs || this.c == null || this.m != 0 || this.f10654a.a() || this.c.j() || this.c.c()) {
            return;
        }
        this.c.n();
        float r = this.x - (this.f10654a.r() - this.f10654a.t());
        if (!this.bu) {
            slg slgVar = this.f10654a;
            float f2 = r / 2.0f;
            slgVar.j(slgVar.t() - f2);
            slg slgVar2 = this.f10654a;
            slgVar2.o(slgVar2.r() + f2);
            float p = this.f10654a.p();
            float q = this.f10654a.q();
            this.f10654a.f(this.q ? q + r : q - r);
            this.f10654a.h(this.q ? p - r : p + r);
            this.f10654a.a(true);
            invalidate();
            return;
        }
        float f3 = r / 2.0f;
        ecW_(new RectF(this.f10654a.q() - r, this.f10654a.t() - f3, this.f10654a.p() + r, this.f10654a.r() + f3), this);
        this.y = bfscp.MOUSE_ON_DOT;
    }

    private boolean h(int i2) {
        HwViewPager hwViewPager = this.e;
        return ae() && (hwViewPager != null && hwViewPager.isSupportLoop()) && b(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        if (this.bi) {
            return;
        }
        e(false);
    }

    private void d(int i2, float f2, int i3) {
        boolean z = this.bh != i2;
        if (!this.bp) {
            if (z) {
                e(i2, f2, i3);
                return;
            } else {
                h(i2, f2, i3);
                return;
            }
        }
        if (sll.m()) {
            return;
        }
        if (z) {
            b(i2, f2, i3);
        } else {
            a(i2, f2, i3);
        }
    }

    private void b(int i2, float f2) {
        if (this.m == 0 && Float.compare(f2, 0.0f) == 0) {
            this.bh = i2;
            onPageScrollStateChanged(this.m);
            return;
        }
        int distanceProper = getDistanceProper();
        if (this.m == 2) {
            d(i2, f2, distanceProper);
        } else {
            c(i2, f2, distanceProper);
        }
        e(i2, f2);
    }

    private float a(float f2) {
        return getAccelerateInterpolator().getInterpolation(f2);
    }

    private boolean b(int i2) {
        return (i2 == 0 && this.bh == this.bd - 1 && (this.bp || this.br)) || (i2 == this.bd - 1 && this.bh == 0 && (this.bp || !this.br));
    }

    private void o() {
        HwDotsPageIndicatorAnimation hwDotsPageIndicatorAnimation = this.c;
        if (hwDotsPageIndicatorAnimation != null && hwDotsPageIndicatorAnimation.c()) {
            this.c.f();
            am();
        }
        if (this.f10654a.a()) {
            am();
        }
    }

    private void i(float f2) {
        i();
        if (this.bd - 1 <= 0 || this.r.d() <= 0.0f || this.r.a() <= 0.0f) {
            return;
        }
        float b2 = f2 - this.r.b();
        boolean z = b2 > 0.0f && !this.q;
        boolean z2 = b2 < 0.0f && this.q;
        float min = Math.min(Math.abs(b2), this.r.a()) / this.r.a();
        HwDotsPageIndicatorInteractor.OnGestureListener onGestureListener = this.p;
        if (onGestureListener != null) {
            onGestureListener.onOverDrag(min);
        }
        Pair<Float, Float> ecL_ = sll.ecL_(getScaleInterpolator(), min, this.bd, getScaledWidth(), this.av);
        float floatValue = ((Float) ecL_.first).floatValue();
        float floatValue2 = ((Float) ecL_.second).floatValue();
        RectF rectF = this.al;
        float f3 = (this.av - floatValue2) / 2.0f;
        e(z2, z, floatValue, rectF.top + f3, rectF.bottom - f3);
    }

    private void a(int i2, float f2, int i3) {
        float a2;
        int i4 = i2 + 1;
        float e2 = this.f10654a.e(ae(), i4);
        slg slgVar = this.f10654a;
        if (this.q) {
            a2 = e2 + (a(1.0f - f2) * i3);
        } else {
            a2 = e2 - (a(1.0f - f2) * i3);
        }
        slgVar.f(a2);
        float f3 = 1.0f - f2;
        if (f3 < getMaxDiffFraction()) {
            float a3 = this.f10654a.a(ae(), i4);
            this.f10654a.h(this.q ? a3 + (c(f3) * i3) : a3 - (c(f3) * i3));
            return;
        }
        float p = this.f10654a.p();
        float a4 = this.f10654a.a(ae(), i2);
        if (g()) {
            return;
        }
        this.bo = false;
        a(false, p, a4, this.f, this.d);
    }

    private void a(float f2, float f3) {
        if (!this.bi) {
            e(false);
            return;
        }
        if (sll.a(this.f10654a, this.q, f2, f3)) {
            q();
            e(this.f10654a.d());
            this.f10654a.q(-1);
            return;
        }
        p();
        int d2 = sll.d(this.f10654a, this.x / 2.0f, (this.v + this.ab) / 2.0f, f2, f3);
        if (d2 == this.bh) {
            return;
        }
        if (d2 == -1) {
            if (this.f10654a.d() != -1) {
                e(this.f10654a.d());
                this.f10654a.q(-1);
                return;
            }
            return;
        }
        if (d2 == this.f10654a.d()) {
            return;
        }
        e(this.f10654a.d());
        this.f10654a.q(-1);
        if (c(d2)) {
            this.f10654a.q(d2);
        }
    }

    private void b(int i2, float f2, int i3) {
        float a2 = this.f10654a.a(ae(), i2);
        this.f10654a.h(this.q ? a2 - (a(f2) * i3) : a2 + (a(f2) * i3));
        if (!sll.k() && f2 >= getMaxDiffFraction()) {
            float q = this.f10654a.q();
            float e2 = this.f10654a.e(ae(), i2 + 1);
            if (g()) {
                return;
            }
            this.bo = true;
            a(true, q, e2, this.f, this.d);
            return;
        }
        float e3 = this.f10654a.e(ae(), i2);
        this.f10654a.f(this.q ? e3 - (c(f2) * i3) : e3 + (c(f2) * i3));
    }

    private boolean a(int i2, float f2) {
        HwDotsPageIndicatorAnimation hwDotsPageIndicatorAnimation;
        return this.l && this.bu && this.ad != awsks.TARGET && ((hwDotsPageIndicatorAnimation = this.c) == null || !(hwDotsPageIndicatorAnimation.j() || this.c.b())) && i2 + 1 <= this.bd - 1 && Float.compare(f2, 0.0f) >= 0;
    }

    private void c(int i2, float f2, int i3) {
        if (this.br) {
            float e2 = this.f10654a.e(ae(), i2);
            float a2 = this.f10654a.a(ae(), i2);
            this.f10654a.f(this.q ? e2 - (c(f2) * i3) : e2 + (c(f2) * i3));
            this.f10654a.h(this.q ? a2 - (a(f2) * i3) : a2 + (a(f2) * i3));
            return;
        }
        int i4 = i2 + 1;
        float e3 = this.f10654a.e(ae(), i4);
        float a3 = this.f10654a.a(ae(), i4);
        this.f10654a.f(this.q ? e3 + (a(1.0f - f2) * i3) : e3 - (a(1.0f - f2) * i3));
        float f3 = 1.0f - f2;
        this.f10654a.h(this.q ? a3 + (c(f3) * i3) : a3 - (c(f3) * i3));
    }

    private void l() {
        this.bc = getPaddingLeft() + (((getWidth() - getPaddingRight()) - r0) / 2.0f);
        float height = getHeight();
        Paint.FontMetrics fontMetrics = this.n;
        float f2 = fontMetrics.descent;
        float f3 = fontMetrics.ascent;
        this.bf = ((height - (f2 - f3)) / 2.0f) - f3;
        ak();
    }

    private boolean c(int i2) {
        HwDotsPageIndicatorAnimation hwDotsPageIndicatorAnimation;
        if (!this.bs || (hwDotsPageIndicatorAnimation = this.c) == null || hwDotsPageIndicatorAnimation.j() || this.c.b() || this.c.e(i2) || this.f10654a.d() == i2) {
            return false;
        }
        if (!this.bu) {
            this.f10654a.b(i2, this.x / 2.0f);
            this.y = bfscp.MOUSE_ON_DOT;
            invalidate();
            return true;
        }
        b(i2, this.x / 2.0f, this);
        this.y = bfscp.MOUSE_ON_DOT;
        return true;
    }

    private void e(int i2, float f2) {
        float b2 = this.f10654a.b(ae());
        float d2 = this.f10654a.d(ae(), i2);
        if (!this.q) {
            this.f10654a.d(i2, d2 - (b2 * f2));
            int i3 = i2 + 1;
            if (i3 < this.bd) {
                slg slgVar = this.f10654a;
                slgVar.d(i3, slgVar.d(ae(), i3) + (b2 * (1.0f - f2)));
            }
        } else {
            this.f10654a.d(i2, d2 + (b2 * f2));
            int i4 = i2 + 1;
            if (i4 < this.bd) {
                slg slgVar2 = this.f10654a;
                slgVar2.d(i4, slgVar2.d(ae(), i4) - (b2 * (1.0f - f2)));
            }
        }
        invalidate();
    }

    private void k() {
        if (this.l) {
            n();
        } else {
            l();
        }
    }

    private void d(float f2, float f3) {
        if (this.t == null || !isInTouchMode()) {
            return;
        }
        RectF rectF = this.af;
        if (rectF != null && rectF.contains(f2, f3)) {
            h();
            return;
        }
        RectF rectF2 = this.ag;
        if (rectF2 == null || !rectF2.contains(f2, f3)) {
            return;
        }
        f();
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x000c, code lost:
    
        if (r3 == 2) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(int r3) {
        /*
            r2 = this;
            com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicator$awsks r0 = r2.ad
            com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicator$awsks r1 = com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicator.awsks.DEFAULT
            if (r0 != r1) goto Lf
            int r0 = r2.m
            r1 = 1
            if (r0 == r1) goto Lf
            r0 = 2
            if (r3 != r0) goto Lf
            goto L10
        Lf:
            r1 = 0
        L10:
            r2.bp = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicator.d(int):void");
    }

    private void d(boolean z) {
        slg slgVar = this.f10654a;
        if (slgVar.a(z, this.bh, slgVar.g())) {
            return;
        }
        slg slgVar2 = this.f10654a;
        slgVar2.d(slgVar2.c(z, this.bh));
        invalidate();
    }

    private boolean d(float f2) {
        HwDotsPageIndicatorAnimation hwDotsPageIndicatorAnimation;
        if (this.bd <= 1 || !this.bs || ae() || ((hwDotsPageIndicatorAnimation = this.c) != null && hwDotsPageIndicatorAnimation.j())) {
            return false;
        }
        sll.e eVar = this.r;
        boolean z = this.q;
        int i2 = this.bh;
        return sll.d(eVar, f2, z, i2 > 0, i2 < this.bd - 1);
    }

    private void n() {
        HwViewPager hwViewPager = this.e;
        this.bh = hwViewPager != null ? hwViewPager.getCurrentItem() : 0;
        if (this.bd < 1) {
            return;
        }
        w();
        ad();
        ac();
        ak();
    }

    private void e(int i2) {
        HwDotsPageIndicatorAnimation hwDotsPageIndicatorAnimation;
        if (!this.bs || (hwDotsPageIndicatorAnimation = this.c) == null || i2 == -1 || hwDotsPageIndicatorAnimation.a(i2)) {
            return;
        }
        if (!this.bu) {
            this.f10654a.m(i2);
            invalidate();
        } else {
            ecZ_(i2, this, this);
            this.y = bfscp.VISIBLE;
        }
    }

    private void a(int i2, int i3, float f2) {
        if (Float.compare(f2, 1.0f) >= 0) {
            e(i2, i3);
            return;
        }
        c(i2, i3, f2);
        b(i2, i3 > i2);
        this.m = 1;
        invalidate();
    }

    private boolean b(boolean z, boolean z2) {
        boolean z3 = this.bn && this.bw;
        boolean z4 = z && z2;
        return (z3 && !z4) || (!z3 && z4);
    }

    private boolean e(int i2, float f2, float f3) {
        if (this.bt || !this.bs || this.w == null || this.bz || this.f10654a.ecI_() == null || !this.l || this.bd == 0) {
            return false;
        }
        boolean a2 = a();
        if (i2 == 10 && !a2) {
            if (this.z == null) {
                this.z = new a(this, null);
            }
            postDelayed(this.z, 100L);
        }
        this.bi = this.f10654a.ecI_().contains(f2, f3);
        return !a2;
    }

    private void ecg_(Canvas canvas) {
        float r = (this.f10654a.r() - this.f10654a.t()) / 2.0f;
        canvas.drawRoundRect(this.f10654a.ecH_(), r, r, this.by);
        aa();
        y();
    }

    private void a(int i2, boolean z) {
        HwViewPager hwViewPager = this.e;
        if (hwViewPager == null || hwViewPager.getAdapter() == null || this.e.getAdapter().getCount() < 2 || i2 < 0 || i2 >= this.bd || i2 == this.bh || a()) {
            return;
        }
        this.ad = awsks.TARGET;
        this.f10654a.p(this.bh);
        float[] b2 = this.f10654a.b(ae(), i2);
        float e2 = this.f10654a.e(ae(), i2);
        float a2 = this.f10654a.a(ae(), i2);
        if (!this.bu) {
            this.f10654a.f(e2);
            this.f10654a.h(a2);
            b(b2);
            this.e.setCurrentItem(i2, false);
            HwDotsPageIndicatorInteractor.OnMouseOperationListener onMouseOperationListener = this.w;
            if (onMouseOperationListener != null) {
                onMouseOperationListener.onFocusAnimationProgress(1.0f);
                return;
            }
            return;
        }
        i();
        slg f2 = this.f10654a.f();
        f2.p(i2);
        f2.f(e2);
        f2.h(a2);
        boolean z2 = f2.aa() > this.f10654a.aa();
        float p = z2 ? this.f10654a.p() : this.f10654a.q();
        float p2 = z2 ? f2.p() : f2.q();
        e(i2, z2, z2 ? this.f10654a.q() : this.f10654a.p(), z2 ? f2.q() : f2.p(), z);
        b(p, p2);
        this.bo = f2.aa() > this.f10654a.aa();
        b(b2);
        this.e.setCurrentItem(i2);
    }

    private void b(float f2) {
        if (!this.bs || this.p == null || this.r.e() <= 0.0f) {
            return;
        }
        if (e(f2)) {
            i(f2);
            return;
        }
        if (!d(f2)) {
            this.r.d(true);
            return;
        }
        if (this.r.f()) {
            this.r.c(this.bm);
            this.r.d(false);
        }
        float c2 = f2 - this.r.c();
        float abs = Math.abs(c2) / this.r.e();
        int i2 = ((c2 <= 0.0f || this.q) && (c2 >= 0.0f || !this.q)) ? this.bh - 1 : this.bh + 1;
        this.ad = awsks.SLIDE;
        int i3 = this.bh;
        if (this.bu) {
            a(i3, i2, abs);
        } else if (Float.compare(abs, 1.0f) >= 0) {
            this.bh = i2;
            this.f10654a = ab();
            invalidate();
            settleToTarget(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, float f2, float f3, float f4, float f5) {
        a(new bzrwd.e(z, f2, f3, f4, f5), this);
    }

    private void b(float[] fArr) {
        if (!this.bu) {
            this.f10654a.d(fArr);
            invalidate();
        } else {
            a(fArr, this);
        }
    }

    private void c(float f2, float f3) {
        d(f2, f3, this);
    }

    private boolean e(float f2) {
        HwDotsPageIndicatorAnimation hwDotsPageIndicatorAnimation;
        if (this.bd <= 1 || !this.bs || !this.bu || ae() || ((hwDotsPageIndicatorAnimation = this.c) != null && (hwDotsPageIndicatorAnimation.j() || a()))) {
            return false;
        }
        sll.e eVar = this.r;
        boolean z = this.q;
        int i2 = this.bh;
        return sll.a(eVar, f2, z, i2 == 0, i2 == this.bd - 1);
    }

    private void b(int i2, boolean z) {
        this.f10654a.d(i2, z ? this.f10654a.e(i2) : this.f10654a.a(i2));
    }

    private void c(int i2, int i3, float f2) {
        if (i2 == i3 || i3 > this.bd - 1 || i3 < 0) {
            return;
        }
        boolean z = i3 > i2;
        e(f2, 1, i2);
        float interpolation = getAccelerateInterpolator().getInterpolation(f2);
        float interpolation2 = getDecelerateInterpolator().getInterpolation(f2);
        i();
        float a2 = this.f10654a.a(ae(), i2);
        float a3 = this.f10654a.a(ae(), i3);
        float e2 = this.f10654a.e(ae(), i2);
        float e3 = this.f10654a.e(ae(), i3);
        float f3 = z ? interpolation2 : interpolation;
        if (!z) {
            interpolation = interpolation2;
        }
        this.f10654a.f(e2 + ((e3 - e2) * f3));
        this.f10654a.h(a2 + ((a3 - a2) * interpolation));
    }

    private void eci_(Canvas canvas) {
        if (this.bl == null || this.cd == null) {
            return;
        }
        if (e() && this.bz) {
            this.cd.setColor(this.ba);
        } else {
            this.cd.setColor(this.ax);
        }
        canvas.drawText(this.bl, this.bc, this.bf, this.cd);
    }

    private void b(float f2, float f3) {
        d(f2, f3, this, new c());
    }

    private void e(int i2, int i3) {
        float g2 = this.f10654a.g(i3);
        this.f10654a.f(this.f10654a.f(i3));
        this.f10654a.h(g2);
        boolean z = i3 > i2;
        float e2 = this.f10654a.e(i2);
        float a2 = this.f10654a.a(i2);
        float c2 = this.f10654a.c(i3);
        if (!z) {
            e2 = a2;
        }
        this.f10654a.d(i2, e2);
        this.f10654a.d(i3, c2);
        invalidate();
        settleToTarget(i3);
    }

    private void b(int i2, int i3) {
        HwDotsPageIndicatorInteractor.OnClickListener onClickListener = this.t;
        if (onClickListener != null) {
            onClickListener.onClick(i2, i3);
        }
    }

    private void b(boolean z) {
        if (a()) {
            return;
        }
        i();
        this.ad = awsks.TARGET;
        int i2 = z ? this.bh + 1 : this.bh - 1;
        float e2 = this.f10654a.e(ae(), i2);
        float a2 = this.f10654a.a(ae(), i2);
        slg f2 = this.f10654a.f();
        f2.f(e2);
        f2.h(a2);
        int aa = this.f10654a.aa();
        f2.p(z ? aa + 1 : aa - 1);
        float maxDiffFraction = getMaxDiffFraction();
        this.bo = f2.aa() > aa;
        c(z ? this.f10654a.p() : this.f10654a.q(), z ? f2.p() : f2.q());
        a(z ? this.f10654a.q() : this.f10654a.p(), z ? f2.q() : f2.p(), this, new g(maxDiffFraction, z, e2, a2));
        this.bh = i2;
        b(this.f10654a.b(ae(), this.bh));
        if (z) {
            this.e.nextPage();
        } else {
            this.e.prePage();
        }
    }

    private void e(boolean z, boolean z2, float f2, float f3, float f4) {
        float f5;
        float f6;
        float f7;
        float f8;
        int i2;
        float f9 = this.ay;
        float f10 = this.au;
        int i3 = this.v;
        int i4 = this.bd;
        int i5 = i4 - 1;
        float f11 = (((f2 - (f9 * 2.0f)) - f10) - (i3 * i5)) / i5;
        float[] fArr = new float[i4];
        boolean z3 = this.q;
        int i6 = 0;
        boolean z4 = z3 && z;
        boolean z5 = z3 && !z;
        boolean z6 = (z3 || z2) ? false : true;
        if (!z4 && !z6) {
            f8 = this.al.left;
            f7 = f8 + f2;
            while (true) {
                i2 = this.bd;
                if (i6 >= i2) {
                    break;
                }
                int i7 = this.q ? (i2 - 1) - i6 : i6;
                fArr[i7] = this.ay + f8 + (i6 * f11) + (this.v / 2.0f) + (r12 * i6);
                i6++;
            }
            if (z5) {
                f6 = fArr[1] + (this.v / 2.0f) + f11;
                f5 = this.au + f6;
            } else {
                f5 = f11 + fArr[i2 - 2] + (this.v / 2.0f);
                f6 = this.au + f5;
            }
        } else {
            float f12 = this.al.right;
            float f13 = f12 - f2;
            while (i5 >= 0) {
                int i8 = (this.bd - 1) - i5;
                int i9 = this.q ? i8 : i5;
                fArr[i9] = (((f12 - this.ay) - (i8 * f11)) - (this.v / 2.0f)) - (i8 * r13);
                i5--;
            }
            if (z4) {
                f6 = this.ay + f13;
                f5 = this.au + f6;
            } else {
                f5 = this.ay + f13;
                f6 = this.au + f5;
            }
            f7 = f12;
            f8 = f13;
        }
        this.f10654a.a(f8, f3, f7, f4);
        this.f10654a.d(fArr);
        this.f10654a.f(f5);
        this.f10654a.h(f6);
        invalidate();
    }

    private void ece_(Canvas canvas) {
        Paint paint;
        ConcurrentHashMap<Integer, Float> e2 = this.f10654a.e();
        float[] g2 = this.f10654a.g();
        for (int i2 = 0; i2 < this.bd; i2++) {
            float l = this.f10654a.l();
            if (e2 != null && e2.get(Integer.valueOf(i2)) != null && i2 != this.bh) {
                l = e2.get(Integer.valueOf(i2)).floatValue();
            }
            if (g2 != null && i2 < g2.length && (paint = this.bx) != null) {
                canvas.drawCircle(g2[i2], this.at, l, paint);
            }
        }
    }

    private void e(int i2, boolean z, float f2, float f3, boolean z2) {
        a(f2, f3, this, new d(getMaxDiffFraction(), z, Math.abs(i2 - this.bh), f3, new b(z2)));
    }

    private void e(float f2, int i2, int i3) {
        HwDotsPageIndicatorInteractor.OnGestureListener onGestureListener = this.p;
        if (onGestureListener != null) {
            onGestureListener.onDragging(f2, i2, i3);
        }
    }
}
