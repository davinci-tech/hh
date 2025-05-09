package com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.CountDownTimer;
import android.os.Message;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsn;
import defpackage.psm;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/* loaded from: classes6.dex */
public class TimeClockView extends SurfaceView implements SurfaceHolder.Callback {

    /* renamed from: a, reason: collision with root package name */
    private int f9878a;
    private int ab;
    private int b;
    private final int c;
    private float d;
    private Canvas e;
    private final int[] f;
    private CountDownTimer g;
    private final int[] h;
    private Paint i;
    private Context j;
    private Paint k;
    private d l;
    private RectF m;
    private SurfaceHolder n;
    private Paint o;
    private int p;
    private int q;
    private Paint r;
    private Paint s;
    private RectF t;
    private float u;
    private HealthTextView v;
    private int w;
    private int x;
    private Paint y;

    public TimeClockView(Context context) {
        this(context, null);
    }

    public TimeClockView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimeClockView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.u = 0.0f;
        this.d = 6.0f;
        this.p = 0;
        int color = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296779_res_0x7f09020b);
        this.c = color;
        this.h = new int[]{ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296709_res_0x7f0901c5), ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296778_res_0x7f09020a), ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296777_res_0x7f090209)};
        this.f = new int[]{ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296709_res_0x7f0901c5), ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296784_res_0x7f090210), color};
        this.j = context;
        setZOrderOnTop(true);
        c();
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.n.setFormat(-2);
        setZOrderOnTop(true);
        e();
    }

    private void e() {
        this.f9878a = nsn.c(BaseApplication.getContext(), 120.0f);
        this.b = nsn.c(BaseApplication.getContext(), 120.0f);
        this.q = nsn.c(BaseApplication.getContext(), 118.0f);
        this.ab = nsn.c(BaseApplication.getContext(), 9.0f);
        this.x = nsn.c(BaseApplication.getContext(), 1.5f);
        int i = this.q;
        float f = -i;
        float f2 = i;
        this.t = new RectF(f, f, f2, f2);
        int i2 = this.q - this.ab;
        float f3 = -i2;
        float f4 = i2;
        this.m = new RectF(f3, f3, f4, f4);
    }

    private void c() {
        LogUtil.a("TimeClockView", "initView");
        this.s = new Paint();
        this.i = new Paint();
        this.k = new Paint();
        this.r = new Paint();
        this.y = new Paint();
        this.o = new Paint();
        SurfaceHolder holder = getHolder();
        this.n = holder;
        holder.setFormat(-2);
        this.n.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(b(i), b(i2));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private int b(int i) {
        int c = nsn.c(BaseApplication.getContext(), 500.0f);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return mode == 1073741824 ? size : mode == Integer.MIN_VALUE ? Math.min(c, size) : c;
    }

    private void dtB_(Canvas canvas) {
        this.i.setColor(this.j.getResources().getColor(R.color._2131296783_res_0x7f09020f));
        this.i.setAntiAlias(true);
        this.i.setStrokeWidth(nsn.c(BaseApplication.getContext(), 1.0f));
        canvas.translate(this.f9878a, this.b);
        for (int i = 0; i < 180; i++) {
            canvas.drawLine(0.0f, this.q - nsn.c(BaseApplication.getContext(), 8.0f), 0.0f, this.q, this.i);
            canvas.rotate(2.0f, 0.0f, 0.0f);
        }
    }

    private void dtC_(Canvas canvas) {
        this.s.setColor(this.c);
        this.s.setStyle(Paint.Style.FILL);
        this.s.setAntiAlias(true);
        this.s.setStrokeWidth(nsn.c(BaseApplication.getContext(), 2.0f));
        canvas.drawCircle(0.0f, 0.0f, nsn.c(BaseApplication.getContext(), 4.0f), this.s);
    }

    private void dtD_(Canvas canvas) {
        dtz_(canvas);
        dtE_(canvas);
    }

    private void dtA_(Canvas canvas) {
        this.r.setColor(-1);
        this.r.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0.0f, 0.0f, nsn.c(BaseApplication.getContext(), 2.0f), this.r);
    }

    private void dtE_(Canvas canvas) {
        this.y.reset();
        this.y.setStrokeWidth(nsn.c(BaseApplication.getContext(), 1.5f));
        this.y.setAntiAlias(true);
        this.y.setColor(this.c);
        this.y.setStyle(Paint.Style.FILL);
        this.y.setShadowLayer(10.0f, -5.0f, 0.0f, Color.parseColor("#80FB6522"));
        canvas.rotate(this.u, 0.0f, 0.0f);
        canvas.drawLine(0.0f, nsn.c(BaseApplication.getContext(), 9.0f), 0.0f, -this.q, this.y);
    }

    private void dtz_(Canvas canvas) {
        this.o.reset();
        this.o.setAntiAlias(true);
        this.o.setStrokeWidth(this.x);
        this.o.setStyle(Paint.Style.STROKE);
        this.k.reset();
        this.k.setAntiAlias(true);
        this.k.setStyle(Paint.Style.STROKE);
        this.k.setStrokeWidth(this.ab * 2);
        canvas.save();
        SweepGradient sweepGradient = new SweepGradient(0.0f, 0.0f, this.h, (float[]) null);
        SweepGradient sweepGradient2 = new SweepGradient(0.0f, 0.0f, this.f, (float[]) null);
        this.k.setShader(sweepGradient);
        this.o.setShader(sweepGradient2);
        canvas.rotate(-90.0f, 0.0f, 0.0f);
        canvas.drawArc(this.m, 0.0f, this.u, false, this.k);
        canvas.rotate(0.0f, 0.0f, 0.0f);
        canvas.drawArc(this.t, 0.0f, this.u, false, this.o);
        canvas.restore();
    }

    public void d(int i, int i2, HealthTextView healthTextView) {
        this.v = healthTextView;
        healthTextView.setTextSize(18.0f);
        if (i2 != 0 && i <= i2) {
            float f = i2;
            this.d = 360.0f / f;
            if (i == i2) {
                this.p = i;
                this.u = (i * 360) / f;
                this.w = 0;
            } else {
                this.p = i2 - i;
                this.u = (r3 * 360) / f;
                this.w = 2;
            }
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.l = new d(this);
        h();
        a();
        LogUtil.a("TimeClockView", "surfaceCreated");
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        LogUtil.a("TimeClockView", "surfaceChanged");
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        LogUtil.a("TimeClockView", "surfaceDestroyed");
    }

    private void h() {
        Canvas lockCanvas = this.n.lockCanvas();
        this.e = lockCanvas;
        lockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        dtB_(this.e);
        this.e.save();
        dtD_(this.e);
        this.e.save();
        dtC_(this.e);
        dtA_(this.e);
        d();
        postInvalidate();
        this.n.unlockCanvasAndPost(this.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        Canvas lockCanvas = this.n.lockCanvas();
        this.e = lockCanvas;
        if (lockCanvas != null) {
            lockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            dtB_(this.e);
            this.e.save();
            dtD_(this.e);
            this.e.save();
            dtC_(this.e);
            dtA_(this.e);
            postInvalidate();
            this.n.unlockCanvasAndPost(this.e);
        }
        d();
        LogUtil.a("TimeClockView", "second= ", Integer.valueOf(this.p));
        if (this.u == 0.0f) {
            psm.e().c(4, this.w);
        }
        this.p--;
        this.u -= this.d;
    }

    private void d() {
        if (this.v == null || this.p < 0) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(0, 0, 0, 0, 0, this.p);
        this.v.setText(new SimpleDateFormat(Constants.TIME_FORMAT_WITHOUT_MILLS, Locale.getDefault()).format(calendar.getTime()));
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.view.TimeClockView$5] */
    private void a() {
        LogUtil.a("TimeClockView", "threadStart");
        if (this.g == null) {
            LogUtil.a("TimeClockView", "CountDownTimer second = ", Integer.valueOf(this.p));
            this.g = new CountDownTimer(120000L, 990) { // from class: com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.view.TimeClockView.5
                @Override // android.os.CountDownTimer
                public void onFinish() {
                }

                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                    LogUtil.a("TimeClockView", "countDownTimer is run");
                    Message.obtain(TimeClockView.this.l, 0).sendToTarget();
                }
            }.start();
        }
    }

    public void b() {
        CountDownTimer countDownTimer = this.g;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        d dVar = this.l;
        if (dVar != null) {
            dVar.removeCallbacksAndMessages(null);
            this.l = null;
            LogUtil.a("TimeClockView", "TimeClockView threadStop");
        }
    }

    static class d extends BaseHandler<TimeClockView> {
        d(TimeClockView timeClockView) {
            super(timeClockView);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dtF_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(TimeClockView timeClockView, Message message) {
            if (message.what == 0) {
                if (timeClockView.p > 60 || timeClockView.p < 0) {
                    return;
                }
                timeClockView.g();
                return;
            }
            LogUtil.a("TimeClockView", "no case match!");
        }
    }
}
