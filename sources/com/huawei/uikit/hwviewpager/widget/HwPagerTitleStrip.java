package com.huawei.uikit.hwviewpager.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.method.SingleLineTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import java.lang.ref.WeakReference;
import java.util.Locale;

@HwViewPager.DecorView
/* loaded from: classes9.dex */
public class HwPagerTitleStrip extends ViewGroup {

    /* renamed from: a, reason: collision with root package name */
    TextView f10776a;
    HwViewPager b;
    int c;
    TextView d;
    TextView e;
    float g;
    private int h;
    private int i;
    private WeakReference<HwPagerAdapter> k;
    private final a l;
    private int m;
    private boolean n;
    private boolean o;
    private int t;
    private static final int[] j = {R.attr.textAllCaps};
    private static final int[] f = {R.attr.textAppearance, R.attr.textSize, R.attr.textColor, R.attr.gravity};

    class a extends DataSetObserver implements HwViewPager.OnPageChangeListener, HwViewPager.OnAdapterChangeListener {
        private int b;

        a() {
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnAdapterChangeListener
        public void onAdapterChanged(HwViewPager hwViewPager, HwPagerAdapter hwPagerAdapter, HwPagerAdapter hwPagerAdapter2) {
            HwPagerTitleStrip.this.e(hwPagerAdapter, hwPagerAdapter2);
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            HwPagerTitleStrip hwPagerTitleStrip = HwPagerTitleStrip.this;
            hwPagerTitleStrip.b(hwPagerTitleStrip.b.getCurrentItem(), HwPagerTitleStrip.this.b.getAdapter());
            float f = Float.compare(HwPagerTitleStrip.this.g, 0.0f) >= 0 ? HwPagerTitleStrip.this.g : 0.0f;
            HwPagerTitleStrip hwPagerTitleStrip2 = HwPagerTitleStrip.this;
            hwPagerTitleStrip2.a(hwPagerTitleStrip2.b.getCurrentItem(), f, true);
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            this.b = i;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            if (f > 0.5f) {
                i++;
            }
            HwPagerTitleStrip.this.a(i, f, false);
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            if (this.b == 0) {
                HwPagerTitleStrip hwPagerTitleStrip = HwPagerTitleStrip.this;
                hwPagerTitleStrip.b(hwPagerTitleStrip.b.getCurrentItem(), HwPagerTitleStrip.this.b.getAdapter());
                float f = Float.compare(HwPagerTitleStrip.this.g, 0.0f) >= 0 ? HwPagerTitleStrip.this.g : 0.0f;
                HwPagerTitleStrip hwPagerTitleStrip2 = HwPagerTitleStrip.this;
                hwPagerTitleStrip2.a(hwPagerTitleStrip2.b.getCurrentItem(), f, true);
            }
        }
    }

    static class e extends SingleLineTransformationMethod {
        private Locale c;

        e(Context context) {
            this.c = context.getResources().getConfiguration().locale;
        }

        @Override // android.text.method.ReplacementTransformationMethod, android.text.method.TransformationMethod
        public CharSequence getTransformation(CharSequence charSequence, View view) {
            CharSequence transformation = super.getTransformation(charSequence, view);
            if (transformation != null) {
                return transformation.toString().toUpperCase(this.c);
            }
            return null;
        }
    }

    public HwPagerTitleStrip(Context context) {
        this(context, null);
    }

    private float c(float f2, boolean z) {
        if (z) {
            f2 = -f2;
        }
        float f3 = f2 + 0.5f;
        return z ? f3 < 0.0f ? f3 + 1.0f : f3 : f3 > 1.0f ? f3 - 1.0f : f3;
    }

    private void c(float f2, int i, int i2) {
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int measuredHeight = this.e.getMeasuredHeight();
        int i3 = measuredHeight / 2;
        float d = d(f2);
        boolean isRtlLayout = this.b.isRtlLayout();
        int i4 = ((i2 - (paddingBottom + i3)) - ((int) (((i2 - (paddingTop + i3)) - r6) * d))) - i3;
        int i5 = measuredHeight + i4;
        int max = Math.max(Math.max(this.f10776a.getMeasuredWidth(), this.e.getMeasuredWidth()), this.d.getMeasuredWidth());
        int i6 = this.m & 7;
        if (i6 == 1) {
            paddingLeft = (((i - paddingLeft) - paddingRight) - max) / 2;
        } else if (i6 == 5) {
            paddingLeft = isRtlLayout ? paddingRight : (i - paddingRight) - max;
        } else if (isRtlLayout) {
            paddingLeft = (i - max) - paddingLeft;
        }
        TextView textView = this.e;
        textView.layout(paddingLeft, i4, textView.getMeasuredWidth() + paddingLeft, i5);
        int measuredHeight2 = this.f10776a.getMeasuredHeight();
        int min = Math.min(paddingTop, (i4 - this.h) - measuredHeight2);
        TextView textView2 = this.f10776a;
        textView2.layout(paddingLeft, min, textView2.getMeasuredWidth() + paddingLeft, measuredHeight2 + min);
        int measuredHeight3 = this.d.getMeasuredHeight();
        int max2 = Math.max((i2 - paddingBottom) - measuredHeight3, i5 + this.h);
        TextView textView3 = this.d;
        textView3.layout(paddingLeft, max2, textView3.getMeasuredWidth() + paddingLeft, measuredHeight3 + max2);
    }

    private float d(float f2) {
        float f3 = f2 + 0.5f;
        return f3 > 1.0f ? f3 - 1.0f : f3;
    }

    private void e(int i) {
        setTextSize(0, i);
    }

    private static void setSingleLineAllCaps(TextView textView) {
        textView.setTransformationMethod(new e(textView.getContext()));
    }

    int getMinHeight() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicHeight();
        }
        return 0;
    }

    int getMinWidth() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicWidth();
        }
        return 0;
    }

    public int getTextSpacing() {
        return this.h;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (!(parent instanceof HwViewPager)) {
            throw new IllegalStateException("HwPagerTitleStrip must be a direct child of a ViewPager.");
        }
        HwViewPager hwViewPager = (HwViewPager) parent;
        HwPagerAdapter adapter = hwViewPager.getAdapter();
        hwViewPager.a(this.l);
        hwViewPager.addOnAdapterChangeListener(this.l);
        this.b = hwViewPager;
        if (hwViewPager.getPageScrollDirection() == 0) {
            setGravity(80);
        } else {
            setGravity(1);
        }
        WeakReference<HwPagerAdapter> weakReference = this.k;
        e(weakReference != null ? weakReference.get() : null, adapter);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        HwViewPager hwViewPager = this.b;
        if (hwViewPager != null) {
            e(hwViewPager.getAdapter(), null);
            this.b.a((HwViewPager.OnPageChangeListener) null);
            this.b.removeOnAdapterChangeListener(this.l);
            this.b = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.b != null) {
            a(this.i, Float.compare(this.g, 0.0f) >= 0 ? this.g : 0.0f, true);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        HwViewPager hwViewPager = this.b;
        if (hwViewPager == null || hwViewPager.getPageScrollDirection() == 0) {
            b(i, i2);
        } else {
            a(i, i2);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.n) {
            return;
        }
        super.requestLayout();
    }

    public void setGravity(int i) {
        this.m = i;
        requestLayout();
    }

    public void setNonPrimaryAlpha(float f2) {
        int i = ((int) (f2 * 255.0f)) & 255;
        this.t = i;
        int i2 = (i << 24) | (this.c & ViewCompat.MEASURED_SIZE_MASK);
        this.f10776a.setTextColor(i2);
        this.d.setTextColor(i2);
    }

    public void setTextColor(int i) {
        this.c = i;
        this.e.setTextColor(i);
        int i2 = (this.t << 24) | (this.c & ViewCompat.MEASURED_SIZE_MASK);
        this.f10776a.setTextColor(i2);
        this.d.setTextColor(i2);
    }

    public void setTextSize(int i, float f2) {
        this.f10776a.setTextSize(i, f2);
        this.e.setTextSize(i, f2);
        this.d.setTextSize(i, f2);
    }

    public void setTextSpacing(int i) {
        this.h = i;
        requestLayout();
    }

    public HwPagerTitleStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = -1;
        this.g = -1.0f;
        this.l = new a();
        TextView textView = new TextView(context);
        this.f10776a = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.e = textView2;
        addView(textView2);
        TextView textView3 = new TextView(context);
        this.d = textView3;
        addView(textView3);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            TextViewCompat.setTextAppearance(this.f10776a, resourceId);
            TextViewCompat.setTextAppearance(this.e, resourceId);
            TextViewCompat.setTextAppearance(this.d, resourceId);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            e(dimensionPixelSize);
        }
        if (obtainStyledAttributes.hasValue(2)) {
            int color = obtainStyledAttributes.getColor(2, 0);
            this.f10776a.setTextColor(color);
            this.e.setTextColor(color);
            this.d.setTextColor(color);
        }
        this.m = obtainStyledAttributes.getInteger(3, 80);
        obtainStyledAttributes.recycle();
        this.c = this.e.getTextColors().getDefaultColor();
        e();
        this.f10776a.setEllipsize(TextUtils.TruncateAt.END);
        this.e.setEllipsize(TextUtils.TruncateAt.END);
        this.d.setEllipsize(TextUtils.TruncateAt.END);
        if (resourceId != 0) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, j);
            boolean z = obtainStyledAttributes2.getBoolean(0, false);
            obtainStyledAttributes2.recycle();
            if (z) {
                setSingleLineAllCaps(this.f10776a);
                setSingleLineAllCaps(this.e);
                setSingleLineAllCaps(this.d);
                this.h = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
            }
        }
        this.f10776a.setSingleLine();
        this.e.setSingleLine();
        this.d.setSingleLine();
        this.h = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }

    private void e() {
        setNonPrimaryAlpha(0.6f);
    }

    void b(int i, HwPagerAdapter hwPagerAdapter) {
        int count = hwPagerAdapter != null ? hwPagerAdapter.getCount() : 0;
        boolean z = true;
        this.n = true;
        CharSequence charSequence = null;
        this.f10776a.setText((i < 1 || hwPagerAdapter == null) ? null : hwPagerAdapter.getPageTitle(i - 1));
        this.e.setText((hwPagerAdapter == null || i >= count) ? null : hwPagerAdapter.getPageTitle(i));
        int i2 = i + 1;
        if (i2 < count && hwPagerAdapter != null) {
            charSequence = hwPagerAdapter.getPageTitle(i2);
        }
        this.d.setText(charSequence);
        HwViewPager hwViewPager = this.b;
        if (hwViewPager != null && hwViewPager.getPageScrollDirection() != 0) {
            z = false;
        }
        e(z);
        this.i = i;
        if (!this.o) {
            a(i, this.g, false);
        }
        this.n = false;
    }

    private void e(boolean z) {
        float f2 = z ? 0.8f : 1.0f;
        float f3 = z ? 1.0f : 0.8f;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((getWidth() - getPaddingLeft()) - getPaddingRight()) * f2)), Integer.MIN_VALUE);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((getHeight() - getPaddingTop()) - getPaddingBottom()) * f3)), Integer.MIN_VALUE);
        this.f10776a.measure(makeMeasureSpec, makeMeasureSpec2);
        this.e.measure(makeMeasureSpec, makeMeasureSpec2);
        this.d.measure(makeMeasureSpec, makeMeasureSpec2);
    }

    void e(HwPagerAdapter hwPagerAdapter, HwPagerAdapter hwPagerAdapter2) {
        if (hwPagerAdapter != null) {
            hwPagerAdapter.unregisterDataSetObserver(this.l);
            this.k = null;
        }
        if (hwPagerAdapter2 != null) {
            hwPagerAdapter2.registerDataSetObserver(this.l);
            this.k = new WeakReference<>(hwPagerAdapter2);
        }
        HwViewPager hwViewPager = this.b;
        if (hwViewPager != null) {
            this.i = -1;
            this.g = -1.0f;
            b(hwViewPager.getCurrentItem(), hwPagerAdapter2);
            requestLayout();
        }
    }

    void a(int i, float f2, boolean z) {
        if (i != this.i) {
            b(i, this.b.getAdapter());
        } else if (!z && f2 == this.g) {
            return;
        }
        this.o = true;
        int width = getWidth();
        int height = getHeight();
        if (this.b.getPageScrollDirection() == 0) {
            b(f2, width, height);
        } else {
            c(f2, width, height);
        }
        this.g = f2;
        this.o = false;
    }

    private void a(int i, int i2) {
        int max;
        if (View.MeasureSpec.getMode(i2) != 1073741824) {
            Log.w("HwPagerTitleStrip", "onMeasureVertical: Must measure with an exact height");
            return;
        }
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, paddingLeft, -2);
        int size = View.MeasureSpec.getSize(i2);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, (int) (size * 0.19999999f), -2);
        this.f10776a.measure(childMeasureSpec, childMeasureSpec2);
        this.e.measure(childMeasureSpec, childMeasureSpec2);
        this.d.measure(childMeasureSpec, childMeasureSpec2);
        if (View.MeasureSpec.getMode(i) == 1073741824) {
            max = View.MeasureSpec.getSize(i);
        } else {
            max = Math.max(getMinWidth(), this.e.getMeasuredWidth() + paddingLeft);
        }
        this.e.getMeasuredState();
        setMeasuredDimension(View.resolveSize(max, i), size);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0082  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(float r13, int r14, int r15) {
        /*
            r12 = this;
            int r0 = r12.getPaddingLeft()
            int r1 = r12.getPaddingRight()
            int r2 = r12.getPaddingTop()
            int r3 = r12.getPaddingBottom()
            android.widget.TextView r4 = r12.e
            int r4 = r4.getMeasuredWidth()
            int r5 = r4 / 2
            int r6 = r1 + r5
            com.huawei.uikit.hwviewpager.widget.HwViewPager r7 = r12.b
            boolean r7 = r7.isRtlLayout()
            float r13 = r12.c(r13, r7)
            int r8 = r14 - r6
            int r9 = r0 + r5
            int r9 = r14 - r9
            int r9 = r9 - r6
            float r6 = (float) r9
            float r6 = r6 * r13
            int r13 = (int) r6
            int r8 = r8 - r13
            int r8 = r8 - r5
            int r4 = r4 + r8
            android.widget.TextView r13 = r12.f10776a
            int r13 = r13.getBaseline()
            android.widget.TextView r5 = r12.e
            int r5 = r5.getBaseline()
            android.widget.TextView r6 = r12.d
            int r6 = r6.getBaseline()
            int r9 = java.lang.Math.max(r13, r5)
            int r9 = java.lang.Math.max(r9, r6)
            int r13 = r9 - r13
            int r5 = r9 - r5
            int r9 = r9 - r6
            int r6 = r12.b(r13, r5, r9)
            int r10 = r12.m
            r10 = r10 & 112(0x70, float:1.57E-43)
            r11 = 16
            if (r10 == r11) goto L67
            r11 = 80
            if (r10 == r11) goto L64
            int r13 = r13 + r2
            int r5 = r5 + r2
            int r2 = r2 + r9
            goto L70
        L64:
            int r15 = r15 - r3
            int r15 = r15 - r6
            goto L6c
        L67:
            int r15 = r15 - r2
            int r15 = r15 - r3
            int r15 = r15 - r6
            int r15 = r15 / 2
        L6c:
            int r13 = r13 + r15
            int r5 = r5 + r15
            int r2 = r15 + r9
        L70:
            android.widget.TextView r15 = r12.e
            int r3 = r15.getMeasuredHeight()
            int r3 = r3 + r5
            r15.layout(r8, r5, r4, r3)
            android.widget.TextView r15 = r12.f10776a
            int r15 = r15.getMeasuredWidth()
            if (r7 == 0) goto L8d
            int r3 = r14 - r1
            int r3 = r3 - r15
            int r5 = r12.h
            int r5 = r5 + r4
            int r3 = java.lang.Math.max(r3, r5)
            goto L96
        L8d:
            int r3 = r12.h
            int r3 = r8 - r3
            int r3 = r3 - r15
            int r3 = java.lang.Math.min(r0, r3)
        L96:
            android.widget.TextView r5 = r12.f10776a
            int r15 = r15 + r3
            int r6 = r5.getMeasuredHeight()
            int r6 = r6 + r13
            r5.layout(r3, r13, r15, r6)
            android.widget.TextView r13 = r12.d
            int r13 = r13.getMeasuredWidth()
            if (r7 == 0) goto Lb2
            int r14 = r12.h
            int r8 = r8 - r14
            int r8 = r8 - r13
            int r14 = java.lang.Math.min(r0, r8)
            goto Lbb
        Lb2:
            int r14 = r14 - r1
            int r14 = r14 - r13
            int r15 = r12.h
            int r4 = r4 + r15
            int r14 = java.lang.Math.max(r14, r4)
        Lbb:
            android.widget.TextView r15 = r12.d
            int r13 = r13 + r14
            int r0 = r15.getMeasuredHeight()
            int r0 = r0 + r2
            r15.layout(r14, r2, r13, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwviewpager.widget.HwPagerTitleStrip.b(float, int, int):void");
    }

    private int b(int i, int i2, int i3) {
        return Math.max(Math.max(i + this.f10776a.getMeasuredHeight(), i2 + this.e.getMeasuredHeight()), i3 + this.d.getMeasuredHeight());
    }

    private void b(int i, int i2) {
        int max;
        if (View.MeasureSpec.getMode(i) != 1073741824) {
            Log.w("HwPagerTitleStrip", "onMeasureVertical: Must measure with an exact width");
            return;
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingTop, -2);
        int size = View.MeasureSpec.getSize(i);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i, (int) (size * 0.19999999f), -2);
        this.f10776a.measure(childMeasureSpec2, childMeasureSpec);
        this.e.measure(childMeasureSpec2, childMeasureSpec);
        this.d.measure(childMeasureSpec2, childMeasureSpec);
        if (View.MeasureSpec.getMode(i2) == 1073741824) {
            max = View.MeasureSpec.getSize(i2);
        } else {
            max = Math.max(getMinHeight(), this.e.getMeasuredHeight() + paddingTop);
        }
        setMeasuredDimension(size, View.resolveSizeAndState(max, i2, this.e.getMeasuredState() << 16));
    }
}
