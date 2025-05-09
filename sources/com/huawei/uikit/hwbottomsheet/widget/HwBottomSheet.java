package com.huawei.uikit.hwbottomsheet.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.uikit.hwbottomsheet.R$drawable;
import com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper;
import com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem;
import defpackage.skv;
import defpackage.snj;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class HwBottomSheet extends ViewGroup {

    /* renamed from: a, reason: collision with root package name */
    private View f10601a;
    private Drawable aa;
    private Drawable ab;
    private Drawable ac;
    private int ad;
    private Drawable ae;
    private snj af;
    private Drawable ag;
    private Drawable ah;
    private SheetState ai;
    private int aj;
    private HwViewDragHelper ak;
    private int al;
    private int am;
    private int an;
    private String ao;
    private int ap;
    private String aq;
    private HwColumnSystem ar;
    private String as;
    private int at;
    private boolean au;
    private boolean av;
    private boolean aw;
    private int ax;
    private boolean ay;
    private int az;
    private boolean b;
    private boolean ba;
    private boolean bb;
    private float bc;
    private boolean bd;
    private View be;
    private boolean bf;
    private MotionEvent bg;
    private View bh;
    private int bi;
    private int bj;
    private View bk;
    private boolean bl;
    private int c;
    private HwDragImageView d;
    private int e;
    private boolean f;
    private int g;
    private float h;
    private float i;
    private View j;
    private int k;
    private float l;
    private float m;
    private final List<SheetSlideListener> n;
    private d o;
    private float p;
    private int q;
    private int r;
    private int s;
    private float t;
    private int u;
    private boolean v;
    private SheetState w;
    private int x;
    private int y;
    private int z;

    public interface SheetSlideListener {
        void onSheetSlide(View view, float f);

        void onSheetStateChanged(View view, SheetState sheetState, SheetState sheetState2);
    }

    public enum SheetState {
        EXPANDED(0),
        COLLAPSED(1),
        ANCHORED(2),
        HIDDEN(3),
        DRAGGING(4);

        private final int b;

        SheetState(int i) {
            this.b = i;
        }

        public int getStateValue() {
            return this.b;
        }
    }

    class a implements HwViewDragHelper.a {
        a() {
        }

        @Override // com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper.a
        public void a(int i, int i2) {
            HwBottomSheet.this.d.a(i, i2);
        }
    }

    static /* synthetic */ class c {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[SheetState.values().length];
            b = iArr;
            try {
                iArr[SheetState.COLLAPSED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[SheetState.ANCHORED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[SheetState.HIDDEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[SheetState.EXPANDED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    class d implements View.OnClickListener {
        private d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!HwBottomSheet.this.isEnabled() || !HwBottomSheet.this.c()) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (HwBottomSheet.this.ai == SheetState.COLLAPSED) {
                HwBottomSheet.this.d(SheetState.EXPANDED, true);
            } else if (HwBottomSheet.this.ai == SheetState.ANCHORED) {
                HwBottomSheet.this.d(SheetState.EXPANDED, true);
            } else {
                HwBottomSheet.this.d(SheetState.COLLAPSED, true);
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        /* synthetic */ d(HwBottomSheet hwBottomSheet, a aVar) {
            this();
        }
    }

    /* loaded from: classes9.dex */
    class e extends HwViewDragHelper.Callback {
        private e() {
        }

        private void e() {
            HwBottomSheet hwBottomSheet = HwBottomSheet.this;
            hwBottomSheet.t = hwBottomSheet.a(hwBottomSheet.be.getTop());
            if (Float.compare(HwBottomSheet.this.t, 1.0f) == 0) {
                if (HwBottomSheet.this.ah != null && (HwBottomSheet.this.w == SheetState.HIDDEN || HwBottomSheet.this.w == SheetState.COLLAPSED)) {
                    HwBottomSheet hwBottomSheet2 = HwBottomSheet.this;
                    hwBottomSheet2.dZV_(hwBottomSheet2.ah);
                } else if (HwBottomSheet.this.w != SheetState.EXPANDED) {
                    HwBottomSheet hwBottomSheet3 = HwBottomSheet.this;
                    hwBottomSheet3.dZV_(hwBottomSheet3.ac);
                }
                HwBottomSheet.this.d(SheetState.EXPANDED);
                return;
            }
            if (Float.compare(HwBottomSheet.this.t, 0.0f) == 0) {
                if (HwBottomSheet.this.ae != null && HwBottomSheet.this.w == SheetState.EXPANDED) {
                    HwBottomSheet hwBottomSheet4 = HwBottomSheet.this;
                    hwBottomSheet4.dZV_(hwBottomSheet4.ae);
                } else if (HwBottomSheet.this.w != SheetState.COLLAPSED) {
                    HwBottomSheet hwBottomSheet5 = HwBottomSheet.this;
                    hwBottomSheet5.dZV_(hwBottomSheet5.ag);
                }
                HwBottomSheet.this.d(SheetState.COLLAPSED);
                return;
            }
            if (Float.compare(HwBottomSheet.this.t, 0.0f) < 0) {
                HwBottomSheet.this.d(SheetState.HIDDEN);
                HwBottomSheet.this.be.setVisibility(4);
                return;
            }
            if (HwBottomSheet.this.w == SheetState.EXPANDED) {
                HwBottomSheet hwBottomSheet6 = HwBottomSheet.this;
                hwBottomSheet6.dZV_(hwBottomSheet6.ab);
            } else if (HwBottomSheet.this.w == SheetState.COLLAPSED) {
                HwBottomSheet hwBottomSheet7 = HwBottomSheet.this;
                hwBottomSheet7.dZV_(hwBottomSheet7.aa);
            }
            HwBottomSheet.this.d(SheetState.ANCHORED);
        }

        @Override // com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i, int i2) {
            if (view == null) {
                Log.w("HwBottomSheet", "clampViewPositionHorizontal: Parameter child is null!");
                return i;
            }
            int paddingLeft = HwBottomSheet.this.getPaddingLeft();
            int width = (HwBottomSheet.this.getWidth() - view.getWidth()) - HwBottomSheet.this.getPaddingRight();
            return i < paddingLeft ? paddingLeft : i < width ? i : width;
        }

        @Override // com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper.Callback
        public int clampViewPositionVertical(View view, int i, int i2) {
            int d = HwBottomSheet.this.d(0.0f);
            int d2 = HwBottomSheet.this.d(1.0f);
            if (i < d2) {
                i = d2;
            }
            return i <= d ? i : d;
        }

        @Override // com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper.Callback
        public int getViewVerticalDragRange(View view) {
            return HwBottomSheet.this.g;
        }

        @Override // com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper.Callback
        public void onViewCaptured(View view, int i) {
            HwBottomSheet.this.k();
        }

        @Override // com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper.Callback
        public void onViewDragStateChanged(int i) {
            if (HwBottomSheet.this.ak == null || HwBottomSheet.this.ak.b() != 0) {
                return;
            }
            e();
        }

        @Override // com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            HwBottomSheet.this.c(i2);
            HwBottomSheet.this.invalidate();
        }

        @Override // com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper.Callback
        public void onViewReleased(View view, float f, float f2) {
            if (view == null) {
                Log.w("HwBottomSheet", "onViewReleased: Parameter releasedChild is null!");
                return;
            }
            int e = HwBottomSheet.this.e(-f2);
            if (HwBottomSheet.this.ak != null) {
                HwBottomSheet.this.ak.c(view.getLeft(), e);
            }
            HwBottomSheet.this.invalidate();
        }

        @Override // com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i) {
            if (view != HwBottomSheet.this.be) {
                HwBottomSheet.this.f();
            }
            return !HwBottomSheet.this.bd && view == HwBottomSheet.this.be;
        }

        /* synthetic */ e(HwBottomSheet hwBottomSheet, a aVar) {
            this();
        }
    }

    public HwBottomSheet(Context context) {
        this(context, null);
    }

    private int a(int i, int i2) {
        return i > i2 ? i : i2;
    }

    private int getMaxIndicatorHeight() {
        int a2 = a(a(a(this.aa.getIntrinsicHeight(), this.ac.getIntrinsicHeight()), this.ab.getIntrinsicHeight()), this.ag.getIntrinsicHeight());
        int intrinsicHeight = getResources().getDrawable(this.u).getIntrinsicHeight();
        int intrinsicHeight2 = getResources().getDrawable(this.z).getIntrinsicHeight();
        return a(a(a(a2, intrinsicHeight), intrinsicHeight2), getResources().getDrawable(this.ad).getIntrinsicHeight());
    }

    private void setDragContentExtraTopPadding(int i) {
        View view = this.bh;
        if (view == null) {
            return;
        }
        if (this.ap != 1) {
            i = 0;
        }
        view.setPadding(view.getPaddingLeft(), this.am + i, this.bh.getPaddingRight(), this.bh.getPaddingBottom());
    }

    private void setDragContentPadding(View view) {
        int i;
        if (this.bh != null || (i = this.e) == -1) {
            return;
        }
        View findViewById = view.findViewById(i);
        this.bh = findViewById;
        if (findViewById == null) {
            Log.w("HwBottomSheet", "setDragContentPadding: Find a null mDragContentView!");
            return;
        }
        this.am = findViewById.getPaddingTop();
        if (this.ap == 0) {
            return;
        }
        View view2 = this.bh;
        view2.setPadding(view2.getPaddingLeft(), this.bh.getPaddingTop() + this.r, this.bh.getPaddingRight(), this.bh.getPaddingBottom());
    }

    private void setDragContentView(View view) {
        this.bh = view;
    }

    private void setDragView(int i) {
        this.bi = i;
        setDragView(findViewById(i));
    }

    private void setMiniBarView(View view) {
        if (view == null) {
            return;
        }
        this.f10601a = view;
        view.setOnClickListener(this.o);
    }

    private void setMinibarLayoutParams(View view) {
        int i;
        if (this.f10601a != null || (i = this.c) == -1) {
            return;
        }
        View findViewById = view.findViewById(i);
        this.f10601a = findViewById;
        if (findViewById == null) {
            Log.w("HwBottomSheet", "setMinibarLayoutParams: Find a null mMiniBarView!");
            return;
        }
        findViewById.setPadding(findViewById.getPaddingLeft(), this.f10601a.getPaddingTop(), this.f10601a.getPaddingRight(), this.f10601a.getPaddingBottom());
        ViewGroup.LayoutParams layoutParams = this.f10601a.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            int i2 = marginLayoutParams.topMargin;
            int i3 = this.an;
            if (i2 != i3) {
                marginLayoutParams.topMargin = i3;
                this.f10601a.setLayoutParams(marginLayoutParams);
            }
        }
    }

    private void setSheetHeightInternal(int i) {
        if (!this.b) {
            this.r = i;
            return;
        }
        if (this.ay) {
            i = a(this.an, i);
        }
        this.r = i;
        setDragContentExtraTopPadding(i);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (view == null || layoutParams == null) {
            Log.w("HwBottomSheet", "addView: Parameters are null!");
            return;
        }
        if (view.getId() != this.bi) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom() + this.r);
            super.addView(view, layoutParams);
        } else {
            if (!this.b) {
                super.addView(view, layoutParams);
                return;
            }
            ViewGroup dZS_ = dZS_(view);
            if (dZS_ == null) {
                Log.w("HwBottomSheet", "addView: indicator's layout might be null!");
            } else {
                super.addView(dZS_, layoutParams);
            }
        }
    }

    public boolean c() {
        return isEnabled() && this.f && this.be != null && this.ai != SheetState.HIDDEN;
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        HwViewDragHelper hwViewDragHelper = this.ak;
        if (hwViewDragHelper == null || !hwViewDragHelper.c(true)) {
            return;
        }
        if (isEnabled()) {
            postInvalidateOnAnimation();
        } else {
            this.ak.a();
        }
    }

    public void d(SheetSlideListener sheetSlideListener) {
        synchronized (this.n) {
            if (sheetSlideListener != null) {
                this.n.add(sheetSlideListener);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            Log.w("HwBottomSheet", "dispatchTouchEvent:Parameter motionEvent is null!");
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if ((this.bd && actionMasked != 0) || !c()) {
            return super.dispatchTouchEvent(motionEvent);
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (actionMasked == 0) {
            this.i = x;
            this.h = y;
            this.bl = false;
            this.bb = false;
        } else if (actionMasked == 1) {
            if (this.bl) {
                this.ak.e(0);
            }
            if (!m() && this.ak.b() == 1) {
                boolean onTouchEvent = onTouchEvent(motionEvent);
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.setAction(3);
                super.dispatchTouchEvent(obtain);
                obtain.recycle();
                return onTouchEvent;
            }
        } else if (actionMasked == 2) {
            return eaa_(x, y, motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public float getAnchorPoint() {
        return this.p;
    }

    public int getHeightGap() {
        return this.k;
    }

    public View getIndicateView() {
        return this.d;
    }

    public int getLayoutType() {
        return this.ap;
    }

    public int getSheetHeight() {
        return this.r;
    }

    public SheetState getSheetState() {
        return this.ai;
    }

    public HwViewDragHelper getViewDragHelper() {
        return this.ak;
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        this.af.ejb_(windowInsets);
        return super.onApplyWindowInsets(windowInsets);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        r();
        this.v = true;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (getResources().getConfiguration().orientation == 2 && this.p < 1.0f && this.ai == SheetState.ANCHORED) {
            this.ai = SheetState.EXPANDED;
        }
        if (this.au) {
            a(getContext());
        } else {
            e(getContext());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.v = true;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        h();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent == null) {
            return;
        }
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        dZY_(accessibilityEvent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0027, code lost:
    
        if (r1 != 3) goto L33;
     */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto Lb
            java.lang.String r5 = "HwBottomSheet"
            java.lang.String r1 = "onInterceptTouchEvent:Parameter motionEvent is null!"
            android.util.Log.w(r5, r1)
            return r0
        Lb:
            boolean r1 = r4.c()
            if (r1 == 0) goto L5e
            boolean r1 = r4.bl
            if (r1 != 0) goto L5e
            boolean r1 = r4.ba
            if (r1 == 0) goto L1a
            goto L5e
        L1a:
            int r1 = r5.getActionMasked()
            r2 = 1
            if (r1 == 0) goto L49
            if (r1 == r2) goto L3b
            r3 = 2
            if (r1 == r3) goto L2a
            r0 = 3
            if (r1 == r0) goto L3b
            goto L57
        L2a:
            boolean r1 = r4.eaf_(r5)
            if (r1 != 0) goto L57
            com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper r5 = r4.ak
            r5.d()
            r4.bd = r2
            r4.f()
            return r0
        L3b:
            com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper r0 = r4.ak
            int r0 = r0.b()
            if (r0 != r2) goto L57
            com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper r0 = r4.ak
            r0.eaC_(r5)
            return r2
        L49:
            boolean r1 = r4.eae_(r5)
            if (r1 != 0) goto L57
            com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper r5 = r4.ak
            r5.d()
            r4.bd = r2
            return r0
        L57:
            com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper r0 = r4.ak
            boolean r5 = r0.eaH_(r5)
            return r5
        L5e:
            r4.eah_(r5)
            com.huawei.uikit.hwbottomsheet.widget.HwViewDragHelper r5 = r4.ak
            r5.a()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.v) {
            j();
        }
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i5 = 0; i5 < childCount; i5++) {
            d(i5, paddingLeft, paddingTop);
        }
        n();
        this.v = false;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        if (e(i, i2)) {
            int size = View.MeasureSpec.getSize(i);
            int size2 = View.MeasureSpec.getSize(i2);
            if (!this.av || (i3 = this.at) <= 0 || i3 >= size) {
                i3 = size;
            }
            int paddingLeft = (i3 - getPaddingLeft()) - getPaddingRight();
            int paddingLeft2 = (size - getPaddingLeft()) - getPaddingRight();
            int i4 = (paddingLeft2 - paddingLeft) / 2;
            int paddingTop = (size2 - getPaddingTop()) - getPaddingBottom();
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                View view = this.be;
                if (childAt == view) {
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : new ViewGroup.MarginLayoutParams(layoutParams);
                    marginLayoutParams.setMarginStart(i4);
                    marginLayoutParams.setMarginEnd(i4);
                    this.be.setLayoutParams(marginLayoutParams);
                    b(i5, paddingLeft, paddingTop);
                } else {
                    b(i5, paddingLeft2, paddingTop);
                }
                View view2 = this.f10601a;
                if (view2 != null) {
                    this.r = view2.getMeasuredHeight() + this.an;
                }
                if (getChildAt(i5) == this.be) {
                    o();
                }
            }
            setMeasuredDimension(size, size2);
        }
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent == null) {
            return;
        }
        super.onPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            try {
                dZW_(bundle);
                parcelable2 = bundle.getParcelable("superState");
                if (parcelable2 == null) {
                    Log.w("HwBottomSheet", "Superstate is null!");
                    return;
                }
            } catch (BadParcelableException unused) {
                Log.e("HwBottomSheet", "onRestoreInstanceState: Bad parcel target.");
            }
            super.onRestoreInstanceState(parcelable2);
        }
        parcelable2 = null;
        super.onRestoreInstanceState(parcelable2);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        try {
            bundle.putParcelable("superState", super.onSaveInstanceState());
            bundle.putSerializable("sliding_state", this.ai != SheetState.DRAGGING ? this.ai : this.w);
            bundle.putSerializable("pre_idle_state", this.w);
            bundle.putSerializable("sheet_anchor_point", Float.valueOf(this.p));
        } catch (BadParcelableException unused) {
            Log.e("HwBottomSheet", "onSaveInstanceState: Bad parcel target.");
        }
        return bundle;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i2 == i4 && i == i3) {
            return;
        }
        this.v = true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || !c() || this.ba) {
            return super.onTouchEvent(motionEvent);
        }
        eaj_(motionEvent);
        this.ak.eaC_(motionEvent);
        return true;
    }

    public void setAnchorPoint(float f) {
        if (f <= 0.0f || Float.compare(f, 1.0f) > 0) {
            return;
        }
        if (Float.compare(f, 1.0f) == 0 && Float.compare(this.p, 1.0f) < 0 && this.ai == SheetState.ANCHORED) {
            d(SheetState.EXPANDED);
        }
        this.p = f;
        this.v = true;
        requestLayout();
    }

    public void setColumnEnabled(boolean z) {
        this.av = z;
        requestLayout();
    }

    public void setDisallowInterceptTouchEvent(boolean z) {
        this.ba = z;
    }

    public void setDragViewBackgroundColor(int i) {
        View view = this.bk;
        if (view == null) {
            return;
        }
        if (view.getBackground() instanceof ColorDrawable) {
            this.bk.setBackgroundColor(i);
        } else if (Build.VERSION.SDK_INT >= 29) {
            this.bk.getBackground().setColorFilter(new BlendModeColorFilter(i, BlendMode.SRC_ATOP));
        } else {
            this.bk.getBackground().setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
        }
    }

    public void setForceShowIndicateEnabled(boolean z) {
        if (this.f10601a != null) {
            Log.w("HwBottomSheet", "setFocusShowIndicate: we have mini bar, must show indicate view focused!");
        } else {
            this.ay = z;
            setSheetHeight(this.r);
        }
    }

    public void setHeightGap(int i) {
        this.k = i;
        o();
        requestLayout();
    }

    public void setIndicateEnable(boolean z) {
        if (this.b != z) {
            this.b = z;
            requestLayout();
        }
    }

    public void setIndicateSafeInsetsEnabled(boolean z) {
        if (this.aw != z) {
            this.aw = z;
            requestLayout();
        }
    }

    public void setLayoutType(int i) {
        if (this.ap == i) {
            return;
        }
        this.ap = i;
        View view = this.bh;
        if (view != null) {
            view.setPadding(view.getPaddingLeft(), this.am + (i == 1 ? this.r : 0), this.bh.getPaddingRight(), this.bh.getPaddingBottom());
        }
        requestLayout();
    }

    public void setScrollableView(View view) {
        if (view == null) {
            Log.w("HwBottomSheet", "setScrollableView: scrollable view in bottom sheet is null!");
        }
        this.j = view;
    }

    public void setSheetHeight(int i) {
        if (i < 0) {
            return;
        }
        o();
        setSheetHeightInternal(i);
        requestLayout();
    }

    public void setSheetState(SheetState sheetState) {
        d(sheetState, false);
    }

    public void setTouchEnabled(boolean z) {
        this.f = z;
    }

    public HwBottomSheet(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void b(Context context) {
        this.ar = new HwColumnSystem(context);
        this.au = false;
        e(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        HwDragImageView hwDragImageView = this.d;
        if (hwDragImageView == null || !hwDragImageView.e() || this.bb) {
            return;
        }
        c(this.ai);
    }

    private ViewGroup g() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        if (layoutInflater == null) {
            return null;
        }
        View inflate = layoutInflater.inflate(R.layout.hwbottomsheet_indicate_layout, (ViewGroup) null);
        if (inflate instanceof ViewGroup) {
            return (ViewGroup) inflate;
        }
        Log.w("HwBottomSheet", "inflateIndicateLayout: Inflater might be null!");
        return null;
    }

    private void h() {
        int i = this.bi;
        if (i != -1) {
            setDragView(findViewById(i));
        } else {
            Log.e("HwBottomSheet", "mDragViewResId is null!");
        }
        int i2 = this.e;
        if (i2 != -1) {
            setDragContentView(findViewById(i2));
        }
        int i3 = this.bj;
        if (i3 != -1) {
            setScrollableView(findViewById(i3));
        }
        int i4 = this.c;
        if (i4 != -1) {
            setMiniBarView(findViewById(i4));
        }
    }

    private void i() {
        SheetState sheetState = this.ai;
        if (sheetState == SheetState.EXPANDED) {
            this.t = 1.0f;
            return;
        }
        if (sheetState == SheetState.ANCHORED) {
            this.t = this.p;
        } else if (sheetState == SheetState.HIDDEN) {
            this.t = a(d(0.0f) + this.r);
        } else {
            this.t = 0.0f;
        }
    }

    private void j() {
        p();
        i();
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt == null) {
                return;
            }
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
    }

    private boolean l() {
        return (this.q != -1 && this.s != -1) && (this.x != -1 && this.y != -1) && (this.ad != -1 && this.z != -1 && this.u != -1);
    }

    private boolean m() {
        int top = this.bk.getTop();
        return top == d(1.0f) || top == d(this.p) || top == d(0.0f);
    }

    private void n() {
        snj snjVar = this.af;
        if (snjVar == null) {
            return;
        }
        Rect eiW_ = snjVar.eiW_(this.bk);
        View view = this.bk;
        if (view != null) {
            view.setPadding(eiW_.left, view.getPaddingTop(), eiW_.right, this.bk.getPaddingBottom());
        }
        HwDragImageView hwDragImageView = this.d;
        if (hwDragImageView != null && (hwDragImageView.getParent() instanceof ViewGroup) && this.aw) {
            ((ViewGroup) this.d.getParent()).setPadding(eiW_.left, this.d.getPaddingTop(), eiW_.right, this.d.getPaddingBottom());
        }
    }

    private void o() {
        View view = this.be;
        if (view != null) {
            this.g = (view.getMeasuredHeight() - this.r) - this.k;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0014, code lost:
    
        if (r0 != 4) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void p() {
        /*
            r5 = this;
            int[] r0 = com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.c.b
            com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet$SheetState r1 = r5.ai
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 2
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = 0
            if (r0 == r1) goto L19
            r1 = 3
            if (r0 == r1) goto L17
            r1 = 4
            if (r0 == r1) goto L19
            goto L1c
        L17:
            r2 = r3
            goto L1c
        L19:
            r4 = r3
            r3 = r2
            r2 = r4
        L1c:
            r5.a(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.p():void");
    }

    private void r() {
        View view = this.bk;
        if (view == null) {
            return;
        }
        this.af.d(view.getPaddingLeft(), this.bk.getPaddingTop(), this.bk.getPaddingRight(), this.bk.getPaddingBottom());
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public HwBottomSheet(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.bi = -1;
        this.b = true;
        this.q = -1;
        this.s = -1;
        this.x = -1;
        this.y = -1;
        this.u = -1;
        this.z = -1;
        this.ad = -1;
        this.af = new snj(this);
        this.ai = SheetState.COLLAPSED;
        this.am = 0;
        this.aj = -1;
        this.ap = 1;
        this.aq = "";
        this.as = "";
        this.ao = "";
        this.aw = false;
        this.ba = false;
        this.ay = true;
        this.bb = false;
        this.bg = null;
        this.bl = false;
        this.n = new ArrayList(5);
        this.r = -1;
        this.p = 1.0f;
        this.w = SheetState.COLLAPSED;
        this.v = true;
        dZU_(context, attributeSet);
    }

    private void c(Context context) {
        this.as = context.getString(R.string._2130851313_res_0x7f0235f1);
        this.aq = context.getString(R.string._2130851315_res_0x7f0235f3);
        this.ao = context.getString(R.string._2130851316_res_0x7f0235f4);
    }

    private void setDragView(View view) {
        View view2 = this.bk;
        if (view2 != null) {
            view2.setOnClickListener(null);
            this.af.d(this.bk.getPaddingLeft(), this.bk.getPaddingTop(), this.bk.getPaddingRight(), this.bk.getPaddingBottom());
        } else {
            Log.e("HwBottomSheet", "mDragView is null!");
        }
        this.bk = view;
        if (view != null) {
            view.setClickable(true);
            this.bk.setOnClickListener(this.o);
        }
    }

    private void a(Context context) {
        this.ar.e(19);
        this.ar.d(context, this.ax, this.az, this.bc);
        this.at = this.ar.h();
    }

    private void b() {
        if (!this.b || this.d == null) {
            return;
        }
        int i = c.b[this.ai.ordinal()];
        if (i == 1) {
            this.d.setImageResource(this.ad);
        } else if (i == 2) {
            this.d.setImageResource(this.z);
        } else {
            if (i != 4) {
                return;
            }
            this.d.setImageResource(this.u);
        }
    }

    private void eaj_(MotionEvent motionEvent) {
        if (eab_(motionEvent)) {
            return;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            c(this.w);
        }
    }

    private boolean eaf_(MotionEvent motionEvent) {
        float abs = Math.abs(motionEvent.getX() - this.l);
        return abs <= ((float) this.ak.e()) || abs <= Math.abs(motionEvent.getY() - this.m);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        private static final int[] c = {android.R.attr.layout_weight};
        float e;

        public LayoutParams() {
            super(-1, -1);
            this.e = 0.0f;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.e = 0.0f;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.e = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.e = 0.0f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c);
            if (obtainStyledAttributes != null) {
                this.e = obtainStyledAttributes.getFloat(0, 0.0f);
                obtainStyledAttributes.recycle();
            }
        }
    }

    private void dZU_(Context context, AttributeSet attributeSet) {
        eac_(context, attributeSet);
        this.af.eiZ_(context, attributeSet);
        int dimension = (int) getResources().getDimension(R.dimen._2131363977_res_0x7f0a0889);
        this.an = dimension;
        this.r = this.ay ? a(this.r, dimension) : this.r;
        this.f = true;
        a aVar = null;
        this.o = new d(this, aVar);
        this.ak = HwViewDragHelper.eas_(this, 1.0f, new e(this, aVar));
        dZT_(context, context.getResources());
        c(context);
        b(context);
    }

    private void eac_(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100181_res_0x7f060215, R.attr._2131100265_res_0x7f060269, R.attr._2131100279_res_0x7f060277, R.attr._2131100280_res_0x7f060278, R.attr._2131100343_res_0x7f0602b7, R.attr._2131100347_res_0x7f0602bb, R.attr._2131100373_res_0x7f0602d5, R.attr._2131100374_res_0x7f0602d6, R.attr._2131100375_res_0x7f0602d7, R.attr._2131100376_res_0x7f0602d8, R.attr._2131100377_res_0x7f0602d9, R.attr._2131100378_res_0x7f0602da, R.attr._2131100379_res_0x7f0602db, R.attr._2131100380_res_0x7f0602dc, R.attr._2131100381_res_0x7f0602dd, R.attr._2131100382_res_0x7f0602de, R.attr._2131100395_res_0x7f0602eb, R.attr._2131100415_res_0x7f0602ff, R.attr._2131100489_res_0x7f060349, R.attr._2131100525_res_0x7f06036d});
        this.bi = obtainStyledAttributes.getResourceId(3, -1);
        this.bj = obtainStyledAttributes.getResourceId(18, -1);
        this.c = obtainStyledAttributes.getResourceId(17, -1);
        this.e = obtainStyledAttributes.getResourceId(2, -1);
        this.p = obtainStyledAttributes.getFloat(0, 1.0f);
        this.b = obtainStyledAttributes.getBoolean(8, true);
        this.k = obtainStyledAttributes.getDimensionPixelSize(5, e(8));
        this.ay = obtainStyledAttributes.getBoolean(4, true);
        this.aw = obtainStyledAttributes.getBoolean(12, false);
        this.ap = obtainStyledAttributes.getInt(16, 1);
        this.r = obtainStyledAttributes.getDimensionPixelSize(19, e(48));
        this.ai = SheetState.values()[obtainStyledAttributes.getInt(15, SheetState.COLLAPSED.getStateValue())];
        if (getResources().getConfiguration().orientation == 2 && this.p < 1.0f && this.ai == SheetState.ANCHORED) {
            this.ai = SheetState.EXPANDED;
        }
        this.q = obtainStyledAttributes.getResourceId(14, -1);
        this.s = obtainStyledAttributes.getResourceId(10, -1);
        this.x = obtainStyledAttributes.getResourceId(7, -1);
        this.y = obtainStyledAttributes.getResourceId(11, -1);
        this.ad = obtainStyledAttributes.getResourceId(13, -1);
        this.z = obtainStyledAttributes.getResourceId(9, -1);
        this.u = obtainStyledAttributes.getResourceId(6, -1);
        this.av = obtainStyledAttributes.getBoolean(1, false);
        obtainStyledAttributes.recycle();
    }

    private void d(int i) {
        this.aj = i;
        if (this.ak.c()) {
            sendAccessibilityEvent(16384);
        }
    }

    private void c(SheetState sheetState) {
        this.d.eao_(this.d.ean_(sheetState), false);
        this.d.a();
        this.d.c();
    }

    private void eah_(MotionEvent motionEvent) {
        if (eab_(motionEvent)) {
            return;
        }
        c(this.w);
    }

    private void b(int i) {
        float a2 = a(this.al);
        float a3 = a(i);
        float f = getResources().getConfiguration().orientation == 2 ? 1.0f : this.p;
        if (Float.compare(f, 1.0f) < 0) {
            c(a2, a3, f);
        } else if (Float.compare(f, 1.0f) == 0) {
            b(a2, a3, f);
        }
        this.al = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        SheetState sheetState = this.ai;
        if (sheetState != SheetState.DRAGGING) {
            this.w = sheetState;
        }
        d(SheetState.DRAGGING);
        this.t = a(i);
        b(i);
        float d2 = d();
        float f = this.t * 7.0f;
        if (f > 1.0f) {
            f = 1.0f;
        }
        a(1.0f - f, d2);
        ead_(this.be);
    }

    private void e(Context context) {
        this.ar.e(19);
        this.ar.e(context);
        this.at = this.ar.h();
    }

    private void dZT_(Context context, Resources resources) {
        boolean l = l();
        this.bf = l;
        if (!l) {
            this.u = R$drawable.hwbottomsheet_indicate_down_with_anim;
            this.ad = R$drawable.hwbottomsheet_indicate_up_with_anim;
            this.z = R$drawable.hwbottomsheet_indicate_middle_with_anim;
            this.aa = context.getDrawable(R$drawable.hwbottomsheet_up_middle_anim);
            this.ac = context.getDrawable(R$drawable.hwbottomsheet_middle_down_anim);
            this.ab = context.getDrawable(R$drawable.hwbottomsheet_dowm_middle_anim);
            this.ag = context.getDrawable(R$drawable.hwbottomsheet_middle_up_anim);
            this.ah = context.getDrawable(R$drawable.hwbottomsheet_up_down_anim);
            this.ae = context.getDrawable(R$drawable.hwbottomsheet_down_up_anim);
            return;
        }
        this.aa = getResources().getDrawable(this.q);
        this.ac = getResources().getDrawable(this.s);
        this.ab = getResources().getDrawable(this.x);
        this.ag = getResources().getDrawable(this.y);
        this.an = getMaxIndicatorHeight();
    }

    private float d() {
        if (Float.compare(this.p, 0.0f) == 0) {
            return 0.0f;
        }
        return this.t / this.p;
    }

    private int e(int i) {
        return (int) ((i * getResources().getDisplayMetrics().density) + 0.5f);
    }

    private boolean e(SheetState sheetState) {
        if ((!isEnabled() && this.ai == sheetState) || sheetState == null || sheetState == SheetState.DRAGGING) {
            return false;
        }
        return (this.v || this.be != null) && this.ai != SheetState.DRAGGING;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(SheetState sheetState, boolean z) {
        if (e(sheetState)) {
            this.ak.b(z);
            if (this.ak.b() == 2) {
                this.ak.a();
            }
            if (this.v) {
                d(sheetState);
                return;
            }
            if (this.ai == SheetState.HIDDEN) {
                this.be.setVisibility(0);
                requestLayout();
            }
            int i = c.b[sheetState.ordinal()];
            if (i == 1) {
                b(0.0f, 0);
                return;
            }
            if (i == 2) {
                b(this.p, 0);
            } else if (i == 3) {
                b(a(d(0.0f) + this.r), 0);
            } else {
                if (i != 4) {
                    return;
                }
                b(1.0f, 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int d(float f) {
        return ((getMeasuredHeight() - getPaddingBottom()) - this.r) - ((int) (f * this.g));
    }

    private void b(int i, int i2, int i3) {
        View childAt = getChildAt(i);
        if (childAt == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
        if (!(childAt.getVisibility() == 8 && i == 0) && (layoutParams instanceof LayoutParams)) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (childAt == this.be) {
                i3 -= ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin;
            }
            childAt.measure(a(layoutParams2, i2), b(layoutParams2, i3));
        }
    }

    private int a(LayoutParams layoutParams, int i) {
        if (((ViewGroup.MarginLayoutParams) layoutParams).width == -2) {
            return View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE);
        }
        if (((ViewGroup.MarginLayoutParams) layoutParams).width == -1) {
            return View.MeasureSpec.makeMeasureSpec(i, 1073741824);
        }
        Log.i("HwBottomSheet", "layoutParams width is others");
        return View.MeasureSpec.makeMeasureSpec(((ViewGroup.MarginLayoutParams) layoutParams).width, 1073741824);
    }

    boolean b(float f, int i) {
        View view;
        if (!isEnabled() || (view = this.be) == null || !this.ak.eaG_(view, view.getLeft(), d(f))) {
            return false;
        }
        k();
        postInvalidateOnAnimation();
        return true;
    }

    private boolean e(int i, int i2) {
        if (getChildCount() != 2) {
            return false;
        }
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824 && mode != Integer.MIN_VALUE) {
            Log.e("HwBottomSheet", "checkMeasureSpec: Width must have an exact value or match parent.");
            return false;
        }
        if (mode2 != 1073741824 && mode2 != Integer.MIN_VALUE) {
            Log.e("HwBottomSheet", "checkMeasureSpec: Height must have an exact value or match parent");
            return false;
        }
        View childAt = getChildAt(1);
        this.be = childAt;
        if (childAt == null) {
            return false;
        }
        if (this.bk == null) {
            setDragView(childAt);
        }
        if (this.be.getVisibility() != 0) {
            this.ai = SheetState.HIDDEN;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float a(int i) {
        int d2 = d(0.0f);
        int i2 = this.g;
        if (i2 == 0) {
            return 0.0f;
        }
        return (d2 - i) / i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(SheetState sheetState) {
        SheetState sheetState2 = this.ai;
        if (sheetState2 == sheetState) {
            return;
        }
        dZX_(this, sheetState2, sheetState);
        this.ai = sheetState;
        View view = this.bh;
        if (view == null) {
            return;
        }
        if (sheetState == SheetState.COLLAPSED) {
            view.setOnClickListener(this.o);
        } else {
            view.setOnClickListener(null);
        }
    }

    private boolean eae_(MotionEvent motionEvent) {
        this.bd = false;
        this.l = motionEvent.getX();
        float y = motionEvent.getY();
        this.m = y;
        return a(this.bk, (int) this.l, (int) y);
    }

    private void dZX_(View view, SheetState sheetState, SheetState sheetState2) {
        synchronized (this.n) {
            Iterator<SheetSlideListener> it = this.n.iterator();
            while (it.hasNext()) {
                it.next().onSheetStateChanged(view, sheetState, sheetState2);
            }
            HwDragImageView hwDragImageView = this.d;
            if (hwDragImageView != null) {
                hwDragImageView.d();
            }
        }
    }

    boolean e() {
        return this.bb;
    }

    private boolean e(float f, float f2) {
        return Math.abs(f) > Math.abs(f2);
    }

    private void c(float f, float f2, float f3) {
        float f4 = (1.0f + f3) / 2.0f;
        float f5 = f3 / 2.0f;
        if (Float.compare(f2, f4) > 0 && Float.compare(f, f4) <= 0) {
            d(0);
            return;
        }
        if (Float.compare(f2, f4) <= 0 && Float.compare(f, f4) > 0) {
            d(1);
            return;
        }
        if (Float.compare(f2, f5) > 0 && Float.compare(f, f5) <= 0) {
            d(1);
        } else {
            if (Float.compare(f2, f5) > 0 || Float.compare(f, f5) <= 0) {
                return;
            }
            d(2);
        }
    }

    private int b(LayoutParams layoutParams, int i) {
        if (((ViewGroup.MarginLayoutParams) layoutParams).height == -2) {
            return View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE);
        }
        float f = layoutParams.e;
        if (f > 0.0f && f < 1.0f) {
            i = (int) (i * f);
        } else if (((ViewGroup.MarginLayoutParams) layoutParams).height != -1) {
            i = ((ViewGroup.MarginLayoutParams) layoutParams).height;
        } else {
            Log.i("HwBottomSheet", "Do Nothing default height = height!");
        }
        return View.MeasureSpec.makeMeasureSpec(i, 1073741824);
    }

    private void d(int i, int i2, int i3) {
        View childAt = getChildAt(i);
        if (childAt == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (childAt == this.be) {
                i3 = d(this.t);
            }
            int measuredHeight = childAt.getMeasuredHeight();
            int i4 = i2 + ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin;
            childAt.layout(i4, i3, childAt.getMeasuredWidth() + i4, measuredHeight + i3);
        }
    }

    private void ead_(View view) {
        synchronized (this.n) {
            Iterator<SheetSlideListener> it = this.n.iterator();
            while (it.hasNext()) {
                it.next().onSheetSlide(view, this.t);
            }
        }
    }

    private void a(float f, float f2) {
        HwDragImageView hwDragImageView;
        HwDragImageView hwDragImageView2;
        HwDragImageView hwDragImageView3;
        View view = this.f10601a;
        if (view == null || this.bh == null) {
            return;
        }
        view.setAlpha(f);
        this.bh.setAlpha(f2);
        if (this.b && (hwDragImageView3 = this.d) != null) {
            hwDragImageView3.setAlpha(f2);
        }
        if (f == 0.0f) {
            this.f10601a.setVisibility(8);
            if (this.b && (hwDragImageView2 = this.d) != null) {
                hwDragImageView2.setVisibility(0);
            }
        } else {
            this.f10601a.setVisibility(0);
            if (this.b && (hwDragImageView = this.d) != null) {
                hwDragImageView.setVisibility(8);
            }
        }
        if (f2 == 0.0f) {
            this.bh.setVisibility(8);
        } else {
            this.bh.setVisibility(0);
        }
    }

    private boolean eab_(MotionEvent motionEvent) {
        HwDragImageView hwDragImageView;
        return motionEvent == null || (hwDragImageView = this.d) == null || !hwDragImageView.e();
    }

    private boolean eaa_(float f, float f2, MotionEvent motionEvent) {
        float f3 = this.i;
        float f4 = f2 - this.h;
        this.i = f;
        this.h = f2;
        if (e(f - f3, f4) || !a(this.j, (int) motionEvent.getX(), (int) motionEvent.getY())) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (f4 > 0.0f) {
            if (skv.ear_(this.j, true) > 0 && this.ak.b() != 1) {
                this.bl = true;
                this.bg = motionEvent;
                return super.dispatchTouchEvent(motionEvent);
            }
            if (this.bl) {
                this.bb = true;
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.setAction(3);
                super.dispatchTouchEvent(obtain);
                obtain.recycle();
                motionEvent.setAction(0);
            }
            this.bl = false;
            return onTouchEvent(motionEvent);
        }
        if (f4 >= 0.0f) {
            Log.i("HwBottomSheet", "dispatchActionMove will not respond");
            return super.dispatchTouchEvent(motionEvent);
        }
        if (this.t < 1.0f) {
            MotionEvent motionEvent2 = this.bg;
            if (motionEvent2 != null) {
                this.ak.eaF_(motionEvent2);
                this.bg = null;
            }
            this.bl = false;
            return onTouchEvent(motionEvent);
        }
        if (!this.bl && this.ak.b() == 1) {
            this.ak.d();
            this.bb = true;
            motionEvent.setAction(0);
        }
        this.bl = true;
        return super.dispatchTouchEvent(motionEvent);
    }

    private boolean a(View view, int i, int i2) {
        if (view == null) {
            return false;
        }
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr2);
        int i3 = iArr[0] + i;
        int i4 = iArr2[0];
        boolean z = i3 >= i4 && i3 < i4 + view.getWidth();
        int i5 = iArr[1] + i2;
        int i6 = iArr2[1];
        return z && (i5 >= i6 && i5 < i6 + view.getHeight());
    }

    private ViewGroup dZS_(View view) {
        ViewGroup g = g();
        if (g == null) {
            Log.w("HwBottomSheet", "inflateDragView: indicator's layout might be null!");
            return null;
        }
        a(g);
        setMinibarLayoutParams(view);
        setDragContentPadding(view);
        g.addView(view, 0);
        return g;
    }

    private void a(ViewGroup viewGroup) {
        HwDragImageView hwDragImageView = (HwDragImageView) viewGroup.findViewById(R.id.sheet_indicate);
        this.d = hwDragImageView;
        if (hwDragImageView == null) {
            return;
        }
        hwDragImageView.setOnClickListener(this.o);
        this.d.e(this);
        HwViewDragHelper hwViewDragHelper = this.ak;
        if (hwViewDragHelper != null) {
            hwViewDragHelper.a(new a());
        }
        if (this.bf) {
            this.d.getLayoutParams().height = this.an;
        }
    }

    private void dZW_(Bundle bundle) {
        Serializable serializable = bundle.getSerializable("sliding_state");
        if (serializable instanceof SheetState) {
            this.ai = (SheetState) serializable;
        } else {
            this.ai = SheetState.COLLAPSED;
        }
        Serializable serializable2 = bundle.getSerializable("pre_idle_state");
        if (serializable2 instanceof SheetState) {
            this.w = (SheetState) serializable2;
        } else {
            this.w = SheetState.COLLAPSED;
        }
        Serializable serializable3 = bundle.getSerializable("sheet_anchor_point");
        if (serializable3 instanceof Float) {
            this.p = ((Float) serializable3).floatValue();
        } else {
            this.p = 1.0f;
        }
    }

    private void b(float f, float f2, float f3) {
        float f4 = f3 / 2.0f;
        if (Float.compare(f2, f4) > 0 && Float.compare(f, f4) <= 0) {
            d(0);
        } else {
            if (Float.compare(f2, f4) > 0 || Float.compare(f, f4) <= 0) {
                return;
            }
            d(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dZV_(Drawable drawable) {
        HwDragImageView hwDragImageView = this.d;
        if (hwDragImageView != null && drawable != null) {
            hwDragImageView.setImageDrawable(drawable);
            if (drawable instanceof AnimatedVectorDrawable) {
                ((AnimatedVectorDrawable) drawable).start();
                return;
            }
            return;
        }
        Log.w("HwBottomSheet", "startIndicatorAnimation: Indicate View or Indicator Drawable is null!!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int e(float f) {
        float f2 = getResources().getConfiguration().orientation == 2 ? 1.0f : this.p;
        if (f > 0.0f && Float.compare(this.t, f2) <= 0) {
            return d(f2);
        }
        if (f > 0.0f && Float.compare(this.t, f2) > 0) {
            return d(1.0f);
        }
        if (f < 0.0f && Float.compare(this.t, f2) >= 0) {
            return d(f2);
        }
        if (f < 0.0f && Float.compare(this.t, f2) < 0) {
            return d(0.0f);
        }
        if (Float.compare(this.t, (f2 + 1.0f) / 2.0f) >= 0) {
            return d(1.0f);
        }
        if (Float.compare(this.t, f2 / 2.0f) >= 0) {
            return d(f2);
        }
        return d(0.0f);
    }

    private void dZY_(AccessibilityEvent accessibilityEvent) {
        String str;
        HwViewDragHelper hwViewDragHelper = this.ak;
        boolean z = (hwViewDragHelper == null || hwViewDragHelper.b() == 0) ? false : true;
        if (accessibilityEvent.getEventType() == 16384 && z) {
            int i = this.aj;
            if (i == 0) {
                str = this.ao;
            } else if (i != 1) {
                str = i != 2 ? "" : this.aq;
            } else {
                str = this.as;
            }
            accessibilityEvent.setClassName("HwBottomSheet");
            accessibilityEvent.getText().clear();
            accessibilityEvent.setContentDescription(str);
        }
    }

    boolean a() {
        return this.bf;
    }
}
