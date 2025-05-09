package com.huawei.ui.homehealth.functionsetcard.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gvv;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class SleepThumbnailView extends View {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f9436a = {R.color._2131296807_res_0x7f090227, R.color._2131296890_res_0x7f09027a, R.color._2131296881_res_0x7f090271, R.color._2131296862_res_0x7f09025e, R.color._2131296823_res_0x7f090237};
    private float[] b;
    private Context c;
    private ArrayList<b> d;
    private int e;
    private float i;

    public SleepThumbnailView(Context context) {
        super(context);
        this.d = new ArrayList<>(5);
        a(context);
    }

    public SleepThumbnailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new ArrayList<>(5);
        a(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0117  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setSleepData(int r18, int r19, int r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instructions count: 347
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.homehealth.functionsetcard.view.SleepThumbnailView.setSleepData(int, int, int, int, int, int, int):void");
    }

    private float d(float f, float[] fArr) {
        int i = 0;
        for (float f2 : fArr) {
            if (f2 > 0.0f) {
                i++;
            }
        }
        float f3 = f - ((i - 1) * 1.0f);
        LogUtil.a("FunctionSet_SleepThumbnailView", "sleepViewWidth = ", Float.valueOf(f3));
        return f3;
    }

    private void a(Context context) {
        this.c = context;
        for (int i : f9436a) {
            this.d.add(new b(context.getResources().getColor(i)));
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (LanguageUtil.bc(this.c.getApplicationContext())) {
            canvas.scale(-1.0f, 1.0f, getWidth() / 2.0f, getHeight());
        }
        a();
        Iterator<b> it = this.d.iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            b next = it.next();
            if (next.b() > 0.0f) {
                float b2 = f + (next.b() * this.i);
                dbJ_(canvas, f, b2, next.d(), next.c());
                f = b2 + 1.0f;
            }
        }
    }

    private void a() {
        int measuredWidth = getMeasuredWidth();
        if (this.e != measuredWidth) {
            this.e = measuredWidth;
            this.i = d(gvv.b(this.c, measuredWidth), this.b);
        }
        LogUtil.a("FunctionSet_SleepThumbnailView", "initSleepViewWidthWithDp currentMeasureWidth :", Integer.valueOf(measuredWidth), "initSleepViewWidthWithDp mSleepViewWidthWithDp :", Float.valueOf(this.i));
    }

    private void dbJ_(Canvas canvas, float f, float f2, int i, int i2) {
        Paint paint = new Paint();
        paint.setColor(i);
        canvas.drawRect(a(f), a(15.0f), a(f2), a(17.0f), paint);
        paint.setShader(new LinearGradient(0.0f, a(17.0f), 0.0f, a(45.0f), new int[]{i2, 0}, (float[]) null, Shader.TileMode.CLAMP));
        canvas.drawRect(a(f), a(17.0f), a(f2), a(45.0f), paint);
    }

    public float a(float f) {
        return nsn.c(this.c, f);
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private int f9437a;
        private int c;
        private float e;

        private int d(int i) {
            return i + 1308622848;
        }

        private b(int i) {
            b(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float b() {
            return this.e;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(float f) {
            this.e = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int d() {
            return this.c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(int i) {
            this.c = i;
            this.f9437a = d(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int c() {
            return this.f9437a;
        }
    }
}
