package com.huawei.health.basesport.controls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes8.dex */
public class CircleProgressButton extends View {

    /* renamed from: a, reason: collision with root package name */
    private Paint f2193a;
    private float b;
    private float c;
    private Paint d;
    private CircleProcessListener e;
    private int f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private e k;
    private boolean l;
    private Bitmap m;
    private Paint n;
    private Matrix o;
    private int p;
    private float q;
    private RectF r;
    private Bitmap s;

    public interface CircleProcessListener {
        void onCancel();

        void onFinished();

        void onStarted();
    }

    static /* synthetic */ float a(CircleProgressButton circleProgressButton, float f) {
        float f2 = circleProgressButton.q + f;
        circleProgressButton.q = f2;
        return f2;
    }

    static /* synthetic */ float c(CircleProgressButton circleProgressButton, float f) {
        float f2 = circleProgressButton.q - f;
        circleProgressButton.q = f2;
        return f2;
    }

    static /* synthetic */ float d(CircleProgressButton circleProgressButton, float f) {
        float f2 = circleProgressButton.b - f;
        circleProgressButton.b = f2;
        return f2;
    }

    static /* synthetic */ float e(CircleProgressButton circleProgressButton, float f) {
        float f2 = circleProgressButton.b + f;
        circleProgressButton.b = f2;
        return f2;
    }

    public CircleProgressButton(Context context) {
        this(context, null);
    }

    public CircleProgressButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleProgressButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = 10;
        this.k = new e(this);
        this.p = getResources().getDimensionPixelOffset(R.dimen._2131363847_res_0x7f0a0807);
        this.m = BitmapFactory.decodeResource(getResources(), R.drawable._2131431856_res_0x7f0b11b0);
        this.s = BitmapFactory.decodeResource(getResources(), R.drawable._2131431857_res_0x7f0b11b1);
        float dimension = getResources().getDimension(R.dimen._2131363846_res_0x7f0a0806);
        Paint paint = new Paint(1);
        this.n = paint;
        float f = dimension / 2.0f;
        paint.setColor(Color.parseColor("#F3301E"));
        this.n.setStyle(Paint.Style.STROKE);
        this.n.setStrokeWidth(f);
        this.n.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint(1);
        this.d = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.d.setStrokeWidth(f);
        this.d.setStrokeCap(Paint.Cap.ROUND);
        this.d.setColor(Color.parseColor("#33F3301E"));
        this.f2193a = new Paint(1);
        this.c = f / 2.0f;
        float f2 = f * 2.0f;
        int i2 = (int) ((this.p - f2) - f2);
        int width = this.m.getWidth();
        if (width == 0) {
            LogUtil.h("Track_CircleProgressButton", "width == 0");
            return;
        }
        float f3 = this.c;
        float f4 = (-r5) - f3;
        float width2 = (((int) ((this.m.getWidth() - f2) - f2)) / 2) + f3;
        this.r = new RectF(f4, f4, width2, width2);
        float f5 = i2 / width;
        Matrix matrix = new Matrix();
        this.o = matrix;
        matrix.setTranslate((-this.m.getWidth()) / 2.0f, (-this.m.getHeight()) / 2.0f);
        this.o.postScale(f5, f5);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = this.p;
        setMeasuredDimension(i3, i3);
    }

    static class e extends Handler {
        private WeakReference<CircleProgressButton> d;

        e(CircleProgressButton circleProgressButton) {
            this.d = new WeakReference<>(circleProgressButton);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.b("Track_CircleProgressButton", "msg is null!");
                return;
            }
            super.handleMessage(message);
            CircleProgressButton circleProgressButton = this.d.get();
            if (circleProgressButton == null) {
                LogUtil.a("Track_CircleProgressButton", "LongPressHandler weakReference is null");
            } else {
                a(message.what, circleProgressButton);
            }
        }

        private void a(int i, CircleProgressButton circleProgressButton) {
            if (i == 0) {
                circleProgressButton.i = circleProgressButton.q == 360.0f;
                if (circleProgressButton.i) {
                    if (circleProgressButton.e != null) {
                        circleProgressButton.e.onFinished();
                        circleProgressButton.q = 0.0f;
                    }
                    removeMessages(0);
                    return;
                }
                CircleProgressButton.a(circleProgressButton, circleProgressButton.f);
                circleProgressButton.invalidate();
                sendEmptyMessageDelayed(0, 1L);
                return;
            }
            if (i == 1) {
                circleProgressButton.g = circleProgressButton.q == 0.0f;
                if (!circleProgressButton.g) {
                    CircleProgressButton.c(circleProgressButton, circleProgressButton.f);
                    circleProgressButton.invalidate();
                    sendEmptyMessageDelayed(1, 1L);
                    return;
                }
                removeMessages(1);
                return;
            }
            if (i == 2) {
                circleProgressButton.j = circleProgressButton.c - circleProgressButton.b <= 0.0f;
                if (!circleProgressButton.j) {
                    CircleProgressButton.e(circleProgressButton, 0.5f);
                    circleProgressButton.invalidate();
                    sendEmptyMessageDelayed(2, 1L);
                    return;
                }
                removeMessages(2);
                return;
            }
            if (i != 3) {
                return;
            }
            circleProgressButton.h = circleProgressButton.b <= 0.0f;
            if (!circleProgressButton.h) {
                CircleProgressButton.d(circleProgressButton, 0.5f);
                circleProgressButton.invalidate();
                sendEmptyMessageDelayed(3, 1L);
                return;
            }
            removeMessages(3);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.l) {
            return true;
        }
        if (motionEvent == null) {
            LogUtil.b("Track_CircleProgressButton", "event is null");
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            if (this.i) {
                this.q = 0.0f;
            }
            if (!this.h) {
                this.k.sendEmptyMessage(3);
            }
            this.k.sendEmptyMessage(2);
            if (!this.g) {
                this.k.removeMessages(1);
            }
            CircleProcessListener circleProcessListener = this.e;
            if (circleProcessListener != null) {
                circleProcessListener.onStarted();
            }
            this.k.sendEmptyMessage(0);
        } else if (action == 1 || action == 3) {
            if (!this.j) {
                this.k.sendEmptyMessage(2);
            }
            this.k.sendEmptyMessage(3);
            if (!this.i) {
                CircleProcessListener circleProcessListener2 = this.e;
                if (circleProcessListener2 != null) {
                    circleProcessListener2.onCancel();
                }
                this.k.sendEmptyMessage(1);
            }
            this.k.removeMessages(0);
        }
        return true;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas == null) {
            LogUtil.h("Track_CircleProgressButton", "canvas is null");
            return;
        }
        super.onDraw(canvas);
        float f = this.p / 2.0f;
        canvas.translate(f, f);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        if (this.b == 0.0f && this.q == 0.0f) {
            canvas.drawBitmap(this.m, this.o, this.f2193a);
        } else {
            canvas.drawBitmap(this.s, this.o, this.f2193a);
        }
        if (this.q != 0.0f) {
            canvas.drawArc(this.r, 0.0f, 360.0f, false, this.d);
            canvas.drawArc(this.r, -90.0f, this.q, false, this.n);
        }
    }

    public void e(CircleProcessListener circleProcessListener) {
        if (circleProcessListener == null) {
            return;
        }
        this.e = circleProcessListener;
    }

    public void setProgressZero() {
        this.q = 0.0f;
        invalidate();
    }

    public void e(boolean z) {
        this.l = z;
    }
}
