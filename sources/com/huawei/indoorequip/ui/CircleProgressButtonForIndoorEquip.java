package com.huawei.indoorequip.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class CircleProgressButtonForIndoorEquip extends View {

    /* renamed from: a, reason: collision with root package name */
    private float f6431a;
    private int ab;
    private Paint b;
    private float c;
    private int d;
    private CircleProcessListener e;
    private Bitmap f;
    private Bitmap g;
    private Bitmap h;
    private Bitmap i;
    private Handler j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private Bitmap p;
    private Bitmap q;
    private Matrix r;
    private Paint s;
    private c t;
    private int u;
    private RectF v;
    private Bitmap w;
    private float x;
    private Bitmap y;

    public interface CircleProcessListener {
        void onCancel();

        void onFinished();

        void onStarted();
    }

    static /* synthetic */ float b(CircleProgressButtonForIndoorEquip circleProgressButtonForIndoorEquip, double d) {
        float f = (float) (circleProgressButtonForIndoorEquip.f6431a + d);
        circleProgressButtonForIndoorEquip.f6431a = f;
        return f;
    }

    static /* synthetic */ float b(CircleProgressButtonForIndoorEquip circleProgressButtonForIndoorEquip, float f) {
        float f2 = circleProgressButtonForIndoorEquip.x + f;
        circleProgressButtonForIndoorEquip.x = f2;
        return f2;
    }

    static /* synthetic */ float c(CircleProgressButtonForIndoorEquip circleProgressButtonForIndoorEquip, double d) {
        float f = (float) (circleProgressButtonForIndoorEquip.f6431a - d);
        circleProgressButtonForIndoorEquip.f6431a = f;
        return f;
    }

    static /* synthetic */ float e(CircleProgressButtonForIndoorEquip circleProgressButtonForIndoorEquip, float f) {
        float f2 = circleProgressButtonForIndoorEquip.x - f;
        circleProgressButtonForIndoorEquip.x = f2;
        return f2;
    }

    public CircleProgressButtonForIndoorEquip(Context context) {
        this(context, null);
    }

    public CircleProgressButtonForIndoorEquip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleProgressButtonForIndoorEquip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.x = 0.0f;
        this.ab = 0;
        this.o = false;
        this.d = 1;
        this.t = new c(this);
        this.j = new Handler(Looper.getMainLooper()) { // from class: com.huawei.indoorequip.ui.CircleProgressButtonForIndoorEquip.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null) {
                    return;
                }
                super.handleMessage(message);
                if (message.what != 999) {
                    return;
                }
                CircleProgressButtonForIndoorEquip.this.t.sendEmptyMessage(9);
            }
        };
        this.u = getResources().getDimensionPixelOffset(R.dimen._2131364575_res_0x7f0a0adf);
        this.q = BitmapFactory.decodeResource(getResources(), R.drawable._2131430664_res_0x7f0b0d08);
        this.y = BitmapFactory.decodeResource(getResources(), R.drawable._2131430665_res_0x7f0b0d09);
        this.f = BitmapFactory.decodeResource(getResources(), R.drawable._2131430664_res_0x7f0b0d08);
        this.h = BitmapFactory.decodeResource(getResources(), R.drawable._2131430664_res_0x7f0b0d08);
        this.g = BitmapFactory.decodeResource(getResources(), R.drawable._2131430664_res_0x7f0b0d08);
        this.i = BitmapFactory.decodeResource(getResources(), R.drawable._2131430664_res_0x7f0b0d08);
        this.w = BitmapFactory.decodeResource(getResources(), R.drawable._2131430664_res_0x7f0b0d08);
        this.p = BitmapFactory.decodeResource(getResources(), R.drawable._2131430664_res_0x7f0b0d08);
        Paint paint = new Paint(1);
        this.s = paint;
        paint.setColor(Color.parseColor("#F3301E"));
        this.s.setStyle(Paint.Style.STROKE);
        float dimension = getResources().getDimension(R.dimen._2131364574_res_0x7f0a0ade) / 2.0f;
        this.s.setStrokeWidth(dimension);
        this.s.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint(1);
        this.b = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.b.setStrokeWidth(dimension);
        this.b.setStrokeCap(Paint.Cap.ROUND);
        this.b.setColor(Color.parseColor("#33F3301E"));
        float f = dimension / 2.0f;
        this.c = f;
        float f2 = dimension * 2.0f;
        int i2 = (int) ((this.u - f2) - f2);
        float f3 = ((-r1) - dimension) - f;
        float f4 = (i2 / 2) + dimension + f;
        this.v = new RectF(f3, f3, f4, f4);
        int width = this.q.getWidth();
        if (width == 0) {
            LogUtil.h("Track_IDEQ_CircleProgressButton", "width == 0");
            return;
        }
        float f5 = i2 / width;
        Matrix matrix = new Matrix();
        this.r = matrix;
        matrix.setTranslate((-this.q.getWidth()) / 2.0f, (-this.q.getHeight()) / 2.0f);
        this.r.postScale(f5, f5);
    }

    public int getState() {
        return this.ab;
    }

    public void setState(int i) {
        this.ab = i;
        this.t.sendEmptyMessage(9);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = this.u;
        setMeasuredDimension(i3, i3);
    }

    static class c extends Handler {
        private WeakReference<CircleProgressButtonForIndoorEquip> e;

        c(CircleProgressButtonForIndoorEquip circleProgressButtonForIndoorEquip) {
            this.e = new WeakReference<>(circleProgressButtonForIndoorEquip);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            super.handleMessage(message);
            CircleProgressButtonForIndoorEquip circleProgressButtonForIndoorEquip = this.e.get();
            if (circleProgressButtonForIndoorEquip == null) {
                return;
            }
            c(message.what, circleProgressButtonForIndoorEquip);
        }

        private void c(int i, CircleProgressButtonForIndoorEquip circleProgressButtonForIndoorEquip) {
            if (i == 0) {
                circleProgressButtonForIndoorEquip.k = circleProgressButtonForIndoorEquip.x == 360.0f;
                if (circleProgressButtonForIndoorEquip.k) {
                    if (circleProgressButtonForIndoorEquip.e != null) {
                        circleProgressButtonForIndoorEquip.e.onFinished();
                        circleProgressButtonForIndoorEquip.x = 0.0f;
                    }
                    removeMessages(0);
                    return;
                }
                CircleProgressButtonForIndoorEquip.b(circleProgressButtonForIndoorEquip, 10.0f);
                circleProgressButtonForIndoorEquip.invalidate();
                sendEmptyMessageDelayed(0, 1L);
                return;
            }
            if (i == 1) {
                circleProgressButtonForIndoorEquip.n = circleProgressButtonForIndoorEquip.x == 0.0f;
                if (!circleProgressButtonForIndoorEquip.n) {
                    CircleProgressButtonForIndoorEquip.e(circleProgressButtonForIndoorEquip, 10.0f);
                    circleProgressButtonForIndoorEquip.invalidate();
                    sendEmptyMessageDelayed(1, 1L);
                    return;
                }
                removeMessages(1);
                return;
            }
            if (i != 2) {
                if (i == 3) {
                    e(circleProgressButtonForIndoorEquip);
                    return;
                } else {
                    if (i != 9) {
                        return;
                    }
                    circleProgressButtonForIndoorEquip.invalidate();
                    return;
                }
            }
            circleProgressButtonForIndoorEquip.l = circleProgressButtonForIndoorEquip.c - circleProgressButtonForIndoorEquip.f6431a <= 0.0f;
            if (!circleProgressButtonForIndoorEquip.l) {
                CircleProgressButtonForIndoorEquip.b(circleProgressButtonForIndoorEquip, 0.5d);
                circleProgressButtonForIndoorEquip.invalidate();
                sendEmptyMessageDelayed(2, 1L);
                return;
            }
            removeMessages(2);
        }

        private void e(CircleProgressButtonForIndoorEquip circleProgressButtonForIndoorEquip) {
            circleProgressButtonForIndoorEquip.m = circleProgressButtonForIndoorEquip.f6431a <= 0.0f;
            if (!circleProgressButtonForIndoorEquip.m) {
                CircleProgressButtonForIndoorEquip.c(circleProgressButtonForIndoorEquip, 0.5d);
                circleProgressButtonForIndoorEquip.invalidate();
                sendEmptyMessageDelayed(3, 1L);
                return;
            }
            removeMessages(3);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        CircleProcessListener circleProcessListener;
        int i;
        int action = motionEvent.getAction();
        if (this.o || (i = this.ab) == 2 || i == 1 || i == 4 || i == 3 || i == 6) {
            if (action == 0 && (circleProcessListener = this.e) != null) {
                circleProcessListener.onStarted();
            }
            return true;
        }
        if (action == 0) {
            if (this.k) {
                this.x = 0.0f;
            }
            if (!this.m) {
                this.t.sendEmptyMessage(3);
            }
            this.t.sendEmptyMessage(2);
            if (!this.n) {
                this.t.removeMessages(1);
            }
            CircleProcessListener circleProcessListener2 = this.e;
            if (circleProcessListener2 != null) {
                circleProcessListener2.onStarted();
            }
            this.t.sendEmptyMessage(0);
        } else if (action == 1 || action == 3) {
            if (!this.l) {
                this.t.sendEmptyMessage(2);
            }
            this.t.sendEmptyMessage(3);
            if (!this.k) {
                CircleProcessListener circleProcessListener3 = this.e;
                if (circleProcessListener3 != null) {
                    circleProcessListener3.onCancel();
                }
                this.t.sendEmptyMessage(1);
            }
            this.t.removeMessages(0);
        }
        return true;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas == null) {
            return;
        }
        float f = this.u / 2.0f;
        canvas.translate(f, f);
        int i = this.ab;
        if (i == 1) {
            this.j.removeMessages(999);
            this.j.sendEmptyMessageDelayed(999, 500L);
            int i2 = this.d;
            if (i2 == 1) {
                this.d = 2;
                canvas.drawBitmap(this.f, this.r, null);
            } else if (i2 == 2) {
                this.d = 1;
                canvas.drawBitmap(this.h, this.r, null);
            }
        } else if (i == 4) {
            this.d = 1;
            this.j.removeMessages(999);
            canvas.drawBitmap(this.g, this.r, null);
        } else if (i == 5) {
            this.d = 1;
            this.j.removeMessages(999);
            canvas.drawBitmap(this.i, this.r, null);
        } else if (i == 2) {
            this.d = 1;
            this.j.removeMessages(999);
            canvas.drawBitmap(this.w, this.r, null);
        } else if (i == 3) {
            this.d = 1;
            this.j.removeMessages(999);
            canvas.drawBitmap(this.p, this.r, null);
        } else {
            this.d = 1;
            this.j.removeMessages(999);
            if (this.f6431a == 0.0f) {
                canvas.drawBitmap(this.q, this.r, null);
            } else {
                canvas.drawBitmap(this.y, this.r, null);
            }
        }
        if (this.x != 0.0f) {
            canvas.drawArc(this.v, 0.0f, 360.0f, false, this.b);
            canvas.drawArc(this.v, -90.0f, this.x, false, this.s);
        }
    }

    public void b(CircleProcessListener circleProcessListener) {
        this.e = circleProcessListener;
    }
}
