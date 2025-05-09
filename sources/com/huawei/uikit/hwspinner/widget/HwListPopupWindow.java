package com.huawei.uikit.hwspinner.widget;

import android.R;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.ShowableListMenu;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import defpackage.slc;

/* loaded from: classes7.dex */
public class HwListPopupWindow implements ShowableListMenu {

    /* renamed from: a, reason: collision with root package name */
    private final d f10743a;
    private boolean aa;
    private int ab;
    private boolean ac;
    private int ad;
    private Drawable ae;
    private DataSetObserver af;
    private View ag;
    private View ah;
    private int ai;
    private AdapterView.OnItemClickListener an;
    private Runnable b;
    private final e c;
    private final c d;
    private final a e;
    private boolean f;
    private boolean g;
    private final Handler h;
    private final Rect i;
    private int j;
    private int k;
    private PopupWindow l;
    private int m;
    private boolean n;
    private Rect o;
    private Context p;
    private ListAdapter q;
    private HwDropDownListView r;
    private int s;
    private int t;
    private int u;
    private boolean v;
    private int w;
    private boolean x;
    private int y;
    private boolean z;

    class a implements View.OnTouchListener {
        private a() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (view == null || motionEvent == null) {
                Log.w("HwListPopupWindow", "setOnTouchListener: onTouch: view or event is null");
                return false;
            }
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            boolean z = action == 0 && HwListPopupWindow.this.l != null && HwListPopupWindow.this.l.isShowing();
            boolean z2 = x >= 0 && x < HwListPopupWindow.this.l.getWidth() && y >= 0 && y < HwListPopupWindow.this.l.getHeight();
            if (z && z2) {
                HwListPopupWindow.this.h.postDelayed(HwListPopupWindow.this.c, 250L);
            }
            if (action == 1) {
                HwListPopupWindow.this.h.removeCallbacks(HwListPopupWindow.this.c);
            }
            return false;
        }

        /* synthetic */ a(HwListPopupWindow hwListPopupWindow, f fVar) {
            this();
        }
    }

    class b extends DataSetObserver {
        private b() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (HwListPopupWindow.this.isShowing()) {
                HwListPopupWindow.this.show();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            HwListPopupWindow.this.dismiss();
        }

        /* synthetic */ b(HwListPopupWindow hwListPopupWindow, f fVar) {
            this();
        }
    }

    class c implements AbsListView.OnScrollListener {
        private c() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 1 && HwListPopupWindow.this.e() && HwListPopupWindow.this.l.getContentView() != null) {
                HwListPopupWindow.this.h.removeCallbacks(HwListPopupWindow.this.c);
                HwListPopupWindow.this.c.run();
            }
        }

        /* synthetic */ c(HwListPopupWindow hwListPopupWindow, f fVar) {
            this();
        }
    }

    class d implements Runnable {
        private d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HwListPopupWindow.this.b();
        }

        /* synthetic */ d(HwListPopupWindow hwListPopupWindow, f fVar) {
            this();
        }
    }

    class e implements Runnable {
        private e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (((HwListPopupWindow.this.r != null && HwListPopupWindow.this.r.isAttachedToWindow()) & (HwListPopupWindow.this.r.getCount() > HwListPopupWindow.this.r.getChildCount())) && (HwListPopupWindow.this.r.getChildCount() <= HwListPopupWindow.this.ab)) {
                HwListPopupWindow.this.l.setInputMethodMode(2);
                HwListPopupWindow.this.show();
            }
        }

        /* synthetic */ e(HwListPopupWindow hwListPopupWindow, f fVar) {
            this();
        }
    }

    class f extends HwForwardingListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ HwListPopupWindow f10745a;

        @Override // com.huawei.uikit.hwspinner.widget.HwForwardingListener
        public ShowableListMenu getPopup() {
            return this.f10745a;
        }
    }

    class h implements Runnable {
        h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View ehf_ = HwListPopupWindow.this.ehf_();
            if (ehf_ == null || ehf_.getWindowToken() == null) {
                return;
            }
            HwListPopupWindow.this.show();
        }
    }

    class i implements AdapterView.OnItemSelectedListener {
        i() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            HwDropDownListView hwDropDownListView;
            if (i != -1 && (hwDropDownListView = HwListPopupWindow.this.r) != null) {
                hwDropDownListView.setListSelectionHidden(false);
            }
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public HwListPopupWindow(Context context) {
        this(context, null, R.attr.listPopupWindowStyle, 0);
    }

    private void l() {
        float f2;
        View view;
        if (this.l == null) {
            return;
        }
        slc.c(this.l, "setShadowEnabled", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(this.f)}, PopupWindow.class);
        if (this.f) {
            slc.c(this.l, "setShadowStyle", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(this.j)}, PopupWindow.class);
            slc.c(this.l, "setShadowSize", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(this.k)}, PopupWindow.class);
            slc.c(this.l, "setShadowColor", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(this.m)}, PopupWindow.class);
            slc.c(this.l, "setShadowClip", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(this.g)}, PopupWindow.class);
            ListAdapter adapter = this.r.getAdapter();
            if (adapter == null || adapter.getCount() <= 0 || (view = adapter.getView(0, null, this.r)) == null) {
                f2 = -1.0f;
            } else {
                view.measure(0, 0);
                f2 = view.getMeasuredHeight() / 2.0f;
            }
            slc.c(this.l, "setShadowViewZ", new Class[]{Float.TYPE}, new Object[]{Float.valueOf(f2)}, PopupWindow.class);
        }
    }

    private void n() {
        View view = this.ag;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.ag);
            }
        }
    }

    public void a(int i2) {
        Drawable background = this.l.getBackground();
        if (background == null) {
            i(i2);
            return;
        }
        background.getPadding(this.i);
        Rect rect = this.i;
        this.t = rect.left + rect.right + i2;
    }

    protected void a(boolean z) {
        this.g = z;
    }

    public void b() {
        HwDropDownListView hwDropDownListView = this.r;
        if (hwDropDownListView != null) {
            hwDropDownListView.setListSelectionHidden(true);
            slc.c(hwDropDownListView, "hideSelector", null, null, AbsListView.class);
            hwDropDownListView.requestLayout();
        }
    }

    protected void b(int i2) {
        this.m = i2;
    }

    public void c(int i2) {
        this.l.setInputMethodMode(i2);
    }

    public void c(boolean z) {
        this.n = z;
        this.l.setFocusable(z);
    }

    public int d() {
        return this.t;
    }

    protected void d(int i2) {
        this.j = i2;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void dismiss() {
        this.l.dismiss();
        n();
        this.l.setContentView(null);
        this.r = null;
        this.h.removeCallbacks(this.c);
    }

    protected void e(int i2) {
        this.k = i2;
    }

    protected void e(boolean z) {
        this.f = z;
    }

    public boolean e() {
        return this.l.getInputMethodMode() != 2;
    }

    public View ehf_() {
        return this.ah;
    }

    public void ehg_(View view) {
        this.ah = view;
    }

    public void ehh_(Drawable drawable) {
        this.ae = drawable;
    }

    public void ehi_(AdapterView.OnItemClickListener onItemClickListener) {
        this.an = onItemClickListener;
    }

    public void g(int i2) {
        this.ai = i2;
    }

    public Drawable getBackground() {
        return this.l.getBackground();
    }

    public int getHorizontalOffset() {
        return this.w;
    }

    public int getVerticalOffset() {
        if (this.x) {
            return this.y;
        }
        return 0;
    }

    public void i(int i2) {
        this.t = i2;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public boolean isShowing() {
        return this.l.isShowing();
    }

    public void j(int i2) {
        HwDropDownListView hwDropDownListView = this.r;
        if (!isShowing() || hwDropDownListView == null) {
            return;
        }
        hwDropDownListView.setListSelectionHidden(false);
        hwDropDownListView.setSelection(i2);
        if (hwDropDownListView.getChoiceMode() != 0) {
            hwDropDownListView.setItemChecked(i2, true);
        }
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.af == null) {
            this.af = new b(this, null);
        }
        ListAdapter listAdapter2 = this.q;
        if (listAdapter2 != null) {
            listAdapter2.unregisterDataSetObserver(this.af);
        }
        this.q = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.af);
        }
        HwDropDownListView hwDropDownListView = this.r;
        if (hwDropDownListView != null) {
            hwDropDownListView.setAdapter(this.q);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.l.setBackgroundDrawable(drawable);
    }

    public void setHorizontalOffset(int i2) {
        this.w = i2;
    }

    public void setVerticalOffset(int i2) {
        this.y = i2;
        this.x = true;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void show() {
        int j = j();
        boolean e2 = e();
        slc.c(this.l, "setAllowScrollingAnchorParent", new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(e2)}, PopupWindow.class);
        this.l.setWindowLayoutType(this.u);
        l();
        if (this.l.isShowing()) {
            a(e2, j);
        } else if (ehf_() != null) {
            h(j);
        }
    }

    public HwListPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listPopupWindowStyle, 0);
    }

    private void a(boolean z, int i2) {
        if (ehf_() == null || !ehf_().isAttachedToWindow()) {
            return;
        }
        int i3 = this.t;
        if (i3 == -1) {
            i3 = -1;
        } else if (i3 == -2) {
            i3 = ehf_().getWidth();
        }
        int i4 = this.s;
        boolean z2 = false;
        if (i4 == -1) {
            if (z) {
                i2 = -1;
            }
            if (z) {
                this.l.setWidth(this.t == -1 ? -1 : 0);
                this.l.setHeight(-1);
            } else {
                this.l.setWidth(this.t == -1 ? -1 : 0);
                this.l.setHeight(0);
            }
        } else if (i4 != -2) {
            i2 = i4;
        }
        PopupWindow popupWindow = this.l;
        if (!this.aa && !this.z) {
            z2 = true;
        }
        popupWindow.setOutsideTouchable(z2);
        this.l.update(ehf_(), this.w, this.y, i3 < 0 ? -1 : i3, i2 < 0 ? -1 : i2);
        this.l.getContentView().requestFocus(OldToNewMotionPath.SPORT_TYPE_TENNIS);
    }

    private int f() {
        Drawable background = this.l.getBackground();
        if (background == null) {
            this.i.setEmpty();
            return 0;
        }
        background.getPadding(this.i);
        Rect rect = this.i;
        int i2 = rect.top;
        int i3 = rect.bottom + i2;
        if (this.x) {
            return i3;
        }
        this.y = -i2;
        return i3;
    }

    private void h(int i2) {
        int i3 = this.t;
        if (i3 == -1) {
            i3 = -1;
        } else if (i3 == -2) {
            i3 = ehf_().getWidth();
        }
        int i4 = this.s;
        if (i4 == -1) {
            i2 = -1;
        } else if (i4 != -2) {
            i2 = i4;
        }
        this.l.setWidth(i3);
        this.l.setHeight(i2);
        slc.c(this.l, "setClipToScreenEnabled", new Class[]{Boolean.TYPE}, new Object[]{true}, PopupWindow.class);
        this.l.setOutsideTouchable((this.aa || this.z) ? false : true);
        this.l.setTouchInterceptor(this.e);
        slc.c(this.l, "setEpicenterBounds", new Class[]{Rect.class}, new Object[]{this.o}, PopupWindow.class);
        if (this.ac) {
            this.l.setOverlapAnchor(this.v);
        }
        this.l.showAsDropDown(ehf_(), this.w, this.y, this.ad);
        this.r.setSelection(-1);
        this.l.getContentView().requestFocus(OldToNewMotionPath.SPORT_TYPE_TENNIS);
        if (!this.n || this.r.isInTouchMode()) {
            b();
        }
        if (this.n) {
            return;
        }
        this.h.post(this.f10743a);
    }

    private int i() {
        int i2;
        int i3 = this.t;
        if (i3 >= 0) {
            i2 = Integer.MIN_VALUE;
        } else {
            i3 = 0;
            i2 = 0;
        }
        return View.MeasureSpec.makeMeasureSpec(i3, i2);
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public HwDropDownListView getListView() {
        return this.r;
    }

    public HwListPopupWindow(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public HwListPopupWindow(Context context, AttributeSet attributeSet, int i2, int i3) {
        this.s = -2;
        this.t = -2;
        this.u = 1002;
        this.x = false;
        this.v = false;
        this.ac = false;
        this.ad = 0;
        this.z = false;
        this.aa = false;
        this.ab = Integer.MAX_VALUE;
        this.ai = 0;
        f fVar = null;
        this.c = new e(this, fVar);
        this.e = new a(this, fVar);
        this.d = new c(this, fVar);
        this.f10743a = new d(this, fVar);
        this.i = new Rect();
        this.n = false;
        this.p = context;
        this.h = new Handler(context.getMainLooper());
        this.w = 0;
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(com.huawei.health.R.dimen._2131364401_res_0x7f0a0a31);
        this.y = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.x = true;
        }
        PopupWindow popupWindow = new PopupWindow(context, attributeSet, i2, i3);
        this.l = popupWindow;
        popupWindow.setInputMethodMode(1);
    }

    private int h() {
        int i2 = this.t;
        if (i2 == -2) {
            int i3 = this.p.getResources().getDisplayMetrics().widthPixels;
            Rect rect = this.i;
            return View.MeasureSpec.makeMeasureSpec(i3 - (rect.left + rect.right), Integer.MIN_VALUE);
        }
        if (i2 != -1) {
            Rect rect2 = this.i;
            return View.MeasureSpec.makeMeasureSpec(i2 - (rect2.left + rect2.right), 1073741824);
        }
        int i4 = this.p.getResources().getDisplayMetrics().widthPixels;
        Rect rect3 = this.i;
        return View.MeasureSpec.makeMeasureSpec(i4 - (rect3.left + rect3.right), 1073741824);
    }

    HwDropDownListView d(Context context, boolean z) {
        return new HwDropDownListView(context, z);
    }

    private int j() {
        int ehd_;
        if (this.r == null) {
            Context context = this.p;
            d(context);
            View view = this.r;
            View view2 = this.ag;
            ehd_ = 0;
            if (view2 != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i2 = this.ai;
                if (i2 == 0) {
                    linearLayout.addView(view2);
                    linearLayout.addView(view, layoutParams);
                } else if (i2 == 1) {
                    linearLayout.addView(view, layoutParams);
                    linearLayout.addView(view2);
                }
                view2.measure(i(), 0);
                ehd_ = ehd_(view2);
                view = linearLayout;
            }
            this.l.setContentView(view);
        } else {
            ehd_ = ehd_(this.ag);
        }
        int f2 = f();
        int g = g();
        if (this.z || this.s == -1) {
            return g + f2;
        }
        int d2 = this.r.d(h(), g - ehd_);
        if (d2 > 0) {
            ehd_ += f2 + this.r.getPaddingTop() + this.r.getPaddingBottom();
        }
        return d2 + ehd_;
    }

    private int g() {
        if (ehf_() == null) {
            return 0;
        }
        return this.l.getMaxAvailableHeight(ehf_(), this.y, this.l.getInputMethodMode() == 2);
    }

    private void d(Context context) {
        this.b = new h();
        this.r = d(context, !this.n);
        this.r.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        Drawable drawable = this.ae;
        if (drawable != null) {
            this.r.setSelector(drawable);
        } else {
            this.r.setSelector(com.huawei.health.R.drawable._2131429552_res_0x7f0b08b0);
        }
        this.r.setAdapter(this.q);
        this.r.setOnItemClickListener(this.an);
        this.r.setFocusable(true);
        this.r.setFocusableInTouchMode(true);
        this.r.setOnItemSelectedListener(new i());
        this.r.setOnScrollListener(this.d);
    }

    private int ehd_(View view) {
        int i2 = 0;
        if (view == null) {
            return 0;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            i2 = layoutParams2.topMargin + layoutParams2.bottomMargin;
        }
        return view.getMeasuredHeight() + i2;
    }
}
