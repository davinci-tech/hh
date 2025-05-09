package com.huawei.openalliance.ad.views;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.ads.uiengine.common.IProgressButton;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.dd;
import java.util.Locale;

/* loaded from: classes5.dex */
public class ProgressButton extends View implements View.OnClickListener, IProgressButton {
    private Drawable A;
    private long B;
    private Paint C;
    private boolean D;
    private IProgressButton.ProgressButtonResetListener E;
    private int F;

    /* renamed from: a, reason: collision with root package name */
    protected Rect f7999a;
    protected Paint b;
    protected CharSequence c;
    String d;
    int e;
    int f;
    protected Drawable g;
    protected final byte[] h;
    protected int i;
    protected int j;
    protected int k;
    protected boolean l;
    protected boolean m;
    protected int n;
    private final String o;
    private int p;
    private boolean q;
    private boolean r;
    private int s;
    private int t;
    private int u;
    private int v;
    private float w;
    private Float x;
    private int y;
    private int z;

    void a(float f, boolean z) {
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public int getCancelBtnHeight(int i) {
        return i;
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public View getProgressBtn() {
        return this;
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public AppStatus getStatus() {
        return null;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        boolean z;
        synchronized (this.h) {
            z = drawable == this.g || super.verifyDrawable(drawable);
        }
        return z;
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void updateLayoutHeight() {
        this.u = ((int) this.w) + getPaddingTop() + getPaddingBottom();
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setTextSize(float f) {
        this.w = f;
        setOriginTextSize(Float.valueOf(f));
        Paint paint = this.b;
        if (paint != null) {
            paint.setAntiAlias(true);
            this.b.setTextSize(this.w);
        }
        d();
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setTextInner(CharSequence charSequence, boolean z) {
        this.l = z;
        setText(charSequence);
    }

    public void setTextColor(int i) {
        this.v = i;
        Paint paint = this.b;
        if (paint != null) {
            paint.setColor(i);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setText(CharSequence charSequence) {
        ho.a(this.o, "setText:%s, need safepadding: %s", charSequence, Boolean.valueOf(this.l));
        synchronized (this.h) {
            c();
            this.c = String.valueOf(charSequence).toUpperCase(Locale.getDefault());
            Float f = this.x;
            float a2 = a(this.c, f != null ? f.floatValue() : this.w);
            if (!TextUtils.isEmpty(charSequence) && Math.abs(a2 - this.w) >= 0.5f) {
                setTextSize(a2);
            }
            if (getWidth() <= 0 && !this.r) {
                post(new Runnable() { // from class: com.huawei.openalliance.ad.views.ProgressButton.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (ho.a()) {
                            ho.a(ProgressButton.this.o, "view post, resetButtonSize");
                        }
                        ProgressButton.this.a();
                    }
                });
                postInvalidate();
            }
            a();
            postInvalidate();
        }
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setShowCancelBtn(boolean z) {
        this.m = z;
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setResetWidth(boolean z) {
        this.r = z;
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setResetListener(IProgressButton.ProgressButtonResetListener progressButtonResetListener) {
        this.E = progressButtonResetListener;
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setProgressDrawable(Drawable drawable, int i) {
        boolean z;
        synchronized (this.h) {
            Drawable drawable2 = this.g;
            if (drawable2 == null || drawable == drawable2) {
                z = false;
            } else {
                drawable2.setCallback(null);
                this.g.setState(new int[0]);
                z = true;
            }
            if (drawable != null) {
                drawable.setCallback(this);
            }
            this.g = drawable;
            this.A = drawable;
            if (z) {
                a(getWidth(), getHeight());
                if (i < 0) {
                    i = 0;
                }
                int i2 = this.z;
                if (i > i2) {
                    i = i2;
                }
                this.y = i;
                a(i, false, false);
            } else {
                setProgress(i);
            }
        }
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setProgressDrawable(Drawable drawable) {
        setProgressDrawable(drawable, 0);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setProgress(int i) {
        synchronized (this.h) {
            a(i, false);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setPaintTypeface(Typeface typeface, int i) {
        if (i <= 0) {
            this.b.setFakeBoldText(false);
            this.b.setTextSkewX(0.0f);
            setPaintTypeface(typeface);
        } else {
            Typeface defaultFromStyle = typeface == null ? Typeface.defaultFromStyle(i) : Typeface.create(typeface, i);
            setPaintTypeface(defaultFromStyle);
            int i2 = (~(defaultFromStyle != null ? defaultFromStyle.getStyle() : 0)) & i;
            this.b.setFakeBoldText((i2 & 1) != 0);
            this.b.setTextSkewX((i2 & 2) != 0 ? -0.25f : 0.0f);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setPaintTypeface(Typeface typeface) {
        synchronized (this.h) {
            this.b.setTypeface(typeface);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setMinWidth(int i) {
        synchronized (this.h) {
            this.t = i;
        }
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setMaxWidth(int i) {
        synchronized (this.h) {
            this.s = i;
        }
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setMax(int i) {
        synchronized (this.h) {
            if (i < 0) {
                i = 0;
            }
            if (i != this.z) {
                this.z = i;
                postInvalidate();
                if (this.y > i) {
                    this.y = i;
                }
                b(this.y, false);
            }
        }
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setFontFamily(String str) {
        this.d = str;
        a(str, this.e, this.f);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setFixedWidth(boolean z) {
        this.q = z;
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setCancelBtnHeight(int i) {
        this.n = i;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        a(i, i2);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState a2;
        synchronized (this.h) {
            a2 = SavedState.a(super.onSaveInstanceState());
            a2.f8001a = this.y;
        }
        return a2;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setProgress(savedState.f8001a);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        synchronized (this.h) {
            super.onDraw(canvas);
            Drawable drawable = this.A;
            if (drawable != null) {
                drawable.draw(canvas);
            }
            if (a(drawable)) {
                canvas.scale(-1.0f, 1.0f, getWidth() / 2.0f, getHeight() / 2.0f);
            }
            a(canvas);
        }
    }

    public void onClick(View view) {
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        synchronized (this.h) {
            super.jumpDrawablesToCurrentState();
            Drawable drawable = this.g;
            if (drawable != null) {
                drawable.jumpToCurrentState();
            }
        }
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public boolean isFastClick() {
        if (System.currentTimeMillis() - this.B < 500) {
            return true;
        }
        this.B = System.currentTimeMillis();
        return false;
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public final void incrementProgressBy(int i) {
        synchronized (this.h) {
            setProgress(this.y + i);
        }
    }

    protected int getTextStart() {
        if (dd.h()) {
            return this.k;
        }
        int width = ((getWidth() - this.f7999a.width()) - this.i) / 2;
        int i = this.j;
        if (width < i) {
            width = i;
        }
        ho.a(this.o, "safeTextStart: %s", Integer.valueOf(width));
        return width;
    }

    public CharSequence getText() {
        CharSequence charSequence;
        synchronized (this.h) {
            charSequence = this.c;
        }
        return charSequence;
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public Rect getPromptRect() {
        Rect rect;
        synchronized (this.h) {
            rect = this.f7999a;
        }
        return rect;
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public Drawable getProgressDrawable() {
        Drawable drawable;
        synchronized (this.h) {
            drawable = this.g;
        }
        return drawable;
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public int getProgress() {
        int i;
        synchronized (this.h) {
            i = this.y;
        }
        return i;
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        ho.a(this.o, "drawableStateChanged");
        super.drawableStateChanged();
        e();
    }

    protected void a(Canvas canvas) {
        Rect rect;
        synchronized (this.h) {
            CharSequence charSequence = this.c;
            if (charSequence != null && charSequence.length() > 0) {
                if (this.b != null && (rect = this.f7999a) != null && rect.width() <= 0) {
                    this.b.getTextBounds(this.c.toString(), 0, this.c.length(), this.f7999a);
                }
                String intern = this.c.toString().intern();
                int width = (getWidth() / 2) - this.f7999a.centerX();
                if (this.l && width < this.i) {
                    width = getTextStart();
                }
                canvas.drawText((CharSequence) intern, 0, intern.length(), width, (getHeight() / 2) - this.f7999a.centerY(), this.b);
            }
        }
    }

    void a(int i, boolean z) {
        synchronized (this.h) {
            if (i < 0) {
                i = 0;
            }
            int i2 = this.z;
            if (i > i2) {
                i = i2;
            }
            if (i != this.y) {
                this.y = i;
                b(i, z);
            }
        }
    }

    protected void a(int i, int i2) {
        synchronized (this.h) {
            Drawable drawable = this.g;
            if (drawable != null) {
                drawable.setBounds(0, 0, i, i2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x007e A[Catch: all -> 0x0089, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:9:0x000f, B:11:0x004d, B:14:0x004f, B:16:0x0053, B:17:0x0055, B:19:0x0059, B:20:0x0068, B:22:0x006c, B:23:0x0077, B:24:0x007a, B:26:0x007e, B:27:0x0085, B:29:0x0070, B:31:0x0074, B:32:0x0087), top: B:3:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void a() {
        /*
            r6 = this;
            byte[] r0 = r6.h
            monitor-enter(r0)
            java.lang.CharSequence r1 = r6.c     // Catch: java.lang.Throwable -> L89
            if (r1 == 0) goto L87
            int r1 = r1.length()     // Catch: java.lang.Throwable -> L89
            if (r1 != 0) goto Lf
            goto L87
        Lf:
            android.graphics.Paint r1 = r6.b     // Catch: java.lang.Throwable -> L89
            java.lang.CharSequence r2 = r6.c     // Catch: java.lang.Throwable -> L89
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L89
            java.lang.CharSequence r3 = r6.c     // Catch: java.lang.Throwable -> L89
            int r3 = r3.length()     // Catch: java.lang.Throwable -> L89
            android.graphics.Rect r4 = r6.f7999a     // Catch: java.lang.Throwable -> L89
            r5 = 0
            r1.getTextBounds(r2, r5, r3, r4)     // Catch: java.lang.Throwable -> L89
            int r1 = r6.getPaddingStart()     // Catch: java.lang.Throwable -> L89
            int r2 = r6.getPaddingLeft()     // Catch: java.lang.Throwable -> L89
            int r3 = r6.j     // Catch: java.lang.Throwable -> L89
            int r1 = r6.a(r1, r2, r3)     // Catch: java.lang.Throwable -> L89
            int r2 = r6.getPaddingEnd()     // Catch: java.lang.Throwable -> L89
            int r3 = r6.getPaddingRight()     // Catch: java.lang.Throwable -> L89
            int r4 = r6.k     // Catch: java.lang.Throwable -> L89
            int r2 = r6.a(r2, r3, r4)     // Catch: java.lang.Throwable -> L89
            android.graphics.Rect r3 = r6.f7999a     // Catch: java.lang.Throwable -> L89
            int r3 = r3.width()     // Catch: java.lang.Throwable -> L89
            int r3 = r3 + r1
            int r3 = r3 + r2
            android.view.ViewGroup$LayoutParams r1 = r6.getLayoutParams()     // Catch: java.lang.Throwable -> L89
            if (r1 != 0) goto L4f
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L89
            return
        L4f:
            int r2 = r6.u     // Catch: java.lang.Throwable -> L89
            if (r2 <= 0) goto L55
            r1.height = r2     // Catch: java.lang.Throwable -> L89
        L55:
            int r2 = r1.height     // Catch: java.lang.Throwable -> L89
            if (r2 > 0) goto L68
            float r2 = r6.w     // Catch: java.lang.Throwable -> L89
            int r2 = (int) r2     // Catch: java.lang.Throwable -> L89
            int r4 = r6.getPaddingTop()     // Catch: java.lang.Throwable -> L89
            int r2 = r2 + r4
            int r4 = r6.getPaddingBottom()     // Catch: java.lang.Throwable -> L89
            int r2 = r2 + r4
            r1.height = r2     // Catch: java.lang.Throwable -> L89
        L68:
            boolean r2 = r6.q     // Catch: java.lang.Throwable -> L89
            if (r2 == 0) goto L70
            r6.b(r3, r1)     // Catch: java.lang.Throwable -> L89
            goto L77
        L70:
            int r2 = r1.width     // Catch: java.lang.Throwable -> L89
            if (r3 == r2) goto L7a
            r6.a(r3, r1)     // Catch: java.lang.Throwable -> L89
        L77:
            r6.setLayoutParams(r1)     // Catch: java.lang.Throwable -> L89
        L7a:
            com.huawei.hms.ads.uiengine.common.IProgressButton$ProgressButtonResetListener r2 = r6.E     // Catch: java.lang.Throwable -> L89
            if (r2 == 0) goto L85
            int r3 = r1.width     // Catch: java.lang.Throwable -> L89
            int r1 = r1.height     // Catch: java.lang.Throwable -> L89
            r2.onSizeReset(r3, r1)     // Catch: java.lang.Throwable -> L89
        L85:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L89
            return
        L87:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L89
            return
        L89:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L89
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.ProgressButton.a():void");
    }

    private void setOriginTextSize(Float f) {
        if (f != null) {
            Float f2 = this.x;
            if (f2 == null || f2.floatValue() == 0.0f) {
                this.x = f;
            }
        }
    }

    private int getPaddingSize() {
        return a(getPaddingStart(), getPaddingLeft(), this.j) + a(getPaddingEnd(), getPaddingRight(), this.k);
    }

    private int getButtonSize() {
        if (!this.q) {
            return this.s;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        int width = getWidth();
        return width <= 0 ? layoutParams.width : width;
    }

    private void e() {
        synchronized (this.h) {
            int[] drawableState = getDrawableState();
            Drawable drawable = this.g;
            if (drawable != null && drawable.isStateful()) {
                this.g.setState(drawableState);
            }
        }
    }

    private void d() {
        Paint paint = new Paint();
        paint.setTextSize(this.w);
        Rect rect = new Rect();
        paint.getTextBounds("...", 0, 3, rect);
        this.p = rect.width();
    }

    private void c() {
        if (this.l) {
            int i = this.u;
            if (i <= 0) {
                i = getMeasuredHeight();
            }
            if (i <= 0) {
                return;
            }
            boolean z = i < this.F;
            this.i = ao.a(getContext(), z ? 24 : 36);
            this.j = ao.a(getContext(), z ? 8 : 16);
            this.k = (i / 2) + (ao.a(getContext(), 12) / 2) + ao.a(getContext(), z ? 4 : 8);
            ho.a(this.o, "update text safe padding, start: %s, end: %s", Integer.valueOf(this.j), Integer.valueOf(this.k));
        }
    }

    private void b(int i, boolean z) {
        synchronized (this.h) {
            a(i, z, true);
        }
    }

    private void b(int i, ViewGroup.LayoutParams layoutParams) {
        int width = getWidth();
        if (width <= 0 && this.r) {
            width = layoutParams.width;
        }
        if (i > width && width > 0) {
            CharSequence a2 = a(this.c, i, width);
            this.c = a2;
            this.b.getTextBounds(a2.toString(), 0, this.c.length(), this.f7999a);
        } else {
            if (width > 0 || !this.r) {
                return;
            }
            layoutParams.width = i;
        }
    }

    private void b() {
        Paint paint = new Paint();
        this.b = paint;
        paint.setAntiAlias(true);
        this.b.setTextSize(this.w);
        this.b.setColor(this.v);
        Paint paint2 = new Paint();
        this.C = paint2;
        paint2.setTextSize(this.w);
        int i = this.f;
        if (i != -1) {
            this.d = null;
        }
        a(this.d, this.e, i);
        setClickable(true);
        Paint paint3 = new Paint();
        paint3.setTextSize(this.w);
        Rect rect = new Rect();
        paint3.getTextBounds("...", 0, 3, rect);
        this.p = rect.width();
        this.D = dd.h();
        this.F = ao.a(getContext(), 40.0f);
    }

    private boolean a(CharSequence charSequence, int i, int i2, int i3) {
        float c = ao.c(getContext(), i);
        ho.a(this.o, "currentSize:%s", Float.valueOf(c));
        ho.a(this.o, "buttonSize:%s", Integer.valueOf(i3));
        if (i3 < 0) {
            return true;
        }
        this.C.setTextSize(c);
        this.C.getTextBounds(charSequence.toString(), 0, charSequence.length(), this.f7999a);
        int width = this.f7999a.width() + i2;
        ho.a(this.o, "textWidth:%s, btnWidth:%s", Integer.valueOf(width), Integer.valueOf(i3));
        return width <= i3;
    }

    private boolean a(Drawable drawable) {
        Drawable findDrawableByLayerId;
        if (drawable == null || !(drawable instanceof LayerDrawable) || (findDrawableByLayerId = ((LayerDrawable) drawable).findDrawableByLayerId(R.id.progress)) == null) {
            return false;
        }
        if ((findDrawableByLayerId instanceof PPSFlickerDrawable) || (findDrawableByLayerId instanceof p)) {
            return this.D;
        }
        return false;
    }

    private void a(String str, int i, int i2) {
        Typeface typeface;
        ho.a(this.o, "setTypefaceFromAttrs");
        if (str != null) {
            typeface = Typeface.create(str, i2);
            if (typeface != null) {
                ho.a(this.o, "setTypeface");
                setPaintTypeface(typeface);
                this.b.setTypeface(typeface);
                return;
            }
        } else {
            typeface = null;
        }
        if (i == 1) {
            typeface = Typeface.SANS_SERIF;
        } else if (i == 2) {
            typeface = Typeface.SERIF;
        } else if (i == 3) {
            typeface = Typeface.MONOSPACE;
        }
        setPaintTypeface(typeface, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0050, code lost:
    
        r6.q = r1.getBoolean(0, false);
        r6.r = r1.getBoolean(4, true);
        r6.s = r1.getDimensionPixelSize(2, 0);
        r6.t = r1.getDimensionPixelSize(3, 0);
        r7 = r1.getDimension(7, 0.0f);
        r6.w = r7;
        setOriginTextSize(java.lang.Float.valueOf(r7));
        r6.v = r1.getColor(6, -1);
        r6.d = r1.getString(1);
        r6.f = r1.getInt(5, -1);
        r6.e = r1.getInt(8, -1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x00a7, code lost:
    
        r1.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x00ac, code lost:
    
        if (r6.u <= 0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x00ae, code lost:
    
        r6.u = (((int) r6.w) + getPaddingTop()) + getPaddingBottom();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0098, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00be, code lost:
    
        r1.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00c1, code lost:
    
        throw r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00a2, code lost:
    
        r7 = r6.o;
        r8 = "initButtonAttr RuntimeException";
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x009e, code lost:
    
        com.huawei.openalliance.ad.ho.c(r7, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x009a, code lost:
    
        r7 = r6.o;
        r8 = "initButtonAttr error";
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004b, code lost:
    
        if (r4 == null) goto L41;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00ae A[Catch: all -> 0x00cb, TryCatch #5 {, blocks: (B:6:0x0007, B:14:0x00a7, B:16:0x00ae, B:19:0x00be, B:20:0x00c1, B:11:0x004d, B:34:0x00c5, B:35:0x00c8, B:39:0x00c9, B:29:0x0038, B:9:0x0016, B:13:0x0050, B:25:0x009a, B:23:0x009e, B:22:0x00a2), top: B:5:0x0007, inners: #0, #3, #4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(android.content.Context r7, android.util.AttributeSet r8) {
        /*
            r6 = this;
            byte[] r0 = r6.h
            monitor-enter(r0)
            if (r8 == 0) goto Lc9
            r1 = 9
            int[] r1 = new int[r1]     // Catch: java.lang.Throwable -> Lcb
            r1 = {x00ce: FILL_ARRAY_DATA , data: [2131100152, 2131100153, 2131100154, 2131100155, 2131100156, 2131100158, 2131100159, 2131100160, 2131100161} // fill-array     // Catch: java.lang.Throwable -> Lcb
            android.content.res.TypedArray r1 = r7.obtainStyledAttributes(r8, r1)     // Catch: java.lang.Throwable -> Lcb
            r2 = 1
            r3 = 0
            r4 = 0
            r5 = 16842997(0x10100f5, float:2.3694245E-38)
            int[] r5 = new int[]{r5}     // Catch: java.lang.Throwable -> L37
            android.content.res.TypedArray r4 = r7.obtainStyledAttributes(r8, r5)     // Catch: java.lang.Throwable -> L37
            r7 = -2
            int r7 = r4.getDimensionPixelSize(r3, r7)     // Catch: java.lang.Throwable -> L37
            r6.u = r7     // Catch: java.lang.Throwable -> L37
            java.lang.String r8 = r6.o     // Catch: java.lang.Throwable -> L37
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L37
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Throwable -> L37
            r5[r3] = r7     // Catch: java.lang.Throwable -> L37
            java.lang.String r7 = "layoutHeight: %s"
            com.huawei.openalliance.ad.ho.a(r8, r7, r5)     // Catch: java.lang.Throwable -> L37
            if (r4 == 0) goto L50
            goto L4d
        L37:
            r7 = move-exception
            java.lang.String r8 = r6.o     // Catch: java.lang.Throwable -> Lc2
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> Lc2
            java.lang.Class r7 = r7.getClass()     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r7 = r7.getSimpleName()     // Catch: java.lang.Throwable -> Lc2
            r5[r3] = r7     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r7 = "get layout height ex: %s"
            com.huawei.openalliance.ad.ho.c(r8, r7, r5)     // Catch: java.lang.Throwable -> Lc2
            if (r4 == 0) goto L50
        L4d:
            r4.recycle()     // Catch: java.lang.Throwable -> Lcb
        L50:
            boolean r7 = r1.getBoolean(r3, r3)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            r6.q = r7     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            r7 = 4
            boolean r7 = r1.getBoolean(r7, r2)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            r6.r = r7     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            r7 = 2
            int r7 = r1.getDimensionPixelSize(r7, r3)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            r6.s = r7     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            r7 = 3
            int r7 = r1.getDimensionPixelSize(r7, r3)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            r6.t = r7     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            r7 = 7
            r8 = 0
            float r7 = r1.getDimension(r7, r8)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            r6.w = r7     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            java.lang.Float r7 = java.lang.Float.valueOf(r7)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            r6.setOriginTextSize(r7)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            r7 = 6
            r8 = -1
            int r7 = r1.getColor(r7, r8)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            r6.v = r7     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            java.lang.String r7 = r1.getString(r2)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            r6.d = r7     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            r7 = 5
            int r7 = r1.getInt(r7, r8)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            r6.f = r7     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            r7 = 8
            int r7 = r1.getInt(r7, r8)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            r6.e = r7     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a java.lang.RuntimeException -> La2
            goto La7
        L98:
            r7 = move-exception
            goto Lbe
        L9a:
            java.lang.String r7 = r6.o     // Catch: java.lang.Throwable -> L98
            java.lang.String r8 = "initButtonAttr error"
        L9e:
            com.huawei.openalliance.ad.ho.c(r7, r8)     // Catch: java.lang.Throwable -> L98
            goto La7
        La2:
            java.lang.String r7 = r6.o     // Catch: java.lang.Throwable -> L98
            java.lang.String r8 = "initButtonAttr RuntimeException"
            goto L9e
        La7:
            r1.recycle()     // Catch: java.lang.Throwable -> Lcb
            int r7 = r6.u     // Catch: java.lang.Throwable -> Lcb
            if (r7 > 0) goto Lc9
            float r7 = r6.w     // Catch: java.lang.Throwable -> Lcb
            int r7 = (int) r7     // Catch: java.lang.Throwable -> Lcb
            int r8 = r6.getPaddingTop()     // Catch: java.lang.Throwable -> Lcb
            int r7 = r7 + r8
            int r8 = r6.getPaddingBottom()     // Catch: java.lang.Throwable -> Lcb
            int r7 = r7 + r8
            r6.u = r7     // Catch: java.lang.Throwable -> Lcb
            goto Lc9
        Lbe:
            r1.recycle()     // Catch: java.lang.Throwable -> Lcb
            throw r7     // Catch: java.lang.Throwable -> Lcb
        Lc2:
            r7 = move-exception
            if (r4 == 0) goto Lc8
            r4.recycle()     // Catch: java.lang.Throwable -> Lcb
        Lc8:
            throw r7     // Catch: java.lang.Throwable -> Lcb
        Lc9:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lcb
            return
        Lcb:
            r7 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lcb
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.ProgressButton.a(android.content.Context, android.util.AttributeSet):void");
    }

    private void a(int i, boolean z, boolean z2) {
        synchronized (this.h) {
            int i2 = this.z;
            float f = i2 > 0 ? i / i2 : 0.0f;
            Drawable drawable = this.A;
            if (drawable != null) {
                drawable.setLevel((int) (10000.0f * f));
            } else {
                invalidate();
            }
            if (z2) {
                a(f, z);
            }
        }
    }

    private void a(int i, ViewGroup.LayoutParams layoutParams) {
        int i2 = this.s;
        if (i <= i2 || i2 <= 0) {
            int i3 = this.t;
            if (i < i3) {
                i = i3;
            }
        } else {
            CharSequence a2 = a(this.c, i, i2);
            this.c = a2;
            this.b.getTextBounds(a2.toString(), 0, this.c.length(), this.f7999a);
            i = this.s;
        }
        layoutParams.width = i;
    }

    public static final class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.huawei.openalliance.ad.views.ProgressButton.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }
        };
        private static SavedState b;

        /* renamed from: a, reason: collision with root package name */
        int f8001a;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f8001a);
        }

        public static SavedState a(Parcelable parcelable) {
            if (b == null) {
                b = new SavedState(parcelable);
            }
            return b;
        }

        private SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f8001a = parcel.readInt();
        }
    }

    private CharSequence a(CharSequence charSequence, int i, int i2) {
        int length = getText().length();
        int ceil = (int) Math.ceil(((i - i2) / getPromptRect().width()) * length);
        int ceil2 = (int) Math.ceil((this.p * length) / getPromptRect().width());
        int i3 = length - ceil;
        if (i3 - ceil2 <= 0) {
            return i3 > 0 ? charSequence.toString().substring(0, i3) : charSequence;
        }
        return charSequence.toString().substring(0, length - (ceil + ceil2)) + "...";
    }

    private int a(int i, int i2, int i3) {
        if (i <= 0) {
            i = i2;
        }
        return (!this.l || i3 <= 0 || i >= i3) ? i : i3;
    }

    private float a(CharSequence charSequence, float f) {
        ho.a(this.o, "startSize:%s", Float.valueOf(f));
        int paddingSize = getPaddingSize();
        int buttonSize = getButtonSize();
        int d = ao.d(getContext(), f);
        while (d > 9 && !a(charSequence, d, paddingSize, buttonSize)) {
            d--;
        }
        float c = ao.c(getContext(), d);
        ho.a(this.o, "resultSize:%s", Float.valueOf(c));
        return c;
    }

    public ProgressButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.o = "ProgressBtn_" + hashCode();
        this.f7999a = new Rect();
        this.q = false;
        this.r = true;
        this.v = -1;
        this.w = 12.0f;
        this.x = null;
        this.d = null;
        this.e = -1;
        this.f = -1;
        this.y = 0;
        this.z = 100;
        this.h = new byte[0];
        this.l = false;
        setOnClickListener(this);
        a(context, attributeSet);
        b();
    }

    public ProgressButton(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
        a(context, attributeSet);
        b();
    }

    public ProgressButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.progressBarStyle);
        a(context, attributeSet);
        b();
    }

    public ProgressButton(Context context) {
        this(context, null);
        b();
    }
}
