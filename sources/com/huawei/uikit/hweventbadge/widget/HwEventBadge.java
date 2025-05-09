package com.huawei.uikit.hweventbadge.widget;

import android.content.Context;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import defpackage.slm;
import defpackage.smr;

/* loaded from: classes9.dex */
public class HwEventBadge extends View {
    private slm d;

    public HwEventBadge(Context context) {
        this(context, null);
    }

    private static Context a(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwEventBadge);
    }

    public slm getHwEventBadgeDrawable() {
        return this.d;
    }

    public int getMode() {
        slm slmVar = this.d;
        if (slmVar != null) {
            return slmVar.c();
        }
        return -1;
    }

    public TextPaint getTextPaint() {
        slm slmVar = this.d;
        if (slmVar == null) {
            return null;
        }
        return slmVar.edp_();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(a(getSuggestedMinimumWidth(), i), a(getSuggestedMinimumHeight(), i2));
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        slm slmVar = this.d;
        if (slmVar != null) {
            slmVar.a(i);
        }
    }

    public void setCount(int i) {
        slm slmVar = this.d;
        if (slmVar == null || slmVar.d() == i) {
            return;
        }
        this.d.b(i);
        if (this.d.c() == 2) {
            requestLayout();
            invalidate();
        }
    }

    public void setHwEventBadgeDrawable(slm slmVar) {
        this.d = slmVar;
    }

    public void setMode(int i) {
        slm slmVar = this.d;
        if (slmVar == null || slmVar.c() == i) {
            return;
        }
        this.d.c(i);
        requestLayout();
        invalidate();
    }

    public void setTextColor(int i) {
        slm slmVar = this.d;
        if (slmVar != null) {
            slmVar.d(i);
        }
    }

    public HwEventBadge(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100301_res_0x7f06028d);
    }

    private int a(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        return (mode == Integer.MIN_VALUE || mode == 0 || mode != 1073741824) ? i : View.MeasureSpec.getSize(i2);
    }

    public HwEventBadge(Context context, AttributeSet attributeSet, int i) {
        super(a(context, i), attributeSet, i);
        slm slmVar = new slm();
        this.d = slmVar;
        slmVar.edq_(super.getContext(), attributeSet, i);
        setBackground(this.d);
    }

    public void setCount(int i, int i2) {
        slm slmVar = this.d;
        if (slmVar != null) {
            int d = slmVar.d();
            int b = this.d.b();
            if (d == i && b == i2) {
                return;
            }
            this.d.e(i, i2);
            if (this.d.c() == 2) {
                requestLayout();
                invalidate();
            }
        }
    }
}
