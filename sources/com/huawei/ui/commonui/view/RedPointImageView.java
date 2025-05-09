package com.huawei.ui.commonui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.core.internal.view.SupportMenu;
import com.huawei.ui.commonui.R$drawable;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class RedPointImageView extends ImageView {

    /* renamed from: a, reason: collision with root package name */
    private boolean f8976a;
    private boolean b;
    private Paint c;
    private Bitmap d;

    public RedPointImageView(Context context) {
        super(context);
        this.f8976a = false;
        b();
    }

    public RedPointImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8976a = false;
        b();
    }

    private void b() {
        Paint paint = new Paint();
        this.c = paint;
        paint.setStyle(Paint.Style.FILL);
        this.c.setColor(SupportMenu.CATEGORY_MASK);
        this.c.setAntiAlias(true);
        this.d = cMY_(getContext(), R$drawable.settings_red_point_bg);
    }

    private static Bitmap cMY_(Context context, int i) {
        Drawable drawable = context.getResources().getDrawable(i);
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.b) {
            if (this.f8976a) {
                float width = (getWidth() / 2) + (this.d.getWidth() * 2);
                if (LanguageUtil.bc(getContext())) {
                    width = (getWidth() / 2) - (this.d.getWidth() * 2);
                }
                canvas.drawCircle(width, (this.d.getHeight() / 2.0f) + (this.d.getWidth() * 2), this.d.getWidth() / 2.0f, this.c);
                return;
            }
            float width2 = getWidth() - (this.d.getWidth() / 2.0f);
            if (LanguageUtil.bc(getContext())) {
                width2 = this.d.getWidth() / 2.0f;
            }
            canvas.drawCircle(width2, this.d.getHeight() / 2.0f, this.d.getWidth() / 2.0f, this.c);
        }
    }

    public void e(boolean z) {
        this.b = z;
        postInvalidate();
    }

    public void b(boolean z) {
        this.f8976a = z;
        postInvalidate();
    }
}
