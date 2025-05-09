package com.huawei.healthcloud.plugintrack.ui.view;

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

/* loaded from: classes4.dex */
public class CircleProgressButton extends View {

    /* renamed from: a, reason: collision with root package name */
    private float f3776a;
    private Paint b;
    private CircleProcessListener c;
    private Paint d;
    private float e;
    private boolean f;
    private boolean g;
    private boolean h;
    private int i;
    private boolean j;
    private boolean k;
    private Bitmap l;
    private Matrix m;
    private Paint n;
    private e o;
    private int p;
    private float q;
    private RectF s;
    private Bitmap t;

    public interface CircleProcessListener {
        void onCancel();

        void onFinished();

        void onStarted();
    }

    static /* synthetic */ float b(CircleProgressButton circleProgressButton, float f) {
        float f2 = circleProgressButton.f3776a - f;
        circleProgressButton.f3776a = f2;
        return f2;
    }

    static /* synthetic */ float c(CircleProgressButton circleProgressButton, float f) {
        float f2 = circleProgressButton.q + f;
        circleProgressButton.q = f2;
        return f2;
    }

    static /* synthetic */ float d(CircleProgressButton circleProgressButton, float f) {
        float f2 = circleProgressButton.f3776a + f;
        circleProgressButton.f3776a = f2;
        return f2;
    }

    static /* synthetic */ float e(CircleProgressButton circleProgressButton, float f) {
        float f2 = circleProgressButton.q - f;
        circleProgressButton.q = f2;
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
        this.i = 10;
        this.o = new e(this);
        this.p = getResources().getDimensionPixelOffset(R.dimen._2131363847_res_0x7f0a0807);
        this.l = BitmapFactory.decodeResource(getResources(), R.drawable._2131431856_res_0x7f0b11b0);
        this.t = BitmapFactory.decodeResource(getResources(), R.drawable._2131431857_res_0x7f0b11b1);
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
        this.b = new Paint(1);
        this.e = f / 2.0f;
        float f2 = f * 2.0f;
        int i2 = (int) ((this.p - f2) - f2);
        int width = this.l.getWidth();
        if (width == 0) {
            LogUtil.h("Track_CircleProgressButton", "width == 0");
            return;
        }
        float f3 = this.e;
        float f4 = (-r5) - f3;
        float width2 = (((int) ((this.l.getWidth() - f2) - f2)) / 2) + f3;
        this.s = new RectF(f4, f4, width2, width2);
        float f5 = i2 / width;
        Matrix matrix = new Matrix();
        this.m = matrix;
        matrix.setTranslate((-this.l.getWidth()) / 2.0f, (-this.l.getHeight()) / 2.0f);
        this.m.postScale(f5, f5);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = this.p;
        setMeasuredDimension(i3, i3);
    }

    static class e extends Handler {
        private WeakReference<CircleProgressButton> e;

        e(CircleProgressButton circleProgressButton) {
            this.e = new WeakReference<>(circleProgressButton);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.b("Track_CircleProgressButton", "msg is null!");
                return;
            }
            super.handleMessage(message);
            CircleProgressButton circleProgressButton = this.e.get();
            if (circleProgressButton == null) {
                LogUtil.a("Track_CircleProgressButton", "LongPressHandler weakReference is null");
            } else {
                a(message.what, circleProgressButton);
            }
        }

        private void a(int i, CircleProgressButton circleProgressButton) {
            if (i == 0) {
                circleProgressButton.f = circleProgressButton.q == 360.0f;
                if (circleProgressButton.f) {
                    if (circleProgressButton.c != null) {
                        circleProgressButton.c.onFinished();
                        circleProgressButton.q = 0.0f;
                    }
                    removeMessages(0);
                    return;
                }
                CircleProgressButton.c(circleProgressButton, circleProgressButton.i);
                circleProgressButton.invalidate();
                sendEmptyMessageDelayed(0, 1L);
                return;
            }
            if (i == 1) {
                circleProgressButton.g = circleProgressButton.q == 0.0f;
                if (!circleProgressButton.g) {
                    CircleProgressButton.e(circleProgressButton, circleProgressButton.i);
                    circleProgressButton.invalidate();
                    sendEmptyMessageDelayed(1, 1L);
                    return;
                }
                removeMessages(1);
                return;
            }
            if (i == 2) {
                circleProgressButton.h = circleProgressButton.e - circleProgressButton.f3776a <= 0.0f;
                if (!circleProgressButton.h) {
                    CircleProgressButton.d(circleProgressButton, 0.5f);
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
            circleProgressButton.j = circleProgressButton.f3776a <= 0.0f;
            if (!circleProgressButton.j) {
                CircleProgressButton.b(circleProgressButton, 0.5f);
                circleProgressButton.invalidate();
                sendEmptyMessageDelayed(3, 1L);
                return;
            }
            removeMessages(3);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.k) {
            return true;
        }
        if (motionEvent == null) {
            LogUtil.b("Track_CircleProgressButton", "event is null");
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            if (this.f) {
                this.q = 0.0f;
            }
            if (!this.j) {
                this.o.sendEmptyMessage(3);
            }
            this.o.sendEmptyMessage(2);
            if (!this.g) {
                this.o.removeMessages(1);
            }
            CircleProcessListener circleProcessListener = this.c;
            if (circleProcessListener != null) {
                circleProcessListener.onStarted();
            }
            this.o.sendEmptyMessage(0);
        } else if (action == 1 || action == 3) {
            if (!this.h) {
                this.o.sendEmptyMessage(2);
            }
            this.o.sendEmptyMessage(3);
            if (!this.f) {
                CircleProcessListener circleProcessListener2 = this.c;
                if (circleProcessListener2 != null) {
                    circleProcessListener2.onCancel();
                }
                this.o.sendEmptyMessage(1);
            }
            this.o.removeMessages(0);
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
        if (this.f3776a == 0.0f && this.q == 0.0f) {
            canvas.drawBitmap(this.l, this.m, this.b);
        } else {
            canvas.drawBitmap(this.t, this.m, this.b);
        }
        if (this.q != 0.0f) {
            canvas.drawArc(this.s, 0.0f, 360.0f, false, this.d);
            canvas.drawArc(this.s, -90.0f, this.q, false, this.n);
        }
    }

    public void a(CircleProcessListener circleProcessListener) {
        if (circleProcessListener == null) {
            return;
        }
        this.c = circleProcessListener;
    }

    public void setProgressZero() {
        this.q = 0.0f;
        invalidate();
    }

    public void a(boolean z) {
        this.k = z;
    }
}
