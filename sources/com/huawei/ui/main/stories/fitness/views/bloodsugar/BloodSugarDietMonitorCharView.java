package com.huawei.ui.main.stories.fitness.views.bloodsugar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import androidx.core.util.Pair;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ggl;
import defpackage.koq;
import defpackage.nrt;
import defpackage.qad;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes6.dex */
public class BloodSugarDietMonitorCharView extends BloodSugarNocturnalHypoglycemiaChartView {
    private final int aa;
    private List<Pair<Long, Float>> ac;
    private final int ad;
    private float[] af;
    private List<qad> ah;
    private float[] z;

    public BloodSugarDietMonitorCharView(Context context) {
        super(context);
        this.ad = Color.argb(127, 252, 49, 89);
        this.aa = Color.argb(0, 252, 49, 89);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarNocturnalHypoglycemiaChartView, android.view.View
    protected void onDraw(Canvas canvas) {
        this.d = canvas;
        c();
        dvT_(canvas);
        e();
        dvS_(canvas);
    }

    private void dvT_(Canvas canvas) {
        if (this.af == null) {
            return;
        }
        float width = getWidth() - this.i;
        Path path = new Path();
        float c = c(this.af[0]);
        float c2 = c(this.af[1]);
        path.moveTo(0.0f, c);
        path.lineTo(width, c);
        path.lineTo(width, c2);
        path.lineTo(0.0f, c2);
        Paint paint = this.t;
        if (nrt.a(BaseApplication.getContext())) {
            paint.setAlpha(30);
        } else {
            paint.setAlpha(15);
        }
        canvas.drawPath(path, paint);
        paint.setAlpha(255);
        String valueOf = String.valueOf(UnitUtil.e(this.af[0], 1, 1));
        float measureText = paint.measureText(valueOf);
        String valueOf2 = String.valueOf(UnitUtil.e(this.af[1], 1, 1));
        float measureText2 = paint.measureText(valueOf2);
        if (LanguageUtil.bc(getContext())) {
            canvas.drawText(valueOf, getWidth() - width, c, paint);
            canvas.drawText(valueOf2, getWidth() - width, c2, paint);
        } else {
            canvas.drawText(valueOf, width - measureText, c, paint);
            canvas.drawText(valueOf2, width - measureText2, c2, paint);
        }
        this.z = new float[4];
        paint.getTextBounds(valueOf, 0, valueOf.length(), new Rect());
        this.z[0] = c - (r12.bottom - r12.top);
        float[] fArr = this.z;
        fArr[1] = c;
        fArr[2] = c2 - (r12.bottom - r12.top);
        this.z[3] = c2;
    }

    public void setData(List<Pair<Long, Float>> list, float[] fArr) {
        if (koq.b(list)) {
            return;
        }
        this.ac = new ArrayList(list.size());
        float f = 0.0f;
        for (Pair<Long, Float> pair : list) {
            this.ac.add(pair);
            if (pair.second.floatValue() > f) {
                f = pair.second.floatValue();
            }
        }
        if (fArr != null && fArr.length > 0) {
            this.af = (float[]) fArr.clone();
        }
        float f2 = this.af[0];
        if (f2 > f) {
            f = f2;
        }
        this.e = f;
    }

    private void dvS_(Canvas canvas) {
        LogUtil.a("BloodSugarDietMonitorCharView", "drawData");
        if (koq.b(this.ac)) {
            return;
        }
        if (LanguageUtil.bc(getContext())) {
            canvas.scale(-1.0f, 1.0f, getWidth() / 2, getHeight() / 2);
        }
        Path path = new Path();
        path.moveTo(c(this.ac.get(0).first.longValue()), c(this.ac.get(0).second.floatValue()));
        int i = 0;
        while (i < this.ac.size() - 1) {
            i++;
            Pair<Long, Float> pair = this.ac.get(i);
            path.lineTo(c(pair.first.longValue()), c(pair.second.floatValue()));
        }
        Paint paint = new Paint(1);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(this.h);
        canvas.drawPath(path, paint);
        float c = c(0.0f);
        List<Pair<Long, Float>> list = this.ac;
        path.lineTo(c(list.get(list.size() - 1).first.longValue()), c);
        path.lineTo(c(this.ac.get(0).first.longValue()), c);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{this.ad, this.aa});
        gradientDrawable.setBounds(0, 0, getWidth(), getHeight());
        int save = canvas.save();
        canvas.clipPath(path);
        gradientDrawable.draw(canvas);
        canvas.restoreToCount(save);
        LogUtil.a("BloodSugarDietMonitorCharView", "drawData finish");
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarNocturnalHypoglycemiaChartView
    protected void e() {
        float width = getWidth();
        for (qad qadVar : this.v) {
            if (LanguageUtil.bc(getContext())) {
                this.d.drawText(qadVar.b(), (width - qadVar.a()) - this.x.measureText(qadVar.b()), qadVar.c(), this.x);
            } else {
                this.d.drawText(qadVar.b(), qadVar.a(), qadVar.c(), this.x);
            }
        }
        for (qad qadVar2 : this.ah) {
            Rect rect = new Rect();
            this.x.getTextBounds(qadVar2.b(), 0, qadVar2.b().length(), rect);
            float f = rect.bottom - rect.top;
            float c = qadVar2.c();
            float f2 = c - f;
            float[] fArr = this.z;
            if (fArr != null) {
                float f3 = fArr[0];
                if (f2 < f3 || f2 > fArr[1]) {
                    float f4 = fArr[2];
                    if (f2 >= f4) {
                        if (f2 <= fArr[3]) {
                        }
                    }
                    if (c >= f3) {
                        if (c <= fArr[1]) {
                        }
                    }
                    if (c >= f4 && c <= fArr[3]) {
                    }
                }
                LogUtil.a("BloodSugarDietMonitorCharView", "rect hide ", rect.toString());
            }
            this.d.drawText(qadVar2.b(), qadVar2.a(), qadVar2.c(), this.x);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarNocturnalHypoglycemiaChartView
    protected void c() {
        this.p = 50.0f;
        this.l = getHeight() - 50.0f;
        float f = (this.l - this.p) / 5.0f;
        this.e = (float) (Math.ceil((double) this.e) % 2.0d == 0.0d ? Math.ceil(this.e) : Math.ceil(this.e) + 1.0d);
        float floatValue = BigDecimal.valueOf(this.e).divide(BigDecimal.valueOf(5L), 1, RoundingMode.DOWN).floatValue();
        float width = getWidth();
        this.ah = new ArrayList(6);
        for (int i = 0; i < 6; i++) {
            float f2 = this.p + (f * i);
            this.d.drawLine(this.i, f2, width - this.i, f2, this.j);
            String valueOf = String.valueOf(UnitUtil.e(this.e - (r7 * floatValue), 1, 1));
            float measureText = this.x.measureText(valueOf);
            float f3 = f2 - this.r;
            if (LanguageUtil.bc(getContext())) {
                this.ah.add(new qad(valueOf, this.i, f3));
            } else {
                this.ah.add(new qad(valueOf, width - (this.i + measureText), f3));
            }
        }
        this.ab = (width - (this.i * 2.0f)) - (this.y * 3.0f);
        a(this.l + Utils.convertDpToPixel(this.u));
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarNocturnalHypoglycemiaChartView
    protected void a(float f) {
        float measureText = this.x.measureText(ggl.a(new Date(this.q), "HH:mm")) / 2.0f;
        this.m = this.i + measureText;
        this.n = this.i + this.ab + measureText;
        float f2 = this.ab / 4.0f;
        for (int i = 0; i < 5; i++) {
            this.v.add(new qad(ggl.a(new Date(this.q + (i * 1800000)), "HH:mm"), this.i + (i * f2), f));
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarNocturnalHypoglycemiaChartView
    protected float c(float f) {
        return this.l - ((this.l - this.p) * (f / this.e));
    }
}
