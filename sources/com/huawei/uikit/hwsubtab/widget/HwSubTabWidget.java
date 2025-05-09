package com.huawei.uikit.hwsubtab.widget;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.uikit.hweffect.engine.HwBlurable;
import com.huawei.uikit.hwsubtab.widget.HwSubTabViewContainer;
import com.huawei.uikit.hwsubtab.widget.HwSubTabWidget;
import com.huawei.uikit.hwunifiedinteract.widget.HwKeyEventDetector;
import defpackage.slc;
import defpackage.slm;
import defpackage.slp;
import defpackage.smp;
import defpackage.smr;
import defpackage.smy;
import defpackage.snj;
import java.lang.reflect.Method;

/* loaded from: classes7.dex */
public class HwSubTabWidget extends LinearLayout implements HwBlurable {

    /* renamed from: a, reason: collision with root package name */
    private int f10754a;
    private int aa;
    private int ab;
    private String ac;
    private boolean ad;
    private smy ae;
    private boolean af;
    private d ag;
    private HwSubTabViewContainer.SlidingTabStrip ah;
    private smy ai;
    private OnSubTabChangeListener aj;
    private int ak;
    private Typeface al;
    private Typeface am;
    private Context an;
    private int aq;
    private int ar;
    public HwSubTabViewContainer b;
    private int c;
    private int d;
    protected ImageView e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private slp k;
    private boolean l;
    private ColorStateList m;
    private int n;
    private boolean o;
    private int p;
    private int q;
    private HwKeyEventDetector r;
    private int s;
    private snj t;
    private ArgbEvaluator u;
    private ValueAnimator v;
    private HwKeyEventDetector.OnGlobalNextTabEventListener w;
    private boolean x;
    private HwKeyEventDetector.OnNextTabEventListener y;
    private float z;

    public interface OnSubTabChangeListener {
        void onSubTabReselected(smy smyVar);

        void onSubTabSelected(smy smyVar);

        void onSubTabUnselected(smy smyVar);
    }

    static class a extends View.BaseSavedState {
        public static final Parcelable.Creator<a> CREATOR = new e();

        /* renamed from: a, reason: collision with root package name */
        private int f10756a;

        static final class e implements Parcelable.Creator<a> {
            e() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public a[] newArray(int i) {
                return new a[i];
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: ehz_, reason: merged with bridge method [inline-methods] */
            public a createFromParcel(Parcel parcel) {
                return new a(parcel);
            }
        }

        a(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (parcel == null) {
                Log.w("HwSubTabWidget", "Parameter dest of writeToParcel should not be null.");
            } else {
                super.writeToParcel(parcel, i);
                parcel.writeInt(this.f10756a);
            }
        }

        a(Parcel parcel) {
            super(parcel);
            this.f10756a = parcel.readInt();
        }

        public void a(int i) {
            this.f10756a = i;
        }

        public int a() {
            return this.f10756a;
        }
    }

    class b implements ViewTreeObserver.OnGlobalLayoutListener {
        b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            HwSubTabWidget.this.b.fullScroll(66);
            HwSubTabWidget.this.ah.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    class c implements HwKeyEventDetector.OnNextTabEventListener {
        c() {
        }

        @Override // com.huawei.uikit.hwunifiedinteract.widget.HwKeyEventDetector.OnNextTabEventListener
        public boolean onNextTab(int i, KeyEvent keyEvent) {
            if (i == 1) {
                HwSubTabWidget.this.i();
            }
            return true;
        }
    }

    class d implements View.OnClickListener {
        private d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == null) {
                Log.w("HwSubTabWidget", "Parameter view of onClick should not be null.");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (!HwSubTabWidget.this.af) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            HwSubTabWidget.this.ad = true;
            int childCount = HwSubTabWidget.this.ah.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = HwSubTabWidget.this.ah.getChildAt(i);
                childAt.setSelected(childAt == view);
                if (childAt == view && HwSubTabWidget.this.p == 0) {
                    HwSubTabWidget.this.b.a(i);
                }
            }
            if (view instanceof SubTabView) {
                ((SubTabView) view).getSubTab().g();
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        /* synthetic */ d(HwSubTabWidget hwSubTabWidget, b bVar) {
            this();
        }
    }

    class e implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ SubTabView c;
        final /* synthetic */ SubTabView e;

        e(SubTabView subTabView, SubTabView subTabView2) {
            this.c = subTabView;
            this.e = subTabView2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            HwSubTabWidget.this.b.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
            if (HwSubTabWidget.this.x) {
                return;
            }
            float animatedFraction = valueAnimator.getAnimatedFraction();
            HwSubTabWidget.this.d(animatedFraction, this.c, this.e);
            HwSubTabWidget.this.b(animatedFraction, this.c, this.e);
        }
    }

    class h implements HwKeyEventDetector.OnGlobalNextTabEventListener {
        h() {
        }

        @Override // com.huawei.uikit.hwunifiedinteract.widget.HwKeyEventDetector.OnGlobalNextTabEventListener
        public boolean onGlobalNextTab(int i, KeyEvent keyEvent) {
            if (i == 1) {
                HwSubTabWidget.this.i();
            }
            return true;
        }
    }

    public HwSubTabWidget(Context context) {
        this(context, null);
    }

    private void setSubTabSelectedInner(int i) {
        int childCount = this.ah.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            SubTabView c2 = c(i2);
            boolean z = i2 == i;
            if (c2 != null) {
                c2.setTypeface(z ? this.am : this.al);
                c2.setSelected(z);
            }
            i2++;
        }
    }

    private void setTabHorizontalPadding(SubTabView subTabView) {
        if (this.p == 0) {
            m();
        } else {
            subTabView.d();
        }
    }

    private void setValueFromPlume(Context context) {
        Method b2 = slc.b("getBoolean", new Class[]{Context.class, View.class, String.class, Boolean.TYPE}, "huawei.android.widget.HwPlume");
        if (b2 == null) {
            setExtendedNextTabEnabled(true, true);
            setExtendedNextTabEnabled(false, true);
            return;
        }
        Object c2 = slc.c((Object) null, b2, new Object[]{context, this, "switchTabEnabled", true});
        if (c2 instanceof Boolean) {
            setExtendedNextTabEnabled(true, ((Boolean) c2).booleanValue());
        } else {
            setExtendedNextTabEnabled(true, true);
        }
        Object c3 = slc.c((Object) null, b2, new Object[]{context, this, "switchTabWhenFocusedEnabled", true});
        if (c3 instanceof Boolean) {
            setExtendedNextTabEnabled(false, ((Boolean) c3).booleanValue());
        } else {
            setExtendedNextTabEnabled(false, true);
        }
    }

    protected HwKeyEventDetector.OnNextTabEventListener a() {
        return new c();
    }

    public smy a(int i) {
        View childAt = this.ah.getChildAt(i);
        if (childAt instanceof SubTabView) {
            return ((SubTabView) childAt).getSubTab();
        }
        return null;
    }

    public void b(smy smyVar, int i, boolean z) {
        if (smyVar == null) {
            Log.w("HwSubTabWidget", "Parameter subTab of addSubTab should not be null.");
            return;
        }
        SubTabView a2 = a(smyVar);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        layoutParams.setMarginStart(this.ar);
        layoutParams.setMarginEnd(this.ar);
        a2.setLayoutParams(layoutParams);
        this.ah.addView(a2, i, layoutParams);
        smyVar.c(i);
        d(i, getSubTabCount(), true);
        if (z) {
            smyVar.g();
            a2.setSelected(true);
            a2.setTextSize(0, this.aa);
        } else {
            a2.setTextSize(0, this.g);
        }
        setTabHorizontalPadding(a2);
    }

    public SubTabView c(int i) {
        View childAt = this.ah.getChildAt(i);
        if (childAt instanceof SubTabView) {
            return (SubTabView) childAt;
        }
        return null;
    }

    public void c(int i, int i2) {
        if (this.p == 1) {
            this.g = i;
            this.aa = i2;
        }
    }

    public void c(smy smyVar) {
        if (this.x) {
            return;
        }
        Context context = this.an;
        FragmentTransaction disallowAddToBackStack = context instanceof FragmentActivity ? ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().disallowAddToBackStack() : null;
        smy smyVar2 = this.ae;
        if ((smyVar2 == null || smyVar2.e() == -1) && smyVar != null && smyVar.e() != -1) {
            this.b.setScrollPosition(smyVar.e(), 0.0f);
        }
        if (this.ae == smyVar) {
            a(disallowAddToBackStack);
        } else {
            a(smyVar, disallowAddToBackStack);
        }
        if (disallowAddToBackStack == null || disallowAddToBackStack.isEmpty()) {
            return;
        }
        disallowAddToBackStack.commit();
    }

    public boolean c() {
        return this.ad;
    }

    protected SubTabView d(smy smyVar) {
        return new SubTabView(getContext(), smyVar);
    }

    protected HwKeyEventDetector d() {
        return new HwKeyEventDetector(this.an);
    }

    public void d(int i) {
        SubTabView c2 = c(i);
        if (c2 != null) {
            c2.a();
        }
        requestLayout();
    }

    /* JADX WARN: Code restructure failed: missing block: B:1:0x0000, code lost:
    
        if (r3 != false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0003, code lost:
    
        if (r1 >= r2) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0005, code lost:
    
        r3 = a(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0009, code lost:
    
        if (r3 == null) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x000b, code lost:
    
        r3.c(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0011, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x000e, code lost:
    
        r1 = r1 + 1;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x000e -> B:2:0x0003). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d(int r1, int r2, boolean r3) {
        /*
            r0 = this;
            if (r3 == 0) goto L3
            goto Le
        L3:
            if (r1 >= r2) goto L11
            smy r3 = r0.a(r1)
            if (r3 == 0) goto Le
            r3.c(r1)
        Le:
            int r1 = r1 + 1
            goto L3
        L11:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwsubtab.widget.HwSubTabWidget.d(int, int, boolean):void");
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        if (!this.k.edk_(this)) {
            super.draw(canvas);
        } else {
            this.k.edj_(canvas, this);
            super.dispatchDraw(canvas);
        }
    }

    protected HwKeyEventDetector.OnGlobalNextTabEventListener e() {
        return new h();
    }

    public void e(int i) {
        if (this.ah == null) {
            return;
        }
        smy a2 = a(i);
        if (a2 != null) {
            a2.c(-1);
        }
        this.ah.removeViewAt(i);
        if (getSubTabCount() == 0) {
            this.ai = null;
            this.ae = null;
        }
        d(i, getSubTabCount(), false);
        if (a2 == this.ae) {
            int i2 = i - 1;
            smy a3 = a(i2 > 0 ? i2 : 0);
            if (a3 != null) {
                c(a3);
                e(a3);
            }
        }
    }

    public void e(smy smyVar) {
        smy smyVar2;
        if (this.x) {
            return;
        }
        smy smyVar3 = this.ae;
        if ((smyVar3 == null || smyVar3.e() == -1) && smyVar.e() != -1) {
            this.b.setScrollPosition(smyVar.e(), 0.0f);
        }
        smy smyVar4 = this.ae;
        if (smyVar4 == smyVar) {
            OnSubTabChangeListener onSubTabChangeListener = this.aj;
            if (onSubTabChangeListener == null || smyVar4 == null) {
                return;
            }
            onSubTabChangeListener.onSubTabReselected(smyVar4);
            return;
        }
        if (smyVar4 != null && this.p == 1) {
            i(smyVar);
        }
        setSubTabSelectedInner(smyVar.e());
        OnSubTabChangeListener onSubTabChangeListener2 = this.aj;
        if (onSubTabChangeListener2 != null && (smyVar2 = this.ae) != null) {
            onSubTabChangeListener2.onSubTabUnselected(smyVar2);
        }
        this.ai = this.ae;
        this.ae = smyVar;
        OnSubTabChangeListener onSubTabChangeListener3 = this.aj;
        if (onSubTabChangeListener3 != null) {
            onSubTabChangeListener3.onSubTabSelected(smyVar);
        }
    }

    public smy f() {
        return new smy(this);
    }

    protected int g() {
        return this.s;
    }

    protected int getAdjustTrigleWidth() {
        return getMeasuredWidth() / 2;
    }

    @Override // com.huawei.uikit.hweffect.engine.HwBlurable
    public int getBlurColor() {
        return this.n;
    }

    @Override // com.huawei.uikit.hweffect.engine.HwBlurable
    public int getBlurType() {
        return this.q;
    }

    public int getFadingMargin() {
        return this.b.getFadingMargin();
    }

    public int getFocusPathColor() {
        return this.ab;
    }

    public int getIndicatorHeight() {
        return this.ah.getSelectedIndicatorHeight();
    }

    public OnSubTabChangeListener getOnSubTabChangeListener() {
        return this.aj;
    }

    public smy getSelectedSubTab() {
        return this.ae;
    }

    public int getSelectedSubTabPostion() {
        int subTabCount = getSubTabCount();
        for (int i = 0; i < subTabCount; i++) {
            if (this.ae == a(i)) {
                return i;
            }
        }
        return -1;
    }

    public int getSubTabAppearance() {
        return this.p;
    }

    public HwSubTabViewContainer.SlidingTabStrip getSubTabContentView() {
        return this.ah;
    }

    public int getSubTabCount() {
        return this.ah.getChildCount();
    }

    public int getSubTabItemActivatedTextSize() {
        return this.aa;
    }

    public int getSubTabItemMargin() {
        return this.ar;
    }

    public int getSubTabItemPadding() {
        return this.ak;
    }

    public int getSubTabItemPaddingSecondary() {
        return this.aq;
    }

    public int getSubTabItemTextSize() {
        return this.g;
    }

    public int getSubTabLeftScrollPadding() {
        return this.h;
    }

    public HwSubTabViewContainer getSubTabViewContainer() {
        return this.b;
    }

    public void h() {
        HwSubTabViewContainer.SlidingTabStrip slidingTabStrip = this.ah;
        if (slidingTabStrip == null) {
            return;
        }
        slidingTabStrip.removeAllViews();
        this.ai = null;
        this.ae = null;
    }

    @Override // com.huawei.uikit.hweffect.engine.HwBlurable
    public boolean isBlurEnable() {
        return this.l;
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        this.t.ejb_(windowInsets);
        return super.onApplyWindowInsets(windowInsets);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.t.d(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        HwKeyEventDetector hwKeyEventDetector = this.r;
        if (hwKeyEventDetector != null) {
            hwKeyEventDetector.a(this.y);
            this.r.eiy_(this, this.w);
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.o = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        HwKeyEventDetector hwKeyEventDetector = this.r;
        if (hwKeyEventDetector != null) {
            hwKeyEventDetector.e();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        HwKeyEventDetector hwKeyEventDetector = this.r;
        if (hwKeyEventDetector == null || !hwKeyEventDetector.eix_(i, keyEvent)) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        HwKeyEventDetector hwKeyEventDetector = this.r;
        if (hwKeyEventDetector == null || !hwKeyEventDetector.eix_(i, keyEvent)) {
            return super.onKeyUp(i, keyEvent);
        }
        return true;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.o) {
            smy smyVar = this.ae;
            if (smyVar != null && smyVar.e() != -1) {
                setSubTabScrollingOffsets(this.ae.e(), 0.0f);
                m();
            }
            this.o = false;
        }
        this.t.e(true);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.f, 1073741824);
        int childMeasureSpec = LinearLayout.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight(), Integer.MIN_VALUE);
        this.ah.measure(childMeasureSpec, makeMeasureSpec);
        this.b.measure(childMeasureSpec, makeMeasureSpec);
        super.onMeasure(i, makeMeasureSpec);
        if (this.p == 0) {
            b();
            super.onMeasure(i, makeMeasureSpec);
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (this.an.getApplicationInfo().targetSdkVersion <= 18) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        if (!(parcelable instanceof a)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        a aVar = (a) parcelable;
        super.onRestoreInstanceState(aVar.getSuperState());
        int i = aVar.f10756a;
        if (i < 0 || i >= getSubTabCount()) {
            return;
        }
        smy a2 = a(i);
        if (a2 != null) {
            a2.g();
        }
        SubTabView c2 = c(i);
        if (c2 != null) {
            c2.setSelected(true);
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        if (this.an.getApplicationInfo().targetSdkVersion <= 18) {
            return super.onSaveInstanceState();
        }
        int selectedSubTabPostion = getSelectedSubTabPostion();
        a aVar = new a(super.onSaveInstanceState());
        aVar.f10756a = selectedSubTabPostion;
        return aVar;
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i != 0) {
            this.k.edl_(this);
            return;
        }
        this.k.edi_(this, this.q);
        this.k.edm_(this, isBlurEnable());
        int i2 = this.n;
        if (i2 != -16777216) {
            this.k.edn_(this, i2);
        }
    }

    @Override // com.huawei.uikit.hweffect.engine.HwBlurable
    public void setBlurColor(int i) {
        this.n = i;
    }

    @Override // com.huawei.uikit.hweffect.engine.HwBlurable
    public void setBlurEnable(boolean z) {
        this.l = z;
        this.k.edm_(this, isBlurEnable());
    }

    @Override // com.huawei.uikit.hweffect.engine.HwBlurable
    public void setBlurType(int i) {
        this.q = i;
    }

    @Override // android.view.View
    public void setClickable(boolean z) {
        this.af = z;
    }

    public void setConfigChange(boolean z) {
        this.o = z;
    }

    public void setExtendedNextTabEnabled(boolean z, boolean z2) {
        HwKeyEventDetector hwKeyEventDetector = this.r;
        if (hwKeyEventDetector == null) {
            return;
        }
        if (z) {
            if (!z2) {
                this.w = null;
                hwKeyEventDetector.eiy_(this, null);
                return;
            } else {
                HwKeyEventDetector.OnGlobalNextTabEventListener e2 = e();
                this.w = e2;
                this.r.eiy_(this, e2);
                return;
            }
        }
        if (!z2) {
            this.y = null;
            hwKeyEventDetector.a((HwKeyEventDetector.OnNextTabEventListener) null);
        } else {
            HwKeyEventDetector.OnNextTabEventListener a2 = a();
            this.y = a2;
            this.r.a(a2);
        }
    }

    public void setFocusPathColor(int i) {
        this.ab = i;
    }

    public void setFunctionMenuContent(String str) {
        this.ac = str;
    }

    public void setIsViewPagerScroll(boolean z) {
        if (this.p == 0) {
            return;
        }
        this.x = z;
    }

    public void setOnSubTabChangeListener(OnSubTabChangeListener onSubTabChangeListener) {
        this.aj = onSubTabChangeListener;
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        this.t.d(i, i2, i3, i4);
    }

    public void setSubTabClicked(boolean z) {
        this.ad = z;
    }

    public void setSubTabItemPadding(int i) {
        this.ak = i;
    }

    public void setSubTabItemPaddingSecondary(int i) {
        this.aq = i;
    }

    public void setSubTabLeftScrollPadding(int i) {
        this.h = i;
    }

    public void setSubTabScrollingOffsets(int i, float f) {
        this.b.setScrollPosition(i, f);
        if (this.p != 1 || f == 0.0f) {
            return;
        }
        ValueAnimator valueAnimator = this.v;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.v.cancel();
            n();
        }
        SubTabView c2 = c(this.ah.f10752a);
        if (i >= this.ah.f10752a) {
            i++;
        }
        SubTabView c3 = c(i);
        if (c2 == null || c3 == null) {
            return;
        }
        d(f, c2, c3);
        b(f, c2, c3);
    }

    public void setSubTabSelected(int i) {
        smy a2 = a(i);
        if (a2 == null) {
            return;
        }
        smy smyVar = this.ae;
        if (smyVar == null || smyVar.e() == -1) {
            this.b.setScrollPosition(i, 0.0f);
        }
        if (this.p == 1 && this.ae != a2) {
            c(a2);
        }
        this.ae = a2;
        setSubTabSelectedInner(i);
    }

    public HwSubTabWidget(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100541_res_0x7f06037d);
    }

    private void ehx_(Context context, AttributeSet attributeSet, int i) {
        int dimensionPixelSize;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100338_res_0x7f0602b2, R.attr._2131100540_res_0x7f06037c, R.attr._2131100542_res_0x7f06037e, R.attr._2131100543_res_0x7f06037f, R.attr._2131100544_res_0x7f060380, R.attr._2131100545_res_0x7f060381, R.attr._2131100546_res_0x7f060382, R.attr._2131100547_res_0x7f060383, R.attr._2131100548_res_0x7f060384, R.attr._2131100549_res_0x7f060385, R.attr._2131100550_res_0x7f060386, R.attr._2131100551_res_0x7f060387, R.attr._2131100552_res_0x7f060388, R.attr._2131100553_res_0x7f060389, R.attr._2131100554_res_0x7f06038a, R.attr._2131100555_res_0x7f06038b, R.attr._2131100556_res_0x7f06038c, R.attr._2131100557_res_0x7f06038d}, i, R.style.Widget_Emui_HwSubTabBar);
        if (o()) {
            dimensionPixelSize = getResources().getDimensionPixelOffset(R.dimen._2131364451_res_0x7f0a0a63);
            if (this.z == 3.2f && this.p == 0) {
                dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131364452_res_0x7f0a0a64);
            }
        } else {
            dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131364450_res_0x7f0a0a62);
        }
        try {
            try {
                this.f = obtainStyledAttributes.getDimensionPixelSize(0, dimensionPixelSize);
            } catch (UnsupportedOperationException unused) {
                this.f = dimensionPixelSize;
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void m() {
        int childCount = this.ah.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.ah.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                ((LinearLayout.LayoutParams) layoutParams).width = -2;
                if (childAt instanceof SubTabView) {
                    ((SubTabView) childAt).d();
                }
            }
        }
    }

    private void n() {
        SubTabView c2;
        smy smyVar = this.ai;
        if (smyVar == null || (c2 = c(smyVar.e())) == null) {
            return;
        }
        ColorStateList subTabColor = c2.getSubTabColor();
        if (subTabColor != null) {
            c2.setTextColor(subTabColor.getDefaultColor());
        }
        c2.setTextSize(0, this.g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean o() {
        return Float.compare(this.z, 1.75f) >= 0 && smp.b(this.an);
    }

    public smy c(CharSequence charSequence) {
        return new smy(this, charSequence);
    }

    public HwSubTabWidget(Context context, AttributeSet attributeSet, int i) {
        super(e(context, i), attributeSet, i);
        this.ab = 0;
        this.af = true;
        this.h = 20;
        this.k = slp.e();
        this.l = false;
        this.o = false;
        this.n = -16777216;
        this.q = 4;
        this.p = 0;
        this.r = null;
        this.x = false;
        this.ad = false;
        ehv_(getContext(), attributeSet, i);
    }

    private void ehw_(Context context, AttributeSet attributeSet, int i) {
        View inflate;
        int dimensionPixelSize;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100338_res_0x7f0602b2, R.attr._2131100540_res_0x7f06037c, R.attr._2131100542_res_0x7f06037e, R.attr._2131100543_res_0x7f06037f, R.attr._2131100544_res_0x7f060380, R.attr._2131100545_res_0x7f060381, R.attr._2131100546_res_0x7f060382, R.attr._2131100547_res_0x7f060383, R.attr._2131100548_res_0x7f060384, R.attr._2131100549_res_0x7f060385, R.attr._2131100550_res_0x7f060386, R.attr._2131100551_res_0x7f060387, R.attr._2131100552_res_0x7f060388, R.attr._2131100553_res_0x7f060389, R.attr._2131100554_res_0x7f06038a, R.attr._2131100555_res_0x7f06038b, R.attr._2131100556_res_0x7f06038c, R.attr._2131100557_res_0x7f06038d}, i, R.style.Widget_Emui_HwSubTabBar);
        this.p = obtainStyledAttributes.getInt(1, 0);
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        if (layoutInflater == null) {
            obtainStyledAttributes.recycle();
            return;
        }
        if (this.p == 1) {
            inflate = layoutInflater.inflate(R.layout.hwsubtab_content_headline, (ViewGroup) this, true);
            dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131362720_res_0x7f0a03a0);
        } else {
            inflate = layoutInflater.inflate(R.layout.hwsubtab_content, (ViewGroup) this, true);
            dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131364467_res_0x7f0a0a73);
        }
        this.g = obtainStyledAttributes.getDimensionPixelSize(16, dimensionPixelSize);
        this.aa = obtainStyledAttributes.getDimensionPixelSize(9, getResources().getDimensionPixelSize(R.dimen._2131362718_res_0x7f0a039e));
        this.ab = obtainStyledAttributes.getColor(0, 0);
        this.b = (HwSubTabViewContainer) inflate.findViewById(R.id.hwsubtab_view_container);
        this.e = (ImageView) inflate.findViewById(R.id.hwsubtab_function_icon);
        this.ah = this.b.getTabStrip();
        ehu_(context, obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    private void ehu_(Context context, TypedArray typedArray) {
        this.z = this.an.getResources().getConfiguration().fontScale;
        if (o()) {
            this.ah.setSelectedIndicatorMargin(getResources().getDimensionPixelSize(R.dimen._2131364455_res_0x7f0a0a67));
        } else {
            this.ah.setSelectedIndicatorMargin(getResources().getDimensionPixelSize(R.dimen._2131364462_res_0x7f0a0a6e));
        }
        this.ah.setSelectedIndicatorHeight(typedArray.getDimensionPixelSize(8, getResources().getDimensionPixelSize(R.dimen._2131364454_res_0x7f0a0a66)));
        this.s = typedArray.getColor(7, ContextCompat.getColor(context, R.color._2131298503_res_0x7f0908c7));
        this.ar = typedArray.getDimensionPixelSize(11, getResources().getDimensionPixelSize(R.dimen._2131364456_res_0x7f0a0a68));
        this.ak = typedArray.getDimensionPixelSize(13, getResources().getDimensionPixelSize(R.dimen._2131364458_res_0x7f0a0a6a));
        this.aq = typedArray.getDimensionPixelSize(14, getResources().getDimensionPixelSize(R.dimen._2131364448_res_0x7f0a0a60));
        this.f10754a = typedArray.getResourceId(10, R.drawable._2131429583_res_0x7f0b08cf);
        this.d = typedArray.getResourceId(5, R.drawable._2131429583_res_0x7f0b08cf);
        this.c = typedArray.getDimensionPixelSize(12, 0);
        this.m = typedArray.getColorStateList(15);
        this.q = typedArray.getInteger(3, 4);
        this.n = typedArray.getColor(2, -16777216);
        this.i = typedArray.getDimensionPixelSize(17, getResources().getDimensionPixelSize(R.dimen._2131364466_res_0x7f0a0a72));
        this.j = typedArray.getDimensionPixelSize(6, getResources().getDimensionPixelSize(R.dimen._2131364453_res_0x7f0a0a65));
        this.b.setSubTabFaddingEdgeColor(typedArray.getColor(4, 0));
    }

    public smy b(CharSequence charSequence, HwSubTabListener hwSubTabListener, Object obj) {
        return new smy(this, charSequence, null, hwSubTabListener, obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j() {
        return getLayoutDirection() == 1;
    }

    private void i(smy smyVar) {
        int e2 = this.ae.e();
        int e3 = smyVar.e();
        SubTabView c2 = c(e2);
        SubTabView c3 = c(e3);
        if (c2 == null || c3 == null) {
            return;
        }
        n();
        ValueAnimator ofInt = ValueAnimator.ofInt(this.b.getScrollX(), b(smyVar));
        this.v = ofInt;
        ofInt.setDuration(300L);
        this.v.setInterpolator(AnimationUtils.loadInterpolator(this.an, R.interpolator._2131689478_res_0x7f0f0006));
        this.v.addUpdateListener(new e(c2, c3));
        this.v.start();
    }

    public void a(smy smyVar, boolean z) {
        if (smyVar == null) {
            Log.w("HwSubTabWidget", "Parameter subTab of addSubTab should not be null.");
            return;
        }
        SubTabView a2 = a(smyVar);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        a2.setLayoutParams(layoutParams);
        layoutParams.setMarginStart(this.ar);
        layoutParams.setMarginEnd(this.ar);
        this.ah.addView(a2, layoutParams);
        if (j()) {
            this.ah.getViewTreeObserver().addOnGlobalLayoutListener(new b());
        }
        smyVar.c(getSubTabCount() - 1);
        if (z) {
            smyVar.g();
            a2.setSelected(true);
            a2.setTextSize(0, this.p == 1 ? this.aa : this.g);
        } else {
            a2.setTextSize(0, this.g);
        }
        setTabHorizontalPadding(a2);
    }

    public class SubTabView extends TextView {

        /* renamed from: a, reason: collision with root package name */
        private final int f10755a;
        private float b;
        private final int c;
        private smy e;
        private int f;
        private ColorStateList g;
        private slm j;

        public SubTabView(Context context, smy smyVar) {
            super(context, null, 0);
            this.c = 6;
            this.f10755a = 2;
            this.f = 0;
            this.e = smyVar;
            e(context);
        }

        private void e(Context context) {
            if (HwSubTabWidget.this.p == 0) {
                setGravity(17);
                d();
            }
            setSingleLine(true);
            setTextSize(0, HwSubTabWidget.this.g);
            if (HwSubTabWidget.this.m != null) {
                setTextColor(HwSubTabWidget.this.m);
                this.g = HwSubTabWidget.this.m;
            }
            setBackgroundResource(HwSubTabWidget.this.f10754a);
            setMinWidth(HwSubTabWidget.this.c);
            setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
            e();
            this.b = getContext().getResources().getDisplayMetrics().density;
            slm slmVar = new slm();
            this.j = slmVar;
            slmVar.edq_(context, null, 0);
            this.j.c(1);
            this.j.setCallback(this);
        }

        private void f() {
            int i = getPaint().getFontMetricsInt().top;
            setPadding(getPaddingLeft(), ((HwSubTabWidget.this.f - HwSubTabWidget.this.i) - (-i)) - (HwSubTabWidget.this.o() ? HwSubTabWidget.this.an.getResources().getDimensionPixelSize(R.dimen._2131364458_res_0x7f0a0a6a) : 0), getPaddingRight(), getPaddingBottom());
        }

        public void a() {
            e();
        }

        protected void b() {
            if (!hasFocus()) {
                HwSubTabWidget.this.ah.setSelectedIndicatorColor(HwSubTabWidget.this.s);
                return;
            }
            HwSubTabWidget.this.ah.setSelectedIndicatorColor(HwSubTabWidget.this.g());
            slm eventBadgeDrawable = getEventBadgeDrawable();
            if (eventBadgeDrawable != null) {
                eventBadgeDrawable.b(0);
            }
            c();
        }

        protected void c() {
            performClick();
        }

        protected void d() {
            setPadding(HwSubTabWidget.this.ak, 0, HwSubTabWidget.this.ak, 0);
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            super.draw(canvas);
            a(canvas);
        }

        public slm getEventBadgeDrawable() {
            return this.j;
        }

        public smy getSubTab() {
            return this.e;
        }

        public ColorStateList getSubTabColor() {
            return this.g;
        }

        public int getTextPaddingLeft() {
            return this.f + getPaddingLeft();
        }

        public int getTextPaddingRight() {
            return this.f + getPaddingRight();
        }

        @Override // android.widget.TextView, android.view.View, android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
            super.invalidateDrawable(drawable);
            invalidate();
        }

        @Override // android.widget.TextView, android.view.View
        protected void onFocusChanged(boolean z, int i, Rect rect) {
            b();
            super.onFocusChanged(z, i, rect);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            if (accessibilityNodeInfo == null) {
                return;
            }
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            if (isSelected()) {
                accessibilityNodeInfo.setClickable(false);
                accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
            }
        }

        @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            if (HwSubTabWidget.this.onKeyDown(i, keyEvent)) {
                return true;
            }
            return super.onKeyDown(i, keyEvent);
        }

        @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyUp(int i, KeyEvent keyEvent) {
            if (HwSubTabWidget.this.onKeyUp(i, keyEvent)) {
                return true;
            }
            return super.onKeyUp(i, keyEvent);
        }

        @Override // android.widget.TextView, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            if (HwSubTabWidget.this.p == 1) {
                f();
            }
            super.onLayout(z, i, i2, i3, i4);
            this.f = ((int) (((getWidth() - getPaddingLeft()) - getPaddingRight()) - getPaint().measureText(this.e.i().toString()))) / 2;
        }

        @Override // android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (motionEvent == null) {
                Log.w("HwSubTabWidget", "onTouchEvent: event is null!");
                return false;
            }
            if (motionEvent.getAction() == 1) {
                slm eventBadgeDrawable = getEventBadgeDrawable();
                if (eventBadgeDrawable != null) {
                    eventBadgeDrawable.b(0);
                }
                if (!isInTouchMode() && HwSubTabWidget.this.ah.hasFocus()) {
                    requestFocus();
                }
            }
            return super.onTouchEvent(motionEvent);
        }

        public void setSubTabColor(ColorStateList colorStateList) {
            this.g = colorStateList;
            setTextColor(colorStateList);
        }

        private void a(Canvas canvas) {
            Paint.FontMetrics fontMetrics;
            int i;
            float f;
            if (this.j == null || Float.compare(this.b, 0.0f) <= 0) {
                return;
            }
            int paddingEnd = HwSubTabWidget.this.j() ? getPaddingEnd() - ((int) (this.b * 8.0f)) : (getWidth() - getPaddingEnd()) + ((int) (this.b * 2.0f));
            int i2 = (int) (this.b * 6.0f);
            if (HwSubTabWidget.this.p == 0) {
                f = this.b;
                i = (int) ((getHeight() / 2.0f) - (3.0f * f));
            } else {
                TextPaint paint = getPaint();
                if (paint == null || (fontMetrics = paint.getFontMetrics()) == null) {
                    return;
                }
                int baseline = getBaseline();
                int i3 = (int) ((fontMetrics.top + fontMetrics.bottom) / 2.0f);
                float f2 = this.b;
                i = (baseline + i3) - ((int) (3.0f * f2));
                f = f2;
            }
            this.j.setBounds(paddingEnd, i, i2 + paddingEnd, ((int) (f * 6.0f)) + i);
            this.j.draw(canvas);
        }

        private void e() {
            CharSequence i = this.e.i();
            if (!TextUtils.isEmpty(i)) {
                setText(i);
                setVisibility(0);
            } else {
                setVisibility(8);
                setText((CharSequence) null);
            }
            if (this.e.h() != -1) {
                setId(this.e.h());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(float f, final SubTabView subTabView, final SubTabView subTabView2) {
        final float f2 = (this.aa - this.g) * f;
        post(new Runnable() { // from class: snd
            @Override // java.lang.Runnable
            public final void run() {
                HwSubTabWidget.this.c(subTabView, f2, subTabView2);
            }
        });
    }

    private SubTabView a(smy smyVar) {
        SubTabView d2 = d(smyVar);
        d2.setFocusable(true);
        if (this.ag == null) {
            this.ag = new d(this, null);
        }
        d2.setOnClickListener(this.ag);
        return d2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i() {
        int i;
        smy a2;
        if (this.ah == null) {
            return false;
        }
        int selectedSubTabPostion = getSelectedSubTabPostion();
        int subTabCount = getSubTabCount();
        if (subTabCount <= 0 || (a2 = a((i = (selectedSubTabPostion + 1) % subTabCount))) == null) {
            return false;
        }
        this.ah.setSelectedIndicatorColor(this.s);
        c(a2);
        e(a2);
        this.ah.setSelectedIndicatorColor(g());
        View childAt = this.ah.getChildAt(i);
        if (childAt != null) {
            childAt.requestFocus();
        }
        return true;
    }

    private static Context e(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwSubTabWidget);
    }

    private void ehv_(Context context, AttributeSet attributeSet, int i) {
        this.an = context;
        this.t = new snj(this);
        setOrientation(0);
        ehw_(this.an, attributeSet, i);
        ehx_(context, attributeSet, i);
        this.am = Typeface.create(getResources().getString(R.string._2130850836_res_0x7f023414), 0);
        this.al = Typeface.create(getResources().getString(R.string._2130850837_res_0x7f023415), 0);
        this.ah.setSelectedIndicatorColor(this.s);
        this.b.setAppearance(this.p);
        this.b.setSubTabItemMargin(this.ar);
        this.r = d();
        this.u = new ArgbEvaluator();
        setValueFromPlume(context);
    }

    private void a(smy smyVar, FragmentTransaction fragmentTransaction) {
        if (this.ae != null && this.p == 1) {
            i(smyVar);
        }
        setSubTabSelectedInner(smyVar != null ? smyVar.e() : -1);
        smy smyVar2 = this.ae;
        if (smyVar2 != null) {
            if (smyVar2.a() != null) {
                this.ae.a().onSubTabUnselected(this.ae, fragmentTransaction);
            }
            OnSubTabChangeListener onSubTabChangeListener = this.aj;
            if (onSubTabChangeListener != null) {
                onSubTabChangeListener.onSubTabUnselected(this.ae);
            }
        }
        this.ai = this.ae;
        this.ae = smyVar;
        if (smyVar != null) {
            if (smyVar.a() != null) {
                this.ae.a().onSubTabSelected(this.ae, fragmentTransaction);
            }
            OnSubTabChangeListener onSubTabChangeListener2 = this.aj;
            if (onSubTabChangeListener2 != null) {
                onSubTabChangeListener2.onSubTabSelected(this.ae);
            }
        }
    }

    private void a(FragmentTransaction fragmentTransaction) {
        smy smyVar = this.ae;
        if (smyVar != null) {
            if (smyVar.a() != null) {
                this.ae.a().onSubTabReselected(this.ae, fragmentTransaction);
            }
            OnSubTabChangeListener onSubTabChangeListener = this.aj;
            if (onSubTabChangeListener != null) {
                onSubTabChangeListener.onSubTabReselected(this.ae);
            }
        }
    }

    private int b(smy smyVar) {
        int right;
        int width;
        int e2 = this.ae.e();
        int e3 = smyVar.e();
        SubTabView c2 = c(e2);
        SubTabView c3 = c(e3);
        if (c2 != null && c3 != null) {
            TextPaint textPaint = new TextPaint(1);
            TextPaint textPaint2 = new TextPaint(1);
            textPaint.setTextSize(this.g);
            textPaint2.setTextSize(this.aa);
            String obj = c2.getText().toString();
            String obj2 = c3.getText().toString();
            int measureText = (int) (textPaint2.measureText(obj) - textPaint.measureText(obj));
            int measureText2 = (int) (textPaint2.measureText(obj2) - textPaint.measureText(obj2));
            int i = this.ar;
            int b2 = i + i + this.b.b(this.h);
            if (!j()) {
                return (e2 < e3 ? c3.getLeft() - measureText : c3.getLeft()) - b2;
            }
            if (e2 < e3) {
                right = c3.getRight() + measureText2 + b2;
                width = this.b.getWidth();
            } else {
                right = c3.getRight() + measureText2 + b2;
                width = this.b.getWidth() + measureText;
            }
            return right - width;
        }
        return this.b.getScrollX();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(float f, final SubTabView subTabView, final SubTabView subTabView2) {
        if (f < 0.0f) {
            return;
        }
        ColorStateList subTabColor = subTabView.getSubTabColor();
        ColorStateList subTabColor2 = subTabView2.getSubTabColor();
        if (subTabColor == null || subTabColor2 == null || this.u == null) {
            return;
        }
        int[] iArr = {android.R.attr.state_selected};
        int defaultColor = subTabColor.getDefaultColor();
        int colorForState = subTabColor.getColorForState(iArr, defaultColor);
        int defaultColor2 = subTabColor2.getDefaultColor();
        int colorForState2 = subTabColor2.getColorForState(iArr, defaultColor2);
        final int intValue = ((Integer) this.u.evaluate(f, Integer.valueOf(colorForState), Integer.valueOf(defaultColor))).intValue();
        final int intValue2 = ((Integer) this.u.evaluate(f, Integer.valueOf(defaultColor2), Integer.valueOf(colorForState2))).intValue();
        post(new Runnable() { // from class: sna
            @Override // java.lang.Runnable
            public final void run() {
                HwSubTabWidget.c(HwSubTabWidget.SubTabView.this, intValue, subTabView2, intValue2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void c(SubTabView subTabView, int i, SubTabView subTabView2, int i2) {
        subTabView.setTextColor(i);
        subTabView2.setTextColor(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(SubTabView subTabView, float f, SubTabView subTabView2) {
        subTabView.setTextSize(0, this.aa - f);
        subTabView2.setTextSize(0, this.g + f);
    }

    private void b() {
        int adjustTrigleWidth = getAdjustTrigleWidth();
        int measuredWidth = this.ah.getMeasuredWidth();
        int childCount = this.ah.getChildCount();
        if (measuredWidth >= adjustTrigleWidth || childCount <= 0) {
            return;
        }
        int i = childCount - 1;
        int i2 = this.ar;
        int i3 = (adjustTrigleWidth - ((i2 + i2) * i)) / childCount;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = this.ah.getChildAt(i4);
            if (childAt == null || childAt.getMeasuredWidth() > i3) {
                return;
            }
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt2 = this.ah.getChildAt(i5);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int paddingLeft = childAt2.getPaddingLeft();
            if (measuredWidth2 < i3) {
                int i6 = ((i3 - measuredWidth2) / 2) + paddingLeft;
                childAt2.setPadding(i6, 0, i6, 0);
            }
            ViewGroup.LayoutParams layoutParams = childAt2.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                if (i5 == i) {
                    int i7 = this.ar;
                    layoutParams2.width = adjustTrigleWidth - (((i7 + i7) + i3) * i);
                } else {
                    layoutParams2.width = i3;
                }
            }
        }
    }
}
