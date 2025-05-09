package com.huawei.ui.main.stories.me.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Locale;

/* loaded from: classes9.dex */
public class CircleImageView extends ImageView {

    /* renamed from: a, reason: collision with root package name */
    private static final ImageView.ScaleType f10375a = ImageView.ScaleType.CENTER_CROP;
    private static final Bitmap.Config d = Bitmap.Config.ARGB_8888;
    private ColorFilter b;
    private Bitmap c;
    private int e;
    private int f;
    private final RectF g;
    private BitmapShader h;
    private float i;
    private final Paint j;
    private final RectF k;
    private int l;
    private int m;
    private float n;
    private final Paint o;
    private boolean p;
    private boolean q;
    private final Matrix r;

    public CircleImageView(Context context) {
        super(context);
        this.k = new RectF();
        this.g = new RectF();
        this.r = new Matrix();
        this.o = new Paint();
        this.j = new Paint();
        this.m = -3355444;
        this.l = 6;
        a();
    }

    public CircleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = new RectF();
        this.g = new RectF();
        this.r = new Matrix();
        this.o = new Paint();
        this.j = new Paint();
        this.m = -3355444;
        this.l = 6;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099649_res_0x7f060001, R.attr._2131099650_res_0x7f060002}, i, 0);
        if (obtainStyledAttributes == null) {
            LogUtil.h("CircleImageView", "Return for null.");
            return;
        }
        this.l = obtainStyledAttributes.getDimensionPixelSize(1, 6);
        this.m = obtainStyledAttributes.getColor(0, -3355444);
        obtainStyledAttributes.recycle();
        a();
    }

    public int getBorderWidth() {
        return this.l;
    }

    public void setBorderWidth(int i) {
        if (i == this.l) {
            return;
        }
        this.l = i;
        d();
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return f10375a;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            LogUtil.h("CircleImageView", "scaleType is null");
        } else {
            if (scaleType == f10375a) {
                return;
            }
            LogUtil.c("CircleImageView", "setScaleType not supported = ", scaleType);
            throw new IllegalArgumentException(String.format(Locale.ENGLISH, "ScaleType %s not supported.", scaleType));
        }
    }

    @Override // android.widget.ImageView
    public void setAdjustViewBounds(boolean z) {
        if (z) {
            LogUtil.c("CircleImageView", "adjustViewBounds not supported.");
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getDrawable() == null) {
            return;
        }
        float width = getWidth() / 2;
        float height = getHeight() / 2;
        canvas.drawCircle(width, height, this.n, this.o);
        if (this.l != 0) {
            canvas.drawCircle(width, height, this.i, this.j);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        d();
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        this.c = dOF_(getDrawable());
        d();
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        if (uri == null) {
            LogUtil.h("CircleImageView", "uri is null");
            return;
        }
        super.setImageURI(uri);
        this.c = dOF_(getDrawable());
        d();
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            LogUtil.h("CircleImageView", "bitmap is null");
            return;
        }
        super.setImageBitmap(bitmap);
        this.c = bitmap;
        d();
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (drawable == null) {
            LogUtil.h("CircleImageView", "drawable is null");
            return;
        }
        super.setImageDrawable(drawable);
        this.c = dOF_(drawable);
        d();
    }

    @Override // android.widget.ImageView
    public void setColorFilter(ColorFilter colorFilter) {
        Paint paint;
        if (colorFilter == null) {
            LogUtil.h("CircleImageView", "drawable is null");
        } else {
            if (colorFilter == this.b || (paint = this.o) == null) {
                return;
            }
            this.b = colorFilter;
            paint.setColorFilter(colorFilter);
            invalidate();
        }
    }

    private void a() {
        super.setScaleType(f10375a);
        this.p = true;
        if (this.q) {
            d();
            this.q = false;
        }
    }

    private void d() {
        if (!this.p) {
            this.q = true;
            return;
        }
        if (this.c == null) {
            return;
        }
        this.h = new BitmapShader(this.c, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        this.o.setAntiAlias(true);
        this.o.setShader(this.h);
        this.j.setStyle(Paint.Style.STROKE);
        this.j.setAntiAlias(true);
        this.j.setColor(this.m);
        this.j.setStrokeWidth(this.l);
        this.e = this.c.getHeight();
        this.f = this.c.getWidth();
        this.g.set(0.0f, 0.0f, getWidth(), getHeight());
        this.i = Math.min((this.g.height() - this.l) / 2.0f, (this.g.width() - this.l) / 2.0f);
        RectF rectF = this.k;
        float f = this.l;
        rectF.set(f, f, this.g.width() - this.l, this.g.height() - this.l);
        this.n = Math.min(this.k.height() / 2.0f, this.k.width() / 2.0f);
        b();
        invalidate();
    }

    private Bitmap dOF_(Drawable drawable) {
        Bitmap createBitmap;
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            if (drawable instanceof ColorDrawable) {
                createBitmap = Bitmap.createBitmap(2, 2, d);
            } else {
                createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), d);
            }
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (OutOfMemoryError e) {
            LogUtil.c("CircleImageView", "OutOfMemoryError = ", e.getMessage());
            return null;
        }
    }

    private void b() {
        float width;
        float height;
        this.r.set(null);
        float f = 0.0f;
        if (this.f * this.k.height() > this.k.width() * this.e) {
            width = this.k.height() / this.e;
            height = 0.0f;
            f = (this.k.width() - (this.f * width)) * 0.5f;
        } else {
            width = this.k.width() / this.f;
            height = (this.k.height() - (this.e * width)) * 0.5f;
        }
        this.r.setScale(width, width);
        Matrix matrix = this.r;
        int i = this.l;
        matrix.postTranslate(((int) (f + 0.5f)) + i, ((int) (height + 0.5f)) + i);
        this.h.setLocalMatrix(this.r);
    }
}
