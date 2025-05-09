package com.huawei.health.recognizekit.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.appcompat.widget.AppCompatImageView;
import com.huawei.health.R;
import defpackage.fba;
import defpackage.fbc;
import health.compact.a.LogUtil;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class OverLayerTopView extends AppCompatImageView {

    /* renamed from: a, reason: collision with root package name */
    private Bitmap f2960a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private RectF f;
    private int g;
    private int h;
    private Context i;
    private Bitmap j;
    private float k;
    private float l;
    private Paint m;
    private int n;
    private Paint o;
    private float p;
    private Paint q;
    private int r;
    private int s;
    private float t;
    private int u;
    private boolean v;
    private Bitmap w;
    private Bitmap x;
    private int y;

    public OverLayerTopView(Context context) {
        this(context, null, 0);
    }

    public OverLayerTopView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public OverLayerTopView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = null;
        this.b = false;
        this.e = false;
        this.d = true;
        this.c = true;
        this.v = true;
        a(context);
    }

    private void a(Context context) {
        d();
        this.i = context;
        c(context);
    }

    private void c(Context context) {
        Point auZ_ = fba.auZ_(context);
        this.f = fba.auU_(this.i);
        this.u = auZ_.x;
        int i = auZ_.y;
        this.y = i;
        this.n = this.u;
        this.s = 0;
        this.h = i;
    }

    private void d() {
        Paint paint = new Paint(1);
        this.m = paint;
        paint.setColor(Color.parseColor("#66FFFFFF"));
        this.m.setStyle(Paint.Style.STROKE);
        this.m.setStrokeWidth(5.0f);
        this.o = new Paint(1);
        Paint paint2 = new Paint(1);
        this.q = paint2;
        paint2.setColor(Color.parseColor("#FF0D9FFB"));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.p = motionEvent.getX();
            this.t = motionEvent.getY();
            this.b = c();
            LogUtil.c("OverLayerTopView", "onTouchEvent action_down mTouchX: " + this.p + " mTouchY:" + this.t + " isValidTouch:" + this.b);
        } else if (action == 1) {
            this.b = false;
        } else if (action == 2) {
            this.k = motionEvent.getX();
            this.l = motionEvent.getY();
            f();
        }
        return true;
    }

    private void f() {
        if (this.b && this.d) {
            float f = this.k;
            boolean z = f > 0.0f && f < ((float) this.n);
            float f2 = this.l;
            if (f2 <= this.s || f2 >= this.h || !z) {
                return;
            }
            this.f = fba.auW_(f, f2, this.r, this.f);
            postInvalidate();
        }
    }

    public void e(boolean z) {
        this.v = z;
        invalidate();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        RectF rectF = this.f;
        if (rectF == null) {
            return;
        }
        if (this.v) {
            canvas.drawRoundRect(rectF, 8.0f, 8.0f, this.m);
            avh_(canvas);
        }
        if (this.e) {
            if (this.g < this.f.top) {
                this.g = (int) this.f.top;
                this.c = true;
            }
            if (this.g < this.f.bottom && this.c) {
                this.g += 10;
            } else {
                this.g -= 10;
                this.c = false;
            }
            canvas.drawRect(this.f.left + 5.0f, this.g - 3, this.f.right - 5.0f, this.g + 3, this.q);
            postInvalidateDelayed(10L, (int) this.f.left, (int) this.f.top, (int) this.f.right, (int) this.f.bottom);
        }
        super.onDraw(canvas);
    }

    private void avh_(Canvas canvas) {
        Bitmap bitmap = this.j;
        if (bitmap == null || bitmap.isRecycled()) {
            this.j = BitmapFactory.decodeResource(getResources(), R.drawable._2131431171_res_0x7f0b0f03);
        }
        Bitmap bitmap2 = this.w;
        if (bitmap2 == null || bitmap2.isRecycled()) {
            this.w = fbc.avg_(this.j, 90.0f);
        }
        Bitmap bitmap3 = this.x;
        if (bitmap3 == null || bitmap3.isRecycled()) {
            this.x = fbc.avg_(this.j, 180.0f);
        }
        Bitmap bitmap4 = this.f2960a;
        if (bitmap4 == null || bitmap4.isRecycled()) {
            this.f2960a = fbc.avg_(this.j, -90.0f);
        }
        canvas.drawBitmap(this.j, this.f.left - 20.0f, this.f.top - 20.0f, this.o);
        canvas.drawBitmap(this.w, this.f.right - 50.0f, this.f.top - 20.0f, this.o);
        canvas.drawBitmap(this.x, this.f.right - 50.0f, this.f.bottom - 50.0f, this.o);
        canvas.drawBitmap(this.f2960a, this.f.left - 20.0f, this.f.bottom - 50.0f, this.o);
    }

    private boolean c() {
        ArrayList<RectF> avb_ = fba.avb_(this.f);
        if (avb_ == null) {
            return false;
        }
        for (int i = 0; i < avb_.size(); i++) {
            if (avb_.get(i).contains(this.p, this.t)) {
                this.r = i;
                return true;
            }
        }
        return false;
    }

    public void b() {
        LogUtil.c("OverLayerTopView", "startScanAnimation");
        this.e = true;
        this.d = false;
        this.g = (int) this.f.top;
        postInvalidate();
    }

    public void e() {
        LogUtil.c("OverLayerTopView", "endScanAnimation");
        this.e = false;
        this.d = true;
        postInvalidate();
    }

    public RectF getCenterRect() {
        return this.f;
    }

    public void a() {
        if (this.i == null) {
            LogUtil.e("OverLayerTopView", "refreshRect mContext is null");
            return;
        }
        LogUtil.c("OverLayerTopView", "refresh on TahitiModel");
        c(this.i);
        postInvalidate();
    }

    public void setMaxDragRange(Bitmap bitmap) {
        LogUtil.c("OverLayerTopView", "setMaxDragRange");
        RectF auS_ = fba.auS_(bitmap, fba.auZ_(this.i));
        this.s = (int) auS_.top;
        this.h = (int) auS_.bottom;
        this.f = fba.auT_(bitmap.getWidth(), bitmap.getHeight(), this.i);
        postInvalidate();
    }

    public void setMaxRectHeight(int i) {
        this.h = i;
    }
}
