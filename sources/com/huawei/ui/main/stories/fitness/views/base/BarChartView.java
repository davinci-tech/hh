package com.huawei.ui.main.stories.fitness.views.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.utils.FitnessUtils;
import defpackage.ixx;
import defpackage.jec;
import defpackage.pyb;
import defpackage.pyf;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class BarChartView extends View {

    /* renamed from: a, reason: collision with root package name */
    private float f9928a;
    private double aa;
    private float ab;
    private float ac;
    private float ad;
    private RectF ae;
    private float af;
    private float ag;
    private ArrayList<pyf> ah;
    private float ai;
    private int aj;
    private float ak;
    private float al;
    private float am;
    private float an;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private LinearGradient f;
    private float g;
    private ArrayList<pyf> h;
    private int i;
    private float j;
    private int k;
    private int l;
    private int m;
    private Paint n;
    private int o;
    private Paint p;
    private List<Double> q;
    private float r;
    private List<Double> s;
    private RectF t;
    private List<String> u;
    private ArrayList<Double> v;
    private String w;
    private Context x;
    private int y;
    private float z;

    public BarChartView(Context context) {
        super(context);
        this.aj = 0;
        this.d = true;
        this.f = null;
        this.ad = 50.0f;
        this.af = 50.0f;
        this.ac = 0.0f;
        this.aa = 200.0d;
        this.s = new ArrayList();
        this.q = new ArrayList();
        this.u = new ArrayList();
        this.p = new Paint();
        this.ah = new ArrayList<>();
        this.h = new ArrayList<>();
        this.v = new ArrayList<>();
        this.w = "";
        this.y = 1000;
        this.e = true;
        this.b = true;
        this.c = false;
        this.n = new Paint();
        this.i = 0;
        this.g = 15.0f;
        this.j = 5.0f;
        this.ae = new RectF();
        this.ai = 0.0f;
        this.ag = 0.0f;
        this.an = 50.0f;
        this.ak = 50.0f;
        this.x = context;
    }

    public BarChartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.aj = 0;
        this.d = true;
        this.f = null;
        this.ad = 50.0f;
        this.af = 50.0f;
        this.ac = 0.0f;
        this.aa = 200.0d;
        this.s = new ArrayList();
        this.q = new ArrayList();
        this.u = new ArrayList();
        this.p = new Paint();
        this.ah = new ArrayList<>();
        this.h = new ArrayList<>();
        this.v = new ArrayList<>();
        this.w = "";
        this.y = 1000;
        this.e = true;
        this.b = true;
        this.c = false;
        this.n = new Paint();
        this.i = 0;
        this.g = 15.0f;
        this.j = 5.0f;
        this.ae = new RectF();
        this.ai = 0.0f;
        this.ag = 0.0f;
        this.an = 50.0f;
        this.ak = 50.0f;
        this.x = context;
    }

    public void setIsDrawMaxDashLineLable(boolean z) {
        this.b = z;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.al = getDefaultSize(getSuggestedMinimumWidth(), i);
        this.am = getDefaultSize(getSuggestedMinimumHeight(), i2);
        Paint paint = new Paint();
        paint.setTextSize(pyb.b(1, 12.0f));
        Calendar calendar = Calendar.getInstance();
        calendar.set(jec.d(), jec.a(), 1, 20, 0);
        String a2 = UnitUtil.a(calendar.getTime(), 1);
        float measureText = (paint.measureText(a2, 0, a2.length()) / 2.0f) + 5.0f;
        this.ad = measureText;
        this.af = measureText;
        this.ac = pyb.b(1, 20.0f);
        this.z = ((this.al - this.ad) - this.af) - pyb.e().dvt_(paint, "00000");
        this.ab = this.am - this.ac;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        dvo_(canvas);
        dvn_(canvas);
        dvl_(canvas);
        dvm_(canvas);
        if (this.c) {
            return;
        }
        dvk_(canvas, this.h);
    }

    public void setPadding(float f, float f2) {
        this.ad = f;
        this.af = f2;
    }

    public void setBarColor(int i, int i2) {
        this.k = i;
        this.o = i2;
    }

    public void setBarTwoColor(int i, int i2) {
        this.m = i;
        this.l = i2;
    }

    public void setDiagramAnchorType(int i) {
        this.y = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void dvl_(android.graphics.Canvas r33) {
        /*
            Method dump skipped, instructions count: 521
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.fitness.views.base.BarChartView.dvl_(android.graphics.Canvas):void");
    }

    private float a(double d) {
        return (this.ab - this.r) - e(d);
    }

    private float e(double d) {
        return (float) (((this.ab - this.r) * d) / this.aa);
    }

    private void dvm_(Canvas canvas) {
        int i;
        float f;
        double d;
        double d2;
        int i2;
        String e;
        Paint paint = new Paint();
        Path path = new Path();
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{4.0f, 2.0f, 4.0f, 2.0f}, 1.0f);
        int i3 = 0;
        int i4 = 0;
        while (i4 < this.v.size()) {
            paint.setStrokeWidth(pyb.b(1, 0.5f));
            paint.setStyle(Paint.Style.STROKE);
            if (this.c) {
                paint.setColor(ViewCompat.MEASURED_SIZE_MASK);
            } else {
                paint.setColor(i3);
            }
            paint.setAlpha(78);
            double doubleValue = this.v.get(i4).doubleValue();
            if (doubleValue <= 0.0d) {
                i2 = i3;
            } else {
                float a2 = a(doubleValue);
                if (i4 == 0) {
                    paint.setStrokeWidth(pyb.b(1, 0.5f));
                    i = 127;
                    f = 0.0f;
                    d = doubleValue;
                    canvas.drawLine(0.0f, a2, this.al, a2, paint);
                } else {
                    i = 127;
                    f = 0.0f;
                    d = doubleValue;
                    paint.setPathEffect(dashPathEffect);
                    paint.setAlpha(127);
                    path.moveTo(0.0f, a2);
                    path.lineTo(this.al, a2);
                    canvas.drawPath(path, paint);
                    path.reset();
                }
                if (this.b) {
                    d2 = d;
                } else {
                    d2 = d;
                    if (d2 == this.aa) {
                        i2 = 0;
                    }
                }
                if (this.c) {
                    paint.setColor(ViewCompat.MEASURED_SIZE_MASK);
                } else {
                    paint.setColor(0);
                }
                paint.setAlpha(i);
                paint.setPathEffect(dashPathEffect);
                paint.setStyle(Paint.Style.FILL);
                paint.setTextSize(pyb.b(1, 11.0f));
                int i5 = this.y;
                if (i5 == 1001) {
                    i2 = 0;
                    e = e((int) d2);
                } else if (i5 == 1003) {
                    i2 = 0;
                    e = UnitUtil.e(CommonUtil.c(d2, 2), 1, 2);
                } else if (i5 == 1004) {
                    i2 = 0;
                    e = UnitUtil.h() ? String.valueOf(UnitUtil.e(UnitUtil.e(d2, 1), 1, 2)) : UnitUtil.e(d2, 1, 1);
                } else {
                    i2 = 0;
                    e = UnitUtil.e(d2, 1, 0);
                }
                float dvt_ = pyb.e().dvt_(paint, e);
                float dvs_ = pyb.e().dvs_(paint);
                float f2 = this.al - dvt_;
                if (LanguageUtil.bc(BaseApplication.getContext())) {
                    f2 = f;
                }
                canvas.drawText(e, f2, a2 + dvs_, paint);
            }
            i4++;
            i3 = i2;
        }
    }

    private void dvo_(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(0);
        paint.setAlpha(25);
        paint.setStrokeWidth(1.0f);
        float f = this.ab;
        canvas.drawLine(0.0f, f, this.al, f, paint);
    }

    private void dvn_(Canvas canvas) {
        Paint paint = new Paint();
        if (this.c) {
            paint.setColor(-1);
        } else {
            paint.setColor(Integer.MIN_VALUE);
        }
        paint.setTextSize(pyb.b(1, 11.0f));
        paint.setAntiAlias(true);
        for (int i = 0; i < this.u.size(); i++) {
            String str = this.u.get(i);
            float measureText = (int) paint.measureText(str, 0, str.length());
            float f = i;
            float f2 = measureText / 2.0f;
            float size = (this.ad + ((this.z / (this.u.size() - 1)) * f)) - f2;
            if (size < 0.0f) {
                size = 0.0f;
            }
            float f3 = this.al;
            if (size + measureText > f3) {
                size = f3 - measureText;
            }
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                size = ((this.al - this.ad) - ((this.z / (this.u.size() - 1)) * f)) - f2;
            }
            canvas.drawText(str, size, this.am - (this.ac / 3.0f), paint);
        }
    }

    private void dvk_(Canvas canvas, ArrayList<pyf> arrayList) {
        String str;
        float width;
        float f;
        Path path;
        int i = 1;
        this.n.setAntiAlias(true);
        this.n.setTextSize(pyb.b(1, 12.0f));
        int i2 = 0;
        int i3 = 0;
        while (i3 < arrayList.size()) {
            pyf pyfVar = arrayList.get(i3);
            int i4 = this.y;
            if (1001 == i4) {
                str = e((int) pyfVar.f16342a);
            } else if (1002 == i4) {
                if (pyfVar.f16342a > 0.0d && pyfVar.f16342a < 0.5d) {
                    str = "<1" + this.w;
                } else {
                    str = UnitUtil.e(pyfVar.f16342a, i, i2) + " " + this.w;
                }
            } else if (1003 == i4) {
                str = UnitUtil.e(pyfVar.f16342a, i, 2) + " " + this.w;
            } else if (1004 == i4) {
                str = UnitUtil.e(pyfVar.f16342a, i, i) + " " + this.w;
                if (UnitUtil.h() && this.x != null) {
                    str = String.valueOf(UnitUtil.e(UnitUtil.e(pyfVar.f16342a, i), i, 2)) + " " + this.x.getString(R$string.IDS_ft);
                }
            } else {
                str = UnitUtil.e(pyfVar.f16342a, i, i2) + " " + this.w;
            }
            String str2 = str;
            int i5 = pyfVar.c;
            float f2 = pyfVar.b.x;
            float f3 = pyfVar.b.y;
            float dvs_ = pyb.e().dvs_(this.n);
            float dvt_ = pyb.e().dvt_(this.n, str2) + (this.g * 2.0f);
            this.ai = dvt_;
            this.ag = (this.j * 2.0f) + dvs_;
            float f4 = dvt_ / 2.0f;
            float b = pyb.b(i, 4.0f);
            if (i5 == i) {
                this.ae.left = f2 - f4;
                this.ae.right = f2 + f4;
                this.ae.top = (f3 - this.ag) - b;
                this.ae.bottom = f3;
                float f5 = 1.5f * b;
                this.ae.top -= f5;
                this.ae.bottom -= f5;
                width = (this.ae.width() * 0.5f) + this.ae.left;
                float f6 = this.ae.bottom;
                Path path2 = new Path();
                path2.moveTo(width + b, f6);
                path2.lineTo(width, f6 + f5);
                path2.lineTo(width - b, f6);
                path2.close();
                f = f6;
                path = path2;
            } else if (i5 != 2) {
                path = null;
                width = 0.0f;
                f = 0.0f;
            } else {
                this.ae.left = f2 - f4;
                this.ae.right = f4 + f2;
                this.ae.top = f3;
                this.ae.bottom = this.ag + f3 + b;
                float f7 = 1.5f * b;
                this.ae.top += f7;
                this.ae.bottom += f7;
                width = this.ae.left + (this.ae.width() * 0.5f);
                float f8 = this.ae.top;
                Path path3 = new Path();
                path3.moveTo(f2, f3);
                path3.lineTo(width + b, f8);
                path3.lineTo(width - b, f8);
                path3.close();
                path = path3;
                f = f8;
            }
            if (this.ae.right > this.al) {
                this.ae.left -= this.ae.right - this.al;
                this.ae.right = this.al;
            }
            if (this.ae.left < 0.0f) {
                this.ae.right -= this.ae.left;
                this.ae.left = 0.0f;
            }
            this.n.setColor(this.i);
            canvas.drawRoundRect(this.ae, this.an, this.ak, this.n);
            if (path != null) {
                canvas.drawPath(path, this.n);
                path.reset();
            }
            this.n.setStrokeWidth(2.0f);
            canvas.drawLine(width - b, f, width + b, f, this.n);
            float f9 = this.ae.left;
            float f10 = this.g;
            float f11 = this.ae.top;
            float f12 = this.j;
            this.n.setColor(-1);
            canvas.drawText(str2, f9 + f10, ((f11 + f12) + dvs_) - 2.0f, this.n);
            i3++;
            i = 1;
            i2 = 0;
        }
    }

    public void setAnchorBackground(int i) {
        this.i = i;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 2 || motionEvent.getAction() == 0) {
            dvq_(motionEvent);
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.d = true;
        }
        return true;
    }

    private void dvq_(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        this.h.clear();
        int i = 0;
        while (true) {
            if (i >= this.ah.size()) {
                break;
            }
            pyf pyfVar = this.ah.get(i);
            if (dvp_(x, y, pyfVar.e)) {
                this.h.add(pyfVar);
                if (this.d) {
                    String d = FitnessUtils.d(this.x.getClass().getSimpleName());
                    if ("Step".equals(d) || "Calorie".equals(d) || "Distance".equals(d) || "Climb".equals(d)) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("click", "1");
                        hashMap.put("activityName", d);
                        hashMap.put("barSize", Integer.valueOf(this.aj));
                        ixx.d().d(this.x, AnalyticsValue.HEALTH_DETAIL_ANCHOR_CLICK_21300009.value(), hashMap, 0);
                    }
                    this.d = false;
                }
            } else {
                i++;
            }
        }
        invalidate();
    }

    private boolean dvp_(float f, float f2, RectF rectF) {
        return f >= rectF.left && f <= rectF.right && f2 >= rectF.top && f2 <= rectF.bottom;
    }

    private String e(int i) {
        int i2 = i / 60;
        int i3 = i % 60;
        String string = this.x.getString(R$string.IDS_messagecenter_time_hour_value);
        String string2 = this.x.getString(R$string.IDS_band_data_sleep_unit_m);
        String str = "";
        if (i2 > 0) {
            str = "" + UnitUtil.e(i2, 1, 0) + string;
        }
        if (i3 <= 0) {
            return str;
        }
        return str + UnitUtil.e(i3, 1, 0) + string2;
    }
}
