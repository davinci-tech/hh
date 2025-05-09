package com.huawei.uikit.hwadvancednumberpicker.widget;

import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LruCache;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.Scroller;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.uikit.hwadvancednumberpicker.utils.HwCommonHandler;
import com.huawei.uikit.hwadvancednumberpicker.utils.HwFormatter;
import com.huawei.uikit.hwcommon.anim.HwCubicBezierInterpolator;
import com.huawei.uikit.hwunifiedinteract.widget.HwGenericEventDetector;
import defpackage.skh;
import defpackage.ski;
import defpackage.skl;
import defpackage.skm;
import defpackage.skw;
import defpackage.slb;
import defpackage.smr;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class HwAdvancedNumberPicker extends FrameLayout implements HwCommonHandler.MessageHandler {

    /* renamed from: a, reason: collision with root package name */
    protected Context f10588a;
    private ThreadPoolExecutor aa;
    private boolean ab;
    private final skw ac;
    private final skw ad;
    private a ae;
    private b af;
    private boolean ag;
    private final AnimatorSet ah;
    private boolean ai;
    private boolean aj;
    private float ak;
    private View al;
    private float am;
    private View an;
    private View ao;
    private VelocityTracker ap;
    private boolean aq;
    private String ar;
    private float as;
    private int at;
    private boolean au;
    private int av;
    private boolean aw;
    private boolean ax;
    private int ay;
    private final Object az;
    protected int b;
    private int ba;
    private c bb;
    private long bc;
    private float bd;
    private HashMap<Integer, String> be;
    private boolean bf;
    private int bg;
    private long bh;
    private int bi;
    private float bj;
    private float bk;
    private float bl;
    private float bm;
    private int bn;
    private boolean bo;
    private boolean bp;
    private int bq;
    private boolean br;
    private TextView bs;
    private boolean bt;
    private boolean bu;
    private final int bv;
    private boolean bw;
    private Drawable bx;
    private boolean by;
    private final int bz;
    private int ca;
    private int cb;
    private int cc;
    private int cd;
    private int ce;
    private int cf;
    private int cg;
    private int ch;
    private int ci;
    private float cj;
    private int ck;
    private int cl;
    private boolean cm;
    private int cn;
    private int co;
    private int[] cp;
    private int cq;
    private Handler cr;
    private int cs;
    private AccessibilityManager.AccessibilityStateChangeListener ct;
    private OnColorChangeListener cu;
    private int cv;
    private Scroller cw;
    private int cx;
    private int cy;
    private float cz;
    protected float d;
    private int da;
    private boolean db;
    private boolean dc;
    private SoundPool dd;
    private boolean de;
    private boolean df;
    private String dg;
    private String[] dh;
    private String di;
    private final boolean dj;
    private String dk;
    private String dl;
    private String dm;
    private OnScrollListener dn;

    /* renamed from: do, reason: not valid java name */
    private OnValueChangeListener f121do;
    private String dp;
    private int dq;
    private long dr;
    private int ds;
    private HwFormatter dt;
    protected int e;
    protected int f;
    protected int g;
    protected int h;
    public boolean i;
    protected int j;
    protected float k;
    protected int l;
    protected float m;
    protected int n;
    public int o;
    private final SparseArray<String> q;
    protected float r;
    private Paint u;
    private double v;
    private HwGenericEventDetector w;
    private int x;
    private Paint y;
    private int z;
    public static final HwFormatter c = new d();
    private static final String p = "HwAdvancedNumberPicker";
    private static final LruCache<String, Bitmap> t = new LruCache<>(62);
    private static final LruCache<String, Bitmap> s = new LruCache<>(62);

    public interface OnColorChangeListener {
        void onColorChange(HwAdvancedNumberPicker hwAdvancedNumberPicker);
    }

    public interface OnScrollListener {
        public static final int SCROLL_STATE_FLING = 2;
        public static final int SCROLL_STATE_IDLE = 0;
        public static final int SCROLL_STATE_TOUCH_SCROLL = 1;

        void onScrollStateChange(HwAdvancedNumberPicker hwAdvancedNumberPicker, int i);
    }

    public interface OnValueChangeListener {
        void onValueChange(HwAdvancedNumberPicker hwAdvancedNumberPicker, int i, int i2);
    }

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private boolean f10589a;

        /* synthetic */ a(HwAdvancedNumberPicker hwAdvancedNumberPicker, boolean z, d dVar) {
            this(z);
        }

        @Override // java.lang.Runnable
        public void run() {
            HwAdvancedNumberPicker.this.ai();
            if (this.f10589a) {
                HwAdvancedNumberPicker.this.postDelayed(this, 100L);
            } else {
                HwAdvancedNumberPicker.this.removeCallbacks(this);
            }
        }

        private a(boolean z) {
            this.f10589a = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(boolean z) {
            this.f10589a = z;
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HwAdvancedNumberPicker.this.z = 0;
            HwAdvancedNumberPicker hwAdvancedNumberPicker = HwAdvancedNumberPicker.this;
            int i = hwAdvancedNumberPicker.b;
            int i2 = hwAdvancedNumberPicker.e;
            if (i == i2) {
                hwAdvancedNumberPicker.ae();
                return;
            }
            int i3 = i - i2;
            if (Math.abs(i3) > HwAdvancedNumberPicker.this.av) {
                i3 += i3 > 0 ? -HwAdvancedNumberPicker.this.n : HwAdvancedNumberPicker.this.n;
            }
            HwAdvancedNumberPicker.this.v = 1.0d;
            if (Math.abs(i3) >= 1) {
                if (i3 < 0) {
                    HwAdvancedNumberPicker.this.ad.d(0, 0, i3);
                } else {
                    HwAdvancedNumberPicker.this.ad.d(0, i3, 0);
                }
            }
            HwAdvancedNumberPicker.this.invalidate();
        }
    }

    class c implements Runnable {
        private View b;
        private int d;

        /* synthetic */ c(HwAdvancedNumberPicker hwAdvancedNumberPicker, int i, View view, d dVar) {
            this(i, view);
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (HwAdvancedNumberPicker.this.az) {
                HwAdvancedNumberPicker.this.ad();
            }
            HwAdvancedNumberPicker.this.dZd_(this.b, this.d);
        }

        private c(int i, View view) {
            this.d = i;
            this.b = view;
        }
    }

    static final class d implements HwFormatter {

        /* renamed from: a, reason: collision with root package name */
        final StringBuilder f10590a;
        final Object[] b;
        final Formatter d;

        d() {
            StringBuilder sb = new StringBuilder(10);
            this.f10590a = sb;
            this.d = new Formatter(sb, Locale.ENGLISH);
            this.b = new Object[1];
        }

        @Override // com.huawei.uikit.hwadvancednumberpicker.utils.HwFormatter
        public String format(int i) {
            this.b[0] = Integer.valueOf(i);
            StringBuilder sb = this.f10590a;
            sb.delete(0, sb.length());
            this.d.format("%02d", this.b);
            return this.d.toString();
        }
    }

    class f implements View.OnClickListener {
        f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == null || !HwAdvancedNumberPicker.this.isEnabled()) {
                Log.w(HwAdvancedNumberPicker.p, "null view.");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (view.getId() == R.id.hwadvancednumberpicker_decrement) {
                HwAdvancedNumberPicker hwAdvancedNumberPicker = HwAdvancedNumberPicker.this;
                hwAdvancedNumberPicker.dg = hwAdvancedNumberPicker.dp;
                int i = HwAdvancedNumberPicker.this.bq + 1;
                if (i >= 0 && HwAdvancedNumberPicker.this.be.size() > i && !TextUtils.isEmpty((CharSequence) HwAdvancedNumberPicker.this.be.get(Integer.valueOf(i)))) {
                    HwAdvancedNumberPicker.this.h();
                }
            } else if (view.getId() == R.id.hwadvancednumberpicker_increment) {
                HwAdvancedNumberPicker hwAdvancedNumberPicker2 = HwAdvancedNumberPicker.this;
                hwAdvancedNumberPicker2.dg = hwAdvancedNumberPicker2.dp;
                int i2 = HwAdvancedNumberPicker.this.bq - 1;
                if (i2 >= 0 && HwAdvancedNumberPicker.this.be.size() > i2 && !TextUtils.isEmpty((CharSequence) HwAdvancedNumberPicker.this.be.get(Integer.valueOf(i2)))) {
                    HwAdvancedNumberPicker.this.g();
                }
            } else {
                Log.w(HwAdvancedNumberPicker.p, "nothing to do.");
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    class g implements HwGenericEventDetector.OnScrollListener {
        g() {
        }

        @Override // com.huawei.uikit.hwunifiedinteract.widget.HwGenericEventDetector.OnScrollListener
        public boolean onScrollBy(float f, float f2, MotionEvent motionEvent) {
            int axisValue = (int) motionEvent.getAxisValue(9);
            if (HwAdvancedNumberPicker.this.u()) {
                HwAdvancedNumberPicker hwAdvancedNumberPicker = HwAdvancedNumberPicker.this;
                hwAdvancedNumberPicker.scrollBy(0, hwAdvancedNumberPicker.n * axisValue);
            } else {
                HwAdvancedNumberPicker hwAdvancedNumberPicker2 = HwAdvancedNumberPicker.this;
                hwAdvancedNumberPicker2.scrollBy(0, hwAdvancedNumberPicker2.av * 2 * axisValue);
            }
            HwAdvancedNumberPicker.this.invalidate();
            return true;
        }
    }

    class h implements AccessibilityManager.AccessibilityStateChangeListener {
        final /* synthetic */ AccessibilityManager d;

        h(AccessibilityManager accessibilityManager) {
            this.d = accessibilityManager;
        }

        @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
        public void onAccessibilityStateChanged(boolean z) {
            HwAdvancedNumberPicker.this.dYY_(z, this.d);
        }
    }

    class i implements Runnable {
        i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HwAdvancedNumberPicker hwAdvancedNumberPicker = HwAdvancedNumberPicker.this;
            ski.dZk_(hwAdvancedNumberPicker.f10588a, hwAdvancedNumberPicker);
            HwAdvancedNumberPicker.this.invalidate();
        }
    }

    class j extends View.AccessibilityDelegate {
        j() {
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            if (accessibilityNodeInfo == null) {
                return;
            }
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            accessibilityNodeInfo.setClickable(false);
            accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
            accessibilityNodeInfo.setSelected(true);
        }
    }

    public HwAdvancedNumberPicker(Context context) {
        this(context, null);
    }

    private void aa() {
        a aVar = this.ae;
        if (aVar == null || this.bn == 1) {
            return;
        }
        aVar.d(false);
    }

    private void ab() {
        AccessibilityManager accessibilityManager;
        if (this.ct != null && (accessibilityManager = (AccessibilityManager) this.f10588a.getSystemService("accessibility")) != null) {
            accessibilityManager.removeAccessibilityStateChangeListener(this.ct);
        }
        this.ct = null;
    }

    private void ac() {
        ThreadPoolExecutor threadPoolExecutor = this.aa;
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdownNow();
        }
        this.aa = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ae() {
        String[] strArr = this.dh;
        if (strArr == null || strArr.length == 0) {
            this.bs.setText(b(this.ch));
        } else {
            this.bs.setText(strArr[(this.ch - this.cd) % strArr.length]);
        }
    }

    private void af() {
        b bVar = this.af;
        if (bVar != null) {
            removeCallbacks(bVar);
        }
    }

    private void ag() {
        this.m = getResources().getDimension(R.dimen._2131363885_res_0x7f0a082d);
        this.r = getResources().getDimension(R.dimen._2131363886_res_0x7f0a082e);
        this.k = getResources().getDimension(R.dimen._2131363887_res_0x7f0a082f);
        this.d = getResources().getDimension(R.dimen._2131363884_res_0x7f0a082c);
        int i2 = (int) (getResources().getConfiguration().fontScale * 100.0f);
        float dimension = getResources().getDimension(R.dimen._2131363875_res_0x7f0a0823);
        float dimension2 = getResources().getDimension(R.dimen._2131363876_res_0x7f0a0824);
        if (i2 == 115) {
            this.d -= dimension2;
            this.m -= dimension2;
            this.r -= dimension2;
            this.k -= dimension2;
        }
        if (i2 == 130) {
            this.d -= dimension;
            this.m -= dimension;
            this.r -= dimension;
            this.k -= dimension;
        }
        if (skh.d(this.f10588a)) {
            this.d -= dimension;
            this.m -= dimension;
            this.r -= dimension;
            this.k -= dimension;
            if (!DateFormat.is24HourFormat(this.f10588a)) {
                this.d -= dimension2;
                this.m -= dimension2;
                this.r -= dimension2;
                this.k -= dimension2;
            }
        }
        this.as = this.d;
    }

    private boolean ah() {
        VelocityTracker velocityTracker = this.ap;
        velocityTracker.computeCurrentVelocity(1000, this.cv);
        int yVelocity = (int) velocityTracker.getYVelocity();
        if (this.cw.isFinished() && this.cm && yVelocity > 0) {
            j(0);
            h(0);
            return true;
        }
        if (this.cw.isFinished()) {
            k(yVelocity);
        }
        this.bt = false;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ai() {
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f10588a.getSystemService("accessibility");
        if (this.bp && accessibilityManager != null && accessibilityManager.isEnabled()) {
            sendAccessibilityEvent(16384);
        }
    }

    private void getFocus() {
        if (!this.dc || isFocused()) {
            return;
        }
        requestFocus();
    }

    private void i() {
        f();
        ae();
        k();
    }

    private void r(HwAdvancedNumberPicker hwAdvancedNumberPicker) {
        OnColorChangeListener onColorChangeListener = this.cu;
        if (onColorChangeListener != null) {
            onColorChangeListener.onColorChange(hwAdvancedNumberPicker);
        }
    }

    private void setSelectorWheelState(int i2) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f10588a.getSystemService("accessibility");
        this.co = i2;
        if (accessibilityManager != null && this.bp && i2 == 2 && accessibilityManager.isEnabled()) {
            accessibilityManager.interrupt();
            this.bs.setContentDescription(this.ar);
            this.bs.sendAccessibilityEvent(16384);
            this.bs.setContentDescription(null);
        }
    }

    private void setSensitivityMode(int i2) {
        if (i2 == 0) {
            this.cj = 1.0f;
        } else if (i2 == 2) {
            this.cj = 0.6f;
        } else {
            this.cj = 0.6f;
        }
    }

    private void z() {
        boolean z = true;
        this.ai = true;
        a aVar = this.ae;
        if (aVar == null) {
            this.ae = new a(this, z, null);
        } else {
            aVar.d(false);
        }
    }

    protected HwGenericEventDetector.OnScrollListener a() {
        return new g();
    }

    public void a(String str, boolean z) {
        this.di = str;
        this.df = z;
    }

    public void a(boolean z) {
        this.cp = null;
        boolean b2 = skh.b(this.f10588a);
        if (z || b2) {
            this.cs = 7;
        } else {
            this.cs = 7;
        }
        int i2 = this.cs;
        this.bq = i2 / 2;
        this.cp = new int[i2];
        x();
        c();
        requestLayout();
    }

    protected void c() {
        f();
        if (this.cp.length - 1 == 0.0f) {
            return;
        }
        int top = ((this.bs.getTop() + this.bs.getBottom()) / 2) - (this.n * this.bq);
        this.b = top;
        this.e = top;
        ae();
    }

    protected void c(int i2) {
        int i3;
        this.aw = true;
        this.z = 0;
        int i4 = this.cv;
        if (i2 > i4 || i2 < (i4 = -i4)) {
            i3 = i4;
        } else {
            if (Math.abs(i2) < this.cn) {
                j(0);
                return;
            }
            i3 = i2;
        }
        if (i2 > 0) {
            this.ac.ebr_(this, 0, i3, 0, Integer.MAX_VALUE);
        } else {
            this.ac.ebr_(this, 0, i3, Integer.MIN_VALUE, 0);
        }
        int a2 = this.ac.a();
        if (a2 == 0 || this.n == 0) {
            this.v = 1.0d;
            return;
        }
        int i5 = this.av * 2;
        int i6 = ((int) (this.l * 1.44d)) + this.b;
        int i7 = i6 - i5;
        this.v = 1.0d;
        if (i2 >= 0) {
            this.v = ((r5 + (((a2 - (i6 - this.e) >= 0 ? r10 : 0) / i5) * i5)) + (r6 - i7)) / a2;
        } else {
            int i8 = -a2;
            this.v = ((r1 + (((i8 - (this.e - i7) >= 0 ? r2 : 0) / i5) * i5)) + (i6 - r6)) / i8;
        }
        invalidate();
    }

    protected float d(int i2, float f2, float f3) {
        float f4;
        float f5;
        int i3 = this.b;
        float f6 = i3;
        float f7 = i3 + (this.n * i2);
        float f8 = i2 < this.cp.length / 2 ? -1.0f : 1.0f;
        if (i2 == 3) {
            return f2;
        }
        if (i2 == 2 || i2 == 4) {
            f4 = f8 * 6.0f;
            f5 = this.cz;
        } else if (i2 == 1 || i2 == 5) {
            f4 = f8 * 1.0f;
            f5 = this.cz;
        } else {
            f4 = f8 * (-4.0f);
            f5 = this.cz;
        }
        return f7 + (f4 * f5) + ((f3 - f6) * 0.75f);
    }

    protected void d() {
        f fVar = new f();
        View findViewById = findViewById(R.id.hwadvancednumberpicker_increment);
        this.an = findViewById;
        findViewById.setOnClickListener(fVar);
        View findViewById2 = findViewById(R.id.hwadvancednumberpicker_decrement);
        this.al = findViewById2;
        findViewById2.setOnClickListener(fVar);
        View findViewById3 = findViewById(R.id.hwadvancednumberpicker_textview);
        this.ao = findViewById3;
        findViewById3.setFocusable(true);
        setEnabled(this.bf);
        setMiddleStateDrawable(this.bi);
        this.ao.setAccessibilityDelegate(new j());
    }

    protected void dZc_(AccessibilityManager accessibilityManager) {
        this.bw = isFocusable();
        this.bu = isFocusableInTouchMode();
        if (accessibilityManager != null && accessibilityManager.isEnabled()) {
            setFocusableInTouchMode(false);
            setFocusable(false);
        }
    }

    protected void dZd_(View view, int i2) {
        if (isHapticFeedbackEnabled()) {
            slb.ebC_(view, i2, 0);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent == null) {
            Log.w(p, "dispatchKeyEvent : event is null");
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 23 || keyCode == 66) {
            af();
        } else {
            if (keyCode == 19) {
                g();
                return true;
            }
            if (keyCode == 20) {
                h();
                return true;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent == null) {
            return true;
        }
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0016, code lost:
    
        if (r0 != 3) goto L16;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r3) {
        /*
            r2 = this;
            if (r3 != 0) goto Lb
            java.lang.String r3 = com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker.p
            java.lang.String r0 = "dispatchTouchEvent : event is null"
            android.util.Log.w(r3, r0)
            r3 = 0
            return r3
        Lb:
            int r0 = r3.getActionMasked()
            r1 = 1
            if (r0 == r1) goto L24
            r1 = 2
            if (r0 == r1) goto L19
            r1 = 3
            if (r0 == r1) goto L24
            goto L27
        L19:
            int r0 = r2.co
            if (r0 != r1) goto L27
            r2.af()
            r2.s()
            goto L27
        L24:
            r2.af()
        L27:
            boolean r3 = super.dispatchTouchEvent(r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            Log.w(p, "dispatchTrackballEvent : event is null");
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 3 || actionMasked == 1) {
            af();
        }
        return super.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        o();
        if (this.ah.isRunning() || this.co != 2) {
            long drawingTime = getDrawingTime();
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (getChildAt(i2).isShown()) {
                    drawChild(canvas, getChildAt(i2), drawingTime);
                }
            }
        }
    }

    protected HwGenericEventDetector e() {
        return new HwGenericEventDetector(getContext());
    }

    protected void e(int i2) {
        if (this.ch == i2) {
            return;
        }
        int f2 = f(i2);
        int i3 = this.ch;
        setValue(f2);
        e(i3, f2);
    }

    protected void f() {
        this.q.clear();
        g(getValue());
    }

    protected void g() {
        if (!this.bt && this.cw.isFinished() && u()) {
            this.z = 0;
            this.cw.startScroll(0, 0, 0, this.av * 2, 150);
        }
        invalidate();
    }

    @Override // android.view.View
    protected float getBottomFadingEdgeStrength() {
        return 0.9f;
    }

    public String[] getDisplayedValues() {
        String[] strArr = this.dh;
        return strArr != null ? (String[]) strArr.clone() : ski.c(this);
    }

    public int getMaxValue() {
        return this.ck;
    }

    public int getMinValue() {
        return this.cd;
    }

    public OnColorChangeListener getOnColorChangeListener() {
        return this.cu;
    }

    public float getSensitivity() {
        HwGenericEventDetector hwGenericEventDetector = this.w;
        if (hwGenericEventDetector != null) {
            return hwGenericEventDetector.b();
        }
        return 0.6f;
    }

    @Override // android.view.View
    public int getSolidColor() {
        return this.ba;
    }

    @Override // android.view.View
    protected float getTopFadingEdgeStrength() {
        return 0.9f;
    }

    public int getValue() {
        return this.ch;
    }

    public boolean getWrapSelectorWheel() {
        return this.ax;
    }

    protected void h() {
        if (!this.bt && this.cw.isFinished() && u()) {
            this.z = 0;
            this.cw.startScroll(0, 0, 0, this.av * (-2), 150);
        }
        invalidate();
    }

    @Override // com.huawei.uikit.hwadvancednumberpicker.utils.HwCommonHandler.MessageHandler
    public void handleMessage(Message message) {
        if (message.what != 103) {
            return;
        }
        r(this);
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return this.bf;
    }

    protected void j() {
        this.bt = false;
        if (this.cw.isFinished()) {
            this.ad.d();
            this.ac.d();
            c(0);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.dd = ski.dZi_(this);
        y();
        r();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a(!this.de && configuration.orientation == 2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        af();
        synchronized (this.az) {
            ski.dZj_(this, this.dd);
        }
        ac();
        ab();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        super.onDraw(canvas);
        if (this.co == 0) {
            return;
        }
        int save = canvas.save();
        if (this.co == 1) {
            Rect clipBounds = canvas.getClipBounds();
            clipBounds.inset(0, this.n);
            canvas.clipRect(clipBounds);
        }
        a(canvas, getResources().getConfiguration().orientation, skh.b(this.f10588a));
        if (this.bx != null) {
            a(canvas);
        }
        canvas.restoreToCount(save);
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (!isEnabled() || motionEvent == null) {
            return false;
        }
        this.au = true;
        HwGenericEventDetector hwGenericEventDetector = this.w;
        if (hwGenericEventDetector != null && this.ab && hwGenericEventDetector.eis_(motionEvent)) {
            return true;
        }
        if (this.ap == null) {
            this.ap = VelocityTracker.obtain();
        }
        this.ap.addMovement(motionEvent);
        if (motionEvent.getActionMasked() == 8) {
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            this.bh = timeInMillis;
            if (timeInMillis - this.bc > 5) {
                float axisValue = motionEvent.getAxisValue(9) * 960.0f;
                if (Math.abs(axisValue) > 4800.0f) {
                    axisValue = axisValue > 0.0f ? 4800.0f : -4800.0f;
                }
                if (!this.bt && this.cw.isFinished() && ski.c(this.ac, axisValue)) {
                    getFocus();
                    k((int) axisValue);
                } else {
                    Log.w(p, "action conflict, no need to scroll");
                }
                this.bc = this.bh;
            }
        }
        VelocityTracker velocityTracker = this.ap;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.ap = null;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (!this.bo) {
            return super.onHoverEvent(motionEvent);
        }
        if (motionEvent == null || motionEvent.getAction() == 10) {
            return true;
        }
        sendAccessibilityEvent(32768);
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || !this.bp || motionEvent == null) {
            return false;
        }
        if (this.ap == null) {
            this.ap = VelocityTracker.obtain();
        }
        this.ap.addMovement(motionEvent);
        this.ap.computeCurrentVelocity(1000, this.cv);
        this.ds = (int) this.ap.getYVelocity();
        this.aw = false;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            z();
            return dYZ_(motionEvent);
        }
        if (actionMasked == 1) {
            VelocityTracker velocityTracker = this.ap;
            velocityTracker.computeCurrentVelocity(1000, this.cv);
            int yVelocity = (int) velocityTracker.getYVelocity();
            if (this.cw.isFinished() && this.cm && yVelocity > 0) {
                j(0);
                h(0);
                return false;
            }
            if (this.cw.isFinished()) {
                k(yVelocity);
            }
            this.bt = false;
        } else if (actionMasked == 2 && ((int) Math.abs(motionEvent.getY() - this.ak)) > this.cq) {
            this.aj = false;
            h(1);
            setSelectorWheelState(2);
            p();
            return true;
        }
        return false;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        int measuredWidth = (getMeasuredWidth() - this.bs.getMeasuredWidth()) / 2;
        int measuredHeight = (getMeasuredHeight() - this.bs.getMeasuredHeight()) / 2;
        this.bs.layout(measuredWidth, measuredHeight, this.bs.getMeasuredWidth() + measuredWidth, this.bs.getMeasuredHeight() + measuredHeight);
        if (!this.br) {
            this.br = true;
            c();
            int height = getHeight();
            int i6 = this.cb;
            int i7 = this.cc;
            int i8 = ((height - i6) / 2) - i7;
            this.cl = i8;
            this.cg = i8 + (i7 * 2) + i6;
        }
        n();
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(d(i2, this.ce), d(i3, this.ca));
        setMeasuredDimension(a(this.bz, getMeasuredWidth(), i2), a(this.bv, getMeasuredHeight(), i3));
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent == null) {
            return;
        }
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setContentDescription(null);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || motionEvent == null) {
            return false;
        }
        if (this.ap == null) {
            this.ap = VelocityTracker.obtain();
        }
        this.ap.addMovement(motionEvent);
        this.ap.computeCurrentVelocity(1000, this.cv);
        this.ds = (int) this.ap.getYVelocity();
        this.aw = false;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            getFocus();
            this.dg = this.dp;
            this.bt = true;
            this.cr.sendEmptyMessage(103);
            z();
        } else if (actionMasked != 1) {
            if (actionMasked == 2) {
                return dZb_(motionEvent);
            }
            if (actionMasked == 3) {
                j();
            }
        } else if (ah()) {
            return false;
        }
        return true;
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (i2 == 0 && this.at != 0) {
            if (this.ap == null) {
                this.ap = VelocityTracker.obtain();
            }
            this.ap.computeCurrentVelocity(1000, this.cv);
            this.ds = (int) this.ap.getYVelocity();
            this.aw = false;
            ah();
        }
        this.at = i2;
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        if (this.co == 0 || !this.bp) {
            return;
        }
        int[] iArr = this.cp;
        int i4 = this.bq;
        if (i4 >= 0 && i4 < iArr.length) {
            boolean z = this.ax;
            if ((!z || this.cm) && i3 > 0 && iArr[i4] <= this.cd) {
                this.e = this.b;
                this.ac.d();
                return;
            } else if (!z && i3 < 0 && iArr[i4] >= this.ck) {
                this.e = this.b;
                this.ac.d();
                return;
            }
        }
        this.e += i3;
        b(iArr);
        e(iArr);
        this.au = false;
    }

    public void setAccessibilityOptimizationEnabled(boolean z) {
        this.ag = z;
    }

    public void setAnnouncedSuffix(String str) {
        this.dm = str;
    }

    public void setAuxiliarySelectedTextSize(float f2) {
        float a2 = ski.a(this.f10588a, f2);
        this.y.setTextSize(a2);
        this.d = a2;
        this.as = a2;
        invalidate();
    }

    public void setAuxiliaryUnselectedTextSize(float f2) {
        float e2 = ski.e(this.f10588a, f2);
        this.u.setTextSize(e2);
        this.m = e2;
        invalidate();
    }

    public void setDisplayedValues(String[] strArr) {
        if (Arrays.equals(this.dh, strArr)) {
            return;
        }
        if (strArr != null) {
            this.dh = (String[]) strArr.clone();
        } else {
            this.dh = null;
        }
        if (this.dh != null) {
            this.bs.setRawInputType(524289);
        } else {
            this.bs.setRawInputType(2);
        }
        i();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        this.bf = z;
        View view = this.ao;
        if (view != null) {
            view.setEnabled(z);
        }
        View view2 = this.an;
        if (view2 != null) {
            view2.setEnabled(z);
        }
        View view3 = this.al;
        if (view3 != null) {
            view3.setEnabled(z);
        }
        if (z || Float.compare(this.bk, 0.0f) == 0) {
            return;
        }
        setAlpha(this.bk);
    }

    public void setExtendScrollEnabled(boolean z) {
        this.ab = z;
    }

    public void setFlingAble(boolean z) {
        this.bp = z;
    }

    public void setFlingAnnounceType(int i2) {
        this.dq = i2;
    }

    public void setFormatter(HwFormatter hwFormatter) {
        if (hwFormatter == this.dt) {
            return;
        }
        this.dt = hwFormatter;
        f();
        ae();
    }

    public void setIsNeedStopDownScroll(boolean z) {
        this.cm = z;
    }

    public void setMaxValue(int i2) {
        if (this.ck == i2) {
            return;
        }
        if (i2 < 0) {
            Log.e(p, "maxValue must be >= 0");
            return;
        }
        this.ck = i2;
        if (i2 < this.ch) {
            this.ch = i2;
        }
        setWrapSelectorWheel(i2 - this.cd > this.cp.length);
        i();
    }

    public void setMiddleStateDrawable(int i2) {
        if (i2 != 0) {
            this.ao.setBackgroundResource(i2);
        }
    }

    public void setMinValue(int i2) {
        if (this.cd == i2) {
            return;
        }
        if (i2 < 0) {
            Log.e(p, "minValue must be >= 0");
            return;
        }
        this.cd = i2;
        int i3 = this.ch;
        int i4 = this.bz;
        if (i3 <= i4) {
            i3 = i4;
        }
        this.ch = i3;
        setWrapSelectorWheel(this.ck - i2 > this.cp.length);
        i();
    }

    public void setOnColorChangeListener(OnColorChangeListener onColorChangeListener) {
        this.cu = onColorChangeListener;
    }

    public void setOnLongPressUpdateInterval(long j2) {
        this.dr = j2;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.dn = onScrollListener;
    }

    public void setOnValueChangedListener(OnValueChangeListener onValueChangeListener) {
        this.f121do = onValueChangeListener;
    }

    @Deprecated
    public void setSecondPaintColor(int i2) {
        this.bg = i2;
        this.u.setColor(i2);
        invalidate();
    }

    public void setSecondaryPaintColor(int i2) {
        this.bg = i2;
        this.u.setColor(i2);
        invalidate();
    }

    public void setSelectedFocusedTextColor(int i2) {
        this.f = i2;
    }

    public void setSelectedUnfocusedTextColor(int i2) {
        this.j = i2;
    }

    public void setSelectionDivider(Drawable drawable) {
        this.bx = drawable;
    }

    public void setSelectionDividerHeight(int i2) {
        this.cc = i2;
    }

    public void setSelectorPaintColor(int i2) {
        this.ay = i2;
        this.y.setColor(i2);
        invalidate();
    }

    public void setSelectorPaintColorInSingleMode(int i2) {
    }

    public void setSensitivity(float f2) {
        HwGenericEventDetector hwGenericEventDetector = this.w;
        if (hwGenericEventDetector != null) {
            hwGenericEventDetector.e(f2);
        }
    }

    @Deprecated
    public void setSlaverPaintColor(int i2) {
        this.bg = i2;
        this.u.setColor(i2);
        invalidate();
    }

    public void setStringUnit(String str) {
        if (this.dh != null) {
            Log.w(p, "mDisplayedValues is not null, unit can not be set.");
        } else {
            if (str.isEmpty()) {
                return;
            }
            this.db = true;
            this.dk = str;
        }
    }

    public void setValue(int i2) {
        if (this.ch == i2) {
            return;
        }
        int i3 = this.cd;
        if (i2 < i3) {
            i2 = this.ax ? this.ck : i3;
        }
        int i4 = this.ck;
        if (i2 <= i4) {
            i3 = i2;
        } else if (!this.ax) {
            i3 = i4;
        }
        this.ch = i3;
        f();
        ae();
        invalidate();
    }

    public void setWrapSelectorWheel(boolean z) {
        if (z && this.ck - this.cd < this.cp.length) {
            Log.e(p, "Range less than selector items count.");
        } else if (z != this.ax) {
            this.ax = z;
        }
    }

    public HwAdvancedNumberPicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100178_res_0x7f060212);
    }

    private void k() {
        int dZg_;
        if (this.dj && this.ce != (dZg_ = ski.dZg_(this.y, this.dh, this.ck) + this.bs.getPaddingLeft() + this.bs.getPaddingRight())) {
            int i2 = this.bz;
            if (dZg_ <= i2) {
                dZg_ = i2;
            }
            this.ce = dZg_;
            invalidate();
        }
    }

    private void l() {
        skw skwVar = this.ac;
        if (skwVar.h()) {
            this.v = 1.0d;
            skwVar = this.ad;
            if (skwVar.h()) {
                return;
            }
        }
        skwVar.c();
        this.x = (int) skwVar.e();
        int b2 = skwVar.b();
        if (this.z == 0) {
            this.z = skwVar.g();
        }
        e((int) (b2 * this.v), skwVar);
    }

    private void n() {
        String a2 = ski.a(this.dh);
        if (TextUtils.isEmpty(a2)) {
            return;
        }
        ski.b bVar = new ski.b();
        bVar.f17089a = a2;
        bVar.d = (int) this.as;
        bVar.e = this.h;
        bVar.c = this.cx;
        this.d = ski.dZf_(this, this.y, bVar) - this.cx;
        v();
    }

    private void o() {
        l();
        t();
    }

    private void o(int i2) {
        if (this.aw) {
            return;
        }
        float abs = Math.abs(this.ds);
        float f2 = this.bd;
        if (abs < f2) {
            e(i2, 2, true);
            return;
        }
        if (abs >= f2 && abs < this.bj) {
            e(i2, 3, true);
            return;
        }
        if (abs >= this.bj && abs < this.bl) {
            e(i2, 4, true);
            return;
        }
        if (abs >= this.bl && abs < this.bm) {
            e(i2, 5, true);
        } else if (abs >= this.bm) {
            e(i2, 6, true);
        }
    }

    private void q() {
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f10588a.getSystemService("accessibility");
        this.bo = accessibilityManager != null && accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled();
        dZc_(accessibilityManager);
    }

    private void r() {
        AccessibilityManager accessibilityManager;
        if (this.ct == null && (accessibilityManager = (AccessibilityManager) this.f10588a.getSystemService("accessibility")) != null) {
            h hVar = new h(accessibilityManager);
            this.ct = hVar;
            accessibilityManager.addAccessibilityStateChangeListener(hVar);
        }
    }

    private void s() {
        if (this.ac.h()) {
            return;
        }
        this.ac.d();
    }

    private void t() {
        if (this.cw.isFinished()) {
            return;
        }
        this.cw.computeScrollOffset();
        dYX_(this.cw.getCurrY(), this.cw);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean u() {
        return this.ac.h() && this.ad.h();
    }

    private void v() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        skh.dYV_(skh.dYT_(this.f10588a), paint);
        paint.setColor(this.ay);
        paint.setTextSize(this.d);
        this.y = paint;
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setTextAlign(Paint.Align.CENTER);
        skh.dYV_(skh.dYU_(this.f10588a), paint2);
        paint2.setColor(this.bg);
        paint2.setTextSize(this.d);
        this.u = paint2;
    }

    private void w() {
        FrameLayout.inflate(getContext(), R.layout.hwadvancednumberpicker, this);
        a(getContext());
        v();
        x();
    }

    private void x() {
        try {
            this.ca = ((int) getResources().getDimension(R.dimen._2131363870_res_0x7f0a081e)) * (this.cp.length - 1);
        } catch (Resources.NotFoundException unused) {
            Log.w(p, "resources not found");
        }
        if (this.bp) {
            if (isInEditMode()) {
                setSelectorWheelState(1);
            } else {
                setSelectorWheelState(2);
                p();
            }
        }
        ae();
        this.br = false;
    }

    private void y() {
        if (this.aa == null) {
            this.aa = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new skm());
        }
    }

    class e extends View.AccessibilityDelegate {

        /* renamed from: a, reason: collision with root package name */
        private String f10591a;
        private String c;

        private e() {
            this.f10591a = "";
            this.c = "";
        }

        private void a() {
            if (!HwAdvancedNumberPicker.this.ac.h()) {
                HwAdvancedNumberPicker.this.dl = HwAdvancedNumberPicker.this.dp + "";
                return;
            }
            if (HwAdvancedNumberPicker.this.ad.h()) {
                c();
                return;
            }
            HwAdvancedNumberPicker.this.dl = HwAdvancedNumberPicker.this.dp + HwAdvancedNumberPicker.this.dm;
        }

        private void c() {
            String str = this.c;
            if (str == null || str.equals(HwAdvancedNumberPicker.this.dp)) {
                HwAdvancedNumberPicker hwAdvancedNumberPicker = HwAdvancedNumberPicker.this;
                hwAdvancedNumberPicker.dl = hwAdvancedNumberPicker.dm;
                return;
            }
            HwAdvancedNumberPicker.this.dl = HwAdvancedNumberPicker.this.dp + HwAdvancedNumberPicker.this.dm;
        }

        private void e() {
            if (!HwAdvancedNumberPicker.this.ag) {
                HwAdvancedNumberPicker.this.dl = HwAdvancedNumberPicker.this.dp + HwAdvancedNumberPicker.this.dm;
                this.c = HwAdvancedNumberPicker.this.dp + "";
                return;
            }
            if (HwAdvancedNumberPicker.this.bn == 0) {
                HwAdvancedNumberPicker.this.dl = HwAdvancedNumberPicker.this.dp + HwAdvancedNumberPicker.this.dm;
            } else if (HwAdvancedNumberPicker.this.bn == 1) {
                HwAdvancedNumberPicker hwAdvancedNumberPicker = HwAdvancedNumberPicker.this;
                hwAdvancedNumberPicker.dl = hwAdvancedNumberPicker.dp;
            } else if (HwAdvancedNumberPicker.this.bn == 2) {
                a();
            }
            this.c = HwAdvancedNumberPicker.this.dp + "";
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            if (view == null || accessibilityEvent == null) {
                return;
            }
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName("HwAdvancedNumberPicker");
            accessibilityEvent.getText().clear();
            if (HwAdvancedNumberPicker.this.cw.isFinished()) {
                accessibilityEvent.setContentDescription(HwAdvancedNumberPicker.this.dl);
            } else {
                accessibilityEvent.setContentDescription(null);
            }
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            if (view == null || accessibilityEvent == null) {
                return;
            }
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setContentDescription(null);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(View view, int i) {
            e();
            if (HwAdvancedNumberPicker.this.dl == null || HwAdvancedNumberPicker.this.dl.equals(this.f10591a)) {
                return;
            }
            this.f10591a = HwAdvancedNumberPicker.this.dl;
            super.sendAccessibilityEvent(view, i);
        }

        /* synthetic */ e(HwAdvancedNumberPicker hwAdvancedNumberPicker, d dVar) {
            this();
        }
    }

    public HwAdvancedNumberPicker(Context context, AttributeSet attributeSet, int i2) {
        super(c(context, i2), attributeSet, i2);
        this.b = Integer.MIN_VALUE;
        this.az = new Object();
        this.bc = 0L;
        this.bh = 0L;
        this.bq = 3;
        this.cs = 0;
        this.cy = 15;
        this.da = 11;
        this.dr = 300L;
        this.q = new SparseArray<>();
        this.aw = false;
        this.bn = 0;
        this.bo = false;
        this.bw = false;
        this.bu = false;
        this.bt = false;
        this.by = false;
        this.cj = 0.6f;
        this.cm = false;
        this.cr = new HwCommonHandler(this);
        this.db = false;
        this.dk = "";
        this.dg = "";
        this.dp = "";
        this.dl = "";
        this.dm = "";
        this.dq = 3;
        this.v = 1.0d;
        this.ab = false;
        this.ai = false;
        this.ae = new a(this, true, null);
        this.ag = false;
        this.au = false;
        this.at = 0;
        this.be = new HashMap<>();
        this.f10588a = getContext();
        this.bv = -1;
        this.bz = 96;
        this.ce = -1;
        this.dj = false;
        this.ah = new AnimatorSet();
        this.ac = new skw();
        this.ad = new skw();
        a(this.f10588a, attributeSet, i2);
        post(new i());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        int i2;
        SoundPool soundPool = this.dd;
        if (soundPool != null && (i2 = this.o) != 0 && this.i) {
            soundPool.play(i2, 1.0f, 1.0f, 0, 0, 1.0f);
        } else {
            Log.w(p, "SoundPool is not initialized properly!");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1, types: [android.content.res.TypedArray] */
    /* JADX WARN: Type inference failed for: r7v3, types: [com.huawei.uikit.hwunifiedinteract.widget.HwGenericEventDetector] */
    private void dZa_(Context context, AttributeSet attributeSet, int i2) {
        q();
        int applyDimension = (int) TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics());
        int dimension = (int) this.f10588a.getResources().getDimension(R.dimen._2131363878_res_0x7f0a0826);
        int applyDimension2 = (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{android.R.attr.enabled, R.attr._2131100275_res_0x7f060273, R.attr._2131100338_res_0x7f0602b2, R.attr._2131100404_res_0x7f0602f4, R.attr._2131100405_res_0x7f0602f5, R.attr._2131100407_res_0x7f0602f7, R.attr._2131100409_res_0x7f0602f9, R.attr._2131100414_res_0x7f0602fe, R.attr._2131100418_res_0x7f060302, R.attr._2131100419_res_0x7f060303, R.attr._2131100420_res_0x7f060304, R.attr._2131100433_res_0x7f060311, R.attr._2131100434_res_0x7f060312, R.attr._2131100507_res_0x7f06035b, R.attr._2131100508_res_0x7f06035c, R.attr._2131100520_res_0x7f060368, R.attr._2131100530_res_0x7f060372, R.attr._2131100531_res_0x7f060373, R.attr._2131100532_res_0x7f060374, R.attr._2131100536_res_0x7f060378, R.attr._2131100595_res_0x7f0603b3, R.attr._2131100596_res_0x7f0603b4}, i2, 0);
        try {
            try {
                this.ba = obtainStyledAttributes.getColor(18, 0);
                this.bx = obtainStyledAttributes.getDrawable(8);
                this.cc = obtainStyledAttributes.getDimensionPixelSize(10, (applyDimension - dimension) - 1);
                this.cb = obtainStyledAttributes.getDimensionPixelSize(9, applyDimension2);
                this.ay = obtainStyledAttributes.getColor(3, -16744961);
                this.bg = obtainStyledAttributes.getColor(17, -452984832);
                this.ci = obtainStyledAttributes.getInt(15, 1);
                this.bf = obtainStyledAttributes.getBoolean(0, true);
                this.bi = obtainStyledAttributes.getResourceId(19, 0);
                this.bk = obtainStyledAttributes.getFloat(1, 0.0f);
            } catch (Resources.NotFoundException unused) {
                Log.w(p, "TypedArray get resource error");
            }
            obtainStyledAttributes.recycle();
            obtainStyledAttributes = e();
            this.w = obtainStyledAttributes;
            if (obtainStyledAttributes != 0) {
                obtainStyledAttributes.eit_(this, a());
                setSensitivityMode(this.ci);
                this.w.e(this.cj);
            }
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    private void g(int i2) {
        for (int i3 = 0; i3 < this.cp.length; i3++) {
            int f2 = f((i2 + i3) - this.bq);
            this.cp[i3] = f2;
            a(f2);
        }
    }

    private void j(int i2) {
        b bVar = this.af;
        if (bVar == null) {
            this.af = new b();
        } else {
            removeCallbacks(bVar);
        }
        postDelayed(this.af, i2);
    }

    private void h(int i2) {
        if (this.bn == i2) {
            return;
        }
        this.bn = i2;
        OnScrollListener onScrollListener = this.dn;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChange(this, i2);
        }
    }

    private void k(int i2) {
        this.ac.d();
        this.ad.d();
        if (Math.abs(i2) > this.cn) {
            c(i2);
            h(2);
        } else if (this.aq) {
            if (u()) {
                j(0);
                h(0);
            }
        } else {
            j(skl.e);
        }
        this.ap.recycle();
        this.ap = null;
    }

    private void a(Context context, AttributeSet attributeSet, int i2) {
        this.ar = this.f10588a.getString(R.string._2130851230_res_0x7f02359e);
        this.l = (int) getResources().getDimension(R.dimen._2131363883_res_0x7f0a082b);
        this.n = (int) getResources().getDimension(R.dimen._2131363882_res_0x7f0a082a);
        this.av = (int) (this.l * 1.44d);
        this.de = getResources().getInteger(R.integer._2131623948_res_0x7f0e000c) == 2;
        this.dc = getResources().getInteger(R.integer._2131623948_res_0x7f0e000c) == 8;
        boolean z = getResources().getConfiguration().orientation == 2;
        boolean b2 = skh.b(context);
        if ((this.de || !z) && !b2 && !this.dc) {
            this.cs = 7;
        } else {
            this.cs = 7;
        }
        int i3 = this.cs;
        this.bq = i3 / 2;
        this.cp = new int[i3];
        dZa_(super.getContext(), attributeSet, i2);
        ag();
        this.bp = true;
        setWillNotDraw(false);
        setSelectorWheelState(0);
        this.cz = getResources().getDimension(R.dimen._2131363860_res_0x7f0a0814);
        this.h = (int) getResources().getDimension(R.dimen._2131363868_res_0x7f0a081c);
        this.g = (int) getResources().getDimension(R.dimen._2131363866_res_0x7f0a081a);
        this.cx = (int) getResources().getDimension(R.dimen._2131363888_res_0x7f0a0830);
        w();
        this.cw = new Scroller(this.f10588a, new HwCubicBezierInterpolator(0.2f, 0.0f, 0.2f, 1.0f));
        y();
        setAccessibilityDelegate(new e(this, null));
        ski.d(context, this, this.by);
    }

    private int i(int i2) {
        int i3 = this.ck;
        int i4 = this.cd;
        if (i3 == i4) {
            return i4;
        }
        if (i2 > i3) {
            return (i4 + ((i2 - i3) % (i3 - i4))) - 1;
        }
        return i2 < i4 ? (i3 - ((i4 - i2) % (i3 - i4))) + 1 : i2;
    }

    protected void e(int i2, int i3, boolean z) {
        ThreadPoolExecutor threadPoolExecutor;
        e(i2);
        if (!z || (threadPoolExecutor = this.aa) == null) {
            return;
        }
        c cVar = this.bb;
        if (cVar != null) {
            threadPoolExecutor.remove(cVar);
        }
        c cVar2 = new c(this, i3, this, null);
        this.bb = cVar2;
        this.aa.execute(cVar2);
    }

    private String b(int i2) {
        HwFormatter hwFormatter = this.dt;
        return hwFormatter != null ? hwFormatter.format(i2) : String.valueOf(i2);
    }

    private void e(int i2, int i3) {
        OnValueChangeListener onValueChangeListener = this.f121do;
        if (onValueChangeListener != null) {
            onValueChangeListener.onValueChange(this, i2, this.ch);
        }
    }

    private int f(int i2) {
        return this.ax ? i(i2) : i2;
    }

    private void p() {
        this.ah.cancel();
        this.bs.setVisibility(4);
    }

    private boolean dZb_(MotionEvent motionEvent) {
        float y = motionEvent.getY();
        if (this.cm && y - this.ak > 0.0f) {
            return false;
        }
        if (!this.cw.isFinished()) {
            return true;
        }
        m();
        b(y);
        return true;
    }

    private static Context c(Context context, int i2) {
        return smr.b(context, i2, 2131952126);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dYY_(boolean z, AccessibilityManager accessibilityManager) {
        if (accessibilityManager == null) {
            return;
        }
        this.bo = accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled();
        if (z) {
            this.bw = isFocusable();
            this.bu = isFocusableInTouchMode();
            setFocusableInTouchMode(false);
            setFocusable(false);
            return;
        }
        setFocusableInTouchMode(this.bu);
        setFocusable(this.bw);
    }

    private void m() {
        if (this.ai) {
            a aVar = this.ae;
            if (aVar != null) {
                removeCallbacks(aVar);
                this.ae.d(true);
                postDelayed(this.ae, 100L);
            }
            this.ai = false;
        }
    }

    private void e(int[] iArr) {
        while (true) {
            int i2 = this.e;
            int i3 = i2 - this.b;
            if (i3 >= (-this.av)) {
                return;
            }
            this.e = i2 + ((i3 <= (-this.n) && u() && this.au) ? this.n : this.av * 2);
            System.arraycopy(iArr, 1, iArr, 0, iArr.length - 1);
            int i4 = iArr[iArr.length - 2] + 1;
            if (this.ax && i4 > this.ck) {
                i4 = this.cd;
            }
            iArr[iArr.length - 1] = i4;
            a(i4);
            int i5 = this.bq;
            if (i5 >= 0 && i5 < iArr.length) {
                o(iArr[i5]);
                d(iArr[this.bq]);
                if (!this.ax && iArr[this.bq] >= this.ck) {
                    this.e = this.b;
                }
            }
        }
    }

    private void a(Context context) {
        this.bs = (TextView) findViewById(R.id.hwadvancednumberpicker_input);
        this.cq = ViewConfiguration.getTapTimeout();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.cq = viewConfiguration.getScaledTouchSlop();
        this.cn = 400;
        int scaledMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity() / 4;
        this.cv = scaledMaximumFlingVelocity;
        float f2 = scaledMaximumFlingVelocity;
        this.bd = 0.1f * f2;
        this.bj = 0.25f * f2;
        this.bl = 0.55f * f2;
        this.bm = f2 * 0.8f;
        this.cf = (int) this.bs.getTextSize();
        d();
    }

    private boolean dYZ_(MotionEvent motionEvent) {
        this.am = motionEvent.getY();
        this.ak = motionEvent.getY();
        af();
        this.ah.cancel();
        this.aj = false;
        this.aq = true;
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        if (this.co == 2) {
            boolean u = u();
            if (!u) {
                this.ac.d();
                this.ad.d();
                h(0);
            }
            this.aj = u;
            this.aq = true;
            p();
            return this.dc;
        }
        this.aq = false;
        setSelectorWheelState(2);
        p();
        return this.dc;
    }

    private int d(int i2, int i3) {
        return i3 == -1 ? i2 : c(i2, i3);
    }

    private void d(int i2) {
        if (this.aw) {
            float abs = Math.abs(this.x);
            float f2 = this.bd;
            if (abs < f2) {
                e(i2, 2, true);
                return;
            }
            if (abs >= f2 && abs < this.bj) {
                e(i2, 3, true);
                return;
            }
            if (abs >= this.bj && abs < this.bl) {
                e(i2, 4, true);
                return;
            }
            if (abs >= this.bl && abs < this.bm) {
                e(i2, 5, true);
            } else if (abs >= this.bm) {
                e(i2, 6, true);
            }
        }
    }

    private void b(float f2) {
        Object systemService = getContext().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (systemService instanceof WindowManager) {
            ((WindowManager) systemService).getDefaultDisplay().getRealMetrics(displayMetrics);
        }
        int i2 = displayMetrics.heightPixels;
        int i3 = displayMetrics.widthPixels;
        if (i2 <= i3) {
            i2 = i3;
        }
        if (f2 <= i2 && f2 >= (-i2)) {
            if ((this.aj || this.bn != 1) && ((int) Math.abs(f2 - this.ak)) > this.cq) {
                this.aj = false;
                h(1);
            }
            scrollBy(0, (int) (f2 - this.am));
            invalidate();
            this.am = f2;
            return;
        }
        Log.e(p, "Illegal event locationY.");
    }

    private void b(int[] iArr) {
        while (true) {
            int i2 = this.e;
            int i3 = i2 - this.b;
            if (i3 <= this.av) {
                return;
            }
            this.e = i2 - ((i3 >= this.n && u() && this.au) ? this.n : this.av * 2);
            System.arraycopy(iArr, 0, iArr, 1, iArr.length - 1);
            int i4 = iArr[1] - 1;
            if (this.ax && i4 < this.cd) {
                i4 = this.ck;
            }
            iArr[0] = i4;
            a(i4);
            int i5 = this.bq;
            if (i5 >= 0 && i5 < iArr.length) {
                o(iArr[i5]);
                d(iArr[this.bq]);
                if (!this.ax && iArr[this.bq] <= this.cd) {
                    this.e = this.b;
                }
            }
        }
    }

    private void dYX_(int i2, Scroller scroller) {
        scrollBy(0, i2 - this.z);
        this.z = i2;
        if (scroller.isFinished()) {
            ai();
        } else {
            invalidate();
        }
    }

    private void e(int i2, skw skwVar) {
        scrollBy(0, i2 - this.z);
        this.z = i2;
        if (skwVar.h()) {
            aa();
        } else {
            invalidate();
        }
    }

    private void a(Canvas canvas, int i2, boolean z) {
        int[] iArr = this.cp;
        float right = (getRight() - getLeft()) / 2.0f;
        float f2 = this.e;
        this.be.clear();
        for (int i3 = 0; i3 < iArr.length; i3++) {
            String str = this.q.get(iArr[i3]);
            if (str != null) {
                String d2 = ski.d(this.dt, str, str.startsWith("0"), this.df, this.di);
                float d3 = d(i3, f2, this.e);
                String d4 = d(d2);
                if (i3 == this.bq) {
                    if (this.db) {
                        d4 = d4 + this.dk;
                    }
                    float d5 = d(i3, d3);
                    canvas.save();
                    Bitmap dZh_ = ski.dZh_(t, d4, this.y);
                    canvas.scale(d5, d5, right, d3);
                    canvas.drawBitmap(dZh_, right - (dZh_.getWidth() / 2.0f), d3 - (dZh_.getHeight() / 2.0f), this.y);
                    canvas.restore();
                    if (!this.de) {
                        this.ao.setContentDescription(d4);
                    }
                    this.dp = d4;
                } else {
                    float d6 = d(i3, d3);
                    canvas.save();
                    Bitmap dZh_2 = ski.dZh_(s, d4, this.u);
                    canvas.scale(d6, d6, right, d3);
                    canvas.drawBitmap(dZh_2, right - (dZh_2.getWidth() / 2.0f), d3 - (dZh_2.getHeight() / 2.0f), this.u);
                    canvas.restore();
                    e(i3, d4);
                }
                f2 += this.n;
                this.be.put(Integer.valueOf(i3), d4);
            }
        }
        ski.dZe_(this.be, this.an, this.al);
    }

    private float d(int i2, float f2) {
        if (Float.compare(this.d, this.k) <= 0) {
            return 1.0f;
        }
        return 1.0f - (((this.d - this.k) * Math.abs(f2 - ((this.n * 3) + this.b))) / ((this.n * 3) * this.d));
    }

    private void e(int i2, String str) {
        View view;
        if (str == null || (view = this.al) == null || this.an == null) {
            return;
        }
        if (i2 == this.bq + 1) {
            view.setContentDescription(str);
        }
        if (i2 == this.bq - 1) {
            this.an.setContentDescription(str);
        }
    }

    private String d(String str) {
        return (this.de || this.dc) ? TextUtils.ellipsize(str, new TextPaint(this.u), (getWidth() - getPaddingLeft()) - getPaddingRight(), TextUtils.TruncateAt.END).toString() : str;
    }

    private void a(Canvas canvas) {
        this.cy = (int) this.f10588a.getResources().getDimension(R.dimen._2131363879_res_0x7f0a0827);
        int dimension = (int) this.f10588a.getResources().getDimension(R.dimen._2131363877_res_0x7f0a0825);
        this.da = dimension;
        int i2 = this.cl;
        int i3 = this.cy;
        int i4 = ((i2 - i3) - dimension) + (i3 - dimension);
        this.bx.setBounds(0, i4, getRight() + 50, this.cc + i4);
        this.bx.draw(canvas);
        int i5 = this.cg;
        int i6 = this.da;
        int i7 = (i5 + (i6 * 2)) - (this.cy - i6);
        this.bx.setBounds(0, i7 - this.cc, getRight() + 50, i7);
        this.bx.draw(canvas);
    }

    private int c(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2) < i3 ? View.MeasureSpec.getSize(i2) : i3;
        if (mode == Integer.MIN_VALUE) {
            return View.MeasureSpec.makeMeasureSpec(size, 1073741824);
        }
        if (mode == 0) {
            return View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
        }
        if (mode == 1073741824) {
            return i2;
        }
        throw new IllegalArgumentException("Unknown measure mode: " + mode);
    }

    private int a(int i2, int i3, int i4) {
        if (i2 == -1) {
            return i3;
        }
        if (i2 <= i3) {
            i2 = i3;
        }
        return FrameLayout.resolveSizeAndState(i2, i4, 0);
    }

    private void a(int i2) {
        String str;
        int i3 = i2 - this.cd;
        if (this.q.get(i2) != null) {
            return;
        }
        if (i2 < this.cd || i2 > this.ck) {
            str = "";
        } else {
            String[] strArr = this.dh;
            if (strArr == null) {
                str = b(i2);
            } else if (i3 >= 0 && i3 < strArr.length) {
                str = strArr[i3];
            } else {
                str = this.q.get(i2);
            }
        }
        this.q.put(i2, str);
    }
}
