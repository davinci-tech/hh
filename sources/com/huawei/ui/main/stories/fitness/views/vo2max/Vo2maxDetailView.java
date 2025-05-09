package com.huawei.ui.main.stories.fitness.views.vo2max;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import defpackage.pyb;
import defpackage.qbe;
import defpackage.qbf;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class Vo2maxDetailView extends View {
    private float aa;
    private float ab;
    private float ac;
    private float ad;
    private ArrayList<qbf> ae;
    private RectF af;
    private float ag;
    private float ah;
    private float ai;
    private float aj;
    private float ak;
    private float al;
    private int am;
    private float an;
    private float g;
    private float h;
    private float j;
    private Paint k;
    private List<String> l;
    private Paint m;
    private float n;
    private float o;
    private ArrayList<qbe> p;
    private float q;
    private Context r;
    private float s;
    private qbf t;
    private int u;
    private float v;
    private boolean w;
    private int x;
    private int y;
    private float z;
    private static final int f = Color.parseColor("#ff3320");
    private static final int d = Color.parseColor("#fb6522");
    private static final int c = Color.parseColor("#f5a623");
    private static final int e = Color.parseColor("#e6d420");
    private static final int b = Color.parseColor("#75df3e");
    private static final int i = Color.parseColor("#01c1f2");

    /* renamed from: a, reason: collision with root package name */
    private static final int f10003a = Color.parseColor("#1f8dff");

    public Vo2maxDetailView(Context context) {
        super(context);
        this.aa = 0.0f;
        this.ad = 0.0f;
        this.z = 0.0f;
        this.ac = 0.0f;
        this.ab = 0.0f;
        this.p = new ArrayList<>();
        this.l = new ArrayList();
        this.w = false;
        this.y = 50;
        this.u = 42;
        this.x = 2;
        this.am = 1;
        this.m = new Paint();
        this.ae = new ArrayList<>();
        this.k = new Paint();
        this.j = 23.0f;
        this.h = 2.0f;
        this.af = new RectF();
        this.ag = 0.0f;
        this.ai = 0.0f;
        this.ah = 50.0f;
        this.an = 50.0f;
        this.r = context;
        e();
    }

    public Vo2maxDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.aa = 0.0f;
        this.ad = 0.0f;
        this.z = 0.0f;
        this.ac = 0.0f;
        this.ab = 0.0f;
        this.p = new ArrayList<>();
        this.l = new ArrayList();
        this.w = false;
        this.y = 50;
        this.u = 42;
        this.x = 2;
        this.am = 1;
        this.m = new Paint();
        this.ae = new ArrayList<>();
        this.k = new Paint();
        this.j = 23.0f;
        this.h = 2.0f;
        this.af = new RectF();
        this.ag = 0.0f;
        this.ai = 0.0f;
        this.ah = 50.0f;
        this.an = 50.0f;
        this.r = context;
        e();
    }

    private void e() {
        this.n = pyb.b(1, 2.0f);
        Paint paint = new Paint();
        this.m = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.m.setColor(getResources().getColor(R.color._2131299244_res_0x7f090bac));
        this.m.setStrokeWidth(this.n);
    }

    public void setType(int i2) {
        this.am = i2;
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        this.ak = getDefaultSize(getSuggestedMinimumWidth(), i2);
        this.aj = getDefaultSize(getSuggestedMinimumHeight(), i3);
        this.z = pyb.b(1, 6.0f);
        this.ad = pyb.b(1, 40.0f);
        this.ab = pyb.b(1, 20.0f);
        Paint paint = new Paint();
        paint.setTextSize(pyb.b(0, this.r.getResources().getDimension(R.dimen._2131362809_res_0x7f0a03f9)));
        this.aa = paint.measureText("00");
        float measureText = paint.measureText("000000");
        this.ac = measureText;
        this.v = (this.ak - this.aa) - measureText;
        this.s = this.aj - this.ab;
        this.o = pyb.b(1, 6.0f);
        this.q = pyb.b(1, 16.0f);
        this.g = pyb.b(1, 3.0f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        dxU_(canvas);
        dxT_(canvas);
        dxW_(canvas);
        dxV_(canvas);
        dxS_(canvas, this.t);
    }

    private void dxV_(Canvas canvas) {
        boolean z;
        if (koq.b(this.p)) {
            return;
        }
        Path path = new Path();
        boolean z2 = true;
        if (this.p.size() - 1 == 0) {
            LogUtil.h("Track_Vo2maxDetailView", "mDataSet size should be more than one");
            return;
        }
        this.al = this.v / (this.p.size() - 1);
        if (this.t == null) {
            this.ae.clear();
            z = true;
        } else {
            z = false;
        }
        for (int i2 = 0; i2 < this.p.size(); i2++) {
            if (this.p.get(i2).a() != 0) {
                float f2 = i2;
                float f3 = this.aa + (this.al * f2);
                if (LanguageUtil.bc(BaseApplication.getContext())) {
                    f3 = (this.ac + this.v) - (this.al * f2);
                }
                float d2 = d(this.p.get(i2).a());
                e(f3, d2, z, i2);
                if (z2) {
                    path.moveTo(f3, d2);
                    z2 = false;
                } else {
                    path.lineTo(f3, d2);
                }
            }
        }
        canvas.drawPath(path, this.m);
        for (int i3 = 0; i3 < this.p.size(); i3++) {
            if (this.p.get(i3).a() != 0) {
                float f4 = i3;
                float f5 = this.aa + (this.al * f4);
                if (LanguageUtil.bc(BaseApplication.getContext())) {
                    f5 = (this.ac + this.v) - (this.al * f4);
                }
                float d3 = d(this.p.get(i3).a());
                canvas.drawBitmap(dxY_(this.p.get(i3).c()), f5 - (r2.getHeight() / 2.0f), d3 - (r2.getHeight() / 2.0f), (Paint) null);
            }
        }
    }

    private void e(float f2, float f3, boolean z, int i2) {
        if (z) {
            qbf qbfVar = new qbf();
            qbfVar.dxQ_(new PointF(f2, ((f3 - (this.o / 2.0f)) - this.h) - this.g));
            if (koq.d(this.p, i2) && this.p.get(i2) != null) {
                qbfVar.d(this.p.get(i2).c());
                qbfVar.b(this.p.get(i2).a());
                qbfVar.b(UnitUtil.e(this.p.get(i2).a(), 1, 0));
            }
            float f4 = this.q;
            qbfVar.dxR_(new RectF(f2 - f4, f3 - f4, f2 + f4, f3 + f4));
            this.ae.add(qbfVar);
            qbf qbfVar2 = this.t;
            if (qbfVar2 == null || qbfVar2.c() <= qbfVar.c()) {
                this.t = qbfVar;
            }
        }
    }

    private Bitmap dxY_(int i2) {
        return BitmapFactory.decodeResource(this.r.getResources(), i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 4 ? i2 != 5 ? i2 != 6 ? R.drawable._2131428396_res_0x7f0b042c : R.drawable._2131428398_res_0x7f0b042e : R.drawable._2131428394_res_0x7f0b042a : R.drawable._2131428395_res_0x7f0b042b : R.drawable._2131428399_res_0x7f0b042f : R.drawable._2131428393_res_0x7f0b0429 : R.drawable._2131428397_res_0x7f0b042d);
    }

    private float d(float f2) {
        int i2 = this.y;
        int i3 = this.u;
        int i4 = i2 - i3;
        if (i4 <= 0) {
            LogUtil.h("Track_Vo2maxDetailView", "mMaxVo2max - mMinVo2max == 0");
            return 0.0f;
        }
        float f3 = this.ad;
        return f3 + ((this.s - f3) * (1.0f - ((f2 - i3) / i4)));
    }

    private void dxW_(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(-5394769);
        paint.setStrokeWidth(1.0f);
        paint.setPathEffect(new DashPathEffect(new float[]{4.0f, 2.0f, 4.0f, 2.0f}, 1.0f));
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(getResources().getColor(R.color._2131297796_res_0x7f090604));
        CornerPathEffect cornerPathEffect = new CornerPathEffect(10.0f);
        paint.setStrokeWidth(1.0f);
        paint2.setPathEffect(cornerPathEffect);
        Paint paint3 = new Paint();
        paint3.setColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        paint3.setTextSize(pyb.b(0, this.r.getResources().getDimension(R.dimen._2131362809_res_0x7f0a03f9)));
        paint3.setAntiAlias(true);
        float f2 = this.z;
        canvas.drawLine(0.0f, f2, this.ak, f2, paint2);
        e eVar = new e();
        eVar.dyi_(canvas);
        eVar.dyj_(paint);
        eVar.dyk_(new Path());
        eVar.dyl_(paint3);
        eVar.d(pyb.e().dvr_(paint, "0") + 5.0f);
        eVar.c(0.0f);
        e(eVar, this.y);
        e(eVar, this.y - this.x);
        e(eVar, this.y - (this.x * 2));
        e(eVar, this.y - (this.x * 3));
    }

    private void e(e eVar, int i2) {
        float d2 = d(i2);
        Path dyg_ = eVar.dyg_();
        dyg_.moveTo(0.0f, d2);
        dyg_.lineTo(this.ak, d2);
        Canvas dye_ = eVar.dye_();
        Paint dyf_ = eVar.dyf_();
        dye_.drawPath(dyg_, dyf_);
        if (this.w) {
            String e2 = UnitUtil.e(i2, 1, 0);
            float a2 = eVar.a();
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                a2 = (this.ak - dyf_.measureText(e2)) - 50.0f;
            }
            if (i2 >= 0) {
                float f2 = this.v;
                float f3 = this.ac;
                dye_.drawText(e2, (((f2 + f3) - a2) - dyf_.measureText(e2)) - 10.0f, d2 + eVar.e() + 20.0f, eVar.dyh_());
            }
        }
    }

    private void dxU_(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color._2131297796_res_0x7f090604));
        paint.setStrokeWidth(1.0f);
        float f2 = this.s;
        canvas.drawLine(0.0f, f2, this.ak, f2, paint);
    }

    private void dxT_(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        paint.setTextSize(pyb.b(0, this.r.getResources().getDimension(R.dimen._2131362808_res_0x7f0a03f8)));
        paint.setAntiAlias(true);
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            String str = this.l.get(i2);
            float measureText = (int) paint.measureText(str, 0, str.length());
            float size = (this.aa + ((this.v / (this.l.size() - 1)) * i2)) - (measureText / 2.0f);
            if (size < 0.0f) {
                size = 0.0f;
            }
            float f2 = this.ak;
            if (size + measureText > f2) {
                size = f2 - measureText;
            }
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                size = (this.ak - size) - measureText;
            }
            canvas.drawText(str, size, this.aj - (this.ab / 3.0f), paint);
        }
    }

    private void dxS_(Canvas canvas, qbf qbfVar) {
        if (!this.w || this.t == null) {
            return;
        }
        this.k.setAntiAlias(true);
        this.k.setTextSize(pyb.b(1, 12.0f));
        String b2 = qbfVar.b();
        float dvs_ = pyb.e().dvs_(this.k);
        this.ag = pyb.e().dvt_(this.k, b2) + (this.j * 2.0f);
        this.ai = (this.h * 2.0f) + dvs_;
        float b3 = pyb.b(1, 4.0f);
        float f2 = qbfVar.dxO_().x;
        float f3 = qbfVar.dxO_().y;
        float f4 = this.ag / 2.0f;
        this.af.left = f2 - f4;
        this.af.right = f2 + f4;
        this.af.top = (f3 - this.ai) - b3;
        this.af.bottom = f3;
        float f5 = 1.5f * b3;
        this.af.top -= f5;
        this.af.bottom -= f5;
        float width = this.af.left + (this.af.width() * 0.5f);
        float f6 = this.af.bottom;
        Path path = new Path();
        path.moveTo(width + b3, f6);
        path.lineTo(width, f5 + f6);
        path.lineTo(width - b3, f6);
        path.close();
        b bVar = new b();
        bVar.dyc_(canvas);
        bVar.b(qbfVar);
        bVar.c(b3);
        bVar.d(width);
        bVar.b(f6);
        bVar.dyd_(path);
        b(bVar);
        dxX_(canvas, b2, dvs_);
    }

    private void dxX_(Canvas canvas, String str, float f2) {
        float f3 = this.af.left;
        float f4 = this.j;
        float f5 = this.af.top;
        float f6 = this.h;
        this.k.setColor(-1);
        canvas.drawText(str, f3 + f4, ((f5 + f6) + f2) - 2.0f, this.k);
    }

    private void b(b bVar) {
        if (this.af.right > this.ak) {
            this.af.left -= this.af.right - this.ak;
            this.af.right = this.ak;
        }
        if (this.af.left < 0.0f) {
            this.af.right -= this.af.left;
            this.af.left = 0.0f;
        }
        this.k.setColor(a(bVar.d().d()));
        Canvas dya_ = bVar.dya_();
        dya_.drawRoundRect(this.af, this.ah, this.an, this.k);
        Path dyb_ = bVar.dyb_();
        dya_.drawPath(dyb_, this.k);
        dyb_.reset();
        this.k.setStrokeWidth(2.0f);
        float a2 = bVar.a();
        float b2 = bVar.b();
        float c2 = bVar.c();
        dya_.drawLine(a2 - b2, c2, a2 + b2, c2, this.k);
    }

    private int a(int i2) {
        switch (i2) {
            case 0:
                return f;
            case 1:
                return d;
            case 2:
                return c;
            case 3:
                return e;
            case 4:
                return b;
            case 5:
                return i;
            case 6:
                return f10003a;
            default:
                return e;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            LogUtil.h("Track_Vo2maxDetailView", "onTouchEvent event == null");
            return false;
        }
        if (motionEvent.getAction() != 0) {
            return true;
        }
        d(motionEvent.getX(), motionEvent.getY());
        return true;
    }

    private void d(float f2, float f3) {
        int i2 = 0;
        while (true) {
            if (i2 >= this.ae.size()) {
                break;
            }
            qbf qbfVar = this.ae.get(i2);
            if (dxZ_(f2, f3, qbfVar.dxP_())) {
                LogUtil.a("Track_Vo2maxDetailView", "triggerClick eventX = ", Float.valueOf(f2), " eventY = ", Float.valueOf(f3), "  data.mRect = ", qbfVar.dxP_().toString());
                this.t = qbfVar;
                break;
            }
            i2++;
        }
        invalidate();
    }

    private boolean dxZ_(float f2, float f3, RectF rectF) {
        return f2 >= rectF.left && f2 <= rectF.right && f3 >= rectF.top && f3 <= rectF.bottom;
    }

    private int getInterval() {
        return this.am != 2 ? 2 : 5;
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private Paint f10005a;
        private float b;
        private float c;
        private Canvas d;
        private Path e;
        private Paint j;

        e() {
        }

        public Canvas dye_() {
            return this.d;
        }

        public void dyi_(Canvas canvas) {
            this.d = canvas;
        }

        public Paint dyf_() {
            return this.f10005a;
        }

        public void dyj_(Paint paint) {
            this.f10005a = paint;
        }

        public Path dyg_() {
            return this.e;
        }

        public void dyk_(Path path) {
            this.e = path;
        }

        public Paint dyh_() {
            return this.j;
        }

        public void dyl_(Paint paint) {
            this.j = paint;
        }

        public float e() {
            return this.b;
        }

        public void d(float f) {
            this.b = f;
        }

        public float a() {
            return this.c;
        }

        public void c(float f) {
            this.c = f;
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private float f10004a;
        private qbf b;
        private float c;
        private Canvas d;
        private float e;
        private Path f;

        b() {
        }

        public Canvas dya_() {
            return this.d;
        }

        public void dyc_(Canvas canvas) {
            this.d = canvas;
        }

        public qbf d() {
            return this.b;
        }

        public void b(qbf qbfVar) {
            this.b = qbfVar;
        }

        public float b() {
            return this.e;
        }

        public void c(float f) {
            this.e = f;
        }

        public float a() {
            return this.c;
        }

        public void d(float f) {
            this.c = f;
        }

        public float c() {
            return this.f10004a;
        }

        public void b(float f) {
            this.f10004a = f;
        }

        public Path dyb_() {
            return this.f;
        }

        public void dyd_(Path path) {
            this.f = path;
        }
    }
}
