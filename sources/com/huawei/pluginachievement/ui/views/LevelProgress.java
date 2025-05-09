package com.huawei.pluginachievement.ui.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.mjv;
import defpackage.mlc;
import defpackage.mlu;
import defpackage.nsn;
import health.compact.a.SharedPreferenceManager;

/* loaded from: classes9.dex */
public class LevelProgress extends View {

    /* renamed from: a, reason: collision with root package name */
    private static final Integer[] f8468a;
    private static final Integer[] b;
    private static final Integer[] c;
    private static final Integer[] d;
    private static final Integer[] e;
    private float ac;
    private int f;
    private RectF g;
    private int h;
    private Context i;
    private int j;
    private Paint k;
    private Paint l;
    private int m;
    private Paint n;
    private OnDownActionListener o;
    private int p;
    private double q;
    private Paint r;
    private Paint s;
    private int t;
    private float u;
    private String v;
    private Rect w;
    private Paint x;
    private float y;

    public interface OnDownActionListener {
        void onDown(int i, int i2);
    }

    private boolean c(int i, int i2, int i3, int i4) {
        return i == i2 || i == i3 || i == i4;
    }

    static {
        Integer valueOf = Integer.valueOf(R.mipmap._2131821358_res_0x7f11032e);
        Integer valueOf2 = Integer.valueOf(R.mipmap._2131821362_res_0x7f110332);
        Integer valueOf3 = Integer.valueOf(R.mipmap._2131821359_res_0x7f11032f);
        Integer valueOf4 = Integer.valueOf(R.mipmap._2131821361_res_0x7f110331);
        f8468a = new Integer[]{valueOf, valueOf2, valueOf3, valueOf4, Integer.valueOf(R.mipmap._2131821360_res_0x7f110330), Integer.valueOf(R.mipmap._2131821357_res_0x7f11032d), valueOf4};
        Integer valueOf5 = Integer.valueOf(R.mipmap._2131821364_res_0x7f110334);
        Integer valueOf6 = Integer.valueOf(R.mipmap._2131821368_res_0x7f110338);
        Integer valueOf7 = Integer.valueOf(R.mipmap._2131821365_res_0x7f110335);
        Integer valueOf8 = Integer.valueOf(R.mipmap._2131821367_res_0x7f110337);
        d = new Integer[]{valueOf5, valueOf6, valueOf7, valueOf8, Integer.valueOf(R.mipmap._2131821366_res_0x7f110336), Integer.valueOf(R.mipmap._2131821363_res_0x7f110333), valueOf8};
        Integer valueOf9 = Integer.valueOf(R.mipmap._2131821378_res_0x7f110342);
        Integer valueOf10 = Integer.valueOf(R.mipmap._2131821382_res_0x7f110346);
        Integer valueOf11 = Integer.valueOf(R.mipmap._2131821379_res_0x7f110343);
        Integer valueOf12 = Integer.valueOf(R.mipmap._2131821381_res_0x7f110345);
        c = new Integer[]{valueOf9, valueOf10, valueOf11, valueOf12, Integer.valueOf(R.mipmap._2131821380_res_0x7f110344), Integer.valueOf(R.mipmap._2131821377_res_0x7f110341), valueOf12};
        e = new Integer[]{Integer.valueOf(R.color._2131298714_res_0x7f09099a), Integer.valueOf(R.color._2131298718_res_0x7f09099e), Integer.valueOf(R.color._2131298715_res_0x7f09099b), Integer.valueOf(R.color._2131298717_res_0x7f09099d), Integer.valueOf(R.color._2131298716_res_0x7f09099c), Integer.valueOf(R.color._2131298712_res_0x7f090998), Integer.valueOf(R.color._2131298713_res_0x7f090999)};
        b = new Integer[]{Integer.valueOf(R.color._2131298699_res_0x7f09098b), Integer.valueOf(R.color._2131298709_res_0x7f090995), Integer.valueOf(R.color._2131298700_res_0x7f09098c), Integer.valueOf(R.color._2131298708_res_0x7f090994), Integer.valueOf(R.color._2131298701_res_0x7f09098d), Integer.valueOf(R.color._2131298697_res_0x7f090989), Integer.valueOf(R.color._2131298698_res_0x7f09098a)};
    }

    public LevelProgress(Context context) {
        this(context, null);
    }

    public LevelProgress(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LevelProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m = 0;
        this.o = null;
        this.i = context;
        e();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
        this.j = View.MeasureSpec.getSize(i) / 2;
        this.h = nsn.c(this.i, 650.0f);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.j = i / 2;
        this.h = nsn.c(this.i, 650.0f);
        float c2 = nsn.c(this.i, 650.0f);
        this.ac = c2;
        float f = this.j;
        float f2 = this.h;
        this.g = new RectF(f - c2, f2 - c2, f + c2, f2 + c2);
    }

    private void ckQ_(Canvas canvas) {
        canvas.drawArc(this.g, 109.2f, -177.09999f, false, this.r);
    }

    private void ckN_(Canvas canvas) {
        for (int i = 0; i < mlc.h(this.p); i++) {
            this.k.setColor(this.i.getResources().getColor(e[i].intValue()));
            if (this.f == mlc.h(this.p) - 1 && i == mlc.h(this.p) - 1) {
                ckT_(canvas, i);
            } else if (i < mlc.h(this.p)) {
                ckV_(canvas, i);
            }
        }
    }

    private void ckT_(Canvas canvas, int i) {
        if (this.t != 0) {
            ckW_(canvas, i);
        }
    }

    private void ckV_(Canvas canvas, int i) {
        float f = (float) this.q;
        if (this.p == 20) {
            f = 2.0f;
        }
        if (i != mlc.h(r1) - 1) {
            canvas.drawArc(this.g, 101.5f - ((i * 3) * 7.7f), -23.099998f, false, this.k);
            return;
        }
        canvas.drawArc(this.g, 101.5f - ((i * 3) * 7.7f), -((((this.p - 1) - r12) * 7.7f) + (f * 7.7f)), false, this.k);
    }

    private void ckJ_(Canvas canvas) {
        if (this.f == mlc.h(this.p) - 1 && this.t == 0) {
            this.k.setColor(this.i.getResources().getColor(e[mlc.h(this.p) - 1].intValue()));
            ckW_(canvas, mlc.h(this.p) - 1);
        }
    }

    private void ckM_(Canvas canvas) {
        this.n.setShader(ckS_(15.4f, 1.8499999f, mjv.b()[this.f].intValue(), R.color._2131296971_res_0x7f0902cb));
        canvas.drawArc(this.g, 109.2f, -16.4f, false, this.n);
        this.n.setShader(ckS_(-2.85f, -15.4f, R.color._2131296971_res_0x7f0902cb, mjv.b()[this.f].intValue()));
        canvas.drawArc(this.g, 87.1f, -15.4f, false, this.n);
    }

    private void ckL_(Canvas canvas) {
        if (this.f == mlc.h(this.p) - 1 && this.t == 0) {
            this.n.setShader(ckS_(11.549999f, 3.85f, b[this.f].intValue(), R.color._2131296971_res_0x7f0902cb));
            canvas.drawArc(this.g, 101.5f, -7.7f, false, this.n);
        }
    }

    private void ckW_(Canvas canvas, int i) {
        float f = (float) this.q;
        if (this.p == 20) {
            f = 2.0f;
        }
        int i2 = i * 3;
        float f2 = (((r1 - 1) - i2) * 7.7f) + (f * 7.7f);
        float f3 = this.u + 1.0f;
        this.u = f3;
        if (f3 <= f2) {
            canvas.drawArc(this.g, 101.5f - (i2 * 7.7f), -f3, false, this.k);
            postInvalidateDelayed(30L);
        } else {
            canvas.drawArc(this.g, 101.5f - (i2 * 7.7f), -f2, false, this.k);
        }
    }

    private void ckO_(Canvas canvas) {
        for (int i = 1; i <= 20; i++) {
            Bitmap decodeResource = BitmapFactory.decodeResource(this.i.getResources(), d[mlc.h(i) - 1].intValue());
            if (i == 20) {
                ckK_(canvas, 3.85f + ((-i) * 7.7f), d(i), decodeResource, i);
            } else {
                ckK_(canvas, 3.85f + ((2 - i) * 7.7f), d(i), decodeResource, i);
            }
        }
    }

    private String d(int i) {
        return this.i.getResources().getString(R.string._2130840814_res_0x7f020cee, Integer.valueOf(i));
    }

    private void ckK_(Canvas canvas, float f, String str, Bitmap bitmap, int i) {
        canvas.save();
        canvas.rotate(f, this.j, this.h);
        double d2 = this.j;
        double d3 = this.ac;
        double cos = Math.cos(Math.toRadians(90.0d));
        double d4 = this.h;
        double d5 = this.ac;
        double sin = Math.sin(Math.toRadians(90.0d));
        float f2 = (float) (d2 + (d3 * cos));
        float f3 = (float) (d4 + (d5 * sin));
        canvas.drawBitmap(i <= this.p ? ckR_(bitmap, i) : BitmapFactory.decodeResource(this.i.getResources(), R.mipmap._2131821356_res_0x7f11032c), f2 - (r2.getHeight() / 2.0f), f3 - (r2.getHeight() / 2.0f), this.l);
        setTextPaintColor(i);
        canvas.drawText(str, f2 - (this.x.measureText(str) / 2.0f), (f3 - r1.getHeight()) - 10.0f, this.x);
        canvas.restore();
    }

    private void setTextPaintColor(int i) {
        this.x.setColor(this.i.getResources().getColor(R.color._2131298710_res_0x7f090996));
        int f = mlc.f(this.f + 1);
        if (c(i, f, f + 1, f + 2)) {
            this.x.setColor(this.i.getResources().getColor(R.color._2131298711_res_0x7f090997));
        }
    }

    private Bitmap ckR_(Bitmap bitmap, int i) {
        int i2 = this.p;
        if (i2 != i) {
            return bitmap;
        }
        int h = mlc.h(i2);
        Integer[] numArr = f8468a;
        if (h > numArr.length) {
            return bitmap;
        }
        return BitmapFactory.decodeResource(this.i.getResources(), numArr[h - 1].intValue());
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas == null) {
            LogUtil.b("LevelProgress", "canvas is null");
            return;
        }
        super.onDraw(canvas);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        canvas.save();
        canvas.rotate(this.y, this.j, this.h);
        ckQ_(canvas);
        ckN_(canvas);
        canvas.restore();
        ckM_(canvas);
        canvas.save();
        canvas.rotate(this.y, this.j, this.h);
        ckJ_(canvas);
        canvas.restore();
        ckL_(canvas);
        canvas.save();
        canvas.rotate(this.y, this.j, this.h);
        ckO_(canvas);
        canvas.restore();
        if ("1".equals(this.v)) {
            canvas.save();
            canvas.rotate(this.y, this.j, this.h);
            ckP_(canvas);
            canvas.restore();
            Rect rocketRect = getRocketRect();
            this.w = rocketRect;
            canvas.drawRect(rocketRect, this.s);
        }
    }

    private Rect getRocketRect() {
        int cos = (int) (this.j + (this.ac * Math.cos(Math.toRadians(101.55f - ((((this.p % 3 != 0 ? r0 : 3) - 1) * 7.7f) + (((float) this.q) * 7.7f))))));
        return new Rect(cos - 50, 0, cos, getHeight());
    }

    private void ckP_(Canvas canvas) {
        float h;
        float f = (float) this.q;
        if (this.p == 20) {
            f = 2.0f;
        }
        float h2 = (((r3 - 1) - ((mlc.h(r3) - 1) * 3)) * 7.7f) + (f * 7.7f);
        if (this.u <= h2) {
            h = ((1 - ((mlc.h(this.p) - 1) * 3)) * 7.7f) + 3.85f;
            h2 = this.u;
        } else {
            h = ((1 - ((mlc.h(this.p) - 1) * 3)) * 7.7f) + 3.85f;
        }
        canvas.save();
        canvas.rotate(h - h2, this.j, this.h);
        double d2 = this.j;
        double d3 = this.ac;
        double cos = Math.cos(Math.toRadians(90.0d));
        double d4 = this.h;
        double d5 = this.ac;
        double sin = Math.sin(Math.toRadians(90.0d));
        Bitmap decodeResource = BitmapFactory.decodeResource(this.i.getResources(), c[mlc.h(this.p) - 1].intValue());
        canvas.drawBitmap(decodeResource, (((float) (d2 + (d3 * cos))) - decodeResource.getWidth()) + 6.0f, ((float) (d4 + (d5 * sin))) - (decodeResource.getHeight() / 2.0f), this.l);
        canvas.restore();
    }

    public void d(int i, double d2, int i2) {
        this.p = i;
        this.q = d2;
        this.f = i2;
        LogUtil.a("LevelProgress", "refreshProgress mCurrentPage ", Integer.valueOf(i2));
        invalidate();
    }

    public void setRotate(float f, int i, int i2, int i3, double d2) {
        int i4;
        this.p = i3;
        this.q = d2;
        this.f = i2;
        this.t = i;
        LogUtil.a("LevelProgress", "setRotate newState ", Integer.valueOf(i), " mCurrentPage ", Integer.valueOf(this.f));
        float f2 = (f * 23.099998f) / (mlu.f(this.i) / 2.0f);
        if (f2 == 0.0f && (i4 = this.m) == 0) {
            this.y += this.f * 7.7f * 3.0f;
            this.m = i4 + 1;
        } else {
            this.y += f2;
        }
        invalidate();
    }

    private void e() {
        LogUtil.a("LevelProgress", "initPaint");
        Paint paint = new Paint();
        this.k = paint;
        paint.setAntiAlias(true);
        this.k.setStyle(Paint.Style.STROKE);
        this.k.setStrokeWidth(6.0f);
        Paint paint2 = new Paint();
        this.n = paint2;
        paint2.setAntiAlias(true);
        this.n.setStyle(Paint.Style.STROKE);
        this.n.setStrokeWidth(7.0f);
        this.n.setColor(this.i.getResources().getColor(R.color._2131296937_res_0x7f0902a9));
        Paint paint3 = new Paint();
        this.l = paint3;
        paint3.setAntiAlias(true);
        this.l.setStyle(Paint.Style.STROKE);
        this.l.setStrokeWidth(1.0f);
        Paint paint4 = new Paint();
        this.r = paint4;
        paint4.setAntiAlias(true);
        this.r.setStyle(Paint.Style.STROKE);
        this.r.setStrokeWidth(5.5f);
        this.r.setColor(this.i.getResources().getColor(R.color._2131296937_res_0x7f0902a9));
        Paint paint5 = new Paint();
        this.x = paint5;
        paint5.setAntiAlias(true);
        this.x.setTextSize(this.i.getResources().getDimension(R.dimen._2131365066_res_0x7f0a0cca));
        this.x.setColor(this.i.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        this.x.setTextAlign(Paint.Align.LEFT);
        this.x.setTypeface(Typeface.create(this.i.getResources().getString(R.string._2130850837_res_0x7f023415), 0));
        Paint paint6 = new Paint();
        this.s = paint6;
        paint6.setAntiAlias(true);
        this.s.setColor(this.i.getResources().getColor(R.color._2131296971_res_0x7f0902cb));
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "MAIN_VIP_KEY");
        this.v = b2;
        LogUtil.a("LevelProgress", "initPaint vip status = :", b2);
    }

    private LinearGradient ckS_(float f, float f2, int i, int i2) {
        double d2 = f + 90.0f;
        double d3 = f2 + 90.0f;
        return new LinearGradient((float) (this.j + (this.ac * Math.cos(Math.toRadians(d2)))), (float) (this.h + (this.ac * Math.sin(Math.toRadians(d2)))), (float) (this.j + (this.ac * Math.cos(Math.toRadians(d3)))), (float) ((this.ac * Math.sin(Math.toRadians(d3))) + this.h), this.i.getResources().getColor(i), this.i.getResources().getColor(i2), Shader.TileMode.CLAMP);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            ckU_(motionEvent);
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void ckU_(MotionEvent motionEvent) {
        Rect rect;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (this.o == null || (rect = this.w) == null || !rect.contains(x, y) || this.f != mlc.h(this.p) - 1) {
            return;
        }
        this.o.onDown((int) motionEvent.getRawX(), y);
    }

    public void setOnDownActionListener(OnDownActionListener onDownActionListener) {
        this.o = onDownActionListener;
    }
}
