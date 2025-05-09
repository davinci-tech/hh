package com.huawei.ucd.cloveranimation;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.huawei.health.R;
import com.huawei.hms.ads.jsb.constant.Constant;
import com.huawei.watchface.videoedit.gles.transition.SquareChangeAnimation;
import defpackage.njq;
import defpackage.njr;
import defpackage.njt;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/* loaded from: classes6.dex */
public class CloverView extends View {

    /* renamed from: a, reason: collision with root package name */
    private AddFrameListener f8730a;
    private boolean aa;
    private boolean ab;
    private boolean ac;
    private boolean ad;
    private int ae;
    private boolean af;
    private RectF ag;
    private boolean ah;
    private boolean ai;
    private float aj;
    private float ak;
    private float al;
    private float am;
    private float an;
    private float ao;
    private float ap;
    private njr aq;
    private float ar;
    private float as;
    private ValueAnimator at;
    private float au;
    private float av;
    private float aw;
    private float ax;
    private float ay;
    private float az;
    private AddFrameListener b;
    private float ba;
    private ValueAnimator bb;
    private float bc;
    private float bd;
    private Paint be;
    private float bf;
    private Handler bg;
    private float bh;
    private float bi;
    private Bitmap bj;
    private Paint bk;
    private Paint bl;
    private float bm;
    private float bn;
    private float bo;
    private float bp;
    private ValueAnimator bq;
    private float br;
    private float bs;
    private int bt;
    private float bu;
    private ValueAnimator bv;
    private Bitmap[] bw;
    private Bitmap[] bx;
    private float by;
    private float bz;
    private float c;
    private Bitmap[] ca;
    private float cb;
    private float cc;
    private ValueAnimator cd;
    private float ce;
    private float cf;
    private float cg;
    private Runnable ci;
    private AddFrameListener ck;
    private OnPetalClickListener cl;
    private boolean d;
    private int e;
    private float f;
    private boolean g;
    private boolean h;
    private float i;
    private Runnable j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    public interface OnPetalClickListener {
        void onLeftClick();

        void onRightClick();

        void onTopClick();
    }

    static /* synthetic */ int a(CloverView cloverView) {
        int i = cloverView.bt;
        cloverView.bt = i + 1;
        return i;
    }

    public void setPetalClickListener(OnPetalClickListener onPetalClickListener) {
        this.cl = onPetalClickListener;
    }

    public void setMaxOutsideScale(float f) {
        if (f < 0.88f) {
            this.cf = 0.88f;
        } else {
            this.cf = f;
        }
    }

    public void setDefaultOutsideScale() {
        this.cf = 0.88f;
    }

    public CloverView(Context context) {
        this(context, null);
    }

    public CloverView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CloverView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 0.58f;
        this.bf = 1.0f;
        this.ap = 1.0f;
        this.al = 1.0f;
        this.ax = 0.0f;
        this.ao = 0.0f;
        this.ar = 0.0f;
        this.as = 0.0f;
        this.bs = 0.0f;
        this.bu = 0.0f;
        this.by = 0.0f;
        this.an = 0.0f;
        this.ak = 0.0f;
        this.am = 0.0f;
        this.bz = 0.0f;
        this.bh = 0.0f;
        this.bo = 0.0f;
        this.aj = 0.0f;
        this.cb = 0.0f;
        this.bd = 0.0f;
        this.br = 0.0f;
        this.cg = 0.0f;
        this.bm = 0.0f;
        this.bp = 0.0f;
        this.ay = 0.0f;
        this.bn = 1.0f;
        this.au = 0.0f;
        this.bc = 1.0f;
        this.av = 0.0f;
        this.az = 1.0f;
        this.aw = 0.0f;
        this.ba = 1.0f;
        this.z = false;
        this.p = false;
        this.q = false;
        this.n = false;
        this.m = false;
        this.t = false;
        this.y = true;
        this.l = false;
        this.k = false;
        this.w = false;
        this.bt = 0;
        this.bw = null;
        this.ca = null;
        this.bx = null;
        this.ac = false;
        this.r = false;
        this.ad = false;
        this.af = false;
        this.ai = false;
        this.ah = false;
        this.o = false;
        this.ab = true;
        this.x = true;
        this.s = false;
        this.e = 1;
        this.u = false;
        this.h = false;
        this.aa = false;
        this.cf = 0.88f;
        this.d = false;
        this.ci = new Runnable() { // from class: com.huawei.ucd.cloveranimation.CloverView.5
            @Override // java.lang.Runnable
            public void run() {
                CloverView.this.ac = true;
                if (CloverView.this.bt >= 119) {
                    CloverView.this.ac = false;
                    return;
                }
                CloverView.this.bg.postDelayed(CloverView.this.ci, 17L);
                if (CloverView.this.s) {
                    CloverView.this.postInvalidate();
                    CloverView.a(CloverView.this);
                }
            }
        };
        this.j = new Runnable() { // from class: com.huawei.ucd.cloveranimation.CloverView.3
            @Override // java.lang.Runnable
            public void run() {
                final Bitmap[] cwp_;
                final Bitmap[] cwp_2;
                final Bitmap[] cwp_3;
                if (CloverView.this.e == 120) {
                    cwp_ = CloverView.this.cwe_(CloverView.this.cwd_(R.drawable._2131428426_res_0x7f0b044a));
                    cwp_2 = CloverView.this.cwe_(CloverView.this.cwd_(R.drawable._2131428424_res_0x7f0b0448));
                    cwp_3 = CloverView.this.cwe_(CloverView.this.cwd_(R.drawable._2131428425_res_0x7f0b0449));
                } else {
                    cwp_ = CloverView.this.cwp_(R.drawable._2131428435_res_0x7f0b0453);
                    cwp_2 = CloverView.this.cwp_(R.drawable._2131428433_res_0x7f0b0451);
                    cwp_3 = CloverView.this.cwp_(R.drawable._2131428434_res_0x7f0b0452);
                }
                CloverView.this.bg.post(new Runnable() { // from class: com.huawei.ucd.cloveranimation.CloverView.3.4
                    @Override // java.lang.Runnable
                    public void run() {
                        CloverView.this.bw = cwp_;
                        CloverView.this.ca = cwp_2;
                        CloverView.this.bx = cwp_3;
                        CloverView.this.s = true;
                        CloverView.this.postInvalidate();
                        if (CloverView.this.f8730a != null) {
                            CloverView.this.f8730a.onFinished();
                        }
                    }
                });
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100658_res_0x7f0603f2, R.attr._2131100660_res_0x7f0603f4});
        this.g = obtainStyledAttributes.getBoolean(0, false);
        this.v = obtainStyledAttributes.getBoolean(1, false);
        obtainStyledAttributes.recycle();
        j();
    }

    private void o() {
        this.bf = Math.min(getWidth(), getHeight()) / (Math.max(this.aq.i(), this.aq.h()) * 2.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap[] cwe_(Bitmap bitmap) {
        Bitmap[] bitmapArr = new Bitmap[this.e];
        Matrix matrix = new Matrix();
        if (this.bf <= 0.0f) {
            o();
        }
        float f = this.bf;
        if (f <= 0.0f) {
            for (int i = 0; i < this.e; i++) {
                bitmapArr[i] = Bitmap.createBitmap(10, 10, Bitmap.Config.RGB_565);
            }
        } else {
            matrix.preScale(f, f);
            int width = bitmap.getWidth() / 10;
            int height = bitmap.getHeight() / 12;
            for (int i2 = 0; i2 < this.e; i2++) {
                try {
                    bitmapArr[i2] = Bitmap.createBitmap(bitmap, ((i2 % 10) * bitmap.getWidth()) / 10, ((i2 / 10) * bitmap.getHeight()) / 12, width, height, matrix, true);
                } catch (IllegalArgumentException | OutOfMemoryError unused) {
                    bitmapArr[i2] = Bitmap.createBitmap(10, 10, Bitmap.Config.RGB_565);
                }
            }
        }
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return bitmapArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap[] cwp_(int i) {
        Bitmap[] bitmapArr = new Bitmap[this.e];
        Bitmap cwd_ = cwd_(i);
        Matrix matrix = new Matrix();
        if (this.bf <= 0.0f) {
            o();
        }
        float f = this.bf;
        if (f <= 0.0f) {
            bitmapArr[0] = Bitmap.createBitmap(10, 10, Bitmap.Config.RGB_565);
        } else {
            matrix.preScale(f, f);
            try {
                bitmapArr[0] = Bitmap.createBitmap(cwd_, 0, 0, cwd_.getWidth(), cwd_.getHeight(), matrix, true);
            } catch (IllegalArgumentException | OutOfMemoryError unused) {
                bitmapArr[0] = Bitmap.createBitmap(10, 10, Bitmap.Config.RGB_565);
            }
        }
        if (cwd_ != null && !cwd_.isRecycled()) {
            cwd_.recycle();
        }
        return bitmapArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap cwd_(int i) {
        byte[] d;
        int memoryClass = ((ActivityManager) getContext().getSystemService("activity")).getMemoryClass();
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (memoryClass < 32) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        } else {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        options.inPurgeable = true;
        options.inInputShareable = true;
        Bitmap decodeStream = BitmapFactory.decodeStream(getResources().openRawResource(i), null, options);
        if ((decodeStream == null || decodeStream.getWidth() <= 0 || decodeStream.getHeight() <= 0) && (d = d(getResources().openRawResource(i))) != null) {
            decodeStream = BitmapFactory.decodeByteArray(d, 0, d.length);
        }
        return (decodeStream == null || decodeStream.getWidth() <= 0 || decodeStream.getHeight() <= 0) ? Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888) : decodeStream;
    }

    private byte[] d(InputStream inputStream) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byteArrayOutputStream.close();
                    inputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (Exception unused) {
            return null;
        }
    }

    public void setAddFrameListener(AddFrameListener addFrameListener) {
        this.f8730a = addFrameListener;
    }

    public void setAnimEndListener(AddFrameListener addFrameListener) {
        this.b = addFrameListener;
    }

    public void setValueEndListener(AddFrameListener addFrameListener) {
        this.ck = addFrameListener;
    }

    public void setContentsRunIcon(boolean z) {
        this.o = z;
    }

    public void setInvalid(boolean z) {
        this.y = z;
        invalidate();
    }

    public void setAllFull(boolean z) {
        this.h = z;
    }

    public void setPlayRunAnimAnimator(boolean z) {
        this.ab = z;
    }

    public void setPlayEncourageAnimAnimator(boolean z) {
        this.x = z;
    }

    public njr getCloverData() {
        return this.aq;
    }

    private void j() {
        this.aq = new njr();
        this.bl = new Paint(1);
        this.ag = new RectF();
        this.e = 1;
        if (this.v || this.g) {
            this.bk = new Paint(1);
            this.be = new Paint(1);
            this.bg = new Handler();
            int max = Math.max((int) this.aq.i(), (int) this.aq.h()) * 2;
            this.bi = (max / 2.0f) - (this.aq.i() / 2.0f);
            if (this.g) {
                this.bj = Bitmap.createBitmap(max, max, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(this.bj);
                this.bk.setColor(-16777216);
                this.bk.setFilterBitmap(true);
                canvas.save();
                canvas.translate(this.bi, 0.0f);
                canvas.drawPath(this.aq.cvq_(), this.bk);
                canvas.restore();
                this.bk.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            }
        }
        if (this.g) {
            return;
        }
        setMaxOutsideScale(1.0f);
    }

    private void h() {
        new Thread(this.j).start();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i == i3 && i2 == i4) {
            return;
        }
        float f = i;
        float f2 = i2;
        this.ag.set(0.0f, 0.0f, f, f2);
        if (i >= i2) {
            this.c = 0.58f;
        } else {
            this.c = (((i2 - i) / 2.0f) + (0.58f * f)) / f2;
        }
        this.ce = (f / 2.0f) - (this.aq.i() / 2.0f);
        this.cc = (f2 * this.c) - this.aq.h();
        this.bf = Math.min(i, i2) / (Math.max(this.aq.i(), this.aq.h()) * 2.0f);
        if (this.v) {
            h();
        }
        if (this.g) {
            this.e = 120;
            h();
        }
    }

    private void g() {
        this.bz = 0.0f;
        this.bh = 0.0f;
        this.bo = 0.0f;
        this.ax = 1.0f;
        this.ap = 1.0f;
        this.an = 1.0f;
        this.ak = 1.0f;
        this.am = 1.0f;
        this.al = 1.0f;
        this.cb = 0.0f;
        this.bd = 0.0f;
        this.br = 0.0f;
        this.bs = 1.0f;
        this.bu = 1.0f;
        this.by = 1.0f;
        this.ay = 0.0f;
        this.bn = 1.0f;
        this.au = 0.0f;
        this.bc = 1.0f;
        this.av = 0.0f;
        this.az = 1.0f;
        this.aw = 0.0f;
        this.ba = 1.0f;
        this.p = false;
        this.q = false;
    }

    public void setCutValuesWithAnim(float f, float f2, float f3) {
        f();
        g();
        if (Float.compare(f, this.bz) != 0 || Float.compare(f2, this.bh) != 0 || Float.compare(f3, this.bo) != 0) {
            this.af = false;
            this.ai = false;
            this.ah = false;
            if (f != this.bz) {
                this.ai = true;
            }
            if (f2 != this.bh) {
                this.af = true;
            }
            if (f3 != this.bo) {
                this.ah = true;
            }
            this.n = true;
            this.m = true;
            this.t = true;
            if (this.af || this.ai || this.ah) {
                e();
            }
            this.z = true;
            b(f, f2, f3);
            return;
        }
        invalidate();
    }

    public void setValuesWithAnim(float f, float f2, float f3, AddFrameListener addFrameListener) {
        if (this.r) {
            this.b = addFrameListener;
        } else {
            this.b = null;
            setValuesWithAnim(f, f2, f3);
        }
    }

    public void setValuesWithAnim(float f, float f2, float f3) {
        if (this.r) {
            return;
        }
        f();
        if (Float.compare(f, this.bz) == 0 && Float.compare(f2, this.bh) == 0 && Float.compare(f3, this.bo) == 0) {
            return;
        }
        this.af = false;
        this.ai = false;
        this.ah = false;
        if (f != this.bz) {
            this.ai = true;
        }
        if (f2 != this.bh) {
            this.af = true;
        }
        if (f3 != this.bo) {
            this.ah = true;
        }
        this.n = true;
        this.m = true;
        this.t = true;
        if (this.af || this.ai || this.ah) {
            e();
        }
        this.z = true;
        b(f, f2, f3);
    }

    public void c(float f) {
        this.aj = f;
        this.k = true;
        this.x = true;
        this.ab = true;
        this.o = true;
        a(f, f, f);
    }

    public void d() {
        this.k = false;
        f();
    }

    public void a(final float f, final float f2, final float f3) {
        f();
        this.r = true;
        this.z = false;
        b();
        this.bz = f;
        this.bh = f2;
        this.bo = f3;
        n();
        c(f, f2, f3);
        this.bg.postDelayed(new Runnable() { // from class: com.huawei.ucd.cloveranimation.CloverView.2
            @Override // java.lang.Runnable
            public void run() {
                CloverView.this.af = false;
                CloverView.this.ai = false;
                CloverView.this.ah = false;
                if (f > 0.0f) {
                    CloverView.this.ai = true;
                }
                if (f2 > 0.0f) {
                    CloverView.this.af = true;
                }
                if (f3 > 0.0f) {
                    CloverView.this.ah = true;
                }
                if (CloverView.this.af || CloverView.this.ai || CloverView.this.ah) {
                    CloverView.this.e();
                }
            }
        }, 400L);
    }

    private void f() {
        ValueAnimator valueAnimator = this.bq;
        if (valueAnimator != null) {
            valueAnimator.removeAllListeners();
            this.bq.cancel();
            this.bq.removeAllUpdateListeners();
        }
        ValueAnimator valueAnimator2 = this.bv;
        if (valueAnimator2 != null) {
            valueAnimator2.removeAllListeners();
            this.bv.cancel();
            this.bv.removeAllUpdateListeners();
        }
        ValueAnimator valueAnimator3 = this.cd;
        if (valueAnimator3 != null) {
            valueAnimator3.removeAllListeners();
            this.cd.cancel();
            this.cd.removeAllUpdateListeners();
        }
        ValueAnimator valueAnimator4 = this.bb;
        if (valueAnimator4 != null) {
            valueAnimator4.removeAllListeners();
            this.bb.cancel();
            this.bb.removeAllUpdateListeners();
        }
        ValueAnimator valueAnimator5 = this.at;
        if (valueAnimator5 != null) {
            valueAnimator5.removeAllListeners();
            this.at.cancel();
            this.at.removeAllUpdateListeners();
        }
    }

    public void setPlayAllFrameAnimation(boolean z) {
        this.w = z;
    }

    public void e() {
        if (!this.ac && this.ab && this.e == 120) {
            this.bt = 0;
            this.bg.postDelayed(this.ci, 17L);
        }
    }

    public void b() {
        this.bz = 0.0f;
        this.bh = 0.0f;
        this.bo = 0.0f;
        this.ap = 0.0f;
        this.al = 0.0f;
        this.ax = 0.0f;
        this.ao = 0.0f;
        this.ar = 0.0f;
        this.as = 0.0f;
        this.an = 0.0f;
        this.ak = 0.0f;
        this.am = 0.0f;
        this.cg = 0.0f;
        this.bm = 0.0f;
        this.bp = 0.0f;
        this.ay = 0.0f;
        this.bn = 1.0f;
        this.au = 0.0f;
        this.bc = 1.0f;
        this.av = 0.0f;
        this.az = 1.0f;
        this.aw = 0.0f;
        this.ba = 1.0f;
        this.p = false;
        this.q = false;
        this.bs = 0.0f;
        this.bu = 0.0f;
        this.by = 0.0f;
        this.n = true;
        this.m = true;
        this.t = true;
        this.y = false;
        this.l = true;
    }

    public void setValuesWithoutAnim(float f, float f2, float f3) {
        this.bz = f;
        this.bh = f2;
        this.bo = f3;
        this.ap = 1.0f;
        this.al = 1.0f;
        this.ax = 1.0f;
        this.ao = 1.0f;
        this.ar = 1.0f;
        this.as = 1.0f;
        this.an = 1.0f;
        this.ak = 1.0f;
        this.am = 1.0f;
        this.cg = 1.0f;
        this.bm = 1.0f;
        this.bp = 1.0f;
        this.ay = 1.0f;
        this.bn = 1.0f;
        this.au = 0.0f;
        this.bc = 1.0f;
        this.av = 0.0f;
        this.az = 1.0f;
        this.aw = 0.0f;
        this.ba = 1.0f;
        this.p = Float.compare(f, 1.0f) == 0 && Float.compare(f2, 1.0f) == 0 && Float.compare(f3, 1.0f) == 0;
        this.q = Float.compare(f, 1.0f) == 0 && Float.compare(f2, 1.0f) == 0 && Float.compare(f3, 1.0f) == 0;
        this.bs = 1.0f;
        this.bu = 1.0f;
        this.by = 1.0f;
        this.n = false;
        this.m = false;
        this.t = false;
        this.y = false;
        this.l = false;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.bl.setShader(null);
        this.bl.setAlpha((int) (this.al * 255.0f));
        if (!this.p) {
            cvT_(canvas);
        }
        if (this.y) {
            return;
        }
        this.bl.setAlpha(255);
        if (this.q) {
            cvX_(canvas, this.ay);
            cvU_(canvas);
            cvS_(canvas, this.ay);
            cwc_(canvas, this.ay);
            cvY_(canvas, this.bc, this.au);
            cvY_(canvas, this.az, this.av);
            cvY_(canvas, this.ba, this.aw);
            cvZ_(canvas, this.bc, this.au);
            cvZ_(canvas, this.az, this.av);
            cvZ_(canvas, this.ba, this.aw);
        } else {
            cvS_(canvas, 1.0f);
            cwc_(canvas, 1.0f);
            cvX_(canvas, 1.0f);
        }
        if (this.p && this.l) {
            cvO_(canvas);
            cvY_(canvas, this.bc, this.au);
            cvY_(canvas, this.az, this.av);
            cvY_(canvas, this.ba, this.aw);
            cvZ_(canvas, this.bc, this.au);
            cvZ_(canvas, this.az, this.av);
            cvZ_(canvas, this.ba, this.aw);
        }
        cvP_(canvas);
    }

    private void cwa_(Canvas canvas, int i, int i2, float f) {
        canvas.save();
        canvas.rotate((this.an * 90.0f) - 90.0f, getWidth() / 2.0f, getHeight() * this.c);
        this.be.setAlpha((int) (this.bs * 255.0f));
        int i3 = (this.bz <= 0.0f || !this.ai) ? 0 : this.bt;
        if (this.w) {
            i3 = this.bt;
        }
        Bitmap bitmap = this.bw[i3];
        if (bitmap == null) {
            return;
        }
        canvas.drawBitmap(bitmap, (getWidth() - i) / 2.0f, ((getHeight() * this.c) - i2) - f, this.be);
        canvas.restore();
    }

    public void d(boolean z) {
        this.aa = z;
        if (z) {
            setValuesWithoutAnim(1.0f, 1.0f, 1.0f);
        } else {
            invalidate();
        }
    }

    private void cvU_(Canvas canvas) {
        if (this.g || !this.aa) {
            return;
        }
        Paint paint = new Paint(1);
        canvas.save();
        Bitmap cwd_ = cwd_(R.drawable._2131431254_res_0x7f0b0f56);
        Matrix matrix = new Matrix();
        float min = Math.min(getWidth(), getHeight());
        float width = min / cwd_.getWidth();
        matrix.preScale(width, width);
        canvas.drawBitmap(Bitmap.createBitmap(cwd_, 0, 0, cwd_.getWidth(), cwd_.getHeight(), matrix, true), (getWidth() - min) / 2.0f, ((getHeight() * this.c) + (Math.min(getWidth(), getHeight()) * (1.0f - this.c))) - (r2.getHeight() * 0.95f), paint);
        canvas.restore();
    }

    private void cvQ_(Canvas canvas, int i, int i2, float f) {
        int i3 = (this.bh <= 0.0f || !this.af) ? 0 : this.bt;
        if (this.w) {
            i3 = this.bt;
        }
        if (this.bx[i3] == null) {
            return;
        }
        canvas.save();
        canvas.rotate((this.ak * 90.0f) - 90.0f, getWidth() / 2.0f, getHeight() * this.c);
        this.be.setAlpha((int) (this.bu * 255.0f));
        canvas.drawBitmap(this.bx[i3], ((getWidth() / 2.0f) - i) - f, ((getHeight() * this.c) - (i2 * 0.211f)) + f, this.be);
        canvas.restore();
    }

    private void cvV_(Canvas canvas, int i, float f) {
        int i2 = (this.bo <= 0.0f || !this.ah) ? 0 : this.bt;
        if (this.w) {
            i2 = this.bt;
        }
        if (this.ca[i2] == null) {
            return;
        }
        canvas.save();
        canvas.rotate((this.am * 90.0f) - 90.0f, getWidth() / 2.0f, getHeight() * this.c);
        this.be.setAlpha((int) (this.by * 255.0f));
        canvas.drawBitmap(this.ca[i2], (getWidth() / 2.0f) + f, ((getHeight() * this.c) - (i * 0.211f)) + f, this.be);
        canvas.restore();
    }

    private void cvP_(Canvas canvas) {
        Bitmap[] bitmapArr;
        Bitmap bitmap;
        if (this.s) {
            if ((!this.o && !this.v) || (bitmapArr = this.bw) == null || (bitmap = bitmapArr[0]) == null) {
                return;
            }
            int width = bitmap.getWidth();
            int height = this.bw[0].getHeight();
            float height2 = getHeight() * 0.03f;
            cwa_(canvas, width, height, height2);
            float f = height2 * 0.707f;
            cvV_(canvas, height, f);
            cvQ_(canvas, width, height, f);
        }
    }

    private void cvX_(Canvas canvas, float f) {
        float f2 = this.bo;
        if (f2 <= 0.0f) {
            return;
        }
        if (f2 <= 0.3f) {
            f2 = 0.3f;
        }
        int saveLayer = canvas.saveLayer(this.ag, null, 31);
        canvas.save();
        float f3 = this.cf;
        float f4 = this.as;
        float f5 = this.bf;
        float f6 = f2 * f3;
        float f7 = f4 * f6 * f5;
        if (this.z) {
            f7 = this.bp * f6 * f5;
        }
        if (this.q) {
            f7 = f3 * f5 * f;
        }
        if (this.p) {
            f7 = f3 * f5 * this.bn;
        }
        canvas.scale(f7, f7, getWidth() / 2.0f, getHeight() * this.c);
        canvas.translate(this.ce, this.cc);
        canvas.save();
        canvas.rotate((this.am * 90.0f) + 30.0f, this.aq.cvr_().x, this.aq.cvr_().y);
        setPetalPaintShader(this.aq.cvt_());
        if (this.h) {
            this.bl.setShader(this.aq.cvl_());
        } else {
            this.bl.setShader(this.aq.cvt_());
        }
        canvas.drawPath(this.aq.cvq_(), this.bl);
        canvas.restore();
        canvas.restore();
        cvW_(canvas);
        canvas.restoreToCount(saveLayer);
    }

    private void setPetalPaintShader(LinearGradient linearGradient) {
        if (this.h) {
            this.bl.setShader(this.aq.cvl_());
        } else {
            this.bl.setShader(linearGradient);
        }
    }

    private void cwc_(Canvas canvas, float f) {
        float f2 = this.bz;
        if (f2 <= 0.0f) {
            return;
        }
        if (f2 <= 0.3f) {
            f2 = 0.3f;
        }
        int saveLayer = canvas.saveLayer(this.ag, null, 31);
        canvas.save();
        float f3 = this.cf;
        float f4 = this.ao;
        float f5 = this.bf;
        float f6 = f2 * f3;
        float f7 = f4 * f6 * f5;
        if (this.z) {
            f7 = this.cg * f6 * f5;
        }
        if (this.q) {
            f7 = f3 * f5 * f;
        }
        if (this.p) {
            f7 = f3 * f5 * this.bn;
        }
        canvas.scale(f7, f7, getWidth() / 2.0f, getHeight() * this.c);
        canvas.translate(this.ce, this.cc);
        canvas.save();
        canvas.rotate((this.an * 90.0f) - 90.0f, this.aq.cvr_().x, this.aq.cvr_().y);
        setPetalPaintShader(this.aq.cvp_());
        this.bl.setAlpha((int) (this.ax * 255.0f));
        canvas.drawPath(this.aq.cvq_(), this.bl);
        canvas.restore();
        canvas.restore();
        cwb_(canvas);
        canvas.restoreToCount(saveLayer);
    }

    private void cvS_(Canvas canvas, float f) {
        float f2 = this.bh;
        if (f2 <= 0.0f) {
            return;
        }
        if (f2 <= 0.3f) {
            f2 = 0.3f;
        }
        float f3 = this.cf;
        float f4 = this.ar;
        float f5 = this.bf;
        float f6 = f2 * f3;
        float f7 = f4 * f6 * f5;
        if (this.z) {
            f7 = this.bm * f6 * f5;
        }
        if (this.q) {
            f7 = f3 * f5 * f;
        }
        if (this.p) {
            f7 = f3 * f5 * this.bn;
        }
        int saveLayer = canvas.saveLayer(this.ag, null, 31);
        canvas.save();
        canvas.scale(f7, f7, getWidth() / 2.0f, getHeight() * this.c);
        canvas.translate(this.ce, this.cc);
        canvas.save();
        canvas.rotate((this.ak * 90.0f) - 210.0f, this.aq.cvr_().x, this.aq.cvr_().y);
        setPetalPaintShader(this.aq.cvn_());
        canvas.drawPath(this.aq.cvq_(), this.bl);
        canvas.restore();
        canvas.restore();
        cvR_(canvas);
        canvas.restoreToCount(saveLayer);
    }

    private void setPetalBackgroundColor(LinearGradient linearGradient) {
        if (this.y) {
            this.bl.setShader(null);
            this.bl.setColor(this.aq.m());
        } else if (this.h) {
            this.bl.setShader(this.aq.cvk_());
        } else {
            this.bl.setShader(linearGradient);
        }
    }

    private void cvT_(Canvas canvas) {
        canvas.save();
        float f = this.ap * this.bf * this.cf;
        if (this.p || this.q) {
            f *= this.bn;
        }
        canvas.scale(f, f, getWidth() / 2.0f, getHeight() * this.c);
        setPetalBackgroundColor(this.aq.cvm_());
        canvas.translate(this.ce, this.cc);
        canvas.save();
        canvas.rotate((this.ap * 60.0f) - 180.0f, this.aq.cvr_().x, this.aq.cvr_().y);
        canvas.drawPath(this.aq.cvq_(), this.bl);
        canvas.restore();
        canvas.save();
        canvas.rotate((this.ap * 60.0f) + 60.0f, this.aq.cvr_().x, this.aq.cvr_().y);
        setPetalBackgroundColor(this.aq.cvs_());
        canvas.drawPath(this.aq.cvq_(), this.bl);
        canvas.restore();
        canvas.rotate((this.ap * 60.0f) - 60.0f, this.aq.cvr_().x, this.aq.cvr_().y);
        setPetalBackgroundColor(this.aq.cvo_());
        canvas.drawPath(this.aq.cvq_(), this.bl);
        canvas.restore();
    }

    private void cwb_(Canvas canvas) {
        if (this.n) {
            canvas.save();
            float f = this.ap * this.bf * this.cf;
            float f2 = this.ao;
            float f3 = f2 > 1.0f ? f2 * f : f;
            float f4 = this.cg;
            if (f4 > 1.0f) {
                f3 = f * f4;
            }
            canvas.scale(f3, f3, getWidth() / 2.0f, getHeight() * this.c);
            canvas.translate(this.ce, this.cc);
            canvas.translate(-this.bi, 0.0f);
            canvas.rotate((this.ap * 60.0f) - 60.0f, this.bj.getWidth() / 2.0f, this.bj.getHeight() / 2.0f);
            canvas.drawBitmap(this.bj, 0.0f, 0.0f, this.bk);
            canvas.restore();
        }
    }

    private void cvR_(Canvas canvas) {
        if (this.m) {
            canvas.save();
            float f = this.ap * this.bf * this.cf;
            float f2 = this.ar;
            float f3 = f2 > 1.0f ? f2 * f : f;
            float f4 = this.bm;
            if (f4 > 1.0f) {
                f3 = f * f4;
            }
            canvas.scale(f3, f3, getWidth() / 2.0f, getHeight() * this.c);
            canvas.translate(this.ce, this.cc);
            canvas.translate(-this.bi, 0.0f);
            canvas.rotate((this.ap * 60.0f) - 180.0f, this.bj.getWidth() / 2.0f, this.bj.getHeight() / 2.0f);
            canvas.drawBitmap(this.bj, 0.0f, 0.0f, this.bk);
            canvas.restore();
        }
    }

    private void cvW_(Canvas canvas) {
        if (this.t) {
            canvas.save();
            float f = this.ap * this.bf * this.cf;
            float f2 = this.as;
            float f3 = f2 > 1.0f ? f2 * f : f;
            float f4 = this.bp;
            if (f4 > 1.0f) {
                f3 = f * f4;
            }
            canvas.scale(f3, f3, getWidth() / 2.0f, getHeight() * this.c);
            canvas.translate(this.ce, this.cc);
            canvas.translate(-this.bi, 0.0f);
            canvas.rotate((this.ap * 60.0f) + 60.0f, this.bj.getWidth() / 2.0f, this.bj.getHeight() / 2.0f);
            canvas.drawBitmap(this.bj, 0.0f, 0.0f, this.bk);
            canvas.restore();
        }
    }

    private void cvY_(Canvas canvas, float f, float f2) {
        if (this.l && this.x) {
            canvas.save();
            float f3 = this.bf * this.cf * f;
            canvas.scale(f3, f3, getWidth() / 2.0f, getHeight() * this.c);
            if (this.q) {
                setPetalPaintShader(this.aq.cvn_());
            }
            if (this.p) {
                this.bl.setShader(this.aq.cvl_());
            }
            int i = (int) (f2 * 255.0f);
            this.bl.setAlpha(i);
            canvas.translate(this.ce, this.cc);
            canvas.save();
            canvas.rotate(-120.0f, this.aq.cvr_().x, this.aq.cvr_().y);
            canvas.drawPath(this.aq.cvq_(), this.bl);
            canvas.restore();
            canvas.save();
            canvas.rotate(120.0f, this.aq.cvr_().x, this.aq.cvr_().y);
            if (this.q) {
                setPetalPaintShader(this.aq.cvt_());
            }
            this.bl.setAlpha(i);
            canvas.drawPath(this.aq.cvq_(), this.bl);
            canvas.restore();
            canvas.rotate((this.ap * 60.0f) - 60.0f, this.aq.cvr_().x, this.aq.cvr_().y);
            if (this.q) {
                setPetalPaintShader(this.aq.cvp_());
            }
            this.bl.setAlpha(i);
            canvas.drawPath(this.aq.cvq_(), this.bl);
            canvas.restore();
        }
    }

    private void cvO_(Canvas canvas) {
        canvas.save();
        canvas.save();
        float f = this.cf * this.bf * this.ay * this.bn;
        canvas.scale(f, f, getWidth() / 2.0f, getHeight() * this.c);
        canvas.translate(this.ce, this.cc);
        canvas.rotate(120.0f, this.aq.cvr_().x, this.aq.cvr_().y);
        this.bl.setShader(this.aq.cvl_());
        canvas.drawPath(this.aq.cvq_(), this.bl);
        canvas.restore();
        cvU_(canvas);
        float f2 = this.cf * this.bf * this.ay * this.bn;
        canvas.scale(f2, f2, getWidth() / 2.0f, getHeight() * this.c);
        canvas.translate(this.ce, this.cc);
        canvas.rotate(-120.0f, this.aq.cvr_().x, this.aq.cvr_().y);
        this.bl.setShader(this.aq.cvl_());
        canvas.drawPath(this.aq.cvq_(), this.bl);
        canvas.restore();
        canvas.save();
        float f3 = this.cf * this.bf * this.ay * this.bn;
        canvas.scale(f3, f3, getWidth() / 2.0f, getHeight() * this.c);
        canvas.translate(this.ce, this.cc);
        this.bl.setShader(this.aq.cvl_());
        this.bl.setAlpha((int) (this.ax * 255.0f));
        canvas.drawPath(this.aq.cvq_(), this.bl);
        canvas.restore();
    }

    private void cvZ_(Canvas canvas, float f, float f2) {
        if (this.l && this.x) {
            this.bl.setStrokeWidth(4.0f);
            this.bl.setStyle(Paint.Style.STROKE);
            if (this.p) {
                this.bl.setShader(this.aq.cvl_());
            }
            if (this.q) {
                setPetalPaintShader(this.aq.cvn_());
            }
            this.bl.setAlpha((int) (f2 * 255.0f));
            canvas.save();
            float f3 = this.cf * this.bf * f;
            canvas.scale(f3, f3, getWidth() / 2.0f, getHeight() * this.c);
            canvas.translate(this.ce, this.cc);
            canvas.rotate(-120.0f, this.aq.cvr_().x, this.aq.cvr_().y);
            canvas.drawPath(this.aq.cvq_(), this.bl);
            canvas.restore();
            canvas.save();
            if (this.q) {
                setPetalPaintShader(this.aq.cvp_());
            }
            float f4 = this.cf * this.bf * f;
            canvas.scale(f4, f4, getWidth() / 2.0f, getHeight() * this.c);
            canvas.translate(this.ce, this.cc);
            canvas.drawPath(this.aq.cvq_(), this.bl);
            canvas.restore();
            canvas.save();
            if (this.q) {
                setPetalPaintShader(this.aq.cvt_());
            }
            float f5 = this.cf * this.bf * f;
            canvas.scale(f5, f5, getWidth() / 2.0f, getHeight() * this.c);
            canvas.translate(this.ce, this.cc);
            canvas.rotate(120.0f, this.aq.cvr_().x, this.aq.cvr_().y);
            canvas.drawPath(this.aq.cvq_(), this.bl);
            canvas.restore();
            this.bl.setStyle(Paint.Style.FILL);
        }
    }

    private void n() {
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 0.0f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.33333334f, 1.0f);
        Keyframe ofFloat3 = Keyframe.ofFloat(0.53333336f, 1.0f);
        Keyframe ofFloat4 = Keyframe.ofFloat(1.0f, 1.0f);
        ValueAnimator ofPropertyValuesHolder = ValueAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofKeyframe("startAlpha", ofFloat, ofFloat2, ofFloat4), PropertyValuesHolder.ofKeyframe("topAlpha", ofFloat, ofFloat3, ofFloat4), PropertyValuesHolder.ofKeyframe("rotate", ofFloat, ofFloat4));
        this.bq = ofPropertyValuesHolder;
        ofPropertyValuesHolder.setInterpolator(new njt());
        this.bq.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.ucd.cloveranimation.CloverView.7
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CloverView.this.al = ((Float) valueAnimator.getAnimatedValue("startAlpha")).floatValue();
                CloverView.this.ax = ((Float) valueAnimator.getAnimatedValue("topAlpha")).floatValue();
                CloverView.this.ap = ((Float) valueAnimator.getAnimatedValue("rotate")).floatValue();
                CloverView.this.invalidate();
            }
        });
        this.bq.setDuration(750L);
        this.bq.start();
    }

    private PropertyValuesHolder cwq_(njt njtVar) {
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 0.0f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.4375f, 1.0f);
        ofFloat2.setInterpolator(njtVar);
        Keyframe ofFloat3 = Keyframe.ofFloat(1.0f, 1.0f);
        ofFloat3.setInterpolator(njtVar);
        return PropertyValuesHolder.ofKeyframe("topAngel", ofFloat, ofFloat2, ofFloat3);
    }

    private PropertyValuesHolder cwj_(njt njtVar) {
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 0.0f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.16666667f, 0.0f);
        ofFloat2.setInterpolator(njtVar);
        Keyframe ofFloat3 = Keyframe.ofFloat(0.25f, 1.0f);
        ofFloat3.setInterpolator(njtVar);
        Keyframe ofFloat4 = Keyframe.ofFloat(1.0f, 1.0f);
        ofFloat4.setInterpolator(njtVar);
        return PropertyValuesHolder.ofKeyframe("runAlpha", ofFloat, ofFloat2, ofFloat3, ofFloat4);
    }

    private PropertyValuesHolder cwr_(njt njtVar) {
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 0.0f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.4375f, 1.05f);
        ofFloat2.setInterpolator(njtVar);
        Keyframe ofFloat3 = Keyframe.ofFloat(0.65625f, 0.98f);
        ofFloat3.setInterpolator(njtVar);
        Keyframe ofFloat4 = Keyframe.ofFloat(0.71875f, 1.01f);
        ofFloat4.setInterpolator(njtVar);
        Keyframe ofFloat5 = Keyframe.ofFloat(0.8125f, 1.0f);
        ofFloat5.setInterpolator(njtVar);
        return PropertyValuesHolder.ofKeyframe(Constant.MAP_KEY_TOP, ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat5, Keyframe.ofFloat(1.0f, 1.0f));
    }

    private PropertyValuesHolder cwf_(njt njtVar) {
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 0.0f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.125f, 0.0f);
        ofFloat2.setInterpolator(njtVar);
        Keyframe ofFloat3 = Keyframe.ofFloat(0.625f, 1.0f);
        ofFloat3.setInterpolator(njtVar);
        Keyframe ofFloat4 = Keyframe.ofFloat(1.0f, 1.0f);
        ofFloat4.setInterpolator(njtVar);
        return PropertyValuesHolder.ofKeyframe("leftAngel", ofFloat, ofFloat2, ofFloat3, ofFloat4);
    }

    private PropertyValuesHolder cwn_(njt njtVar) {
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 0.0f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.29166666f, 0.0f);
        ofFloat2.setInterpolator(njtVar);
        Keyframe ofFloat3 = Keyframe.ofFloat(0.375f, 1.0f);
        ofFloat3.setInterpolator(njtVar);
        Keyframe ofFloat4 = Keyframe.ofFloat(1.0f, 1.0f);
        ofFloat4.setInterpolator(njtVar);
        return PropertyValuesHolder.ofKeyframe("sleepAlpha", ofFloat, ofFloat2, ofFloat3, ofFloat4);
    }

    private PropertyValuesHolder cwg_(njt njtVar) {
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 0.0f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.125f, 0.0f);
        Keyframe ofFloat3 = Keyframe.ofFloat(0.625f, 1.05f);
        ofFloat3.setInterpolator(njtVar);
        Keyframe ofFloat4 = Keyframe.ofFloat(0.84375f, 0.98f);
        ofFloat4.setInterpolator(njtVar);
        Keyframe ofFloat5 = Keyframe.ofFloat(0.90625f, 1.01f);
        ofFloat5.setInterpolator(njtVar);
        Keyframe ofFloat6 = Keyframe.ofFloat(1.0f, 1.0f);
        ofFloat6.setInterpolator(njtVar);
        return PropertyValuesHolder.ofKeyframe(SquareChangeAnimation.LEFT, ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat5, ofFloat6);
    }

    private PropertyValuesHolder cwh_(njt njtVar) {
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 0.0f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.0625f, 0.0f);
        ofFloat2.setInterpolator(njtVar);
        Keyframe ofFloat3 = Keyframe.ofFloat(0.53125f, 1.0f);
        ofFloat3.setInterpolator(njtVar);
        Keyframe ofFloat4 = Keyframe.ofFloat(1.0f, 1.0f);
        ofFloat4.setInterpolator(njtVar);
        return PropertyValuesHolder.ofKeyframe("rightAngel", ofFloat, ofFloat2, ofFloat3, ofFloat4);
    }

    private PropertyValuesHolder cwo_(njt njtVar) {
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 0.0f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.22916667f, 0.0f);
        ofFloat2.setInterpolator(njtVar);
        Keyframe ofFloat3 = Keyframe.ofFloat(0.3125f, 1.0f);
        ofFloat3.setInterpolator(njtVar);
        Keyframe ofFloat4 = Keyframe.ofFloat(1.0f, 1.0f);
        ofFloat4.setInterpolator(njtVar);
        return PropertyValuesHolder.ofKeyframe("smileAlpha", ofFloat, ofFloat2, ofFloat3, ofFloat4);
    }

    private PropertyValuesHolder cwi_(njt njtVar) {
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 0.0f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.0625f, 0.0f);
        ofFloat2.setInterpolator(njtVar);
        Keyframe ofFloat3 = Keyframe.ofFloat(0.53125f, 1.05f);
        ofFloat3.setInterpolator(njtVar);
        Keyframe ofFloat4 = Keyframe.ofFloat(0.75f, 0.98f);
        ofFloat4.setInterpolator(njtVar);
        Keyframe ofFloat5 = Keyframe.ofFloat(0.8125f, 1.01f);
        ofFloat5.setInterpolator(njtVar);
        Keyframe ofFloat6 = Keyframe.ofFloat(0.90625f, 1.0f);
        ofFloat6.setInterpolator(njtVar);
        return PropertyValuesHolder.ofKeyframe(SquareChangeAnimation.RIGHT, ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat5, ofFloat6, Keyframe.ofFloat(1.0f, 1.0f));
    }

    private void i() {
        njt njtVar = new njt();
        PropertyValuesHolder cwq_ = cwq_(njtVar);
        PropertyValuesHolder cwj_ = cwj_(njtVar);
        PropertyValuesHolder cwr_ = cwr_(njtVar);
        PropertyValuesHolder cwf_ = cwf_(njtVar);
        PropertyValuesHolder cwn_ = cwn_(njtVar);
        PropertyValuesHolder cwg_ = cwg_(njtVar);
        ValueAnimator ofPropertyValuesHolder = ValueAnimator.ofPropertyValuesHolder(cwr_, cwj_, cwq_, cwh_(njtVar), cwn_, cwi_(njtVar), cwf_, cwo_(njtVar), cwg_);
        this.bv = ofPropertyValuesHolder;
        ofPropertyValuesHolder.setInterpolator(new FastOutSlowInInterpolator());
        this.bv.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.ucd.cloveranimation.CloverView.6
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CloverView.this.ao = ((Float) valueAnimator.getAnimatedValue(Constant.MAP_KEY_TOP)).floatValue();
                CloverView.this.bs = ((Float) valueAnimator.getAnimatedValue("runAlpha")).floatValue();
                CloverView.this.an = ((Float) valueAnimator.getAnimatedValue("topAngel")).floatValue();
                CloverView.this.ar = ((Float) valueAnimator.getAnimatedValue(SquareChangeAnimation.LEFT)).floatValue();
                CloverView.this.bu = ((Float) valueAnimator.getAnimatedValue("sleepAlpha")).floatValue();
                CloverView.this.ak = ((Float) valueAnimator.getAnimatedValue("leftAngel")).floatValue();
                CloverView.this.as = ((Float) valueAnimator.getAnimatedValue(SquareChangeAnimation.RIGHT)).floatValue();
                CloverView.this.by = ((Float) valueAnimator.getAnimatedValue("smileAlpha")).floatValue();
                CloverView.this.am = ((Float) valueAnimator.getAnimatedValue("rightAngel")).floatValue();
                CloverView.this.invalidate();
            }
        });
        this.bv.setDuration(2400L);
    }

    private void c(final float f, final float f2, final float f3) {
        i();
        this.bv.addListener(new Animator.AnimatorListener() { // from class: com.huawei.ucd.cloveranimation.CloverView.9
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                CloverView.this.cb = f;
                CloverView.this.bd = f2;
                CloverView.this.br = f3;
                if (CloverView.this.b != null) {
                    CloverView.this.b.onFinished();
                }
                if (CloverView.this.k) {
                    CloverView.this.z = true;
                    CloverView.this.b(1.0f, 1.0f, 1.0f);
                    CloverView.this.r = false;
                } else {
                    if (!CloverView.this.x || Float.compare(f, 1.0f) != 0 || Float.compare(f2, 1.0f) != 0 || Float.compare(f3, 1.0f) != 0) {
                        CloverView.this.r = false;
                        return;
                    }
                    CloverView.this.q = true;
                    CloverView.this.p = false;
                    CloverView.this.l();
                    CloverView.this.m();
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                CloverView.this.r = false;
                if (CloverView.this.b != null) {
                    CloverView.this.b.onFinished();
                }
            }
        });
        this.bv.start();
    }

    private PropertyValuesHolder getValueScaleRotation() {
        njt njtVar = new njt();
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 0.0f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.3030303f, 1.05f);
        ofFloat2.setInterpolator(njtVar);
        Keyframe ofFloat3 = Keyframe.ofFloat(0.6363636f, 0.98f);
        ofFloat3.setInterpolator(njtVar);
        Keyframe ofFloat4 = Keyframe.ofFloat(0.8181818f, 1.01f);
        ofFloat4.setInterpolator(njtVar);
        Keyframe ofFloat5 = Keyframe.ofFloat(1.0f, 1.0f);
        ofFloat5.setInterpolator(njtVar);
        return PropertyValuesHolder.ofKeyframe("valueScale", ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setScaleRatio(float f) {
        if (this.ai) {
            float f2 = this.cb;
            this.cg = f2 + ((1.0f - f2) * f);
        } else {
            this.cg = 1.0f;
        }
        if (this.af) {
            float f3 = this.bd;
            this.bm = f3 + ((1.0f - f3) * f);
        } else {
            this.bm = 1.0f;
        }
        if (this.ah) {
            float f4 = this.br;
            this.bp = f4 + ((1.0f - f4) * f);
        } else {
            this.bp = 1.0f;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final float f, final float f2, final float f3) {
        this.ad = true;
        ValueAnimator ofPropertyValuesHolder = ValueAnimator.ofPropertyValuesHolder(getValueScaleRotation());
        this.cd = ofPropertyValuesHolder;
        ofPropertyValuesHolder.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.ucd.cloveranimation.CloverView.8
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CloverView.this.setScaleRatio(((Float) valueAnimator.getAnimatedValue("valueScale")).floatValue());
                CloverView.this.bz = f;
                CloverView.this.bh = f2;
                CloverView.this.bo = f3;
                CloverView.this.invalidate();
            }
        });
        this.cd.setDuration(825L);
        this.cd.addListener(new Animator.AnimatorListener() { // from class: com.huawei.ucd.cloveranimation.CloverView.10
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                CloverView.this.cb = f;
                CloverView.this.bd = f2;
                CloverView.this.br = f3;
                if (Float.compare(f, 1.0f) != 0 || Float.compare(f2, 1.0f) != 0 || Float.compare(f3, 1.0f) != 0) {
                    CloverView.this.ad = false;
                    return;
                }
                CloverView.this.q = true;
                CloverView.this.p = false;
                CloverView.this.l();
                CloverView.this.m();
            }
        });
        this.cd.start();
    }

    public void a() {
        f();
        this.q = false;
        this.p = true;
        l();
        m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        njt njtVar = new njt();
        njq njqVar = new njq();
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 0.0f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.6666667f, 1.0f);
        ofFloat2.setInterpolator(njtVar);
        Keyframe ofFloat3 = Keyframe.ofFloat(0.0f, 1.0f);
        Keyframe ofFloat4 = Keyframe.ofFloat(0.22222222f, 0.95f);
        ofFloat4.setInterpolator(njqVar);
        Keyframe ofFloat5 = Keyframe.ofFloat(0.6666667f, 1.02f);
        ofFloat5.setInterpolator(njqVar);
        Keyframe ofFloat6 = Keyframe.ofFloat(1.0f, 1.0f);
        ofFloat6.setInterpolator(njqVar);
        ValueAnimator ofPropertyValuesHolder = ValueAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofKeyframe("scaleIn", ofFloat, ofFloat2, ofFloat6), PropertyValuesHolder.ofKeyframe("resilience", ofFloat3, ofFloat4, ofFloat5, ofFloat6));
        this.bb = ofPropertyValuesHolder;
        ofPropertyValuesHolder.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.ucd.cloveranimation.CloverView.15
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CloverView.this.ay = ((Float) valueAnimator.getAnimatedValue("scaleIn")).floatValue();
                CloverView.this.bn = ((Float) valueAnimator.getAnimatedValue("resilience")).floatValue();
                CloverView.this.invalidate();
            }
        });
        this.bb.setDuration(675L);
        this.bb.start();
    }

    private PropertyValuesHolder getAlphaRotation() {
        return PropertyValuesHolder.ofKeyframe("alpha", Keyframe.ofFloat(0.0f, 0.0f), Keyframe.ofFloat(0.09090909f, 0.3f), Keyframe.ofFloat(0.6818182f, 0.0f), Keyframe.ofFloat(1.0f, 0.0f));
    }

    private PropertyValuesHolder cwk_(FastOutSlowInInterpolator fastOutSlowInInterpolator) {
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 0.9f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.6818182f, 1.2f);
        ofFloat2.setInterpolator(fastOutSlowInInterpolator);
        Keyframe ofFloat3 = Keyframe.ofFloat(1.0f, 1.0f);
        ofFloat3.setInterpolator(fastOutSlowInInterpolator);
        return PropertyValuesHolder.ofKeyframe("scale", ofFloat, ofFloat2, ofFloat3);
    }

    private PropertyValuesHolder getAlphaRotation1() {
        return PropertyValuesHolder.ofKeyframe("alpha1", Keyframe.ofFloat(0.0f, 0.0f), Keyframe.ofFloat(0.22727273f, 0.0f), Keyframe.ofFloat(0.3181818f, 0.3f), Keyframe.ofFloat(0.84090906f, 0.0f), Keyframe.ofFloat(1.0f, 0.0f));
    }

    private PropertyValuesHolder cwl_(FastOutSlowInInterpolator fastOutSlowInInterpolator) {
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 1.0f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.22727273f, 0.9f);
        ofFloat2.setInterpolator(fastOutSlowInInterpolator);
        Keyframe ofFloat3 = Keyframe.ofFloat(0.84090906f, 1.2f);
        ofFloat3.setInterpolator(fastOutSlowInInterpolator);
        Keyframe ofFloat4 = Keyframe.ofFloat(1.0f, 1.0f);
        ofFloat4.setInterpolator(fastOutSlowInInterpolator);
        return PropertyValuesHolder.ofKeyframe("scale1", ofFloat, ofFloat2, ofFloat3, ofFloat4);
    }

    private PropertyValuesHolder getAlphaRotation2() {
        return PropertyValuesHolder.ofKeyframe("alpha2", Keyframe.ofFloat(0.0f, 0.0f), Keyframe.ofFloat(0.45454547f, 0.0f), Keyframe.ofFloat(0.54545456f, 0.3f), Keyframe.ofFloat(1.0f, 0.0f));
    }

    private PropertyValuesHolder cwm_(FastOutSlowInInterpolator fastOutSlowInInterpolator) {
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 1.0f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.45454547f, 0.9f);
        ofFloat2.setInterpolator(fastOutSlowInInterpolator);
        Keyframe ofFloat3 = Keyframe.ofFloat(1.0f, 1.2f);
        ofFloat3.setInterpolator(fastOutSlowInInterpolator);
        return PropertyValuesHolder.ofKeyframe("scale2", ofFloat, ofFloat2, ofFloat3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEncourageAnimValue(ValueAnimator valueAnimator) {
        this.au = ((Float) valueAnimator.getAnimatedValue("alpha")).floatValue();
        this.bc = ((Float) valueAnimator.getAnimatedValue("scale")).floatValue();
        this.av = ((Float) valueAnimator.getAnimatedValue("alpha1")).floatValue();
        this.az = ((Float) valueAnimator.getAnimatedValue("scale1")).floatValue();
        this.aw = ((Float) valueAnimator.getAnimatedValue("alpha2")).floatValue();
        this.ba = ((Float) valueAnimator.getAnimatedValue("scale2")).floatValue();
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (this.x) {
            FastOutSlowInInterpolator fastOutSlowInInterpolator = new FastOutSlowInInterpolator();
            ValueAnimator ofPropertyValuesHolder = ValueAnimator.ofPropertyValuesHolder(getAlphaRotation(), cwk_(fastOutSlowInInterpolator), getAlphaRotation1(), cwl_(fastOutSlowInInterpolator), getAlphaRotation2(), cwm_(fastOutSlowInInterpolator));
            this.at = ofPropertyValuesHolder;
            ofPropertyValuesHolder.setInterpolator(new FastOutSlowInInterpolator());
            this.at.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.ucd.cloveranimation.CloverView.4
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    CloverView.this.setEncourageAnimValue(valueAnimator);
                }
            });
            this.at.setDuration(3000L);
            this.at.addListener(new Animator.AnimatorListener() { // from class: com.huawei.ucd.cloveranimation.CloverView.1
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    CloverView.this.c();
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    if (CloverView.this.ck != null) {
                        CloverView.this.ck.onFinished();
                    }
                }
            });
            this.at.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.q) {
            this.ad = false;
            this.r = false;
            AddFrameListener addFrameListener = this.ck;
            if (addFrameListener != null) {
                addFrameListener.onFinished();
            }
        }
        if (this.k) {
            if (this.u) {
                c(this.aj);
            } else {
                a();
            }
            this.u = !this.u;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.cl == null) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f = motionEvent.getX();
            this.i = motionEvent.getY();
            this.ae = 0;
        } else if (action == 1) {
            int i = this.ae;
            if (i == 0 || (i == 2 && !this.d)) {
                e(motionEvent.getX(), motionEvent.getY());
            }
            this.d = false;
        } else if (action == 2) {
            if (!this.d && (Math.abs(motionEvent.getX() - this.f) > 10.0f || Math.abs(motionEvent.getY() - this.i) > 10.0f)) {
                this.d = true;
            }
            this.ae = 2;
        }
        return true;
    }

    public boolean cws_(Path path, float f, float f2) {
        RectF rectF = new RectF();
        Region region = new Region();
        path.computeBounds(rectF, true);
        float f3 = this.bf * this.cf;
        region.setPath(path, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
        return region.contains((int) (f / f3), (int) (f2 / f3));
    }

    private void e(float f, float f2) {
        float width = (getWidth() / 2.0f) - (((this.aq.i() * this.bf) * this.cf) / 2.0f);
        float height = (getHeight() * this.c) - ((this.aq.h() * this.bf) * this.cf);
        if (cws_(this.aq.cvq_(), f - width, f2 - height)) {
            this.cl.onTopClick();
            return;
        }
        float width2 = getWidth() / 2.0f;
        float height2 = getHeight() * this.c;
        double d = width2;
        double d2 = f - width2;
        double d3 = f2 - height2;
        double d4 = height2;
        if (cws_(this.aq.cvq_(), ((float) (((Math.cos(2.0943951023931953d) * d2) + d) - (Math.sin(2.0943951023931953d) * d3))) - width, ((float) ((Math.cos(2.0943951023931953d) * d3) + ((Math.sin(2.0943951023931953d) * d2) + d4))) - height)) {
            this.cl.onLeftClick();
            return;
        }
        if (cws_(this.aq.cvq_(), ((float) ((d + (Math.cos(-2.0943951023931953d) * d2)) - (Math.sin(-2.0943951023931953d) * d3))) - width, ((float) ((d4 + (d2 * Math.sin(-2.0943951023931953d))) + (d3 * Math.cos(-2.0943951023931953d)))) - height)) {
            this.cl.onRightClick();
        }
    }
}
