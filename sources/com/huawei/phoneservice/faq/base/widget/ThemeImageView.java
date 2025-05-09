package com.huawei.phoneservice.faq.base.widget;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.huawei.phoneservice.faq.base.util.b;
import com.huawei.phoneservice.faq.base.util.i;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes9.dex */
public class ThemeImageView extends AppCompatImageView {
    private static final Set<Integer> b = new HashSet();
    static final String c = "ThemeImageView";

    /* renamed from: a, reason: collision with root package name */
    private Bitmap f8246a;
    private Integer d;
    private int e;
    private Paint f;
    private Bitmap g;
    private Bitmap h;
    private int i;
    private Integer j;

    public void a() {
        if (this.f8246a == null && getDrawable() != null) {
            this.f8246a = cdF_(getDrawable());
        }
        Bitmap bitmap = this.f8246a;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        try {
            super.setImageDrawable(getImageDrawable());
        } catch (IllegalArgumentException e) {
            i.c(c, e + "setImageDrawable error");
        }
    }

    public ThemeImageView b(int i) {
        this.j = Integer.valueOf(i);
        return this;
    }

    public ThemeImageView d(int i) {
        this.d = Integer.valueOf(i);
        return this;
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        setImageDrawable(getResources().getDrawable(i));
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (this.i != 0 && drawable != null && drawable.getConstantState() != null && cdG_(drawable) && !(drawable instanceof StateListDrawable)) {
            try {
                Bitmap cdF_ = cdF_(drawable);
                this.f8246a = cdF_;
                if (cdF_ != null) {
                    drawable = getImageDrawable();
                }
            } catch (IllegalArgumentException e) {
                i.c(c, e + "setImageDrawable error");
            }
        }
        super.setImageDrawable(drawable);
    }

    public ColorFilter getPressedFilter() {
        Integer num = this.j;
        return new PorterDuffColorFilter(num == null ? getResources().getColor(this.i) : num.intValue(), PorterDuff.Mode.SRC_IN);
    }

    public ColorFilter getNormalFilter() {
        Integer num = this.d;
        return new PorterDuffColorFilter(num == null ? getResources().getColor(this.e) : num.intValue(), PorterDuff.Mode.SRC_IN);
    }

    public static StateListDrawable cdI_(Drawable drawable, Drawable drawable2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_enabled, R.attr.state_pressed}, drawable2);
        stateListDrawable.addState(new int[0], drawable);
        return stateListDrawable;
    }

    private void cdH_(Context context, AttributeSet attributeSet) {
        int intValue;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{com.huawei.health.R.attr._2131100894_res_0x7f0604de, com.huawei.health.R.attr._2131100957_res_0x7f06051d});
            this.e = obtainStyledAttributes.getResourceId(0, com.huawei.health.R.color._2131297642_res_0x7f09056a);
            this.i = obtainStyledAttributes.getResourceId(1, 0);
            obtainStyledAttributes.recycle();
        }
        if (this.e != 0) {
            d(getResources().getColor(this.e));
        }
        if (this.i == 0) {
            if (this.d != null) {
                intValue = (int) (r3.intValue() * 0.8d);
            }
            Paint paint = new Paint();
            this.f = paint;
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            a();
        }
        intValue = getResources().getColor(this.i);
        b(intValue);
        Paint paint2 = new Paint();
        this.f = paint2;
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        a();
    }

    private Drawable getImageDrawable() throws IllegalArgumentException {
        Paint paint = new Paint(1);
        if (this.h == null) {
            this.h = Bitmap.createBitmap(this.f8246a.getWidth(), this.f8246a.getHeight(), this.f8246a.getConfig());
        }
        if (this.g == null) {
            this.g = Bitmap.createBitmap(this.f8246a.getWidth(), this.f8246a.getHeight(), this.f8246a.getConfig());
        }
        Canvas canvas = new Canvas(this.h);
        paint.setColorFilter(getNormalFilter());
        canvas.drawPaint(this.f);
        canvas.drawBitmap(this.f8246a, new Matrix(), paint);
        canvas.setBitmap(this.g);
        paint.setColorFilter(getPressedFilter());
        canvas.drawPaint(this.f);
        canvas.drawBitmap(this.f8246a, new Matrix(), paint);
        if (isInEditMode()) {
            return cdI_(new BitmapDrawable((Resources) null, this.h), new BitmapDrawable((Resources) null, this.g));
        }
        Resources resources = getResources();
        return cdI_(new BitmapDrawable(resources, this.h), new BitmapDrawable(resources, this.g));
    }

    private boolean cdG_(Drawable drawable) {
        Set<Integer> set = b;
        synchronized (set) {
            if (!b.b(set) && drawable != null && drawable.getConstantState() != null) {
                Iterator<Integer> it = set.iterator();
                while (it.hasNext()) {
                    Drawable drawable2 = getResources().getDrawable(it.next().intValue());
                    if (drawable2 != null && drawable.getConstantState().equals(drawable2.getConstantState())) {
                        return false;
                    }
                }
                return true;
            }
            return true;
        }
    }

    public static Bitmap cdF_(Drawable drawable) {
        Bitmap bitmap = null;
        if (drawable == null) {
            return null;
        }
        try {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (IllegalArgumentException unused) {
            i.a(c, "drawableToBitmap IllegalArgumentException");
            return bitmap;
        }
    }

    public ThemeImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = null;
        this.j = null;
        cdH_(context, attributeSet);
    }

    public ThemeImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = null;
        this.j = null;
        cdH_(context, attributeSet);
    }

    public ThemeImageView(Context context) {
        super(context);
        this.d = null;
        this.j = null;
    }
}
