package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import com.huawei.health.R;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;

/* loaded from: classes7.dex */
public class snl extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    private int f17138a;
    private HwViewPager b;
    private int c;
    private Bitmap d;
    private Paint e;
    private int g;
    private int h;
    private int i;
    private int j;

    public snl(int i, HwViewPager hwViewPager) {
        this.b = hwViewPager;
        Paint paint = new Paint();
        this.e = paint;
        paint.setColor(i);
        this.e.setMaskFilter(new BlurMaskFilter(107.0f, BlurMaskFilter.Blur.NORMAL));
        this.e.setAntiAlias(true);
        Context context = hwViewPager.getContext();
        if (context != null) {
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen._2131364526_res_0x7f0a0aae);
            this.g = dimensionPixelSize;
            this.j = dimensionPixelSize / 2;
        }
        this.d = Bitmap.createBitmap(this.g, this.b.getHeight(), Bitmap.Config.RGB_565);
        this.i = this.b.getTop();
        this.h = this.b.getBottom();
    }

    private void eiH_(Canvas canvas, int i) {
        canvas.clipRect(this.c, this.i, i, this.h);
        canvas.drawBitmap(this.d, this.c - this.j, this.i, this.e);
    }

    private void eiI_(Canvas canvas, int i) {
        canvas.clipRect(i, this.i, canvas.getWidth() - this.c, this.h);
        canvas.drawBitmap(this.d, (canvas.getWidth() - this.c) - this.j, this.i, this.e);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.b.getAdapter() == null) {
            return;
        }
        if (this.b.isSupportLoop() || this.f17138a != r0.getCount() - 1) {
            if (!this.b.getReverseDrawingOrder()) {
                if (this.b.isRtlLayout()) {
                    eiH_(canvas, this.c - 300);
                    return;
                } else {
                    eiI_(canvas, (canvas.getWidth() - this.c) + 300);
                    return;
                }
            }
            if (this.c > 0) {
                if (this.b.isRtlLayout()) {
                    eiH_(canvas, this.c + 300);
                } else {
                    eiI_(canvas, (canvas.getWidth() - this.c) - 300);
                }
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.e.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        this.d = Bitmap.createBitmap(this.g, this.b.getHeight(), Bitmap.Config.RGB_565);
        this.i = this.b.getTop();
        this.h = this.b.getBottom();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.e.setColorFilter(colorFilter);
    }

    public void c(int i, int i2) {
        this.f17138a = i;
        if (!this.b.getReverseDrawingOrder()) {
            this.c = i2;
        } else if (i2 < 0) {
            this.c = this.b.getWidth() + i2;
        } else {
            this.c = i2;
        }
        invalidateSelf();
    }
}
