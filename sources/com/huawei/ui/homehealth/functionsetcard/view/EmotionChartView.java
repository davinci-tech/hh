package com.huawei.ui.homehealth.functionsetcard.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import health.compact.a.LanguageUtil;
import java.util.Collections;
import java.util.List;

/* loaded from: classes9.dex */
public class EmotionChartView extends View {

    /* renamed from: a, reason: collision with root package name */
    private final float f9434a;
    private Paint b;
    private List<Integer> c;
    private Paint d;
    private Context e;
    private float f;
    private int g;
    private float h;
    private int i;
    private float j;
    private int[] m;
    private Paint o;

    public EmotionChartView(Context context) {
        super(context);
        this.f9434a = BaseApplication.getContext().getResources().getDimension(R.dimen._2131363003_res_0x7f0a04bb);
        this.e = context;
    }

    public EmotionChartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f9434a = BaseApplication.getContext().getResources().getDimension(R.dimen._2131363003_res_0x7f0a04bb);
        this.e = context;
    }

    public EmotionChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f9434a = BaseApplication.getContext().getResources().getDimension(R.dimen._2131363003_res_0x7f0a04bb);
        this.e = context;
    }

    public void c(int[] iArr) {
        this.m = iArr;
        c();
    }

    public void setEmotionChartDataList(List<Integer> list) {
        this.c = list;
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            Collections.reverse(this.c);
        }
        invalidate();
    }

    private void c() {
        this.o = new Paint(1);
        this.b = new Paint(1);
        this.d = new Paint(1);
        int[] iArr = this.m;
        if (iArr == null || iArr.length < 3) {
            LogUtil.a("EmotionChartView", "initPaint mPointColors is empty");
            return;
        }
        this.o.setColor(iArr[0]);
        this.b.setColor(this.m[1]);
        this.d.setColor(this.m[2]);
    }

    private void b() {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (this.i != measuredWidth || this.g != measuredHeight) {
            this.i = measuredWidth;
            this.g = measuredHeight;
            LogUtil.a("EmotionChartView", "initDivider mMeasureWidth :", Integer.valueOf(measuredWidth), " mMeasureHeight:", Integer.valueOf(this.g));
            this.j = measuredHeight / 5.0f;
            float size = (measuredWidth / this.c.size()) / 3.0f;
            this.f = size;
            this.h = size * 2.0f;
        }
        LogUtil.a("EmotionChartView", "initDivider mItemWidth :", Float.valueOf(this.h), "  mItemHeight:", Float.valueOf(this.j));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (koq.b(this.c)) {
            return;
        }
        b();
        float min = Math.min(this.j / 2.0f, Math.min(this.h / 2.0f, this.f9434a));
        int i = 0;
        float f = 0.0f;
        while (i < this.c.size()) {
            int intValue = this.c.get(i).intValue();
            if (f != 0.0f) {
                f += this.f;
            }
            float f2 = f;
            float f3 = f2 + this.h;
            dbx_(canvas, f2, f3, intValue, min);
            i++;
            f = f3;
        }
    }

    private void dbx_(Canvas canvas, float f, float f2, int i, float f3) {
        if (i == 3) {
            canvas.drawRoundRect(new RectF(f, 0.0f, f2, this.j), f3, f3, this.o);
            return;
        }
        if (i == 2) {
            float f4 = this.j;
            canvas.drawRoundRect(new RectF(f, 2.0f * f4, f2, f4 * 3.0f), f3, f3, this.b);
        } else if (i == 1) {
            float f5 = this.j;
            canvas.drawRoundRect(new RectF(f, 4.0f * f5, f2, f5 * 5.0f), f3, f3, this.d);
        }
    }
}
