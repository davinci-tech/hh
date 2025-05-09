package com.huawei.ui.commonui.muscleview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import defpackage.mld;
import defpackage.npu;
import defpackage.npw;
import defpackage.nqd;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class HealthTrainBodyLineView extends View {

    /* renamed from: a, reason: collision with root package name */
    private boolean f8911a;
    private List<npu> b;
    private Paint c;
    private int d;
    private Path e;
    private int f;
    private Paint g;
    private int h;

    private boolean c(float f) {
        return f < 0.0f;
    }

    public HealthTrainBodyLineView(Context context) {
        super(context);
        this.d = getResources().getColor(R$color.emui_color_muscle_line);
        this.h = getResources().getColor(R$color.emui_color_muscle_ring_backgroud);
        this.f = getResources().getColor(R$color.emui_color_muscle_ring);
        this.f8911a = false;
        d();
    }

    public HealthTrainBodyLineView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = getResources().getColor(R$color.emui_color_muscle_line);
        this.h = getResources().getColor(R$color.emui_color_muscle_ring_backgroud);
        this.f = getResources().getColor(R$color.emui_color_muscle_ring);
        this.f8911a = false;
        d();
    }

    public HealthTrainBodyLineView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = getResources().getColor(R$color.emui_color_muscle_line);
        this.h = getResources().getColor(R$color.emui_color_muscle_ring_backgroud);
        this.f = getResources().getColor(R$color.emui_color_muscle_ring);
        this.f8911a = false;
        d();
    }

    private void d() {
        this.e = new Path();
        Paint paint = new Paint();
        this.c = paint;
        paint.setAntiAlias(true);
        this.c.setColor(this.d);
        this.c.setStyle(Paint.Style.STROKE);
        this.c.setPathEffect(new DashPathEffect(new float[]{4.0f, 4.0f}, 0.0f));
        this.c.setStrokeWidth(mld.d(getContext(), 1.0f));
        Paint paint2 = new Paint();
        this.g = paint2;
        paint2.setAntiAlias(true);
        this.g.setStrokeWidth(mld.d(getContext(), 1.5f));
        this.f8911a = LanguageUtil.bc(getContext());
    }

    public void setLineData(List<npu> list) {
        this.b = list;
        invalidate();
    }

    public void setLineColor(int i) {
        this.d = i;
        invalidate();
    }

    public void setRingColor(int i) {
        this.f = i;
        invalidate();
    }

    public void setRingBgColor(int i) {
        this.h = i;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        List<npu> list = this.b;
        if (list == null) {
            return;
        }
        for (npu npuVar : list) {
            if (npuVar == null) {
                LogUtil.h("HealthTrainBodyLineView", "onDraw bean == null");
            } else if (npuVar.e() != null && npuVar.cDN_() != null) {
                HealthTrainBodyView e = npuVar.e();
                View cDN_ = npuVar.cDN_();
                npw a2 = e.a(npuVar.c());
                if (a2 != null && a2.b() != null && !a2.b().isEmpty()) {
                    if (e.getType() == 2) {
                        if (a2.b().size() >= 2 && this.f8911a) {
                            cDT_(e, cDN_, a2.b().get(1), canvas);
                        } else {
                            cDT_(e, cDN_, a2.b().get(0), canvas);
                        }
                    }
                    if (e.getType() == 1) {
                        if (a2.b().size() >= 2 && !this.f8911a) {
                            cDT_(e, cDN_, a2.b().get(1), canvas);
                        } else {
                            cDT_(e, cDN_, a2.b().get(0), canvas);
                        }
                    }
                }
            }
        }
    }

    private void cDR_(Canvas canvas, float f, float f2) {
        this.g.setStyle(Paint.Style.FILL_AND_STROKE);
        this.g.setColor(this.h);
        canvas.drawCircle(f, f2, mld.d(getContext(), 3.0f), this.g);
        this.g.setStyle(Paint.Style.STROKE);
        this.g.setColor(this.f);
        canvas.drawCircle(f, f2, mld.d(getContext(), 3.0f), this.g);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x000d, code lost:
    
        if (r1.f8911a != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0005, code lost:
    
        if (r1.f8911a != false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:?, code lost:
    
        return (r3 + r4) + r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0013, code lost:
    
        return r3 + r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private float c(int r2, float r3, float r4, float r5) {
        /*
            r1 = this;
            r0 = 1
            if (r2 != r0) goto L8
            boolean r2 = r1.f8911a
            if (r2 == 0) goto Lf
            goto L12
        L8:
            r0 = 2
            if (r2 != r0) goto L14
            boolean r2 = r1.f8911a
            if (r2 == 0) goto L12
        Lf:
            float r3 = r3 + r4
            float r3 = r3 + r5
            goto L13
        L12:
            float r3 = r3 + r4
        L13:
            return r3
        L14:
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.commonui.muscleview.view.HealthTrainBodyLineView.c(int, float, float, float):float");
    }

    private float c(int i, float f, float f2) {
        float f3;
        int d;
        int d2;
        if (i == 1) {
            if (this.f8911a) {
                f3 = f + f2;
                d = mld.d(getContext(), 7.0f);
                return f3 + d;
            }
            d2 = mld.d(getContext(), 7.0f);
            return f - d2;
        }
        if (i != 2) {
            return -1.0f;
        }
        if (this.f8911a) {
            d2 = mld.d(getContext(), 7.0f);
            return f - d2;
        }
        f3 = f + f2;
        d = mld.d(getContext(), 7.0f);
        return f3 + d;
    }

    private void cDT_(HealthTrainBodyView healthTrainBodyView, View view, nqd nqdVar, Canvas canvas) {
        ViewGroup cDS_ = cDS_(view.getParent());
        if (cDS_ == null) {
            return;
        }
        float c = c(healthTrainBodyView.getType(), cDS_.getX() + view.getX(), view.getWidth());
        if (c(c)) {
            return;
        }
        float x = healthTrainBodyView.getX();
        float scaleX = healthTrainBodyView.getScaleX();
        float c2 = mld.c(getContext());
        float b = nqdVar.b();
        ViewGroup cDS_2 = cDS_(healthTrainBodyView.getParent());
        if (cDS_2 == null) {
            return;
        }
        float x2 = cDS_2.getX();
        float y = cDS_2.getY();
        float f = x + x2 + (b * scaleX * c2);
        if (c(f)) {
            return;
        }
        float scaleY = healthTrainBodyView.getScaleY();
        float c3 = nqdVar.c();
        float y2 = healthTrainBodyView.getY();
        this.e.reset();
        float y3 = cDS_.getY() + view.getY() + (view.getHeight() / 2.0f);
        int width = healthTrainBodyView.getWidth();
        this.e.moveTo(c, y3);
        float c4 = c(healthTrainBodyView.getType(), x, x2, width, y3);
        if (c(c4)) {
            return;
        }
        float f2 = ((c3 * c2) + y2 + y) * scaleY;
        this.e.lineTo(c4, f2);
        this.e.lineTo(f, f2);
        canvas.drawPath(this.e, this.c);
        cDR_(canvas, c, y3);
    }

    private ViewGroup cDS_(ViewParent viewParent) {
        if (viewParent instanceof ViewGroup) {
            return (ViewGroup) viewParent;
        }
        return null;
    }

    private float c(int i, float f, float f2, int i2, float f3) {
        float c = c(i, f, f2, i2);
        if (c(c)) {
            return -1.0f;
        }
        this.e.lineTo(c, f3);
        return c;
    }
}
