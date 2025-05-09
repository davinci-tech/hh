package com.huawei.uikit.hwbottomnavigationview.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem;
import com.huawei.uikit.hweffect.engine.HwBlurable;
import com.huawei.uikit.hwtextview.widget.HwTextView;
import com.huawei.uikit.hwunifiedinteract.widget.HwKeyEventDetector;
import defpackage.skr;
import defpackage.sks;
import defpackage.skt;
import defpackage.slp;
import defpackage.smp;
import defpackage.smr;
import defpackage.snj;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class HwBottomNavigationView extends LinearLayout implements HwBlurable {

    /* renamed from: a, reason: collision with root package name */
    protected int f10596a;
    private boolean aa;
    private int ab;
    private boolean ac;
    private boolean ad;
    private MenuInflater ae;
    private int af;
    private d ag;
    private int ah;
    private c ai;
    private boolean aj;
    private int ak;
    private boolean al;
    private boolean am;
    private int an;
    private boolean ao;
    private int ap;
    private int aq;
    private int ar;
    private int as;
    private slp at;
    private boolean au;
    private boolean av;
    private int aw;
    private float ax;
    private snj ay;
    private Drawable az;
    public Context b;
    private HwColumnSystem ba;
    private boolean bc;
    protected int c;
    protected int d;
    protected OnItemDoubleTapListener e;
    protected int f;
    protected int g;
    protected Menu h;
    protected BottomNavListener i;
    protected a j;
    protected int k;
    protected int l;
    protected int m;
    protected Resources n;
    private int o;
    private int p;
    private int q;
    private HwKeyEventDetector r;
    private String s;
    private int t;
    private int u;
    private HwKeyEventDetector.OnNextTabEventListener v;
    private GestureDetector w;
    private BottomNavigationItemView x;
    private HwKeyEventDetector.OnGlobalNextTabEventListener y;
    private final Rect z;

    public interface BottomNavListener {
        void onBottomNavItemReselected(MenuItem menuItem, int i);

        void onBottomNavItemSelected(MenuItem menuItem, int i);

        void onBottomNavItemUnselected(MenuItem menuItem, int i);
    }

    /* loaded from: classes9.dex */
    public interface OnItemDoubleTapListener {
        void onDoubleTaped(MenuItem menuItem, int i);
    }

    protected class a {
        private int c;
        private int d;

        a() {
        }

        public void a() {
            this.c = 0;
            this.d = 0;
        }

        public void a(int i) {
            this.c = i;
        }

        public int b() {
            return this.c;
        }

        public int e() {
            return this.d;
        }

        public void e(int i) {
            this.d = i;
        }
    }

    class b implements HwKeyEventDetector.OnNextTabEventListener {
        b() {
        }

        @Override // com.huawei.uikit.hwunifiedinteract.widget.HwKeyEventDetector.OnNextTabEventListener
        public boolean onNextTab(int i, KeyEvent keyEvent) {
            if (i == 1) {
                HwBottomNavigationView.this.b();
            }
            return true;
        }
    }

    class c implements View.OnTouchListener {
        private c() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (!(view instanceof BottomNavigationItemView)) {
                return false;
            }
            HwBottomNavigationView.this.x = (BottomNavigationItemView) view;
            return HwBottomNavigationView.this.w.onTouchEvent(motionEvent);
        }

        /* synthetic */ c(HwBottomNavigationView hwBottomNavigationView, f fVar) {
            this();
        }
    }

    class d implements View.OnClickListener {
        private d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view instanceof BottomNavigationItemView) {
                BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) view;
                if (HwBottomNavigationView.this.ac) {
                    boolean isChecked = bottomNavigationItemView.getIsChecked();
                    HwBottomNavigationView hwBottomNavigationView = HwBottomNavigationView.this;
                    skt.dZQ_(bottomNavigationItemView, true, !isChecked, hwBottomNavigationView.i, hwBottomNavigationView.h);
                } else {
                    HwBottomNavigationView.this.d(bottomNavigationItemView, true);
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        /* synthetic */ d(HwBottomNavigationView hwBottomNavigationView, f fVar) {
            this();
        }
    }

    class e implements HwKeyEventDetector.OnGlobalNextTabEventListener {
        e() {
        }

        @Override // com.huawei.uikit.hwunifiedinteract.widget.HwKeyEventDetector.OnGlobalNextTabEventListener
        public boolean onGlobalNextTab(int i, KeyEvent keyEvent) {
            if (i == 1) {
                HwBottomNavigationView.this.b();
            }
            return true;
        }
    }

    class f extends GestureDetector.SimpleOnGestureListener {
        f() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            HwBottomNavigationView hwBottomNavigationView = HwBottomNavigationView.this;
            if (hwBottomNavigationView.e != null && hwBottomNavigationView.x != null) {
                int itemIndex = HwBottomNavigationView.this.x.getItemIndex();
                HwBottomNavigationView hwBottomNavigationView2 = HwBottomNavigationView.this;
                hwBottomNavigationView2.e.onDoubleTaped(hwBottomNavigationView2.h.getItem(itemIndex), itemIndex);
            }
            return super.onDoubleTap(motionEvent);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return super.onDoubleTapEvent(motionEvent);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            return super.onSingleTapConfirmed(motionEvent);
        }
    }

    public HwBottomNavigationView(Context context) {
        this(context, null);
    }

    private void setNavigationViewClipOnLand(BottomNavigationItemView bottomNavigationItemView) {
        if (!bottomNavigationItemView.d() && !bottomNavigationItemView.c()) {
            bottomNavigationItemView.setClipChildren(true);
            bottomNavigationItemView.setClipToPadding(true);
        } else {
            setClipChildren(false);
            setClipToPadding(false);
            bottomNavigationItemView.setClipChildren(false);
            bottomNavigationItemView.setClipToPadding(false);
        }
    }

    protected HwKeyEventDetector.OnGlobalNextTabEventListener a() {
        return new e();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        View childAt = getChildAt(this.q);
        if (hasFocus() || this.q < 0 || childAt == null) {
            super.addFocusables(arrayList, i, i2);
        } else if (childAt.isFocusable()) {
            arrayList.add(childAt);
        } else {
            super.addFocusables(arrayList, i, i2);
        }
    }

    public void c() {
        this.af = -1;
        this.h.clear();
        this.f = 0;
        removeAllViews();
    }

    public void c(int i, boolean z) {
        if (i >= this.f || !(getChildAt(i) instanceof BottomNavigationItemView)) {
            return;
        }
        ((BottomNavigationItemView) getChildAt(i)).setHasMessage(z);
    }

    protected HwKeyEventDetector.OnNextTabEventListener d() {
        return new b();
    }

    public boolean dZJ_(CharSequence charSequence, Drawable drawable) {
        return dZG_(0, 0, charSequence, drawable, true);
    }

    protected void dZK_(MenuItem menuItem, int i, boolean z, AttributeSet attributeSet, int i2) {
        if (menuItem == null) {
            Log.w("HwBottomNavigationView", "createNewItem: Param menuItem is null");
            return;
        }
        BottomNavigationItemView bottomNavigationItemView = new BottomNavigationItemView(this.b, menuItem, this.am, i, z, attributeSet, i2);
        bottomNavigationItemView.setClickable(true);
        bottomNavigationItemView.setFocusable(true);
        bottomNavigationItemView.setBackgroundResource(this.u);
        bottomNavigationItemView.d(this.f10596a);
        bottomNavigationItemView.c(this.d);
        bottomNavigationItemView.e(this.k);
        bottomNavigationItemView.a(this.m);
        bottomNavigationItemView.setMsgBgColor(this.l);
        bottomNavigationItemView.setOnClickListener(this.ag);
        bottomNavigationItemView.setOnTouchListener(this.ai);
        addView(bottomNavigationItemView);
    }

    public void dZL_(int i, Drawable drawable, int i2, boolean z) {
        if (i2 < 0 || i2 >= this.f) {
            return;
        }
        MenuItem item = this.h.getItem(i2);
        View childAt = getChildAt(i2);
        if (childAt instanceof BottomNavigationItemView) {
            BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) childAt;
            if (i != 0) {
                item.setTitle(i);
            }
            if (drawable != null) {
                item.setIcon(drawable);
            }
            bottomNavigationItemView.dZO_(item, z);
        }
    }

    public void dZM_(Drawable drawable, int i, boolean z) {
        if (i < 0 || i >= this.f || drawable == null) {
            return;
        }
        MenuItem item = this.h.getItem(i);
        item.setIcon(drawable);
        if (getChildAt(i) instanceof BottomNavigationItemView) {
            ((BottomNavigationItemView) getChildAt(i)).dZP_(item, z);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        dZy_(canvas);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        if (!this.at.edk_(this) || this.bc) {
            super.draw(canvas);
            return;
        }
        this.at.edj_(canvas, this);
        super.dispatchDraw(canvas);
        dZy_(canvas);
    }

    protected HwKeyEventDetector e() {
        return new HwKeyEventDetector(this.b);
    }

    protected void e(int i, int i2, a aVar) {
        int i3;
        int size = View.MeasureSpec.getSize(i);
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int childCount = getChildCount();
        if (aVar == null) {
            Log.w("HwBottomNavigationView", "measureOnPortraitByAverageWidth: Param measureSize is null");
            return;
        }
        if (childCount <= 0) {
            aVar.a(size);
            aVar.e(0);
            return;
        }
        if (this.al && (i3 = this.ar) > 0 && i3 < paddingLeft) {
            paddingLeft = i3;
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int b2 = b(childCount, paddingLeft / childCount, ViewGroup.getChildMeasureSpec(i2, paddingTop, -2));
        a(childCount, b2);
        aVar.a(size);
        aVar.e(b2 + paddingTop);
    }

    @Override // com.huawei.uikit.hweffect.engine.HwBlurable
    public int getBlurColor() {
        return this.ab;
    }

    @Override // com.huawei.uikit.hweffect.engine.HwBlurable
    public int getBlurType() {
        return this.ah;
    }

    public int getFocusPathColor() {
        return this.t;
    }

    public boolean getLongClickEnable() {
        return this.ad;
    }

    @Override // com.huawei.uikit.hweffect.engine.HwBlurable
    public boolean isBlurEnable() {
        return this.av;
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        this.ay.ejb_(windowInsets);
        return super.onApplyWindowInsets(windowInsets);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.ay.d(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        HwKeyEventDetector hwKeyEventDetector = this.r;
        if (hwKeyEventDetector != null) {
            hwKeyEventDetector.a(this.v);
            this.r.eiy_(this, this.y);
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.ao) {
            this.ar = c(this.ba, this.h.size());
        } else {
            this.ar = b(this.ba, this.h.size());
        }
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
        this.ay.e(true);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        this.j.a();
        if (getOrientation() == 1) {
            a(i2, this.j);
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.j.b(), 1073741824), View.MeasureSpec.makeMeasureSpec(this.j.e(), 1073741824));
            return;
        }
        boolean z = this.al;
        int size = View.MeasureSpec.getSize(i);
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int childCount = getChildCount();
        if (this.bc) {
            super.onMeasure(i, i2);
            return;
        }
        if ((childCount > 3 || this.as > 0) && z && (i3 = this.aq) > 0 && i3 < paddingLeft) {
            paddingLeft = i3;
        }
        boolean e2 = e(paddingLeft);
        if (childCount == 0) {
            setMeasuredDimension(size, 0);
            return;
        }
        this.am = e2;
        c(childCount);
        if (this.am) {
            a(i, i2, this.j);
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.j.b(), 1073741824), View.MeasureSpec.makeMeasureSpec(this.j.e(), 1073741824));
        } else {
            d(i, i2, this.j);
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.j.b(), 1073741824), View.MeasureSpec.makeMeasureSpec(this.j.e(), 1073741824));
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (this.bc) {
            return;
        }
        if (i != 0) {
            this.at.edl_(this);
            return;
        }
        this.at.edi_(this, this.ah);
        this.at.edm_(this, isBlurEnable());
        int i2 = this.ab;
        if (i2 != -16777216) {
            this.at.edn_(this, i2);
        }
    }

    public void setActiveColor(int i) {
        for (int i2 = 0; i2 < this.f; i2++) {
            setItemActiveColor(i, i2);
        }
    }

    public void setBackgroundResourceId(int i) {
        this.u = i;
        for (int i2 = 0; i2 < this.f; i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof BottomNavigationItemView) {
                ((BottomNavigationItemView) childAt).setBackgroundResource(i);
            }
        }
    }

    @Override // com.huawei.uikit.hweffect.engine.HwBlurable
    public void setBlurColor(int i) {
        this.ab = i;
    }

    @Override // com.huawei.uikit.hweffect.engine.HwBlurable
    public void setBlurEnable(boolean z) {
        this.av = z;
        this.at.edm_(this, isBlurEnable());
    }

    @Override // com.huawei.uikit.hweffect.engine.HwBlurable
    public void setBlurType(int i) {
        this.ah = i;
    }

    public void setBottomNavListener(BottomNavListener bottomNavListener) {
        this.i = bottomNavListener;
    }

    public void setColumnEnabled(boolean z) {
        this.al = z;
        requestLayout();
    }

    public void setDefaultColor(int i) {
        for (int i2 = 0; i2 < this.f; i2++) {
            setItemDefaultColor(i, i2);
        }
    }

    public void setDividerEnabled(boolean z) {
        if (this.au == z) {
            return;
        }
        this.au = z;
        requestLayout();
    }

    public void setDoubleTapListener(OnItemDoubleTapListener onItemDoubleTapListener) {
        this.e = onItemDoubleTapListener;
    }

    public void setExtendedNextTabEnabled(boolean z, boolean z2) {
        HwKeyEventDetector hwKeyEventDetector = this.r;
        if (hwKeyEventDetector == null) {
            return;
        }
        if (z) {
            if (!z2) {
                this.y = null;
                hwKeyEventDetector.eiy_(this, null);
                return;
            } else {
                HwKeyEventDetector.OnGlobalNextTabEventListener a2 = a();
                this.y = a2;
                this.r.eiy_(this, a2);
                return;
            }
        }
        if (!z2) {
            this.v = null;
            hwKeyEventDetector.a((HwKeyEventDetector.OnNextTabEventListener) null);
        } else {
            HwKeyEventDetector.OnNextTabEventListener d2 = d();
            this.v = d2;
            this.r.a(d2);
        }
    }

    public void setFocusPathColor(int i) {
        this.t = i;
    }

    public void setIconFocusActiveColor(int i) {
    }

    public void setIconFocusDefaultColor(int i) {
    }

    public void setItemActiveColor(int i, int i2) {
        if (i2 < 0 || i2 >= this.f) {
            return;
        }
        View childAt = getChildAt(i2);
        if (childAt instanceof BottomNavigationItemView) {
            ((BottomNavigationItemView) childAt).d(i);
        }
    }

    public void setItemChecked(int i) {
        int childCount = getChildCount();
        if (i < 0 || i >= childCount) {
            return;
        }
        View childAt = getChildAt(i);
        if (childAt instanceof BottomNavigationItemView) {
            BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) childAt;
            bottomNavigationItemView.setChecked(true, this.af != -1);
            d(bottomNavigationItemView, false);
        }
    }

    public void setItemDefaultColor(int i, int i2) {
        if (i2 < 0 || i2 >= this.f) {
            return;
        }
        View childAt = getChildAt(i2);
        if (childAt instanceof BottomNavigationItemView) {
            ((BottomNavigationItemView) childAt).c(i);
        }
    }

    public void setItemIconFocusActiveColor(int i, int i2) {
    }

    public void setItemIconFocusDefaultColor(int i, int i2) {
    }

    public void setItemTitleActiveColor(int i, int i2) {
        if (i2 < 0 || i2 >= this.f) {
            return;
        }
        View childAt = getChildAt(i2);
        if (childAt instanceof BottomNavigationItemView) {
            ((BottomNavigationItemView) childAt).e(i);
        }
    }

    public void setItemTitleDefaultColor(int i, int i2) {
        if (i2 < 0 || i2 >= this.f) {
            return;
        }
        View childAt = getChildAt(i2);
        if (childAt instanceof BottomNavigationItemView) {
            ((BottomNavigationItemView) childAt).a(i);
        }
    }

    public void setItemUnchecked(int i) {
        int childCount = getChildCount();
        if (i < 0 || i >= childCount) {
            return;
        }
        View childAt = getChildAt(i);
        if (childAt instanceof BottomNavigationItemView) {
            skt.dZQ_((BottomNavigationItemView) childAt, false, false, this.i, this.h);
        }
    }

    public void setLongClickEnable(boolean z) {
        this.ad = z;
    }

    public void setMessageBgColor(int i) {
        this.l = i;
        for (int i2 = 0; i2 < this.f; i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof BottomNavigationItemView) {
                ((BottomNavigationItemView) childAt).setMsgBgColor(i);
            }
        }
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        this.ay.d(i, i2, i3, i4);
    }

    public void setPortLayout(boolean z) {
        if (this.aj != z) {
            this.aj = z;
            requestLayout();
        }
    }

    public void setSelectItemEnabled(int i, boolean z) {
        if (i < 0 || i >= this.f) {
            return;
        }
        View childAt = getChildAt(i);
        if (childAt instanceof BottomNavigationItemView) {
            ((BottomNavigationItemView) childAt).setEnabled(z);
        }
    }

    public void setSpaciousStyle(boolean z) {
        this.ac = z;
    }

    public void setTitle(int i, int i2, boolean z) {
    }

    public void setTitle(int i, CharSequence charSequence, boolean z) {
    }

    public void setTitleActiveColor(int i) {
        for (int i2 = 0; i2 < this.f; i2++) {
            setItemTitleActiveColor(i, i2);
        }
    }

    public void setTitleDefaultColor(int i) {
        for (int i2 = 0; i2 < this.f; i2++) {
            setItemTitleDefaultColor(i, i2);
        }
    }

    public HwBottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100216_res_0x7f060238);
    }

    private void dZH_(Context context, AttributeSet attributeSet, int i) {
        this.b = context;
        this.n = context.getResources();
        a(context, attributeSet);
        if (this.n.getInteger(R.integer._2131623948_res_0x7f0e000c) == 2) {
            this.bc = true;
        }
        try {
            this.h = (Menu) Class.forName("com.android.internal.view.menu.MenuBuilder").getConstructor(Context.class).newInstance(context);
        } catch (ClassNotFoundException unused) {
            Log.e("HwBottomNavigationView", "HwBottomNavigationView: MenuBuilder ClassNotFoundException failed");
        } catch (IllegalAccessException unused2) {
            Log.e("HwBottomNavigationView", "HwBottomNavigationView: MenuBuilder IllegalAccessException failed");
        } catch (InstantiationException unused3) {
            Log.e("HwBottomNavigationView", "HwBottomNavigationView: MenuBuilder InstantiationException failed");
        } catch (NoSuchMethodException unused4) {
            Log.e("HwBottomNavigationView", "HwBottomNavigationView: MenuBuilder NoSuchMethodException failed");
        } catch (InvocationTargetException unused5) {
            Log.e("HwBottomNavigationView", "HwBottomNavigationView: MenuBuilder InvocationTargetException failed");
        }
        this.ae = new MenuInflater(this.b);
        dZx_(context, attributeSet, i);
        this.o = this.n.getInteger(R.integer._2131623965_res_0x7f0e001d);
        this.p = this.n.getDimensionPixelOffset(R.dimen._2131363958_res_0x7f0a0876);
        f fVar = null;
        this.ag = new d(this, fVar);
        this.ai = new c(this, fVar);
        j();
        dZz_(attributeSet, i);
        this.r = e();
        skt.d(context, this);
        h();
    }

    private void h() {
        this.w = new GestureDetector(this.b, new f());
    }

    public HwBottomNavigationView(Context context, AttributeSet attributeSet, int i) {
        super(c(context, i), attributeSet, i);
        this.j = new a();
        this.f10596a = 678391;
        this.d = 855638016;
        this.k = 678391;
        this.m = -1728053248;
        this.g = -452984832;
        this.c = -452984832;
        this.l = 16394797;
        this.z = new Rect();
        this.af = -1;
        this.aj = false;
        this.at = slp.e();
        this.av = false;
        this.au = false;
        this.bc = false;
        this.o = 0;
        this.q = 0;
        this.r = null;
        this.ad = false;
        dZH_(super.getContext(), attributeSet, i);
    }

    private void c(int i, int i2, a aVar) {
        int i3;
        int size = View.MeasureSpec.getSize(i);
        float paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        aVar.a(size);
        int childCount = getChildCount();
        if (childCount <= 0) {
            aVar.a(size);
            aVar.e(0);
            return;
        }
        if (this.al && (i3 = this.ar) > 0) {
            float f2 = i3;
            if (f2 < paddingLeft) {
                paddingLeft = f2;
            }
        }
        float f3 = paddingLeft / childCount;
        ArrayList arrayList = new ArrayList(childCount);
        for (int i4 = 0; i4 < childCount; i4++) {
            a(arrayList, i4, f3);
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingTop, -2);
        d(f3, childMeasureSpec, arrayList, aVar);
        e(f3, childMeasureSpec, arrayList, aVar);
        a(childCount, aVar.e());
        aVar.e(aVar.e() + paddingTop);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        return getLayoutDirection() == 1;
    }

    private void a(int i) {
        int i2;
        if (i == 21 || i == 22) {
            return;
        }
        if (i == 19 && (i2 = this.q) > 0) {
            this.q = i2 - 1;
        } else {
            if (i != 20 || this.q >= getChildCount()) {
                return;
            }
            this.q++;
        }
    }

    public boolean dZI_(int i, Drawable drawable, boolean z) {
        return dZF_(0, 0, i, drawable, z);
    }

    private static Context c(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwBottomNavigationView);
    }

    private void dZx_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100211_res_0x7f060233, R.attr._2131100212_res_0x7f060234, R.attr._2131100213_res_0x7f060235, R.attr._2131100214_res_0x7f060236, R.attr._2131100215_res_0x7f060237, R.attr._2131100265_res_0x7f060269, R.attr._2131100338_res_0x7f0602b2, R.attr._2131100353_res_0x7f0602c1, R.attr._2131100364_res_0x7f0602cc, R.attr._2131100365_res_0x7f0602cd, R.attr._2131100366_res_0x7f0602ce, R.attr._2131100367_res_0x7f0602cf, R.attr._2131100368_res_0x7f0602d0, R.attr._2131100383_res_0x7f0602df, R.attr._2131100385_res_0x7f0602e1, R.attr._2131100390_res_0x7f0602e6, R.attr._2131100408_res_0x7f0602f8, R.attr._2131100410_res_0x7f0602fa, R.attr._2131100431_res_0x7f06030f, R.attr._2131100572_res_0x7f06039c, R.attr._2131100581_res_0x7f0603a5, R.attr._2131100584_res_0x7f0603a8, R.attr._2131100585_res_0x7f0603a9, R.attr._2131100598_res_0x7f0603b6, R.attr._2131100599_res_0x7f0603b7}, i, R.style.Widget_Emui_HwBottomNavigationView);
        this.d = obtainStyledAttributes.getColor(10, 855638016);
        this.f10596a = obtainStyledAttributes.getColor(8, 678391);
        this.m = obtainStyledAttributes.getColor(22, -1728053248);
        this.k = obtainStyledAttributes.getColor(21, 678391);
        this.g = obtainStyledAttributes.getColor(12, -452984832);
        this.c = obtainStyledAttributes.getColor(11, -452984832);
        this.l = obtainStyledAttributes.getColor(16, 16394797);
        this.az = obtainStyledAttributes.getDrawable(2);
        this.ah = obtainStyledAttributes.getInteger(1, 4);
        this.ab = obtainStyledAttributes.getColor(0, -16777216);
        this.al = obtainStyledAttributes.getBoolean(5, false);
        this.t = obtainStyledAttributes.getColor(6, 0);
        this.u = obtainStyledAttributes.getResourceId(13, R.drawable.hwbottomnavigationview_item_background_selector);
        this.aa = obtainStyledAttributes.getBoolean(20, true);
        this.ac = obtainStyledAttributes.getBoolean(14, false);
        this.ak = obtainStyledAttributes.getDimensionPixelSize(19, this.n.getDimensionPixelSize(R.dimen._2131363961_res_0x7f0a0879));
        this.an = obtainStyledAttributes.getDimensionPixelSize(9, this.n.getDimensionPixelSize(R.dimen._2131363955_res_0x7f0a0873));
        int resourceId = obtainStyledAttributes.getResourceId(4, 0);
        obtainStyledAttributes.recycle();
        if (resourceId > 0) {
            this.ae.inflate(resourceId, this.h);
        }
        this.s = this.n.getString(R.string._2130851235_res_0x7f0235a3);
    }

    public class BottomNavigationItemView extends LinearLayout {

        /* renamed from: a, reason: collision with root package name */
        protected int f10597a;
        private int aa;
        private sks ab;
        private int ac;
        private int ad;
        private int ae;
        private int af;
        private int ag;
        private int ah;
        private int ai;
        private int aj;
        private int ak;
        private int al;
        private boolean am;
        private boolean an;
        private int ao;
        private Drawable ap;
        private float ar;
        private int as;
        float b;
        boolean c;
        float d;
        protected LinearLayout e;
        protected HwTextView f;
        protected Context g;
        protected boolean h;
        protected int i;
        protected boolean j;
        protected boolean k;
        protected int l;
        protected boolean m;
        protected MenuItem n;
        protected Paint o;
        protected int p;
        protected int q;
        protected ImageView r;
        protected ImageView s;
        protected int t;
        private sks u;
        protected ImageView w;
        private sks x;
        private sks y;
        private int z;

        class b implements View.OnLongClickListener {
            b() {
            }

            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                HwBottomNavigationView.this.ad = true;
                return true;
            }
        }

        private void dZN_(Context context, AttributeSet attributeSet, int i) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100211_res_0x7f060233, R.attr._2131100212_res_0x7f060234, R.attr._2131100213_res_0x7f060235, R.attr._2131100214_res_0x7f060236, R.attr._2131100215_res_0x7f060237, R.attr._2131100265_res_0x7f060269, R.attr._2131100338_res_0x7f0602b2, R.attr._2131100353_res_0x7f0602c1, R.attr._2131100364_res_0x7f0602cc, R.attr._2131100365_res_0x7f0602cd, R.attr._2131100366_res_0x7f0602ce, R.attr._2131100367_res_0x7f0602cf, R.attr._2131100368_res_0x7f0602d0, R.attr._2131100383_res_0x7f0602df, R.attr._2131100385_res_0x7f0602e1, R.attr._2131100390_res_0x7f0602e6, R.attr._2131100408_res_0x7f0602f8, R.attr._2131100410_res_0x7f0602fa, R.attr._2131100431_res_0x7f06030f, R.attr._2131100572_res_0x7f06039c, R.attr._2131100581_res_0x7f0603a5, R.attr._2131100584_res_0x7f0603a8, R.attr._2131100585_res_0x7f0603a9, R.attr._2131100598_res_0x7f0603b6, R.attr._2131100599_res_0x7f0603b7}, i, R.style.Widget_Emui_HwBottomNavigationView);
            View.inflate(context, obtainStyledAttributes.getResourceId(3, R.layout.hwbottomnavigationview_item_layout), this);
            this.ao = obtainStyledAttributes.getDimensionPixelSize(15, 0);
            this.p = obtainStyledAttributes.getDimensionPixelSize(18, HwBottomNavigationView.this.n.getDimensionPixelSize(R.dimen._2131363957_res_0x7f0a0875));
            this.l = obtainStyledAttributes.getInteger(17, HwBottomNavigationView.this.n.getInteger(R.integer._2131623964_res_0x7f0e001c));
            this.ag = obtainStyledAttributes.getDimensionPixelSize(24, HwBottomNavigationView.this.n.getDimensionPixelSize(R.dimen._2131363965_res_0x7f0a087d));
            this.ai = obtainStyledAttributes.getDimensionPixelSize(7, HwBottomNavigationView.this.n.getDimensionPixelSize(R.dimen._2131363954_res_0x7f0a0872));
            this.ah = obtainStyledAttributes.getDimensionPixelSize(23, HwBottomNavigationView.this.n.getDimensionPixelSize(R.dimen._2131363964_res_0x7f0a087c));
            obtainStyledAttributes.recycle();
        }

        private void f() {
            if ((skr.d() && !skr.b() && skr.c(getContext())) || skr.c()) {
                setMinimumHeight(this.p);
                HwBottomNavigationView.this.setPortLayout(true);
            } else {
                setMinimumHeight(this.aa);
                HwBottomNavigationView.this.setPortLayout(false);
            }
        }

        private int getExtendImageSize() {
            return HwBottomNavigationView.this.n.getDimensionPixelSize(R.dimen._2131363971_res_0x7f0a0883);
        }

        private int getSingleImageSize() {
            int i = this.z;
            return (i == 1 || this.an) ? (i == 1 || !this.an) ? (i != 1 || this.an) ? this.as : this.aj : this.al : this.ak;
        }

        private void j() {
            if (this.z == 1) {
                f();
                if (this.an) {
                    int i = this.ai;
                    setPadding(i, 0, i, 0);
                } else {
                    int i2 = this.ai;
                    int i3 = this.ag;
                    setPadding(i2, i3, i2, i3);
                }
            } else {
                setMinimumHeight(this.p);
                if (this.an) {
                    setPadding(HwBottomNavigationView.this.ak, 0, HwBottomNavigationView.this.ak, 0);
                } else {
                    setPadding(HwBottomNavigationView.this.ak, this.ag, HwBottomNavigationView.this.ak, this.ag);
                }
            }
            int singleImageSize = getSingleImageSize();
            ViewGroup.LayoutParams layoutParams = this.r.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                layoutParams2.width = singleImageSize;
                layoutParams2.height = singleImageSize;
                if (this.an) {
                    layoutParams2.setMargins(0, HwBottomNavigationView.this.n.getDimensionPixelSize(R.dimen._2131363968_res_0x7f0a0880), 0, 0);
                }
                this.r.setLayoutParams(layoutParams2);
                this.y.d(singleImageSize);
                this.y.c(this.j, false);
            }
        }

        protected BottomNavigationItemView a(int i) {
            HwBottomNavigationView.this.m = i;
            a(false, true);
            return this;
        }

        protected BottomNavigationItemView c(int i) {
            this.i = i;
            a(false, true);
            return this;
        }

        public boolean c() {
            return this.h;
        }

        protected BottomNavigationItemView d(int i) {
            this.f10597a = i;
            a(false, true);
            return this;
        }

        public boolean d() {
            return this.k;
        }

        void dZO_(MenuItem menuItem, boolean z) {
            this.k = false;
            this.m = z;
            this.r.setVisibility(8);
            this.e.setVisibility(0);
            if (this.z == 0) {
                this.w.setVisibility(0);
            }
            this.n = menuItem;
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                layoutParams2.gravity = 48;
                setLayoutParams(layoutParams2);
            }
            this.x.dZw_(this.n.getIcon());
            this.ab.dZw_(this.n.getIcon());
            a(true, true);
        }

        void dZP_(MenuItem menuItem, boolean z) {
            this.k = true;
            this.an = z;
            this.e.setVisibility(8);
            this.w.setVisibility(8);
            this.r.setVisibility(0);
            setGravity(1);
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                if (z) {
                    layoutParams2.gravity = 48;
                } else {
                    layoutParams2.gravity = 16;
                }
                setLayoutParams(layoutParams2);
            }
            this.n = menuItem;
            sks sksVar = new sks(this.g, menuItem.getIcon(), getSingleImageSize());
            this.y = sksVar;
            this.r.setImageDrawable(sksVar);
            this.ap = menuItem.getIcon();
            j();
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void dispatchDraw(Canvas canvas) {
            if (canvas == null) {
                Log.w("HwBottomNavigationView", "dispatchDraw: Param canvas is null");
                return;
            }
            super.dispatchDraw(canvas);
            if (!this.am || this.k) {
                return;
            }
            ImageView icon = getIcon();
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            getGlobalVisibleRect(rect);
            icon.getGlobalVisibleRect(rect2);
            int i = HwBottomNavigationView.this.g() ? (rect2.left - rect.left) + this.af : (rect2.right - rect.left) - this.af;
            int i2 = rect2.top;
            int i3 = rect.top;
            canvas.drawCircle(i, (i2 - i3) + r3, this.af, this.o);
        }

        protected BottomNavigationItemView e(int i) {
            HwBottomNavigationView.this.k = i;
            a(false, true);
            return this;
        }

        LinearLayout getContainer() {
            return this.e;
        }

        public TextView getContent() {
            return this.f;
        }

        ImageView getIcon() {
            return this.z == 1 ? this.s : this.w;
        }

        public boolean getIsChecked() {
            return this.j;
        }

        public int getItemIndex() {
            return this.ac;
        }

        public Drawable getOriginalDrawable() {
            return this.ap;
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent == null) {
                return;
            }
            super.onInitializeAccessibilityEvent(accessibilityEvent);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            if (accessibilityNodeInfo == null) {
                return;
            }
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setSelected(this.j);
            TextUtils.isEmpty(this.n.getTitle());
            if (e()) {
                accessibilityNodeInfo.setHintText(HwBottomNavigationView.this.s);
            }
        }

        @Override // android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            HwBottomNavigationView.this.a(keyEvent, i);
            if (HwBottomNavigationView.this.onKeyDown(i, keyEvent)) {
                return true;
            }
            return super.onKeyDown(i, keyEvent);
        }

        @Override // android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyUp(int i, KeyEvent keyEvent) {
            if (HwBottomNavigationView.this.onKeyUp(i, keyEvent)) {
                return true;
            }
            return super.onKeyUp(i, keyEvent);
        }

        @Override // android.view.View
        public boolean performClick() {
            boolean performClick = super.performClick();
            sendAccessibilityEvent(32768);
            return performClick;
        }

        public void setChecked(boolean z, boolean z2) {
            if (this.k) {
                this.j = z;
                this.y.c(z, false);
            } else if (z != this.j) {
                this.j = z;
                sks sksVar = this.z == 1 ? this.x : this.ab;
                this.u = sksVar;
                sksVar.c(z, z2);
                this.f.setTextColor(this.j ? HwBottomNavigationView.this.k : HwBottomNavigationView.this.m);
            }
        }

        void setDirection(int i) {
            if (i == this.z) {
                return;
            }
            this.z = i;
            if (this.k) {
                j();
            } else {
                a(true, false);
            }
        }

        @Override // android.view.View
        public void setEnabled(boolean z) {
            sks sksVar = this.u;
            if (sksVar != null && this.f != null) {
                if (z) {
                    sksVar.setAlpha(255);
                    this.f.setAlpha(1.0f);
                } else {
                    sksVar.setAlpha((int) ((this.ar * 255.0f) + 0.5f));
                    this.f.setAlpha(this.ar);
                }
            }
            super.setFocusable(z);
            super.setClickable(z);
        }

        void setHasMessage(boolean z) {
            this.am = z;
            invalidate();
        }

        public void setMsgBgColor(int i) {
            this.ad = i;
            this.o.setColor(i);
            invalidate();
        }

        public void setOriginalDrawable(Drawable drawable) {
            this.ap = drawable;
        }

        boolean b() {
            return this.an;
        }

        boolean e() {
            return this.am;
        }

        private void a() {
            if (Float.compare(smp.a(this.g), 1.75f) >= 0) {
                setOnLongClickListener(new b());
            }
        }

        private void a(boolean z, boolean z2) {
            ViewGroup.LayoutParams layoutParams = this.f.getLayoutParams();
            if (z && (layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                if (this.z == 1) {
                    setGravity(17);
                    f();
                    int i = this.ai;
                    setPadding(i, 0, i, 0);
                    this.w.setVisibility(8);
                    this.s.setVisibility(0);
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    marginLayoutParams.setMargins(0, 0, 0, 0);
                    this.f.setLayoutParams(marginLayoutParams);
                    this.f.setAutoTextSize(1, this.ae);
                    this.f.setGravity(GravityCompat.START);
                    this.u = this.x;
                } else {
                    setGravity(0);
                    setMinimumHeight(this.p);
                    int i2 = this.ag;
                    setPadding(0, this.ah + i2, 0, i2);
                    this.w.setVisibility(0);
                    this.s.setVisibility(8);
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams;
                    marginLayoutParams2.setMargins(HwBottomNavigationView.this.ak, 0, HwBottomNavigationView.this.ak, 0);
                    this.f.setLayoutParams(marginLayoutParams2);
                    this.f.setAutoTextSize(0, this.q);
                    this.f.setGravity(1);
                    this.u = this.ab;
                }
                this.f.setText(this.n.getTitle());
                this.u.c(this.j, false);
            }
            if (z2) {
                if (this.m) {
                    this.x.a(this.f10597a);
                    this.x.b(this.i);
                    this.ab.a(this.f10597a);
                    this.ab.b(this.i);
                }
                this.f.setTextColor(this.j ? HwBottomNavigationView.this.k : HwBottomNavigationView.this.m);
            }
        }

        public BottomNavigationItemView(Context context, MenuItem menuItem, boolean z, int i, boolean z2, AttributeSet attributeSet, int i2) {
            super(context);
            this.j = false;
            this.m = true;
            this.z = -1;
            this.g = context;
            this.n = menuItem;
            dZN_(context, attributeSet, i2);
            this.f = (HwTextView) findViewById(R.id.content);
            this.w = (ImageView) findViewById(R.id.top_icon);
            this.s = (ImageView) findViewById(R.id.start_icon);
            this.r = (ImageView) findViewById(R.id.single_icon);
            this.e = (LinearLayout) findViewById(R.id.container);
            this.ar = HwBottomNavigationView.this.n.getFloat(R.dimen._2131362645_res_0x7f0a0355);
            this.x = new sks(this.g, this.n.getIcon());
            this.ab = new sks(this.g, this.n.getIcon(), this.ao);
            this.ap = this.x.dZv_();
            this.aa = HwBottomNavigationView.this.n.getDimensionPixelSize(R.dimen._2131363956_res_0x7f0a0874);
            this.ae = HwBottomNavigationView.this.n.getInteger(R.integer._2131623963_res_0x7f0e001b);
            this.q = HwBottomNavigationView.this.n.getDimensionPixelSize(R.dimen._2131362708_res_0x7f0a0394);
            this.t = HwBottomNavigationView.this.n.getInteger(R.integer._2131623966_res_0x7f0e001e);
            this.ak = HwBottomNavigationView.this.n.getDimensionPixelSize(R.dimen._2131363970_res_0x7f0a0882);
            this.al = HwBottomNavigationView.this.n.getDimensionPixelSize(R.dimen._2131363967_res_0x7f0a087f);
            this.aj = HwBottomNavigationView.this.n.getDimensionPixelSize(R.dimen._2131363969_res_0x7f0a0881);
            this.as = HwBottomNavigationView.this.n.getDimensionPixelSize(R.dimen._2131363966_res_0x7f0a087e);
            this.af = HwBottomNavigationView.this.n.getDimensionPixelSize(R.dimen._2131363959_res_0x7f0a0877);
            this.f.setAutoTextInfo(this.l, this.t, 1);
            if (z) {
                this.z = 1;
            } else {
                this.z = 0;
            }
            this.ac = i;
            this.m = z2;
            this.s.setImageDrawable(this.x);
            this.w.setImageDrawable(this.ab);
            Paint paint = new Paint();
            this.o = paint;
            paint.setAntiAlias(true);
            setOrientation(1);
            setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            a(true, true);
            a();
        }
    }

    private void d(int i) {
        boolean g = g();
        if (i == 21) {
            if (g) {
                if (this.q < getChildCount()) {
                    this.q++;
                    return;
                }
                return;
            } else {
                int i2 = this.q;
                if (i2 > 0) {
                    this.q = i2 - 1;
                    return;
                }
                return;
            }
        }
        if (i == 22) {
            if (g) {
                int i3 = this.q;
                if (i3 > 0) {
                    this.q = i3 - 1;
                    return;
                }
                return;
            }
            if (this.q < getChildCount()) {
                this.q++;
            }
        }
    }

    private void a(Context context, AttributeSet attributeSet) {
        snj snjVar = new snj(this);
        this.ay = snjVar;
        snjVar.eiZ_(context, attributeSet);
        this.ay.c(false);
        this.ay.d(true);
    }

    private boolean a(Menu menu) {
        return menu.size() <= 5;
    }

    private boolean dZG_(int i, int i2, CharSequence charSequence, Drawable drawable, boolean z) {
        MenuItem icon = this.h.add(0, i, i2, charSequence).setIcon(drawable);
        int size = this.h.size();
        this.f = size;
        if (this.ao) {
            this.ar = c(this.ba, size);
        } else {
            this.ar = b(this.ba, size);
        }
        dZK_(icon, this.f - 1, z, null, 0);
        return a(this.h);
    }

    private void j() {
        int i;
        HwColumnSystem hwColumnSystem = new HwColumnSystem(this.b);
        this.ba = hwColumnSystem;
        this.ao = false;
        this.ar = b(hwColumnSystem, this.h.size());
        if (this.bc) {
            this.am = false;
            this.al = false;
        } else if (this.al && (i = this.aq) > 0) {
            this.am = e(i);
        } else {
            this.am = e((this.b.getResources().getDisplayMetrics().widthPixels - getPaddingLeft()) - getPaddingRight());
        }
    }

    private boolean dZF_(int i, int i2, int i3, Drawable drawable, boolean z) {
        MenuItem icon = this.h.add(0, 0, 0, i3).setIcon(drawable);
        int size = this.h.size();
        this.f = size;
        if (this.ao) {
            this.ar = c(this.ba, size);
        } else {
            this.ar = b(this.ba, size);
        }
        dZK_(icon, this.f - 1, z, null, 0);
        return a(this.h);
    }

    private boolean e(int i) {
        return !this.aj && ((float) i) / 5.0f > getResources().getDisplayMetrics().density * ((float) this.o);
    }

    private void dZz_(AttributeSet attributeSet, int i) {
        this.f = this.h.size();
        for (int i2 = 0; i2 < this.f; i2++) {
            dZK_(this.h.getItem(i2), i2, this.aa, attributeSet, i);
        }
    }

    private int b(HwColumnSystem hwColumnSystem, int i) {
        hwColumnSystem.e(8);
        hwColumnSystem.e(this.b);
        this.as = hwColumnSystem.h();
        hwColumnSystem.e(9);
        hwColumnSystem.e(this.b);
        int h = hwColumnSystem.h();
        this.aq = h;
        return i > 3 ? h : this.as;
    }

    private int c(HwColumnSystem hwColumnSystem, int i) {
        hwColumnSystem.e(8);
        hwColumnSystem.d(this.b, this.ap, this.aw, this.ax);
        this.as = hwColumnSystem.h();
        hwColumnSystem.e(9);
        hwColumnSystem.d(this.b, this.ap, this.aw, this.ax);
        int h = hwColumnSystem.h();
        this.aq = h;
        return i > 3 ? h : this.as;
    }

    private void a(int i, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            View childAt = getChildAt(i3);
            if (childAt instanceof BottomNavigationItemView) {
                BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) childAt;
                ViewGroup.LayoutParams layoutParams = bottomNavigationItemView.getLayoutParams();
                if (layoutParams instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                    layoutParams2.height = i2;
                    bottomNavigationItemView.setLayoutParams(layoutParams2);
                }
            }
        }
    }

    private void a(int i, int i2, a aVar) {
        int i3;
        int size = View.MeasureSpec.getSize(i);
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childCount = getChildCount();
        if (childCount <= 0) {
            aVar.a(size);
            aVar.e(0);
            return;
        }
        if (this.al && (i3 = this.ar) > 0 && i3 < paddingLeft) {
            paddingLeft = i3;
        }
        int i4 = paddingLeft / childCount;
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingTop, -2);
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt instanceof BottomNavigationItemView) {
                BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) childAt;
                setNavigationViewClipOnLand(bottomNavigationItemView);
                bottomNavigationItemView.measure(View.MeasureSpec.makeMeasureSpec(i4, 1073741824), childMeasureSpec);
                LinearLayout container = bottomNavigationItemView.getContainer();
                ViewGroup.LayoutParams layoutParams = container.getLayoutParams();
                if (layoutParams instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                    if (bottomNavigationItemView.c()) {
                        layoutParams2.height = -1;
                    }
                    layoutParams2.gravity = 17;
                    dZD_(container, 0, 0, layoutParams2);
                }
                int measuredHeight = bottomNavigationItemView.getMeasuredHeight();
                if (measuredHeight > i5) {
                    i5 = measuredHeight;
                }
                dZB_(bottomNavigationItemView, i4);
            }
        }
        a(childCount, i5);
        aVar.a(size);
        aVar.e(i5 + paddingTop);
    }

    private void e(float f2, int i, List<Float> list, a aVar) {
        int e2 = aVar.e();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof BottomNavigationItemView) {
                BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) childAt;
                if (!bottomNavigationItemView.d() && !bottomNavigationItemView.c()) {
                    bottomNavigationItemView.setClipChildren(true);
                    bottomNavigationItemView.setClipToPadding(true);
                } else {
                    bottomNavigationItemView.setClipChildren(false);
                    bottomNavigationItemView.setClipToPadding(false);
                }
                if (bottomNavigationItemView.c) {
                    bottomNavigationItemView.c = false;
                } else {
                    a(f2, i, bottomNavigationItemView);
                    int measuredHeight = bottomNavigationItemView.getMeasuredHeight();
                    if (measuredHeight > e2) {
                        e2 = measuredHeight;
                    }
                }
            }
        }
        aVar.e(e2);
    }

    private void d(int i, int i2, a aVar) {
        int childCount = getChildCount();
        int i3 = 0;
        boolean z = false;
        boolean z2 = false;
        while (true) {
            boolean z3 = true;
            if (i3 >= childCount) {
                break;
            }
            View childAt = getChildAt(i3);
            if (childAt instanceof BottomNavigationItemView) {
                BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) childAt;
                z2 |= bottomNavigationItemView.d();
                if (!bottomNavigationItemView.b() && !bottomNavigationItemView.c()) {
                    z3 = false;
                }
                z |= z3;
            }
            i3++;
        }
        if (z) {
            setClipChildren(false);
            setClipToPadding(false);
        }
        if (childCount != 2 && childCount != 1 && !z2) {
            c(i, i2, aVar);
        } else {
            e(i, i2, aVar);
        }
    }

    private int b(int i, int i2, int i3) {
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            View childAt = getChildAt(i5);
            if (childAt instanceof BottomNavigationItemView) {
                BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) childAt;
                if (!bottomNavigationItemView.d() && !bottomNavigationItemView.c()) {
                    bottomNavigationItemView.setClipChildren(true);
                    bottomNavigationItemView.setClipToPadding(true);
                } else {
                    bottomNavigationItemView.setClipChildren(false);
                    bottomNavigationItemView.setClipToPadding(false);
                }
                bottomNavigationItemView.measure(View.MeasureSpec.makeMeasureSpec(i2, 1073741824), i3);
                dZB_(bottomNavigationItemView, i2);
                View container = bottomNavigationItemView.getContainer();
                ViewGroup.LayoutParams layoutParams = container.getLayoutParams();
                if (layoutParams instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                    layoutParams2.gravity = 1;
                    container.setLayoutParams(layoutParams2);
                    dZD_(container, 0, 0, layoutParams2);
                }
                View icon = bottomNavigationItemView.getIcon();
                ViewGroup.LayoutParams layoutParams3 = icon.getLayoutParams();
                if (layoutParams3 instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) layoutParams3;
                    layoutParams4.gravity = 1;
                    dZD_(icon, 0, 0, layoutParams4);
                }
                int measuredHeight = bottomNavigationItemView.getMeasuredHeight();
                if (measuredHeight > i4) {
                    i4 = measuredHeight;
                }
            }
        }
        return i4;
    }

    private void c(List<Float> list, int i, float f2, int i2, BottomNavigationItemView bottomNavigationItemView) {
        int i3;
        int childCount = getChildCount();
        if (i != 0 && i != childCount - 1) {
            int i4 = i - 1;
            float floatValue = list.get(i4).floatValue();
            int i5 = i + 1;
            float floatValue2 = list.get(i5).floatValue();
            if (floatValue >= 0.0f && floatValue2 >= 0.0f) {
                float floatValue3 = list.get(i).floatValue();
                if (floatValue > floatValue2) {
                    floatValue = floatValue2;
                }
                float f3 = floatValue3 / 2.0f;
                if (!(getChildAt(i4) instanceof BottomNavigationItemView) || !(getChildAt(i5) instanceof BottomNavigationItemView)) {
                    return;
                }
                BottomNavigationItemView bottomNavigationItemView2 = (BottomNavigationItemView) getChildAt(i4);
                BottomNavigationItemView bottomNavigationItemView3 = (BottomNavigationItemView) getChildAt(i5);
                if (f3 + floatValue > 0.0f) {
                    i3 = (int) (f2 - floatValue3);
                    bottomNavigationItemView.measure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
                    float f4 = (-floatValue3) / 2.0f;
                    bottomNavigationItemView2.b = f4;
                    bottomNavigationItemView3.d = f4;
                } else {
                    i3 = (int) (f2 + (2.0f * floatValue));
                    bottomNavigationItemView.measure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
                    bottomNavigationItemView2.b = floatValue;
                    bottomNavigationItemView3.d = floatValue;
                }
            } else {
                i3 = (int) f2;
                bottomNavigationItemView.measure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
            }
        } else {
            i3 = (int) f2;
            bottomNavigationItemView.measure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
        }
        bottomNavigationItemView.c = true;
        dZB_(bottomNavigationItemView, i3);
    }

    private void d(float f2, int i, List<Float> list, a aVar) {
        int childCount = getChildCount();
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            if (list.get(i3).floatValue() < 0.0f) {
                View childAt = getChildAt(i3);
                if (childAt instanceof BottomNavigationItemView) {
                    BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) childAt;
                    View icon = bottomNavigationItemView.getIcon();
                    ViewGroup.LayoutParams layoutParams = icon.getLayoutParams();
                    if (layoutParams instanceof LinearLayout.LayoutParams) {
                        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                        layoutParams2.gravity = 1;
                        dZD_(icon, 0, 0, layoutParams2);
                    }
                    dZC_(bottomNavigationItemView.getContainer(), 0, 0);
                    c(list, i3, f2, i, bottomNavigationItemView);
                    int measuredHeight = bottomNavigationItemView.getMeasuredHeight();
                    if (measuredHeight > i2) {
                        i2 = measuredHeight;
                    }
                }
            }
        }
        aVar.e(i2);
    }

    private void a(float f2, int i, BottomNavigationItemView bottomNavigationItemView) {
        ViewGroup.LayoutParams layoutParams = bottomNavigationItemView.getContainer().getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            ((LinearLayout.LayoutParams) layoutParams).gravity = 1;
        }
        ViewGroup.LayoutParams layoutParams2 = bottomNavigationItemView.getIcon().getLayoutParams();
        if (layoutParams2 instanceof LinearLayout.LayoutParams) {
            ((LinearLayout.LayoutParams) layoutParams2).gravity = 1;
        }
        bottomNavigationItemView.measure(View.MeasureSpec.makeMeasureSpec((int) ((f2 - bottomNavigationItemView.d) - bottomNavigationItemView.b), 1073741824), i);
        dZB_(bottomNavigationItemView, (int) ((f2 - bottomNavigationItemView.d) - bottomNavigationItemView.b));
        bottomNavigationItemView.d = 0.0f;
        bottomNavigationItemView.b = 0.0f;
    }

    private void a(int i, a aVar) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        int childCount = getChildCount();
        if (childCount <= 0) {
            aVar.e(0);
            aVar.a(0);
            return;
        }
        int size = View.MeasureSpec.getSize(i);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int i2 = (size - paddingTop) / (childCount * 2);
        aVar.e(size);
        aVar.a(this.p);
        boolean z = layoutParams.height == -2;
        int i3 = z ? 0 : i2;
        int b2 = b(aVar.b(), i3);
        if (z) {
            aVar.e(b2 * childCount * 2);
        } else {
            aVar.e(i3 * childCount * 2);
        }
        aVar.e(aVar.e() + paddingTop);
    }

    private int b(int i, int i2) {
        int childCount = getChildCount();
        int i3 = 0;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt != null) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i, 1073741824), 0);
                i3 = Math.max(i3, childAt.getMeasuredHeight());
            }
        }
        int max = Math.max(i3, i2);
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt2 = getChildAt(i5);
            if (childAt2 instanceof BottomNavigationItemView) {
                BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) childAt2;
                bottomNavigationItemView.setDirection(0);
                bottomNavigationItemView.setGravity(17);
                ViewGroup.LayoutParams layoutParams = bottomNavigationItemView.getLayoutParams();
                if (layoutParams instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                    layoutParams2.height = max;
                    layoutParams2.width = i;
                    childAt2.setLayoutParams(layoutParams);
                }
            }
        }
        return max;
    }

    private void c(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof BottomNavigationItemView) {
                BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) childAt;
                if (this.am) {
                    bottomNavigationItemView.setDirection(1);
                } else {
                    bottomNavigationItemView.setDirection(0);
                }
            }
        }
    }

    private void a(List<Float> list, int i, float f2) {
        View childAt = getChildAt(i);
        if (childAt instanceof BottomNavigationItemView) {
            float desiredWidth = f2 - (Layout.getDesiredWidth(this.h.getItem(i).getTitle(), ((BottomNavigationItemView) childAt).getContent().getPaint()) + (this.ak * 2));
            if (desiredWidth > 0.0f) {
                list.add(Float.valueOf(desiredWidth / 2.0f));
            } else {
                list.add(Float.valueOf(desiredWidth));
            }
        }
    }

    private void dZC_(View view, int i, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            dZD_(view, i, i2, (ViewGroup.MarginLayoutParams) layoutParams);
        }
    }

    private void dZD_(View view, int i, int i2, ViewGroup.MarginLayoutParams marginLayoutParams) {
        if (g()) {
            marginLayoutParams.setMargins(i2, marginLayoutParams.topMargin, i, marginLayoutParams.bottomMargin);
        } else {
            marginLayoutParams.setMargins(i, marginLayoutParams.topMargin, i2, marginLayoutParams.bottomMargin);
        }
        view.setLayoutParams(marginLayoutParams);
    }

    private void dZB_(View view, int i) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = i;
        view.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(BottomNavigationItemView bottomNavigationItemView, boolean z) {
        BottomNavListener bottomNavListener;
        int itemIndex = bottomNavigationItemView.getItemIndex();
        int i = this.af;
        if (itemIndex == i && (bottomNavListener = this.i) != null) {
            bottomNavListener.onBottomNavItemReselected(this.h.getItem(itemIndex), itemIndex);
        } else if (itemIndex != i) {
            if (i < this.f && i >= 0) {
                View childAt = getChildAt(i);
                if (!(childAt instanceof BottomNavigationItemView)) {
                    return;
                }
                ((BottomNavigationItemView) childAt).setChecked(false, true);
                BottomNavListener bottomNavListener2 = this.i;
                if (bottomNavListener2 != null) {
                    bottomNavListener2.onBottomNavItemUnselected(this.h.getItem(this.af), this.af);
                }
            }
            this.af = itemIndex;
            if (z) {
                bottomNavigationItemView.setChecked(true, true);
            }
            BottomNavListener bottomNavListener3 = this.i;
            if (bottomNavListener3 != null) {
                bottomNavListener3.onBottomNavItemSelected(this.h.getItem(this.af), this.af);
            }
        } else {
            Log.e("HwBottomNavigationView", "invalid index");
        }
        this.q = this.af;
    }

    private void dZy_(Canvas canvas) {
        if (!this.au || this.az == null) {
            return;
        }
        Rect rect = this.z;
        if (getOrientation() == 1) {
            if (g()) {
                rect.left = 0;
                rect.right = this.az.getIntrinsicWidth();
                rect.top = getPaddingTop();
                rect.bottom = (getBottom() - getTop()) - getPaddingBottom();
            } else {
                rect.left = ((getRight() - getLeft()) - getPaddingRight()) - 1;
                rect.right = this.az.getIntrinsicWidth();
                rect.top = getPaddingTop();
                rect.bottom = (getBottom() - getTop()) - getPaddingBottom();
            }
        } else {
            rect.left = getPaddingLeft();
            rect.right = (getRight() - getLeft()) - getPaddingRight();
            rect.top = 0;
            rect.bottom = this.az.getIntrinsicHeight();
        }
        this.az.setBounds(rect);
        this.az.draw(canvas);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(KeyEvent keyEvent, int i) {
        View childAt = getChildAt(this.q);
        if (childAt != null && childAt.isFocused()) {
            if (getOrientation() == 1) {
                a(i);
            } else {
                d(i);
            }
            if (i == 61) {
                dZA_(keyEvent);
                return;
            }
            return;
        }
        if (this.q == getChildCount()) {
            this.q--;
        }
    }

    private void dZA_(KeyEvent keyEvent) {
        int i;
        if (keyEvent != null) {
            if (keyEvent.isShiftPressed() && (i = this.q) > 0) {
                this.q = i - 1;
            } else {
                if (keyEvent.isShiftPressed() || this.q >= getChildCount() - 1) {
                    return;
                }
                this.q++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        int childCount = getChildCount();
        if (childCount <= 0) {
            return;
        }
        int i = (this.af + 1) % childCount;
        setItemChecked(i);
        if (i == this.af) {
            int childCount2 = getChildCount();
            if (i < 0 || i >= childCount2) {
                return;
            }
            View childAt = getChildAt(i);
            if (childAt instanceof BottomNavigationItemView) {
                childAt.requestFocus();
            }
        }
    }
}
