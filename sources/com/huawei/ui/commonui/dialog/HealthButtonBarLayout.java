package com.huawei.ui.commonui.dialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.core.widget.TextViewCompat;
import com.huawei.ui.commonui.R$styleable;

/* loaded from: classes6.dex */
public class HealthButtonBarLayout extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private final float f8808a;
    private int b;
    private final boolean c;
    private final float d;
    private final float e;

    public HealthButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HealthButtonBarLayout);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.d = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthButtonBarLayout_btnAutoSizeMinTextSize, Math.round(TypedValue.applyDimension(2, 9.0f, displayMetrics)));
        this.f8808a = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthButtonBarLayout_btnAutoSizeMaxTextSize, Math.round(TypedValue.applyDimension(2, 16.0f, displayMetrics)));
        this.e = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthButtonBarLayout_btnAutoSizeStepGranularity, Math.round(TypedValue.applyDimension(2, 1.0f, displayMetrics)));
        this.c = obtainStyledAttributes.getBoolean(R$styleable.HealthButtonBarLayout_btnReverse, true);
        obtainStyledAttributes.recycle();
    }

    private float getMinTextSize() {
        int childCount = getChildCount();
        float f = 0.0f;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt != null && childAt.getVisibility() != 8 && (childAt instanceof Button)) {
                float textSize = ((Button) childAt).getTextSize();
                if (textSize < f || Float.compare(f, 0.0f) == 0) {
                    f = textSize;
                }
            }
        }
        return f;
    }

    private void e(float f) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt != null && childAt.getVisibility() != 8 && (childAt instanceof Button)) {
                Button button = (Button) childAt;
                TextViewCompat.setAutoSizeTextTypeWithDefaults(button, 0);
                button.setTextSize(0, f);
            }
        }
    }

    public void a(boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt != null && childAt.getVisibility() != 8 && (childAt instanceof Button)) {
                Button button = (Button) childAt;
                if (z) {
                    TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(button, Math.round(this.d), Math.round(this.f8808a), Math.round(this.e), 0);
                } else {
                    TextViewCompat.setAutoSizeTextTypeWithDefaults(button, 0);
                    button.setTextSize(0, this.f8808a);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0044, code lost:
    
        if (r2 != false) goto L19;
     */
    @Override // android.widget.LinearLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r6, int r7) {
        /*
            r5 = this;
            int r0 = android.view.View.MeasureSpec.getSize(r6)
            int r1 = r5.b
            r2 = 0
            if (r0 <= r1) goto L12
            boolean r1 = r5.b()
            if (r1 == 0) goto L12
            r5.setStacked(r2)
        L12:
            r5.b = r0
            boolean r1 = r5.b()
            r3 = 1
            if (r1 != 0) goto L2b
            int r1 = android.view.View.MeasureSpec.getMode(r6)
            r4 = 1073741824(0x40000000, float:2.0)
            if (r1 != r4) goto L2b
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r1)
            r2 = r3
            goto L2c
        L2b:
            r0 = r6
        L2c:
            super.onMeasure(r0, r7)
            boolean r0 = r5.b()
            if (r0 != 0) goto L44
            int r0 = r5.getMeasuredWidthAndState()
            r1 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r0 = r0 & r1
            r1 = 16777216(0x1000000, float:2.3509887E-38)
            if (r0 != r1) goto L44
            r5.setStacked(r3)
            goto L46
        L44:
            if (r2 == 0) goto L49
        L46:
            super.onMeasure(r6, r7)
        L49:
            boolean r6 = r5.b()
            if (r6 == 0) goto L56
            float r6 = r5.getMinTextSize()
            r5.e(r6)
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.commonui.dialog.HealthButtonBarLayout.onMeasure(int, int):void");
    }

    private void setStacked(boolean z) {
        if (z == b()) {
            return;
        }
        setOrientation(z ? 1 : 0);
        setShowDividers(z ? 0 : 2);
        if (this.c) {
            for (int childCount = getChildCount() - 2; childCount >= 0; childCount--) {
                bringChildToFront(getChildAt(childCount));
            }
        }
        a(z);
    }

    private boolean b() {
        return getOrientation() == 1;
    }
}
