package com.huawei.uikit.hwbutton.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.uikit.hwbutton.R$dimen;
import com.huawei.uikit.hwprogressbar.widget.HwProgressBar;
import com.huawei.uikit.hwtextview.widget.HwTextView;
import defpackage.sjx;
import defpackage.skg;
import defpackage.smp;
import defpackage.smr;
import java.util.Locale;

/* loaded from: classes7.dex */
public class HwButton extends HwTextView {

    /* renamed from: a, reason: collision with root package name */
    private int f10609a;
    private float aa;
    private String ad;
    private boolean b;
    private int c;
    private int d;
    private int e;
    private Drawable f;
    private ColorStateList g;
    private Drawable h;
    private Drawable i;
    private Drawable j;
    private boolean k;
    private Rect l;
    private int[] m;
    private int n;
    private int o;
    private int p;
    private Drawable q;
    private HwProgressBar r;
    private int s;
    private float t;
    private int u;
    private String v;
    private int w;
    private int x;
    private int y;
    private boolean z;

    public HwButton(Context context) {
        this(context, null);
    }

    private static Context a(Context context, int i) {
        return smr.b(context, i, 2131952132);
    }

    private void a() {
        if (this.ad == null) {
            int i = this.d;
            int i2 = this.n;
            setPadding(i, i2, this.f10609a, i2);
        } else {
            if (getLayoutDirection() != 1) {
                int i3 = this.d;
                int iconSize = getIconSize();
                int waitingDrawablePadding = getWaitingDrawablePadding();
                int i4 = this.n;
                setPadding(i3 + iconSize + waitingDrawablePadding, i4, this.f10609a, i4);
                return;
            }
            int i5 = this.d;
            int i6 = this.n;
            int i7 = this.f10609a;
            int iconSize2 = getIconSize();
            setPadding(i5, i6, i7 + iconSize2 + getWaitingDrawablePadding(), this.n);
        }
    }

    protected static int c(int i) {
        return (int) TypedValue.applyDimension(1, i, Resources.getSystem().getDisplayMetrics());
    }

    private void d() {
        int i;
        int i2;
        int i3;
        int i4;
        if (this.z) {
            if (this.r == null) {
                HwProgressBar b = HwProgressBar.b(getContext());
                this.r = b;
                if (b == null) {
                    Log.e("HwButton", "createProgressbar: HwProgressBar instantiate null!");
                    return;
                }
            }
            c();
            ViewParent parent = getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup == null) {
                Log.w("HwButton", "HwButton::create progressbar fail");
                return;
            }
            int[] iArr = new int[2];
            int[] iArr2 = new int[2];
            getLocationOnScreen(iArr);
            viewGroup.getLocationOnScreen(iArr2);
            boolean z = this.k;
            if (z) {
                i = this.m[0];
                i2 = iArr2[0];
            } else {
                i = iArr[0];
                i2 = iArr2[0];
            }
            int i5 = i - i2;
            if (z) {
                i3 = this.m[1];
                i4 = iArr2[1];
            } else {
                i3 = iArr[1];
                i4 = iArr2[1];
            }
            int width = this.l.width();
            if (getLayoutDirection() == 1) {
                i5 += width;
            }
            this.r.offsetLeftAndRight(i5);
            this.r.offsetTopAndBottom(i3 - i4);
            Drawable indeterminateDrawable = this.r.getIndeterminateDrawable();
            if (indeterminateDrawable instanceof skg) {
                this.r.setAlpha(Color.alpha(this.e) > 0 ? (Color.alpha(this.e) * 1.0f) / 255.0f : 1.0f);
                ((skg) indeterminateDrawable).d(this.e);
            }
            viewGroup.getOverlay().add(this.r);
        }
    }

    private void e() {
        ViewGroup viewGroup;
        HwProgressBar hwProgressBar = this.r;
        if (hwProgressBar != null) {
            ViewParent parent = hwProgressBar.getParent();
            if ((parent instanceof ViewGroup) && (viewGroup = (ViewGroup) parent) != null) {
                viewGroup.removeView(this.r);
            }
            this.r = null;
        }
    }

    private void eaJ_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100190_res_0x7f06021e, R.attr._2131100222_res_0x7f06023e, R.attr._2131100224_res_0x7f060240, R.attr._2131100225_res_0x7f060241, R.attr._2131100226_res_0x7f060242, R.attr._2131100227_res_0x7f060243, R.attr._2131100228_res_0x7f060244, R.attr._2131100229_res_0x7f060245, R.attr._2131100255_res_0x7f06025f, R.attr._2131100334_res_0x7f0602ae, R.attr._2131100335_res_0x7f0602af, R.attr._2131100336_res_0x7f0602b0, R.attr._2131100338_res_0x7f0602b2, R.attr._2131100339_res_0x7f0602b3, R.attr._2131100341_res_0x7f0602b5, R.attr._2131100362_res_0x7f0602ca}, i, 0);
        this.t = obtainStyledAttributes.getFloat(15, 1.0f);
        this.q = obtainStyledAttributes.getDrawable(9);
        this.p = obtainStyledAttributes.getDimensionPixelSize(7, 0);
        this.s = obtainStyledAttributes.getDimensionPixelSize(13, 0);
        this.c = obtainStyledAttributes.getColor(6, 0);
        this.e = obtainStyledAttributes.getColor(3, 0);
        this.x = obtainStyledAttributes.getDimensionPixelSize(5, c(24));
        this.u = obtainStyledAttributes.getDimensionPixelSize(4, c(8));
        this.o = obtainStyledAttributes.getColor(12, 0);
        boolean z = obtainStyledAttributes.getBoolean(0, true);
        if (smp.b(context) && z && Float.compare(smp.a(context), 1.75f) >= 0) {
            int i2 = obtainStyledAttributes.getInt(2, 0);
            if (i2 == 0) {
                this.n = getResources().getDimensionPixelSize(R$dimen.hwbutton_big_padding_top_or_bottom);
            }
            if (i2 == 1) {
                this.n = getResources().getDimensionPixelSize(R$dimen.hwbutton_small_padding_top_or_bottom);
            }
            setMaxLines(2);
        }
        this.aa = getTextSize();
        obtainStyledAttributes.recycle();
        setDefaultFocusHighlightEnabled(false);
    }

    private void setOriDrawableVisible(boolean z) {
        if (z) {
            setCompoundDrawables(this.j, this.i, this.h, this.f);
            return;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        if (compoundDrawables.length > 3) {
            this.j = compoundDrawables[0];
            this.i = compoundDrawables[1];
            this.h = compoundDrawables[2];
            this.f = compoundDrawables[3];
        }
        setCompoundDrawables(null, null, null, null);
    }

    protected int e(int i, int i2) {
        int i3;
        int iconSize;
        int width = getWidth();
        if (getLayoutDirection() != 1) {
            return i > width ? this.d : (width / 2) - (i2 / 2);
        }
        if (i > width) {
            i3 = 0 - this.f10609a;
            iconSize = getIconSize();
        } else {
            i3 = 0 - ((width / 2) - (i2 / 2));
            iconSize = getIconSize();
        }
        return i3 - iconSize;
    }

    protected void e(boolean z, int i, int i2) {
        if (z) {
            setEnabled(false);
        } else {
            setEnabled(this.b);
        }
    }

    public int getFocusPathColor() {
        return this.o;
    }

    public Drawable getFocusedDrawable() {
        return this.q;
    }

    public int getFocusedPathPadding() {
        return this.s;
    }

    public int getFocusedPathWidth() {
        return this.p;
    }

    public float getHoveredZoomScale() {
        return this.t;
    }

    public int getIconSize() {
        return this.x;
    }

    public int getWaitingDrawablePadding() {
        return this.u;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        e();
    }

    @Override // android.view.View
    public void onHoverChanged(boolean z) {
        super.onHoverChanged(z);
        if (this.t == 1.0f) {
            return;
        }
        b(z);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return;
        }
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.z) {
            a();
            d();
        }
    }

    public void setClickAnimationEnabled(boolean z) {
    }

    public void setClickSelf(boolean z) {
        this.k = z;
    }

    public void setFocusPathColor(int i) {
        this.o = i;
    }

    public void setFocusedDrawable(Drawable drawable) {
        this.q = drawable;
    }

    public void setFocusedPathPadding(int i) {
        this.s = i;
    }

    public void setFocusedPathWidth(int i) {
        this.p = i;
    }

    public void setHoveredZoomScale(float f) {
        this.t = f;
    }

    public void setIconSize(int i) {
        this.x = i;
    }

    protected void setParentLocation(int[] iArr) {
        if (iArr == null) {
            return;
        }
        this.m = iArr;
    }

    @Override // com.huawei.uikit.hwtextview.widget.HwTextView, android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
        requestLayout();
        invalidate();
    }

    public void setWaitingDrawablePadding(int i) {
        this.u = i;
    }

    public void setWaitingEnable(boolean z, String str) {
        if (!z) {
            if (this.z) {
                this.ad = null;
                e();
                setOriDrawableVisible(true);
                setText(this.v);
                ColorStateList colorStateList = this.g;
                if (colorStateList != null) {
                    setTextColor(colorStateList);
                    this.g = null;
                }
                int i = this.d;
                int i2 = this.n;
                setPadding(i, i2, this.f10609a, i2);
                e(false, this.w, this.y);
                this.z = false;
                return;
            }
            return;
        }
        this.ad = str;
        if (!this.z) {
            this.d = getPaddingStart();
            this.f10609a = getPaddingEnd();
            this.g = getTextColors();
            this.w = getWidth();
            this.y = getHeight();
            this.b = isEnabled();
            this.v = getText().toString();
            setOriDrawableVisible(false);
        }
        a();
        setText(str);
        int i3 = this.c;
        if (i3 != 0) {
            setTextColor(i3);
        }
        e(true, this.w, this.y);
        this.z = true;
    }

    public HwButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100223_res_0x7f06023f);
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f) {
        if (getAutoSizeTextType() == 0) {
            this.aa = f;
        }
        super.setTextSize(i, f);
    }

    public HwButton(Context context, AttributeSet attributeSet, int i) {
        super(a(context, i), attributeSet, i);
        this.g = null;
        this.n = 0;
        this.l = new Rect();
        this.m = new int[2];
        this.k = false;
        eaJ_(super.getContext(), attributeSet, i);
    }

    private void c() {
        this.r.measure(getWidth(), getHeight());
        int b = b(this.ad);
        int i = this.d;
        int i2 = this.f10609a;
        getHitRect(this.l);
        int height = this.l.height() / 2;
        int i3 = this.x / 2;
        int e = e(i + b + i2, b);
        this.r.layout(e, height - i3, this.x + e, height + i3);
    }

    private int b(String str) {
        int i;
        if (str != null) {
            String obj = getText().toString();
            if (Build.VERSION.SDK_INT >= 28 && isAllCaps()) {
                obj = obj.toUpperCase(Locale.ENGLISH);
            }
            i = (int) getPaint().measureText(obj);
            if (getTextSize() != 0.0f && this.aa != 0.0f) {
                i = (int) ((i / getTextSize()) * this.aa);
            } else {
                Log.w("HwButton", "getButtonContentWidth: wrong para!");
            }
        } else {
            i = 0;
        }
        if (i != 0) {
            return getIconSize() + getWaitingDrawablePadding() + i;
        }
        return getIconSize();
    }

    private void b(boolean z) {
        sjx.dYl_(this, z ? this.t : 1.0f).start();
    }
}
