package com.huawei.ui.main.stories.userprofile.scroll;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LanguageUtil;

/* loaded from: classes7.dex */
public class PersonalLevelProgress extends View {
    private static final int b = 2131298909;
    private static final int d = 2131820986;

    /* renamed from: a, reason: collision with root package name */
    private Context f10535a;
    private Paint c;
    private Paint e;
    private Bitmap f;
    private float g;
    private int h;
    private Paint i;
    private double j;
    private int l;
    private float o;

    public PersonalLevelProgress(Context context) {
        this(context, null);
    }

    public PersonalLevelProgress(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PersonalLevelProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = 1;
        this.j = 0.0d;
        this.f10535a = context;
        this.g = context.getResources().getDimension(R.dimen._2131363003_res_0x7f0a04bb);
        this.o = this.f10535a.getResources().getDimension(R.dimen._2131363003_res_0x7f0a04bb);
        c();
    }

    public void e(int i, double d2) {
        if (this.h == i && d2 == this.j) {
            LogUtil.h("PersonalLevelProgress", "refreshProgress no need to refresh");
            return;
        }
        this.h = i;
        this.j = d2;
        this.f = getAchieveBitmap();
        invalidate();
    }

    private void c() {
        LogUtil.a("PersonalLevelProgress", "initPaint");
        Paint paint = new Paint();
        this.i = paint;
        paint.setAntiAlias(true);
        this.i.setStyle(Paint.Style.FILL);
        this.i.setColor(this.f10535a.getResources().getColor(b));
        Paint paint2 = new Paint();
        this.c = paint2;
        paint2.setAntiAlias(true);
        this.c.setStyle(Paint.Style.FILL);
        this.c.setColor(this.f10535a.getResources().getColor(R.color._2131298908_res_0x7f090a5c));
        Paint paint3 = new Paint();
        this.e = paint3;
        paint3.setAntiAlias(true);
        this.e.setStyle(Paint.Style.FILL);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
        this.l = getMeasuredWidth();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas == null) {
            LogUtil.b("PersonalLevelProgress", "canvas is null");
            return;
        }
        super.onDraw(canvas);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        if (this.f == null) {
            this.f = getAchieveBitmap();
        }
        dVE_(canvas);
    }

    private void dVE_(Canvas canvas) {
        float f;
        float f2;
        float height = (this.f.getHeight() / 2.0f) - (this.g / 2.0f);
        float f3 = this.l;
        float height2 = (this.f.getHeight() / 2.0f) + (this.g / 2.0f);
        float f4 = (int) (this.l * this.j);
        if (f4 <= this.f.getWidth() / 2.0f) {
            f = f3;
            f2 = (this.f.getWidth() / 2.0f) - f4;
        } else {
            if (f4 >= this.l - (this.f.getWidth() / 2.0f)) {
                f3 = ((this.l * 2) - (this.f.getWidth() / 2.0f)) - f4;
            }
            f = f3;
            f2 = 0.0f;
        }
        if (LanguageUtil.bc(this.f10535a)) {
            dVH_(canvas, f2, height, f, height2);
        } else {
            dVF_(canvas, f2, height, f, height2);
        }
    }

    private void dVF_(Canvas canvas, float f, float f2, float f3, float f4) {
        int i = (int) (this.l * this.j);
        float f5 = this.o;
        canvas.drawRoundRect(f, f2, f3, f4, f5, f5, this.c);
        float max = Math.max(Math.min(this.l - (this.f.getWidth() / 2.0f), i), f);
        float f6 = this.o;
        canvas.drawRoundRect(f, f2, max, f4, f6, f6, this.i);
        dVG_(canvas, i, false);
    }

    private void dVH_(Canvas canvas, float f, float f2, float f3, float f4) {
        int i = this.l;
        int i2 = (int) (i * this.j);
        float f5 = i;
        float f6 = this.o;
        canvas.drawRoundRect(f5 - f, f2, f5 - f3, f4, f6, f6, this.c);
        float min = Math.min(this.l - (this.f.getWidth() / 2.0f), i2);
        float f7 = this.l;
        float max = Math.max(min, f);
        float f8 = this.o;
        canvas.drawRoundRect(f7 - f, f2, f7 - max, f4, f8, f8, this.i);
        dVG_(canvas, i2, true);
    }

    private void dVG_(Canvas canvas, int i, boolean z) {
        float width;
        float f = i;
        if (f <= this.f.getWidth() / 2.0f) {
            width = 0.0f;
        } else if (f >= this.l - (this.f.getWidth() / 2.0f)) {
            width = this.l - this.f.getWidth();
        } else {
            width = f - (this.f.getWidth() / 2.0f);
        }
        if (z) {
            canvas.drawBitmap(this.f, (this.l - width) - r7.getWidth(), 0.0f, this.e);
        } else {
            canvas.drawBitmap(this.f, width, 0.0f, this.e);
        }
    }

    private Bitmap getAchieveBitmap() {
        return BitmapFactory.decodeResource(this.f10535a.getResources(), d);
    }
}
