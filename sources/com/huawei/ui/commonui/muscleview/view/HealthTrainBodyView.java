package com.huawei.ui.commonui.muscleview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import androidx.core.graphics.PathParser;
import com.huawei.ui.commonui.R$color;
import defpackage.mld;
import defpackage.npw;
import defpackage.npy;
import defpackage.nqd;
import java.util.List;

/* loaded from: classes6.dex */
public class HealthTrainBodyView extends View {

    /* renamed from: a, reason: collision with root package name */
    private Paint f8912a;
    private volatile List<npy> b;
    private int c;
    private int d;
    private Paint e;
    private volatile List<npw> f;
    private volatile List<npy> g;
    private float[] h;
    private int[] i;
    private int[] j;
    private int k;
    private SparseArray<Integer> l;

    public HealthTrainBodyView(Context context) {
        super(context);
        this.i = new int[]{getResources().getColor(R$color.emui_color_muscle_total_low), getResources().getColor(R$color.emui_color_muscle_total_top)};
        this.j = new int[]{getResources().getColor(R$color.emui_color_muscle_slight_top), getResources().getColor(R$color.emui_color_muscle_slight_top)};
        this.h = new float[]{0.0f, 1.0f};
        this.c = getResources().getColor(R$color.emui_color_muscle_backgroud);
        this.d = getResources().getColor(R$color.emui_color_muscle_body_backgroud);
        b();
    }

    public HealthTrainBodyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = new int[]{getResources().getColor(R$color.emui_color_muscle_total_low), getResources().getColor(R$color.emui_color_muscle_total_top)};
        this.j = new int[]{getResources().getColor(R$color.emui_color_muscle_slight_top), getResources().getColor(R$color.emui_color_muscle_slight_top)};
        this.h = new float[]{0.0f, 1.0f};
        this.c = getResources().getColor(R$color.emui_color_muscle_backgroud);
        this.d = getResources().getColor(R$color.emui_color_muscle_body_backgroud);
        b();
    }

    public HealthTrainBodyView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = new int[]{getResources().getColor(R$color.emui_color_muscle_total_low), getResources().getColor(R$color.emui_color_muscle_total_top)};
        this.j = new int[]{getResources().getColor(R$color.emui_color_muscle_slight_top), getResources().getColor(R$color.emui_color_muscle_slight_top)};
        this.h = new float[]{0.0f, 1.0f};
        this.c = getResources().getColor(R$color.emui_color_muscle_backgroud);
        this.d = getResources().getColor(R$color.emui_color_muscle_body_backgroud);
        b();
    }

    private void b() {
        Paint paint = new Paint();
        this.e = paint;
        paint.setAntiAlias(true);
        this.e.setColor(this.c);
        Paint paint2 = new Paint();
        this.f8912a = paint2;
        paint2.setAntiAlias(true);
        this.f8912a.setStyle(Paint.Style.STROKE);
        this.f8912a.setColor(this.d);
        this.f8912a.setStrokeWidth(1.0f);
        this.k = 1;
        invalidate();
    }

    public void setSecondColors(int... iArr) {
        this.i = iArr;
    }

    public void setFirstColors(int... iArr) {
        this.j = iArr;
    }

    public void setFrontViewData(List<npy> list, List<npw> list2, SparseArray<Integer> sparseArray) {
        this.k = 1;
        this.g = list;
        this.f = list2;
        this.l = sparseArray;
        d();
    }

    public void setBackViewData(List<npy> list, List<npw> list2, SparseArray<Integer> sparseArray) {
        this.k = 2;
        this.b = list;
        this.f = list2;
        this.l = sparseArray;
        d();
    }

    private void d() {
        if (this.f != null) {
            for (npw npwVar : this.f) {
                if (npwVar != null) {
                    if (this.l == null) {
                        break;
                    }
                    for (int i = 0; i < this.l.size(); i++) {
                        int keyAt = this.l.keyAt(i);
                        if (keyAt == npwVar.e()) {
                            Paint paint = new Paint();
                            paint.setAntiAlias(true);
                            List<nqd> b = npwVar.b();
                            if (b != null && !b.isEmpty()) {
                                int intValue = this.l.get(keyAt).intValue();
                                for (nqd nqdVar : b) {
                                    if (nqdVar != null) {
                                        if (intValue >= 1) {
                                            LinearGradient linearGradient = new LinearGradient(nqdVar.g(), nqdVar.h(), nqdVar.g(), nqdVar.e(), intValue > 1 ? this.i : this.j, this.h, Shader.TileMode.CLAMP);
                                            Paint paint2 = new Paint();
                                            paint2.setAntiAlias(true);
                                            paint2.setShader(linearGradient);
                                            nqdVar.cDQ_(paint2);
                                            paint = paint2;
                                        } else {
                                            paint.setColor(this.c);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            invalidate();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = this.k;
        if (i == 1) {
            cDW_(canvas);
        } else if (i != 2) {
            return;
        } else {
            cDU_(canvas);
        }
        cDX_(canvas);
    }

    private void cDX_(Canvas canvas) {
        if (this.f == null || this.f.isEmpty()) {
            return;
        }
        for (npw npwVar : this.f) {
            if (npwVar != null && npwVar.b() != null && !npwVar.b().isEmpty()) {
                for (nqd nqdVar : npwVar.b()) {
                    if (nqdVar != null && nqdVar.cDP_() != null) {
                        int save = canvas.save();
                        canvas.scale(mld.c(getContext()), mld.c(getContext()));
                        canvas.drawPath(PathParser.createPathFromPathData(nqdVar.d()), nqdVar.cDP_());
                        canvas.restoreToCount(save);
                    }
                }
            }
        }
    }

    private void cDW_(Canvas canvas) {
        if (this.g == null || this.g.isEmpty()) {
            return;
        }
        cDV_(canvas, this.g);
    }

    private void cDU_(Canvas canvas) {
        if (this.b == null || this.b.isEmpty()) {
            return;
        }
        cDV_(canvas, this.b);
    }

    public npw a(int i) {
        for (npw npwVar : this.f) {
            if (npwVar != null && i == npwVar.e()) {
                return npwVar;
            }
        }
        return null;
    }

    public int getType() {
        return this.k;
    }

    public void setBgColor(int i) {
        this.c = i;
        this.e.setColor(i);
        invalidate();
    }

    private void cDV_(Canvas canvas, List<npy> list) {
        boolean z = true;
        for (npy npyVar : list) {
            if (npyVar != null) {
                int save = canvas.save();
                canvas.scale(mld.c(getContext()), mld.c(getContext()));
                if (z) {
                    canvas.drawPath(PathParser.createPathFromPathData(npyVar.a()), this.f8912a);
                    canvas.restoreToCount(save);
                    z = false;
                } else {
                    canvas.drawPath(PathParser.createPathFromPathData(npyVar.a()), this.e);
                    canvas.restoreToCount(save);
                }
            }
        }
    }
}
