package com.huawei.uikit.phone.hwbottomnavigationview.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.uikit.hwbottomnavigationview.widget.HwBottomNavigationView;
import com.huawei.uikit.phone.hwunifiedinteract.widget.HwKeyEventDetector;
import defpackage.smp;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class HwBottomNavigationView extends com.huawei.uikit.hwbottomnavigationview.widget.HwBottomNavigationView {
    private int aa;
    private int ab;
    private boolean ac;
    private HwBottomNavigationView.BottomNavigationItemView ad;
    private PopupWindow o;
    private TextView p;
    private int q;
    private ImageView r;
    private View s;
    private View t;
    private int u;
    private int v;
    private Rect w;
    private List<Rect> x;
    private int y;
    private Drawable[] z;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HwBottomNavigationView.this.w = new Rect(HwBottomNavigationView.this.getLeft(), HwBottomNavigationView.this.getTop(), HwBottomNavigationView.this.getRight(), HwBottomNavigationView.this.getBottom());
            int childCount = HwBottomNavigationView.this.getChildCount();
            HwBottomNavigationView.this.x.clear();
            for (int i = 0; i < childCount; i++) {
                View childAt = HwBottomNavigationView.this.getChildAt(i);
                HwBottomNavigationView.this.x.add(new Rect(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom()));
            }
            HwBottomNavigationView.this.v = -1;
            HwBottomNavigationView.this.ab = -1;
        }
    }

    class d implements ViewTreeObserver.OnPreDrawListener {
        d() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            HwBottomNavigationView.this.s.getViewTreeObserver().removeOnPreDrawListener(this);
            HwBottomNavigationView.this.o.dismiss();
            if (Float.compare(smp.a(HwBottomNavigationView.this.b), 3.2f) >= 0) {
                HwBottomNavigationView hwBottomNavigationView = HwBottomNavigationView.this;
                hwBottomNavigationView.setPopupHeight(hwBottomNavigationView.q);
            } else {
                HwBottomNavigationView hwBottomNavigationView2 = HwBottomNavigationView.this;
                hwBottomNavigationView2.setPopupHeight(hwBottomNavigationView2.y);
            }
            HwBottomNavigationView.this.o.showAtLocation(HwBottomNavigationView.this.getRootView(), 17, 0, 0);
            return true;
        }
    }

    public HwBottomNavigationView(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPopupHeight(int i) {
        this.o.setHeight(Math.max(this.s.getHeight(), i));
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0023, code lost:
    
        if (r1 != 3) goto L45;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            r0 = 0
            if (r8 != 0) goto L4
            return r0
        L4:
            android.content.Context r1 = r7.b
            float r1 = defpackage.smp.a(r1)
            r2 = 1071644672(0x3fe00000, float:1.75)
            int r1 = java.lang.Float.compare(r1, r2)
            r2 = -1
            if (r1 != r2) goto L18
            boolean r8 = super.dispatchTouchEvent(r8)
            return r8
        L18:
            int r1 = r8.getAction()
            r3 = 1
            if (r1 == r3) goto L7b
            r3 = 2
            if (r1 == r3) goto L26
            r2 = 3
            if (r1 == r2) goto L7b
            goto L89
        L26:
            java.util.List<android.graphics.Rect> r1 = r7.x
            if (r1 == 0) goto L76
            boolean r1 = r7.getLongClickEnable()
            if (r1 != 0) goto L31
            goto L76
        L31:
            float r1 = r8.getX()
            float r3 = r8.getY()
        L39:
            java.util.List<android.graphics.Rect> r4 = r7.x
            int r4 = r4.size()
            if (r0 >= r4) goto L72
            java.util.List<android.graphics.Rect> r4 = r7.x
            java.lang.Object r4 = r4.get(r0)
            android.graphics.Rect r4 = (android.graphics.Rect) r4
            int r5 = (int) r1
            int r6 = (int) r3
            boolean r4 = r4.contains(r5, r6)
            if (r4 == 0) goto L53
            r7.v = r0
        L53:
            r7.f()
            int r4 = r7.v
            if (r4 == r2) goto L6f
            int r5 = r7.ab
            if (r5 == r4) goto L6f
            android.view.View r4 = r7.getChildAt(r4)
            boolean r5 = r4 instanceof com.huawei.uikit.hwbottomnavigationview.widget.HwBottomNavigationView.BottomNavigationItemView
            if (r5 == 0) goto L6a
            r7.eje_(r4)
            goto L6f
        L6a:
            boolean r8 = super.dispatchTouchEvent(r8)
            return r8
        L6f:
            int r0 = r0 + 1
            goto L39
        L72:
            r7.e(r1, r3)
            goto L89
        L76:
            boolean r8 = super.dispatchTouchEvent(r8)
            return r8
        L7b:
            r7.setLongClickEnable(r0)
            android.widget.PopupWindow r0 = r7.o
            if (r0 == 0) goto L89
            com.huawei.uikit.hwbottomnavigationview.widget.HwBottomNavigationView$BottomNavigationItemView r0 = r7.ad
            if (r0 == 0) goto L89
            r7.l()
        L89:
            boolean r8 = super.dispatchTouchEvent(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.phone.hwbottomnavigationview.widget.HwBottomNavigationView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean g() {
        return this.ac;
    }

    public Drawable[] getPopupIconSet() {
        return this.z;
    }

    @Override // com.huawei.uikit.hwbottomnavigationview.widget.HwBottomNavigationView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        j();
    }

    @Override // com.huawei.uikit.hwbottomnavigationview.widget.HwBottomNavigationView, android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        j();
    }

    public void setPopupIconSet(Drawable[] drawableArr) {
        this.z = drawableArr;
    }

    public void setTintPopupEnable(boolean z) {
        this.ac = z;
    }

    public HwBottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100216_res_0x7f060238);
    }

    private void f() {
        int i;
        HwBottomNavigationView.BottomNavigationItemView bottomNavigationItemView;
        int i2 = this.v;
        if (i2 == -1 || (i = this.ab) == -1 || i2 != i || this.o != null || (bottomNavigationItemView = this.ad) == null) {
            return;
        }
        bottomNavigationItemView.setPressed(true);
        i();
    }

    private void l() {
        this.ad.setPressed(false);
        this.ad.performClick();
        this.o.dismiss();
        this.o = null;
        this.p = null;
        this.r = null;
        this.s = null;
    }

    private void m() {
        if (!smp.e(this.b) || smp.d(this.b)) {
            this.p.setSingleLine();
        } else {
            this.p.setMaxLines(4);
        }
        this.s.getViewTreeObserver().addOnPreDrawListener(new d());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.uikit.hwbottomnavigationview.widget.HwBottomNavigationView
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public HwKeyEventDetector e() {
        return new HwKeyEventDetector(getContext());
    }

    public HwBottomNavigationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.v = -1;
        this.ab = -1;
        h();
    }

    private void i() {
        Drawable originalDrawable;
        Drawable.ConstantState constantState;
        Drawable[] popupIconSet = getPopupIconSet();
        if (popupIconSet == null) {
            originalDrawable = this.ad.getOriginalDrawable();
        } else {
            int length = popupIconSet.length;
            int i = this.v;
            if (i > -1 && i < length) {
                originalDrawable = popupIconSet[i];
                if (originalDrawable == null) {
                    originalDrawable = this.ad.getOriginalDrawable();
                }
            } else {
                originalDrawable = this.ad.getOriginalDrawable();
            }
        }
        if (originalDrawable == null || (constantState = originalDrawable.getConstantState()) == null) {
            return;
        }
        Drawable newDrawable = constantState.newDrawable();
        if (this.ad.isEnabled()) {
            HwBottomNavigationView.BottomNavigationItemView bottomNavigationItemView = this.ad;
            ejf_(bottomNavigationItemView, bottomNavigationItemView.getContent().getText().toString(), newDrawable);
        }
    }

    private void j() {
        post(new a());
    }

    private void h() {
        this.y = getResources().getDimensionPixelSize(R.dimen._2131361978_res_0x7f0a00ba);
        this.q = getResources().getDimensionPixelSize(R.dimen._2131361977_res_0x7f0a00b9);
        this.aa = ContextCompat.getColor(this.b, R.color._2131297482_res_0x7f0904ca);
        this.ac = true;
        this.x = new ArrayList();
        j();
    }

    private void eje_(View view) {
        HwBottomNavigationView.BottomNavigationItemView bottomNavigationItemView = this.ad;
        if (bottomNavigationItemView != null) {
            bottomNavigationItemView.setPressed(false);
            PopupWindow popupWindow = this.o;
            if (popupWindow != null) {
                popupWindow.dismiss();
                this.o = null;
            }
        }
        HwBottomNavigationView.BottomNavigationItemView bottomNavigationItemView2 = (HwBottomNavigationView.BottomNavigationItemView) view;
        this.ad = bottomNavigationItemView2;
        if (this.o == null) {
            bottomNavigationItemView2.setPressed(true);
            i();
            this.ab = this.v;
        }
    }

    private void e(float f, float f2) {
        if (smp.egC_(this.w, f, f2)) {
            return;
        }
        setLongClickEnable(false);
        HwBottomNavigationView.BottomNavigationItemView bottomNavigationItemView = this.ad;
        if (bottomNavigationItemView != null) {
            bottomNavigationItemView.setPressed(false);
        }
        PopupWindow popupWindow = this.o;
        if (popupWindow != null) {
            popupWindow.dismiss();
            this.o = null;
            this.p = null;
            this.r = null;
            this.s = null;
        }
    }

    private void ejf_(HwBottomNavigationView.BottomNavigationItemView bottomNavigationItemView, String str, Drawable drawable) {
        if (drawable == null) {
            return;
        }
        if (Float.compare(smp.a(this.b), 3.2f) >= 0) {
            this.u = this.q;
        } else {
            this.u = this.y;
        }
        if (this.o == null) {
            View inflate = LayoutInflater.from(this.b).inflate(R.layout.hwbottomnavigationview_auxiliary_popup_layout, (ViewGroup) null, false);
            this.t = inflate;
            this.p = (TextView) inflate.findViewById(R.id.popup_window_text_view);
            this.r = (ImageView) this.t.findViewById(R.id.popup_window_image_view);
            this.s = this.t.findViewById(R.id.popup_window_content);
            if (this.r != null && this.p != null) {
                ejd_(drawable);
                if (str != null) {
                    d(bottomNavigationItemView, str);
                }
            }
            this.o = new PopupWindow(this.t, this.u, -2);
            m();
            this.o.showAtLocation(getRootView(), 17, 0, 0);
        }
    }

    private void ejd_(Drawable drawable) {
        if (g()) {
            drawable.setTint(this.aa);
        }
        this.r.setImageDrawable(drawable);
    }

    private void d(HwBottomNavigationView.BottomNavigationItemView bottomNavigationItemView, String str) {
        if (bottomNavigationItemView.d()) {
            this.p.setVisibility(8);
            ViewGroup.LayoutParams layoutParams = this.r.getLayoutParams();
            if (layoutParams instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                layoutParams2.addRule(15);
                this.s.setLayoutParams(layoutParams2);
                return;
            }
            return;
        }
        this.p.setText(str);
        this.p.setVisibility(0);
    }
}
