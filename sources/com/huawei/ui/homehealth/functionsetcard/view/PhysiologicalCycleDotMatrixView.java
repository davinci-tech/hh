package com.huawei.ui.homehealth.functionsetcard.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jec;
import defpackage.koq;
import defpackage.nsn;
import defpackage.scz;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

/* loaded from: classes6.dex */
public class PhysiologicalCycleDotMatrixView extends View {
    private int aa;
    private float ab;
    private int ac;
    private Paint ad;
    private Paint ae;
    private Paint af;
    private float ag;
    private float ah;
    private float ai;
    private String ak;
    private Paint g;
    private int[][] h;
    private Context j;
    private Paint k;
    private Paint l;
    private Paint m;
    private Paint n;
    private Paint o;
    private Paint p;
    private Paint q;
    private Paint r;
    private Paint s;
    private Paint t;
    private Paint u;
    private Paint v;
    private Paint w;
    private Paint x;
    private Paint y;
    private Paint z;
    private static final float e = nsn.c(BaseApplication.e(), 4.0f);

    /* renamed from: a, reason: collision with root package name */
    private static final float f9435a = nsn.c(BaseApplication.e(), 6.0f);
    private static final float b = nsn.c(BaseApplication.e(), 8.0f);
    private static final float c = nsn.c(BaseApplication.e(), 8.5f);
    private static final float i = nsn.c(BaseApplication.e(), 11.0f);
    private static final float f = nsn.c(BaseApplication.e(), 8.0f);
    private static final int d = nsn.c(BaseApplication.e(), 10.0f);

    private boolean b(int i2) {
        return i2 == 1 || i2 == 3 || i2 == 5 || i2 == 7;
    }

    private boolean e(int i2) {
        return i2 == 9;
    }

    public PhysiologicalCycleDotMatrixView(Context context, List<HiHealthData> list) {
        super(context);
        this.ak = "今";
        this.j = context;
        this.h = e(new ArrayList(list));
        a();
    }

    private int[][] e(List<HiHealthData> list) {
        boolean e2 = scz.e();
        LogUtil.a("DotMatrixView", "createDataList size : ", Integer.valueOf(list.size()), " isOpenOvulationFertilePeriodSwitch ", Boolean.valueOf(e2));
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            HiHealthData hiHealthData = list.get(i2);
            if (hiHealthData == null) {
                LogUtil.h("DotMatrixView", "hiData item is null");
            } else {
                try {
                    int e3 = e(String.valueOf(hiHealthData.getDouble("point_value")), hiHealthData.getStartTime(), e2);
                    arrayList.add(Integer.valueOf(e3));
                    LogUtil.a("DotMatrixView", "createDataList pointType ", Integer.valueOf(e3));
                } catch (NullPointerException unused) {
                    LogUtil.b("DotMatrixView", "data.getStartTime() nullPointerException");
                }
            }
        }
        Collections.reverse(arrayList);
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 3, 7);
        for (int i3 = 0; i3 < iArr.length; i3++) {
            int i4 = 0;
            while (true) {
                int[] iArr2 = iArr[i3];
                if (i4 < iArr2.length) {
                    int i5 = (i3 * 7) + i4;
                    iArr2[i4] = koq.d(arrayList, i5) ? ((Integer) arrayList.get(i5)).intValue() : 0;
                    i4++;
                }
            }
        }
        this.ac = iArr[0].length;
        this.aa = iArr.length;
        for (HiHealthData hiHealthData2 : list) {
            if (hiHealthData2 != null) {
                LogUtil.a("DotMatrixView", "createDataList, time: ", new Date(hiHealthData2.getStartTime()), ", type: ", String.valueOf(hiHealthData2.getDouble("point_value")));
            }
        }
        return iArr;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int e(String str, long j, boolean z) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case 47602:
                if (str.equals("0.0")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 49524:
                if (str.equals("2.0")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 46730099:
                if (str.equals("100.0")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 46731060:
                if (str.equals("101.0")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 46732021:
                if (str.equals("102.0")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 48577141:
                if (str.equals("300.0")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 48578102:
                if (str.equals("301.0")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 48579063:
                if (str.equals("302.0")) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            case 49500662:
                if (str.equals("400.0")) {
                    c2 = '\b';
                    break;
                }
                c2 = 65535;
                break;
            case 49501623:
                if (str.equals("401.0")) {
                    c2 = '\t';
                    break;
                }
                c2 = 65535;
                break;
            case 49502584:
                if (str.equals("402.0")) {
                    c2 = '\n';
                    break;
                }
                c2 = 65535;
                break;
            case 49503545:
                if (str.equals("403.0")) {
                    c2 = 11;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                return c(j) ? 1 : 0;
            case 1:
                if (z) {
                    return c(j) ? 9 : 8;
                }
                return c(j) ? 1 : 0;
            case 2:
            case 3:
            case 4:
                return c(j) ? 3 : 2;
            case 5:
            case 6:
            case 7:
                return c(j) ? 7 : 6;
            case '\b':
            case '\t':
            case '\n':
            case 11:
                if (z) {
                    return c(j) ? 5 : 4;
                }
                return c(j) ? 1 : 0;
            default:
                LogUtil.h("DotMatrixView", "getPointType other type!");
                return 0;
        }
    }

    private boolean c(long j) {
        return jec.d(System.currentTimeMillis()) == j;
    }

    private void a() {
        this.p = dbF_(R.color._2131298804_res_0x7f0909f4);
        this.r = dbH_(R.color._2131298806_res_0x7f0909f6);
        this.q = dbH_(R.color._2131298805_res_0x7f0909f5);
        this.w = dbF_(R.color._2131298807_res_0x7f0909f7);
        this.l = dbF_(R.color._2131298809_res_0x7f0909f9);
        this.s = dbH_(R.color._2131298811_res_0x7f0909fb);
        this.m = dbH_(R.color._2131298810_res_0x7f0909fa);
        this.t = dbF_(R.color._2131298812_res_0x7f0909fc);
        this.ad = dbF_(R.color._2131298813_res_0x7f0909fd);
        this.ae = dbH_(R.color._2131298815_res_0x7f0909ff);
        this.z = dbH_(R.color._2131298814_res_0x7f0909fe);
        this.af = dbF_(R.color._2131298816_res_0x7f090a00);
        this.u = dbG_(R.color._2131298816_res_0x7f090a00, f9435a);
        this.v = dbI_(R.color._2131298816_res_0x7f090a00, c);
        this.y = dbH_(R.color._2131298814_res_0x7f0909fe);
        this.x = dbF_(R.color._2131298808_res_0x7f0909f8);
        this.n = dbH_(R.color._2131298802_res_0x7f0909f2);
        this.k = dbH_(R.color._2131298801_res_0x7f0909f1);
        this.o = dbF_(R.color._2131298803_res_0x7f0909f3);
        Paint dbF_ = dbF_(R.color._2131296665_res_0x7f090199);
        this.g = dbF_;
        dbF_.setStyle(Paint.Style.FILL_AND_STROKE);
        c();
        if (LanguageUtil.m(BaseApplication.e())) {
            return;
        }
        this.ak = UnitUtil.e(Calendar.getInstance().get(5), 1, 0);
    }

    private void c() {
        float f2 = e;
        float f3 = 2.0f * f2;
        this.ah = i + f3;
        this.ag = f3 + f;
        this.ab = 0.3f * f2;
        this.ai = f2 * 0.6f;
    }

    private Paint dbF_(int i2) {
        return dbG_(i2, this.ai);
    }

    private Paint dbG_(int i2, float f2) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(i2, null));
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(f2);
        paint.setStrokeWidth(this.ab);
        return paint;
    }

    private Paint dbH_(int i2) {
        return dbI_(i2, this.ai);
    }

    private Paint dbI_(int i2, float f2) {
        Paint dbF_ = dbF_(i2);
        dbF_.setStyle(Paint.Style.FILL);
        dbF_.setTextSize(f2);
        dbF_.setStrokeWidth(this.ab);
        return dbF_;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int[] iArr;
        int i2;
        int i3;
        super.onDraw(canvas);
        if (LanguageUtil.bc(this.j.getApplicationContext())) {
            canvas.scale(-1.0f, 1.0f, getWidth() / 2.0f, getHeight());
        }
        for (int i4 = 0; i4 < this.h.length; i4++) {
            for (int i5 = 0; i5 < this.h[0].length; i5++) {
                float b2 = b(i4, i5);
                int[][] iArr2 = this.h;
                if (i5 != iArr2[0].length - 1 && (i2 = (iArr = iArr2[i4])[i5]) != -1 && (i3 = iArr[i5 + 1]) != -1) {
                    dbA_(canvas, (i5 * this.ah) + b2, c + (i4 * this.ag), Math.min(i2, i3));
                }
                dbz_(canvas, b2 + (i5 * this.ah), c + (i4 * this.ag), e, this.h[i4][i5]);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        if (b(this.h[0][this.ac - 1]) || e(this.h[0][this.ac - 1])) {
            float f2 = this.ah;
            float f3 = f9435a;
            float f4 = c;
            setMeasuredDimension((int) (((this.ac - 1) * f2) + f3 + f4), (int) (f4 + ((this.aa - 1) * this.ag) + f3));
            return;
        }
        float f5 = this.ah;
        float f6 = f9435a;
        setMeasuredDimension((int) (((this.ac - 1) * f5) + (2.0f * f6)), (int) (b + ((this.aa - 1) * this.ag) + f6));
    }

    private void dbz_(Canvas canvas, float f2, float f3, float f4, int i2) {
        switch (i2) {
            case 0:
                canvas.drawCircle(f2, f3, f4, this.p);
                break;
            case 1:
                canvas.drawCircle(f2, f3, b, this.r);
                this.w.setTextSize(d);
                dbE_(canvas, this.ak, f2, f3, this.w);
                break;
            case 2:
                canvas.drawCircle(f2, f3, f4, this.l);
                break;
            case 3:
                canvas.drawCircle(f2, f3, b, this.s);
                this.t.setTextSize(d);
                dbE_(canvas, this.ak, f2, f3, this.t);
                break;
            case 4:
                canvas.drawCircle(f2, f3, f4, this.ad);
                break;
            case 5:
                canvas.drawCircle(f2, f3, b, this.ae);
                this.af.setTextSize(d);
                dbE_(canvas, this.ak, f2, f3, this.af);
                break;
            case 6:
                canvas.drawCircle(f2, f3, f4, this.g);
                dbD_(canvas, f2, f3, f4, i2);
                break;
            case 7:
                canvas.drawCircle(f2, f3, 24.0f - this.g.getStrokeWidth(), this.g);
                dbD_(canvas, f2, f3, b, 6);
                this.o.setTextSize(d);
                dbE_(canvas, this.ak, f2, f3, this.o);
                break;
            case 8:
                canvas.drawCircle(f2, f3, f9435a / 2.0f, this.g);
                dbB_(canvas, f2, f3);
                break;
            case 9:
                dbC_(canvas, f2, f3);
                this.x.setTextSize(d);
                dbE_(canvas, this.ak, f2, f3, this.x);
                break;
        }
    }

    private void dbB_(Canvas canvas, float f2, float f3) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) ContextCompat.getDrawable(this.j, R.drawable._2131430977_res_0x7f0b0e41);
        if (bitmapDrawable == null) {
            LogUtil.a("DotMatrixView", "drawOvulationIcon: ", "unTargetDrawable is null");
            return;
        }
        Bitmap bitmap = bitmapDrawable.getBitmap();
        if (bitmap == null) {
            LogUtil.a("DotMatrixView", "drawOvulationIcon: ", "bitmap is null");
            return;
        }
        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new RectF(f2 - (bitmap.getWidth() / 2.0f), f3 - (bitmap.getHeight() / 2.0f), f2 + (bitmap.getWidth() / 2.0f), f3 + (bitmap.getHeight() / 2.0f)), this.u);
    }

    private void dbC_(Canvas canvas, float f2, float f3) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) ContextCompat.getDrawable(this.j, R.drawable._2131430976_res_0x7f0b0e40);
        if (bitmapDrawable == null) {
            LogUtil.a("DotMatrixView", "drawOvulationTargetIcon: ", "targetDrawable is null");
            return;
        }
        Bitmap bitmap = bitmapDrawable.getBitmap();
        if (bitmap == null) {
            LogUtil.a("DotMatrixView", "drawOvulationTargetIcon: ", "bitmap is null");
            return;
        }
        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new RectF(f2 - (bitmap.getWidth() / 2.0f), f3 - (bitmap.getHeight() / 2.0f), f2 + (bitmap.getWidth() / 2.0f), f3 + (bitmap.getHeight() / 2.0f)), this.v);
    }

    private float b(int i2, int i3) {
        if (i2 != 0 || i3 != 0) {
            if (this.h[i2][i3] == 8) {
                return f9435a;
            }
            return e;
        }
        int i4 = this.h[i2][i3];
        if (i4 == 1 || i4 == 3 || i4 == 5 || i4 == 7) {
            return b;
        }
        if (i4 == 8) {
            return f9435a;
        }
        if (i4 == 9) {
            return c;
        }
        return e;
    }

    private void dbA_(Canvas canvas, float f2, float f3, int i2) {
        Path path = new Path();
        float f4 = e;
        float f5 = f3 - f4;
        path.moveTo(f2, f5);
        float f6 = f2 * 2.0f;
        float f7 = this.ah;
        float f8 = f4 / 3.0f;
        path.quadTo((f6 + f7) / 2.0f, f8 + f3, f7 + f2, f5);
        float f9 = f4 + f3;
        path.lineTo(this.ah + f2, f9);
        path.quadTo((f6 + this.ah) / 2.0f, f3 - f8, f2, f9);
        path.lineTo(f2, f5);
        switch (i2) {
            case 0:
            case 1:
                canvas.drawPath(path, this.q);
                break;
            case 2:
            case 3:
                canvas.drawPath(path, this.m);
                break;
            case 4:
            case 5:
                canvas.drawPath(path, this.z);
                break;
            case 6:
                this.k.setTextSize(nsn.c(this.j, 8.0f));
                char[] cArr = new char[64];
                Arrays.fill(cArr, FilenameUtils.EXTENSION_SEPARATOR);
                canvas.drawTextOnPath(cArr, 0, 64, path, 0.0f, 0.0f, this.k);
                break;
            case 7:
                canvas.drawPath(path, this.n);
                break;
            case 8:
            case 9:
                canvas.drawPath(path, this.y);
                break;
        }
    }

    private void dbD_(Canvas canvas, float f2, float f3, float f4, int i2) {
        for (int i3 = 0; i3 < 10; i3++) {
            canvas.save();
            Pair<Float, Float> dby_ = dby_(f2, f3, 0.05f * f4, i3 * (6.283185307179586d / 10));
            canvas.translate(((Float) dby_.first).floatValue(), ((Float) dby_.second).floatValue());
            canvas.rotate((i3 * 360.0f) / 10);
            if (i2 == 7) {
                canvas.drawRoundRect(new RectF((-f4) * 0.15f, 1.2f * f4, 0.15f * f4, 0.7f * f4), f4, f4, this.n);
            } else {
                canvas.drawCircle(0.85f * f4, 0.0f, 0.15f * f4, this.o);
            }
            canvas.restore();
        }
    }

    private Pair<Float, Float> dby_(float f2, float f3, float f4, double d2) {
        double d3 = f4;
        return new Pair<>(Float.valueOf((float) (f2 + (Math.cos(d2) * d3))), Float.valueOf((float) (f3 + (d3 * Math.sin(d2)))));
    }

    private void dbE_(Canvas canvas, String str, float f2, float f3, Paint paint) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("DotMatrixView", "toady is null,return");
            return;
        }
        float measureText = paint.measureText(str);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float f4 = fontMetrics.descent;
        float f5 = f3 + (((f4 - fontMetrics.ascent) / 2.0f) - f4);
        float f6 = f2 - (measureText / 2.0f);
        if (LanguageUtil.bc(this.j)) {
            canvas.save();
            canvas.scale(-1.0f, 1.0f, f2, getHeight());
            canvas.drawText(str, f6, f5, paint);
            canvas.restore();
            return;
        }
        canvas.drawText(str, f6, f5, paint);
    }
}
