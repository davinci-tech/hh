package com.huawei.health.suggestion.ui.fitness.module;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class MediaProgress extends View {

    /* renamed from: a, reason: collision with root package name */
    private Paint f3184a;
    private int b;
    private Context c;
    private Paint d;
    private int e;
    private RectF f;
    private List<Motion> g;
    private List<Float> h;
    private int i;
    private float j;
    private int k;
    private int l;
    private List<d> m;
    private int n;
    private int o;
    private int p;
    private RectF q;
    private Paint r;
    private d s;

    public MediaProgress(Context context) {
        super(context);
        this.l = 3;
        this.h = new ArrayList();
        this.m = new ArrayList();
        this.o = 0;
        this.k = Color.parseColor("#20ffffff");
        this.n = Color.parseColor("#ffffff");
        this.e = Color.parseColor("#80ffffff");
        this.g = new ArrayList();
        this.c = context;
    }

    public MediaProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = 3;
        this.h = new ArrayList();
        this.m = new ArrayList();
        this.o = 0;
        this.k = Color.parseColor("#20ffffff");
        this.n = Color.parseColor("#ffffff");
        this.e = Color.parseColor("#80ffffff");
        this.g = new ArrayList();
        this.c = context;
    }

    public MediaProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = 3;
        this.h = new ArrayList();
        this.m = new ArrayList();
        this.o = 0;
        this.k = Color.parseColor("#20ffffff");
        this.n = Color.parseColor("#ffffff");
        this.e = Color.parseColor("#80ffffff");
        this.g = new ArrayList();
        this.c = context;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.r = new Paint(1);
        this.d = new Paint(1);
        this.f3184a = new Paint(1);
        this.r.setColor(this.k);
        this.d.setColor(this.e);
        this.f3184a.setColor(this.k);
        this.l = b(this.c, this.l);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), i), this.l);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.i = Math.max(i2, i);
        this.o = this.g.size();
        int i5 = this.i;
        this.m.clear();
        float f = 0.0f;
        for (int i6 = 0; i6 < this.o; i6++) {
            float floatValue = ((this.h.get(i6).floatValue() * 1.0f) / this.p) * 1.0f * i5;
            this.m.add(new d(f, floatValue));
            f += floatValue;
        }
        this.d.setStrokeWidth(b(this.c, 1));
        this.f = new RectF(0.0f, 0.0f, this.i, this.l);
        this.q = new RectF(0.0f, 0.0f, 0.0f, this.l);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.m.size() <= 0) {
            return;
        }
        this.f3184a.setColor(this.k);
        float f = this.l / 2.0f;
        canvas.drawRoundRect(this.f, f, f, this.f3184a);
        this.r.setColor(this.n);
        for (int i = 0; i <= this.b; i++) {
            d dVar = this.m.get(i);
            this.s = dVar;
            if (i == this.b) {
                float e = dVar.e() + (this.s.c() * this.j);
                RectF rectF = this.q;
                if (e >= this.s.e() + this.s.c()) {
                    e = this.s.e() + this.s.c();
                }
                rectF.right = e;
            } else {
                this.q.right = dVar.e() + this.s.c();
            }
        }
        for (int i2 = 1; i2 < this.o; i2++) {
            float f2 = this.l / 2.0f;
            canvas.drawCircle(this.m.get(i2).e(), f2, f2 - 0.5f, this.d);
        }
        float f3 = this.l / 2.0f;
        canvas.drawRoundRect(this.q, f3, f3, this.r);
    }

    class d {
        float b;
        float d;
        RectF e;

        d(float f, float f2) {
            this.d = f;
            this.b = f2;
            this.e = new RectF(f, 0.0f, f2 + f, MediaProgress.this.l);
        }

        public float e() {
            return this.d;
        }

        public float c() {
            return this.b;
        }
    }

    public void setProgress(int i, int i2, float f) {
        if (i < 0 || i >= this.g.size() || i >= this.h.size()) {
            return;
        }
        this.b = i;
        if (this.g.get(i) != null && Math.abs(this.h.get(i).floatValue()) > 1.0E-6d) {
            this.j = ((i2 * this.g.get(i).acquireDuration()) + f) / this.h.get(i).floatValue();
        }
        invalidate();
    }

    public void setMotions(List<Motion> list) {
        if (list == null) {
            return;
        }
        this.g.clear();
        this.g.addAll(list);
        this.o = list.size();
        for (Motion motion : list) {
            if (motion != null) {
                this.p = (int) (this.p + (motion.acquireDuration() * motion.acquireGroups()));
                this.h.add(Float.valueOf(motion.acquireDuration() * motion.acquireGroups()));
            }
        }
    }

    public float d(int i) {
        if (i < 0 || i >= this.h.size()) {
            return 0.0f;
        }
        return this.h.get(i).floatValue();
    }

    public float getTotalTime() {
        return this.p;
    }

    public void setIntervalColor(int i) {
        this.e = i;
    }

    public void setIntervalColor(String str) {
        this.e = Color.parseColor(str);
    }

    public void setProgressBgColor(int i) {
        this.k = i;
    }

    public void setProgressBgColor(String str) {
        this.k = Color.parseColor(str);
    }

    public void setProgressColor(int i) {
        this.n = i;
    }

    public void setProgressColor(String str) {
        this.n = Color.parseColor(str);
    }

    public void setProgressHeight(int i) {
        this.l = i;
    }

    private static int b(Context context, int i) {
        return (int) ((i * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
