package com.huawei.ui.commonui.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import defpackage.koq;
import defpackage.nmm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class IndicatorCircle extends View {

    /* renamed from: a, reason: collision with root package name */
    private List<nmm> f8848a;
    private float b;
    private float c;
    private List<nmm> d;
    private float e;
    private float f;
    private Drawable g;
    private float h;
    private float[] i;
    private float j;
    private int[] k;
    private Runnable l;
    private boolean m;
    private boolean n;
    private Paint o;
    private HealthLevelIndicator p;
    private PorterDuffXfermode q;
    private RectF t;

    public IndicatorCircle(Context context) {
        super(context);
        this.t = new RectF();
        this.d = new ArrayList();
        this.f8848a = new ArrayList();
        this.k = null;
        this.i = null;
        this.h = 0.0f;
        this.n = false;
        this.f = 0.0f;
        this.c = 0.0f;
        this.b = 0.0f;
        this.l = null;
        this.m = true;
        c();
    }

    public IndicatorCircle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.t = new RectF();
        this.d = new ArrayList();
        this.f8848a = new ArrayList();
        this.k = null;
        this.i = null;
        this.h = 0.0f;
        this.n = false;
        this.f = 0.0f;
        this.c = 0.0f;
        this.b = 0.0f;
        this.l = null;
        this.m = true;
        c();
    }

    public IndicatorCircle(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.t = new RectF();
        this.d = new ArrayList();
        this.f8848a = new ArrayList();
        this.k = null;
        this.i = null;
        this.h = 0.0f;
        this.n = false;
        this.f = 0.0f;
        this.c = 0.0f;
        this.b = 0.0f;
        this.l = null;
        this.m = true;
        c();
    }

    private void c() {
        setLayerType(2, null);
        final float dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131363197_res_0x7f0a057d);
        post(new Runnable() { // from class: com.huawei.ui.commonui.indicator.IndicatorCircle.5
            @Override // java.lang.Runnable
            public void run() {
                int max = Math.max(IndicatorCircle.this.getMeasuredWidth(), IndicatorCircle.this.getMeasuredHeight());
                float f = dimensionPixelSize / 2.0f;
                float f2 = max;
                float f3 = f2 - f;
                IndicatorCircle.this.t = new RectF(f, f, f3, f3);
                IndicatorCircle.this.e = f2 / 2.0f;
                IndicatorCircle.this.f = (f * 180.0f) / ((float) (r0.e * 3.141592653589793d));
                IndicatorCircle indicatorCircle = IndicatorCircle.this;
                indicatorCircle.c = indicatorCircle.f + 2.0f;
                IndicatorCircle indicatorCircle2 = IndicatorCircle.this;
                indicatorCircle2.b = indicatorCircle2.f;
                if (IndicatorCircle.this.l != null) {
                    LogUtil.a("IndicatorCircle", "getIndicatorAngleByEndPoints, execute pending indicate task!");
                    IndicatorCircle.this.l.run();
                    IndicatorCircle.this.l = null;
                }
                IndicatorCircle.this.m = true;
                IndicatorCircle.this.invalidate();
            }
        });
        Paint paint = new Paint();
        this.o = paint;
        paint.setStrokeWidth(dimensionPixelSize);
        this.o.setStyle(Paint.Style.STROKE);
        this.o.setStrokeCap(Paint.Cap.ROUND);
        this.o.setAntiAlias(true);
        this.q = new PorterDuffXfermode(PorterDuff.Mode.XOR);
        this.g = ContextCompat.getDrawable(getContext(), R$drawable.indicator_circle_array_img);
        this.h = getContext().getResources().getDimensionPixelSize(R.dimen._2131363205_res_0x7f0a0585);
        this.g.setBounds(0, 0, getContext().getResources().getDimensionPixelSize(R.dimen._2131363207_res_0x7f0a0587), getContext().getResources().getDimensionPixelSize(R.dimen._2131363206_res_0x7f0a0586));
    }

    private void b(List<nmm> list) {
        if (koq.b(list)) {
            LogUtil.b("IndicatorCircle", "extractEndPoints failed, cause levelsData is empty");
            return;
        }
        int size = list.size();
        this.i = new float[size + 1];
        for (int i = 0; i < size; i++) {
            nmm nmmVar = list.get(i);
            if (nmmVar != null) {
                this.i[i] = nmmVar.d();
            }
        }
        nmm nmmVar2 = list.get(size - 1);
        if (nmmVar2 == null) {
            return;
        }
        this.i[size] = nmmVar2.b();
    }

    public void setData(List<nmm> list, int[] iArr) throws CloneNotSupportedException {
        if (iArr != null) {
            int length = iArr.length;
            int[] iArr2 = new int[length];
            this.k = iArr2;
            System.arraycopy(iArr, 0, iArr2, 0, length);
        }
        b(list);
        this.f8848a.clear();
        this.f8848a.addAll(list);
        this.d.clear();
        Iterator<nmm> it = this.f8848a.iterator();
        while (it.hasNext()) {
            this.d.add(it.next().clone());
        }
        d();
        invalidate();
    }

    private nmm b(float f) {
        for (nmm nmmVar : this.f8848a) {
            if (f >= nmmVar.d() && f < nmmVar.b()) {
                return nmmVar;
            }
        }
        if (f < this.f8848a.get(0).d()) {
            return this.f8848a.get(0);
        }
        return this.f8848a.get(r4.size() - 1);
    }

    public void setLevel(float f) {
        boolean equals = Float.valueOf(f).equals(Float.valueOf(Float.NaN));
        this.n = equals;
        if (this.p != null) {
            nmm b = !equals ? b(f) : null;
            this.p.d(b != null ? b.c() : null, b != null ? b.e() : 0);
        }
        if (this.n) {
            invalidate();
            return;
        }
        if (this.i == null) {
            e(f);
        } else {
            d(f);
        }
        invalidate();
    }

    private void e(float f) {
        for (int i = 0; i < this.f8848a.size(); i++) {
            nmm nmmVar = this.f8848a.get(i);
            float d = nmmVar.d();
            float b = nmmVar.b();
            if (f >= d && f < b) {
                nmm nmmVar2 = this.d.get(i);
                this.j = ((nmmVar2.d() + nmmVar2.b()) / 2.0f) + 90.0f;
                return;
            }
        }
        nmm nmmVar3 = this.f8848a.get(0);
        nmm nmmVar4 = this.d.get(0);
        nmm nmmVar5 = this.d.get(r2.size() - 1);
        this.j = (((f < nmmVar3.d() ? nmmVar4.d() : nmmVar5.d()) + (f < nmmVar3.b() ? nmmVar4.b() : nmmVar5.b())) / 2.0f) + 90.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final float f) {
        if (getMeasuredHeight() <= 0 || getMeasuredWidth() <= 0) {
            LogUtil.h("IndicatorCircle", "getIndicatorAngleByEndPoints, not measured yet!");
            this.l = new Runnable() { // from class: com.huawei.ui.commonui.indicator.IndicatorCircle.1
                @Override // java.lang.Runnable
                public void run() {
                    IndicatorCircle.this.d(f);
                }
            };
            return;
        }
        LogUtil.i("IndicatorCircle", "getIndicatorAngleByEndPoints, mEndPoints = ", Arrays.toString(this.i), ", level data list = ", this.f8848a);
        int i = 0;
        while (true) {
            float[] fArr = this.i;
            if (i < fArr.length - 1) {
                float f2 = fArr[i];
                int i2 = i + 1;
                float f3 = fArr[i2];
                if (f >= f2 && f < f3) {
                    LogUtil.a("IndicatorCircle", "getIndicatorAngleByEndPoints find a levelData, level = ", Float.valueOf(f), ", ep1 = ", Float.valueOf(f2), ", ep2 = ", Float.valueOf(f3));
                    nmm nmmVar = this.d.get(i);
                    float d = nmmVar.d();
                    float b = nmmVar.b();
                    float f4 = this.b;
                    float f5 = b + this.c;
                    this.j = 90.0f + f5 + (((f - f2) / (f3 - f2)) * ((d + f4) - f5));
                    return;
                }
                i = i2;
            } else {
                nmm nmmVar2 = this.d.get(0);
                nmm nmmVar3 = this.d.get(this.f8848a.size() - 1);
                LogUtil.a("IndicatorCircle", "getIndicatorAngleByEndPoints not in range, level = ", Float.valueOf(f));
                this.j = (f < this.i[0] ? nmmVar2.b() - this.f : nmmVar3.d() + this.f) + 90.0f;
                return;
            }
        }
    }

    private void d() {
        List<nmm> list = this.d;
        if (list == null) {
            return;
        }
        int size = list.size();
        float f = 270.0f / size;
        int i = size - 1;
        int i2 = i;
        while (i2 >= 0) {
            nmm nmmVar = this.d.get(i2);
            nmmVar.d(i2 == i ? 45.0f : this.d.get(i2 + 1).b());
            nmmVar.b(nmmVar.d() - (this.k == null ? f : r4[i2]));
            i2--;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        List<nmm> list = this.d;
        if (list == null) {
            return;
        }
        int size = list.size() - 1;
        for (int i = size; i >= 0; i--) {
            nmm nmmVar = this.d.get(i);
            if (i < size) {
                cAZ_(canvas, nmmVar, this.q, 2.0f);
            }
            cAZ_(canvas, nmmVar, null, 0.0f);
        }
        if (!this.n && this.m) {
            cBa_(canvas);
        }
    }

    private void cAZ_(Canvas canvas, nmm nmmVar, Xfermode xfermode, float f) {
        this.o.setXfermode(xfermode);
        this.o.setColor(nmmVar.e());
        canvas.drawArc(this.t, nmmVar.d() + f, nmmVar.b() - nmmVar.d(), false, this.o);
    }

    private void cBa_(Canvas canvas) {
        canvas.save();
        float f = this.e;
        float intrinsicWidth = this.g.getIntrinsicWidth();
        float intrinsicHeight = this.g.getIntrinsicHeight();
        float f2 = intrinsicWidth / 2.0f;
        float f3 = intrinsicHeight / 2.0f;
        canvas.translate(f - f2, f - f3);
        canvas.rotate(this.j, f2, f3);
        canvas.translate(0.0f, (-(this.e - intrinsicHeight)) - this.h);
        this.g.draw(canvas);
        canvas.restore();
    }

    void d(HealthLevelIndicator healthLevelIndicator, boolean z) {
        this.p = healthLevelIndicator;
        this.m = z;
    }
}
