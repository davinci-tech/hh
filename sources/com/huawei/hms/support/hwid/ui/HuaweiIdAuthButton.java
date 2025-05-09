package com.huawei.hms.support.hwid.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;

/* loaded from: classes9.dex */
public class HuaweiIdAuthButton extends RelativeLayout {
    public static final int COLOR_POLICY_BLACK = 3;
    public static final int COLOR_POLICY_GRAY = 4;
    public static final int COLOR_POLICY_RED = 0;
    public static final int COLOR_POLICY_WHITE = 1;
    public static final int COLOR_POLICY_WHITE_WITH_BORDER = 2;
    public static final int CORNER_RADIUS_LARGE = -1;
    public static final int CORNER_RADIUS_MEDIUM = -2;
    public static final int CORNER_RADIUS_SMALL = -3;
    public static final int THEME_FULL_TITLE = 1;
    public static final int THEME_NO_TITLE = 0;

    /* renamed from: a, reason: collision with root package name */
    private static final PorterDuffColorFilter f6031a = new PorterDuffColorFilter(218103808, PorterDuff.Mode.SRC_ATOP);
    private static final PorterDuffColorFilter b = new PorterDuffColorFilter(436207616, PorterDuff.Mode.SRC_ATOP);
    private static final PorterDuffColorFilter c = new PorterDuffColorFilter(872415231, PorterDuff.Mode.SRC_ATOP);
    private int d;
    private int e;
    private int f;
    private GradientDrawable g;
    private Button h;
    private ImageView i;
    private LinearLayout j;

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public HuaweiIdAuthButton(Context context) {
        super(context);
        a((AttributeSet) null);
    }

    public HuaweiIdAuthButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
    }

    public HuaweiIdAuthButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x001e, code lost:
    
        if (r0 != 4) goto L26;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            int r0 = r4.getAction()
            r1 = 1
            if (r0 != 0) goto L39
            android.graphics.drawable.GradientDrawable r0 = r3.g
            if (r0 == 0) goto L46
            boolean r0 = r3.isEnabled()
            if (r0 == 0) goto L46
            int r0 = r3.e
            if (r0 == 0) goto L31
            if (r0 == r1) goto L29
            r2 = 2
            if (r0 == r2) goto L29
            r2 = 3
            if (r0 == r2) goto L21
            r2 = 4
            if (r0 == r2) goto L29
            goto L46
        L21:
            android.graphics.drawable.GradientDrawable r0 = r3.g
            android.graphics.PorterDuffColorFilter r2 = com.huawei.hms.support.hwid.ui.HuaweiIdAuthButton.c
            r0.setColorFilter(r2)
            goto L46
        L29:
            android.graphics.drawable.GradientDrawable r0 = r3.g
            android.graphics.PorterDuffColorFilter r2 = com.huawei.hms.support.hwid.ui.HuaweiIdAuthButton.f6031a
            r0.setColorFilter(r2)
            goto L46
        L31:
            android.graphics.drawable.GradientDrawable r0 = r3.g
            android.graphics.PorterDuffColorFilter r2 = com.huawei.hms.support.hwid.ui.HuaweiIdAuthButton.b
            r0.setColorFilter(r2)
            goto L46
        L39:
            int r0 = r4.getAction()
            if (r1 != r0) goto L46
            android.graphics.drawable.GradientDrawable r0 = r3.g
            if (r0 == 0) goto L46
            r0.clearColorFilter()
        L46:
            boolean r0 = r3.hasOnClickListeners()
            if (r0 == 0) goto L51
            boolean r4 = super.onTouchEvent(r4)
            return r4
        L51:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.support.hwid.ui.HuaweiIdAuthButton.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void a(AttributeSet attributeSet) {
        GradientDrawable gradientDrawable = (GradientDrawable) getResources().getDrawable(R.drawable.hwid_auth_button_background);
        this.g = gradientDrawable;
        setBackground(gradientDrawable);
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.j = linearLayout;
        int i = 0;
        linearLayout.setOrientation(0);
        this.j.setGravity(17);
        b();
        a();
        addView(this.j);
        int a2 = a(8.0f);
        int i2 = 1;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100607_res_0x7f0603bf, R.attr._2131100608_res_0x7f0603c0, R.attr._2131100609_res_0x7f0603c1});
            int i3 = obtainStyledAttributes.getInt(0, 1);
            i = obtainStyledAttributes.getInt(1, 0);
            int layoutDimension = obtainStyledAttributes.getLayoutDimension(2, a(8.0f));
            obtainStyledAttributes.recycle();
            i2 = i3;
            a2 = layoutDimension;
        }
        setLayoutParams(generateDefaultLayoutParams());
        setUIMode(i2, i, a2);
    }

    private void a() {
        if (this.i == null) {
            this.i = new ImageView(getContext());
        }
        this.i.setImageDrawable(getResources().getDrawable(R.drawable._2131429225_res_0x7f0b0769));
        setBackgroundDrawableColor(R.color._2131298388_res_0x7f090854);
        this.j.addView(this.i);
    }

    private void b() {
        if (this.h == null) {
            this.h = new Button(getContext());
        }
        this.h.setStateListAnimator(null);
        this.h.setBackground(null);
        int intrinsicWidth = a(R.drawable._2131429227_res_0x7f0b076b).getIntrinsicWidth();
        int a2 = a(8.0f);
        this.h.setCompoundDrawablePadding(a2);
        a(intrinsicWidth, a2);
        this.h.setLayoutParams(new ViewGroup.LayoutParams(-2, a(36.0f)));
        this.j.addView(this.h);
    }

    private void a(int i, int i2) {
        this.h.setTextSize(16.0f);
        this.h.setText(getResources().getText(R.string._2130851241_res_0x7f0235a9));
        this.h.setSingleLine();
        int i3 = getResources().getDisplayMetrics().widthPixels;
        int a2 = a(16.0f);
        int i4 = a2 + a2;
        int i5 = i3 - i4;
        this.h.setMaxWidth(i5);
        float measureText = this.h.getPaint().measureText(this.h.getText().toString());
        while (((int) measureText) + i + i2 + i4 > i5 && this.h.getTextSize() > a(9.0f)) {
            Button button = this.h;
            button.setTextSize(0, button.getTextSize() - 1.0f);
            measureText = this.h.getPaint().measureText(this.h.getText().toString());
        }
        this.h.setEllipsize(TextUtils.TruncateAt.END);
        this.h.setPadding(a2, 0, a2, 0);
    }

    private Drawable a(int i) {
        Drawable drawable = getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.h.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        return drawable;
    }

    public int getTheme() {
        return this.d;
    }

    public int getColorPolicy() {
        return this.e;
    }

    public int getCornerRadius() {
        return this.f;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (!z) {
            setAlpha(0.38f);
        } else {
            setAlpha(1.0f);
        }
    }

    public void setTheme(int i) {
        if (i == 0) {
            this.d = i;
            this.i.setVisibility(0);
            this.h.setVisibility(8);
            setMinimumWidth(a(48.0f));
            setMinimumHeight(a(48.0f));
            this.j.setMinimumWidth(a(48.0f));
            this.j.setMinimumHeight(a(48.0f));
            return;
        }
        if (i != 1) {
            return;
        }
        this.d = i;
        this.i.setVisibility(8);
        this.h.setVisibility(0);
        setMinimumWidth(a(200.0f));
        setMinimumHeight(a(36.0f));
        this.j.setMinimumWidth(a(200.0f));
        this.j.setMinimumHeight(a(36.0f));
    }

    public void setColorPolicy(int i) {
        if (i == 0) {
            a(i, R.color._2131298385_res_0x7f090851, R.color._2131298387_res_0x7f090853, R.drawable._2131429227_res_0x7f0b076b);
            this.i.setImageDrawable(getResources().getDrawable(R.drawable._2131429226_res_0x7f0b076a));
            return;
        }
        if (i == 1) {
            a(i, R.color._2131298388_res_0x7f090854, R.color._2131298386_res_0x7f090852, R.drawable._2131429223_res_0x7f0b0767);
            this.i.setImageDrawable(getResources().getDrawable(R.drawable._2131429225_res_0x7f0b0769));
            return;
        }
        if (i == 2) {
            a(i, R.color._2131298388_res_0x7f090854, R.color._2131298386_res_0x7f090852, R.drawable._2131429223_res_0x7f0b0767);
            if (this.d == 1) {
                b(a(1.0f), getResources().getColor(R.color._2131298383_res_0x7f09084f));
                return;
            }
            return;
        }
        if (i == 3) {
            a(i, R.color._2131298382_res_0x7f09084e, R.color._2131298387_res_0x7f090853, R.drawable._2131429227_res_0x7f0b076b);
            this.i.setImageDrawable(getResources().getDrawable(R.drawable._2131429226_res_0x7f0b076a));
        } else {
            if (i != 4) {
                return;
            }
            a(i, R.color._2131298384_res_0x7f090850, R.color._2131298386_res_0x7f090852, R.drawable._2131429223_res_0x7f0b0767);
        }
    }

    private void a(int i, int i2, int i3, int i4) {
        this.e = i;
        if (this.d == 1 || i == 0 || i == 3 || i == 1) {
            setBackgroundDrawableColor(i2);
        } else {
            setBackgroundDrawableColor(R.color._2131298388_res_0x7f090854);
            this.i.setImageDrawable(getResources().getDrawable(R.drawable._2131429225_res_0x7f0b0769));
        }
        b(0, 0);
        this.h.setTextColor(getResources().getColor(i3));
        a(i4);
    }

    public void setCornerRadius(int i) {
        if (i == -3) {
            i = a(3.0f);
        } else if (i == -2) {
            i = a(8.0f);
        } else if (i == -1) {
            i = a(24.0f);
        }
        if (i < 0) {
            return;
        }
        this.f = i;
        setBackgroundCornerRadius(i);
    }

    private void setBackgroundCornerRadius(int i) {
        this.g.mutate();
        this.g.setCornerRadius(i);
    }

    private void setBackgroundDrawableColor(int i) {
        this.g.mutate();
        this.g.setColor(getResources().getColor(i));
    }

    private void b(int i, int i2) {
        this.g.mutate();
        this.g.setStroke(i, i2);
    }

    public void setUIMode(int i, int i2, int i3) {
        setTheme(i);
        setColorPolicy(i2);
        setCornerRadius(i3);
    }

    private int a(float f) {
        return (int) ((f * getResources().getDisplayMetrics().density) + 0.5f);
    }
}
