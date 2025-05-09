package com.huawei.uikit.hwprogressbutton.widget;

import android.R;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.huawei.uikit.hwprogressbar.widget.HwProgressBar;
import com.huawei.uikit.hwtextview.widget.HwTextView;
import defpackage.sjx;
import defpackage.skc;
import defpackage.slc;
import defpackage.smp;
import defpackage.smr;
import java.lang.reflect.Method;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

/* loaded from: classes7.dex */
public class HwProgressButton extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private static Method f10680a;
    private static final int c = e();
    private float b;
    private float d;
    private Drawable e;
    private float f;
    private int g;
    private float h;
    private float i;
    private ObjectAnimator j;
    private int k;
    private Context l;
    private int m;
    private HwTextView n;
    private HwProgressBar o;
    private ColorStateList p;
    private ColorStateList q;
    private String r;
    private Drawable s;
    private Drawable t;
    private NumberFormat u;
    private LayerDrawable w;
    private boolean x;
    private Locale y;

    static class a extends View.BaseSavedState {
        public static final Parcelable.Creator<a> CREATOR = new b();

        /* renamed from: a, reason: collision with root package name */
        int f10681a;
        int b;

        static final class b implements Parcelable.Creator<a> {
            b() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public a[] newArray(int i) {
                return new a[i];
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: eea_, reason: merged with bridge method [inline-methods] */
            public a createFromParcel(Parcel parcel) {
                return new a(parcel, null);
            }
        }

        /* synthetic */ a(Parcel parcel, d dVar) {
            this(parcel);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (parcel == null) {
                Log.w("HwProgressButton", "writeToParcel, parcel is null");
                return;
            }
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f10681a);
            parcel.writeInt(this.b);
        }

        a(Parcelable parcelable) {
            super(parcelable);
        }

        private a(Parcel parcel) {
            super(parcel);
            this.f10681a = parcel.readInt();
            this.b = parcel.readInt();
        }
    }

    class d implements HwProgressBar.OnVisualProgressChangedListener {
        d() {
        }

        @Override // com.huawei.uikit.hwprogressbar.widget.HwProgressBar.OnVisualProgressChangedListener
        public void onVisualProgressChanged(HwProgressBar hwProgressBar, float f) {
            HwProgressButton.this.k = (int) (f * 100.0d);
            HwProgressButton hwProgressButton = HwProgressButton.this;
            hwProgressButton.setPercentage(hwProgressButton.k);
        }
    }

    public HwProgressButton(Context context) {
        this(context, null);
    }

    protected static int e() {
        if (f10680a == null) {
            f10680a = slc.b("getInt", new Class[]{String.class, Integer.TYPE}, "android.os.SystemProperties");
        }
        Method method = f10680a;
        if (method == null) {
            return 200;
        }
        Object c2 = slc.c((Object) null, method, new Object[]{"hw_sc.platform.ux_level", 200});
        if (c2 instanceof Integer) {
            return ((Integer) c2).intValue();
        }
        return 200;
    }

    private int getCurSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPercentage(int i) {
        HwTextView hwTextView = this.n;
        if (hwTextView == null) {
            Log.w("HwProgressButton", "setPercentage, mPercentage is null");
            return;
        }
        if (this.m == 2) {
            hwTextView.setText(this.r);
            return;
        }
        if (this.j != null) {
            i = this.k;
        }
        if (this.u == null) {
            a();
        }
        NumberFormat numberFormat = this.u;
        if (numberFormat != null) {
            synchronized (numberFormat) {
                this.n.setText(this.u.format(i / 100.0d));
            }
            return;
        }
        this.n.setText(String.format(Locale.ROOT, "%2d", Integer.valueOf(i)) + "%");
    }

    private void setPercentageTextColor(ColorStateList colorStateList) {
        HwTextView hwTextView = this.n;
        if (hwTextView == null || colorStateList == null) {
            return;
        }
        hwTextView.setTextColor(colorStateList);
    }

    public void b() {
        setState(2);
        setPercentage(this.o.getProgress());
        f();
    }

    protected void c() {
        HwProgressBar hwProgressBar = this.o;
        if (hwProgressBar == null) {
            Log.w("HwProgressButton", "incrementProgressBy, mProgressBar is null");
        } else {
            hwProgressBar.setProgressDrawable(this.s);
        }
    }

    public void c(int i) {
        if (this.o == null) {
            Log.w("HwProgressButton", "incrementProgressBy, mProgressBar is null");
            return;
        }
        if (this.m != 1) {
            setState(1);
            this.o.setBackground(null);
            c();
            setPercentageTextColor(this.q);
        }
        this.o.incrementProgressBy(i);
        setPercentage(this.o.getProgress());
    }

    public void d() {
        this.o.setBackground(null);
        if (this.x) {
            if (this.w == null) {
                a(getContext());
            }
            this.o.setProgressDrawable(this.w);
        } else {
            Drawable drawable = this.t;
            if (drawable == null || drawable.getConstantState() == null) {
                this.o.setProgressDrawable(this.t);
            } else {
                this.o.setProgressDrawable(this.t.getConstantState().newDrawable());
            }
        }
        setPercentageTextColor(this.p);
        setState(0);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        edV_(canvas);
    }

    protected Drawable edW_(int i) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), i);
        if (c >= 200) {
            edY_(drawable);
        }
        return drawable;
    }

    protected void edY_(Drawable drawable) {
        if (Build.VERSION.SDK_INT < 29 || !smp.b(this.l) || Float.compare(smp.a(this.l), 1.75f) < 0) {
            if (!(drawable instanceof LayerDrawable)) {
                Log.e("HwProgressButton", "drawable is not LayerDrawable");
                return;
            }
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            Drawable findDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.progress);
            if (findDrawableByLayerId instanceof ClipDrawable) {
                skc skcVar = new skc(findDrawableByLayerId, 0, 1);
                drawable.mutate();
                layerDrawable.setDrawableByLayerId(R.id.progress, skcVar);
            }
        }
    }

    protected void eji_(Canvas canvas) {
    }

    public float getCornerRadius() {
        return this.i;
    }

    public Drawable getFocusedDrawable() {
        return this.e;
    }

    public int getFocusedPathColor() {
        return this.g;
    }

    public float getFocusedPathPadding() {
        return this.h;
    }

    public float getFocusedPathWidth() {
        return this.d;
    }

    public HwTextView getPercentage() {
        return this.n;
    }

    public int getProgress() {
        return this.o.getProgress();
    }

    public ProgressBar getProgressBar() {
        return this.o;
    }

    public Drawable getProgressBarBackgroundDrawable() {
        return this.s;
    }

    public Drawable getProgressButtonBackgroundDrawable() {
        return this.t;
    }

    public int getState() {
        return this.m;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a();
    }

    @Override // android.view.View
    public void onHoverChanged(boolean z) {
        super.onHoverChanged(z);
        if (this.b == 1.0f) {
            return;
        }
        e(z);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), i2);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        HwProgressBar hwProgressBar;
        if (!(parcelable instanceof a)) {
            Log.w("HwProgressButton", "onRestoreInstanceState, state = " + parcelable);
            return;
        }
        a aVar = (a) parcelable;
        super.onRestoreInstanceState(aVar.getSuperState());
        setState(aVar.b);
        if (this.m != 0 && (hwProgressBar = this.o) != null) {
            hwProgressBar.setProgressDrawable(edW_(com.huawei.health.R.drawable._2131429273_res_0x7f0b0799));
            setProgress(aVar.f10681a);
            setPercentage(aVar.f10681a);
            setPercentageTextColor(this.q);
            return;
        }
        Log.w("HwProgressButton", "onRestoreInstanceState mState = " + this.m + " , mProgressBar = " + this.o);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        a aVar = new a(super.onSaveInstanceState());
        aVar.f10681a = this.o.getProgress();
        aVar.b = this.m;
        return aVar;
    }

    public void setCornerRadius(float f) {
        this.i = f;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            this.o.setAlpha(1.0f);
            this.n.setAlpha(1.0f);
        } else {
            this.o.setAlpha(this.f);
            this.n.setAlpha(this.f);
        }
    }

    public void setFocusedDrawable(Drawable drawable) {
        this.e = drawable;
    }

    public void setFocusedPathColor(int i) {
        this.g = i;
    }

    public void setFocusedPathPadding(float f) {
        this.h = f;
    }

    public void setFocusedPathWidth(float f) {
        this.d = f;
    }

    public void setHwProgressButtonTextColor(ColorStateList colorStateList) {
    }

    public void setIdleText(String str) {
        if (str == null) {
            Log.w("HwProgressButton", "setIdleText, idleText is null");
        } else {
            if (this.m == 0) {
                this.n.setText(str);
                return;
            }
            Log.w("HwProgressButton", "setIdleText, mState = " + this.m);
        }
    }

    public void setPauseText(String str) {
        if (str == null) {
            Log.w("HwProgressButton", "setPauseText, pauseText is null");
        } else {
            this.r = str;
        }
    }

    public void setProgress(int i) {
        this.o.setProgress(i);
    }

    public void setProgressBarBackgroundDrawable(Drawable drawable) {
        this.s = drawable;
        if (c >= 200) {
            edY_(drawable);
        }
    }

    public void setProgressButtonBackgroundDrawable(Drawable drawable) {
        this.t = drawable;
        HwProgressBar hwProgressBar = this.o;
        if (hwProgressBar != null) {
            hwProgressBar.setProgressDrawable(drawable);
        }
    }

    protected void setState(int i) {
        if (this.m == i) {
            return;
        }
        if (i >= 0 && i <= 2) {
            this.m = i;
            return;
        }
        Log.w("HwProgressButton", "setState: invalid state: " + i);
    }

    public HwProgressButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.huawei.health.R.attr._2131100468_res_0x7f060334);
    }

    private void f() {
        Object b = slc.b(this.o, "mVisualProgressUpdateAnimator", (Class<?>) HwProgressBar.class);
        if (b instanceof ObjectAnimator) {
            this.j = (ObjectAnimator) b;
        }
        int floatValue = slc.b(this.o, "mVisualProgress", (Class<?>) HwProgressBar.class) instanceof Float ? (int) (((Float) r0).floatValue() * 100.0d) : 0;
        ObjectAnimator objectAnimator = this.j;
        if (objectAnimator == null || !objectAnimator.isRunning()) {
            return;
        }
        this.j.cancel();
        setPercentage(floatValue);
    }

    private void i() {
        if (isEnabled()) {
            return;
        }
        this.o.setAlpha(this.f);
        this.n.setAlpha(this.f);
    }

    public void setProgress(int i, boolean z) {
        if (this.o == null) {
            Log.w("HwProgressButton", "setProgress, mProgressBar is null");
            return;
        }
        if (this.m != 1) {
            setState(1);
            this.o.setBackground(null);
            this.o.setProgressDrawable(this.s);
            setPercentageTextColor(this.q);
        }
        if (z) {
            this.o.setProgress(i, true);
        } else {
            this.o.setProgress(i);
            setPercentage(this.o.getProgress());
        }
    }

    public HwProgressButton(Context context, AttributeSet attributeSet, int i) {
        super(d(context, i), attributeSet, i);
        this.o = null;
        this.n = null;
        this.m = 0;
        this.x = true;
        this.b = 1.0f;
        this.k = 0;
        this.l = context;
        edU_(super.getContext(), attributeSet, com.huawei.health.R.layout.hwprogressbutton_layout);
    }

    private static Context d(Context context, int i) {
        return smr.b(context, i, com.huawei.health.R.style.Theme_Emui_HwProgressButton);
    }

    private void edU_(Context context, AttributeSet attributeSet, int i) {
        if (getCurSDKVersion() >= 26) {
            setDefaultFocusHighlightEnabled(false);
        }
        Object systemService = getContext().getSystemService("layout_inflater");
        if (systemService instanceof LayoutInflater) {
            ((LayoutInflater) systemService).inflate(i, (ViewGroup) this, true);
        }
        this.o = (HwProgressBar) findViewById(com.huawei.health.R.id.hwprogressbutton_progress_bar);
        HwTextView hwTextView = (HwTextView) findViewById(com.huawei.health.R.id.hwprogressbutton_percentage_text_view);
        this.n = hwTextView;
        HwProgressBar hwProgressBar = this.o;
        if (hwProgressBar != null && hwTextView != null) {
            hwProgressBar.setOnVisualProgressChangedListener(new d());
            this.r = "";
            this.f = b(com.huawei.health.R.dimen._2131362645_res_0x7f0a0355);
            edT_(context, attributeSet);
            i();
            setFocusable(true);
            return;
        }
        Log.e("HwProgressButton", "init: mProgressBar is " + this.o + " mPercentage is " + this.n + " layoutResId: " + i);
    }

    private void a(Context context) {
        Drawable drawable = ContextCompat.getDrawable(context, com.huawei.health.R.drawable._2131429281_res_0x7f0b07a1);
        Drawable mutate = DrawableCompat.wrap(ContextCompat.getDrawable(context, com.huawei.health.R.drawable._2131429280_res_0x7f0b07a0)).mutate();
        DrawableCompat.setTint(mutate, ContextCompat.getColor(context, com.huawei.health.R.color._2131298422_res_0x7f090876));
        this.w = new LayerDrawable(new Drawable[]{mutate, drawable});
    }

    private void edT_(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes;
        Resources.Theme theme = context.getTheme();
        if (theme == null || (obtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, new int[]{com.huawei.health.R.attr._2131100334_res_0x7f0602ae, com.huawei.health.R.attr._2131100335_res_0x7f0602af, com.huawei.health.R.attr._2131100336_res_0x7f0602b0, com.huawei.health.R.attr._2131100338_res_0x7f0602b2, com.huawei.health.R.attr._2131100339_res_0x7f0602b3, com.huawei.health.R.attr._2131100340_res_0x7f0602b4, com.huawei.health.R.attr._2131100341_res_0x7f0602b5, com.huawei.health.R.attr._2131100362_res_0x7f0602ca, com.huawei.health.R.attr._2131100455_res_0x7f060327, com.huawei.health.R.attr._2131100456_res_0x7f060328, com.huawei.health.R.attr._2131100457_res_0x7f060329, com.huawei.health.R.attr._2131100458_res_0x7f06032a, com.huawei.health.R.attr._2131100459_res_0x7f06032b, com.huawei.health.R.attr._2131100460_res_0x7f06032c, com.huawei.health.R.attr._2131100462_res_0x7f06032e, com.huawei.health.R.attr._2131100463_res_0x7f06032f, com.huawei.health.R.attr._2131100464_res_0x7f060330, com.huawei.health.R.attr._2131100465_res_0x7f060331, com.huawei.health.R.attr._2131100466_res_0x7f060332, com.huawei.health.R.attr._2131100467_res_0x7f060333}, com.huawei.health.R.attr._2131100468_res_0x7f060334, com.huawei.health.R.style.Widget_Emui_HwProgressButton_Normal_Translucent)) == null) {
            return;
        }
        this.t = obtainStyledAttributes.getDrawable(8);
        Drawable drawable = obtainStyledAttributes.getDrawable(17);
        this.s = drawable;
        if (c >= 200) {
            edY_(drawable);
        }
        this.o.setProgressDrawable(this.t);
        boolean z = obtainStyledAttributes.getBoolean(16, true);
        this.x = z;
        if (z) {
            a(context);
            this.o.setProgressDrawable(this.w);
        }
        this.p = obtainStyledAttributes.getColorStateList(18);
        this.q = obtainStyledAttributes.getColorStateList(15);
        setPercentageTextColor(this.p);
        this.e = obtainStyledAttributes.getDrawable(0);
        this.b = obtainStyledAttributes.getFloat(7, 1.0f);
        this.g = obtainStyledAttributes.getColor(3, 0);
        this.h = obtainStyledAttributes.getDimension(4, 0.0f);
        this.d = obtainStyledAttributes.getDimension(5, 0.0f);
        this.i = obtainStyledAttributes.getDimension(14, 0.0f);
        obtainStyledAttributes.recycle();
    }

    private float b(int i) {
        TypedValue typedValue = new TypedValue();
        getResources().getValue(i, typedValue, true);
        return typedValue.getFloat();
    }

    private void edV_(Canvas canvas) {
        if (isFocusable() && hasWindowFocus() && hasFocus()) {
            eji_(canvas);
        }
    }

    private void e(boolean z) {
        sjx.dYl_(this, z ? this.b : 1.0f).start();
    }

    private void a() {
        Locale locale = this.y;
        if (locale == null || !locale.equals(Locale.getDefault())) {
            Locale locale2 = Locale.getDefault();
            this.y = locale2;
            if (locale2 != null) {
                this.u = NumberFormat.getPercentInstance(locale2);
            } else {
                this.u = NumberFormat.getPercentInstance();
            }
            NumberFormat numberFormat = this.u;
            if (numberFormat != null) {
                numberFormat.setRoundingMode(RoundingMode.DOWN);
                this.u.setMaximumFractionDigits(0);
            }
        }
    }
}
